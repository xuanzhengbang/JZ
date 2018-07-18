/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : 127.0.0.1:3306
Source Database       : zd_setmeal_food

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-19 00:43:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zd_dine_type
-- ----------------------------
DROP TABLE IF EXISTS `zd_dine_type`;
CREATE TABLE `zd_dine_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `del_flag` int(2) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '备注/说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='订餐类型';

-- ----------------------------
-- Records of zd_dine_type
-- ----------------------------
INSERT INTO `zd_dine_type` VALUES ('1', 'baoxiang', '包厢', '0', '2018-07-18 23:06:35', '2018-07-18 23:06:35', '');
INSERT INTO `zd_dine_type` VALUES ('2', 'shengriyan', '生日宴', '0', '2018-07-18 23:06:59', '2018-07-18 23:06:59', '');
INSERT INTO `zd_dine_type` VALUES ('3', 'daxieyan', '答谢宴', '0', '2018-07-18 23:07:15', '2018-07-18 23:07:15', '');
INSERT INTO `zd_dine_type` VALUES ('4', 'ganxieyan', '感谢宴', '0', '2018-07-18 23:07:40', '2018-07-18 23:07:40', '');
INSERT INTO `zd_dine_type` VALUES ('5', 'hunyan', '婚宴', '0', '2018-07-18 23:07:53', '2018-07-18 23:07:53', '');
INSERT INTO `zd_dine_type` VALUES ('6', 'qiaoqian', '乔迁', '0', '2018-07-18 23:08:07', '2018-07-18 23:08:07', '');

-- ----------------------------
-- Table structure for zd_food
-- ----------------------------
DROP TABLE IF EXISTS `zd_food`;
CREATE TABLE `zd_food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL COMMENT '菜单编码',
  `name` varchar(255) NOT NULL COMMENT '菜名',
  `type_code` varchar(255) NOT NULL COMMENT '分类',
  `del_flag` int(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '备注/说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of zd_food
-- ----------------------------

-- ----------------------------
-- Table structure for zd_food_type
-- ----------------------------
DROP TABLE IF EXISTS `zd_food_type`;
CREATE TABLE `zd_food_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL COMMENT '分类编号-唯一',
  `name` varchar(255) NOT NULL COMMENT '分类名',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '备注/说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单分类表';

-- ----------------------------
-- Records of zd_food_type
-- ----------------------------

-- ----------------------------
-- Table structure for zd_place
-- ----------------------------
DROP TABLE IF EXISTS `zd_place`;
CREATE TABLE `zd_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '地点编号-唯一',
  `name` varchar(255) NOT NULL,
  `del_flag` int(2) NOT NULL DEFAULT '0' COMMENT '删除标记：0/未删除，1/删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '备注/说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='地点表';

-- ----------------------------
-- Records of zd_place
-- ----------------------------
INSERT INTO `zd_place` VALUES ('1', 'p_0001', '二楼包厢', '0', '2018-07-18 23:11:41', '2018-07-18 23:11:41', '');
INSERT INTO `zd_place` VALUES ('2', 'p_0002', '三楼包厢', '0', '2018-07-18 23:11:55', '2018-07-18 23:11:55', '');
INSERT INTO `zd_place` VALUES ('3', 'p_0003', '吉祥厅', '0', '2018-07-18 23:12:06', '2018-07-18 23:12:06', '');
INSERT INTO `zd_place` VALUES ('4', 'p_0004', '如意厅', '0', '2018-07-18 23:12:19', '2018-07-18 23:12:19', '');
INSERT INTO `zd_place` VALUES ('5', 'p_0005', '二楼宴会大厅', '0', '2018-07-18 23:12:57', '2018-07-18 23:16:18', '');
INSERT INTO `zd_place` VALUES ('6', 'p_0006', '三楼金色宴会厅', '0', '2018-07-18 23:13:15', '2018-07-18 23:13:15', '');
INSERT INTO `zd_place` VALUES ('7', 'p_0007', '三楼多功能厅', '0', '2018-07-18 23:13:45', '2018-07-18 23:16:22', '');
INSERT INTO `zd_place` VALUES ('8', 'p_0008', '六楼多功能厅', '0', '2018-07-18 23:14:09', '2018-07-18 23:14:09', '');

-- ----------------------------
-- Table structure for zd_place_item
-- ----------------------------
DROP TABLE IF EXISTS `zd_place_item`;
CREATE TABLE `zd_place_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place_code` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `del_flag` int(255) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zd_place_item
-- ----------------------------
INSERT INTO `zd_place_item` VALUES ('1', 'p_0001', 'pi_8201', '8201', '0', '2018-07-18 23:17:24', '2018-07-18 23:18:40', '');
INSERT INTO `zd_place_item` VALUES ('2', 'p_0001', 'pi_8202', '8202', '0', '2018-07-18 23:17:56', '2018-07-18 23:18:38', '');
INSERT INTO `zd_place_item` VALUES ('3', 'p_0001', 'pi_8203', '8203', '0', '2018-07-18 23:19:07', '2018-07-18 23:19:07', '');
INSERT INTO `zd_place_item` VALUES ('4', 'p_0001', 'pi_8205', '8205', '0', '2018-07-18 23:19:33', '2018-07-18 23:19:33', '');
INSERT INTO `zd_place_item` VALUES ('5', 'p_0001', 'pi_8206', '8206', '0', '2018-07-18 23:19:46', '2018-07-18 23:19:46', '');
INSERT INTO `zd_place_item` VALUES ('6', 'p_0001', 'pi_8207', '8207', '0', '2018-07-18 23:20:03', '2018-07-18 23:20:03', '');
INSERT INTO `zd_place_item` VALUES ('7', 'p_0001', 'pi_8208', '8208', '0', '2018-07-18 23:20:13', '2018-07-18 23:20:13', '');
INSERT INTO `zd_place_item` VALUES ('8', 'p_0001', 'pi_8218', '8218', '0', '2018-07-18 23:20:29', '2018-07-18 23:20:29', '');
INSERT INTO `zd_place_item` VALUES ('9', 'p_0001', 'pi_8288', '8288', '0', '2018-07-18 23:20:41', '2018-07-18 23:20:41', '');
INSERT INTO `zd_place_item` VALUES ('10', 'p_0002', 'pi_8301', '8301', '0', '2018-07-18 23:21:10', '2018-07-18 23:21:10', '');
INSERT INTO `zd_place_item` VALUES ('11', 'p_0002', 'pi_8302', '8302', '0', '2018-07-18 23:21:31', '2018-07-18 23:21:31', '');
INSERT INTO `zd_place_item` VALUES ('12', 'p_0002', 'pi_8303', '8303', '0', '2018-07-18 23:22:01', '2018-07-18 23:22:01', '');
INSERT INTO `zd_place_item` VALUES ('13', 'p_0002', 'pi_8305', '8305', '0', '2018-07-18 23:22:14', '2018-07-18 23:22:14', '');
INSERT INTO `zd_place_item` VALUES ('14', 'p_0002', 'pi_8306', '8306', '0', '2018-07-18 23:22:24', '2018-07-18 23:22:24', '');
INSERT INTO `zd_place_item` VALUES ('15', 'p_0002', 'pi_8307', '8307', '0', '2018-07-18 23:22:35', '2018-07-18 23:22:35', '');
INSERT INTO `zd_place_item` VALUES ('16', 'p_0002', 'pi_8308', '8308', '0', '2018-07-18 23:22:52', '2018-07-18 23:22:52', '');
INSERT INTO `zd_place_item` VALUES ('17', 'p_0002', 'pi_8309', '8309', '0', '2018-07-18 23:23:04', '2018-07-18 23:23:04', '');
INSERT INTO `zd_place_item` VALUES ('18', 'p_0002', 'pi_jzt', '军转厅', '0', '2018-07-18 23:23:42', '2018-07-18 23:23:42', '');

-- ----------------------------
-- Table structure for zd_setmeal
-- ----------------------------
DROP TABLE IF EXISTS `zd_setmeal`;
CREATE TABLE `zd_setmeal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL COMMENT '标准/价格',
  `book_date` date NOT NULL COMMENT '预定时间',
  `place_code` varchar(255) NOT NULL COMMENT '地点编码',
  `dine_type_code` varchar(255) NOT NULL COMMENT '类型编码',
  `num` varchar(255) NOT NULL COMMENT '桌数',
  `create_uid` varchar(255) NOT NULL COMMENT '创建人uid',
  `del_flag` int(2) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '备注/说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐表';

-- ----------------------------
-- Records of zd_setmeal
-- ----------------------------

-- ----------------------------
-- Table structure for zd_setmeal_item
-- ----------------------------
DROP TABLE IF EXISTS `zd_setmeal_item`;
CREATE TABLE `zd_setmeal_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `food_code` varchar(255) NOT NULL,
  `food_name` varchar(255) NOT NULL,
  `setmeal_code` varchar(255) NOT NULL COMMENT '套餐code',
  `del_flag` int(2) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '备注/说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐明细';

-- ----------------------------
-- Records of zd_setmeal_item
-- ----------------------------

-- ----------------------------
-- Table structure for zd_user
-- ----------------------------
DROP TABLE IF EXISTS `zd_user`;
CREATE TABLE `zd_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL COMMENT '用户id编码',
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `role` int(4) NOT NULL DEFAULT '0' COMMENT '角色：0/大厨，1/管理员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '备注/说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of zd_user
-- ----------------------------
