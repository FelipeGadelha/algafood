create sequence city_id_seq start 1 increment 1
create sequence groups_id_seq start 1 increment 1
create sequence kitchen_id_seq start 1 increment 1
create sequence order_id_seq start 1 increment 1
create sequence order_item_id_seq start 1 increment 1
create sequence payment_method_id_seq start 1 increment 1
create sequence permission_id_seq start 1 increment 1
create sequence product_id_seq start 1 increment 1
create sequence restaurant_id_seq start 1 increment 1
create sequence state_id_seq start 1 increment 1
create sequence users_id_seq start 1 increment 1
create table city (id int8 not null, name varchar(255), state_id int8 not null, primary key (id))
create table groups (id int8 not null, name varchar(255), primary key (id))
create table groups_permission (groups_id int8 not null, permission_id int8 not null)
create table kitchen (id int8 not null, name varchar(255), primary key (id))
create table order_item (id int8 not null, observation varchar(255), quantity int4, total_price numeric(19, 2), unit_price numeric(19, 2), order_id int8 not null, product_id int8 not null, primary key (id))
create table orders (id int8 not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_place varchar(255), cancel_date timestamp, confirmation_date timestamp, creation_date timestamp not null, delivery_date timestamp, status int4, subtotal numeric(19, 2), tax_freight numeric(19, 2), total_value numeric(19, 2), address_city_id int8, user_client_id int8 not null, payment_method_id int8 not null, restaurant_id int8 not null, primary key (id))
create table payment_method (id int8 not null, description varchar(255), primary key (id))
create table permission (id int8 not null, description varchar(255), name varchar(255), primary key (id))
create table product (id int8 not null, active boolean, description varchar(255), name varchar(255), price numeric(19, 2), restaurant_id int8 not null, primary key (id))
create table restaurant (id int8 not null, address_cep varchar(255), address_complement varchar(255), address_district varchar(255), address_number varchar(255), address_place varchar(255), creation_date timestamp not null, name varchar(255) not null, tax_freight numeric(19, 2) not null, update_date timestamp not null, address_city_id int8, kitchen_id int8 not null, primary key (id))
create table restaurant_payment_method (restaurant_id int8 not null, payment_method_id int8 not null)
create table state (id int8 not null, name varchar(255) not null, primary key (id))
create table users (id int8 not null, creation_date timestamp not null, email varchar(255), name varchar(255), password varchar(255), primary key (id))
create table users_groups (users_id int8 not null, groups_id int8 not null)
alter table city add constraint FK6p2u50v8fg2y0js6djc6xanit foreign key (state_id) references state
alter table groups_permission add constraint FKq5xwkpxmmgji5tt5x7feywwqq foreign key (permission_id) references permission
alter table groups_permission add constraint FK3mc8dsxrdu2uyvq247b42xoe7 foreign key (groups_id) references groups
alter table order_item add constraint FKt4dc2r9nbvbujrljv3e23iibt foreign key (order_id) references orders
alter table order_item add constraint FK551losx9j75ss5d6bfsqvijna foreign key (product_id) references product
alter table orders add constraint FKixyk2gsx2rutmumm3gxvb3y0v foreign key (address_city_id) references city
alter table orders add constraint FKk51dqcjh32oa2e9yr4u4dvi5w foreign key (user_client_id) references users
alter table orders add constraint FKgeqwl6x0iadp9e2459uh3o8fv foreign key (payment_method_id) references payment_method
alter table orders add constraint FKi7hgjxhw21nei3xgpe4nnpenh foreign key (restaurant_id) references restaurant
alter table product add constraint FKp4b7e36gck7975p51raud8juk foreign key (restaurant_id) references restaurant
alter table restaurant add constraint FK8pcwgn41lfg43d8u82ewn3cn foreign key (address_city_id) references city
alter table restaurant add constraint FKrur1dqx79jim8s8dvdp16qc3d foreign key (kitchen_id) references kitchen
alter table restaurant_payment_method add constraint FK5dxd5cjhjqf8eai6xugad3e1g foreign key (payment_method_id) references payment_method
alter table restaurant_payment_method add constraint FKbjuwyavt07p2uihdqt6jtmkyj foreign key (restaurant_id) references restaurant
alter table users_groups add constraint FKjex8no6gj9undclnlyn9l52wm foreign key (groups_id) references groups
alter table users_groups add constraint FKeg984vk9mx0imcdffn06f8q45 foreign key (users_id) references users
