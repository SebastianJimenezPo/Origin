/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Billetera {
    //Atributos
    private String idBilletera;
    private LocalDate fechaCreacion;
    private String estado;
    private ArrayList<CuentaBancaria> cuentas;
    //Metodos
    public Billetera(String idBilletera) {
        this.idBilletera = idBilletera;
        this.fechaCreacion = LocalDate.now();
        this.estado = "ACTIVA";
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        if (cuenta == null) {
            return;
        }
        if (buscarCuenta(cuenta.getNumeroCuenta()) != null) {
            System.out.println("La cuenta ya esta registrada.");
            return;
        }
        cuentas.add(cuenta);
        System.out.println("Cuenta agregada correctamente.");
    }

    public void eliminarCuenta(String numeroCuenta) {
        CuentaBancaria cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            cuentas.remove(cuenta);
            System.out.println("Cuenta eliminada.");
        } else {
            System.out.println("No se encontro la cuenta.");
        }
    }

    public CuentaBancaria buscarCuenta(String numeroCuenta) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public void listarCuentas() {
        System.out.println("\nCUENTAS DE LA BILLETERA");
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
            return;
        }
        for (CuentaBancaria cuenta : cuentas) {
            System.out.println(cuenta);
        }
    }

    public void cambiarEstado(String nuevoEstado) {
        estado = nuevoEstado;
    }

    public String getIdBilletera() {
        return idBilletera;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public ArrayList<CuentaBancaria> getCuentas() {
        return new ArrayList<>(cuentas);
    }
}

