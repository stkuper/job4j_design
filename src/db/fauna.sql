create table fauna(
id serial primary key,
f_name text,
avg_age int,
discovery_date date
);

insert into fauna(f_name, avg_age, discovery_date) values
('ant', 1000, null),
('cat', 11000, null),
('dog', 25000, null),
('big_fish_tasty', 1500, '21.03.1967'),
('fish_tasty', 1000, '31.05.1921'),
('litle_fish', 150, '11.05.1987'),
('cow', 45000, '01.01.100'),
('monkey', 50000, '03.07.505'),
('bear', 70000, '02.09.403'),
('turtle', 150000, '05.11.1111'),
('lion', 30000, '07.08.99');

select * from fauna;
select * from fauna where f_name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';

