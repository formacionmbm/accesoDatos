package com.accesodatos.jpa.profeJPA.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import com.accesodatos.jpa.profeJPA.entities.Pais;


public class PaisDAO {
	@PersistenceUnit(name = "UP")
	EntityManagerFactory emf;
	
	
	public Pais find(Integer id) {
		
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("UP");
			EntityManager em= emf.createEntityManager();
			
			Pais huerto = em.find(Pais.class, id);
			return huerto;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
