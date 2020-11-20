/*
 Navicat Premium Data Transfer

 Source Server         : 末端供应链
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 10.12.49.26:3306
 Source Schema         : esc-test-200806

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 11/08/2020 14:37:00
*/


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_external_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `basic_external_warehouse`;
CREATE TABLE `basic_external_warehouse`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(32)  NOT NULL COMMENT '名字',
  `address` varchar(128)  NOT NULL COMMENT '地址',
  `latitude` double NULL DEFAULT NULL COMMENT '纬度',
  `longitude` double NULL DEFAULT NULL COMMENT '经度',
  `description` varchar(64)  NULL DEFAULT NULL COMMENT '描述',
  `phone` varchar(11)  NOT NULL COMMENT '手机号',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_material
-- ----------------------------
DROP TABLE IF EXISTS `basic_material`;
CREATE TABLE `basic_material`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(64)  NOT NULL COMMENT '名称',
  `code` varchar(32)  NULL DEFAULT NULL COMMENT '编码',
  `spec` varchar(64)  NULL DEFAULT NULL COMMENT '规格',
  `unit` varchar(32)  NULL DEFAULT NULL COMMENT '单位',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除(0: 未删除 1: 删除)'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_product
-- ----------------------------
DROP TABLE IF EXISTS `basic_product`;
CREATE TABLE `basic_product`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(64)  NOT NULL COMMENT '名称',
  `spec` varchar(64)  NULL DEFAULT NULL COMMENT '规格',
  `unit` varchar(32)  NULL DEFAULT NULL COMMENT '单位',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除(0: 未删除 1: 删除)'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_product_bom
-- ----------------------------
DROP TABLE IF EXISTS `basic_product_bom`;
CREATE TABLE `basic_product_bom`  (
  `id` varchar(32)  NOT NULL,
  `num` int(11) NOT NULL COMMENT '数量',
  `product_id` varchar(32)  NOT NULL COMMENT '成品编号',
  `material_id` varchar(32)  NULL DEFAULT NULL COMMENT '物料编号',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_production_line
-- ----------------------------
DROP TABLE IF EXISTS `basic_production_line`;
CREATE TABLE `basic_production_line`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(64)  NOT NULL COMMENT '名称',
  `address` varchar(32)  NULL DEFAULT NULL COMMENT '位置',
  `leader` varchar(32)  NULL DEFAULT NULL COMMENT '产线负责人',
  `leader_phone` varchar(32)  NULL DEFAULT NULL COMMENT '负责人联系方式',
  `warehouse_id` varchar(32)  NULL DEFAULT NULL COMMENT '内仓编号',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `workshop_id` varchar(32)  NULL DEFAULT NULL COMMENT '车间编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_shelves
-- ----------------------------
DROP TABLE IF EXISTS `basic_shelves`;
CREATE TABLE `basic_shelves`  (
  `id` varchar(32)  NOT NULL,
  `warehouse_id` varchar(32)  NOT NULL COMMENT '内仓编号',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `layers` varchar(32)  NOT NULL COMMENT '货架的总层数',
  `code` varchar(32)  NULL DEFAULT NULL COMMENT '货架编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '货架的总层数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_storage_bin
-- ----------------------------
DROP TABLE IF EXISTS `basic_storage_bin`;
CREATE TABLE `basic_storage_bin`  (
  `id` varchar(32)  NOT NULL,
  `code` varchar(32)  NULL DEFAULT NULL COMMENT '库位编码',
  `warehouse_id` varchar(32)  NOT NULL COMMENT '内仓编号',
  `shelves_id` varchar(32)  NOT NULL COMMENT '货架编号',
  `state` tinyint(4) NOT NULL COMMENT '库位状态（0：空闲、1：占用、2：入库锁定、3：冻结）',
  `column_num` varchar(32)  NULL DEFAULT NULL COMMENT '货架列数',
  `row_num` varchar(32)  NOT NULL COMMENT '货架层数',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库位所在货架的层数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_storage_bin_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `basic_storage_bin_tag_relation`;
CREATE TABLE `basic_storage_bin_tag_relation`  (
  `tag_id` varchar(32)  NOT NULL COMMENT '标签id',
  `bin_id` varchar(32)  NOT NULL COMMENT '关联表id：内仓/货架/库位/成品/物料'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_tag
-- ----------------------------
DROP TABLE IF EXISTS `basic_tag`;
CREATE TABLE `basic_tag`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(32)  NOT NULL COMMENT '标签名称',
  `value` varchar(32)  NOT NULL COMMENT '标签值',
  `priority` tinyint(4) NULL DEFAULT NULL COMMENT '优先级',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_unit
-- ----------------------------
DROP TABLE IF EXISTS `basic_unit`;
CREATE TABLE `basic_unit`  (
  `id` varchar(32)  NOT NULL,
  `description` varchar(255)  NULL DEFAULT NULL COMMENT '单位描述',
  `name` varchar(64)  NOT NULL COMMENT '名称',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除(0: 未删除, 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '是否删除(0: 未删除, 1: 已删除)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `basic_warehouse`;
CREATE TABLE `basic_warehouse`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(64)  NOT NULL COMMENT '名称',
  `area` varchar(64)  NOT NULL COMMENT '面积',
  `address` varchar(128)  NOT NULL COMMENT '地址',
  `type` tinyint(4) NOT NULL COMMENT '内仓类型',
  `longitude` double NULL DEFAULT NULL COMMENT '经度',
  `latitude` double NULL DEFAULT NULL COMMENT '纬度',
  `is_full` tinyint(4) NOT NULL COMMENT '是否满仓',
  `is_open` tinyint(4) NOT NULL COMMENT '是否启用',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_warehouse_organization_relation
-- ----------------------------
DROP TABLE IF EXISTS `basic_warehouse_organization_relation`;
CREATE TABLE `basic_warehouse_organization_relation`  (
  `fa_warehouse_id` varchar(32)  NOT NULL COMMENT '内仓编号',
  `org_id` varchar(32)  NOT NULL COMMENT '组织编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_warehouse_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `basic_warehouse_user_relation`;
CREATE TABLE `basic_warehouse_user_relation`  (
  `warehouse_id` varchar(32)  NOT NULL COMMENT '内仓编号',
  `user_id` varchar(32)  NOT NULL COMMENT '用户编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_workshop
-- ----------------------------
DROP TABLE IF EXISTS `basic_workshop`;
CREATE TABLE `basic_workshop`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(32)  NOT NULL COMMENT '车间名称',
  `address` varchar(128)  NULL DEFAULT NULL COMMENT '车间地址',
  `manager` varchar(32)  NOT NULL COMMENT '负责人',
  `phone` varchar(32)  NULL DEFAULT NULL COMMENT '联系方式',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_workshop_material_relation
-- ----------------------------
DROP TABLE IF EXISTS `basic_workshop_material_relation`;
CREATE TABLE `basic_workshop_material_relation`  (
  `workshop_id` varchar(32)  NULL DEFAULT NULL COMMENT '车间id',
  `material_id` varchar(32)  NULL DEFAULT NULL COMMENT '物料id',
  `num` double NULL DEFAULT NULL COMMENT '库存量'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog`  (
  `ID` varchar(255)  NOT NULL,
  `AUTHOR` varchar(255)  NOT NULL,
  `FILENAME` varchar(255)  NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10)  NOT NULL,
  `MD5SUM` varchar(35)  NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255)  NULL DEFAULT NULL,
  `COMMENTS` varchar(255)  NULL DEFAULT NULL,
  `TAG` varchar(255)  NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20)  NULL DEFAULT NULL,
  `CONTEXTS` varchar(255)  NULL DEFAULT NULL,
  `LABELS` varchar(255)  NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10)  NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_car
-- ----------------------------
DROP TABLE IF EXISTS `fa_car`;
CREATE TABLE `fa_car`  (
  `id` varchar(32)  NOT NULL,
  `car_no` varchar(32)  NOT NULL COMMENT '车牌号',
  `driver_name` varchar(32)  NOT NULL COMMENT '司机名称',
  `driver_phone` varchar(32)  NOT NULL COMMENT '司机手机',
  `type` int(11) NOT NULL COMMENT '车辆类型',
  `is_open` int(11) NOT NULL COMMENT '是否停用',
  `in_use` int(11) NOT NULL COMMENT '是否使用',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_car_schedule
-- ----------------------------
DROP TABLE IF EXISTS `fa_car_schedule`;
CREATE TABLE `fa_car_schedule`  (
  `id` varchar(32)  NOT NULL,
  `car_id` varchar(32)  NOT NULL COMMENT '车辆id',
  `out_product_order_id` varchar(32)  NULL DEFAULT NULL COMMENT '出货单id',
  `start_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_discharge
-- ----------------------------
DROP TABLE IF EXISTS `fa_discharge`;
CREATE TABLE `fa_discharge`  (
  `id` varchar(32)  NOT NULL,
  `send_order_id` varchar(32)  NOT NULL COMMENT '送货单id',
  `material_id` varchar(32)  NOT NULL COMMENT '物料id',
  `warehouse_id` varchar(32)  NOT NULL COMMENT '仓库id',
  `num` varchar(32)  NOT NULL COMMENT '卸货数量',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_factory_info
-- ----------------------------
DROP TABLE IF EXISTS `fa_factory_info`;
CREATE TABLE `fa_factory_info`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(64)  NOT NULL COMMENT '厂区名称',
  `address` varchar(128)  NULL DEFAULT NULL COMMENT '厂区地址',
  `longitude` double NULL DEFAULT NULL COMMENT '经度',
  `latitude` double NULL DEFAULT NULL COMMENT '纬度',
  `img_path` varchar(128)  NULL DEFAULT NULL COMMENT '图片路径',
  `description` varchar(128)  NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '厂区信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_good_taken_image
-- ----------------------------
DROP TABLE IF EXISTS `fa_good_taken_image`;
CREATE TABLE `fa_good_taken_image`  (
  `id` varchar(32)  NOT NULL,
  `path` varchar(255)  NOT NULL COMMENT '图片路径',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `delivery_id` varchar(32)  NULL DEFAULT NULL COMMENT '清单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '清单id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_recommend_log
-- ----------------------------
DROP TABLE IF EXISTS `fa_recommend_log`;
CREATE TABLE `fa_recommend_log`  (
  `id` varchar(32)  NOT NULL,
  `storehouse_id` varchar(32)  NOT NULL COMMENT '库位id',
  `text` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`storehouse_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_registration
-- ----------------------------
DROP TABLE IF EXISTS `fa_registration`;
CREATE TABLE `fa_registration`  (
  `id` varchar(32)  NOT NULL,
  `car_no` varchar(16)  NOT NULL COMMENT '车牌号',
  `driver_name` varchar(32)  NULL DEFAULT NULL COMMENT '司机名称',
  `driver_phone` varchar(32)  NULL DEFAULT NULL COMMENT '司机手机',
  `car_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '车辆类型(0:物料车辆,1:成品车辆)',
  `enter_time` datetime(0) NULL DEFAULT NULL COMMENT '入园时间',
  `exit_time` datetime(0) NULL DEFAULT NULL COMMENT '出园时间',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL COMMENT '是否删除',
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '车辆状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_car_type`(`car_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '车辆状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_truckstate
-- ----------------------------
DROP TABLE IF EXISTS `fa_truckstate`;
CREATE TABLE `fa_truckstate`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(32)  NOT NULL COMMENT '状态名称',
  `registration_id` varchar(32)  NOT NULL COMMENT '登记表id',
  `status` tinyint(4) NOT NULL COMMENT '登记车辆状态(0:已登记; 1:等待进园; 2:已进园; 3:已进厂; 4:已出场; 5:已出园)',
  `warehouse_id` varchar(32)  NULL DEFAULT NULL COMMENT '仓库id',
  `time` datetime(0) NOT NULL COMMENT '时间',
  `car_no` varchar(32)  NOT NULL COMMENT '车牌号',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pm_inspection_record
-- ----------------------------
DROP TABLE IF EXISTS `pm_inspection_record`;
CREATE TABLE `pm_inspection_record`  (
  `id` varchar(32)  NOT NULL,
  `pass` int(11) NOT NULL COMMENT '是否通过',
  `examiner` varchar(32)  NOT NULL COMMENT '校验人',
  `url` varchar(128)  NOT NULL COMMENT '图片地址',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `record_id` varchar(32)  NOT NULL COMMENT '生产记录id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '生产记录id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pm_material_requisition
-- ----------------------------
DROP TABLE IF EXISTS `pm_material_requisition`;
CREATE TABLE `pm_material_requisition`  (
  `id` varchar(32)  NOT NULL,
  `code` varchar(64)  NOT NULL COMMENT '领料单号',
  `description` varchar(32)  NULL DEFAULT NULL COMMENT '备注',
  `is_compile` varchar(32)  NULL DEFAULT NULL COMMENT '领料是否完成',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `workshop_id` varchar(32)  NULL DEFAULT NULL COMMENT '车间id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '领料单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pm_material_requisition_detail
-- ----------------------------
DROP TABLE IF EXISTS `pm_material_requisition_detail`;
CREATE TABLE `pm_material_requisition_detail`  (
  `id` varchar(32)  NOT NULL,
  `requisition_id` varchar(64)  NOT NULL COMMENT '领料单id',
  `material_id` varchar(32)  NULL DEFAULT NULL COMMENT '物料id',
  `num` varchar(64)  NULL DEFAULT NULL COMMENT '所需物料数',
  `batch` varchar(32)  NULL DEFAULT NULL COMMENT '批次号',
  `get_time` timestamp(0) NULL DEFAULT NULL COMMENT '领取时间',
  `warehouse_id` varchar(32)  NULL DEFAULT NULL COMMENT '内仓id',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '领料单项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pm_production_plan
-- ----------------------------
DROP TABLE IF EXISTS `pm_production_plan`;
CREATE TABLE `pm_production_plan`  (
  `id` varchar(32)  NOT NULL,
  `product_id` varchar(32)  NULL DEFAULT NULL COMMENT '成品id',
  `num` int(11) NULL DEFAULT NULL COMMENT '生产数量',
  `production_line_id` varchar(32)  NULL DEFAULT NULL COMMENT '产线id',
  `start_date` timestamp(0) NULL DEFAULT NULL COMMENT '计划开始时间',
  `end_date` timestamp(0) NULL DEFAULT NULL COMMENT '计划结束时间',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `code` varchar(64)  NULL DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '生产状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pm_production_record
-- ----------------------------
DROP TABLE IF EXISTS `pm_production_record`;
CREATE TABLE `pm_production_record`  (
  `id` varchar(32)  NOT NULL,
  `product_id` varchar(32)  NOT NULL COMMENT '产品id',
  `num` double NOT NULL COMMENT '数量',
  `batch` varchar(64)  NOT NULL COMMENT '批次号',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '检测状态',
  `production_date` timestamp(0) NULL DEFAULT NULL COMMENT '生产时间',
  `plan_id` varchar(32)  NULL DEFAULT NULL COMMENT '生产计划编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '生产时间' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for relation_storage_bin_material
-- ----------------------------
DROP TABLE IF EXISTS `relation_storage_bin_material`;
CREATE TABLE `relation_storage_bin_material`  (
  `id` varchar(32)  NOT NULL,
  `storage_bin_id` varchar(32)  NOT NULL COMMENT '库位编号',
  `material_id` varchar(32)  NOT NULL COMMENT '物料编号',
  `num` double NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = ' ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for relation_storage_bin_product
-- ----------------------------
DROP TABLE IF EXISTS `relation_storage_bin_product`;
CREATE TABLE `relation_storage_bin_product`  (
  `id` varchar(32)  NOT NULL,
  `storage_bin_id` varchar(32)  NOT NULL COMMENT '库位编号',
  `product_id` varchar(32)  NOT NULL COMMENT '成品编号',
  `num` double NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = ' ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_delivery_plan
-- ----------------------------
DROP TABLE IF EXISTS `sm_delivery_plan`;
CREATE TABLE `sm_delivery_plan`  (
  `id` varchar(32)  NOT NULL,
  `sale_id` varchar(32)  NOT NULL COMMENT '销售订单id',
  `is_compile_plan` tinyint(4) NOT NULL DEFAULT 0 COMMENT '当前计划是否完成(0:未完成,1:已完成)',
  `description` varchar(128)  NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `delivery_date` date NULL DEFAULT NULL COMMENT '交付时间',
  `name` varchar(64)  NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_sale_id`(`sale_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '名称' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_delivery_plan_details
-- ----------------------------
DROP TABLE IF EXISTS `sm_delivery_plan_details`;
CREATE TABLE `sm_delivery_plan_details`  (
  `id` varchar(32)  NOT NULL,
  `delivery_plan_id` varchar(32)  NOT NULL COMMENT '交付计划id',
  `product_id` varchar(32)  NOT NULL COMMENT '成品id',
  `num` double NOT NULL DEFAULT 0 COMMENT '数量',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_product_delivery_order
-- ----------------------------
DROP TABLE IF EXISTS `sm_product_delivery_order`;
CREATE TABLE `sm_product_delivery_order`  (
  `id` varchar(32)  NOT NULL,
  `order_no` varchar(32)  NULL DEFAULT NULL COMMENT '出货单号',
  `out_date` date NULL DEFAULT NULL COMMENT '出货时间',
  `address` varchar(128)  NULL DEFAULT NULL COMMENT '出货地址',
  `is_compile` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否完成(0:未完成,1:已完成)',
  `description` varchar(128)  NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `sale_id` varchar(32)  NOT NULL COMMENT '销售单id',
  `receiver` varchar(32)  NULL DEFAULT NULL COMMENT '收货人',
  `car_id` varchar(32)  NOT NULL COMMENT '车辆id',
  `out_operator` varchar(32)  NULL DEFAULT NULL COMMENT '出货人',
  `receiver_phone` varchar(32)  NULL DEFAULT NULL COMMENT '收货人联系方式'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_product_delivery_order_details
-- ----------------------------
DROP TABLE IF EXISTS `sm_product_delivery_order_details`;
CREATE TABLE `sm_product_delivery_order_details`  (
  `id` varchar(32)  NOT NULL,
  `out_product_order_id` varchar(32)  NOT NULL COMMENT '出货单id',
  `product_id` varchar(32)  NOT NULL COMMENT '成品id',
  `num` double NOT NULL DEFAULT 0 COMMENT '数量',
  `warehouse_id` varchar(32)  NULL DEFAULT NULL COMMENT '仓库id',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_product_delivery_requisition
-- ----------------------------
DROP TABLE IF EXISTS `sm_product_delivery_requisition`;
CREATE TABLE `sm_product_delivery_requisition`  (
  `id` varchar(32)  NOT NULL,
  `order_id` varchar(32)  NOT NULL COMMENT '销售单编号' ,
  `out_date` timestamp(0) NULL DEFAULT NULL COMMENT '出货时间',
  `address` varchar(255)  NULL DEFAULT NULL COMMENT '出货地址',
  `is_compile` bit(1) NULL DEFAULT NULL COMMENT '是否完成(0:未完成,1:已完成,2:创建中)',
  `out_operator` varchar(32)  NULL DEFAULT NULL COMMENT '出货人',
  `receiver` varchar(32)  NULL DEFAULT NULL COMMENT '收货人',
  `receiver_phone` varchar(32)  NULL DEFAULT NULL COMMENT '收货人联系方式',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `car_schedule_id` varchar(32)  NULL DEFAULT NULL COMMENT '车辆排班id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_product_delivery_requisition_details
-- ----------------------------
DROP TABLE IF EXISTS `sm_product_delivery_requisition_details`;
CREATE TABLE `sm_product_delivery_requisition_details`  (
  `id` varchar(32)  NOT NULL,
  `requistion_id` varchar(32)  NOT NULL COMMENT '申请单',
  `product_id` varchar(32)  NULL DEFAULT NULL COMMENT '成品id',
  `num` double NULL DEFAULT NULL COMMENT '数量',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_product_delivery_requisition_warehouse_relation
-- ----------------------------
DROP TABLE IF EXISTS `sm_product_delivery_requisition_warehouse_relation`;
CREATE TABLE `sm_product_delivery_requisition_warehouse_relation`  (
  `requisition_id` varchar(32)  NOT NULL COMMENT '申请单编号',
  `warehouse_id` varchar(32)  NOT NULL COMMENT '内仓编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_sale
-- ----------------------------
DROP TABLE IF EXISTS `sm_sale`;
CREATE TABLE `sm_sale`  (
  `id` varchar(32)  NOT NULL,
  `order_no` varchar(32)  NULL DEFAULT NULL COMMENT '订单编号',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '签单时间',
  `seller` varchar(64)  NULL DEFAULT NULL COMMENT '签单人',
  `seller_phone` varchar(32)  NULL DEFAULT NULL COMMENT '签单人手机',
  `amount` double NULL DEFAULT NULL COMMENT '金额',
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '订单状态',
  `expect_date` date NULL DEFAULT NULL COMMENT '期望到货时间',
  `address` varchar(128)  NULL DEFAULT NULL COMMENT '出货地址',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sm_sale_details
-- ----------------------------
DROP TABLE IF EXISTS `sm_sale_details`;
CREATE TABLE `sm_sale_details`  (
  `id` varchar(32)  NOT NULL,
  `sale_id` varchar(32)  NOT NULL COMMENT '销售订单id',
  `product_id` varchar(32)  NOT NULL COMMENT '成品id',
  `num` double NOT NULL DEFAULT 0 COMMENT '数量',
  `unit` varchar(32)  NOT NULL COMMENT '单位',
  `delivery_num` double NOT NULL DEFAULT 0 COMMENT '已交付数量',
  `is_compile_product` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否完成交付(0:未完成,1:已完成)',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_product_id`(`product_id`) USING BTREE,
  INDEX `index_sale_id`(`sale_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售订单详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_invoice
-- ----------------------------
DROP TABLE IF EXISTS `sup_invoice`;
CREATE TABLE `sup_invoice`  (
  `id` varchar(32)  NOT NULL,
  `code` varchar(32)  NULL DEFAULT NULL COMMENT '送货单编号',
  `send_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '送货日期',
  `send_address` varchar(128)  NOT NULL COMMENT '送货地址',
  `send_type` int(11) NOT NULL COMMENT '送货类型',
  `expect_arrive_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '期望送达时间',
  `note` varchar(128)  NULL DEFAULT NULL COMMENT '备注',
  `car_no` varchar(32)  NOT NULL COMMENT '车牌号',
  `state` int(11) NOT NULL COMMENT '订单状态： No_Finished(\"未录入\", (byte) 0),\r\n     No_Delivery(\"未送货\", (byte) 1),\r\n     In_Transit(\"在途中\", (byte) 2),\r\n     Registered(\"已登记\", (byte) 3),\r\n     Waitfor_Park(\"等待进园\", (byte) 4),\r\n     Entering_Park(\"正在进园\", (byte) 5),\r\n     In_Park(\"园区中\", (byte) 6),\r\n     Enter_Storage(\"已进厂\", (byte) 7),\r\n     Outerter_Storage(\"已出厂\", (byte) 8),\r\n     Leaveing_Park(\"正在出园\", (byte) 9),\r\n     Leave_Park(\"已出园\", (byte) 10),\r\n     Completed(\"已完成\", (byte) 11),\r\n     Cancel(\"已撤销\", (byte) 12),',
  `car_type` int(11) NULL DEFAULT NULL COMMENT '车辆类型',
  `driver_name` varchar(32)  NOT NULL COMMENT '司机姓名',
  `driver_phone` varchar(11)  NOT NULL COMMENT '司机手机',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `supplier_id` varchar(32)  NULL DEFAULT NULL,
  `finish_time` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00' COMMENT '完成时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_invoice_delivery_info
-- ----------------------------
DROP TABLE IF EXISTS `sup_invoice_delivery_info`;
CREATE TABLE `sup_invoice_delivery_info`  (
  `id` varchar(32)  NOT NULL,
  `invoice_id` varchar(32)  NULL DEFAULT NULL COMMENT '送货单id',
  `warehouse_id` varchar(32)  NULL DEFAULT NULL COMMENT '内仓id',
  `registration_id` varchar(32)  NULL DEFAULT NULL COMMENT '登记表id',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态：UNREGISTER(\"未登记\", (byte) 2),// 非正常卸货送货单更新成未登记状态',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enter_time` timestamp(0) NULL DEFAULT NULL COMMENT '进厂时间',
  `exit_time` timestamp(0) NULL DEFAULT NULL COMMENT '出厂时间',
  `registration_time` timestamp(0) NULL DEFAULT NULL COMMENT '登记时间',
  `note` varchar(32)  NULL DEFAULT NULL COMMENT '备注',
  `good_taken_info` varchar(128)  NULL DEFAULT NULL COMMENT '带货信息'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_invoice_delivery_info_details
-- ----------------------------
DROP TABLE IF EXISTS `sup_invoice_delivery_info_details`;
CREATE TABLE `sup_invoice_delivery_info_details`  (
  `id` varchar(32)  NOT NULL,
  `material_id` varchar(32)  NOT NULL COMMENT '物料id',
  `num` double NULL DEFAULT NULL COMMENT '数量',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `receive_num` double NOT NULL DEFAULT 0 COMMENT '实收数量',
  `delivery_info_id` varchar(32)  NULL DEFAULT NULL COMMENT '送货清单id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_invoice_delivery_info_status_log
-- ----------------------------
DROP TABLE IF EXISTS `sup_invoice_delivery_info_status_log`;
CREATE TABLE `sup_invoice_delivery_info_status_log`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(32)  NOT NULL COMMENT '状态名称',
  `status` int(11) NOT NULL COMMENT '状态',
  `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间',
  `delivery_info_id` varchar(32)  NULL DEFAULT NULL COMMENT '送货清单id',
  `warehouse_id` varchar(32)  NULL DEFAULT NULL COMMENT '内仓id',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `car_no` varchar(64)  NOT NULL COMMENT '车牌'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_material_transport_plan
-- ----------------------------
DROP TABLE IF EXISTS `sup_material_transport_plan`;
CREATE TABLE `sup_material_transport_plan`  (
  `id` varchar(32)  NOT NULL,
  `fa_warehouse_id` varchar(32)  NOT NULL COMMENT '内仓id',
  `material_id` varchar(32)  NOT NULL COMMENT '物料id',
  `num` double NOT NULL COMMENT '数量',
  `supplier_id` varchar(32)  NULL DEFAULT NULL COMMENT '供应商编号' ,
  `state` int(11) NOT NULL COMMENT '状态',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `require_time` datetime(0) NOT NULL COMMENT '需求时间',
  `source` tinyint(4) NULL DEFAULT 0 COMMENT '来源(0:自动生成1:新增操作)',
  `finished_num` double NULL DEFAULT NULL COMMENT '已完成数量'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_material_transport_plan_duplicate
-- ----------------------------
DROP TABLE IF EXISTS `sup_material_transport_plan_duplicate`;
CREATE TABLE `sup_material_transport_plan_duplicate`  (
  `id` varchar(32)  NOT NULL,
  `fa_warehouse_id` varchar(32)  NOT NULL COMMENT '内仓id',
  `material_id` varchar(32)  NOT NULL COMMENT '物料id',
  `num` double NOT NULL COMMENT '数量',
  `supplier_id` varchar(32)  NULL DEFAULT NULL COMMENT '供应商编号',
  `state` int(11) NOT NULL COMMENT '状态',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `require_time` datetime(0) NOT NULL COMMENT '需求时间',
  `source` tinyint(4) NULL DEFAULT 0 COMMENT '来源(0:自动生成1:新增操作)',
  `finished_num` double NULL DEFAULT NULL COMMENT '已完成数量'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_material_transport_plan_invoice_relation
-- ----------------------------
DROP TABLE IF EXISTS `sup_material_transport_plan_invoice_relation`;
CREATE TABLE `sup_material_transport_plan_invoice_relation`  (
  `material_transport_plan_id` varchar(32)  NOT NULL COMMENT '物料调货计划id',
  `invoice_id` varchar(32)  NOT NULL COMMENT '送货单id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_supplier
-- ----------------------------
DROP TABLE IF EXISTS `sup_supplier`;
CREATE TABLE `sup_supplier`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(32)  NOT NULL COMMENT '名字',
  `address` varchar(128)  NOT NULL COMMENT '地址',
  `description` varchar(64)  NULL DEFAULT NULL COMMENT '描述',
  `phone` varchar(11)  NOT NULL COMMENT '手机号',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(32)  NULL DEFAULT NULL COMMENT '供应商编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商编码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_supplier_external_warehouse_relation
-- ----------------------------
DROP TABLE IF EXISTS `sup_supplier_external_warehouse_relation`;
CREATE TABLE `sup_supplier_external_warehouse_relation`  (
  `supplier_id` varchar(32)  NULL DEFAULT NULL COMMENT '供应商id',
  `warehouse_id` varchar(32)  NOT NULL COMMENT '外仓id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_supplier_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `sup_supplier_warehouse`;
CREATE TABLE `sup_supplier_warehouse`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(32)  NOT NULL COMMENT '名字',
  `address` varchar(128)  NOT NULL COMMENT '地址',
  `description` varchar(64)  NULL DEFAULT NULL COMMENT '描述',
  `phone` varchar(11)  NOT NULL COMMENT '手机号',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sup_supplier_warehouse_relation
-- ----------------------------
DROP TABLE IF EXISTS `sup_supplier_warehouse_relation`;
CREATE TABLE `sup_supplier_warehouse_relation`  (
  `id` varchar(32)  NOT NULL,
  `org_id` varchar(32)  NULL DEFAULT NULL COMMENT '供应商id',
  `warehouse_id` varchar(32)  NOT NULL COMMENT '外仓id',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(32)  NOT NULL,
  `dict_key` varchar(64)  NOT NULL,
  `dict_value` varchar(64)  NOT NULL,
  `dict_type_id` varchar(32)  NOT NULL,
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '名称' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(64)  NOT NULL  COMMENT '名称',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(64)  NOT NULL COMMENT '组织名称',
  `type` int(11) NOT NULL COMMENT '组织类型',
  `lft` int(11) NULL DEFAULT NULL COMMENT '左子树',
  `rgt` int(11) NULL DEFAULT NULL COMMENT '右子树',
  `level` int(11) NULL DEFAULT NULL COMMENT '等级',
  `root_id` int(11) NULL DEFAULT NULL COMMENT '根节点编号',
  `address` varchar(32)  NULL DEFAULT NULL COMMENT '组织地址',
  `phone` varchar(32)  NULL DEFAULT NULL COMMENT '电话',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `supplier_code` varchar(32)  NULL DEFAULT NULL COMMENT '供应商编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_organization_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization_role`;
CREATE TABLE `sys_organization_role`  (
  `id` varchar(32)  NOT NULL,
  `organization_id` varchar(32)  NOT NULL COMMENT '组织编号',
  `role_id` varchar(32)  NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` varchar(32)  NOT NULL,
  `name` varchar(64)  NOT NULL COMMENT '资源名',
  `type` int(11) NOT NULL COMMENT '权限类型',
  `url` varchar(128)  NULL DEFAULT NULL COMMENT '资源地址',
  `permission` varchar(32)  NULL DEFAULT NULL COMMENT '权限code',
  `level` int(11) NULL DEFAULT NULL COMMENT '资源级别',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent_id` varchar(32)  NULL DEFAULT NULL COMMENT '父级id',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `icon` varchar(32)  NULL DEFAULT NULL COMMENT '图标',
  `title` varchar(32)  NULL DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32)  NOT NULL COMMENT '编号',
  `name` varchar(64)  NOT NULL COMMENT '角色名称',
  `description` varchar(128)  NOT NULL COMMENT '描述',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `role_id` varchar(32)  NOT NULL COMMENT '角色编号',
  `resource_id` varchar(32)  NOT NULL COMMENT '资源编号'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_storage_bin_recommend
-- ----------------------------
DROP TABLE IF EXISTS `sys_storage_bin_recommend`;
CREATE TABLE `sys_storage_bin_recommend`  (
  `id` varchar(32)  NOT NULL,
  `warehouse_id` varchar(32)  NOT NULL COMMENT '内仓id',
  `tag_order` int(11) NULL DEFAULT NULL COMMENT '标签权重',
  `entrance_order` int(11) NULL DEFAULT NULL COMMENT '距离入口权重',
  `exit_order` int(11) NULL DEFAULT NULL COMMENT '距离出口权重',
  `distance_order` int(11) NULL DEFAULT NULL COMMENT '距离权重',
  `entrance_value` int(11) NULL DEFAULT NULL COMMENT '距离入口值',
  `exit_value` int(11) NULL DEFAULT NULL COMMENT '距离出口值',
  `distance_value` int(11) NULL DEFAULT NULL COMMENT '距离值',
  `is_bin_state` tinyint(4) NOT NULL COMMENT '库位状态',
  `bin_lock_time` int(11) NULL DEFAULT NULL COMMENT '库位锁定时间',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32)  NOT NULL COMMENT '编号',
  `username` varchar(64)  NOT NULL COMMENT '用户名',
  `real_name` varchar(64)  NOT NULL COMMENT '真实姓名',
  `password` varchar(60)  NOT NULL COMMENT '密码',
  `email` varchar(32)  NULL DEFAULT NULL COMMENT '邮件',
  `phone` varchar(255)  NULL DEFAULT NULL COMMENT '电话',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型\r\n0： 超管\r\n1： 普通用户\r\n2： 供应商',
  `address` varchar(255)  NULL DEFAULT NULL COMMENT '地址',
  `image_path` varchar(255)  NULL DEFAULT NULL COMMENT '图片地址',
  `enable` tinyint(1) NOT NULL COMMENT '是否禁用',
  `org_id` varchar(32)  NULL DEFAULT NULL COMMENT '组织编号',
  `last_login` timestamp(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` varchar(32)  NULL DEFAULT NULL COMMENT '供应商编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(32)  NOT NULL COMMENT '用户编号',
  `role_id` varchar(32)  NOT NULL COMMENT '角色编号'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_material_inbound_order
-- ----------------------------
DROP TABLE IF EXISTS `wm_material_inbound_order`;
CREATE TABLE `wm_material_inbound_order`  (
  `id` varchar(32)  NOT NULL,
  `code` varchar(32)  NOT NULL COMMENT '入库单号',
  `invoice_id` varchar(32)  NULL DEFAULT NULL COMMENT '送货单id',
  `in_operator` varchar(32)  NOT NULL COMMENT '入库人',
  `delivery_man` varchar(32)  NOT NULL COMMENT '送货人',
  `in_time` timestamp(0) NULL DEFAULT NULL COMMENT '入库时间',
  `is_finished` int(11) NOT NULL COMMENT '是否完成入库：0未完成 1完成',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `warehouse_id` varchar(64)  NOT NULL COMMENT '内仓id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_material_inbound_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `wm_material_inbound_order_detail`;
CREATE TABLE `wm_material_inbound_order_detail`  (
  `id` varchar(32)  NOT NULL,
  `order_id` varchar(32)  NOT NULL COMMENT '物料入库单id',
  `material_id` varchar(32)  NOT NULL COMMENT '物料id',
  `bin_id` varchar(32)  NOT NULL COMMENT '库位id',
  `batch` varchar(64)  NULL DEFAULT NULL COMMENT '批次',
  `num` double NOT NULL COMMENT '数量',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_material_order
-- ----------------------------
DROP TABLE IF EXISTS `wm_material_order`;
CREATE TABLE `wm_material_order`  (
  `id` varchar(32)  NOT NULL,
  `material_id` varchar(32)  NOT NULL COMMENT '物料id',
  `unit` varchar(32)  NULL DEFAULT NULL COMMENT '单元',
  `num` int(11) NOT NULL COMMENT '订购总数量',
  `delivery_num` int(11) NULL DEFAULT NULL COMMENT '交付数量',
  `delivery_date` date NULL DEFAULT NULL COMMENT '交付日期',
  `supplier_id` varchar(32)  NULL DEFAULT NULL COMMENT '供应商id',
  `amount` double NULL DEFAULT NULL COMMENT '金额',
  `document_date` date NULL DEFAULT NULL COMMENT '文档日期',
  `order_no` varchar(32)  NULL DEFAULT NULL COMMENT '订单号',
  `note` varchar(64)  NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_material_outbound_order
-- ----------------------------
DROP TABLE IF EXISTS `wm_material_outbound_order`;
CREATE TABLE `wm_material_outbound_order`  (
  `id` varchar(32)  NOT NULL,
  `out_operator` varchar(32)  NOT NULL COMMENT '出库人',
  `out_time` timestamp(0) NULL DEFAULT NULL COMMENT '出库时间',
  `is_finished` int(11) NOT NULL COMMENT '是否完成出库：0未完成 1完成',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `warehouse_id` varchar(32)  NOT NULL COMMENT '内仓id',
  `code` varchar(32)  NOT NULL COMMENT '出库单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '内仓id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_material_outbound_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `wm_material_outbound_order_detail`;
CREATE TABLE `wm_material_outbound_order_detail`  (
  `id` varchar(32)  NOT NULL,
  `order_id` varchar(32)  NOT NULL COMMENT '出库单id',
  `material_id` varchar(32)  NOT NULL COMMENT '物料id',
  `num` int(11) NOT NULL COMMENT '数量',
  `bin_id` varchar(32)  NOT NULL COMMENT '库位id',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_material_require_plan
-- ----------------------------
DROP TABLE IF EXISTS `wm_material_require_plan`;
CREATE TABLE `wm_material_require_plan`  (
  `id` varchar(32)  NOT NULL,
  `state` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态(0:未完成,1:已完成)',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `warehouse_id` varchar(32)  NOT NULL COMMENT '内仓id',
  `material_id` varchar(32)  NOT NULL COMMENT '物料id',
  `num` double NOT NULL COMMENT '数目',
  `out_num` double NULL DEFAULT 0 COMMENT '已出库数量',
  `require_time` datetime(0) NOT NULL COMMENT '需求时间',
  `workshop_id` varchar(32)  NULL DEFAULT NULL COMMENT '车间id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '已出库数量' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_material_require_plan_material_requisition_relation
-- ----------------------------
DROP TABLE IF EXISTS `wm_material_require_plan_material_requisition_relation`;
CREATE TABLE `wm_material_require_plan_material_requisition_relation`  (
  `material_require_plan_id` varchar(32)  NOT NULL COMMENT '物料需求计划id',
  `material_requisition_id` varchar(32)  NOT NULL COMMENT '领料单id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_material_require_plan_production_plan_relation
-- ----------------------------
DROP TABLE IF EXISTS `wm_material_require_plan_production_plan_relation`;
CREATE TABLE `wm_material_require_plan_production_plan_relation`  (
  `material_require_plan_id` varchar(32)  NOT NULL COMMENT '物料需求计划id',
  `production_plan_id` varchar(32)  NULL DEFAULT NULL  COMMENT '生产计划id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_product_inbound_order
-- ----------------------------
DROP TABLE IF EXISTS `wm_product_inbound_order`;
CREATE TABLE `wm_product_inbound_order`  (
  `id` varchar(32)  NOT NULL,
  `in_operator` varchar(32)  NOT NULL COMMENT '入库人',
  `delivery_man` varchar(32)  NOT NULL COMMENT '送货人',
  `in_time` timestamp(0) NULL DEFAULT NULL COMMENT '入库时间',
  `is_finished` int(11) NOT NULL COMMENT '是否完成入库：0未完成 1完成',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(32)  NOT NULL COMMENT '入库编号',
  `warehouse_id` varchar(64)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '入库类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_product_inbound_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `wm_product_inbound_order_detail`;
CREATE TABLE `wm_product_inbound_order_detail`  (
  `id` varchar(32)  NOT NULL,
  `order_id` varchar(32)  NOT NULL,
  `product_id` varchar(32)  NOT NULL COMMENT '成品id',
  `num` int(11) NOT NULL COMMENT '数量',
  `bin_id` varchar(32)  NOT NULL COMMENT '库位id',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_product_outbound_order
-- ----------------------------
DROP TABLE IF EXISTS `wm_product_outbound_order`;
CREATE TABLE `wm_product_outbound_order`  (
  `id` varchar(32)  NOT NULL,
  `product_out_order_id` varchar(32)  NOT NULL COMMENT '出货单id',
  `out_operator` varchar(32)  NOT NULL COMMENT '出库人',
  `receiver` varchar(32)  NOT NULL COMMENT '收货人',
  `out_time` timestamp(0) NULL DEFAULT NULL COMMENT '出库时间',
  `is_finished` int(11) NOT NULL COMMENT '是否完成出库：0未完成 1完成',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `warehouse_id` varchar(32)  NOT NULL COMMENT '内仓id',
  `code` varchar(32)  NOT NULL COMMENT '出库单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '内仓id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wm_product_outbound_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `wm_product_outbound_order_detail`;
CREATE TABLE `wm_product_outbound_order_detail`  (
  `id` varchar(32)  NOT NULL,
  `product_outbound_order_id` varchar(32)  NOT NULL,
  `product_id` varchar(32)  NOT NULL COMMENT '成品id',
  `num` int(11) NOT NULL COMMENT '数量',
  `bin_id` varchar(32)  NOT NULL COMMENT '库位id',
  `creator` varchar(32)  NULL DEFAULT NULL COMMENT '创建者',
  `operator` varchar(32)  NULL DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`
(
    `id`              varchar(32) NOT NULL COMMENT '编号',
    `frozen_require`  int         NOT NULL COMMENT '需求冻结时间',
    `frozen_transfer` int         NOT NULL COMMENT '调货计划冻结时间',
    `address`         varchar(255) DEFAULT NULL COMMENT '厂商地址',
    `location`        varchar(128) DEFAULT NULL COMMENT '定位',
    `creator`         varchar(32)  DEFAULT NULL COMMENT '创建人',
    `operator`        varchar(32)  DEFAULT NULL COMMENT '修改人',
    `create_time`     timestamp(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     timestamp(0) DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统参数设置表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

