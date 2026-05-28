package edu.pe.utp.arreglos.unidimensionales.estructurado;

import java.util.Scanner;

public class ArreglosLineales {

    public static void main(String[] args) {

        try (Scanner teclado = new Scanner(System.in)) {
            int numeroABuscar;
            int[] arreglo = {5, 10, 15, 20};
            int suma = 0;
            boolean encontrado = false;

            System.out.println("ELEMENTOS DEL ARREGLO");

            // Ejercicio 1: recorrido de un arreglo
            for (int i = 0; i < arreglo.length; i++) {
                System.out.println("Posicion " + (i) + ": " + arreglo[i]);
            }

            // Ejercicio 2: suma de elementos de un arreglo
            for (int i = 0; i < arreglo.length; i++) {
                suma += arreglo[i];
            }
            System.out.println("\nLa suma de los elementos del arreglo es: " + suma);

            // Ejercicio 3: búsqueda de un elemento dentro de un arreglo
            System.out.print("\nIngrese el numero a buscar en el arreglo: ");
            numeroABuscar = teclado.nextInt();

            for (int i = 0; i < arreglo.length; i++) {
                if (numeroABuscar == arreglo[i]) {
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                System.out.print("\nEl numero " + numeroABuscar + " si existe en el arreglo");
            } else {
                System.out.print("\nEl numero " + numeroABuscar + " no existe en el arreglo");
            }
        }
    }
}
