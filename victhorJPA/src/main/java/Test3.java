import java.util.List;

import com.accesodatos.jpa.victhorJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.victhorJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.victhorJPA.entities.Pais;
import com.accesodatos.jpa.victhorJPA.entities.Region;
import com.accesodatos.jpa.victhorJPAdao.PaisDAO;

public class Test3 {

	public static void main(String[] args) throws DAOException{
		
		IDAO<String,Pais> dao=new PaisDAO();
		System.out.println("----- Lista de Paises -------------------");
		List<Pais> paises=dao.findAll();
		for(Pais pais:paises) {
			System.out.println(pais);
		}
		System.out.println("----- Pais id=IT -------------------");
		Pais pais = dao.findOne("IT");
		System.out.println(pais);
		
		System.out.println("----- Crear Pais id=ES -------------------");
		pais = new Pais();
		pais.setId("ES");
		pais.setNombre("España");
		pais.setRegion(new Region());
		pais.getRegion().setId(1);

		
		dao.create(pais);
		
		
		pais = dao.findOne("ES");
		System.out.println(pais);
		
		System.out.println("----- Modificar Pais id=ES -------------------");
		pais.setNombre("PaisEspañaModificado");
		
		dao.update(pais);
		
		pais = dao.findOne("ES");
		System.out.println(pais);
		
		System.out.println("----- Eliminar Pais id=ES -------------------");
		dao.delete("ES");
		
		pais = dao.findOne("ES"); //guarda null en region ya que se elimina el registro
		System.out.println(pais);

	}

}
