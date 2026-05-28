package edu.pe.utp.pc2.ejercicio5;

public class Principal {

    public static void main(String[] args) {

        // Librería antigua
        OldEmailService oldService =
                new OldEmailService();

        // Adaptador
        EmailSender adapter =
                new EmailAdapter(oldService);

        // Nueva aplicación usando sistema antiguo
        adapter.send("Hola usuario");
    }
}