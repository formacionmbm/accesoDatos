package com.pfinal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the VIVERO database table.
 * 
 */
@Entity
@Table(name="VIVEROS")
@NamedQuery(name="Vivero.findAll", query="SELECT v FROM Vivero v")
public class Vivero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String nombre;

	//bi-directional many-to-one association to Huerto
	@OneToMany(mappedBy="vivero")
	private List<Huerto> huertos;

	public Vivero() {
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

	public List<Huerto> getHuertos() {
		return this.huertos;
	}

	public void setHuertos(List<Huerto> huertos) {
		this.huertos = huertos;
	}

	public Huerto addHuerto(Huerto huerto) {
		getHuertos().add(huerto);
		huerto.setVivero(this);

		return huerto;
	}

	public Huerto removeHuerto(Huerto huerto) {
		getHuertos().remove(huerto);
		huerto.setVivero(null);

		return huerto;
	}

	@Override
	public String toString() {
		return "Vivero [id=" + id + ", nombre=" + nombre + "]";
	}

	
	
	

}