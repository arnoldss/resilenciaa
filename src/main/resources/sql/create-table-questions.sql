
 CREATE TABLE RS_QUESTION (
    id int NOT NULL PRIMARY KEY,
    name TEXT,
 	status int,
 	createdAt timestamp,
 	areaId int,
 	subareaId int
 	
);



/*
 
 * status 0 inactive
 * status 1 active
 */