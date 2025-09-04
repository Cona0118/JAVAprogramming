<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바 빈 프로퍼티 값 변경</title>
</head>
<body>
<jsp:useBean id="member" class="com.saeyan.javabeans.MemberBean"/>

자바 빈 객체 생성 후 저장된 정보 출력 <br><br>
이름 : <jsp:getProperty name="member" property="name" /> <br>
아이디 : <jsp:getProperty name="member" property="userid" /> 
<hr>

정보 변경 후 변경된 정보 출력<br><br>
<jsp:setProperty name="member" property="name" value="전수빈" />
<jsp:setProperty name="member" property="userid" value="pinksubin" />

이름 : <jsp:getProperty name="member" property="name" /> <br>
아이디 : <jsp:getProperty name="member" property="userid" /> 

</body>
</html>