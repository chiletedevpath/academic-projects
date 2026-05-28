package pe.edu.utp.modelo.cliente;

import pe.edu.utp.modelo.persona.Persona;

public class Cliente extends Persona {
    private String codigoCliente;
    private TipoCliente tipoCliente;


    public Cliente(String codigoCliente, TipoCliente tipoCliente) {
        this.codigoCliente = codigoCliente;
        this.tipoCliente = tipoCliente;
    }

    public Cliente(int idPersona, String DNI, String nombres, String apellidos, String telefono, String correo, String direccion, String codigoCliente, TipoCliente tipoCliente) {
        super(idPersona, DNI, nombres, apellidos, telefono, correo, direccion);
        this.codigoCliente = codigoCliente;
        this.tipoCliente = tipoCliente;
    }

    @Override
    public void mostrarInformacionBasica() {
        System.out.println(this.getDNI() + "\n" + this.getNombres() + "\n" + this.getApellidos());
        ;
    }
}
