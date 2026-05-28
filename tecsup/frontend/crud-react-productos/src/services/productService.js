const API_URL = `${import.meta.env.VITE_API_BASE_URL}/productos`;

/* =========================
   OBTENER PRODUCTOS
========================= */

export async function getProducts() {
  const response = await fetch(API_URL);

  if (!response.ok) {
    throw new Error("Error al obtener productos");
  }

  return await response.json();
}

/* =========================
   CREAR PRODUCTO
========================= */

export async function createProduct(product) {
  const response = await fetch(API_URL, {
    method: "POST",

    headers: {
      "Content-Type": "application/json"
    },

    body: JSON.stringify(product)
  });

  if (!response.ok) {
    throw new Error("Error al crear producto");
  }

  return await response.json();
}

/* =========================
   ELIMINAR PRODUCTO
========================= */

export async function deleteProduct(id) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "DELETE"
  });

  if (!response.ok) {
    throw new Error("Error al eliminar producto");
  }
}

/* =========================
   ACTUALIZAR PRODUCTO
========================= */

export async function updateProduct(id, product) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",

    headers: {
      "Content-Type": "application/json"
    },

    body: JSON.stringify(product)
  });

  if (!response.ok) {
    throw new Error("Error al actualizar producto");
  }

  return await response.json();
}
