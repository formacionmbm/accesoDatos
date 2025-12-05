package com.front.pTipoAnt.data;

/**
 * Bean Region - LA VERSIÓN MÁS BÁSICA
 * Solo tiene atributos y métodos para obtener/establecerlos.
 */
public class Region {


	private Long id; 
	private String nombre; 
	
	
	public Region() {

	}

	
	public Region(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "Region [id=" + id + ", nombre=" + nombre + "]";
	}
}