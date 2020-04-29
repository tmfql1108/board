<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="/resources/css/write.css">
</head>
	<body>
		<div id="nav">
		 <%@ include file="../include/navigator.jsp" %>
		</div>
	<div class = "board-wrtie-total">
		 <h1>새 글 작성</h1>
		 <div class = "board-write">
			<section id = "container">
				<c:if test="${msg == null }">
							<form  class="board-write-form"  method="post">
									<div class="board-write-wrap">
						        		<table class="board-register-table">
												<tbody class = "board-register-table-tbody" >
															<tr>
																<th><label>제목</label></th>
																<td><input type="text" name="board_title" required /></td>
															</tr>
															<tr>
																<th><label>작성자</label></th>
																<td><input type="text" name="member_nick" value = "${member.member_nick }" readonly="readonly" /></td>
															</tr>
															<tr>
																<th><label>내용</label></th>
																<td><textarea cols="50" rows="5" name="board_content" class ="board_content" required></textarea></td>
														</tr>
												</tbody>
										</table>
								</div>
								
								<div class = "board-write-button-wrap">
										<button type="submit">등록</button>
										 <button type="reset">취소</button> 
								</div>
						</form>
				</c:if>
				<c:if test="${msg == false }">
					<p> 로그인후 이용할 수 있는 서비스 입니다. </p>
					<p><a href = "/">홈으로</a></p>
				</c:if>
			</section>
			</div>
		</div>
	</body>
</html>