<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>128P 회원가입 페이지 작성하기</title>
<script type="text/javascript" src="2_registcheck.js"></script>
</head>
<body>
<form method="post" action="registServlet" name="info">
이름			<input type="text" name="name"> <br>
주민등록번호	<input type="text" name="numPre"> - <input type="password" name="numPost"> <br>
아이디			<input type="text" name="userid"> <br>
비밀번호		<input type="password" name="userpwd"> <br>
비밀번호 확인	<input type="password" name="userpwdcheck"> <br>
이메일			<input type="text" name="useremailID"> @ <input type="text" name="useremailaddr">
				<select id="useremailaddr2" name="useremailaddr2">
					<option value="naver.com"> naver.com </option>
					<option value="daum.com"> daum.com </option>
					<option value="gmail.com"> gmail.com </option>
				</select> <br>
우편번호		<input type="text" name="addrNum"> <br>
주소			<input type="text" name="addr1"> <input type="text" name="addr2"> <br>
핸드폰 번호		<input type="text" name="phone"> <br>
직업			<select id="job" name="job">
					<option value="학생"> 학생 </option>
					<option value="직장인"> 직장인 </option>
					<option value="공무원"> 공무원 </option>
				</select> <br>
메일 정보 수신 	<input type="radio" name="permission" value="yes"> 수신 
			 	<input type="radio" name="permission" value="no"> 수신거부 <br>
관심 분야		<input type="checkbox" name="interest" value="원두"> 원두
			   	<input type="checkbox" name="interest" value="로스팅"> 로스팅
			   	<input type="checkbox" name="interest" value="핸드드립"> 핸드드립
			   	<input type="checkbox" name="interest" value="에스프레소"> 에스프레소 <br>
<input type="submit" value="전송" onclick="return register()"> 
<input type="reset" value="취소">
</form>
</body>

</html>
