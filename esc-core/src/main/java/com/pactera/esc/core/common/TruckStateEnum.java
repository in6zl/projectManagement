package com.pactera.esc.core.common;

/**
 * @Author zl
 * @Date 2020/07/08 16:26
 */
public enum TruckStateEnum implements BaseEnum {
    UnRegister(0, "未登记"),
    Registered(1, "已登记"),
    Waitfor_Park(2, "等待进园"),
    Entering_Park(3, "正在进园"),
    In_Park(4, "园区中"),
    Enter_Storage(5, "已进厂"),
    Outerter_Storage(6, "已出厂"),
    Leaveing_Park(7, "正在出园"),
    Leave_Park(8, "已出园"),
    Cancel(9, "已取消");

    private Integer code;
    private String msg;

    TruckStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    public static String getTruckStateMsg(Integer code){
        String name = "";
        for (TruckStateEnum truckStateEnum : TruckStateEnum.values()) {
            if (truckStateEnum.getCode() .equals(code) )
                name = truckStateEnum.getMsg();
        }
        return name;
    }
}
