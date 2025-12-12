package com.accesodatos.jps.cesarJPA.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pais {

	@Id
	@Column(name = "COUNTRY_ID")
	private long id;
	
	@Column(name = "COUNTRY_NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "REGION_ID")
	private Region region;

	public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
	
}
