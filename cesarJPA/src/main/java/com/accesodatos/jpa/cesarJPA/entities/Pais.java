package com.accesodatos.jpa.cesarJPA.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pais {

	@Id
	@Column(name = "COUNTRY_ID")
	private String id;
	
	@Column(name = "COUNTRY_NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "REGION_ID")
	private Region region;

	public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", name=" + name + ", region=" + region + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, region);
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
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(region, other.region);
	}

	
}
