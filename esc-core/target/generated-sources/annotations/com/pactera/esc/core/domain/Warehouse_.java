package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Warehouse.class)
public abstract class Warehouse_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Warehouse, String> area;
	public static volatile SingularAttribute<Warehouse, String> address;
	public static volatile SingularAttribute<Warehouse, Integer> isOpen;
	public static volatile SingularAttribute<Warehouse, Double> latitude;
	public static volatile SingularAttribute<Warehouse, String> name;
	public static volatile ListAttribute<Warehouse, Organization> organizations;
	public static volatile SingularAttribute<Warehouse, String> id;
	public static volatile SingularAttribute<Warehouse, Integer> isFull;
	public static volatile SingularAttribute<Warehouse, Integer> type;
	public static volatile ListAttribute<Warehouse, User> users;
	public static volatile SingularAttribute<Warehouse, Double> longitude;

	public static final String AREA = "area";
	public static final String ADDRESS = "address";
	public static final String IS_OPEN = "isOpen";
	public static final String LATITUDE = "latitude";
	public static final String NAME = "name";
	public static final String ORGANIZATIONS = "organizations";
	public static final String ID = "id";
	public static final String IS_FULL = "isFull";
	public static final String TYPE = "type";
	public static final String USERS = "users";
	public static final String LONGITUDE = "longitude";

}

