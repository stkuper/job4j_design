create table type(
id serial primary key,
tName varchar(30)
);

create table product(
id serial primary key,
product_name varchar(50),
type_id int references type(id),
expired_date date,
price decimal,
quantity int
);

insert into type(tName) values
('СЫР'),
('МОЛОКО'),
('КОЛБАСЫ'),
('БАКАЛЕЯ'),
('МОРОЖЕНОЕ'),
('ПРИПРАВЫ'),
('ВОДА'),
('СОУСЫ');

insert into product(product_name, type_id, expired_date, price, quantity) values
('СЫР РУССКИЙ', 1, '2023-11-23', 700.50, 3),
('СЫР БЕЛОРУССКИЙ', 1, '2023-12-23', 800.50, 4),
('СЫР КОСТРОМСКОЙ', 1, '2023-10-12', 600.50, 2),
('МОЛОКО КОРОВЬЕ', 2, '2023-11-20', 100.50, 4),
('МОЛОКО КОЗЬЕ', 2, '2023-10-20', 200.50, 2),
('КЕФИР', 2, '2023-11-20', 100.50, 3),
('ТВОРОГ', 2, '2023-11-20', 200.50, 2),
('СОСИСКИ', 3, '2023-11-20', 300.50, 3),
('САРДЕЛЬКИ', 3, '2023-11-20', 400.50, 3),
('КОЛБАСЫ', 3, '2023-12-20', 600.50, 3),
('ГРЕЧКА', 4, '2023-10-20', 50.50, 6),
('РИС', 4, '2023-10-20', 90.50, 5),
('МАКАРОНЫ', 4, '2023-11-20', 70.50, 7),
('ПЛОМБИР МОРОЖЕННОЕ', 5, '2023-12-20', 70.50, 5),
('МОРОЖЕННОЕ ЭСКИМО', 5, '2023-12-20', 100.50, 4),
('СОЛЬ', 6, '2023-12-20', 20.50, 14),
('ПЕРЕЦ', 6, '2023-12-20', 50.50, 9),
('ВОДА', 7, '2023-11-20', 50.50, 19),
('КЕТЧУП', 8, '2023-12-20', 250.50, 11);

select * from product;
select * from type;

select * from product
join type on product.type_id = type.id
where tName = 'СЫР';

select * from product
where product_name like '%МОРОЖЕННОЕ%';

select * from product
where current_date > expired_date;

select product_name, price from product
where price = (select max(price) from product);

select tName, count(*) as количество from product
join type on product.type_id = type.id
group by tName;

select * from product
join type on product.type_id = type.id
where tName = 'СЫР' or tName = 'МОЛОКО';

select tName, count(*) as количество from product
join type on product.type_id = type.id
group by tName
having count(*) < 10;

select * from product
join type on product.type_id = type.id;

