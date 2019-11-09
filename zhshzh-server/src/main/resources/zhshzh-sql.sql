-- 创建系统用户表
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `user_info_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_account` VARCHAR(32) NOT NULL COMMENT '用户账号',
  `user_password` VARCHAR(64) NOT NULL COMMENT '用户密码',
  `user_name` VARCHAR(32) NOT NULL COMMENT '用户姓名',
  `user_serial_number` VARCHAR(16) NULL COMMENT '用户编号',
  `user_sex` CHAR(1) NULL COMMENT '性别(M：男； W：女)',
  `user_birth` DATE NULL COMMENT '出生日期',
  `user_phone_number` VARCHAR(16) NULL COMMENT '联系电话',
  `user_mail_box` VARCHAR(64) NULL COMMENT '员工邮箱',
  `is_delete` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除； 1：已删除）',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` BIGINT UNSIGNED NOT NULL COMMENT '创建人',
  `update_by` BIGINT UNSIGNED NOT NULL COMMENT '修改人',
  PRIMARY KEY (`user_info_id`),
  UNIQUE INDEX `user_account_UNIQUE` (`user_account` ASC),
  UNIQUE INDEX `user_serial_number_UNIQUE` (`user_serial_number` ASC),
  UNIQUE INDEX `user_phone_number_UNIQUE` (`user_phone_number` ASC),
  UNIQUE INDEX `user_mail_box_UNIQUE` (`user_mail_box` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 100000
DEFAULT CHARACTER SET = utf8
COMMENT = '系统用户表';
