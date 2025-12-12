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
import com.pfinal.dao.ViveroDAO;
import com.pfinal.dao.interfaces.IDAO;
import com.pfinal.dao.interfaces.IDAOBasic;
import com.pfinal.model.Huerto;
import com.pfinal.model.Vivero;

@WebServlet("/vivero")
public class ViveroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IDAOBasic<Vivero> dao;
	IDAO<Huerto> huertoDAO;

	private static final Logger log = Logger.getLogger(ViveroController.class);

	public ViveroController() {
		super();
		this.dao = ViveroDAO.init();
		this.huertoDAO = HuertoDAO.init();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/vivero.jsp";

		try {
			String accion = request.getParameter("accion");
			String id = request.getParameter("id");
			String elemento = request.getParameter("elemento");
			String idVivero = request.getParameter("idVivero");

			log.debug("id:" + id);
			log.debug("accion:" + accion);
			log.debug("elemento:" + elemento);
			log.debug("idVivero:" + idVivero);
			
			if(!"VIVERO".equalsIgnoreCase(elemento)) id = idVivero;

			if (accion == null || !"VIVERO".equalsIgnoreCase(elemento))
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
				forward = "/viveros";
				crear(request, response,Long.valueOf(id));
				break;
			case "ELIMINAR":
				forward = "/viveros";
				eliminar(request, response,Long.valueOf(id));
				break;
			}
		} catch (DAOException e) {
			log.error("DAOException",e);
			request.setAttribute("error", e.getMensaje());
		}

		log.debug("forward" + forward);
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

		Vivero vivero = this.dao.find(id);
		
		
		log.debug("vivero:"+vivero);
		
		request.setAttribute("titulo", "Vivero");
		request.setAttribute("elemento", vivero);
		request.setAttribute("nombreElementos", "Huertos del Vivero");
		
		Long proximoId= this.huertoDAO.findLastId();
		log.debug("proximoId:" + proximoId);
		request.setAttribute("proximoId", ++proximoId);
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException{
		log.debug("modificar");

		String nombre = request.getParameter("nombre");
		this.dao.grabar(id, nombre);

	}

	private void crear(HttpServletRequest request, HttpServletResponse response, Long id) throws DAOException{
		log.debug("crear");
		String nombre = request.getParameter("nombre");
		Vivero vivero = new Vivero();
		vivero.setId(id);
		vivero.setNombre(nombre);
		this.dao.crear(vivero);

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response,Long id) throws DAOException{
		log.debug("eliminar");
		this.dao.delete(id);
	}

}
