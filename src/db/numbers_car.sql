create table number(
id serial primary key,
seria varchar(10),
number int,
region int
);

create table cars(
id serial primary key,
marka text,
model text,
owner text,
number_id int references number(id) unique
);

insert into number(seria, number, region) values
('cdf', 1254, 23),
('lkf', 3465, 65),
('tuy', 9533, 99),
('qrw', 9865, 12),
('llg', 8434, 61);

insert into cars(marka, model, owner, number_id) values
('lada', 'vesta', 'Ivanov', 5),
('kia', 'c-50', 'Petrov', 1),
('bmv', 'cx', 'Sidorov', 3),
('skoda', 'b-10', 'Surkov', 2),
('renault', 'medina', 'Pushin', 4);

select * from cars inner
join number on cars.number_id = number.id;

select c.marka, c.model, n.seria, n.number, n.region
from cars as c join number as n on c.number_id = n.id;

select c.marka as Марка, c.model as Модель,
n.seria as Серия, n.number as Номер, n.region as Регион
from cars as c join number as n on c.number_id = n.id;

select c.marka Марка, c.model Модель, c.owner as "Имя собственника",
n.seria Серия, n.number Номер, n.region Регион
from cars c join number n on c.number_id = n.id;