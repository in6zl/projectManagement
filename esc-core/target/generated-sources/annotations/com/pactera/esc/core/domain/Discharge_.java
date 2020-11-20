package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Discharge.class)
public abstract class Discharge_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Discharge, Material> material;
	public static volatile SingularAttribute<Discharge, String> num;
	public static volatile SingularAttribute<Discharge, String> id;
	public static volatile SingularAttribute<Discharge, Invoice> invoice;
	public static volatile SingularAttribute<Discharge, Warehouse> warehouse;

	public static final String MATERIAL = "material";
	public static final String NUM = "num";
	public static final String ID = "id";
	public static final String INVOICE = "invoice";
	public static final String WAREHOUSE = "warehouse";

}

