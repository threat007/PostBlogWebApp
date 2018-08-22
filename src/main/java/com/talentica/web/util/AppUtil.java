package com.talentica.web.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppUtil {

	private static EntityManagerFactory emf;
	private static EntityManager entityManager;

	public static EntityManager beginTransaction(){
		emf = Persistence.createEntityManagerFactory("databaseConnection");
		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		return entityManager;
	}

	public static void commitTransaction(){
		entityManager.getTransaction().commit();
	}
}
