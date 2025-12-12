import java.util.List;

import com.pfinal.common.DAOException;
import com.pfinal.dao.ViveroDAO;
import com.pfinal.model.Vivero;

public class AppVivero {

	public static void main(String[] args) {
		
		try {
			ViveroDAO viveroDAO = ViveroDAO.init();

			List<Vivero> viveros = viveroDAO.findAll();

			System.out.println(viveros);

			
			
			  //Crear un vivero
			  
			  
			  Vivero vivero= new Vivero(); vivero.setId(1l); vivero.setNombre("Mi Vivero");
			  System.out.println(vivero);
			  
			  viveroDAO.crear(vivero);
			 
			  
			  //Modificar vivero 
			  
			  //viveroDAO.graba(1l, "Mi Super Vivero");
			  
			  //Eliminar vivero
			  //viveroDAO.delete(1l);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		 

	}

}
