<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도전해보세요 9장 517p</title>
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

<script type="text/javascript" src="employees.js"></script>
</head>
<body>
<form method="post" action="login.do" name="frm">
<table>
	<tr>
		<td></td>
		<td></td>
		<td> 로그인 </td>
		<td> 사원 등록 <br> (관리자로 로그인 후 사용 가능) </td>
		<td> 마이페이지 <br> (로그인 후 사용 가능) </td>
	</tr>
</table>
<br>
<table>
	<tr>
		<td colspan="2"> 로그인 </td>
	</tr>
	<tr>
		<td> 아이디 </td>
		<td> <input type="text" name="id"> </td>
	</tr>
	<tr>
		<td> 비밀번호 </td>
		<td> <input type="password" name="pass"> </td>
	</tr>
	<tr>
		<td> 레벨 </td>
		<td>
			<select name="curLev">
				<option value="B"> 일반회원 </option>
				<option value="A"> 관리자 </option>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2"> <input type="submit" value="로그인" onclick="return loginCheck()"> <input type="reset" value="취소"> </td>
	</tr>
</table>
</form>
</body>
</html>