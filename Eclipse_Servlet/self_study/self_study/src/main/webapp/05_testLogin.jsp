<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = "pinksung";
String pwd = "1234";
String name = "성윤정";

if(id.equals(request.getParameter("id")) && pwd.equals(request.getParameter("pwd")) ){
	// 1. 쿠캐 객체 생성 (username에 성윤정을 맵핑)
	Cookie username = new Cookie("username","성윤정");
	
	// 2. 속성 부여
	
	// 3. 클라이언트에 전송
	response.addCookie(username);
%>
	<h2>성공적으로 로그인하셨습니다.</h2>
	<p> <a href="05_main.jsp"> 들어가기 </a>
<%
} else{
%>	<h2>로그인에 실패했습니다.</h2>
	<p> <a href="05_loginForm.jsp"> 되돌아가기 </a>
<%
}
%>