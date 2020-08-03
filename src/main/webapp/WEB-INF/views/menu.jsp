<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<nav class="navbar navbar-expand-lg navbar-light " style="background-color: white!important;">
	<input type="image" src="<c:url value='/resources/img/logo.png'/>"
		onclick="location.href='/'" class="rounded float-right" width="230"
		alt="..." />
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<button onclick="location.href='/snack'"
					class="rounded float-right"
					style="border: none; background: none;">
					<img src="<c:url value='/resources/img/search.png'/>" width="50%"
						height="50%" />
				</button>
			</li>
			<li class="nav-item active">
				<button onclick="location.href='/chart'"
					class="rounded float-right"
					style="border: none; background: none;">
					<img src="<c:url value='/resources/img/chart.png'/>" width="50%"
						height="50%" />
				</button>
			</li>
		</ul>

		<c:if test="${member == null}">
			<input type="image" src="<c:url value='/resources/img/add.png'/>"
				onclick="location.href='/member/write.do'"
				class="rounded float-right mr-3" width="30" alt="..." />
			<input type="image" src="<c:url value='/resources/img/user.png'/>"
				onclick="location.href='/member/login.do'"
				class="rounded float-right" width="30" alt="..." />
		</c:if>
		<c:if test="${member != null}">
			<button class="btn btn-dark mr-3" onclick="location.href='/member/info'">${member.memberName}</button>
			<input type="image" src="<c:url value='/resources/img/logout.png'/>"
				onclick="location.href='/member/logout.do'"
				class="rounded float-right" width="30" alt="..." />
		</c:if>
	</div>
</nav>