# ☕ La Cafetería Asíncrona

Proyecto desarrollado en JavaScript puro para practicar conceptos de programación asíncrona utilizando Promesas, `async/await`, `setTimeout` y manejo de errores.

---

# 📚 Objetivo

Simular el flujo de pedidos de una cafetería donde cada operación toma tiempo y puede fallar, aplicando:

- `Promise`
- `resolve` y `reject`
- `.then()` y `.catch()`
- `async/await`
- `try/catch`
- `setTimeout`
- manejo de errores asíncronos

---

# ⚙️ Tecnologías utilizadas

- JavaScript (ES6+)
- Node.js

---

# 📂 Estructura del proyecto

```txt
cafeteria-asincrona-js/
│
├── app.js
└── README.md
```

---

# 🚀 Cómo ejecutar el proyecto

## 1. Clonar el repositorio

```bash
git clone https://github.com/chiletedevpath/reto2-cafeteria-asincrona-js.git
```

## 2. Ingresar a la carpeta del proyecto

```bash
cd cafeteria-asincrona-js
```

## 3. Ejecutar el programa

```bash
node app.js
```

---

# 🧠 Funcionamiento del sistema

El sistema simula el flujo completo de una cafetería:

1. El cliente realiza un pedido.
2. El sistema verifica si el producto existe en el menú.
3. El café entra en preparación.
4. Existe una probabilidad de falla de la máquina.
5. El pedido se entrega o se muestra un error.

---

# 📌 Funcionalidades implementadas

## ✅ Recepción de pedidos

La función `recibirPedido()`:

- verifica si el producto existe en el menú
- simula una demora de 3 segundos
- resuelve o rechaza la promesa según el caso

## ✅ Preparación del café

La función `prepararCafe()`:

- simula otros 3 segundos de preparación
- tiene un 20% de probabilidad de falla
- entrega el café o genera un error

## ✅ Procesamiento asíncrono

La función `procesarPedido()`:

- utiliza `async/await`
- controla el flujo completo del sistema
- maneja errores mediante `try/catch`

---

# 🖥️ Ejemplo de salida en consola

## Caso exitoso

```txt
📝 Procesando pedido...
Pedido recibido: latte
✅ Entregado: ☕ Café listo: latte
```

## Caso de producto inexistente

```txt
📝 Procesando pedido...
❌ Error: No tenemos te helado en el menú
```

## Caso de falla de máquina

```txt
📝 Procesando pedido...
Pedido recibido: espresso
❌ Error: La máquina está rota, no se pudo preparar el café
```

---

# 📖 Conceptos aplicados

- Programación asíncrona
- Promesas en JavaScript
- Manejo de errores
- `async/await`
- Temporizadores con `setTimeout`
- Flujo secuencial asíncrono

---

# 👨‍💻 Autor

Adrian Pisco Soto

Proyecto desarrollado como práctica del curso Full Stack - Tecsup
