/*
Navicat MySQL Data Transfer

Source Server         : yier
Source Server Version : 50736
Source Host           : localhost:3306
Source Database       : usertext

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2025-04-08 18:19:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `registration_date` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_extra_info
-- ----------------------------
DROP TABLE IF EXISTS `user_extra_info`;
CREATE TABLE `user_extra_info` (
  `extra_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `id_card_number` varchar(18) NOT NULL,
  PRIMARY KEY (`extra_info_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
