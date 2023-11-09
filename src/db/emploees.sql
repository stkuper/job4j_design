BEGIN;
CREATE TABLE emploees(
id serial primary key,
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

SELECT * FROM emploees;

UPDATE emploees
SET experience = random() * 10;
UPDATE emploees
SET salary = random() * 3000;

SAVEPOINT backup;

DELETE FROM emploees;
DROP TABLE IF EXISTS emploees;

ROLLBACK TO backup;

UPDATE emploees
SET salary = salary * 2
WHERE experience > 3 AND salary < 2500;

SAVEPOINT salary_increase;

DELETE FROM emploees
WHERE experience = 1;

ROLLBACK TO salary_increase;

COMMIT;

SELECT * FROM emploees;
DELETE FROM emploees;
DROP TABLE IF EXISTS emploees;