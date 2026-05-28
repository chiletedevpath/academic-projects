CREATE DATABASE bd_academica_lab;


CREATE TABLE alumnos(
    numcontrol BIGINT PRIMARY KEY,
    nombre VARCHAR(45),
    apellidopaterno VARCHAR(45),
    apellidomaterno VARCHAR(45),
    fecha_nacimiento DATE,
    fecha_ingreso DATE,
    telefono VARCHAR(12),
    carrera VARCHAR(45),
    promedio DECIMAL(6,2)
);



-- ANTES DE INSERTAR LOS DATOS EN LA TABLA ORIGINAL; 
-- CREO UNA TABLA TEMPORAL PARA DETECTAR DUPLICADO O ERRORES DE COLUMNAS
CREATE TEMP TABLE alumnos_temp(
    numcontrol BIGINT PRIMARY KEY,
    nombre VARCHAR(45),
    apellidopaterno VARCHAR(45),
    apellidomaterno VARCHAR(45),
    fecha_nacimiento DATE,
    fecha_ingreso DATE,
    telefono VARCHAR(12),
    carrera VARCHAR(45),
    promedio DECIMAL(6,2)
);



-- INGRESO LOS DATOS EN LA TABLA TEMPORAL
INSERT INTO alumnos_temp
(numcontrol,nombre,apellidopaterno,apellidomaterno,fecha_nacimiento,fecha_ingreso,telefono,carrera,promedio)
VALUES
(1000,'Juan','Vazquez','Perez','2002-05-08','2023-02-18','987654321','Mercadotecnia',15.92),
(1001,'Luis','Garcia','Lopez','2001-03-12','2023-02-18','987654322','Ingenieria en Sistemas',16.50),
(1002,'Carlos','Ramirez','Torres','2000-07-21','2023-02-18','987654323','Contabilidad',14.80),
(1003,'Maria','Sanchez','Perez','2002-11-30','2023-02-18','987654324','Psicologia',17.20),
(1004,'Ana','Gomez','Rojas','2001-09-15','2023-02-18','987654325','Derecho',16.75),
(1005,'Jose','Fernandez','Castro','2000-01-10','2023-02-18','987654326','Administracion',15.60),
(1006,'Pedro','Lopez','Diaz','2002-06-25','2023-02-18','987654327','Ingenieria Industrial',14.90),
(1007,'Lucia','Martinez','Silva','2001-04-18','2023-02-18','987654328','Arquitectura',18.10),
(1008,'Miguel','Torres','Vega','2000-12-05','2023-02-18','987654329','Ingenieria Civil',15.30),
(1009,'Elena','Castillo','Mendoza','2002-02-22','2023-02-18','987654330','Medicina',17.80),
(1010,'Diego','Ramos','Quispe','2001-08-11','2023-02-18','987654331','Ingenieria de Software',16.20),
(1011,'Sofia','Flores','Huaman','2000-03-09','2023-02-18','987654332','Educacion',15.70),
(1012,'Andres','Morales','Cruz','2002-10-14','2023-02-18','987654333','Contabilidad',14.50),
(1013,'Valeria','Vargas','Paredes','2001-05-19','2023-02-18','987654334','Administracion',17.00),
(1014,'Jorge','Salazar','Reyes','2000-07-07','2023-02-18','987654335','Derecho',16.10),
(1015,'Camila','Ortega','Navarro','2002-01-28','2023-02-18','987654336','Psicologia',18.30),
(1016,'Fernando','Delgado','Campos','2001-09-03','2023-02-18','987654337','Ingenieria Industrial',15.40),
(1017,'Daniela','Guerrero','Rios','2000-06-16','2023-02-18','987654338','Arquitectura',17.60),
(1018,'Ricardo','Espinoza','Luna','2002-04-01','2023-02-18','987654339','Ingenieria Civil',14.95),
(1019,'Paula','Navarro','Soto','2001-11-23','2023-02-18','987654340','Medicina',18.00);

SELECT * FROM alumnos_temp;

--DETECTAR DUPLICADOS
SELECT numcontrol, COUNT (*) AS veces_duplicados
FROM alumnos_temp
GROUP BY numcontrol
HAVING COUNT(*) > 1;


-- DETECTAR FECHAS INTERCAMBIADAS
SELECT *
FROM alumnos_temp
WHERE fecha_nacimiento > fecha_ingreso;


-- COMO NO HAY DUPLICADOS Y TAMPOCO FECHAS INTERCAMBIADAS; LOS DATOS DE LA TABLA TEMPORAL
-- SE INSERTA EN LA TABLA ORIGINAL
INSERT INTO alumnos
SELECT * FROM alumnos_temp;

SELECT * FROM alumnos;


-- Crear tabla carreras
CREATE TABLE carreras(
    idcarrera INTEGER PRIMARY KEY,
    nombrecarrera VARCHAR(45)
);

-- Insertar carreras
INSERT INTO carreras VALUES
(1,'Psicologia'),
(2,'Ingenieria en Sistemas'),
(3,'Contabilidad'),
(4,'Ingenieria Quimica'),
(5,'Mercadotecnia'),
(6,'Arquitectura'),
(7,'Administracion de empresas'),
(8,'Medicina');

SELECT * FROM carreras;

-- Crear tabla normalizada
CREATE TABLE alumnos_normalizado(
    numcontrol BIGINT PRIMARY KEY,
    nombre VARCHAR(45),
    apellidopaterno VARCHAR(45),
    apellidomaterno VARCHAR(45),
    fecha_nacimiento DATE,
    fecha_ingreso DATE,
    telefono VARCHAR(12),
    idcarrera INTEGER,
    promedio DECIMAL(6,2),
    FOREIGN KEY (idcarrera) REFERENCES carreras(idcarrera)
);

-- Insertar datos normalizados
INSERT INTO alumnos_normalizado
(numcontrol,nombre,apellidopaterno,apellidomaterno,fecha_nacimiento,fecha_ingreso,telefono,idcarrera,promedio)
VALUES
(1000,'Juan','Vazquez','Perez','2002-05-08','2023-02-18','987654321',5,15.92),
(1001,'Luis','Garcia','Lopez','2001-03-12','2023-02-18','987654322',2,16.50),
(1002,'Carlos','Ramirez','Torres','2000-07-21','2023-02-18','987654323',3,14.80),
(1003,'Maria','Sanchez','Perez','2002-11-30','2023-02-18','987654324',1,17.20),
(1004,'Ana','Gomez','Rojas','2001-09-15','2023-02-18','987654325',7,16.75),
(1005,'Jose','Fernandez','Castro','2000-01-10','2023-02-18','987654326',7,15.60),
(1006,'Pedro','Lopez','Diaz','2002-06-25','2023-02-18','987654327',4,14.90),
(1007,'Lucia','Martinez','Silva','2001-04-18','2023-02-18','987654328',6,18.10),
(1008,'Miguel','Torres','Vega','2000-12-05','2023-02-18','987654329',4,15.30),
(1009,'Elena','Castillo','Mendoza','2002-02-22','2023-02-18','987654330',8,17.80);

SELECT * FROM alumnos_normalizado;

-- JOIN 
SELECT a.numcontrol, a.nombre, a.apellidopaterno,
       a.idcarrera, c.nombrecarrera
FROM alumnos_normalizado a
INNER JOIN carreras c
ON a.idcarrera = c.idcarrera;

-- PARTE D: ALTER TABLE
-- Paso 12: Agregar columna
ALTER TABLE alumnos_normalizado
ADD COLUMN cantidadbeca DECIMAL(7,2) DEFAULT 0;

SELECT * FROM alumnos_normalizado;

-- Paso 13: Actualizar datos
UPDATE alumnos_normalizado
SET cantidadbeca = 500
WHERE promedio >= 95;

UPDATE alumnos_normalizado
SET cantidadbeca = 300
WHERE promedio >= 90 AND promedio < 95;

-- Paso 14: Renombrar columna
ALTER TABLE alumnos_normalizado
RENAME COLUMN cantidadbeca TO monto_beca;

SELECT * FROM alumnos_normalizado;


-- PARTE E: RELACIONES (1:1 vs 1:N)
-- Paso 15: Crear tablas
CREATE TABLE estudiantes(
    idestudiante INTEGER PRIMARY KEY,
    apellidos VARCHAR(45),
    nombre VARCHAR(45)
);

SELECT * FROM estudiantes;

CREATE TABLE infoestudiante(
    id_estudiante INTEGER PRIMARY KEY,
    ciudad VARCHAR(45),
    telefono VARCHAR(12),
    FOREIGN KEY (id_estudiante)
    REFERENCES estudiantes(idestudiante)
);

SELECT * FROM infoestudiante;

-- SOLUCION: Rediseñar a 1:N
DROP TABLE infoestudiante;

CREATE TABLE infoestudiante(
    idinfo INTEGER PRIMARY KEY,
    id_estudiante INTEGER,
    ciudad VARCHAR(45),
    telefono VARCHAR(12),
    FOREIGN KEY (id_estudiante)
    REFERENCES estudiantes(idestudiante)
);

INSERT INTO estudiantes(idestudiante, apellidos, nombre) VALUES
(1, 'Perez Gomez', 'Juan'),
(2, 'Lopez Diaz', 'Maria');

INSERT INTO infoestudiante(idinfo, id_estudiante, ciudad, telefono) VALUES
(100, 1, 'Lima', '945741360'),
(200, 2, 'Cajamarca', '147159357'),
(300, 2, 'Chilete', '258136777'),
(400, 1, 'Trujillo', '916087423');

SELECT * FROM infoestudiante;

-- PARTE F: LIMPIEZA DE DATOS (DATA QUALITY)
-- Detectar errores
CREATE TABLE personas(
dni VARCHAR(8),
nombre VARCHAR(45),
apellidopaterno VARCHAR(45),
apellidomaterno VARCHAR(45),
fechanacimiento DATE,
municipio VARCHAR(45),
estado VARCHAR(45),
telefono VARCHAR(12),
sexo CHARACTER(1)
);

INSERT INTO personas(dni,nombre,apellidopaterno,apellidomaterno,fechanacimiento,municipio,estado,telefono,sexo)
 VALUES('100012A','Juan','Vazquez','Perez','2020-05-08','Guadalajara', 'Jalisco','1234564343','M'),
('1001B12','Juan','Vazquez','Perez','2021-02-08','Merida','Yucatan','1234564348','M'),
('1002W12','Carlos Miguel','Lopez','Perez','2020-03-15','Morelia','Michoacan','1234564349','M'),
('1003Q43','Maria Carlota','SAnchez','Perez','2018-02-02','Guasave','Sinaloa','1234564322','F'),
('1004S23','Casandra','Gavilan','Gonzalez','2021-07-25','Monterrey','Nuevo Leon','1234564335','F'),
('1005F32','Andrea','Davila','Antonios','2018-06-02','Guadalajara','Jalisco','1234564326','F'),
('1006H78','Joao','Aguiar','Garza','2015-03-15','Morelia','Michoacan','1234564327','M'),
('1007Y54','Daniel','Zambrano','Espino','2015-07-18','Toluca','Estado de Mexico','1234564328','M'),
('1008W43','Flor','Velazquez','Espinoza','2017-02-08','Guadalajara','Jalisco','1234564345','F'),
('1009W23','Celeste','Vazquez','De la O','2015-08-22','Tijuana','Baja California','1234564385','F'),
('1010W12','Abigail','Andrade','Beltran','2020-05-12','Guadalajara','Jalisco','1234564373','F'),
('1011Q25','Juan Carlos','Espinoza','Campos','2020-05-15','Tijuana','Baja California','1234564399','M'),
('1012W25','Dionicio','Espino','Espinoza','2021-06-15','Guadalajara','Jalisco','1234564398','F'),
('1013Q45','Jose Carlos','Flores','Garcia','2021-07-17','Guasave','Sinaloa','1234564390','M'),
('1014Y59','Jose Pedro','Valle','Perez','2021-08-01','Mazatlan','Sinaloa','1234564312','M'),
('1015P45','Miguel Luis','Flores','Sanchez','2021-01-15','Monterrey','Nuevo Leon','1234564315','M'),
('1016H89','JoseMarcelo','Gonzalez','Miranda','2018-05-12','Guadalajara','Jalisco','1234564222','M'),
('1017278','Flor Estela','Huerta','Espinosa','2018-07-09','Guadalajara','Jalisco','1234564555','F'),
('1018Q34','Cristian Jesus','Kilberth','Perez','2018-01-08','Ensenada','Baja California','1234564532','F'),
('1019W47','Maria Cecilia','Lopez','Lopez','2045-07-28','Guadalajara','Jalisco','1234564145','F'),
('1020P30','Juan Alberto','Martinez','Vazquez','2016-07-22','Guadalajara','Jalisco','1234564142','M'),
('1021Q25','Franchesco Daniel','Nunez','Perez','2017-07-15','Puerto Vallarta','Jalisco','1234564248','M'),
('1022F78','Laura','Quinonez','Garcia','2020-02-08','Puebla','Puebla','1234564788','F');

SELECT *
FROM personas
WHERE fechanacimiento > CURRENT_DATE;

-- Corregir caracteres
UPDATE personas
SET nombre = TRANSLATE(nombre,
'ÁÉÍÓÚáéíóúÑñ',
'AEIOUaeiouNn');

SELECT * FROM personas;

create table ciudades(
nombre VARCHAR(45),
temperatura_maxima DOUBLE PRECISION,
temperatura_minima DOUBLE PRECISION,
fecha DATE
);

INSERT INTO ciudades(nombre,temperatura_maxima,temperatura_minima,fecha) VALUES
('Obregón',40.2,28.3,'2022-01-01'),('Hermosillo',44.2,39.6,'2022-01-01'),
('Navojoa',39.4,30.6,'2022-01-01'),('Guasave',41.8,39.2,'2022-01-01'),
('Chihuahua',28.5,25.6,'2022-01-01'),('Mazatlán','39.6',35.1,'2022-01-01'),
('Monterrey',24.1,18.2,'2022-01-01'),('Guadalajara',25.8,18.4,'2022-01-01'),
('Puerto Vallarta',29.6,21.2,'2022-01-01'),('Cd. de México',21.4,18.6,'2022-01-01'),
('Cancún',29.4,26.7,'2022-01-01'),('Los Mochis',44.1,39.6,'2022-01-01'),
('Culiacán',44.2,40.7,'2022-01-01'),('Cuernavaca',23.4,21.8,'2022-01-01'),
('Mocorito',40.1,39.8,'2022-01-01'),('Colima',36.5,33.6,'2022-01-01'),
('Obregón',40.1,38.5,'2022-01-02'),('Hermosillo',44.6,43.1,'2022-01-02'),
('Navojoa',42.4,40.1,'2022-01-02'),('Guasave',40.8,37.6,'2022-01-02'),
('Chihuahua',29.5,25.7,'2022-01-02'),('Mazatlán',40.1,36.7,'2022-01-02'),
('Monterrey',24.1,22.9,'2022-01-02'),('Guadalajara',25.8,21.5,'2022-01-02'),
('Puerto Vallarta',29.6,24.2,'2022-01-02'),('Cd. de México',21.4,17.5,'2022-01-02'),
('Cancún',29.4,25.7,'2022-01-02'),('Los Mochis',44.1,40.1,'2022-01-02'),
('Culiacán',44.2,36.7,'2022-01-02'),('Cuernavaca',23.4,18.5,'2022-01-02'),
('Mocorito',40.1,34.2,'2022-01-02'),('Colima',36.5,27.7,'2022-01-02'),
('Obregón',41.2,35.3,'2022-01-03'),('Hermosillo',42.6,36.8,'2022-01-03'),
('Navojoa',31.4,27.6,'2022-01-03'),('Guasave',37.6,35.1,'2022-01-03'),
('Chihuahua',36,34,'2022-01-03'),('Mazatlán',37,35.1,'2022-01-03'),
('Monterrey',26.1,15.2,'2022-01-3'),('Guadalajara',28.2,22.4,'2022-01-03'),
('Puerto Vallarta',26.6,26.2,'2022-01-03'),('Cd. de México',23.1,18.2,'2022-01-03'),
('Cancún',29.1,28.7,'2022-01-03'),('Los Mochis',44.1,39.6,'2022-01-03'),
('Culiacán',44.2,40.7,'2022-01-03'),('Cuernavaca',23.4,21.8,'2022-01-03'),
('Mocorito',40.1,39.8,'2022-01-03'),('Colima',36.5,33.6,'2022-01-03'),
('Obregón',38.1,35.2,'2022-01-03'),('Hermosillo',44.6,43.1,'2022-01-03'),
('Navojoa',41.4,38.6,'2022-01-03'),('Guasave',39.6,35.4,'2022-01-03'),
('Chihuahua',26.7,22.6,'2022-01-03'),('Mazatlán',39.1,35.7,'2022-01-03'),
('Monterrey',26.1,21.9,'2022-01-03'),('Guadalajara',27.4,21.8,'2022-01-03'),
('Puerto Vallarta',28.6,22.2,'2022-01-03'),('Cd. de México',24.4,17.2,'2022-01-03'),
('Cancún',26.4,25.7,'2022-01-03'),('Los Mochis',42.1,39.1,'2022-01-03'),
('Culiacán',41.2,38.7,'2022-01-03'),('Cuernavaca',26.4,21.5,'2022-01-03'),
('Mocorito',42.1,35.2,'2022-01-03'),('Colima',37.5,28.7,'2022-01-03');

-- Corregir fechas
UPDATE ciudades
SET fecha = '2022-01-03'
WHERE fecha = '2022-01-3';

SELECT * FROM ciudades;

-- PARTE G: CONSULTAS FINALES
-- Promedio por carrera
SELECT c.nombrecarrera, AVG(a.promedio)
FROM alumnos_normalizado a
INNER JOIN carreras c
ON a.idcarrera = c.idcarrera
GROUP BY c.nombrecarrera;

-- Alumnos con beca
SELECT nombre, promedio, monto_beca
FROM alumnos_normalizado
WHERE monto_beca > 0;

-- Temperaturas extremas
SELECT nombre, MAX(temperatura_maxima)
FROM ciudades
GROUP BY nombre
HAVING MAX(temperatura_maxima) > 40;

-- ACTIVIDAD FINAL 
-- reto
SELECT 
    a.nombre || ' ' || a.apellidopaterno || ' ' || a.apellidomaterno AS nombre_completo,
    c.nombrecarrera AS carrera,
    a.promedio,
    CASE 
        WHEN a.monto_beca > 0 THEN 'Becado'
        ELSE 'No becado'
    END AS estado
FROM alumnos_normalizado a
INNER JOIN carreras c 
ON a.idcarrera = c.idcarrera
ORDER BY a.promedio DESC;