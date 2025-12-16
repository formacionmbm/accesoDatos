package com.accesodatos.jpa.profeJPA.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import com.accesodatos.jpa.profeJPA.entities.Region;


public class RegionDAO {
	@PersistenceUnit(name = "UP")
	EntityManagerFactory emf;
	
	
	public Region find(Integer id) {
		
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("UP");
			EntityManager em= emf.createEntityManager();
			
			Region huerto = em.find(Region.class, id);
			return huerto;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
