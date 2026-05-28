package edu.pe.utp.arreglos.bidimensionales;

public class SumaElementosMatriz {

    public static void main(String[] args) {

        int[][] matriz = {
                {5, 10, 15},
                {20, 25, 30}
        };

        int suma = 0;

        System.out.println("\nMatriz a usar: ");

        for (int i = 0; i < matriz.length; i++) { // recorrer filas
            for (int j = 0; j < matriz[i].length; j++) { // recorrer columnas

                System.out.print(matriz[i][j] + " ");
                suma += matriz[i][j];
            }
            System.out.println();
        }
        System.out.println("\nLa suma de los elementos de la matriz es: " + suma);
    }
}
