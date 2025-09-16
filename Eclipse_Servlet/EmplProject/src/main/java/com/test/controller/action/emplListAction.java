package com.test.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.emplDAO;
import com.test.dto.emplVO;

public class emplListAction implements Action {
	@Override
	public void executeMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String url = "/empl/emplList.jsp"; 
		emplDAO eDao = emplDAO.getInstance();
		List<emplVO> emplList = eDao.selectAllEmpls();
		request.setAttribute("emplList",emplList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url); 
		dispatcher.forward(request, response);
	}
}
