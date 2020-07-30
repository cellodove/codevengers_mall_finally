<%@page import="ven.shop.model.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="memberDAO" class="ven.shop.dao.AdminDAO"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 회원관리</title>
<script src="../js/script.js?ver=0.2"></script>
</head>
<body>
	<%@ include file="admin_top.jsp" %>
	<table style="width:80%">
		<tr style="background-color: cyan">
			<th>아이디</th><th>회원명</th><th>전화</th><th>주소</th><th>이메일</th><th>수정</th>
		</tr>
	<%
		ArrayList<MemberVO> list = memberDAO.getMemberAll();
		for(MemberVO vo : list){
	%>
			<tr>
				<td><%=vo.getMem_id() %></td>
				<td><%=vo.getMem_name() %></td>
				<td><%=vo.getMem_tel1() %>-<%=vo.getMem_tel2() %>-<%=vo.getMem_tel3() %></td>
				<td><%=vo.getMem_address1() %><%=vo.getMem_address2() %></td>
				
				<td><%=vo.getMem_email() %></td>
				<td><a href="javascript:admin_modify_member('<%=vo.getMem_id() %>')">수정하기</a></td>
			</tr>
	<%
		}
	%>
	</table>	
	
	<%@ include file="admin_bottom.jsp" %>
	
	<form action="member_modify_admin_form.jsp" name="modifyFrm" method="post">
		<input type="hidden" name="id">
	</form>
</body>
</html>