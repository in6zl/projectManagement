package com.pactera.esc.core.common;

/**
 * @Author zl
 * @Date 2020/07/14 15:57
 */
public enum DeliveryStateEnum implements BaseEnum {
    UNREGISTER("未登记",  2),// 非正常卸货送货单更新成未登记状态
    REGISTER("已登记",  3),
    WAITFOR_Park("等待进园",  4),// 叫号后 根据 未登记+车牌号 新增送货单状态表为 等待进园
    In_Park("园区中",  6),
    Enter_Storage("已进厂",  7),// 送货单状态表
    DeliveryInfo_in_Strorage("卸货中", 8), // 送货单状态表
    DeliveryInfo_Out_Strorage("卸货完成", 9),// 送货单状态表
    Outerter_Storage("已出厂",  10), // 送货单状态表
    REFUSE("已拒收",  11), // 送货单状态表
    CANCEL("已撤销",  12); // 送货单状态表

    private Integer code;
    private String msg;

    DeliveryStateEnum( String msg,Integer code) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    public static String getDeliveryStateMsg(Integer code){
        String name = "";
        for (DeliveryStateEnum deliveryStateEnum : DeliveryStateEnum.values()) {
            if (deliveryStateEnum.getCode() .equals(code) )
                name = deliveryStateEnum.getMsg();
        }
        return name;
    }
}
