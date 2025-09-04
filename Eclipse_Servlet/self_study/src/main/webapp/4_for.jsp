<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>232p 자연수 합 구하기</title>
</head>
<body>
<% int n = Integer.parseInt(request.getParameter("end")); %>
<h1>1부터 <%=n %>까지 자연수 합 구하기</h1>
<%
	int sum = 0;
	for(int i=1; i<=n; i++) {
		out.print(i);
		sum += i;
		if( i < n ) out.print("+");
		else out.print(" = ");
	}
	out.print(sum);
%>
</body>
</html>
