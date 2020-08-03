<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="/?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
<style>
	.star-rating {width:304px; }
	.star-rating,.star-rating span {display:inline-block; height:55px; overflow:hidden; background:url('/resources/img/star.png')no-repeat; }
	.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
</style>
<link href="<c:url value='/resources/img/icon.png'/>" rel="shortcut icon" type="image/x-icon">
<title>INFO</title>
</head>
<body>
<div class="card" style="background-image:url(<c:url value='/resources/img/list_bg.jpg'/>);
						background-repeat : no-repeat;
						background-size : cover;">
	<%@ include file="../menu.jsp" %>
	<br>
	<div class="container-fluid" >
		<div class="card mb-3 " style="max-width: 1000px; margin:auto ">
			<div class="row no-gutters ">
				<div class="col-md-4">
					<img src="<c:url value='/resources/img/snack/${snack.snackIMG}'/>" class="card-img" alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-header text-center" ><b>${snack.snackNAME}</b></h5>
						<br>
						<div style="margin-left:100px; margin-top:20px">
						<p class="card-text">제조사 : ${snack.snackCompanyS}</p>
						<p class="card-text">종류 : ${snack.snackCategoryS}</p>
						<p class="card-text">열량 : ${snack.snackCal}kcal</p>
						<c:if test="${avg!=null}">
							<div class="wrap-star">
    							<c:choose>
	    							<c:when test="${avg==0}">
    									아직 리뷰가 없습니다
    								</c:when>
						       		<c:otherwise>
    									<div class='star-rating'>
								    	    <span style ="width:${avg}%"></span>						        
							   			</div>
							        </c:otherwise>
							    </c:choose>
							</div>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="container-md">
		<c:if test="${member != null}">
		<form action="/review/write.do">
			<input hidden="true" name="snackId" value="${snack.snackId}"/>
			<div class="input-group mb-3">
			<div class="btn-group" role="group">
    			<select name="star">
    				<option value="5">✩✩✩✩✩</option>
    				<option value="4">✩✩✩✩</option>
    				<option value="3">✩✩✩</option>
    				<option value="2">✩✩</option>
    				<option value="1">✩</option>
				</select>
    		</div>
			<input type="text" name="comment" class="form-control text-center" placeholder="한줄평" aria-label="한줄평" aria-describedby="button-addon2">
			<div class="input-group-append">
				<button type="submit" class="btn btn-dark float-right" type="button" id="button-addon2">저장</button>
			</div>
			</div>
		</form>
		</c:if>
			<table class="table">
			<thead class="thead-dark">
				<tr>
					<th style="width : 10%">작성자</th>
					<th style="width : 10%">별점</th>
					<th style="width : 60%">리뷰</th>
					<th style="width : 20%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="review" items="${viewAll}">
				<tr>
					<th scope="row">${review.memberId}</th>
					<td><c:forEach begin="1" end="${review.star}">
							✩
					</c:forEach></td>
					<td style="align-left">${review.comment}</td>
					<td><c:if test="${review.memberId eq member.memberId}">
						<button type="button" class="btn btn-danger" onclick="location.href='/review/delete/${review.reviewId}'">삭제</button>
					</c:if></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	<br>
	<div class="d-flex justify-content-around ">
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<c:if test="${paging.startPage != 1}">
						<li class="page-item "><a class="page-link" href="/snack/info/${snack.snackId}?nowPage=${paging.startPage -1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if> 
					<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage}">
								<li class="page-item"><a class="page-link">${p}</a></li>
							</c:when>
							<c:when test="${p != paging.nowPage}">
								<li class="page-item"><a class="page-link" href="/snack/info/${snack.snackId}?nowPage=${p}">${p}</a></li>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${paging.endPage != paging.lastPage}">
		 				<li class="page-item"><a class="page-link" href="/snack/info/${snack.snackId}?nowPage=${paging.endPage +1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
 					</c:if>
				</ul>
			</nav>
		</div>
	</div>
<br><br><br>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
