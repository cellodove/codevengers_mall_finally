<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="itemDAO" class="ven.shop.dao.ItemDAO"/>

<%
	itemDAO.deletePreviewImg(request);
	String imageName = itemDAO.insertPreviewImg(request);
	out.println(imageName);
%>