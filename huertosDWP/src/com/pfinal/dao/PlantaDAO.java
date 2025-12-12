package com.pfinal.dao;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.pfinal.common.DAOException;
import com.pfinal.dao.interfaces.IDAO;
import com.pfinal.model.Maceta;
import com.pfinal.model.Planta;

public class PlantaDAO implements IDAO<Planta> {

	private static final Logger log = Logger.getLogger(PlantaDAO.class);

	private PlantaDAO() {
	}

	public static PlantaDAO init() {
		log.debug("init");
		PlantaDAO plantaDAO = new PlantaDAO();
		return plantaDAO;
	}

	@Override
	public Planta find(Long id) throws DAOException {
		log.debug("find");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			Planta planta = emanager.find(Planta.class, id);
			return planta;
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		} finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}
	}

	@Override
	public List<Planta> findAll() throws DAOException {
		log.debug("findAll");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			List<Planta> plantas = emanager.createNamedQuery("Planta.findAll", Planta.class).getResultList();
			return plantas;
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}
	}

	@Override
	public void crear(Planta element) throws DAOException {
		log.debug("crear");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			emanager.getTransaction().begin();
			emanager.persist(element);
			emanager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		} finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}

	}

	@Override
	public void grabar(Long id, String nombre) throws DAOException {
		log.debug("grabar");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			Planta planta = emanager.find(Planta.class, id);

			emanager.getTransaction().begin();
			planta.setNombre(nombre);
			emanager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}

	}

	@Override
	public void grabar(Long id, Long idElement) throws DAOException {
		log.debug("grabar");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			Planta planta = emanager.find(Planta.class, id);

			Maceta maceta = emanager.find(Maceta.class, idElement);

			emanager.getTransaction().begin();
			planta.setMaceta(maceta);
			emanager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}

	}

	@Override
	public void delete(Long id) throws DAOException {
		log.debug("delete");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			Planta planta = emanager.find(Planta.class, id);
			emanager.getTransaction().begin();
			emanager.remove(planta);
			emanager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}

	}

	@Override
	public Long findLastId() throws DAOException {
		log.debug("findLastId");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;		
		Long id = null;
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			List<Planta> plantas = findAll();
			id= plantas.stream().map(h ->  h.getId()).max(Comparator.naturalOrder()).get();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}
		return id;

	}


}
