package edu.pe.utp.patrones_diseno.estructural.adapter.ejercicio3;

public class Principal {

    public static void main(String[] args) {

        // Sistema antiguo
        OldPaymentSystem oldSystem =
                new OldPaymentSystem();

        // Adaptador
        PaymentProcessor adapter =
                new PaymentAdapter(oldSystem);

        // Sistema moderno usando API antigua
        adapter.pay(100);
    }
}
