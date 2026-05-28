CREATE TABLE clientes(
    idcliente INTEGER PRIMARY KEY,
    nombre VARCHAR(50),
    ciudad VARCHAR(50)
);

CREATE TABLE productos(
    idproducto INTEGER PRIMARY KEY,
    nombre_producto VARCHAR(50),
    precio DECIMAL(10,2)
);

CREATE TABLE ventas(
    idventa INTEGER PRIMARY KEY,
    idcliente INTEGER,
    idproducto INTEGER,
    fecha DATE,
    cantidad INTEGER,
    monto DECIMAL(10,2),
    FOREIGN KEY (idcliente) REFERENCES clientes(idcliente),
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto)
);

INSERT INTO clientes VALUES
(1,'Ana Torres','Lima'),
(2,'Luis Perez','Arequipa'),
(3,'Maria Soto','Cusco'),
(4,'Carlos Rojas','Lima'),
(5,'Lucia Fernandez','Piura'),
(6,'Jose Ramirez','Trujillo'),
(7,'Andrea Vega','Lima'),
(8,'Miguel Castro','Cusco'),
(9,'Rosa Delgado','Arequipa'),
(10,'Pedro Salas','Lima');
SELECT * FROM clientes;

INSERT INTO productos VALUES
(1,'Laptop',3500),
(2,'Mouse',50),
(3,'Teclado',120),
(4,'Monitor',900),
(5,'Impresora',700),
(6,'Tablet',1200),
(7,'Celular',2500),
(8,'Audifonos',150),
(9,'USB',40),
(10,'Camara',1800);

SELECT * FROM productos;

INSERT INTO ventas VALUES
(101,1,1,'2025-01-01',1,3500),
(102,2,2,'2025-01-02',2,100),
(103,3,3,'2025-01-03',1,120),
(104,4,4,'2025-01-04',1,900),
(105,5,5,'2025-01-05',1,700),
(106,6,6,'2025-01-06',2,2400),
(107,7,7,'2025-01-07',1,2500),
(108,8,8,'2025-01-08',3,450),
(109,9,9,'2025-01-09',5,200),
(110,10,10,'2025-01-10',1,1800),
(111,1,2,'2025-01-11',3,150),
(112,2,3,'2025-01-12',2,240),
(113,3,4,'2025-01-13',1,900),
(114,4,5,'2025-01-14',2,1400),
(115,5,6,'2025-01-15',1,1200),
(116,6,7,'2025-01-16',2,5000),
(117,7,8,'2025-01-17',4,600),
(118,8,9,'2025-01-18',3,120),
(119,9,10,'2025-01-19',1,1800),
(120,10,1,'2025-01-20',1,3500),
(121,1,3,'2025-01-21',2,240),
(122,2,4,'2025-01-22',1,900),
(123,3,5,'2025-01-23',3,2100),
(124,4,6,'2025-01-24',1,1200),
(125,5,7,'2025-01-25',2,5000),
(126,6,8,'2025-01-26',5,750),
(127,7,9,'2025-01-27',4,160),
(128,8,10,'2025-01-28',1,1800),
(129,9,1,'2025-01-29',2,7000),
(130,10,2,'2025-01-30',3,150),
(131,1,4,'2025-02-01',1,900),
(132,2,5,'2025-02-02',2,1400),
(133,3,6,'2025-02-03',1,1200),
(134,4,7,'2025-02-04',1,2500),
(135,5,8,'2025-02-05',3,450),
(136,6,9,'2025-02-06',6,240),
(137,7,10,'2025-02-07',1,1800),
(138,8,1,'2025-02-08',1,3500),
(139,9,2,'2025-02-09',4,200),
(140,10,3,'2025-02-10',2,240),
(141,1,5,'2025-02-11',1,700),
(142,2,6,'2025-02-12',2,2400),
(143,3,7,'2025-02-13',1,2500),
(144,4,8,'2025-02-14',2,300),
(145,5,9,'2025-02-15',5,200),
(146,6,10,'2025-02-16',1,1800),
(147,7,1,'2025-02-17',1,3500),
(148,8,2,'2025-02-18',2,100),
(149,9,3,'2025-02-19',3,360),
(150,10,4,'2025-02-20',1,900);

SELECT * FROM ventas;

-- Primero: validar clientes inexistentes en ventas
SELECT *
FROM ventas v
LEFT JOIN clientes c ON v.idcliente = c.idcliente
WHERE c.idcliente IS NULL;

-- Segundo: Validar productos inexistentes en ventas
SELECT *
FROM ventas v
LEFT JOIN productos p ON v.idproducto = p.idproducto
WHERE p.idproducto IS NULL;

-- Tercero: Consulta para validar cantidades incorrectas
SELECT *
FROM ventas
WHERE cantidad <= 0;

-- Cuarto: Consulta para validar precios incorrectos
SELECT *
FROM productos
WHERE precio <= 0;

-- Consulta para validar montos inconsistentes
SELECT *
FROM ventas v
INNER JOIN productos p ON v.idproducto = p.idproducto
WHERE v.monto <> (v.cantidad * p.precio);


-- Conversión correcta
-- SELECT CAST('3500' AS INTEGER);
-- SELECT CAST('1200.50' AS DECIMAL(10,2));

-- Conversión incorrecta
-- SELECT CAST('ABC' AS INTEGER);
-- SELECT CAST('12X' AS INTEGER);

-- 1.	Mostrar todas las ventas con:
SELECT c.nombre AS cliente, p.nombre_producto AS producto, v.cantidad, v.monto
FROM ventas v
INNER JOIN clientes c ON v.idcliente = c.idcliente
INNER JOIN productos p ON v.idproducto = p.idproducto

-- 2.	Mostrar el total vendido por cliente.
SELECT c.idcliente, c.nombre AS cliente, SUM(v.monto) AS monto_por_cliente 
FROM ventas v
INNER JOIN clientes c ON v.idcliente = c.idcliente
GROUP BY c.idcliente, c.nombre
ORDER BY c.nombre ASC;

-- 3.	Mostrar el total vendido por producto.
SELECT p.nombre_producto AS producto, SUM(v.monto) AS monto_por_producto
FROM ventas v
INNER JOIN productos p ON v.idproducto = p.idproducto
GROUP BY p.idproducto, p.nombre_producto 
ORDER BY monto_por_producto DESC;

-- 4.	Mostrar el promedio de ventas por cliente.
SELECT c.nombre AS cliente, ROUND (AVG(v.monto), 2) AS promedio_por_cliente 
FROM ventas v
INNER JOIN clientes c ON v.idcliente = c.idcliente
GROUP BY c.idcliente, c.nombre
ORDER BY promedio_por_cliente ASC;

-- 5.	Mostrar solo clientes cuya suma de ventas sea mayor a 2000.
SELECT c.nombre, SUM(v.monto) AS monto_por_cliente
FROM ventas v
INNER JOIN  clientes c ON c.idcliente = v.idcliente
GROUP BY c.idcliente, c.nombre
HAVING SUM(v.monto) > 2000
ORDER BY monto_por_cliente DESC;

