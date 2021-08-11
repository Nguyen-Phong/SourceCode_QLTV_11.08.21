-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: lm
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `MaSach` varchar(50) NOT NULL,
  `TenSach` varchar(50) NOT NULL,
  `soLuong` int NOT NULL,
  PRIMARY KEY (`MaSach`),
  UNIQUE KEY `TenSach` (`TenSach`),
  UNIQUE KEY `MaSach_UNIQUE` (`MaSach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('GD1','Toán 10',36),('GD2','Ngữ Văn 10',20),('GD3','Hóa Học 10',20),('GD4','Tiếng Anh',25),('KH1','Vũ trụ 10',20),('KH3','Giun Đất',60),('KH4','Khoáng chất',50),('NN1','Tiếng Anh 10',50),('NN2','Bí kíp trồng cây',15),('NN3','Những loại thuốc quý',20),('NN4','Rừng Amazon',10),('TK1','Những loài côn trùng hiếm',10),('TK2','Khám phá Đại Dương',50),('TK3','Động vật lưỡng cư',15),('TK4','Động vật thủy sinh',15);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muontra`
--

DROP TABLE IF EXISTS `muontra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muontra` (
  `MaSach` varchar(50) NOT NULL,
  `maThanhVien` varchar(20) NOT NULL,
  `ngayMuon` date NOT NULL,
  `ngayTra` date NOT NULL,
  KEY `fk_maSach` (`MaSach`),
  KEY `fk_maTV` (`maThanhVien`),
  CONSTRAINT `fk_maSach` FOREIGN KEY (`MaSach`) REFERENCES `book` (`MaSach`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_maTV` FOREIGN KEY (`maThanhVien`) REFERENCES `thanhvien` (`maThanhVien`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muontra`
--

LOCK TABLES `muontra` WRITE;
/*!40000 ALTER TABLE `muontra` DISABLE KEYS */;
INSERT INTO `muontra` VALUES ('NN3','SV4','2020-06-05','2020-08-01'),('NN2','NV2','2016-05-06','2016-09-05'),('NN2','NV3','2019-12-05','2020-01-29'),('NN2','NV3','2019-12-05','2020-01-29'),('NN2','NV3','2019-12-05','2020-01-29'),('NN2','NV3','2019-12-05','2020-01-29'),('NN2','NV3','2019-12-05','2020-01-29'),('KH3','GV2','2015-06-09','2016-09-05'),('KH1','CN1','2020-12-05','2021-09-06'),('TK1','NV1','2020-12-12','2021-06-09'),('TK1','NV2','2020-12-12','2021-06-09'),('GD3','NV3','2019-06-05','2020-12-06'),('TK1','CN2','2019-05-06','2020-12-05');
/*!40000 ALTER TABLE `muontra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thanhvien`
--

DROP TABLE IF EXISTS `thanhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanhvien` (
  `maThanhVien` varchar(20) NOT NULL,
  `tenThanhVien` varchar(50) NOT NULL,
  `tuoi` int NOT NULL,
  `gioiTinh` varchar(10) NOT NULL,
  `chucVu` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `diaChi` varchar(50) NOT NULL,
  PRIMARY KEY (`maThanhVien`),
  UNIQUE KEY `maThanhVien_UNIQUE` (`maThanhVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thanhvien`
--

LOCK TABLES `thanhvien` WRITE;
/*!40000 ALTER TABLE `thanhvien` DISABLE KEYS */;
INSERT INTO `thanhvien` VALUES ('CN1','Lò Thị Tèo',35,'Nữ','Công Nhân','03256654225','Mũi Né'),('CN2','Thề Văn Thốt',23,'Nam','Công nhân cơ khí','06225554123','112 Hoàng Mai, HN'),('CN3','Thể Văn Thao',28,'Nam','Công nhân','0956116554','Hà Nội'),('GV2','La Thị Làng',45,'Nữ','Giáo Viên','0165335221','Thái Bình'),('GV3','Mê Thị Nghề',45,'Nữ','Hiệu Trưởng','0366688897','Cần Thơ'),('NV1','Pắc Văn Kèo',47,'Nam','Cán Bộ Xã','095644521','Gia Rai'),('NV2','Mê Thị Trồng Cây',90,'Nữ','Nghỉ Hưu','0999888667','Hà Giang'),('NV3','Đạo Văn Đức',60,'Nam','Nghỉ hưu','0685445223','Thái Nguyên'),('NV4','Cầu Thị Nghè',26,'Nữ','Thư ký','0956123554','Thái Bình'),('SV1','Nguyễn Văn Anh',19,'Nam','Sinh Viên','0956664445','Kon Tum'),('SV2','Hoạch Thị Định',23,'Khác','Sinh Viên','0955666552','Hà Nam'),('SV3','Trần Văn Tủn',21,'Nam','Sinh Viên','0325444521','Hải Phòng'),('SV4','Thành Văn Công',19,'Khác','Sinh Viên','0655222554','Quy Nhơn'),('SV6','Nguyễn Thị Bé Xíu',19,'Khác','Sinh Viên','0956662110','Mũi Né');
/*!40000 ALTER TABLE `thanhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuthu`
--

DROP TABLE IF EXISTS `thuthu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thuthu` (
  `id_acc` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id_acc`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `password` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuthu`
--

LOCK TABLES `thuthu` WRITE;
/*!40000 ALTER TABLE `thuthu` DISABLE KEYS */;
INSERT INTO `thuthu` VALUES (1,'phong','lathuthu'),(2,'teo','cunglathuthu'),(3,'tun','lailathuthu');
/*!40000 ALTER TABLE `thuthu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-11 15:50:19
