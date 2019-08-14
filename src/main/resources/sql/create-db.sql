
 CREATE TABLE RS_USER ( id int NOT NULL PRIMARY KEY,createdAt timestamp,name TEXT,email	TEXT,password TEXT,comments	TEXT,telefono TEXT,role int,status int);
ALTER TABLE RS_USER ADD CONSTRAINT constraint_name UNIQUE (email);
/*
 * Role 0 admin
 * Role 1 user
 * status 0 inactive
 * status 1 active
 */*/