CREATE DATABASE `devicemanagement`;
USE`devicemanagement`;

CREATE TABLE `user` (
    `id` INT(20) NOT NULL PRIMARY KEY,
    `username` CHAR(30) NOT NULL,
    `password` CHAR(30) NOT NULL,
    `email` CHAR(30) ,
) 

create table `device` (
    `id` integer not null ,
)