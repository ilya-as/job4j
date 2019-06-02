create table type (
   id serial primary key,
   name varchar(200)
);

create table product (
id serial primary key,
name varchar(200),
type_id int references type(id),
expired_date timestamp without time zone,
price numeric(10, 2)
);


INSERT INTO type(name) values('СЫР'), ('МОЛОКО'), ('мороженное');

INSERT INTO product(name,type_id, expired_date, price) values
('Молочный сыр', 1, '2019-05-24 18:00:00', 700),
('Молоко', 2, '2019-05-24 18:00:00', 50),
('Сыр', 1, '2019-05-24 18:00:00', 900),
('Пармезан', 1, '2019-05-24 18:00:00', 1200),
('мороженное рожок', 3,'2019-05-24 18:00:00', 80),
('мороженное', 3,'2019-05-24 18:00:00', 40);

--1. Написать запрос получение всех продуктов с типом "СЫР"
Select * from product LEFT JOIN type ON product.type_id= type.id
where type.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
Select * from product
where name LIKE '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
Select * from product
where (EXTRACT(MONTH FROM expired_date) - EXTRACT(MONTH FROM NOW()))=1;

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product
WHERE price = (
SELECT MAX(price) FROM product
);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
Select COUNT(product.name) from product LEFT JOIN type ON product.type_id= type.id
where type.name = 'СЫР';

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
Select COUNT(product.name) from product LEFT JOIN type ON product.type_id= type.id
where type.name IN ('СЫР','МОЛОКО');

7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
Select type.name from product LEFT JOIN type ON product.type_id= type.id
group by type.name having count(product.id) < 10;

8. Вывести все продукты и их тип.
Select product.name AS  product, type.name as type from product LEFT JOIN type ON product.type_id= type.id