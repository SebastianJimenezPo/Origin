/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

/**
 *
 * @author User
 */
public class LectorTokenNFC extends LectorAutenticacion {
    //Atributos
    //Metodos
    public LectorTokenNFC(String idDispositivo) {
        super(idDispositivo, 1);
    }

    @Override
    public boolean validarIdentidad(CredencialAutenticacion credencial) {
        if (credencial == null) {
            return false;
        }
        return verificarEstado()
                && credencial.estaVigente()
                && credencial.getTipo().equalsIgnoreCase("NFC");
    }
}
