CREATE TABLE clientes (
    id_cliente INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    ciudad VARCHAR(50) NOT NULL
);

SELECT * FROM clientes;

CREATE TABLE medicamentos (
    id_medicamento INTEGER PRIMARY KEY,
    nombre_medicamento VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    stock INTEGER NOT NULL CHECK (stock >= 0),
    fecha_vencimiento DATE NOT NULL
);

SELECT * FROM medicamentos;

CREATE TABLE ventas (
    id_venta INTEGER PRIMARY KEY,
    id_cliente INTEGER NOT NULL,
    fecha_venta DATE NOT NULL DEFAULT CURRENT_DATE,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

SELECT * FROM ventas;


CREATE TABLE detalle_venta (
    id_detalle INTEGER PRIMARY KEY,
    id_venta INTEGER NOT NULL,
    id_medicamento INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas(id_venta),
    FOREIGN KEY (id_medicamento) REFERENCES medicamentos(id_medicamento)
);


SELECT * FROM detalle_venta;

INSERT INTO clientes VALUES
(1, 'Juan Pérez', '12345678', 'Lima'),
(2, 'Ana Torres', '87654321', 'Arequipa'),
(3, 'Luis Gómez', '45678912', 'Cusco'),
(4, 'Carla Díaz', '78912345', 'Lima');

SELECT * FROM clientes;

INSERT INTO medicamentos VALUES
(1, 'Paracetamol', 'Analgésico', 5.00, 100, '2026-12-31'),
(2, 'Ibuprofeno', 'Antiinflamatorio', 8.00, 80, '2026-10-15'),
(3, 'Amoxicilina', 'Antibiótico', 12.00, 50, '2025-12-01'),
(4, 'Vitamina C', 'Suplemento', 6.00, 120, '2027-01-10'),
(5, 'Jarabe para la tos', 'Respiratorio', 10.00, 60, '2025-11-20');

SELECT * FROM medicamentos;

INSERT INTO ventas VALUES
(1, 1, '2026-04-01'),
(2, 2, '2026-04-02'),
(3, 3, '2026-04-03'),
(4, 4, '2026-04-04');

SELECT * FROM ventas;

INSERT INTO detalle_venta VALUES
(1, 1, 1, 3),
(2, 1, 4, 2),
(3, 2, 2, 4),
(4, 2, 5, 1),
(5, 3, 3, 2),
(6, 4, 1, 5);

SELECT * FROM ventas;

SELECT 
    c.nombre AS cliente,
    m.nombre_medicamento AS medicamento,
    d.cantidad,
    v.fecha_venta
FROM detalle_venta d
INNER JOIN ventas v ON d.id_venta = v.id_venta
INNER JOIN clientes c ON v.id_cliente = c.id_cliente
INNER JOIN medicamentos m ON d.id_medicamento = m.id_medicamento;

SELECT 
    SUM(m.precio * d.cantidad) AS total_vendido
FROM detalle_venta d
INNER JOIN medicamentos m ON d.id_medicamento = m.id_medicamento;

SELECT 
    m.nombre_medicamento,
    SUM(d.cantidad) AS total_unidades_vendidas
FROM detalle_venta d
INNER JOIN medicamentos m ON d.id_medicamento = m.id_medicamento
GROUP BY m.nombre_medicamento
ORDER BY total_unidades_vendidas DESC;

SELECT 
    c.nombre AS cliente,
    COUNT(v.id_venta) AS cantidad_compras
FROM clientes c
INNER JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.nombre
ORDER BY cantidad_compras DESC
LIMIT 1;

CREATE OR REPLACE VIEW vw_reporte_ventas_farmacia AS
SELECT 
    v.id_venta AS venta,
    c.nombre AS cliente,
    m.nombre_medicamento AS medicamento,
    m.categoria,
    d.cantidad,
    m.precio,
    (d.cantidad * m.precio) AS subtotal
FROM ventas v
INNER JOIN clientes c ON v.id_cliente = c.id_cliente
INNER JOIN detalle_venta d ON v.id_venta = d.id_venta
INNER JOIN medicamentos m ON d.id_medicamento = m.id_medicamento;

SELECT * FROM vw_reporte_ventas_farmacia;

CREATE OR REPLACE FUNCTION fn_total_venta(p_id_venta INT)
RETURNS DECIMAL(10,2) AS $$
DECLARE
    total DECIMAL(10,2);
BEGIN
    SELECT COALESCE(SUM(d.cantidad * m.precio), 0)
    INTO total
    FROM detalle_venta d
    INNER JOIN medicamentos m ON d.id_medicamento = m.id_medicamento
    WHERE d.id_venta = p_id_venta;

    RETURN total;
END;
$$ LANGUAGE plpgsql;

SELECT fn_total_venta(1) AS total_venta;

CREATE OR REPLACE FUNCTION fn_validar_detalle_venta()
RETURNS TRIGGER AS $$
DECLARE
    v_stock INTEGER;
    v_fecha_vencimiento DATE;
BEGIN
    -- Obtener stock y fecha de vencimiento del medicamento
    SELECT stock, fecha_vencimiento
    INTO v_stock, v_fecha_vencimiento
    FROM medicamentos
    WHERE id_medicamento = NEW.id_medicamento;

    -- Validar cantidad > 0
    IF NEW.cantidad <= 0 THEN
        RAISE EXCEPTION 'La cantidad debe ser mayor a 0';
    END IF;

    -- Validar stock suficiente
    IF NEW.cantidad > v_stock THEN
        RAISE EXCEPTION 'Stock insuficiente';
    END IF;

    -- Validar que no esté vencido
    IF v_fecha_vencimiento < CURRENT_DATE THEN
        RAISE EXCEPTION 'No se puede vender un medicamento vencido';
    END IF;

    -- Actualizar stock
    UPDATE medicamentos
    SET stock = stock - NEW.cantidad
    WHERE id_medicamento = NEW.id_medicamento;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_detalle_venta
BEFORE INSERT
ON detalle_venta
FOR EACH ROW
EXECUTE FUNCTION fn_validar_detalle_venta();

-- CASO INVALIDO
INSERT INTO detalle_venta VALUES (10, 1, 1, 0);

-- CASO valido
INSERT INTO ventas VALUES (5, 1, CURRENT_DATE);
