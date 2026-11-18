-- CREATE DATABASE `zpwan_blog_info`;
-- use `zpwan_blog_info`;

CREATE TABLE `zpwan_blog_article` (
  `id` varchar(50) NOT NULL COMMENT 'id,主键',
  `title` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '文章名称',
  `content` longtext CHARACTER SET utf8mb4 NOT NULL  COMMENT '文章内容-代码',
   `md` longtext CHARACTER SET utf8mb4 NOT NULL COMMENT '文章内容-用于编辑',
  `imgUrl` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '文章封面',
  `quotes` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '文章附属名言',
  `description` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '文章概述',
  `author` varchar(10) NOT NULL DEFAULT 'wzp' COMMENT '文章作者',
  `tags` varchar(100) NOT NULL DEFAULT '' COMMENT '文章标签',
  `first_catalogId` varchar(50) NOT NULL DEFAULT '' COMMENT '一级目录id',
  `first_catalogName` varchar(50) NOT NULL DEFAULT '' COMMENT '一级目录名称value',
  `second_catalogId` varchar(50) NOT NULL DEFAULT '' COMMENT '二级目录id',
  `second_catalogName` varchar(50) NOT NULL DEFAULT '' COMMENT '二级目录名称value',
  `isDelete` int(10) DEFAULT '0' COMMENT '是否被删除 0:否  1：是',
  `isShelves` int(10) DEFAULT '1' COMMENT '是否上架 0:否  1：是',
  `isRecommend` int(10) DEFAULT '0' COMMENT '是否推荐 0:否 1:是',
  `isTop`int(10) DEFAULT '0' COMMENT '是否置顶 0:否  1:是',
  `visit_count` int(255) NOT NULL DEFAULT '0' COMMENT '访问量',
  `like_count` int(255) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `comment_count` int(255) NOT NULL DEFAULT '0' COMMENT '评论数',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `zpwan_blog_file` (
  `id` VARCHAR(50) NOT NULL COMMENT 'id',
  `fileName` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '文件名',
  `md5` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'md5值',
  `url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '地址',
  `length` bigint(10) NOT NULL DEFAULT '0' COMMENT '长度',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `zpwan_blog_dictionary` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'code值',
  `value` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'value',
  `type` INT(10) NOT NULL DEFAULT '1' COMMENT '类型 1:一级目录   2：二级目录',
  `description` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '描述',
  `pId` INT(10) NOT NULL DEFAULT '0' COMMENT '父级id 默认为0 代表没有父级',
  `pCode` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '父级Code值',
   `pValue` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '父级Value值',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE `zpwan_blog_tag` (
    `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `code` VARCHAR(20) NOT NULL DEFAULT '' COMMENT 'code值',
    `value` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'value值',
    `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    PRIMARY KEY(`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `zpwan_blog_banner` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` VARCHAR (50) NOT NULL  DEFAULT '' COMMENT '标题',
  `url` VARCHAR (150) NOT NULL DEFAULT '' COMMENT '跳转链接',
  `imgUrl` VARCHAR (150) NOT NULL DEFAULT '' COMMENT '封面链接',
  `isShelves` INT(10) NOT NULL DEFAULT '1' COMMENT '是否上架 0:否 1:是',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `zpwan_blog_comment` (
  `id` VARCHAR(50) NOT NULL COMMENT 'id',
  `author` VARCHAR (255) NOT NULL  DEFAULT '{}' COMMENT '评论的作者',
  `article_id` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '所属文章',
  `content` VARCHAR (1000) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '评论内容',
  `is_delete` INT(10) NOT NULL DEFAULT '0' COMMENT '是否被删除 0:否 1:是',
  `ups` INTEGER(10) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `downs` INTEGER(10) NOT NULL DEFAULT '0' COMMENT '点踩数',
  `parent_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '评论的父级id 默认为空',
  `is_root` VARCHAR(10) NOT NULL DEFAULT 'true' COMMENT '是否为根评论 默认true',
  `reply_userInfo` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '评论人的信息',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `zpwan_blog_admin` (
  `admin_id` VARCHAR(50) NOT NULL COMMENT 'admin_id',
  `admin_name` VARCHAR (50) NOT NULL  DEFAULT '' COMMENT '管理员名',
  `admin_password` VARCHAR (50) NOT NULL  DEFAULT '' COMMENT '管理员密码',
  `role` INT(10) NOT NULL DEFAULT '1' COMMENT '角色 1:普通管理员  2超级管理员',
  `avatar_url` VARCHAR (255) NOT NULL DEFAULT '' COMMENT '头像',
 `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
 `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO zpwan_blog_admin(admin_id,admin_name,admin_password,role,avatar_url) values('zpwan_blog_zzz','zpwan','zpwan123',2,'');

CREATE TABLE `zpwan_blog_userups` (
  `id` INT (10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` VARCHAR(50) NOT NULL COMMENT '文章id',
  `user_id` VARCHAR (50) NOT NULL  COMMENT '用户id',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `zpwan_blog_friendlylink` (
  `id` INT (10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(100) NOT NULL COMMENT '链接名称',
  `ico` VARCHAR(255) NOT NULL COMMENT '链接ico',
  `url` VARCHAR (255) NOT NULL  COMMENT '链接地址',
  `isShelves` INT(10) NOT NULL DEFAULT '0' COMMENT '上下架 0:否 1:是',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `zpwan_blog_notices` (
  `id` INT (10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` VARCHAR(255) NOT NULL COMMENT '通知内容',
  `isEmergency` INT(10) NOT NULL DEFAULT '0' COMMENT '是否紧急 0:否 1:是',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `zpwan_blog_tasks` (
  `task_id` VARCHAR (50) NOT NULL COMMENT 'task_id',
  `title` VARCHAR(255) NOT NULL COMMENT '任务内容',
  `priority` INT(10) NOT NULL DEFAULT '1' COMMENT '优先级 1:低 2:高',
  `is_complete` INT(10) NOT NULL DEFAULT '0' COMMENT '完成 0:否 1:是',
  `endline` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '截止日期',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `zpwan_blog_leavemessage` (
  `leave_message_id` VARCHAR (50) NOT NULL COMMENT 'leave_message_id',
  `content` longtext CHARACTER SET utf8mb4 NOT NULL  COMMENT '留言内容',
  `author` VARCHAR(255) NOT NULL COMMENT '留言作者',
  `is_delete` INT(10) NOT NULL DEFAULT '0' COMMENT '是否被删除 0:否 1:是',
  `is_shelves` INT(10) NOT NULL DEFAULT '0' COMMENT '是否上架 0:否 1:是',
  `is_good` INT(10) NOT NULL DEFAULT '0' COMMENT '是否是精品 0:否 1:是',
  `init_date` VARCHAR(50) NOT NULL COMMENT '留言的时间年月日',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`leave_message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `zpwan_blog_picture` (
  `picture_id` VARCHAR (50) NOT NULL COMMENT 'picture_id',
  `picture_url` VARCHAR(255) NOT NULL COMMENT '地址',
  `createDate` BIGINT(255) NOT NULL COMMENT '时间戳',
  `init_date` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '日期',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`picture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `zpwan_blog_music` (
  `id` VARCHAR (36) NOT NULL COMMENT 'music_id',
  `src` VARCHAR(255) NOT NULL COMMENT '地址',
  `pic` VARCHAR(255) NOT NULL COMMENT '封面',
  `title` VARCHAR(20) NOT NULL COMMENT '歌名',
  `author` VARCHAR(20) NOT NULL COMMENT '歌手',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;