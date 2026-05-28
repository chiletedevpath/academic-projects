package edu.pe.utp.pc2.ejercicio1;

class DatabaseConnection {

    // Instancia única
    private static DatabaseConnection instancia;

    // Constructor privado
    private DatabaseConnection() {
    }

    // Metodo para obtener la instancia única
    public static DatabaseConnection getInstancia() {

        if (instancia == null) {

            instancia = new DatabaseConnection();
        }

        return instancia;
    }

    // Metodo de conexión
    public void connect() {

        System.out.println("Conectado a la BD");
    }
}