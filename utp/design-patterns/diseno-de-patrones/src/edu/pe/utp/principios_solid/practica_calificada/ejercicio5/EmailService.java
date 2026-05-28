package edu.pe.utp.principios_solid.practica_calificada.ejercicio5;

public class EmailService implements Notificador {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando email: " + mensaje);
    }
}
