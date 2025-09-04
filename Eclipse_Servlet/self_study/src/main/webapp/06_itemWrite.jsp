<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="item" class="com.mission.javaneans.itemBean" />
<jsp:setProperty name="item" property="*" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 페이지</title>
</head>
<body>
<h2>입력 완료된 정보</h2>
<table>
	<tr>
		<td> 상품명 </td>
		<td> <jsp:getProperty name="item" property="product" /> </td>
	</tr>
	<tr>
		<td> 가격 </td>
		<td> <jsp:getProperty name="item" property="price" />원</td>
	</tr>
	<tr>
		<td> 설명 </td>
		<td> <jsp:getProperty name="item" property="detail" /> </td>
	</tr>
</table>
</body>
</html>