create table users(
id serial primary key,
first_name varchar(100),
last_name varchar(100)
);

create table roles(
id serial primary key,
role_name varchar(100),
users_id int references users(id)
);

create table rules(
id serial primary key,
rule_name varchar(100)
);

create table items(
id serial primary key,
item_name varchar(100),
users_id int references users(id)
);

create table comments(
id serial primary key,
comment_name varchar(100),
items_id int references items(id)
);

create table attachs(
id serial primary key,
attach_name varchar(100),
items_id int references items(id)
);

create table states(
id serial primary key,
state_name varchar(100),
items_id int references items(id)
);

create table categories(
id serial primary key,
categori_name varchar(100),
items_id int references items(id)
);

create table roles_rules(
id serial primary key,
roles_id int references roles(id),
rules_id int references rules(id)
);