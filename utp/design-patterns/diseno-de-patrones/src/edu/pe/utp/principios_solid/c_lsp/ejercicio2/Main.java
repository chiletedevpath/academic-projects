package edu.pe.utp.principios_solid.c_lsp.ejercicio2;

public class Main {

    public static void main(String [] args){

        Trabajable persona1 = new Humano();
        Trabajable robot1 = new Robot();
        Comible persona2 = new Humano();

        persona1.trabajar();
        robot1.trabajar();
        persona2.comer();
    }
}
