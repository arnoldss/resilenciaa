
 CREATE TABLE RS_PROPERTY_TYPE_CATALOG (
    id int NOT NULL PRIMARY KEY,
    name TEXT,
 	status int,
 	createdAt timestamp
 	
);

INSERT INTO RS_PROPERTY_TYPE_CATALOG VALUES (0,'Propia',1,CURRENT_DATE);
INSERT INTO RS_PROPERTY_TYPE_CATALOG VALUES (1,'Rentada',1,CURRENT_DATE);


/*
 
 * status 0 inactive
 * status 1 active
 */