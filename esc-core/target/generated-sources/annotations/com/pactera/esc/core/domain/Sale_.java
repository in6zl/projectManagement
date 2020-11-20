package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sale.class)
public abstract class Sale_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Sale, String> seller;
	public static volatile ListAttribute<Sale, DeliveryPlan> deliveryPlanList;
	public static volatile SingularAttribute<Sale, Double> amount;
	public static volatile ListAttribute<Sale, OutProductOrder> outProductOrderList;
	public static volatile SingularAttribute<Sale, String> orderNo;
	public static volatile SingularAttribute<Sale, LocalDateTime> orderTime;
	public static volatile SingularAttribute<Sale, String> address;
	public static volatile SingularAttribute<Sale, String> sellerPhone;
	public static volatile SingularAttribute<Sale, String> id;
	public static volatile SingularAttribute<Sale, Byte> state;
	public static volatile SingularAttribute<Sale, LocalDateTime> expectDate;
	public static volatile ListAttribute<Sale, SaleDetails> saleDetailsList;

	public static final String SELLER = "seller";
	public static final String DELIVERY_PLAN_LIST = "deliveryPlanList";
	public static final String AMOUNT = "amount";
	public static final String OUT_PRODUCT_ORDER_LIST = "outProductOrderList";
	public static final String ORDER_NO = "orderNo";
	public static final String ORDER_TIME = "orderTime";
	public static final String ADDRESS = "address";
	public static final String SELLER_PHONE = "sellerPhone";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String EXPECT_DATE = "expectDate";
	public static final String SALE_DETAILS_LIST = "saleDetailsList";

}

