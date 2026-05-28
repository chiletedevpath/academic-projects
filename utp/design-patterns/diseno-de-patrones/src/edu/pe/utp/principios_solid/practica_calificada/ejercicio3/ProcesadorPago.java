package edu.pe.utp.principios_solid.practica_calificada.ejercicio3;

public class ProcesadorPago {

    public void procesarPago(MetodoPago metodo, double monto) {
        metodo.pagar(monto);
    }

    public void procesarReembolso(MetodoReembolso metodo, double monto) {
        metodo.reembolsar(monto);
    }
}