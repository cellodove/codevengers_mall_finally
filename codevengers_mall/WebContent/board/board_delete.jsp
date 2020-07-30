<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<h1>게시판 글 삭제</h1>
			<p class="formSign">
				<strong class="require">필수</strong> 는 반드시 입력하여야 하는 항목입니다.
			</p>

			<form id="joinForm" name="deleteForm" action="./BoardDeleteService.bf?qbrd_num=${param.qbrd_num}" method="post">
				<fieldset>
					<legend>게시판 글 삭제</legend>
					<p>
						<label for="qbrd_passwd"> 글 입력 비밀번호 <strong class="require">필수</strong>
						</label> <input type="password" id="qbrd_passwd" name="qbrd_passwd" required
							placeholder="비밀번호
입력" size="12" />
					</p>
					<div class="btnJoinArea">
						<button type="submit" class="btnOk">삭제</button>
						<button type="reset" class="btnCancel">취소</button>
						<button type="button" value="button"
							onclick="location.href='./BoardList.bf'" class="btnOk">
							목록</button>
					</div>
				</fieldset>
			</form>
		</section>
	</div>
</body>
</html>