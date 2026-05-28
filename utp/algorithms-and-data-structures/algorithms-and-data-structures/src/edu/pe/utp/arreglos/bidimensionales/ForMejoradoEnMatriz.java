package edu.pe.utp.arreglos.bidimensionales;

public class ForMejoradoEnMatriz {

    // FOR MEJORADO O FOR EACH

    public static void main(String[] args) {

        int[][] matriz = {
                {5, 10, 15},
                {20, 25, 30}
        };

        System.out.println("\nMatriz original: ");

        for (int[] fila : matriz) { // Extrae las filas de la matriz
            for (int numero : fila) { // Extrae los numeros de la fila
                System.out.print(numero + " ");
            }
            System.out.println();
        }
    }
}
