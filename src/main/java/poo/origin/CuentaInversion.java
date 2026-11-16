/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

/**
 *
 * @author User
 */
public class CuentaInversion extends CuentaBancaria {
    //Atributos
    private double tasaRendimiento;
    private int plazoDias;
    //Metodos
    public CuentaInversion(String numeroCuenta, String titular,
            double tasaRendimiento, int plazoDias) {
        super(numeroCuenta, titular);
        this.tasaRendimiento = tasaRendimiento;
        this.plazoDias = plazoDias;
    }

    public double calcularRendimiento() {
        return consultarSaldo()
                * tasaRendimiento
                * plazoDias / 360;
    }

    @Override
    public double calcularComision(double monto,boolean autenticacionFuerte) {
        if (autenticacionFuerte) {
            return monto * 0.005;
        }
        return monto * 0.01;
    }

    public double getTasaRendimiento() {
        return tasaRendimiento;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public void mostrarRendimiento() {
        System.out.printf("Tasa de rendimiento: %.2f%%%n",tasaRendimiento * 100);
        System.out.println("Plazo: " + plazoDias + " dias");
        System.out.printf("Rendimiento estimado: $%.2f%n",calcularRendimiento());
    }
}
