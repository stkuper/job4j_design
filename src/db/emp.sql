CREATE TABLE emploees(
id serial PRIMARY KEY,
first_name varchar(50),
last_name varchar(50),
profession varchar(50),
experience int,
salary decimal(12, 2)
);

INSERT INTO emploees(first_name, last_name, profession) VALUES
('Ivan', 'Ivanov', 'manager'),
('Petr', 'Petrov', 'DevOPS'),
('Sveta', 'Svetikova', 'finance'),
('Sergey', 'Sergeev', 'Java'),
('Denis', 'Denisov', 'JavaScript');

UPDATE emploees
SET experience = RANDOM() * 10;
UPDATE emploees
SET salary = RANDOM() * 3000;

BEGIN transaction isolation level serializable;
select sum(salary) from emploees;
UPDATE emploees
SET salary = salary * 2
WHERE experience > 3 AND salary < 2500;
COMMIT;

BEGIN transaction isolation level serializable;
select sum(salary) from emploees;
UPDATE emploees
SET salary = salary * 2
WHERE experience > 3 AND salary < 2500;
COMMIT;

SELECT * FROM emploees;
DELETE FROM emploees;
DROP TABLE IF EXISTS emploees;