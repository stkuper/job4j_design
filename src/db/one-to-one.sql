create table numbers(
id serial primary key,
region int,
number int
)

create table bus(
id serial primary key,
brand varchar(100),
model varchar(100),
number_id int references numbers(id) unique
);