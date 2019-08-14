
 CREATE TABLE RS_SUSTENTABILIDAD (
    id int NOT NULL PRIMARY KEY,
    idUser	int,
 	createdAt timestamp,
	sourceId int,
	benefactor TEXT,
	porcentajeAnual int,
	comentario	TEXT,
	status int,
	 sourceName TEXT
);



/*status fields
 * 0 = inactivo 
 * 1 = activo 
 */