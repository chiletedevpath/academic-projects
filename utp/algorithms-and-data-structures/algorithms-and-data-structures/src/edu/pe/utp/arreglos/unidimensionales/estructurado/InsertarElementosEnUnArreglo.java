package edu.pe.utp.arreglos.unidimensionales.estructurado;

import java.util.Scanner;

public class InsertarElementosEnUnArreglo {

    public static void main(String[] args) {

        try (Scanner teclado = new Scanner(System.in)) {
            int[] arreglo = {5, 10, 15, 20};
            int numero;
            int posicion;

            System.out.println("Arreglo inicial");
            for (int i = 0; i < arreglo.length; i++) {
                System.out.println("Indice " + (i) + ": " + arreglo[i]);
            }

            System.out.print("Ingrese el valor del numero: ");
            numero = teclado.nextInt();

            do {
                System.out.print("Ingrese la posicion del nuevo numero: ");
                posicion = teclado.nextInt();

                if (posicion < 0 || posicion > arreglo.length) {
                    System.out.println("Indice fuera de rango");
                }
            } while (posicion < 0 || posicion > arreglo.length);

            int[] nuevoArreglo = new int[arreglo.length + 1];

            for (int i = 0, j = 0; i < nuevoArreglo.length; i++) {
                if (i == posicion) {
                    nuevoArreglo[i] = numero;
                } else {
                    nuevoArreglo[i] = arreglo[j];
                    j++;
                }
            }

            System.out.println("\nArreglo actualizado:");
            for (int i = 0; i < nuevoArreglo.length; i++) {
                System.out.println("Indice " + (i) + ": " + nuevoArreglo[i]);
            }
        }
    }
}

