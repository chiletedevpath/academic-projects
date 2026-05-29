/********************************************************************************************
    3. MODULO DE CLASIFICACION DE PRODUCTOS
    OBJETIVO:
        • DEFINIR FAMILIAS, GRUPOS, MARCAS, UNIDADES Y LINEAS
        • EVITAR DUPLICADOS LOGICOS EN CATALOGOS
        • MEJORAR RENDIMIENTO DE CONSULTAS Y JOINS
        • PREPARAR LA BASE PARA CRECIMIENTO DEL INVENTARIO
********************************************************************************************/

USE ferreteriaSotoBD;
GO

/********************************************************************************************
    3.1 TABLAS PRINCIPALES DE CLASIFICACION (SI YA EXISTEN NO SE RECREAN)
********************************************************************************************/

-- TABLA FAMILIAS
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Familias')
BEGIN
    CREATE TABLE dbo.Familias (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(120) NOT NULL
            CONSTRAINT UQ_Familias_Nombre UNIQUE,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Familias_Estado CHECK (Estado IN ('Activo','Inactivo'))
    );
END;
GO

-- TABLA GRUPOS
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Grupos')
BEGIN
    CREATE TABLE dbo.Grupos (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        FamiliaID INT NOT NULL,
        Nombre NVARCHAR(120) NOT NULL,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Grupos_Estado CHECK (Estado IN ('Activo','Inactivo')),
        FOREIGN KEY (FamiliaID) REFERENCES dbo.Familias(ID)
    );
END;
GO

-- TABLA MARCAS
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Marcas')
BEGIN
    CREATE TABLE dbo.Marcas (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(120) NOT NULL
            CONSTRAINT UQ_Marcas_Nombre UNIQUE,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Marcas_Estado CHECK (Estado IN ('Activo','Inactivo'))
    );
END;
GO

-- TABLA UNIDADES DE MEDIDA
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'UnidadesMedida')
BEGIN
    CREATE TABLE dbo.UnidadesMedida (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(80) NOT NULL,
        Abreviatura NVARCHAR(10) NOT NULL,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_UnidadesMedida_Estado CHECK (Estado IN ('Activo','Inactivo'))
    );
END;
GO

-- TABLA LINEAS
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Lineas')
BEGIN
    CREATE TABLE dbo.Lineas (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        FamiliaID INT NOT NULL,
        Nombre NVARCHAR(150) NOT NULL,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Lineas_Estado CHECK (Estado IN ('Activo','Inactivo')),
        CONSTRAINT FK_Lineas_Familia FOREIGN KEY (FamiliaID) REFERENCES dbo.Familias(ID)
    );
END;
GO



/********************************************************************************************
    3.2 UNICIDAD LOGICA – SE EVITAN DUPLICADOS
********************************************************************************************/

-- UNICIDAD EN UNIDADES DE MEDIDA (NOMBRE)
IF NOT EXISTS (
    SELECT 1 FROM sys.objects 
    WHERE name = 'UQ_UnidadesMedida_Nombre' AND type = 'UQ'
)
BEGIN
    ALTER TABLE dbo.UnidadesMedida
    ADD CONSTRAINT UQ_UnidadesMedida_Nombre UNIQUE (Nombre);
END;
GO

-- UNICIDAD EN UNIDADES DE MEDIDA (ABREVIATURA)
IF NOT EXISTS (
    SELECT 1 FROM sys.objects 
    WHERE name = 'UQ_UnidadesMedida_Abreviatura' AND type = 'UQ'
)
BEGIN
    ALTER TABLE dbo.UnidadesMedida
    ADD CONSTRAINT UQ_UnidadesMedida_Abreviatura UNIQUE (Abreviatura);
END;
GO

-- GRUPOS ÚNICOS POR FAMILIA
IF NOT EXISTS (
    SELECT 1 FROM sys.objects 
    WHERE name = 'UQ_Grupos_Familia_Nombre' AND type = 'UQ'
)
BEGIN
    ALTER TABLE dbo.Grupos
    ADD CONSTRAINT UQ_Grupos_Familia_Nombre UNIQUE (FamiliaID, Nombre);
END;
GO

-- LINEAS ÚNICAS POR FAMILIA
IF NOT EXISTS (
    SELECT 1 FROM sys.objects 
    WHERE name = 'UQ_Lineas_Familia_Nombre' AND type = 'UQ'
)
BEGIN
    ALTER TABLE dbo.Lineas
    ADD CONSTRAINT UQ_Lineas_Familia_Nombre UNIQUE (FamiliaID, Nombre);
END;
GO



/********************************************************************************************
    3.3 INDICES PARA MEJORAR RENDIMIENTO EN JOINS
********************************************************************************************/

-- INDICE EN GRUPOS POR FAMILIA
IF NOT EXISTS (
    SELECT 1 FROM sys.indexes 
    WHERE name = 'IX_Grupos_FamiliaID'
)
BEGIN
    CREATE INDEX IX_Grupos_FamiliaID ON dbo.Grupos(FamiliaID);
END;
GO

-- INDICE EN LINEAS POR FAMILIA
IF NOT EXISTS (
    SELECT 1 FROM sys.indexes 
    WHERE name = 'IX_Lineas_FamiliaID'
)
BEGIN
    CREATE INDEX IX_Lineas_FamiliaID ON dbo.Lineas(FamiliaID);
END;
GO



/********************************************************************************************
    3.4 CARGA INICIAL DE UNIDADES DE MEDIDA (SE AGREGA SOLO SI AUN NO EXISTEN)
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM dbo.UnidadesMedida WHERE Nombre = 'Unidad')
BEGIN
    INSERT INTO dbo.UnidadesMedida (Nombre, Abreviatura, Estado)
    VALUES 
        ('Unidad','UND','Activo'),
        ('Galón','GLN','Activo'),
        ('Juego','JGO','Activo'),
        ('Kilogramo','KG','Activo'),
        ('Par','PAR','Activo');
END;
GO



/********************************************************************************************
    3.5 VERIFICACION FINAL DEL MODULO
********************************************************************************************/
SELECT 'FAMILIAS' AS Tabla, COUNT(*) AS Registros FROM dbo.Familias
UNION ALL SELECT 'GRUPOS', COUNT(*) FROM dbo.Grupos
UNION ALL SELECT 'MARCAS', COUNT(*) FROM dbo.Marcas
UNION ALL SELECT 'UNIDADESMEDIDA', COUNT(*) FROM dbo.UnidadesMedida
UNION ALL SELECT 'LINEAS', COUNT(*) FROM dbo.Lineas;
GO