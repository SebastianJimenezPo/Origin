/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

/**
 *
 * @author User
 */
public class CuentaCorriente extends CuentaBancaria {
    //Atributos
    private double cupoSobregiro;
    //Metodos
    public CuentaCorriente(String numeroCuenta, String titular,double cupoSobregiro) {
        super(numeroCuenta, titular);
        this.cupoSobregiro = cupoSobregiro;
    }

    @Override
    boolean puedeDebitar(double monto) {
        return getEstado().equalsIgnoreCase("ACTIVA")
                && monto > 0
                && consultarSaldo() - monto >= -cupoSobregiro;
    }

    public double consultarCupoDisponible() {
        return cupoSobregiro + consultarSaldo();
    }

    @Override
    public double calcularComision(double monto,
            boolean autenticacionFuerte) {
        if (autenticacionFuerte) {
            return monto * 0.003;
        }
        return monto * 0.008;
    }

    public double getCupoSobregiro() {
        return cupoSobregiro;
    }

    public void mostrarCupo() {
        System.out.println("Cupo de sobregiro: $" + cupoSobregiro);
        System.out.println("Cupo disponible: $" + consultarCupoDisponible());
    }
}

