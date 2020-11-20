package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RecommendLog.class)
public abstract class RecommendLog_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<RecommendLog, String> storehouseId;
	public static volatile SingularAttribute<RecommendLog, String> id;
	public static volatile SingularAttribute<RecommendLog, String> text;

	public static final String STOREHOUSE_ID = "storehouseId";
	public static final String ID = "id";
	public static final String TEXT = "text";

}

