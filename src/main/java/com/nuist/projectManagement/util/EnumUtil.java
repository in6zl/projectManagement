package com.nuist.projectManagement.util;


import com.nuist.projectManagement.common.BaseEnum;

/**
 * 枚举工具类
 *
 * @author minhao.cai
 * @date 2020/04/23
 */
public class EnumUtil {

    /**
     * 根据code返回msg
     *
     * @param code
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends BaseEnum> String getByCode(Integer code, Class<T> t) {
        for(T item : t.getEnumConstants()){
            if(code.equals(item.getCode())){
                return item.getMsg();
            }
        }
        return null;
    }
}
