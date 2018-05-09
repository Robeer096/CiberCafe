-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 12-05-2017 a las 11:37:05
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `colegio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE IF NOT EXISTS `alumnos` (
  `nombre` varchar(25) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `matricula` int(11) NOT NULL,
  `curso` varchar(8) NOT NULL,
  `fechaMatricula` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dni`),
  KEY `curso` (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`nombre`, `dni`, `telefono`, `matricula`, `curso`, `fechaMatricula`) VALUES
('Ana López Sanz', '11111111A', '677876543', 1001, 'DAW1', '2017-05-23 22:00:00'),
('Luis Pérez Rodríguez', '22222222B', '677889988', 1002, 'ASIR1', '2017-03-20 23:00:00'),
('Juan López López', '44444444D', '655443322', 1003, 'DAW1', '2017-02-11 23:00:00'),
('q', 'q', 'q', 1, 'DAW1', '2017-05-10 11:48:05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE IF NOT EXISTS `cursos` (
  `curso` varchar(5) NOT NULL,
  `descripcion` varchar(80) NOT NULL,
  `aula` varchar(5) NOT NULL,
  PRIMARY KEY (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`curso`, `descripcion`, `aula`) VALUES
('ASIR1', 'Administración de sistemas informáticos y redes', 'lab2'),
('DAW1', 'Desarrollo de aplicaciones web', 'lab1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutores`
--

CREATE TABLE IF NOT EXISTS `tutores` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `curso` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `curso` (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tutores`
--

INSERT INTO `tutores` (`dni`, `nombre`, `curso`) VALUES
('33333333C', 'Begoña López', 'DAW1');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`curso`);

--
-- Filtros para la tabla `tutores`
--
ALTER TABLE `tutores`
  ADD CONSTRAINT `tutores_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`curso`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
