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
import java.util.List;

/**
 * @author minhao.cai
 * @date 2020/03/20
 */
@Getter
@Setter
@Entity(name = "sys_role")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private static final long serialVersionUID = -5339292750377484387L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @ApiModelProperty(value = "ID")
    @NotBlank(message = "ID不为空", groups = {Constants.CheckId.class})
    private String id;

    @Column
    @NotBlank(message = "角色名不为空")
    @ApiModelProperty(value = "角色名")
    private String name;

    @Column
    @ApiModelProperty(value = "描述")
    private String description;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users = Lists.newArrayList();

    @ManyToMany
    @JoinTable(name="sys_role_resource",joinColumns={ @JoinColumn(name="role_id")}, inverseJoinColumns={ @JoinColumn(name = "resource_id") })
    private List<Resource> resources = Lists.newArrayList();

    @ManyToMany(mappedBy = "roles")
    private List<Organization> organizations = Lists.newArrayList();
    //-------------------------

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
