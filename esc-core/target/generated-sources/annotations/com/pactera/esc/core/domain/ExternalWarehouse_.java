package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExternalWarehouse.class)
public abstract class ExternalWarehouse_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<ExternalWarehouse, String> address;
	public static volatile SetAttribute<ExternalWarehouse, Supplier> suppliers;
	public static volatile SingularAttribute<ExternalWarehouse, String> phone;
	public static volatile SingularAttribute<ExternalWarehouse, Double> latitude;
	public static volatile SingularAttribute<ExternalWarehouse, String> name;
	public static volatile SingularAttribute<ExternalWarehouse, String> description;
	public static volatile SingularAttribute<ExternalWarehouse, String> id;
	public static volatile SingularAttribute<ExternalWarehouse, Double> longitude;

	public static final String ADDRESS = "address";
	public static final String SUPPLIERS = "suppliers";
	public static final String PHONE = "phone";
	public static final String LATITUDE = "latitude";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String LONGITUDE = "longitude";

}

