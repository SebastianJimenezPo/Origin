/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

/**
 *
 * @author User
 */
public class CuentaAhorros extends CuentaBancaria {
    //Atributos
    private double metaAhorro;
    //Metodos
    public CuentaAhorros(String numeroCuenta, String titular,double metaAhorro) {
        super(numeroCuenta, titular);
        this.metaAhorro = metaAhorro;
    }

    public void establecerMeta(double nuevaMeta) {
        if (nuevaMeta > 0) {
            metaAhorro = nuevaMeta;
        }
    }

    public double calcularProgreso() {
        if (metaAhorro <= 0) {
            return 0;
        }
        return (consultarSaldo() / metaAhorro) * 100;
    }

    @Override
    public double calcularComision(double monto,
            boolean autenticacionFuerte) {
        if (autenticacionFuerte) {
            return 0;
        }
        return monto * 0.005;
    }

    public double getMetaAhorro() {
        return metaAhorro;
    }

    public void mostrarMeta() {
        System.out.println("Meta de ahorro: $" + metaAhorro);
        System.out.printf("Progreso: %.2f%%%n", calcularProgreso());
    }
}

