package com.pactera.esc.core.common;

/**
 * @Author zl
 * @Date 2020/07/15 17:24
 */
public enum CallNumberTypeEnum implements BaseEnum {
    WAITING_FOR_ENTERING("等待进厂",  0),
    DISCHARGING("正在卸货",  1),
    EXIT_STORAGE("已离厂",  2),
    ALL_DELIVERIES("全部",  3);

    private Integer code;
    private String msg;

    CallNumberTypeEnum( String msg,Integer code) {
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
}
