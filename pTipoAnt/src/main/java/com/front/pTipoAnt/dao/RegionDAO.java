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
import com.front.pTipoAnt.data.Region;

public class RegionDAO implements IDAO<Integer, Region> {

	DriverManagerOracle driverManager;


	public RegionDAO() {

		this.driverManager = DriverManagerOracle.getInstancia();
	}

	public Connection getConection() {
		return DriverManagerOracle.getInstancia().getConexion();
	}

	@Override
	public List<Region> findAll() throws DAOException {
		
		Connection con;
		Statement stm;
		ResultSet rs;

		List<Region> regiones = new ArrayList<Region>();

		String sql = "SELECT REGION_ID,REGION_NAME FROM REGIONS ";

		try {
			con = driverManager.getConexion();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				Region region = new Region();
				region.setId(rs.getint("REGION_ID"));
				region.setNombre(rs.getString("REGION_NAME"));
				regiones.add(region);
				
			}

			return regiones;

		} catch (SQLException sqle) {
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (Exception e) {
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

	@Override
	public Region findOne(Integer id) throws DAOException {
		

		Connection con;
		PreparedStatement pstm;
		ResultSet rs;

		Region region = null;

		String sql = "SELECT REGION_ID, REGION_NAME FROM REGIONS WHERE REGION_ID =?";

		try {
			
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			if (rs.next()) {
				region = new Region();
				region.setId(rs.getInt("DEPARTMENT_ID"));
				region.setNombre(rs.getString("DEPARTMENT_NAME"));
				
			} else {
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
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			throw daoe;

		} catch (Exception e) {
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
		return region;
	}

	@Override
	public void create(Region region) throws DAOException {
		
		
		Connection con;
		PreparedStatement pstm;

		String sql = "INSERT INTO REGIONS (REGION_ID,REGION_NAME) VALUES(?,?,?,?)";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, region.getId());
			pstm.setString(2, region.getNombre());
			
			int i = pstm.executeUpdate();

			if (i == 0) {
				
				throw new DAOException(TipoException.ELEMENTO_NO_CREADO);
			}

			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			throw new DAOException(daoe.getTipoExcepcion());
		} catch (Exception e) {
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

	@Override
	public void update(Region item) throws DAOException {
		
		Connection con;
		PreparedStatement pstm;

		String sql = "UPDATE REGIONS SET REGION_NAME=? WHERE REGION_ID=?";

		try(){
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.getNombre());
			pstm.setInt(2, item.getId());

			int i = pstm.executeUpdate();

			if (i == 0) {
				throw new DAOException(TipoException.ELEMENTO_NO_ACTUALIZADO);
			} else if (i > 1) {
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}
			con.commit();
			
		} catch (SQLException sqle) {
			con.rollback();
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			con.rollback();
			throw daoe;

		} catch (Exception e) {
			con.rollback();
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

	@Override
	public void delete(Integer key) throws DAOException {
		log.debug("delete");

		Connection con;
		PreparedStatement pstm;

		String sql = "DELETE FROM REGIONS WHERE REGION_ID=?";

		con = driverManager.getConexion();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, key);
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
