package com.pactera.esc.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarSchedule.class)
public abstract class CarSchedule_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<CarSchedule, Car> car;
	public static volatile SingularAttribute<CarSchedule, Date> startTime;
	public static volatile SingularAttribute<CarSchedule, String> id;
	public static volatile SingularAttribute<CarSchedule, Date> endTime;
	public static volatile SingularAttribute<CarSchedule, OutProductOrder> outProductOrder;

	public static final String CAR = "car";
	public static final String START_TIME = "startTime";
	public static final String ID = "id";
	public static final String END_TIME = "endTime";
	public static final String OUT_PRODUCT_ORDER = "outProductOrder";

}

