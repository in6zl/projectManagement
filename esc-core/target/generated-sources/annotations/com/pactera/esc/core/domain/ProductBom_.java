package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductBom.class)
public abstract class ProductBom_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<ProductBom, Product> product;
	public static volatile SingularAttribute<ProductBom, Material> material;
	public static volatile SingularAttribute<ProductBom, Double> num;
	public static volatile SingularAttribute<ProductBom, String> id;

	public static final String PRODUCT = "product";
	public static final String MATERIAL = "material";
	public static final String NUM = "num";
	public static final String ID = "id";

}

