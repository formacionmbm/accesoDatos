import java.util.List;

import com.pfinal.common.DAOException;
import com.pfinal.dao.MacetaDAO;
import com.pfinal.dao.PlantaDAO;
import com.pfinal.model.Maceta;
import com.pfinal.model.Planta;

public class AppPlanta {

	public static void main(String[] args) {

		try {
			MacetaDAO macetaDAO = MacetaDAO.init();
			PlantaDAO plantaDAO = PlantaDAO.init();
			/*
			 * List<Planta> plantas = plantaDAO.findAll();
			 * 
			 * System.out.println(plantas);
			 * 
			 * // Crear un planta
			 * 
			 * Planta planta = new Planta(); planta.setId(1l);
			 * planta.setNombre("Mi Planta"); System.out.println(planta);
			 * 
			 * plantaDAO.crear(planta);
			 * 
			 * Planta planta2 = new Planta(); planta2.setId(2l);
			 * planta2.setNombre("Mi Planta2");
			 * 
			 * Maceta maceta = macetaDAO.find(1l); System.out.println(planta2);
			 * planta.setMaceta(maceta);
			 * 
			 * plantaDAO.crear(planta2);
			 * 
			 * // Modificar planta
			 * 
			 * long idPlanta = 1l; long idMaceta = 1l; plantaDAO.grabar(idPlanta, idMaceta);
			 * 
			 * plantaDAO.graba(1l, "Mi Super Planta");
			 */

			// Eliminar planta
			plantaDAO.delete(2l);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
