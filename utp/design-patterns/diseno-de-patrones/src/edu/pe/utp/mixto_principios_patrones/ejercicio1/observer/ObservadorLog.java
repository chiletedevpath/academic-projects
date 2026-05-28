package edu.pe.utp.mixto_principios_patrones.ejercicio1.observer;

import edu.pe.utp.mixto_principios_patrones.ejercicio1.model.Pedido;

public class ObservadorLog implements ObservadorPedido {

    @Override
    public void actualizar(Pedido pedido) {
        System.out.println("[LOG] Se registró un nuevo pedido con ID: " + pedido.getId());

    }
}
