/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

/**
 *
 * @author User
 */
public class LectorReconocimientoFacial extends LectorAutenticacion {
    //Atributos
    private double toleranciaCoincidencia;
    //Metodos
    public LectorReconocimientoFacial(String idDispositivo,
            double toleranciaCoincidencia) {
        super(idDispositivo, 3);
        this.toleranciaCoincidencia = toleranciaCoincidencia;
    }

    @Override
    public boolean validarIdentidad(CredencialAutenticacion credencial) {
        if (credencial == null) {
            return false;
        }
        return verificarEstado()
                && credencial.estaVigente()
                && credencial.getTipo().equalsIgnoreCase("FACIAL")
                && credencial.getNivelCoincidencia()
                >= toleranciaCoincidencia;
    }

    public double getToleranciaCoincidencia() {
        return toleranciaCoincidencia;
    }
}

