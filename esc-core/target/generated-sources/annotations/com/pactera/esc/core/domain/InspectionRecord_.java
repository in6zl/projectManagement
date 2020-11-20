package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InspectionRecord.class)
public abstract class InspectionRecord_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<InspectionRecord, String> pass;
	public static volatile SingularAttribute<InspectionRecord, ProductionRecord> record;
	public static volatile SingularAttribute<InspectionRecord, String> examiner;
	public static volatile SingularAttribute<InspectionRecord, String> id;
	public static volatile SingularAttribute<InspectionRecord, String> url;

	public static final String PASS = "pass";
	public static final String RECORD = "record";
	public static final String EXAMINER = "examiner";
	public static final String ID = "id";
	public static final String URL = "url";

}

