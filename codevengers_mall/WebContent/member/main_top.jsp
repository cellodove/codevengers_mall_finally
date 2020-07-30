<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="./css/bootstrap.min.css">
<%
	String mem_id = (String)session.getAttribute("mem_id");
	String login_check = "";
	String member_register_check = "";
	
	if(mem_id == null){  //로그인이 아니라면
		login_check = "<a href='http://localhost/codevengers_mall/MemberLogin.do'>로그인</a>";
		member_register_check = "<a href='http://localhost/codevengers_mall/MemberWrite.do'>회원가입</a>";
		
	} else {  //로그인이라면
		login_check = "<a href='./member/member_logout.jsp'>로그아웃</a>";
		
	}
%>
 <nav class="navbar navbar-default">
  <div class="navbar-header">

   <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expaned="false">
     <span class="icon-bar"></span>
     <span class="icon-bar"></span>
     <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="./index.jsp">Codevengers Shoes</a>
  </div>
  <div class="collapse navbar-collapse" id="#bs-example-navbar-collapse-1">

   <ul class="nav navbar-nav navbar-right">

    <li class="dropdown">

     <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
     접속하기
     <span class="caret"></span>
     </a>
     <ul class="dropdown-menu">
     <li><%=login_check %></li>
     <li><%=member_register_check %></li>
     <li><form action="./MemberInfo.do" method="post">
			<input type="hidden" name="mem_id" value="<%=mem_id%>">
			<input type="submit" value="회원정보"></li>
     <li><a href="./member/member_itemlist.jsp">상품목록</a></li>
     <li><a href="./member/member_wishlist.jsp">장바구니</a></li>
     <li><a href="./member/member_orderlist.jsp">구매목록</a></li>
     <li><a href="BoardList.bf">게시판</a></li>
     </ul>
    </li>
   </ul>
  </div> 
 </nav>
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> 
 <script src="./js/bootstrap.js"></script>