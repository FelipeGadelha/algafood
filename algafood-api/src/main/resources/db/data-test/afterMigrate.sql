
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

INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date, address_city_id, address_cep, address_place, address_number, address_district) VALUES (1, 'Thai Gourmet', 10, 1, now(), now(), 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) VALUES (2, 'Coco Bambu', 100.0, 1, now(), now());
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) VALUES (3, 'Outback', 150.50, 3, now(), now());
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) VALUES (4, 'Paris 6', 150.50, 5, now(), now());
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) VALUES (5, 'Sí Señor', 80.0, 4, now(), now());
INSERT INTO Restaurant (id, name, tax_freight, kitchen_id, creation_date, update_date) VALUES (6, 'terraço italia', 90.0, 6, now(), now());

INSERT INTO payment_method (id, description) VALUES (1, 'cartão de credito');
INSERT INTO payment_method (id, description) VALUES (2, 'cartão de debito');
INSERT INTO payment_method (id, description) VALUES (3, 'boleto');

INSERT INTO permission (id, name, description) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permission (id, name, description) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

