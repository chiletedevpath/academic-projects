package edu.pe.utp.principios_solid.b_ocp.ejercicio2;

public class PagoConTarjeta implements MedioPago {

    @Override
    public void pagar(double monto) {
        System.out.println("Procesando pago con tarjeta...");
        System.out.println("Comisión: " + (monto * 0.03));
    }
}


