package com.app_cajero.cajero.dto;

public class LoginResponse {

    private String nombre;
    private String documento;
    private double saldo;

    public LoginResponse(String nombre, String documento, double saldo) {
        this.nombre = nombre;
        this.documento = documento;
        this.saldo = saldo;
    }

    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public double getSaldo() { return saldo; }
}
