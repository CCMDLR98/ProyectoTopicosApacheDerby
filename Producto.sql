DROP TABLE Producto;

CREATE TABLE Producto
(
	idCodigo INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	cantidad INT NOT NULL,
	tipoUnidad VARCHAR (15) NOT NULL,
	descripcion VARCHAR (40) NOT NULL,
	precio int NOT NULL,
	importe int NOT NULL,
	Activo BOOLEAN DEFAULT FALSE
);
INSERT INTO Producto (cantidad,tipoUnidad,descripcion,precio,importe)
	VALUES (12,'PIEZA','LAPIZ GRANDE',15,17);