package com.pactera.esc.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author si.chen
 * @date 2020/5/19 9:36
 */
@Getter
@AllArgsConstructor
public enum OrganizationEnum {

    /**
     * 普通
     */
    COMMON(0, "普通组织"),
    /**
     * 供应商
     */
    SUPPLIER(1, "供应商");

    private Integer type;

    private String typeName;

}
