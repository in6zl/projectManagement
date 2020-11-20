package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OutboundOrderMaterialDetail.class)
public abstract class OutboundOrderMaterialDetail_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<OutboundOrderMaterialDetail, Material> material;
	public static volatile SingularAttribute<OutboundOrderMaterialDetail, OutboundOrderMaterial> outboundOrderMaterial;
	public static volatile SingularAttribute<OutboundOrderMaterialDetail, StorageBin> bin;
	public static volatile SingularAttribute<OutboundOrderMaterialDetail, Integer> num;
	public static volatile SingularAttribute<OutboundOrderMaterialDetail, String> id;

	public static final String MATERIAL = "material";
	public static final String OUTBOUND_ORDER_MATERIAL = "outboundOrderMaterial";
	public static final String BIN = "bin";
	public static final String NUM = "num";
	public static final String ID = "id";

}

