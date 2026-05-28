SELECT username, default_tablespace 
FROM user_users;

SELECT tablespace_name 
FROM dba_tablespaces;

SELECT tablespace_name, file_name 
FROM dba_data_files;

-- Tabla CLIENTES
CREATE TABLE clientes (
    id_cliente NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    email VARCHAR2(100),
    telefono VARCHAR2(20)
);

SELECT * FROM clientes;

-- Tabla PRODUCTOS
CREATE TABLE productos (
    id_producto NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    precio NUMBER(10,2) NOT NULL,
    stock NUMBER NOT NULL
);

SELECT * FROM productos;


-- Tabla VENTAS
CREATE TABLE ventas (
    id_venta NUMBER PRIMARY KEY,
    id_cliente NUMBER,
    fecha DATE DEFAULT SYSDATE,
    CONSTRAINT fk_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES clientes(id_cliente)
);

SELECT * FROM ventas;

-- Tabla DETALLE_VENTA
CREATE TABLE detalle_venta (
    id_detalle NUMBER PRIMARY KEY,
    id_venta NUMBER,
    id_producto NUMBER,
    cantidad NUMBER NOT NULL,
    precio NUMBER(10,2) NOT NULL,
    CONSTRAINT fk_venta
        FOREIGN KEY (id_venta)
        REFERENCES ventas(id_venta),
    CONSTRAINT fk_producto
        FOREIGN KEY (id_producto)
        REFERENCES productos(id_producto)
);

SELECT * FROM detalle_venta;

-- CLIENTES
INSERT INTO clientes VALUES (1, 'Juan Perez', 'juan@gmail.com', '999111222');
INSERT INTO clientes VALUES (2, 'Maria Lopez', 'maria@gmail.com', '988777666');

-- PRODUCTOS
INSERT INTO productos VALUES (1, 'Laptop', 2500, 10);
INSERT INTO productos VALUES (2, 'Mouse', 50, 100);

-- VENTAS
INSERT INTO ventas VALUES (1, 1, SYSDATE);
INSERT INTO ventas VALUES (2, 2, SYSDATE);

-- DETALLE
INSERT INTO detalle_venta VALUES (1, 1, 1, 1, 2500);
INSERT INTO detalle_venta VALUES (2, 1, 2, 2, 50);
INSERT INTO detalle_venta VALUES (3, 2, 2, 3, 50);

-- Guardar cambios
COMMIT;

SELECT * FROM clientes;

-- 2. Productos
SELECT * FROM productos;

-- 3. Ventas con cliente
SELECT v.id_venta, c.nombre, v.fecha
FROM ventas v
JOIN clientes c ON v.id_cliente = c.id_cliente;

-- 4. Detalle de ventas
SELECT v.id_venta, p.nombre, d.cantidad, d.precio,
(d.cantidad * d.precio) AS subtotal
FROM detalle_venta d
JOIN ventas v ON d.id_venta = v.id_venta
JOIN productos p ON d.id_producto = p.id_producto;

-- 5. Total por cliente
SELECT c.nombre,
SUM(d.cantidad * d.precio) AS total
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
JOIN detalle_venta d ON v.id_venta = d.id_venta
GROUP BY c.nombre;

-- 6. Producto más vendido
SELECT p.nombre, SUM(d.cantidad) AS total_vendido
FROM productos p
JOIN detalle_venta d ON p.id_producto = d.id_producto
GROUP BY p.nombre
ORDER BY total_vendido DESC
FETCH FIRST 1 ROWS ONLY;

-- 7. Productos con stock bajo (<20)
SELECT * FROM productos
WHERE stock < 20;

-- 8. Ordenar productos por precio
SELECT * FROM productos
ORDER BY precio DESC;

-- Vista
CREATE OR REPLACE VIEW vista_ventas AS
SELECT v.id_venta,
v.fecha,
c.nombre AS cliente,
p.nombre AS producto,
d.cantidad,
d.precio,
(d.cantidad * d.precio) AS subtotal
FROM ventas v
JOIN clientes c ON v.id_cliente = c.id_cliente
JOIN detalle_venta d ON v.id_venta = d.id_venta
JOIN productos p ON d.id_producto = p.id_producto;

SELECT * FROM vista_ventas;

SELECT table_name, tablespace_name
FROM user_tables;

SELECT segment_name, tablespace_name
FROM user_segments
WHERE segment_type = 'TABLE';
