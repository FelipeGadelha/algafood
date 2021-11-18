SET session_replication_role = 'replica';

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
DELETE FROM users_restaurants_owner;
DELETE FROM orders;
DELETE FROM order_item;

SET session_replication_role = 'origin';

ALTER SEQUENCE city_id_seq RESTART WITH 1;
ALTER SEQUENCE kitchen_id_seq RESTART WITH 1;
ALTER SEQUENCE state_id_seq RESTART WITH 1;
ALTER SEQUENCE payment_method_id_seq RESTART WITH 1;
ALTER SEQUENCE groups_id_seq RESTART WITH 1;
ALTER SEQUENCE permission_id_seq RESTART WITH 1;
ALTER SEQUENCE product_id_seq RESTART WITH 1;
ALTER SEQUENCE restaurant_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO kitchen (id, name) VALUES (1, 'Tailandesa');
INSERT INTO kitchen (id, name) VALUES (2, 'Indiana');
INSERT INTO kitchen (id, name) VALUES (3, 'Australiana');
INSERT INTO kitchen (id, name) VALUES (4, 'Mexicana');
INSERT INTO kitchen (id, name) VALUES (5, 'Francesa');
INSERT INTO kitchen (id, name) VALUES (6, 'Italinana');

SELECT nextval ('kitchen_id_seq');
SELECT nextval ('kitchen_id_seq');
SELECT nextval ('kitchen_id_seq');
SELECT nextval ('kitchen_id_seq');
SELECT nextval ('kitchen_id_seq');
SELECT nextval ('kitchen_id_seq');

INSERT INTO state (id, name) VALUES (1, 'Minas Gerais');
INSERT INTO state (id, name) VALUES (2, 'São Paulo');
INSERT INTO state (id, name) VALUES (3, 'Ceará');

SELECT nextval ('state_id_seq');
SELECT nextval ('state_id_seq');
SELECT nextval ('state_id_seq');

INSERT INTO city (id, name, state_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO city (id, name, state_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO city (id, name, state_id) VALUES (3, 'São Paulo', 2);
INSERT INTO city (id, name, state_id) VALUES (4, 'Campinas', 2);
INSERT INTO city (id, name, state_id) VALUES (5, 'Fortaleza', 3);

SELECT nextval ('city_id_seq');
SELECT nextval ('city_id_seq');
SELECT nextval ('city_id_seq');
SELECT nextval ('city_id_seq');
SELECT nextval ('city_id_seq');

INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active, open, address_city_id, address_cep, address_place, address_number, address_district) VALUES (1, 'Thai Gourmet', 10, 1, now(), now(), true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active, open) VALUES (2, 'Coco Bambu', 100.0, 1, now(), now(), true, true);
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active, open) VALUES (3, 'Outback', 150.50, 3, now(), now(), true, true);
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active, open) VALUES (4, 'Paris 6', 150.50, 5, now(), now(), true, true);
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active, open) VALUES (5, 'Sí Señor', 80.0, 4, now(), now(), true, true);
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, active, open) VALUES (6, 'terraço italia', 90.0, 6, now(), now(), true, true);

SELECT nextval ('restaurant_id_seq');
SELECT nextval ('restaurant_id_seq');
SELECT nextval ('restaurant_id_seq');
SELECT nextval ('restaurant_id_seq');
SELECT nextval ('restaurant_id_seq');
SELECT nextval ('restaurant_id_seq');

INSERT INTO payment_method (id, description) VALUES (1, 'cartão de credito');
INSERT INTO payment_method (id, description) VALUES (2, 'cartão de debito');
INSERT INTO payment_method (id, description) VALUES (3, 'boleto');

SELECT nextval ('payment_method_id_seq');
SELECT nextval ('payment_method_id_seq');
SELECT nextval ('payment_method_id_seq');

INSERT INTO permission (id, name, description) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permission (id, name, description) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
INSERT INTO permission (id, name, description) VALUES (3, 'EXCLUIR_COZINHAS', 'Permite exclusão de cozinhas');

SELECT nextval ('permission_id_seq');
SELECT nextval ('permission_id_seq');

INSERT INTO restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

--INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES
INSERT INTO product (id, name, description, price, active, restaurant_id) VALUES
(1, 'Coxinha', 'coxinha de frango', 50.0, true, 1),
(2, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1),
(3, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1),
(4, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2),
(5, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3),
(6, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3),
(7, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4),
(8, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4),
(9, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5),
(10, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6);

SELECT nextval ('product_id_seq');

INSERT INTO groups (name) VALUES ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

INSERT INTO users (id, name, email, password, creation_date) VALUES
(1, 'João da Silva', 'joao.ger@gmail.com', '123', now()),
(2, 'Maria Joaquina', 'maria.vnd@email.com', '123', now()),
(3, 'José Souza', 'jose.aux@outlook.com', '123', now()),
(4, 'Sebastião Martins', 'sebastiao.cad@test.com', '123', now()),
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', now());

INSERT INTO restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
INSERT INTO groups_permission (groups_id, permission_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
INSERT INTO users_groups (users_id, groups_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

INSERT INTO users_restaurants_owner (user_id, restaurant_id) values (1, 5), (3, 5);

INSERT INTO orders (
id, subtotal, tax_freight, total_value, restaurant_id, user_client_id, payment_method_id,
address_city_id, address_cep, address_place, address_number, address_complement, address_district,
status, creation_date)
VALUES
(1, 298.90, 10, 308.90, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CREATED', now()),
(2, 79, 0, 79, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CREATED', now());

INSERT INTO order_item (id, order_id, product_id, quantity, unit_price, total_price, observation) VALUES
(1, 1, 1, 1, 78.9, 78.9, null),
(2, 1, 2, 2, 110, 220, 'Menos picante, por favor'),
(3, 2, 6, 1, 79, 79, 'Ao ponto');
