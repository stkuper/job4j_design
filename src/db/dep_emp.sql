create table departments(
id serial primary key,
dName varchar(100)
);

create table employees(
id serial primary key,
eName varchar(100),
departments_id int references departments(id)
);

insert into departments(dName) values
('main'),
('economics'),
('general'),
('order');

insert into employees(eName, departments_id) values
('Sacha', 1),
('Lesha', 2),
('Anya', 2),
('Petya', 3),
('Vanya', 3),
('Sergey', 4),
('Sara', null),
('Somone', null);

select * from employees e
left join departments d on d.id = e.departments_id;

select * from employees e
right join departments d on d.id = e.departments_id;

select * from employees e
full join departments d on d.id = e.departments_id;

select * from employees
cross join departments;

select eName from employees e
left join departments d on d.id = e.departments_id
where e.departments_id is null;

select eName, dName from departments d
left join employees e on e.departments_id = d.id;

select eName, dName from employees e
right join departments d on d.id = e.departments_id;

create table teen(
id serial primary key,
tName varchar(10),
gender varchar(10)
);

insert into teen(tName, gender) values
('Анна', 'Ж'),
('Света', 'Ж'),
('Юля', 'Ж'),
('Саня', 'М'),
('Юра', 'М'),
('Вова', 'М');

select * from teen t
cross join teen g
where t.gender = 'М' and g.gender = 'Ж';