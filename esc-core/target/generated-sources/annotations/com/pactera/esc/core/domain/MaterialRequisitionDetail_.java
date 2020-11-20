package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaterialRequisitionDetail.class)
public abstract class MaterialRequisitionDetail_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<MaterialRequisitionDetail, String> requisitionId;
	public static volatile SingularAttribute<MaterialRequisitionDetail, Material> material;
	public static volatile SingularAttribute<MaterialRequisitionDetail, LocalDateTime> getTime;
	public static volatile SingularAttribute<MaterialRequisitionDetail, Integer> num;
	public static volatile SingularAttribute<MaterialRequisitionDetail, String> batch;
	public static volatile SingularAttribute<MaterialRequisitionDetail, String> id;
	public static volatile SingularAttribute<MaterialRequisitionDetail, Warehouse> warehouse;

	public static final String REQUISITION_ID = "requisitionId";
	public static final String MATERIAL = "material";
	public static final String GET_TIME = "getTime";
	public static final String NUM = "num";
	public static final String BATCH = "batch";
	public static final String ID = "id";
	public static final String WAREHOUSE = "warehouse";

}

