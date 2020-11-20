package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductionPlan.class)
public abstract class ProductionPlan_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<ProductionPlan, Product> product;
	public static volatile SingularAttribute<ProductionPlan, String> code;
	public static volatile SingularAttribute<ProductionPlan, ProductionLine> productionLine;
	public static volatile SingularAttribute<ProductionPlan, LocalDateTime> endDate;
	public static volatile SingularAttribute<ProductionPlan, Integer> num;
	public static volatile SingularAttribute<ProductionPlan, String> id;
	public static volatile SingularAttribute<ProductionPlan, LocalDateTime> startDate;
	public static volatile SingularAttribute<ProductionPlan, Integer> status;

	public static final String PRODUCT = "product";
	public static final String CODE = "code";
	public static final String PRODUCTION_LINE = "productionLine";
	public static final String END_DATE = "endDate";
	public static final String NUM = "num";
	public static final String ID = "id";
	public static final String START_DATE = "startDate";
	public static final String STATUS = "status";

}

