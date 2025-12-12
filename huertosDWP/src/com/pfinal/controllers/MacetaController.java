package com.pfinal.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pfinal.common.DAOException;
import com.pfinal.dao.MacetaDAO;
import com.pfinal.dao.PlantaDAO;
import com.pfinal.dao.interfaces.IDAO;
import com.pfinal.model.Maceta;
import com.pfinal.model.Planta;

@WebServlet("/maceta")
public class MacetaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IDAO<Maceta> dao;
	IDAO<Planta> plantaDAO;

	private static final Logger log = Logger.getLogger(MacetaController.class);

	public MacetaController() {
		super();
		this.dao = MacetaDAO.init();
		this.plantaDAO = PlantaDAO.init();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/maceta.jsp";

		try {
			String accion = request.getParameter("accion");
			String id = request.getParameter("id");
			String elemento = request.getParameter("elemento");
			String idMaceta = request.getParameter("idMaceta");

			log.debug("id:" + id);
			log.debug("accion:" + accion);
			log.debug("elemento:" + elemento);
			log.debug("idMaceta:" + idMaceta);
			
			if(!"MACETA".equalsIgnoreCase(elemento)) id = idMaceta;

			if (accion == null || !"MACETA".equalsIgnoreCase(elemento))
				accion = "VER";
			if (accion != null)
				accion = accion.trim();
			log.debug("id final:" + id);
			log.debug("accion final:" + accion);
			switch (accion) {

			case "MODIFICAR":
				modificar(request, response,Long.valueOf(id));
			case "IR_MODIFICAR":
			case "VER":
				visualizar(request, response,Long.valueOf(id));
				break;
			case "CREAR":
				forward = "/huerto";
				crear(request, response,Long.valueOf(id));
				break;
			case "ELIMINAR":
				forward = "/huerto";
				eliminar(request, response,Long.valueOf(id));
				break;
			}
		} catch (DAOException e) {
			log.error("DAOException",e);
			request.setAttribute("error", e.getMensaje());
		}

		log.debug("forward"+forward);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPost");
		doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPut");
		doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doDelete");
		doGet(request, response);
	}
	
	private void visualizar(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException{
		log.debug("visualizar");

		Maceta maceta = this.dao.find(id);
				
		request.setAttribute("titulo", "Maceta");
		request.setAttribute("elemento", maceta);
		request.setAttribute("nombreElementos", "Plantas");

		
		Long proximoId= this.plantaDAO.findLastId();
		log.debug("proximoId:" + proximoId);
		request.setAttribute("proximoId", ++proximoId);
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException{
		log.debug("modificar");

		if(request.getParameter("nombre")!=null) {
			this.dao.grabar(id, request.getParameter("nombre"));
		}
		
		if(request.getParameter("idHuerto")!=null) {
			Long idElement = Long.valueOf(request.getParameter("idHuerto"));
			this.dao.grabar(id, idElement);
		}

	}


	private void crear(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException{
		log.debug("crear");

		String nombre = request.getParameter("nombre");
		Maceta maceta = new Maceta();
		maceta.setId(id);
		maceta.setNombre(nombre);
		this.dao.crear(maceta);
		if(request.getParameter("idHuerto")!=null) {
			Long idElement = Long.valueOf(request.getParameter("idHuerto"));
			this.dao.grabar(id, idElement);
		}

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException{
		log.debug("eliminar");

		this.dao.delete(id);

	}
}
