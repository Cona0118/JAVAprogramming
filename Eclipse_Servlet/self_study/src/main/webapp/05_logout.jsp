<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<%
Cookie ck[] = request.getCookies();

for(Cookie c : ck){
	if(c.getName().equals("username")) {
		c.setMaxAge(0);
		response.addCookie(c);
	}
}
%>

<script>
	alert("로그아웃되었습니다.");
	location.href="05_loginForm.jsp";
</script>
</body>
</html>