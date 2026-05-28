package edu.pe.utp.patrones_diseno.estructural.facade;

public class Principal {

    public static void main(String[] args) {

        // El cliente solo usa la fachada
        TiendaFacade tienda = new TiendaFacade();

        tienda.realizarCompras();
    }
}
