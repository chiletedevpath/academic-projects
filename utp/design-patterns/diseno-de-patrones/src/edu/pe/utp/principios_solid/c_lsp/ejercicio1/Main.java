package edu.pe.utp.principios_solid.c_lsp.ejercicio1;

public class Main {
    public static void main(String[] args) {

        ServicioNotificacion servicio1 = new ServicioNotificacion(new Email());
        servicio1.notificar("Cobro por correo electronico", true);

        ServicioNotificacion servicio2 = new ServicioNotificacion(new SMS());
        servicio2.notificar("Cobro por mensaje de texto", true);

        ServicioNotificacion servicio3 = new ServicioNotificacion(new Email());
        servicio3.notificar("Notificacion interna de la empresa",false);
    }
}


