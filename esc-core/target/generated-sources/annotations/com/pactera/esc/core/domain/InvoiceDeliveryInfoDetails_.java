package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvoiceDeliveryInfoDetails.class)
public abstract class InvoiceDeliveryInfoDetails_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<InvoiceDeliveryInfoDetails, InvoiceDeliveryInfo> invoiceDeliveryInfo;
	public static volatile SingularAttribute<InvoiceDeliveryInfoDetails, Material> material;
	public static volatile SingularAttribute<InvoiceDeliveryInfoDetails, Double> num;
	public static volatile SingularAttribute<InvoiceDeliveryInfoDetails, String> id;
	public static volatile SingularAttribute<InvoiceDeliveryInfoDetails, Double> receiveNum;

	public static final String INVOICE_DELIVERY_INFO = "invoiceDeliveryInfo";
	public static final String MATERIAL = "material";
	public static final String NUM = "num";
	public static final String ID = "id";
	public static final String RECEIVE_NUM = "receiveNum";

}

