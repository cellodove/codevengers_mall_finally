<%@page import="java.util.Enumeration"%>
<%@page import="ven.shop.model.MallOrderVO"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="wishlistDAO" class="ven.shop.dao.WishListDAO" scope="session"/>
<jsp:useBean id="itemDAO" class="ven.shop.dao.ItemDAO"/>
<jsp:useBean id="orderDAO" class="ven.shop.dao.OrderDAO"/>

<%
	Hashtable<String,MallOrderVO> hCart = wishlistDAO.getCartList();
	Enumeration enu = hCart.keys();
	if(hCart.size() == 0 ){
%>
	<script>
		alert("주문 내역이 없습니다");
		location.href = "member_orderlist.jsp";
	</script>
<%
	} else {
		while(enu.hasMoreElements()){
			MallOrderVO orderVO = (MallOrderVO)hCart.get(enu.nextElement());
			orderDAO.insertOrder(orderVO);  //주문추가
			itemDAO.reduceProduct(orderVO);  //재고감소
			wishlistDAO.deleteCart(orderVO);  //장바구니에서 삭제

		}
%>
	<script>
		alert("주문처리가 완료되었습니다 \n 감사합니다");
		location.href = "member_orderlist.jsp";
	</script>
<%
	}
%>