package com.front.pTipoAnt.controller;

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

import com.front.pTipoAnt.bussines.ServDepartamento;
import com.front.pTipoAnt.bussines.interfaces.IServicio;
import com.front.pTipoAnt.common.exceptions.ServicioException;
import com.front.pTipoAnt.data.Departamento;

@SuppressWarnings("serial")
@WebServlet("/jspDatos")
public class DepartamentosDatosController extends HttpServlet {
	
IServicio<Long,Departamento> servicio;

	
	private static final Logger log = Logger.getLogger(DepartamentosDatosController.class);

	public DepartamentosDatosController() {
		super();
		this.servicio = new ServDepartamento();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		List<Departamento> departamentos;
		try {
			departamentos = new ArrayList<Departamento>();
			departamentos = servicio.findAll();
			req.setAttribute("listado", departamentos);
		} catch (ServicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jspView");
		dispatcher.forward(req,resp);
	
	}
	
	

}
