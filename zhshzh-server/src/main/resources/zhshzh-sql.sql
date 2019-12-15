-- 创建系统用户表
DROP TABLE IF EXISTS `sys_user_info`;

CREATE TABLE `sys_user_info` (
	`user_info_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`user_name` VARCHAR (32) NOT NULL COMMENT '用户账号',
	`password` VARCHAR (64) NOT NULL COMMENT '用户密码',
	`full_name` VARCHAR (32) NOT NULL COMMENT '用户姓名',
	`serial_number` VARCHAR (16) NULL COMMENT '用户编号',
	`sex` CHAR (1) NULL COMMENT '性别(M：男； W：女)',
	`birth` DATE NULL COMMENT '出生日期',
	`phone_number` VARCHAR (16) NULL COMMENT '联系电话',
	`email` VARCHAR (64) NULL COMMENT '员工邮箱',
	`is_delete` TINYINT (1) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除； 1：已删除）',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`create_by` BIGINT UNSIGNED NOT NULL COMMENT '创建人',
	`update_by` BIGINT UNSIGNED NOT NULL COMMENT '修改人',
	PRIMARY KEY (`user_info_id`),
	UNIQUE INDEX `username_UNIQUE` (`user_name` ASC),
	UNIQUE INDEX `serial_number_UNIQUE` (`serial_number` ASC),
	UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC),
	UNIQUE INDEX `email_UNIQUE` (`email` ASC)
) ENGINE = INNODB AUTO_INCREMENT = 100000 DEFAULT CHARACTER SET = utf8 COMMENT = '系统用户表';


-- 创建系统接口日志表
DROP TABLE IF EXISTS `sys_interface_log`;

CREATE TABLE `sys_interface_log` (
	`interface_log_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '接口日志id',
	`path` VARCHAR (200) NOT NULL COMMENT '请求路径',
	`type` VARCHAR (10) NOT NULL COMMENT '请求方式',
	`request_data` TEXT DEFAULT NULL COMMENT '请求数据',
	`process_time` INT NOT NULL COMMENT '请求数据处理时长（ms）',
	`class_name` VARCHAR (200) NOT NULL COMMENT '请求类名',
	`method_name` VARCHAR (32) NOT NULL COMMENT '请求方法名',
	`user_name` VARCHAR (32) DEFAULT NULL COMMENT '请求用户',
	`principal` VARCHAR (300) DEFAULT NULL COMMENT '用户权限信息',
	`client_ip` VARCHAR (32) NOT NULL COMMENT '客户端ip',
	`is_delete` TINYINT (1) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除； 1：已删除）',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`create_by` VARCHAR (32) NOT NULL DEFAULT 'system' COMMENT '创建人',
	`update_by` VARCHAR (32) NOT NULL DEFAULT 'system' COMMENT '修改人',
	PRIMARY KEY (`interface_log_id`)
) ENGINE = INNODB DEFAULT CHARACTER SET = utf8 COMMENT = '系统接口日志表';
