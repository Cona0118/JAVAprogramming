<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<div class="wrap" align="center">
<h1>비밀번호 확인</h1>
<form method="post" action="${pageContext.request.contextPath}/emplServlet?command=checkPass">
<input type="hidden" name="id" value="${param.id}">
<table>
	<tr>
		<td> <input type="password" name="pass"> </td>
		<td> <input type="submit" value="확인"> </td>
	</tr>
</table>
<br><br>
${message}
</form>
</div>
</body>
</html>