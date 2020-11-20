package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductionLine.class)
public abstract class ProductionLine_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<ProductionLine, String> leader;
	public static volatile SingularAttribute<ProductionLine, String> address;
	public static volatile SingularAttribute<ProductionLine, String> name;
	public static volatile ListAttribute<ProductionLine, ProductionPlan> productionPlanList;
	public static volatile SingularAttribute<ProductionLine, String> id;
	public static volatile SingularAttribute<ProductionLine, String> leaderPhone;
	public static volatile SingularAttribute<ProductionLine, Warehouse> warehouse;
	public static volatile SingularAttribute<ProductionLine, WorkShop> chejian;

	public static final String LEADER = "leader";
	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String PRODUCTION_PLAN_LIST = "productionPlanList";
	public static final String ID = "id";
	public static final String LEADER_PHONE = "leaderPhone";
	public static final String WAREHOUSE = "warehouse";
	public static final String CHEJIAN = "chejian";

}

