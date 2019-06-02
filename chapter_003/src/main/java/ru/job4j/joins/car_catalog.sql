CREATE DATABASE car_catalog;

create table car_body (
   id serial primary key,
   name varchar(200)
);

create table transmission (
   id serial primary key,
   name varchar(200)
);

create table engine (
   id serial primary key,
   name varchar(200)
);

create table cars (
   id serial primary key,
   name varchar(200),
   engine_id int references engine(id),
   car_body_id int references car_body(id),
   transmission_id int references transmission(id)
);

INSERT INTO car_body(name) values('Седан'), ('Хетчбек'), ('Универсал');
INSERT INTO transmission(name) values('Ручная'), ('Австоматическая'), ('Роботизированная');
INSERT INTO engine(name) values('Бензиновый'), ('Дизельный'), ('Электрический');

INSERT INTO cars(name,engine_id, car_body_id, transmission_id) values
('Toyota', 1, 1, 1),
('BMW', 2, 2, 2),
('Lada', 1,2,2);

Select * from cars LEFT JOIN engine ON cars.engine_id= engine.id
LEFT JOIN transmission ON cars.transmission_id= transmission.id
LEFT JOIN car_body ON cars.car_body_id= car_body.id

Select transmission.name from transmission LEFT JOIN cars ON cars.transmission_id= transmission.id
where cars.id is null;

Select engine.name from engine LEFT JOIN cars ON cars.engine_id= engine.id
where cars.id is null;


Select car_body.name from car_body LEFT JOIN cars ON car_body.id=cars.car_body_id
where cars.id is null;