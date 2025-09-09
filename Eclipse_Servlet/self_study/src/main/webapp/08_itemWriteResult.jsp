<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String url = "jdbc:mysql://localhost:3306/scott?useSSL=false&serverTimezone=UTC";
	String uid = "root";
	String pass = "univ301";
	
	String sql = "insert into item values(?,?,?)";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 입력 완료</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");

String name = request.getParameter("name");
String price = request.getParameter("price");
String description = request.getParameter("description");

try{
	Class.forName("com.mysql.cj.jdbc.Driver"); 
	conn = DriverManager.getConnection(url, uid, pass); 
	pstmt = conn.prepareStatement(sql); 
	
	pstmt.setString(1,name);
	pstmt.setInt(2,Integer.parseInt(price));
	pstmt.setString(3,description);
	
	pstmt.executeUpdate(); 
	
	} catch(Exception e){
		e.printStackTrace();
} finally{
	try{ 
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	} catch(Exception e){
		e.printStackTrace();
	}
}
%>
<a href="08_itemWrite.jsp"> 상품 확인</a>

</body>
</html>