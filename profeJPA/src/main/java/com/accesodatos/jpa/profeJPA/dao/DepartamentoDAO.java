package com.accesodatos.jpa.profeJPA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.accesodatos.jpa.profeJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.profeJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.profeJPA.entities.Departamento;

public class DepartamentoDAO implements IDAO<Long, Departamento> {
	EntityManagerFactory emf;
	
	public DepartamentoDAO() {
		this.emf=Persistence.createEntityManagerFactory("UP");
	}
	
	@Override
	public List<Departamento> findAll() throws DAOException {
		List<Departamento> list;
		EntityManager em;
		try {
			em=emf.createEntityManager();
			list= em.createQuery("select d from Departamento d",Departamento.class).getResultList();
			return list;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

	@Override
	public Departamento findOne(Long id) throws DAOException {
		Departamento departamento;
		EntityManager em;
		try {
			em=emf.createEntityManager();
			departamento= em.find(Departamento.class, id);
			return departamento;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

	@Override
	public void create(Departamento element) throws DAOException {
		EntityManager em;
		try {
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
	public void update(Departamento element) throws DAOException {
		EntityManager em;
		try {
			em=emf.createEntityManager();
			Departamento departamento = em.find(Departamento.class,element.getId());
			em.getTransaction().begin();
			departamento.setNombre(element.getNombre());
			departamento.setIdManager(element.getIdManager());
			departamento.setIdDireccion(element.getIdDireccion());
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			throw new DAOException();
		}

	}

	@Override
	public void delete(Long id) throws DAOException {
		EntityManager em;
		try {
			em=emf.createEntityManager();
			Departamento departamento = em.find(Departamento.class,id);
			em.getTransaction().begin();
			em.remove(departamento);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			throw new DAOException();
		}

	}

}
