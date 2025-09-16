package com.test.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.emplDAO;

public class checkPassAction implements Action {
	public void executeMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String url = null;
		
		emplDAO eDao = emplDAO.getInstance();
		
		if (pass.equals(eDao.checkPass(id))) {
			url = "/empl/checkSuccess.jsp";
		}
		else {
			url = "/empl/checkPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
