package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Resource.class)
public abstract class Resource_ extends com.pactera.esc.core.domain.BaseEntity_ {

	public static volatile SingularAttribute<Resource, Integer> level;
	public static volatile SingularAttribute<Resource, String> name;
	public static volatile SingularAttribute<Resource, String> icon;
	public static volatile SingularAttribute<Resource, String> permission;
	public static volatile SingularAttribute<Resource, String> id;
	public static volatile SingularAttribute<Resource, Integer> sort;
	public static volatile SingularAttribute<Resource, Integer> type;
	public static volatile SingularAttribute<Resource, String> title;
	public static volatile SingularAttribute<Resource, String> url;
	public static volatile SingularAttribute<Resource, String> parentId;

	public static final String LEVEL = "level";
	public static final String NAME = "name";
	public static final String ICON = "icon";
	public static final String PERMISSION = "permission";
	public static final String ID = "id";
	public static final String SORT = "sort";
	public static final String TYPE = "type";
	public static final String TITLE = "title";
	public static final String URL = "url";
	public static final String PARENT_ID = "parentId";

}

