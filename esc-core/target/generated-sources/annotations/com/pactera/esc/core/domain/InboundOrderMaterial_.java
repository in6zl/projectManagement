package com.pactera.esc.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InboundOrderMaterial.class)
public abstract class InboundOrderMaterial_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<InboundOrderMaterial, Date> inTime;
	public static volatile SingularAttribute<InboundOrderMaterial, String> inOperator;
	public static volatile SingularAttribute<InboundOrderMaterial, String> code;
	public static volatile SingularAttribute<InboundOrderMaterial, String> deliveryMan;
	public static volatile ListAttribute<InboundOrderMaterial, InboundOrderMaterialDetail> detailList;
	public static volatile SingularAttribute<InboundOrderMaterial, String> id;
	public static volatile SingularAttribute<InboundOrderMaterial, Invoice> invoice;
	public static volatile SingularAttribute<InboundOrderMaterial, Warehouse> warehouse;
	public static volatile SingularAttribute<InboundOrderMaterial, Integer> isFinished;

	public static final String IN_TIME = "inTime";
	public static final String IN_OPERATOR = "inOperator";
	public static final String CODE = "code";
	public static final String DELIVERY_MAN = "deliveryMan";
	public static final String DETAIL_LIST = "detailList";
	public static final String ID = "id";
	public static final String INVOICE = "invoice";
	public static final String WAREHOUSE = "warehouse";
	public static final String IS_FINISHED = "isFinished";

}

