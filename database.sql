/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.15-log : Database - frame
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`frame` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `frame`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `CardID` varchar(20) NOT NULL COMMENT '卡号',
  `AccountBalance` varchar(12) NOT NULL COMMENT '账户余额，小数两位',
  `Identify` varchar(18) NOT NULL COMMENT '身份证号',
  `Password` varchar(10) NOT NULL COMMENT '密码',
  `Type` varchar(10) DEFAULT NULL COMMENT '类型',
  `CreditLimit` varchar(20) NOT NULL COMMENT '透支额度',
  `effectiveDate` varchar(8) NOT NULL COMMENT '有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`id`,`CardID`,`AccountBalance`,`Identify`,`Password`,`Type`,`CreditLimit`,`effectiveDate`) values (1,'111111111111111','9800000','350182199712102912','123456','0','0','5'),(3,'111111111111112','0','350182199712102913','11','借记卡','0','0'),(4,'111111111111113','0','350182199712102916','11','借记卡','0','0'),(5,'111111111111114','0','350182199712102914','11','借记卡','0','0'),(6,'111111111111115','0','350182199712107777','11','借记卡','0','0'),(7,'admin','10000100000','admin','123456','借记卡','0','5'),(8,'111111111111116','19999030000','450332200007150923','000715','借记卡','0','0');

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `CardID` varchar(20) NOT NULL,
  `AffairType` varchar(2) NOT NULL COMMENT '事务类型0存款 1取款 3转入 4转出',
  `TradeTime` varchar(20) NOT NULL COMMENT '交易时间',
  `TradeBalance` varchar(12) NOT NULL COMMENT '交易金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `bill` */

insert  into `bill`(`id`,`CardID`,`AffairType`,`TradeTime`,`TradeBalance`) values (1,'admin','0','2019-06-23 11:29','10000'),(2,'admin','1','2019-06-23 11:46','10000'),(3,'admin','1','2019-06-23 11:48','10000'),(4,'111111111111116','1','2019-06-23 14:11','1000000'),(5,'admin','3','2019-06-23 15:44','10000'),(6,'111111111111116','4','2019-06-23 15:44','10000'),(7,'admin','3','2019-06-23 15:45','10000'),(8,'111111111111116','4','2019-06-23 15:45','10000'),(9,'admin','0','2019-06-24 14:37','10000'),(10,'admin','0','2019-06-24 14:37','10000000000'),(11,'111111111111111','0','2019-07-01 13:51','10000'),(12,'111111111111111','1','2019-07-01 15:31','10000'),(13,'111111111111111','0','2019-07-01 15:35','10000000'),(14,'111111111111111','1','2019-07-01 15:35','200000');

/*Table structure for table `exchange_interest_rate` */

DROP TABLE IF EXISTS `exchange_interest_rate`;

CREATE TABLE `exchange_interest_rate` (
  `id` bigint(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `parent_code` varchar(10) DEFAULT NULL,
  `CN_name` varchar(10) DEFAULT NULL,
  `EN_name` varchar(50) DEFAULT NULL,
  `rate` double(18,4) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `exchange_interest_rate` */

insert  into `exchange_interest_rate`(`id`,`code`,`parent_code`,`CN_name`,`EN_name`,`rate`) values (2,'10001','10000','美元','dollar',6.8520),(3,'10002','10000','日元','Yen',0.0632),(4,'10003','10000','英镑','Pound',8.6637),(5,'10004','10000','港元','Hong Kong dollar',0.8769),(6,'10005','10000','加元','Cad',5.2161),(7,'10006','10000','欧元','Euro',7.7345),(8,'10007','10000','韩元','South Korean won',0.0059),(9,'10008','10000','澳元','Australian dollar',4.7739);

/*Table structure for table `teach_admin` */

DROP TABLE IF EXISTS `teach_admin`;

CREATE TABLE `teach_admin` (
  `id` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL,
  `password` char(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `account` (`account`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员表';

/*Data for the table `teach_admin` */

insert  into `teach_admin`(`id`,`account`,`password`,`name`,`role`) values (2,'admin','e10adc3949ba59abbe56e057f20f883e','数据结构课组','32'),(22,'1','e10adc3949ba59abbe56e057f20f883e','1','30');

/*Table structure for table `teach_back_auth` */

DROP TABLE IF EXISTS `teach_back_auth`;

CREATE TABLE `teach_back_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(32) COLLATE utf8_bin NOT NULL,
  `auth_name` varchar(32) COLLATE utf8_bin NOT NULL,
  `auth_url` varchar(32) COLLATE utf8_bin NOT NULL,
  `auth_ico` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `auth_parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `teach_back_auth` */

insert  into `teach_back_auth`(`id`,`auth_code`,`auth_name`,`auth_url`,`auth_ico`,`auth_parent`) values (30,'1000','系统管理','#','fa fa-cogs',NULL),(35,'10001','系统用户管理','/userMn/','fa fa-user',30),(36,'10002','系统角色管理','/roleMn/','fa fa-black-tie',30),(37,'10003','系统权限管理','/authMn/','fa fa-gavel',30),(50,'2000','存取款','#','fa fa-credit-card',NULL),(51,'2001','存款','/depositMn/','fa fa-cloud-upload',50),(52,'2002','取款','/withdrawMn/','fa fa-cloud-download',50),(53,'3000','转账余额','#','fa fa-bank',NULL),(54,'3001','查询余额','/balanceMn/','fa fa-calculator',53),(55,'3002','转账','/transferMn/','fa fa-truck',53),(56,'4000','其他业务','#','fa fa-database',NULL),(58,'4002','咨询业务','/consultationMn/','fa fa-question',56),(59,'5000','兑换货币','#','fa fa-balance-scale',NULL),(60,'5001','兑换人民币','/rmbMn/','fa fa-yen',59),(61,'5002','兑换其他货币','/exchangeMn/','fa fa-dollar',59),(62,'3003','账单明细','/billMn/','fa fa-yen',53);

/*Table structure for table `teach_back_role` */

DROP TABLE IF EXISTS `teach_back_role`;

CREATE TABLE `teach_back_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_code` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_desc` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `teach_back_role` */

insert  into `teach_back_role`(`id`,`role_name`,`role_code`,`role_desc`) values (30,'系统管理员','1001','后台系统管理员角色'),(31,'教师','1002','后台教师角色'),(32,'超级管理员','1003','拥有全部权限');

/*Table structure for table `teach_back_role_auth` */

DROP TABLE IF EXISTS `teach_back_role_auth`;

CREATE TABLE `teach_back_role_auth` (
  `roleId` int(32) DEFAULT NULL COMMENT '角色ID',
  `authId` int(32) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `teach_back_role_auth` */

insert  into `teach_back_role_auth`(`roleId`,`authId`) values (32,30),(32,35),(32,36),(32,37),(32,50),(32,51),(32,52),(32,53),(32,54),(32,55),(32,56),(32,58),(32,59),(32,60),(32,61),(32,62),(30,50),(30,51),(30,52),(30,53),(30,54),(30,55),(30,56),(30,58),(30,59),(30,60),(30,61),(30,62);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) NOT NULL COMMENT '姓名',
  `Identify` varchar(18) NOT NULL COMMENT '身份证号',
  `TelNum` varchar(11) NOT NULL COMMENT '电话',
  `Address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`Username`,`Identify`,`TelNum`,`Address`) values (1,'潘祈航','350182199712102912','18084257848',NULL),(4,'1','350182199712102913','18084257848',''),(5,'1','350182199712102916','18084257848',''),(6,'2','350182199712102914','18084257848',''),(7,'7','350182199712107777','18084257848',''),(8,'admin','admin','12345678912',NULL),(10,'常妍','450332200007150923','13147853132','广西省桂林市七星区穿山东路1号七星花园三单元七栋201');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
