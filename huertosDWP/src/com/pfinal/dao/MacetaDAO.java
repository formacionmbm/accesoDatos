package com.pfinal.dao;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.pfinal.common.DAOException;
import com.pfinal.dao.interfaces.IDAO;
import com.pfinal.model.Huerto;
import com.pfinal.model.Maceta;

public class MacetaDAO implements IDAO<Maceta> {

	private static final Logger log = Logger.getLogger(MacetaDAO.class);

	private MacetaDAO() {
	}

	public static MacetaDAO init() {
		log.debug("init");		
		
		MacetaDAO macetaDAO = new MacetaDAO();
		return macetaDAO;
	}

	@Override
	public Maceta find(Long id) throws DAOException {
		log.debug("find");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			
			Maceta maceta = emanager.find(Maceta.class, id);
			return maceta;
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
	public List<Maceta> findAll() throws DAOException {
		log.debug("findAll");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			
			List<Maceta> macetas = emanager.createNamedQuery("Maceta.findAll", Maceta.class).getResultList();
			return macetas;
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
	public void crear(Maceta element) throws DAOException {
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
			
			
			Maceta maceta = emanager.find(Maceta.class, id);

			emanager.getTransaction().begin();
			maceta.setNombre(nombre);
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
			
			
			Maceta maceta = emanager.find(Maceta.class, id);

			Huerto huerto = emanager.find(Huerto.class, idElement);

			emanager.getTransaction().begin();
			maceta.setHuerto(huerto);
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
			
			
			Maceta maceta = emanager.find(Maceta.class, id);
			emanager.getTransaction().begin();
			emanager.remove(maceta);
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
	public Long findLastId() throws DAOException {
		log.debug("findLastId");
		
		Long id = null;		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			
			List<Maceta> macetas = findAll();
			id= macetas.stream().map(h ->  h.getId()).max(Comparator.naturalOrder()).get();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		} finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}
		return id;

	}

}
