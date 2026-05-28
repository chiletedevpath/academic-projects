package edu.pe.utp.pc2.ejercicio3;

public class AndroidFactory implements GUIFactory {

    @Override
    public Button createButton() {

        return new AndroidButton();
    }

    @Override
    public Menu createMenu() {

        return new AndroidMenu();
    }
}