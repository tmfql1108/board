<%@ page import="java.util.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="/resources/css/register.css">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>

</head>
<body>
	<div id="nav" style ="display:none">
	 <%@ include file="../include/navigator.jsp" %>
	</div>
	<div class = "member-register">
		 <h1>회원가입</h1>
		<form  class="member-register- form" role="form" method="post"  autocomplete="off" name = form onsubmit ="return checkAll()">
			 <div class="member-register-wrap">
			 	<table class="member-register-table">
						<tbody class = "member-register-table-tbody" >
								<tr>
									 <th> <label for="member_id">아 이 디 : </label></th>
									  <td><input type="text" id="member_id" name="member_id" placeholder="영문 대소문자와 숫자 4~12자리"  required/></td>
									  <td><button type = "button" class = "idCheck"> 아이디 확인 </button></td>
								      <td> <p class = "idResult"><span class ="msg"></span> </p><td>
								</tr>
								<tr>
									  <th><label for="member_pwd">패스워드 : </label></th>
									  <td><input type="password" id="member_pwd" name="member_pwd" placeholder="영문 대소문자와 숫자 4~12자리"  required/></td>
								 </tr>
								 <tr>
									  <th><label for="member_nick">닉 네 임 : </label></th>
									 <td> <input type="text" id="member_nick" name="member_nick" placeholder="영문 대소문자와 숫자 4~12자리"  required/></td>
									  <td> <button type = "button" class = "nickCheck"> 닉네임 확인 </button></td>
										<td><p class = "nickResult"><span class ="msg"></span> </p></td>
							 	 </tr>
							 	 <tr>
								 	 <th> <label for="member_email">이메일 : </label></th>
									  <td><input type="text" id="member_email" name="member_email" placeholder= "ex@eeee.ex" required /></td>
								</tr>
							</tbody>
					</table>
				</div>
			<div class = "register-button-wrap">
				 <p>
					  	 <button type="submit" id="submit" class ="register-ok" >가입</button>  
					  	<a id ="home"href="/">홈으로</a>
				 </p>
			 </div>
		</form>
	</div>
<script>
		function checkAll() {
				if(!checkMemberId(form.member_id.value)){
					return false;
				} else if (!checkMemberPwd(form.member_pwd.value )) {
					return false;
				} else if (!checkMemberEmail(form.member_email.value)){
					return false;
				}else if (!checkMemberNick(form.member_nick.value)){
					return false;
				}  //end if-elseif
				return true;
			}  //end checkAll
			
			function checkExistData(value, dataName) {
				if(value = ""){
					alert(dataName + "입력해주세요");
					return false;
				}  //end if
				return true;
			}  //end checkExistData
			
			function checkMemberId(member_id) {
				if(!checkExistData (member_id, "아이디를"))  return false;
				
				var idRegExp =/^[a-zA-Z0-9]{4,12}$/; //아이디 유효성 검사
				if(!idRegExp.test(member_id)) {
					 alert("아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
			            form.member_id.value = "";
			            form.member_id.focus();
			            return false;
				}  //end if
				return true;
			}  //end checkMemberId
			
			function checkMemberPwd(member_pwd){
				if(!checkExistData(member_pwd , "패스워드를")) return false; 
				
				var pwdRegExp = /^[a-zA-Z0-9]{4,12}$/;
				 if (!pwdRegExp.test(member_pwd)) {
			            alert("비밀번호는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
			            form.member_pwd.value = "";
			            form.member_pwd.focus();
			            return false;
			        }  //end if

			        return true; //확인이 완료되었을 때
			}  //end checkMemberPwd
			
			function checkMemberEmail(member_email) {
				if( !checkExistData(member_email, "이메일을 ")) return false;
				
				var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
				if (!emailRegExp.test(member_email)) {
		            alert("이메일 형식이 올바르지 않습니다!");
		            form.member_email.value = "";
		            form.member_email.focus();
		            return false;
		        }
		        return true; //확인이 완료되었을 때
			}
			
			   function checkMemberNick(member_nick) {
			        if (!checkExistData(member_nick, "닉네임을"))
			            return false;
			 
			        var nickRegExp = /^[a-zA-Z0-9]{4,12}$/;
			        if (!nickRegExp.test(member_nick)) {
			            alert("닉네임은 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
			            form.member_nick.value = "";
			            form.member_nick.focus();
			            return false;
			        }
			        return true; //확인이 완료되었을 때
			    }  //end checkMemberNick 

			$(".idCheck").click( function() {
				var query = {member_id : $("#member_id").val()};
				
				console.log("idCheck invoked");
				console.log("member_id : " + $("#member_id").val() );
				$.ajax ({
					url : "/member/idCheck",
					type : "post",
					data : query,
					success : function(data) {
						console.log("data : " + data);
						if(data == 1) {
							console.log("+++++아이디 사용 불가");
							$(".idResult .msg").text("사용 불가");
							$(".idResult .msg").attr("style","color:#f00");
						} else {
							console.log("+++++아이디 사용 가능");
							$(".idResult .msg").text("사용 가능");
							$(".idResult .msg").attr("style","color:#00f");
						}  //end if-else
					}  //end success
				});  //end ajax
				
			}); //end idCheck click
			
			$(".nickCheck").click(function() {
				var query = {member_nick : $("#member_nick").val()};
				
				console.log("nickCheck invoked");
				console.log("member_nick : " + $("#member_nick").val() );
				
				$.ajax ({
					url : "/member/nickCheck",
					type : "post",
					data :  query,
					success : function(data) {
						console.log("data : " + data);
						if(data == 1) {
							console.log("+++++닉네임 사용 불가");
							$(".nickResult .msg").text("사용 불가");
							$(".nickResult .msg").attr("style","color:#f00");
						} else {
							console.log("+++++닉네임 사용 가능");
							$(".nickResult .msg").text("사용 가능");
							$(".nickResult .msg").attr("style","color:#00f");
						}  //end if-else
					}  //end success
				});  //end ajax
			}); //end nickResult click
		</script>

</body>
</html>