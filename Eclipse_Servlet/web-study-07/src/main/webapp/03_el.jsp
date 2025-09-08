<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
<!-- 덧셈 : 7  -->
\${5+2): ${5+2} <br>

<!-- 나눗셈 : 2.5  -->	
\$(5/2): ${5/2} <br>	<!-- / 혹은 div -->

<!-- 나머지 : 1  -->
\${5 mod 2): ${5 mod 2}<br> 	

<!-- 크기비교  -->
\${5 > 2}: ${5 > 2}<br>		
\${2 gt 10}: ${2 gt 10} <br>	<!-- gt = > -->

<!-- 조건 -->
\${(5 > 2) ? 5: 2}: ${(5 > 2) ? 5: 2} <br> <!-- 5 -->

<!-- 논리 -->
\${(5 > 2) || (2 < 10)} : ${(5 > 2) || (2 < 10)} <br>

<!-- null 검사 T/F -->
<% String input=null; %>
\${empty input}: ${empty input}<br>
</body>
</html>