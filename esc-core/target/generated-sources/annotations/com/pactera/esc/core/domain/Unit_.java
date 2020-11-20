package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Unit.class)
public abstract class Unit_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Unit, Integer> isDelete;
	public static volatile SingularAttribute<Unit, String> name;
	public static volatile SingularAttribute<Unit, String> description;
	public static volatile SingularAttribute<Unit, String> id;

	public static final String IS_DELETE = "isDelete";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

