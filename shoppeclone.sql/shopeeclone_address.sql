-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: shopeeclone
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `addressId` bigint NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` datetime(6) DEFAULT NULL,
  `modifierBy` varchar(255) DEFAULT NULL,
  `modifierDate` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `district_id` bigint DEFAULT NULL,
  `province_id` bigint DEFAULT NULL,
  `userId` bigint DEFAULT NULL,
  `ward_id` bigint DEFAULT NULL,
  PRIMARY KEY (`addressId`),
  KEY `FKq9uo60rtdod4kgwhsbuf31nb2` (`district_id`),
  KEY `FK3jmvohgy18ctlwbo2dj9uqefl` (`province_id`),
  KEY `FK9nd6mcqqt1y4jhxxj4wuuebq7` (`userId`),
  KEY `FKne7hrmbcv57v0r965ct6lqpb6` (`ward_id`),
  CONSTRAINT `FK3jmvohgy18ctlwbo2dj9uqefl` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`provinceId`),
  CONSTRAINT `FK9nd6mcqqt1y4jhxxj4wuuebq7` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `FKne7hrmbcv57v0r965ct6lqpb6` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`wardId`),
  CONSTRAINT `FKq9uo60rtdod4kgwhsbuf31nb2` FOREIGN KEY (`district_id`) REFERENCES `districts` (`districtId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (2,'admin','2023-08-30 00:10:05.219000','admin','2023-08-30 00:10:05.219000','thap thuong',1,1,NULL,1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-25 12:20:44
