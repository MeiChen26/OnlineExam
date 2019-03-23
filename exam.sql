/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-23 16:55:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for front_user
-- ----------------------------
DROP TABLE IF EXISTS `front_user`;
CREATE TABLE `front_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime NOT NULL,
  `student_no` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='注册用户表';

-- ----------------------------
-- Records of front_user
-- ----------------------------
INSERT INTO `front_user` VALUES ('1', '张三', '123456', '0', '2018-04-21 15:21:41', '123456');

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
  `coach_id` int(11) DEFAULT NULL COMMENT '学员所属教练',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '9afccd80c6b2bda18bf6419c5ee57bc2d0352724437fc776c0a3c349', '', '1', null, '0', '2017-02-13 18:23:40', '2018-12-02 16:44:29');
INSERT INTO `sys_user` VALUES ('2', '1234567890', '小镇', '9b5307133af194d892ca1b2b76a1a12f27d2cee4349ced1d666fbee8', '1234567890', null, '3', '1', '2018-12-04 16:15:04', '2018-12-04 16:36:57');
INSERT INTO `sys_user` VALUES ('3', '1234567890', '小镇', '8150a04581e90d4479d9eb298c5fdea3c2041c56bde725a6d458569e', '1234567890', null, '3', '1', '2018-12-04 16:37:14', '2018-12-05 16:17:05');
INSERT INTO `sys_user` VALUES ('4', '1234567890', '小镇', '0cc98ba4a742d168e94c72e937d235e991cf0b71771885a130a4ced3', '1234567890', '2', '3', '0', '2018-12-05 16:17:18', null);
INSERT INTO `sys_user` VALUES ('5', '0123456789', '小李', '9f6275fcf7de34eaf2dc940d6feacc47c42f4d6e18d9baa72666342e', '0123456789', '2', '2', '0', '2018-12-05 16:45:56', '2018-12-07 14:16:07');

-- ----------------------------
-- Table structure for tb_exam
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam`;
CREATE TABLE `tb_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学员id',
  `score` int(11) DEFAULT NULL COMMENT '科目1分数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='考试成绩表';

-- ----------------------------
-- Records of tb_exam
-- ----------------------------
INSERT INTO `tb_exam` VALUES ('2', '5', '90', '2018-12-05 16:46:30', '2018-12-05 16:50:45');
INSERT INTO `tb_exam` VALUES ('3', '1', '2', '2019-03-23 08:10:14', '2019-03-23 08:10:14');
INSERT INTO `tb_exam` VALUES ('4', '1', '6', '2019-03-23 14:51:10', '2019-03-23 14:51:10');
INSERT INTO `tb_exam` VALUES ('5', '1', '6', '2019-03-23 14:53:19', '2019-03-23 14:53:19');
INSERT INTO `tb_exam` VALUES ('6', '1', '6', '2019-03-23 14:55:25', '2019-03-23 14:55:25');
INSERT INTO `tb_exam` VALUES ('7', '1', '2', '2019-03-23 15:02:30', '2019-03-23 15:02:30');
INSERT INTO `tb_exam` VALUES ('8', '1', '4', '2019-03-23 15:04:39', '2019-03-23 15:04:39');
INSERT INTO `tb_exam` VALUES ('9', '1', '8', '2019-03-23 16:10:18', '2019-03-23 16:10:18');
INSERT INTO `tb_exam` VALUES ('10', '1', '2', '2019-03-23 16:25:33', '2019-03-23 16:25:33');
INSERT INTO `tb_exam` VALUES ('11', '1', '4', '2019-03-23 16:27:04', '2019-03-23 16:27:04');

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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='答题信息表';

-- ----------------------------
-- Records of tb_exam_info
-- ----------------------------
INSERT INTO `tb_exam_info` VALUES ('1', '6', '18', null, '1', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('2', '6', '25', null, '1', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('3', '6', '16', null, '1', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('4', '6', '22', null, '0', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('5', '6', '23', null, '0', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('6', '6', '9', null, '0', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('7', '6', '27', null, '0', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('8', '6', '17', null, '0', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('9', '6', '10', null, '0', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('10', '6', '26', null, '0', '2019-03-23 14:55:25', null);
INSERT INTO `tb_exam_info` VALUES ('11', '7', '21', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('12', '7', '10', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('13', '7', '19', null, '1', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('14', '7', '22', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('15', '7', '9', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('16', '7', '26', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('17', '7', '18', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('18', '7', '17', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('19', '7', '25', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('20', '7', '16', null, '0', '2019-03-23 15:02:30', null);
INSERT INTO `tb_exam_info` VALUES ('21', '8', '23', null, '0', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('22', '8', '16', null, '1', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('23', '8', '22', null, '1', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('24', '8', '25', null, '0', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('25', '8', '19', null, '0', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('26', '8', '26', null, '0', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('27', '8', '27', null, '0', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('28', '8', '10', null, '0', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('29', '8', '21', null, '0', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('30', '8', '24', null, '0', '2019-03-23 15:04:39', null);
INSERT INTO `tb_exam_info` VALUES ('31', '9', '21', null, '1', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('32', '9', '18', null, '1', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('33', '9', '25', null, '0', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('34', '9', '10', null, '1', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('35', '9', '24', null, '1', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('36', '9', '17', null, '0', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('37', '9', '16', null, '0', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('38', '9', '26', null, '0', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('39', '9', '27', null, '0', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('40', '9', '19', null, '0', '2019-03-23 16:10:18', null);
INSERT INTO `tb_exam_info` VALUES ('41', '11', '21', '1', '0', '2019-03-23 16:27:04', 'B');
INSERT INTO `tb_exam_info` VALUES ('42', '11', '9', '2', '1', '2019-03-23 16:27:04', 'A');
INSERT INTO `tb_exam_info` VALUES ('43', '11', '26', '3', '0', '2019-03-23 16:27:04', 'A');
INSERT INTO `tb_exam_info` VALUES ('44', '11', '10', '4', '1', '2019-03-23 16:27:04', 'B');
INSERT INTO `tb_exam_info` VALUES ('45', '11', '19', '5', '0', '2019-03-23 16:27:04', null);
INSERT INTO `tb_exam_info` VALUES ('46', '11', '27', '6', '0', '2019-03-23 16:27:04', null);
INSERT INTO `tb_exam_info` VALUES ('47', '11', '17', '7', '0', '2019-03-23 16:27:04', null);
INSERT INTO `tb_exam_info` VALUES ('48', '11', '18', '8', '0', '2019-03-23 16:27:04', null);
INSERT INTO `tb_exam_info` VALUES ('49', '11', '23', '9', '0', '2019-03-23 16:27:04', null);
INSERT INTO `tb_exam_info` VALUES ('50', '11', '25', '10', '0', '2019-03-23 16:27:04', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COMMENT='选项表';

-- ----------------------------
-- Records of tb_options
-- ----------------------------
INSERT INTO `tb_options` VALUES ('1', '2', 'A', '111', '', null, null);
INSERT INTO `tb_options` VALUES ('26', '8', 'A', '444', '', '2018-12-06 10:58:46', null);
INSERT INTO `tb_options` VALUES ('27', '8', 'B', '4443', '', '2018-12-06 10:58:46', null);
INSERT INTO `tb_options` VALUES ('28', '8', 'C', '333', '\0', '2018-12-06 10:58:46', null);
INSERT INTO `tb_options` VALUES ('29', '8', 'D', '222', '\0', '2018-12-06 10:58:46', null);
INSERT INTO `tb_options` VALUES ('38', '11', 'A', '123', '', '2018-12-06 11:00:14', null);
INSERT INTO `tb_options` VALUES ('39', '11', 'B', '1234', '', '2018-12-06 11:00:14', null);
INSERT INTO `tb_options` VALUES ('40', '11', 'C', '12355', '', '2018-12-06 11:00:14', null);
INSERT INTO `tb_options` VALUES ('41', '11', 'D', '12355', '', '2018-12-06 11:00:14', null);
INSERT INTO `tb_options` VALUES ('44', '16', 'A', '正确', '', '2018-12-07 14:58:59', null);
INSERT INTO `tb_options` VALUES ('45', '16', 'B', '错误', '\0', '2018-12-07 14:58:59', null);
INSERT INTO `tb_options` VALUES ('46', '17', 'A', '正确', '', '2018-12-07 15:04:31', null);
INSERT INTO `tb_options` VALUES ('47', '17', 'B', '错误', '\0', '2018-12-07 15:04:31', null);
INSERT INTO `tb_options` VALUES ('48', '18', 'A', '正确', '\0', '2018-12-07 15:05:04', null);
INSERT INTO `tb_options` VALUES ('49', '18', 'B', '错误', '', '2018-12-07 15:05:04', null);
INSERT INTO `tb_options` VALUES ('50', '19', 'A', '正确', '', '2018-12-07 15:05:36', null);
INSERT INTO `tb_options` VALUES ('51', '19', 'B', '错误', '\0', '2018-12-07 15:05:36', null);
INSERT INTO `tb_options` VALUES ('52', '20', 'A', '正确', '\0', '2018-12-07 15:06:06', null);
INSERT INTO `tb_options` VALUES ('53', '20', 'B', '错误', '', '2018-12-07 15:06:06', null);
INSERT INTO `tb_options` VALUES ('54', '21', 'A', '正确', '', '2018-12-07 15:06:40', null);
INSERT INTO `tb_options` VALUES ('55', '21', 'B', '错误', '\0', '2018-12-07 15:06:40', null);
INSERT INTO `tb_options` VALUES ('56', '22', 'A', '正确', '', '2018-12-07 15:07:10', null);
INSERT INTO `tb_options` VALUES ('57', '22', 'B', '错误', '\0', '2018-12-07 15:07:10', null);
INSERT INTO `tb_options` VALUES ('58', '23', 'A', '正确', '', '2018-12-07 15:07:37', null);
INSERT INTO `tb_options` VALUES ('59', '23', 'B', '错误', '\0', '2018-12-07 15:07:37', null);
INSERT INTO `tb_options` VALUES ('64', '9', 'A', '正确', '', '2018-12-07 15:09:32', null);
INSERT INTO `tb_options` VALUES ('65', '9', 'B', '错误', '\0', '2018-12-07 15:09:32', null);
INSERT INTO `tb_options` VALUES ('66', '10', 'A', '正确', '\0', '2018-12-07 15:22:27', null);
INSERT INTO `tb_options` VALUES ('67', '10', 'B', '错误', '', '2018-12-07 15:22:27', null);
INSERT INTO `tb_options` VALUES ('72', '25', 'A', '一种违法行为', '\0', '2018-12-07 15:37:44', null);
INSERT INTO `tb_options` VALUES ('73', '25', 'B', '两种违法行为', '', '2018-12-07 15:37:44', null);
INSERT INTO `tb_options` VALUES ('74', '25', 'C', '三种违法行为', '\0', '2018-12-07 15:37:44', null);
INSERT INTO `tb_options` VALUES ('75', '25', 'D', '四种违法行为', '', '2018-12-07 15:37:44', null);
INSERT INTO `tb_options` VALUES ('76', '26', 'A', '醉酒驾驶', '', '2018-12-07 15:40:18', null);
INSERT INTO `tb_options` VALUES ('77', '26', 'B', '疲劳驾驶', '\0', '2018-12-07 15:40:18', null);
INSERT INTO `tb_options` VALUES ('78', '26', 'C', '超速教师', '', '2018-12-07 15:40:18', null);
INSERT INTO `tb_options` VALUES ('79', '26', 'D', '即是驾驶', '', '2018-12-07 15:40:18', null);
INSERT INTO `tb_options` VALUES ('84', '27', 'A', '违规', '\0', '2019-03-23 14:46:15', null);
INSERT INTO `tb_options` VALUES ('85', '27', 'B', '违章', '\0', '2019-03-23 14:46:15', null);
INSERT INTO `tb_options` VALUES ('86', '27', 'C', '违法', '', '2019-03-23 14:46:15', null);
INSERT INTO `tb_options` VALUES ('87', '27', 'D', '犯罪', '\0', '2019-03-23 14:46:15', null);
INSERT INTO `tb_options` VALUES ('88', '24', 'A', '正确', '\0', '2019-03-23 14:46:47', null);
INSERT INTO `tb_options` VALUES ('89', '24', 'B', '错误', '', '2019-03-23 14:46:47', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='题库表';

-- ----------------------------
-- Records of tb_question
-- ----------------------------
INSERT INTO `tb_question` VALUES ('9', '1', '交通信号包括交通信号灯、交通标志、交通标线和交通警察的指挥。', '全国统一的交通信号分为：交通信号灯、交通标志、交通标线和交通警察的指挥。', '1', 'A', null, '2018-12-04 16:08:45', null);
INSERT INTO `tb_question` VALUES ('10', '1', '夜间通过无交通信号灯控制的交叉路口时，不得变换远、近光灯。', '换远近光灯是为了让来车认识到前方有车，应注意减速慢行注意安全。', '1', 'B', null, '2018-12-06 10:59:38', null);
INSERT INTO `tb_question` VALUES ('16', '1', '高速公路上同时有最高和最低速度限制，因为过快或者过慢都容易导致追尾。', '过快容易与前车追尾，过慢容易与后车追尾，题干所说的是正确的。', '1', 'A', null, '2018-12-07 14:58:59', null);
INSERT INTO `tb_question` VALUES ('17', '1', '驾驶人有使用其他车辆检验合格标志嫌疑的，交通警察可依法扣留车辆。', '《道路交通安全违法行为处理程序规定》第二十五条规定，有下列情形之一的，依法扣留车辆：有伪造、变造或者使用伪造、变造的机动车登记证书、号牌、行驶证、检验合格标志、 保险标志、驾驶证或者使用其他车辆的机动车登记证书、号牌、行驶证、检验合格标志、保险标志嫌疑的。', '1', 'A', null, '2018-12-07 15:04:31', null);
INSERT INTO `tb_question` VALUES ('18', '1', '黄灯持续闪烁，表示机动车可以加速通过。', '黄灯持续闪烁，表明已经越过停止线的车辆可以行使，没越过停止线的车辆要停车等待，机动车不能加速通过。', '1', 'B', null, '2018-12-07 15:05:04', null);
INSERT INTO `tb_question` VALUES ('19', '1', '驾驶机动车在道路上超车完毕驶回原车道时开启右转向灯。', '应该这样想，超车只能左边超车，当你超车完成回到原来的道路就是往右转了。实在不懂的还可以看看《道路交通安全法实施条例实施条例》 \r\n第五十七条：机动车应当按照下列规定使用转向灯： \r\n（一）向左转弯、向左变更车道、准备超车、驶离停车地点或者掉头时，应当提前开启左转向灯； \r\n（二）向右转弯、向右变更车道、超车完毕驶回原车道、靠路边停车时，应当提前开启右转向灯。 \r\n因此，本题答案应为正确。', '1', 'A', null, '2018-12-07 15:05:36', null);
INSERT INTO `tb_question` VALUES ('20', '1', '车辆在高速公路行驶时，可以仅凭感觉确认车速。', '不仅是在高速行车中，任何时候都不能凭感觉去确认车速。要通过速度表确认。', '1', 'B', null, '2018-12-07 15:06:06', null);
INSERT INTO `tb_question` VALUES ('21', '1', '驾驶机动车发生财产损失交通事故，当事人对事实及成因无争议的可先撤离现场。', '《道路交通事故处理程序规定》第十三条：机动车与机动车、机动车与非机动车发生财产损失事故，当事人对事实及成因无争议的，可以自行协商处理损害赔偿事宜。车辆可以移动的，当事人应当在确保安全的原则下对现场拍照或者标划事故车辆现场位置后，立即撤离现场，将车辆移至不妨碍交通的地点，再进行协商。非机动车与非机动车或者行人发生财产损失事故，基本事实及成因清楚的，当事人应当先撤离现场，再协商处理损害赔偿事宜。', '1', 'A', null, '2018-12-07 15:06:40', null);
INSERT INTO `tb_question` VALUES ('22', '1', '驶机动车下长坡时，仅靠行车制动器制动，容易引起行车制动器失灵。', '汽车制动分为三种：1、行车制动，就是脚下的制动踏板，控制四轮；2、驻车制动，就是手刹车，停车时防止前滑后溜；3、发动机制动，也就是抢档，用于刹车失灵，在行车制动失灵时用，一级一级减档，从高到底，利用发动机变速箱的原理使车速降下来。下长坡时，驾驶员长时间使用行车制动器，易造成制动器热衰退，汽车的制动效能变差。', '1', 'A', null, '2018-12-07 15:07:10', null);
INSERT INTO `tb_question` VALUES ('23', '1', '闪光警告信号灯为持续闪烁的黄灯，其作用是提示车辆、行人需要快速通过。', '持续闪烁的黄灯是为了警告车辆和行人在此处应该注意观察，确认安全后再通过，而非快速通过。', '1', 'A', null, '2018-12-07 15:07:37', null);
INSERT INTO `tb_question` VALUES ('24', '1', '在后方无来车的情况下，在隧道中倒车应靠边行驶。', '《中华人民共和国道路交通安全法实施条例》第五十条规定： 机动车不得在铁路道口、交叉路口、单行路、桥梁、急弯、陡坡或者隧道中倒车。', '1', 'B', '', '2018-12-07 15:08:04', null);
INSERT INTO `tb_question` VALUES ('25', '4', '动画8中有几种违法行为？', '有2种违法行为：1、遮挡号牌；2、占用非机动车道右转弯！这一题要特别注意“鸣喇叭催促前方机动车”不算违法行为。部分同学认为是三种违法行为，但实际考过学生反馈正确答案是有二种违法行为。', '1', 'B,D', '', '2018-12-07 15:34:33', null);
INSERT INTO `tb_question` VALUES ('26', '4', '9/1131  林某驾车以110公里/小时的速度在城市道路行驶，与一辆机动车追尾后弃车逃离被群众拦下。经鉴定，事发时林某血液中的酒精浓度为135.8毫克/百毫升。林某的主要违法行为是什么？', '违法行为一：以110公里/小时的速度在城市道路行驶违法行为；二：与一辆机动车追尾后弃车逃离违法行为；三：林某血液中的酒精浓度为135.8毫克/百毫升。', '2', 'A,C,D', '', '2018-12-07 15:40:18', null);
INSERT INTO `tb_question` VALUES ('27', '1', '驾驶这种机动车上路行驶属于什么行为？', '《道路交通安全法》第十一条：机动车号牌应当按照规定悬挂并保持清晰、完整，不得故意遮挡、污损。违反《道路交通安全法》即为违法。', '1', 'C', '', '2018-12-07 15:43:24', null);
