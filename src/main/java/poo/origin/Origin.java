/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.origin;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Origin {
    public static void main(String[] args) {
        System.out.println("BIENVENIDO A ORIGIN");
        Billetera billetera = new Billetera("BILL-001");
        Usuario usuario = new Usuario("USR-001","Carlos Rodriguez","carlos@origin.com",billetera);
        usuario.mostrarInformacion();
        CuentaAhorros ahorros = new CuentaAhorros("CTA-001","Carlos Rodriguez",10000);
        CuentaCorriente corriente = new CuentaCorriente("CTA-002","Carlos Rodriguez",2000);
        CuentaInversion inversion = new CuentaInversion("CTA-003","Carlos Rodriguez",0.12,180);
        billetera.agregarCuenta(ahorros);
        billetera.agregarCuenta(corriente);
        billetera.agregarCuenta(inversion);
        billetera.listarCuentas();

        LectorHuella lectorHuella = new LectorHuella("HUELLA-001");
        LectorReconocimientoFacial lectorFacial = new LectorReconocimientoFacial("FACIAL-001", 90);
        LectorTokenNFC lectorNFC = new LectorTokenNFC("NFC-001");
        LocalDate vencimiento = LocalDate.now().plusDays(30);
        CredencialAutenticacion credencialHuella = new CredencialAutenticacion("HUELLA",
                        "HUELLA-CARLOS",vencimiento);
        CredencialAutenticacion credencialFacial = new CredencialAutenticacion("FACIAL",
                        "ROSTRO-CARLOS",vencimiento,98);
        CredencialAutenticacion credencialNFC = new CredencialAutenticacion("NFC",
                        "TOKEN-CARLOS",vencimiento);

        System.out.println("\nDEPOSITO INICIAL");
        Transaccion depositoInicial = new Transaccion("TRX-001","DEPOSITO",5000,false,
                null,ahorros,null,null);
        depositoInicial.ejecutar();
        depositoInicial.mostrarInformacion();
        
        System.out.println("\nTRANSFERENCIA CON HUELLA");
        Transaccion transferencia = new Transaccion("TRX-002","TRANSFERENCIA",1000,
                true,ahorros,corriente,lectorHuella,credencialHuella);
        boolean resultadoTransferencia = transferencia.ejecutar();
        if (resultadoTransferencia) {
            System.out.println("Transferencia realizada correctamente.");
        } else {
            System.out.println("No fue posible realizar la transferencia.");
        }
        transferencia.mostrarInformacion();

        System.out.println("\nRETIRO CON RECONOCIMIENTO FACIAL");
        Transaccion retiro = new Transaccion("TRX-003","RETIRO",300,true,corriente,
                null,lectorFacial,credencialFacial);
        boolean resultadoRetiro = retiro.ejecutar();
        if (resultadoRetiro) {
            System.out.println("Retiro realizado correctamente.");
        } else {
            System.out.println("No fue posible realizar el retiro.");
        }
        retiro.mostrarInformacion();
        
        System.out.println("\nDEPOSITO CON TOKEN NFC");
        Transaccion depositoInversion = new Transaccion("TRX-004","DEPOSITO",2000,
                true,null,inversion,lectorNFC,credencialNFC);
        boolean resultadoDeposito = depositoInversion.ejecutar();
        if (resultadoDeposito) {
            System.out.println("Deposito realizado correctamente.");
        } else {
            System.out.println("No fue posible realizar el deposito.");
        }
        depositoInversion.mostrarInformacion();

        System.out.println("\nSALDOS FINALES");
        System.out.println("\nCUENTA DE AHORROS");
        ahorros.mostrarInformacion();
        ahorros.mostrarMeta();
        System.out.println("\nCUENTA CORRIENTE");
        corriente.mostrarInformacion();
        corriente.mostrarCupo();
        System.out.println("\nCUENTA DE INVERSION");
        inversion.mostrarInformacion();
        inversion.mostrarRendimiento();
        
        System.out.println("\nRESUMEN DE LA BILLETERA");
        billetera.listarCuentas();
    }
}
