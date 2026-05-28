package edu.pe.utp.principios_solid.b_ocp.ejercicio2;

public class PagoConTransferencia implements MedioPago {

    @Override
    public void pagar(double monto) {
        System.out.println("Procesando transferencia bancaria...");
        System.out.println("Comisión: " + (monto * 0.01));
    }
}

