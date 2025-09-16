package com.test.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.emplDAO;
import com.test.dto.emplVO;

public class emplUpdateFormAction implements Action {
	@Override
	public void executeMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String url = "/empl/emplUpdateForm.jsp"; 
		String id = request.getParameter("id");
		emplDAO eDao = emplDAO.getInstance();
		emplVO eVo = eDao.selectOneEmpl(id);
		
		request.setAttribute("empl", eVo);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
