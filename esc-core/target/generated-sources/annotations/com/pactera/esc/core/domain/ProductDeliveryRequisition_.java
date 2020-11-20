package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductDeliveryRequisition.class)
public abstract class ProductDeliveryRequisition_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<ProductDeliveryRequisition, LocalDateTime> outDate;
	public static volatile SingularAttribute<ProductDeliveryRequisition, Byte> isCompile;
	public static volatile SingularAttribute<ProductDeliveryRequisition, Sale> sale;
	public static volatile ListAttribute<ProductDeliveryRequisition, Warehouse> warehouseList;
	public static volatile SingularAttribute<ProductDeliveryRequisition, String> address;
	public static volatile SingularAttribute<ProductDeliveryRequisition, String> outOperator;
	public static volatile SingularAttribute<ProductDeliveryRequisition, String> receiverPhone;
	public static volatile SingularAttribute<ProductDeliveryRequisition, String> receiver;
	public static volatile SingularAttribute<ProductDeliveryRequisition, CarSchedule> carSchedule;
	public static volatile ListAttribute<ProductDeliveryRequisition, ProductDeliveryRequisitionDetails> detailsList;
	public static volatile SingularAttribute<ProductDeliveryRequisition, String> id;

	public static final String OUT_DATE = "outDate";
	public static final String IS_COMPILE = "isCompile";
	public static final String SALE = "sale";
	public static final String WAREHOUSE_LIST = "warehouseList";
	public static final String ADDRESS = "address";
	public static final String OUT_OPERATOR = "outOperator";
	public static final String RECEIVER_PHONE = "receiverPhone";
	public static final String RECEIVER = "receiver";
	public static final String CAR_SCHEDULE = "carSchedule";
	public static final String DETAILS_LIST = "detailsList";
	public static final String ID = "id";

}

