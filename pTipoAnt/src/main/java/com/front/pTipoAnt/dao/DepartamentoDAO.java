package com.front.pTipoAnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.front.pTipoAnt.common.exceptions.DAOException;
import com.front.pTipoAnt.common.exceptions.TipoException;
import com.front.pTipoAnt.dao.interfaces.IDAO;
import com.front.pTipoAnt.data.Departamento;

public class DepartamentoDAO implements IDAO<Long, Departamento> {

	DriverManagerOracle driverManager;

	private static final Logger log = Logger.getLogger(DepartamentoDAO.class);

	public DepartamentoDAO() {

		this.driverManager = DriverManagerOracle.getInstancia();
	}

	public Connection getConection() {
		return DriverManagerOracle.getInstancia().getConexion();
	}

	@Override
	public List<Departamento> findAll() throws DAOException {
		log.debug("findAll");

		Connection con;
		Statement stm;
		ResultSet rs;

		List<Departamento> departamentos = new ArrayList<Departamento>();

		String sql = "SELECT DEPARTMENT_ID,DEPARTMENT_NAME,LOCATION_ID,MANAGER_ID FROM DEPARTMENTS ORDER BY DEPARTMENT_ID";

		try {
			con = driverManager.getConexion();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				Departamento departamento = new Departamento();
				departamento.setId(rs.getLong("DEPARTMENT_ID"));
				departamento.setNombre(rs.getString("DEPARTMENT_NAME"));
				departamento.setIdDireccion(rs.getLong("LOCATION_ID"));
				departamento.setIdManager(rs.getInt("MANAGER_ID"));
				departamentos.add(departamento);
			}

			return departamentos;

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

	@Override
	public Departamento findOne(Long id) throws DAOException, ArrayIndexOutOfBoundsException, NullPointerException,
			ArithmeticException, IllegalArgumentException {
		// log.debug("findOne");
		// log.info("id:"+id);

		Connection con;
		PreparedStatement pstm;
		ResultSet rs;

		Departamento departamento = null;

		String sql = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID FROM DEPARTMENTS WHERE DEPARTMENT_ID =?";

		// log.info(sql);
		try {
			Object i = 42;
			String s = (String) i;

			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			rs = pstm.executeQuery();

			if (rs.next()) {
				departamento = new Departamento();
				departamento.setId(rs.getLong("DEPARTMENT_ID"));
				departamento.setNombre(rs.getString("DEPARTMENT_NAME"));
				departamento.setIdDireccion(rs.getLong("LOCATION_ID"));
				departamento.setIdManager(rs.getInt("MANAGER_ID"));
			} else {
				// log.error(TipoException.ELEMENTO_NO_ENCONTRADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_NO_ENCONTRADO);
			}
			if (rs.next()) {
				log.fatal(TipoException.ELEMENTO_DUPLICADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}
			rs.close();
			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			// log.error(sqle.getMessage(),sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			// System.out.println("Nuestro objeto excepcion:"+daoe);
			throw daoe;

		} catch (ClassCastException e) {
			System.out.println("Se ha producido un error de conversion de tipos");
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		} catch (Exception e) {
			// log.error(e.getMessage(),e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
		return departamento;
	}

	@Override
	public void create(Departamento item) throws DAOException {
		// TODO Auto-generated method stub
		log.debug("Create");

		Connection con;
		PreparedStatement pstm;

		String sql = "INSERT INTO DEPARTMENTS (DEPARTMENT_ID,DEPARTMENT_NAME,LOCATION_ID,MANAGER_ID) VALUES(?,?,?,?)";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, item.getId());
			pstm.setString(2, item.getNombre());
			pstm.setLong(3, item.getIdDireccion());
			pstm.setInt(4, item.getIdManager());

			int i = pstm.executeUpdate();

			if (i == 0) {
				log.info("Elemento creado:");
			} else {
				log.error(TipoException.ELEMENTO_NO_CREADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_NO_CREADO);
			}

			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			log.error(daoe.getMessage(), daoe);
			throw new DAOException(daoe.getTipoExcepcion());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

	@Override
	public void update(Departamento item) throws DAOException {
		log.debug("update");

		Connection con;
		PreparedStatement pstm;

		String sql = "UPDATE DEPARTMENTS SET DEPARTMENT_NAME=?, LOCATION_ID=?, MANAGER_ID=? WHERE DEPARTMENT_ID=?";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.getNombre());
			pstm.setLong(2, item.getIdDireccion());
			pstm.setInt(3, item.getIdManager());
			pstm.setLong(4, item.getId());

			int i = pstm.executeUpdate();

			if (i == 0) {
				log.error(TipoException.ELEMENTO_NO_ACTUALIZADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_NO_ACTUALIZADO);
			} else if (i > 1) {
				log.error(TipoException.ELEMENTO_DUPLICADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}

			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			throw daoe;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

	@Override
	public void delete(Long key) throws DAOException {
		log.debug("delete");

		Connection con;
		PreparedStatement pstm;

		String sql = "DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID=?";

		con = driverManager.getConexion();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, key);
			int i = pstm.executeUpdate();

			if (i == 0) {
				log.error(TipoException.ELEMENTO_NO_ELIMINADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_NO_ELIMINADO);
			} else if (i > 1) {
				log.error(TipoException.ELEMENTO_DUPLICADO.getMensaje());
				con.rollback();
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}
			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			throw daoe;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

}
