package com.app_cajero.cajero.dto;


/**
 * DTO para recibir datos de login
 * Contiene documento y clave del cliente
 */

public class LoginRequest {
    private String documento;
    private String clave;
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
}
