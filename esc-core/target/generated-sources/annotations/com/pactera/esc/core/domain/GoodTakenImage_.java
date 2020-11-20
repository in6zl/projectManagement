package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GoodTakenImage.class)
public abstract class GoodTakenImage_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<GoodTakenImage, String> path;
	public static volatile SingularAttribute<GoodTakenImage, InvoiceDeliveryInfo> delivery;
	public static volatile SingularAttribute<GoodTakenImage, String> id;

	public static final String PATH = "path";
	public static final String DELIVERY = "delivery";
	public static final String ID = "id";

}

