<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<% request.setCharacterEncoding("utf-8"); %>
<!doctype html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" >
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<!-- ajax -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="/?nowPage=${paging.nowPage}&cntPerPage="+sel;
	};
	$(document).ready(function() {
		$("#submitButton").click(function() {
			if($("#insertComment").val() == "")
				alert("내용을 입력하세요");
			else{
				var comment ={
					"comment" : $("#insertComment").val()
				}
				$('#insertComment').val(" ");
				$.ajax({
					type:"POST",
					url:"/home/ajax",
					dataType:"json",
					contentType:"application/json",
					data:JSON.stringify(comment),			
					success:function(response){
						//확인
						console.log(response); 
						$('#recommendTable > tbody').empty();      //tbody만 없애기
						$.each(response, function(index, item){
							var member = "${member.memberId}"
							var member2 = response[index].memberId;
							if(member === member2){
								$('#recommendTable > tbody').prepend( //append 뒤에 붙이기
									'<tr><th scope="row" id = "listMemberId">'+response[index].memberId+'</th>'
									+ '<td class="text-left" id = "listComment">'+response[index].comment+'</td>'
									+ '<td>'
									+ '<button type="button" class="btn btn-outline-danger" onclick="location.href=\'recommend/delete/'+response[index].recommendId+"\'\">삭제</button>"
									+ '</td></tr>')
								} else{
								$('#recommendTable > tbody').prepend( //append 뒤에 붙이기
									'<tr><th scope="row" id = "listMemberId">'+response[index].memberId
									+'</th>'
									+ '<td class="text-left" id = "listComment">'+response[index].comment+'</td>'
									+ '<td>'
									+ '</td></tr>')
								} 
						});
					},
					error:function(e){
						console.log(e);
					}
				});
			}
		});
	});
</script>
<link href="<c:url value='/resources/img/icon.png'/>" rel="shortcut icon" type="image/x-icon">
<title>BASAK</title>
</head>
<body>
<div class="card" style="background-image:url(<c:url value='/resources/img/main_bg.jpg'/>);
						background-repeat : no-repeat;
						background-size : cover;">
  	<%@ include file="menu.jsp" %>
    <br><br>
    <div class="container-fluid">
		<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-8">
		<img src="<c:url value='/resources/img/recommend.png'/>" width="400" class="rounded mx-auto d-block" alt="..."/>	
		<c:choose>
		<c:when test="${member != null}">
		<div class="container-sm">
			<form onSubmit="return false;">
				<div class="form-group">
					<div>
						<div style="float: left; width: 91%;">
							<input class="form-control" name="comment" id="insertComment" aria-describedby="recom"> 
						</div>
						<div style="float: left; width: 9%;">
							<button id="submitButton" class="btn btn-dark float-right" >저장</button>
						</div>	
					</div>
					<div>
						<small id="recom" class="form-text text-muted">여러분의 의견을 자유롭게 남겨주세요.</small>
					</div>
				</div>
			</form>
		</div>
		</c:when>
		<c:otherwise>
		<br><br>
		</c:otherwise>
		</c:choose>
		<br>
		<div class="container-sm">
			<table class="table text-center" id = "recommendTable">
				<thead class="thead-dark">
					<tr>
						<th style="width : 20%">아이디</th>
						<th style="width : 70%">내용</th>
						<th style="width : 10%"> </th>
					</tr>
				</thead>
				<tbody id = "recommend_tbody">
				<c:forEach var="recommend" items="${viewAll}">
					<tr>
						<th scope="row">${recommend.memberId}</th>
						<td class="text-left">${recommend.comment}</td>
						<td><c:if test="${recommend.memberId eq member.memberId}">
							<button type="button" class="btn btn-danger" onclick="location.href='recommend/delete/${recommend.recommendId}'">삭제</button>
						</c:if></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="d-flex justify-content-around ">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<c:if test="${paging.startPage != 1}">
							<li class="page-item "><a class="page-link" href="/?nowPage=${paging.startPage -1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						</c:if> 
						<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
							<c:choose>
								<c:when test="${p == paging.nowPage}">
									<li class="page-item"><a class="page-link">${p}</a></li>
								</c:when>
								<c:when test="${p != paging.nowPage}">
									<li class="page-item"><a class="page-link" href="/?nowPage=${p}">${p}</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<c:if test="${paging.endPage != paging.lastPage}">
							<li class="page-item"><a class="page-link" href="/?nowPage=${paging.endPage +1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
		</div>
		</div>
	<br><br><br>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>