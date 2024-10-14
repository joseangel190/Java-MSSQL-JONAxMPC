CREATE TABLE dbo.MaeParametrias (
    biId BIGINT IDENTITY(1,1) NOT NULL, 
    biVersion BIGINT NOT NULL, 
    bIsPersistente BIT NOT NULL,
    vDescripcion VARCHAR(150) NOT NULL,
    vAbreviatura VARCHAR(50),
    vCodigo VARCHAR(150),
    vCodigoRandom VARCHAR(150),
    biOrden BIGINT,
    biIdParametriaPadre BIGINT NULL,
    vTag VARCHAR(150),
    dt2MarcaTiempo DATETIME2 NOT NULL, 
    CONSTRAINT PK_MAEPARAMETRIAS PRIMARY KEY (biId),
    CONSTRAINT FK_PARAMETRIAPADRE FOREIGN KEY (biIdParametriaPadre) REFERENCES DEMOSIAT.dbo.MaeParametrias(biId) 
);