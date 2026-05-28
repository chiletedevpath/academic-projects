package edu.pe.utp.principios_solid.b_ocp.ejercicio2;

public class PagoConPaypal implements MedioPago {

    @Override
    public void pagar(double monto) {
        System.out.println("Procesando pago con PayPal...");
        System.out.println("Comisión: " + (monto * 0.05));
    }
}


