
 CREATE TABLE RS_COUNTRY_CATALOG (
    id int NOT NULL PRIMARY KEY,
    createdAt timestamp,
    name 		TEXT
   
 	
);

INSERT INTO RS_COUNTRY_CATALOG VALUES (0,CURRENT_DATE,'Mexico');

/*
 * Role 0 admin
 * Role 1 user
 * status 0 inactive
 * status 1 active
 */