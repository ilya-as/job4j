--Создать SQL скрипт инициализирующий создание новой базы данных.

CREATE DATABASE items;

--Создать SQL скрипт создающий таблицы для хранения структуры системы заявок.


create table roles (
   id serial primary key,
   role varchar(50) not null
);

create table users (
   id serial primary key,
   name varchar(200) not null,
   role_id int references roles(id)
);

create table rules (
   id serial primary key,
   rule varchar(50) not null
);

create table rule_roles (
   id serial primary key,
   rule_id int references rules(id),
   role_id int references roles(id)
);

create table categorys (
   id serial primary key,
   category varchar(200)
);

create table states (
   id serial primary key,
   state varchar(200)
);

create table items (
   id serial primary key,
   user_id int references users(id),
   category_id int references categorys(id),
   state_id int references states(id),
   item varchar(200) not null
);

create table comments (
   id serial primary key,
   item_id int references items(id),
   comment varchar(2000)
);

create table attachs (
   id serial primary key,
   item_id int references items(id),
   attach varchar(2000)
);

--Создать SQL скрипт заполняющий начальные данные для системы заявок.

insert into roles (role) values ('admin');
insert into roles (role) values ('user');
insert into users (name, role_id) values ('Administartor',1);
insert into rules (rule) values ('add');
insert into rules (rule) values ('edit');
insert into rules (rule) values ('delete');
insert into rule_roles (role_id, rule_id) values (1, 1);
insert into rule_roles (role_id, rule_id) values (1, 2);
insert into rule_roles (role_id, rule_id) values (1, 3);
insert into rule_roles (role_id, rule_id) values (2, 2);
insert into categorys (category) values ('category2');
insert into states (state) values ('state');
insert into items (item,user_id,category_id,state_id) values ('item1',1,1,1);
insert into comments (comment,item_id) values ('comment',1);
insert into attachs (attach,item_id) values ('attach',1);