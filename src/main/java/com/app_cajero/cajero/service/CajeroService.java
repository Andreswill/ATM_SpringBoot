package com.app_cajero.cajero.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.app_cajero.cajero.model.Cajero;
import com.app_cajero.cajero.repository.CajeroRepository;


@Service
public class CajeroService {
    @Autowired
    private CajeroRepository cajeroRepository;
   
    public String estadoCajero() {
    Cajero cajero = cajeroRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Cajero no encontrado"));

    if (cajero.verificarDisponibilidad()) {
        return "Hay disponibilidad de todos los billetes.";
    } else {
        return cajero.faltantes();
    }
}
    public void procesarRetiro(double valor) {

    if (valor % 10000 != 0) {
        throw new RuntimeException("Monto inválido (solo múltiplos de 10000)");
    }

    Cajero cajero = cajeroRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Cajero no encontrado"));

    int restante = (int) valor;

    int usar100 = Math.min(restante / 100000, cajero.getBilletes100000());
    restante -= usar100 * 100000;

    int usar50 = Math.min(restante / 50000, cajero.getBilletes50000());
    restante -= usar50 * 50000;

    int usar20 = Math.min(restante / 20000, cajero.getBilletes20000());
    restante -= usar20 * 20000;

    int usar10 = Math.min(restante / 10000, cajero.getBilletes10000());
    restante -= usar10 * 10000;

    if (restante > 0) {
        throw new RuntimeException("No hay billetes suficientes");
    }

    // Descontar en la entidad
    cajero.setBilletes100000(cajero.getBilletes100000() - usar100);
    cajero.setBilletes50000(cajero.getBilletes50000() - usar50);
    cajero.setBilletes20000(cajero.getBilletes20000() - usar20);
    cajero.setBilletes10000(cajero.getBilletes10000() - usar10);

    // Guardar en BD
    cajeroRepository.save(cajero);
}
}