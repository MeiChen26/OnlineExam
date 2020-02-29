/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : 127.0.0.1:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-23 20:33:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for front_user
-- ----------------------------
DROP TABLE IF EXISTS `front_user`;
CREATE TABLE `front_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime DEFAULT NULL,
  `student_no` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='注册用户表';

-- ----------------------------
-- Records of front_user
-- ----------------------------
INSERT INTO `front_user` VALUES ('1', '张三', '123456', '0', '2018-04-21 15:21:41', '123456');
INSERT INTO `front_user` VALUES ('2', '李四', '123456', '0', '2019-03-21 21:16:20', '00001');
INSERT INTO `front_user` VALUES ('3', '王五', '123456', '0', '2019-03-21 21:26:35', '00005');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名，登陆名',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名，昵称',
  `password` varchar(70) DEFAULT NULL COMMENT '密码',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `type` int(2) DEFAULT NULL COMMENT '1:管理员；2：学员',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '9afccd80c6b2bda18bf6419c5ee57bc2d0352724437fc776c0a3c349', '', '1', '0', '2017-02-13 18:23:40', '2018-12-02 16:44:29');

-- ----------------------------
-- Table structure for tb_exam
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam`;
CREATE TABLE `tb_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学员id',
  `score` int(11) DEFAULT NULL COMMENT '分数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='考试成绩表';

-- ----------------------------
-- Records of tb_exam
-- ----------------------------
INSERT INTO `tb_exam` VALUES ('1', '1', '90', '2018-12-05 16:46:30', '2018-12-05 16:50:45');
INSERT INTO `tb_exam` VALUES ('2', '1', '6', '2019-03-21 21:12:14', '2019-03-21 21:12:14');
INSERT INTO `tb_exam` VALUES ('3', '2', '4', '2019-03-21 21:17:06', '2019-03-21 21:17:06');
INSERT INTO `tb_exam` VALUES ('4', '3', '8', '2019-03-21 21:27:19', '2019-03-21 21:27:19');
INSERT INTO `tb_exam` VALUES ('5', '1', '4', '2019-03-23 20:23:10', '2019-03-23 20:23:10');

-- ----------------------------
-- Table structure for tb_exam_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_info`;
CREATE TABLE `tb_exam_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) DEFAULT NULL COMMENT '试卷id',
  `question_id` int(11) DEFAULT NULL COMMENT '试题id',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `is_right` int(1) DEFAULT NULL COMMENT '是否正确',
  `create_time` datetime DEFAULT NULL,
  `myanswer` varchar(10) DEFAULT NULL COMMENT '我的答案',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='答题信息表';

-- ----------------------------
-- Records of tb_exam_info
-- ----------------------------
INSERT INTO `tb_exam_info` VALUES ('51', '5', '32', '1', '0', '2019-03-23 20:23:10', 'D');
INSERT INTO `tb_exam_info` VALUES ('52', '5', '40', '2', '1', '2019-03-23 20:23:10', 'B');
INSERT INTO `tb_exam_info` VALUES ('53', '5', '39', '3', '1', '2019-03-23 20:23:10', 'C');
INSERT INTO `tb_exam_info` VALUES ('54', '5', '35', '4', '0', '2019-03-23 20:23:10', null);
INSERT INTO `tb_exam_info` VALUES ('55', '5', '30', '5', '0', '2019-03-23 20:23:10', null);
INSERT INTO `tb_exam_info` VALUES ('56', '5', '28', '6', '0', '2019-03-23 20:23:10', null);
INSERT INTO `tb_exam_info` VALUES ('57', '5', '33', '7', '0', '2019-03-23 20:23:10', null);
INSERT INTO `tb_exam_info` VALUES ('58', '5', '38', '8', '0', '2019-03-23 20:23:10', null);
INSERT INTO `tb_exam_info` VALUES ('59', '5', '31', '9', '0', '2019-03-23 20:23:10', null);
INSERT INTO `tb_exam_info` VALUES ('60', '5', '29', '10', '0', '2019-03-23 20:23:10', null);

-- ----------------------------
-- Table structure for tb_options
-- ----------------------------
DROP TABLE IF EXISTS `tb_options`;
CREATE TABLE `tb_options` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) DEFAULT NULL COMMENT '题目id',
  `opt` varchar(50) DEFAULT NULL COMMENT '选项',
  `content` varchar(1000) DEFAULT NULL COMMENT '选项内容',
  `rig` bit(1) DEFAULT NULL COMMENT '是否正确',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COMMENT='选项表';

-- ----------------------------
-- Records of tb_options
-- ----------------------------
INSERT INTO `tb_options` VALUES ('92', '29', 'A', '书面形式', '', '2019-03-20 21:07:03', null);
INSERT INTO `tb_options` VALUES ('93', '29', 'B', '口头形式', '\0', '2019-03-20 21:07:03', null);
INSERT INTO `tb_options` VALUES ('94', '29', 'C', '书面形式结合口头形式', '\0', '2019-03-20 21:07:03', null);
INSERT INTO `tb_options` VALUES ('95', '29', 'D', '双方协商确定', '\0', '2019-03-20 21:07:03', null);
INSERT INTO `tb_options` VALUES ('100', '31', 'A', '选择合适的委托方式和承包方式', '\0', '2019-03-20 21:09:58', null);
INSERT INTO `tb_options` VALUES ('101', '31', 'B', '确定合同的具体签订时间、地点', '', '2019-03-20 21:09:58', null);
INSERT INTO `tb_options` VALUES ('102', '31', 'C', '恰当的选择合同种类、形式及条件', '\0', '2019-03-20 21:09:58', null);
INSERT INTO `tb_options` VALUES ('103', '31', 'D', '确定合同中一些重要的条款', '\0', '2019-03-20 21:09:58', null);
INSERT INTO `tb_options` VALUES ('104', '30', 'A', '互相理解', '\0', '2019-03-20 21:10:41', null);
INSERT INTO `tb_options` VALUES ('105', '30', 'B', '互相配合', '', '2019-03-20 21:10:41', null);
INSERT INTO `tb_options` VALUES ('106', '30', 'C', '互相包容', '\0', '2019-03-20 21:10:41', null);
INSERT INTO `tb_options` VALUES ('107', '30', 'D', '互相支持', '\0', '2019-03-20 21:10:41', null);
INSERT INTO `tb_options` VALUES ('108', '32', 'A', '工程规模不大，工期较短，业主采用固定总价合同形式', '\0', '2019-03-20 21:12:35', null);
INSERT INTO `tb_options` VALUES ('109', '32', 'B', '业主采用固定总价形式，招标文件中工程量较准确、范围清楚', '\0', '2019-03-20 21:12:35', null);
INSERT INTO `tb_options` VALUES ('110', '32', 'C', '业主将做标期压缩的很短，承包人分析招标文件时间不足', '', '2019-03-20 21:12:35', null);
INSERT INTO `tb_options` VALUES ('111', '32', 'D', '工程环境不确定性较小、物价和汇率波动幅度较小', '\0', '2019-03-20 21:12:35', null);
INSERT INTO `tb_options` VALUES ('112', '33', 'A', '国家和主管部门颁发的有关合同、劳动保护、环境保护等方面的法律、法规', '\0', '2019-03-20 21:13:42', null);
INSERT INTO `tb_options` VALUES ('113', '33', 'B', '发包人、工程师和承包人之间的通知', '\0', '2019-03-20 21:13:42', null);
INSERT INTO `tb_options` VALUES ('114', '33', 'C', '发包人向工程师授权的文件', '\0', '2019-03-20 21:13:42', null);
INSERT INTO `tb_options` VALUES ('115', '33', 'D', '签订的合同文件', '', '2019-03-20 21:13:42', null);
INSERT INTO `tb_options` VALUES ('116', '34', 'A', '合同真实性审查', '\0', '2019-03-20 21:14:35', null);
INSERT INTO `tb_options` VALUES ('117', '34', 'B', '合同公平性审查', '\0', '2019-03-20 21:14:35', null);
INSERT INTO `tb_options` VALUES ('118', '34', 'C', '合同周密性审查', '\0', '2019-03-20 21:14:35', null);
INSERT INTO `tb_options` VALUES ('119', '34', 'D', '合同规范性审查', '', '2019-03-20 21:14:35', null);
INSERT INTO `tb_options` VALUES ('120', '35', 'A', '索赔实际上是一种经济惩罚行为', '\0', '2019-03-20 21:15:45', null);
INSERT INTO `tb_options` VALUES ('121', '35', 'B', '索赔和反索赔具有同时性', '', '2019-03-20 21:15:45', null);
INSERT INTO `tb_options` VALUES ('122', '35', 'C', '只有发包人可以针对承包人的索赔提出反索赔', '\0', '2019-03-20 21:15:45', null);
INSERT INTO `tb_options` VALUES ('123', '35', 'D', '索赔单指承包人向发包人的索赔', '\0', '2019-03-20 21:15:45', null);
INSERT INTO `tb_options` VALUES ('124', '36', 'A', '“专用条件”对某一类工程具有普遍适用性', '', '2019-03-20 21:16:50', null);
INSERT INTO `tb_options` VALUES ('125', '36', 'B', '“专用条件”中有很多建议性的措辞和范例', '\0', '2019-03-20 21:16:50', null);
INSERT INTO `tb_options` VALUES ('126', '36', 'C', '是将特定的工程合同具体化', '\0', '2019-03-20 21:16:50', null);
INSERT INTO `tb_options` VALUES ('127', '36', 'D', '是对“通用条件”进行了修改或补充', '\0', '2019-03-20 21:16:50', null);
INSERT INTO `tb_options` VALUES ('128', '37', 'A', '1', '\0', '2019-03-20 21:17:27', null);
INSERT INTO `tb_options` VALUES ('129', '37', 'B', '2', '\0', '2019-03-20 21:17:27', null);
INSERT INTO `tb_options` VALUES ('130', '37', 'C', '3', '', '2019-03-20 21:17:27', null);
INSERT INTO `tb_options` VALUES ('131', '37', 'D', '4', '\0', '2019-03-20 21:17:28', null);
INSERT INTO `tb_options` VALUES ('132', '38', 'A', '1', '\0', '2019-03-20 21:18:09', null);
INSERT INTO `tb_options` VALUES ('133', '38', 'B', '2', '\0', '2019-03-20 21:18:09', null);
INSERT INTO `tb_options` VALUES ('134', '38', 'C', '3', '\0', '2019-03-20 21:18:09', null);
INSERT INTO `tb_options` VALUES ('135', '38', 'D', '4', '', '2019-03-20 21:18:09', null);
INSERT INTO `tb_options` VALUES ('136', '39', 'A', '实施计划', '\0', '2019-03-20 21:19:11', null);
INSERT INTO `tb_options` VALUES ('137', '39', 'B', '检查绩效', '\0', '2019-03-20 21:19:11', null);
INSERT INTO `tb_options` VALUES ('138', '39', 'C', '进度控制', '', '2019-03-20 21:19:11', null);
INSERT INTO `tb_options` VALUES ('139', '39', 'D', '质量控制', '\0', '2019-03-20 21:19:11', null);
INSERT INTO `tb_options` VALUES ('140', '28', 'A', '1', '\0', '2019-03-20 22:22:53', null);
INSERT INTO `tb_options` VALUES ('141', '28', 'B', '2', '', '2019-03-20 22:22:53', null);
INSERT INTO `tb_options` VALUES ('142', '28', 'C', '3', '\0', '2019-03-20 22:22:53', null);
INSERT INTO `tb_options` VALUES ('143', '28', 'D', '4', '\0', '2019-03-20 22:22:53', null);
INSERT INTO `tb_options` VALUES ('148', '40', 'A', '错误答案', '\0', '2019-03-21 21:25:35', null);
INSERT INTO `tb_options` VALUES ('149', '40', 'B', '正确答案', '', '2019-03-21 21:25:35', null);
INSERT INTO `tb_options` VALUES ('150', '40', 'C', '凤飞飞错误答案', '\0', '2019-03-21 21:25:35', null);
INSERT INTO `tb_options` VALUES ('151', '40', 'D', '哈哈哈错误答案', '\0', '2019-03-21 21:25:35', null);

-- ----------------------------
-- Table structure for tb_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_question`;
CREATE TABLE `tb_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` int(2) DEFAULT NULL COMMENT '科目',
  `title` varchar(2000) DEFAULT NULL COMMENT '题目',
  `analysis` text COMMENT '解析',
  `type` int(2) DEFAULT NULL COMMENT '题型。1：单选；2：多选，3：其他',
  `answer` varchar(50) DEFAULT NULL COMMENT '答案，多选用,分割',
  `attachment` varchar(1000) DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='题库表';

-- ----------------------------
-- Records of tb_question
-- ----------------------------
INSERT INTO `tb_question` VALUES ('28', '1', '1+1=', '', '1', 'B', null, '2019-03-20 21:00:38', null);
INSERT INTO `tb_question` VALUES ('29', '1', '企业对外签订合同，应采用()。', 'A', '1', 'A', null, '2019-03-20 21:07:03', null);
INSERT INTO `tb_question` VALUES ('30', '1', '合同的签订和顺利实施是建立在参与合同各方紧密协作、( )、互相信任的基础之上。', 'B', '1', 'B', null, '2019-03-20 21:08:41', null);
INSERT INTO `tb_question` VALUES ('31', '1', '合同总体策划主要确定的一些重大问题，不包括( )。', 'B', '1', 'B', null, '2019-03-20 21:09:58', null);
INSERT INTO `tb_question` VALUES ('32', '1', '对于工程合同来说，如果存在( )问题，则承包人风险较大。', 'C', '1', 'C', null, '2019-03-20 21:12:35', null);
INSERT INTO `tb_question` VALUES ('33', '1', '下列不一定属于建设工程施工项目合同管理依据的是()', 'D', '1', 'D', null, '2019-03-20 21:13:42', null);
INSERT INTO `tb_question` VALUES ('34', '1', '合同审查的内容，不包括( )', 'D', '1', 'D', null, '2019-03-20 21:14:35', null);
INSERT INTO `tb_question` VALUES ('35', '1', '下列关于索赔和反索赔的描述，正确的是( )。', 'B', '1', 'B', null, '2019-03-20 21:15:45', null);
INSERT INTO `tb_question` VALUES ('36', '1', '下列对FIDIC合同条件中“专用条件”描述错误的是(A)。', 'SDD', '1', 'A', null, '2019-03-20 21:16:50', null);
INSERT INTO `tb_question` VALUES ('37', '1', '1+2=', '1+2=3', '1', 'C', null, '2019-03-20 21:17:27', null);
INSERT INTO `tb_question` VALUES ('38', '1', '1+3=', '1+3=4', '1', 'D', null, '2019-03-20 21:18:09', null);
INSERT INTO `tb_question` VALUES ('39', '1', '对合同范围的管理，可依靠一系列管理过程来实现，其中不包括()。', '', '1', 'C', null, '2019-03-20 21:19:11', null);
INSERT INTO `tb_question` VALUES ('40', '1', '测试1编辑', '测试试题，编辑', '1', 'B', null, '2019-03-21 21:25:08', null);
