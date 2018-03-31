-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 31, 2018 at 07:06 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `techbot`
--

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `idClasse` varchar(50) NOT NULL,
  `text` varchar(1024) DEFAULT NULL,
  `CID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idClasse`),
  KEY `REF_ClassifierClasse` (`CID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classe`
--

INSERT INTO `classe` (`idClasse`, `text`, `CID`) VALUES
('temperature', 'You asked me about Temperature ! I\'m not clever enough to figure out more though.', '2fc31ex330-nlc-1535'),
('conditions', 'You asked me about weather ! Sorry, that\'s all I can figure out...', '2fc31ex330-nlc-1535'),
('presentation', 'Je m\'appelle QBot (TechBot pour les intimes), et je travaille pour un projet de jeunes étudiants à l\'IUT Paris Descartes ! Mon rôle est de vous aider à  implémenter une intelligence artificielle personalisée pour vos besoins sur n\'importe quelle plateforme de votre choix (e-mails, twitter etc...).', '2fbbc6x326-nlc-1922'),
('tech', 'Je fonctionne avec le puissant moteur d\'intelligence artificielle Watson™ développée par IBM®. Ce moteur est à la pointe de la technologie, en se basant sur le maintenant célèbre principe du Deep Learning. \r\nPour en savoir plus sur Watson™, cliquez ici https://www.ibm.com/watson/', '2fbbc6x326-nlc-1922'),
('utilisation', 'Cette version du service QBot n\'est encore qu\'un prototype. Cela dit, vous pouvez dès maintenant activer ou désactiver les services affichés sur la page principale du site (vous pouvez y accéder en cliquant sur le bouton \"retour\").', '2fbbc6x326-nlc-1922');

-- --------------------------------------------------------

--
-- Table structure for table `classifier`
--

DROP TABLE IF EXISTS `classifier`;
CREATE TABLE IF NOT EXISTS `classifier` (
  `CID` varchar(50) NOT NULL,
  `nomClassifier` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classifier`
--

INSERT INTO `classifier` (`CID`, `nomClassifier`) VALUES
('2fc31ex330-nlc-1535', 'MyTestWeather'),
('2fbbc6x326-nlc-1922', 'TechBot_Presentation');

-- --------------------------------------------------------

--
-- Table structure for table `classifierservice`
--

DROP TABLE IF EXISTS `classifierservice`;
CREATE TABLE IF NOT EXISTS `classifierservice` (
  `CID` varchar(50) NOT NULL,
  `idService` int(11) NOT NULL,
  PRIMARY KEY (`CID`,`idService`),
  KEY `REF_Service` (`idService`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classifierservice`
--

INSERT INTO `classifierservice` (`CID`, `idService`) VALUES
('2fbbc6x326-nlc-1922', 3),
('2fbbc6x326-nlc-1922', 4),
('2fc31ex330-nlc-1535', 1),
('2fc31ex330-nlc-1535', 2);

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `idService` int(11) NOT NULL,
  `classeService` varchar(50) DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idService`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`idService`, `classeService`, `login`, `mdp`) VALUES
(1, 'services.mail.AttenteMail', 'techbotdemo@gmail.com', 'fgVFunR3Z94ueFnE'),
(2, 'twitter', 'techbotdemo', 'fgVFunR3Z94ueFnE'),
(3, 'services.mail.AttenteMail', 'techbotdemo@gmail.com', 'fgVFunR3Z94ueFnE'),
(4, 'services.IHM.IHM_Implementation', 'Local', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
