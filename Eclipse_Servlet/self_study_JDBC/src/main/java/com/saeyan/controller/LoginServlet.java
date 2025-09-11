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


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "login.jsp";
        HttpSession session = request.getSession();
        
        if(session.getAttribute("loginUser") != null) { 
            url = "main.jsp"; 
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "login.jsp"; 
        
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
        String curLev = request.getParameter("curLev"); 
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        EmployeesDAO mDao = EmployeesDAO.getInstance();
        int result = mDao.userCheck(id, pass);
        
        
        if(result == 1){
            EmployeesVO mVo = mDao.getEmployees(id);
            
            
            if (curLev.equals("A") && !mVo.getLev().equals("A")) {
                out.println("<script type='text/javascript'>");
                out.println("alert('관리자 권한이 없습니다.');");
                out.println("history.back();");  
                out.println("</script>");
                return; 
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", mVo);
                session.setAttribute("curLev", curLev);  
                url = "main.jsp"; 
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
                return;
            }
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
            out.println("history.back();"); 
            out.println("</script>");
        }
    }
}