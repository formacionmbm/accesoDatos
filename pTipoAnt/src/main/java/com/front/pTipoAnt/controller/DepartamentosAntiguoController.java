package com.front.pTipoAnt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.front.pTipoAnt.bussines.ServDepartamento;
import com.front.pTipoAnt.bussines.interfaces.IServicio;
import com.front.pTipoAnt.common.exceptions.ServicioException;
import com.front.pTipoAnt.data.Departamento;

@SuppressWarnings("serial")
@WebServlet("/antiguo")
public class DepartamentosAntiguoController extends HttpServlet {
	
IServicio<Long,Departamento> servicio;

	
	private static final Logger log = Logger.getLogger(DepartamentosAntiguoController.class);

	public DepartamentosAntiguoController() {
		super();
		this.servicio = new ServDepartamento();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pagina ="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<script>\r\n" + 
				"function verElemento(id){\r\n" + 
				"	document.getElementById(\"formDepartamento\").id.value = id;\r\n" + 
				"	document.getElementById(\"formDepartamento\").accion.value = \"V\"; \r\n" + 
				"	\r\n" + 
				"	document.getElementById(\"formDepartamento\").submit();\r\n" + 
				"}\r\n" + 
				"</script>\r\n" + 
				"<style>\r\n" + 
				"\r\n" + 
				"	label{\r\n" + 
				"		color: #0040aa;\r\n" + 
				"	}\r\n" + 
				"</style>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Departamentos</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<h1>Departamentos</h1>\r\n" + 
				"<div >\r\n" + 
				"<form method=\"post\" action=\"./departamento\" id=\"formDepartamento\">\r\n" + 
				" <input hidden=\"true\" type=\"text\" name=\"accion\" value=\"\" />\r\n" + 
				" <input hidden=\"true\" type=\"text\" name=\"id\" value=\"\" />\r\n" + 
				"</form>\r\n" + 
				"</div>\r\n" + 
				"<ul>";
		
				
		
		List<Departamento> listado = new ArrayList<Departamento>();
		try {
			listado = servicio.findAll();
		} catch (ServicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Departamento elemento:listado) {
		pagina+="<li><a href=\"javascript:verElemento("+elemento.getId()+")>"+elemento.getNombre()+"</a></li>";
		}
		
		pagina +="</ul>\r\n" + 
				"\r\n" + 
				"\r\n" + 				
				"</body>\r\n" + 
				"</html>";

		resp.setContentType("text/html");
		resp.getOutputStream().print(pagina);
	
	}
	
	

}
