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