
 CREATE TABLE RS_POSITION_CATALOG (
    id int NOT NULL PRIMARY KEY,
    name TEXT,
 	status int,
 	createdAt timestamp
 	
);

INSERT INTO RS_POSITION_CATALOG VALUES (0,'Fundador',1,CURRENT_DATE);

/*
 
 * status 0 inactive
 * status 1 active
 */