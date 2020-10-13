package com.nuist.projectManagement.common;

import lombok.Getter;

@Getter
public enum BaseErrorCode {
    /**
     * base：0开头
     */
    AUTHENTICATE_FAILURE("-1", "Authenticate failure"),
    INVALID_PARAMETERS("00001", "Invalid Parameters"),
    ITEM_NOT_FOUND("00002", "Item Not Found"),
    ITEM_REPEAT("00003", "Item Repeat"),
    STATUS_NOT_MATCH("00004", "status not match"),
    ITEM_IS_USED("00005", "Item is used"),
    ITEM_NOT_MATCH("00006", "Item not matched"),
    ACCESS_TOKEN_INVALID("00007", "invalid authToken"),
    BEAN_NOT("00008", "对象不存在"),
    NOT_DELETE_IN_USE("00009", "存在已关联的对象，禁止删除"),
    FILE_NOT_EXIST("00011", "文件不存在"),
    UPLOAD_ERROR("00012", "上传文件失败"),
    READ_ERROR("00013", "读取异常！"),
    TYPE_ERROR("00014", "文件格式错误！"),
    ITEM_IS_DELETED("00015", "Item is deleted"),
    /*-----系统管理：1开头-----------*/
    NAME_IS_USED("10001", "名称已存在"),
    KEY_HAVE("10002", "字典键已存在"),
    VALUE_HAVE("10034","字典值已存在"),
    NAME_HAVE("10003", "名称已存在"),
    DICT_HAVE("10004", "字典类型被绑定,请先删除字典表"),
    ORDER_NO_HAVE("10005", "订单号已存在"),
    PASSWORD_ERROR("10006", "用户密码错误"),
    ORDER_DETAILS_HAVE("10007", "请先删除该原材料订单详情"),
    SUPPLIER_NOT_HAVE("10008", "开发商不存在"),
    ORDER_NOT("10009", "订单号不存在"),
    MATERIAL_NOT("10010", "请核查物料号是否正确"),
    TEXT_NOT("10011", "请核查短文本是否输入"),
    DELIVERED_NUM_NOT("10012", "请核查待发货数量是否输入"),
    NUM_NOT("10013", "请核查待发货数量是否输入"),
    DELIVERYDATE_NOT("10014", "交货期日期格式不正确"),
    ARRIVALTIME_NOT("10015", "预计到达日期格式不正确"),
    TEST_NOT("10016", "微检日期格式不正确"),
    EXPECTEDARRIVALTIME_NOT("10017", "预计到达日期格式不正确"),
    EXPIRATION_NOT("10018", "过期日期格式不正确"),
    DOCOUMENT_NOT("10019", "文件日期格式不正确"),
    IDLIST_BLANK("10020", "请勾选序号列表"),
    CAR_NOT("10021","车辆不可使用"),
    INOUTBOUND_RELATED("10022","已存在关联的出入库单，禁止修改或者删除"),
    HAVE_PARENT_ID("10023","已存在子菜单，不能删除"),
    DELIVERY_NUM_OVER_FLOW("10024","出货数量不能大于销售单数量"),
    HAVE_INBOUND_ORDER_MATERIAL("10025","物料已入库，不能删除"),
    DISCHARGE_NUM_LESS("10026","卸货数量小于入库数量，不能修改"),
    SEND_ORDER_HAVE_SENDED("10027","该送货单已发车，无法删除"),
    OUTBOUND_STOREHOUSE("10028","出库数量大于库位数量，无法出库"),
    DATE_PARSE_ERROR("10029","日期格式不正确"),
    INFORMATION_NOT("10030","内容为空"),
    NUMBER_NOT("10031","请输入数字"),
    UNIT_NOT("10032", "请核查单位是否正确"),
    NUM_NOT_ENOUGH("10033","物料库存不足"),
    DELIVERY_HAVE("10035","已拟定交付计划,无法更新"),
    WAREHOUSE_NOT("10036","内仓不存在"),
    WORKSHOP_NOT("10037","车间不存在")
    ;
    private String code;
    private String message;

    BaseErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
