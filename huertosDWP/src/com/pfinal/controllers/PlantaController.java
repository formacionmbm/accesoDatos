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
import com.pfinal.dao.PlantaDAO;
import com.pfinal.dao.interfaces.IDAO;
import com.pfinal.model.Planta;

@WebServlet("/planta")
public class PlantaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IDAO<Planta> dao;

	private static final Logger log = Logger.getLogger(PlantaController.class);

	public PlantaController() {
		super();
		this.dao = PlantaDAO.init();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/planta.jsp";

		try {
			String accion = request.getParameter("accion");
			String id = request.getParameter("id");

			log.debug("id:" + id);
			log.debug("accion:" + accion);

			if (accion == null) accion="VER";
			if (accion != null)
				accion = accion.trim();
			switch (accion) {

			case "MODIFICAR":
				modificar(request, response);
			case "IR_MODIFICAR":
			case "VER":
				visualizar(request, response);
				break;
			case "CREAR":
				forward = "/maceta";
				crear(request, response);
				break;
			case "ELIMINAR":
				forward = "/maceta";
				eliminar(request, response);
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
	

	private void visualizar(HttpServletRequest request, HttpServletResponse response) throws DAOException{
		log.debug("visualizar");

		Long id = Long.valueOf(request.getParameter("id"));
		Planta planta = this.dao.find(id);
				
		request.setAttribute("titulo", "Planta");
		request.setAttribute("elemento", planta);
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) throws DAOException{
		log.debug("modificar");

		Long id = Long.valueOf(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		this.dao.grabar(id, nombre);

	}


	private void crear(HttpServletRequest request, HttpServletResponse response) throws DAOException{
		log.debug("crear");

		Long id = Long.valueOf(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		Planta planta = new Planta();
		planta.setId(id);
		planta.setNombre(nombre);
		this.dao.crear(planta);
		if(request.getParameter("idMaceta")!=null) {
			Long idElement = Long.valueOf(request.getParameter("idMaceta"));
			this.dao.grabar(id, idElement);
		}

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws DAOException{
		log.debug("eliminar");

		Long id = Long.valueOf(request.getParameter("id"));
		this.dao.delete(id);

	}

}
