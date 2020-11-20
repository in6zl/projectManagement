package com.pactera.esc.core.common;

/**
 * 枚举base
 *
 * @author minhao.cai
 * @date 2020/04/23
 */
public interface BaseEnum {

    Integer getCode();

    String getMsg();

    default Byte getByteCode() {
        return 0;
    }
}
