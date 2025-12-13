package com.front.pTipoAnt.common.exceptions;

@SuppressWarnings("serial")
public class ServicioException extends Exception {
	TipoException tipoExcepcion;



	public ServicioException(TipoException tipoExcepcion) {
		super();

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


	}

	public TipoException getTipoExcepcion() {
		return tipoExcepcion;
	}	
	

}
