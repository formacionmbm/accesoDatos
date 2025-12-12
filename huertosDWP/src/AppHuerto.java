import java.util.List;

import com.pfinal.common.DAOException;
import com.pfinal.dao.HuertoDAO;
import com.pfinal.model.Huerto;

public class AppHuerto {

	public static void main(String[] args) {
		
		try {
			HuertoDAO huertoDAO = HuertoDAO.init();

			List<Huerto> huertos = huertoDAO.findAll();

			System.out.println(huertos);

			// Crear un huerto

			/*
			 * Huerto huerto = new Huerto(); huerto.setId(1l);
			 * huerto.setNombre("Mi Huerto"); System.out.println(huerto);
			 * 
			 * huertoDAO.crear(huerto);
			 */
			
			/*
			 * Huerto huerto = new Huerto(); huerto.setId(2l);
			 * huerto.setNombre("Mi Huerto");
			 * 
			 * Vivero vivero = viveroDAO.find(1l); System.out.println(huerto);
			 * huerto.setVivero(vivero);
			 * 
			 * huertoDAO.crear(huerto);
			 */

			// Modificar huerto
			
			/*
			 * long idHuerto = 1l; long idVivero = 1l; huertoDAO.grabar(idHuerto, idVivero);
			 */
			 
			 //huertoDAO.graba(1l, "Mi Super Huerto");

			// Eliminar huerto
			 huertoDAO.delete(2l);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
