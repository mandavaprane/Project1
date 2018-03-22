/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hoteldb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hoteldb`;

/*Table structure for table `bookingoption` */

DROP TABLE IF EXISTS `bookingoption`;

CREATE TABLE `bookingoption` (
  `optionRequestId` bigint(20) NOT NULL AUTO_INCREMENT,
  `acFacilityCost` int(11) DEFAULT NULL,
  `curtainCost` varchar(255) DEFAULT NULL,
  `flowerCost` int(11) DEFAULT NULL,
  `flowerDecoration` char(1) DEFAULT NULL,
  `needAcFacility` char(1) DEFAULT NULL,
  `needCurtains` char(1) DEFAULT NULL,
  `needToChangeWallColor` char(1) DEFAULT NULL,
  `roomType` varchar(255) DEFAULT NULL,
  `roomTypeCost` int(11) DEFAULT NULL,
  `wallColorCost` int(11) DEFAULT NULL,
  `request_id` bigint(20) NOT NULL,
  PRIMARY KEY (`optionRequestId`),
  KEY `FKC64E460E1EE0C1EA` (`request_id`),
  CONSTRAINT `FKC64E460E1EE0C1EA` FOREIGN KEY (`request_id`) REFERENCES `hotelrequest` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `bookingoption` */

insert  into `bookingoption`(`optionRequestId`,`acFacilityCost`,`curtainCost`,`flowerCost`,`flowerDecoration`,`needAcFacility`,`needCurtains`,`needToChangeWallColor`,`roomType`,`roomTypeCost`,`wallColorCost`,`request_id`) values (1,200,'0',100,'Y','Y','N','N','singleroom',500,0,1),(2,200,'0',100,'Y','Y','N','N','singleroom',500,0,2),(3,200,'0',100,'Y','Y','N','N','singleroom',500,0,2),(5,200,'0',100,'Y','Y','N','N','singleroom ',500,0,2),(6,200,'0',0,'N','Y','N','N','singleroom',500,0,6),(7,200,'0',0,'N','Y','N','N','singleroom',500,0,7);

/*Table structure for table `cusers` */

DROP TABLE IF EXISTS `cusers`;

CREATE TABLE `cusers` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roletype` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `cusers` */

insert  into `cusers`(`user_id`,`email`,`firstname`,`lastLogin`,`lastname`,`password`,`roletype`,`username`) values (1,'admin@hotelmanager.com','admin','2018-03-15 15:22:45','admin','admin@123','administrator','admin'),(2,'praneetha.mandava@gmail.com','Praneetha','2018-03-15 12:07:04','Mandava','password','administrator','praneetha'),(3,'sv.arun548@gmail.com','Arun','2018-03-15 15:25:51','Sonti','arun@123','bookingclerk','sonti_arun'),(4,'almchandaa@gmail.com','chandana','2018-03-15 12:08:23','A','chandana','bookingclerk','chandana');

/*Table structure for table `hotelrequest` */

DROP TABLE IF EXISTS `hotelrequest`;

CREATE TABLE `hotelrequest` (
  `request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NoOfRoom` varchar(255) DEFAULT NULL,
  `bookingDate` datetime DEFAULT NULL,
  `bookingRate` varchar(255) DEFAULT NULL,
  `custumerAddress` varchar(255) DEFAULT NULL,
  `foodType` varchar(255) DEFAULT NULL,
  `mobileNo` varchar(255) DEFAULT NULL,
  `postingDate` datetime DEFAULT NULL,
  `requesterEmail` varchar(255) DEFAULT NULL,
  `requesterName` varchar(255) DEFAULT NULL,
  `requestingTaxiService` varchar(255) DEFAULT NULL,
  `roomPool_id` bigint(20) NOT NULL,
  PRIMARY KEY (`request_id`),
  KEY `FK60ED8A5BF1617B5E` (`roomPool_id`),
  CONSTRAINT `FK60ED8A5BF1617B5E` FOREIGN KEY (`roomPool_id`) REFERENCES `roompool` (`roomPool_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `hotelrequest` */

insert  into `hotelrequest`(`request_id`,`NoOfRoom`,`bookingDate`,`bookingRate`,`custumerAddress`,`foodType`,`mobileNo`,`postingDate`,`requesterEmail`,`requesterName`,`requestingTaxiService`,`roomPool_id`) values (1,'1','2018-03-19 09:30:00','800','columbus','veg','7068885715','2018-03-13 05:29:22','praneetha@gmail.com','praneetha','N',1),(2,'2','2018-04-05 11:00:00','1300','columbus','nonveg','7068885720','2018-03-13 07:34:55','arun@gmail.com','arun','N',1),(4,'1','2018-03-31 00:00:00','800.0','2840 warm springs road, apt r4\r\nAPT #R4','vegetarian',NULL,'2018-03-15 14:30:07','praneetha.mandava@gmail.com','Praneetha Mandava',NULL,1),(5,'9','2018-03-16 00:00:00','7200.0','columbus','vegetarian',NULL,'2018-03-15 14:30:20','chand@gmail.com','chandna',NULL,1),(6,'1','2018-04-01 00:00:00','700.0','2840 warm springs road, apt r4\r\nAPT #R4','vegetarian',NULL,'2018-03-15 15:17:59','adem@gmail.com','adem',NULL,2),(7,'2','2018-04-03 00:00:00','1400.0','columbus','vegetarian',NULL,'2018-03-15 15:20:20','lalith@gmail.com','lalith',NULL,2);

/*Table structure for table `roompool` */

DROP TABLE IF EXISTS `roompool`;

CREATE TABLE `roompool` (
  `roomPool_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acFacility` char(1) DEFAULT NULL,
  `curtainAvailable` char(1) DEFAULT NULL,
  `flowerDecorationReq` char(1) DEFAULT NULL,
  `roomBooked` varchar(255) DEFAULT NULL,
  `roomQuantity` int(11) DEFAULT NULL,
  `roomType` varchar(255) DEFAULT NULL,
  `roomVacant` varchar(255) DEFAULT NULL,
  `splWallColor` char(1) DEFAULT NULL,
  PRIMARY KEY (`roomPool_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `roompool` */

insert  into `roompool`(`roomPool_id`,`acFacility`,`curtainAvailable`,`flowerDecorationReq`,`roomBooked`,`roomQuantity`,`roomType`,`roomVacant`,`splWallColor`) values (1,'Y','N','Y','10',50,'singleroom','10','N'),(2,'Y','N','N','8',15,'singleroom','7','N'),(3,'Y','Y','Y','20',20,'singleroom','0','Y'),(4,'Y','N','N','5',25,'doubleroom','20','N'),(5,'Y','Y','Y','9',20,'doubleroom','11','Y');

/*Table structure for table `user_log_tb` */

DROP TABLE IF EXISTS `user_log_tb`;

CREATE TABLE `user_log_tb` (
  `logId` bigint(20) NOT NULL AUTO_INCREMENT,
  `activityStatus` varchar(255) DEFAULT NULL,
  `activityTimeStamp` datetime DEFAULT NULL,
  `activityType` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`logId`),
  KEY `FKEAD1203D1F948778` (`user_id`),
  CONSTRAINT `FKEAD1203D1F948778` FOREIGN KEY (`user_id`) REFERENCES `cusers` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `user_log_tb` */

insert  into `user_log_tb`(`logId`,`activityStatus`,`activityTimeStamp`,`activityType`,`user_id`) values (1,'Success','2018-03-15 12:01:58','Login',1),(2,'Success','2018-03-15 12:02:42','Logout',1),(3,'Failed','2018-03-15 12:02:50','Login',1),(4,'Success','2018-03-15 12:06:36','Login',1),(5,'Success','2018-03-15 12:08:49','Logout',1),(6,'Success','2018-03-15 12:19:56','Login',1),(7,'Success','2018-03-15 12:20:15','Logout',1),(8,'Success','2018-03-15 14:08:09','Login',1),(9,'Success','2018-03-15 14:10:26','Login',1),(10,'Success','2018-03-15 14:14:36','Login',1),(11,'Success','2018-03-15 14:16:34','Logout',1),(12,'Success','2018-03-15 14:30:05','Login',1),(13,'Success','2018-03-15 15:17:53','Login',1),(14,'Success','2018-03-15 15:22:45','Logout',1),(15,'Success','2018-03-15 15:25:47','Login',1),(16,'Success','2018-03-15 15:25:51','Logout',1);

/* Function  structure for function  `checkValidUser` */

/*!50003 DROP FUNCTION IF EXISTS `checkValidUser` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `checkValidUser`(username varchar(200),passwd VARCHAR(200)) RETURNS varchar(200) CHARSET utf8
BEGIN
    
    declare userexist  varchar(200);
    
    select username into userexist from cusers c where c.username= username and c.password=passwd; 
	if userexist is null then
	   return 'Unauthorized User.';
	else
	   return 'Authorized User';	 
	end if;
    
    END */$$
DELIMITER ;

/* Procedure structure for procedure `GET_ROOM_TYPE_AVAILABILITY` */

/*!50003 DROP PROCEDURE IF EXISTS  `GET_ROOM_TYPE_AVAILABILITY` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_ROOM_TYPE_AVAILABILITY`(IN whtroomtype varchar(50), IN roomquantity INT, IN needFlowerDecoration CHAR,
     IN needSpecialCurtain CHAR, IN needSpecialWallColor CHAR , IN needACFacility CHAR)
BEGIN
    
    declare vacantRoom int;
      SELECT r.roomVacant into vacantRoom FROM roompool r
      where r.roomQuantity  >= roomquantity and r.roomType= whtroomtype and
    r.flowerDecorationReq=needFlowerDecoration and r.curtainAvailable=needSpecialCurtain and
     r.splWallColor=needSpecialWallColor and  r.acFacility=needACFacility;
     
	
     
    IF vacantRoom > roomquantity  THEN
    
    SELECT 'Room(s) is/are available for Booking' as response;
    
       
    elseif (vacantRoom is null) then
    
    SELECT 'Room(s) doesnt exist' AS response;
	ELSE
    
     SELECT 'all matching room(s) are booked' AS response;    
    
    
    END IF;
	
	
	
	
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
