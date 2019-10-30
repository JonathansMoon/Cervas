package com.cervas.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estilo.class)
public abstract class Estilo_ {

	public static volatile SingularAttribute<Estilo, Long> codigo;
	public static volatile SingularAttribute<Estilo, String> nome;
	public static volatile ListAttribute<Estilo, Cerveja> cervejas;

	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String CERVEJAS = "cervejas";

}

