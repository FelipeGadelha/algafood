
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

insert into Restaurant (id, name, tax_freight, kitchen_id) values (1, 'Coco Bambu', 100.0, 1);
insert into Restaurant (id, name, tax_freight, kitchen_id) values (2, 'Outback', 150.50, 3);
insert into Restaurant (id, name, tax_freight, kitchen_id) values (3, 'Paris 6', 150.50, 5);
insert into Restaurant (id, name, tax_freight, kitchen_id) values (4, 'Sí Señor', 80.0, 4);
insert into Restaurant (id, name, tax_freight, kitchen_id) values (5, 'terraço italia', 90.0, 6);

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
