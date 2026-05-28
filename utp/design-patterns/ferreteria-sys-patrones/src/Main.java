import pe.edu.utp.modelo.cliente.Cliente;
import pe.edu.utp.modelo.cliente.TipoCliente;
import pe.edu.utp.modelo.usuario.RolUsuario;
import pe.edu.utp.modelo.usuario.Usuario;

public class Main {
    public static void main(String[] args) {

        Cliente cliente1 = new Cliente(1,
                "7374838181",
                "Adrian Pisco",
                "Pisco Soto",
                "945834043",
                "correo@nuevo.com",
                "San Salvador",
                "COD001",
                TipoCliente.NORMAL);

        Usuario usuario1 = new Usuario(1,
                "251469",
                "Kerosene",
                "Leon",
                "987524123",
                "kero@correo.com",
                "av.keroperdido",
                "kero",
                "admin123",
                RolUsuario.ADMINISTRADOR);

        System.out.println("Cliente 1");
        cliente1.mostrarInformacionBasica();

        System.out.println("\n===================\n");

        System.out.println("Usuario 1");
        usuario1.mostrarInformacionBasica();
    }
}

