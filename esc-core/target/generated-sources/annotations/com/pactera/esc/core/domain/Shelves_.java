package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Shelves.class)
public abstract class Shelves_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Shelves, String> code;
	public static volatile SingularAttribute<Shelves, String> layers;
	public static volatile SingularAttribute<Shelves, String> id;
	public static volatile SingularAttribute<Shelves, Warehouse> warehouse;

	public static final String CODE = "code";
	public static final String LAYERS = "layers";
	public static final String ID = "id";
	public static final String WAREHOUSE = "warehouse";

}

