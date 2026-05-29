/********************************************************************************************
    6. MODULO DE POLITICAS DE PRECIOS (EMPRESARIAL)
    OBJETIVO:
        • PERMITIR QUE EL GERENTE MODIFIQUE MARGENES DESDE LA APLICACION JAVA
        • EVITAR MODIFICACION DIRECTA DE SQL
        • ELIMINAR LOGICA DE PRECIOS FIJA EN EL SISTEMA
        • APLICAR PRECIOS DE VENTA A PARTIR DE POLITICAS ACTIVAS
********************************************************************************************/
USE ferreteriaSotoBD;
GO


/********************************************************************************************
    6.1 TABLA PRINCIPAL DE POLITICAS DE PRECIOS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'PoliticaPrecios')
BEGIN
    CREATE TABLE dbo.PoliticaPrecios (
        ID INT IDENTITY(1,1) PRIMARY KEY,

        RangoMin DECIMAL(10,2) NOT NULL,
        RangoMax DECIMAL(10,2) NULL,              -- NULL = SIN LIMITE SUPERIOR

        MargenPorcentaje DECIMAL(5,2) NOT NULL,   -- EJEMPLO: 60 = 60%

        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_PoliticaPrecios_Estado CHECK (Estado IN ('Activo','Inactivo')),

        FechaCreacion DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(),
        FechaActualizacion DATETIMEOFFSET NULL
    );
END;
GO


/********************************************************************************************
    6.2 POLITICAS INICIALES (REEMPLAZAN LA LOGICA FIJA DEL SISTEMA)
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM dbo.PoliticaPrecios)
BEGIN
    INSERT INTO dbo.PoliticaPrecios (RangoMin, RangoMax, MargenPorcentaje)
    VALUES
    (0.00, 10.00, 60.00),
    (10.00, 50.00, 40.00),
    (50.00, NULL, 25.00);
END;
GO


/********************************************************************************************
    6.3 VISTA PARA CONSULTA EN LA APLICACION JAVA
    LA APLICACION SE ENCARGA DEL ORDENAMIENTO
********************************************************************************************/
CREATE OR ALTER VIEW vwPoliticaPrecios AS
SELECT
    ID,
    RangoMin,
    RangoMax,
    MargenPorcentaje,
    Estado,
    FechaCreacion,
    FechaActualizacion
FROM dbo.PoliticaPrecios;
GO


/********************************************************************************************
    6.4 PROCEDIMIENTO PARA APLICAR POLITICAS A PRODUCTOS
    SE EJECUTA DESDE JAVA CUANDO SE DESEA ACTUALIZAR PRECIOS DE VENTA
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.AplicarPoliticasDePrecio
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @RangoMin DECIMAL(10,2),
            @RangoMax DECIMAL(10,2),
            @Margen DECIMAL(5,2);

    DECLARE Politicas CURSOR FOR
        SELECT RangoMin, RangoMax, MargenPorcentaje
        FROM dbo.PoliticaPrecios
        WHERE Estado = 'Activo'
        ORDER BY RangoMin ASC;

    OPEN Politicas;
    FETCH NEXT FROM Politicas INTO @RangoMin, @RangoMax, @Margen;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        UPDATE dbo.Productos
        SET PrecioVenta = ROUND(PrecioCompra * (1 + @Margen / 100), 2),
            FechaActualizacion = SYSDATETIMEOFFSET()
        WHERE PrecioCompra IS NOT NULL
          AND (
                (@RangoMax IS NULL AND PrecioCompra >= @RangoMin)
                OR
                (PrecioCompra >= @RangoMin AND PrecioCompra < @RangoMax)
              );

        FETCH NEXT FROM Politicas INTO @RangoMin, @RangoMax, @Margen;
    END;

    CLOSE Politicas;
    DEALLOCATE Politicas;
END;
GO


/********************************************************************************************
    6.5 PROCEDIMIENTO PARA ACTUALIZAR POLITICAS DESDE LA APLICACION JAVA
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.ActualizarPoliticaPrecio
(
    @ID INT,
    @RangoMin DECIMAL(10,2),
    @RangoMax DECIMAL(10,2),
    @MargenPorcentaje DECIMAL(5,2),
    @Estado NVARCHAR(20)
)
AS
BEGIN
    UPDATE dbo.PoliticaPrecios
    SET 
        RangoMin = @RangoMin,
        RangoMax = @RangoMax,
        MargenPorcentaje = @MargenPorcentaje,
        Estado = @Estado,
        FechaActualizacion = SYSDATETIMEOFFSET()
    WHERE ID = @ID;
END;
GO


/********************************************************************************************
    6.6 PROCEDIMIENTO PARA CREAR NUEVAS POLITICAS DESDE LA APLICACION JAVA
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.CrearPoliticaPrecio
(
    @RangoMin DECIMAL(10,2),
    @RangoMax DECIMAL(10,2),
    @MargenPorcentaje DECIMAL(5,2)
)
AS
BEGIN
    INSERT INTO dbo.PoliticaPrecios (RangoMin, RangoMax, MargenPorcentaje)
    VALUES (@RangoMin, @RangoMax, @MargenPorcentaje);
END;
GO


/********************************************************************************************
    6.7 PROCEDIMIENTO PARA DESACTIVAR UNA POLITICA DE PRECIO
    NO SE ELIMINA, SOLO CAMBIA A ESTADO INACTIVO
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.DesactivarPoliticaPrecio
(
    @ID INT
)
AS
BEGIN
    UPDATE dbo.PoliticaPrecios
    SET Estado = 'Inactivo',
        FechaActualizacion = SYSDATETIMEOFFSET()
    WHERE ID = @ID;
END;
GO