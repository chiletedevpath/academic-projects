# Aplicar uno de los principios SOLID con JAVA

```java
class MySQLDatabase {

    public void conectar() {

        System.out.println("Conectando a MySQL...");
    }

    public void guardar(String dato) {

        System.out.println("Guardando en MySQL: " + dato);
    }
}

class EmailService {

    public void enviar(String mensaje) {

        System.out.println("Enviando email: " + mensaje);
    }
}

class PedidoService {

    private MySQLDatabase db = new MySQLDatabase();
    private EmailService emailService = new EmailService();

    public void procesarPedido(String pedido) {

        db.conectar();
        db.guardar(pedido);

        emailService.enviar("Pedido procesado: " + pedido);
    }
}