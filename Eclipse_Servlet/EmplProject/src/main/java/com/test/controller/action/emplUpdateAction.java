package com.test.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.emplDAO;
import com.test.dto.emplVO;

public class emplUpdateAction implements Action {
	@Override
	public void executeMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		emplVO eVo = new emplVO();
		
		eVo.setId(request.getParameter("id"));
		eVo.setPass(request.getParameter("pass"));
		eVo.setName(request.getParameter("name"));
		eVo.setLev(request.getParameter("lev"));
		eVo.setBigo(request.getParameter("bigo"));
		eVo.setPhone(request.getParameter("phone"));
		if(request.getParameter("gender") != null ) {
			eVo.setGender(Integer.parseInt(request.getParameter("gender")));
		}
		else {
			eVo.setGender(2);
		}
		
		
		emplDAO eDao = emplDAO.getInstance();
		eDao.updateEmpl(eVo);
		
		new emplListAction().executeMethod(request, response);
	}
}
