package com.pactera.esc.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author zmj
 * @description
 * @date 2020/5/13 0013
 */
@Getter
@Setter
@MappedSuperclass
public class BaseRef implements Serializable {

    /**
     * 物料需求计划id
     */
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "material_require_plan_id" )
    @NotFound(action = NotFoundAction.IGNORE)
    private MaterialRequirePlan materialRequirePlan;
}
