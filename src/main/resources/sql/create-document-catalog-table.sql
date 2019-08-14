
 CREATE TABLE RS_DOCUMENT_CATALOG (
    id int NOT NULL PRIMARY KEY,
    name TEXT,
 	status int,
 	createdAt timestamp
 	
);

INSERT INTO RS_DOCUMENT_CATALOG VALUES (0,'Alta R.F.C.',1,CURRENT_DATE);

/*
 
 * status 0 inactive
 * status 1 active
 */