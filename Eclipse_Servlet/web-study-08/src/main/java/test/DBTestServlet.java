package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class DBTestServlet
 */
@WebServlet("/DBTestServlet")
public class DBTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/scott?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "univ301";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 드라이버 로드 (8 이후 버전은 생략 가능하지만 명시하는게 안정적)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // DB 연결
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            // 쿼리 실행
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT NOW() AS currentTime"); // 현재 시간 조회
            if (rs.next()) {
                String currentTime = rs.getString("currentTime");
                out.println("<h2>DB 연결 성공!</h2>");
                out.println("<p>현재 MySQL 서버 시간: " + currentTime + "</p>");
            } else {
                out.println("<p>데이터를 가져오지 못했습니다.</p>");
            }
        } catch (ClassNotFoundException e) {
            out.println("<p>JDBC 드라이버 로드 실패: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("<p>DB 연결/쿼리 오류: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
	}
}
 