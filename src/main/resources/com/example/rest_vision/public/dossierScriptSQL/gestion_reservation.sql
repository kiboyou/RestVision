-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 16, 2024 at 11:09 PM
-- Server version: 8.0.36-0ubuntu0.22.04.1
-- PHP Version: 8.1.2-1ubuntu2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rest_vision`
--

-- --------------------------------------------------------

--
-- Table structure for table `places`
--

CREATE TABLE `places` (
  `idplace` int NOT NULL,
  `numperson` int NOT NULL,
  `numtable` int NOT NULL,
  `statutplace` enum('disponible','indisponible') GENERATED ALWAYS AS ((case when (`numtable` = 0) then _utf8mb4'indisponible' else _utf8mb4'disponible' end)) STORED
) ;

--
-- Dumping data for table `places`
--

INSERT INTO `places` (`idplace`, `numperson`, `numtable`) VALUES
(2, 4, 2),
(3, 3, 2),
(4, 2, 2),
(5, 1, 2);

--
-- Triggers `places`
--
DELIMITER $$
CREATE TRIGGER `update_statutplace` BEFORE INSERT ON `places` FOR EACH ROW BEGIN
    IF NEW.numtable = 0 THEN
        SET NEW.statutplace = 'indisponible';
    ELSE
        SET NEW.statutplace = 'disponible';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `idreservation` int NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `datecheckin` date NOT NULL,
  `timecheckin` varchar(50) NOT NULL,
  `timecheckout` varchar(10) NOT NULL,
  `numperson` int NOT NULL,
  `datereservation` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `statutreservation` enum('Confirmée','Annulée','Terminée') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Confirmée'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`idreservation`, `firstname`, `lastname`, `phone`, `datecheckin`, `timecheckin`, `timecheckout`, `numperson`, `datereservation`, `statutreservation`) VALUES
(2, 'Acobe', 'BONI', '53117212', '2024-05-08', '11h30', '12:30', 3, '2024-05-12 01:28:01', 'Confirmée'),
(3, 'Mohamed', 'Kiboyou', '48281052', '2024-05-14', '11:30', '12:30', 2, '2024-05-12 18:44:32', 'Terminée'),
(4, 'Fanny', 'Faga', '53117212', '2024-05-06', '11:30', '12:30', 4, '2024-05-12 18:46:24', 'Annulée'),
(5, 'Christian', 'Ahikpa', '53117212', '2024-05-14', '17:00', '18:00', 2, '2024-05-12 18:47:10', 'Terminée'),
(6, 'BILONG ', 'Emmanuel', '53127212', '2024-05-13', '11:30', '12:30', 1, '2024-05-13 09:25:45', 'Terminée');

--
-- Triggers `reservations`
--
DELIMITER $$
CREATE TRIGGER `update_places` AFTER INSERT ON `reservations` FOR EACH ROW BEGIN
    DECLARE available_place_id INT;
    
    -- Recherche d'une place disponible avec un nombre de personnes correspondant
    SELECT idplace INTO available_place_id
    FROM places
    WHERE numperson = NEW.numperson AND numtable > 0
    LIMIT 1;
    
    -- Si une place est trouvée, décrémenter numtable de 1
    IF available_place_id IS NOT NULL THEN
        UPDATE places
        SET numtable = numtable - 1
        WHERE idplace = available_place_id;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_places_after_reservation_update` AFTER UPDATE ON `reservations` FOR EACH ROW BEGIN
    DECLARE change_status BOOLEAN;
    DECLARE affected_persons INT;
    
    -- Vérifier si le statut de la réservation a changé
    IF OLD.statutreservation != NEW.statutreservation THEN
        SET change_status = TRUE;
    ELSE
        SET change_status = FALSE;
    END IF;
    
    -- Si le statut de la réservation est passé à Terminé ou Annulé
    IF change_status AND (NEW.statutreservation = 'Terminée' OR NEW.statutreservation = 'Annulée') THEN
        -- Récupérer le nombre de personnes de la réservation
        SET affected_persons = NEW.numperson;
        
        -- Incrémenter numtable dans places pour chaque personne affectée
        UPDATE places
        SET numtable = numtable + 1
        WHERE numperson = affected_persons;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `login`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`idplace`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`idreservation`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `places`
--
ALTER TABLE `places`
  MODIFY `idplace` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `idreservation` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
