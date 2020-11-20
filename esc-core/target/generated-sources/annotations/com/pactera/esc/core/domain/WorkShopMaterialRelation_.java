package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WorkShopMaterialRelation.class)
public abstract class WorkShopMaterialRelation_ {

	public static volatile SingularAttribute<WorkShopMaterialRelation, WorkShop> workShop;
	public static volatile SingularAttribute<WorkShopMaterialRelation, Material> material;
	public static volatile SingularAttribute<WorkShopMaterialRelation, Double> num;

	public static final String WORK_SHOP = "workShop";
	public static final String MATERIAL = "material";
	public static final String NUM = "num";

}

