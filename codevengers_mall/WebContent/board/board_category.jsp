<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시판 웹사이트</title>
</head>
<body>
 <%@ include file="../member/main_top.jsp" %>
<table border="1" style="text-align: center">

<tr>
<td width="1850" height="850" style="cursor:pointer;" onclick="location.href='../BoardList.bf'" onmouseover="window.status=''">
QnA 게시판
</td>
<td width="1850" height="850" style="cursor:pointer;" onclick="location.href='../RvWList.so'" onmouseover="window.status=''">
Review 게시판(공사중)
</td>
</tr>
</table>
</body>
</html>