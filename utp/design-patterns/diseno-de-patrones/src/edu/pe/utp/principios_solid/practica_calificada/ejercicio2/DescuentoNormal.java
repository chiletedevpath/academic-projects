package edu.pe.utp.principios_solid.practica_calificada.ejercicio2;

public class DescuentoNormal implements Descuento {

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.05;
    }
}
