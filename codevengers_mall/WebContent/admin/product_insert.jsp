<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/script.js?ver=0.3"></script>
<body>
	<%@ include file = "admin_top.jsp" %>

	<!-- 파일을 전송하기 위한 enctype -->
	<form name="productFrm" action="product_process.jsp?flag=insert" enctype="multipart/form-data" method="post">
		<table style="width: 80%">
			<tr>
				<th colspan="2">** 상품 등록 **</th>
			</tr>
			<tr>
				<td style="width:10%">번호</td>
				<td><input type="text" name="no"></td>
			</tr>
			<tr>
				<td style="width:20%">상품명</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>설 명</td>
				<td><textarea name="detail" rows="3" cols="30"></textarea></td>
			</tr>
			<tr>
				<td>재고량</td>
				<td><input type="text" name="stock"></td>
			</tr>
			<tr>
				<td>이미지</td>
				<td>
					<img name="preview" src="../images/product/noimage.jpg" style="width:100%"><br/>
					<input type="file" name="image" size="30" onchange="filePreview()">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<br/>
					<input type="submit" value="상품 등록">
					<input type="reset" value="새로 입력" onclick="resetInsertData()">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file = "admin_bottom.jsp" %>
</body>
</html>