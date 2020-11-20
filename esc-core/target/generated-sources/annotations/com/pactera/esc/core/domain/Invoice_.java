package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Invoice.class)
public abstract class Invoice_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile ListAttribute<Invoice, InvoiceDeliveryInfo> invoiceDeliveryInfos;
	public static volatile SingularAttribute<Invoice, String> note;
	public static volatile SingularAttribute<Invoice, LocalDateTime> finishTime;
	public static volatile SingularAttribute<Invoice, String> code;
	public static volatile SingularAttribute<Invoice, LocalDateTime> sendDate;
	public static volatile SingularAttribute<Invoice, LocalDateTime> expectArriveTime;
	public static volatile SingularAttribute<Invoice, String> sendAddress;
	public static volatile SingularAttribute<Invoice, Integer> carType;
	public static volatile SingularAttribute<Invoice, String> carNo;
	public static volatile SingularAttribute<Invoice, Supplier> supplier;
	public static volatile SingularAttribute<Invoice, Integer> sendType;
	public static volatile ListAttribute<Invoice, InboundOrderMaterial> inboundOrderMaterials;
	public static volatile SingularAttribute<Invoice, String> driverName;
	public static volatile SingularAttribute<Invoice, String> driverPhone;
	public static volatile SingularAttribute<Invoice, String> id;
	public static volatile SingularAttribute<Invoice, Integer> state;

	public static final String INVOICE_DELIVERY_INFOS = "invoiceDeliveryInfos";
	public static final String NOTE = "note";
	public static final String FINISH_TIME = "finishTime";
	public static final String CODE = "code";
	public static final String SEND_DATE = "sendDate";
	public static final String EXPECT_ARRIVE_TIME = "expectArriveTime";
	public static final String SEND_ADDRESS = "sendAddress";
	public static final String CAR_TYPE = "carType";
	public static final String CAR_NO = "carNo";
	public static final String SUPPLIER = "supplier";
	public static final String SEND_TYPE = "sendType";
	public static final String INBOUND_ORDER_MATERIALS = "inboundOrderMaterials";
	public static final String DRIVER_NAME = "driverName";
	public static final String DRIVER_PHONE = "driverPhone";
	public static final String ID = "id";
	public static final String STATE = "state";

}

