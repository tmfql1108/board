<%@ page import="java.util.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 조회</title>
<link rel="stylesheet" href="/resources/css/view.css">
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
</head>
<body>
	<div id="nav">
	 <%@ include file="../include/navigator.jsp" %>
	</div>
	 <h1>게시글 상세</h1>

	 <div class = "board-detail">
			<form  class="board-detail-form"  method="post">
				<input type ="hidden" id="page" name = "page" value = "${ scri.page}" readonly="readonly">
				<input type ="hidden" id="perPageNum" name = "perPageNum" value = "${ scri.perPageNum}" readonly="readonly">
				<input type ="hidden" id="searchType" name = "searchType" value = "${ scri.searchType}" readonly="readonly">
				<input type ="hidden" id="keyword" name = "keyword" value = "${ scri.keyword}" readonly="readonly">
				<div class="board-detail-wrap">
					<table class="board-detail-table">
							<tbody class = "board-detail-table-tbody" >
									<tr>
										<th><label>제목</label></th>
										<td><input type="text" value = "${view.board_title }" disabled /></td>
									</tr>
									<tr>
										<th><label>작성자</label></th>
											<td><input type="text" value = "${view.member_nick }" disabled /></td>
									</tr>
									<tr>
										<th><label>작성일자</label></th>
											<td><input type="text" value ="<fmt:formatDate value="${view.board_reg}" pattern="yyy-.MM-dd  hh:mm" />" disabled/></td>
									</tr>
									<tr>
										<th><label>조회수</label></th>
										<td><input type="text" value = "${view.board_viewcnt}" disabled /></td>
										
									</tr>
									<tr>
										<th><label>내용</label></th>
											<td><textarea cols="50" rows="5" name="board_content" class ="detail-content" disabled>${view.board_content }</textarea></td>
									</tr>
							</tbody>
						</table>
				</div>			
				<div class ="mybutton-wrap">
							<c:if test="${member.member_nick eq view.member_nick }">
							<a href = "/board/modify?board_no=${view.board_no}">수정</a>
							<a href = "/board/delete?board_no=${view.board_no}">삭제</a>
						</c:if>
				</div>

		</form>
		
	<!-- 댓글 입력 부분 -->
        <div class="review-wrap">
          <div class="review-top">
          </br>
           </br>
            </br>
            <h3 class="review-title">질문을 올려주세요</h3>
        	<p>${member.member_nick}님</p>

            <div class="score-textarea">
              <textarea class="text-comment" id="text-comment" name="comment" title="질문을 올려주세요" cols="65" rows="5"  placeholder="이곳에 댓글을 입력하세요"></textarea>
            </div>
            <button class="review-btn">입력</button>
          </div>
        </div>
        <!-- 댓글 출력부분 -->
        <div class="review-entry" id="review-entry">
          <ul class='review-content' id='review-content'>
          </ul>
       </div>	
      
</div>

<script type="text/javascript">

	//페이지가 뜰때마다 리스트 뿌려주기
	$(document).ready(function () {
		console.log("+++++ready invoked : ");
		commentLen = 0;
		console.log("+++++commentLen : : " + commentLen);
	    getReply(commentLen);
	}); // (document).ready
	
	
		var board_no = '${view.board_no}';   //게시글 번호
		var member_nick = '${member.member_nick}';  //현재 로그인한 작성자
  		console.log("+++++board_no : "+board_no);
  		console.log("+++++member_nick : "+member_nick);
    
    //댓글 리스트
    function getReply(commentLen) {
    console.log("+++++getReply invoked : ");
      $.ajax({
        url: "/comment_list", //데이터를 보낼 URL
        type: "GET", //데이터 전송방식
        data: {
          "board_no": board_no,
          "member_nick": member_nick
        },	  //전송 데이터
        success: function (data) { //성공
	          var str = "";
	          console.log("+++++data.length : "+data.length);
	          if (data.length - commentLen > 0) {
	        	  //데이터가 있는지 확인
			            $.each(data, function (key, value) {
			            	console.log("+++++data : "+data);
			            	 console.log("+++++key  : "+key);
			     			console.log("+++++value  : "+value);
					              if (value.member_nick == member_nick) {
							            	  // 내글일때 수정삭제
							                str += "<li class = 'lists-item' id='lists-item'>";
							                str += "<div class='review-box' id='review-box'>";
							                str += "<p class='review-text-result' id = 'review-text-result'>" + value.comment  + "</p>";
							                str += "<div class='review-bottom'>";
							                str += "<span class='review-date'>" + value.comment_reg + "</span>";
							                str += "<div class='review-btn-area'>";
							                str += "<button class='review-btn-modify'>수정</button>";
							                str += "<button class='review-btn-delete'>삭제 </button>";
							                str += "</div>";
							                str += "</div>";
							                str += "</div>";
							                str += "<div class='review-name'>";
							                str += "<span>" +value. member_nick+ "</span>";
							                str += "</div>";
							                str += "<div class='reply-no-box'>";
							                str += "<input type ='text' id='review-no' name = 'review-no' value = "+value.comment_no+">";
							               /*  str += "<span class='review-no' id ='review-no' > 등록번호" + value.comment_no + "</span>"; */
							                str += "</div>";
							                str += "</li>";
					              } else {
							            	  //그 외 열람만
							                str += "<li class = 'lists-item' id='lists-item'>";
							                str += "<div class='review-box' id='review-box'>";
							                str += "<p class='review-text-result' id = 'review-text-result'>" + value.comment + "</p>";
							                str += "<div class='review-bottom'>";
							                str += "<span class='review-date' id ='review-date'>" + value.comment_reg  + "</span>";
							                str += "</div>";
							                str += "</div>";
							                str += "<div class='review-name'>";
							                str += "<span>" + value.member_nick + "</span>";
							                str += "</div>";
							                str += "</li>";
					              } //if-else 
			             		 commentLen++;
			                	
			            }); //each   
	          } else {
	        	  //게시글이 하나도 없을때
	            str += "<li>";
	            str += "<div class='review-box' id='review-box'>";
	            str += "<p class='review-text-result'>등록된 댓글이 없습니다. 댓글을 등록해보세요</p>";
	            str += "<span class='review-date'></span>";
	            str += "</div>";
	            str += "</li>";
	          } //if-else
	        	  
	          $(".review-content").html(str);
	        }, //success
	        error: function () {
	          console.log("error");
	        } //error
      }); //ajax
    } //getReply
    </script>

  <script type="text/javascript">
    var board_no = '${view.board_no}';
	var member_nick = '${member.member_nick}';
	console.log("+++++board_no : "+board_no);
	console.log("+++++member_nick : "+member_nick);

      //댓글 삽입   
      $(".review-btn").on("click", function f_reply_insert() {

        var comment = $("#text-comment").val();
        console.log("+++++comment comment : " +$("#text-comment").val());
        
        if ($("#text-comment").val().trim() === "") {
	          alert("댓글을 입력하세요");
	          $("#text-comment").val("").focus();
        } else {
	          $.ajax({
		            url: "/comment_insert",
		            type: "POST",
		            data: {
		              "board_no": board_no,
		              "member_nick": member_nick,
		              "comment": comment
		            },    
		            success: function () {
		            	
		              console.log("reply_insert success");
		              $("#text-comment").val("");
		              getReply(commentLen);
		            }, //success
		            error: function () {
		            		   alert("댓글은 한번만 작성할수있습니다.");
		            		   $("#text-comment").val("");
		              console.log("error");
	            } //error
	          }) //ajax
        } //if-else
      }); //click


      //댓글 수정   
      $(document).on("click", ".review-btn-modify", function f_reply_Modify() {
        console.log("수정버튼 클릭");
        var board_no = '${view.board_no}';
    	var member_nick = '${member.member_nick}';
    	console.log("+++++board_no : "+board_no);
    	console.log("+++++member_nick : "+member_nick);

        var mod_comment = $(this).parents().siblings('.review-text-result').text();

        console.log(mod_comment);
	        str = "";
	        str +=  "<textarea class='text-comment-modify' id='text-comment-modify' name='modcomment'cols='70' rows='5'></textarea>";
	        str += "<div class='modify-btn-area'>";
	        str += "<button class='modify-ok-btn'>수정 </button><tr/>";
	        str += "<button class='modify-cancel-btn'>취소 </button>";
	        str += "</div>";

        $(this).parents().parent("#review-box").html(str);

        $("#text-comment-modify").val(mod_comment);
      }); //replyModify click 
    </script>


    <script type="text/javascript">
      //댓글 수정 저장
     var board_no = '${view.board_no}';
	var member_nick = '${member.member_nick}';
	console.log("+++++board_no : "+board_no);
	console.log("+++++member_nick : "+member_nick);

      $(document).on("click", ".modify-ok-btn", function f_replyModifyOK() {
        console.log(" 수정 버튼 클릭");

        var modcomment = $('#text-comment-modify').val();
        console.log("+++++modcomment : "+modcomment);
        $.ajax({
          url: "/comment_update",
          type: "POST",
          data: {
        	   "board_no": board_no,
               "member_nick": member_nick,
               "modcomment": modcomment
          },
          success: function () {
            getReply(commentLen);
          }, //success
          error: function () {
            console.log("error");
          } //error
        }); //ajax 
      });
      
      //댓글 수정 취소
      $(document).on("click", ".modify-cancel-btn", function f_replyModifyCancel() {
        console.log(" 수정 취소 버튼 클릭");
        getReply(commentLen);
      });
      
    </script>
    
    <script type="text/javascript" >
    var board_no = '${view.board_no}';
    var member_nick = '${member.member_nick}';
    
   var comment_no = $('.review-no').val();
      //댓글 삭제   
      $(document).on("click", ".review-btn-delete", function () {
        console.log("삭제 버튼 클릭");

        var delectConfirm = confirm("댓글을 삭제하시겠습니까?");

        console.log("+++++board_no : "+board_no);
      	console.log("+++++member_nick : "+member_nick);
      	console.log("+++++comment_no : "+comment_no);
        if (delectConfirm) {
          $.ajax({
            url: "/comment_delete",
            type: "POST",
            data: {
              "board_no": board_no,
              "member_nick": member_nick
            },
            success: function () {
              getReply(commentLen);
            }, //success
            error: function () {
              console.log("error");
            } //error
          }) // ajax
        } //if 
      }); //replyDelete click
    </script>
 
</body>
</html>