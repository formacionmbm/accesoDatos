package com.accesodatos.jpa.gelmaryJPA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesodatos.jpa.gelmaryJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.gelmaryJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.gelmaryJPA.entities.Region;

public class RegionDAO implements IDAO<Integer, Region>{

	EntityManagerFactory emf;

	public RegionDAO() {
		this.emf = Persistence.createEntityManagerFactory("UP");
	}

	@Override
	public List<Region> findAll() throws DAOException {
		List<Region> list;
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			list = em.createQuery("select r from Region r", Region.class).getResultList();
			return list;
			
		} catch (Exception e) {
			throw new DAOException();
		}
		
	}

	@Override
	public Region findOne(Integer id) throws DAOException {
		Region region;
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			region = em.find(Region.class, id);
			return region;
			
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public void create(Region region) throws DAOException {
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(region); //meter el objeto en el contexto de persistencia
			em.getTransaction().commit();
			
		} catch (Exception e) {
			throw new DAOException();
		}
		
	}

	@Override
	public void update(Region region) throws DAOException {
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			em.getTransaction().begin();
			//em.merge(region); //actualizar el objeto en el contexto de persistencia
			
			Region region1 = em.find(Region.class, region.getId());
			region1.setNombre(region.getNombre());
			em.getTransaction().commit();
			
		} catch (Exception e) {
			throw new DAOException();
		}
		
		
	}

	@Override
	public void delete(Integer id) throws DAOException {
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			em.getTransaction().begin();
			//em.merge(region); //actualizar el objeto en el contexto de persistencia
			
			Region region1 = em.find(Region.class, id);
			em.remove(region1);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			throw new DAOException();
		}
	}
	
	
	
	
}
