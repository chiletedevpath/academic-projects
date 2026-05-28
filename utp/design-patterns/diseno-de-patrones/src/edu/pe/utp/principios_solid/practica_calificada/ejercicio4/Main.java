package edu.pe.utp.principios_solid.practica_calificada.ejercicio4;

public class Main {

    public static void main(String[] args) {

        System.out.println("EMPLEADO");
        Empleado empleado = new Empleado();
        empleado.trabajar();
        empleado.marcarEntrada();
        empleado.marcarSalida();
        empleado.generarReporte();
        empleado.calcularSalario();
        empleado.capacitar();

        System.out.println("\nFREELANCER");
        Freelancer freelancer = new Freelancer();
        freelancer.trabajar();
        freelancer.generarReporte();
        freelancer.calcularSalario();
    }
}

