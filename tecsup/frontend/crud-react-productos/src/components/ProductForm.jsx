import { useEffect, useState } from "react";

function ProductForm({ onCreate, onUpdate, editingProduct }) {
  /* =========================
     ESTADOS
  ========================= */

  const [nombre, setNombre] = useState("");

  const [descripcion, setDescripcion] = useState("");

  const [precio, setPrecio] = useState("");

  const [stock, setStock] = useState("");

  /* =========================
     CARGAR PRODUCTO A EDITAR
  ========================= */

  useEffect(() => {
    if (editingProduct) {
      setNombre(editingProduct.nombre);
      setDescripcion(editingProduct.descripcion);
      setPrecio(editingProduct.precio);
      setStock(editingProduct.stock);
    }
  }, [editingProduct]);

  /* =========================
     SUBMIT
  ========================= */

  async function handleSubmit(event) {
    event.preventDefault();

    const productData = {
      nombre,
      descripcion,
      precio: Number(precio),
      stock: Number(stock),
      estado: true
    };

    /* =========================
       EDITAR
    ========================= */

    if (editingProduct) {
      await onUpdate(editingProduct.id, productData);
    } else {
      /* =========================
       CREAR
    ========================= */
      await onCreate(productData);
    }

    /* =========================
       LIMPIAR FORM
    ========================= */

    setNombre("");
    setDescripcion("");
    setPrecio("");
    setStock("");
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>{editingProduct ? "Editar Producto" : "Crear Producto"}</h2>

      <input
        type="text"
        placeholder="Nombre"
        value={nombre}
        onChange={(e) => setNombre(e.target.value)}
        required
      />

      <br />
      <br />

      <input
        type="text"
        placeholder="Descripción"
        value={descripcion}
        onChange={(e) => setDescripcion(e.target.value)}
        required
      />

      <br />
      <br />

      <input
        type="number"
        placeholder="Precio"
        value={precio}
        onChange={(e) => setPrecio(e.target.value)}
        required
      />

      <br />
      <br />

      <input
        type="number"
        placeholder="Stock"
        value={stock}
        onChange={(e) => setStock(e.target.value)}
        required
      />

      <br />
      <br />

      <button type="submit">{editingProduct ? "Actualizar Producto" : "Guardar Producto"}</button>
    </form>
  );
}

export default ProductForm;
