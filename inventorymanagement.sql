-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 27, 2024 at 05:48 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventorymanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id`, `created_at`, `description`, `name`, `update_at`) VALUES
(1, '2024-12-13 09:13:42.000000', 'internet of things', 'IT', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `department_users`
--

CREATE TABLE `department_users` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  `users_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `reorder` double DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_stocks`
--

CREATE TABLE `product_stocks` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `stock_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `request_status` enum('approved','pending','rejected') DEFAULT NULL,
  `request_date` datetime(6) DEFAULT NULL,
  `total_quantity` double DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `request_products`
--

CREATE TABLE `request_products` (
  `id` bigint NOT NULL,
  `rquest_type` enum('ajout','ajustement','retrait') DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `products_id` bigint DEFAULT NULL,
  `requests_id` bigint DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `created_at`, `description`, `name`, `updated_at`) VALUES
(1, NULL, NULL, 'ROLE_UTILISATEUR', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `stocks`
--

CREATE TABLE `stocks` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `current_quantity` double DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `reorder_level` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_info` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `supplies`
--

CREATE TABLE `supplies` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `suppliers_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `supply_products`
--

CREATE TABLE `supply_products` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `supply_date` datetime(6) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `supply_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_at`, `email`, `name`, `password`, `status`, `updated_at`, `role_id`, `username`) VALUES
(8, NULL, 'yk5ferra@gmail.com', 'yao kan', '$2a$10$pzTfNB5HuWNeRGzX/izJMegZqelGBWkyVF2lhnRFrp4Gh6pqx2rCS', NULL, NULL, 1, NULL),
(9, NULL, 'yk5ferra1@gmail.com', 'yao kan', 'maurice1', NULL, NULL, 1, NULL),
(10, NULL, 'yk5ferra2@gmail.com', 'yao kan', '$2a$10$WtaBKsKUrmfEGC8TLSyq6.RP7gzsHSZHeEpn2gNChmojkMqioggLG', NULL, NULL, 1, NULL),
(11, NULL, 'yk5ferra3@gmail.com', 'yao kan', '$2a$10$HHw3X1c4HCmTAKe03PYprOebXB8yhHbuTHn.om4yCL9e0P2OLXMPa', NULL, NULL, 1, NULL),
(12, '2024-10-22 10:00:00.000000', 'janedoe@example.com', 'Jane Doe', '$2a$10$EWJfu6yFekN7/CNLhI3QHOMfjXu6zSbFTH9iIu6RmMzZgdaadrIPa', b'1', '2024-10-22 10:00:00.000000', 1, NULL),
(13, '2024-10-22 10:00:00.000000', 'janedoe3@example.com', 'Jane Doe', '$2a$10$NJHMXcxJiutsJWLbAbJMm.kf.INsGGYr2MJ3OMylAe64l2UDZUOMG', b'1', '2024-10-22 10:00:00.000000', 1, NULL),
(14, '2024-10-22 10:00:00.000000', 'janedoe2@example.com', 'Jane Doe', '$2a$10$SG4SlIJVQGAz0XkJuzJ5..QzojA7MAMGM1KEUFKMW1sqTNY8ErCni', b'1', '2024-10-22 10:00:00.000000', 1, NULL),
(15, '2024-10-22 10:00:00.000000', 'janedoe1@example.com', 'Jane Doe', '$2a$10$5dVZulc5yWdlqJtf0PnFW.TLgUd9JmRbCYvN.dF27hGEsnZPNR/hC', b'1', '2024-10-22 10:00:00.000000', 1, NULL),
(16, '2024-10-22 10:00:00.000000', 'yao@example.com', 'Jane Doe', '$2a$10$X7staUA5WAem7Sw5PipSKuec3D3HK8/O47VENl3VNmKOGkKsFAPYS', b'1', '2024-10-22 10:00:00.000000', 1, 'maurice.yao'),
(17, '2024-12-18 15:09:41.434000', 'yao@exampe.com', 'Jane Doe', '$2a$10$JjJY9YAU/QkiXC46ZncmUeshOKGKGlcWnsCocE6whGiSdpjkCF9Pm', b'1', NULL, NULL, NULL),
(18, '2024-12-18 15:15:08.235000', 'yao@exame.com', 'Jane Doe', '$2a$10$cZKvIHln/1X9wSi9ipaeVuSn8SeGyMA07vIUTI2L8Wsb6olktin.O', b'1', NULL, NULL, NULL),
(19, '2024-12-18 15:15:19.015000', 'yao@examee.com', 'Jane Doe', '$2a$10$Jskzsvg2l3jVeqEHzC7l0./Aq5.xogMPGIBHWpv32OdJArHL9IToS', b'1', NULL, NULL, NULL),
(20, '2024-12-18 15:17:09.847000', 'yao@exa.com', 'Jane Doe', '$2a$10$ETph5X5JURF0XT8PQTm1heds9A6eRYldm7izp82ZOYhQhU6w8XAlO', b'1', NULL, NULL, NULL),
(21, '2024-12-18 15:19:25.795000', 'yao@ex.com', 'Jane Doe', '$2a$10$b5wnAl.TZuepbqzw.zj7SeC/5aEQhJQVbD2EQDdJUpXLOBDD3jfDK', b'1', NULL, NULL, NULL),
(22, '2024-12-18 15:20:41.441000', 'yao@exazaa.com', 'Jane Doe', '$2a$10$V8QcuBZ/bJV71/af4YDJCOzRQv0xmjxJfnx1/2BYYlFeq2zaQ60Ia', b'1', NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `department_users`
--
ALTER TABLE `department_users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkgpf5meu87saqtcl4my2hoq7i` (`department_id`),
  ADD KEY `FK76r2jmwlufk5vav9s6buttl55` (`users_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`);

--
-- Indexes for table `product_stocks`
--
ALTER TABLE `product_stocks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1dm90iiym3tkqyivfwcsrmn2k` (`product_id`),
  ADD KEY `FKlatjymjyaxo0kqs8bmtcn4gxi` (`stock_id`);

--
-- Indexes for table `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8usbpx9csc6opbjg1d7kvtf8c` (`user_id`);

--
-- Indexes for table `request_products`
--
ALTER TABLE `request_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5ga9qjajwmeayxxl03gir1egi` (`products_id`),
  ADD KEY `FKyn04leoo0b98fxe5yxkv1cl6` (`requests_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stocks`
--
ALTER TABLE `stocks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplies`
--
ALTER TABLE `supplies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3dbqc9j3oru5ff8qbcb4720jr` (`suppliers_id`);

--
-- Indexes for table `supply_products`
--
ALTER TABLE `supply_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKeif1g9tylbcn84fbihuqxu954` (`product_id`),
  ADD KEY `FKi7gbgx1p0e0cru4djb06u0iig` (`supply_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `department_users`
--
ALTER TABLE `department_users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_stocks`
--
ALTER TABLE `product_stocks`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `requests`
--
ALTER TABLE `requests`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `request_products`
--
ALTER TABLE `request_products`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `stocks`
--
ALTER TABLE `stocks`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `supplies`
--
ALTER TABLE `supplies`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `supply_products`
--
ALTER TABLE `supply_products`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `department_users`
--
ALTER TABLE `department_users`
  ADD CONSTRAINT `FK76r2jmwlufk5vav9s6buttl55` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKkgpf5meu87saqtcl4my2hoq7i` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `product_stocks`
--
ALTER TABLE `product_stocks`
  ADD CONSTRAINT `FK1dm90iiym3tkqyivfwcsrmn2k` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKlatjymjyaxo0kqs8bmtcn4gxi` FOREIGN KEY (`stock_id`) REFERENCES `stocks` (`id`);

--
-- Constraints for table `requests`
--
ALTER TABLE `requests`
  ADD CONSTRAINT `FK8usbpx9csc6opbjg1d7kvtf8c` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `request_products`
--
ALTER TABLE `request_products`
  ADD CONSTRAINT `FK5ga9qjajwmeayxxl03gir1egi` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKyn04leoo0b98fxe5yxkv1cl6` FOREIGN KEY (`requests_id`) REFERENCES `requests` (`id`);

--
-- Constraints for table `supplies`
--
ALTER TABLE `supplies`
  ADD CONSTRAINT `FK3dbqc9j3oru5ff8qbcb4720jr` FOREIGN KEY (`suppliers_id`) REFERENCES `suppliers` (`id`);

--
-- Constraints for table `supply_products`
--
ALTER TABLE `supply_products`
  ADD CONSTRAINT `FKeif1g9tylbcn84fbihuqxu954` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKi7gbgx1p0e0cru4djb06u0iig` FOREIGN KEY (`supply_id`) REFERENCES `supplies` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
