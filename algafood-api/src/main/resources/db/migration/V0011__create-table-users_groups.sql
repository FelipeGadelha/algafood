create table users_groups (
	users_id BIGINT NOT NULL, 
	groups_id BIGINT NOT NULL
	);

alter table users_groups add constraint FK_users_groups_users foreign key (users_id) references users (id);
alter table users_groups add constraint FK_users_groups_groups foreign key (groups_id) references groups (id);