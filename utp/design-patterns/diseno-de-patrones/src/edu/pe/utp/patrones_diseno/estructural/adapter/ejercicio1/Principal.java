package edu.pe.utp.patrones_diseno.estructural.adapter.ejercicio1;

public class Principal {

    public static void main(String[] args){

        SistemaVentasAntiguo sistemaVentasAntiguo = new SistemaVentasAntiguo();

        SistemaFacturacion adaptador = new AdaptadorVentas(sistemaVentasAntiguo);

        adaptador.procesarFactura();

    }
}
