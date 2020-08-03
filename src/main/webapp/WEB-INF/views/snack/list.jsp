<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<link href="<c:url value='/resources/img/icon.png'/>" rel="shortcut icon" type="image/x-icon">
<title>SEARCH</title>
</head>
<body>
	<div class="card" style="background-image:url(<c:url value='/resources/img/list_bg.jpg'/>);
						background-repeat : no-repeat;
						background-size : cover;">
		<%@ include file="../menu.jsp"%>
		<br> <br>
		<div class="container-sm">
			<div class="d-flex justify-content-around">
				
					<form name="form1" method="get" action="/snack" class="input-group input-group-lg col-md-8 offset-md-4">
					<select class="custom-select" name="category" id="inputGroupSelect04" aria-label="Example select with button addon">
						<option value="" selected>종류</option>
						<c:forEach var="snackCate" items="${snackCate}">
							<option value="${snackCate.categoryId}">${snackCate.categoryName}</option>
						</c:forEach>
					</select> <select class="custom-select" name="company" id="inputGroupSelect04" aria-label="Example select with button addon">
						<option value="" selected>브랜드</option>
						<c:forEach var="snackCom" items="${snackCom}">
							<option value="${snackCom.companyId}">${snackCom.companyName}</option>
						</c:forEach>
					</select> 
					<input name="search" type="text" style="width: 40%" class="form-control" aria-label="Text input with segmented dropdown button">
					<div class="input-group-append">
						<button class="btn btn-dark" type="submit">
							<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  							<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
  							<path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
							</svg>
							&nbsp 검색
						</button>
					</div>
					</form>
			</div>
		</div>
		<br>
		<br>
		<br>

		<div class="container-md">
			<div class="row row-cols-1 row-cols-md-4 ">
				<c:forEach var="snack" items="${snackid}">
					<div class="col mb-4">
						<a href="/snack/info/${snack.snackId}" class="text-muted">
							<div class="card bg-light text-center">
								<img src="<c:url value='/resources/img/snack/${snack.snackIMG}'/>" class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title" style="color: black;">${snack.snackNAME}</h5>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
			<br> <br> <br>
		</div>
	</div>
	<%@ include file="../footer.jsp"%>
</body>
</html>
