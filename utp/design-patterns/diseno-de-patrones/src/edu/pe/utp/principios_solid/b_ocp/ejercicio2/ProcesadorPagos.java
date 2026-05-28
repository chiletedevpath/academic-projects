package edu.pe.utp.principios_solid.b_ocp.ejercicio2;

public class ProcesadorPagos {

    private final MedioPago medioPago;

    public ProcesadorPagos(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public void calcularMonto(double monto) {
        medioPago.pagar(monto);
    }
}



