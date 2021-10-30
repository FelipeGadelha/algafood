CREATE TABLE order_item (
	id SERIAL NOT NULL, 
	observation VARCHAR(255), 
	quantity int4 NOT NULL, 
	total_price NUMERIC(10,2) NOT NULL, 
	unit_price NUMERIC(10,2) NOT NULL,
	order_id BIGINT NOT NULL, 
	product_id BIGINT NOT NULL, 
	PRIMARY KEY (id)
	);

alter table order_item add constraint FK_order_item_order foreign key (order_id) references orders;
alter table order_item add constraint FK_order_item_product foreign key (product_id) references product;