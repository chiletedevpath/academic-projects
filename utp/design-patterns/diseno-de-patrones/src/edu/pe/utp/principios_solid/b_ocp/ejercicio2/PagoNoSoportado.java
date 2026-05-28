package edu.pe.utp.principios_solid.b_ocp.ejercicio2;

public class PagoNoSoportado implements MedioPago {

    @Override
    public void pagar(double monto) {
        System.out.println("Método no soportado");
    }
}


