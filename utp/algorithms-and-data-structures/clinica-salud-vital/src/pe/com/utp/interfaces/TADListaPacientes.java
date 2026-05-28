package pe.com.utp.interfaces;

import pe.com.utp.modelo.persona.Paciente;

/*
 * Tipo Abstracto de Datos para gestionar pacientes de forma dinamica.
 * Define el contrato que debe cumplir cualquier estructura que administre
 * pacientes mediante insercion, busqueda, eliminacion, conteo y recorrido.
 */
public interface TADListaPacientes {

    void insertarPaciente(Paciente paciente);

    Paciente buscarPacientePorDni(String dni);

    void eliminarPaciente(String dni);

    boolean estaVacia();

    int contarPacientes();

    void mostrarPacientes();
}
