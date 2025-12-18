package com.accesoDatos.jpa.fernandoJpa;

import java.util.List;

import com.accesoDatos.jpa.fernandoJpa.dao.RegionDao;
import com.accesoDatos.jpa.fernandoJpa.dao.interfaces.IDAO;
import com.accesoDatos.jpa.fernandoJpa.entities.Region;
import com.accesoDatos.jpa.fernandoJpa.exceptions.DAOException;

public class TestRegionDao {

	public static void main(String[] args) throws DAOException {
		IDAO<Integer,Region> dao = new RegionDao();
		System.out.println("----- Lista de regions -------------------");
		List<Region> regions=dao.findAll();
		for(Region region:regions) {
			System.out.println(region);
		}
		System.out.println("----- Region id=1 -------------------");
		Region region = dao.findOne(1);
		System.out.println(region);
		
		System.out.println("----- Crear Region id=1000 -------------------");
		region = new Region();
		region.setId(1000);
		region.setNombre("Alguno");

		
		dao.create(region);
		
		
		region = dao.findOne(1000);
		System.out.println(region);
		
		System.out.println("----- Modificar Region id=1000 -------------------");
		region.setNombre("RegionModificado");
		dao.update(region);
		
		region = dao.findOne(1000);
		System.out.println(region);
		
		System.out.println("----- Eliminar Region id=1000 -------------------");
		dao.delete(1000);
		
		region = dao.findOne(1000);
		System.out.println(region);
	}


}
