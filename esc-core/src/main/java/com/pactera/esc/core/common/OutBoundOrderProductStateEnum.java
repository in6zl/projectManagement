package com.pactera.esc.core.common;

/**
 * 出库单状态
 *
 * @author minhao.cai
 * @date 2020/05/13
 */
public enum OutBoundOrderProductStateEnum implements BaseEnum {
    /**
     * 0:未完成
     */
    NO_FINISHED(0, "出库中"),
    /**
     * 1:已完成
     */
    FINISHED(1, "出库完成");

    private Integer code;
    private String msg;

    OutBoundOrderProductStateEnum(Integer code, String msg) {
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
