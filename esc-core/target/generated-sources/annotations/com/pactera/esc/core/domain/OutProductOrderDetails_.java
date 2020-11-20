package com.pactera.esc.core.domain;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OutProductOrderDetails.class)
public abstract class OutProductOrderDetails_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<OutProductOrderDetails, Product> product;
	public static volatile SingularAttribute<OutProductOrderDetails, String> creator;
	public static volatile SingularAttribute<OutProductOrderDetails, LocalDateTime> createTime;
	public static volatile SingularAttribute<OutProductOrderDetails, Double> num;
	public static volatile SingularAttribute<OutProductOrderDetails, LocalDateTime> updateTime;
	public static volatile SingularAttribute<OutProductOrderDetails, String> id;
	public static volatile SingularAttribute<OutProductOrderDetails, OutProductOrder> outProductOrder;
	public static volatile SingularAttribute<OutProductOrderDetails, String> operator;

	public static final String PRODUCT = "product";
	public static final String CREATOR = "creator";
	public static final String CREATE_TIME = "createTime";
	public static final String NUM = "num";
	public static final String UPDATE_TIME = "updateTime";
	public static final String ID = "id";
	public static final String OUT_PRODUCT_ORDER = "outProductOrder";
	public static final String OPERATOR = "operator";

}

