<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>쇼핑몰</title>
</head>
<body>
	<%@ include file="main_top.jsp" %>
	


	<table style="width: 80%">
	
		<!-- top page에서 memberid를 가져옴 -->
		<% if(mem_id != null) { %>
			<tr style="text-align: center;">
				<td style="font-size: 20pt;">
					<%=mem_id %>님! 환영합니다.
				</td>
			</tr>
		<% } else { %>
			<tr style="text-align: center;">
				<td style="font-size: 20pt;">
					고객님, 어서오세요.
					<br/><br/><br/>
					<br/><br/><br/>
					<br/><br/><br/>
					<br/><br/><br/>
					<br/><br/><br/>
				</td>
			</tr>
			<tr>
				<td>				
					로그인 후 이용바랍니다.<br/>
				</td>
			</tr>
		<% } %>
	
	</table>

<!-- include bottom page -->
	<%@ include file="main_bottom.jsp" %>
	
</body>
</html>