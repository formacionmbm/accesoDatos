package test;

import java.util.List;

import com.accesodatos.jpa.gelmaryJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.gelmaryJPA.dao.RegionDAO;
import com.accesodatos.jpa.gelmaryJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.gelmaryJPA.entities.Region;

public class TestRegionDAO {

	public static void main(String[] args) throws DAOException {
		IDAO<Integer,Region> dao = new RegionDAO();
		System.out.println("----- Lista de regiones -------------------");
		List<Region> regiones=dao.findAll();
		for(Region region:regiones) {
			System.out.println(region);
		}
		System.out.println("----- Region id=1 -------------------");
		Region region = dao.findOne(1);
		System.out.println(region);
		
		System.out.println("----- Crear Region id=1000 -------------------");
		region = new Region();
		region.setId(1000);
		region.setNombre("Region ficticia");

		
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
