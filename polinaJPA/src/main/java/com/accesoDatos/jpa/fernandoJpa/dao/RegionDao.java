package com.accesoDatos.jpa.fernandoJpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesoDatos.jpa.fernandoJpa.dao.interfaces.IDAO;
import com.accesoDatos.jpa.fernandoJpa.entities.Region;
import com.accesoDatos.jpa.fernandoJpa.exceptions.DAOException;

public class RegionDao implements IDAO<Integer, Region> {

	EntityManagerFactory emf;
	
	public RegionDao() {
		this.emf = Persistence.createEntityManagerFactory("UP");
		
	}

	@Override
	public List<Region> findAll() throws DAOException {
		
		EntityManager em = this.emf.createEntityManager(); 
		List<Region> listado = em.createQuery("select r from Region r").getResultList();
		return listado;
	}

	@Override
	public Region findOne(Integer key) throws DAOException {
		EntityManager em = this.emf.createEntityManager(); 
		Region region = em.find(Region.class, key);
		return region;
	}

	@Override
	public void create(Region region) throws DAOException {
		EntityManager em = this.emf.createEntityManager(); 
		em.getTransaction().begin();
		em.persist(region);
		em.getTransaction().commit();
	}

	@Override
	public void update(Region region) throws DAOException {
		EntityManager em = this.emf.createEntityManager(); 
		em.getTransaction().begin();
//		em.merge(region);
		Region region1 = em.find(Region.class, region.getId());
		region1.setNombre(region.getNombre());
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(Integer id) throws DAOException {
		EntityManager em = this.emf.createEntityManager(); 
		em.getTransaction().begin();
		Region region1 = em.find(Region.class, id);
		em.remove(region1);
		em.getTransaction().commit();
		
	}

}
