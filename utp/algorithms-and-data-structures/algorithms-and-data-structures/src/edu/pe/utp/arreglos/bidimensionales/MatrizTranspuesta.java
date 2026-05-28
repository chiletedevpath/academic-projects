package edu.pe.utp.arreglos.bidimensionales;

public class MatrizTranspuesta {
    public static void main(String[] args) {

        int[][] matriz = {
                {5, 10, 15},
                {20, 25, 30}
        };

        System.out.println("\nMatriz original: ");

        for (int i = 0; i < matriz.length; i++) { // recorrer filas
            for (int j = 0; j < matriz[i].length; j++) { // recorrer columnas
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nMatriz transpuesta: ");
        // INICIAL
        // matriz[0] = {5, 10, 15}
        // matriz[1] = {20, 25, 30}

        // COMO DEBE QUEDAR
        // matriz[0] = {5, 20}
        // matriz[1] = {10, 25}
        // matriz[2] = {15, 30}
        for (int i = 0; i < matriz[0].length; i++) { // matriz[0].length = número de columnas (tamaño de la primera fila)
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[j][i] + " ");
            }
            System.out.println();
        }
    }
}
