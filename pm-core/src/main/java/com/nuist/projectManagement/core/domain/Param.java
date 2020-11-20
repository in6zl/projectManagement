package com.pactera.esc.core.domain;

import com.pactera.esc.core.annotations.EntityName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author zl
 * @Date 2020/08/12 13:23
 */
@Getter
@Setter
@Entity(name = "sys_param")
@EntityName(name = "系统变量")
public class Param extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "param_key")
    private String key;

    @Column(name = "param_value")
    private String value;

    @Column(name = "param_name")
    private String name;
}
