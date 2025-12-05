package com.front.pTipoAnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.front.pTipoAnt.common.exceptions.DAOException;
import com.front.pTipoAnt.common.exceptions.TipoException;
import com.front.pTipoAnt.dao.interfaces.IDAO;
import com.front.pTipoAnt.data.Region;

public class RegionDAO implements IDAO<Integer, Region>{
	
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

		String sql = "SELECT REGION_ID, REGION_NAME FROM REGIONS";

		try {
			con = driverManager.getConexion();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				Region region = new Region();
				region.setId(rs.getInt("REGION_ID"));
				region.setNombre(rs.getString("REGION_NAME"));
				regiones.add(region);
			}
			
//			rs.close();
//			stm.close();
//			con.close();		

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

		String sql = "SELECT REGION_ID, REGIONS_NAME FROM REGIONS WHERE REGION_ID =?";

		try {

			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			if (rs.next()) {  //devuelve true si hay registro encontrado en la query
				region = new Region();
				region.setId(rs.getInt("REGION_ID"));
				region.setNombre(rs.getString("REGIONS_NAME"));
			} else {
			// lanzamos nosotros la excepcion si no encuentra ningun registro con el id pasado como parametro
				throw new DAOException(TipoException.ELEMENTO_NO_ENCONTRADO); // a la linea 110 "throw daoe"
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

		} catch (ClassCastException e) {
			System.out.println("Se ha producido un error de conversion de tipos");
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		} catch (Exception e) {

			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
		return region;
	}
	
	@Override
	public void create(Region item) throws DAOException {
		// TODO Auto-generated method stub


		Connection con;
		PreparedStatement pstm;

		String sql = "INSERT INTO REGIONS (REGION_ID,REGION_NAME) VALUES(?,?)";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, item.getId());
			pstm.setString(2, item.getNombre());

			int i = pstm.executeUpdate();

			if (i == 0) {
				throw new DAOException(TipoException.ELEMENTO_NO_CREADO);
			}

			pstm.close();
			con.close();

		} catch (SQLException sqle) {

			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {

			throw daoe;
			
		} catch (Exception e) {

			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}
	
	@Override
	public void update(Region item) throws DAOException {


		Connection con;
		PreparedStatement pstm;

		String sql = "UPDATE REGIONS SET REGION_NAME=? WHERE REGION_ID=?";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.getNombre()); // la primera ? REGION_NAME
			pstm.setInt(2, item.getId()); // la segunda ? REGION_ID

			int i = pstm.executeUpdate();

			if (i == 0) {

				throw new DAOException(TipoException.ELEMENTO_NO_ACTUALIZADO);
				
			} else if (i > 1) {

				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}

			//con.commit();
			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			//con.rollback();
			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			//con.rollback();
			throw daoe;

		} catch (Exception e) {
			//con.rollback();
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}
	
	@Override
	public void delete(Integer key) throws DAOException {


		Connection con;
		PreparedStatement pstm;

		String sql = "DELETE FROM REGIONS WHERE REGION_ID=?";

		con = driverManager.getConexion();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, key);
			int i = pstm.executeUpdate();

			if (i == 0) {

				throw new DAOException(TipoException.ELEMENTO_NO_ELIMINADO);
			} else if (i > 1) {

				con.rollback();
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}
			pstm.close();
			con.close();

		} catch (SQLException sqle) {

			throw new DAOException(TipoException.EXCEPCION_SQL);

		} catch (DAOException daoe) {
			throw daoe;

		} catch (Exception e) {

			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}
}
