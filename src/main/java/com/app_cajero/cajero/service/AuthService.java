package com.app_cajero.cajero.service;

import com.app_cajero.cajero.dto.ClienteResponse;
import com.app_cajero.cajero.model.Cliente;
import com.app_cajero.cajero.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Realiza el login de un cliente usando documento y clave
     * @param documento Documento del cliente
     * @param clave Clave de 4 dígitos
     * @return Cliente si login correcto
     * @throws RuntimeException si usuario no existe, clave incorrecta
     *         o usuario bloqueado
     */

    public ClienteResponse login(String documento, String clave) {

        Cliente cliente = clienteRepository.findByDocumento(documento)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Validar bloqueo por intentos fallidos
        if (cliente.getBloqueadoHasta() != null &&
            cliente.getBloqueadoHasta().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Usuario bloqueado. Intente en 10 minutos.");
        }

        // Clave incorrecta
        if (!cliente.getClave().equals(clave)) {
            // Incrementar contador de intentos fallidos
            cliente.setIntentosFallidos(cliente.getIntentosFallidos() + 1);
            // Si supera 3 intentos, bloquear usuario por 10 minutos
            if (cliente.getIntentosFallidos() >= 3) {
                cliente.setBloqueadoHasta(LocalDateTime.now().plusMinutes(10));
                cliente.setIntentosFallidos(0);
            }

            clienteRepository.save(cliente);
            throw new RuntimeException("Clave incorrecta");
        }

        // Login correcto: resetear intentos fallidos y actualizar última actividad
        cliente.setIntentosFallidos(0);
        cliente.setUltimaActividad(LocalDateTime.now());
        clienteRepository.save(cliente);

        return new ClienteResponse(
                cliente.getNombre(),
                cliente.getDocumento(),
                cliente.getSaldo()
        );
    }
}