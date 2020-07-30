<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="orderDAO" class="ven.shop.dao.OrderDAO"/>
<jsp:useBean id="orderVO" class="ven.shop.model.MallOrderVO"/>
<jsp:setProperty property="*" name="order"/>
<%
	String flag = request.getParameter("flag");
	String no = request.getParameter("no");
	String state = request.getParameter("state");
	
	boolean result = false;
	
	if(flag.equals("update")){
		result = orderDAO.updateOrder(Integer.parseInt(no), state);
	} else if(flag.equals("delete")) { 
		result = orderDAO.deleteOrder(orderVO);
	}
	
	if(result){
%>
	<script>
		alert("정상적으로 처리되었습니다.");
		location.href = "ordermanager.jsp";
	</script>
<%	}else{	%>

	<script>
		alert("오류발생! \n 관리자에게 문의하세요.");
		location.href = "ordermanager.jsp";
	</script>
<%	}	%>