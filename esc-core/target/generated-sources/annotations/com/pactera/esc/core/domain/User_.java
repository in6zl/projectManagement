package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<User, LocalDateTime> lastLogin;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, String> imagePath;
	public static volatile ListAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Integer> type;
	public static volatile SingularAttribute<User, String> realName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, Integer> enable;
	public static volatile SingularAttribute<User, Organization> organization;
	public static volatile SingularAttribute<User, Supplier> supplier;
	public static volatile SingularAttribute<User, String> id;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> username;

	public static final String LAST_LOGIN = "lastLogin";
	public static final String ADDRESS = "address";
	public static final String IMAGE_PATH = "imagePath";
	public static final String ROLES = "roles";
	public static final String TYPE = "type";
	public static final String REAL_NAME = "realName";
	public static final String PASSWORD = "password";
	public static final String PHONE = "phone";
	public static final String ENABLE = "enable";
	public static final String ORGANIZATION = "organization";
	public static final String SUPPLIER = "supplier";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

