package com.front.pTipoAnt.dao;   //ésta es la clase de negocio

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

public class RegionDAO implements IDAO<Long, Region> {

	DriverManagerOracle driverManager;

	private static final Logger log = Logger.getLogger(DepartamentoDAO.class);

	public RegionDAO() {

		this.driverManager = DriverManagerOracle.getInstancia();
	}

	public Connection getConection() {
		return DriverManagerOracle.getInstancia().getConexion();
	}

	@Override
	public List<Region> findAll() throws DAOException { //excepción checked
		log.debug("findAll");

		Connection con;
		Statement stm;
		ResultSet rs;

		List<Region> regiones = new ArrayList<Region>();

		String sql = "SELECT REGION_ID,REGION_NAME";

		try {
			con = driverManager.getConexion();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				Region region = new Region();
				region.setId(rs.getLong("REGION_ID"));
				region.setName(rs.getString("REGION_NAME"));
				regiones.add(region);
			}

			return regiones;

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);   //va a ser hija de IOexception (entrada y salida)

		} catch (Exception e) {  //excepción genérica
			log.error(e.getMessage(), e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}

	}

	@Override
	public Region findOne(Long id) throws DAOException, ArrayIndexOutOfBoundsException, NullPointerException,
			ArithmeticException, IllegalArgumentException {
		// log.debug("findOne");
		// log.info("id:"+id);

		Connection con;
		PreparedStatement pstm;
		ResultSet rs;

		Region region = null;

		String sql = "SELECT REGION_ID, REGION_NAME =?";

		// log.info(sql);
		try {
			//Object i = 42;
			//String s = (String) i;

			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			rs = pstm.executeQuery();

			if (rs.next()) {
				Region region1 = new Region();
				region1.setId(rs.getLong("REGION_ID"));
				region1.setName(rs.getString("REGION_NAME"));
				
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
		return region;
	}

	@Override
	public void create(Region item) throws DAOException {
		// TODO Auto-generated method stub
		log.debug("Create");

		Connection con;
		PreparedStatement pstm;

		String sql = "INSERT INTO REGIONS (REGION_ID,REGION_NAME) VALUES(?,?)";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, item.getId());
			pstm.setString(2, item.getName());

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
	public void update1(Region item) throws DAOException {
		log.debug("update");

		Connection con;
		PreparedStatement pstm;

		String sql = "UPDATE REGIONS SET REGION_ID=?, REGION_NAME=?";

		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.getName());
			pstm.setLong(2, item.getId());
			
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

		String sql = "DELETE FROM REGIONS WHERE REGION_ID=?";

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

	@Override
	public void update(Region item) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
