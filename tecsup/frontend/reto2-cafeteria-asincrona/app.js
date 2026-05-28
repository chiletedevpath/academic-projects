/* =========================================
   MENU DISPONIBLE
========================================= */

const menu = ["espresso", "cappuccino", "latte", "americano"];

/* =========================================
   PASO 1 - RECIBIR PEDIDO
========================================= */

function recibirPedido(producto) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      /* Verifica si el producto existe */
      if (menu.includes(producto)) {
        resolve(`Pedido recibido: ${producto}`);
      } else {
        reject(`No tenemos ${producto} en el menú`);
      }
    }, 3000);
  });
}

/* =========================================
   PASO 2 - PREPARAR CAFE
========================================= */

function prepararCafe(mensajePrevio) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      /* 20% de probabilidad de error */
      const falla = Math.random() < 0.2;

      if (falla) {
        reject("La máquina está rota, no se pudo preparar el café");
      } else {
        /* Extrae el nombre del producto */
        const producto = mensajePrevio.split(": ")[1];

        resolve(`Café listo: ${producto}`);
      }
    }, 3000);
  });
}

/* =========================================
   PASO 3 - PROCESAR PEDIDO
========================================= */

async function procesarPedido(producto) {
  try {
    console.log("Procesando pedido...");

    /* Espera el pedido */
    const resultado1 = await recibirPedido(producto);

    console.log(resultado1);

    /* Espera preparar el cafe */
    const resultado2 = await prepararCafe(resultado1);

    console.log(`Entregado: ${resultado2}`);
  } catch (error) {
    console.log(`Error: ${error}`);
  }
}

/* =========================================
   PRUEBAS
========================================= */

/* Producto válido */
procesarPedido("latte");

/* Producto inválido */
/*
procesarPedido("te helado");
*/
