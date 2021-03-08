create table users (
	id BIGINT NOT NULL, 
	creation_date timestamp NOT NULL, 
	name varchar(100),
	email varchar(255), 
	password varchar(255),
	
	primary key (id)
	);

create sequence users_id_seq start 1 increment 1;
