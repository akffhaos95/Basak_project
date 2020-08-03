<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
<!--     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> -->
<!--     <link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet"> -->
<!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
<!--     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> -->
    <link href="<c:url value='/resources/img/icon.png'/>" rel="shortcut icon" type="image/x-icon">
    <title>Info</title>
    <style type="text/css">
        body {
            font-family: 'Varela Round', sans-serif;
        }

        .modal-login {
            width: 700px;
        }

        .modal-login2 {
            width: 1500px;
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

        /* .modal-login .close {
            position: absolute;
            top: -10px;
            right: -10px;
        } */

        .modal-login h4 {
            color: #636363;
            text-align: center;
            font-size: 26px;
            margin-top: 0;
        }

        .modal-login .modal-content {
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
            font-size: 15px;
        }

        /* .modal-login .form-control {
            min-height: 38px;
            padding-left: 5px;
            box-shadow: none !important;
            border-width: 0 0 1px 0;
            border-radius: 0;
        } */

        /* .modal-login .form-control:focus {
            border-color: #ccc;
        } */

        /* .modal-login .input-group-addon {
            max-width: 42px;
            text-align: center;
            background: none;
            border-width: 0 0 1px 0;
            padding-left: 5px;
            border-radius: 0;
        } */

        .modal-login .btn {
            font-size: 16px;
            font-weight: bold;
            background: #19aa8d;
            border-radius: 3px;
            border: none;
            min-width: 140px;
            outline: none !important;
        }

        /* .modal-login .btn:hover,
        .modal-login .btn:focus {
            background: #179b81;
        } */

        /* .modal-login .hint-text {
            text-align: center;
            padding-top: 5px;
            font-size: 13px;
        } */

        /* .modal-login .modal-footer {
            color: #999;
            border-color: #dee4e7;
            text-align: center;
            margin: 0 -25px -25px;
            font-size: 13px;
            justify-content: center;
        } */

        /* .modal-login a {
            color: #fff;
            text-decoration: underline;
        } */

        /* .modal-login a:hover {
            text-decoration: none;
        } */

        /* .modal-login a {
            color: #19aa8d;
            text-decoration: none;
        } */

        /* .modal-login a:hover {
            text-decoration: underline;
        } */

        /* .modal-login .fa {
            font-size: 21px;
        }

        .trigger-btn {
            display: inline-block;
            margin: 100px auto;
        } */

        body {
            background-image: url("/resources/img/info_bg.jpg");
            background-repeat: no-repeat;
            background-size: cover;
        }
        .star-rating {width:304px; }
		.star-rating,.star-rating span {display:inline-block; height:55px; overflow:hidden; background:url('/resources/img/star.png')no-repeat; }
		.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
    </style>
</head>
<!-- Modal HTML -->
<body>
	<%@ include file="../menu.jsp" %>
    <div id="myModal" class="container" style="margin-top:50px; width:50%; height: 310; float:left;">
        <div class="modal-dialog modal-login ">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Info</h4>
                </div><br>
                <div class="modal-body">
                    <form action="/member/update" method="get">
                        <div class="form-group row">
                            <label for="ID" class="col-sm-2 col-form-label">ID</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext form-control-lg" id="ID" value="${member.memberId}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="Name" class="col-sm-2 col-form-label">Name</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext form-control-lg" id="Name" value="${member.memberName}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="Email" class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext form-control-lg" id="Email" value="${member.memberMail}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="Email" class="col-sm-2 col-form-label">Star</label>
                            <div class="col-sm-10">
                                <div class='star-rating'>
									<span style ="width:${avg}%"></span>						        
							   	</div>
                            </div>
                        </div>

                        <br>
                        <div class="d-flex justify-content-center">
                            <button onclick="location.href='/member/update'" class="btn btn-success">수정하기</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <div id="myModal" class="container" style="margin-top:50px; width:50%; height: 310; float:right">
        <div class="modal-dialog modal-login2 ">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">내가 쓴 리뷰</h4>
                </div><br>
                <div class="modal-body">

                    <table class="table table-sm">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>별점</th>
                                <th>리뷰</th>
                                <th>과자</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="test" items="${viewAll}">
                        	<tr>
								<td scope="row">${test.reviewId}</td>
								<td>${test.star}</td>
								<td>${test.comment}</td>
								<td>${test.snackId}
								<td>        
									<button type="submit" class="btn btn-success" onclick="location.href='/review/delete/${test.reviewId}'">삭제</button>
                    	        </td>		
							</tr>
						</c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>