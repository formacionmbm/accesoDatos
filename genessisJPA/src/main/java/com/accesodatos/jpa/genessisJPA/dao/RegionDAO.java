package com.accesodatos.jpa.genessisJPA.dao;
//dependencias de Maven: proveedor de persistencias, diver y trazas

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesodatos.jpa.genessisJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.genessisJPA.dao.interfaces.IDAO;
import com.accesodatos.jpa.genessisJPA.entities.Region;



public class RegionDAO implements IDAO<Integer,Region>{    //IDAO: cinco métodos
	
	EntityManagerFactory emf;
	
	
	public RegionDAO()  {
		this.emf = Persistence.createEntityManagerFactory("UP");
	}

	
	public List<Region> findAll() throws DAOException  {
	  EntityManager em = this.emf.createEntityManager();
	  List<Region> listado = em.createQuery("select r from region").getResultList();
		return listado;
	}
	
	
	public Region findOne(Integer key) throws DAOException {
		EntityManager em = this.emf.createEntityManager();
		Region region = em.find(Region.class, key);
		return region;
	}
	
	
	public void create (Region region) throws DAOException {
		EntityManager em = this.emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(region);
		em.getTransaction().commit();
	}
	
	
	public void update (Region region) throws DAOException{
		EntityManager em = this.emf.createEntityManager();
		em.getTransaction().begin();
//		em.merge(region);
		Region region1 = em.find(Region.class, region.getId());
		region1.setNombre(region.getNombre());
		em.getTransaction().commit();
	}
	
	public void delete (Integer id) throws DAOException{
		EntityManager em = this.emf.createEntityManager();
		em.getTransaction().begin();
		Region region1 = em.find(Region.class, id);
		em.remove(region1);
		em.getTransaction().commit();
	}


}

