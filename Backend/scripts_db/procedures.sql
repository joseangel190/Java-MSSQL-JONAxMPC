-- Procedure Eliminar
CREATE PROCEDURE [dbo].[DeleteParametriaJson]
 
@json NVARCHAR(max),
@COUNT BIGINT OUTPUT

AS
DECLARE 
@idJson BIGINT
BEGIN
 
	-- INSERT INTO dbo.MaeParametrias(biVersion,bIsPersistente,vDescripcion,vAbreviatura,vCodigo,vCodigoRandom,biOrden,biIdParametriaPadre,vTag,dt2MarcaTiempo)
    SELECT
    @idJson=ID
    FROM OPENJSON(@json) -- as tbl
    WITH (
      id      			BIGINT			'$.id'
    ) AS jsonValues;
	DELETE FROM dbo.MaeParametrias
	WHERE biId=@idJson;
SELECT @COUNT= 0; --SCOPE_IDENTITY()
 
END

-- Procedure Insertar
GO
CREATE PROCEDURE [dbo].[InsertParametriaJson]
 
@json NVARCHAR(max),
@COUNT BIGINT OUTPUT

AS
BEGIN
 
INSERT INTO dbo.MaeParametrias(biVersion,bIsPersistente,vDescripcion,vAbreviatura,vCodigo,vCodigoRandom,biOrden,biIdParametriaPadre,vTag,dt2MarcaTiempo)
    SELECT
        biVersion,bIsPersistente,vDescripcion,vAbreviatura,vCodigo,vCodigoRandom,biOrden,biIdParametriaPadre,vTag,dt2MarcaTiempo
    FROM OPENJSON(@json)
    WITH (
      biVersion      		BIGINT			'$.version'
    , bIsPersistente 		bit				'$.isPersistente'
    , vDescripcion   		varchar(150)	'$.descripcion'
	, vAbreviatura   		varchar(150)	'$.abreviatura'
	, vCodigo   			varchar(150)    '$.codigo'
	, vCodigoRandom   		varchar(150)    '$.codigoRandom'
	, biOrden   			BIGINT			'$.orden'
	, biIdParametriaPadre	BIGINT			'$.idParametriaPadre'
	, vTag   				varchar(150)    '$.tag'
    , dt2MarcaTiempo		datetime2(7)    '$.marcaTiempo'
    ) AS jsonValues;
SELECT @COUNT= SCOPE_IDENTITY()
 
END

-- Procedure Actualizar
GO

CREATE PROCEDURE [dbo].[UpdateParametriaJson]
 
@json NVARCHAR(max),
@COUNT BIGINT OUTPUT

AS
BEGIN
 
	-- INSERT INTO dbo.MaeParametrias(biVersion,bIsPersistente,vDescripcion,vAbreviatura,vCodigo,vCodigoRandom,biOrden,biIdParametriaPadre,vTag,dt2MarcaTiempo)
    -- SELECT
    --    biVersion,bIsPersistente,vDescripcion,vAbreviatura,vCodigo,vCodigoRandom,biOrden,biIdParametriaPadre,vTag,dt2MarcaTiempo
	update dbo.MaeParametrias
		set vDescripcion=descripcion,
			biVersion=bVersion,
			vAbreviatura=abreviatura,
			vCodigo=codigo,
			vCodigoRandom=codigoRandom,
			biOrden=orden,
			biIdParametriaPadre=idParametriaPadre,
			vTag=tag,
			dt2MarcaTiempo=marcaTiempo
    FROM OPENJSON(@json) -- as tbl
    WITH (
      id      			BIGINT			'$.id'
	, bversion      		BIGINT			'$.version'
    , isPersistente 		bit				'$.isPersistente'
    , descripcion   		varchar(150)	'$.descripcion'
	, abreviatura   		varchar(150)	'$.abreviatura'
	, codigo   			varchar(150)    '$.codigo'
	, codigoRandom   		varchar(150)    '$.codigoRandom'
	, orden   			BIGINT			'$.orden'
	, idParametriaPadre	BIGINT			'$.idParametriaPadre'
	, tag   				varchar(150)    '$.tag'
    , marcaTiempo		datetime2(7)    '$.marcaTiempo'
    ) AS jsonValues
	where biId=id;
SELECT @COUNT= 0; --SCOPE_IDENTITY()
 
END

-- Procedure Listar
GO
CREATE PROCEDURE [dbo].[ListParametria]
AS   
BEGIN
    SET NOCOUNT ON;
    select *
	from MaeParametrias
END

-- Procedure Listar Paginacion
GO
CREATE PROCEDURE [dbo].[ListParametriaLimit]
@limit Bigint,
@offset Bigint
AS   
BEGIN
    SET NOCOUNT ON;
    select *
	from MaeParametrias
	order by biId
	offset @offset rows 
	fetch next @limit rows only;

END