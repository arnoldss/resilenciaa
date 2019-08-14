
 CREATE TABLE RS_SUBAREA_CATALOG (
    id int NOT NULL PRIMARY KEY,
    name TEXT,
 	status int,
 	createdAt timestamp,
 	areaId int
);



/*
 
 * status 0 inactive
 * status 1 active
 */