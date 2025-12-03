package com.front.pTipoAnt.common.exceptions;


/**
 * Clase propia que recoge las excepciones del DAO
 * @author MARIA
 *
 */
@SuppressWarnings("serial")
public class DAOException extends Exception {

	TipoException tipoExcepcion;

	public DAOException(TipoException tipoExcepcion) {
		super();
		this.tipoExcepcion = tipoExcepcion;
	}

	public TipoException getTipoExcepcion() {
		return tipoExcepcion;
	}
	
	  public String getMessage() {
	        return this.tipoExcepcion.toString();
	    }

	
	
}
