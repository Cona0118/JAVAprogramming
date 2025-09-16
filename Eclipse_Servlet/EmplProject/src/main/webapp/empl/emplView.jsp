<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 상세보기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/empl.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/empl.js"></script>
</head>
<body>
<div class="wrap" align="center">
<h1>사원 상세보기</h1>
<table>
	<tr>
		<td>아이디</td>
		<td> ${empl.id} </td>
	</tr>
	<tr>
		<td>이름</td>
		<td> ${empl.name}</td>
	</tr>
	<tr>
		<td>회원구분</td>
		<td>
			<c:if test="${empl.lev == 'A'}"> 운영자 </c:if>
			<c:if test="${empl.lev == 'B'}"> 일반회원 </c:if>
		</td>
	</tr>
	<tr>
		<td>부서</td>
		<td>
			<c:if test="${empl.bigo == 'dept10'}"> 부서10 </c:if>
			<c:if test="${empl.bigo == 'dept20'}"> 부서20 </c:if>
		</td>
	</tr>
	<tr>
		<td>연락처</td>
		<td> ${empl.phone}</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td> ${empl.enter}</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>
			<c:if test="${empl.gender == 0}"> 남성 </c:if>
			<c:if test="${empl.gender == 1}"> 여성 </c:if>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"> 
			<!-- input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/emplServlet?command=emplUpdateForm&id=${empl.id}'"-->
			<input type="button" value="수정" onclick="open_win('${pageContext.request.contextPath}/emplServlet?command=checkPassForm&id=${empl.id}', 'update')">
			
			<!-- input type="button" value="삭제" onclick="location.href='${pageContext.request.contextPath}/emplServlet?command=emplDelete&id=${empl.id}'"--> 
			<input type="button" value="삭제" onclick="open_win('${pageContext.request.contextPath}/emplServlet?command=checkPassForm&id=${empl.id}', 'delete')">
			
			<input type="button" value="리스트로" onclick="location.href='${pageContext.request.contextPath}/emplServlet?command=emplList'"> 
		</td>
	</tr>
</table>
</div>
</body>
</html>