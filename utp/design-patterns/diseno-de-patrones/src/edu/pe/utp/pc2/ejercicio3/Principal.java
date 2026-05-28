package edu.pe.utp.pc2.ejercicio3;

public class Principal {

    public static void main(String[] args) {

            // Elegir plataforma
            GUIFactory factory = new AndroidFactory();

            // Crear objetos relacionados
            Button btn = factory.createButton();

            Menu menu = factory.createMenu();

            btn.paint();

            menu.show();
        }
    }
