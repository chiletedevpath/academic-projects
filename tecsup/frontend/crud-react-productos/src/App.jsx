import "./styles/app.css";

import { useEffect, useState } from "react";

import ProductForm from "./components/ProductForm";

import {
  getProducts,
  createProduct,
  deleteProduct,
  updateProduct
} from "./services/productService";

function App() {
  /* =========================
     ESTADOS
  ========================= */

  const [products, setProducts] = useState([]);

  const [loading, setLoading] = useState(true);

  const [error, setError] = useState(null);

  const [editingProduct, setEditingProduct] = useState(null);

  /* =========================
     OBTENER PRODUCTOS
  ========================= */

  async function loadProducts() {
    try {
      const data = await getProducts();

      setProducts(data);
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  }

  /* =========================
     CREAR PRODUCTO
  ========================= */

  async function handleCreate(product) {
    try {
      await createProduct(product);

      await loadProducts();
    } catch (error) {
      alert(error.message);
    }
  }

  /* =========================
     ELIMINAR PRODUCTO
  ========================= */

  async function handleDelete(id) {
    const confirmDelete = window.confirm("¿Deseas eliminar este producto?");

    if (!confirmDelete) {
      return;
    }

    try {
      await deleteProduct(id);

      await loadProducts();
    } catch (error) {
      alert(error.message);
    }
  }

  /* =========================
     ACTUALIZAR PRODUCTO
  ========================= */

  async function handleUpdate(id, product) {
    try {
      await updateProduct(id, product);

      setEditingProduct(null);

      await loadProducts();
    } catch (error) {
      alert(error.message);
    }
  }

  /* =========================
     EDITAR PRODUCTO
  ========================= */

  function handleEdit(product) {
    setEditingProduct(product);
  }

  /* =========================
     useEffect
  ========================= */

  useEffect(() => {
    loadProducts();
  }, []);

  /* =========================
     RENDER
  ========================= */

  if (loading) {
    return <h2>Cargando productos...</h2>;
  }

  if (error) {
    return <h2>{error}</h2>;
  }

  return (
    <main>
      <h1>CRUD React Productos</h1>

      <hr />

      <ProductForm
        onCreate={handleCreate}
        onUpdate={handleUpdate}
        editingProduct={editingProduct}
      />

      <hr />

      {products.map((product) => (
        <div key={product.id} className="product-card">
          {" "}
          <h3>{product.nombre}</h3>
          <p>{product.descripcion}</p>
          <p>Precio: S/ {product.precio}</p>
          <p>Stock: {product.stock}</p>
          <button onClick={() => handleEdit(product)}>Editar</button>
          <button onClick={() => handleDelete(product.id)}>Eliminar</button>
          <hr />
          <hr />
        </div>
      ))}
    </main>
  );
}

export default App;
