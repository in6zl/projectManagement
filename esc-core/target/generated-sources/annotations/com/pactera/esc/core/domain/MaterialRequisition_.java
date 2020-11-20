package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaterialRequisition.class)
public abstract class MaterialRequisition_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<MaterialRequisition, Boolean> isCompile;
	public static volatile SingularAttribute<MaterialRequisition, String> code;
	public static volatile SingularAttribute<MaterialRequisition, WorkShop> workShop;
	public static volatile SingularAttribute<MaterialRequisition, String> description;
	public static volatile SingularAttribute<MaterialRequisition, String> id;

	public static final String IS_COMPILE = "isCompile";
	public static final String CODE = "code";
	public static final String WORK_SHOP = "workShop";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

