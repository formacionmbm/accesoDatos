package com.accesodatos.jpa.victhorJPA.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "COUNTRIES")
public class Pais {
	
	@Id
	@Column (name = "COUNTRY_ID")
	private int id;
	@Column (name = "COUNTRY_NAME")
	private String nombre;
	
	@ManyToOne
	@JoinColumn (name = "REGION_ID")
	private Region region;
	
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + ", region=" + region + "]";
	}

	
}
