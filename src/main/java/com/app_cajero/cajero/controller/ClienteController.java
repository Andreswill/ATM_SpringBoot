package com.app_cajero.cajero.controller;

import com.app_cajero.cajero.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/retirar")
    public String retirar(@RequestParam String documento,
                          @RequestParam double valor) {
        clienteService.retirar(documento, valor);
        return "Retiro exitoso";
    }

   
}

