package edu.pe.utp.principios_solid.b_ocp.ejercicio1;

public class Main {
    public static void main(String[] args) {

        CalculadoraDescuento persona1 = new CalculadoraDescuento(new DescuentoEmpleado());
        System.out.println("Descuento empleado: " + persona1.calcular(100));

        CalculadoraDescuento persona2 = new CalculadoraDescuento(new DescuentoRegular());
        System.out.println("Descuento Regular: " + persona2.calcular(100));

        CalculadoraDescuento persona3 = new CalculadoraDescuento(new DescuentoVIP());
        System.out.println("Descuento VIP: " + persona3.calcular(100));

        CalculadoraDescuento persona4 = new CalculadoraDescuento(new DescuentoSinDescuento());
        System.out.println("Sin descuento: " + persona4.calcular(100));
    }
}


