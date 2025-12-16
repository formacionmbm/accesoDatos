package com.accesodatos.jpa.victhorJPAdao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import com.accesodatos.jpa.victhorJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.victhorJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.victhorJPA.entities.Pais;

public class PaisDAO implements IDAO<Integer,Pais>{

	@PersistenceUnit
	EntityManagerFactory emf;
	
	EntityManager em;
	
	@Override
	public List<Pais> findAll() throws DAOException {
		List<Pais> list;
		
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			em = emf.createEntityManager();
			list = em.createQuery("select p from Pais p",Pais.class).getResultList();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}
		
	}

	@Override
	public Pais findOne(Integer id) throws DAOException {
		
		try {
			emf=Persistence.createEntityManagerFactory("UP");
			em=emf.createEntityManager();
			
			Pais pais = em.find(Pais.class, id);
			return pais;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

	@Override
	public void create(Pais element) throws DAOException {
		try {
			emf=Persistence.createEntityManagerFactory("UP");
			em=emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(element);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}
		
	}

	@Override
	public void update(Pais element) throws DAOException {
		emf=Persistence.createEntityManagerFactory("UP");
		em=emf.createEntityManager();
		
		Pais pais=em.find(Pais.class,element.getId());
		
		em.getTransaction().begin();
		pais.setNombre(element.getNombre());
		pais.setRegion(element.getRegion());
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(Integer id) throws DAOException {
		try {
			emf=Persistence.createEntityManagerFactory("UP");
			em=emf.createEntityManager();
			
			Pais pais=em.find(Pais.class, id);
			
			em.getTransaction().begin();
			em.remove(pais);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}
		
	}

}
