package br.ufc.coop.java.dbjpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {
	
	private EntityManagerFactory entityManagerFactory;

	public void setUp() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		entityManagerFactory = Persistence.createEntityManagerFactory("school-database");
	}
	
	public EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
}
