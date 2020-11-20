package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SaleDetails.class)
public abstract class SaleDetails_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<SaleDetails, Sale> sale;
	public static volatile SingularAttribute<SaleDetails, Product> product;
	public static volatile SingularAttribute<SaleDetails, String> unit;
	public static volatile SingularAttribute<SaleDetails, Double> deliveryNum;
	public static volatile SingularAttribute<SaleDetails, Double> num;
	public static volatile SingularAttribute<SaleDetails, String> id;
	public static volatile SingularAttribute<SaleDetails, Byte> isCompileProduct;

	public static final String SALE = "sale";
	public static final String PRODUCT = "product";
	public static final String UNIT = "unit";
	public static final String DELIVERY_NUM = "deliveryNum";
	public static final String NUM = "num";
	public static final String ID = "id";
	public static final String IS_COMPILE_PRODUCT = "isCompileProduct";

}

