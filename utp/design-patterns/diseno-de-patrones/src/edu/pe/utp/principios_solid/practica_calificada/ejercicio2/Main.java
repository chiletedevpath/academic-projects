package edu.pe.utp.principios_solid.practica_calificada.ejercicio2;

public class Main {
    public static void main(String[] args) {

        Pedido pedido1 = new Pedido("NORMAL", "STANDARD", 100, new DescuentoNormal(), new EnvioStandard());

        Pedido pedido2 = new Pedido("VIP", "EXPRESS", 200, new DescuentoVip(), new EnvioExpress());

        Pedido pedido3 = new Pedido("EMPRESA", "STANDARD", 300, new DescuentoEmpresa(), new EnvioStandard());

        System.out.println("Total pedido 1: " + pedido1.calcularTotal());
        System.out.println("Total pedido 2: " + pedido2.calcularTotal());
        System.out.println("Total pedido 3: " + pedido3.calcularTotal());
    }
}