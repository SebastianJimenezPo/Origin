/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

/**
 *
 * @author User
 */
public abstract class CuentaBancaria {
    //Atributos
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private String estado;
    //Metodos
    public CuentaBancaria(String numeroCuenta, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0;
        this.estado = "ACTIVA";
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("El monto debe ser positivo.");
            return;
        }
        if (!estado.equalsIgnoreCase("ACTIVA")) {
            System.out.println("La cuenta no está activa.");
            return;
        }
        saldo += monto;
    }

    public boolean retirar(double monto, boolean autenticacionFuerte) {
        if (monto <= 0 || !estado.equalsIgnoreCase("ACTIVA")) {
            return false;
        }
        double comision = calcularComision(monto, autenticacionFuerte);
        double total = monto + comision;
        if (!puedeDebitar(total)) {
            return false;
        }
        aplicarDebito(total);
        return true;
    }

    public boolean transferir(CuentaBancaria destino, double monto,
            boolean autenticacionFuerte) {
        if (destino == null || destino == this || monto <= 0) {
            return false;
        }
        if (!estado.equalsIgnoreCase("ACTIVA")
                || !destino.getEstado().equalsIgnoreCase("ACTIVA")) {
            return false;
        }
        double comision = calcularComision(monto, autenticacionFuerte);
        double total = monto + comision;
        if (!puedeDebitar(total)) {
            return false;
        }
        aplicarDebito(total);
        destino.aplicarCredito(monto);
        return true;
    }

    public abstract double calcularComision(
            double monto, boolean autenticacionFuerte);
    boolean puedeDebitar(double monto) {
        return estado.equalsIgnoreCase("ACTIVA")
                && monto > 0
                && saldo >= monto;
    }
    
    void aplicarDebito(double monto) {
        saldo -= monto;
    }

    void aplicarCredito(double monto) {
        saldo += monto;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public String getEstado() {
        return estado;
    }

    public void cambiarEstado(String nuevoEstado) {
        estado = nuevoEstado;
    }

    public void mostrarInformacion() {
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: $" + String.format("%.2f", saldo));
        System.out.println("Estado: " + estado);
    }

    @Override
    public String toString() {
        return "Cuenta " + numeroCuenta
                + " | Titular: " + titular
                + " | Saldo: $" + String.format("%.2f", saldo);
    }
}

