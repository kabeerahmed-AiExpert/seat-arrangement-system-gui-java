-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2025 at 05:55 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `arrangeseats_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `arrangedseats`
--

CREATE TABLE `arrangedseats` (
  `Name` varchar(100) DEFAULT NULL,
  `Roll_No` varchar(50) DEFAULT NULL,
  `Sap` varchar(50) DEFAULT NULL,
  `Section` varchar(20) DEFAULT NULL,
  `Building` varchar(100) DEFAULT NULL,
  `Room` varchar(100) DEFAULT NULL,
  `SeatNo` int(11) DEFAULT NULL,
  `ArrangedBy` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `arrangedseats`
--
ALTER TABLE `arrangedseats`
  ADD KEY `idx_building_room` (`Building`,`Room`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
