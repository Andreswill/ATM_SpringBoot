package com.app_cajero.cajero.controller;

import com.app_cajero.cajero.dto.LoginRequest;
import com.app_cajero.cajero.dto.RetiroRequest;
import com.app_cajero.cajero.dto.TransferenciaRequest;
import com.app_cajero.cajero.service.AuthService;
import com.app_cajero.cajero.service.CajeroService;
import com.app_cajero.cajero.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CajeroController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CajeroService cajeroService;

    /**
     * Endpoint de login de clientes
     * @param request JSON con documento y clave
     * @return Cliente logueado o mensaje de error
     */

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    try {
        return ResponseEntity.ok(
                authService.login(request.getDocumento(), request.getClave())
        );

    } catch (RuntimeException e) {

        if (e.getMessage().contains("bloqueado")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
    }
    }
    /**
     * Endpoint para retirar dinero de un cliente
     * @param request JSON con documento y valor a retirar
     * @return Mensaje con saldo actualizado o error
     */

    @PostMapping("/cajero/retiro")
    public ResponseEntity<?> retiro(@RequestBody RetiroRequest request) {

    try {
        return ResponseEntity.ok(
                clienteService.retirar(request.getDocumento(), request.getValor())
        );

    } catch (RuntimeException e) {

        if (e.getMessage().contains("bloqueado")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

        if (e.getMessage().contains("Saldo")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        if (e.getMessage().contains("Monto")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        if (e.getMessage().contains("billetes")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}

   /**
     * Endpoint para consultar el estado del cajero
     * @return String con la cantidad de billetes disponibles
     */
    @GetMapping("/cajero/estado")
    public ResponseEntity<?> estado() {
        return ResponseEntity.ok(cajeroService.estadoCajero());
    }

    /**
     * Endpoint para transferir dinero entre clientes
     * @param request JSON con documentoOrigen, documentoDestino y valor
     * @return Mensaje con saldo actualizado o error
     */

    @PostMapping("/cajero/transferencia")
    public ResponseEntity<?> transferencia(@RequestBody TransferenciaRequest request) {

    try {
        return ResponseEntity.ok(
                clienteService.transferir(
                        request.getDocumentoOrigen(),
                        request.getDocumentoDestino(),
                        request.getValor())
        );

    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}
