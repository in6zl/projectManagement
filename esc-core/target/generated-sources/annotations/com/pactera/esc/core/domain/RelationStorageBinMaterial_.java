package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RelationStorageBinMaterial.class)
public abstract class RelationStorageBinMaterial_ {

	public static volatile SingularAttribute<RelationStorageBinMaterial, Material> material;
	public static volatile SingularAttribute<RelationStorageBinMaterial, StorageBin> storageBin;
	public static volatile SingularAttribute<RelationStorageBinMaterial, Double> num;
	public static volatile SingularAttribute<RelationStorageBinMaterial, String> Id;

	public static final String MATERIAL = "material";
	public static final String STORAGE_BIN = "storageBin";
	public static final String NUM = "num";
	public static final String ID = "Id";

}

