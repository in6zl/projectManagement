package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InboundOrderProductDetail.class)
public abstract class InboundOrderProductDetail_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<InboundOrderProductDetail, Product> product;
	public static volatile SingularAttribute<InboundOrderProductDetail, StorageBin> bin;
	public static volatile SingularAttribute<InboundOrderProductDetail, Integer> num;
	public static volatile SingularAttribute<InboundOrderProductDetail, InboundOrderProduct> inboundOrderProduct;
	public static volatile SingularAttribute<InboundOrderProductDetail, String> id;

	public static final String PRODUCT = "product";
	public static final String BIN = "bin";
	public static final String NUM = "num";
	public static final String INBOUND_ORDER_PRODUCT = "inboundOrderProduct";
	public static final String ID = "id";

}

