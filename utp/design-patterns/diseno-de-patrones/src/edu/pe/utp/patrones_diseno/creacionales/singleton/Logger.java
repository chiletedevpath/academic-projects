package edu.pe.utp.patrones_diseno.creacionales.singleton;

class Logger {

    // Instancia única
    private static Logger instancia;

    // Constructor privado
    private Logger() {
    }

    // Método para obtener la única instancia
    public static Logger getInstancia() {

        if (instancia == null) {

            instancia = new Logger();
        }

        return instancia;
    }

    // Método log
    public void log(String msg) {

        System.out.println(msg);
    }
}