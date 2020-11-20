package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dict.class)
public abstract class Dict_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Dict, String> dictValue;
	public static volatile SingularAttribute<Dict, String> id;
	public static volatile SingularAttribute<Dict, String> dictKey;
	public static volatile SingularAttribute<Dict, DictType> dictType;

	public static final String DICT_VALUE = "dictValue";
	public static final String ID = "id";
	public static final String DICT_KEY = "dictKey";
	public static final String DICT_TYPE = "dictType";

}

