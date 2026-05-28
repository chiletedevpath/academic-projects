package edu.pe.utp.arreglos.unidimensionales.estructurado;

import java.util.Scanner;

public class ActualizarElementosDeUnArreglo {

    public static void main(String[] args) {

        try (Scanner teclado = new Scanner(System.in)) {
            int[] arreglo = {5, 10, 15, 20};
            int indiceActualizar;
            int nuevoValor;

            System.out.println("Elementos del arreglo original");
            for (int i = 0; i < arreglo.length; i++) {
                System.out.println("Indice " + (i) + ": " + arreglo[i]);
            }

            do {
                System.out.print("\nIngrese el índice a actualizar: ");
                indiceActualizar = teclado.nextInt();

                if (indiceActualizar < 0 || indiceActualizar >= arreglo.length) {
                    System.out.println("Indice fuera de rango");
                }

            } while (indiceActualizar < 0 || indiceActualizar >= arreglo.length);

            System.out.print("\nIngrese el valor del nuevo indice: ");
            nuevoValor = teclado.nextInt();
            arreglo[indiceActualizar] = nuevoValor;

            System.out.println("\n Elementos del arreglo actualizado");
            for (int i = 0; i < arreglo.length; i++) {
                System.out.println("Posicion " + (i) + ": " + arreglo[i]);
            }
        }
    }
}