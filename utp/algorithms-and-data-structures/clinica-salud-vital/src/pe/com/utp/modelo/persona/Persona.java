package pe.com.utp.modelo.persona;

/*
 * Clase abstracta base del sistema
 * Centraliza los atributos y comportamientos comunes de todas las personas registradas en la clinica
 */
public abstract class Persona {

    // Atributos comunes de la clase Persona
    private String codigo;
    private String dni;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;

    /*
     * Constructor principal utilizado por las clases hijas.
     * Permite inicializar los datos comunes de una persona.
     */
    public Persona(String codigo, String dni, String nombres, String apellidos, String telefono, String correo) {

        this.codigo = codigo;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
    }

    /*
     * Metodo abstracto obligatorio.
     * Cada clase hija debera definir como mostrar sus datos.
     */
    public abstract void mostrarDatos();

    // Getters y Setters
    // Metodos de acceso y modificación de atributos.
    // Se aplica encapsulamiento mediante getters y setters.
    public String getCodigo() {
        return codigo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}