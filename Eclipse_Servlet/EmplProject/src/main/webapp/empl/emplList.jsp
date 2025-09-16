<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 전체 리스트</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/empl.css">
</head>
<body>
<div class="wrap" align="center">
<h1> 사원 전체 리스트</h1>
<form>
	<a href="${pageContext.request.contextPath}/empl/emplInsertForm.jsp" >사원등록</a>
	<table>
		<tr>
			<td>아이디</td><td>이름</td>
		</tr>
	
		<c:forEach var="empl" items="${emplList}">
		<tr>
			<td>${empl.id}</td>
			<td> <a href="${pageContext.request.contextPath}/emplServlet?command=emplView&id=${empl.id}">${empl.name}</a> </td>
		</tr>
		</c:forEach>
	</table>
</form>
</div>
</body>
</html>