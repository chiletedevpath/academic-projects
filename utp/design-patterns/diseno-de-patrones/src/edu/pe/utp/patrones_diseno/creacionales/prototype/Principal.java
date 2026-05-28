package edu.pe.utp.patrones_diseno.creacionales.prototype;

public class Principal {

    public static void main(String[] args) {

        // Enemigo base
        Enemy enemigoBase = new Enemy("Orco", 100);

        // Clonar enemigos
        Enemy e1 = enemigoBase.clonar();
        Enemy e2 = enemigoBase.clonar();
        Enemy e3 = enemigoBase.clonar();

        e1.mostrar();
        e2.mostrar();
        e3.mostrar();
    }
}