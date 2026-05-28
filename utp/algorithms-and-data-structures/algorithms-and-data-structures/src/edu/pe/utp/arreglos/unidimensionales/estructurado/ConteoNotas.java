package edu.pe.utp.arreglos.unidimensionales.estructurado;

import java.util.Scanner;

public class ConteoNotas {

    public static void main(String[] args) {

        int notaIngresada;
        Scanner leer = new Scanner(System.in);
        String respuesta;
        int[] cantidadPorNota = new int[21];

        do {
            do {
                System.out.print("Nota de [0-20]: ");
                notaIngresada = leer.nextInt();

                if (notaIngresada < 0 || notaIngresada > 20) { // CAMBIO: && X ||
                    System.out.println("Error, nota de [0-20]");
                }

            } while (notaIngresada < 0 || notaIngresada > 20);

            cantidadPorNota[notaIngresada] += 1;

            do {
                System.out.print("Desea continuar? ");
                respuesta = leer.next();

                if (!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no"))) {
                    System.out.println("Debe responder solo si o no");
                }

            } while (!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no")));

        } while (respuesta.equalsIgnoreCase("si")); // validacion de respuesta

        // Solo se muestran las notas cuya cantidad es mayor a cero
        for (int nota = 0; nota < 21; nota++) {
            if (cantidadPorNota[nota] > 0) {
                System.out.println(nota + "\t" + cantidadPorNota[nota]);
            }
        }
    }
}
