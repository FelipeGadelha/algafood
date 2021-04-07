create table users (
	id SERIAL NOT NULL, 
	creation_date timestamp NOT NULL, 
	name varchar(100),
	email varchar(255), 
	password varchar(255),
	
	primary key (id)
	);
