package com.pactera.esc.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaterialOrder.class)
public abstract class MaterialOrder_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<MaterialOrder, Date> documentDate;
	public static volatile SingularAttribute<MaterialOrder, String> note;
	public static volatile SingularAttribute<MaterialOrder, String> unit;
	public static volatile SingularAttribute<MaterialOrder, Double> amount;
	public static volatile SingularAttribute<MaterialOrder, Integer> deliveryNum;
	public static volatile SingularAttribute<MaterialOrder, String> orderNo;
	public static volatile SingularAttribute<MaterialOrder, Material> material;
	public static volatile SingularAttribute<MaterialOrder, Integer> num;
	public static volatile SingularAttribute<MaterialOrder, Supplier> supplier;
	public static volatile SingularAttribute<MaterialOrder, String> id;
	public static volatile SingularAttribute<MaterialOrder, Date> deliveryDate;

	public static final String DOCUMENT_DATE = "documentDate";
	public static final String NOTE = "note";
	public static final String UNIT = "unit";
	public static final String AMOUNT = "amount";
	public static final String DELIVERY_NUM = "deliveryNum";
	public static final String ORDER_NO = "orderNo";
	public static final String MATERIAL = "material";
	public static final String NUM = "num";
	public static final String SUPPLIER = "supplier";
	public static final String ID = "id";
	public static final String DELIVERY_DATE = "deliveryDate";

}

