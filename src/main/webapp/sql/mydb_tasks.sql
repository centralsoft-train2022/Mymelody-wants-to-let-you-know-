CREATE DATABASE  IF NOT EXISTS `mymelody` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mymelody`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: mymelody
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `taskid` int NOT NULL AUTO_INCREMENT,
  `taskname` varchar(45) NOT NULL,
  `taskbody` varchar(45) DEFAULT NULL,
  `completed` tinyint(1) NOT NULL,
  `kigen` datetime NOT NULL,
  `needmail` tinyint(1) NOT NULL,
  `mailtime` datetime DEFAULT NULL,
  `taskinterval` datetime DEFAULT NULL,
  `taskvisible` tinyint(1) NOT NULL,
  `users_userid` int NOT NULL,
  `pictures_pictureid` int NOT NULL,
  `regular` tinyint(1) NOT NULL,
  PRIMARY KEY (`taskid`),
  KEY `fk_tasks_users_idx` (`users_userid`),
  KEY `fk_tasks_pictures1_idx` (`pictures_pictureid`),
  CONSTRAINT `fk_tasks_pictures1` FOREIGN KEY (`pictures_pictureid`) REFERENCES `pictures` (`pictureid`),
  CONSTRAINT `fk_tasks_users` FOREIGN KEY (`users_userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,'洗剤を買う','A社の洗剤を買う',0,'2022-06-20 14:43:23',1,'2022-06-18 14:43:23','0000-01-00 00:00:00',1,1,1,1),(2,'シャンプーを買う','B社のシャンプーを買う',0,'2022-06-25 14:43:23',1,'2022-06-23 14:43:23','0000-01-00 00:00:00',1,2,2,1),(3,'課題終わらせる','終わらせて確認メールを送る',0,'2022-06-30 14:43:23',1,'2022-06-28 14:43:23',NULL,1,1,3,0);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-14 15:45:27
