package pe.com.utp.modelo.cita;

import pe.com.utp.modelo.enums.EstadoCita;
import pe.com.utp.modelo.persona.Doctor;
import pe.com.utp.modelo.persona.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Cita {

    // Codigo unico usado para buscar la cita dentro del arreglo de CitaService.
    private String codigo;

    // Relaciones principales del dominio clinico.
    private Paciente paciente;
    private Doctor doctor;

    // Datos propios de la atencion programada.
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoCita estado;
    private String motivo;

    // Fecha y hora en que el objeto Cita fue creado en el sistema.
    private final LocalDateTime fechaRegistro;

    public Cita(String codigo, Paciente paciente, Doctor doctor, LocalDate fecha, LocalTime hora, EstadoCita estado, String motivo) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.motivo = motivo;
        this.fechaRegistro = LocalDateTime.now();
    }

    public void mostrarDatos() {
        DateTimeFormatter formatoFechaPeru = DateTimeFormatter.ofPattern("EEEE dd/MM/yyy");
        DateTimeFormatter formatoHoraConSegundos = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatoHoraRegistro = DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy HH:mm:ss");

        System.out.println("Codigo de atencion: " + codigo);
        System.out.println("Nombre del Paciente: " + paciente.getNombres() + " " + paciente.getApellidos());
        System.out.println("DNI: " + paciente.getDni());
        System.out.println("Medico responsable: " + doctor.getNombres() + " " + doctor.getApellidos());
        System.out.println("Nro. CMP: " + doctor.getCmp());
        System.out.println("Fecha: " + fecha.format(formatoFechaPeru));
        System.out.println("Hora: " + hora.format(formatoHoraConSegundos));
        System.out.println("Estado: " + estado);
        System.out.println("Motivo: " + motivo);
        System.out.println("Registrada: " + fechaRegistro.format(formatoHoraRegistro));
    }

    public void cancelar() {
        this.estado = EstadoCita.CANCELADA;
    }

    // Reprogramar modifica fecha y hora, y deja evidencia del cambio de estado.
    public void reprogramar(LocalDate nuevaFecha, LocalTime nuevaHora) {
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        this.estado = EstadoCita.REPROGRAMADA;
    }

    public void marcarComoAtendida() {
        this.estado = EstadoCita.ATENDIDA;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    private void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    private void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
