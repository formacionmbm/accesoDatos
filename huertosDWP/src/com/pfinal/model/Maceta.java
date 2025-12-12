package com.pfinal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MACETAS database table.
 * 
 */
@Entity
@Table(name="MACETAS")
@NamedQuery(name="Maceta.findAll", query="SELECT m FROM Maceta m")
public class Maceta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String nombre;

	//bi-directional many-to-one association to Huerto
	@ManyToOne
	@JoinColumn(name="IDHUERTO")
	private Huerto huerto;

	//bi-directional many-to-one association to Planta
	@OneToMany(mappedBy="maceta")
	private List<Planta> plantas;

	public Maceta() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Huerto getHuerto() {
		return this.huerto;
	}

	public void setHuerto(Huerto huerto) {
		this.huerto = huerto;
	}

	public List<Planta> getPlantas() {
		return this.plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	public Planta addPlanta(Planta planta) {
		getPlantas().add(planta);
		planta.setMaceta(this);

		return planta;
	}

	public Planta removePlanta(Planta planta) {
		getPlantas().remove(planta);
		planta.setMaceta(null);

		return planta;
	}

	@Override
	public String toString() {
		return "Maceta [id=" + id + ", nombre=" + nombre + ", huerto=" + huerto  + "]";
	}

	
	
	

}