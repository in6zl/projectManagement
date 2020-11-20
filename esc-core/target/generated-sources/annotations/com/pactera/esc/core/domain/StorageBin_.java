package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StorageBin.class)
public abstract class StorageBin_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<StorageBin, String> code;
	public static volatile SingularAttribute<StorageBin, String> columnNum;
	public static volatile ListAttribute<StorageBin, Tag> faStorehouseTag;
	public static volatile SingularAttribute<StorageBin, String> rowNum;
	public static volatile SingularAttribute<StorageBin, String> id;
	public static volatile SingularAttribute<StorageBin, Integer> state;
	public static volatile SingularAttribute<StorageBin, Warehouse> warehouse;
	public static volatile SingularAttribute<StorageBin, Shelves> shelves;

	public static final String CODE = "code";
	public static final String COLUMN_NUM = "columnNum";
	public static final String FA_STOREHOUSE_TAG = "faStorehouseTag";
	public static final String ROW_NUM = "rowNum";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String WAREHOUSE = "warehouse";
	public static final String SHELVES = "shelves";

}

