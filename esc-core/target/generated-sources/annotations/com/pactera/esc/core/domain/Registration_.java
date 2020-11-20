package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Registration.class)
public abstract class Registration_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile ListAttribute<Registration, InvoiceDeliveryInfo> deliveryList;
	public static volatile SingularAttribute<Registration, LocalDateTime> exitTime;
	public static volatile SingularAttribute<Registration, Byte> carType;
	public static volatile SingularAttribute<Registration, String> carNo;
	public static volatile SingularAttribute<Registration, Integer> isDelete;
	public static volatile SingularAttribute<Registration, String> driverName;
	public static volatile SingularAttribute<Registration, String> driverPhone;
	public static volatile SingularAttribute<Registration, String> id;
	public static volatile SingularAttribute<Registration, Integer> state;
	public static volatile SingularAttribute<Registration, LocalDateTime> enterTime;

	public static final String DELIVERY_LIST = "deliveryList";
	public static final String EXIT_TIME = "exitTime";
	public static final String CAR_TYPE = "carType";
	public static final String CAR_NO = "carNo";
	public static final String IS_DELETE = "isDelete";
	public static final String DRIVER_NAME = "driverName";
	public static final String DRIVER_PHONE = "driverPhone";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String ENTER_TIME = "enterTime";

}

