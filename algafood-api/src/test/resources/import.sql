insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

select nextval ('state_id_seq');
select nextval ('state_id_seq');
select nextval ('state_id_seq');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

select nextval ('city_id_seq');
select nextval ('city_id_seq');
select nextval ('city_id_seq');
select nextval ('city_id_seq');
select nextval ('city_id_seq');

insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into kitchen (id, name) values (3, 'Australiana');
insert into kitchen (id, name) values (4, 'Mexicana');
insert into kitchen (id, name) values (5, 'Francesa');
insert into kitchen (id, name) values (6, 'Italinana');

select nextval ('kitchen_id_seq');
select nextval ('kitchen_id_seq');
select nextval ('kitchen_id_seq');
select nextval ('kitchen_id_seq');
select nextval ('kitchen_id_seq');
select nextval ('kitchen_id_seq');

insert into Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, address_city_id, address_cep, address_place, address_number, address_district) values (1, 'Thai Gourmet', 10, 1, now(), now(), 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) values (2, 'Coco Bambu', 100.0, 1, now(), now());
insert into Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) values (3, 'Outback', 150.50, 3, now(), now());
insert into Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) values (4, 'Paris 6', 150.50, 5, now(), now());
insert into Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) values (5, 'Sí Señor', 80.0, 4, now(), now());
insert into Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) values (6, 'terraço italia', 90.0, 6, now(), now());

select nextval ('restaurant_id_seq');
select nextval ('restaurant_id_seq');
select nextval ('restaurant_id_seq');
select nextval ('restaurant_id_seq');
select nextval ('restaurant_id_seq');
select nextval ('restaurant_id_seq');

insert into payment_method (id, description) values (1, 'cartão de credito');
insert into payment_method (id, description) values (2, 'cartão de debito');
insert into payment_method (id, description) values (3, 'boleto');

select nextval ('payment_method_id_seq');
select nextval ('payment_method_id_seq');
select nextval ('payment_method_id_seq');

insert into permission (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

select nextval ('permission_id_seq');
select nextval ('permission_id_seq');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into product (id, name, description, price, active, restaurant_id) values (1, 'Coxinha', 'coxinha de frango', 50.0, true, 1);

select nextval ('product_id_seq');
