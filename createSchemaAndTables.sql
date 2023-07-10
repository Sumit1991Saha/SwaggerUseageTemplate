create database if not exists openapi;
use openapi;
drop table employee;
create table employee (
    id bigint not null auto_increment,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    designation varchar(100) not null,
    emp_type varchar(100) not null,
    primary key (id)
)engine=InnoDB;

insert into employee (firstname, lastname, designation, emp_type) values ("Lokesh", "Gupta", "Manager", "SALES");
insert into employee (firstname, lastname, designation, emp_type) values ("John", "Gruber", "Student", "DEVELOPER");
insert into employee (firstname, lastname, designation, emp_type) values ("Melcum", "Marshal", "Engineer", "DEVELOPER");
