package edu.pe.utp.patrones_diseno.estructural.adapter.ejercicio2;

public class AdaptadorCorreoASMS implements SistemaSMS {

    private SistemaCorreo sistemaCorreo;

    public AdaptadorCorreoASMS(SistemaCorreo sistemaCorreo) {
        this.sistemaCorreo = sistemaCorreo;
    }

    @Override
    public void enviarMensajeSMS(String mensaje) {
        sistemaCorreo.enviarMensajeCorreo(mensaje);

    }
}
