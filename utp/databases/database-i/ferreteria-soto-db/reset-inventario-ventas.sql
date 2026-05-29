/********************************************************************************************
    RESET GLOBAL – FERRETERIA SOTO
    OBJETIVO:
        • ELIMINAR PRODUCTOS, VENTAS, DETALLES Y AUDITORIA (AMBIENTE DE DESARROLLO)
        • RESETEAR IDENTITY A 1
        • NO AFECTA USUARIOS, CLIENTES NI CATALOGOS (FAMILIAS, GRUPOS, MARCAS, LINEAS)
    ADVERTENCIA:
        ESTE SCRIPT NO DEBE EJECUTARSE EN PRODUCCION
********************************************************************************************/

USE ferreteriaSotoBD;
GO


/********************************************************************************************
    CONFIRMACION DE SEGURIDAD
********************************************************************************************/
PRINT 'RESET GLOBAL – CONFIRMAR ANTES DE EJECUTAR.';
PRINT 'ESTE PROCESO ELIMINA PRODUCTOS, VENTAS, DETALLEVENTAS Y AUDITORIA.';
PRINT 'SI CONTINUA, LOS DATOS NO PODRAN SER RECUPERADOS.';
GO


/********************************************************************************************
    1. DESACTIVAR FOREIGN KEYS PARA PERMITIR EL BORRADO MASIVO
********************************************************************************************/
PRINT '=== DESACTIVANDO FOREIGN KEYS ===';

ALTER TABLE dbo.DetalleVentas    NOCHECK CONSTRAINT ALL;
ALTER TABLE dbo.AuditoriaVentas  NOCHECK CONSTRAINT ALL;
ALTER TABLE dbo.Ventas           NOCHECK CONSTRAINT ALL;
ALTER TABLE dbo.Productos        NOCHECK CONSTRAINT ALL;
GO


/********************************************************************************************
    2. BORRADO DE DATOS
********************************************************************************************/
PRINT '=== BORRANDO DATOS ===';

DELETE FROM dbo.AuditoriaVentas;
DELETE FROM dbo.DetalleVentas;
DELETE FROM dbo.Ventas;
DELETE FROM dbo.Productos;
GO


/********************************************************************************************
    3. RESETEO DE IDENTITY
********************************************************************************************/
PRINT '=== RESETEANDO IDENTITY ===';

DBCC CHECKIDENT ('dbo.AuditoriaVentas', RESEED, 0);
DBCC CHECKIDENT ('dbo.DetalleVentas', RESEED, 0);
DBCC CHECKIDENT ('dbo.Ventas', RESEED, 0);
DBCC CHECKIDENT ('dbo.Productos', RESEED, 0);
GO


/********************************************************************************************
    4. REACTIVACION DE FOREIGN KEYS
********************************************************************************************/
PRINT '=== REACTIVANDO FOREIGN KEYS ===';

ALTER TABLE dbo.DetalleVentas    CHECK CONSTRAINT ALL;
ALTER TABLE dbo.AuditoriaVentas  CHECK CONSTRAINT ALL;
ALTER TABLE dbo.Ventas           CHECK CONSTRAINT ALL;
ALTER TABLE dbo.Productos        CHECK CONSTRAINT ALL;
GO


/********************************************************************************************
    5. RESUMEN FINAL DEL SISTEMA
********************************************************************************************/
PRINT '=== RESET COMPLETO EXITOSO ===';

SELECT 
    (SELECT COUNT(*) FROM dbo.Productos)        AS Productos,
    (SELECT COUNT(*) FROM dbo.Ventas)           AS Ventas,
    (SELECT COUNT(*) FROM dbo.DetalleVentas)    AS DetalleVentas,
    (SELECT COUNT(*) FROM dbo.AuditoriaVentas)  AS AuditoriaVentas;
GO