create table books(
id serial primary key,
book_name varchar(100),
count_page int
);

create table authors(
id serial primary key,
first_name varchar(100),
last_name varchar(100)
);

create table books_authors(
id serial primary key,
book_id int references books(id),
author_id int references authors(id)
);