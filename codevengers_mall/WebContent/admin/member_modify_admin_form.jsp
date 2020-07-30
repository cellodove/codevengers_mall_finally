<%@page import="ven.shop.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="memberDAO" class="ven.shop.dao.AdminDAO"/>
<%
	String id = request.getParameter("id");
	MemberVO memberVO =  memberDAO.getData(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/script.js?ver=2"></script>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("btnModify").onclick = admin_modify_member_action;
	document.getElementById("btnModifyCancel").onclick = admin_modify_member_cancel;
	document.getElementById("btnDelete").onclick = 
		function(){
		admin_modify_member_delete("<%=memberVO.getMem_id()%>");
	}
}
</script>
</head>
<body>
<br>
<table class="table">
<tr>
	<td align="center" valign="middle" style="background-color: #FFFFCC">
		<form name="modifyFormAdmin" method="post" action=member_modify_admin_action.jsp>
			<table border="1">
				<tr align="center" style="background-color: #8899aa">
					<td colspan="2"><b style="color: #FFFFFF"><%=memberVO.getMem_name() %>님의 정보를 수정(관리자)</b></td>
				</tr>
				<tr>
					<td width="16%">아이디</td>
					<td width="57%">
						<%=memberVO.getMem_id()%>
						<input type="hidden" name="mem_id" value="<%=memberVO.getMem_id() %>" />
					</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="mem_passwd" size="15" value="<%=memberVO.getMem_passwd() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="mem_name" size="15" value="<%=memberVO.getMem_name() %>"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="mem_email" size="27" value="<%=memberVO.getMem_email() %>"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
					<input type="text" name="tel1" size="10" value="<%=memberVO.getMem_tel1() %>">
					<input type="text" name="tel2" size="10" value="<%=memberVO.getMem_tel2() %>">
					<input type="text" name="tel3" size="10" value="<%=memberVO.getMem_tel3() %>">
					</td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td>
						<input type="text" name="zipcode" size="7" value="<%=memberVO.getMem_zipcode()%>"> 
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
					<input type="text" name="mem_address1" size="60" value="<%=memberVO.getMem_address1()%>">
					<input type="text" name="mem_address2" size="60" value="<%=memberVO.getMem_address2()%>">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정완료" id="btnModify" />
						&nbsp;&nbsp;
						<input type="button" value="수정취소" id="btnModifyCancel" />
						<input type="button" value="회원삭제" id="btnDelete" />
					</td>
				</tr>
			</table>
		</form>
	</td>
</tr>
</table>
</body>
</html>