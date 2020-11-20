package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Supplier.class)
public abstract class Supplier_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Supplier, String> code;
	public static volatile SingularAttribute<Supplier, String> address;
	public static volatile SingularAttribute<Supplier, String> phone;
	public static volatile SingularAttribute<Supplier, String> name;
	public static volatile SingularAttribute<Supplier, String> description;
	public static volatile SingularAttribute<Supplier, String> id;
	public static volatile ListAttribute<Supplier, User> users;

	public static final String CODE = "code";
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String USERS = "users";

}

