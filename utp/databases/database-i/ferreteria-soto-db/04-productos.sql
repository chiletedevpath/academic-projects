/********************************************************************************************
    4. MODULO DE INVENTARIO CON PEPS (FIFO)
    OBJETIVO:
        • REGISTRAR CADA COMPRA COMO UN LOTE DE INVENTARIO
        • MANEJAR COSTOS EN DOLARES Y SOLES POR LOTE
        • APLICAR PEPS (FIFO) AL MOMENTO DE LA VENTA
        • CALCULAR COSTO DE VENTA POR DETALLE
        • MANTENER TRAZABILIDAD COMPLETA POR LOTE
********************************************************************************************/
USE ferreteriaSotoBD;
GO


/********************************************************************************************
    4.1 TABLA DE ENTRADAS DE INVENTARIO (POR LOTES)
    CADA REGISTRO REPRESENTA UNA COMPRA DE UN PRODUCTO EN UNA FECHA Y TIPO DE CAMBIO DETERMINADO
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'EntradasInventario' AND schema_id = SCHEMA_ID('dbo'))
BEGIN
    CREATE TABLE dbo.EntradasInventario (
        ID INT IDENTITY(1,1) PRIMARY KEY,

        ProductoID INT NOT NULL,

        Cantidad INT NOT NULL,
        StockDisponible INT NOT NULL,  -- SE DESCUENTA CUANDO SE VENDE

        PrecioCompra DECIMAL(10,2) NOT NULL,
        Moneda NVARCHAR(10) NOT NULL
            CONSTRAINT CK_EntradasInventario_Moneda CHECK (Moneda IN ('PEN','USD')),

        TipoCambio DECIMAL(10,4) NOT NULL, -- TIPO DE CAMBIO USADO EN ESTA COMPRA

        CostoUnitarioEnSoles AS (
            CASE 
                WHEN Moneda = 'USD' THEN PrecioCompra * TipoCambio
                ELSE PrecioCompra
            END
        ),

        FechaCompra DATETIME NOT NULL DEFAULT(GETDATE()),

        CONSTRAINT FK_EntradasInventario_Producto
            FOREIGN KEY (ProductoID) REFERENCES dbo.Productos(ID)
    );
END;
GO


/********************************************************************************************
    4.2 TABLA DE SALIDAS DE INVENTARIO (COSTO DE VENTA POR LOTE)
    VINCULA CADA DETALLE DE VENTA CON UNO O VARIOS LOTES (ENTRADAS) APLICANDO PEPS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'SalidasInventario' AND schema_id = SCHEMA_ID('dbo'))
BEGIN
    CREATE TABLE dbo.SalidasInventario (
        ID INT IDENTITY(1,1) PRIMARY KEY,

        DetalleVentaID INT NOT NULL,
        LoteID INT NOT NULL,
        ProductoID INT NOT NULL,

        Cantidad INT NOT NULL,
        CostoUnitarioEnSoles DECIMAL(10,2) NOT NULL,
        CostoTotalEnSoles AS (Cantidad * CostoUnitarioEnSoles) PERSISTED,

        FechaSalida DATETIME NOT NULL DEFAULT(GETDATE()),

        CONSTRAINT FK_SalidasInventario_DetalleVenta 
            FOREIGN KEY (DetalleVentaID) REFERENCES dbo.DetalleVentas(ID),

        CONSTRAINT FK_SalidasInventario_Lote
            FOREIGN KEY (LoteID) REFERENCES dbo.EntradasInventario(ID),

        CONSTRAINT FK_SalidasInventario_Producto
            FOREIGN KEY (ProductoID) REFERENCES dbo.Productos(ID)
    );
END;
GO


/********************************************************************************************
    4.3 CAMPOS DE COSTO EN DETALLEVENTAS
    SE AGREGA EL COSTO UNITARIO Y TOTAL SEGUN PEPS
********************************************************************************************/
IF COL_LENGTH('dbo.DetalleVentas', 'CostoUnitario') IS NULL
BEGIN
    ALTER TABLE dbo.DetalleVentas
    ADD CostoUnitario DECIMAL(10,2) NULL;
END;
GO

IF COL_LENGTH('dbo.DetalleVentas', 'CostoTotal') IS NULL
BEGIN
    ALTER TABLE dbo.DetalleVentas
    ADD CostoTotal DECIMAL(10,2) NULL;
END;
GO


/********************************************************************************************
    4.4 PROCEDIMIENTO PARA REGISTRAR ENTRADA DE INVENTARIO (COMPRA / INGRESO)
    USO:
        EXEC RegistrarEntradaInventario 
            @ProductoID = 1,
            @Cantidad = 50,
            @PrecioCompra = 10.50,
            @Moneda = 'USD',
            @TipoCambio = 3.80;
********************************************************************************************/
IF EXISTS (SELECT 1 FROM sys.objects WHERE name = 'RegistrarEntradaInventario' AND type = 'P')
    DROP PROCEDURE dbo.RegistrarEntradaInventario;
GO

CREATE PROCEDURE dbo.RegistrarEntradaInventario
(
    @ProductoID INT,
    @Cantidad INT,
    @PrecioCompra DECIMAL(10,2),
    @Moneda NVARCHAR(10),
    @TipoCambio DECIMAL(10,4)
)
AS
BEGIN
    SET NOCOUNT ON;

    IF @Cantidad <= 0
    BEGIN
        RAISERROR('LA CANTIDAD DEBE SER MAYOR A CERO', 16, 1);
        RETURN;
    END

    IF @Moneda NOT IN ('PEN','USD')
    BEGIN
        RAISERROR('MONEDA INVALIDA. USE PEN O USD', 16, 1);
        RETURN;
    END

    IF @TipoCambio <= 0
    BEGIN
        RAISERROR('TIPO DE CAMBIO INVALIDO', 16, 1);
        RETURN;
    END

    INSERT INTO dbo.EntradasInventario
        (ProductoID, Cantidad, StockDisponible, PrecioCompra, Moneda, TipoCambio)
    VALUES
        (@ProductoID, @Cantidad, @Cantidad, @PrecioCompra, @Moneda, @TipoCambio);
END;
GO


/********************************************************************************************
    4.5 PROCEDIMIENTO PARA APLICAR PEPS A UN DETALLE DE VENTA
    OBJETIVO:
        • CONSUMIR STOCK DE LOS LOTES MAS ANTIGUOS (FECHA COMPRA ASCENDENTE)
        • REGISTRAR SALIDAS EN SalidasInventario
        • CALCULAR COSTOUnitario Y COSTOTotal EN DetalleVentas
    NOTA:
        ESTE PROCEDIMIENTO NO CREA LA VENTA NI EL DETALLE.
        SE LLAMA DESPUES DE INSERTAR EN DetalleVentas.
********************************************************************************************/
IF EXISTS (SELECT 1 FROM sys.objects WHERE name = 'AplicarPEPS_DetalleVenta' AND type = 'P')
    DROP PROCEDURE dbo.AplicarPEPS_DetalleVenta;
GO

CREATE PROCEDURE dbo.AplicarPEPS_DetalleVenta
(
    @DetalleVentaID INT
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE 
        @ProductoID INT,
        @CantidadPendiente INT,
        @CantidadConsumir INT,
        @LoteID INT,
        @CostoUnitarioLote DECIMAL(10,2);

    /* OBTENER DATOS DEL DETALLE DE VENTA */
    SELECT 
        @ProductoID = ProductoID,
        @CantidadPendiente = Cantidad
    FROM dbo.DetalleVentas
    WHERE ID = @DetalleVentaID;

    IF @ProductoID IS NULL
    BEGIN
        RAISERROR('DETALLE DE VENTA NO ENCONTRADO', 16, 1);
        RETURN;
    END

    /* LIMPIAR POSIBLES SALIDAS ANTERIORES (RECALCULO) */
    DELETE FROM dbo.SalidasInventario
    WHERE DetalleVentaID = @DetalleVentaID;

    /* VALIDAR STOCK DISPONIBLE (GLOBAL POR PRODUCTO) */
    DECLARE @StockTotalDisponible INT;

    SELECT @StockTotalDisponible = ISNULL(SUM(StockDisponible), 0)
    FROM dbo.EntradasInventario
    WHERE ProductoID = @ProductoID;

    IF @StockTotalDisponible < @CantidadPendiente
    BEGIN
        RAISERROR('STOCK INSUFICIENTE PARA APLICAR PEPS', 16, 1);
        RETURN;
    END

    /* BUCLE PEPS: CONSUMIR LOTES MAS ANTIGUOS PRIMERO */
    WHILE @CantidadPendiente > 0
    BEGIN
        SELECT TOP 1 
            @LoteID = ID,
            @CostoUnitarioLote = CostoUnitarioEnSoles,
            @CantidadConsumir = 
                CASE 
                    WHEN StockDisponible >= @CantidadPendiente THEN @CantidadPendiente
                    ELSE StockDisponible
                END
        FROM dbo.EntradasInventario
        WHERE ProductoID = @ProductoID
          AND StockDisponible > 0
        ORDER BY FechaCompra ASC, ID ASC;

        IF @LoteID IS NULL
        BEGIN
            RAISERROR('NO SE ENCONTRO LOTE DISPONIBLE PARA PEPS', 16, 1);
            RETURN;
        END

        /* REGISTRAR SALIDA POR ESTE LOTE */
        INSERT INTO dbo.SalidasInventario
            (DetalleVentaID, LoteID, ProductoID, Cantidad, CostoUnitarioEnSoles)
        VALUES
            (@DetalleVentaID, @LoteID, @ProductoID, @CantidadConsumir, @CostoUnitarioLote);

        /* ACTUALIZAR STOCK DEL LOTE */
        UPDATE dbo.EntradasInventario
        SET StockDisponible = StockDisponible - @CantidadConsumir
        WHERE ID = @LoteID;

        /* REDUCIR CANTIDAD PENDIENTE */
        SET @CantidadPendiente = @CantidadPendiente - @CantidadConsumir;
    END

    /* CALCULAR COSTO TOTAL DEL DETALLE A PARTIR DE LAS SALIDAS */
    DECLARE 
        @CostoTotal DECIMAL(18,2),
        @CantidadTotal INT;

    SELECT 
        @CantidadTotal = SUM(Cantidad),
        @CostoTotal = SUM(CostoTotalEnSoles)
    FROM dbo.SalidasInventario
    WHERE DetalleVentaID = @DetalleVentaID;

    IF @CantidadTotal IS NULL OR @CantidadTotal = 0
    BEGIN
        RAISERROR('ERROR AL CALCULAR COSTO DE PEPS PARA EL DETALLE', 16, 1);
        RETURN;
    END

    /* ACTUALIZAR COSTO EN DetalleVentas */
    UPDATE dbo.DetalleVentas
    SET 
        CostoTotal = @CostoTotal,
        CostoUnitario = @CostoTotal / @CantidadTotal
    WHERE ID = @DetalleVentaID;
END;
GO


/********************************************************************************************
    4.6 VISTA RESUMEN DE COSTO POR DETALLE DE VENTA (OPCIONAL)
    PERMITE VER RAPIDAMENTE:
        • PRECIO DE VENTA
        • COSTO (PEPS)
        • MARGEN POR DETALLE
********************************************************************************************/
IF OBJECT_ID('dbo.vwDetalleVentasCostoPEPS', 'V') IS NOT NULL
    DROP VIEW dbo.vwDetalleVentasCostoPEPS;
GO

CREATE VIEW dbo.vwDetalleVentasCostoPEPS
AS
SELECT
    DV.ID AS DetalleVentaID,
    DV.VentaID,
    DV.ProductoID,
    P.Nombre AS Producto,
    DV.Cantidad,
    DV.PrecioUnitario,
    DV.Importe AS ImporteVenta,
    DV.CostoUnitario,
    DV.CostoTotal,
    (DV.Importe - ISNULL(DV.CostoTotal, 0)) AS Margen
FROM dbo.DetalleVentas DV
INNER JOIN dbo.Productos P ON P.ID = DV.ProductoID;
GO