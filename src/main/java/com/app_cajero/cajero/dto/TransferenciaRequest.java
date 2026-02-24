package com.app_cajero.cajero.dto;

public class TransferenciaRequest {
    private String documentoOrigen;
    private String documentoDestino;
    private double valor;

    // getters y setters
    public String getDocumentoOrigen() {
        return documentoOrigen;
    }

    public void setDocumentoOrigen(String documentoOrigen) {
        this.documentoOrigen = documentoOrigen;
    }

    public String getDocumentoDestino() {
        return documentoDestino;
    }

    public void setDocumentoDestino(String documentoDestino) {
        this.documentoDestino = documentoDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
