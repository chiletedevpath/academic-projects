package pe.edu.utp.modelo.persona;

public abstract class Persona {
    private int idPersona;
    private String DNI;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;

    public Persona() {
    }

    public Persona(int idPersona, String DNI, String nombres, String apellidos, String telefono, String correo, String direccion) {
        this.idPersona = idPersona;
        this.DNI = DNI;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*NO LO USO POR EL MOMENTO
    public void nombreCompleto(){
        System.out.println(this.nombres + " " + this.apellidos);
    };
    */

    public abstract void mostrarInformacionBasica();
}

