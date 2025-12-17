package com.accesodatos.jpa.genessisJPA.dao.interfaces;

import java.util.List;

import com.accesodatos.jpa.genessisJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.genessisJPA.entities.Region;


public interface IDAO<K,T> {
	List<T> findAll() throws DAOException;

	T findOne(K key) throws DAOException;

	void create(T item) throws DAOException;

	void update(T item) throws DAOException;

	void delete(K key) throws DAOException;
}
