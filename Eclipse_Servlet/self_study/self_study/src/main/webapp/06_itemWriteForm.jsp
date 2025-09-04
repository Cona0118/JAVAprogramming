<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 입력</title>
</head>
<body>
<h2>정보 입력 폼</h2>
<form method="post" action="06_itemWrite.jsp">
<table>
	<tr>
		<td> 상품명 </td>
		<td> <input type="text" name="product"  size="15"> </td>
	</tr>
	<tr>
		<td> 가격 </td>
		<td> <input type="text" name="price" size="15"> </td>
	</tr>
	<tr>
		<td> 설명 </td>
		<td> <textarea rows="10" cols="60" name="detail"></textarea> </td>
	</tr>
	<tr>
		<td colspan="2"> 
			<input type="submit" value="등록"> <input type="reset" value="취소"> 
		</td>
	</tr>
</table>
</form>
</body>
</html>