<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty loginUser}"> <jsp:forward page="login.do" /> </c:if>
<c:if test="${curLev == 'B'}"> <jsp:forward page="main.jsp" /> </c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 추가</title>
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
<form method="post" action="addEmployees.do" name="frm">
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
<br>
<table>
	<tr>
		<td colspan="2"> 사원 등록 </td>
	</tr>
	<tr>
		<td> 아이디 </td>
		<td> <input type="text" name="id" > </td>
	</tr>
	<tr>
		<td> 비밀번호 </td>
		<td> <input type="password" name="pass"> </td>
	</tr>
	<tr>
		<td> 이름 </td>
		<td> <input type="text" name="name" > </td>
	</tr>
	<tr>
		<td> 권한 </td>
		<td>
			<select name="lev">
				<option value="B"> 일반회원 </option>
				<option value="A"> 관리자 </option>
			</select>
		</td>
	</tr>
	<tr>
		<td> 성별 </td>
		<td>
			<select name="gender">
				<option value="1"> 남자 </option>
				<option value="2"> 여자 </option>
			</select>
		</td>
	</tr>
	<tr>
		<td> 전화번호 </td>
		<td> <input type="text" name="phone"> </td>
	</tr>
	<tr>
		<td colspan="2"> <input type="submit" value="등록"> <input type="reset" value="취소"> <input type="button" value="메인페이지로 이동" onclick="location.href='main.jsp'"> </td>
	</tr>
</table>
</form>

</body>
</html>