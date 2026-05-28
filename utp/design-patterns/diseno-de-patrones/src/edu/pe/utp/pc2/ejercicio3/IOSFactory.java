package edu.pe.utp.pc2.ejercicio3;

public class IOSFactory implements GUIFactory {

    @Override
    public Button createButton() {

        return new IOSButton();
    }

    @Override
    public Menu createMenu() {

        return new IOSMenu();
    }
}