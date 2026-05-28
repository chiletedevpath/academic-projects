package edu.pe.utp.patrones_diseno.creacionales.factory;

public class Principal {

    public static void main(String[] args) {

        // Elegir sistema operativo
        GUIFactory factory = new WindowsFactory();

        // Crear objetos relacionados
        Button btn = factory.createButton();

        Window win = factory.createWindow();

        btn.paint();

        win.open();
    }
}
