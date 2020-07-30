<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="../css/bootstrap.min.css">
<%
	String admin_id = (String)session.getAttribute("adminOk");
	if(admin_id == null) {
%>
	<script>
		alert("관리자 로그아웃 되었습니다.");
		location.href = "../indextest.jsp";
	</script>
<%
		return;
	}
%>
<nav class="navbar navbar-default">
  <div class="navbar-header">

   <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expaned="false">
     <span class="icon-bar"></span>
     <span class="icon-bar"></span>
     <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="../index.jsp">Codevengers Shoes</a>
  </div>
  <div class="collapse navbar-collapse" id="#bs-example-navbar-collapse-1">

   <ul class="nav navbar-nav navbar-right">

    <li class="dropdown">

     <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
     접속하기
     <span class="caret"></span>
     </a>
     <ul class="dropdown-menu">
     <li><a href="admin_login_out.jsp">로그아웃</a></li>
     <li><a href="membermanager.jsp">회원관리</a></li>
     <li><a href="productmanager.jsp">상품관리</a></li>
     <li><a href="ordermanager.jsp">주문관리</a></li>
     </ul>
    </li>
   </ul>
  </div> 
 </nav>
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> 
 <script src="../js/bootstrap.js"></script>