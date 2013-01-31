-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le: Jeu 31 Janvier 2013 à 09:43
-- Version du serveur: 5.5.27-log
-- Version de PHP: 5.4.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `guide`
--

-- --------------------------------------------------------

--
-- Structure de la table `batiment`
--

CREATE TABLE IF NOT EXISTS `batiment` (
  `Id` int(3) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `id_image` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batiment`
--

INSERT INTO `batiment` (`Id`, `nom`, `description`, `id_image`) VALUES
(1, 'batiment 1', 'desciption batiment 1', 'ind'),
(2, 'batiment 2', 'desciption batiment 2', 'ind'),
(3, 'batiment 3', 'desciption batiment 3', 'ind'),
(4, 'batiment 4', 'desciption batiment 4', 'ind'),
(5, 'batiment 5', 'desciption batiment 5', 'ind'),
(6, 'batiment 6', 'desciption batiment 6', 'ind'),
(7, 'batiment 7', 'description batiment 7', 'ind'),
(8, 'batiment 8', 'description batiment 8', 'ind'),
(9, 'batiment 9', 'description batiment 9', 'ind'),
(10, 'batiment 10', 'description batiment 10', 'ind'),
(11, 'batiment 11', 'description batiment 11', 'ind'),
(12, 'batiment 12', 'description batiment 12', 'ind'),
(13, 'batiment 13', 'description batiment 13', 'ind'),
(14, 'batiment 14', 'description batiment 14', 'ind'),
(15, 'batiment 15', 'description batiment 15', 'ind'),
(16, 'batiment 16', 'description batiment 16', 'ind'),
(17, 'batiment 17', 'description batiment 17', 'ind'),
(18, 'batiment 18', 'description batiment 18', 'ind'),
(19, 'batiment 19', 'description batiment 19', 'ind'),
(20, 'batiment 20', 'description batiment 20', 'ind'),
(21, 'batiment 21', 'description batiment 21', 'ind'),
(22, 'batiment 22', 'description batiment 22', 'ind'),
(23, 'batiment 23', 'description batiment 23', 'ind'),
(24, 'batiment 24', 'description batiment 24', 'ind'),
(25, 'batiment 25', 'description batiment 25', 'ind'),
(26, 'batiment 26', 'description batiment 26', 'ind'),
(27, 'batiment 27', 'description batiment 27', 'ind'),
(28, 'batiment 28', 'description batiment 28', 'ind'),
(29, 'MDE', 'description batiment 29', 'ind'),
(35, 'Restaurant des personnels', 'description batiment 35', 'ind'),
(31, 'Residence Universitaire Minerve 1', 'description batiment 31', 'ind'),
(32, 'Residence Universitaire Minerve 2', 'description batiment 32', 'ind'),
(33, 'Polytech', 'description batiment 33', 'ind'),
(34, 'IAE', 'description batiment 34', 'ind'),
(30, 'F.S.M', 'description batiment 30', 'ind');

-- --------------------------------------------------------

--
-- Structure de la table `historique`
--

CREATE TABLE IF NOT EXISTS `historique` (
  `nom` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `points_reference`
--

CREATE TABLE IF NOT EXISTS `points_reference` (
  `Id` int(5) NOT NULL AUTO_INCREMENT,
  `Id_Batiment` int(3) NOT NULL,
  `Type_point` varchar(10) NOT NULL,
  `Latitude` double NOT NULL,
  `Longitude` double NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=193 ;

--
-- Contenu de la table `points_reference`
--

INSERT INTO `points_reference` (`Id`, `Id_Batiment`, `Type_point`, `Latitude`, `Longitude`) VALUES
(1, 1, 'centre', 43.634165, 3.862327),
(2, 2, 'centre', 43.634014, 3.861779),
(3, 3, 'centre', 43.633878, 3.86135),
(4, 4, 'centre', 43.633726, 3.860878),
(5, 5, 'centre', 43.633031, 3.862122),
(6, 6, 'centre', 43.632674, 3.863995),
(7, 7, 'centre', 43.631637, 3.863475),
(8, 8, 'centre', 43.631412, 3.864483),
(9, 9, 'centre', 43.631043, 3.866001),
(10, 10, 'centre', 43.631443, 3.865803),
(11, 11, 'centre', 43.631785, 3.865593),
(12, 12, 'centre', 43.632294, 3.865663),
(13, 13, 'centre', 43.632647, 3.865492),
(14, 14, 'centre', 43.633055, 3.86525),
(15, 15, 'centre', 43.633369, 3.865003),
(16, 16, 'centre', 43.633792, 3.864864),
(17, 17, 'centre', 43.634142, 3.866484),
(18, 18, 'centre', 43.633722, 3.866597),
(19, 19, 'centre', 43.634056, 3.867273),
(20, 20, 'centre', 43.632923, 3.866618),
(21, 21, 'centre', 43.633074, 3.86702),
(22, 22, 'centre', 43.632204, 3.867026),
(26, 26, 'centre', 43.630869, 3.868168),
(25, 25, 'centre', 43.631237, 3.867954),
(24, 24, 'centre', 43.631451, 3.86746),
(23, 23, 'centre', 43.631843, 3.867214),
(27, 27, 'centre', 43.630065, 3.86783),
(28, 28, 'centre', 43.63043, 3.868956),
(29, 29, 'centre', 43.632061, 3.860759),
(30, 35, 'centre', 43.634922, 3.860755),
(31, 31, 'centre', 43.634908, 3.861101),
(32, 32, 'centre', 43.63473, 3.860479),
(33, 33, 'centre', 43.632713, 3.86254),
(34, 34, 'centre', 43.632441, 3.861564),
(35, 1, 'lateral', 43.634786, 3.862102),
(36, 1, 'lateral', 43.63472, 3.861882),
(37, 1, 'lateral', 43.63361, 3.862759),
(38, 1, 'lateral', 43.633544, 3.862539),
(39, 2, 'lateral', 43.634563, 3.861353),
(40, 2, 'lateral', 43.634625, 3.861573),
(41, 2, 'lateral', 43.633453, 3.862228),
(42, 2, 'lateral', 43.633388, 3.862013),
(43, 3, 'lateral', 43.634497, 3.86112),
(44, 3, 'lateral', 43.634431, 3.860908),
(45, 3, 'lateral', 43.63333, 3.861804),
(46, 3, 'lateral', 43.633256, 3.861571),
(47, 4, 'lateral', 43.633177, 3.861289),
(48, 4, 'lateral', 43.633113, 3.861074),
(49, 4, 'lateral', 43.634355, 3.86064),
(50, 4, 'lateral', 43.634291, 3.860423),
(51, 5, 'lateral', 43.633447, 3.863196),
(52, 5, 'lateral', 43.633313, 3.863274),
(53, 5, 'lateral', 43.632643, 3.861018),
(54, 5, 'lateral', 43.632777, 3.860946),
(55, 1, 'chemin', 43.633507, 3.862705),
(56, 2, 'chemin', 43.633348, 3.862155),
(57, 3, 'chemin', 43.63322, 3.861721),
(58, 4, 'chemin', 43.633074, 3.861217),
(59, 5, 'chemin', 43.632891, 3.862196),
(60, 6, 'chemin', 43.632791, 3.864258),
(61, 7, 'chemin', 43.631517, 3.86297),
(62, 8, 'chemin', 43.631556, 3.864971),
(63, 9, 'chemin', 43.630923, 3.865521),
(64, 10, 'chemin', 43.631284, 3.86532),
(65, 11, 'chemin', 43.631647, 3.865116),
(66, 12, 'chemin', 43.632057, 3.864891),
(67, 13, 'chemin', 43.632404, 3.86469),
(68, 14, 'chemin', 43.632822, 3.864451),
(69, 15, 'chemin', 43.63314, 3.864269),
(70, 16, 'chemin', 43.633563, 3.86403),
(71, 17, 'chemin', 43.633946, 3.865768),
(72, 18, 'chemin', 43.6336, 3.865961),
(73, 19, 'chemin', 43.633743, 3.867678),
(74, 20, 'chemin', 43.632899, 3.866334),
(75, 21, 'chemin', 43.633175, 3.867276),
(76, 22, 'chemin', 43.63241, 3.867989),
(77, 23, 'chemin', 43.63213, 3.868142),
(78, 24, 'chemin', 43.631738, 3.868416),
(79, 25, 'chemin', 43.631457, 3.868617),
(80, 26, 'chemin', 43.631092, 3.868882),
(81, 27, 'chemin', 43.629925, 3.867969),
(82, 28, 'chemin', 43.630772, 3.868688),
(83, 29, 'chemin', 43.632076, 3.861124),
(84, 35, 'chemin', 43.634658, 3.860356),
(85, 31, 'chemin', 43.634658, 3.860356),
(86, 32, 'chemin', 43.634658, 3.860356),
(87, 33, 'chemin', 43.632946, 3.862412),
(88, 34, 'chemin', 43.632674, 3.861441),
(89, 5, 'lateral', 43.632886, 3.861267),
(90, 5, 'lateral', 43.632924, 3.861391),
(91, 5, 'lateral', 43.632973, 3.861581),
(92, 5, 'lateral', 43.633027, 3.861788),
(93, 5, 'lateral', 43.633107, 3.862002),
(94, 5, 'lateral', 43.633142, 3.862126),
(95, 5, 'lateral', 43.633175, 3.862276),
(96, 5, 'lateral', 43.633247, 3.862517),
(97, 5, 'lateral', 43.633324, 3.862748),
(98, 5, 'lateral', 43.633357, 3.862863),
(99, 5, 'lateral', 43.633167, 3.862831),
(100, 5, 'lateral', 43.633146, 3.862697),
(101, 5, 'lateral', 43.633058, 3.862402),
(102, 5, 'lateral', 43.632983, 3.862212),
(103, 5, 'lateral', 43.632948, 3.862094),
(104, 5, 'lateral', 43.632919, 3.86193),
(105, 5, 'lateral', 43.632833, 3.861643),
(106, 5, 'lateral', 43.632763, 3.861474),
(107, 5, 'lateral', 43.632726, 3.861356),
(108, 6, 'lateral', 43.633235, 3.863805),
(109, 6, 'lateral', 43.633179, 3.863614),
(110, 6, 'lateral', 43.632146, 3.864414),
(111, 6, 'lateral', 43.632092, 3.864223),
(112, 6, 'lateral', 43.63235, 3.864052),
(113, 6, 'lateral', 43.632641, 3.863917),
(114, 7, 'lateral', 43.632, 3.863558),
(115, 7, 'lateral', 43.631296, 3.863389),
(116, 7, 'lateral', 43.631857, 3.863083),
(117, 8, 'lateral', 43.631548, 3.864078),
(118, 8, 'lateral', 43.631098, 3.864347),
(119, 8, 'lateral', 43.631259, 3.864886),
(120, 8, 'lateral', 43.631721, 3.86465),
(121, 9, 'lateral', 43.63101, 3.865666),
(122, 9, 'lateral', 43.630892, 3.865736),
(123, 9, 'lateral', 43.631187, 3.866267),
(124, 9, 'lateral', 43.631067, 3.866334),
(125, 10, 'lateral', 43.631478, 3.866227),
(126, 10, 'lateral', 43.631447, 3.866122),
(127, 10, 'lateral', 43.631276, 3.865546),
(128, 10, 'lateral', 43.631253, 3.865468),
(129, 10, 'lateral', 43.631399, 3.865379),
(130, 10, 'lateral', 43.631422, 3.865449),
(131, 10, 'lateral', 43.631597, 3.866047),
(132, 10, 'lateral', 43.631628, 3.866146),
(133, 11, 'lateral', 43.631635, 3.865326),
(134, 11, 'lateral', 43.631766, 3.865253),
(135, 11, 'lateral', 43.631942, 3.865857),
(136, 11, 'lateral', 43.631814, 3.865929),
(137, 12, 'lateral', 43.63202, 3.865036),
(138, 12, 'lateral', 43.632175, 3.86495),
(139, 12, 'lateral', 43.632573, 3.866329),
(140, 12, 'lateral', 43.632422, 3.866412),
(141, 12, 'lateral', 43.63256, 3.866272),
(142, 12, 'lateral', 43.632193, 3.865006),
(143, 13, 'lateral', 43.632406, 3.864899),
(144, 13, 'lateral', 43.632527, 3.864832),
(145, 13, 'lateral', 43.632897, 3.866085),
(146, 13, 'lateral', 43.632773, 3.866154),
(147, 14, 'lateral', 43.633179, 3.865991),
(148, 14, 'lateral', 43.633338, 3.8659),
(149, 14, 'lateral', 43.63294, 3.864529),
(150, 14, 'lateral', 43.632787, 3.864609),
(151, 14, 'lateral', 43.632954, 3.864569),
(152, 14, 'lateral', 43.633293, 3.865739),
(153, 15, 'lateral', 43.633474, 3.865626),
(154, 15, 'lateral', 43.633606, 3.865554),
(155, 15, 'lateral', 43.63327, 3.864416),
(156, 15, 'lateral', 43.633142, 3.864486),
(157, 16, 'lateral', 43.633532, 3.864253),
(158, 16, 'lateral', 43.633519, 3.864204),
(159, 16, 'lateral', 43.633678, 3.864121),
(160, 16, 'lateral', 43.63408, 3.865492),
(161, 16, 'lateral', 43.633922, 3.865583),
(162, 16, 'lateral', 43.633862, 3.86539),
(163, 17, 'lateral', 43.633953, 3.86609),
(164, 17, 'lateral', 43.633994, 3.86623),
(165, 17, 'lateral', 43.63419, 3.866903),
(166, 17, 'lateral', 43.634204, 3.866943),
(167, 17, 'lateral', 43.634332, 3.866868),
(168, 17, 'lateral', 43.634081, 3.866023),
(169, 30, 'centre', 43.632084, 3.863837),
(170, 30, 'chemin', 43.632162, 3.864623),
(172, 30, 'lateral', 43.631964, 3.863647),
(173, 30, 'lateral', 43.632041, 3.863604),
(174, 30, 'lateral', 43.632193, 3.863794),
(175, 30, 'lateral', 43.632198, 3.863909),
(176, 30, 'lateral', 43.632171, 3.864044),
(177, 30, 'lateral', 43.632094, 3.864086),
(178, 33, 'lateral', 43.632975, 3.863078),
(179, 33, 'lateral', 43.632851, 3.862651),
(180, 33, 'lateral', 43.632771, 3.862383),
(181, 33, 'lateral', 43.632662, 3.861997),
(182, 33, 'lateral', 43.632428, 3.862061),
(183, 33, 'lateral', 43.632538, 3.862681),
(184, 33, 'lateral', 43.632837, 3.863081),
(185, 34, 'lateral', 43.632261, 3.86167),
(186, 34, 'lateral', 43.632546, 3.861514),
(187, 34, 'lateral', 43.63241, 3.861423),
(188, 34, 'lateral', 43.632266, 3.861506),
(189, 34, 'lateral', 43.632363, 3.861807),
(190, 34, 'lateral', 43.632521, 3.861723),
(191, 34, 'lateral', 43.632563, 3.861592),
(192, 18, 'lateral', 43.633794, 3.866015);

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE IF NOT EXISTS `salle` (
  `Id` int(5) NOT NULL AUTO_INCREMENT,
  `nom` varchar(256) NOT NULL,
  `Id_batiment` int(2) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=95 ;

--
-- Contenu de la table `salle`
--

INSERT INTO `salle` (`Id`, `nom`, `Id_batiment`) VALUES
(1, 'TP Mécaniqur', 1),
(2, 'TP EEA', 1),
(3, 'TP Biochimie-Physiologie', 1),
(4, 'Secrétariat du département Mécanique (Rez de chaussée)', 1),
(5, 'Salle de Cours 1.01', 1),
(6, 'TD 1.01', 1),
(7, 'TP Physique', 2),
(8, 'Salles de TD', 2),
(9, 'Service Commun d''Action Sociale (SCAS)', 2),
(10, 'TP Chimie', 3),
(11, 'Salles de TD', 3),
(12, 'TP du département Biologie- Ecologie-Environnement', 4),
(13, 'Salles de TD', 4),
(14, 'Secrétariat du Département des Langues', 5),
(15, 'Laboratoire des langues', 5),
(16, 'Cafetaria', 5),
(17, 'SUAPS', 5),
(18, 'Centre de Formation par apprentissage', 5),
(19, 'Amphi 5.01', 5),
(20, 'Salles de TD', 5),
(21, 'Salles de TD informatisée', 5),
(22, 'Planning + objets trouvés', 6),
(23, 'Relations Internationales', 6),
(24, 'Conventions de stages', 6),
(25, 'Service relations aux Entreprises (SIPEF)', 6),
(26, 'Amphis 6.01 à 6.04', 6),
(27, 'Salles de TP et TD informatique', 6),
(28, 'Amphi Dumontet', 7),
(29, 'Bibliothèque Universitaire des sciences', 8),
(30, 'CASSIOPEE', 8),
(31, 'Secrétariat Département DEScire', 9),
(32, 'Secrétariat département Mathématique (2ème étage)', 9),
(33, 'Bibliothèque Mathématiques (pour étudiants niveau M)', 9),
(34, 'Salles de cours + salles de TD', 9),
(35, 'Salle de cours 10.01', 10),
(36, 'Salles informatiques en libre service', 10),
(37, 'Direction des Services Informatiques (DSI)', 10),
(38, 'Amphi Minéralogie (Rez de chaussée, département DESTEEM)', 11),
(39, 'Secrétariat département Biologie-Ecologie (2ème étage)', 22),
(40, 'Secrétariat département DESTEEM (1er étage)', 22),
(41, 'TP du département DESTEEM', 23),
(42, 'Salle de cours 23.01', 23),
(43, 'Secrétariat du département Biochimie-Physiologie (4ème étage)', 24),
(44, 'TP de Physiologie végétale (département Biochimie-Physiologie)', 25),
(45, 'TP de Psychophysiologie (département Biochimie-Physiologie)', 25),
(46, 'Salle de cours 25..01', 25),
(47, 'Salles de TD Informatique', 25),
(48, 'TP de Physiologie Animale (département Biochimie-Physiologie)', 26),
(49, 'TP du département Biologie-Ecologie-Environnement', 26),
(50, 'TP de Microbiologie (département Biochimie-Physiologie)', 26),
(51, 'TP de Physique', 12),
(52, 'TP EEA', 12),
(53, 'Salle de cours 12.01', 12),
(54, 'Salle de TD 12.02', 12),
(55, 'Secrétariat  département EEA (Rez de chaussée)', 13),
(56, 'Bureau FdS (Rez de chaussée)', 13),
(57, 'Salle de réunion FdS (Rez de chaussée)', 13),
(58, 'Secrétariat département Physique (1er étage)', 13),
(59, 'TP Automatique, Electrotechnique (département EEA)', 14),
(60, 'TP CAPES Physique-Chimie', 16),
(61, 'TP de Chimie', 16),
(62, 'Salles de TP Informatique', 16),
(63, 'Secrétariat département Informatique', 16),
(64, 'Salle de cours 16.01', 16),
(65, 'Salles de TD 16.01 et 16.02', 16),
(66, 'TP de Chimie ', 19),
(67, 'Secrétariat département Chimie (Rez de chaussée)', 19),
(68, 'Salles de TD informatique (département de chimie)', 19),
(69, 'Pile à combustible (Département Chimie)', 19),
(70, 'Salles de TD', 19),
(71, 'CAPES Physique-Chimie (partie Physique)', 20),
(72, 'Médecine préventive (SCOOPS)', 20),
(73, 'Cellule handicap', 20),
(74, 'Infirmerie', 20),
(75, 'Salles de cours 20.01 et 20.02', 20),
(76, 'Salles de TD 20.02 et 20.04', 20),
(77, 'Administration UM2', 7),
(78, 'Agence Comptable (paiement des droits d''inscriptions)', 7),
(79, 'Direction du Patrimoine et des Infrastructures (DPI) ', 27),
(80, 'Institut d''Administration des Entreprises (IAE)', 29),
(81, 'Polytech'' Montpellier', 31),
(82, 'Maison des Etudiants (MDE', 38),
(83, 'Bureaux de la direction de la Faculté des Sciences : direction centrale', 30),
(84, 'Amphi 5.02', 5),
(85, 'Amphi 5.03', 5),
(86, 'Amphi 5.04', 5),
(87, 'Amphi 5.05', 5),
(88, 'Amphi 5.06', 5),
(89, 'Amphi 5.02', 5),
(90, 'Amphi 5.03', 5),
(91, 'Amphi 5.04', 5),
(92, 'Amphi 5.05', 5),
(93, 'Amphi 5.06', 5),
(94, 'TD 1.02', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
