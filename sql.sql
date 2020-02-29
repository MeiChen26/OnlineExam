/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : electriccharge

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-13 16:22:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `front_user_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', 'ceshi', '0', '2018-04-26 14:40:06');
INSERT INTO `comment` VALUES ('2', '1', 'ddddd', '0', '2018-04-26 14:40:09');
INSERT INTO `comment` VALUES ('3', '1', 'fff', '0', '2018-04-26 14:40:13');
INSERT INTO `comment` VALUES ('5', '7', 'dddd', null, null);
INSERT INTO `comment` VALUES ('6', '8', 'eee', null, null);
INSERT INTO `comment` VALUES ('7', '9', 'ggggg', null, null);

-- ----------------------------
-- Table structure for electric_info
-- ----------------------------
DROP TABLE IF EXISTS `electric_info`;
CREATE TABLE `electric_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `front_user_id` int(11) DEFAULT NULL,
  `electric_consumption` double(10,2) DEFAULT '0.00' COMMENT '用电量',
  `electric_amount` double(10,2) DEFAULT '0.00' COMMENT '用电金额',
  `isowe` int(1) DEFAULT NULL COMMENT '是否欠费',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of electric_info
-- ----------------------------
INSERT INTO `electric_info` VALUES ('1', '1', '4.00', '1.00', '1', '0', '2018-04-21 15:23:36', '2018-04-21 15:23:39');
INSERT INTO `electric_info` VALUES ('2', '2', '3.00', '3.00', '0', '0', '2018-04-21 15:24:07', '2018-04-21 15:24:10');
INSERT INTO `electric_info` VALUES ('3', '3', '3.00', '3.00', '0', '0', '2018-04-21 15:24:30', '2018-04-21 15:24:32');
INSERT INTO `electric_info` VALUES ('6', '7', '1.00', '1.00', '0', '0', '2018-04-26 14:45:46', null);
INSERT INTO `electric_info` VALUES ('7', '8', '1.00', '1.00', '0', '0', '2018-04-26 15:40:59', null);
INSERT INTO `electric_info` VALUES ('8', '9', '1.00', '1.00', '0', '0', '2018-04-26 16:44:58', null);

-- ----------------------------
-- Table structure for front_user
-- ----------------------------
DROP TABLE IF EXISTS `front_user`;
CREATE TABLE `front_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `login_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `city` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市',
  `address` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `owe_remind` int(1) DEFAULT '0' COMMENT '欠费提醒',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='注册用户表';

-- ----------------------------
-- Records of front_user
-- ----------------------------
INSERT INTO `front_user` VALUES ('1', '1', '123456', '北京', '1', '0', '0', '2018-04-21 15:21:41');
INSERT INTO `front_user` VALUES ('2', '2', '2', '上海', '2', '0', '0', '2018-04-21 15:22:18');
INSERT INTO `front_user` VALUES ('3', '3', '3', '广州', '3', '0', '0', '2018-04-21 15:22:39');
INSERT INTO `front_user` VALUES ('7', 'test1', '123456', '北京', 'fff', '0', '0', '2018-04-26 14:45:15');
INSERT INTO `front_user` VALUES ('8', 'test2', '123456', '上海', 'ss', '1', '0', '2018-04-26 15:40:14');
INSERT INTO `front_user` VALUES ('9', 'test3', '123456', '上海', 'ss', '1', '0', '2018-04-26 16:44:24');

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(20) NOT NULL COMMENT '模块名称',
  `module_level` tinyint(3) DEFAULT NULL COMMENT '0，表示系统，1表示一级模块 ……',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父模块ID',
  `module_url` varchar(80) DEFAULT NULL COMMENT '模块URL',
  `permission` varchar(30) NOT NULL COMMENT '权限标记',
  `priority` int(5) NOT NULL DEFAULT '100' COMMENT '优先级，菜单排序',
  `icon_class` varchar(50) DEFAULT NULL COMMENT '模块对应图标',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '隐藏标记: 1,隐藏;0,未隐藏;',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('1', '系统管理', '1', '0', ' ', 'sys:manage', '100', null, '基本功能模块', '0', '2017-02-21 20:41:58');
INSERT INTO `sys_module` VALUES ('2', '系统用户管理', '2', '1', '/user/userList', 'sys:user:userList', '100', '', '基本功能模块', '0', '2018-04-21 22:51:41');
INSERT INTO `sys_module` VALUES ('3', '模块管理', '2', '1', '/module/moduleList', 'sys:module:moduleList', '100', null, '基本功能模块', '0', '2017-02-21 20:44:42');
INSERT INTO `sys_module` VALUES ('4', '角色管理', '2', '1', '/role/roleList', 'test', '100', '', '基本功能模块', '0', '2017-10-10 11:28:40');
INSERT INTO `sys_module` VALUES ('5', '编辑', '3', '2', '/user/editUser', 'sys:user:edit', '100', '', '基本功能模块', '1', '2017-10-10 14:55:27');
INSERT INTO `sys_module` VALUES ('6', '删除', '3', '2', '/', 'sys:user:delete', '100', '', '基本功能模块', '1', '2017-10-10 14:55:16');
INSERT INTO `sys_module` VALUES ('7', '编辑', '3', '3', '/', 'sys:module:edit', '100', '', '基本功能模块', '1', '2017-10-10 14:56:13');
INSERT INTO `sys_module` VALUES ('8', '添加', '3', '2', '/', 'sys:user:add', '100', '', '基本功能模块', '1', '2017-10-10 14:56:40');
INSERT INTO `sys_module` VALUES ('9', '添加', '3', '3', '/', 'sys:module:add', '100', '', '基本功能模块', '1', '2017-10-10 14:57:16');
INSERT INTO `sys_module` VALUES ('10', '删除', '3', '3', '/', 'sys:module:delete', '100', '', '基本功能模块', '1', '2017-10-10 14:57:52');
INSERT INTO `sys_module` VALUES ('11', '添加', '3', '4', '/', 'sys:role:add', '100', '', '基本功能模块', '1', '2017-10-10 14:58:31');
INSERT INTO `sys_module` VALUES ('12', '编辑', '3', '4', '/', 'sys:role:edit', '100', '', '基本功能模块', '1', '2017-10-10 14:59:10');
INSERT INTO `sys_module` VALUES ('13', '删除', '3', '4', '/', 'sys:role:delete', '100', '', '基本功能模块', '1', '2017-10-10 14:59:43');
INSERT INTO `sys_module` VALUES ('14', '设置权限', '3', '4', '/', 'sys:role:setRights', '100', '', '基本功能模块', '1', '2017-10-10 15:00:19');
INSERT INTO `sys_module` VALUES ('15', '电费管理', '1', '0', '/', 'v', '100', '', '业务功能模块', '0', '2018-04-21 22:42:06');
INSERT INTO `sys_module` VALUES ('16', '用户管理', '2', '15', '/frontUser/list', 'v', '100', '', '业务功能模块', '0', '2018-04-21 23:03:34');
INSERT INTO `sys_module` VALUES ('17', '电费列表', '2', '15', '/electricInfo/list', 'v', '100', '', '业务功能模块', '0', '2018-04-21 23:08:51');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1表示已删除',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'Administrator', '超级管理员', '0', '2017-02-25 09:20:55');
INSERT INTO `sys_role` VALUES ('2', 'Manager', '管理员', '0', '2017-02-25 09:21:26');
INSERT INTO `sys_role` VALUES ('3', 'Common', '普通账号', '0', '2017-02-25 09:21:56');
INSERT INTO `sys_role` VALUES ('4', 'Anonymous', '匿名Guest', '0', '2017-02-25 09:22:20');
INSERT INTO `sys_role` VALUES ('6', '123', '123', '1', '2017-10-10 12:25:12');

-- ----------------------------
-- Table structure for sys_role_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `module_id` int(11) NOT NULL COMMENT '模块ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_module
-- ----------------------------
INSERT INTO `sys_role_module` VALUES ('4', '1', '4', '2017-02-25 20:32:02');
INSERT INTO `sys_role_module` VALUES ('11', '3', '1', '2017-02-26 10:58:55');
INSERT INTO `sys_role_module` VALUES ('25', '1', '1', '2017-02-26 13:02:34');
INSERT INTO `sys_role_module` VALUES ('32', '2', '1', '2017-02-26 14:55:05');
INSERT INTO `sys_role_module` VALUES ('33', '2', '2', '2017-02-26 14:55:05');
INSERT INTO `sys_role_module` VALUES ('34', '2', '3', '2017-02-26 14:55:05');
INSERT INTO `sys_role_module` VALUES ('35', '2', '5', '2017-02-26 14:55:05');
INSERT INTO `sys_role_module` VALUES ('36', '2', '6', '2017-02-26 14:55:05');
INSERT INTO `sys_role_module` VALUES ('37', '1', '3', '2017-10-10 14:51:58');
INSERT INTO `sys_role_module` VALUES ('38', '1', '2', '2017-10-10 15:00:43');
INSERT INTO `sys_role_module` VALUES ('39', '1', '13', '2017-10-10 15:00:43');
INSERT INTO `sys_role_module` VALUES ('40', '1', '14', '2017-10-10 15:00:43');
INSERT INTO `sys_role_module` VALUES ('53', '1', '5', '2017-10-10 15:07:53');
INSERT INTO `sys_role_module` VALUES ('54', '1', '6', '2017-10-10 15:07:53');
INSERT INTO `sys_role_module` VALUES ('55', '1', '7', '2017-10-10 15:07:53');
INSERT INTO `sys_role_module` VALUES ('56', '1', '8', '2017-10-10 15:07:53');
INSERT INTO `sys_role_module` VALUES ('57', '1', '9', '2017-10-10 15:07:53');
INSERT INTO `sys_role_module` VALUES ('58', '1', '10', '2017-10-10 15:07:53');
INSERT INTO `sys_role_module` VALUES ('59', '1', '11', '2017-10-10 15:07:53');
INSERT INTO `sys_role_module` VALUES ('60', '1', '12', '2017-10-10 15:07:53');
INSERT INTO `sys_role_module` VALUES ('61', '1', '15', '2018-04-21 22:49:48');
INSERT INTO `sys_role_module` VALUES ('62', '1', '16', '2018-04-21 23:09:56');
INSERT INTO `sys_role_module` VALUES ('63', '1', '17', '2018-04-21 23:09:56');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名，登陆名',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名，昵称',
  `password` varchar(70) DEFAULT NULL COMMENT '密码',
  `dept_id` int(11) DEFAULT '0' COMMENT '部门ID',
  `email` varchar(80) DEFAULT NULL COMMENT 'Email',
  `phone` varchar(15) DEFAULT NULL COMMENT '电话、手机',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登陆时间',
  `last_login_ip` varchar(18) DEFAULT NULL COMMENT '上次登陆IP',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '9afccd80c6b2bda18bf6419c5ee57bc2d0352724437fc776c0a3c349', '0', 'admin@localhost.com', '', null, null, '0', '2017-02-13 18:23:40');
INSERT INTO `sys_user` VALUES ('2', 'ttt', 'ttt', '36af38da3e317a1a44babfe22ecb8083ec61ba155f230357f3e346c1', '0', 'ttt222', '18866967977', null, null, '0', '2017-02-13 18:29:08');
INSERT INTO `sys_user` VALUES ('3', 'test', '测试账号', '9c50897782c0288f3a4623c7cc6830efde32b0da008842ae52e2b2a2', '0', 'test@test.com', '', null, null, '0', '2017-02-13 20:13:17');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('18', '1', '1', '2017-02-25 15:37:16');
INSERT INTO `sys_user_role` VALUES ('19', '0', '4', '2017-02-25 15:37:31');
INSERT INTO `sys_user_role` VALUES ('23', '2', '4', '2018-02-22 16:54:22');
INSERT INTO `sys_user_role` VALUES ('24', '3', '4', '2018-02-23 07:59:58');
INSERT INTO `sys_user_role` VALUES ('27', '5', '4', '2018-02-23 09:29:24');
