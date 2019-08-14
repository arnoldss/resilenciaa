
 CREATE TABLE RS_USER_DOC (
    id int NOT NULL PRIMARY KEY,
    userId int,
    createdAt timestamp,
    fileName 		TEXT,
    docName		TEXT,
 	comments	TEXT,
 	status int,
 	docType TEXT
 	
);
ALTER TABLE RS_USER_DOC ADD CONSTRAINT constraint_RS_USER_DOC UNIQUE (fileName,status,userId);
INSERT INTO RS_USER_DOC VALUES (0,0,CURRENT_DATE, 'Jose Luis Duenez','joseluisduenez@gmail.com', 'testing',0);

/*
 
 * status 0 inactive
 * status 1 active
 */