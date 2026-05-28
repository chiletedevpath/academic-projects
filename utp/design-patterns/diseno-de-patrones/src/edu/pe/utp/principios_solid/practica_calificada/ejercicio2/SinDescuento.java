package edu.pe.utp.principios_solid.practica_calificada.ejercicio2;

public class SinDescuento implements Descuento {

    @Override
    public double aplicarDescuento(double monto) {
        return 0;
    }
}