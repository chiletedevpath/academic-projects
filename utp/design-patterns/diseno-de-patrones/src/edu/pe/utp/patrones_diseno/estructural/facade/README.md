# PATRÓN DE DISEÑO ESTRUCTURAL FACADE

---

## Ejercicio 01

## 📌 Enunciado

Una tienda en línea cuenta con varios subsistemas dentro de su plataforma:

- 📦 Un sistema para gestionar el stock de productos.
- 💳 Un sistema para procesar pagos.
- 🧾 Un sistema para generar facturas.
- 📩 Un sistema para enviar notificaciones al cliente.

Cada uno de estos sistemas funciona correctamente de manera independiente. Sin embargo, todos están siendo utilizados directamente desde el módulo principal, provocando que el código sea difícil de entender, mantener y administrar.

---

## ⚠️ Problema identificado

El módulo principal debe comunicarse directamente con múltiples subsistemas para completar una compra.

Esto genera:

- ❌ alto acoplamiento
- ❌ código complejo
- ❌ dificultad de mantenimiento
- ❌ poca organización del sistema

---

## 🎯 Objetivo del ejercicio

Desarrollar un programa en Java ☕ aplicando el patrón de diseño **Facade**, con el fin de simplificar la comunicación entre el módulo principal y los diferentes subsistemas de la tienda virtual.

---

## ✅ Solución esperada

Implementar una clase fachada (**Facade**) que centralice todas las operaciones necesarias para completar una compra.

La fachada deberá proporcionar un único método llamado:

```java
realizarCompra()