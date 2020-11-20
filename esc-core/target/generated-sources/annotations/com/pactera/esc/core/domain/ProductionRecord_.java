package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductionRecord.class)
public abstract class ProductionRecord_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<ProductionRecord, Product> product;
	public static volatile SingularAttribute<ProductionRecord, LocalDateTime> productionDate;
	public static volatile SingularAttribute<ProductionRecord, Double> num;
	public static volatile SingularAttribute<ProductionRecord, String> batch;
	public static volatile SingularAttribute<ProductionRecord, InboundOrderProduct> inboundOrderProduct;
	public static volatile SingularAttribute<ProductionRecord, String> id;
	public static volatile SingularAttribute<ProductionRecord, ProductionPlan> productionPlan;
	public static volatile SingularAttribute<ProductionRecord, Integer> status;

	public static final String PRODUCT = "product";
	public static final String PRODUCTION_DATE = "productionDate";
	public static final String NUM = "num";
	public static final String BATCH = "batch";
	public static final String INBOUND_ORDER_PRODUCT = "inboundOrderProduct";
	public static final String ID = "id";
	public static final String PRODUCTION_PLAN = "productionPlan";
	public static final String STATUS = "status";

}

