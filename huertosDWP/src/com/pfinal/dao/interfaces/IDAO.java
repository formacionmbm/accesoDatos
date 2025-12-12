package com.pfinal.dao.interfaces;

import java.util.List;

import com.pfinal.common.DAOException;

public interface IDAO<T> extends  IDAOBasic<T>{

	public void grabar(Long id,Long idElement) throws DAOException;
}
