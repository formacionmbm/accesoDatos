package com.accesodatos.jpa.gelmaryJPA.dao.interfaes;

import java.util.List;

import com.accesodatos.jpa.gelmaryJPA.common.exceptions.DAOException;

public interface IDAO<K, T> {
	
	public List<T> findAll() throws DAOException;
	public T findOne(K id) throws DAOException;
	public void create(T element) throws DAOException;
	public void update(T element) throws DAOException;
	public void delete(K id) throws DAOException;

}
