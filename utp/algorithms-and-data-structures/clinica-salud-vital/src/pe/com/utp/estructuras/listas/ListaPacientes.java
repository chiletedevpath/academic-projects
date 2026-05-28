package pe.com.utp.estructuras.listas;

import pe.com.utp.interfaces.TADListaPacientes;
import pe.com.utp.modelo.persona.Paciente;

public class ListaPacientes implements TADListaPacientes {

    // Referencia al primer nodo de la lista enlazada.
    private Nodo inicio;

    // Contador logico de nodos; evita recorrer toda la lista solo para contar.
    private int tamanioListaPacientes;

    // La lista inicia vacia: no existe primer nodo y el contador empieza en cero.
    public ListaPacientes() {
        inicio = null;
        this.tamanioListaPacientes = 0;
    }

    // Inserta un nuevo paciente al final de la lista enlazada simple.
    @Override
    public void insertarPaciente(Paciente paciente) {

        // El paciente se encapsula dentro de un nodo para poder enlazarlo.
        Nodo nuevoNodo = new Nodo(paciente);

        // Caso 1: lista vacia. El nuevo nodo pasa a ser el inicio.
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            // Caso 2: lista con datos. Se recorre hasta el ultimo nodo.
            Nodo actual = inicio;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            // El ultimo nodo apunta al nuevo nodo, quedando insertado al final.
            actual.setSiguiente(nuevoNodo);
        }
        tamanioListaPacientes++;
        System.out.println("Paciente insertado correctamente en la lista");
    }

    // Busca un paciente por DNI recorriendo secuencialmente los nodos.
    @Override
    public Paciente buscarPacientePorDni(String DNI) {

        // actual apunta al nodo que se evalua en cada iteracion.
        Nodo actual = inicio;

        while (actual != null) {

            // El DNI funciona como criterio de busqueda del paciente.
            if (actual.getPaciente().getDni().equals(DNI)) {
                return actual.getPaciente();
            }

            // Si no coincide, se avanza al siguiente nodo.
            actual = actual.getSiguiente();
        }

        // Si se llega a null, el paciente no existe en la lista.
        return null;
    }

    // Elimina un paciente por DNI, actualizando los enlaces entre nodos.
    @Override
    public void eliminarPaciente(String dni) {

        // No se puede eliminar si no hay nodos.
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
            return;
        }

        // Caso especial: el nodo a eliminar es el primero.
        if (inicio.getPaciente().getDni().equals(dni)) {
            inicio = inicio.getSiguiente();
            tamanioListaPacientes--;
            System.out.println("Paciente eliminado correctamente");
            return;
        }

        // anterior conserva el nodo previo; actual representa el nodo evaluado.
        Nodo actual = inicio;
        Nodo anterior = null;

        // Se avanza hasta encontrar el DNI o llegar al final de la lista.
        while (actual != null && !actual.getPaciente().getDni().equals(dni)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        // Si actual es null, no se encontro el paciente.
        if (actual == null) {
            System.out.println("Paciente no encontrado");
            return;
        }

        // Cambio principal de memoria: anterior salta el nodo actual.
        anterior.setSiguiente(actual.getSiguiente());
        tamanioListaPacientes--;
        System.out.println("Paciente eliminado correctamente");
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }

    @Override
    public int contarPacientes() {
        return tamanioListaPacientes;
    }

    // Recorre la lista y muestra cada paciente almacenado en sus nodos.
    @Override
    public void mostrarPacientes() {

        // Si inicio es null, no hay nodos para mostrar.
        if (estaVacia()) {
            System.out.println("La lista de pacientes esta vacia");
            return;
        }

        System.out.println("LISTA ENLAZADA DE PACIENTES");

        // Recorrido desde el primer nodo hasta llegar a null.
        Nodo actual = inicio;

        while (actual != null) {
            System.out.println();

            // El nodo delega la visualizacion al objeto Paciente almacenado.
            actual.getPaciente().mostrarDatos();

            actual = actual.getSiguiente();
        }
    }
}
