package com.pactera.esc.core.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeliveryPlan.class)
public abstract class DeliveryPlan_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<DeliveryPlan, Byte> isCompilePlan;
	public static volatile SingularAttribute<DeliveryPlan, Sale> sale;
	public static volatile ListAttribute<DeliveryPlan, DeliveryPlanDetails> deliveryPlanDetailsList;
	public static volatile SingularAttribute<DeliveryPlan, String> name;
	public static volatile SingularAttribute<DeliveryPlan, String> description;
	public static volatile SingularAttribute<DeliveryPlan, String> id;
	public static volatile SingularAttribute<DeliveryPlan, LocalDate> deliveryDate;

	public static final String IS_COMPILE_PLAN = "isCompilePlan";
	public static final String SALE = "sale";
	public static final String DELIVERY_PLAN_DETAILS_LIST = "deliveryPlanDetailsList";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String DELIVERY_DATE = "deliveryDate";

}

