package com.front.pTipoAnt.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerOracle {
	private static DriverManagerOracle instancia; //si no hay atributos es un método de negocio
	private DriverManagerOracle() {;}
	public static DriverManagerOracle getInstancia() {
		if(instancia == null) instancia = new DriverManagerOracle();
		return instancia;
	}
	
	public Connection getConexion() {		//método getConexion
		String connectionString="jdbc:oracle:thin:@localhost:1521:xe"; //URI identifica inequivocamente un recurso que es la BBDD
		Connection con=null;
		try {	//nos importancia un pimiento
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
