package com.front.pTipoAnt.data;

import java.util.Objects;

public class Country {
	private Long id;
	private String nombre;
	private Long regionIdLong;
	
	public Country() {
		super();
	}

	public Country(Long id, String nombre, Long regionIdLong) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.regionIdLong = regionIdLong;
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

	public Long getRegionIdLong() {
		return regionIdLong;
	}

	public void setRegionIdLong(Long regionIdLong) {
		this.regionIdLong = regionIdLong;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", nombre=" + nombre + ", regionIdLong=" + regionIdLong + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, regionIdLong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(regionIdLong, other.regionIdLong);
	}
	
}
