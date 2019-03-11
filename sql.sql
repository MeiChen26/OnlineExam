-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.39-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 jiaxiao 的数据库结构
CREATE DATABASE IF NOT EXISTS `jiaxiao` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `jiaxiao`;

-- 导出  表 jiaxiao.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名，登陆名',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名，昵称',
  `password` varchar(70) DEFAULT NULL COMMENT '密码',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `type` int(2) DEFAULT NULL COMMENT '1:管理员；2：学员',
  `coach_id` int(11) DEFAULT NULL COMMENT '学员所属教练',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除：1，已删除或无效；0，未删除或有效',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  jiaxiao.sys_user 的数据：~5 rows (大约)
DELETE FROM `sys_user`;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `user_name`, `real_name`, `password`, `phone`, `type`, `coach_id`, `deleted`, `create_time`, `update_time`) VALUES
	(1, 'admin', '超级管理员', '9afccd80c6b2bda18bf6419c5ee57bc2d0352724437fc776c0a3c349', '', 1, NULL, 0, '2017-02-13 18:23:40', '2018-12-02 16:44:29'),
	(2, '1234567890', '小镇', '9b5307133af194d892ca1b2b76a1a12f27d2cee4349ced1d666fbee8', '1234567890', NULL, 3, 1, '2018-12-04 16:15:04', '2018-12-04 16:36:57'),
	(3, '1234567890', '小镇', '8150a04581e90d4479d9eb298c5fdea3c2041c56bde725a6d458569e', '1234567890', NULL, 3, 1, '2018-12-04 16:37:14', '2018-12-05 16:17:05'),
	(4, '1234567890', '小镇', '0cc98ba4a742d168e94c72e937d235e991cf0b71771885a130a4ced3', '1234567890', 2, 3, 0, '2018-12-05 16:17:18', NULL),
	(5, '0123456789', '小李', '9f6275fcf7de34eaf2dc940d6feacc47c42f4d6e18d9baa72666342e', '0123456789', 2, 2, 0, '2018-12-05 16:45:56', '2018-12-07 14:16:07');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 jiaxiao.tb_coach 结构
CREATE TABLE IF NOT EXISTS `tb_coach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '教练名称',
  `carno` varchar(50) DEFAULT NULL COMMENT '车牌号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='教练表';

-- 正在导出表  jiaxiao.tb_coach 的数据：~2 rows (大约)
DELETE FROM `tb_coach`;
/*!40000 ALTER TABLE `tb_coach` DISABLE KEYS */;
INSERT INTO `tb_coach` (`id`, `name`, `carno`, `create_time`, `update_time`) VALUES
	(2, '李四', '鲁Q4561234', '2018-12-04 16:09:41', NULL);
/*!40000 ALTER TABLE `tb_coach` ENABLE KEYS */;

-- 导出  表 jiaxiao.tb_exam 结构
CREATE TABLE IF NOT EXISTS `tb_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学员id',
  `score1` int(11) DEFAULT NULL COMMENT '科目1分数',
  `score2` int(11) DEFAULT NULL COMMENT '科目2分数',
  `score3` int(11) DEFAULT NULL COMMENT '科目3分数',
  `score4` int(11) DEFAULT NULL COMMENT '科目4分数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='考试成绩表';

-- 正在导出表  jiaxiao.tb_exam 的数据：~2 rows (大约)
DELETE FROM `tb_exam`;
/*!40000 ALTER TABLE `tb_exam` DISABLE KEYS */;
INSERT INTO `tb_exam` (`id`, `student_id`, `score1`, `score2`, `score3`, `score4`, `create_time`, `update_time`) VALUES
	(2, 5, 90, 100, 100, 100, '2018-12-05 16:46:30', '2018-12-05 16:50:45');
/*!40000 ALTER TABLE `tb_exam` ENABLE KEYS */;

-- 导出  表 jiaxiao.tb_options 结构
CREATE TABLE IF NOT EXISTS `tb_options` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) DEFAULT NULL COMMENT '题目id',
  `opt` varchar(50) DEFAULT NULL COMMENT '选项',
  `content` varchar(1000) DEFAULT NULL COMMENT '选项内容',
  `rig` bit(1) DEFAULT NULL COMMENT '是否正确',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COMMENT='选项表';

-- 正在导出表  jiaxiao.tb_options 的数据：~21 rows (大约)
DELETE FROM `tb_options`;
/*!40000 ALTER TABLE `tb_options` DISABLE KEYS */;
INSERT INTO `tb_options` (`id`, `question_id`, `opt`, `content`, `rig`, `create_time`, `update_time`) VALUES
	(1, 2, 'A', '111', b'1', NULL, NULL),
	(26, 8, 'A', '444', b'1', '2018-12-06 10:58:46', NULL),
	(27, 8, 'B', '4443', b'1', '2018-12-06 10:58:46', NULL),
	(28, 8, 'C', '333', b'0', '2018-12-06 10:58:46', NULL),
	(29, 8, 'D', '222', b'0', '2018-12-06 10:58:46', NULL),
	(38, 11, 'A', '123', b'1', '2018-12-06 11:00:14', NULL),
	(39, 11, 'B', '1234', b'1', '2018-12-06 11:00:14', NULL),
	(40, 11, 'C', '12355', b'1', '2018-12-06 11:00:14', NULL),
	(41, 11, 'D', '12355', b'1', '2018-12-06 11:00:14', NULL),
	(44, 16, 'A', '正确', b'1', '2018-12-07 14:58:59', NULL),
	(45, 16, 'B', '错误', b'0', '2018-12-07 14:58:59', NULL),
	(46, 17, 'A', '正确', b'1', '2018-12-07 15:04:31', NULL),
	(47, 17, 'B', '错误', b'0', '2018-12-07 15:04:31', NULL),
	(48, 18, 'A', '正确', b'0', '2018-12-07 15:05:04', NULL),
	(49, 18, 'B', '错误', b'1', '2018-12-07 15:05:04', NULL),
	(50, 19, 'A', '正确', b'1', '2018-12-07 15:05:36', NULL),
	(51, 19, 'B', '错误', b'0', '2018-12-07 15:05:36', NULL),
	(52, 20, 'A', '正确', b'0', '2018-12-07 15:06:06', NULL),
	(53, 20, 'B', '错误', b'1', '2018-12-07 15:06:06', NULL),
	(54, 21, 'A', '正确', b'1', '2018-12-07 15:06:40', NULL),
	(55, 21, 'B', '错误', b'0', '2018-12-07 15:06:40', NULL),
	(56, 22, 'A', '正确', b'1', '2018-12-07 15:07:10', NULL),
	(57, 22, 'B', '错误', b'0', '2018-12-07 15:07:10', NULL),
	(58, 23, 'A', '正确', b'1', '2018-12-07 15:07:37', NULL),
	(59, 23, 'B', '错误', b'0', '2018-12-07 15:07:37', NULL),
	(60, 24, 'A', '正确', b'0', '2018-12-07 15:08:04', NULL),
	(61, 24, 'B', '错误', b'1', '2018-12-07 15:08:04', NULL),
	(64, 9, 'A', '正确', b'1', '2018-12-07 15:09:32', NULL),
	(65, 9, 'B', '错误', b'0', '2018-12-07 15:09:32', NULL),
	(66, 10, 'A', '正确', b'0', '2018-12-07 15:22:27', NULL),
	(67, 10, 'B', '错误', b'1', '2018-12-07 15:22:27', NULL),
	(72, 25, 'A', '一种违法行为', b'0', '2018-12-07 15:37:44', NULL),
	(73, 25, 'B', '两种违法行为', b'1', '2018-12-07 15:37:44', NULL),
	(74, 25, 'C', '三种违法行为', b'0', '2018-12-07 15:37:44', NULL),
	(75, 25, 'D', '四种违法行为', b'1', '2018-12-07 15:37:44', NULL),
	(76, 26, 'A', '醉酒驾驶', b'1', '2018-12-07 15:40:18', NULL),
	(77, 26, 'B', '疲劳驾驶', b'0', '2018-12-07 15:40:18', NULL),
	(78, 26, 'C', '超速教师', b'1', '2018-12-07 15:40:18', NULL),
	(79, 26, 'D', '即是驾驶', b'1', '2018-12-07 15:40:18', NULL),
	(80, 27, 'A', '违规', b'0', '2018-12-07 15:43:24', NULL),
	(81, 27, 'B', '违章', b'0', '2018-12-07 15:43:24', NULL),
	(82, 27, 'C', '违法', b'1', '2018-12-07 15:43:24', NULL),
	(83, 27, 'D', '犯罪', b'0', '2018-12-07 15:43:24', NULL);
/*!40000 ALTER TABLE `tb_options` ENABLE KEYS */;

-- 导出  表 jiaxiao.tb_question 结构
CREATE TABLE IF NOT EXISTS `tb_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` int(2) DEFAULT NULL COMMENT '科目，1：科目1；2：科目2；3：科目3；4：科目4',
  `title` varchar(2000) DEFAULT NULL COMMENT '题目',
  `analysis` text COMMENT '解析',
  `type` int(2) DEFAULT NULL COMMENT '题型。1：单选；2：多选，3：其他',
  `answer` varchar(50) DEFAULT NULL COMMENT '答案，多选用,分割',
  `attachment` varchar(1000) DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='题库表';

-- 正在导出表  jiaxiao.tb_question 的数据：~9 rows (大约)
DELETE FROM `tb_question`;
/*!40000 ALTER TABLE `tb_question` DISABLE KEYS */;
INSERT INTO `tb_question` (`id`, `subject`, `title`, `analysis`, `type`, `answer`, `attachment`, `create_time`, `update_time`) VALUES
	(1, 2, '倒车入库操作', NULL, NULL, NULL, 'assets/upload/question/1543910572532.mp4', '2018-12-04 15:09:29', NULL),
	(7, 3, '测试1', NULL, NULL, NULL, 'assets/upload/question/1543910606009.mp4', '2018-12-04 16:03:26', NULL),
	(8, 4, '44', '大饭桶感染他人', 2, 'A,B', NULL, '2018-12-04 16:04:07', NULL),
	(9, 1, '交通信号包括交通信号灯、交通标志、交通标线和交通警察的指挥。', '全国统一的交通信号分为：交通信号灯、交通标志、交通标线和交通警察的指挥。', 1, 'A', NULL, '2018-12-04 16:08:45', NULL),
	(10, 1, '夜间通过无交通信号灯控制的交叉路口时，不得变换远、近光灯。', '换远近光灯是为了让来车认识到前方有车，应注意减速慢行注意安全。', 1, 'B', NULL, '2018-12-06 10:59:38', NULL),
	(11, 4, '123', '是的法定是的法定是的法定是的法定是的法定是的法定是的法定是的法定是的法定是的法定是的法定是的法定是的法定', 2, 'A,B,C,D', NULL, '2018-12-06 11:00:04', NULL),
	(12, 2, '2', NULL, NULL, NULL, 'assets/upload/question/1544066871330.mp4', '2018-12-06 11:27:51', NULL),
	(13, 2, '3', NULL, NULL, NULL, 'assets/upload/question/1544067043985.mp4', '2018-12-06 11:30:44', NULL),
	(14, 2, '5', NULL, NULL, NULL, 'assets/upload/question/1544067052229.mp4', '2018-12-06 11:30:52', NULL),
	(16, 1, '高速公路上同时有最高和最低速度限制，因为过快或者过慢都容易导致追尾。', '过快容易与前车追尾，过慢容易与后车追尾，题干所说的是正确的。', 1, 'A', NULL, '2018-12-07 14:58:59', NULL),
	(17, 1, '驾驶人有使用其他车辆检验合格标志嫌疑的，交通警察可依法扣留车辆。', '《道路交通安全违法行为处理程序规定》第二十五条规定，有下列情形之一的，依法扣留车辆：有伪造、变造或者使用伪造、变造的机动车登记证书、号牌、行驶证、检验合格标志、 保险标志、驾驶证或者使用其他车辆的机动车登记证书、号牌、行驶证、检验合格标志、保险标志嫌疑的。', 1, 'A', NULL, '2018-12-07 15:04:31', NULL),
	(18, 1, '黄灯持续闪烁，表示机动车可以加速通过。', '黄灯持续闪烁，表明已经越过停止线的车辆可以行使，没越过停止线的车辆要停车等待，机动车不能加速通过。', 1, 'B', NULL, '2018-12-07 15:05:04', NULL),
	(19, 1, '驾驶机动车在道路上超车完毕驶回原车道时开启右转向灯。', '应该这样想，超车只能左边超车，当你超车完成回到原来的道路就是往右转了。实在不懂的还可以看看《道路交通安全法实施条例实施条例》 \r\n第五十七条：机动车应当按照下列规定使用转向灯： \r\n（一）向左转弯、向左变更车道、准备超车、驶离停车地点或者掉头时，应当提前开启左转向灯； \r\n（二）向右转弯、向右变更车道、超车完毕驶回原车道、靠路边停车时，应当提前开启右转向灯。 \r\n因此，本题答案应为正确。', 1, 'A', NULL, '2018-12-07 15:05:36', NULL),
	(20, 1, '车辆在高速公路行驶时，可以仅凭感觉确认车速。', '不仅是在高速行车中，任何时候都不能凭感觉去确认车速。要通过速度表确认。', 1, 'B', NULL, '2018-12-07 15:06:06', NULL),
	(21, 1, '驾驶机动车发生财产损失交通事故，当事人对事实及成因无争议的可先撤离现场。', '《道路交通事故处理程序规定》第十三条：机动车与机动车、机动车与非机动车发生财产损失事故，当事人对事实及成因无争议的，可以自行协商处理损害赔偿事宜。车辆可以移动的，当事人应当在确保安全的原则下对现场拍照或者标划事故车辆现场位置后，立即撤离现场，将车辆移至不妨碍交通的地点，再进行协商。非机动车与非机动车或者行人发生财产损失事故，基本事实及成因清楚的，当事人应当先撤离现场，再协商处理损害赔偿事宜。', 1, 'A', NULL, '2018-12-07 15:06:40', NULL),
	(22, 1, '驶机动车下长坡时，仅靠行车制动器制动，容易引起行车制动器失灵。', '汽车制动分为三种：1、行车制动，就是脚下的制动踏板，控制四轮；2、驻车制动，就是手刹车，停车时防止前滑后溜；3、发动机制动，也就是抢档，用于刹车失灵，在行车制动失灵时用，一级一级减档，从高到底，利用发动机变速箱的原理使车速降下来。下长坡时，驾驶员长时间使用行车制动器，易造成制动器热衰退，汽车的制动效能变差。', 1, 'A', NULL, '2018-12-07 15:07:10', NULL),
	(23, 1, '闪光警告信号灯为持续闪烁的黄灯，其作用是提示车辆、行人需要快速通过。', '持续闪烁的黄灯是为了警告车辆和行人在此处应该注意观察，确认安全后再通过，而非快速通过。', 1, 'A', NULL, '2018-12-07 15:07:37', NULL),
	(24, 1, '在后方无来车的情况下，在隧道中倒车应靠边行驶。', '《中华人民共和国道路交通安全法实施条例》第五十条规定： 机动车不得在铁路道口、交叉路口、单行路、桥梁、急弯、陡坡或者隧道中倒车。', 1, 'B', NULL, '2018-12-07 15:08:04', NULL),
	(25, 4, '动画8中有几种违法行为？', '有2种违法行为：1、遮挡号牌；2、占用非机动车道右转弯！这一题要特别注意“鸣喇叭催促前方机动车”不算违法行为。部分同学认为是三种违法行为，但实际考过学生反馈正确答案是有二种违法行为。', 1, 'B,D', 'assets/upload/question/1544168263417.gif', '2018-12-07 15:34:33', NULL),
	(26, 4, '9/1131  林某驾车以110公里/小时的速度在城市道路行驶，与一辆机动车追尾后弃车逃离被群众拦下。经鉴定，事发时林某血液中的酒精浓度为135.8毫克/百毫升。林某的主要违法行为是什么？', '违法行为一：以110公里/小时的速度在城市道路行驶违法行为；二：与一辆机动车追尾后弃车逃离违法行为；三：林某血液中的酒精浓度为135.8毫克/百毫升。', 2, 'A,C,D', NULL, '2018-12-07 15:40:18', NULL),
	(27, 1, '驾驶这种机动车上路行驶属于什么行为？', '《道路交通安全法》第十一条：机动车号牌应当按照规定悬挂并保持清晰、完整，不得故意遮挡、污损。违反《道路交通安全法》即为违法。', 1, 'C', 'assets/upload/question/1544168603971.jpg', '2018-12-07 15:43:24', NULL);
/*!40000 ALTER TABLE `tb_question` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
