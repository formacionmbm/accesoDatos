package com.front.pTipoAnt.data;

/**
 * Bean Pais
 * @author MARIA
 *
 */
public class Pais {
	private Long id;
	private String nombre;
	private Integer idRegion;
	
	public Pais() {
		super();
	}

	public Pais(Long id, String nombre, Integer idRegion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idRegion = idRegion;
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

	public Integer getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}


	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + ", idRegion=" + idRegion + "]";
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idRegion == null) {
			if (other.idRegion != null)
				return false;
		} else if (!idRegion.equals(other.idRegion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}

