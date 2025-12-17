package com.accesodatos.jpa.cesarJPA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesodatos.jpa.cesarJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.cesarJPA.dao.interfaces.IDAO;
import com.accesodatos.jpa.cesarJPA.entities.Pais;

public class PaisDAO implements IDAO<String, Pais>{
	
	EntityManagerFactory emf;
	
	public PaisDAO() {
		this.emf = Persistence.createEntityManagerFactory("cesarJPA");
	}

	@Override
	public List<Pais> findAll() throws DAOException {
		List<Pais> list;
		EntityManager em;
		try {
			em=emf.createEntityManager();
			list= em.createQuery("select p from Pais p",Pais.class).getResultList();
			return list;
			
		}catch(Exception e) {
			
			throw new DAOException();
		}
	}

	@Override
	public Pais findOne(String id) throws DAOException {
		Pais pais;
		EntityManager em;
		try {
			em=emf.createEntityManager();
			pais= em.find(Pais.class, id);
			return pais;
			
		}catch(Exception e) {
			
			throw new DAOException();
		}
	}

	@Override
	public void create(Pais element) throws DAOException {
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
	public void update(Pais element) throws DAOException {
		EntityManager em;
		try {
			em=emf.createEntityManager();
			Pais pais = em.find(Pais.class,element.getId());
			em.getTransaction().begin();
			pais.setName(element.getName());
			pais.setRegion(element.getRegion());
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			throw new DAOException();
		}
		
	}

	@Override
	public void delete(String id) throws DAOException {
		EntityManager em;
		try {
			em=emf.createEntityManager();
			Pais pais = em.find(Pais.class,id);
			em.getTransaction().begin();
			em.remove(pais);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			throw new DAOException();
		}
		
	}
	
	

}