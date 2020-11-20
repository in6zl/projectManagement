package com.pactera.esc.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OutboundOrderMaterial.class)
public abstract class OutboundOrderMaterial_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<OutboundOrderMaterial, String> code;
	public static volatile SingularAttribute<OutboundOrderMaterial, String> outOperator;
	public static volatile SingularAttribute<OutboundOrderMaterial, String> id;
	public static volatile SingularAttribute<OutboundOrderMaterial, Warehouse> warehouse;
	public static volatile SingularAttribute<OutboundOrderMaterial, Integer> isFinished;
	public static volatile SingularAttribute<OutboundOrderMaterial, Date> outTime;

	public static final String CODE = "code";
	public static final String OUT_OPERATOR = "outOperator";
	public static final String ID = "id";
	public static final String WAREHOUSE = "warehouse";
	public static final String IS_FINISHED = "isFinished";
	public static final String OUT_TIME = "outTime";

}

