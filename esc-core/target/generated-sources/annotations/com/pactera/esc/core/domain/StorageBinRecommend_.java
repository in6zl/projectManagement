package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StorageBinRecommend.class)
public abstract class StorageBinRecommend_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<StorageBinRecommend, Integer> tagOrder;
	public static volatile SingularAttribute<StorageBinRecommend, Integer> isBinState;
	public static volatile SingularAttribute<StorageBinRecommend, Integer> entranceOrder;
	public static volatile SingularAttribute<StorageBinRecommend, Integer> binLockTime;
	public static volatile SingularAttribute<StorageBinRecommend, String> warehouseId;
	public static volatile SingularAttribute<StorageBinRecommend, Integer> exitValue;
	public static volatile SingularAttribute<StorageBinRecommend, Integer> distanceOrder;
	public static volatile SingularAttribute<StorageBinRecommend, Integer> distanceValue;
	public static volatile SingularAttribute<StorageBinRecommend, String> id;
	public static volatile SingularAttribute<StorageBinRecommend, Integer> exitOrder;
	public static volatile SingularAttribute<StorageBinRecommend, Integer> entranceValue;

	public static final String TAG_ORDER = "tagOrder";
	public static final String IS_BIN_STATE = "isBinState";
	public static final String ENTRANCE_ORDER = "entranceOrder";
	public static final String BIN_LOCK_TIME = "binLockTime";
	public static final String WAREHOUSE_ID = "warehouseId";
	public static final String EXIT_VALUE = "exitValue";
	public static final String DISTANCE_ORDER = "distanceOrder";
	public static final String DISTANCE_VALUE = "distanceValue";
	public static final String ID = "id";
	public static final String EXIT_ORDER = "exitOrder";
	public static final String ENTRANCE_VALUE = "entranceValue";

}

