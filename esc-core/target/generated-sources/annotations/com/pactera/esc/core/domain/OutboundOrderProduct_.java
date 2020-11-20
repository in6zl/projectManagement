package com.pactera.esc.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OutboundOrderProduct.class)
public abstract class OutboundOrderProduct_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<OutboundOrderProduct, String> code;
	public static volatile SingularAttribute<OutboundOrderProduct, String> outOperator;
	public static volatile SingularAttribute<OutboundOrderProduct, String> receiver;
	public static volatile SingularAttribute<OutboundOrderProduct, String> id;
	public static volatile SingularAttribute<OutboundOrderProduct, OutProductOrder> outProductOrder;
	public static volatile SingularAttribute<OutboundOrderProduct, Warehouse> warehouse;
	public static volatile SingularAttribute<OutboundOrderProduct, Integer> isFinished;
	public static volatile SingularAttribute<OutboundOrderProduct, Date> outTime;

	public static final String CODE = "code";
	public static final String OUT_OPERATOR = "outOperator";
	public static final String RECEIVER = "receiver";
	public static final String ID = "id";
	public static final String OUT_PRODUCT_ORDER = "outProductOrder";
	public static final String WAREHOUSE = "warehouse";
	public static final String IS_FINISHED = "isFinished";
	public static final String OUT_TIME = "outTime";

}

