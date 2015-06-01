-- phpMyAdmin SQL Dump
-- version 4.2.12deb2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 01-06-2015 a las 17:11:08
-- Versión del servidor: 5.5.43-0+deb8u1
-- Versión de PHP: 5.6.7-1

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
`uid` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `url_doc` varchar(250) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `img_url` varchar(250) NOT NULL,
  `users_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `docs`
--

INSERT INTO `docs` (`uid`, `nombre`, `tipo`, `url_doc`, `created_at`, `updated_at`, `descripcion`, `img_url`, `users_id`) VALUES
(1, 'Plan de Evaluacion', 'excel', 'http://bit.ly/', '2015-04-24 00:00:00', '2015-03-24 00:00:00', 'Plan de Evaluacion de matematica 2015', 'http://bit.ly/', 10),
(2, 'Tarea Taller Quimica II', 'ods', 'http://ods.org/', '2015-03-24 00:00:00', '2015-03-24 00:00:00', 'Completen este taller para la evaluacion del segundo corte', 'http://ods.org/', 11),
(3, 'Presentacion de quimica organica', 'ppt', 'http://ppt.org/', '2015-03-24 00:00:00', '2015-03-24 00:00:00', 'Ejemplo presentacion de quimica organica', 'http://ppt.org/', 12),
(4, 'Examen ingles III', 'ods', 'http://ods.org', '2015-03-24 00:00:00', '2015-03-24 00:00:00', 'Prueba modelo examen ingles III', 'http://ods.org', 13),
(5, 'Plan de evaluacion Microprocesadores', 'ods', 'http://ods.org', '2015-03-24 00:00:00', '2015-03-24 00:00:00', 'Modelo de evaluacion microprocesadores', 'http://ods.org', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notas`
--

CREATE TABLE IF NOT EXISTS `notas` (
`uid` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `users_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `universidad`
--

CREATE TABLE IF NOT EXISTS `universidad` (
`uid` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `universidad`
--

INSERT INTO `universidad` (`uid`, `nombre`, `created_at`, `updated_at`) VALUES
(1, 'UPTM', '2015-03-24 00:00:00', '2015-03-24 00:00:00'),
(2, 'UCAB', '2015-03-24 00:00:00', '2015-03-24 00:00:00'),
(3, 'UCV', '2015-03-24 00:00:00', '2015-03-24 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`uid` int(11) NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `universidad_id` int(11) NOT NULL,
  `tipo_user` int(2) NOT NULL,
  `cedula` int(8) NOT NULL,
  `profile_pic` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`uid`, `unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`, `universidad_id`, `tipo_user`, `cedula`, `profile_pic`) VALUES
(10, '12314235234234234234234', 'Carlos Malave', 'hellfish2@gmail.com', 'asdasdasdksda3kel3kmdlk3mldk3mldkm3ldk3mldkmld3kmdl3kmdl3kmd1l3m2l3kemdlkm3ldkm3', 'kajsdoa3dl', '2015-03-24 00:00:00', '2015-03-24 00:00:00', 1, 1, 11111111, 'http://bit.ly'),
(11, '45353642342342342342342', 'Juan Gomez', 'jgomezbjj@gmail.com', 'aosidjoasidjaosdijaosidjaosijosaijxoasixjaosixjaosxijaosixjaosixjaosxijaosxijaos', 'i1j2lklqkd', '2015-03-24 00:00:00', '2015-03-24 00:00:00', 1, 2, 10999999, 'http://google.com/'),
(13, 'lkajdsalsidjdli3ldn3ldn', 'Monica Rivas', 'monicarivas1210@gmail.com', 'alskd3lmdl3kdml3kdmlkmd22lk2mlk22lk1lk2l1kj23lkjlkjldkjlskjdlaskdj33kjdlkjsldkjl', 'ñ3dlk3ñldk', '2015-03-24 00:00:00', '2015-03-24 00:00:00', 2, 2, 10928039, 'http://twitter.com/');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `docs`
--
ALTER TABLE `docs`
 ADD PRIMARY KEY (`uid`), ADD KEY `users_id` (`users_id`) COMMENT 'users_id';

--
-- Indices de la tabla `notas`
--
ALTER TABLE `notas`
 ADD PRIMARY KEY (`uid`), ADD KEY `users_id` (`users_id`);

--
-- Indices de la tabla `universidad`
--
ALTER TABLE `universidad`
 ADD PRIMARY KEY (`uid`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`uid`), ADD UNIQUE KEY `unique_id` (`unique_id`), ADD UNIQUE KEY `email` (`email`), ADD KEY `universidad_id` (`universidad_id`) COMMENT 'universidad_id';

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `docs`
--
ALTER TABLE `docs`
MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `notas`
--
ALTER TABLE `notas`
MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `universidad`
--
ALTER TABLE `universidad`
MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
