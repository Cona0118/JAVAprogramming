<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>JSTL Test</title></head>
<body>
<h2>JSTL 테스트</h2>
<!-- JSTL core 태그 사용 -->
<p>메시지: ${message}</p>
<!-- 조건문 -->
<c:if test="${number gt 100}">
    <p>number 변수는 100보다 큽니다: ${number}</p>
</c:if>
<!-- 반복문 -->
<c:forEach var="i" begin="1" end="3">
    <p>반복: ${i}</p>
</c:forEach>
</body>
</html>