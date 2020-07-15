USE devicemanagement;

#系统用户数据
INSERT INTO `user` (name, password, email, superuser) values ('admin','19831122','admin@example.com',1);
INSERT INTO `user` (name, password, email, superuser) values ('abc','123','abc@example.com',0);
INSERT INTO `user` (name, password, email, superuser) values ('efg','123','efg@example.com',0);
INSERT INTO `user` (name, password, email, superuser) values ('hij','123','hij@example.com',0);

#位置信息数据
INSERT INTO `location` (name, description) values ('08A501','实验中心准备室');
INSERT INTO `location` (name, description) values ('08A502','计算机组成原理实验室');

#设备的类型数据
INSERT INTO `type` (name, description) values ('无人机','微型遥控无人机');
INSERT INTO `type` (name, description) values ('微型计算机','台式电脑');
INSERT INTO `type` (name, description) values ('服务器','X86服务器');

#设备保管人数据
INSERT INTO `owner` (name, department,description) values ('张三','计算机科学系','');
INSERT INTO `owner` (name, department,description) values ('李四','网络工程系','');

#具体的设备数据
INSERT INTO `device` (`name`,`vendor`,`sn`,`asset_no`,`in_date`,`updated`,`location_id`,`type_id`,`owner_id`)
values ('Inspire 2','大疆','324324234234234','zua223232323','2019-11-23','2020-03-23',1,1,1);
INSERT INTO `device` (`name`,`vendor`,`sn`,`asset_no`,`in_date`,`updated`,`location_id`,`type_id`,`owner_id`)
values ('X3850 X5','联想','232324234234234','zua223232323','2019-11-23','2020-03-23',1,2,1);
INSERT INTO `device` (`name`,`vendor`,`sn`,`asset_no`,`in_date`,`updated`,`location_id`,`type_id`,`owner_id`)
values ('HP 380G','惠普','2323242342342x','zua223232323','2019-11-23','2020-03-23',1,3,1);