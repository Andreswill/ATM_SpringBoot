package com.app_cajero.cajero.controller;

import com.app_cajero.cajero.dto.ClienteResponse;
import com.app_cajero.cajero.dto.LoginResponse;
import com.app_cajero.cajero.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

   @PostMapping("/login")
public LoginResponse login(@RequestParam String documento,
                           @RequestParam String clave) {

    ClienteResponse cliente = authService.login(documento, clave);

    return new LoginResponse(
            cliente.getNombre(),
            cliente.getDocumento(),
            cliente.getSaldo()
    );
}}

