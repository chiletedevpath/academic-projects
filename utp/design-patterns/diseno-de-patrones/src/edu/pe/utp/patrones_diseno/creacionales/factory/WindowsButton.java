package edu.pe.utp.patrones_diseno.creacionales.factory;

class WindowsButton implements Button {

    @Override
    public void paint() {

        System.out.println("Botón Windows");
    }
}