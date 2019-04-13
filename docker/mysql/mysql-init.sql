-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: bloodbank
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1


--
-- Current Database: `bloodbank`
--


USE `bloodbank`;

--
-- Table structure for table `donar`
--

DROP TABLE IF EXISTS `donar`;
CREATE TABLE `donar` (
  `name` varchar(30) NOT NULL,
  `age` int(10) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `phone` bigint(12) NOT NULL,
  `location` varchar(1000) DEFAULT NULL,
  `medical_records` varchar(2000) DEFAULT NULL,
  `bloodgroup` varchar(10) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donar`
--

LOCK TABLES `donar` WRITE;
INSERT INTO `donar` VALUES ('kannan',22,'kannan@gmail.com',765829471,'USA','diabetes','O+ve');
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES ('admin','admin@bloodbank.com','admin','admin');
INSERT INTO `user` VALUES ('kannan','kannan@gmail.com','password','user');
UNLOCK TABLES;
