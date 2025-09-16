<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 등록</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/empl.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/empl.js"></script>
</head>
<body>
<div class="wrap" align="center">
<h1> 사원 정보 등록</h1>
<form method="post" action="${pageContext.request.contextPath}/emplServlet?command=emplInsert">
<table>
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" name="id" required>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pass" required></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" required></td>
	</tr>
	<tr>
		<td>회원구분</td>
		<td>
			<input type="radio" name="lev" value="A" checked> 운영자
			<input type="radio" name="lev" value="B"> 일반회원
		</td>
	</tr>
	<tr>
		<td>부서</td>
		<td>
			<select name="bigo">
				<option value="dept10"> 부서10</option>
				<option value="dept20"> 부서20</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>연락처</td>
		<td><input type="text" name="phone"></td>
	</tr>
	<tr>
		<td>성별</td>
		<td>
			<input type="radio" name="gender" value=0> 남성
			<input type="radio" name="gender" value=1> 여성
			<input type="radio" name="gender" value=2> 비공개
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"> 
			<input type="submit" value="등록">
			<input type="reset" value="취소"> 
			<input type="button" value="리스트로" onclick="location.href='${pageContext.request.contextPath}/emplServlet?command=emplList'"> 
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>