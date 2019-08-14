
 CREATE TABLE RS_INCOME_SOURCE_CATALOG (
    id int NOT NULL PRIMARY KEY,
    name TEXT,
 	status int,
 	createdAt timestamp
 	
);

INSERT INTO RS_INCOME_SOURCE_CATALOG VALUES (0,'Aportaciones de Consejeros',1,CURRENT_DATE);

/*
 
 * status 0 inactive
 * status 1 active
 */