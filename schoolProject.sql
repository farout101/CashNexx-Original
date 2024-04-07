-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: bankingdb
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `admintable`
--

DROP TABLE IF EXISTS `admintable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admintable` (
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admintable`
--

LOCK TABLES `admintable` WRITE;
/*!40000 ALTER TABLE `admintable` DISABLE KEYS */;
INSERT INTO `admintable` VALUES ('admin','password');
/*!40000 ALTER TABLE `admintable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banned_user_table`
--

DROP TABLE IF EXISTS `banned_user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banned_user_table` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `nrcNumber` varchar(255) NOT NULL,
  `gmail` varchar(255) DEFAULT NULL,
  `Career` varchar(255) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  `hashedPassword` varchar(255) DEFAULT NULL,
  `accountNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `gmail` (`gmail`),
  UNIQUE KEY `accountNumber` (`accountNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banned_user_table`
--

LOCK TABLES `banned_user_table` WRITE;
/*!40000 ALTER TABLE `banned_user_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `banned_user_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_table`
--

DROP TABLE IF EXISTS `loan_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan_table` (
  `loan_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `account_number` varchar(16) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `loan_amount` decimal(10,2) NOT NULL,
  `paid_amount` decimal(10,2) DEFAULT '0.00',
  `due_date` date DEFAULT NULL,
  `fully_paid` tinyint(1) DEFAULT '0',
  `repayment_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`loan_id`),
  KEY `loan_table_ibfk_2` (`user_id`),
  CONSTRAINT `loan_table_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `usertable` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_table`
--

LOCK TABLES `loan_table` WRITE;
/*!40000 ALTER TABLE `loan_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pending_user`
--

DROP TABLE IF EXISTS `pending_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pending_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `nrc_number` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `career` varchar(255) NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `account_number` varchar(255) NOT NULL,
  `otp` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pending_user`
--

LOCK TABLES `pending_user` WRITE;
/*!40000 ALTER TABLE `pending_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `pending_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pendingloaninfo`
--

DROP TABLE IF EXISTS `pendingloaninfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pendingloaninfo` (
  `user_id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `loan_amount` decimal(10,2) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `estimated_date` date DEFAULT NULL,
  `accountNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pendingloaninfo`
--

LOCK TABLES `pendingloaninfo` WRITE;
/*!40000 ALTER TABLE `pendingloaninfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `pendingloaninfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pendinguserinfo`
--

DROP TABLE IF EXISTS `pendinguserinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pendinguserinfo` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `nrcNumber` varchar(255) NOT NULL,
  `gmail` varchar(255) DEFAULT NULL,
  `career` varchar(255) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  `hashedPassword` varchar(255) DEFAULT NULL,
  `accountNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `accountNumber` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pendinguserinfo`
--

LOCK TABLES `pendinguserinfo` WRITE;
/*!40000 ALTER TABLE `pendinguserinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `pendinguserinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transactionId` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `senderGmail` varchar(255) NOT NULL,
  `receiverGmail` varchar(255) NOT NULL,
  PRIMARY KEY (`transactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'Regular',8000.00,'2024-03-11 15:40:51','phyozawlinn1852020@gmail.com','3694986334906168'),(2,'Regular',8000.00,'2024-03-11 16:07:44','phyozawlinn1852020@gmail.com','4259980363199892'),(3,'Regular',8000.00,'2024-03-11 16:12:05','phyozawlinn1852020@gmail.com','4259980363199892'),(4,'Regular',8000.00,'2024-03-11 16:19:31','phyozawlinn1852020@gmail.com','4259980363199892'),(5,'Regular',10000.00,'2024-03-11 16:42:33','phyozawlinn1852020@gmail.com','4259980363199892'),(6,'Regular',8000.00,'2024-03-11 16:49:21','phyozawlinn1852020@gmail.com','4259980363199892'),(7,'Regular',10000.00,'2024-03-11 17:20:20','phyozawlinn1852020@gmail.com','4259980363199892'),(8,'Regular',10000.00,'2024-03-11 17:29:28','phyozawlinn1852020@gmail.com','8076970036316546'),(9,'Regular',10000.00,'2024-03-11 17:33:53','phyozawlinn1852020@gmail.com','8076970036316546'),(10,'Regular',90000.00,'2024-03-12 06:44:14','myintmyatps664@gmail.com','5262690205478408'),(11,'Regular',50000.00,'2024-03-12 07:26:11','weiyanhtut0@gmail.com','3451748200448808');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_table`
--

DROP TABLE IF EXISTS `transaction_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_table` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `sender_user_gmail` varchar(255) NOT NULL,
  `recipient_account_number` varchar(255) DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `transaction_date` date NOT NULL,
  `transaction_time` time DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_table`
--

LOCK TABLES `transaction_table` WRITE;
/*!40000 ALTER TABLE `transaction_table` DISABLE KEYS */;
INSERT INTO `transaction_table` VALUES (1,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','12:48:00'),(2,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','12:49:10'),(3,'myintmyatps664@gmail.com','3694986334906168',36.00,'2024-03-10','12:50:49'),(4,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','12:53:03'),(5,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','12:55:51'),(6,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','12:58:37'),(7,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:03:08'),(8,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:04:31'),(9,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:05:45'),(10,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:07:33'),(11,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:11:11'),(12,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:15:07'),(13,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:15:35'),(14,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:19:03'),(15,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:20:11'),(16,'myintmyatps664@gmail.com','3694986334906168',10000.00,'2024-03-10','13:22:55'),(17,'phyozawlinn1852020@gmail.com','3694986334906168',8000.00,'2024-03-11','22:10:52'),(18,'phyozawlinn1852020@gmail.com','4259980363199892',8000.00,'2024-03-11','22:37:45'),(19,'phyozawlinn1852020@gmail.com','4259980363199892',8000.00,'2024-03-11','22:42:06'),(20,'phyozawlinn1852020@gmail.com','4259980363199892',8000.00,'2024-03-11','22:49:32'),(21,'phyozawlinn1852020@gmail.com','4259980363199892',10000.00,'2024-03-11','23:12:33'),(22,'phyozawlinn1852020@gmail.com','4259980363199892',8000.00,'2024-03-11','23:19:21'),(23,'phyozawlinn1852020@gmail.com','4259980363199892',10000.00,'2024-03-11','23:50:21'),(24,'phyozawlinn1852020@gmail.com','8076970036316546',10000.00,'2024-03-11','23:59:28'),(25,'phyozawlinn1852020@gmail.com','8076970036316546',10000.00,'2024-03-12','00:03:54'),(26,'myintmyatps664@gmail.com','5262690205478408',90000.00,'2024-03-12','13:14:15'),(27,'weiyanhtut0@gmail.com','3451748200448808',50000.00,'2024-03-12','13:56:11');
/*!40000 ALTER TABLE `transaction_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertable`
--

DROP TABLE IF EXISTS `usertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertable` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `nrcNumber` varchar(255) NOT NULL,
  `gmail` varchar(255) DEFAULT NULL,
  `Career` varchar(255) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  `hashedPassword` varchar(255) DEFAULT NULL,
  `accountNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `accountNumber` (`accountNumber`),
  UNIQUE KEY `gmail` (`gmail`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertable`
--

LOCK TABLES `usertable` WRITE;
/*!40000 ALTER TABLE `usertable` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-13 19:35:44
