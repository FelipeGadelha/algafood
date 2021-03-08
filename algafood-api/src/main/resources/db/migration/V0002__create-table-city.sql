create table city (
    id BIGINT NOT NULL, 
    name varchar(150) NOT NULL, 
    state_id BIGINT NOT NULL,
    primary key (id)
    );

create sequence city_id_seq start 1 increment 1;