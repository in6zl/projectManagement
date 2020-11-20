package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductDeliveryRequisitionDetails.class)
public abstract class ProductDeliveryRequisitionDetails_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<ProductDeliveryRequisitionDetails, Product> product;
	public static volatile SingularAttribute<ProductDeliveryRequisitionDetails, Double> num;
	public static volatile SingularAttribute<ProductDeliveryRequisitionDetails, String> id;
	public static volatile SingularAttribute<ProductDeliveryRequisitionDetails, ProductDeliveryRequisition> productDeliveryRequisition;

	public static final String PRODUCT = "product";
	public static final String NUM = "num";
	public static final String ID = "id";
	public static final String PRODUCT_DELIVERY_REQUISITION = "productDeliveryRequisition";

}

