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
import com.front.pTipoAnt.data.Pais;

public class PaisDAO implements IDAO<Long, Pais> {

	DriverManagerOracle driverManager;

	private static final Logger log = Logger.getLogger(PaisDAO.class);

	public PaisDAO() {

		this.driverManager = DriverManagerOracle.getInstancia();
	}

	public Connection getConection() {
		return DriverManagerOracle.getInstancia().getConexion();
	}

	@Override
	public List<Pais> findAll() throws DAOException {
		log.debug("findAll");

		Connection con;
		Statement stm;
		ResultSet rs;

		List<Pais> departamentos = new ArrayList<Pais>();

		String sql = "SELECT COUNTRY_ID,COUNTRY_NAME,REGION_ID FROM COUNTRIES ORDER BY COUNTRY_ID";

		try {
			con = driverManager.getConexion();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				Pais pais = new Pais();
				pais.setId(rs.getLong("COUNTRY_ID"));
				pais.setNombre(rs.getString("COUNTRY_NAME"));
				pais.setIdRegion(rs.geti("REGION_ID"));
				pais.add(pais);
			}

			return pais;

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

	@Override
	public Pais findOne(Long id) throws DAOException {
		 log.debug("findOne");
		 log.info("id:"+id);

		Connection con;
		PreparedStatement pstm;
		ResultSet rs;

		Pais pais = null;

		String sql = "SELECT COUNTRY_ID, COUNTRY_NAME, REGION_ID FROM COUNTRIES WHERE COUNTRY_ID =?";

		
		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			rs = pstm.executeQuery();

			if (rs.next()) {
				pais = new Pais();
				pais.setId(rs.getLong("COUNTRY_ID"));
				pais.setNombre(rs.getString("COUNTRY_NAME"));
				pais.setIdRegion(rs.getLong("REGION_ID"));
			} else {
				throw new DAOException(TipoException.ELEMENTO_NO_ENCONTRADO);
			}
			if (rs.next()) {
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}
			rs.close();
			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			throw daoe;
		} catch (Exception e) {
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
		return pais;
	}

	@Override
	public void create(Pais pais) throws DAOException {
		log.info("create");
		log.debug("[departamento:"+pais+"]");

		Connection con;
		PreparedStatement pstm;

		String sql = "INSERT INTO COUNTRIES (COUNTRY_ID,COUNTRY_NAME,REGION_ID) VALUES(?,?,?)";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, pais.getId());
			pstm.setString(2, pais.getNombre());
			pstm.setLong(3, pais.getIdRegion());

			int i = pstm.executeUpdate();

			if (i == 1) {
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
	public void update(Pais item) throws DAOException {
		log.debug("update");

		Connection con;
		PreparedStatement pstm;

		String sql = "UPDATE COUNTRIES SET COUNTRY_NAME=?, COUNTRY_ID=? WHERE COUNTRY_ID=?";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.getNombre());
			pstm.setLong(2, item.getIdRegion());
			
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
