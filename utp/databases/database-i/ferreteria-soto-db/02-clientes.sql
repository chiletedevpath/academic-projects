/********************************************************************************************
    2. MODULO CLIENTES (PERSONAS Y EMPRESAS)
    OBJETIVO:
        • ADMINISTRAR CLIENTES PARA BOLETAS Y FACTURAS
        • USAR DNI COMO IDENTIFICADOR OBLIGATORIO
        • PERMITIR RUC SOLO PARA EMPRESAS
        • APLICAR VALIDACIONES SUNAT
        • PROCEDIMIENTO CONTROLADO DE REGISTRO
********************************************************************************************/


/********************************************************************************************
    2.0 LIMPIEZA DE CONSTRAINTS DUPLICADOS
********************************************************************************************/
DECLARE @sql NVARCHAR(500);

SELECT @sql = 'ALTER TABLE Clientes DROP CONSTRAINT ' + name
FROM sys.objects
WHERE type = 'C' AND (
    name LIKE 'CK_Clientes_DNI_%' OR
    name LIKE 'CK_Clientes_RUC_%'
);

IF @sql IS NOT NULL EXEC(@sql);

IF EXISTS (SELECT 1 FROM sys.objects WHERE name = 'UQ_Clientes_DNI')
    ALTER TABLE Clientes DROP CONSTRAINT UQ_Clientes_DNI;

IF EXISTS (SELECT 1 FROM sys.objects WHERE name = 'UQ_Clientes_RUC')
    ALTER TABLE Clientes DROP CONSTRAINT UQ_Clientes_RUC;
GO


/********************************************************************************************
    2.1 REGLA DE DNI (OBLIGATORIO + UNICO)
********************************************************************************************/
ALTER TABLE Clientes
ALTER COLUMN DNI NVARCHAR(15) NOT NULL;
GO

ALTER TABLE Clientes
ADD CONSTRAINT UQ_Clientes_DNI UNIQUE (DNI);
GO


/********************************************************************************************
    2.2 REGLA DE RUC (OPCIONAL + UNICO SI EXISTE)
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='IX_Clientes_RUC_NoNull')
BEGIN
    CREATE UNIQUE NONCLUSTERED INDEX IX_Clientes_RUC_NoNull
    ON Clientes(RUC)
    WHERE RUC IS NOT NULL;
END;
GO


/********************************************************************************************
    2.3 VALIDACIONES SUNAT (DNI Y RUC)
********************************************************************************************/
ALTER TABLE Clientes
ADD CONSTRAINT CK_Clientes_DNI_8Digitos
CHECK (LEN(DNI) = 8 AND DNI NOT LIKE '%[^0-9]%');
GO

ALTER TABLE Clientes
ADD CONSTRAINT CK_Clientes_RUC_11Digitos
CHECK (RUC IS NULL OR (LEN(RUC) = 11 AND RUC NOT LIKE '%[^0-9]%'));
GO

ALTER TABLE Clientes
ADD CONSTRAINT CK_Clientes_RUC_Prefijo
CHECK (
    RUC IS NULL OR 
    LEFT(RUC, 2) IN ('10','20','15','17','16','21','22','23')
);
GO


/********************************************************************************************
    2.4 PROCEDIMIENTO ALMACENADO: CargarCliente
********************************************************************************************/
IF EXISTS (SELECT * FROM sys.objects WHERE name='CargarCliente' AND type='P')
    DROP PROCEDURE CargarCliente;
GO

CREATE PROCEDURE dbo.CargarCliente
(
    @Nombres      NVARCHAR(150),
    @Apellidos    NVARCHAR(150),
    @DNI          NVARCHAR(15),
    @Direccion    NVARCHAR(300)=NULL,
    @RUC          NVARCHAR(20)=NULL,
    @RazonSocial  NVARCHAR(200)=NULL
)
AS
BEGIN
    IF EXISTS(SELECT 1 FROM Clientes WHERE DNI = @DNI)
    BEGIN
        RAISERROR('DNI YA EXISTE', 16, 1);
        RETURN;
    END

    IF @RUC IS NOT NULL AND EXISTS(SELECT 1 FROM Clientes WHERE RUC = @RUC)
    BEGIN
        RAISERROR('RUC YA EXISTE', 16, 1);
        RETURN;
    END

    IF @RUC IS NOT NULL AND (@RazonSocial IS NULL OR @Direccion IS NULL)
    BEGIN
        RAISERROR('CLIENTE CON RUC DEBE TENER RAZON SOCIAL Y DIRECCION', 16, 1);
        RETURN;
    END

    INSERT INTO Clientes (Nombres,Apellidos,DNI,Direccion,RUC,RazonSocial)
    VALUES (@Nombres,@Apellidos,@DNI,@Direccion,@RUC,@RazonSocial);
END;
GO


/********************************************************************************************
    2.5 VERIFICACION FINAL DE LA ESTRUCTURA DEL MODULO
********************************************************************************************/
SELECT TOP 50 * FROM Clientes;
GO

SELECT name, type_desc
FROM sys.objects
WHERE name LIKE '%Clientes%';
GO

SELECT *
FROM sys.indexes
WHERE object_id = OBJECT_ID('Clientes');
GO
