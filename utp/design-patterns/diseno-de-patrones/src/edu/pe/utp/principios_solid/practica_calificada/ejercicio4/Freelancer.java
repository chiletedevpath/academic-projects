package edu.pe.utp.principios_solid.practica_calificada.ejercicio4;

public class Freelancer implements Trabajador, Reportable, Pagable {

    @Override
    public void trabajar() {
        System.out.println("Trabajando freelance...");
    }

    @Override
    public void generarReporte() {
        System.out.println("Generando reporte...");
    }

    @Override
    public void calcularSalario() {
        System.out.println("Calculando pago por proyecto...");
    }
}
