package com.front.pTipoAnt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.front.pTipoAnt.bussines.ServDepartamento;
import com.front.pTipoAnt.bussines.interfaces.IServicio;
import com.front.pTipoAnt.common.exceptions.ServicioException;
import com.front.pTipoAnt.common.exceptions.TipoException;
import com.front.pTipoAnt.data.Departamento;

/**
 * Servlet implementation class ControlDepartamento
 */
@WebServlet("/")
public class DepartamentosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IServicio<Long,Departamento> servicio;

	
	private static final Logger log = Logger.getLogger(DepartamentosController.class);

	public DepartamentosController() {
		super();
		this.servicio = new ServDepartamento();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet");
		try {
			List<Departamento> departamentos;
			
			departamentos = new ArrayList<Departamento>();
			departamentos = servicio.findAll();
			log.info("departamentos"+departamentos);
			request.setAttribute("listado", departamentos);
		} catch (ServicioException e) {
			gestionExcepcion(e,request);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/departamentos.jsp");
		dispatcher.forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doPost");
		doGet(request,response);
	}

	private void gestionExcepcion(Exception e, HttpServletRequest request) {
		log.debug("gestionExcepcion");
		
		ResourceBundle rb = ResourceBundle.getBundle("error");
		String mensajeError="";
		if(e instanceof ServicioException) {
			ServicioException se = (ServicioException)e;
			log.info("Tipo Excepcion de Servicio:"+se.getTipoExcepcion().toString());
			mensajeError = rb.getString(se.getTipoExcepcion().toString());
			
		}else {
			log.info("Tipo Excepcion General:"+TipoException.EXCEPCION_GENERAL.toString());
			mensajeError = rb.getString(TipoException.EXCEPCION_GENERAL.toString());
		}
		log.info("Mensaje Error:"+mensajeError);
		request.setAttribute("error", mensajeError);
	}
	

}
