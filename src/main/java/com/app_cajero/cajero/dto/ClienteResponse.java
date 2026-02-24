package com.app_cajero.cajero.dto;

public class ClienteResponse {
    private String nombre;
    private String documento;
    private double saldo;

    public ClienteResponse(String nombre, String documento, double saldo) {
        this.nombre = nombre;
        this.documento = documento;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
