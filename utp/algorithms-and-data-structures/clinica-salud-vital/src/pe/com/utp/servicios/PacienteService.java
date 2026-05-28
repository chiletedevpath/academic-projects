package pe.com.utp.servicios;

import pe.com.utp.modelo.persona.Paciente;

//Clase encargada de gestionar las operaciones relacionadas con los pacientes del sistema
public class PacienteService {

    // Capacidad maxima del arreglo de pacientes
    private static final int CAPACIDAD = 100;

    // Arreglo unidimensional de objetos Paciente
    // Permite almacenar pacientes en memoria
    private Paciente[] pacientes;

    // Controla la cantidad real de pacientes registrados
    // Evita recorrer posiciones vacias del arreglo
    private int totalPacientes;

    // Constructor de la clase.
    // Inicializa el arreglo y el contador.
    public PacienteService() {
        pacientes = new Paciente[CAPACIDAD];
        totalPacientes = 0;
    }

    // Registra un nuevo paciente en el arreglo. El paciente se almacena en la siguiente posición disponible
    public void registrarPaciente(Paciente paciente) {
        // Verifica si el arreglo llegó a su capacidad máxima
        if (totalPacientes >= CAPACIDAD) {
            System.out.println("No hay espacio disponible para registrar más pacientes");
            return;
        }

        // Inserta el objeto paciente en la posición actual
        pacientes[totalPacientes] = paciente;

        // Incrementa la cantidad logica de pacientes registrados
        totalPacientes++;

        System.out.println("Paciente registrado correctamente");
    }

    // Muestra todos los pacientes registrados. Recorre únicamente las posiciones ocupadas del arreglo
    public void mostrarPacientes() {

        // Verifica si existen pacientes registrados
        if (totalPacientes == 0) {
            System.out.println("No existen pacientes registrados");
            return;
        }
        System.out.println("===== LISTA DE PACIENTES =====");

        // Recorre el arreglo desde la posición 0 hasta la última posición lógica ocupada.
        for (int i = 0; i < totalPacientes; i++) {
            System.out.println();
            // Muestra los datos del paciente actual
            pacientes[i].mostrarDatos();
        }
    }

    // Busca un paciente mediante su DNI
    public Paciente buscarPacientePorDni(String dni) {

        // Recorre únicamente las posiciones ocupadas
        for (int i = 0; i < totalPacientes; i++) {

            // Compara el DNI ingresado con el DNI del paciente almacenado en la posición actual
            if (pacientes[i].getDni().equals(dni)) {
                // Retorna el objeto encontrado
                return pacientes[i];
            }
        }
        // Retorna null si el paciente no existe
        return null;
    }

    // Actualiza el teléfono y correo de un paciente
    // Primero busca el paciente mediante su DNI
    public void actualizarPaciente(String dni, String nuevoTelefono, String nuevoCorreo) {

        // Busca el paciente en el arreglo
        Paciente pacienteEncontrado = buscarPacientePorDni(dni);

        // Verifica si el paciente existe
        if (pacienteEncontrado == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        // Modifica directamente los atributos del objeto almacenado en memoria.
        pacienteEncontrado.setTelefono(nuevoTelefono);
        pacienteEncontrado.setCorreo(nuevoCorreo);

        System.out.println("Paciente actualizado correctamente");
    }

    // Elimina un paciente mediante su DNI
    // Los elementos posteriores se desplazan una posición hacia la izquierda
    public void eliminarPaciente(String dni) {
        int posicion = -1;

        // Busca la posición del paciente dentro del arreglo
        for (int i = 0; i < totalPacientes; i++) {
            if (pacientes[i].getDni().equals(dni)) {
                posicion = i;
                break;
            }
        }

        // Verifica si el paciente existe
        if (posicion == -1) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        // Desplaza los elementos posteriores para cubrir el espacio eliminado
        for (int i = posicion; i < totalPacientes - 1; i++) {
            pacientes[i] = pacientes[i + 1];
        }

        // Limpia la última posición lógica del arreglo
        pacientes[totalPacientes - 1] = null;

        // Reduce la cantidad lógica de pacientes
        totalPacientes--;

        System.out.println("Paciente eliminado correctamente.");
    }
}