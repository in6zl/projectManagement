package com.pactera.esc.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Material.class)
public abstract class Material_ {

	public static volatile SingularAttribute<Material, String> code;
	public static volatile SingularAttribute<Material, String> unitName;
	public static volatile SingularAttribute<Material, Integer> isDelete;
	public static volatile SingularAttribute<Material, String> name;
	public static volatile SingularAttribute<Material, String> id;
	public static volatile SingularAttribute<Material, String> spec;
	public static volatile ListAttribute<Material, Tag> tags;

	public static final String CODE = "code";
	public static final String UNIT_NAME = "unitName";
	public static final String IS_DELETE = "isDelete";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String SPEC = "spec";
	public static final String TAGS = "tags";

}

