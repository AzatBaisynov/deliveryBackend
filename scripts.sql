create table dish(
id serial primary key,
cafe_id Integer references cafe(id) not null,
dish_name varchar(100) not null,
grams_weight Integer not null,
price Integer
);

create table cafe(
id serial primary key,
cafe_name varchar(100) not null unique,
open_at time,
close_at time,
average_check Integer not null,
delivery_cost Integer
);

create table food_order(
id serial primary key,
dish_id Integer references dish(id) not null,
order_cost Integer not null,
waiting_time varchar(50) not null,
order_time timestamp
);