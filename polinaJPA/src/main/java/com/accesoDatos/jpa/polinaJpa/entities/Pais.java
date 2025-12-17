package com.accesoDatos.jpa.polinaJpa.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean Pais
 * 
 * @author POLINA
 *
 */

@Entity
@Table(name = "COUNTRIES")
public class Pais {
	@Id
	@Column(name = "COUNTRY_ID")
	private String id;
	@Column(name = "COUNTRY_NAME")
	private String nombre;
	@Column(name = "REGION_ID")
	private int region_id;

	public Pais() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRegion_id() {
		return region_id;
	}

	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, region_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre) && region_id == other.region_id;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + ", region_id=" + region_id + "]";
	}

	



}

