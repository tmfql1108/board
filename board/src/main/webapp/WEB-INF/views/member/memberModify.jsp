<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" href="/resources/css/memberModify.css">
</head>
<body>
<div id="nav">
 <%@ include file="../include/navigator.jsp" %>
</div>

	<form role="form" method = "post" autocomplete="off">
		<h1>회원정보수정</h1>
		 <p>
			  <label for="member_id">회원 아이디 : </label>
			  <input type="text" id="member_id" name = "member_id" value="${member.member_id}" readonly="readonly" />
		 </p>  
		 
		  <p>
			  <label for="member_pwd">새로운 비밀번호 입력</label>
			  <input type="password" id="member_pwd" name="member_pwd" required/>
		 </p>  
		 <div>
			  <p class = "member-modify-bottom-group">
				  	<button type="submit">수정 완료</button>
				  	<button type="reset">작성 취소</button>
				  	<a href ="/member/withdrawal" id ="withdrawal-button" >회원 탈퇴</a>
				</p>
			 </div> 
	</form>
</body>
</html>