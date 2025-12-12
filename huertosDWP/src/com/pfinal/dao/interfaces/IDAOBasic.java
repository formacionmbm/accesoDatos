package com.pfinal.dao.interfaces;

import java.util.List;

import com.pfinal.common.DAOException;

public interface IDAOBasic<T> {

	public T find(Long id) throws DAOException;
	public List<T> findAll() throws DAOException;
	public void crear(T element) throws DAOException;
	public void grabar(Long id, String nombre) throws DAOException;
	public void delete(Long id) throws DAOException;
	public Long findLastId() throws DAOException;
}
