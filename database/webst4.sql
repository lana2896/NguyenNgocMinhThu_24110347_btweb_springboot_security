IF DB_ID(N'webst4') IS NULL
    CREATE DATABASE webst4 COLLATE Vietnamese_CI_AS;
GO

USE webst4;
GO

IF OBJECT_ID(N'dbo.roles', N'U') IS NULL
CREATE TABLE dbo.roles (
    id   BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);
GO

IF OBJECT_ID(N'dbo.users', N'U') IS NULL
CREATE TABLE dbo.users (
    id         BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    email      VARCHAR(120)  NOT NULL UNIQUE,
    password   VARCHAR(150)  NOT NULL,
    full_name  NVARCHAR(120) NOT NULL,
    enabled    BIT           NOT NULL DEFAULT 0,
    created_at DATETIME2(6)  NOT NULL DEFAULT SYSDATETIME(),
    role_id    BIGINT        NOT NULL,
    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES dbo.roles(id)
);
GO

IF OBJECT_ID(N'dbo.products', N'U') IS NULL
CREATE TABLE dbo.products (
    id      BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    name    NVARCHAR(200) NOT NULL,
    user_id BIGINT        NOT NULL,
    CONSTRAINT fk_products_user FOREIGN KEY (user_id) REFERENCES dbo.users(id)
);
GO

IF NOT EXISTS (SELECT 1 FROM dbo.roles WHERE name = 'USER')  INSERT INTO dbo.roles(name) VALUES ('USER');
IF NOT EXISTS (SELECT 1 FROM dbo.roles WHERE name = 'ADMIN') INSERT INTO dbo.roles(name) VALUES ('ADMIN');
GO
