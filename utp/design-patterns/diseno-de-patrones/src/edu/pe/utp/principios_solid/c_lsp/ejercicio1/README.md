# Aplicar uno de los principios SOLID con JAVA

```java
interface Notificacion {

    void enviar(String mensaje);

}

class Email implements Notificacion {

    public void enviar(String mensaje) {

        System.out.println("Enviando email: " + mensaje);
    }
}

class SMS implements Notificacion {

    public void enviar(String mensaje) {

        System.out.println("Enviando SMS: " + mensaje);
    }
}

class NotificacionSilenciosa implements Notificacion {

    public void enviar(String mensaje) {

        // no hace nada
    }
}