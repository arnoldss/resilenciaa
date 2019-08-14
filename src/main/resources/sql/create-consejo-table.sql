
 CREATE TABLE RS_CONSEJO (
    id int NOT NULL PRIMARY KEY,
    idUser	int,
 	createdAt timestamp,
 	activo		int,
 	positionId int,
 	nombre	TEXT,
 	remove	int,
 	fechaInicio timestamp,
 	fechaFinal timestamp,
 	apellido	TEXT,
 	fechaDeNacimiento timestamp,
 	rfc		TEXT,
 	positionName TEXT
);



/*activo and remove fields
 * 0 = inactivo and removed
 * 1 = activo and not removed
 */