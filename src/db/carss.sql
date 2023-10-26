create table car_bodies(
body_id serial primary key,
cbName varchar(100)
);

create table car_engines(
engine_id serial primary key,
ceName varchar(100)
);

create table car_transmission(
transmission_id serial primary key,
ctName varchar(100)
);

create table owner(
owner_id serial primary key,
first_name varchar(50),
last_name varchar(50)
);

create table cars(
id serial primary key,
cName varchar(100),
weight int,
date_of_manufacture date,
date_of_sale date,
body_id int references car_bodies(body_id),
engine_id int references car_engines(engine_id),
transmission_id int references car_transmission(transmission_id),
owner_id int references owner(owner_id)
);

insert into car_bodies(cbName) values
('седан'),
('хэтчбэк'),
('пикап'),
('кроссовер'),
('минивэн');

insert into car_engines(ceName) values
('бензиновый'),
('дизельный'),
('газовый'),
('электрический'),
('гибридный');

insert into car_transmission(ctName) values
('механическая'),
('автоматическая'),
('вариаторная'),
('роботизированная');

insert into owner(first_name, last_name) values
('Иван', 'Петров'),
('Петр', 'Сидоров'),
('Сидр', 'Иванов'),
('Анна', 'Светикова'),
('Света', 'Анникова'),
('Юлия', 'Романова'),
('Роман', 'Александров'),
('Александр', 'Романов');

insert into cars(cName, weight, date_of_manufacture, date_of_sale,
            body_id, engine_id, transmission_id, owner_id) values
('granta', 1430, '2020-01-02', '2021-02-03', 1, 1, 1, 1),
('vesta', 1630, '2021-03-04', '2021-04-03', 1, 1, 3, 2),
('mini', 1130, '2022-03-05', '2020-05-06', 2, 1, 2, 3),
('tiygo', 1930, '2020-05-07', '2021-06-07', 4, 2, 2, 4),
('tundra', 2030, '2021-02-04', '2022-03-02', 3, 2, 1, 5),
('taiga', 2330, '2022-05-08', '2023-06-01', 3, 2, 3, 6),
('volga', 1830, '2020-01-05', '2020-09-06', 2, 3, 1, 7),
('oka', 830, '2020-07-08', '2021-03-08', 1, 3, 1, 8),
('camry', 1530, '2022-02-22', '2023-01-12', 1, 1, 3, 1),
('ural', 2230, '2021-07-09', '2023-04-05', 4, 2, 1, 3),
('atom', 1130, '2022-09-06', '2023-10-11', 2, 4, 2, 5);

create or replace view cars_body_engine_transmission_owner as
select cars.id, cName Модель, weight "Масса машины", date_of_manufacture "Дата производства",
       date_of_sale "Дата продажи", cbName Кузов, ceName Двигатель, ctName Трансмиссия,
       first_name "Имя собственника", last_name "Фамилия собственника" from cars
left join car_bodies using(body_id)
left join car_engines using(engine_id)
left join car_transmission using(transmission_id)
left join owner using(owner_id);

select * from cars_body_engine_transmission_owner;
