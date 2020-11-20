package com.pactera.esc.core.domain;

import com.pactera.esc.core.common.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * 系统参数设置表
 * @author yayun
 */
@Getter
@Setter
@Entity(name = "sys_setting")
public class SystemSetting extends BaseEntity{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @ApiModelProperty(value = "ID")
    @NotBlank(message = "ID不为空", groups = {Constants.Update.class})
    private String id;

    @Column(name = "frozen_require")
    private Integer frozenRequire;

    @Column(name = "frozen_transfer")
    private Integer frozenTransfer;

    @Column
    private String address;

    @Column
    private String location;
}
