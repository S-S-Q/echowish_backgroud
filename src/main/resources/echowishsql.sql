CREATE DATABASE echowish;
USE echowish;
DROP TABLE IF EXISTS `preconcern`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `concern`;
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `chat`;


CREATE TABLE `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account` VARCHAR(20) DEFAULT NULL COMMENT '用户账户号码',
  `password` VARCHAR(20) DEFAULT NULL COMMENT '用户密码',
  `name` VARCHAR(10) DEFAULT ('用户') COMMENT '用户名字',
  `sex` INT DEFAULT (-(1)) COMMENT '性别 未选择为-1 男为0 女为1',
  `grade` INT DEFAULT (2021) COMMENT '年级',
  `campus` VARCHAR(5) DEFAULT ('未选择') COMMENT '大学城校区，国际校区,五山校区,未选择',
  `head_image` VARCHAR(40) DEFAULT ('') COMMENT '头像图片路径',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `account` (`account`),
  CONSTRAINT `user_chk_1` CHECK (((`sex` = -(1)) OR (`sex` = 0) OR (`sex` = 1))),
  CONSTRAINT `user_chk_2` CHECK (((`campus` = '大学城校区') OR (`campus` ='国际校区') OR (`campus` ='五山校区') OR (`campus` = '未选择')))
) ;


CREATE TABLE `post` (
  `post_id` INT NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `user_id` INT DEFAULT NULL COMMENT '发帖用户Id',
  `title` VARCHAR(20) NOT NULL COMMENT '帖子标题',
  `zone` VARCHAR(4) DEFAULT NULL,
  `content` VARCHAR(100) NOT NULL COMMENT '帖子内容',
  `reward` VARCHAR(10) DEFAULT NULL COMMENT '报酬',
  `post_image` VARCHAR(40) DEFAULT NULL COMMENT '帖子图像',
  `time` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  INDEX(`time`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `post_chk_1` CHECK (((`zone` = '组队') OR (`zone` = '问答') OR (`zone` = '寻物') OR (`zone` = '跑腿') OR (`zone` = '二手')))
) ;



CREATE TABLE `comment` (
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `content` VARCHAR(30) DEFAULT NULL,
  `time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`,`time`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ;


CREATE TABLE `preconcern` (
  `host_id` INT NOT NULL,
  `fri_id` INT NOT NULL ,
  PRIMARY KEY (`host_id`,`fri_id`),
  KEY `fri_id` (`fri_id`),
  FOREIGN KEY (`host_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`fri_id`) REFERENCES `user` (`user_id`)
) ;

CREATE TABLE concern(
  `host_id` INT NOT NULL ,
  `fri_id` INT NOT NULL ,
  PRIMARY KEY (`host_id`,`fri_id`),
  KEY `fri_id` (`fri_id`),
FOREIGN KEY (`host_id`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`fri_id`) REFERENCES `user` (`user_id`)
) ;

CREATE TABLE chat(
content VARCHAR(30) ,
my_id INT NOT NULL ,
other_id INT NOT NULL,
`time` TIMESTAMP,
FOREIGN KEY(`my_id`) REFERENCES `user` (`user_id`),
FOREIGN KEY(other_id) REFERENCES `user` (`user_id`)
);