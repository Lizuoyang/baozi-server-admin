/*
 Navicat Premium Data Transfer

 Source Server         : my-server
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 81.71.68.248:2306
 Source Schema         : baozi_admin

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 22/01/2021 15:57:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
                               `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                               `system_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '系统编码，所有都要存',
                               `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父主键：系统下的一级菜单存0',
                               `menu_type` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单类型：菜单(menu)、导航(navigation)',
                               `menu_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
                               `menu_icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
                               `menu_url` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单链接',
                               `menu_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                               `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除0:未删除。1：已删除',
                               `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
                               `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                               `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
                               `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '4', '0', 'navigation', '权限管理', 'authority', '/authority', 2, '这是权限管理的备注', 0, 'lizuoyang', '2021-01-22 15:31:09', 'lizuoyang', '2021-01-22 15:31:09');
INSERT INTO `t_sys_menu` VALUES ('1339467680672374786', '4', '1', 'menu', '系统管理', 'el-icon-s-tools', '/authority/system', 3, NULL, 0, 'lizuoyang', '2021-01-22 15:33:06', 'lizuoyang', '2021-01-22 15:33:06');
INSERT INTO `t_sys_menu` VALUES ('1348465949859602434', '4', '1', 'menu', '操作管理', 'el-icon-open', '/authority/operation', 4, NULL, 0, 'lizuoyang', '2021-01-20 09:40:41', 'lizuoyang', '2021-01-20 09:40:41');
INSERT INTO `t_sys_menu` VALUES ('2', '4', '1', 'menu', '用户管理', 'user2', '/authority/user', 1, NULL, 0, 'lizuoyang', '2021-01-21 16:03:38', 'lizuoyang', '2021-01-21 16:03:39');
INSERT INTO `t_sys_menu` VALUES ('3', '4', '1', 'menu', '角色管理', 'role', '/authority/role', 2, NULL, 0, 'lizuoyang', '2021-01-20 09:40:33', 'lizuoyang', '2021-01-20 09:40:34');
INSERT INTO `t_sys_menu` VALUES ('4', '4', '1', 'menu', '菜单管理', 'menu', '/authority/menu', 5, NULL, 0, 'lizuoyang', '2021-01-20 09:40:47', 'lizuoyang', '2021-01-20 09:40:47');
INSERT INTO `t_sys_menu` VALUES ('SM512154421820256256', '4', '0', 'navigation', '监控中心', 'el-icon-s-platform', '/monitor', 3, '', 0, 'lizuoyang', '2021-01-22 15:31:02', 'lizuoyang', '2021-01-22 15:31:03');
INSERT INTO `t_sys_menu` VALUES ('SM512154836246851584', '4', 'SM512154421820256256', 'menu', 'SQL监控', 'mysql', '/monitor/druid', 1, '阿里Druid数据源监控面板', 0, 'lizuoyang', '2021-01-22 15:36:14', 'lizuoyang', '2021-01-22 15:36:15');
INSERT INTO `t_sys_menu` VALUES ('SM512165063914549248', '4', 'SM512154421820256256', 'menu', '接口文档', 'el-icon-info', '/monitor/swagger', 2, NULL, 0, 'lizuoyang', '2021-01-21 17:35:06', 'lizuoyang', '2021-01-21 17:35:06');
INSERT INTO `t_sys_menu` VALUES ('SM512497777129680896', '4', '0', 'navigation', '图标管理', 'icon', '/icon', 1, NULL, 0, 'lizuoyang', '2021-01-22 15:30:02', 'lizuoyang', '2021-01-22 15:30:02');
INSERT INTO `t_sys_menu` VALUES ('SM512497891965530112', '4', 'SM512497777129680896', 'menu', '图标列表', NULL, '/icon/index', 1, NULL, 0, 'lizuoyang', '2021-01-22 15:30:29', 'lizuoyang', '2021-01-22 15:30:29');

-- ----------------------------
-- Table structure for t_sys_menu_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu_operation`;
CREATE TABLE `t_sys_menu_operation`  (
                                         `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                         `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单主键',
                                         `operation_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作功能主键',
                                         `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单操作中间表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu_operation
-- ----------------------------
INSERT INTO `t_sys_menu_operation` VALUES ('511685054896664576', '3', '1348525220206886913', '2021-01-20 09:40:34');
INSERT INTO `t_sys_menu_operation` VALUES ('511685054896664577', '3', '1348905796873158658', '2021-01-20 09:40:34');
INSERT INTO `t_sys_menu_operation` VALUES ('511685054896664578', '3', '1348526041602605058', '2021-01-20 09:40:34');
INSERT INTO `t_sys_menu_operation` VALUES ('511685054896664579', '3', '1349176192323248130', '2021-01-20 09:40:34');
INSERT INTO `t_sys_menu_operation` VALUES ('511685084537810944', '1348465949859602434', '1348525220206886913', '2021-01-20 09:40:41');
INSERT INTO `t_sys_menu_operation` VALUES ('511685084542005248', '1348465949859602434', '1348905796873158658', '2021-01-20 09:40:41');
INSERT INTO `t_sys_menu_operation` VALUES ('511685084542005249', '1348465949859602434', '1348526041602605058', '2021-01-20 09:40:41');
INSERT INTO `t_sys_menu_operation` VALUES ('511685110727045120', '4', '1348525220206886913', '2021-01-20 09:40:47');
INSERT INTO `t_sys_menu_operation` VALUES ('511685110727045121', '4', '1348526041602605058', '2021-01-20 09:40:47');
INSERT INTO `t_sys_menu_operation` VALUES ('511685110727045122', '4', '1348905796873158658', '2021-01-20 09:40:47');
INSERT INTO `t_sys_menu_operation` VALUES ('512143848286056448', '2', '1348525220206886913', '2021-01-21 16:03:39');
INSERT INTO `t_sys_menu_operation` VALUES ('512143848286056449', '2', '1348526041602605058', '2021-01-21 16:03:39');
INSERT INTO `t_sys_menu_operation` VALUES ('512143848286056450', '2', '1348905796873158658', '2021-01-21 16:03:39');
INSERT INTO `t_sys_menu_operation` VALUES ('512498550739693568', '1339467680672374786', '1348525220206886913', '2021-01-22 15:33:06');
INSERT INTO `t_sys_menu_operation` VALUES ('512498550739693569', '1339467680672374786', '1348526041602605058', '2021-01-22 15:33:06');
INSERT INTO `t_sys_menu_operation` VALUES ('512498550739693570', '1339467680672374786', '1348905796873158658', '2021-01-22 15:33:06');

-- ----------------------------
-- Table structure for t_sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_operation`;
CREATE TABLE `t_sys_operation`  (
                                    `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                    `operation_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作编码',
                                    `operation_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作名称',
                                    `operation_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作描述',
                                    `operation_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
                                    `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
                                    `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                    `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
                                    `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作功能表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_operation
-- ----------------------------
INSERT INTO `t_sys_operation` VALUES ('1348525220206886913', 'add', '新增', '新增按钮', 1, 'lizuoyang', '2021-01-11 17:21:48', 'lizuoyang', '2021-01-11 17:21:48');
INSERT INTO `t_sys_operation` VALUES ('1348526041602605058', 'edit', '编辑', '', 2, 'lizuoyang', '2021-01-11 17:22:05', 'lizuoyang', '2021-01-11 17:22:06');
INSERT INTO `t_sys_operation` VALUES ('1348526106643677186', 'export', '导出', '', 3, 'lizuoyang', '2021-01-11 17:22:10', 'lizuoyang', '2021-01-11 17:22:11');
INSERT INTO `t_sys_operation` VALUES ('1348905796873158658', 'remove', '删除', '删除按钮', 1, 'lizuoyang', '2021-01-12 16:12:58', NULL, '2021-01-13 14:32:43');
INSERT INTO `t_sys_operation` VALUES ('1348917952211542018', 'import', '导入', '', 1, 'lizuoyang', '2021-01-13 14:32:43', 'lizuoyang', '2021-01-13 14:32:43');
INSERT INTO `t_sys_operation` VALUES ('1349170526430912513', 'review', '审核', '审核按钮权限', 6, 'lizuoyang', '2021-01-13 09:44:55', NULL, '2021-01-13 14:32:43');
INSERT INTO `t_sys_operation` VALUES ('1349176192323248130', 'authority', '权限', NULL, 8, 'lizuoyang', '2021-01-13 10:07:25', NULL, '2021-01-13 14:32:43');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
                               `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                               `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编码',
                               `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
                               `role_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
                               `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除0:未删除。1：已删除',
                               `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
                               `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                               `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
                               `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', 'admin', '超级管理员', 1, 0, 'lizuoyang', '2020-12-17 14:39:11', NULL, '2021-01-20 09:03:13');
INSERT INTO `t_sys_role` VALUES ('SR511690897243369472', 'test', '测试人员', 2, 0, NULL, '2021-01-20 10:03:47', NULL, '2021-01-20 10:03:46');

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu`  (
                                    `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                    `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色主键',
                                    `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单表主键',
                                    `operation_list` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作权限集合',
                                    `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单中间表 角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910016', '1', '1', '', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910017', '1', '2', 'add,edit,remove', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910018', '1', '3', 'add,edit,remove,authority', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910019', '1', '1339467680672374786', 'add,edit,remove', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910020', '1', '1348465949859602434', 'add,edit,remove', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910021', '1', '4', 'add,edit,remove', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910022', '1', 'SM512154421820256256', '', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910023', '1', 'SM512154836246851584', '', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910024', '1', 'SM512165063914549248', '', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910025', '1', 'SM512497777129680896', '', '2021-01-22 15:30:39');
INSERT INTO `t_sys_role_menu` VALUES ('512497934541910026', '1', 'SM512497891965530112', '', '2021-01-22 15:30:39');

-- ----------------------------
-- Table structure for t_sys_system
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_system`;
CREATE TABLE `t_sys_system`  (
                                 `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                 `system_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '系统编码',
                                 `system_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '系统名称',
                                 `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                 `system_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
                                 `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除0:未删除。1：已删除',
                                 `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
                                 `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                 `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
                                 `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_system
-- ----------------------------
INSERT INTO `t_sys_system` VALUES ('4', 'system', '系统设置', NULL, 1, 0, 'lizuoyang', '2020-12-18 09:03:03', NULL, '2020-12-18 09:03:04');
INSERT INTO `t_sys_system` VALUES ('ST512134836744220672', 'blog', '博客后台', NULL, 2, 0, NULL, '2021-01-22 10:23:47', NULL, '2021-01-22 10:23:48');
INSERT INTO `t_sys_system` VALUES ('ST512420620013531136', 'shop', '商城后台', NULL, 3, 0, NULL, '2021-01-22 10:23:26', NULL, '2021-01-22 10:23:26');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
                               `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                               `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
                               `login_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录名',
                               `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
                               `sex` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别:男0、女1',
                               `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
                               `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
                               `image_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像路径',
                               `register_date` timestamp(0) NULL DEFAULT NULL COMMENT '注册时间',
                               `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除0:未删除。1：已删除',
                               `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
                               `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                               `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
                               `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', '李佐洋', 'lizuoyang', 'qwe22515', '男', '17752846201', '939822143@qq.com', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2020-12-07 18:40:32', 0, 'lizuoyang', '2021-01-20 13:51:22', 'lizuoyang', '2021-01-20 13:51:23');
INSERT INTO `t_sys_user` VALUES ('SU511694431472971776', '李元芳', 'test', 'Test@123', '男', '18822334455', 'liyuanfangsina@qq.com', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2021-01-20 10:17:50', 0, 'lizuoyang', '2021-01-20 15:05:59', 'lizuoyang', '2021-01-20 15:05:59');
INSERT INTO `t_sys_user` VALUES ('SU511763837443198976', '李园园', 'liyuanyuan', 'A@123456', '女', '15807392699', '15807392699@163.com', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2021-01-20 14:53:37', 0, 'lizuoyang', '2021-01-20 14:53:37', 'lizuoyang', '2021-01-20 14:53:37');
INSERT INTO `t_sys_user` VALUES ('SU511764152288628736', '包子本包', 'baozi', 'A@123456', '女', '15874255746', '247212241@qq.com', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2021-01-20 14:54:52', 0, 'lizuoyang', '2021-01-20 14:54:52', 'lizuoyang', '2021-01-20 14:54:52');

-- ----------------------------
-- Table structure for t_sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_menu`;
CREATE TABLE `t_sys_user_menu`  (
                                    `id` bigint(0) NULL DEFAULT NULL COMMENT '主键',
                                    `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户主键',
                                    `menu_id` bigint(0) NULL DEFAULT NULL COMMENT '菜单主键',
                                    `operation_list` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作权限集合',
                                    `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
                                    `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                    `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
                                    `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户单独菜单中间表 用户单独菜单中间表，里面用字段存操作功能集合,例子：create,import。预留单独给用户分配菜单、操作的功能' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
                                    `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
                                    `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户主键',
                                    `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色主键',
                                    `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色中间表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('511748174095929344', '1', '1', '2021-01-20 13:51:23');
INSERT INTO `t_sys_user_role` VALUES ('511763837518696449', 'SU511763837443198976', '1', '2021-01-20 15:04:21');
INSERT INTO `t_sys_user_role` VALUES ('511764152297017345', 'SU511764152288628736', '1', '2021-01-20 15:04:21');
INSERT INTO `t_sys_user_role` VALUES ('511766949902807040', 'SU511694431472971776', 'SR511690897243369472', '2021-01-20 15:05:59');

-- ----------------------------
-- Table structure for t_sys_website_info
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_website_info`;
CREATE TABLE `t_sys_website_info`  (
                                       `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键ID',
                                       `website_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站名称',
                                       `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
                                       `website_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '网站简介',
                                       `website_author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站作者',
                                       `website_case_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站备案号',
                                       `website_favicon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站Logo',
                                       `website_github_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站GitHub源码地址',
                                       `website_gitee_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站Gitee源码地址',
                                       `website_preview_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站预览地址',
                                       `created_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
                                       `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
                                       `updated_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
                                       `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
                                       `website_icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站图标',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '网站配置信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
