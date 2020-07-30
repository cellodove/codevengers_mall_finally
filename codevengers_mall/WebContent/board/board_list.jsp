<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA</title>
<link rel="stylesheet" type="text/css" href="./css/QnA_Board.css">
</head>
<body>

	<%@ include file="../member/main_top.jsp"%>

	<div id="contentsArea">
		<section id="titlename" class="qnaBoard">
			<h1>질문 게시판</h1>
			<div id="infoArea">
				<section class="search">
					<form action="./BoardSearchList.bf" name="search" method="post">
						<fieldset>
							<legend>검색</legend>
							<label for="keyword"></label> <select name="keyfield"
								class="b_search">
								<option value="all" selected="selected">전체검색</option>
								<option value="qbrd_title"
									<c:if test="${'qbrd_title'==keyfield}">selected="selected"</c:if>>
									제목</option>
								<option value="name"
									<c:if test="${'mem_id'==keyfield}">selected="selected"</c:if>>
									글쓴이</option>
								<option value="qbrd_content"
									<c:if test="${'qbrd_content'==keyfield}">selected="selected"</c:if>>
									내용</option>
							</select> <input type="search" id="keyword" name="keyword"
								required="required" placeholder="검색어 입력">
							<button type="submit">검색</button>
						</fieldset>
					</form>
				</section>
			</div>
			<p class="allPost">
				전체 글: &nbsp; <strong><c:out value="${listcount}" /></strong>개
			</p>
			<table class="boardTable">
				<caption>게시판 리스트</caption>
				<c:choose>
					<c:when test="${listcount>0}">
						<thead>
							<tr>
								<th scope="col" class="bbsNumber">번호</th>
								<th scope="col" class="bbsTitle">제목</th>
								<th scope="col" class="bbsAuthor">글쓴이</th>
								<th scope="col" class="bbsDate">등록일</th>

							</tr>
						</thead>
						<!-- 해당페이지 저장된 글 호출 -->
						<c:forEach var="qna_board" items="${boardList}">
							<tbody>
								<tr>
									<td><c:out value="${qna_board.qbrd_num}" /></td>
									<td><c:if test="${!empty qna_board.qbrd_answer}">
											<c:forEach var="j" begin="0" end="${qna_board.qbrd_answer*2}"
												step="1">&nbsp;
</c:forEach>
										</c:if> <a
										href="./BoardDetail.bf?qbrd_num=<c:out value="${qna_board.qbrd_num}"/>">
											<c:out value="${qna_board.qbrd_title}" />

									</a></td>
									<td><c:out value="${qna_board.mem_id}" />
									<td>
									<td><c:out value="${qna_board.qbrd_date}" /></td>

								</tr>
							</tbody>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
			<div align="center">
				<table id="boardTableNe" class="boardTableNe">
					<tbody>
						<c:if test="${searchlistcount==0}">
							<tr>
								<td colspan="4"></td>
								<td>등록된 글이 없습니다.</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="5"><c:choose>
									<c:when test="${page<=1}">[이전]&nbsp;</c:when>
									<c:otherwise>
										<a href="./BoardList.bf?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp;
</c:otherwise>
								</c:choose> <c:forEach var="start" begin="${startpage}" end="${endpage}"
									step="1">
									<c:choose>
										<c:when test="${start eq page}">[<c:out
												value="${start}" />]
</c:when>
										<c:otherwise>
											<a href="./BoardList.bf?page=<c:out value="${start}"/>">[<c:out
													value="${start}" />]
											</a>&nbsp;
</c:otherwise>
									</c:choose>
								</c:forEach> <c:choose>
									<c:when test="${page>=maxPage}">[다음]</c:when>
									<c:otherwise>
										<a href="./BoardList.bf?page=<c:out value="${page+1}"/>">[다음]</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</tbody>
				</table>
				<div class="btnJoinAreb">
					<button type="button" value="button"
						onclick="location.href='./BoardWrite.bf'" class="btnOk">
						글쓰기</button>
				</div>
			</div>
		</section>
	</div>
</body>
</html>