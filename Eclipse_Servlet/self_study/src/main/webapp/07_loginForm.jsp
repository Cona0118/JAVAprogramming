<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>
<form method="post" action="07_login.jsp">
아이디 : <input type="text" name="userid"> <br>
암 &nbsp; 호 : <input type="password" name="userpwd"> <br>
<label for="user"> <input type="radio" id="user" name="userType" value="1"> 사용자 </label>
<label for="admin"> <input type="radio" id="admin" name="userType" value="2"> 관리자 </label> <br>
<input type="submit" value="로그인">
</form>
</body>
</html>