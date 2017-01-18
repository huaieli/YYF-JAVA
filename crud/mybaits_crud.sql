/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50537
Source Host           : 127.0.0.1:3306
Source Database       : mybaits_crud

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-01-18 10:14:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `code` varchar(10) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `flag` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'name', 'U17010001', 'phone2', 'remark2', '0');
INSERT INTO `user` VALUES ('7', 'name', 'U17010002', 'phone', 'remark', '0');
INSERT INTO `user` VALUES ('8', 'name', 'U17010003', 'phone', 'remark', '0');
INSERT INTO `user` VALUES ('9', 'name', 'U17010004', 'phone', 'remark', '0');
INSERT INTO `user` VALUES ('10', 'name', 'U17010005', 'phone', 'remark', '0');
INSERT INTO `user` VALUES ('11', 'name', 'U17010006', 'phone', 'remark', '0');
INSERT INTO `user` VALUES ('12', 'name', 'U17010007', 'phone', 'remark', '0');
INSERT INTO `user` VALUES ('13', 'name', 'U17010008', 'phone', 'remark', '0');
INSERT INTO `user` VALUES ('14', 'name', 'U17010009', 'phone', 'remark', '0');
INSERT INTO `user` VALUES ('15', 'name', 'U17010009', 'phone', 'remark', '0');
