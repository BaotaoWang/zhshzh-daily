/**
    首先更改mysql的配置文件
    linux下配置文件是/etc/my.cnf文件
    windows下配置文件是安装目录下的my.ini文件
    sql_mode默认是ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
    将其修改为sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
    如果sql_mode前有注释就把注释打开，如果没有就加上sql_mode
 */
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : daily

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-03-11 23:56:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_icon_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_icon_info`;
CREATE TABLE `sys_icon_info` (
  `icon_info_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '图标id',
  `icon_name` varchar(64) NOT NULL COMMENT '图标名称',
  `icon_order` smallint(5) unsigned DEFAULT NULL COMMENT '图标顺序',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建人',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '修改人',
  PRIMARY KEY (`icon_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8 COMMENT='系统图标表';

-- ----------------------------
-- Records of sys_icon_info
-- ----------------------------
INSERT INTO `sys_icon_info` VALUES ('1', 'el-icon-platform-eleme', '1', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('2', 'el-icon-eleme', '2', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('3', 'el-icon-delete-solid', '3', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('4', 'el-icon-delete', '4', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('5', 'el-icon-s-tools', '5', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('6', 'el-icon-setting', '6', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('7', 'el-icon-user-solid', '7', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('8', 'el-icon-user', '8', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('9', 'el-icon-phone', '9', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('10', 'el-icon-phone-outline', '10', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('11', 'el-icon-more', '11', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('12', 'el-icon-more-outline', '12', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('13', 'el-icon-star-on', '13', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('14', 'el-icon-star-off', '14', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('15', 'el-icon-s-goods', '15', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('16', 'el-icon-goods', '16', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('17', 'el-icon-warning', '17', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('18', 'el-icon-warning-outline', '18', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('19', 'el-icon-question', '19', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('20', 'el-icon-info', '20', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('21', 'el-icon-remove', '21', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('22', 'el-icon-circle-plus', '22', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('23', 'el-icon-success', '23', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('24', 'el-icon-error', '24', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('25', 'el-icon-zoom-in', '25', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('26', 'el-icon-zoom-out', '26', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('27', 'el-icon-remove-outline', '27', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('28', 'el-icon-circle-plus-outline', '28', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('29', 'el-icon-circle-check', '29', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('30', 'el-icon-circle-close', '30', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('31', 'el-icon-s-help', '31', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('32', 'el-icon-help', '32', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('33', 'el-icon-minus', '33', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('34', 'el-icon-plus', '34', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('35', 'el-icon-check', '35', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('36', 'el-icon-close', '36', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('37', 'el-icon-picture', '37', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('38', 'el-icon-picture-outline', '38', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('39', 'el-icon-picture-outline-round', '39', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('40', 'el-icon-upload', '40', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('41', 'el-icon-upload2', '41', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('42', 'el-icon-download', '42', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('43', 'el-icon-camera-solid', '43', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('44', 'el-icon-camera', '44', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('45', 'el-icon-video-camera-solid', '45', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('46', 'el-icon-video-camera', '46', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('47', 'el-icon-message-solid', '47', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('48', 'el-icon-bell', '48', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('49', 'el-icon-s-cooperation', '49', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('50', 'el-icon-s-order', '50', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('51', 'el-icon-s-platform', '51', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('52', 'el-icon-s-fold', '52', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('53', 'el-icon-s-unfold', '53', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('54', 'el-icon-s-operation', '54', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('55', 'el-icon-s-promotion', '55', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('56', 'el-icon-s-home', '56', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('57', 'el-icon-s-release', '57', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('58', 'el-icon-s-ticket', '58', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('59', 'el-icon-s-management', '59', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('60', 'el-icon-s-open', '60', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('61', 'el-icon-s-shop', '61', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('62', 'el-icon-s-marketing', '62', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('63', 'el-icon-s-flag', '63', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('64', 'el-icon-s-comment', '64', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('65', 'el-icon-s-finance', '65', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('66', 'el-icon-s-claim', '66', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('67', 'el-icon-s-custom', '67', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('68', 'el-icon-s-opportunity', '68', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('69', 'el-icon-s-data', '69', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('70', 'el-icon-s-check', '70', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('71', 'el-icon-s-grid', '71', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('72', 'el-icon-menu', '72', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('73', 'el-icon-share', '73', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('74', 'el-icon-d-caret', '74', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('75', 'el-icon-caret-left', '75', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('76', 'el-icon-caret-right', '76', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('77', 'el-icon-caret-bottom', '77', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('78', 'el-icon-caret-top', '78', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('79', 'el-icon-bottom-left', '79', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('80', 'el-icon-bottom-right', '80', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('81', 'el-icon-back', '81', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('82', 'el-icon-right', '82', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('83', 'el-icon-bottom', '83', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('84', 'el-icon-top', '84', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('85', 'el-icon-top-left', '85', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('86', 'el-icon-top-right', '86', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('87', 'el-icon-arrow-left', '87', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('88', 'el-icon-arrow-right', '88', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('89', 'el-icon-arrow-down', '89', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('90', 'el-icon-arrow-up', '90', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('91', 'el-icon-d-arrow-left', '91', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('92', 'el-icon-d-arrow-right', '92', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('93', 'el-icon-video-pause', '93', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('94', 'el-icon-video-play', '94', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('95', 'el-icon-refresh', '95', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('96', 'el-icon-refresh-right', '96', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('97', 'el-icon-refresh-left', '97', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('98', 'el-icon-finished', '98', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('99', 'el-icon-sort', '99', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('100', 'el-icon-sort-up', '100', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('101', 'el-icon-sort-down', '101', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('102', 'el-icon-rank', '102', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('103', 'el-icon-loading', '103', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('104', 'el-icon-view', '104', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('105', 'el-icon-c-scale-to-original', '105', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('106', 'el-icon-date', '106', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('107', 'el-icon-edit', '107', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('108', 'el-icon-edit-outline', '108', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('109', 'el-icon-folder', '109', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('110', 'el-icon-folder-opened', '110', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('111', 'el-icon-folder-add', '111', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('112', 'el-icon-folder-remove', '112', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('113', 'el-icon-folder-delete', '113', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('114', 'el-icon-folder-checked', '114', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('115', 'el-icon-tickets', '115', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('116', 'el-icon-document-remove', '116', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('117', 'el-icon-document-delete', '117', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('118', 'el-icon-document-copy', '118', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('119', 'el-icon-document-checked', '119', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('120', 'el-icon-document', '120', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('121', 'el-icon-document-add', '121', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('122', 'el-icon-printer', '122', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('123', 'el-icon-paperclip', '123', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('124', 'el-icon-takeaway-box', '124', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('125', 'el-icon-search', '125', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('126', 'el-icon-monitor', '126', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('127', 'el-icon-attract', '127', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('128', 'el-icon-mobile', '128', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('129', 'el-icon-scissors', '129', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('130', 'el-icon-umbrella', '130', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('131', 'el-icon-headset', '131', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('132', 'el-icon-brush', '132', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('133', 'el-icon-mouse', '133', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('134', 'el-icon-coordinate', '134', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('135', 'el-icon-magic-stick', '135', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('136', 'el-icon-reading', '136', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('137', 'el-icon-data-line', '137', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('138', 'el-icon-data-board', '138', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('139', 'el-icon-pie-chart', '139', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('140', 'el-icon-data-analysis', '140', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('141', 'el-icon-collection-tag', '141', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('142', 'el-icon-film', '142', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('143', 'el-icon-suitcase', '143', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('144', 'el-icon-suitcase-1', '144', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('145', 'el-icon-receiving', '145', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('146', 'el-icon-collection', '146', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('147', 'el-icon-files', '147', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('148', 'el-icon-notebook-1', '148', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('149', 'el-icon-notebook-2', '149', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('150', 'el-icon-toilet-paper', '150', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('151', 'el-icon-office-building', '151', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('152', 'el-icon-school', '152', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('153', 'el-icon-table-lamp', '153', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('154', 'el-icon-house', '154', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('155', 'el-icon-no-smoking', '155', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('156', 'el-icon-smoking', '156', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('157', 'el-icon-shopping-cart-full', '157', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('158', 'el-icon-shopping-cart-1', '158', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('159', 'el-icon-shopping-cart-2', '159', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('160', 'el-icon-shopping-bag-1', '160', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('161', 'el-icon-shopping-bag-2', '161', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('162', 'el-icon-sold-out', '162', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('163', 'el-icon-sell', '163', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('164', 'el-icon-present', '164', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('165', 'el-icon-box', '165', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('166', 'el-icon-bank-card', '166', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('167', 'el-icon-money', '167', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('168', 'el-icon-coin', '168', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('169', 'el-icon-wallet', '169', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('170', 'el-icon-discount', '170', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('171', 'el-icon-price-tag', '171', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('172', 'el-icon-news', '172', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('173', 'el-icon-guide', '173', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('174', 'el-icon-male', '174', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('175', 'el-icon-female', '175', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('176', 'el-icon-thumb', '176', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('177', 'el-icon-cpu', '177', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('178', 'el-icon-link', '178', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('179', 'el-icon-connection', '179', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('180', 'el-icon-open', '180', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('181', 'el-icon-turn-off', '181', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('182', 'el-icon-set-up', '182', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('183', 'el-icon-chat-round', '183', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('184', 'el-icon-chat-line-round', '184', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('185', 'el-icon-chat-square', '185', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('186', 'el-icon-chat-dot-round', '186', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('187', 'el-icon-chat-dot-square', '187', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('188', 'el-icon-chat-line-square', '188', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('189', 'el-icon-message', '189', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('190', 'el-icon-postcard', '190', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('191', 'el-icon-position', '191', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('192', 'el-icon-turn-off-microphone', '192', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('193', 'el-icon-microphone', '193', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('194', 'el-icon-close-notification', '194', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('195', 'el-icon-bangzhu', '195', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('196', 'el-icon-time', '196', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('197', 'el-icon-odometer', '197', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('198', 'el-icon-crop', '198', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('199', 'el-icon-aim', '199', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('200', 'el-icon-switch-button', '200', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('201', 'el-icon-full-screen', '201', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('202', 'el-icon-copy-document', '202', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('203', 'el-icon-mic', '203', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('204', 'el-icon-stopwatch', '204', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('205', 'el-icon-medal-1', '205', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('206', 'el-icon-medal', '206', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('207', 'el-icon-trophy', '207', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('208', 'el-icon-trophy-1', '208', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('209', 'el-icon-first-aid-kit', '209', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('210', 'el-icon-discover', '210', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('211', 'el-icon-place', '211', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('212', 'el-icon-location', '212', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('213', 'el-icon-location-outline', '213', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('214', 'el-icon-location-information', '214', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('215', 'el-icon-add-location', '215', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('216', 'el-icon-delete-location', '216', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('217', 'el-icon-map-location', '217', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('218', 'el-icon-alarm-clock', '218', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('219', 'el-icon-timer', '219', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('220', 'el-icon-watch-1', '220', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('221', 'el-icon-watch', '221', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('222', 'el-icon-lock', '222', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('223', 'el-icon-unlock', '223', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('224', 'el-icon-key', '224', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('225', 'el-icon-service', '225', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('226', 'el-icon-mobile-phone', '226', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('227', 'el-icon-bicycle', '227', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('228', 'el-icon-truck', '228', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('229', 'el-icon-ship', '229', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('230', 'el-icon-basketball', '230', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('231', 'el-icon-football', '231', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('232', 'el-icon-soccer', '232', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('233', 'el-icon-baseball', '233', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('234', 'el-icon-wind-power', '234', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('235', 'el-icon-light-rain', '235', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('236', 'el-icon-lightning', '236', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('237', 'el-icon-heavy-rain', '237', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('238', 'el-icon-sunrise', '238', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('239', 'el-icon-sunrise-1', '239', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('240', 'el-icon-sunset', '240', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('241', 'el-icon-sunny', '241', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('242', 'el-icon-cloudy', '242', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('243', 'el-icon-partly-cloudy', '243', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('244', 'el-icon-cloudy-and-sunny', '244', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('245', 'el-icon-moon', '245', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('246', 'el-icon-moon-night', '246', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('247', 'el-icon-dish', '247', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('248', 'el-icon-dish-1', '248', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('249', 'el-icon-food', '249', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('250', 'el-icon-chicken', '250', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('251', 'el-icon-fork-spoon', '251', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('252', 'el-icon-knife-fork', '252', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('253', 'el-icon-burger', '253', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('254', 'el-icon-tableware', '254', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('255', 'el-icon-sugar', '255', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('256', 'el-icon-dessert', '256', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('257', 'el-icon-ice-cream', '257', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('258', 'el-icon-hot-water', '258', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('259', 'el-icon-water-cup', '259', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('260', 'el-icon-coffee-cup', '260', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('261', 'el-icon-cold-drink', '261', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('262', 'el-icon-goblet', '262', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('263', 'el-icon-goblet-full', '263', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('264', 'el-icon-goblet-square', '264', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('265', 'el-icon-goblet-square-full', '265', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('266', 'el-icon-refrigerator', '266', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('267', 'el-icon-grape', '267', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('268', 'el-icon-watermelon', '268', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('269', 'el-icon-cherry', '269', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('270', 'el-icon-apple', '270', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('271', 'el-icon-pear', '271', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('272', 'el-icon-orange', '272', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('273', 'el-icon-coffee', '273', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('274', 'el-icon-ice-tea', '274', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('275', 'el-icon-ice-drink', '275', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('276', 'el-icon-milk-tea', '276', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('277', 'el-icon-potato-strips', '277', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('278', 'el-icon-lollipop', '278', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('279', 'el-icon-ice-cream-square', '279', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');
INSERT INTO `sys_icon_info` VALUES ('280', 'el-icon-ice-cream-round', '280', '\0', '2019-12-31 22:07:22', '2019-12-31 22:07:22', '100000', '100000');

-- ----------------------------
-- Table structure for sys_interface_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_interface_log`;
CREATE TABLE `sys_interface_log` (
  `interface_log_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '接口日志id',
  `request_url` varchar(300) DEFAULT NULL COMMENT '请求url',
  `type` varchar(10) DEFAULT NULL COMMENT '请求方式',
  `request_data` text COMMENT '请求数据',
  `response_data` text COMMENT '请求数据',
  `process_time` bigint(20) DEFAULT NULL COMMENT '请求数据处理时长（ms）',
  `class_name` varchar(200) DEFAULT NULL COMMENT '请求类名',
  `method_name` varchar(32) DEFAULT NULL COMMENT '请求方法名',
  `user_id` bigint(20) DEFAULT NULL COMMENT '请求用户id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '请求用户账号',
  `principal` varchar(300) DEFAULT NULL COMMENT '用户权限信息',
  `client_ip` varchar(32) DEFAULT NULL COMMENT '客户端ip',
  `server_ip` varchar(32) DEFAULT NULL COMMENT '服务端ip',
  `server_port` int(10) unsigned DEFAULT NULL COMMENT '服务端端口号',
  `request_exception` text COMMENT '请求异常',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `update_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '修改人',
  PRIMARY KEY (`interface_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='系统接口日志表';

-- ----------------------------
-- Table structure for sys_menu_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_info`;
CREATE TABLE `sys_menu_info` (
  `menu_info_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(64) NOT NULL COMMENT '菜单名称',
  `menu_route` varchar(200) NOT NULL COMMENT '菜单路由',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT '父级菜单id',
  `menu_order` smallint(5) unsigned DEFAULT NULL COMMENT '菜单序号',
  `menu_icon` varchar(64) DEFAULT NULL COMMENT '菜单图标',
  `menu_description` varchar(200) DEFAULT NULL COMMENT '菜单描述',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建人',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '修改人',
  PRIMARY KEY (`menu_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu_info
-- ----------------------------
INSERT INTO `sys_menu_info` VALUES ('1', '首页', '/homePage', '0', '1', 'el-icon-s-home', '首页', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('2', '项目管理', '/project', '0', '2', 'el-icon-set-up', '项目管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('3', '项目列表', '/projectList', '2', '1', null, '项目列表', '\0', '2020-01-20 21:19:56', '2020-01-20 21:21:14', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('4', '日志管理', '/daily', '0', '3', 'el-icon-document', '日志管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('5', '周工作计划', '/addWorkPlan', '4', '1', null, '周工作计划', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('6', '工作日志', '/addWorkLog', '4', '2', null, '工作日志', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('7', '周工作计划查询', '/workPanList', '4', '3', null, '周工作计划查询', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('8', '工作日志查询', '/workLogList', '4', '4', null, '工作日志查询', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('9', '消息管理', '/message', '0', '4', 'el-icon-message', '消息管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('10', '评论', '/comment', '9', '1', null, '评论', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('11', '回复', '/reply', '9', '2', null, '回复', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('12', '提醒', '/remind', '9', '3', null, '提醒', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('13', '系统设置', '/settings', '0', '5', 'el-icon-setting', '系统设置', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('14', '组织管理', '/organization', '13', '1', null, '系统设置', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('15', '系统设置', '/role', '13', '2', null, '组织管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('16', '角色管理', '/authority', '13', '3', null, '角色管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('17', '人员管理', '/user', '13', '4', null, '人员管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('18', '参数管理', '/params', '13', '5', null, '参数管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('19', '审计管理', '/audit', '13', '6', null, '审计管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('20', '假期管理', '/holidays', '13', '7', null, '假期管理', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');
INSERT INTO `sys_menu_info` VALUES ('21', '反馈建议', '/suggestion', '0', '6', 'el-icon-chat-line-round', '反馈建议', '\0', '2020-01-20 21:19:56', '2020-01-20 21:19:56', '0', '0');

-- ----------------------------
-- Table structure for sys_role_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_info`;
CREATE TABLE `sys_role_info` (
  `role_info_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_code` varchar(32) NOT NULL COMMENT '角色标识',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建人',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '修改人',
  PRIMARY KEY (`role_info_id`),
  UNIQUE KEY `role_code_UNIQUE` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role_info
-- ----------------------------
INSERT INTO `sys_role_info` VALUES ('1', 'ROLE_ADMIN', '系统管理员', '该角色可拥有所有的权限', '\0', '2020-01-07 21:01:54', '2020-01-07 21:01:54', '0', '0');
INSERT INTO `sys_role_info` VALUES ('2', 'ROLE_USER', '普通用户', '普通用户', '\0', '2020-03-10 21:24:01', '2020-03-10 21:24:01', '0', '0');

-- ----------------------------
-- Table structure for sys_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_relation`;
CREATE TABLE `sys_role_menu_relation` (
  `rm_relation_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色-菜单关系id',
  `role_info_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `menu_info_id` bigint(20) unsigned NOT NULL COMMENT '菜单id',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建人',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '修改人',
  PRIMARY KEY (`rm_relation_id`),
  KEY `sys_role_menu_relation_role_info_id_FOREIGN` (`role_info_id`),
  KEY `sys_role_menu_relation_menu_info_id_FOREIGN` (`menu_info_id`),
  CONSTRAINT `sys_role_menu_relation_menu_info_id_FOREIGN` FOREIGN KEY (`menu_info_id`) REFERENCES `sys_menu_info` (`menu_info_id`),
  CONSTRAINT `sys_role_menu_relation_role_info_id_FOREIGN` FOREIGN KEY (`role_info_id`) REFERENCES `sys_role_info` (`role_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='角色-菜单关系表';

-- ----------------------------
-- Records of sys_role_menu_relation
-- ----------------------------
INSERT INTO `sys_role_menu_relation` VALUES ('1', '1', '1', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('2', '1', '2', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('3', '1', '3', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('4', '1', '4', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('5', '1', '5', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('6', '1', '6', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('7', '1', '7', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('8', '1', '8', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('9', '1', '9', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('10', '1', '10', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('11', '1', '11', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('12', '1', '12', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('13', '1', '13', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('14', '1', '14', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('15', '1', '15', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('16', '1', '16', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('17', '1', '17', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('18', '1', '18', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('19', '1', '19', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('20', '1', '20', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('21', '1', '21', '\0', '2020-01-20 21:45:53', '2020-01-20 21:45:53', '0', '0');
INSERT INTO `sys_role_menu_relation` VALUES ('22', '2', '1', '\0', '2020-03-11 20:53:28', '2020-03-11 20:53:28', '0', '0');

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `user_info_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(32) NOT NULL COMMENT '用户账号',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `full_name` varchar(32) NOT NULL COMMENT '用户姓名',
  `serial_number` varchar(16) DEFAULT NULL COMMENT '用户编号',
  `sex` char(1) DEFAULT NULL COMMENT '性别(M：男； W：女)',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `phone_number` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(64) DEFAULT NULL COMMENT '员工邮箱',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建人',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '修改人',
  PRIMARY KEY (`user_info_id`),
  UNIQUE KEY `username_UNIQUE` (`user_name`),
  UNIQUE KEY `serial_number_UNIQUE` (`serial_number`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=100002 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES ('100000', 'admin', '$2a$10$0MN.Awl8Qi/lmMr3rVMW5eMeoPR8OEEXcSgQNYWnH/QFNXhFQYd.q', '系统管理员', '000001', 'M', '1970-01-01', '18888888888', 'admin@admin.com', '\0', '2020-01-07 21:01:54', '2020-01-07 21:01:54', '0', '0');
INSERT INTO `sys_user_info` VALUES ('100001', 'test', '$2a$10$Usgl..nsBi1zLqRG/K8VLeui9MS0LGrceQwt5eEzFWEV5fi4mL3XC', '测试账号', '000002', 'M', '2020-03-11', '17777777777', 'test@test.com', '\0', '2020-03-11 20:50:31', '2020-03-11 21:15:49', '0', '0');

-- ----------------------------
-- Table structure for sys_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation` (
  `ur_relation_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户-角色关系id',
  `user_info_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `role_info_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除（0：false-未删除； 1：true-已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建人',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '修改人',
  PRIMARY KEY (`ur_relation_id`),
  KEY `sys_user_role_relation_user_info_id_FOREIGN` (`user_info_id`),
  KEY `sys_user_role_relation_role_info_id_FOREIGN` (`role_info_id`),
  CONSTRAINT `sys_user_role_relation_role_info_id_FOREIGN` FOREIGN KEY (`role_info_id`) REFERENCES `sys_role_info` (`role_info_id`),
  CONSTRAINT `sys_user_role_relation_user_info_id_FOREIGN` FOREIGN KEY (`user_info_id`) REFERENCES `sys_user_info` (`user_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户-角色关系表';

-- ----------------------------
-- Records of sys_user_role_relation
-- ----------------------------
INSERT INTO `sys_user_role_relation` VALUES ('1', '100000', '1', '\0', '2020-01-07 21:01:54', '2020-01-07 21:01:54', '0', '0');
INSERT INTO `sys_user_role_relation` VALUES ('2', '100000', '2', '\0', '2020-03-10 21:24:19', '2020-03-10 21:28:03', '0', '0');
INSERT INTO `sys_user_role_relation` VALUES ('3', '100001', '2', '\0', '2020-03-11 20:52:29', '2020-03-11 20:52:29', '0', '0');
