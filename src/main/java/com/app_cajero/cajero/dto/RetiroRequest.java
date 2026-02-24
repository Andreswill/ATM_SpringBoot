package com.app_cajero.cajero.dto;

public class RetiroRequest {
    private String documento;
    private double valor;
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
}
