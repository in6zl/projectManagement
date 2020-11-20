package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RelationStorageBinProduct.class)
public abstract class RelationStorageBinProduct_ {

	public static volatile SingularAttribute<RelationStorageBinProduct, Product> product;
	public static volatile SingularAttribute<RelationStorageBinProduct, StorageBin> storageBin;
	public static volatile SingularAttribute<RelationStorageBinProduct, Double> num;
	public static volatile SingularAttribute<RelationStorageBinProduct, String> Id;

	public static final String PRODUCT = "product";
	public static final String STORAGE_BIN = "storageBin";
	public static final String NUM = "num";
	public static final String ID = "Id";

}

