package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WorkShop.class)
public abstract class WorkShop_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<WorkShop, String> address;
	public static volatile SingularAttribute<WorkShop, String> manager;
	public static volatile SingularAttribute<WorkShop, String> phone;
	public static volatile SingularAttribute<WorkShop, String> name;
	public static volatile SingularAttribute<WorkShop, String> id;
	public static volatile ListAttribute<WorkShop, WorkShopMaterialRelation> workShopMaterialRelationList;

	public static final String ADDRESS = "address";
	public static final String MANAGER = "manager";
	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String WORK_SHOP_MATERIAL_RELATION_LIST = "workShopMaterialRelationList";

}

