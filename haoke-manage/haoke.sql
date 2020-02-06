/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 8.0.16 : Database - haoke
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`haoke` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `haoke`;

/*Table structure for table `tb_estate` */

DROP TABLE IF EXISTS `tb_estate`;

CREATE TABLE `tb_estate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '楼盘名称',
  `province` varchar(10) DEFAULT NULL COMMENT '所在省',
  `city` varchar(10) DEFAULT NULL COMMENT '所在市',
  `area` varchar(10) DEFAULT NULL COMMENT '所在区',
  `address` varchar(100) DEFAULT NULL COMMENT '具体地址',
  `year` varchar(10) DEFAULT NULL COMMENT '建筑年代',
  `type` varchar(10) DEFAULT NULL COMMENT '建筑类型',
  `property_cost` varchar(10) DEFAULT NULL COMMENT '物业费',
  `property_company` varchar(20) DEFAULT NULL COMMENT '物业公司',
  `developers` varchar(20) DEFAULT NULL COMMENT '开发商',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8 COMMENT='楼盘表';

/*Data for the table `tb_estate` */

insert  into `tb_estate`(`id`,`name`,`province`,`city`,`area`,`address`,`year`,`type`,`property_cost`,`property_company`,`developers`,`created`,`updated`) values
(1001,'中远两湾城','上海市','上海市','普陀区','远景路97弄','2001','塔楼/板楼','1.5','上海中远物业管理发展有限公司','上海万业企业股份有限公司','2018-11-06 23:00:20','2018-11-06 23:00:23'),
(1002,'上海康城','上海市','上海市','闵行区','莘松路958弄','2001','塔楼/板楼','1.5','盛孚物业','闵行房地产','2018-11-06 23:02:30','2018-11-27 23:02:33'),
(1003,'保利西子湾','上海市','上海市','松江区','广富林路1188弄','2008','塔楼/板楼','1.75','上海保利物业管理','上海城乾房地产开发有限公司','2018-11-06 23:04:22','2018-11-06 23:04:25'),
(1004,'万科城市花园','上海市','上海市','松江区','广富林路1188弄','2002','塔楼/板楼','1.5','上海保利物业管理','上海城乾房地产开发有限公司','2018-11-13 16:43:40','2018-11-13 16:43:42'),
(1005,'上海阳城','上海市','上海市','闵行区','罗锦路888弄','2002','塔楼/板楼','1.5','上海莲阳物业管理有限公司','上海莲城房地产开发有限公司','2018-11-06 23:23:52','2018-11-06 23:23:55');

/*Table structure for table `tb_house_resources` */

DROP TABLE IF EXISTS `tb_house_resources`;

CREATE TABLE `tb_house_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '房源标题',
  `estate_id` bigint(20) DEFAULT NULL COMMENT '楼盘id',
  `building_num` varchar(5) DEFAULT NULL COMMENT '楼号（栋）',
  `building_unit` varchar(5) DEFAULT NULL COMMENT '单元号',
  `building_floor_num` varchar(5) DEFAULT NULL COMMENT '门牌号',
  `rent` int(10) DEFAULT NULL COMMENT '租金',
  `rent_method` tinyint(1) DEFAULT NULL COMMENT '租赁方式，1-整租，2-合租',
  `payment_method` tinyint(1) DEFAULT NULL COMMENT '支付方式，1-付一押一，2-付三押一，3-付六押一，4-年付押一，5-其它',
  `house_type` varchar(255) DEFAULT NULL COMMENT '户型，如：2室1厅1卫',
  `covered_area` varchar(10) DEFAULT NULL COMMENT '建筑面积',
  `use_area` varchar(10) DEFAULT NULL COMMENT '使用面积',
  `floor` varchar(10) DEFAULT NULL COMMENT '楼层，如：8/26',
  `orientation` varchar(2) DEFAULT NULL COMMENT '朝向：东、南、西、北',
  `decoration` tinyint(1) DEFAULT NULL COMMENT '装修，1-精装，2-简装，3-毛坯',
  `facilities` varchar(50) DEFAULT NULL COMMENT '配套设施， 如：1,2,3',
  `pic` varchar(200) DEFAULT NULL COMMENT '图片，最多5张',
  `house_desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `contact` varchar(10) DEFAULT NULL COMMENT '联系人',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `time` tinyint(1) DEFAULT NULL COMMENT '看房时间，1-上午，2-中午，3-下午，4-晚上，5-全天',
  `property_cost` varchar(10) DEFAULT NULL COMMENT '物业费',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='房源表';

/*Data for the table `tb_house_resources` */

insert  into `tb_house_resources`(`id`,`title`,`estate_id`,`building_num`,`building_unit`,`building_floor_num`,`rent`,`rent_method`,`payment_method`,`house_type`,`covered_area`,`use_area`,`floor`,`orientation`,`decoration`,`facilities`,`pic`,`house_desc`,`contact`,`mobile`,`time`,`property_cost`,`created`,`updated`) values
(1,'东方曼哈顿 3室2厅 16000 元',1005,'2','1','1',1111,1,NULL,' 1室1厅1卫1厨1阳台','2','2','1/2','南',NULL,'1,2,3,8,9',NULL,'这个经纪人很懒，没写核心卖 点','张 三','11111111111',1,'11','2019-06-13 19:08:38','2019-06-13 19:08:38'),
(7,'华通晟云科技有限公司',1005,'10','2','404',1200,1,1,'1室2厅5卫6厨2阳台','98','78','12/24','南',1,'1,2,3',NULL,'棒棒哒','codewj','15806204096',1,'12','2019-06-14 09:07:01','2019-06-14 09:07:01'),
(8,'上海市,上海市,闵行区,七莘路3333弄2区-15区',1004,'10','2','123',1200,1,2,'1室2厅1卫1厨2阳台','98','123','123/145','南',1,'1,2,3,7','http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/14/15604886265708432.jpg,http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/14/15604886333049205.jpg','good ','codewj','15806204096',2,'123','2019-06-14 13:04:01','2019-06-14 13:04:01');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`age`,`email`) values
(1,'Jone',18,'test1@baomidou.com'),
(2,'Jack',20,'test2@baomidou.com'),
(3,'Tom',28,'test3@baomidou.com'),
(4,'Sandy',21,'test4@baomidou.com'),
(5,'Billie',24,'test5@baomidou.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


USE `haoke`;

/*Table structure for table `tb_ad` */

DROP TABLE IF EXISTS `tb_ad`;

CREATE TABLE `tb_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(10) DEFAULT NULL COMMENT '广告类型',
  `title` varchar(100) DEFAULT NULL COMMENT '描述',
  `url` varchar(200) DEFAULT NULL COMMENT '图片URL地址',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='广告表';

/*Data for the table `tb_ad` */

insert  into `tb_ad`(`id`,`type`,`title`,`url`,`created`,`updated`) values
(1,1,'UniCity万科天空之城','http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/21/1561085662709476.jpg','2018-11-26 11:28:49','2018-11-26 11:28:51'),
(2,1,'天和尚海庭前','http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/21/1561085716927197.jpg','2018-11-26 11:29:27','2018-11-26 11:29:29'),
(3,1,'[奉贤 南桥] 光语著','http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/21/15610857423871578.jpg','2018-11-26 11:30:04','2018-11-26 11:30:06'),
(4,1,'[上海周边 嘉兴] 融创海逸长洲','http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/21/15610857626646530.jpg','2018-11-26 11:30:49','2018-11-26 11:30:53');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
