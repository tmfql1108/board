<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link rel="stylesheet" href="/resources/css/modify.css">
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
</head>
<body>
<div id="nav">
 <%@ include file="../include/navigator.jsp" %>
</div>
<div class = "board-modify-total">
	 <h1>글 수정</h1>
	 <div class = "board-modify">
		<form class="board-modify-form" method="post">
				
		<div class="board-modify-wrap">
				<table class="board-modify-table">
						<tbody class = "board-modify-table-tbody" >
							<tr>
								<th><label>  제목</label></th>
								<td><input type="text" name="board_title"  value = "${ view.board_title}" required/></td>
							</tr>
							<tr>
								<th><label>  작성자</label></th>
								<td><input type="text" name="member_nick" value = "${member.member_nick }" readonly="readonly" required /></td>
							</tr>
							<tr>
								<th><label>  내용</label></th>
								<td><textarea cols="50" rows="5" name="board_content"  class= "board_content" required>${ view.board_content}</textarea></td>
							</tr>
						</tbody>
					</table>
			</div>
			<div class = "board-modify-button-wrap">
				<button type="submit">완료</button>
				<button type="reset">취소</button>
			</div>
		
		</form>
	</div>

</div>

</body>
</html>