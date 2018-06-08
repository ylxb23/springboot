-- schema in mysql database named `sama`

CREATE TABLE `app_info` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
   `app_name` varchar(45) NOT NULL DEFAULT '' COMMENT 'App名称',
   `app_version` varchar(30) NOT NULL DEFAULT '' COMMENT 'App版本号',
   `description` varchar(100) NOT NULL DEFAULT '' COMMENT '描述信息',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
   `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除(0-否;1-删除)',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='App信息';
 
 
 -- schema in mysql database named `zero`
 
 CREATE TABLE `person` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
   `name` varchar(45) NOT NULL DEFAULT ''  COMMENT '姓名',
   `age` int(11) DEFAULT NULL DEFAULT 0 COMMENT '年龄',
   `gendar` varchar(2) DEFAULT NULL DEFAULT '' COMMENT '性别',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Person信息';
 

 
-- schema in mysql database named `third`
-- ----------------------------
-- Table structure for bbs_reply
-- ----------------------------
CREATE TABLE `bbs_reply`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级ID',
  `top_pid` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最上层父级ID',
  `tid` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属帖子ID',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复内容',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `floor` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属楼层',
  `likes` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数',
  `unlikes` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '踩数量',
  `collections` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL COMMENT '最近更新时间',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态(0-正常, 1-删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`user_id`) USING BTREE COMMENT '用户ID索引',
  INDEX `idx_pid`(`pid`) USING BTREE COMMENT '父级回复ID索引',
  INDEX `idx_topPid`(`top_pid`) USING BTREE COMMENT '最上层PID索引',
  INDEX `idx_tid`(`tid`) USING BTREE COMMENT '所属帖子ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '帖子回复表' ROW_FORMAT = Dynamic;
