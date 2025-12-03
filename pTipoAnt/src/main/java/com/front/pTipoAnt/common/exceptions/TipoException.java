package com.front.pTipoAnt.common.exceptions;

public enum TipoException {
	EXCEPCION_GENERAL("Excepción General"),
	EXCEPCION_SQL("Excepción SQL"),
	ELEMENTO_NO_ENCONTRADO("Elemento no encontrado"),
	ELEMENTO_NO_CREADO("Elemento no creado"),
	ELEMENTO_NO_ACTUALIZADO("Elemento no actualizado"),
	ELEMENTO_NO_ELIMINADO("Elemento no eliminado"),
	ELEMENTO_DUPLICADO("Elemento no duplicado"),
	EXCEPCION_DAO("Excepcion en el dao"),
	OPERACION_NO_REALIZADA("La operación no se ha podido realizar");

	private String mensaje=null;

	private TipoException(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}


}
