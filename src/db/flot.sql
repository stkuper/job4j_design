create table flot(
    id serial primary key,
    nameShip varchar(100),
    cargo int,
    trade boolean
);

insert into flot(nameShip, cargo, trade)
values('Vezuchiy', 7000, true),
('Nevezuchiy', 5000, false);
select * from flot;
update flot set nameShip = 'TheBestFromVezuchiy';
select * from flot;
delete from flot;