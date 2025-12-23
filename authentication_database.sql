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
-- Database: `authentication_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `Emails` varchar(30) NOT NULL,
  `Passwords` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`Emails`, `Passwords`) VALUES
('f24ari001@aror.edu.pk', 'sap183'),
('f24ari002@aror.edu.pk', 'sap184'),
('f24ari003@aror.edu.pk', 'sap185'),
('f24ari004@aror.edu.pk', 'sap186'),
('f24ari005@aror.edu.pk', 'sap187'),
('f24ari006@aror.edu.pk', 'sap188'),
('f24ari007@aror.edu.pk', 'sap189'),
('f24ari008@aror.edu.pk', 'sap190'),
('f24ari009@aror.edu.pk', 'sap191'),
('f24ari010@aror.edu.pk', 'sap192'),
('f24ari011@aror.edu.pk', 'sap193'),
('f24ari012@aror.edu.pk', 'sap194'),
('f24ari013@aror.edu.pk', 'sap195'),
('f24ari014@aror.edu.pk', 'sap196'),
('f24ari015@aror.edu.pk', 'sap197'),
('f24ari016@aror.edu.pk', 'sap198'),
('f24ari017@aror.edu.pk', 'sap199'),
('f24ari018@aror.edu.pk', 'sap200'),
('f24ari019@aror.edu.pk', 'sap201'),
('f24ari020@aror.edu.pk', 'sap202'),
('f24ari021@aror.edu.pk', 'sap203'),
('f24ari022@aror.edu.pk', 'sap204'),
('f24ari023@aror.edu.pk', 'sap205'),
('f24ari024@aror.edu.pk', 'sap206'),
('f24ari025@aror.edu.pk', 'sap207'),
('f24ari026@aror.edu.pk', 'sap208'),
('f24ari027@aror.edu.pk', 'sap209'),
('f24ari028@aror.edu.pk', 'sap210'),
('f24ari029@aror.edu.pk', 'sap211'),
('f24ari030@aror.edu.pk', 'sap212'),
('f24ari031@aror.edu.pk', 'sap213'),
('f24ari032@aror.edu.pk', 'sap214'),
('f24ari033@aror.edu.pk', 'sap215'),
('f24ari034@aror.edu.pk', 'sap216'),
('f24ari035@aror.edu.pk', 'sap217'),
('f24ari036@aror.edu.pk', 'sap218'),
('f24ari037@aror.edu.pk', 'sap219'),
('f24ari038@aror.edu.pk', 'sap220'),
('f24ari039@aror.edu.pk', 'sap221'),
('f24ari040@aror.edu.pk', 'sap222'),
('f24ari041@aror.edu.pk', 'sap223'),
('f24ari042@aror.edu.pk', 'sap224'),
('f24ari043@aror.edu.pk', 'sap225'),
('f24ari044@aror.edu.pk', 'sap226'),
('f24ari045@aror.edu.pk', 'sap227'),
('f24ari046@aror.edu.pk', 'sap228'),
('f24ari047@aror.edu.pk', 'sap229'),
('f24ari048@aror.edu.pk', 'sap230'),
('f24ari049@aror.edu.pk', 'sap231'),
('f24ari050@aror.edu.pk', 'sap232'),
('f24ari051@aror.edu.pk', 'sap233'),
('f24ari052@aror.edu.pk', 'sap234'),
('f24ari053@aror.edu.pk', 'sap235'),
('f24ari054@aror.edu.pk', 'sap236'),
('f24ari055@aror.edu.pk', 'sap237'),
('f24ari056@aror.edu.pk', 'sap238'),
('f24ari057@aror.edu.pk', 'sap239'),
('f24ari058@aror.edu.pk', 'sap240'),
('f24ari059@aror.edu.pk', 'sap241'),
('f24ari060@aror.edu.pk', 'sap242'),
('f24ari061@aror.edu.pk', 'sap243'),
('f24ari062@aror.edu.pk', 'sap244'),
('f24ari063@aror.edu.pk', 'sap245'),
('f24ari064@aror.edu.pk', 'sap246'),
('f24ari065@aror.edu.pk', 'sap247'),
('f24ari066@aror.edu.pk', 'sap248'),
('f24ari067@aror.edu.pk', 'sap249'),
('f24ari068@aror.edu.pk', 'sap250'),
('f24ari069@aror.edu.pk', 'sap251'),
('f24ari070@aror.edu.pk', 'sap252'),
('f24ari071@aror.edu.pk', 'sap253'),
('f24ari072@aror.edu.pk', 'sap254'),
('f24ari073@aror.edu.pk', 'sap255'),
('f24ari074@aror.edu.pk', 'sap256'),
('f24ari075@aror.edu.pk', 'sap257'),
('f24ari076@aror.edu.pk', 'sap258'),
('f24ari077@aror.edu.pk', 'sap259'),
('f24ari078@aror.edu.pk', 'sap260'),
('f24ari079@aror.edu.pk', 'sap261'),
('f24ari080@aror.edu.pk', 'sap262'),
('f24ari081@aror.edu.pk', 'sap263'),
('f24ari082@aror.edu.pk', 'sap264'),
('f24ari083@aror.edu.pk', 'sap265'),
('f24ari084@aror.edu.pk', 'sap266'),
('f24ari085@aror.edu.pk', 'sap267'),
('f24ari086@aror.edu.pk', 'sap268'),
('f24ari087@aror.edu.pk', 'sap269'),
('f24ari088@aror.edu.pk', 'sap270'),
('f24ari089@aror.edu.pk', 'sap271'),
('f24ari090@aror.edu.pk', 'sap272'),
('f24ari091@aror.edu.pk', 'sap273'),
('f24ari092@aror.edu.pk', 'sap274'),
('f24ari093@aror.edu.pk', 'sap275'),
('f24ari094@aror.edu.pk', 'sap276'),
('f24ari095@aror.edu.pk', 'sap277'),
('f24ari096@aror.edu.pk', 'sap278'),
('f24ari097@aror.edu.pk', 'sap279'),
('f24ari098@aror.edu.pk', 'sap280'),
('f24ari099@aror.edu.pk', 'sap281'),
('f24ari100@aror.edu.pk', 'sap282'),
('f24ari101@aror.edu.pk', 'sap283'),
('f24ari102@aror.edu.pk', 'sap284'),
('f24ari103@aror.edu.pk', 'sap285'),
('f24ari104@aror.edu.pk', 'sap286'),
('f24ari105@aror.edu.pk', 'sap287'),
('f24ari106@aror.edu.pk', 'sap288'),
('f24ari107@aror.edu.pk', 'sap289'),
('f24ari108@aror.edu.pk', 'sap290'),
('f24ari109@aror.edu.pk', 'sap291'),
('f24ari110@aror.edu.pk', 'sap292'),
('f24ari111@aror.edu.pk', 'sap293'),
('f24ari112@aror.edu.pk', 'sap294'),
('f24ari113@aror.edu.pk', 'sap295'),
('f24ari114@aror.edu.pk', 'sap296'),
('f24ari115@aror.edu.pk', 'sap297'),
('f24ari116@aror.edu.pk', 'sap298'),
('f24ari117@aror.edu.pk', 'sap299'),
('f24ari118@aror.edu.pk', 'sap300'),
('f24ari119@aror.edu.pk', 'sap301'),
('f24ari120@aror.edu.pk', 'sap302'),
('f24ari121@aror.edu.pk', 'sap303'),
('f24ari122@aror.edu.pk', 'sap304'),
('f24ari123@aror.edu.pk', 'sap305'),
('f24ari124@aror.edu.pk', 'sap306'),
('f24ari125@aror.edu.pk', 'sap307'),
('f24ari126@aror.edu.pk', 'sap308'),
('f24ari127@aror.edu.pk', 'sap309'),
('f24ari128@aror.edu.pk', 'sap310'),
('f24ari129@aror.edu.pk', 'sap311'),
('f24ari130@aror.edu.pk', 'sap312'),
('f24ari131@aror.edu.pk', 'sap313'),
('f24ari132@aror.edu.pk', 'sap314'),
('f24ari133@aror.edu.pk', 'sap315'),
('f24ari134@aror.edu.pk', 'sap316'),
('f24ari135@aror.edu.pk', 'sap317'),
('f24ari136@aror.edu.pk', 'sap318'),
('f24ari137@aror.edu.pk', 'sap319'),
('f24ari138@aror.edu.pk', 'sap320'),
('f24ari139@aror.edu.pk', 'sap321'),
('f24ari140@aror.edu.pk', 'sap322'),
('f24ari141@aror.edu.pk', 'sap323'),
('f24ari142@aror.edu.pk', 'sap324'),
('f24ari143@aror.edu.pk', 'sap325'),
('f24ari144@aror.edu.pk', 'sap326'),
('f24ari145@aror.edu.pk', 'sap327'),
('f24ari146@aror.edu.pk', 'sap328'),
('f24ari147@aror.edu.pk', 'sap329'),
('f24ari148@aror.edu.pk', 'sap330'),
('f24ari149@aror.edu.pk', 'sap331'),
('f24ari150@aror.edu.pk', 'sap332'),
('f24ari151@aror.edu.pk', 'sap333'),
('f24ari152@aror.edu.pk', 'sap334'),
('f24ari153@aror.edu.pk', 'sap335'),
('f24ari154@aror.edu.pk', 'sap336'),
('f24ari155@aror.edu.pk', 'sap337'),
('f24ari156@aror.edu.pk', 'sap338'),
('f24ari157@aror.edu.pk', 'sap339'),
('f24ari158@aror.edu.pk', 'sap340'),
('f24ari159@aror.edu.pk', 'sap341'),
('f24ari160@aror.edu.pk', 'sap342'),
('f24ari161@aror.edu.pk', 'sap343'),
('f24ari162@aror.edu.pk', 'sap344'),
('f24ari163@aror.edu.pk', 'sap345'),
('f24ari164@aror.edu.pk', 'sap346'),
('f24ari165@aror.edu.pk', 'sap347'),
('f24ari166@aror.edu.pk', 'sap348'),
('f24ari167@aror.edu.pk', 'sap349'),
('f24ari168@aror.edu.pk', 'sap350'),
('f24ari169@aror.edu.pk', 'sap351'),
('f24ari170@aror.edu.pk', 'sap352'),
('f24ari171@aror.edu.pk', 'sap353'),
('f24ari172@aror.edu.pk', 'sap354'),
('f24ari173@aror.edu.pk', 'sap355'),
('f24ari174@aror.edu.pk', 'sap356'),
('f24ari175@aror.edu.pk', 'sap357'),
('f24ari176@aror.edu.pk', 'sap358'),
('f24ari177@aror.edu.pk', 'sap359'),
('f24ari178@aror.edu.pk', 'sap360'),
('f24ari179@aror.edu.pk', 'sap361'),
('f24ari180@aror.edu.pk', 'sap362'),
('f24ari181@aror.edu.pk', 'sap363'),
('f24ari182@aror.edu.pk', 'sap364'),
('f24ari183@aror.edu.pk', 'sap365'),
('f24ari184@aror.edu.pk', 'sap366'),
('f24ari185@aror.edu.pk', 'sap367'),
('f24ari186@aror.edu.pk', 'sap368'),
('f24ari187@aror.edu.pk', 'sap369'),
('f24ari188@aror.edu.pk', 'sap370'),
('f24ari189@aror.edu.pk', 'sap371'),
('f24ari190@aror.edu.pk', 'sap372'),
('f24ari191@aror.edu.pk', 'sap373'),
('f24ari192@aror.edu.pk', 'sap374'),
('f24ari193@aror.edu.pk', 'sap375'),
('f24ari194@aror.edu.pk', 'sap376'),
('f24ari195@aror.edu.pk', 'sap377'),
('f24ari196@aror.edu.pk', 'sap378'),
('f24ari197@aror.edu.pk', 'sap379'),
('f24ari198@aror.edu.pk', 'sap380'),
('f24ari199@aror.edu.pk', 'sap381'),
('f24ari200@aror.edu.pk', 'sap382');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `Emails` varchar(35) NOT NULL,
  `Passwords` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`Emails`, `Passwords`) VALUES
('abdulkhadiquefaculty@aror.edi.pk', 'faculty123'),
('kabeerahmedfaculty@aror.edi.pk', 'faculty124'),
('nooralifaculty@aror.edi.pk', 'faculty125'),
('mazharuddinfaculty@aror.edi.pk', 'faculty126');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
