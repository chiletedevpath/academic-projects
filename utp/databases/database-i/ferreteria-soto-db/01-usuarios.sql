/********************************************************************************************
    1. MODULO DE USUARIOS Y AUDITORIA
    INCLUYE:
        • TABLAS DE CATALOGO PARA ROLES Y ESTADOS
        • TABLA PRINCIPAL USUARIOS
        • VALIDACION DE CORREO
        • INDICES PARA OPTIMIZAR LOGIN
        • TABLA DE AUDITORIA DE USUARIOS
        • TRIGGER CORPORATIVO DE AUDITORIA
        • PROCEDIMIENTO ALMACENADO PARA CREAR USUARIOS
        • CARGA INICIAL DE CUENTAS
********************************************************************************************/


/********************************************************************************************
    1.1 TABLAS DE CATALOGO (ROLES Y ESTADOS)
********************************************************************************************/

IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='Roles')
BEGIN
    CREATE TABLE dbo.Roles (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(50) NOT NULL CONSTRAINT UQ_Roles_Nombre UNIQUE
    );

    INSERT INTO dbo.Roles (Nombre)
    VALUES ('Administrador'), ('Usuario');
END
GO


IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='EstadosUsuario')
BEGIN
    CREATE TABLE dbo.EstadosUsuario (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(20) NOT NULL CONSTRAINT UQ_EstadosUsuario_Nombre UNIQUE
    );

    INSERT INTO dbo.EstadosUsuario (Nombre)
    VALUES ('Activo'), ('Inactivo');
END
GO



/********************************************************************************************
    1.2 TABLA PRINCIPAL: USUARIOS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='Usuarios')
BEGIN
    CREATE TABLE dbo.Usuarios (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Usuario NVARCHAR(50) NOT NULL CONSTRAINT UQ_Usuarios_Usuario UNIQUE,
        ContrasenaHash BINARY(32) NOT NULL,
        RolID INT NOT NULL CONSTRAINT FK_Usuarios_RolID REFERENCES dbo.Roles(ID),
        Correo NVARCHAR(150) NOT NULL CONSTRAINT UQ_Usuarios_Correo UNIQUE,
        NombreReal NVARCHAR(150) NOT NULL DEFAULT 'SIN NOMBRE',
        EstadoID INT NOT NULL CONSTRAINT FK_Usuarios_EstadoID REFERENCES dbo.EstadosUsuario(ID),
        FechaCreacion DATETIMEOFFSET NOT NULL DEFAULT(SYSDATETIMEOFFSET()),
        FechaActualizacion DATETIMEOFFSET NULL
    );
END
GO



/********************************************************************************************
    1.3 VALIDACION DE CORREO
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.check_constraints WHERE name='CK_Usuarios_Correo_Valido')
BEGIN
    ALTER TABLE dbo.Usuarios
    ADD CONSTRAINT CK_Usuarios_Correo_Valido
    CHECK (Correo LIKE '%_@_%._%');
END
GO



/********************************************************************************************
    1.4 INDICES
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='IX_Usuarios_Usuario')
    CREATE INDEX IX_Usuarios_Usuario ON dbo.Usuarios(Usuario);

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='IX_Usuarios_EstadoID')
    CREATE INDEX IX_Usuarios_EstadoID ON dbo.Usuarios(EstadoID);
GO



/********************************************************************************************
    1.5 AUDITORIA DE USUARIOS
********************************************************************************************/

IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='AuditoriaUsuarios')
BEGIN
    CREATE TABLE dbo.AuditoriaUsuarios (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        UsuarioID INT NOT NULL,
        UsuarioAdminID INT NULL,
        Accion NVARCHAR(200) NOT NULL,
        Fecha DATETIMEOFFSET NOT NULL DEFAULT(SYSDATETIMEOFFSET()),
        IP NVARCHAR(50) NULL,
        FOREIGN KEY (UsuarioID) REFERENCES dbo.Usuarios(ID),
        FOREIGN KEY (UsuarioAdminID) REFERENCES dbo.Usuarios(ID)
    );
END
GO



/********************************************************************************************
    1.6 TRIGGER DE AUDITORIA — VERSION CORREGIDA
********************************************************************************************/

CREATE OR ALTER TRIGGER TR_Usuarios_Acciones
ON dbo.Usuarios
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @AdminID INT = TRY_CAST(SESSION_CONTEXT(N'AdminID') AS INT);
    DECLARE @IP NVARCHAR(50) = CONVERT(NVARCHAR(50), SESSION_CONTEXT(N'IP'));

    -----------------------------------------------------------------
    -- EVITAR ERRORES SI EL USUARIO YA NO EXISTE POR OPERACIONES MANUALES
    -----------------------------------------------------------------
    IF EXISTS (SELECT 1 FROM deleted) AND NOT EXISTS (SELECT 1 FROM dbo.Usuarios WHERE ID IN (SELECT ID FROM deleted))
    BEGIN
        RETURN;
    END

    -- CREACION
    IF EXISTS (SELECT 1 FROM inserted) AND NOT EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO dbo.AuditoriaUsuarios (UsuarioID, UsuarioAdminID, Accion, IP)
        SELECT ID, @AdminID, 'USUARIO CREADO', @IP
        FROM inserted;
    END

    -- ELIMINACION
    IF EXISTS (SELECT 1 FROM deleted) AND NOT EXISTS (SELECT 1 FROM inserted)
    BEGIN
        INSERT INTO dbo.AuditoriaUsuarios (UsuarioID, UsuarioAdminID, Accion, IP)
        SELECT ID, @AdminID, 'USUARIO ELIMINADO', @IP
        FROM deleted;
    END

    -- MODIFICACION
    IF EXISTS (SELECT 1 FROM inserted) AND EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO dbo.AuditoriaUsuarios (UsuarioID, UsuarioAdminID, Accion, IP)
        SELECT ID, @AdminID, 'USUARIO MODIFICADO', @IP
        FROM inserted;

        UPDATE dbo.Usuarios
        SET FechaActualizacion = SYSDATETIMEOFFSET()
        WHERE ID IN (SELECT ID FROM inserted);
    END
END
GO



/********************************************************************************************
    1.7 PROCEDIMIENTO ALMACENADO PARA CREAR USUARIOS
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.CrearUsuario
(
    @Usuario NVARCHAR(50),
    @Contrasena NVARCHAR(200),
    @Rol NVARCHAR(50),
    @Correo NVARCHAR(150),
    @NombreReal NVARCHAR(150)
)
AS
BEGIN
    DECLARE @RolID INT = (SELECT ID FROM dbo.Roles WHERE Nombre=@Rol);
    DECLARE @EstadoID INT = (SELECT ID FROM dbo.EstadosUsuario WHERE Nombre='Activo');

    IF @RolID IS NULL
    BEGIN
        RAISERROR('ROL INVALIDO', 16, 1);
        RETURN;
    END

    INSERT INTO dbo.Usuarios (Usuario, ContrasenaHash, RolID, Correo, NombreReal, EstadoID)
    VALUES (
        @Usuario,
        HASHBYTES('SHA2_256', @Contrasena),
        @RolID,
        @Correo,
        @NombreReal,
        @EstadoID
    );
END
GO

-- EJEMPLO DE USO DEL PROCEDIMIENTO
EXEC dbo.CrearUsuario 
    @Usuario = 'prueba200',
    @Contrasena = 'prueba200',
    @Rol = 'Usuario',
    @Correo = 'prueba200@ferreteriasoto.com',
    @NombreReal = 'Prueba200';
    GO

-- VERIFICACION DEL REGISTRO CREADO
SELECT * FROM dbo.Usuarios;
GO

/********************************************************************************************
    1.8 USUARIOS INICIALES
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM dbo.Usuarios WHERE Usuario='Admin')
BEGIN
    EXEC dbo.CrearUsuario 
        @Usuario = 'Admin',
        @Contrasena = 'admin123',
        @Rol = 'Administrador',
        @Correo = 'admin@ferreteriasoto.com',
        @NombreReal = 'Administrador del Sistema';
END

IF NOT EXISTS (SELECT 1 FROM dbo.Usuarios WHERE Usuario='Walter')
BEGIN
    EXEC dbo.CrearUsuario 
        @Usuario = 'Walter',
        @Contrasena = 'walter123',
        @Rol = 'Usuario',
        @Correo = 'waltersoto@ferreteriasoto.com',
        @NombreReal = 'Walter Soto Namoc';
END
GO

/********************************************************************************************
    1.9 CONSULTA GENERAL DE AUDITORIA
********************************************************************************************/
SELECT 
    au.ID,
    au.UsuarioID,
    u.Usuario AS UsuarioAfectado,
    u.NombreReal AS NombreAfectado,
    au.Accion,
    au.Fecha,
    au.IP,
    au.UsuarioAdminID,
    admin.Usuario AS UsuarioAdministrador,
    admin.NombreReal AS NombreAdministrador
FROM dbo.AuditoriaUsuarios au
LEFT JOIN dbo.Usuarios u     ON u.ID     = au.UsuarioID
LEFT JOIN dbo.Usuarios admin ON admin.ID = au.UsuarioAdminID
ORDER BY au.Fecha DESC;
GO


SELECT * FROM dbo.AuditoriaUsuarios;
GO