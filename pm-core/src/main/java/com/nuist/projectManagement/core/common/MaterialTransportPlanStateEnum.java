package com.pactera.esc.core.common;

/**
 * @Author zl
 * @Date 2020/07/08 16:26
 */
public enum MaterialTransportPlanStateEnum {
    UnConfirmed((byte)0, "未确认"),
    Confirmed((byte)1, "已确认"),
    Cancle((byte)2, "已取消"),
    Processing((byte)3, "处理中"),
    Completed((byte)4, "已完成");
    private Byte code;
    private String msg;

    MaterialTransportPlanStateEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Byte getCode() {
        return this.code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public static String getTruckStateMsg(Integer code){
        String name = "";
        for (MaterialTransportPlanStateEnum truckStateEnum : MaterialTransportPlanStateEnum.values()) {
            if (truckStateEnum.getCode() .equals(code) )
                name = truckStateEnum.getMsg();
        }
        return name;
    }
}
