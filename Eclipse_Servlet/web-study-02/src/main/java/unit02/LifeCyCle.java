package unit02;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LifeCyCle")
public class LifeCyCle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    int initCount = 1;
    int doGetCount = 1;
    int destroyCount = 1;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드는 첫 요청만 호출 : "+ initCount++);	
	}

	public void destroy() {
		System.out.println("destroy 메소드는 톰캣이 종료될 때만 호출 : "+ destroyCount++);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 메소드가 요청될 때마다 호출 : "+ doGetCount++);		
	}
}

