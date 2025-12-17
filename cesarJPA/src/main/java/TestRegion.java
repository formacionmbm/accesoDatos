import java.util.List;

import com.accesodatos.jpa.cesarJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.cesarJPA.dao.RegionDAO;
import com.accesodatos.jpa.cesarJPA.dao.interfaces.IDAO;
import com.accesodatos.jpa.cesarJPA.entities.Region;

public class TestRegion {

	public static void main(String[] args) throws DAOException {
		IDAO<Long,Region> dao = new RegionDAO();
		System.out.println("----- Lista de departamentos -------------------");
		List<Region> regiones=dao.findAll();
		for(Region region:regiones) {
			System.out.println(region);
		}
		System.out.println("----- Region id=80 -------------------");
		Region region = dao.findOne(2l);
		System.out.println(region);
		
		System.out.println("----- Crear Departamento id=1000 -------------------");
		region = new Region();
		region.setId(1000l);
		region.setName("Alguno");
		
		
		dao.create(region);
		
		
		region = dao.findOne(1000l);
		System.out.println(region);
		
		System.out.println("----- Modificar Departamento id=1000 -------------------");
		region.setName("Region Modificada");
		dao.update(region);
		
		region = dao.findOne(1000l);
		System.out.println(region);
		
		System.out.println("----- Eliminar Departamento id=1000 -------------------");
		dao.delete(1000l);
		
		region = dao.findOne(1000l);
		System.out.println(region);
	}
	
}
