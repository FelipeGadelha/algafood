create table permission (
	id BIGINT NOT NULL, 
	name varchar(100) NOT NULL,
	description varchar(100) NOT NULL,
	primary key (id)
	);

create sequence permission_id_seq start 1 increment 1;
