-- create table identities
CREATE TABLE identities
	(identity_id INT NOT NULL GENERATED ALWAYS AS IDENTITY
	CONSTRAINT IDENTITY_PK PRIMARY KEY, 
	username VARCHAR(255),
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	password VARCHAR(255),
	email VARCHAR(255)
	)
	
	
