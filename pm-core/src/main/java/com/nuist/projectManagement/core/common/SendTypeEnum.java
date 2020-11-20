package com.pactera.esc.core.common;

/**
 * 供应商车辆类型
 *
 * @author minhao.cai
 * @date 2020/04/24
 */
public enum SendTypeEnum implements BaseEnum {
    /**
     * 厢式货车
     */
    VAN(0,"厢式货车"),
    /**
     * 仓栅式货车
     */
    BOX_TRUCK(1,"仓栅式货车"),
    /**
     * 槽罐车
     */
    TANK_CAR(2, "槽罐车");

    private Integer code;
    private String msg;

    SendTypeEnum(Integer code, String msg){
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
