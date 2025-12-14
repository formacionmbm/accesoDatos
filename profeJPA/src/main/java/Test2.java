import java.util.List;

import com.accesodatos.jpa.victhorJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.victhorJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.victhorJPA.entities.Region;
import com.accesodatos.jpa.victhorJPAdao.RegionDAO;


public class Test2 {

	public static void main(String[] args) throws DAOException {
		IDAO<Integer,Region> dao = new RegionDAO();
		System.out.println("----- Lista de Regiones -------------------");
		List<Region> regiones=dao.findAll();
		for(Region region:regiones) {
			System.out.println(region);
		}
		System.out.println("----- Region id=1 -------------------");
		Region region = dao.findOne(1);
		System.out.println(region);
		
		System.out.println("----- Crear Region id=5 -------------------");
		region = new Region();
		region.setId(5);
		region.setNombre("Oceania");

		
		dao.create(region);
		
		
		region = dao.findOne(5);
		System.out.println(region);
		
		System.out.println("----- Modificar Region id=5 -------------------");
		region.setNombre("RegionModificada");
		dao.update(region);
		
		region = dao.findOne(5);
		System.out.println(region);
		
		System.out.println("----- Eliminar Region id=5 -------------------");
		dao.delete(5);
		
		region = dao.findOne(5); //guarda null en region ya que se elimina el registro
		System.out.println(region);

	}

}
