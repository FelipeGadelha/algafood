create table restaurant_payment_method (
	restaurant_id BIGINT NOT NULL, 
	payment_method_id BIGINT NOT NULL
	);

alter table restaurant_payment_method add constraint FK_restaurant_payment_method_restaurant foreign key (restaurant_id) references restaurant (id);
alter table restaurant_payment_method add constraint FK_restaurant_payment_method_payment_method foreign key (payment_method_id) references payment_method (id);
