package edu.pe.utp.patrones_diseno.estructural.facade.ejercicio2;

class HomeTheaterFacade {

    private TV tv;
    private SoundSystem soundSystem;
    private Lights lights;

    // Constructor
    public HomeTheaterFacade() {

        tv = new TV();

        soundSystem = new SoundSystem();

        lights = new Lights();
    }

    // Método simplificado
    public void watchMovie() {

        System.out.println("===== INICIANDO PELÍCULA =====");

        tv.on();

        soundSystem.on();

        lights.dim();

        System.out.println("Película iniciada");
    }
}