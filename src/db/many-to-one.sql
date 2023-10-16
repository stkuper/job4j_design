create table cars(
id serial primary key,
brand varchar(100),
model varchar(100)
);

create table owners(
id serial primary key,
first_name varchar(100),
last_name varchar(100),
car_id int references cars(id)
);
