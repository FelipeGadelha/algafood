create table orders (
	id SERIAL NOT NULL, 
	subtotal NUMERIC(10,2) NOT NULL,
  	tax_freight NUMERIC(10,2) NOT NULL,
  	total_value NUMERIC(10,2) NOT NULL,

	restaurant_id BIGINT NOT NULL,
	user_client_id BIGINT NOT NULL,
	payment_method_id BIGINT NOT NULL,
	  
	address_city_id BIGINT NOT NULL,
	address_cep VARCHAR(9) NOT NULL,
	address_place VARCHAR(100) NOT NULL,
	address_number VARCHAR(20) NOT NULL,
	address_complement VARCHAR(60),
	address_district VARCHAR(60) NOT NULL,
	  
	status VARCHAR(10) NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	confirmation_date TIMESTAMP,
	cancel_date TIMESTAMP,
	delivery_date TIMESTAMP,
	
	PRIMARY KEY(id)
	);
		
ALTER TABLE orders ADD CONSTRAINT FK_orders_address_city FOREIGN KEY (address_city_id) REFERENCES city;
ALTER TABLE orders ADD CONSTRAINT FK_orders_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant;
ALTER TABLE orders ADD CONSTRAINT FK_orders_user_client FOREIGN KEY (user_client_id) REFERENCES users;
ALTER TABLE orders ADD CONSTRAINT FK_orders_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_method;