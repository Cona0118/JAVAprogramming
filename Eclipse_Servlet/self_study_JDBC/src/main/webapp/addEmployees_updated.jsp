<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty loginUser}"> <jsp:forward page="login.do" /> </c:if>
<c:if test="${empty newEmployee}"> <jsp:forward page="main.jsp" /> </c:if>
<c:if test="${curLev == 'B'}"> <jsp:forward page="main.jsp" /> </c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원등록</title>
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
		<c:if test="${curLev == 'A'}"> <td  onclick="location.href='addEmployees.do'" style="cursor:pointer;"> 사원 등록 </td> </c:if>
		<td onclick="location.href='mypage.jsp'" style="cursor:pointer;"> 마이페이지 </td>
	</tr>
</table>
<br>
<table>
	<tr>
		<td colspan="2"> <h3>사원 정보</h3> 회원 등록에 성공하였습니다. </td>
	</tr>
	<tr>
		<td> 아이디 </td>
		<td> ${newEmployee.id} </td>
	</tr>
	<tr>
		<td> 비밀번호 </td>
		<td>${newEmployee.pass} </td>
	</tr>
	<tr>
		<td> 이름 </td>
		<td> ${newEmployee.name}</td>
	</tr>
	<tr>
		<td> 권한 </td>
		<td>
			<c:if test="${newEmployee.lev == 'B'}">일반회원</c:if>
			<c:if test="${newEmployee.lev == 'A'}">관리자</c:if>
		</td>
	</tr>
	<tr>
		<td> 성별 </td>
		<td>
			<c:if test="${newEmployee.gender == '1'}">남자</c:if>
			<c:if test="${newEmployee.gender == '2'}">여자</c:if>
		</td>
	</tr>
	<tr>
		<td> 전화번호 </td>
		<td> ${newEmployee.phone} </td>
	</tr>
	<tr>
		<td colspan="2"> <input type="button" value="메인페이지로 돌아가기" onclick="location.href='main.jsp'"> </td>
	</tr>
</table>
</body>
</html>