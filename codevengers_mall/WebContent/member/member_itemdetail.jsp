<%@page import="ven.shop.model.MallItemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="itemDAO" class="ven.shop.dao.ItemDAO"/>
<% request.setCharacterEncoding("utf-8"); %>
<% 
	String no = request.getParameter("no");
	MallItemVO itemVO = itemDAO.getProduct(Integer.parseInt(no));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
	<%@ include file = "member_itemtop.jsp" %>
	<form action="member_wishlistprocess.jsp">
		<table style="width: 80%">
			<tr>
				<td style="width: 20%">
					<img src="../images/product/<%=itemVO.getImage() %>" width="100%">
				</td>
				<td style="width: 50%; vertical-align: top;">
					<table style="width: 100%">
						<tr>
							<td>번호 : </td>
							<td>
								<%=itemVO.getNo() %>
								<input type="hidden" name="product_no" value="<%=itemVO.getNo() %>">
							</td>
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
						<tr>
							<td>주문 수 : </td>
							<td>
								<input type="text" name="quantity" value="1" size="5" style="text-align: center;">
							</td>
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
					<input type="submit" value="장바구니에 담기">
					<input type="button" value="이전으로 이동" onclick="history.back()">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file = "member_itembottom.jsp" %>
	
</body>
</html>