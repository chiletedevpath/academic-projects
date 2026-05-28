package edu.pe.utp.pc2.ejercicio1.app;

import edu.pe.utp.pc2.ejercicio1.estructura.ListaLibros;
import edu.pe.utp.pc2.ejercicio1.modelo.Libro;

/**
 * Clase principal del programa.
 * Aqui se crea una lista enlazada simple de libros y se ejecuta la operacion 7.
 */
public class Main {

    public static void main(String[] args) {

        ListaLibros lista = new ListaLibros();

        // Cada Libro es un TAD; la lista no almacena String ni tipos primitivos.
        Libro libro1 = new Libro("L001", "978001", "Estructuras de Datos", 2021);
        Libro libro2 = new Libro("L002", "978002", "Programacion en Java", 2020);
        Libro libro3 = new Libro("L003", "978003", "Algoritmos Aplicados", 2022);

        // Se cargan tres elementos como minimo, segun pide el enunciado.
        lista.insertarAlFinal(libro1);
        lista.insertarAlFinal(libro2);
        lista.insertarAlFinal(libro3);

        System.out.println("===== LISTA INICIAL DE LIBROS =====");
        lista.mostrarLista();

        // Operacion 7: retornar la primera posicion donde aparece un valor.
        String isbnBuscado = "978002";

        System.out.println("\n===== OPERACION 7: BUSCAR PRIMERA POSICION =====");
        System.out.println("ISBN buscado: " + isbnBuscado);

        int posicionEncontrada = lista.buscarPrimeraPosicion(isbnBuscado);

        if (posicionEncontrada != -1) {
            System.out.println("Primera posicion encontrada: " + posicionEncontrada);
        } else {
            System.out.println("Libro no encontrado.");
        }

        // Caso adicional para demostrar el retorno -1 cuando el ISBN no existe.
        String isbnNoExistente = "999999";
        int posicionNoEncontrada = lista.buscarPrimeraPosicion(isbnNoExistente);
        System.out.println("\nISBN no existente: " + isbnNoExistente);
        System.out.println("Resultado de busqueda fallida: " + posicionNoEncontrada);
    }
}
