package com.front.pTipoAnt.bussines;

import java.lang.System.Logger;
import java.util.List;

import com.front.pTipoAnt.bussines.interfaces.IServicio;
import com.front.pTipoAnt.common.exceptions.DAOException;
import com.front.pTipoAnt.common.exceptions.ServicioException;
import com.front.pTipoAnt.common.exceptions.TipoException;
import com.front.pTipoAnt.dao.DepartamentoDAO;
import com.front.pTipoAnt.dao.interfaces.IDAO;
import com.front.pTipoAnt.data.Departamento;

public class ServDepartamento implements IServicio<Long,Departamento>{



	IDAO<Long,Departamento> iDao ;
	

	public ServDepartamento() {
		super();
		this.iDao = new DepartamentoDAO();
	}
	
	@Override
	public List<Departamento> findAll() throws ServicioException{


		try {
			return this.iDao.findAll();
		} catch (DAOException daoe) {

			throw new ServicioException(daoe);
		}catch (Exception e) {

			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public Departamento findOne(Long key) throws ServicioException{


		try {
			return this.iDao.findOne(key);
		} catch (DAOException daoe) {

			throw new ServicioException(daoe);
		}catch (Exception e) {

			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}
	

	@Override
	public void create(Departamento item) throws ServicioException {

		
		try {
			this.iDao.create(item);
		}catch(DAOException daoe ) {

			throw new ServicioException(daoe);
		}catch(Exception e) {

			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public void update(Departamento item) throws ServicioException{


		try {
			this.iDao.update(item);
		} catch (DAOException daoe) {

			throw new ServicioException(daoe);
		}catch (Exception e) {

			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public void delete(Long key) throws ServicioException{


		try {
			this.iDao.delete(key);
		} catch (DAOException daoe) {

			throw new ServicioException(daoe);
		}catch (Exception e) {

			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}
	

}
