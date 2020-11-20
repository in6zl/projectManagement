package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Car.class)
public abstract class Car_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Car, Integer> isOpen;
	public static volatile SingularAttribute<Car, String> carNo;
	public static volatile SingularAttribute<Car, Integer> inUse;
	public static volatile SingularAttribute<Car, String> driverName;
	public static volatile SingularAttribute<Car, String> driverPhone;
	public static volatile SingularAttribute<Car, String> id;
	public static volatile SingularAttribute<Car, Integer> type;

	public static final String IS_OPEN = "isOpen";
	public static final String CAR_NO = "carNo";
	public static final String IN_USE = "inUse";
	public static final String DRIVER_NAME = "driverName";
	public static final String DRIVER_PHONE = "driverPhone";
	public static final String ID = "id";
	public static final String TYPE = "type";

}

