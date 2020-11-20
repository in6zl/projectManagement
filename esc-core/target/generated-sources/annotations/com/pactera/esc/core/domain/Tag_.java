package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public abstract class Tag_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Tag, String> name;
	public static volatile SingularAttribute<Tag, String> id;
	public static volatile SingularAttribute<Tag, Integer> priority;
	public static volatile SingularAttribute<Tag, String> value;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PRIORITY = "priority";
	public static final String VALUE = "value";

}

