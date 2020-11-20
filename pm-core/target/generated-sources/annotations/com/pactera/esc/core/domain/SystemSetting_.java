package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SystemSetting.class)
public abstract class SystemSetting_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<SystemSetting, String> address;
	public static volatile SingularAttribute<SystemSetting, Integer> frozenTransfer;
	public static volatile SingularAttribute<SystemSetting, Integer> frozenRequire;
	public static volatile SingularAttribute<SystemSetting, String> location;
	public static volatile SingularAttribute<SystemSetting, String> id;

	public static final String ADDRESS = "address";
	public static final String FROZEN_TRANSFER = "frozenTransfer";
	public static final String FROZEN_REQUIRE = "frozenRequire";
	public static final String LOCATION = "location";
	public static final String ID = "id";

}

