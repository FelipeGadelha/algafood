create table city (
    id SERIAL NOT NULL, 
    name varchar(150) NOT NULL, 
    state_id BIGINT NOT NULL,
    primary key (id)
    );
