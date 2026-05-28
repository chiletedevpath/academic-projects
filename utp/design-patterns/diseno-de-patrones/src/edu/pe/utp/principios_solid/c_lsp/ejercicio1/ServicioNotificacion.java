package edu.pe.utp.principios_solid.c_lsp.ejercicio1;

public class ServicioNotificacion {

    private final Notificacion notificacion;

    public ServicioNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    public void notificar(String mensaje, boolean enviar){
        if(enviar){
            notificacion.enviar(mensaje);
        } else{
            System.out.println("Notificacion omitida");
        }
    }
}

