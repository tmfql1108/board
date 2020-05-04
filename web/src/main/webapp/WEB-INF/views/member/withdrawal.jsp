<%@ page import="java.util.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<link rel="stylesheet" href="/resources/css/withdrawal.css">
</head>
<body>

	<form role="form" method="post" autocomplete="off">
		<h1>회원 탈퇴</h1>
		<div>
			 <p>
				  <label for="member_id">아 이 디 : </label>
				  <input type="text" id="member_id" name="member_id"  value = "${member.member_id }"/>
			 </p>  
			 <p>
				  <label for="member_pwd">패스워드 : </label>
				  <input type="password" id="member_pwd" name="member_pwd" required />
			 </p>
		 </div>
		<div class ="member-withdrawal-bottom-group">
			  	 <button type="submit" id="submit" >탈퇴</button> 
		 </div>
	</form>
	
	<c:if test="${msg ==false }">
		<p>
			입력한 비밀번호가 잘 못 되었습니다. 
		</p>
	</c:if>


</body>
</html>