package pe.com.utp.modelo.persona;

import pe.com.utp.modelo.enums.Especialidad;
import pe.com.utp.modelo.enums.TurnoConsultaAmbulatoria;

/*
 * Clase hija de Persona.
 * Representa a los doctores registrados en la clinica.
 */
public class Doctor extends Persona {

    // Atributos especificos del doctor
    private String cmp;
    private Especialidad especialidad;
    private TurnoConsultaAmbulatoria horario;

    /*
     * Constructor de la clase Doctor.
     * Inicializa atributos heredados y propios.
     */
    public Doctor(String codigo, String dni, String nombres, String apellidos, String telefono, String cmp, String correo, Especialidad especialidad, TurnoConsultaAmbulatoria horario) {
        super(codigo, dni, nombres, apellidos, telefono, correo);
        this.especialidad = especialidad;
        this.horario = horario;
        this.cmp = cmp;
    }

    /*
     * Implementacion del metodo abstracto heredado.
     * Permite mostrar informacion especifica del doctor.
     */
    @Override
    public void mostrarDatos() {
        System.out.println("----- DATOS DEL DOCTOR -----");
        System.out.println("Código: " + getCodigo());
        System.out.println("DNI: " + getDni());
        System.out.println("Nombres: " + getNombres());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("Teléfono: " + getTelefono());
        System.out.println("CMP: " + cmp);
        System.out.println("Correo: " + getCorreo());
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Horario: " + horario);
    }

    // Metodos de acceso y modificacion de atributos
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public TurnoConsultaAmbulatoria getHorario() {
        return horario;
    }

    public void setHorario(TurnoConsultaAmbulatoria horario) {
        this.horario = horario;
    }
}