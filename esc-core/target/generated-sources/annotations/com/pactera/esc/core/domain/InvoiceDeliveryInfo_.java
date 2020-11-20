package com.pactera.esc.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvoiceDeliveryInfo.class)
public abstract class InvoiceDeliveryInfo_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<InvoiceDeliveryInfo, Date> registrationTime;
	public static volatile ListAttribute<InvoiceDeliveryInfo, InvoiceDeliveryInfoStatusLog> invoiceDeliveryInfoStatusLogs;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, String> note;
	public static volatile ListAttribute<InvoiceDeliveryInfo, InvoiceDeliveryInfoDetails> invoiceDeliveryInfoDetails;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, Date> enterFaWaHouseTime;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, Registration> registration;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, String> id;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, Invoice> invoice;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, String> goodTakenInfo;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, Warehouse> warehouse;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, Integer> status;
	public static volatile SingularAttribute<InvoiceDeliveryInfo, Date> outFaWaHouseTime;

	public static final String REGISTRATION_TIME = "registrationTime";
	public static final String INVOICE_DELIVERY_INFO_STATUS_LOGS = "invoiceDeliveryInfoStatusLogs";
	public static final String NOTE = "note";
	public static final String INVOICE_DELIVERY_INFO_DETAILS = "invoiceDeliveryInfoDetails";
	public static final String ENTER_FA_WA_HOUSE_TIME = "enterFaWaHouseTime";
	public static final String REGISTRATION = "registration";
	public static final String ID = "id";
	public static final String INVOICE = "invoice";
	public static final String GOOD_TAKEN_INFO = "goodTakenInfo";
	public static final String WAREHOUSE = "warehouse";
	public static final String STATUS = "status";
	public static final String OUT_FA_WA_HOUSE_TIME = "outFaWaHouseTime";

}

