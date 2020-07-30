<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./css/QnA_Board.css">
</head>
<body>
	<div id="contentsArea">
		<section id="titlename">
			<h1>누른거 내용</h1>
			<p class="formSign">누른거 글 내용입니다.</p>
			<div id="joinForm">
				<input type="hidden" name="qbrd_num"
					value="<c:out value='${qnABoardVO.qbrd_num}'/>">
				<fieldset>
					<legend> 게시판 내용</legend>
					<p>
						<label for="mem_id"> 이름  :</label> <br />
						<c:out value="${qnABoardVO.mem_id }" />
					</p>
					<p>
						<label for="qbrd_title"> 제목 : </label> <br />
						<c:out value="${qnABoardVO.qbrd_title }" />
					</p>
					<p>
						<label for="qbrd_content"> 내용 : </label> <br />
						<c:out value="${qnABoardVO.qbrd_content}" />
					</p>
					 
					<c:choose>
						<c:when test="${!empty qnABoardVO.qbrd_file}">
							<p>
								<label for="qbrd_file">파일 첨부</label><br />
								<c:out value="${qnABoardVO.qbrd_file}" />
								&nbsp;&nbsp;&nbsp; <a
									href="./boardUpload/<c:out value='${qnABoardVO.qbrd_file}'/>">
									파일 다운 </a>
								
								<input type="hidden" name="old_file"
									value="<c:out value='${qnABoardVO.qbrd_file}'/>" />
								&nbsp;&nbsp;&nbsp;
							</p>
						</c:when>
						<c:otherwise>
							<p>
								<label for="old_file">파일 첨부</label> <br /> 첨부 파일이 없습니다.
							</p>
						</c:otherwise>
					</c:choose>
					<div class="btnJoinArea">
						<a href="./BoardReplyMove.bf?qbrd_num=<c:out value="${qnABoardVO.qbrd_num}"/>">
							<button type="button" class="btnOk">답변</button>
						</a> <a href="./BoardModify.bf?qbrd_num=<c:out value="${qnABoardVO.qbrd_num}"/>">
							<button type="button" class="btnOk">수정</button>
						</a> <a href="./BoardDelete.bf?qbrd_num=<c:out value="${qnABoardVO.qbrd_num}"/>">
							<button type="button" class="btnOk">삭제</button>
						</a>
						<button type="button" value="button"
							onclick="location.href='./BoardList.bf'" class="btnOk">
							목록</button>
					</div>
				</fieldset>
			</div>
		</section>
	</div>
</body>
</html>