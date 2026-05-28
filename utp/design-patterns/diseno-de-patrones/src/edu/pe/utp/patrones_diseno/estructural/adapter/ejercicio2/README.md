# PATRÓN DE DISEÑO ESTRUCTURAL ADAPTER

---

## 🔌 Ejercicio 02

---

## 📌 Enunciado

Una empresa de mensajería ha desarrollado un nuevo sistema para generar notificaciones a través de correo electrónico.
Sin embargo, uno de sus módulos antiguos todavía utiliza una interfaz antigua de envío de SMS.

Se necesita aplicar el patrón de diseño **Adapter** para adaptar la nueva clase de notificaciones por correo electrónico
a la interfaz esperada por el módulo antiguo, el cual solo entiende el envío de mensajes SMS.

---

## 🎯 Objetivo del ejercicio

Desarrollar un programa en Java que permita adaptar el nuevo sistema de notificaciones al sistema antiguo, utilizando
el patrón **Adapter** para lograr la compatibilidad entre ambos sistemas.

---

## ⚠️ Problema identificado

Actualmente existen dos sistemas dentro de la empresa:

### 📱 Sistema Antiguo

El módulo antiguo trabaja únicamente con mensajes SMS.

### 📧 Sistema Moderno

El nuevo sistema trabaja con notificaciones por correo electrónico.

---

## ❌ Problema principal

Ambos sistemas no son compatibles entre sí, debido a que utilizan interfaces diferentes para enviar notificaciones.

Por ello, el módulo antiguo no puede comunicarse directamente con el nuevo sistema moderno.

---

## ✅ Solución esperada

Aplicar el patrón **Adapter** para crear una clase adaptadora que permita conectar ambos sistemas sin modificar
completamente el sistema antiguo.

---

## 🚀 Resultado esperado

El sistema antiguo deberá seguir funcionando normalmente, pero internamente utilizará el nuevo sistema de correo
electrónico gracias al Adapter.W