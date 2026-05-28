package edu.pe.utp.principios_solid.practica_calificada.ejercicio5;

public class Main {

    public static void main(String[] args) {

        Database db = new MySQLDatabase();
        Notificador email = new EmailService();

        PedidoService pedidoService = new PedidoService(db, email);

        pedidoService.procesarPedido("Pedido #001");
    }
}

