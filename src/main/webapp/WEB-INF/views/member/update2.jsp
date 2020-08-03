<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link href="<c:url value='/resources/img/icon.png'/>" rel="shortcut icon" type="image/x-icon">
<title>BASAK</title>
<style type="text/css">
body {
	font-family: 'Varela Round', sans-serif;
}
.modal-login {
	width: 350px;
}
.modal-login .modal-content {
	padding: 20px;
	border-radius: 5px;
	border: none;
}
.modal-login .modal-header {
	border-bottom: none;
	position: relative;
	justify-content: center;
}
.modal-login .close {
	position: absolute;
	top: -10px;
	right: -10px;
}
.modal-login h4 {
	color: #636363;
	text-align: center;
	font-size: 26px;
	margin-top: 0;
}
.modal-login .modal-content {
	width: 400px;
	color: #999;
	border-radius: 1px;
	margin-bottom: 15px;
	background: #fff;
	border: 1px solid #f3f3f3;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 25px;
}
.modal-login .form-group {
	margin-bottom: 20px;
}
.modal-login label {
	font-weight: normal;
	font-size: 13px;
}
.modal-login .form-control {
	min-height: 38px;
	padding-left: 5px;
	box-shadow: none !important;
	border-width: 0 0 1px 0;
	border-radius: 0;
}
.modal-login .form-control:focus {
	border-color: #ccc;
}
.modal-login .input-group-addon {
	max-width: 42px;
	text-align: center;
	background: none;
	border-width: 0 0 1px 0;
	padding-left: 5px;
	border-radius: 0;
}
.modal-login .btn {
	font-size: 16px;
	font-weight: bold;
	background: #19aa8d;
	border-radius: 3px;
	border: none;
	min-width: 140px;
	outline: none !important;
}
.modal-login .btn:hover, .modal-login .btn:focus {
	background: #179b81;
}
.modal-login .hint-text {
	text-align: center;
	padding-top: 5px;
	font-size: 13px;
}
.modal-login .modal-footer {
	color: #999;
	border-color: #dee4e7;
	text-align: center;
	margin: 0 -25px -25px;
	font-size: 13px;
	justify-content: center;
}
.modal-login a {
	color: #fff;
	text-decoration: underline;
}
.modal-login a:hover {
	text-decoration: none;
}
.modal-login a {
	color: #19aa8d;
	text-decoration: none;
}
.modal-login a:hover {
	text-decoration: underline;
}
.modal-login .fa {
	font-size: 21px;
}
.trigger-btn {
	display: inline-block;
	margin: 100px auto;
}
</style>
<script>
	$(document).ready(function() {
		$("#btnUpdate").click(function() {
			// 확인 대화상자
			if(document.getElementById('memberPw').value == document.getElementById('checkPw').value){
				alter("diff");
				return
			}
			if (confirm("수정하시겠습니까?")) {
				document.update_form.action = "update.do";
				document.update_form.submit();
			}
		});
	});
</script>

</head>
<body>
	<!-- Modal HTML -->
	<div class="card bg-dark text-white">
		<img src="<c:url value='/resources/img/login_bg.jpg'/>"
			class="card-img" alt="...">
		<div id="myModal" class="modal fade in"
			style="display: block; padding-top: 100px;">
			<div class="modal-dialog modal-login ">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Update</h4>
					</div>
					<br> <br>
					<div class="modal-body">
						<form name="update_form" method="post">
							<p>
								아이디 : <input type="text" name="memberId"
									value="${member.memberId}" readonly>
							</p>
							<p>
								기존 비밀번호 : <input type="text"
									value="${member.memberPw}">
							</p>
							<p>
								변경할 비밀번호 : <input type="text" name="memberPw" id="memberPw">
							</p>
							<p>
								비밀번호 확인 : <input type="text" id="checkPw">
							</p>
							<p>
								닉네임 : <input type="text" name="memberName"
									value="${member.memberName}">
							</p>
							<button id="btnUpdate" type="submit" class="btn btn-success">확인</button>
							<button type="reset" onclick="location.href='/member/info'" class="btn btn-light">취소</button>
							<div style="color: red;">${message}</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
