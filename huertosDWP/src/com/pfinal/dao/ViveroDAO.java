package com.pfinal.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.pfinal.common.DAOException;
import com.pfinal.dao.interfaces.IDAOBasic;
import com.pfinal.model.Vivero;

public class ViveroDAO implements IDAOBasic<Vivero> {

	private static final Logger log = Logger.getLogger(ViveroDAO.class);

	private ViveroDAO() {
	}

	public static ViveroDAO init() {
		log.debug("init");
		ViveroDAO viveroDAO = new ViveroDAO();
		return viveroDAO;
	}

	@Override
	public Vivero find(Long id) throws DAOException {
		log.debug("find");
		EntityManagerFactory emf = null;
		EntityManager emanager = null;

		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			Vivero vivero = emanager.find(Vivero.class, id);
			return vivero;
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
	public List<Vivero> findAll() throws DAOException {
		log.debug("findAll");
		EntityManagerFactory emf = null;
		EntityManager emanager = null;
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();
			
			List<Vivero> viveros = emanager.createQuery("select v from Vivero v",Vivero.class).getResultList();//emanager.createNamedQuery("Vivero.findAll", Vivero.class).getResultList();
			return viveros;
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
	public void crear(Vivero element) throws DAOException {
		log.debug("crear");
		EntityManagerFactory emf = null;
		EntityManager emanager = null;
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
		EntityManagerFactory emf = null;
		EntityManager emanager = null;
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();

			Vivero vivero = emanager.find(Vivero.class, id);

			emanager.getTransaction().begin();
			vivero.setNombre(nombre);
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
		EntityManagerFactory emf = null;
		EntityManager emanager = null;
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();

			Vivero vivero = emanager.find(Vivero.class, id);
			emanager.getTransaction().begin();
			emanager.remove(vivero);
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
		EntityManagerFactory emf = null;
		EntityManager emanager = null;
		Long id = null;
		try {
			emf = Persistence.createEntityManagerFactory("UP");
			emanager = emf.createEntityManager();

			String query = "SELECT max(id) FROM Vivero";
			id = ((BigDecimal) emanager.createNativeQuery(query).getSingleResult()).longValue();
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
