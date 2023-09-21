-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: lms_server
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('ce070509-cdaf-4343-803f-3e10e484f259','1fd7b44a-870a-41c5-82bf-cffcd2634344'),('ebe2fde1-c3bd-4455-b430-18b36eee65b1','6e12a470-13b5-4cf4-92d5-625fd3f3ea8e'),('cd09da81-8290-460a-b2eb-75739b07612d','93b0d697-d8ff-430f-b3cb-a3e2308a8a72');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_course_group_membership`
--

DROP TABLE IF EXISTS `cart_course_group_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_course_group_membership` (
  `id` varchar(255) NOT NULL,
  `cart_id` varchar(255) DEFAULT NULL,
  `course_group_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_user_course` (`cart_id`,`course_group_id`),
  KEY `course_group_id` (`course_group_id`),
  CONSTRAINT `cart_course_group_membership_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `cart_course_group_membership_ibfk_2` FOREIGN KEY (`course_group_id`) REFERENCES `course_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_course_group_membership`
--

LOCK TABLES `cart_course_group_membership` WRITE;
/*!40000 ALTER TABLE `cart_course_group_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_course_group_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_course_membership`
--

DROP TABLE IF EXISTS `cart_course_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_course_membership` (
  `id` varchar(255) NOT NULL,
  `cart_id` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_user_course` (`cart_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `cart_course_membership_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `cart_course_membership_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_course_membership`
--

LOCK TABLES `cart_course_membership` WRITE;
/*!40000 ALTER TABLE `cart_course_membership` DISABLE KEYS */;
INSERT INTO `cart_course_membership` VALUES ('4aaf7ceb-9926-4b93-b620-7fb35875559a','ebe2fde1-c3bd-4455-b430-18b36eee65b1','06e891fb-537a-4283-856e-ca7ff279abd6'),('e8ef5c0e-1385-4478-9808-8b0264bd42a0','ebe2fde1-c3bd-4455-b430-18b36eee65b1','0db40b2e-3091-4990-ad78-c1c66ea35605');
/*!40000 ALTER TABLE `cart_course_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `cover_image` varchar(512) DEFAULT NULL,
  `primary_coins` varchar(255) DEFAULT NULL,
  `secondary_coins` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  `instructor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('06e891fb-537a-4283-856e-ca7ff279abd6','Marketing Thinking - Khóa học tư duy & nghiệp vụ Marketing','https://i.ibb.co/tXwTh66/marketing-things.png','50','0','Khóa học giúp bạn giải quyết mọi bài toán Marketing trong nhiều ngành hàng, quy mô công ty khác nhau. Cập nhật toàn bộ case-study mới nhất, bám sát thị trường & ngành Marketing','ĐỖ XUÂN KHOA\r Foundeer & CEO Markus Agency'),('0b9ef256-10de-416a-a829-5dbe0539ce23','Khóa Học Facebook Ads Căn Bản Từ A-Z ( Mới 2023 )','https://i.ibb.co/YWJmzrW/Share-Khoa-Hoc-Facebook-Ads-Can-Ban-Tu-A-Z-Moi-Nhat-Cua-Btani.png','70','0','Khóa học này sẽ giúp bạn hiểu và vận hành quảng cáo Facebook vào kinh doanh một cách thực tiễn và hiệu quả','Co.Founder BTani Group - Bùi Quốc Bảo'),('0db40b2e-3091-4990-ad78-c1c66ea35605','Shopee Vạn Đơn Ecommerce Master Sale','https://i.ibb.co/RQNgjS4/Share-Khoa-Hoc-Ecommerce-Master-Sale-Cung-Hoang-Hoc.png','50','0','Trong khoá học bạn sẽ học được những kỹ năng Shopee thực chiến, bí mật gia tăng doanh số thần tốc , thủ thuật chạy đấu thầu từ khoá, quy trình vận hành Shopee tự động…','Hoàng Học'),('5aa5abb9-8d73-4e9b-a767-74c88527f66a','Khóa học Money Hacker Club (VIP) ','https://i.ibb.co/mz4Lzkf/015-A1699-1.jpg','50','0','Khóa Học Money Hacker Club (VIP) là một trong những khóa học kiếm tiền hay nhất cùng với hoàng nam mmo. Với những buổi học hướng dẫn Affiliate Marketing, Kinh doanh, MMO hết sức hiệu quả','Nam Hoàng'),('77e1a3c2-87f4-4daa-9c4b-8524c6312414','Khóa học Kiếm tiền với Youtube (Youtube Adsense)','https://i.ibb.co/Wv2NcyP/Share-Khoa-Hoc-Kiem-Tien-Voi-Youtube.png','50','0','Hướng dẫn kiếm tiền với Youtube từ quảng cáo Adsense','Tô Hải Đoàn'),('8048cc3b-8ad8-4af4-bb94-c44535b23444','3','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','',''),('9703da08-93d1-4fdd-9a4e-025533898b93','3','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('b5a60b96-88da-470e-bb80-cf86620bb145','2','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('bdad47fb-a450-4ef3-b960-f16016e519e1','3','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('c9cddb9a-7188-41b7-8170-c689a4c32c97','3','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('d89747d6-092a-4f92-87ed-2aa93790bc43','3','https://i.ibb.co/tXwTh66/marketing-things.png','100','0','ABCXYZ',''),('d89a1cb1-94f1-4a6d-9adb-aced04064217','3','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('df83cf82-8531-4daf-8a25-de897f826831','3','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('e3774d11-8434-4fd2-b262-7b5aaee9619e','2','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('e449d264-7629-4bbd-a044-63c8b0ce2511','2','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('f5088b60-55da-4dc5-9733-4993c281a380','3','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','description',''),('fd936eb9-cc3b-408d-8a8b-186c0a1bf4e2','3','https://i.ibb.co/tXwTh66/marketing-things.png','0','0','','');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_enrollment`
--

DROP TABLE IF EXISTS `course_enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_enrollment` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `enrollment_date_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `expiration_date_time` datetime DEFAULT NULL,
  `course_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_enrollment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `course_enrollment_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_enrollment`
--

LOCK TABLES `course_enrollment` WRITE;
/*!40000 ALTER TABLE `course_enrollment` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_group`
--

DROP TABLE IF EXISTS `course_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_group` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `cover_image` varchar(255) DEFAULT NULL,
  `primary_coins` varchar(255) DEFAULT NULL,
  `secondary_coins` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_group`
--

LOCK TABLES `course_group` WRITE;
/*!40000 ALTER TABLE `course_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_group_enrollment`
--

DROP TABLE IF EXISTS `course_group_enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_group_enrollment` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `enrollment_date_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `expiration_date_time` datetime DEFAULT NULL,
  `course_group_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `course_group_id` (`course_group_id`),
  CONSTRAINT `course_group_enrollment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `course_group_enrollment_ibfk_2` FOREIGN KEY (`course_group_id`) REFERENCES `course_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_group_enrollment`
--

LOCK TABLES `course_group_enrollment` WRITE;
/*!40000 ALTER TABLE `course_group_enrollment` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_group_enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_group_membership`
--

DROP TABLE IF EXISTS `course_group_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_group_membership` (
  `id` varchar(255) NOT NULL,
  `course_id` varchar(255) NOT NULL,
  `course_group_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `course_group_id` (`course_group_id`),
  CONSTRAINT `course_group_membership_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `course_group_membership_ibfk_2` FOREIGN KEY (`course_group_id`) REFERENCES `course_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_group_membership`
--

LOCK TABLES `course_group_membership` WRITE;
/*!40000 ALTER TABLE `course_group_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_group_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `hashed_password` varchar(511) DEFAULT NULL,
  `role` enum('admin','customer','seller') NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `affiliate_code` varchar(255) DEFAULT NULL,
  `primary_coins` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_number` (`phone_number`),
  UNIQUE KEY `affiliate_code` (`affiliate_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1fd7b44a-870a-41c5-82bf-cffcd2634344','Test123@','Test123@','123456','$2a$05$du3JqhhiolxulWPmbqCUIeKL/4yj9blRLHKcxf47.HKc0rUvQNAwi','customer','Test123@','AFFmxQEtk7KIT',NULL),('6e12a470-13b5-4cf4-92d5-625fd3f3ea8e','321','321@gmail.com','01234456728','$2a$05$CUzxmKiOrJvXxluRC7FnNOwRnc5XHBeWk.kw4QPfeD/FCKKI9n2Um','customer','321','AFFO5G2PwTZQR',NULL),('93b0d697-d8ff-430f-b3cb-a3e2308a8a72','123435','3212@gmail.com','012344567289','$2a$05$/8DBH8AxY5O757fbbOUO/OZUE38prB0cvHX2RxsgeWhdOE1Hn6OV.','customer','123435','AFFSuBAHuhowG',NULL),('d87b6183-7a74-4b3b-99ae-e47e337ff17a','ok','123@gmail.com','0123445678','$2a$10$aFCfP3ynaFQj6dSn2/6qlOLcArJ4DpZRplp0XswyOk//nhdLN6jXa','customer','ok','AFFBaOEzFGZ4e',NULL),('hdhdhdhdhdhdaisud','manhblue','user@example.com','0962986206','$2a$04$RDdqwovFOJRaklweetoxoOikX480q3fei19AlLNK.68MkyGTPihBm','customer','Manh Blue User','ABC123',NULL),('hdhdhdhdhdhdaiuuuud','manhblue2','user@example2.com','0984784246','$2a$04$RDdqwovFOJRaklweetoxoOikX480q3fei19AlLNK.68MkyGTPihBm','seller','Manh Blue User','ABC1234',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-21 16:44:57
