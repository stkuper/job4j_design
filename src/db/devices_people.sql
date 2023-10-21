create table devices(
id serial primary key,
dName varchar(255),
price decimal
);

create table people(
id serial primary key,
pName varchar(255)
);

create table devices_people(
id serial primary key,
devices_id int references devices(id),
people_id int references people(id)
);

insert into devices(dName, price) values
('mobile', 55555.99),
('watch', 12599.59),
('tv', 99999.99),
('headphones', 10599.99),
('notebook', 199999.99);

insert into people(pName) values
('Ivan'),
('Dima'),
('Kirill'),
('Petr'),
('Stas'),
('Alexey');

insert into devices_people(devices_id, people_id) values
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 2),
(2, 3),
(3, 4),
(3, 6),
(4, 2),
(4, 5),
(4, 6),
(5, 4),
(5, 6);

select * from devices;
select * from people;
select * from devices_people;

select avg(price) from devices;

select people.pName, avg(devices.price)
from devices_people as dp
join devices
on dp.devices_id = devices.id
join people
on dp.people_id = people.id
group by people.pName;

select p.pName, avg(d.price)
from devices_people as dp
join devices d
on dp.devices_id = d.id
join people p
on dp.people_id = p.id
group by p.pName
having avg(d.price) > 50000.00;