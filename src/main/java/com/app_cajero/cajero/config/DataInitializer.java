package com.app_cajero.cajero.config;

import com.app_cajero.cajero.model.Cliente;
import com.app_cajero.cajero.model.Cajero;
import com.app_cajero.cajero.repository.ClienteRepository;
import com.app_cajero.cajero.repository.CajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CajeroRepository cajeroRepository;

    @Override
    public void run(String... args) {

        if(clienteRepository.count() == 0) {
            clienteRepository.save(new Cliente("Juan","123","1111",1000000));
            clienteRepository.save(new Cliente("Maria","456","2222",800000));
            clienteRepository.save(new Cliente("Carlos","789","3333",500000));
        }

        if(cajeroRepository.count() == 0) {
            cajeroRepository.save(new Cajero(10,10,10,10));
        }
    }
}
