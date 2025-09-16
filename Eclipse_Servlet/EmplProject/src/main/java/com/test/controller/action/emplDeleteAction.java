package com.test.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.emplDAO;

public class emplDeleteAction implements Action {
	public void executeMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		
		emplDAO eDao = emplDAO.getInstance();
		eDao.deleteEmpl(id);
		
		new emplListAction().executeMethod(request, response);
	}
}
