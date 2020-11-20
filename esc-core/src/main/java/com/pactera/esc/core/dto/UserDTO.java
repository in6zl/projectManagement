package com.pactera.esc.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 2930625848033603517L;

    private String id;

    private String username;

    private String realName;

    private String password;

    private String newPassword;

    private String email;

    private String phone;

    private Integer type;

    private String address;

    private String imagePath;

    private Integer enable;

    private String organizationId;

    private String organizationName;

    private LocalDateTime lastLogin;

    private List<UserRoleDTO> userRoleList;

    private Set<RoleDTO> roles;

    private List<String> roleIds;

    private List<ResourceDTO> resources;

    private Set<String> permissions;

    private String roleNames;

    private String supplierId;

    private String supplierName;
}
