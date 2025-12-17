package com.accesodatos.jpa.cesarJPA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesodatos.jpa.cesarJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.cesarJPA.dao.interfaces.IDAO;
import com.accesodatos.jpa.cesarJPA.entities.Region;

public class RegionDAO implements IDAO<Long, Region>{
	
	EntityManagerFactory emf;
	
	public RegionDAO() {
		this.emf = Persistence.createEntityManagerFactory("cesarJPA");
	}

	@Override
	public List<Region> findAll() throws DAOException {
		List<Region> list;
		EntityManager em;
		try {
			em=emf.createEntityManager();
			list= em.createQuery("select r from Region r",Region.class).getResultList();
			return list;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

	@Override
	public Region findOne(Long id) throws DAOException {
		Region region;
		EntityManager em;
		try {
			em=emf.createEntityManager();
			region= em.find(Region.class, id);
			return region;
			
		}catch(Exception e) {
			
			throw new DAOException();
		}
	}

	@Override
	public void create(Region element) throws DAOException {
		EntityManager em;
		try {
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(element);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			throw new DAOException();
		}
		
	}

	@Override
	public void update(Region element) throws DAOException {
		EntityManager em;
		try {
			em=emf.createEntityManager();
			Region region = em.find(Region.class,element.getId());
			em.getTransaction().begin();
			region.setName(element.getName());
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			throw new DAOException();
		}
		
	}

	@Override
	public void delete(Long id) throws DAOException {
		EntityManager em;
		try {
			em=emf.createEntityManager();
			Region region = em.find(Region.class,id);
			em.getTransaction().begin();
			em.remove(region);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			throw new DAOException();
		}
		
	}
	
	

}
