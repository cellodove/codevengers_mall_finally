<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="memberDAO" class="ven.shop.dao.AdminDAO"/>
<jsp:useBean id="memberVO" class="ven.shop.model.MemberVO"/>
<jsp:setProperty property="*" name="memberVO"/>
<%
	boolean b = memberDAO.modifyData(memberVO);
	if(b){
%>
	<script>
		alert("수정되었습니다");
		location.href="membermanager.jsp"
	</script>
<%	} else { %>
	<script>
		alert("수정 실패!");
		history.back();
	</script>	
<%	} %>