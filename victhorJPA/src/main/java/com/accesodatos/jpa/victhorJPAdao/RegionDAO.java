package com.accesodatos.jpa.victhorJPAdao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import com.accesodatos.jpa.victhorJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.victhorJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.victhorJPA.entities.Departamento;
import com.accesodatos.jpa.victhorJPA.entities.Region;


public class RegionDAO implements IDAO<Integer, Region>{
	@PersistenceUnit(name = "UP")
	EntityManagerFactory emf;

	
	
	public RegionDAO() {
		this.emf=Persistence.createEntityManagerFactory("UP");
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
	public Region findOne(Integer id) throws DAOException {
		try{
			
			EntityManager em= emf.createEntityManager();
			
			Region region = em.find(Region.class, id);
			return region;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}


	@Override
	public void create(Region element) throws DAOException {
		
		try {
			
			EntityManager em= emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(element);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			throw new DAOException();
		}
		
	}


	@Override
	public void update(Region element) throws DAOException {
		
		try {
			
			EntityManager em= emf.createEntityManager();
			Region region = em.find(Region.class,element.getId());
			em.getTransaction().begin();
			region.setNombre(element.getNombre());
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			throw new DAOException();
		}
		
	}


	@Override
	public void delete(Integer id) throws DAOException {
		
		try {
			
			EntityManager em= emf.createEntityManager();
			Region region = em.find(Region.class,id);
			em.getTransaction().begin();
			em.remove(region);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			throw new DAOException();
		}
		
	}

}
