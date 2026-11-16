/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

/**
 *
 * @author User
 */
public class Usuario {
    //Atributos
    private String idUsuario;
    private String nombre;
    private String correo;
    private Billetera billetera;
    //Metodos
    public Usuario(String idUsuario, String nombre, String correo,Billetera billetera) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.billetera = billetera;
    }

    public Billetera consultarBilletera() {
        return billetera;
    }

    public void actualizarCorreo(String nuevoCorreo) {
        correo = nuevoCorreo;
    }

    public void mostrarInformacion() {
        System.out.println("\nINFORMACION DEL USUARIO");
        System.out.println("ID: " + idUsuario);
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Billetera: " + billetera.getIdBilletera());
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
}

