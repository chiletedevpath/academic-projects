package edu.pe.utp.patrones_diseno.creacionales.factory;

class MacFactory implements GUIFactory {

    @Override
    public Button createButton() {

        return new MacButton();
    }

    @Override
    public Window createWindow() {

        return new MacWindow();
    }
}