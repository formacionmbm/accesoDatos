package com.accesodatos.jpa.victhorJPAdao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.accesodatos.jpa.victhorJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.victhorJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.victhorJPA.entities.Pais;

public class PaisDAO implements IDAO<Integer,Pais>{

	@PersistenceUnit
	EntityManagerFactory emf;
	
	@Override
	public List<Pais> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pais findOne(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Pais element) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Pais element) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
