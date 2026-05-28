package edu.pe.utp.principios_solid.b_ocp.ejercicio1;

public class DescuentoSinDescuento implements Descuento {

    @Override
    public double aplicarDescuento(double total) {
        return total;
    }
}
