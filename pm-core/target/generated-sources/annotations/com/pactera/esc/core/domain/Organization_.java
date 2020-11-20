package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Organization.class)
public abstract class Organization_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Organization, String> address;
	public static volatile SingularAttribute<Organization, Integer> level;
	public static volatile SingularAttribute<Organization, Integer> rootId;
	public static volatile SingularAttribute<Organization, String> phone;
	public static volatile SetAttribute<Organization, Role> roles;
	public static volatile SingularAttribute<Organization, String> name;
	public static volatile SingularAttribute<Organization, String> id;
	public static volatile SingularAttribute<Organization, Integer> lft;
	public static volatile SingularAttribute<Organization, String> supplierCode;
	public static volatile SingularAttribute<Organization, Integer> type;
	public static volatile SingularAttribute<Organization, Integer> rgt;
	public static volatile SetAttribute<Organization, User> users;

	public static final String ADDRESS = "address";
	public static final String LEVEL = "level";
	public static final String ROOT_ID = "rootId";
	public static final String PHONE = "phone";
	public static final String ROLES = "roles";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String LFT = "lft";
	public static final String SUPPLIER_CODE = "supplierCode";
	public static final String TYPE = "type";
	public static final String RGT = "rgt";
	public static final String USERS = "users";

}

