<%@ page import="java.util.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/navigator.css">
</head>
<body>
	<div  class = "navigator">
		<table class = "navigator-table">
			<th>
					<td><a href = "/board/listSearch">글 목록  | </a></td>
					<td><a href = "/board/write">새 글 작성  | </a></td>
					
					<c:if test = "${member != null }">
					<td><p><a href = "/member/memberModify" id ="member-nick">${member.member_nick}</a>님   |</p></td>
					<td><a href = "/member/logout">로그아웃  |</a></td>
					<td><a href = "/">메인</a></td>
					</c:if>
		</table>
	</div>	
</body>
</html>