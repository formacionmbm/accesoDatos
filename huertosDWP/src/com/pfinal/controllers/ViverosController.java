package com.pfinal.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pfinal.common.DAOException;
import com.pfinal.dao.ViveroDAO;
import com.pfinal.dao.interfaces.IDAOBasic;
import com.pfinal.model.Vivero;

@WebServlet("/viveros")
public class ViverosController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IDAOBasic<Vivero> dao;

	private static final Logger log = Logger.getLogger(ViverosController.class);

	public ViverosController() {
		super();
		this.dao = ViveroDAO.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");
		String forward = "/viveros.jsp";
		try {
			List<Vivero> viveros = new ArrayList<Vivero>();
			viveros = dao.findAll();
			log.debug("viveros:" + viveros);
			request.setAttribute("titulo", "Viveros");
			request.setAttribute("elementos", viveros);
			
			Long proximoId= dao.findLastId();
			log.debug("proximoId:" + proximoId);
			request.setAttribute("proximoId", ++proximoId);
			
		} catch (DAOException e) {
			log.error("DAOException",e);
			request.setAttribute("error", e.getMensaje());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPost");
		doGet(request,response);
	}
}
