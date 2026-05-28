package edu.pe.utp.patrones_diseno.creacionales.factory;

class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {

        return new WindowsButton();
    }

    @Override
    public Window createWindow() {

        return new WindowsWindow();
    }
}
