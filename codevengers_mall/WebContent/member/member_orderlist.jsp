<%@page import="ven.shop.model.MallItemVO"%>
<%@page import="ven.shop.model.MallOrderVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="orderDAO" class="ven.shop.dao.OrderDAO"/>
<jsp:useBean id="itemDAO" class="ven.shop.dao.ItemDAO"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/script.js"></script>
</head>
<body>

	<%@ include file="member_itemtop.jsp" %>
	<table style="width: 80%">
		<tr>
			<th>주문번호</th><th>품명</th><th>주문수</th><th>주문일</th><th>주문상태</th>
		</tr>
	<%	
		mem_id = (String)session.getAttribute("mem_id");
		ArrayList<MallOrderVO> list = orderDAO.getOrder(mem_id);
		if(list.size()==0) {
	%>
		<tr>
			<td colspan="5"> 주문한 상품이 없습니다.</td>
		</tr>
	<%
		} else {
			for(MallOrderVO orderVO:list){
				MallItemVO itemVO = itemDAO.getProduct(Integer.parseInt(orderVO.getProduct_no()));
	%>
				<tr>
					<td><%=orderVO.getNo() %></td>
					<td><%=itemVO.getName() %></td>
					<td><%=orderVO.getQuantity() %></td>
					<td><%=orderVO.getSdate() %></td>
					<td>
					<%
						switch(Integer.parseInt(orderVO.getState())){
							case 1: out.println("접수");break;
							case 2: out.println("입금확인");break;
							case 3: out.println("배송준비");break;
							case 4: out.println("배송중");break;
							default : out.println("접수중");break;
						}
					%>
					</td>
				</tr>			
	<%
			}
		}
	%>
	</table>
	<%@ include file="member_itembottom.jsp" %>
</body>
</html>