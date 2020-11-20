package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Product, String> unitName;
	public static volatile SingularAttribute<Product, Integer> isDelete;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile ListAttribute<Product, ProductBom> productBomList;
	public static volatile SingularAttribute<Product, String> id;
	public static volatile SingularAttribute<Product, String> spec;
	public static volatile ListAttribute<Product, Tag> tags;

	public static final String UNIT_NAME = "unitName";
	public static final String IS_DELETE = "isDelete";
	public static final String NAME = "name";
	public static final String PRODUCT_BOM_LIST = "productBomList";
	public static final String ID = "id";
	public static final String SPEC = "spec";
	public static final String TAGS = "tags";

}

