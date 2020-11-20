package com.pactera.esc.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author si.chen
 * @date 2020/3/26 16:05
 */
@Data
public class UserRoleDTO implements Serializable {

    private String id;

    private String userId;

    private String username;

    private String roleId;

    private String roleName;
}
