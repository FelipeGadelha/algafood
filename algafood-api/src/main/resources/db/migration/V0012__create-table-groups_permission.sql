create table groups_permission (
	groups_id BIGINT NOT NULL, 
	permission_id BIGINT NOT NULL
	);

alter table groups_permission add constraint FK_groups_permission_groups foreign key (groups_id) references groups (id);
alter table groups_permission add constraint FK_groups_permission_permission foreign key (permission_id) references permission (id);