package edu.pe.utp.pc2.ejercicio1.estructura;

import edu.pe.utp.pc2.ejercicio1.modelo.Libro;

/**
 * NodoLibro representa un nodo de una lista enlazada simple.
 * Cada nodo contiene:
 * - Un dato de tipo Libro.
 * - Una referencia al siguiente nodo.
 */
public class NodoLibro {

    private Libro dato;
    private NodoLibro siguiente;

    /**
     * Al crear un nodo, recibe un objeto Libro.
     * no se encuentra enlazado con otro nodo.
     */
    public NodoLibro(Libro dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Libro getDato() {
        return dato;
    }

    public void setDato(Libro dato) {
        this.dato = dato;
    }

    public NodoLibro getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLibro siguiente) {
        this.siguiente = siguiente;
    }
}