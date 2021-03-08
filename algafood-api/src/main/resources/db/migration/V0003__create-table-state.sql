create table state (
    id BIGINT NOT NULL, 
    name varchar(150) NOT NULL, 
    primary key (id)
    );

create sequence state_id_seq start 1 increment 1;

alter table city add constraint FK_city_state foreign key (state_id) references state (id);