package test;


import java.util.List;

import com.accesodatos.jpa.gelmaryJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.gelmaryJPA.dao.PaisDAO;
import com.accesodatos.jpa.gelmaryJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.gelmaryJPA.entities.Pais;
import com.accesodatos.jpa.gelmaryJPA.entities.Region;



public class TestPaisDAO {

	public static void main(String[] args) throws DAOException {
		IDAO<String,Pais> dao = new PaisDAO();
		System.out.println("----- Lista de paises -------------------");
		List<Pais> paises=dao.findAll();
		for(Pais pais:paises) {
			System.out.println(pais);
		}
		System.out.println("----- Pais id=AR -------------------");
		Pais pais = dao.findOne("AR");
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
		
		System.out.println("----- Modificar Pais id=XX -------------------");
		pais.setNombre("PaisModificado");
		dao.update(pais);
		
		pais = dao.findOne("XX");
		System.out.println(pais);
		
		System.out.println("----- Eliminar Pais id=XX -------------------");
		dao.delete("XX");
		
		pais = dao.findOne("XX");
		System.out.println(pais);
	}

}
