<%@ page import="java.util.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="/resources/css/home.css">
</head>
<body>
		<h1> 메인 </h1>
			<div class = "main-login">
				<c:if test = "${member == null }">	 
					<form role = "form" method ="post" autocomplete="off" action ="/member/login">
							<p>
									<label for = "member_id">아 이 디 : </label>
									<input type = "text" id = "member_id" name = "member_id" required >
							</p>
							<p>
									<label for = "member_pwd">비밀번호 : </label>
									<input type = "password" id = "member_pwd" name = "member_pwd" required >
							</p>
							<div class = "login-inspection">
								<c:if test="${msg == false }">
									<p>로그인에 실패하였습니다. 아이디 또는 패스워드를 다시 입력해주십시오.</p>
								</c:if>
							</div>	
							<div class = "button-group">
							<p><button type = "submit" id = "button-login">로그인</button></p>
							<p><a  href ="/member/register" id ="button-register">회원가입</a></p>
							</div>
					</form>
				</c:if>
			</div>
					<div class = "myinfo">
						<c:if test = "${member != null }">
								<td><p>환영합니다. <a href = "/member/memberModify">${member.member_nick}</a>님 </p></td>
								<a href = "member/logout">로그아웃</a>
								<a href="/board/listSearch">글 목록</a>
						</c:if>
					</div>
</body>
</html>
