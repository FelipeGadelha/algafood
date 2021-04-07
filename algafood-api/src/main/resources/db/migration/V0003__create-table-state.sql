create table state (
    id SERIAL NOT NULL, 
    name varchar(150) NOT NULL, 
    primary key (id)
    );
    
alter table city add constraint FK_city_state foreign key (state_id) references state (id);