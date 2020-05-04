<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="/resources/css/listSearch.css">
<!-- 제이쿼리 -->
	<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
</head>

<body>
	<div id="nav">
		 <%@ include file="../include/navigator.jsp" %>
	</div>
	
	  <div class="board-list">
	      <div class="board-title-main">
		      <h3 class="board-title">게시글 목록</h3>
		    </div>
			<table class = "board-table">
		      <colgroup>
		        <col width="15%" />
		        <col width="50%" />
		        <col width="15%" />
		         <col width="10%" />
		      </colgroup>

				<thead class = "thead-board">
					<div class = "thead-board-tr" >
								<tr class = "tr">
									<th>게시글 번호</td>
									<th>제목</td>
									<th>작성자</td>
									<th>조회수</td>
								</tr>
						</div>
				</thead>

			<tbody class = "tbody-board">
				<c:forEach items="${list}" var="list">
					<tr class = "tr">
						<td class = "tdBoardNo">${list.board_no }</td>
						<td class = "tdBoardTitle">	<a id = "BaardTitle-a" href = "/board/view?board_no=${list.board_no}&page=${scri.page}&perPageNum=${scri.perPageNum}">${list.board_title }</a>
						<small><b>[ <c:out value="${list.replycnt} "/> ]</b></small></td>
						<td class = "tdBoardNick">${list.member_nick }</td>
						<td class = "tdBoardViewCnt">${list.board_viewcnt }</td>
					</tr>
			    </c:forEach>
			</tbody>
			</table>
			 <div class = "search row">
				    <select name="searchType" class="form-control" >
						   	<option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
						   	<option value = "all"<c:out value="${scri.searchType eq 'all' ? 'selected' : ''}"/>>전체</option>
							<option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
							<option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
							<option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
				    </select>
				 </div>    
				<div class="input-group">
					<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" class="form-control"/>
					<span class="input-group-btn">
						<button id="searchBtn" class="btn btn-default">검색</button>
					</span>
				</div>
			 
			<script>
			$(function(){
				$('#searchBtn').click(function() {
					self.location = "listSearch" + '${pageMaker.makeQuery(1)}'
							+ "&searchType=" + $("select option:selected").val()
							+ "&keyword=" + encodeURIComponent($('#keywordInput').val());
						});
			});			
			</script>
</div>	
		<div>
			<c:if test="${pageMaker.prev}">
			  		<a href="listSearch${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
			  </c:if> 
			  
			 <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
  					<a href="listSearch${pageMaker.makeQuery(idx)}">${idx}</a>
			  </c:forEach>
			    
			  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			   		<a href="listSearch${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
			  </c:if> 
		</div>	

</body>
</html>