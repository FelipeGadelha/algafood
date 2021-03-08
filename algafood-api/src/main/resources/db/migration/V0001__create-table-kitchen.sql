create table kitchen (
    id BIGINT NOT NULL, 
    name varchar(60) NOT NULL, 
    primary key (id)
    );

create sequence kitchen_id_seq start 1 increment 1;