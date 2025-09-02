<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! // 선언문
	String str = "안녕하세요";
	int a = 5, b = -4;
	
	public int abs(int n){
		if(n<0) n = -n;
		return n;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언문에 변수/메서드 정의</title>
</head>
<body>
<% // 스크립트릿
	out.print(str+"<br>");
	out.print(a+"의 절대값 : "+ abs(a)+"<br>");
	out.print(b+"의 절대값 : "+ abs(b)+"<br>");
%>
</body>
</html>