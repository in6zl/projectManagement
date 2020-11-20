package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Role, String> name;
	public static volatile ListAttribute<Role, Organization> organizations;
	public static volatile SingularAttribute<Role, String> description;
	public static volatile ListAttribute<Role, Resource> resources;
	public static volatile SingularAttribute<Role, String> id;
	public static volatile ListAttribute<Role, User> users;

	public static final String NAME = "name";
	public static final String ORGANIZATIONS = "organizations";
	public static final String DESCRIPTION = "description";
	public static final String RESOURCES = "resources";
	public static final String ID = "id";
	public static final String USERS = "users";

}

