package edu.pe.utp.pc2.ejercicio6;

public class GameFacade {

    private Graphics graphics;
    private Audio audio;
    private Network network;

    // Constructor
    public GameFacade() {

        graphics = new Graphics();

        audio = new Audio();

        network = new Network();
    }

    public void startGame() {

        System.out.println(" INICIANDO VIDEOJUEGO ");

        graphics.start();

        audio.start();

        network.connect();

        System.out.println("Videojuego iniciado");
    }
}