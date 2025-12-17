package com.accesodatos.jpa.gelmaryJPA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesodatos.jpa.gelmaryJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.gelmaryJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.gelmaryJPA.entities.Pais;
import com.accesodatos.jpa.gelmaryJPA.entities.Region;

public class PaisDAO implements IDAO<String, Pais>{

	EntityManagerFactory emf;

	public PaisDAO() {
		this.emf = Persistence.createEntityManagerFactory("UP");
	}

	@Override
	public List<Pais> findAll() throws DAOException {
		List<Pais> list;
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			list = em.createQuery("select p from Pais p", Pais.class).getResultList();
			return list;
			
		} catch (Exception e) {
			throw new DAOException();
		}
		
	}

	@Override
	public Pais findOne(String id) throws DAOException {
		Pais pais;
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			pais = em.find(Pais.class, id);
			return pais;
			
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public void create(Pais pais) throws DAOException {
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(pais); //meter el objeto en el contexto de persistencia
			em.getTransaction().commit();
			
		} catch (Exception e) {
			throw new DAOException();
		}
		
	}

	@Override
	public void update(Pais pais) throws DAOException {
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			em.getTransaction().begin();
			//em.merge(region); //actualizar el objeto en el contexto de persistencia
			
			Pais pais1 = em.find(Pais.class, pais.getId());
			pais1.setNombre(pais.getNombre());
			Region region = em.find(Region.class, pais.getRegion().getId());
			pais1.setRegion(region);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			throw new DAOException();
		}
		
		
	}

	@Override
	public void delete(String id) throws DAOException {
		EntityManager em;
		
		try {
			em=this.emf.createEntityManager();
			em.getTransaction().begin();
			//em.merge(region); //actualizar el objeto en el contexto de persistencia
			
			Pais pais1 = em.find(Pais.class, id);
			em.remove(pais1);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			throw new DAOException();
		}
	}
	
	
	
	
}
