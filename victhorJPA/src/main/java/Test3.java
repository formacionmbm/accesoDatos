import java.util.List;

import com.accesodatos.jpa.victhorJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.victhorJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.victhorJPA.entities.Pais;
import com.accesodatos.jpa.victhorJPA.entities.Region;
import com.accesodatos.jpa.victhorJPAdao.PaisDAO;

public class Test3 {

	public static void main(String[] args) throws DAOException{
		
		IDAO<Integer,Pais> dao=new PaisDAO();
		System.out.println("----- Lista de Paises -------------------");
		List<Pais> paises=dao.findAll();
		for(Pais pais:paises) {
			System.out.println(pais);
		}
		System.out.println("----- Pais id=1 -------------------");
		Pais pais = dao.findOne(1);
		System.out.println(pais);
		
		System.out.println("----- Crear Pais id=100 -------------------");
		pais = new Pais();
		pais.setId(100);
		pais.setNombre("China");
		pais.setRegion(null);

		
		dao.create(pais);
		
		
		pais = dao.findOne(100);
		System.out.println(pais);
		
		System.out.println("----- Modificar Pais id=100 -------------------");
		pais.setNombre("PaisModificado");
		dao.update(pais);
		
		pais = dao.findOne(100);
		System.out.println(pais);
		
		System.out.println("----- Eliminar Pais id=100 -------------------");
		dao.delete(100);
		
		pais = dao.findOne(100); //guarda null en region ya que se elimina el registro
		System.out.println(pais);

	}

}
