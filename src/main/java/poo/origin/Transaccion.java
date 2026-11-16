/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.origin;

import java.time.LocalDateTime;

/**
 *
 * @author User
 */
public class Transaccion {
    //Atributos
    private String idTransaccion;
    private String tipo;
    private double monto;
    private LocalDateTime fecha;
    private String estado;
    private boolean autenticacionRequerida;
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;
    private LectorAutenticacion lector;
    private CredencialAutenticacion credencial;
    //Metodos
    public Transaccion(String idTransaccion, String tipo, double monto,
            boolean autenticacionRequerida,CuentaBancaria cuentaOrigen,
            CuentaBancaria cuentaDestino,LectorAutenticacion lector,
            CredencialAutenticacion credencial) {
        this.idTransaccion = idTransaccion;
        this.tipo = tipo.toUpperCase();
        this.monto = monto;
        this.autenticacionRequerida = autenticacionRequerida;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.lector = lector;
        this.credencial = credencial;
        this.fecha = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    public boolean requiereAutenticacion() {
        return autenticacionRequerida;
    }

    private boolean autenticar() {
        if (!autenticacionRequerida) {
            return true;
        }
        if (lector == null || credencial == null) {
            return false;
        }
        return lector.validarIdentidad(credencial);
    }

    private boolean esAutenticacionFuerte() {
        return lector != null
                && lector.getNivelSeguridad() >= 2;
    }

    public boolean ejecutar() {
        if (!estado.equals("PENDIENTE")) {
            System.out.println("La transaccion ya fue procesada.");
            return false;
        }
        if (monto <= 0) {
            estado = "RECHAZADA";
            return false;
        }
        if (!autenticar()) {
            System.out.println("Autenticacion fallida.");
            estado = "RECHAZADA";
            return false;
        }
        boolean autenticacionFuerte = esAutenticacionFuerte();
        switch (tipo) {
            case "DEPOSITO":
                if (cuentaDestino == null) {
                    estado = "RECHAZADA";
                    return false;
                }
                if (!cuentaDestino.getEstado().equalsIgnoreCase("ACTIVA")) {
                    estado = "RECHAZADA";
                    return false;
                }
                cuentaDestino.aplicarCredito(monto);
                estado = "EXITOSA";
                return true;
            case "RETIRO":
                if (cuentaOrigen == null) {
                    estado = "RECHAZADA";
                    return false;
                }
                double comisionRetiro =
                        cuentaOrigen.calcularComision(
                                monto, autenticacionFuerte);
                double totalRetiro = monto + comisionRetiro;
                if (!cuentaOrigen.puedeDebitar(totalRetiro)) {
                    estado = "RECHAZADA";
                    return false;
                }
                cuentaOrigen.aplicarDebito(totalRetiro);
                estado = "EXITOSA";
                return true;
            case "TRANSFERENCIA":
                if (cuentaOrigen == null || cuentaDestino == null
                        || cuentaOrigen == cuentaDestino) {
                    estado = "RECHAZADA";
                    return false;
                }
                if (!cuentaOrigen.getEstado().equalsIgnoreCase("ACTIVA")
                        || !cuentaDestino.getEstado().equalsIgnoreCase("ACTIVA")) {
                    estado = "RECHAZADA";
                    return false;
                }
                double comisionTransferencia =
                        cuentaOrigen.calcularComision(
                                monto, autenticacionFuerte);
                double totalTransferencia =
                        monto + comisionTransferencia;
                if (!cuentaOrigen.puedeDebitar(totalTransferencia)) {
                    estado = "RECHAZADA";
                    return false;
                }
                cuentaOrigen.aplicarDebito(totalTransferencia);
                cuentaDestino.aplicarCredito(monto);
                estado = "EXITOSA";
                return true;
            default:
                System.out.println("Tipo de transaccion desconocido.");
                estado = "RECHAZADA";
                return false;
        }
    }

    public void cancelar() {
        if (estado.equals("PENDIENTE")) {
            estado = "CANCELADA";
        }
    }

    public String consultarEstado() {
        return estado;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void mostrarInformacion() {
        System.out.println("\nINFORMACION DE TRANSACCION");
        System.out.println("ID: " + idTransaccion);
        System.out.println("Tipo: " + tipo);
        System.out.println("Monto: $" + monto);
        System.out.println("Fecha: " + fecha);
        System.out.println("Estado: " + estado);
    }
}

