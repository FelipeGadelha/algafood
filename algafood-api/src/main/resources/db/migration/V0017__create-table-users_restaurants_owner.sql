create table users_restaurants_owner (
	user_id BIGINT NOT NULL,
	restaurant_id BIGINT NOT NULL,

	primary key (user_id, restaurant_id)
	);

alter table users_restaurants_owner add constraint FK_users_restaurants_owner_user foreign key (user_id) references users (id);
alter table users_restaurants_owner add constraint FK_users_restaurants_owner_restaurant foreign key (restaurant_id) references restaurant (id);