<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<%
// name과 value를 얻어와서 비교
Cookie ck[] = null;

// 1. 클라이언트로부터 Cookie[]를 얻어옴
ck = request.getCookies();

if( ck!= null ){
	// 2. name을 얻어 "username"인지 비교
	for(Cookie c : ck){
		if(c.getName().equals("username")) {
			// 3. 쿠키의 value를 얻어와 출력
			out.println(c.getValue());
		}
	}
%>	 님 안녕하세요!<br>
	<form method="post" action="05_logout.jsp">
		<input type="submit" value="로그아웃">
	</form>
<%
}	else{
%>
	<h2> 로그인에 실패했습니다.</h2>
	<p> <a href="05_loginForm.jsp"> 되돌아가기</a>
<%
}
%>
</body>
</html>