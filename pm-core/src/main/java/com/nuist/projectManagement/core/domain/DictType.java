package com.pactera.esc.core.domain;

import com.pactera.esc.core.annotations.EntityName;
import com.pactera.esc.core.common.Pageable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 字典类型
 */
@Getter
@Setter
@Entity(name = "sys_dict_type")
@EntityName(name = "字典类型")
public class DictType extends BaseEntity {

    private static final long serialVersionUID = 5044455084068853741L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column
    @NotBlank(message = "name required")
    private String name;

    @Transient
    private Pageable pageable = new Pageable();


}
