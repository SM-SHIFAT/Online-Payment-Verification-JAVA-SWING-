-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2021 at 07:22 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `opm`
--

-- --------------------------------------------------------

--
-- Table structure for table `due_total`
--

CREATE TABLE `due_total` (
  `due` int(50) NOT NULL,
  `total` int(50) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `name` varchar(50) NOT NULL,
  `id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `semester` varchar(1) NOT NULL,
  `type` varchar(1) NOT NULL,
  `fees` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`name`, `id`, `password`, `email`, `semester`, `type`, `fees`) VALUES
('ss', '43', '234', 'no@', '6', 's', 123123),
('admin', 'admin', 'admin', 'admin@gmail.com', '0', 'a', 0),
('s', 's', 's', 'fgh@gmail', '5', 's', 18000),
('student', 'student', 'student', '@student', '4', 's', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` varchar(50) NOT NULL,
  `txid` varchar(50) NOT NULL,
  `taka` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  `s_payment` varchar(1) NOT NULL,
  `c_date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `txid`, `taka`, `date`, `s_payment`, `c_date`) VALUES
('s', '1111', '1000', '27-12-2020', '6', '27-12-2020'),
('s', '1234', '10000', '27-12-2020', '5', '27-12-2020'),
('43', '1234x', '1234', '29-11-2020', '5', '29-11-2020'),
('student', '123v12v312', '2000', '1-2-2020', '4', '13-2-2020'),
('1182362', '123v1782v312', '1000', '2-3-2020', '4', '13-1-2020'),
('student', 'f346hfdh34', '300', '1-1-2020', '4', '14-1-2020');

-- --------------------------------------------------------

--
-- Table structure for table `verify`
--

CREATE TABLE `verify` (
  `id` varchar(50) NOT NULL,
  `txid` varchar(50) NOT NULL,
  `taka` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `due_total`
--
ALTER TABLE `due_total`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`txid`);

--
-- Indexes for table `verify`
--
ALTER TABLE `verify`
  ADD PRIMARY KEY (`txid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
