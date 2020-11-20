package com.pactera.esc.core.domain;

import com.pactera.esc.core.annotations.EntityName;
import com.pactera.esc.core.common.Pageable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 字典信息
 */
@Getter
@Setter
@Entity(name = "sys_dict")
@EntityName(name = "字典信息")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = -2650399957538980572L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "dict_key")
    @NotBlank(message = "dictKey required")
    private String dictKey;

    @Column(name = "dict_value")
    @NotBlank(message = "dictValue required")
    private String dictValue;

    @ManyToOne
    @JoinColumn(name = "dict_type_id")
    private DictType dictType;

    @Transient
    private Pageable pageable = new Pageable();
}
