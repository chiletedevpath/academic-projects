package edu.pe.utp.pc2.ejercicio2;

public class Principal {

    public static void main(String[] args) {

        // Tarjeta base
        BusinessCard plantilla =
                new BusinessCard(
                        "TechSoft",
                        "Minimalista"
                );

        // Clonar tarjetas
        BusinessCard c1 = plantilla.clonar();

        BusinessCard c2 = plantilla.clonar();

        BusinessCard c3 = plantilla.clonar();

        c1.mostrar();

        c2.mostrar();

        c3.mostrar();
    }
}