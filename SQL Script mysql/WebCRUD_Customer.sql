CREATE DATABASE  IF NOT EXISTS `web_customer_tracker`;
USE `web_customer_tracker`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


INSERT INTO `customer` VALUES 
	(1,'Mohamed','Essam','essam@yahoo.com'),
	(2,'Ahmed','Hassan','ahassan@gmail.com'),
	(3,'Sayed','Mohamed','samohamed@gmail.com'),
	(4,'Ayman','Sayed','asayed@gmail.com');