<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원삭제</title>
</head>
<body>
	<form action="./MemberDeleteReal.do" style="margin-left: 100px; margin-top: 30px;">
		<input type="hidden" name="mem_id" value="${mem_id}">
		<input type="password" name="mem_passwd" placeholder="비밀번호를 입력하세요"><br>
		<input type="submit" value="회원삭제"
			style="margin-left: 50px; margin-top: 10px;">
	</form>

</body>
</html>