package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TruckState.class)
public abstract class TruckState_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<TruckState, String> carNo;
	public static volatile SingularAttribute<TruckState, String> name;
	public static volatile SingularAttribute<TruckState, Registration> registration;
	public static volatile SingularAttribute<TruckState, String> id;
	public static volatile SingularAttribute<TruckState, LocalDateTime> time;
	public static volatile SingularAttribute<TruckState, Warehouse> warehouse;
	public static volatile SingularAttribute<TruckState, String> status;

	public static final String CAR_NO = "carNo";
	public static final String NAME = "name";
	public static final String REGISTRATION = "registration";
	public static final String ID = "id";
	public static final String TIME = "time";
	public static final String WAREHOUSE = "warehouse";
	public static final String STATUS = "status";

}

