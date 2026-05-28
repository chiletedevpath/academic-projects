package edu.pe.utp.principios_solid.practica_calificada.ejercicio5;

public class PedidoService {

    private Database db;
    private Notificador notificador;

    public PedidoService(Database db, Notificador notificador) {
        this.db = db;
        this.notificador = notificador;
    }

    public void procesarPedido(String pedido) {
        db.conectar();
        db.guardar(pedido);

        notificador.enviar("Pedido procesado: " + pedido);
    }
}

