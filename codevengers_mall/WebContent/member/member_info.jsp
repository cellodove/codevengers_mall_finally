<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>codevengers 회원정보</title>
</head>
<body>
	
	<div id="contentsArea">
		<section id="titlename">
			<h1>회원정보</h1>
			<p class="formSign">회원정보</p>
			<div id="joinForm">

				<fieldset>
					<legend> 회원정보 내용</legend>
					
					<p>
						<label for="name"> 아이디 </label> <br/>
						<c:out value="${mem_id}"/>
						<input type="hidden" name="mem_id" value="${mem_id}">
						
					</p>
					
					<p>
						<label for="subject"> 비밀번호 </label> <br/>
						<c:out value="${mem_passwd}"/>
					</p>
					
					<p>
						<label for="name"> 이름 </label> <br/>
						<c:out value="${mem_name}"/>
						
					</p>
					
					<p>
						<label for="content"> 생일 </label> <br/>
						<c:out value="${mem_birth}"/>
					</p>
					
					<p>
						<label for="content"> 전화번호 </label> <br/>
						<c:out value="0${mem_tel1}"/>-<c:out value="${mem_tel2}"/>-<c:out value="${mem_tel3}"/>
					</p>
					
					<p>
						<label for="content"> 우편번호 </label> <br/>
						<c:out value="${mem_zipcode}"/>
					</p>
					
					<p>
						<label for="content"> 주소 </label> <br/>
						<c:out value="${mem_address1}"/>&nbsp;<c:out value="${mem_address2}"/>
					</p>
					
					<p>
						<label for="content"> 성별 </label> <br/>
						<c:out value="${mem_gender}"/>
					</p>
					
					<p>
						<label for="content"> 이메일 </label> <br/>
						<c:out value="${mem_email}"/>
					</p>
					
					<p>
						<label for="content"> 이메일 인증 </label> <br/>
						<c:out value="${mem_email_ck}"/>
					</p>
					
					<p>
						<label for="content"> 회원등급 </label> <br/>
						<c:out value="${mem_grade}"/>
					</p>
					
					<p>
						<label for="content"> 포인트 </label> <br/>
						<c:out value="${mem_point}"/>
					</p>
					
					<p>
						<label for="content"> 이메일수신여부 </label> <br/>
						<c:out value="${mem_receive_email}"/>
					</p>
					
					<p>
						<label for="content"> 문자수신여부 </label> <br/>
						<c:out value="${mem_receive_sms}"/>
					</p>
					
					<p>
						<label for="content"> 회원등록일 </label> <br/>
						<c:out value="${mem_register_datetime}"/>
					</p>
					
						
					<div class="btnJoinArea">
						
						<a href="./MemberModify.do?mem_id=<c:out value="${mem_id}"/>">
							<button type="button" class="btnOk">수정</button>
						</a> 
						<a href="./MemberDelete.do?mem_id=<c:out value="${mem_id}"/>">
							<button type="button" class="btnOk">탈퇴</button>
						</a>
						<button type="button" value="button"onclick="location.href='./MemberMain.do'" class="btnOk">메인</button>
					</div>
				</fieldset>
			</div>
		</section>
	</div>
</body>
</html>