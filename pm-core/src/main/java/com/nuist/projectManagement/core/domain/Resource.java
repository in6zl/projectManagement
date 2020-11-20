package com.pactera.esc.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.Pageable;
import com.pactera.esc.core.common.SortParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author minhao.cai
 * @date 2020/03/20
 */
@Getter
@Setter
@Entity(name = "sys_resource")
@Accessors(chain = true)
@EqualsAndHashCode
public class Resource extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3384616224889121481L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @ApiModelProperty(value = "ID")
    @NotBlank(message = "ID不为空", groups = {Constants.CheckId.class})
    private String id;

    @Column
    @NotBlank(message = "资源名不为空")
    @ApiModelProperty(value = "资源名")
    private String name;

    @Column
    @NotNull(message = "权限类型不为空")
    @ApiModelProperty(value = "权限类型：0菜单 1按钮")
    private Integer type;

    @Column
    @ApiModelProperty(value = "资源地址")
    private String url;

    @Column
    @ApiModelProperty(value = "权限code")
    private String permission;

    @Column
    @ApiModelProperty(value = "资源级别：0按钮默认值 1一级菜单 2二级菜单")
    private Integer level;

    @Column
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "父级id")
    private String parentId;

    @Column(name = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @Column(name = "title")
    @ApiModelProperty(value = "显示名称")
    @NotBlank(message = "显示名称")
    private String title;

    //-----------------------

    @Transient
    @JsonIgnore
    private String idNe;

    @Transient
    @JsonIgnore
    private String nameLike;

    @Transient
    private Pageable pageable = new Pageable();

    @Transient
    private SortParam sortParam = new SortParam();

}
