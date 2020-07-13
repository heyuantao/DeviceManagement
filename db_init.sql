CREATE DATABASE devicemanagement ;

USE devicemanagement;

#系统用户表
CREATE TABLE `user` (
                        `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
                        `name`       CHAR(30) NOT NULL COMMENT '用户姓名',
                        `password`    CHAR(30) NOT NULL COMMENT '用户密码',
                        `email`       CHAR(30)  COMMENT '用户邮箱',
                        `superuser`   TINYINT NOT NULL DEFAULT 0 COMMENT '是否为管理员'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO `user` (name, password, email, superuser) values ('admin','19831122','admin@example.com',1);
INSERT INTO `user` (name, password, email, superuser) values ('abc','123','abc@example.com',0);
INSERT INTO `user` (name, password, email, superuser) values ('efg','123','efg@example.com',0);
INSERT INTO `user` (name, password, email, superuser) values ('hij','123','hij@example.com',0);

#位置信息表 设置为设备存放的位置
CREATE TABLE `location` (
                            `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '位置ID',
                            `name`        CHAR(30) NOT NULL COMMENT '位置名称',
                            `description`    TEXT COMMENT '位置描述'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO `location` (name, description) values ('08A501','实验中心准备室');
INSERT INTO `location` (name, description) values ('08A502','计算机组成原理实验室');

#设备的类型
CREATE TABLE `type` (
                        `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '类型ID',
                        `name`        CHAR(30) NOT NULL COMMENT '设备类型名称',
                        `description`    TEXT COMMENT '设备类型描述'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;


INSERT INTO `type` (name, description) values ('微型计算机','台式电脑');
INSERT INTO `type` (name, description) values ('无人机','微型遥控无人机');

#设备保管人
CREATE TABLE `owner` (
                         `id`            INT PRIMARY KEY AUTO_INCREMENT COMMENT '保管人ID',
                         `name`          CHAR(30) NOT NULL COMMENT '保管人名称',
                         `department`    CHAR(30) NOT NULL COMMENT '保管人所在部门',
                         `description`   TEXT COMMENT '保管人描述'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;


INSERT INTO `owner` (name, department,description) values ('张三','计算机科学系','');
INSERT INTO `owner` (name, department,description) values ('李四','网络工程系','');

#具体的设备
CREATE TABLE device (
                        `id`            INT PRIMARY KEY AUTO_INCREMENT COMMENT '设备ID',
                        `name`          CHAR(30) NOT NULL COMMENT '设备名称',
                        `vendor`        CHAR(30) NOT NULL COMMENT '设备生成厂商',
                        `sn`            CHAR(30) NOT NULL COMMENT '设备序列号',
                        `asset_no`      CHAR(30) NOT NULL COMMENT '固定资产编号',
                        `in_date`       DATE NOT NULL COMMENT '设备购买时间',
                        `updated`       DATE NOT NULL COMMENT '记录更新时间',
                        `location_id`   INT COMMENT '设备的位置外键ID',
                        `type_id`       INT COMMENT '设备的类型外键ID',
                        `owner_id`      INT COMMENT '设备的保管人外键ID',

                        FOREIGN KEY (location_id)   REFERENCES location(id),
                        FOREIGN KEY (type_id)       REFERENCES type(id),
                        FOREIGN KEY (owner_id)      REFERENCES owner(id)
    #CONSTRAINT location_fk  FOREIGN KEY (location_id)   REFERENCES location(id),
    #CONSTRAINT type_fk      FOREIGN KEY (type_id)       REFERENCES type(id),
    #CONSTRAINT owner_fk     FOREIGN KEY (owner_id)       REFERENCES owner(id)
    #ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO `device` (`name`,`vendor`,`sn`,`asset_no`,`in_date`,`updated`,`location_id`,`type_id`,`owner_id`)
values ('Inspire 2','大疆','324324234234234','zua223232323','2019-11-23','2020-03-23',1,2,1);
INSERT INTO `device` (`name`,`vendor`,`sn`,`asset_no`,`in_date`,`updated`,`location_id`,`type_id`,`owner_id`)
values ('X3850 X5','联想','232324234234234','zua223232323','2019-11-23','2020-03-23',1,1,1);