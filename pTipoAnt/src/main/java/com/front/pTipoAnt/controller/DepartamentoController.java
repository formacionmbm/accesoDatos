package com.front.pTipoAnt.controller;

import java.io.IOException;
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
import com.front.pTipoAnt.common.Constantes;
import com.front.pTipoAnt.common.Tipo;
import com.front.pTipoAnt.common.exceptions.ServicioException;
import com.front.pTipoAnt.common.exceptions.TipoException;
import com.front.pTipoAnt.data.Departamento;

/**
 * Servlet implementation class DepartamentoController
 */
@WebServlet("/departamento")
public class DepartamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IServicio<Long, Departamento> servicio;

	private static final Logger log = Logger.getLogger(DepartamentoController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepartamentoController() {
		super();
		this.servicio = new ServDepartamento();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String jsp = "/departamento.jsp";

		String accion = request.getParameter("accion");
		String id = request.getParameter("id");

		log.debug("id" + id);
		log.debug("accion" + accion);

		if (accion != null)
			accion = accion.trim();
		switch (accion) {

		case Constantes.FORMULARIO_MODIFICAR: 
			prepararFormModificacion(request, response);
		
		case Constantes.VISUALIZAR:
			visualizar(request, response);
			break;
		case Constantes.MODIFICAR:
			modificar(request, response);
			break;
		}
		request.setAttribute("tipos", Tipo.values());

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	private void prepararFormModificacion(HttpServletRequest request, HttpServletResponse response) {
		log.debug("prepararFormModificacion");
		try {
			if (request.getParameter("id") == null && "".equals(request.getParameter("id").trim())) {
				Long id = Long.valueOf(request.getParameter("id"));
				Departamento departamento = this.servicio.findOne(id);
				request.setAttribute("elemento", departamento);
			} else {
				throw new Exception();
			}
		} catch (NumberFormatException e) {
			gestionExcepcion(e, request);
		} catch (ServicioException e) {
			gestionExcepcion(e, request);
		} catch (Exception e) {
			gestionExcepcion(e, request);
		}

	}

	private void visualizar(HttpServletRequest request, HttpServletResponse response) {
		log.debug("visualizar");
		try {
			Departamento departamento = new Departamento();
			if (request.getParameter("id") != null && !"".equals(request.getParameter("id").trim())) {
				Long id = Long.valueOf(request.getParameter("id"));
				departamento = this.servicio.findOne(id);
				request.setAttribute("elemento", departamento);
			} else {
				throw new Exception();
			}
		} catch (NumberFormatException e) {
			gestionExcepcion(e, request);
		} catch (ServicioException e) {
			gestionExcepcion(e, request);
		} catch (Exception e) {
			gestionExcepcion(e, request);
		}

	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		log.debug("modificar");
		try {
			Departamento departamento = new Departamento();
			if ((request.getParameter("id") != null && !"".equals(request.getParameter("id").trim()))
					&& (request.getParameter("nombre") != null && !"".equals(request.getParameter("nombre").trim()))) {
				Long id = Long.valueOf(request.getParameter("id"));
				departamento.setId(id);
				departamento = this.servicio.findOne(id);
				departamento.setNombre(request.getParameter("nombre"));
				this.servicio.update(departamento);
				request.setAttribute("elemento", departamento);
			} else {
				throw new Exception();
			}
		} catch (NumberFormatException e) {
			gestionExcepcion(e, request);
		} catch (ServicioException e) {
			gestionExcepcion(e, request);
		} catch (Exception e) {
			gestionExcepcion(e, request);
		}

	}
	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPost");
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPut");
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doDelete");
		doGet(request, response);
	}

	private void gestionExcepcion(Exception e, HttpServletRequest request) {
		log.debug("gestionExcepcion");

		ResourceBundle rb = ResourceBundle.getBundle("error");
		String mensajeError = "";
		if (e instanceof ServicioException) {
			ServicioException se = (ServicioException) e;
			log.info("Tipo Excepcion de Servicio:" + se.getTipoExcepcion().toString());
			mensajeError = rb.getString(se.getTipoExcepcion().toString());

		} else {
			log.info("Tipo Excepcion General:" + TipoException.EXCEPCION_GENERAL.toString());
			mensajeError = rb.getString(TipoException.EXCEPCION_GENERAL.toString());
		}
		log.info("Mensaje Error:" + mensajeError);
		request.setAttribute("error", mensajeError);
	}

}
