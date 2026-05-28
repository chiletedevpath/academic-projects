package edu.pe.utp.mixto_principios_patrones.ejercicio1.observer;

import edu.pe.utp.mixto_principios_patrones.ejercicio1.model.Pedido;

public class ObservadorCliente implements ObservadorPedido {

    @Override
    public void actualizar(Pedido pedido) {
        System.out.println("[CLIENTE] Hola " + pedido.getCliente() +
                ", tu pedido fue registrado correctamente.");
    }
}
