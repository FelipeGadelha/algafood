create table payment_method (
	id BIGINT NOT NULL, 
	description varchar(60) NOT NULL,
	
	primary key (id)
	);

create sequence payment_method_id_seq start 1 increment 1;
