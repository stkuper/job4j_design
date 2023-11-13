CREATE TABLE customers(
    id serial PRIMARY KEY,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers(first_name, last_name, country) VALUES
('Петр', 'Петров','Россия'),
('Иван', 'Иванов','Казахстан'),
('Света', 'Светикова','Белоруссия'),
('Джон', 'Джонсон','США'),
('Василий', 'Голобородько','Украина');

UPDATE customers
SET age = RANDOM() * 100;

SELECT * FROM customers
WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders(
    id serial PRIMARY KEY,
    amount int,
    customer_id int REFERENCES customers(id)
);

INSERT INTO orders(amount, customer_id) VALUES
(3300, 1),
(5600, 3),
(12400, 4);

SELECT * FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);