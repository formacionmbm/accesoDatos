package com.accesoDatos.jpa.polinaJpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesoDatos.jpa.polinaJpa.dao.interfaces.IDAO;
import com.accesoDatos.jpa.polinaJpa.entities.Pais;
import com.accesoDatos.jpa.polinaJpa.entities.Region;
import com.accesoDatos.jpa.polinaJpa.exceptions.DAOException;

public class PaisDao implements IDAO<String, Pais> {

	EntityManagerFactory emf;
	
	public PaisDao() {
		this.emf = Persistence.createEntityManagerFactory("UP");
		
	}

	@Override
	public List<Pais> findAll() throws DAOException {
		
		EntityManager em = this.emf.createEntityManager(); 
		List<Pais> listado = em.createQuery("select p from Pais p").getResultList();
		return listado;
	}

	@Override
	public Pais findOne(String key) throws DAOException {
		EntityManager em = this.emf.createEntityManager(); 
		Pais pais = em.find(Pais.class, key);
		return pais;
	}

	@Override
	public void create(Pais pais) throws DAOException {
		EntityManager em = this.emf.createEntityManager(); 
		em.getTransaction().begin();
		em.persist(pais);
		em.getTransaction().commit();
	}

	@Override
	public void update(Pais pais) throws DAOException {
		EntityManager em = this.emf.createEntityManager(); 
		em.getTransaction().begin();
		Pais pais1 = em.find(Pais.class, pais.getId());
		pais1.setNombre(pais.getNombre());
		Region region = em.find(Region.class, pais.getRegion().getId());
		pais1.setRegion(region);
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(String id) throws DAOException {
		EntityManager em = this.emf.createEntityManager(); 
		em.getTransaction().begin();
		Pais pais1 = em.find(Pais.class, id);
		em.remove(pais1);
		em.getTransaction().commit();
		
	}

}
