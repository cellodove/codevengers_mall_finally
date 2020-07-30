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
<script src="../js/script.js?ver=0.1"></script>
</head>
<body>
	<%@ include file = "admin_top.jsp" %>
	<table style="width: 80%">
		<tr style="background-color: yellow">
			<th>상품명</th><th>가격</th><th>등록일</th><th>재고량</th><th>상세보기</th>
		</tr>
	<%
		ArrayList<MallItemVO> list = itemDAO.getProductAll();
		if(list.size() == 0){
	%>
		<tr><td colspan="6">등록된 상품이 없습니다.</td></tr>
	<%
		} else {
			for(MallItemVO vo:list){
	%>
				<tr>
					<td><%=vo.getName() %></td>
					<td><%=vo.getPrice() %></td>
					<td><%=vo.getSdate() %></td>
					<td><%=vo.getStock() %></td>
					<td><a href="javascript:productDetail_admin('<%=vo.getNo() %>')">상세보기</a></td>
				</tr>
	<%
			}
		}
	%>
		<tr>
			<td colspan="6"><a href="product_insert.jsp">[ 상품등록 ]</a></td>
		</tr>
	</table>
	<%@ include file = "admin_bottom.jsp" %>
	<form action="productdetail_admin.jsp" name="detailFrm" method="post">
		<input type="hidden" name="no">
	</form>
</body>
</html>