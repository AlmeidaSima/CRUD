CREATE DATABASE  IF NOT EXISTS `beststore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `beststore`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: beststore
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text,
  `image_file_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'ASUS','Electrónica','2025-02-08 00:00:00.000000','Laptop potente para gaming','mj11.jpg','Laptop Gamer',1299.99),(2,'Samsung','Electrónica','2025-02-08 00:00:00.000000','Teléfono con gran capacidad y cámara','mj12.jpg','Smartphone Pro',999.99),(3,'Sony','Accesorios','2025-02-08 00:00:00.000000','Auriculares con cancelación de ruido','mj13.jpg','Auriculares Bluetooth',199.99),(4,'Apple','Tecnología','2025-02-08 00:00:00.000000','Reloj inteligente con muchas funciones','mj14.jpg','Smartwatch',349.99),(5,'Logitech','Accesorios','2025-02-08 00:00:00.000000','Teclado mecánico retroiluminado','mj15.jpg','Teclado Mecánico',89.99),(6,'DXRacer','Muebles','2025-02-08 00:00:00.000000','Silla ergonómica para gamers','mj16.jpg','Silla Gamer',259.99),(7,'LG','Electrónica','2025-02-08 00:00:00.000000','Monitor con resolución 4K UHD','mj17.jpg','Monitor 4K',499.99),(8,'Razer','Accesorios','2025-02-08 00:00:00.000000','Mouse gaming inalámbrico','mj18.jpg','Figura de mona china',79.99),(15,'Laptop','Computers','2025-02-10 00:02:19.201000','Laptop ideal para que juegues roblox','1739163739201__fx507zc4-hn005w_1_1.webp','Laptop Asus',3500),(16,'china','Other','2025-02-10 00:08:18.115000','Cabeza de este fracasao','1739164098115__61bfc9d38f1143045f7c4871.jpg','Cabeza de la pantera del Callao',4000),(18,'aaaaaaaaaaa','Computers','2025-02-10 00:10:27.389000','quiere oe ctv no seas huevonazo','1739644478779_GjHUx68bQAAiqMs.jpg.jpg','Amor propi',0);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'beststore'
--

--
-- Dumping routines for database 'beststore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-15 14:01:47
