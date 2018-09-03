/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.0.1:3306
 Source Schema         : third

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 03/09/2018 22:57:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bbs_reply
-- ----------------------------
DROP TABLE IF EXISTS `bbs_reply`;
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

-- ----------------------------
-- Table structure for learning
-- ----------------------------
DROP TABLE IF EXISTS `learning`;
CREATE TABLE `learning`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复内容',
  `user_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL COMMENT '最近更新时间',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态(0-正常, 1-删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_username`(`user_name`) USING BTREE COMMENT '用户名索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '练习表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
