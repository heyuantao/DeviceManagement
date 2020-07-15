CREATE DATABASE devicemanagement ;

USE devicemanagement;

#系统用户表
CREATE TABLE `user` (
                        `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
                        `name`        CHAR(30) NOT NULL UNIQUE COMMENT '用户姓名',
                        `password`    CHAR(30) NOT NULL COMMENT '用户密码',
                        `email`       CHAR(30) NOT NULL UNIQUE COMMENT '用户邮箱',
                        `superuser`   TINYINT NOT NULL DEFAULT 0 COMMENT '是否为管理员'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;


#位置信息表 设置为设备存放的位置
CREATE TABLE `location` (
                            `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '位置ID',
                            `name`        CHAR(30) NOT NULL COMMENT '位置名称',
                            `description`    TEXT COMMENT '位置描述'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;


#设备的类型
CREATE TABLE `type` (
                        `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '类型ID',
                        `name`        CHAR(30) NOT NULL COMMENT '设备类型名称',
                        `description`    TEXT COMMENT '设备类型描述'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;


#设备保管人
CREATE TABLE `owner` (
                         `id`            INT PRIMARY KEY AUTO_INCREMENT COMMENT '保管人ID',
                         `name`          CHAR(30) NOT NULL COMMENT '保管人名称',
                         `department`    CHAR(30) NOT NULL COMMENT '保管人所在部门',
                         `description`   TEXT COMMENT '保管人描述'
)ENGINE=INNODB DEFAULT CHARSET=UTF8;


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
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

#CONSTRAINT location_fk  FOREIGN KEY (location_id)   REFERENCES location(id),
#CONSTRAINT type_fk      FOREIGN KEY (type_id)       REFERENCES type(id),
#CONSTRAINT owner_fk     FOREIGN KEY (owner_id)       REFERENCES owner(id)
#ON DELETE CASCADE ON UPDATE CASCADE