package edu.pe.utp.principios_solid.b_ocp.ejercicio2;

public class Main {

    public static void main(String[] args) {

        ProcesadorPagos metodo1 = new ProcesadorPagos(new PagoConCripto());
        metodo1.calcularMonto(1000);

        ProcesadorPagos metodo2 = new ProcesadorPagos(new PagoConPaypal());
        metodo2.calcularMonto(1000);

        ProcesadorPagos metodo3 = new ProcesadorPagos(new PagoConTransferencia());
        metodo3.calcularMonto(1000);

        ProcesadorPagos metodo4 = new ProcesadorPagos(new PagoConTarjeta());
        metodo4.calcularMonto(1000);

        ProcesadorPagos metodo5 = new ProcesadorPagos(new PagoNoSoportado());
        metodo5.calcularMonto(1000);

    }
}


