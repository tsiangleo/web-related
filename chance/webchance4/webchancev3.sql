/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.1.73-community : Database - webchance3
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webchance3` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `webchance3`;

/*Table structure for table `c_admin` */

DROP TABLE IF EXISTS `c_admin`;

CREATE TABLE `c_admin` (
  `C_Admin_ID` int(11) NOT NULL AUTO_INCREMENT,
  `C_Admin_LoginName` varchar(255) NOT NULL,
  `C_Admin_Pwd` varchar(255) NOT NULL,
  `C_Admin_Name` varchar(255) DEFAULT NULL,
  `C_Admin_Level` int(11) DEFAULT NULL,
  `C_Admin_IsLocked` tinyint(1) DEFAULT '0',
  `C_Admin_CreateTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`C_Admin_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `c_feedback` */

DROP TABLE IF EXISTS `c_feedback`;

CREATE TABLE `c_feedback` (
  `C_FeedBack_ID` int(11) NOT NULL AUTO_INCREMENT,
  `C_FeedBack_UserID` int(11) NOT NULL,
  `C_FeedBack_UserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_FeedBack_Content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_FeedBack_Time` bigint(20) NOT NULL,
  `C_FeedBack_CheckResult` int(11) DEFAULT NULL,
  `C_FeedBack_CheckAdminID` int(11) DEFAULT NULL,
  `C_FeedBack_CheckTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`C_FeedBack_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_reportbangbang` */

DROP TABLE IF EXISTS `c_reportbangbang`;

CREATE TABLE `c_reportbangbang` (
  `C_ReportBangBang_ID` int(11) NOT NULL AUTO_INCREMENT,
  `C_ReportBangBang_UserID` int(11) NOT NULL,
  `C_ReportBangBang_UserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportBangBang_ByUserID` int(11) DEFAULT NULL,
  `C_ReportBangBang_ByUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportBangBang_Time` bigint(20) NOT NULL,
  `C_ReportBangBang_Reason` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportBangBang_BangBangID` int(11) NOT NULL,
  `C_ReportBangBang_CheckResult` int(11) DEFAULT NULL,
  `C_ReportBangBang_CheckAdminID` int(11) DEFAULT NULL,
  `C_ReportBangBang_CheckTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`C_ReportBangBang_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_reportchatmsg` */

DROP TABLE IF EXISTS `c_reportchatmsg`;

CREATE TABLE `c_reportchatmsg` (
  `C_ReportChatMsg_ID` int(11) NOT NULL AUTO_INCREMENT,
  `C_ReportChatMsg_ReportUserID` int(11) DEFAULT NULL,
  `C_ReportChatMsg_SendCID` int(11) DEFAULT NULL,
  `C_ReportChatMsg_ReceiveCID` int(11) DEFAULT NULL,
  `C_ReportChatMsg_MsgContent` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportChatMsg_Type` int(11) DEFAULT NULL,
  `C_ReportChatMsg_SendTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`C_ReportChatMsg_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_reportdiary` */

DROP TABLE IF EXISTS `c_reportdiary`;

CREATE TABLE `c_reportdiary` (
  `C_ReportDiary_ID` int(11) NOT NULL AUTO_INCREMENT,
  `C_ReportDiary_UserID` int(11) NOT NULL,
  `C_ReportDiary_UserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportDiary_ByUserID` int(11) DEFAULT NULL,
  `C_ReportDiary_ByUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportDiary_Time` bigint(20) NOT NULL,
  `C_ReportDiary_DiaryID` int(11) NOT NULL,
  `C_ReportDiary_CheckResult` int(11) DEFAULT NULL,
  `C_ReportDiary_CheckAdminID` int(11) DEFAULT NULL,
  `C_ReportDiary_CheckTime` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`C_ReportDiary_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_reportuser` */

DROP TABLE IF EXISTS `c_reportuser`;

CREATE TABLE `c_reportuser` (
  `C_ReportUser_ID` int(11) NOT NULL AUTO_INCREMENT,
  `C_ReportUser_UserID` int(11) NOT NULL,
  `C_ReportUser_UserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportUser_ByUserID` int(11) NOT NULL,
  `C_ReportUser_ByUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportUser_Time` bigint(20) DEFAULT NULL,
  `C_ReportUser_Type` int(11) DEFAULT NULL,
  `C_ReportUser_Reason` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `C_ReportUser_CheckResult` int(11) DEFAULT '0',
  `C_ReportUser_CheckAdminID` int(11) DEFAULT '0',
  `C_ReportUser_CheckTime` bigint(20) DEFAULT '0',
  PRIMARY KEY (`C_ReportUser_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
