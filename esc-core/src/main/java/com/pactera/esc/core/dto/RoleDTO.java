package com.pactera.esc.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yayun
 */
@Data
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = -4100448561418743650L;

    private String id;

    private String name;

    private String description;

    private List<ResourceDTO> resources;

    private Integer organizationId;
}
