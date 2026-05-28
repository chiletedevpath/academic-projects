package edu.pe.utp.pc2.ejercicio1.estructura;

import edu.pe.utp.pc2.ejercicio1.modelo.Libro;

/**
 * Lista enlazada simple para almacenar objetos Libro.
 * La variable inicio guarda la referencia del primer nodo.
 */
public class ListaLibros {

    private NodoLibro inicio;

    public ListaLibros() {
        this.inicio = null;
    }

    /**
     * Inserta un libro al final de la lista enlazada.
     * Este metodo permite cargar los tres elementos minimos del enunciado.
     */
    public void insertarAlFinal(Libro libro) {
        NodoLibro nuevoNodo = new NodoLibro(libro);

        if (inicio == null) {
            inicio = nuevoNodo;
            return;
        }

        NodoLibro actual = inicio;

        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nuevoNodo);
    }

    /**
     * Operacion 7:
     * Retorna la posicion donde se encontro por primera vez un valor.
     * En este caso, el valor a buscar es el ISBN del libro.
     *
     * @param isbnBuscado ISBN del libro que se desea ubicar.
     * @return posicion donde se encontro por primera vez; -1 si no existe.
     */
    public int buscarPrimeraPosicion(String isbnBuscado) {

        if (isbnBuscado == null || isbnBuscado.trim().isEmpty()) {
            return -1;
        }

        String isbnNormalizado = isbnBuscado.trim();
        NodoLibro actual = inicio;
        int posicion = 1;

        while (actual != null) {
            if (actual.getDato().getIsbn().equalsIgnoreCase(isbnNormalizado)) {
                return posicion;
            }

            // Avanza la referencia actual y actualiza la posicion logica.
            actual = actual.getSiguiente();
            posicion++;
        }

        return -1;
    }

    /**
     * Muestra todos los libros almacenados en la lista enlazada.
     */
    public void mostrarLista() {
        if (inicio == null) {
            System.out.println("La lista esta vacia.");
            return;
        }

        NodoLibro actual = inicio;
        int posicion = 1;

        while (actual != null) {
            System.out.println("Posicion " + posicion + ": " + actual.getDato());
            actual = actual.getSiguiente();
            posicion++;
        }
    }
}
