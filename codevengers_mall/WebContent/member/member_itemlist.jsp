<%@page import="ven.shop.model.MallItemVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="itemDAO" class="ven.shop.dao.ItemDAO"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
	<%@ include file="member_itemtop.jsp" %>
	<table border="1">
	<%	
		ArrayList<MallItemVO> list = itemDAO.getProductAll(); 
		for(MallItemVO itemVO : list){
	%>

				<td>
					<a href="javascript:productDetail('<%=itemVO.getNo()%>')">
					<img src="../images/product/<%=itemVO.getImage() %>" width="442" height="442">
					</a>
				</td>

	<%	} %>
	</table>
	<%@ include file="member_itembottom.jsp" %>
	
	<!-- 제품번호를 상세보기로 넘기기 위한 form -->
	<form action="member_itemdetail.jsp" name="detailFrm" method="post">
		<input type="hidden" name="no">
	</form>
</body>
</html>