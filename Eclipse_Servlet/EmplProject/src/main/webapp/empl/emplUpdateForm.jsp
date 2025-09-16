<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 수정</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/empl.css">
</head>
<body>
<div class="wrap" align="center">
<h1> 사원 정보 수정</h1>
<form method="post" action="${pageContext.request.contextPath}/emplServlet?command=emplUpdate">
<table>
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id" value="${empl.id}" readonly></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pass"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" value="${empl.name}"></td>
	</tr>
	<tr>
		<td>회원구분</td>
		<td>
			<input type="radio" name="lev" value="A" <c:if test="${empl.lev == 'A'}"> checked </c:if>> 운영자
			<input type="radio" name="lev" value="B" <c:if test="${empl.lev == 'B'}"> checked </c:if>> 일반회원
		</td>
	</tr>
	<tr>
		<td>부서</td>
		<c:if test="${empl.bigo == 'dept10'}">
		<td>
			<select name="bigo">
				<option value='dept10'> 부서10</option>
				<option value='dept20'> 부서20</option>
			</select>
		</td>
		</c:if>
		<c:if test="${empl.bigo == 'dept20'}">
		<td>
			<select name="bigo">
				<option value='dept20'> 부서20</option>
				<option value='dept10'> 부서10</option>
			</select>
		</td>
		</c:if>
	</tr>
	<tr>
		<td>연락처</td>
		<td><input type="text" name="phone" value="${empl.phone}"></td>
	</tr>
	<tr>
		<td>성별</td>
		<td>
			<input type="radio" name="gender" value="0" <c:if test="${empl.gender == 0}"> checked </c:if>> 남성
			<input type="radio" name="gender" value="1" <c:if test="${empl.gender == 1}"> checked </c:if>> 여성
			<input type="radio" name="gender" value="2" <c:if test="${empl.gender == 2}"> checked </c:if>> 비공개
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"> 
			<input type="submit" value="완료">
			<input type="reset" value="취소"> 
			<input type="button" value="리스트로" onclick="location.href='${pageContext.request.contextPath}/emplServlet?command=emplList'"> 
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>