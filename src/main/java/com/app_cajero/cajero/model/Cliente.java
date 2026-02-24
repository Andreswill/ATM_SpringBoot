package com.app_cajero.cajero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String documento;
    private String clave;
    private double saldo;

    private int intentosFallidos;
    private LocalDateTime bloqueadoHasta;
    private LocalDateTime ultimaActividad;

    public LocalDateTime getUltimaActividad() {
        return ultimaActividad;
    }

    public void setUltimaActividad(LocalDateTime ultimaActividad) {
        this.ultimaActividad = ultimaActividad;
    }

    public Cliente() {}

    public Cliente(String nombre, String documento, String clave, double saldo) {
        this.nombre = nombre;
        this.documento = documento;
        this.clave = clave;
        this.saldo = saldo;
    }


    public void retirar(double valor) {
        if (saldo < valor) {
            throw new RuntimeException("Saldo insuficiente");
        }
        this.saldo -= valor;
    }

    // GETTERS Y SETTERS
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getClave() { return clave; }
    public double getSaldo() { return saldo; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public LocalDateTime getBloqueadoHasta() { return bloqueadoHasta; }

    public void setIntentosFallidos(int intentosFallidos) { this.intentosFallidos = intentosFallidos; }
    public void setBloqueadoHasta(LocalDateTime bloqueadoHasta) { this.bloqueadoHasta = bloqueadoHasta; }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
