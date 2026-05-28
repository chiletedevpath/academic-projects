package edu.pe.utp.principios_solid.c_lsp.ejercicio2;

public class Humano implements Trabajable, Comible {

    @Override
    public void trabajar() {
        System.out.println("Humano Trabajando...");
    }

    @Override
    public void comer() {
        System.out.println("Humano Comiendo...");
    }
}
