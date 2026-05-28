package pe.com.utp.estructuras.listas;

import pe.com.utp.modelo.persona.Paciente;

/*
 * Nodo de una lista enlazada simple.
 * Cada nodo almacena un objeto Paciente y una referencia al siguiente nodo.
 * Si siguiente es null, el nodo actual es el ultimo de la lista.
 */
public class Nodo {

    // Objeto del dominio almacenado como dato del nodo.
    private Paciente paciente;

    // Enlace hacia el siguiente nodo de la lista.
    private Nodo siguiente;

    // Al crear un nodo, aun no queda enlazado con otro nodo.
    public Nodo(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
