package pe.com.utp.modelo.persona;

/*
 * Clase hija de Persona.
 * Representa a los administradores del sistema clinico.
 */
public class Administrador extends Persona {

    // Atributo propio
    private String cargo;

    /*
     * Constructor de la clase Administrador.
     * Inicializa atributos heredados y propios.
     */
    public Administrador(String codigo, String dni, String nombres, String apellidos, String telefono, String correo, String cargo) {
        super(codigo, dni, nombres, apellidos, telefono, correo);
        this.cargo = cargo;
    }

    /*
     * Implementacion del metodo abstracto heredado.
     * Permite mostrar información específica del administrador.
     */
    @Override
    public void mostrarDatos() {

        System.out.println("----- DATOS DEL ADMINISTRADOR -----");
        System.out.println("Código: " + getCodigo());
        System.out.println("DNI: " + getDni());
        System.out.println("Nombres: " + getNombres());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("Teléfono: " + getTelefono());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Cargo: " + cargo);
    }

    // Metodos de acceso y modificacion de atributos
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}