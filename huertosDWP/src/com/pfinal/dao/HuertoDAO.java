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
import com.pfinal.model.Vivero;

public class HuertoDAO implements IDAO<Huerto> {

	

	private static final Logger log = Logger.getLogger(HuertoDAO.class);

	private HuertoDAO() {
	}

	public static HuertoDAO init() {
		log.debug("init");
		HuertoDAO huertoDAO = new HuertoDAO();
		return huertoDAO;
	}

	@Override
	public Huerto find(Long id) throws DAOException {
		log.debug("find");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			Huerto huerto = emanager.find(Huerto.class, id);
			return huerto;
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
	public List<Huerto> findAll() throws DAOException {
		log.debug("findAll");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			List<Huerto> huertos = emanager.createNamedQuery("Huerto.findAll", Huerto.class).getResultList();
			return huertos;
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
	public void crear(Huerto element) throws DAOException {
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
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}

	}

	@Override
	public void grabar(Long id, String nombre) throws DAOException {
		log.debug("grabar");
		log.debug("monbre:"+nombre);
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			Huerto huerto = emanager.find(Huerto.class, id);

			emanager.getTransaction().begin();
			huerto.setNombre(nombre);
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
			
			Huerto huerto = emanager.find(Huerto.class, id);

			Vivero vivero = emanager.find(Vivero.class, idElement);

			emanager.getTransaction().begin();
			huerto.setVivero(vivero);
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
			
			Huerto huerto = emanager.find(Huerto.class, id);
			emanager.getTransaction().begin();
			emanager.remove(huerto);
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
	public Long findLastId() throws DAOException{
		log.debug("findLastId");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		Long id=null;
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			List<Huerto> huertos = findAll();
			id= huertos.stream().map(h ->  h.getId()).max(Comparator.naturalOrder()).get();
			
		} catch (Exception e) {
			log.error("Exception",e);
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
