package com.pactera.esc.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.pactera.esc.core.annotations.EntityName;
import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.Pageable;
import com.pactera.esc.core.common.SortParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author minhao.cai
 * @date 2020/03/20
 */
@Getter
@Setter
@Entity(name = "sys_user")
@Accessors(chain = true)
@EntityName(name = "用户")
public class User extends BaseEntity {

    private static final long serialVersionUID = 7272940415780655765L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @ApiModelProperty(value = "ID")
    @NotBlank(message = "ID不为空", groups = {Constants.CheckId.class})
    private String id;

    @Column
    @NotBlank(message = "用户名不为空", groups = {Constants.Create.class})
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(name = "real_name")
    @NotBlank(message = "真实姓名不为空", groups = {Constants.Create.class})
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @Column
    @NotBlank(message = "密码不为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @Column
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Column
    @NotBlank(message = "手机号为空", groups = {Constants.Create.class})
    @ApiModelProperty(value = "手机号")
    private String phone;

    @Column
    @NotNull(message = "用户类型不为空")
    @ApiModelProperty(value = "用户类型：1普通用户 0超管 2供应商")
    private Integer type;

    @Column
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "image_path")
    @ApiModelProperty(value = "头像地址")
    private String imagePath;

    @Column
    @ApiModelProperty(value = "是否启用：0禁用 1启用")
    private Integer enable;

    @ManyToOne
    @JoinColumn(name = "org_id")
    @NotNull(message = "组织id不能为空", groups = {Constants.Create.class})
    @ApiModelProperty(value = "组织id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @ApiModelProperty(value = "供应商id")
    private Supplier supplier;

    @Column(name = "last_login")
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLogin;

    @ManyToMany
    @JoinTable(name="sys_user_role",joinColumns={ @JoinColumn(name="user_id")}, inverseJoinColumns={ @JoinColumn(name = "role_id") })
    private List<Role> roles = Lists.newArrayList();


    //--------------入参、业务中数据---------------------

    @Transient
    @JsonIgnore
    private String idNe;

    @Transient
    @JsonIgnore
    private String usernameLike;

    @Transient
    @JsonIgnore
    private String phoneLike;

    @Transient
    private Pageable pageable = new Pageable();

    @Transient
    private SortParam sortParam = new SortParam();

    @Transient
    @NotBlank(message = "旧密码不为空", groups = {Constants.Password.class})
    private String oldPassword;
}
