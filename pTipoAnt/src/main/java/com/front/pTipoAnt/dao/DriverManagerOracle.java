package com.front.pTipoAnt.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerOracle {
	private static DriverManagerOracle instancia;
	private DriverManagerOracle() {;}
	public static DriverManagerOracle getInstancia() {
		if(instancia == null) instancia = new DriverManagerOracle();
		return instancia;
	}
	
	public Connection getConexion() {		
		String connectionString="jdbc:oracle:thin:@localhost:1521:xe";
		Connection con=null;
		try {
		        Class.forName("oracle.jdbc.OracleDriver");
		    } catch (ClassNotFoundException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		try {
		        con=DriverManager.getConnection(connectionString,"HR","hr");
		    } catch (SQLException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		return con;
	}

}
