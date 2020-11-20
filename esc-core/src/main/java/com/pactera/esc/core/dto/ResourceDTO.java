package com.pactera.esc.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yayun
 */
@Data
public class ResourceDTO implements Serializable {

    private static final long serialVersionUID = 950829208250691496L;
    private String id;

    private String name;

    private Byte type;

    private String url;

    private String permission;

    private String icon;

    private Integer level;

    private Integer sort;

    private String parentId;

    private List<ResourceDTO> children;

    private Boolean selected = false;

    private String title;
}
