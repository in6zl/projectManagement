package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaterialRequirePlan.class)
public abstract class MaterialRequirePlan_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<MaterialRequirePlan, WorkShop> workShop;
	public static volatile SingularAttribute<MaterialRequirePlan, Material> material;
	public static volatile SingularAttribute<MaterialRequirePlan, Double> num;
	public static volatile SingularAttribute<MaterialRequirePlan, Double> outNum;
	public static volatile ListAttribute<MaterialRequirePlan, ProductionPlan> productionPlanList;
	public static volatile SingularAttribute<MaterialRequirePlan, String> id;
	public static volatile SingularAttribute<MaterialRequirePlan, Byte> state;
	public static volatile SingularAttribute<MaterialRequirePlan, Warehouse> warehouse;
	public static volatile ListAttribute<MaterialRequirePlan, MaterialRequisition> materialRequisitionList;
	public static volatile SingularAttribute<MaterialRequirePlan, LocalDateTime> requireTime;

	public static final String WORK_SHOP = "workShop";
	public static final String MATERIAL = "material";
	public static final String NUM = "num";
	public static final String OUT_NUM = "outNum";
	public static final String PRODUCTION_PLAN_LIST = "productionPlanList";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String WAREHOUSE = "warehouse";
	public static final String MATERIAL_REQUISITION_LIST = "materialRequisitionList";
	public static final String REQUIRE_TIME = "requireTime";

}

