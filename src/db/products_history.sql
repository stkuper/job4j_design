DROP trigger if exists tax_before_row on products2;
DROP trigger if exists tax_after_st on products2;
DROP trigger if exists history_trigger on products2;
DROP table if exists products2;
DROP table if exists history_of_price;

CREATE TABLE products2(
    id serial PRIMARY KEY,
    product_name varchar(50),
    producer varchar(50),
    count integer DEFAULT 0,
    price integer
);

CREATE OR REPLACE function st_tax() returns trigger as $$
BEGIN
    update products2
    set price = price + price * 0.1
    where id = (SELECT id from nt);
    return new;
END;
$$ LANGUAGE plpgsql;

CREATE trigger tax_after_st
    after INSERT on products2
    referencing new table as nt
    for each statement
    execute procedure st_tax();

CREATE OR REPLACE function row_tax() returns trigger as $$
BEGIN
    new.price = new.price + new.price * 0.2;
    return new;
END;
$$ LANGUAGE plpgsql;

CREATE trigger tax_before_row
    before INSERT on products2
    for each row
    execute procedure row_tax();

CREATE TABLE history_of_price(
    id serial PRIMARY KEY,
    history_name varchar(50),
    price integer,
    hdate timestamp
);

CREATE OR REPLACE function history_row() returns trigger as $$
BEGIN
    insert into history_of_price(history_name, price, hdate)
    values (new.product_name, new.price, now());
    return new;
END;
$$ LANGUAGE plpgsql;

CREATE trigger history_trigger
    after INSERT on products2
    for each row
    execute procedure history_row();

INSERT INTO products2 (product_name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
INSERT INTO products2 (product_name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
INSERT INTO products2 (product_name, producer, count, price) VALUES ('product_4', 'producer_4', 5, 100);

TRUNCATE TABLE products2;
TRUNCATE table history_of_price;

SELECT * FROM history_of_price;
SELECT * FROM products2;