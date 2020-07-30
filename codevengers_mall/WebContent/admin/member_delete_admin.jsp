<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="memberDAO" class="ven.shop.dao.AdminDAO"/>
<%
	String mem_id = (String)request.getParameter("mem_id");
	boolean b = memberDAO.deleteData(mem_id);
	if(b){
%>
		<script>
			alert("삭제 성공");
			location.href = "membermanager.jsp";
		</script>	
<% } else { %>
		<script>
			alert("삭제 실패!\n");
			history.back();
		</script>	
<%
	}
%>