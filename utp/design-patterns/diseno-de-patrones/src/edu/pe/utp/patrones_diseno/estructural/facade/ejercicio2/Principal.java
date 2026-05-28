package edu.pe.utp.patrones_diseno.estructural.facade.ejercicio2;

public class Principal {

    public static void main(String[] args) {

        // El usuario solo usa la fachada
        HomeTheaterFacade homeTheater =
                new HomeTheaterFacade();

        homeTheater.watchMovie();
    }
}
