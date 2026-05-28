package edu.pe.utp.patrones_diseno.estructural.adapter.ejercicio3;

class PaymentAdapter implements PaymentProcessor {

    private OldPaymentSystem oldPaymentSystem;

    // Constructor
    public PaymentAdapter(OldPaymentSystem oldPaymentSystem) {

        this.oldPaymentSystem = oldPaymentSystem;
    }

    // Adaptación
    @Override
    public void pay(double amount) {

        oldPaymentSystem.makePayment(amount);
    }
}
