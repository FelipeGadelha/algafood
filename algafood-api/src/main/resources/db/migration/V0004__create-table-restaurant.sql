create table restaurant (
	id BIGINT NOT NULL,  
	kitchen_id BIGINT NOT NULL, 
	name varchar(100) NOT NULL, 
	tax_freight numeric(19, 2) NOT NULL, 
	creation_date timestamp NOT NULL, 
	update_date timestamp NOT NULL, 
	
	address_cep varchar(9), 
	address_complement varchar(60), 
	address_district varchar(150), 
	address_number varchar(20), 
	address_place varchar(150),
	address_city_id BIGINT, 
	
	primary key (id)
	);
	
create sequence restaurant_id_seq start 1 increment 1;

alter table restaurant add constraint FK_restaurant_address_city foreign key (address_city_id) references city (id);
alter table restaurant add constraint FK_restaurant_kitchen foreign key (kitchen_id) references kitchen (id);
