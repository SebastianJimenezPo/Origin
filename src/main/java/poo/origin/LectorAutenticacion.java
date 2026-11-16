/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

/**
 *
 * @author User
 */
public abstract class LectorAutenticacion {
    //Atributos
    private String idDispositivo;
    private int nivelSeguridad;
    private String estado;
    //Metodos
    public LectorAutenticacion(String idDispositivo, int nivelSeguridad) {
        this.idDispositivo = idDispositivo;
        this.nivelSeguridad = nivelSeguridad;
        this.estado = "ACTIVO";
    }

    public boolean verificarEstado() {
        return estado.equalsIgnoreCase("ACTIVO");
    }

    public abstract boolean validarIdentidad(
            CredencialAutenticacion credencial);

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public int getNivelSeguridad() {
        return nivelSeguridad;
    }

    public String getEstado() {
        return estado;
    }

    public void cambiarEstado(String nuevoEstado) {
        estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Lector: " + idDispositivo
                + " | Nivel de seguridad: " + nivelSeguridad
                + " | Estado: " + estado;
    }
}
