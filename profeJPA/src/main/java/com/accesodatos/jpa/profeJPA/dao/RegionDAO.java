package com.accesodatos.jpa.profeJPA.dao.;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.accesodatos.jpa.profeJPA.dao.interfaces.IDAO;
import com.accesodatos.jpa.profeJPA.entities.Region;
import com.accesodatos.jpa.profeJPA.exceptions.DAOException;

public class RegionDAO implements IDAO{
	
	EntityManagerFactory emf;
	
	public RegionDAO() {
		this.emf = Persistence.createEntityManagerFactory("UP")
	}

	public List<Region> findAll() throws DAOException {
	  EntityManager em = this.emf.createEntityManager();
	  List<Region> listado = em.createQuery("selectT r from region").getResultList();
		return listado;
	}
	
	public Region findOne(Integer key) throws DAOException {
		EntityManager em = this.emf.createEntityManager();
		  Region region = em.find(Region.class, key);
		return region;
	}
	
}