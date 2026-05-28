package edu.pe.utp.mixto_principios_patrones.ejercicio1.observer;

import edu.pe.utp.mixto_principios_patrones.ejercicio1.adapter.Notificador;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.model.Pedido;

public class ObservadorWhatsApp implements ObservadorPedido{

    private final Notificador notificador;

    public ObservadorWhatsApp(Notificador notificador) {
        this.notificador = notificador;
    }

    @Override
    public void actualizar(Pedido pedido) {
        String mensaje = "Nuevo pedido registrado para " + pedido.getCliente()
                + " por un monto de S/ " + pedido.getMonto();

        notificador.enviar(mensaje);

    }
}
