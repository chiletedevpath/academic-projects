package pe.edu.utp.modelo.usuario;

import pe.edu.utp.modelo.persona.Persona;

public class Usuario extends Persona {
    private String username;
    private String password;
    private RolUsuario rolUsuario;

    public Usuario(String username, String password, RolUsuario rolUsuario) {
        this.username = username;
        this.password = password;
        this.rolUsuario = rolUsuario;
    }

    public Usuario(int idPersona, String DNI, String nombres, String apellidos, String telefono, String correo, String direccion, String username, String password, RolUsuario rolUsuario) {
        super(idPersona, DNI, nombres, apellidos, telefono, correo, direccion);
        this.username = username;
        this.password = password;
        this.rolUsuario = rolUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    @Override
    public void mostrarInformacionBasica() {
        System.out.println(this.getDNI() + "\n" + this.getNombres() + "\n" + this.getApellidos());

    }
}
