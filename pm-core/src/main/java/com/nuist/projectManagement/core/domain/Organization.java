package com.pactera.esc.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.pactera.esc.core.annotations.*;
import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.Pageable;
import com.pactera.esc.core.common.SortParam;
import com.pactera.esc.core.tree.NodeInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author minhao.cai
 * @date 2020/03/20
 */
@Getter
@Setter
@Entity(name = "sys_organization")
@EntityName(name = "机构")
public class Organization extends BaseEntity implements Serializable, NodeInfo {

    private static final long serialVersionUID = -5414557633403867700L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @NotBlank(message = "ID不为空", groups = {Constants.Update.class})
    @ApiModelProperty(value = "ID")
    private String id;

    @Column
    @NotBlank(message = "组织名不为空")
    @ApiModelProperty(value = "组织名")
    private String name;

    @Column
    @NotNull(message = "组织类型不能为空")
    @ApiModelProperty(value = "组织类型")
    private Integer type = 0;

    @Column(name = "lft", updatable = false)
    @LeftColumn
    private int lft;

    @RightColumn
    @Column(name = "rgt", updatable = false)
    private int rgt;

    @LevelColumn
    @Column(name = "level", updatable = false)
    private Integer level;

    @RootColumn
    @Column(name = "root_id")
    private Integer rootId;

    @Column
    @NotBlank(message = "地址不为空")
    @ApiModelProperty(value = "地址")
    private String address;

    @Column
    @NotBlank(message = "手机号不为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @Column
    private String supplierCode;

    @ManyToMany
    @JoinTable(name = "sys_organization_role",
        joinColumns = @JoinColumn(name = "organization_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles = Sets.newHashSet();

    @OneToMany(mappedBy = "organization")
    @JsonIgnore
    private Set<User> users = Sets.newHashSet();

    //----------------------

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

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getLeftValue() {
        return this.lft;
    }

    @Override
    public int getRightValue() {
        return this.rgt;
    }

    @Override
    public int getRootValue() {
        return this.rootId;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLeftValue(int value) {
        this.lft = value;
    }

    @Override
    public void setRightValue(int value) {
        this.rgt = value;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setRootValue(int value) {
        this.rootId = value;
    }
}
