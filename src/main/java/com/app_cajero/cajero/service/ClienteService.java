package com.app_cajero.cajero.service;

import com.app_cajero.cajero.model.Cliente;
import com.app_cajero.cajero.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CajeroService cajeroService;

     /**
     * Retira dinero de la cuenta del cliente.
     * @param documento Documento del cliente que realiza el retiro
     * @param valor Monto a retirar
     * @return Mensaje con saldo actualizado
     * @throws RuntimeException si saldo insuficiente, usuario bloqueado,
     *         cajero sin billetes o sesión expirada por inactividad
     */

    @Transactional // Garantiza que el retiro sea una operación atómica. Si algo falla, se revierte toda la transacción.
    public String retirar(String documento, double valor) {
    // Buscar cliente por documento
    Cliente cliente = clienteRepository.findByDocumento(documento)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

     // Validar inactividad (cierre automático por 180s)
    if (cliente.getUltimaActividad() != null &&
    cliente.getUltimaActividad().plusSeconds(180)
            .isBefore(LocalDateTime.now())) {
    throw new RuntimeException("Sesión expirada por inactividad.");
}
     // Validar si usuario está bloqueado
    if (cliente.getBloqueadoHasta() != null &&
        cliente.getBloqueadoHasta().isAfter(LocalDateTime.now())) {
        throw new RuntimeException("Usuario bloqueado. No puede realizar operaciones.");
    }

    // Validar saldo
    if (cliente.getSaldo() < valor) {
        throw new RuntimeException("Saldo insuficiente");
    }

    // Validar disponibilidad de billetes en el cajero
    cajeroService.procesarRetiro(valor);

    // Descontar saldo del cliente
    cliente.setSaldo(cliente.getSaldo() - valor);

    // Actualizar tiempo de última actividad del cliente
    cliente.setUltimaActividad(LocalDateTime.now());
    // Guardar cambios en la base de datos
    clienteRepository.save(cliente);

    return "Retiro exitoso. Saldo actual: " + cliente.getSaldo() + 
       ". Sesión finalizada.";
}
    
     /**
     * Transfiere dinero de un cliente a otro.
     * @param origen Documento del cliente que envía dinero
     * @param destino Documento del cliente que recibe dinero
     * @param valor Monto a transferir
     * @return Mensaje con saldo actualizado del cliente origen
     * @throws RuntimeException si saldo insuficiente, usuario bloqueado,
     *         cliente destino no existe o sesión expirada
     */

    @Transactional
    public String transferir(String documentoOrigen, String documentoDestino, double valor) {

    Cliente origen = clienteRepository.findByDocumento(documentoOrigen)
            .orElseThrow(() -> new RuntimeException("Cliente origen no encontrado"));

    Cliente destino = clienteRepository.findByDocumento(documentoDestino)
            .orElseThrow(() -> new RuntimeException("Cliente destino no encontrado"));

    // Validar inactividad primero
    if (origen.getUltimaActividad() != null &&
    origen.getUltimaActividad().plusSeconds(180)
            .isBefore(LocalDateTime.now())) {
    throw new RuntimeException("Sesión expirada por inactividad.");
    }
    // Validar bloqueo
    if (origen.getBloqueadoHasta() != null &&
        origen.getBloqueadoHasta().isAfter(LocalDateTime.now())) {
        throw new RuntimeException("Usuario bloqueado.");
    }

    // Validar saldo
    if (origen.getSaldo() < valor) {
        throw new RuntimeException("Saldo insuficiente");
    }

    // Transferir
    origen.setSaldo(origen.getSaldo() - valor);
    destino.setSaldo(destino.getSaldo() + valor);

    // Actualizar actividad del cliente que inició la transferencia
    origen.setUltimaActividad(LocalDateTime.now());

    // Guardar cambios
    clienteRepository.save(origen);
    clienteRepository.save(destino);

    return "Transferencia exitosa. Saldo actual: " + origen.getSaldo();
}
    
}