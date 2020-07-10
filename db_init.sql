CREATE DATABASE `devicemanagement`;

USE`devicemanagement`;

CREATE TABLE `user` (
    `id` INT(20) NOT NULL PRIMARY KEY,
    `username` CHAR(30) NOT NULL,
    `password` CHAR(30) NOT NULL,
    `email` CHAR(30) ,
    `superuser` TINYINT NOT NULL DEFAULT 0
);

CREATE TABLE `location` (
    `id` INT(20) NOT NULL PRIMARY KEY,
    `name` CHAR(30) NOT NULL,
    `describe` TEXT
);

CREATE TABLE `device` (
    `id`            INT(20) NOT NULL PRIMARY KEY ,
    `name`          CHAR(30) NOT NULL ,
    `in_date`       DATE NOT NULL ,
    `location_id`   INT(20)
)
