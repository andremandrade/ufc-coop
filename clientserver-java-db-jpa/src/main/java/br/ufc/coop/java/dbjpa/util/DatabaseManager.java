package br.ufc.coop.java.dbjpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {
	
	private static EntityManagerFactory entityManagerFactory;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			entityManagerFactory = Persistence.createEntityManagerFactory("school-database");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
}
