<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 조건문, 반복문 등 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 날짜, 숫자 등 포맷 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shopping.css">
</head>
<body>
<div id="wrap" align="center"> <!-- 전체 영역 감싸기, 중앙 정렬 -->
<h1> 게시글 리스트 </h1>
<table class="list">
	<tr>
		<td colspan="5" style="border:white; text-align:right">
			<a href="${pageContext.request.contextPath}/BoardServlet?command=board_write_form">게시글 등록</a>
		</td>
	</tr>
	<tr> <th>번호</th> <th>제목</th> <th>작성자</th> <th>작성일</th> <th>조회</th> </tr>
	<c:forEach var="board" items="${boardList}">
	<tr class="record">
		<td>${board.num }</td>
		<td> <a href="${pageContext.request.contextPath}/BoardServlet?command=board_view&num=${board.num }">${board.title }</a> </td>
		<td>${board.name }</td>
		<td> <fmt:formatDate value="${board.writedate}" /> </td>
		<td>${board.readcount}</td>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>