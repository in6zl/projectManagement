package com.pactera.esc.core.common;

/**
 * 出货单状态
 *
 * @author minhao.cai
 * @date 2020/04/24
 */
public enum OutProductOrderStateEnum implements BaseEnum {
    /**
     * 0:出货中
     */
    CREATING((byte)0, "出货中"),
    /**
     * 1:在途中
     */
    WAY((byte)1, "在途中"),
    /**
     * 2:已完成
     */
    FINISHED((byte)2, "已完成");

    private Byte code;
    private String msg;

    OutProductOrderStateEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code.intValue();
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public Byte getByteCode() {
        return this.code;
    }


}
