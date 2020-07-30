<%@page import="ven.shop.model.MallItemVO"%>
<%@page import="ven.shop.model.MallOrderVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="orderDAO" class="ven.shop.dao.OrderDAO"/>
<jsp:useBean id="itemDAO" class="ven.shop.dao.ItemDAO"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리</title>
<script src="../js/script.js?ver=0.1"></script>
</head>
<body>
	<%@ include file= "admin_top.jsp" %>
	<table style="width: 80%; text-align: center">
		<tr>
			<th>주문번호</th><th>주문자</th><th>상품명</th><th>주문수</th><th>주문일</th><th>주문상태</th><th>보기</th>
		</tr>
	<% 
		ArrayList<MallOrderVO> list = orderDAO.getOrderAll();
		if(list.size()<=0){		
	%>
		<tr><td colspan="7">주문 목록이 없습니다.</td></tr>
	<% 	} else {
		for(MallOrderVO orderVO : list){
			MallItemVO itemVO = itemDAO.getProduct(orderVO.getNo());
	%>
		<tr>
			<td><%=orderVO.getNo() %></td>
			<td><%=orderVO.getMem_id() %></td>
			
			<td><%=orderVO.getQuantity() %></td>
			<td><%=orderVO.getSdate() %></td>
			<td>
			<%	
				switch(Integer.parseInt(orderVO.getState())){
					case 1: out.println("접수"); break;
					case 2: out.println("입금확인"); break;
					case 3: out.println("배송준비"); break;
					case 4: out.println("배송중"); break;
					case 5: out.println("처리완료"); break;
					default: out.println("접수중"); break;
				}
			%>
			</td>
			<td><a href="javascript:orderDetail('<%=orderVO.getNo()%>')">상세보기</a></td>
		</tr>
	<%	
			} 
		} 
	%>
	</table>
		<%@ include file= "admin_bottom.jsp" %>
		<form action="order_detail.jsp" name="detailFrm" method="post">
			<input type="hidden" name="no" />
		</form>
</body>
</html>