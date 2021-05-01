package com.uqac.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Singleton {
	private static EntityManagerFactory emf=null;
	
	
	
	private Singleton() {
		emf =Persistence.createEntityManagerFactory("jpa"); 
	}
	
	public static EntityManagerFactory getEntityManagetFactory() {
		if(emf==null) new Singleton();
		return emf;
	}

}
