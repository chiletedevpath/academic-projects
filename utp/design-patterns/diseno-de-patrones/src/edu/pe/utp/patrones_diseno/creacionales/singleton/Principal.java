package edu.pe.utp.patrones_diseno.creacionales.singleton;

public class Principal {

    public static void main(String[] args) {

        Logger log1 = Logger.getInstancia();
        Logger log2 = Logger.getInstancia();

        log1.log("Usuario inicia sesión");
        log2.log("Transferencia realizada");

        System.out.println(log1 == log2);
    }
}
