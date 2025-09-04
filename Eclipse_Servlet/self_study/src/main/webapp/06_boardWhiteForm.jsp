<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<style>
table, td {
	border : 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
<form method="post" action="06_boardWhite.jsp">
<table>
	<caption>게시판 글쓰기</caption>
	<tr>
		<td> 작성자 </td>
		<td> <input type="text" name="name"  size="15"> </td>
	</tr>
	<tr>
		<td> 비밀번호 </td>
		<td> <input type="password" name="pwd" size="15"> (게시물 수정, 삭제 시 필요합니다.)</td>
	</tr>
	<tr>
		<td> 이메일 </td>
		<td> <input type="text" name="email" size="30" > </td>
	</tr>
	<tr>
		<td> 글 제목 </td>
		<td> <input type="text" name="title" size="55" > </td>
	</tr>
	<tr>
		<td> 글 내용 </td>
		<td> <textarea rows="30" cols="60" name="content"></textarea> </td>
	</tr>
	<tr>
		<td colspan="2"> 
			<input type="submit" value="등록"> <input type="reset" value="다시 작성"> 
		</td>
	</tr>

</table>
</form>
</body>
</html>