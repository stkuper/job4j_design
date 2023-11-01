CREATE TABLE products(
    id serial PRIMARY KEY,
    product_name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

CREATE OR REPLACE procedure insert_data
(i_name varchar, prod varchar, i_count integer, i_price integer) as $$
BEGIN
    insert into products(product_name, producer, count, price)
    values (i_name, prod, i_count, i_price);
END;
$$ LANGUAGE plpgsql;

call insert_data('product_2', 'producer_2', 15, 32);
SELECT * FROM products;

CREATE OR REPLACE procedure update_data
(u_count integer, tax float, u_id integer) as $$
BEGIN
    if u_count > 0 THEN
        UPDATE products SET count = count - u_count where id = u_id;
    end if;
    if tax > 0 THEN
        UPDATE products SET price = price + price * tax;
    end if;
END;
$$ LANGUAGE plpgsql;

call update_data(10, 0, 1);
SELECT * FROM products;
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);
SELECT * FROM products;

call update_data(0, 0.2, 0);
SELECT * FROM products;

DELETE FROM products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

CREATE OR REPLACE function f_insert_data
(i_name varchar, prod varchar, i_count integer, i_price integer) RETURNS void AS $$
BEGIN
    insert into products(product_name, producer, count, price)
    values(i_name, prod, i_count, i_price);
END;
$$ LANGUAGE plpgsql;

SELECT f_insert_data('product_1', 'producer_1', 25, 50);
SELECT * FROM products;

CREATE OR REPLACE function f_update_data(u_count integer, tax float, u_id integer)
RETURNS integer AS $$
declare
    result integer;
BEGIN
    if u_count > 0 THEN
        update products set count = count - u_count where id = u_id;
        SELECT into result count from products where id = u_id;
    end if;
    if tax > 0 THEN
        UPDATE products SET price = price + price * tax;
        SELECT into result SUM(price) from products;
    end if;
    return result;
END;
$$ LANGUAGE plpgsql;

SELECT f_update_data(10, 0, 1);
SELECT f_insert_data('product_2', 'producer_2', 15, 32);
SELECT f_insert_data('product_3', 'producer_3', 8, 115);
SELECT * FROM products;
SELECT f_update_data(0, 0.2, 0);
SELECT * FROM products;

CREATE OR REPLACE procedure delete_data_row(d_id integer) AS $$
BEGIN
    DELETE FROM products
    WHERE products.id = d_id;
END;
$$ LANGUAGE plpgsql;

call delete_data_row(2);

CREATE OR REPLACE function f_delete_data_row(d_id integer)
RETURNS void AS $$
BEGIN
    DELETE FROM products
    WHERE products.id = d_id;
END;
$$ LANGUAGE plpgsql;

SELECT f_delete_data_row(1);
SELECT * FROM products;

DROP procedure insert_data;
DROP procedure update_data;
DROP procedure delete_data_row;
DROP function f_insert_data;
DROP function f_update_data;
DROP function f_delete_data_row;
DROP TABLE products;



