package com.pactera.esc.core.common;

/**
 * @Author zl
 * @Date 2020/07/17 17:18
 */
public enum DeleteEnum implements BaseEnum {
    /**
     * 已删除
     */
    UN_DELETED(0,"未删除"),
    /**
     * 未删除
     */
    DELETED(1,"已删除"),;

    private Integer code;
    private String msg;

    DeleteEnum(Integer code, String msg){
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
