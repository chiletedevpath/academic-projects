package edu.pe.utp.principios_solid.b_ocp.ejercicio1;

public class CalculadoraDescuento {

    private final Descuento descuento;

    public CalculadoraDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

    public double calcular(double total) {
        return descuento.aplicarDescuento(total);
    }
}


