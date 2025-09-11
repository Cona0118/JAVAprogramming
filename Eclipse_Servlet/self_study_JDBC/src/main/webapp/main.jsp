<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty loginUser}"> <jsp:forward page="login.do" /> </c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원전용 페이지</title>
<style>
table {
	margin: auto;
	text-align: center;
}
td {
  border: 1px solid black;
  width:300px;
  height: 30px
}
</style>
</head>
<body>
<table>
	<tr>
		<td> ${loginUser.name}님 반갑습니다</td>
		<td> 레벨 : ${curLev}</td>
		<td onclick="location.href='logout.do'" style="cursor:pointer;"> 로그아웃 </td>
		<c:if test="${curLev == 'B'}"> <td> 사원 등록 <br> (관리자로 로그인 후 사용 가능) </td> </c:if>
		<c:if test="${curLev == 'A'}"> <td onclick="location.href='addEmployees.do'" style="cursor:pointer;"> 사원 등록 </td> </c:if>
		<td onclick="location.href='mypage.jsp'" style="cursor:pointer;"> 마이페이지 </td>
	</tr>
</table>

<h2 style="text-align:center"> 회원 전용 페이지</h2>
</body>
</html>