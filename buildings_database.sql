-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2025 at 05:56 AM
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
-- Database: `buildings_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `building_four`
--

CREATE TABLE `building_four` (
  `RoomNames` varchar(12) NOT NULL,
  `RoomCapacity` int(3) NOT NULL,
  `Available` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `building_four`
--

INSERT INTO `building_four` (`RoomNames`, `RoomCapacity`, `Available`) VALUES
('B4-LR-101', 30, '1'),
('B4-LR-102', 90, '1'),
('B4-LR-103', 40, '1');

-- --------------------------------------------------------

--
-- Table structure for table `building_one`
--
-- Error reading structure for table buildings_database.building_one: #1932 - Table 'buildings_database.building_one' doesn't exist in engine
-- Error reading data for table buildings_database.building_one: #1064 - You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near 'FROM `buildings_database`.`building_one`' at line 1

-- --------------------------------------------------------

--
-- Table structure for table `building_one_new`
--

CREATE TABLE `building_one_new` (
  `RoomNames` varchar(30) NOT NULL,
  `RoomCapacity` int(3) NOT NULL,
  `Available` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `building_one_new`
--

INSERT INTO `building_one_new` (`RoomNames`, `RoomCapacity`, `Available`) VALUES
('B1-LR101', 63, 1),
('B1-LR102', 73, 1),
('B1-LR103', 92, 1);

-- --------------------------------------------------------

--
-- Table structure for table `building_three`
--

CREATE TABLE `building_three` (
  `RoomNames` varchar(12) NOT NULL,
  `RoomCapacity` int(3) NOT NULL,
  `Available` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `building_three`
--

INSERT INTO `building_three` (`RoomNames`, `RoomCapacity`, `Available`) VALUES
('B3-LR-101', 60, '1'),
('B3-LR-102', 65, '1'),
('B3-LR-103', 75, '1');

-- --------------------------------------------------------

--
-- Table structure for table `building_two`
--

CREATE TABLE `building_two` (
  `RoomNames` varchar(12) NOT NULL,
  `RoomCapacity` int(3) NOT NULL,
  `Available` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `building_two`
--

INSERT INTO `building_two` (`RoomNames`, `RoomCapacity`, `Available`) VALUES
('B2-LR-101', 50, '1'),
('B2-LR-102', 45, '1'),
('B2-LR-103', 60, '1');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
