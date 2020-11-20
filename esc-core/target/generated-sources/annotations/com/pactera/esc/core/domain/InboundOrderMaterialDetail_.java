package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InboundOrderMaterialDetail.class)
public abstract class InboundOrderMaterialDetail_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<InboundOrderMaterialDetail, InboundOrderMaterial> inboundOrderMaterial;
	public static volatile SingularAttribute<InboundOrderMaterialDetail, Material> material;
	public static volatile SingularAttribute<InboundOrderMaterialDetail, StorageBin> bin;
	public static volatile SingularAttribute<InboundOrderMaterialDetail, Double> num;
	public static volatile SingularAttribute<InboundOrderMaterialDetail, String> batch;
	public static volatile SingularAttribute<InboundOrderMaterialDetail, String> id;

	public static final String INBOUND_ORDER_MATERIAL = "inboundOrderMaterial";
	public static final String MATERIAL = "material";
	public static final String BIN = "bin";
	public static final String NUM = "num";
	public static final String BATCH = "batch";
	public static final String ID = "id";

}

