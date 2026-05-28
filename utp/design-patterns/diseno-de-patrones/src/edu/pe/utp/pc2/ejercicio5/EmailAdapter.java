package edu.pe.utp.pc2.ejercicio5;

public class EmailAdapter implements EmailSender {

    private OldEmailService oldEmailService;

    // Constructor
    public EmailAdapter(OldEmailService oldEmailService) {

        this.oldEmailService = oldEmailService;
    }

    // Adaptación
    @Override
    public void send(String message) {

        oldEmailService.sendMail(message);
    }
}
