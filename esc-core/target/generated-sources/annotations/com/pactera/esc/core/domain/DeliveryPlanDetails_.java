package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeliveryPlanDetails.class)
public abstract class DeliveryPlanDetails_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<DeliveryPlanDetails, Product> product;
	public static volatile SingularAttribute<DeliveryPlanDetails, Double> num;
	public static volatile SingularAttribute<DeliveryPlanDetails, DeliveryPlan> deliveryPlan;
	public static volatile SingularAttribute<DeliveryPlanDetails, String> id;

	public static final String PRODUCT = "product";
	public static final String NUM = "num";
	public static final String DELIVERY_PLAN = "deliveryPlan";
	public static final String ID = "id";

}

