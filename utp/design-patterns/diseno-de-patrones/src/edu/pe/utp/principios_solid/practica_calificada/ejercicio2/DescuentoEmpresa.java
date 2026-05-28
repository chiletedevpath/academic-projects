package edu.pe.utp.principios_solid.practica_calificada.ejercicio2;

public class DescuentoEmpresa implements Descuento {

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.25;
    }
}