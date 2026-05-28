package edu.pe.utp.principios_solid.c_lsp.ejercicio1;

public class SMS implements Notificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}
