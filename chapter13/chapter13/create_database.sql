/**
 * This script has been written for use with an Apache Derby database.
 * 
 * To use, first create a schema in your database to load these objects into.
 * 
 * Next, grant the schema the necessary privileges to create tables, procedures,
 * and sequences.
 *
 * Last, connect as the schema that you have created and execute this script.
 */

create table recipes(
    id              int not null,
    recipe_number   varchar(10) not null,
    recipe_name     varchar(100) not null,
    description     varchar(500),
    text            clob,
    constraint recipes_pk primary key (id) enable
);

create sequence recipes_seq
start with 1
increment by 1;
-- derby syntax
alter table recipes
add constraint recipes_number_nn check (recipe_number is not null);

insert into recipes values(
next value for recipes_seq,
'13-1',
'Connecting to a Database',
'DriverManager and DataSource Implementations',
'Recipe Text');

insert into recipes values(
next value for recipes_seq,
'13-2',
'Querying a Database and Retrieving Results',
'Obtaining and using data from a DBMS',
'Recipe Text');

insert into recipes values(
next value for recipes_seq,
'13-3',
'Handling SQL Exceptions',
'Using SQLException',
'Recipe Text');

create table book_author(
id          int primary key,
last        varchar(30),
first       varchar(30));

create sequence book_author_seq
start with 1
increment by 1;

create table book(
id          int primary key,
title       varchar(150),
image       varchar(150),
description clob);

create sequence book_seq
start with 1
increment by 1;

insert into book values(
next value for book_seq,
'Java 8 Recipes',
'java8recipes.png',
'Learn about solving real-world problems with Java 8');

insert into book values(
next value for book_seq,
'Java EE 7 Recipes',
'javaee7recipes.png',
'Learn about solving real-world problems with Java EE 7');

insert into book values(
next value for book_seq,
'Introducing Java EE 7',
'introjavaee7recipes.png',
'Learn about all of the new features included in Java EE 7');

insert into book values(
next value for book_seq,
'Java 7 Recipes',
'java7recipes.png',
'Learn about solving real-world problems with Java 7');

insert into book values(
next value for book_seq,
'Java FX 2.0 - Introduction by Example',
'javafx2.png',
'Learn about solving real-world problems with JavaFX');

create table publication(
id              int primary key,
author_id       int not null,
book_title      varchar(500) not null,
publish_date    date,
publish_co      varchar(100));

create sequence publication_seq
start with 1
increment by 1;

insert into publication values (
next value for publication_seq,
(select id from book_author where lastname = 'JUNEAU'),
'Java EE 7 Recipes',
date('2013-05-01'),
'APRESS');

insert into publication values (
next value for publication_seq,
(select id from book_author where lastname = 'JUNEAU'),
'Introducing Java EE 7',
date('2013-07-01'),
'APRESS');

create table author_work(
id              int primary key,
author_id       int not null,
chapter_number  int not null,
chapter_title   varchar(100) not null,
constraint author_work_fk
foreign key(author_id) references book_author(id));

create sequence author_work_seq
start with 1
increment by 1;

insert into book_author values(
next value for book_author_seq,
'JUNEAU',
'JOSH');

insert into book_author values(
next value for book_author_seq,
'DEA',
'CARL');

insert into book_author values(
next value for book_author_seq,
'BEATY',
'MARK');

insert into book_author values(
next value for book_author_seq,
'GUIME',
'FREDDY');

insert into book_author values(
next value for book_author_seq,
'OCONNER',
'JOHN');

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'JUNEAU'),
(select id from book where title = 'Java 8 Recipes'));

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'JUNEAU'),
(select id from book where title = 'Java 7 Recipes'));

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'JUNEAU'),
(select id from book where title = 'Java EE 7 Recipes'));

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'JUNEAU'),
(select id from book where title = 'Introducing Java EE 7'));

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'GUIME'),
(select id from book where title = 'Java 7 Recipes'));

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'DEA'),
(select id from book where title = 'Java 7 Recipes'));

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'OCONNER'),
(select id from book where title = 'Java 7 Recipes'));

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'BEATY'),
(select id from book where title = 'Java 7 Recipes'));

insert into author_work values(
next value for author_work_seq,
(select id from book_author where lastname = 'DEA'),
(select id from book where title = 'Java FX 2.0 - Introduction by Example'));

create table recipe_text (
id              int primary key,
recipe_id       int not null,
text            clob,
constraint recipe_text_fk
foreign key (recipe_id)
references recipes(id));

create sequence recipe_text_seq
start with 10
increment by 1;


create or replace procedure dummy_proc (text IN VARCHAR2,
                                        msg OUT VARCHAR2) as
begin
    -- Do something
    msg :=text;
end;

commit;
/