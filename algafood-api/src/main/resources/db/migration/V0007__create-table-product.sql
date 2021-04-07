create table product (
	id SERIAL NOT NULL, 
	name varchar(100) NOT NULL, 
	description TEXT NOT NULL, 
	price numeric(10, 2) NOT NULL, 
	active boolean NOT NULL, 
	restaurant_id BIGINT NOT NULL, 
	
	primary key (id)
	);

alter table product add constraint FK_product_restaurant foreign key (restaurant_id) references restaurant (id);
