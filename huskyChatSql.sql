CREATE DATABASE huskyChat;
USE huskyChat;

CREATE TABLE `user`(
	`id` INT PRIMARY KEY AUTO_INCREMENT COMMENT "id",
	`account` VARCHAR(30) NOT NULL COMMENT "账号",
	`password` VARCHAR(30) NOT NULL COMMENT "密码",
	`email` VARCHAR(30) NOT NULL COMMENT "邮箱",
	`phone` CHAR(11) COMMENT "手机号码",
	`nickName` VARCHAR(50) COMMENT "昵称",
	`gender` INT(1) COMMENT "性别",
	`birthday` DATETIME COMMENT "生日",
	`sign` VARCHAR(255) COMMENT "个性签名",
	`intro` TEXT COMMENT "个人介绍",
	`region` VARCHAR(255) COMMENT "地区",
	`headImg` VARCHAR(255) COMMENT "头像",
	`status` INT(1) COMMENT "用户状态",
	`relationStatus` INT (1) COMMENT "好友添加隐私状态"
	) COMMENT = "用户表";

CREATE TABLE `userSecurity`(
	`account` VARCHAR(30) NOT NULL COMMENT "用户账号",
	`security` TEXT NOT NULL COMMENT "密保",
	`updateTime` DATETIME COMMENT "修改时间"
	) COMMENT = "用户密保表";

SELECT
	`id`,
	`user`.`account`,
	`password`,
	`email`,
	`phone`,
	`nickName`,
	`gender`,
	`birthday`,
	`sign`,
	`intro`,
	`region`,
	`headImg`,
	`status`,
	`relationStatus`,
	`security`
	`updateTime`
FROM
	`user` LEFT JOIN `userSecurity` ON(`user`.`account` = `userSecurity`.`account`)
	
CREATE TABLE `userRelation`(
	`accountA` VARCHAR(30) COMMENT "用户A",
	`accountB` VARCHAR(30) COMMENT "用户B",
	`remarks` VARCHAR(50) COMMENT "备注昵称",
	`reasonRemarks` VARCHAR(255) COMMENT "添加好友备注原因",
	`time` DATETIME COMMENT "添加好友时间",
	`status` INT(1) COMMENT "好友状态"
	) COMMENT = "好友关系表";

CREATE TABLE `userMess`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT "id",
	`accountA` VARCHAR(30) NOT NULL COMMENT "账号B",
	`accountB` VARCHAR(30) NOT NULL COMMENT "用户B",
	`content` TEXT NOT NULL COMMENT "内容",
	`time` DATETIME COMMENT "发送时间",
	`messType` INT(1) COMMENT "消息类型",
	`status` INT(1) COMMENT "消息状态"
	) COMMENT = "用户消息表";

SELECT
	`accountA`,
	`accountB`,
	`content`,
	`time`,
	`messType`,
	`status`,
	(SELECT nickName FROM `user` WHERE `userMess`.`accountA` = `user`.`account`) AS `nickNameA`,
	(SELECT nickName FROM `user` WHERE `userMess`.`accountB` = `user`.`account`) AS `nickNameB`,
	(SELECT headImg FROM `user` WHERE `userMess`.`accountA` = `user`.`account`) AS `headImgA`,
	(SELECT headImg FROM `user` WHERE `userMess`.`accountB` = `user`.`account`) AS `headImgB`
FROM
	`userMess`
WHERE
	(`accountA` = '123456' AND `accountB` = '2224248204')
	OR
	(`accountB` = '2224248204' AND `accountA` = '123456')
ORDER BY `time` DESC
LIMIT 0,1

INSERT INTO `userMess`(`accountA`, `accountB`, `content`, `time`, `messType`, `status`)


SELECT
	`id`,
	`accountA`,
	`accountB`,
	`content`,
	`time`,
	`messType`,
	`status`
FROM `userMess`


CREATE TABLE `notice`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT "ID",
	`account` VARCHAR(30) NOT NULL COMMENT "用户账号",
	`content` TEXT NOT NULL COMMENT "通知内容",
	`type` INT(2) NOT NULL COMMENT "通知的类型", /* 0 用户注册成功, 1 用户找回密码 */
	`isRead` INT(1) NOT NULL COMMENT "已读状态", /* 0 未读, 1 已读 */
	`time` DATETIME NOT NULL COMMENT "通知时间") COMMENT = "通知";
