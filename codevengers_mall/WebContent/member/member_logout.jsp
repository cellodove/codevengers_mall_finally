<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% session.removeAttribute("mem_id");%>
	<script type="text/javascript">
		alert("로그아웃 성공");
		location.href="../index.jsp";
	</script>
</body>
</html>