/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.31-log : Database - hometoschool
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hometoschool` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hometoschool`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`) values (1,'123456','123456');

/*Table structure for table `homebehave` */

DROP TABLE IF EXISTS `homebehave`;

CREATE TABLE `homebehave` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `studentid` varchar(255) NOT NULL COMMENT '学生id',
  `taskname` varchar(255) NOT NULL COMMENT '任务名称',
  `taskcontent` varchar(255) NOT NULL COMMENT '任务内容',
  `taskbehave` varchar(255) NOT NULL COMMENT '任务表现',
  `TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `img` varchar(255) DEFAULT NULL COMMENT '图片',
  `username` varchar(255) NOT NULL COMMENT '家长账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `homebehave` */


/*Table structure for table `homework` */

DROP TABLE IF EXISTS `homework`;

CREATE TABLE `homework` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `teacherid` varchar(255) NOT NULL COMMENT '教师id',
  `taskname` varchar(255) NOT NULL COMMENT '任务名称',
  `taskcontent` varchar(255) NOT NULL COMMENT '任务内容',
  `img` varchar(255) DEFAULT NULL COMMENT '任务附件',
  `taskimg` varchar(255) DEFAULT NULL COMMENT '需要在线播放任务文件地址',
  `tasktype` int(1) NOT NULL COMMENT '0表示普通作业，1表示手写作业，2表示音频作业，3表示视频作业',
  `TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `homework` */

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `notice_title` varchar(255) NOT NULL COMMENT '公告名字',
  `notice_content` varchar(255) NOT NULL COMMENT '公告内容',
  `teacherid` varchar(255) NOT NULL COMMENT '老师id',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

insert  into `notice`(`id`,`notice_title`,`notice_content`,`teacherid`,`time`) values (8,'下学期学费通知','请在9.1号在教务系统完成新学期缴费','111111','2021-07-11 13:25:42');

/*Table structure for table `parents` */

DROP TABLE IF EXISTS `parents`;

CREATE TABLE `parents` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '家长id',
  `name` varchar(255) NOT NULL COMMENT '家长姓名',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `age` int(3) NOT NULL COMMENT '年龄',
  `phone_number` varchar(11) NOT NULL COMMENT '手机号',
  `address` varchar(255) NOT NULL COMMENT '住址',
  `img` varchar(255) DEFAULT NULL COMMENT '照片',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `parents` */


/*Table structure for table `relationship` */

DROP TABLE IF EXISTS `relationship`;

CREATE TABLE `relationship` (
  `id` int(3) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `studentid` varchar(255) NOT NULL COMMENT '学生id',
  `parentid` varchar(255) NOT NULL COMMENT '父母id',
  `relation` varchar(255) NOT NULL COMMENT '亲情关系',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `relationship` */


/*Table structure for table `schoolbehave` */

DROP TABLE IF EXISTS `schoolbehave`;

CREATE TABLE `schoolbehave` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `studentid` varchar(255) NOT NULL COMMENT '学生id',
  `taskname` varchar(255) NOT NULL COMMENT '任务名称',
  `taskcontent` varchar(255) NOT NULL COMMENT '任务内容',
  `taskbehave` varchar(255) NOT NULL COMMENT '任务表现',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `img` varchar(255) DEFAULT NULL COMMENT '图片',
  `teacherid` varchar(255) NOT NULL COMMENT '老师工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `schoolbehave` */


/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `studentid` varchar(255) NOT NULL COMMENT '学生学号',
  `NAME` varchar(255) NOT NULL COMMENT '学生姓名',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `age` int(3) NOT NULL COMMENT '年龄',
  `grade` varchar(10) NOT NULL COMMENT '年纪',
  `phone_number` varchar(11) NOT NULL COMMENT '手机号',
  `address` varchar(255) NOT NULL COMMENT '住址',
  `img` varchar(255) DEFAULT NULL COMMENT '照片',
  `teacherid` varchar(255) NOT NULL COMMENT '班主任工号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentid` (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `students` */


/*Table structure for table `teachers` */

DROP TABLE IF EXISTS `teachers`;

CREATE TABLE `teachers` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '老师id',
  `teacherid` varchar(255) NOT NULL COMMENT '老师工号',
  `NAME` varchar(255) NOT NULL COMMENT '老师姓名',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `age` int(3) NOT NULL COMMENT '年龄',
  `phone_number` varchar(11) NOT NULL COMMENT '手机号',
  `address` varchar(255) NOT NULL COMMENT '住址',
  `img` varchar(255) DEFAULT NULL COMMENT '照片',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacherid` (`teacherid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `teachers` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
