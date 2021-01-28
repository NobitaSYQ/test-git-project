/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.2.110
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : 192.168.2.110:3306
 Source Schema         : javashop02

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 06/07/2020 16:58:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for es_clerk0
-- ----------------------------
DROP TABLE IF EXISTS `es_clerk0`;
CREATE TABLE `es_clerk0`  (
  `clerk_id` bigint(20) NOT NULL COMMENT '店员id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `clerk_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店员名称',
  `founder` int(2) NULL DEFAULT NULL COMMENT '是否为超级管理员，1为超级管理员 0为其他管理员',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  `user_state` int(2) NULL DEFAULT NULL COMMENT '店员状态，0为禁用，1为正常',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建日期',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`clerk_id`) USING BTREE,
  INDEX `index_clerk`(`clerk_id`, `member_id`, `role_id`, `shop_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店员(es_clerk)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_clerk1
-- ----------------------------
DROP TABLE IF EXISTS `es_clerk1`;
CREATE TABLE `es_clerk1`  (
  `clerk_id` bigint(20) NOT NULL COMMENT '店员id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `clerk_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店员名称',
  `founder` int(2) NULL DEFAULT NULL COMMENT '是否为超级管理员，1为超级管理员 0为其他管理员',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  `user_state` int(2) NULL DEFAULT NULL COMMENT '店员状态，0为禁用，1为正常',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建日期',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`clerk_id`) USING BTREE,
  INDEX `index_clerk`(`clerk_id`, `member_id`, `role_id`, `shop_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店员(es_clerk)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_goods0
-- ----------------------------
DROP TABLE IF EXISTS `es_goods0`;
CREATE TABLE `es_goods0`  (
  `goods_id` bigint(20) NOT NULL COMMENT '主键',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌id',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `goods_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型normal普通point积分',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '重量',
  `market_enable` int(1) NULL DEFAULT NULL COMMENT '上架状态 1上架  0下架',
  `intro` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详情',
  `price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `cost` decimal(20, 2) NULL DEFAULT NULL COMMENT '成本价格',
  `mktprice` decimal(20, 2) NULL DEFAULT NULL COMMENT '市场价格',
  `have_spec` int(1) NULL DEFAULT NULL COMMENT '是否有规格0没有 1有',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `last_modify` bigint(20) NULL DEFAULT NULL COMMENT '最后修改时间',
  `view_count` int(10) NULL DEFAULT NULL COMMENT '浏览数量',
  `buy_count` int(10) NULL DEFAULT NULL COMMENT '购买数量',
  `disabled` int(10) NULL DEFAULT NULL COMMENT '是否被删除0 删除 1未删除',
  `quantity` int(10) NULL DEFAULT NULL COMMENT '库存',
  `enable_quantity` int(10) NULL DEFAULT NULL COMMENT '可用库存',
  `point` int(10) NULL DEFAULT NULL COMMENT '如果是积分商品需要使用的积分',
  `page_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seo标题',
  `meta_keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seo关键字',
  `meta_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seo描述',
  `grade` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品好评率',
  `thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图路径',
  `big` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大图路径',
  `small` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图路径',
  `original` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原图路径',
  `seller_id` bigint(20) NULL DEFAULT NULL COMMENT '卖家id',
  `shop_cat_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺分类id',
  `comment_num` int(10) NULL DEFAULT NULL COMMENT '评论数量',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '运费模板id',
  `goods_transfee_charge` int(1) NULL DEFAULT NULL COMMENT '谁承担运费0：买家承担，1：卖家承担',
  `seller_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖家名字',
  `is_auth` int(1) NULL DEFAULT NULL COMMENT '0 需要审核 并且待审核，1 不需要审核 2需要审核 且审核通过 3 需要审核 且审核未通过',
  `auth_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核信息',
  `self_operated` int(1) NULL DEFAULT NULL COMMENT '是否是自营商品 0 不是 1是',
  `under_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下架原因',
  `priority` int(1) NULL DEFAULT 1 COMMENT '优先级:高(3)、中(2)、低(1)',
  `mobile_intro` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品移动端详情',
  `goods_video` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品视频',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `ind_goods_category_id`(`category_id`) USING BTREE,
  INDEX `ind_goods_brand_id`(`brand_id`) USING BTREE,
  INDEX `ind_goods_name`(`goods_name`) USING BTREE,
  INDEX `ind_goods_sn`(`sn`) USING BTREE,
  INDEX `ind_goods_other`(`goods_type`, `market_enable`, `disabled`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品(es_goods)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_goods1
-- ----------------------------
DROP TABLE IF EXISTS `es_goods1`;
CREATE TABLE `es_goods1`  (
  `goods_id` bigint(20) NOT NULL COMMENT '主键',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌id',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `goods_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型normal普通point积分',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '重量',
  `market_enable` int(1) NULL DEFAULT NULL COMMENT '上架状态 1上架  0下架',
  `intro` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详情',
  `price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `cost` decimal(20, 2) NULL DEFAULT NULL COMMENT '成本价格',
  `mktprice` decimal(20, 2) NULL DEFAULT NULL COMMENT '市场价格',
  `have_spec` int(1) NULL DEFAULT NULL COMMENT '是否有规格0没有 1有',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `last_modify` bigint(20) NULL DEFAULT NULL COMMENT '最后修改时间',
  `view_count` int(10) NULL DEFAULT NULL COMMENT '浏览数量',
  `buy_count` int(10) NULL DEFAULT NULL COMMENT '购买数量',
  `disabled` int(10) NULL DEFAULT NULL COMMENT '是否被删除0 删除 1未删除',
  `quantity` int(10) NULL DEFAULT NULL COMMENT '库存',
  `enable_quantity` int(10) NULL DEFAULT NULL COMMENT '可用库存',
  `point` int(10) NULL DEFAULT NULL COMMENT '如果是积分商品需要使用的积分',
  `page_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seo标题',
  `meta_keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seo关键字',
  `meta_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seo描述',
  `grade` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品好评率',
  `thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图路径',
  `big` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大图路径',
  `small` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图路径',
  `original` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原图路径',
  `seller_id` bigint(20) NULL DEFAULT NULL COMMENT '卖家id',
  `shop_cat_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺分类id',
  `comment_num` int(10) NULL DEFAULT NULL COMMENT '评论数量',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '运费模板id',
  `goods_transfee_charge` int(1) NULL DEFAULT NULL COMMENT '谁承担运费0：买家承担，1：卖家承担',
  `seller_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖家名字',
  `is_auth` int(1) NULL DEFAULT NULL COMMENT '0 需要审核 并且待审核，1 不需要审核 2需要审核 且审核通过 3 需要审核 且审核未通过',
  `auth_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核信息',
  `self_operated` int(1) NULL DEFAULT NULL COMMENT '是否是自营商品 0 不是 1是',
  `under_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下架原因',
  `priority` int(1) NULL DEFAULT 1 COMMENT '优先级:高(3)、中(2)、低(1)',
  `mobile_intro` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品移动端详情',
  `goods_video` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品视频',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `ind_goods_category_id`(`category_id`) USING BTREE,
  INDEX `ind_goods_brand_id`(`brand_id`) USING BTREE,
  INDEX `ind_goods_name`(`goods_name`) USING BTREE,
  INDEX `ind_goods_sn`(`sn`) USING BTREE,
  INDEX `ind_goods_other`(`goods_type`, `market_enable`, `disabled`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品(es_goods)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_goods_sku0
-- ----------------------------
DROP TABLE IF EXISTS `es_goods_sku0`;
CREATE TABLE `es_goods_sku0`  (
  `sku_id` bigint(20) NOT NULL COMMENT '主键',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `quantity` int(10) NULL DEFAULT NULL COMMENT '库存',
  `enable_quantity` int(10) NULL DEFAULT NULL COMMENT '可用库存',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `specs` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '规格信息json',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本价格',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '重量',
  `seller_id` bigint(20) NULL DEFAULT NULL COMMENT '卖家id',
  `seller_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖家名称',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `hash_code` int(10) NOT NULL COMMENT '标识规格唯一性',
  PRIMARY KEY (`sku_id`) USING BTREE,
  INDEX `index_goods_sku`(`sku_id`, `goods_id`, `sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品sku(es_goods_sku)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_goods_sku1
-- ----------------------------
DROP TABLE IF EXISTS `es_goods_sku1`;
CREATE TABLE `es_goods_sku1`  (
  `sku_id` bigint(20) NOT NULL COMMENT '主键',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `quantity` int(10) NULL DEFAULT NULL COMMENT '库存',
  `enable_quantity` int(10) NULL DEFAULT NULL COMMENT '可用库存',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `specs` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '规格信息json',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本价格',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '重量',
  `seller_id` bigint(20) NULL DEFAULT NULL COMMENT '卖家id',
  `seller_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖家名称',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `hash_code` int(10) NOT NULL COMMENT '标识规格唯一性',
  PRIMARY KEY (`sku_id`) USING BTREE,
  INDEX `index_goods_sku`(`sku_id`, `goods_id`, `sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品sku(es_goods_sku)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_member0
-- ----------------------------
DROP TABLE IF EXISTS `es_member0`;
CREATE TABLE `es_member0`  (
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员登陆用户名',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员登陆密码',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '会员注册时间',
  `sex` int(1) NULL DEFAULT NULL COMMENT '会员性别 1：男，0：女',
  `birthday` bigint(20) NULL DEFAULT NULL COMMENT '会员生日',
  `province_id` bigint(20) NULL DEFAULT NULL COMMENT '所属省份ID',
  `city_id` bigint(20) NULL DEFAULT NULL COMMENT '所属城市ID',
  `county_id` bigint(20) NULL DEFAULT NULL COMMENT '所属县(区)ID',
  `town_id` bigint(20) NULL DEFAULT NULL COMMENT '所属城镇ID',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属省份名称',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属城市名称',
  `county` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属县(区)名称',
  `town` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属城镇名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机号码',
  `grade_point` bigint(20) NULL DEFAULT NULL COMMENT '等级积分',
  `msn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员MSN',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员备注',
  `last_login` bigint(20) NULL DEFAULT NULL COMMENT '上次登陆时间',
  `login_count` int(10) NULL DEFAULT NULL COMMENT '登陆次数',
  `is_cheked` int(1) NULL DEFAULT NULL COMMENT '邮件是否已验证',
  `register_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册IP地址',
  `recommend_point_state` int(10) NULL DEFAULT NULL COMMENT '是否已经完成了推荐积分',
  `info_full` int(1) NULL DEFAULT NULL COMMENT '会员信息是否完善',
  `find_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'find_code',
  `face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员头像',
  `midentity` int(10) NULL DEFAULT NULL COMMENT '身份证号',
  `disabled` int(8) NULL DEFAULT NULL COMMENT '会员状态',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `have_shop` int(1) NULL DEFAULT NULL COMMENT '是否开通店铺',
  `consum_point` bigint(20) NULL DEFAULT NULL COMMENT '消费积分',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`member_id`) USING BTREE,
  INDEX `ind_member_uname`(`uname`, `email`) USING BTREE,
  INDEX `ind_member_b2b2c`(`shop_id`, `have_shop`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员表(es_member)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_member1
-- ----------------------------
DROP TABLE IF EXISTS `es_member1`;
CREATE TABLE `es_member1`  (
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员登陆用户名',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员登陆密码',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '会员注册时间',
  `sex` int(1) NULL DEFAULT NULL COMMENT '会员性别 1：男，0：女',
  `birthday` bigint(20) NULL DEFAULT NULL COMMENT '会员生日',
  `province_id` bigint(20) NULL DEFAULT NULL COMMENT '所属省份ID',
  `city_id` bigint(20) NULL DEFAULT NULL COMMENT '所属城市ID',
  `county_id` bigint(20) NULL DEFAULT NULL COMMENT '所属县(区)ID',
  `town_id` bigint(20) NULL DEFAULT NULL COMMENT '所属城镇ID',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属省份名称',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属城市名称',
  `county` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属县(区)名称',
  `town` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属城镇名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机号码',
  `grade_point` bigint(20) NULL DEFAULT NULL COMMENT '等级积分',
  `msn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员MSN',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员备注',
  `last_login` bigint(20) NULL DEFAULT NULL COMMENT '上次登陆时间',
  `login_count` int(10) NULL DEFAULT NULL COMMENT '登陆次数',
  `is_cheked` int(1) NULL DEFAULT NULL COMMENT '邮件是否已验证',
  `register_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册IP地址',
  `recommend_point_state` int(10) NULL DEFAULT NULL COMMENT '是否已经完成了推荐积分',
  `info_full` int(1) NULL DEFAULT NULL COMMENT '会员信息是否完善',
  `find_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'find_code',
  `face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员头像',
  `midentity` int(10) NULL DEFAULT NULL COMMENT '身份证号',
  `disabled` int(8) NULL DEFAULT NULL COMMENT '会员状态',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `have_shop` int(1) NULL DEFAULT NULL COMMENT '是否开通店铺',
  `consum_point` bigint(20) NULL DEFAULT NULL COMMENT '消费积分',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`member_id`) USING BTREE,
  INDEX `ind_member_uname`(`uname`, `email`) USING BTREE,
  INDEX `ind_member_b2b2c`(`shop_id`, `have_shop`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员表(es_member)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order0
-- ----------------------------
DROP TABLE IF EXISTS `es_order0`;
CREATE TABLE `es_order0`  (
  `order_id` bigint(20) NOT NULL COMMENT '主键ID',
  `trade_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易编号',
  `seller_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `seller_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家账号',
  `order_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `pay_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款状态',
  `ship_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货运状态',
  `shipping_id` bigint(20) NULL DEFAULT NULL COMMENT '配送方式ID',
  `comment_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论是否完成',
  `shipping_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送方式',
  `payment_method_id` bigint(20) NULL DEFAULT NULL COMMENT '支付方式id',
  `payment_plugin_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付插件id',
  `payment_method_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式名称',
  `payment_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式类型',
  `payment_time` bigint(20) NULL DEFAULT NULL COMMENT '支付时间',
  `pay_money` decimal(20, 2) NULL DEFAULT NULL COMMENT '已支付金额',
  `ship_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `ship_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `ship_zip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人邮编',
  `ship_mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机',
  `ship_tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `receive_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货时间',
  `ship_province_id` bigint(20) NULL DEFAULT NULL COMMENT '配送地区-省份ID',
  `ship_city_id` bigint(20) NULL DEFAULT NULL COMMENT '配送地区-城市ID',
  `ship_county_id` bigint(20) NULL DEFAULT NULL COMMENT '配送地区-区(县)ID',
  `ship_town_id` bigint(20) NULL DEFAULT NULL COMMENT '配送街道id',
  `ship_province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送地区-省份',
  `ship_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送地区-城市',
  `ship_county` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送地区-区(县)',
  `ship_town` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送街道',
  `order_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '订单总额',
  `goods_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品总额',
  `shipping_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '配送费用',
  `discount_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `disabled` int(1) NULL DEFAULT NULL COMMENT '是否被删除',
  `weight` decimal(20, 2) NULL DEFAULT NULL COMMENT '订单商品总重量',
  `goods_num` int(10) NULL DEFAULT NULL COMMENT '商品数量',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `cancel_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单取消原因',
  `the_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签收人',
  `items_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '货物列表json',
  `warehouse_id` int(10) NULL DEFAULT NULL COMMENT '发货仓库ID',
  `need_pay_money` decimal(20, 2) NULL DEFAULT NULL COMMENT '应付金额',
  `ship_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货单号',
  `address_id` bigint(20) NULL DEFAULT NULL COMMENT '收货地址ID',
  `admin_remark` int(50) NULL DEFAULT NULL COMMENT '管理员备注',
  `logi_id` bigint(20) NULL DEFAULT NULL COMMENT '物流公司ID',
  `logi_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流公司名称',
  `complete_time` bigint(20) NULL DEFAULT NULL COMMENT '完成时间',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '订单创建时间',
  `signing_time` bigint(20) NULL DEFAULT NULL COMMENT '签收时间',
  `ship_time` bigint(20) NULL DEFAULT NULL COMMENT '送货时间',
  `pay_order_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式返回的交易号',
  `service_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售后状态',
  `bill_status` int(10) NULL DEFAULT NULL COMMENT '结算状态',
  `bill_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结算单号',
  `client_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单来源',
  `sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `need_receipt` int(1) NULL DEFAULT NULL COMMENT '是否需要发票',
  `order_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'normal' COMMENT '订单类型',
  `order_data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '订单数据',
  `balance` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '预存款抵扣金额',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `ind_order_sn`(`sn`) USING BTREE,
  INDEX `ind_order_state`(`order_status`, `pay_status`, `ship_status`) USING BTREE,
  INDEX `ind_order_memberid`(`member_id`) USING BTREE,
  INDEX `ind_order_term`(`disabled`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表(es_order)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order1
-- ----------------------------
DROP TABLE IF EXISTS `es_order1`;
CREATE TABLE `es_order1`  (
  `order_id` bigint(20) NOT NULL COMMENT '主键ID',
  `trade_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易编号',
  `seller_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `seller_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家账号',
  `order_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `pay_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款状态',
  `ship_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货运状态',
  `shipping_id` bigint(20) NULL DEFAULT NULL COMMENT '配送方式ID',
  `comment_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论是否完成',
  `shipping_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送方式',
  `payment_method_id` bigint(20) NULL DEFAULT NULL COMMENT '支付方式id',
  `payment_plugin_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付插件id',
  `payment_method_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式名称',
  `payment_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式类型',
  `payment_time` bigint(20) NULL DEFAULT NULL COMMENT '支付时间',
  `pay_money` decimal(20, 2) NULL DEFAULT NULL COMMENT '已支付金额',
  `ship_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `ship_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `ship_zip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人邮编',
  `ship_mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机',
  `ship_tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `receive_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货时间',
  `ship_province_id` bigint(20) NULL DEFAULT NULL COMMENT '配送地区-省份ID',
  `ship_city_id` bigint(20) NULL DEFAULT NULL COMMENT '配送地区-城市ID',
  `ship_county_id` bigint(20) NULL DEFAULT NULL COMMENT '配送地区-区(县)ID',
  `ship_town_id` bigint(20) NULL DEFAULT NULL COMMENT '配送街道id',
  `ship_province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送地区-省份',
  `ship_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送地区-城市',
  `ship_county` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送地区-区(县)',
  `ship_town` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送街道',
  `order_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '订单总额',
  `goods_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品总额',
  `shipping_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '配送费用',
  `discount_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `disabled` int(1) NULL DEFAULT NULL COMMENT '是否被删除',
  `weight` decimal(20, 2) NULL DEFAULT NULL COMMENT '订单商品总重量',
  `goods_num` int(10) NULL DEFAULT NULL COMMENT '商品数量',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `cancel_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单取消原因',
  `the_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签收人',
  `items_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '货物列表json',
  `warehouse_id` int(10) NULL DEFAULT NULL COMMENT '发货仓库ID',
  `need_pay_money` decimal(20, 2) NULL DEFAULT NULL COMMENT '应付金额',
  `ship_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货单号',
  `address_id` bigint(20) NULL DEFAULT NULL COMMENT '收货地址ID',
  `admin_remark` int(50) NULL DEFAULT NULL COMMENT '管理员备注',
  `logi_id` bigint(20) NULL DEFAULT NULL COMMENT '物流公司ID',
  `logi_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流公司名称',
  `complete_time` bigint(20) NULL DEFAULT NULL COMMENT '完成时间',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '订单创建时间',
  `signing_time` bigint(20) NULL DEFAULT NULL COMMENT '签收时间',
  `ship_time` bigint(20) NULL DEFAULT NULL COMMENT '送货时间',
  `pay_order_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式返回的交易号',
  `service_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售后状态',
  `bill_status` int(10) NULL DEFAULT NULL COMMENT '结算状态',
  `bill_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结算单号',
  `client_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单来源',
  `sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `need_receipt` int(1) NULL DEFAULT NULL COMMENT '是否需要发票',
  `order_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'normal' COMMENT '订单类型',
  `order_data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '订单数据',
  `balance` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '预存款抵扣金额',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `ind_order_sn`(`sn`) USING BTREE,
  INDEX `ind_order_state`(`order_status`, `pay_status`, `ship_status`) USING BTREE,
  INDEX `ind_order_memberid`(`member_id`) USING BTREE,
  INDEX `ind_order_term`(`disabled`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表(es_order)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order_items0
-- ----------------------------
DROP TABLE IF EXISTS `es_order_items0`;
CREATE TABLE `es_order_items0`  (
  `item_id` bigint(20) NOT NULL COMMENT '主键ID',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '货品ID',
  `num` int(10) NULL DEFAULT NULL COMMENT '销售量',
  `ship_num` int(10) NULL DEFAULT NULL COMMENT '发货量',
  `trade_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易编号',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` decimal(20, 2) NULL DEFAULT NULL COMMENT '销售金额',
  `cat_id` bigint(20) NULL DEFAULT NULL COMMENT '分类ID',
  `state` int(1) NULL DEFAULT NULL COMMENT '状态',
  `snapshot_id` bigint(20) NULL DEFAULT NULL COMMENT '快照id',
  `spec_json` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格json',
  `promotion_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '促销类型',
  `promotion_id` bigint(20) NULL DEFAULT NULL COMMENT '促销id',
  `refund_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '订单项可退款金额',
  `comment_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'UNFINISHED' COMMENT '评论状态',
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `es_order_item`(`order_sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单货物表(es_order_items)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order_items1
-- ----------------------------
DROP TABLE IF EXISTS `es_order_items1`;
CREATE TABLE `es_order_items1`  (
  `item_id` bigint(20) NOT NULL COMMENT '主键ID',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '货品ID',
  `num` int(10) NULL DEFAULT NULL COMMENT '销售量',
  `ship_num` int(10) NULL DEFAULT NULL COMMENT '发货量',
  `trade_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易编号',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` decimal(20, 2) NULL DEFAULT NULL COMMENT '销售金额',
  `cat_id` bigint(20) NULL DEFAULT NULL COMMENT '分类ID',
  `state` int(1) NULL DEFAULT NULL COMMENT '状态',
  `snapshot_id` bigint(20) NULL DEFAULT NULL COMMENT '快照id',
  `spec_json` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格json',
  `promotion_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '促销类型',
  `promotion_id` bigint(20) NULL DEFAULT NULL COMMENT '促销id',
  `refund_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '订单项可退款金额',
  `comment_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'UNFINISHED' COMMENT '评论状态',
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `es_order_item`(`order_sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单货物表(es_order_items)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order_log0
-- ----------------------------
DROP TABLE IF EXISTS `es_order_log0`;
CREATE TABLE `es_order_log0`  (
  `log_id` bigint(20) NOT NULL COMMENT '主键ID',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `op_id` bigint(20) NULL DEFAULT NULL COMMENT '操作者id',
  `op_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者名称',
  `message` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `op_time` bigint(20) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `ind_order_log`(`order_sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单日志表(es_order_log)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order_log1
-- ----------------------------
DROP TABLE IF EXISTS `es_order_log1`;
CREATE TABLE `es_order_log1`  (
  `log_id` bigint(20) NOT NULL COMMENT '主键ID',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `op_id` bigint(20) NULL DEFAULT NULL COMMENT '操作者id',
  `op_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者名称',
  `message` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `op_time` bigint(20) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `ind_order_log`(`order_sn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单日志表(es_order_log)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order_meta
-- ----------------------------
DROP TABLE IF EXISTS `es_order_meta`;
CREATE TABLE `es_order_meta`  (
  `meta_id` bigint(20) NOT NULL COMMENT '主键ID',
  `order_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `meta_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展-键',
  `meta_value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '扩展-值',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售后状态',
  PRIMARY KEY (`meta_id`) USING BTREE,
  INDEX `es_ind_orderex_metaid`(`meta_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单扩展信息表(es_order_meta)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order_meta0
-- ----------------------------
DROP TABLE IF EXISTS `es_order_meta0`;
CREATE TABLE `es_order_meta0`  (
  `meta_id` bigint(20) NOT NULL COMMENT '主键ID',
  `order_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `meta_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展-键',
  `meta_value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '扩展-值',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售后状态',
  PRIMARY KEY (`meta_id`) USING BTREE,
  INDEX `es_ind_orderex_metaid`(`meta_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单扩展信息表(es_order_meta0)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_order_meta1
-- ----------------------------
DROP TABLE IF EXISTS `es_order_meta1`;
CREATE TABLE `es_order_meta1`  (
  `meta_id` bigint(20) NOT NULL COMMENT '主键ID',
  `order_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `meta_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展-键',
  `meta_value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '扩展-值',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售后状态',
  PRIMARY KEY (`meta_id`) USING BTREE,
  INDEX `es_ind_orderex_metaid`(`meta_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单扩展信息表(es_order_meta1)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_tag_goods0
-- ----------------------------
DROP TABLE IF EXISTS `es_tag_goods0`;
CREATE TABLE `es_tag_goods0`  (
  `id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NULL DEFAULT NULL COMMENT '标签id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_tag_goods`(`id`, `tag_id`, `goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签商品关联(es_tag_goods)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_tag_goods1
-- ----------------------------
DROP TABLE IF EXISTS `es_tag_goods1`;
CREATE TABLE `es_tag_goods1`  (
  `id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NULL DEFAULT NULL COMMENT '标签id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_tag_goods`(`id`, `tag_id`, `goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签商品关联(es_tag_goods)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_tags0
-- ----------------------------
DROP TABLE IF EXISTS `es_tags0`;
CREATE TABLE `es_tags0`  (
  `tag_id` bigint(20) NOT NULL COMMENT '主键',
  `tag_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名字',
  `seller_id` bigint(20) NULL DEFAULT NULL COMMENT '所属卖家',
  `mark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  PRIMARY KEY (`tag_id`) USING BTREE,
  INDEX `index_tags`(`tag_id`, `seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品标签(es_tags)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_tags1
-- ----------------------------
DROP TABLE IF EXISTS `es_tags1`;
CREATE TABLE `es_tags1`  (
  `tag_id` bigint(20) NOT NULL COMMENT '主键',
  `tag_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名字',
  `seller_id` bigint(20) NULL DEFAULT NULL COMMENT '所属卖家',
  `mark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  PRIMARY KEY (`tag_id`) USING BTREE,
  INDEX `index_tags`(`tag_id`, `seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品标签(es_tags)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_trade0
-- ----------------------------
DROP TABLE IF EXISTS `es_trade0`;
CREATE TABLE `es_trade0`  (
  `trade_id` bigint(20) NOT NULL COMMENT 'trade_id',
  `trade_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易编号',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '买家id',
  `member_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家用户名',
  `payment_method_id` bigint(20) NULL DEFAULT NULL COMMENT '支付方式id',
  `payment_plugin_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付插件id',
  `payment_method_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式名称',
  `payment_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式类型',
  `total_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '总价格',
  `goods_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `freight_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '运费',
  `discount_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '优惠的金额',
  `consignee_id` bigint(20) NULL DEFAULT NULL COMMENT '收货人id',
  `consignee_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `consignee_country` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货国家',
  `consignee_country_id` bigint(20) NULL DEFAULT NULL COMMENT '收货国家id',
  `consignee_province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货省',
  `consignee_province_id` bigint(20) NULL DEFAULT NULL COMMENT '收货省id',
  `consignee_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货市',
  `consignee_city_id` bigint(20) NULL DEFAULT NULL COMMENT '收货市id',
  `consignee_county` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货区',
  `consignee_county_id` bigint(20) NULL DEFAULT NULL COMMENT '收货区id',
  `consignee_town` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货镇',
  `consignee_town_id` bigint(20) NULL DEFAULT NULL COMMENT '收货镇id',
  `consignee_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货详细地址',
  `consignee_mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机号',
  `consignee_telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '交易创建时间',
  `order_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '订单json(预留，7.0可能废弃)',
  `trade_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `balance` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '预存款抵扣金额',
  PRIMARY KEY (`trade_id`) USING BTREE,
  INDEX `index_trade`(`trade_id`, `trade_sn`, `member_id`, `payment_method_id`, `payment_plugin_id`, `trade_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交易表(es_trade)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for es_trade1
-- ----------------------------
DROP TABLE IF EXISTS `es_trade1`;
CREATE TABLE `es_trade1`  (
  `trade_id` bigint(20) NOT NULL COMMENT 'trade_id',
  `trade_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易编号',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '买家id',
  `member_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家用户名',
  `payment_method_id` bigint(20) NULL DEFAULT NULL COMMENT '支付方式id',
  `payment_plugin_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付插件id',
  `payment_method_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式名称',
  `payment_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式类型',
  `total_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '总价格',
  `goods_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `freight_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '运费',
  `discount_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '优惠的金额',
  `consignee_id` bigint(20) NULL DEFAULT NULL COMMENT '收货人id',
  `consignee_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `consignee_country` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货国家',
  `consignee_country_id` bigint(20) NULL DEFAULT NULL COMMENT '收货国家id',
  `consignee_province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货省',
  `consignee_province_id` bigint(20) NULL DEFAULT NULL COMMENT '收货省id',
  `consignee_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货市',
  `consignee_city_id` bigint(20) NULL DEFAULT NULL COMMENT '收货市id',
  `consignee_county` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货区',
  `consignee_county_id` bigint(20) NULL DEFAULT NULL COMMENT '收货区id',
  `consignee_town` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货镇',
  `consignee_town_id` bigint(20) NULL DEFAULT NULL COMMENT '收货镇id',
  `consignee_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货详细地址',
  `consignee_mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机号',
  `consignee_telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '交易创建时间',
  `order_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '订单json(预留，7.0可能废弃)',
  `trade_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `balance` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '预存款抵扣金额',
  PRIMARY KEY (`trade_id`) USING BTREE,
  INDEX `index_trade`(`trade_id`, `trade_sn`, `member_id`, `payment_method_id`, `payment_plugin_id`, `trade_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交易表(es_trade)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_pay_log0`;
CREATE TABLE `es_pay_log0`  (
  `pay_log_id` bigint(20) NOT NULL COMMENT '收款单ID',
  `order_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `pay_way` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `pay_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `pay_time` bigint(20) NULL DEFAULT NULL COMMENT '付款时间',
  `pay_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '付款金额',
  `pay_member_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款会员名',
  `pay_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款状态',
  `pay_log_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流水号',
  `pay_order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式返回流水号',
  PRIMARY KEY (`pay_log_id`) USING BTREE,
  INDEX `ind_pay_log`(`order_sn`, `pay_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收款单(es_pay_log)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_pay_log1`;
CREATE TABLE `es_pay_log1`  (
  `pay_log_id` bigint(20) NOT NULL COMMENT '收款单ID',
  `order_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `pay_way` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `pay_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `pay_time` bigint(20) NULL DEFAULT NULL COMMENT '付款时间',
  `pay_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '付款金额',
  `pay_member_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款会员名',
  `pay_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款状态',
  `pay_log_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流水号',
  `pay_order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式返回流水号',
  PRIMARY KEY (`pay_log_id`) USING BTREE,
  INDEX `ind_pay_log`(`order_sn`, `pay_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收款单(es_pay_log)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_shop_notice_log0`;
CREATE TABLE `es_shop_notice_log0`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `notice_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站内信内容',
  `send_time` bigint(50) NULL DEFAULT NULL COMMENT '发送时间',
  `is_delete` int(10) NULL DEFAULT NULL COMMENT '是否删除 ：1 删除   0  未删除',
  `is_read` int(10) NULL DEFAULT NULL COMMENT '是否已读 ：1已读   0 未读',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_shop_notice_log`(`id`, `shop_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺站内消息(es_shop_notice_log)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_shop_notice_log1`;
CREATE TABLE `es_shop_notice_log1`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `notice_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站内信内容',
  `send_time` bigint(50) NULL DEFAULT NULL COMMENT '发送时间',
  `is_delete` int(10) NULL DEFAULT NULL COMMENT '是否删除 ：1 删除   0  未删除',
  `is_read` int(10) NULL DEFAULT NULL COMMENT '是否已读 ：1已读   0 未读',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_shop_notice_log`(`id`, `shop_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺站内消息(es_shop_notice_log)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_order_out_status0`;
CREATE TABLE `es_order_out_status0`  (
  `out_id` bigint(20) NOT NULL COMMENT '主键',
  `order_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `out_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出库类型',
  `out_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出库状态',
  PRIMARY KEY (`out_id`) USING BTREE,
  INDEX `index_order_out_status`(`out_id`, `order_sn`, `out_type`, `out_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单出库状态(es_order_out_status)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_order_out_status1`;
CREATE TABLE `es_order_out_status1`  (
  `out_id` bigint(20) NOT NULL COMMENT '主键',
  `order_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `out_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出库类型',
  `out_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出库状态',
  PRIMARY KEY (`out_id`) USING BTREE,
  INDEX `index_order_out_status`(`out_id`, `order_sn`, `out_type`, `out_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单出库状态(es_order_out_status)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_member_notice_log0`;
CREATE TABLE `es_member_notice_log0`  (
  `id` bigint(20) NOT NULL COMMENT '会员历史消息id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '站内信内容',
  `send_time` bigint(20) NULL DEFAULT NULL COMMENT '发送时间',
  `is_del` int(2) NULL DEFAULT NULL COMMENT '是否删除，0删除，1没有删除',
  `is_read` int(2) NULL DEFAULT NULL COMMENT '是否已读，0未读，1已读',
  `receive_time` bigint(20) NULL DEFAULT NULL COMMENT '接收时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_member_log`(`id`, `member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员站内消息历史(es_member_notice_log)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_member_notice_log1`;
CREATE TABLE `es_member_notice_log1`  (
  `id` bigint(20) NOT NULL COMMENT '会员历史消息id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '站内信内容',
  `send_time` bigint(20) NULL DEFAULT NULL COMMENT '发送时间',
  `is_del` int(2) NULL DEFAULT NULL COMMENT '是否删除，0删除，1没有删除',
  `is_read` int(2) NULL DEFAULT NULL COMMENT '是否已读，0未读，1已读',
  `receive_time` bigint(20) NULL DEFAULT NULL COMMENT '接收时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_member_log`(`id`, `member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员站内消息历史(es_member_notice_log)' ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_pintuan_child_order0`;
CREATE TABLE `es_pintuan_child_order0`  (
  `child_order_id` bigint(20) NOT NULL COMMENT '子订单id',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `pintuan_id` bigint(20) NULL DEFAULT NULL COMMENT '拼团活动id',
  `order_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '主订单id',
  `member_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家名称',
  `origin_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '原价',
  `sales_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '拼团价',
  PRIMARY KEY (`child_order_id`) USING BTREE,
  INDEX `index_pintuan_child_order0`(`child_order_id`, `order_sn`, `member_id`, `sku_id`, `pintuan_id`, `order_status`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `es_pintuan_child_order1`;
CREATE TABLE `es_pintuan_child_order1`  (
  `child_order_id` bigint(20) NOT NULL COMMENT '子订单id',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `pintuan_id` bigint(20) NULL DEFAULT NULL COMMENT '拼团活动id',
  `order_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '主订单id',
  `member_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家名称',
  `origin_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '原价',
  `sales_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '拼团价',
  PRIMARY KEY (`child_order_id`) USING BTREE,
  INDEX `index_pintuan_child_order0`(`child_order_id`, `order_sn`, `member_id`, `sku_id`, `pintuan_id`, `order_status`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

SET FOREIGN_KEY_CHECKS = 1;