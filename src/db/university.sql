create table universities(
id serial primary key,
un_name text
);

create table students(
id serial primary key,
st_name text,
course int,
budget bool,
speciality text,
enroll_date timestamp,
university_id int references universities(id)
);

insert into universities(un_name)
values ('U1');
insert into universities(un_name)
values ('U2');
insert into universities(un_name)
values ('U3');
insert into universities(un_name)
values ('U4');
insert into universities(un_name)
values ('U5');

insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('AB', 1, true, 'S1', '2020-09-01', 1);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('AC', 2, true, 'S1', '2019-09-02', 1);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('AD', 3, true, 'M1', '2018-09-03', 1);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('AE', 4, false, 'Z1', '2017-09-04', 1);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('AF', 5, false, 'E1', '2016-09-05', 1);

insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('BC', 1, true, 'Q2', '2020-09-01', 2);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('BD', 2, true, 'R2', '2019-09-02', 2);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('BE', 3, false, 'T2', '2018-09-03', 2);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('BF', 4, false, 'Y2', '2017-09-04', 2);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('BG', 5, false, 'U2', '2016-09-05', 2);

insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('CD', 1, true, 'A3', '2020-09-01', 3);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('CE', 2, true, 'D3', '2019-09-01', 3);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('CF', 3, false, 'F3', '2018-09-01', 3);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('CG', 4, false, 'G3', '2017-09-01', 3);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('CH', 5, true, 'H3', '2016-09-01', 3);

insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('DE', 1, false, 'Z4', '2020-09-01', 4);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('DF', 2, true, 'X4', '2019-09-01', 4);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('DG', 3, true, 'C4', '2018-09-01', 4);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('DH', 4, false, 'V4', '2017-09-01', 4);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('DJ', 5, true, 'V4', '2016-09-01', 4);

insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('EF', 1, true, 'P5', '2020-09-01', 5);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('EG', 2, true, 'O5', '2019-09-01', 5);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('EH', 3, false, 'I5', '2018-09-01', 5);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('EJ', 4, true, 'J5', '2017-09-01', 5);
insert into students(st_name, course, budget, speciality, enroll_date, university_id)
values ('EI', 5, true, 'K5', '2016-09-01', 5);

select * from students;
select st_name, course, budget, speciality from students;
select * from students where course = 2;
select * from students where course != 2;
select * from students where st_name is null;
select * from students where st_name is not null;
select * from students where enroll_date > '01.09.2019';
select * from students where course > 2;
select * from students where st_name like 'D%';
select * from students where st_name like '%G';
select * from students where st_name like 'D%' and course > 2;
select * from students where st_name like 'D%' or course > 2;
select current_date;
select current_date > '10.09.2020';
select current_date + interval '2 month';
select * from students order by speciality asc;
select * from students order by speciality desc;
select distinct * from students;
select distinct course from students;
select * from students limit 10;