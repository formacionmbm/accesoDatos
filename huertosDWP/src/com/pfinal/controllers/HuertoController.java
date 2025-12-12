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
import com.pfinal.dao.HuertoDAO;
import com.pfinal.dao.MacetaDAO;
import com.pfinal.dao.interfaces.IDAO;
import com.pfinal.model.Huerto;
import com.pfinal.model.Maceta;

@WebServlet("/huerto")
public class HuertoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IDAO<Huerto> dao;
	IDAO<Maceta> macetaDAO;

	private static final Logger log = Logger.getLogger(HuertoController.class);

	public HuertoController() {
		super();
		this.dao = HuertoDAO.init();
		this.macetaDAO = MacetaDAO.init();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/huerto.jsp";
		
				

		try {
			String accion = request.getParameter("accion");
			String id = request.getParameter("id");
			String elemento = request.getParameter("elemento");
			String idHuerto = request.getParameter("idHuerto");

			log.debug("id:" + id);
			log.debug("accion:" + accion);
			log.debug("elemento:" + elemento);
			log.debug("idHuerto:" + idHuerto);
			
			if(!"HUERTO".equalsIgnoreCase(elemento)) id = idHuerto;

			if (accion == null || !"HUERTO".equalsIgnoreCase(elemento))
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
				forward = "/vivero";
				crear(request, response,Long.valueOf(id));
				break;
			case "ELIMINAR":
				forward = "/vivero";
				eliminar(request, response,Long.valueOf(id));
				break;
			}
		} catch (DAOException e) {
			log.error("DAOException", e);
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

	private void visualizar(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException {
		log.debug("visualizar");

		Huerto huerto = this.dao.find(id);

		request.setAttribute("titulo", "Huerto");
		request.setAttribute("elemento", huerto);
		request.setAttribute("nombreElementos", "Macetas");

		Long proximoId = this.macetaDAO.findLastId();
		log.debug("proximoId:" + proximoId);
		request.setAttribute("proximoId", ++proximoId);
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException {
		log.debug("modificar");

		String nombre = request.getParameter("nombre");

		this.dao.grabar(id, nombre);

	}

	private void crear(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException {
		log.debug("crear");

		String nombre = request.getParameter("nombre");

		log.debug("id:" + id);
		log.debug("nombre:" + nombre);
		log.debug("idVivero:" + request.getParameter("idVivero"));
		Huerto huerto = new Huerto();
		huerto.setId(id);
		huerto.setNombre(nombre);
		this.dao.crear(huerto);
		if (request.getParameter("idVivero") != null) {
			Long idElement = Long.valueOf(request.getParameter("idVivero"));
			this.dao.grabar(id, idElement);
		}

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException {
		log.debug("eliminar");

		this.dao.delete(id);

	}

}
