-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: personalaims
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aims_order`
--

DROP TABLE IF EXISTS `aims_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aims_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paymentInfoId` varchar(100) DEFAULT NULL,
  `deliveryInfoId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Order_Payment` (`paymentInfoId`),
  KEY `FK_Order_DeliveryInfo` (`deliveryInfoId`),
  CONSTRAINT `FK_Order_DeliveryInfo` FOREIGN KEY (`deliveryInfoId`) REFERENCES `delivery_info` (`id`),
  CONSTRAINT `FK_Order_Payment` FOREIGN KEY (`paymentInfoId`) REFERENCES `payment_info` (`transactionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `authors` varchar(50) DEFAULT NULL,
  `cover` varchar(50) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `publicationDate` date DEFAULT NULL,
  `language` varchar(50) DEFAULT NULL,
  `numberOfPages` int(11) DEFAULT NULL,
  `genres` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Book_Parent` FOREIGN KEY (`id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cd`
--

DROP TABLE IF EXISTS `cd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cd` (
  `id` int(11) NOT NULL,
  `artists` varchar(50) DEFAULT NULL,
  `recordLabel` varchar(50) DEFAULT NULL,
  `trackList` varchar(50) DEFAULT NULL,
  `genres` varchar(50) DEFAULT NULL,
  `PublicationDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CD_Parent` FOREIGN KEY (`id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delivery_info`
--

DROP TABLE IF EXISTS `delivery_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiver` varchar(50) DEFAULT NULL,
  `phoneNumber` char(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `cityAddress` varchar(50) DEFAULT NULL,
  `detailedAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `digital_video_disc`
--

DROP TABLE IF EXISTS `digital_video_disc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `digital_video_disc` (
  `id` int(11) NOT NULL,
  `discFormat` varchar(50) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `runTime` int(11) DEFAULT NULL,
  `studio` varchar(50) DEFAULT NULL,
  `language` varchar(50) DEFAULT NULL,
  `subtitles` varchar(50) DEFAULT NULL,
  `publicationDate` date DEFAULT NULL,
  `genres` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_DVD_Parent` FOREIGN KEY (`id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `priceNoVAT` double DEFAULT NULL,
  `priceWithVAT` double DEFAULT NULL,
  `deliveryFee` double DEFAULT NULL,
  `totalFee` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Invoice_Order` (`orderId`),
  CONSTRAINT `FK_Invoice_Order` FOREIGN KEY (`orderId`) REFERENCES `aims_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `long_play_record`
--

DROP TABLE IF EXISTS `long_play_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `long_play_record` (
  `id` int(11) NOT NULL,
  `discFormat` varchar(50) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `runTime` int(11) DEFAULT NULL,
  `studio` varchar(50) DEFAULT NULL,
  `language` varchar(50) DEFAULT NULL,
  `subtitles` varchar(50) DEFAULT NULL,
  `publicationDate` date DEFAULT NULL,
  `genres` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_DVD_Parent_copy` FOREIGN KEY (`id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medias_in_order`
--

DROP TABLE IF EXISTS `medias_in_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medias_in_order` (
  `order_id` int(11) NOT NULL,
  `media_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `is_rush` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`media_id`),
  KEY `FK_OrderMedia_Media` (`media_id`),
  CONSTRAINT `FK_OrderMedia_Media` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`),
  CONSTRAINT `FK_OrderMedia_Order` FOREIGN KEY (`order_id`) REFERENCES `aims_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_info`
--

DROP TABLE IF EXISTS `payment_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_info` (
  `transactionId` varchar(100) NOT NULL,
  `cardOwner` varchar(100) DEFAULT NULL,
  `balanceChange` double DEFAULT NULL,
  `transactionMessage` varchar(100) DEFAULT NULL,
  `transactionTime` datetime DEFAULT NULL,
  PRIMARY KEY (`transactionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'personalaims'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-03 23:02:49
