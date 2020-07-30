<%@page import="ven.shop.model.MallItemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="itemDAO" class="ven.shop.dao.ItemDAO"/>
<%
	String no = request.getParameter("no");
	MallItemVO itemVO = itemDAO.getProduct(Integer.parseInt(no)); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품보기</title>
<link href="../css/style.css" rel="style/sheet" type="text/css">
<script type="text/javascript" src="../js/script.js?ver=0.3"></script>
</head>
<body>
	<%@ include file = "admin_top.jsp" %>
	<table style="width: 80%">
		<tr>
			<td style="width: 20%">
				<img src="../images/product/<%=itemVO.getImage() %>" style="width:100%">
			</td>
			<td style="width: 50%; vertical-align: top;">
				<table style="width: 100%">
					<tr>
						<td>번호 : </td>
						<td><%=itemVO.getNo() %></td>
					</tr>
					<tr>
						<td>품명 : </td>
						<td><%=itemVO.getName() %></td>
					</tr>
					<tr>
						<td>가격 : </td>
						<td><%=itemVO.getPrice() %></td>
					</tr>
					<tr>
						<td>등록일 : </td>
						<td><%=itemVO.getSdate() %></td>
					</tr>
					<tr>
						<td>재고량 : </td>
						<td><%=itemVO.getStock() %></td>
					</tr>
				</table>
			</td>
			<td style="width: 30%; vertical-align: top;">
				<b>* 상품 설명 *</b><br/>
				<%=itemVO.getDetail() %>
			</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align: center;">
				<a href="javascript:productUpdate('<%=itemVO.getNo() %>')">수정하기</a>&nbsp;&nbsp;
				<a href="javascript:productDelete('<%=itemVO.getNo() %>')">삭제하기</a>
			</td>
		</tr>
	</table>
	<%@ include file = "admin_bottom.jsp" %>
	<form action="product_update.jsp" name="updateFrm" method="post">
		<input type="hidden" name="no">
	</form>
	<form action="product_process.jsp?flag=delete" name="delFrm" method="post">
		<input type="hidden" name="no">
	</form>
</body>
</html>