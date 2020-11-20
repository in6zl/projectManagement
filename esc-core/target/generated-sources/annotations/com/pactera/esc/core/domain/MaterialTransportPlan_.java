package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaterialTransportPlan.class)
public abstract class MaterialTransportPlan_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<MaterialTransportPlan, Double> finishedNum;
	public static volatile SingularAttribute<MaterialTransportPlan, Material> material;
	public static volatile ListAttribute<MaterialTransportPlan, Invoice> invoices;
	public static volatile SingularAttribute<MaterialTransportPlan, Double> num;
	public static volatile SingularAttribute<MaterialTransportPlan, Supplier> supplier;
	public static volatile SingularAttribute<MaterialTransportPlan, String> id;
	public static volatile SingularAttribute<MaterialTransportPlan, Byte> state;
	public static volatile SingularAttribute<MaterialTransportPlan, Byte> source;
	public static volatile SingularAttribute<MaterialTransportPlan, Warehouse> warehouse;
	public static volatile SingularAttribute<MaterialTransportPlan, LocalDateTime> requireTime;

	public static final String FINISHED_NUM = "finishedNum";
	public static final String MATERIAL = "material";
	public static final String INVOICES = "invoices";
	public static final String NUM = "num";
	public static final String SUPPLIER = "supplier";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String SOURCE = "source";
	public static final String WAREHOUSE = "warehouse";
	public static final String REQUIRE_TIME = "requireTime";

}

