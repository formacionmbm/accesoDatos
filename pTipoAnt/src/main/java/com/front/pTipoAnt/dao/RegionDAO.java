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
import com.front.pTipoAnt.data.Region; // ¡Cambiado a Region!

public class RegionDAO implements IDAO<Integer, Region> {

	DriverManagerOracle driverManager;

	private static final Logger log = Logger.getLogger(RegionDAO.class);

	public RegionDAO() {
		this.driverManager = DriverManagerOracle.getInstancia();
	}

	@Override
	public List<Region> findAll() throws DAOException {
		log.debug("findAll");

		Connection con;
		Statement stm;
		ResultSet rs;
		List<Region> regiones = new ArrayList<>();

		String sql = "SELECT REGION_ID, REGION_NAME FROM REGIONS ORDER BY REGION_ID";

		try {
			con = driverManager.getConexion();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				Region r = new Region();
				r.setId(rs.getLong("REGION_ID"));
				r.setNombre(rs.getString("REGION_NAME"));
				regiones.add(r);
			}

			return regiones;

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public Region findOne(Long id) throws DAOException {
		log.debug("findOne");
		log.info("id: " + id);

		Connection con;
		PreparedStatement pstm;
		ResultSet rs;

		Region region = null;

		String sql = "SELECT REGION_ID, REGION_NAME FROM REGIONS WHERE REGION_ID = ?";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			rs = pstm.executeQuery();

			if (rs.next()) {
				region = new Region();
				region.setId(rs.getLong("REGION_ID"));
				region.setNombre(rs.getString("REGION_NAME"));
			} else {
				throw new DAOException(TipoException.ELEMENTO_NO_ENCONTRADO);
			}

			return region;

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
	public void create(Region region) throws DAOException {
		log.info("create");
		log.debug("[region: " + region + "]");

		Connection con;
		PreparedStatement pstm;

		String sql = "INSERT INTO REGIONS (REGION_ID, REGION_NAME) VALUES (?, ?)";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, region.getId());
			pstm.setString(2, region.getNombre());

			int i = pstm.executeUpdate();

			if (i != 1) {
				throw new DAOException(TipoException.ELEMENTO_NO_CREADO);
			}

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
	public void update(Region region) throws DAOException {
		log.debug("update");

		Connection con;
		PreparedStatement pstm;

		String sql = "UPDATE REGIONS SET REGION_NAME = ? WHERE REGION_ID = ?";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, region.getNombre());
			pstm.setLong(2, region.getId());

			int i = pstm.executeUpdate();

			if (i == 0) {
				throw new DAOException(TipoException.ELEMENTO_NO_ACTUALIZADO);
			}

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
	public void delete(Long id) throws DAOException {
		log.debug("delete");

		Connection con;
		PreparedStatement pstm;

		String sql = "DELETE FROM REGIONS WHERE REGION_ID = ?";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);

			int i = pstm.executeUpdate();

			if (i == 0) {
				throw new DAOException(TipoException.ELEMENTO_NO_ELIMINADO);
			}

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