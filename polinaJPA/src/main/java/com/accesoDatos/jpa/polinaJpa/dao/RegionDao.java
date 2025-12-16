package com.accesoDatos.jpa.polinaJpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesoDatos.jpa.polinaJpa.dao.interfaces.IDAO;
import com.accesoDatos.jpa.polinaJpa.entities.Region;
import com.accesoDatos.jpa.polinaJpa.exceptions.DAOException;

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
	public void create(Region item) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Region item) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer key) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
