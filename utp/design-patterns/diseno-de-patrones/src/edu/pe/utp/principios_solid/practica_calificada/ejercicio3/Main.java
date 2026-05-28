package edu.pe.utp.principios_solid.practica_calificada.ejercicio3;

public class Main {
    public static void main(String[] args) {

        // OJO: Efectivo no tiene reembolso, por eso no lo uso
        ProcesadorPago procesador = new ProcesadorPago();

        // Crear métodos de pago
        MetodoPago pagoEfectivo = new Efectivo();
        MetodoPago pagoTarjeta = new TarjetaCredito();

        // Procesar pagos
        System.out.println("PAGOS ");
        procesador.procesarPago(pagoEfectivo, 100);
        procesador.procesarPago(pagoTarjeta, 200);

        // Procesar reembolso (solo tarjeta)
        System.out.println("\n REEMBOLSOS");
        MetodoReembolso reembolsoTarjeta = new TarjetaCredito();
        procesador.procesarReembolso(reembolsoTarjeta, 50);

    }
}