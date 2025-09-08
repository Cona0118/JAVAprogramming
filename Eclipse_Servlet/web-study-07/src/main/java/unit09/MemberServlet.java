package unit09;

import java.io.IOException;

import com.saeyan.javabeans.MemberBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberBean member = new MemberBean("전수빈","pinksubin");
		
		request.setAttribute("member",member);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("09_el.jsp");
		dispatcher.forward(request, response);
	}
}
