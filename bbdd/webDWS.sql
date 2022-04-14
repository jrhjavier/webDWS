-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2021 a las 00:34:23
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pccommunity`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer`
--

CREATE TABLE `customer` (
`idCustomer` bigint(20) NOT NULL,
`name` varchar(255) DEFAULT NULL,
`surname` varchar(255) DEFAULT NULL,
`email` varchar(255) DEFAULT NULL,
`phoneNumber` varchar(255) DEFAULT NULL,
`passwd` varchar(255) DEFAULT NULL,
`address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `customer`
--

INSERT INTO `customer` (`idCustomer`, `name`, `surname`, `email`, `phoneNumber`, `passwd`, `address`) VALUES
(1, '-', 'admin@admin.com', 'Admin', '$2a$10$jjeRxQL.w215ar0VsFv.NuyS.579KUFaWwMFmJXO8cC4GZ0v3sCTi', '-', '-'),
(2, 'Maldivas', 'aaa@aaaa.com', 'Alex', '$2a$10$rRMsSOKrbawRolrWYwsbBePNGnlitX4KPROhqqYOy.s2cbHv3KpQK', '666666666', 'Gallego'),
(3, 'aqui', 'luis@enrique.com', 'Luis', '$2a$10$mKcM6XFdQYbrbnTr/cXeme6lv4PdCPV/hEJD7nIh1MvG/goLmqJQC', '555', 'Enrique');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer_roles`
--

CREATE TABLE `customer_roles` (
                                  `customer_id_customer` bigint(20) NOT NULL,
                                  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `customer_roles`
--

INSERT INTO `customer_roles` (`customer_id_customer`, `roles`) VALUES
                                                                   (1, 'ROLE_USER'),
                                                                   (1, 'ROLE_ADMIN'),
                                                                   (1, 'ROLE_SADMIN'),
                                                                   (3, 'ROLE_USER'),
                                                                   (4, 'ROLE_USER'),
                                                                   (2, 'ROLE_USER'),
                                                                   (2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order_product`
--

CREATE TABLE `order_product` (
                                 `uds` int(11) NOT NULL,
                                 `order_id_id_order` bigint(20) NOT NULL,
                                 `product_id_id_product` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `order_product`
--

INSERT INTO `order_product` (`uds`, `order_id_id_order`, `product_id_id_product`) VALUES
                                                                                      (4, 1, 1),
                                                                                      (3, 2, 1),
                                                                                      (2, 3, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
                           `id_product` bigint(20) NOT NULL,
                           `banner_source1` varchar(255) DEFAULT NULL,
                           `banner_source2` varchar(255) DEFAULT NULL,
                           `banner_source3` varchar(255) DEFAULT NULL,
                           `category` varchar(255) DEFAULT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `highlighted` varchar(255) DEFAULT NULL,
                           `image_source` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `price` int(11) NOT NULL,
                           `stars_average` int(11) NOT NULL,
                           `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id_product`, `banner_source1`, `banner_source2`, `banner_source3`, `category`, `description`, `highlighted`, `image_source`, `name`, `price`, `stars_average`, `stock`) VALUES
                                                                                                                                                                                                    (1, '/images/3090/banner_1.jpg', '/images/3090/banner_2.jpg', '/images/3090/banner_3.jpg', 'componentes', 'Una bonita gtx', 'Highlighted', '/images/3090/principal.jpg', 'GTX 3090', 650, 1, 15),
                                                                                                                                                                                                    (2, '/images/monitor/banner_1.png', '/images/monitor/banner_2.png', '/images/monitor/banner_3.png', 'monitores', 'Un cacho monitor', '', '/images/monitor/principal.png', 'HP 7Q5W765', 200, 0, 5),
                                                                                                                                                                                                    (3, '/images/raton/banner_1.png', '/images/raton/banner_2.png', '/images/raton/banner_3.png', 'perifericos', 'Pedazo raton', '', '/images/raton/principal.png', 'Logitech GT53', 50, 0, 20),
                                                                                                                                                                                                    (4, '/images/teclado/banner_1.png', '/images/teclado/banner_2.png', '/images/teclado/banner_3.png', 'perifericos', 'Un bonito teclado', '', '/images/teclado/principal.png', 'Logitech KB65', 100, 0, 2),
                                                                                                                                                                                                    (5, '/images/ryzen/principal.jpg', '/images/ryzen/principal.jpg', '/images/ryzen/principal.jpg', 'componentes', 'El mejor procesador de tu vida', 'Highlighted', '/images/ryzen/principal.jpg', 'Ryzen i7', 300, 1, 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `review`
--

CREATE TABLE `review` (
                          `id_review` bigint(20) NOT NULL,
                          `message` varchar(255) DEFAULT NULL,
                          `stars` int(11) NOT NULL,
                          `client_id_customer` bigint(20) DEFAULT NULL,
                          `product_id_product` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `review`
--

INSERT INTO `review` (`id_review`, `message`, `stars`, `client_id_customer`, `product_id_product`) VALUES
                                                                                                       (1, 'Aqui una valoration', 1, 3, 1),
                                                                                                       (2, 'Otra resseña', 3, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_orders`
--

CREATE TABLE `t_orders` (
                            `id_order` bigint(20) NOT NULL,
                            `date` varchar(255) DEFAULT NULL,
                            `details` varchar(255) DEFAULT NULL,
                            `payment_method` varchar(255) DEFAULT NULL,
                            `state` varchar(255) DEFAULT NULL,
                            `client_id_customer` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `t_orders`
--

INSERT INTO `t_orders` (`id_order`, `date`, `details`, `payment_method`, `state`, `client_id_customer`) VALUES
                                                                                                            (1, '15/5/2021', 'Phone number to bizum: 444', 'bizum', 'Requested', 2),
                                                                                                            (2, '15/5/2021', 'Paypal account: luisa@gmail.com', 'paypal', 'Sent', 3),
                                                                                                            (3, '17/5/2021', '[*]Card number: \n[*]Card name: \n[*]Date of Expiry: ', 'card', 'Sent', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `customer`
--
ALTER TABLE `customer`
    ADD PRIMARY KEY (`id_customer`);

--
-- Indices de la tabla `customer_roles`
--
ALTER TABLE `customer_roles`
    ADD KEY `FK4i00a54f5469bqtwubuhg968r` (`customer_id_customer`);

--
-- Indices de la tabla `order_product`
--
ALTER TABLE `order_product`
    ADD PRIMARY KEY (`order_id_id_order`,`product_id_id_product`),
  ADD KEY `IDXr9ues58506gakgmfewin408tg` (`order_id_id_order`),
  ADD KEY `IDX80si4rbj6w1bph60v0dmg3wtm` (`product_id_id_product`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
    ADD PRIMARY KEY (`id_product`);

--
-- Indices de la tabla `review`
--
ALTER TABLE `review`
    ADD PRIMARY KEY (`id_review`),
  ADD KEY `FKhvkfel7jhh6kuob6k0d95hrf7` (`client_id_customer`),
  ADD KEY `FKlutl26o7e7lyqfacff96s7omm` (`product_id_product`);

--
-- Indices de la tabla `t_orders`
--
ALTER TABLE `t_orders`
    ADD PRIMARY KEY (`id_order`),
  ADD KEY `FKjii8rbj914nb1t9g5ydripmh6` (`client_id_customer`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `customer`
--
ALTER TABLE `customer`
    MODIFY `id_customer` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `product`
--
ALTER TABLE `product`
    MODIFY `id_product` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `review`
--
ALTER TABLE `review`
    MODIFY `id_review` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `t_orders`
--
ALTER TABLE `t_orders`
    MODIFY `id_order` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `customer_roles`
--
ALTER TABLE `customer_roles`
    ADD CONSTRAINT `FK4i00a54f5469bqtwubuhg968r` FOREIGN KEY (`customer_id_customer`) REFERENCES `customer` (`id_customer`);

--
-- Filtros para la tabla `order_product`
--
ALTER TABLE `order_product`
    ADD CONSTRAINT `FK201uhq4kg1emrtuch8qjsoxyy` FOREIGN KEY (`product_id_id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `FK3v0kw7igb9hvcy56d2b8v5blt` FOREIGN KEY (`order_id_id_order`) REFERENCES `t_orders` (`id_order`);

--
-- Filtros para la tabla `review`
--
ALTER TABLE `review`
    ADD CONSTRAINT `FKhvkfel7jhh6kuob6k0d95hrf7` FOREIGN KEY (`client_id_customer`) REFERENCES `customer` (`id_customer`),
  ADD CONSTRAINT `FKlutl26o7e7lyqfacff96s7omm` FOREIGN KEY (`product_id_product`) REFERENCES `product` (`id_product`);

--
-- Filtros para la tabla `t_orders`
--
ALTER TABLE `t_orders`
    ADD CONSTRAINT `FKjii8rbj914nb1t9g5ydripmh6` FOREIGN KEY (`client_id_customer`) REFERENCES `customer` (`id_customer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
