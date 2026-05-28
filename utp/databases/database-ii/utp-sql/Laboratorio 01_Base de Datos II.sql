CREATE TABLE clientes(
id_cliente SERIAL PRIMARY KEY,
nombre_cliente VARCHAR(100) NOT NULL,
ciudad VARCHAR(100) NOT NULL
);

SELECT * FROM clientes;

CREATE TABLE productos(
id_producto SERIAL PRIMARY KEY,
nombre_producto VARCHAR(100) NOT NULL,
precio DECIMAL(10,2) NOT NULL
);

SELECT * FROM productos;

CREATE TABLE ventas(
id_venta SERIAL PRIMARY KEY,
id_cliente INT NOT NULL,
id_producto INT NOT NULL,
cantidad INT NOT NULL,
fecha DATE NOT NULL,
FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

SELECT * FROM VENTAS;

INSERT INTO clientes(nombre_cliente, ciudad) VALUES
('Ana Torres', 'Lima'),
('Luis Ramos', 'Arequipa'),
('Maria Lopez', 'Cusco'),
('Jorge Diaz', 'Lima');


INSERT INTO productos(nombre_producto, precio) VALUES
('Laptop', 3500.00),
('Mouse', 80.00),
('Teclado', 150.00),
('Monitor', 900.00);

INSERT INTO ventas(id_cliente, id_producto, cantidad, fecha) VALUES
(1, 1, 1, '2026-04-01'),
(2, 2, 3, '2026-04-01'),
(3, 3, 2, '2026-04-02'),
(1, 4, 1, '2026-04-03'),
(4, 1, 1, '2026-04-03'),
(2, 3, 4, '2026-04-04');

SELECT * FROM clientes;
SELECT * FROM productos; 
SELECT * FROM ventas;

SELECT c.ciudad, SUM(v.cantidad) AS total_vendido
FROM ventas v
JOIN clientes c ON v.id_cliente = c.id_cliente
GROUP BY c.ciudad
ORDER BY SUM(v.cantidad) DESC;

SELECT p.nombre_producto, SUM(v.cantidad * p.precio) AS ingreso_total
FROM ventas v
JOIN productos p ON v.id_producto = p.id_producto
GROUP BY p.nombre_producto
ORDER BY ingreso_total DESC;


