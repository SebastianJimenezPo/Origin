/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class CredencialAutenticacion {
    //Atributos
    private String tipo;
    private String identificador;
    private LocalDate fechaExpiracion;
    private double nivelCoincidencia;
    //Metodos
    // Constructor principal
    public CredencialAutenticacion(String tipo, String identificador,
            LocalDate fechaExpiracion, double nivelCoincidencia) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.fechaExpiracion = fechaExpiracion;
        this.nivelCoincidencia = nivelCoincidencia;
    }

    // Constructor sobrecargado
    public CredencialAutenticacion(String tipo, String identificador,
            LocalDate fechaExpiracion) {
        this(tipo, identificador, fechaExpiracion, 100);
    }

    public boolean estaVigente() {
        return !LocalDate.now().isAfter(fechaExpiracion);
    }

    public String getTipo() {
        return tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public double getNivelCoincidencia() {
        return nivelCoincidencia;
    }

    @Override
    public String toString() {
        return "Credencial: " + tipo
                + " | Identificador: " + identificador
                + " | Vence: " + fechaExpiracion;
    }
}

