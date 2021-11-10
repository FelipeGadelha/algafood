--SET session_replication_role = 'replica';
set foreign_key_checks = 0;

DELETE FROM city;
DELETE FROM kitchen;
DELETE FROM state;
DELETE FROM payment_method;
DELETE FROM groups;
DELETE FROM groups_permission;
DELETE FROM permission;
DELETE FROM product;
DELETE FROM restaurant;
DELETE FROM restaurant_payment_method;
DELETE FROM users;
DELETE FROM users_groups;

set foreign_key_checks = 1;
--SET session_replication_role = 'origin';

--ALTER SEQUENCE city_id_seq RESTART WITH 1;
--ALTER SEQUENCE kitchen_id_seq RESTART WITH 1;
--ALTER SEQUENCE state_id_seq RESTART WITH 1;
--ALTER SEQUENCE payment_method_id_seq RESTART WITH 1;
--ALTER SEQUENCE groups_id_seq RESTART WITH 1;
--ALTER SEQUENCE permission_id_seq RESTART WITH 1;
--ALTER SEQUENCE product_id_seq RESTART WITH 1;
--ALTER SEQUENCE restaurant_id_seq RESTART WITH 1;
--ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO kitchen (id, name) VALUES (1, 'Tailandesa');
INSERT INTO kitchen (id, name) VALUES (2, 'Indiana');
INSERT INTO kitchen (id, name) VALUES (3, 'Australiana');
INSERT INTO kitchen (id, name) VALUES (4, 'Mexicana');
INSERT INTO kitchen (id, name) VALUES (5, 'Francesa');
INSERT INTO kitchen (id, name) VALUES (6, 'Italinana');

INSERT INTO state (id, name) VALUES (1, 'Minas Gerais');
INSERT INTO state (id, name) VALUES (2, 'São Paulo');
INSERT INTO state (id, name) VALUES (3, 'Ceará');

INSERT INTO city (id, name, state_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO city (id, name, state_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO city (id, name, state_id) VALUES (3, 'São Paulo', 2);
INSERT INTO city (id, name, state_id) VALUES (4, 'Campinas', 2);
INSERT INTO city (id, name, state_id) VALUES (5, 'Fortaleza', 3);

INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active, address_city_id, address_cep, address_place, address_number, address_district) VALUES (1, 'Thai Gourmet', 10, 1, now(), now(), true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active) VALUES (2, 'Coco Bambu', 100.0, 1, now(), now(), true);
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active) VALUES (3, 'Outback', 150.50, 3, now(), now(), true);
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active) VALUES (4, 'Paris 6', 150.50, 5, now(), now(), true);
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active) VALUES (5, 'Sí Señor', 80.0, 4, now(), now(), true);
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active) VALUES (6, 'terraço italia', 90.0, 6, now(), now(), true);

INSERT INTO payment_method (id, description) VALUES (1, 'cartão de credito');
INSERT INTO payment_method (id, description) VALUES (2, 'cartão de debito');
INSERT INTO payment_method (id, description) VALUES (3, 'boleto');

INSERT INTO permission (id, name, description) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permission (id, name, description) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

