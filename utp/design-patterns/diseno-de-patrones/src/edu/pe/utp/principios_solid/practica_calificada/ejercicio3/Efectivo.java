package edu.pe.utp.principios_solid.practica_calificada.ejercicio3;

public class Efectivo implements MetodoPago {

    @Override
    public void pagar(double monto) {
        System.out.println("Pagando en efectivo: " + monto);
    }
}