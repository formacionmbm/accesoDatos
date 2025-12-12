import java.util.List;

import com.pfinal.common.DAOException;
import com.pfinal.dao.HuertoDAO;
import com.pfinal.dao.MacetaDAO;
import com.pfinal.model.Huerto;
import com.pfinal.model.Maceta;

public class AppMaceta {

	public static void main(String[] args) {
		
		
		try {
			HuertoDAO huertoDAO = HuertoDAO.init();

			MacetaDAO macetaDAO = MacetaDAO.init();

			List<Maceta> macetas = macetaDAO.findAll();

			System.out.println(macetas);

			// Crear un maceta

			/*
			 * Maceta maceta = new Maceta(); maceta.setId(1l);
			 * maceta.setNombre("Mi Maceta"); System.out.println(maceta);
			 * 
			 * macetaDAO.crear(maceta);
			 */

			/*
			 * Maceta maceta2 = new Maceta(); maceta2.setId(2l);
			 * maceta2.setNombre("Mi Maceta");
			 * 
			 * Huerto huerto = huertoDAO.find(1l); System.out.println(maceta2);
			 * maceta2.setHuerto(huerto);
			 * 
			 * macetaDAO.crear(maceta2);
			 * 
			 * // Modificar maceta
			 * 
			 * long idMaceta = 1l; long idHuerto = 1l; macetaDAO.grabar(idMaceta, idHuerto);
			 * 
			 * macetaDAO.graba(1l, "Mi Super Maceta");
			 */

			// Eliminar maceta
			 macetaDAO.delete(2l);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
