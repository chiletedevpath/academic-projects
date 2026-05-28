# 🛒 EJERCICIO PROPUESTO
# Sistema: Gestión de Pedidos con Notificaciones

---

## 📌 Enunciado

Una empresa desea desarrollar un sistema para registrar pedidos de clientes.

Cada vez que se registre un pedido, el sistema debe ejecutar automáticamente distintas acciones relacionadas al proceso de notificación y registro de eventos.

El sistema debe estar diseñado de manera flexible y escalable, permitiendo agregar nuevas funcionalidades sin modificar la lógica principal del sistema.

---

# 🎯 Objetivo del ejercicio

Desarrollar un programa en Java ☕ aplicando principios SOLID y patrones de diseño para gestionar pedidos y ejecutar acciones automáticas cuando se registre un nuevo pedido.

---

# 📋 Requerimientos del sistema

## 1️⃣ Registro de pedidos

El sistema debe permitir registrar pedidos con la siguiente información:

- 🆔 ID del pedido
- 👤 Nombre del cliente
- 💰 Monto del pedido

---

## 2️⃣ Clase central del sistema

Debe existir una clase principal encargada de gestionar los pedidos registrados dentro del sistema.

Además, esta clase debe garantizar que solo exista una única instancia durante toda la ejecución del programa.

---

## 3️⃣ Acciones automáticas al registrar pedidos

Cada vez que se registre un pedido, el sistema deberá ejecutar automáticamente las siguientes acciones:

- 📝 Registrar el evento en un log
- 📧 Notificar al cliente
- 📱 Enviar un mensaje mediante un servicio externo de WhatsApp

---

## 4️⃣ Escalabilidad del sistema

El sistema debe permitir agregar nuevas formas de notificación o nuevas acciones automáticas sin modificar la clase principal encargada de gestionar los pedidos.

---

## 5️⃣ Servicio externo no modificable

Existe una clase externa que no puede ser modificada y que será utilizada para enviar mensajes de WhatsApp.

```java
class ServicioWhatsAppExterno {

    public void enviarMensajeWhatsApp(String texto) {

        System.out.println("[WHATSAPP EXTERNO] " + texto);
    }
}