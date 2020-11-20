package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ {

	public static volatile SingularAttribute<BaseEntity, String> creator;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> createTime;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> updateTime;
	public static volatile SingularAttribute<BaseEntity, String> operator;

	public static final String CREATOR = "creator";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_TIME = "updateTime";
	public static final String OPERATOR = "operator";

}

