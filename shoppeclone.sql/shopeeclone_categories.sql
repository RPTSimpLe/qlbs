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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `categoryId` bigint NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` datetime(6) DEFAULT NULL,
  `modifierBy` varchar(255) DEFAULT NULL,
  `modifierDate` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'admin','2023-08-28 18:59:27.050000','admin','2023-08-28 18:59:27.050000','33','ao'),(2,'admin','2023-11-05 02:10:36.294000','admin','2023-11-05 02:10:36.294000','asdasdsasd','Lê Thái Dương'),(3,'admin','2023-11-05 02:10:45.267000','admin','2023-11-05 02:10:45.267000','aasdadsad','asdadadasd'),(6,'admin','2023-11-05 02:10:59.637000','admin','2023-11-05 02:10:59.637000','asdasdasd','asdasdasd'),(7,'admin','2023-11-05 02:11:08.071000','admin','2023-11-05 02:11:08.071000','asdasasdda','asdasdasda'),(8,'admin','2023-11-05 02:11:17.437000','admin','2023-11-05 02:11:17.437000','asdasdasd','asdasdas');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
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
