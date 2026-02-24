package com.app_cajero.cajero.model;

import jakarta.persistence.*;

@Entity
public class Cajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int billetes100000;
    private int billetes50000;
    private int billetes20000;
    private int billetes10000;

    public Cajero() {}

    public Cajero(int b100, int b50, int b20, int b10) {
        this.billetes100000 = b100;
        this.billetes50000 = b50;
        this.billetes20000 = b20;
        this.billetes10000 = b10;
    }

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public int getBilletes100000() {
        return billetes100000;
    }

    public void setBilletes100000(int billetes100000) {
        this.billetes100000 = billetes100000;
    }

    public int getBilletes50000() {
        return billetes50000;
    }

    public void setBilletes50000(int billetes50000) {
        this.billetes50000 = billetes50000;
    }

    public int getBilletes20000() {
        return billetes20000;
    }

    public void setBilletes20000(int billetes20000) {
        this.billetes20000 = billetes20000;
    }

    public int getBilletes10000() {
        return billetes10000;
    }

    public void setBilletes10000(int billetes10000) {
        this.billetes10000 = billetes10000;
    }

    // Métodos auxiliares

    public boolean verificarDisponibilidad() {
        return billetes100000 > 0 &&
               billetes50000 > 0 &&
               billetes20000 > 0 &&
               billetes10000 > 0;
    }

    public String faltantes() {
        String f = "";
        if (billetes100000 == 0) f += "Faltan billetes de 100000 ";
        if (billetes50000 == 0) f += "Faltan billetes de 50000 ";
        if (billetes20000 == 0) f += "Faltan billetes de 20000 ";
        if (billetes10000 == 0) f += "Faltan billetes de 10000 ";
        return f;
    }
}