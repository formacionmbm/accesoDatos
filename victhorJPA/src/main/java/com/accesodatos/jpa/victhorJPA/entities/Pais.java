package com.accesodatos.jpa.victhorJPA.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "COUNTRIES")
public class Pais {
	
	@Id
	@Column (name = "COUNTRY_ID")
	private int id;
	@Column (name = "COUNTRY_NAME")
	private String nombre;
	@Column (name = "REGION_ID")
	private int idRegion;
	
	// Constructor por defecto
	public Pais() {
		super();
	}

	// Getter y Setter de los Atributos privados
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + ", idRegion=" + idRegion + "]";
	}
	
	
}
