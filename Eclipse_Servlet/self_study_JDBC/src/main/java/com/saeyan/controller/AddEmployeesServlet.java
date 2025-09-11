package com.saeyan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.EmployeesDAO;
import com.saeyan.dto.EmployeesVO;

@WebServlet("/addEmployees.do")
public class AddEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        String curLev = (String) session.getAttribute("curLev");
        if (!"A".equals(curLev)) {
            response.sendRedirect("main.jsp"); 
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("addEmployees.jsp");
        dispatcher.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String curLev = (String) session.getAttribute("curLev");
	    if (!"A".equals(curLev)) {
	        response.sendRedirect("main.jsp");
	        return;
	    }
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String lev = request.getParameter("lev");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		
		EmployeesVO eVo = new EmployeesVO();
		eVo.setId(id);
		eVo.setPass(pass);
		eVo.setName(name);
		eVo.setLev(lev);
		eVo.setGender(gender);
		eVo.setPhone(phone);
		
		EmployeesDAO mDao = EmployeesDAO.getInstance();
		int result = mDao.insertEmployees(eVo);
		
		if (result == 1) {
			request.setAttribute("newEmployee", eVo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("addEmployees_updated.jsp");
			dispatcher.forward(request,response);
		}
		else {
			response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script type='text/javascript'>");
		    out.println("alert('사원 등록에 실패했습니다.');");
		    out.println("history.back();");
		    out.println("</script>");
		}
		
		
	
	}

}
