package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OutProductOrder.class)
public abstract class OutProductOrder_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<OutProductOrder, LocalDateTime> outDate;
	public static volatile SingularAttribute<OutProductOrder, Byte> isCompile;
	public static volatile SingularAttribute<OutProductOrder, Sale> sale;
	public static volatile SingularAttribute<OutProductOrder, String> orderNo;
	public static volatile SingularAttribute<OutProductOrder, String> address;
	public static volatile SingularAttribute<OutProductOrder, String> outOperator;
	public static volatile SingularAttribute<OutProductOrder, String> receiverPhone;
	public static volatile SingularAttribute<OutProductOrder, String> receiver;
	public static volatile SingularAttribute<OutProductOrder, Car> car;
	public static volatile ListAttribute<OutProductOrder, OutProductOrderDetails> outProductOrderDetailsList;
	public static volatile SingularAttribute<OutProductOrder, String> description;
	public static volatile SingularAttribute<OutProductOrder, String> id;

	public static final String OUT_DATE = "outDate";
	public static final String IS_COMPILE = "isCompile";
	public static final String SALE = "sale";
	public static final String ORDER_NO = "orderNo";
	public static final String ADDRESS = "address";
	public static final String OUT_OPERATOR = "outOperator";
	public static final String RECEIVER_PHONE = "receiverPhone";
	public static final String RECEIVER = "receiver";
	public static final String CAR = "car";
	public static final String OUT_PRODUCT_ORDER_DETAILS_LIST = "outProductOrderDetailsList";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

