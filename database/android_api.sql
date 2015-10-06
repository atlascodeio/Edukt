-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 03-10-2015 a las 14:38:58
-- Versión del servidor: 5.5.42-cll-lve
-- Versión de PHP: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `android_api`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docs`
--

CREATE TABLE IF NOT EXISTS `docs` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `url_doc` varchar(250) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `img_url` varchar(250) NOT NULL,
  `users_id` int(11) NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `users_id` (`users_id`) COMMENT 'users_id'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `docs`
--

INSERT INTO `docs` (`uid`, `nombre`, `tipo`, `url_doc`, `created_at`, `updated_at`, `descripcion`, `img_url`, `users_id`) VALUES
(1, 'Plan de Evaluacion', 'excel', 'http://i3soluciones.com/edukt/docs/PySide GUI Application Development.pdf', '2015-04-24 01:01:01', '2015-03-24 00:00:00', 'Plan de Evaluacion de matematica 2015', '', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `migration`
--

CREATE TABLE IF NOT EXISTS `migration` (
  `version` varchar(180) NOT NULL,
  `apply_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `migration`
--

INSERT INTO `migration` (`version`, `apply_time`) VALUES
('m000000_000000_base', 1433652332),
('m150214_044831_init_user', 1433652338);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notas`
--

CREATE TABLE IF NOT EXISTS `notas` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `users_id` int(11) NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `users_id` (`users_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

--
-- Volcado de datos para la tabla `notas`
--

INSERT INTO `notas` (`uid`, `nombre`, `descripcion`, `created_at`, `updated_at`, `users_id`) VALUES
(1, 'Faltare', 'No podre ir a clases, suspendido el examen!', '2015-06-01 00:00:00', '2015-06-01 00:00:00', 10),
(2, 'Fecha tope trabajo Metodología', 'Muchachos tienen fecha tope para el trabajo el 15 de junio. No habra Prorroga!', '2015-06-02 00:00:00', '2015-06-02 00:00:00', 11),
(3, '', 'Leer el documento de estandares de programacion en Django! Prueba sospresa hoy!', '2015-06-02 00:00:00', '2015-06-02 00:00:00', 13),
(4, '', 'Chapon! gracias por registrarte!', '2015-06-03 19:09:07', '0000-00-00 00:00:00', 10),
(5, '', 'hoy evaluacion programacion 4!', '2015-06-04 00:49:25', '0000-00-00 00:00:00', 10),
(21, '', 'Defensa de tesis el lunes 08/06/15!!!', '2015-06-05 04:09:18', '0000-00-00 00:00:00', 13),
(22, '', 'Invitacion a la presentacion Edukt en el aula a8', '2015-06-08 09:00:01', '0000-00-00 00:00:00', 10),
(23, '', 'Presentacion de edukt en la sala de eventos especiales 3:00pm no falten!', '2015-06-08 12:28:10', '0000-00-00 00:00:00', 10),
(24, '', 'Evaluacion de Matematica en aula 8 3:00 pm', '2015-06-08 12:58:50', '0000-00-00 00:00:00', 10),
(33, '', 'Saludos a toda la comunidad de la UPTM', '2015-07-02 14:36:33', '0000-00-00 00:00:00', 10),
(37, '', 'testing hora 1:00', '2015-07-03 13:00:12', '0000-00-00 00:00:00', 10),
(46, 'Hhh', 'Test ', '2015-10-02 10:32:28', '0000-00-00 00:00:00', 10),
(47, 'Hhh', 'Test ', '2015-10-02 10:32:30', '0000-00-00 00:00:00', 10),
(48, 'Leo', 'Test leo', '2015-10-02 10:34:00', '0000-00-00 00:00:00', 10),
(49, 'Prueba Vizio ', 'Franklin ', '2015-10-02 14:11:36', '0000-00-00 00:00:00', 10),
(50, '', 'PRueba de concepto aulas', '2015-10-02 14:13:53', '0000-00-00 00:00:00', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `profile_user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `profile`
--

INSERT INTO `profile` (`id`, `user_id`, `create_time`, `update_time`, `full_name`) VALUES
(1, 1, '2015-06-07 13:45:37', '2015-06-07 14:05:20', 'Carlos Malavé');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `can_admin` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `name`, `create_time`, `update_time`, `can_admin`) VALUES
(1, 'Admin', '2015-06-07 13:45:37', NULL, 1),
(2, 'User', '2015-06-07 13:45:37', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `universidad`
--

CREATE TABLE IF NOT EXISTS `universidad` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `universidad`
--

INSERT INTO `universidad` (`uid`, `nombre`, `created_at`, `updated_at`) VALUES
(1, 'UPTM', '2015-03-24 00:00:00', '2015-06-02 10:47:57'),
(2, 'UCAB', '2015-03-24 00:00:00', '2015-06-01 23:34:21'),
(3, 'UCV', '2015-03-24 00:00:00', '2015-06-01 23:34:04'),
(4, 'UNEFA', '2015-06-01 23:24:30', '2015-06-01 23:25:32'),
(5, 'USM', '2015-06-01 23:52:00', '0000-00-00 00:00:00'),
(7, 'IUTAV', '2015-06-02 14:14:14', '2015-06-05 03:22:32'),
(9, 'MARIA DE  LAS FUENTES', '2015-06-25 16:16:11', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `status` smallint(6) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `new_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `auth_key` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `api_key` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL,
  `create_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `ban_time` timestamp NULL DEFAULT NULL,
  `ban_reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email` (`email`),
  UNIQUE KEY `user_username` (`username`),
  KEY `user_role_id` (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `role_id`, `status`, `email`, `new_email`, `username`, `password`, `auth_key`, `api_key`, `login_ip`, `login_time`, `create_ip`, `create_time`, `update_time`, `ban_time`, `ban_reason`) VALUES
(1, 1, 1, 'hellfish2@gmail.com', NULL, 'hellfish2', '$2y$13$ZSsFmgi4bG7GpzhEMU84g.OR.2ZXmENKWCcgs1Pm9GknV4kW0FEGa', 'zKMCZMhzzzj3af0bwqFGskxkdXdepdRc', '6eMbfNEFG37Lh6cyi-VORCARTTEHWvl_', '201.208.136.231', '2015-10-02 10:50:23', NULL, '2015-06-07 13:45:37', '2015-06-07 14:05:20', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `unique_id` varchar(23) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `universidad_id` int(11) DEFAULT NULL,
  `tipo_user` enum('Alumno','Profesor') DEFAULT NULL,
  `cedula` int(8) DEFAULT NULL,
  `profile_pic` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `unique_id` (`unique_id`),
  KEY `universidad_id` (`universidad_id`) COMMENT 'universidad_id'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=76 ;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`uid`, `unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`, `universidad_id`, `tipo_user`, `cedula`, `profile_pic`) VALUES
(10, '560e0050a50405.05863724', 'Carlos Malave', 'hellfish2@gmail.com', 'Lt4Pa4D66ruLJnewDD0RhnXtBkU3ZDc0Y2NjYjVm', '7d74cccb5f', '2015-03-24 00:00:00', '2015-10-01 23:26:00', 1, 'Profesor', 17216369, 'http://i3soluciones.com/edukt/images/hellfish.jpg'),
(11, '557378ea411bb6.48092615', 'Juan Gomez', 'jgomezbjj@gmail.com', 'KdPwk95GPT3kp4KZteRrUomDmog1ZDM3MjY4OWM0', '5d372689c4', '2015-03-24 00:00:00', '2015-06-06 15:49:14', 4, 'Profesor', 14393103, 'http://i3soluciones.com/edukt/images/jgomezbjj.jpg'),
(13, '557378fda82119.23967299', 'Eduardo Morales', 'spiderbbc@gmail.com', 'RSs1Z2ECg6ZTRi8gy/DUp9tuMyBmMjMwMjE0ZmMx', 'f230214fc1', '2015-03-24 00:00:00', '2015-06-06 15:49:33', 7, 'Profesor', 10928039, 'http://i3soluciones.com/edukt/images/spiderbbc.jpg'),
(28, '556fd999b52342.70786370', 'Laura Mendoza', 'lmendoza@gmail.com', 'Hnou9jCbCQPrc5uMunsKXYwPYGs5NTVkZGYwYmEz', '955ddf0ba3', '2015-06-02 16:02:10', '2015-06-03 21:52:41', 1, 'Alumno', 6366625, 'http://bit.ly'),
(39, '5596be3fb94302.90610817', 'alumno', 'alumno@alumno.com', 'jIJ0Jrx0nrDCjFIjruMIiD0t0Ew0NWYxOTBhMTAw', '45f190a100', '2015-06-03 22:17:00', '2015-07-03 09:54:23', 1, 'Alumno', 12345678, 'http://bit.ly'),
(40, '5596be51090404.43661350', 'profesor', 'profesor@profesor.com', 'KVaoLk/zf5rdcw+25hzvV6+sxLQ0OTg3MjUzMTUz', '4987253153', '2015-06-03 22:17:37', '2015-07-03 09:54:41', 1, 'Profesor', 87654321, 'http://bit.ly'),
(56, '55737c312f84a5.98669283', 'Leonardo Caballero ', 'leonardocaballero@gmail.com', 'L+6bmtt/3V5soW+UlqxAm1ogtDRiMTJiNDJiZTQ2', 'b12b42be46', '2015-06-06 05:56:03', '2015-06-06 16:03:13', 1, 'Alumno', 14522590, 'http://i3soluciones.com/edukt/images/profile.png'),
(61, '5595fd5f09f096.51276373', 'Yudit sanchez ', 'Ysanch@gmail.com', 'hsvxOQvcIuNyU/sveXfTnEw6ru81NWRhZWY4NjVh', '55daef865a', '2015-06-07 02:10:16', '2015-07-02 20:11:27', 1, 'Profesor', 255565, 'http://bit.ly'),
(62, '55744db69f66b0.41378014', 'Mario Dominguez', 'Nipysback@gmail.com', 'MwvRWKhKJUg3MiQ4mIH1/nSOFE4wZjE0NWQ2ZTNl', '0f145d6e3e', '2015-06-07 06:57:10', NULL, 7, 'Alumno', 19022374, NULL),
(63, '5574fb881ae4e7.99003241', 'Harold', 'Harold_burton@hotmail.com', 'eWd2K8uy5RfhXMCdImaIbPA0E141NzgyNWFiM2E4', '57825ab3a8', '2015-06-07 19:11:07', '2015-06-07 19:18:48', 1, 'Alumno', 13785502, 'http://'),
(64, '5574fcf378a788.17053770', 'Giuseppe Gangi', 'giuseppemiro@gmail.com', 'LiTwPF9Kou4swy68hQdfHBpjJ2NlZWU0NDVkOTdi', 'eee445d97b', '2015-06-07 19:12:14', '2015-06-07 19:24:51', 1, 'Alumno', 19422694, 'http://bit.ly'),
(68, '5586451c0e9243.27054162', 'Mario Fernández ', 'alespcs@gmail.com ', 'D19KmmwMoYYJJLaywPu35kcjvSEyNDhhZTkzNmEx', '248ae936a1', '2015-06-20 22:01:16', NULL, 1, 'Alumno', 18261481, NULL),
(69, '55864d297499a5.04396578', 'Flamel Canto', 'canto.FLAMEL@estudiante.uptm.edu.ve', 'pwb1o69erNLOLo9tAUZGR2k6KDdhNDgxZGY0YzJl', 'a481df4c2e', '2015-06-20 22:35:37', NULL, 1, 'Alumno', 16445336, NULL),
(70, '5586c9cb79e5e3.33069367', 'argimiro', 'argimirovs04@gmail.com', 'sNq1PNdA3HiCYqzoqYt6l7ER/wk3MDhiN2JhNDQ1', '708b7ba445', '2015-06-21 07:27:23', NULL, 1, 'Alumno', 16715149, NULL),
(71, '558b2e7dcfead6.19386066', 'arg\nargimiro_v@hotmail.com', 'argimiro_v@hotmail.com', '37AQhrgMWQjq74ivH8f+ntUqx8YwZjZjOWFhNDI0', '0f6c9aa424', '2015-06-24 15:26:05', NULL, 1, 'Alumno', 16715149, NULL),
(72, '558ef6fa1835f7.35795300', 'Rafael Rivas', 'ricardochapon@gmail.com ', 'xnsiGS8Ld/skEPojaC9/tDV/GntkZjc5NmVlZGE4', 'df796eeda8', '2015-06-27 12:18:18', NULL, 1, 'Alumno', 13991296, NULL),
(74, '559e9d91309527.22509885', 'eliane Galavis ', 'eliane21711@gmail.com', 'ICp142TrNjmGuIClIRkNYR36L7pjZmRjZjNlNTg2', 'cfdcf3e586', '2015-07-09 09:13:05', NULL, 7, 'Alumno', 20114997, NULL),
(75, '55a48cd93d2f08.48133802', 'Ketty Noguera', 'ketulladevil@gmail.com', 'z09MVsRH7KZu/vm2ekZTfS4YJ1U1NmQwM2NlMTRi', '56d03ce14b', '2015-07-13 21:15:21', NULL, 1, 'Alumno', 3492527, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_auth`
--

CREATE TABLE IF NOT EXISTS `user_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `provider` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `provider_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `provider_attributes` text COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_auth_provider_id` (`provider_id`),
  KEY `user_auth_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_key`
--

CREATE TABLE IF NOT EXISTS `user_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` smallint(6) NOT NULL,
  `key_value` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `consume_time` timestamp NULL DEFAULT NULL,
  `expire_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_key_key` (`key_value`),
  KEY `user_key_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `docs`
--
ALTER TABLE `docs`
  ADD CONSTRAINT `docs_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`uid`);

--
-- Filtros para la tabla `notas`
--
ALTER TABLE `notas`
  ADD CONSTRAINT `notas_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`uid`);

--
-- Filtros para la tabla `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `profile_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`universidad_id`) REFERENCES `universidad` (`uid`);

--
-- Filtros para la tabla `user_auth`
--
ALTER TABLE `user_auth`
  ADD CONSTRAINT `user_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `user_key`
--
ALTER TABLE `user_key`
  ADD CONSTRAINT `user_key_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
