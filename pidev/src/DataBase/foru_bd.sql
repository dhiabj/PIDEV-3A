-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2022 at 12:54 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `foru_bd`
--

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `total` float NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`id`, `etat`, `date`, `total`, `user_id`) VALUES
(1, 'valide', '2022-03-03', 0, 1),
(2, 'valide', '2022-03-02', 18, 1),
(3, 'valide', '2022-03-02', 62, 1),
(4, 'valide', '2022-03-02', 27, 1),
(5, 'valide', '2022-03-02', 0, 1),
(6, 'valide', '2022-03-02', 15, 1),
(7, 'en attente', '2022-03-10', 0, 1),
(8, 'en attente', '2022-03-04', 31, 1),
(9, 'en attente', '2022-03-04', 0, 1),
(10, 'valide', '2022-03-03', 16, 1),
(11, 'valide', '2022-03-03', 8, 1),
(12, 'valide', '2022-03-04', 10, 1),
(13, 'valide', '2022-03-04', 17, 1),
(14, 'valide', '2022-03-04', 8, 1),
(15, 'valide', '2022-03-04', 33.75, 1),
(16, 'valide', '2022-03-05', 8, 1),
(17, 'valide', '2022-03-05', 6.75, 1),
(18, 'valide', '2022-03-05', 24, 1),
(19, 'valide', '2022-03-05', 18, 1),
(20, 'valide', '2022-03-05', 14.25, 1),
(21, 'valide', '2022-03-06', 17, 1);

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `nbr_personnes` int(11) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `date`, `nbr_personnes`, `categorie`, `description`) VALUES
(1, 'Vegan4ever', '2022-02-18', 4, 'Vegan', 'Welcome here my friends');

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`id`, `nom`, `quantite`) VALUES
(1, 'fdsfsd', 56);

-- --------------------------------------------------------

--
-- Table structure for table `livraison`
--

CREATE TABLE `livraison` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `livreur_id` int(11) NOT NULL,
  `comande_id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `prix` float NOT NULL,
  `ingredients` varchar(255) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `titre`, `description`, `prix`, `ingredients`, `categorie`, `image`) VALUES
(1, 'Pizza Marinera', 'Pizza Marinera', 8, 'Thon, oignons, olives noires, champignons, fromage, pizza sauce', 'Normal', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\pizza_marinera_22503_600_square.jpg'),
(2, 'Pizza Chicken BBQ', 'Pizza Chicken BBQ', 15, 'Poulet grillé, champignons frais, oignons doux, fromage, sauce BBQ', 'Normal', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\BBQ-Chicken-Pizza-one-slice.jpg'),
(3, 'Pizza The Hawaiien', 'Pizza The Hawaiien', 9, 'Jambon de dinde, ananas, fromage, pizza sauce', 'Normal', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\hawaiian-pizza-16-1200.jpg'),
(4, 'Garden Salad', 'Garden Salad', 10, 'Tomates, oignons, olives noires, poivrons, concombre, sur un lit de laitue fraiche, dressée avec la sauce Ranch', 'Vegan', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\Garden-Salad_47-SQ.jpg'),
(5, 'Bouddha bowl au falafels', 'Bouddha bowl au falafels', 19, 'Falafel,riz basmati,sauce soja', 'Vegan', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\904d0e97-0221-4d82-9ea7-a035b476863b.jpg'),
(6, 'Burger classic Vegan', 'Burger classic Vegan', 12, 'Steak végan,tomate,oignons,salade de saison', 'Vegan', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\Burger-31LGH-a296a356-020c-4969-86e8-d8c26139f83f-0-1400x919.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `menu_commande`
--

CREATE TABLE `menu_commande` (
  `id` int(11) NOT NULL,
  `command_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu_commande`
--

INSERT INTO `menu_commande` (`id`, `command_id`, `menu_id`) VALUES
(1, 1, 1),
(2, 1, 4),
(3, 1, 5),
(4, 1, 6),
(5, 2, 1),
(6, 2, 2),
(7, 2, 6),
(8, 2, 3),
(9, 2, 1),
(10, 2, 4),
(11, 2, 2),
(12, 3, 4),
(13, 3, 1),
(14, 3, 3),
(15, 4, 6),
(16, 5, 2),
(17, 5, 2),
(18, 6, 6),
(19, 6, 4),
(20, 6, 1),
(21, 7, 5),
(22, 7, 6),
(23, 8, 3),
(24, 8, 1),
(25, 8, 6),
(40, 10, 1),
(41, 10, 1),
(43, 11, 1),
(44, 12, 4),
(46, 13, 1),
(47, 13, 3),
(49, 14, 1),
(50, 15, 3),
(54, 15, 3),
(56, 15, 1),
(57, 15, 5),
(58, 16, 1),
(59, 17, 3),
(60, 18, 3),
(61, 18, 2),
(62, 19, 2),
(63, 19, 3),
(64, 20, 5),
(65, 21, 1),
(66, 21, 3);

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `raison` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

CREATE TABLE `reponse` (
  `id` int(11) NOT NULL,
  `reponse` varchar(255) NOT NULL,
  `reclamation_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `user_id`, `event_id`, `nom`) VALUES
(2, 2, 1, 'Smayar Reservation');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `num_tel` varchar(8) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `date`, `num_tel`, `adresse`, `role`) VALUES
(1, 'dhia', 'Bejaoui', 'dhiabejaoui@gmail.com', '61959959', '2022-02-18', '53404720', '20 Mars', 'Client'),
(2, 'Ahlem', 'Mart Smayar', 'yyy', '47bce5c74f589f4867dbd57e9ca9f808', '2022-03-24', '22510165', 'aaa', 'Client'),
(3, 'Smayer', 'Samir', 'aaa', '47bce5c74f589f4867dbd57e9ca9f808', '2022-02-18', '22510164', 'marsa', 'Admin'),
(13, 'fatheya', 'mounira', 'eee', '47bce5c74f589f4867dbd57e9ca9f808', '2022-03-23', '22510144', 'tunis', 'Livreur'),
(14, 'aaa', 'aaa', 'nairouza.shili@gmail.com', '47bce5c74f589f4867dbd57e9ca9f808', '2022-03-16', '22510114', 'aaa', 'Client');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Indexes for table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `livreur_id` (`livreur_id`),
  ADD KEY `comande_id` (`comande_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `titre` (`titre`);

--
-- Indexes for table `menu_commande`
--
ALTER TABLE `menu_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `command_id` (`command_id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `reclamation_id` (`reclamation_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `event_id` (`event_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `menu_commande`
--
ALTER TABLE `menu_commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `livraison_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `livraison_ibfk_2` FOREIGN KEY (`livreur_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `livraison_ibfk_3` FOREIGN KEY (`comande_id`) REFERENCES `commande` (`id`);

--
-- Constraints for table `menu_commande`
--
ALTER TABLE `menu_commande`
  ADD CONSTRAINT `menu_commande_ibfk_1` FOREIGN KEY (`command_id`) REFERENCES `commande` (`id`),
  ADD CONSTRAINT `menu_commande_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`);

--
-- Constraints for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `reponse_ibfk_1` FOREIGN KEY (`reclamation_id`) REFERENCES `reclamation` (`id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `evenement` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
