create table products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price) VALUES
('product_1', 1, 5),
('product_2', 2, 10),
('product_3', 3, 15),
('product_4', 4, 20),
('product_5', 5, 25),
('product_6', 6, 30),
('product_7', 7, 35),
('product_8', 8, 40),
('product_9', 9, 45),
('product_10', 10, 50),
('product_11', 11, 55),
('product_12', 12, 60),
('product_13', 13, 65),
('product_14', 14, 70),
('product_15', 15, 75),
('product_16', 16, 80),
('product_17', 17, 85),
('product_18', 18, 90),
('product_19', 19, 95),
('product_20', 20, 100);

select * from products;

BEGIN;
DECLARE
	cursor_products cursor for
		select * from products;
FETCH 10 FROM cursor_products;
FETCH NEXT FROM cursor_products;
FETCH NEXT FROM cursor_products;
MOVE FORWARD 2 FROM cursor_products;
FETCH NEXT FROM cursor_products;
CLOSE cursor_products;
COMMIT;

BEGIN;
DECLARE
	cursorback_products SCROLL cursor for
		select * from products;
MOVE FORWARD 21 FROM cursorback_products;
FETCH BACKWARD ALL FROM cursorback_products;

FETCH LAST FROM cursorback_products;
MOVE BACKWARD 4 FROM cursorback_products;
FETCH BACKWARD FROM cursorback_products;

MOVE BACKWARD 7 FROM cursorback_products;
FETCH BACKWARD FROM cursorback_products;

MOVE BACKWARD 4 FROM cursorback_products;
FETCH BACKWARD FROM cursorback_products;

FETCH BACKWARD FROM cursorback_products;
CLOSE cursorback_products;
COMMIT;