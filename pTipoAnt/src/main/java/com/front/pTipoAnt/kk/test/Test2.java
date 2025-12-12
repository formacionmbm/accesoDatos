package com.front.pTipoAnt.kk.test;

import java.util.List;

import com.front.pTipoAnt.common.exceptions.DAOException;
import com.front.pTipoAnt.dao.RegionDAO;
import com.front.pTipoAnt.dao.interfaces.IDAO;
import com.front.pTipoAnt.data.Region;

public class Test2 {

	public static void main(String[] args) throws DAOException {
		IDAO<Integer, Region> dao = new RegionDAO();
		System.out.println("----- Lista de regiones -------------------");
		List<Region> regiones=dao.findAll();
		for(Region region:regiones) {
			System.out.println(region);
		}
		System.out.println("----- Region id=4 -------------------");
		Region region = dao.findOne(4);
		System.out.println(region);
		
		System.out.println("----- Crear Region id=1000 -------------------");
		region = new Region();
		region.setId(1000);
		region.setNombre("Alguno");
				
		dao.create(region);
		
		
		region = dao.findOne(1000);
		System.out.println(region);
		
		System.out.println("----- Modificar Region id=1000 -------------------");
		region.setNombre("RegionModificada");
		dao.update(region);
		
		region = dao.findOne(1000);
		System.out.println(region);
		
		System.out.println("----- Eliminar Region id=1000 -------------------");
		dao.delete(1000);
		
		region = dao.findOne(1000);
		System.out.println(region);
	}

}
