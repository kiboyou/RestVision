-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.30 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour rest_vision
CREATE DATABASE IF NOT EXISTS `rest_vision` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rest_vision`;

-- Listage de la structure de table rest_vision. commande
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL DEFAULT '0',
  `menu_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `quantite` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table rest_vision.commande : ~12 rows (environ)
INSERT INTO `commande` (`id`, `client_id`, `menu_id`, `nom`, `type`, `prix`, `quantite`, `date`) VALUES
	(1, 1, 'ME-01', 'Salade Mexicaine', 'Entrée', 90, 5, '2024-05-14 04:28:54'),
	(2, 1, 'MD-01', 'Gateau au raisin sec', 'Dessert', 75, 5, '2024-05-14 04:32:39'),
	(3, 1, 'ME-01', 'Salade Mexicaine', 'Entrée', 90, 5, '2024-05-14 04:44:33'),
	(4, 1, 'MP-01', 'Couscous Tunisien', 'Plat Principal', 40, 5, '2024-05-14 04:50:59'),
	(5, 1, 'MD-01', 'Gateau au raisin sec', 'Dessert', 150, 10, '2024-05-14 05:15:01'),
	(6, 1, 'MP-01', 'Couscous Tunisien', 'Plat Principal', 80, 10, '2024-05-14 21:49:40'),
	(7, 1, 'MP-01', 'Couscous Tunisien', 'Plat Principal', 80, 10, '2024-05-15 00:13:04'),
	(8, 2, 'ME-01', 'Salade Mexicaine', 'Entrée', 180, 10, '2024-05-15 00:19:06'),
	(9, 2, 'MP-01', 'Couscous Tunisien', 'Plat Principal', 80, 10, '2024-05-15 00:19:16'),
	(10, 2, 'MD-01', 'Gateau au raisin sec', 'Dessert', 150, 10, '2024-05-15 00:19:31'),
	(15, 3, 'ME-01', 'Salade Mexicaine', 'Entrée', 90, 5, '2024-05-15 00:56:45'),
	(16, 4, 'ME-01', 'Salade Mexicaine', 'Entrée', 90, 5, '2024-05-15 00:57:54');

-- Listage de la structure de table rest_vision. menu
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `menu_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table rest_vision.menu : ~3 rows (environ)
INSERT INTO `menu` (`id`, `menu_id`, `nom`, `type`, `prix`, `status`) VALUES
	(1, 'ME-01', 'Salade Mexicaine', 'Entrée', 18, 'Disponible'),
	(2, 'MP-01', 'Couscous Tunisien', 'Plat Principal', 8, 'Disponible'),
	(4, 'MD-01', 'Gateau au raisin sec', 'Dessert', 15, 'Disponible');

-- Listage de la structure de table rest_vision. recu
CREATE TABLE IF NOT EXISTS `recu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table rest_vision.recu : ~4 rows (environ)
INSERT INTO `recu` (`id`, `client_id`, `total`, `date`) VALUES
	(1, 1, 605, '2024-05-15 00:14:28'),
	(2, 2, 410, '2024-05-15 00:19:54'),
	(3, 3, 180, '2024-05-15 00:57:26'),
	(4, 4, 130, '2024-05-15 01:04:49');

-- Listage de la structure de table rest_vision. users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table rest_vision.users : ~1 rows (environ)
INSERT INTO `users` (`id`, `login`, `password`) VALUES
	(1, 'admin', 'admin');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
