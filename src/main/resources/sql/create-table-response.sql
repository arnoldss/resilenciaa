
 CREATE TABLE RS_RESPONSE (
    id int NOT NULL PRIMARY KEY,
    name TEXT,
 	status int,
 	createdAt timestamp,
 	questionid int,
 	number int
 	
);

ALTER TABLE RS_RESPONSE ADD CONSTRAINT constraint_RS_RESPONSE UNIQUE (questionid,number);


/*
 
 * status 0 inactive
 * status 1 active
 */