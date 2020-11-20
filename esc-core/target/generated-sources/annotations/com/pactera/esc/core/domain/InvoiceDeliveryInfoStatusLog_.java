package com.pactera.esc.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvoiceDeliveryInfoStatusLog.class)
public abstract class InvoiceDeliveryInfoStatusLog_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<InvoiceDeliveryInfoStatusLog, InvoiceDeliveryInfo> invoiceDeliveryInfo;
	public static volatile SingularAttribute<InvoiceDeliveryInfoStatusLog, String> carNo;
	public static volatile SingularAttribute<InvoiceDeliveryInfoStatusLog, String> name;
	public static volatile SingularAttribute<InvoiceDeliveryInfoStatusLog, String> id;
	public static volatile SingularAttribute<InvoiceDeliveryInfoStatusLog, Date> time;
	public static volatile SingularAttribute<InvoiceDeliveryInfoStatusLog, Warehouse> warehouse;
	public static volatile SingularAttribute<InvoiceDeliveryInfoStatusLog, Integer> status;

	public static final String INVOICE_DELIVERY_INFO = "invoiceDeliveryInfo";
	public static final String CAR_NO = "carNo";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String TIME = "time";
	public static final String WAREHOUSE = "warehouse";
	public static final String STATUS = "status";

}

