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
	`is_delete` BIT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
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
-- 插入系统管理员
INSERT INTO `sys_user_info`
(user_name, password, full_name, serial_number, sex, birth, phone_number, email, create_by, update_by)
VALUES
('admin', '$2a$10$0MN.Awl8Qi/lmMr3rVMW5eMeoPR8OEEXcSgQNYWnH/QFNXhFQYd.q', '系统管理员', '000001', 'M', '1970-01-01',
'18888888888','admin@admin.com','0','0');



-- 创建系统接口日志表
DROP TABLE IF EXISTS `sys_interface_log`;

CREATE TABLE `sys_interface_log` (
	`interface_log_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '接口日志id',
	`request_url` VARCHAR (300) DEFAULT NULL COMMENT '请求url',
	`type` VARCHAR (10) DEFAULT NULL COMMENT '请求方式',
	`request_data` TEXT DEFAULT NULL COMMENT '请求数据',
	`response_data` TEXT DEFAULT NULL COMMENT '请求数据',
	`process_time` BIGINT DEFAULT NULL COMMENT '请求数据处理时长（ms）',
	`class_name` VARCHAR (200) DEFAULT NULL COMMENT '请求类名',
	`method_name` VARCHAR (32) DEFAULT NULL COMMENT '请求方法名',
	`user_id` BIGINT DEFAULT NULL COMMENT '请求用户id',
	`user_name` VARCHAR (32) DEFAULT NULL COMMENT '请求用户账号',
	`principal` VARCHAR (300) DEFAULT NULL COMMENT '用户权限信息',
	`client_ip` VARCHAR (32) DEFAULT NULL COMMENT '客户端ip',
	`server_ip` VARCHAR (32) DEFAULT NULL COMMENT '服务端ip',
	`server_port` INT UNSIGNED DEFAULT NULL COMMENT '服务端端口号',
	`request_exception` TEXT DEFAULT NULL COMMENT '请求异常',
	`is_delete` BIT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`create_by` VARCHAR (32) NOT NULL DEFAULT 'system' COMMENT '创建人',
	`update_by` VARCHAR (32) NOT NULL DEFAULT 'system' COMMENT '修改人',
	PRIMARY KEY (`interface_log_id`)
) ENGINE = INNODB DEFAULT CHARACTER SET = utf8 COMMENT = '系统接口日志表';


-- 创建系统角色表
DROP TABLE IF EXISTS `sys_role_info`;

CREATE TABLE `sys_role_info` (
	`role_info_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色id',
	`role_name` VARCHAR (32) NOT NULL COMMENT '角色名称',
	`role_description` VARCHAR (200) DEFAULT NULL COMMENT '角色描述',
	`is_delete` BIT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`create_by` BIGINT UNSIGNED NOT NULL COMMENT '创建人',
	`update_by` BIGINT UNSIGNED NOT NULL COMMENT '修改人',
	PRIMARY KEY (`role_info_id`)
) ENGINE = INNODB DEFAULT CHARACTER SET = utf8 COMMENT = '系统角色表';


-- 创建用户-角色关系表
DROP TABLE IF EXISTS `user_role_relation`;

CREATE TABLE `user_role_relation` (
	`ur_relation_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户-角色关系id',
	`user_info_id` BIGINT NOT NULL COMMENT '用户id',
	`role_info_id` BIGINT NOT NULL COMMENT '角色id',
	`is_delete` BIT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`create_by` BIGINT UNSIGNED NOT NULL COMMENT '创建人',
	`update_by` BIGINT UNSIGNED NOT NULL COMMENT '修改人',
	PRIMARY KEY (`ur_relation_id`)
) ENGINE = INNODB DEFAULT CHARACTER SET = utf8 COMMENT = '用户-角色关系表';


-- 创建系统菜单表
DROP TABLE IF EXISTS `sys_menu_info`;

CREATE TABLE `sys_menu_info` (
	`menu_info_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单id',
	`menu_name` VARCHAR (64) NOT NULL COMMENT '菜单名称',
	`menu_route` VARCHAR (200) NOT NULL COMMENT '菜单路由',
	`parent_id` BIGINT UNSIGNED NOT NULL COMMENT '父级菜单id',
	`menu_order` SMALLINT UNSIGNED DEFAULT NULL COMMENT '菜单序号',
	`menu_icon` BIGINT UNSIGNED DEFAULT NULL COMMENT '菜单图标id',
	`menu_description` VARCHAR (200) DEFAULT NULL COMMENT '菜单描述',
	`is_delete` BIT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`create_by` BIGINT UNSIGNED NOT NULL COMMENT '创建人',
	`update_by` BIGINT UNSIGNED NOT NULL COMMENT '修改人',
	PRIMARY KEY (`menu_info_id`)
) ENGINE = INNODB DEFAULT CHARACTER SET = utf8 COMMENT = '系统菜单表';


-- 创建角色-菜单关系表
DROP TABLE IF EXISTS `role_menu_relation`;

CREATE TABLE `role_menu_relation` (
	`rm_relation_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色-菜单关系id',
	`role_info_id` BIGINT NOT NULL COMMENT '角色id',
	`menu_info_id` BIGINT NOT NULL COMMENT '菜单id',
	`is_delete` BIT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`create_by` BIGINT UNSIGNED NOT NULL COMMENT '创建人',
	`update_by` BIGINT UNSIGNED NOT NULL COMMENT '修改人',
	PRIMARY KEY (`rm_relation_id`)
) ENGINE = INNODB DEFAULT CHARACTER SET = utf8 COMMENT = '角色-菜单关系表';
