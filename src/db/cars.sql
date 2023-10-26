create table car_bodies(
id serial primary key,
cbName varchar(100)
);

create table car_engines(
id serial primary key,
ceName varchar(100)
);

create table car_transmission(
id serial primary key,
ctName varchar(100)
);

create table cars2(
id serial primary key,
cName varchar(100),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmission(id)
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

insert into cars2(cName, body_id, engine_id, transmission_id) values
('granta', 1, 1, 1),
('vesta', 1, 1, 3),
('mini', 2, 1, 2),
('tiygo', 4, 2, 2),
('tundra', 3, 2, 1),
('taiga', 3, 2, 3),
('volga', 2, 3, 1),
('oka', 1, 3, 1),
('camry', 1, 1, 3),
('ural', 4, 2, 1),
('atom', 2, 4, 2);

select cars2.id, cName Модель, body_id Кузов,
engine_id Двигатель, transmission_id Трансмиссия from cars2
left join car_bodies on cars2.body_id = car_bodies.id
left join car_engines on cars2.engine_id = car_engines.id
left join car_transmission on cars2.transmission_id = car_transmission.id;

select cbName Кузов from car_bodies
where cbName not in
(select cbName from car_bodies
join cars2 on cars2.body_id = car_bodies.id);

select ceName Двигатель from car_engines
where ceName not in
(select ceName from car_engines
join cars2 on cars2.engine_id = car_engines.id);

select ctName "Коробка передач" from car_transmission
where ctName not in
(select ctName from car_transmission
join cars2 on cars2.transmission_id = car_transmission.id);