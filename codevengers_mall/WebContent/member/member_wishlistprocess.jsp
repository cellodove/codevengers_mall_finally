<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="wishlistDAO" class="ven.shop.dao.WishListDAO" scope="session"/>
<jsp:useBean id="orderVO" class="ven.shop.model.MallOrderVO"/>
<jsp:setProperty property="*" name="orderVO"/>

<%
	String flag = request.getParameter("flag"); //구매목록 보기, 수정, 삭제 구분 
	String mem_id = (String)session.getAttribute("mem_id"); //로그인 확인

	if(mem_id == null){ //로그인이 안되어있을때
		
		response.sendRedirect("../index.jsp"); //로그인 강요
		
	} else {
		//카트에 상품담기
		if(flag == null){
			orderVO.setMem_id("mem_id");
			wishlistDAO.addCart(orderVO);

%>
			<script>
				alert("장바구니에 담았습니다");
				location.href = "member_wishlist.jsp";
			</script>
<%
		//카트에 상품 수정
		} else if(flag.equals("update")){
			orderVO.setMem_id("mem_id");
			wishlistDAO.updateCart(orderVO);
%>
			<script>
				alert("장바구니에 내용을 수정했습니다");
				location.href = "member_wishlist.jsp";
			</script>
<%
		} else if(flag.equals("delete")){
			wishlistDAO.deleteCart(orderVO);
%>
			<script>
				alert("장바구니에 상품을 삭제했습니다.");
				location.href = "member_wishlist.jsp";
			</script>
<%
		}
	}
%>