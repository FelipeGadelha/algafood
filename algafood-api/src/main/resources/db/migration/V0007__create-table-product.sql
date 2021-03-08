create table product (
	id BIGINT NOT NULL, 
	name varchar(100) NOT NULL, 
	description TEXT NOT NULL, 
	price numeric(10, 2) NOT NULL, 
	active boolean NOT NULL, 
	restaurant_id BIGINT NOT NULL, 
	
	primary key (id)
	);

create sequence product_id_seq start 1 increment 1;

alter table product add constraint FK_product_restaurant foreign key (restaurant_id) references restaurant (id);
