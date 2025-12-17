package com.accesodatos.jpa.profeJPA.dao.interfaces;

import java.util.List;

import com.accesodatos.jpa.profeJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.profeJPA.entities.Region;


public class IDAO {
	List<T> findAll() throws DAOException;

	T findOne(K key) throws DAOException;

	void create(T item) throws DAOException;

	void update(T item) throws DAOException;

	void delete(K key) throws DAOException;
}
