<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>

<c:if test="${param.userType == 1}"> 
<body style="background-color: gray;">
	${param.userid} 님이 사용자로 로그인하셨습니다.
</c:if>

<c:if test="${param.userType == 2}"> 
<body>
	${param.userid} 님이 관리자로 로그인하셨습니다.
</c:if>

</body>
</html>