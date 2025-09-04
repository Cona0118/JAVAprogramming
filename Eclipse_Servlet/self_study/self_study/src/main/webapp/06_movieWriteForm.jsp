<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 정보 등록</title>
</head>
<body>
<h2>정보 등록</h2>
<form method="post" action="06_movieWrite.jsp">
<table>
	<tr>
		<td> 제목 </td>
		<td> <input type="text" name="title" size ="15"> </td>
	</tr>
	<tr>
		<td> 가격 </td>
		<td> <input type="text" name="price" size ="15"> 원 </td>
	</tr>
	<tr>
		<td> 감독 </td>
		<td> <input type="text" name="director" size ="15"> </td>
	</tr>
	<tr>
		<td> 출연배우 </td>
		<td> <input type="text" name="actor" size ="15"> </td>
	</tr>
	<tr>
		<td> 시놉시스 </td>
		<td> <textarea rows="20" cols="40" name="synopsis"></textarea> </td>
	</tr>
	<tr>
		<td> 장르 </td>
		<td> 
			<select name="genre">
				<option value="코미디">코미디</option>
				<option value="스릴러">스릴러</option>
				<option value="액션">액션</option>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="확인">
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>