package com.pactera.esc.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author si.chen
 * @date 2020/3/27 16:38
 */
@Data
@NoArgsConstructor
public class OrganizationDTO implements Serializable {
    private static final long serialVersionUID = -5567124263172541712L;

    private String id;

    private String name;

    private Integer type;

    private String address;

    private String phone;

    private String supplierCode;

    private String[] roles;
}
