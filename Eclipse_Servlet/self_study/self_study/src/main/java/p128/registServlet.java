package p128;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registServlet")
public class registServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String name = request.getParameter("name");
		String numPre = request.getParameter("numPre");
		String numPost = request.getParameter("numPost");
		String userid = request.getParameter("userid");
		String useremailID = request.getParameter("useremailID");
		String useremailaddr = request.getParameter("useremailaddr");
		String useremailaddr2 = request.getParameter("useremailaddr2");
		String addrNum = request.getParameter("addrNum");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String phone = request.getParameter("phone");
		String job = request.getParameter("job");
		String interests[] = request.getParameterValues("interest");
		
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("이름: ");
		out.println(name);
		out.print("<br>주민등록번호: ");
		out.println(numPre + "-" + numPost);
		out.print("<br>아이디: ");
		out.println(userid);
		out.print("<br>이메일: ");
		if(useremailaddr == null || useremailaddr == "") out.println(useremailID + "@" + useremailaddr2);
		else out.println(useremailID + "@" + useremailaddr);
		out.print("<br>우편번호: ");
		out.println(addrNum);
		out.print("<br>주소: ");
		out.println(addr1 + " " + addr2);
		out.print("<br>핸드폰 번호: ");
		out.println(phone);
		out.print("<br>직업: ");
		out.println(job);
		
		out.print("<br>관심분야: ");
		if (interests == null) {
			out.print("선택한 항목이 없습니다.");
		} else {
			for (String interest: interests) {
				out.print(interest +" ");
			}
		}
		
		out.println("<br><a href='javascript: history.go(-1)'>다시</a>");
		out.print("</body></html>");
		out.close();
	}
}
