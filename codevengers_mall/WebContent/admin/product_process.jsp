<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="itemDAO" class="ven.shop.dao.ItemDAO"/>
<jsp:useBean id="vo" class="ven.shop.model.MallItemVO"/>
<%
	String flag = request.getParameter("flag");

	boolean result = false;

	if(flag.equals("insert")){
		result = itemDAO.insertProduct(request);
	} else if (flag.equals("modify")) {
		result = itemDAO.updateProduct(request);
	} else if (flag.equals("delete")) {
		result = itemDAO.deleteProduct(request.getParameter("no"));
	} else {
		response.sendRedirect("productmanager.jsp");
	}
	if(result){
%>
		<script>
			alert("정상 처리되었습니다.");
			location.href = "productmanager.jsp";
		</script>

	<% } else { %>
		<script>
			alert("오류 발생!\n관리자에게 문의 하시오");
			location.href = "productmanager.jsp";
		</script>
	<% } %>