package edu.pe.utp.principios_solid.b_ocp.ejercicio1;

public class DescuentoRegular implements Descuento {

    @Override
    public double aplicarDescuento(double total) {
        return total * 0.9;
    }
}
