package com.accesoDatos.jpa.fernandoJpa;

import java.util.List;

import com.accesoDatos.jpa.fernandoJpa.dao.PaisDao;
import com.accesoDatos.jpa.fernandoJpa.dao.interfaces.IDAO;
import com.accesoDatos.jpa.fernandoJpa.entities.Pais;
import com.accesoDatos.jpa.fernandoJpa.entities.Region;
import com.accesoDatos.jpa.fernandoJpa.exceptions.DAOException;

public class TestPaisDao {

	public static void main(String[] args) throws DAOException {
		IDAO<String,Pais> dao = new PaisDao();
		System.out.println("----- Lista de paiss -------------------");
		List<Pais> paises=dao.findAll();
		for(Pais pais:paises) {
			System.out.println(pais);
		}
		System.out.println("----- Pais id=1 -------------------");
		Pais pais = dao.findOne("AU");
		System.out.println(pais);
		
		System.out.println("----- Crear Pais id=XX -------------------");
		pais = new Pais();
		pais.setId("XX");
		pais.setNombre("Alguno");
		pais.setRegion(new Region());
		pais.getRegion().setId(1);

		
		dao.create(pais);
		
		
		pais = dao.findOne("XX");
		System.out.println(pais);
		
		System.out.println("----- Modificar Pais id= XX -------------------");
		pais.setNombre("PaisModificado");
		dao.update(pais);
		
		pais = dao.findOne("XX");
		System.out.println(pais);
		
		System.out.println("----- Eliminar Pais id= XX -------------------");
		dao.delete("XX");
		
		pais = dao.findOne("XX");
		System.out.println(pais);
	}


}
