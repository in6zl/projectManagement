package com.pactera.esc.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InboundOrderProduct.class)
public abstract class InboundOrderProduct_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<InboundOrderProduct, Date> inTime;
	public static volatile SingularAttribute<InboundOrderProduct, String> inOperator;
	public static volatile SingularAttribute<InboundOrderProduct, String> code;
	public static volatile SingularAttribute<InboundOrderProduct, String> deliveryMan;
	public static volatile SingularAttribute<InboundOrderProduct, String> id;
	public static volatile SingularAttribute<InboundOrderProduct, Integer> isFinished;
	public static volatile SingularAttribute<InboundOrderProduct, Warehouse> warehouse;

	public static final String IN_TIME = "inTime";
	public static final String IN_OPERATOR = "inOperator";
	public static final String CODE = "code";
	public static final String DELIVERY_MAN = "deliveryMan";
	public static final String ID = "id";
	public static final String IS_FINISHED = "isFinished";
	public static final String WAREHOUSE = "warehouse";

}

