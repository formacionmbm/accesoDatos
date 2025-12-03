package com.front.pTipoAnt.common.exceptions;



import org.apache.log4j.Logger;

import com.front.pTipoAnt.bussines.ServDepartamento;

@SuppressWarnings("serial")
public class ServicioException extends Exception {
	TipoException tipoExcepcion;

	private static final Logger log = Logger.getLogger(ServDepartamento.class);

	public ServicioException(TipoException tipoExcepcion) {
		super();
		log.error(tipoExcepcion.getMensaje());
		this.tipoExcepcion = tipoExcepcion;
	}

	public ServicioException(DAOException daoe) {
		super();
		switch(daoe.tipoExcepcion) {

		case ELEMENTO_NO_ENCONTRADO:
			this.tipoExcepcion= TipoException.ELEMENTO_NO_ENCONTRADO;
			break;
		case ELEMENTO_NO_CREADO:
			this.tipoExcepcion= TipoException.OPERACION_NO_REALIZADA;
			break;
		case ELEMENTO_NO_ACTUALIZADO:
			this.tipoExcepcion= TipoException.OPERACION_NO_REALIZADA;
			break;
		case ELEMENTO_NO_ELIMINADO:
			this.tipoExcepcion= TipoException.OPERACION_NO_REALIZADA;
			break;
		default:
			this.tipoExcepcion= TipoException.EXCEPCION_DAO;
			break;
		}
		log.error(this.tipoExcepcion.getMensaje());

	}

	public TipoException getTipoExcepcion() {
		return tipoExcepcion;
	}	
	

}
