<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<jsp:useBean id="memberDAO" class="ven.shop.dao.MemberDAO"/>
<% request.setCharacterEncoding("utf-8"); %>
<% String mem_id = request.getParameter("mem_id"); %>
<% boolean b = memberDAO.checkId(mem_id); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id 중복 검사</title>
</head>
<body>
<b><%=mem_id %></b>
<%
	if(b){
%>
		는(은) 이미  사용중인 id 입니다.<p/>
		<a href="#" onclick = "opener.document.regForm.id.focus(); window.close();">닫기</a>
		<!-- opener : window.open으로 현재 창을 호출한 부모 페이지 -->
<%
	} else {
%>
		는(은) 사용 가능 합니다.<p/>
		<a href="#" onclick = "opener.document.regForm.pass.focus(); window.close();">닫기</a>
<%	} %>
</body>
</html>