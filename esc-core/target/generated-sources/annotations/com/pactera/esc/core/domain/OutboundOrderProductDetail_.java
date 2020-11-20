package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OutboundOrderProductDetail.class)
public abstract class OutboundOrderProductDetail_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<OutboundOrderProductDetail, Product> product;
	public static volatile SingularAttribute<OutboundOrderProductDetail, OutboundOrderProduct> outboundOrderProduct;
	public static volatile SingularAttribute<OutboundOrderProductDetail, StorageBin> bin;
	public static volatile SingularAttribute<OutboundOrderProductDetail, Integer> num;
	public static volatile SingularAttribute<OutboundOrderProductDetail, String> id;

	public static final String PRODUCT = "product";
	public static final String OUTBOUND_ORDER_PRODUCT = "outboundOrderProduct";
	public static final String BIN = "bin";
	public static final String NUM = "num";
	public static final String ID = "id";

}

