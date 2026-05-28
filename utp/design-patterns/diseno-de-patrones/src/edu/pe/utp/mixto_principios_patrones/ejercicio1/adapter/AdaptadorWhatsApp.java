package edu.pe.utp.mixto_principios_patrones.ejercicio1.adapter;

public class AdaptadorWhatsApp implements Notificador {

    private final ServicioWhatsAppExterno servicioExterno;

    public AdaptadorWhatsApp(ServicioWhatsAppExterno servicioExterno) {
        this.servicioExterno = servicioExterno;
    }

    @Override
    public void enviar(String mensaje) {
        servicioExterno.enviarMensajeWhatsApp(mensaje);
    }
}
