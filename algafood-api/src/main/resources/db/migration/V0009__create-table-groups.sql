create table groups (
	id BIGINT NOT NULL, 
	name varchar(100) NOT NULL, 
	
	primary key (id)
	);

create sequence groups_id_seq start 1 increment 1;