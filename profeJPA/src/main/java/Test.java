

import java.util.List;

import com.accesodatos.jpa.victhorJPA.common.exceptions.DAOException;
import com.accesodatos.jpa.victhorJPA.dao.interfaes.IDAO;
import com.accesodatos.jpa.victhorJPA.entities.Departamento;
import com.accesodatos.jpa.victhorJPAdao.DepartamentoDAO;



public class Test {

	public static void main(String[] args) throws DAOException {
		IDAO<Long,Departamento> dao = new DepartamentoDAO();
		System.out.println("----- Lista de departamentos -------------------");
		List<Departamento> departamentos=dao.findAll();
		for(Departamento departamento:departamentos) {
			System.out.println(departamento);
		}
		System.out.println("----- Departamento id=80 -------------------");
		Departamento departamento = dao.findOne(80l);
		System.out.println(departamento);
		
		System.out.println("----- Crear Departamento id=1000 -------------------");
		departamento = new Departamento();
		departamento.setId(1000l);
		departamento.setNombre("Alguno");
		departamento.setIdManager(100);
		departamento.setIdDireccion(1700l);
		
		dao.create(departamento);
		
		
		departamento = dao.findOne(1000l);
		System.out.println(departamento);
		
		System.out.println("----- Modificar Departamento id=1000 -------------------");
		departamento.setNombre("DepartamentoModificado");
		dao.update(departamento);
		
		departamento = dao.findOne(1000l);
		System.out.println(departamento);
		
		System.out.println("----- Eliminar Departamento id=1000 -------------------");
		dao.delete(1000l);
		
		departamento = dao.findOne(1000l);
		System.out.println(departamento);
	}

}
