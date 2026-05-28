package pe.com.utp.app;

import pe.com.utp.estructuras.matrices.MatrizHorarios;
import pe.com.utp.modelo.cita.Cita;
import pe.com.utp.modelo.enums.Especialidad;
import pe.com.utp.modelo.enums.EstadoCita;
import pe.com.utp.modelo.enums.TurnoConsultaAmbulatoria;
import pe.com.utp.modelo.persona.Doctor;
import pe.com.utp.modelo.persona.Paciente;
import pe.com.utp.servicios.CitaService;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * Clase principal del sistema.
 * Ejecuta un flujo academico para demostrar objetos, arreglo de citas,
 * matriz de horarios y busqueda lineal.
 */
public class Main {

    public static void main(String[] args) {

        // Datos base: pacientes que participaran en las citas.
        Paciente paciente1 = new Paciente("PAC-001", "73748381", "Adrian", "Pisco", "945834043", "adrian.pisco@gmail.com", 28);
        Paciente paciente2 = new Paciente("PAC-002", "25836900", "Wilmer", "Soto", "985000677", "wilmer.soto@gmail.com", 28);

        // Medicos asignados a la matriz de horarios. Cada medico representa una fila.
        Doctor doctor1 = new Doctor("DOC-001", "25413651", "Juan Carlos", "Lozano Leon", "925587412", "000555", "doctor1@gmail.com", Especialidad.MEDICINA_GENERAL, TurnoConsultaAmbulatoria.MAÑANA);
        Doctor doctor2 = new Doctor("DOC-002", "02578933", "Carlos Elvis", "Tapia Silva", "925587412", "000556", "doctor2@gmail.com", Especialidad.DERMATOLOGIA, TurnoConsultaAmbulatoria.TARDE);
        Doctor doctor3 = new Doctor("DOC-003", "73740025", "David", "Moncada Teran", "925357891", "000557", "doctor3@gmail.com", Especialidad.TRAUMATOLOGIA, TurnoConsultaAmbulatoria.MAÑANA);

        // La matriz queda configurada con los medicos reales que representan sus filas.
        MatrizHorarios matrizHorarios = new MatrizHorarios(doctor1, doctor2, doctor3);

        // Cada cita relaciona un paciente con un doctor, fecha, hora, estado y motivo.
        Cita cita1 = new Cita("CIT-001", paciente1, doctor1, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Dolor de cuello");
        Cita cita2 = new Cita("CIT-002", paciente2, doctor2, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Presencia de fiebre");

        // El servicio recibe la matriz configurada para mantener coherencia entre citas y horarios.
        CitaService citaServiceservice = new CitaService(matrizHorarios);

        // Registro de citas: la fila del medico se obtiene automaticamente desde la cita.
        citaServiceservice.registrarCita(cita1, 1);
        citaServiceservice.registrarCita(cita2, 2);

        // Reportes basados en la matriz de horarios.
        citaServiceservice.mostrarMatrizHorarios();
        citaServiceservice.mostrarTotalCitasPorDia();
        citaServiceservice.mostrarTotalCitasPorDoctor();

        // Busqueda lineal sobre el arreglo de citas.
        Cita citaEncontrada = citaServiceservice.buscarCitaPorCodigo("CIT-001");
        if (citaEncontrada != null) {
            System.out.println("\n--- DETALLES DE LA CITA BUSCADA ---");
            citaEncontrada.mostrarDatos();
        }

        // Caso no encontrado para demostrar el retorno null del metodo de busqueda.
        Cita citaNoEncontrada = citaServiceservice.buscarCitaPorCodigo("CIT-008");
    }
}
