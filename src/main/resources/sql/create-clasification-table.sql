
 CREATE TABLE RS_CLASIFICATION_CATALOG (
    id int NOT NULL PRIMARY KEY,
    name TEXT,
 	status int,
 	createdAt timestamp
 	
);

INSERT INTO RS_CLASIFICATION_CATALOG VALUES (0,'Asistencial',1,CURRENT_DATE);

/*
 
 * status 0 inactive
 * status 1 active
 */