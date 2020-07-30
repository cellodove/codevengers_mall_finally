<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	
	<form action="./MemberLoginCheck.do" method="post"
		style="padding-left: 100px; padding-top: 50px;">
		아이디<input type="text" name="mem_id" style="margin-left: 14px;"><br>
		비밀번호<input type="password" name="mem_passwd" style="margin-top: 10px;"><br>
		<input type="submit" value="로그인"
			style="margin-left: 108px; margin-top: 10px;">
	</form>
	<button type="button" value="button"
		onclick="location.href='./MemberWrite.do'" class="btnOk"
		style="padding-left: 6px; margin-left: 200px; margin-top: 10px;">회원가입</button>


</body>
</html>