<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<!-- <html lang="en" style="height: 100%"> -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Chart</title>
	<script type="text/javascript">
		window.onload = function () {
			document.getElementById("option1").onclick = function () {
				document.getElementById("Title").style.display = "none";
				document.getElementById("Title1").style.display = "block";
				document.getElementById("Title2").style.display = "none";
				document.getElementById("Title3").style.display = "none";

				document.getElementById("ChartTitle").style.display = "none";
				document.getElementById("ChartTitle1").style.display = "block";
				document.getElementById("ChartTitle2").style.display = "none";
				document.getElementById("ChartTitle3").style.display = "none";

				document.getElementById("myChart1").style.display = "block";
				document.getElementById("myChart2").style.display = "none";
				document.getElementById("myChart3").style.display = "none";
				const json1 = JSON.parse('${snackJson}')
				var label1 = new Array();
				var mydata1 = new Array();
				for(var i=0; i < 5; i++){
					label1.push(json1[i]['name'].trim());
					mydata1.push(json1[i]['avg']);
				}
				var ctx = document.getElementById("myChart1");
				var mixedChart = {
					type: 'bar',
					labels: label1,
					datasets: [
						{
							label: 'Bar',
							data: mydata1,
							backgroundColor: 'rgba(256, 0, 0, 0.1)'
						},
						{
							label: 'Line',
							data: mydata1,
							backgroundColor: 'transparent',
							borderColor: 'skyblue',
							type: 'line'
						}
					],
				};
				var myChart = new Chart(ctx, {
					type: 'bar',
					data: mixedChart,
					options: {
						legend: {
							display: true
						},
						scales: {
							yAxes: [{
								ticks: {
									min: 0,
									max: 5,
									stepSize: 1,
									fontSize: 20,
								}
							}],
							xAxes: [{
								ticks: {
									fontSize: 20,
								}
							}]
						}
					}
				});
			}

			document.getElementById("option2").onclick = function () {
				document.getElementById("Title").style.display = "none";
				document.getElementById("Title1").style.display = "none";
				document.getElementById("Title2").style.display = "block";
				document.getElementById("Title3").style.display = "none";

				document.getElementById("ChartTitle").style.display = "none";
				document.getElementById("ChartTitle1").style.display = "none";
				document.getElementById("ChartTitle2").style.display = "block";
				document.getElementById("ChartTitle3").style.display = "none";

				document.getElementById("myChart1").style.display = "none";
				document.getElementById("myChart2").style.display = "block";
				document.getElementById("myChart3").style.display = "none";
				const json2 = JSON.parse('${companyJson}')
				var back2 =  ['rgba(255, 99, 132, 0.5)',
							'rgba(54, 162, 235, 0.5)',
							'rgba(255, 206, 86, 0.5)',
							'rgba(75, 192, 192, 0.5)',
							'rgba(153, 102, 255, 0.5)',
							'rgba(255, 159, 64, 0.5)']
				var bord2 = ['rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)']

				var label2 = new Array();
				var mydata2 = new Array();
				var back = new Array();
				var bord = new Array();
				for(i in json2){
					label2.push(json2[i]['name'].trim());
					mydata2.push(json2[i]['avg']);
					back.push(back2[i]);
					bord.push(bord2[i]);
				}
				data = {
					labels: label2,
					datasets: [{
						data: mydata2,
						backgroundColor: back,
						borderColor: bord,
						borderWidth: 1
					}], 
				};
				var ctx1 = document.getElementById("myChart2");
				var myPieChart = new Chart(ctx1, {
					type: 'pie',
					data: data,
					options: {}
				});
			}

			document.getElementById("option3").onclick = function () {
				document.getElementById("Title").style.display = "none";
				document.getElementById("Title1").style.display = "none";
				document.getElementById("Title2").style.display = "none";
				document.getElementById("Title3").style.display = "block";

				document.getElementById("ChartTitle").style.display = "none";
				document.getElementById("ChartTitle1").style.display = "none";
				document.getElementById("ChartTitle2").style.display = "none";
				document.getElementById("ChartTitle3").style.display = "block";

				document.getElementById("myChart1").style.display = "none";
				document.getElementById("myChart2").style.display = "none";
				document.getElementById("myChart3").style.display = "block";
				const json3 = JSON.parse('${categoryJson}')
				var back3 = ['rgba(255, 99, 132, 0.5)',
							'rgba(54, 162, 235, 0.5)',
							'rgba(255, 206, 86, 0.5)',
							'rgba(75, 192, 192, 0.5)',
							'rgba(153, 102, 255, 0.5)',
							'rgba(255, 159, 64, 0.5)']
				var bord3 = ['rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)']
				
				var label3 = new Array();
				var mydata3 = new Array();
				var back = new Array();
				var bord = new Array();
				for(i in json3){
					label3.push(json3[i]['name'].trim());
					mydata3.push(json3[i]['avg']);
					back.push(back3[i]);
					bord.push(bord3[i]);
				}
				data = {
					labels: label3,
					datasets: [{
						data: mydata3,
						backgroundColor: back,
						borderColor: bord,
						borderWidth: 1
					}],

				};
				var donutOptions = {
					cutoutPercentage: 40,
					legend: { position: 'bottom', padding: 5, labels: { pointStyle: 'circle', usePointStyle: true } }
				};

				var ctx2 = document.getElementById("myChart3");
				var myDoughnutChart = new Chart(ctx2, {
					type: 'doughnut',
					data: data,
					options: donutOptions
				});
			}
		}
	</script>
</head>

<body>
	<%@ include file="menu.jsp" %>
	<br>
	<div class="btn-group btn-group-toggle " data-toggle="buttons" style="padding-left: 150px;">
		<label class="btn btn-info btn-lg" id="option1"><input type="radio">제품</label>
		<label class="btn btn-info btn-lg" id="option2"><input type="radio">브랜드</label>
		<label class="btn btn-info btn-lg" id="option3"><input type="radio">종류</label>
	</div>

	<div class="container">
		<div class="row my-3">
			<div class="col-12">
				<h4 id="Title">버튼을 클릭하세요</h4>
				<h4 id="Title1" style="display: none;">제품별 Bar Chart</h4>
				<h4 id="Title2" style="display: none;">브랜드별 Pie Chart</h4>
				<h4 id="Title3" style="display: none;">종류별 Doughnut Chart</h4>
			</div>
		</div>
		<div class="row my-2">
			<div class="col-lg-6">
				<div class="card" style="width: 60rem; Height: 35rem">
					<div class="card-body">
						<canvas id="myChart1"></canvas>
						<canvas id="myChart2" style="display: none;"></canvas>
						<canvas id="myChart3" style="display: none;"></canvas> </div>
					<div class="card-footer text-center text-dark">
						<h3 id="ChartTitle">(빈 그래프)</h3>
						<h3 id="ChartTitle1" style="display: none;">과자 별점 순위 그래프</h3>
						<h3 id="ChartTitle2" style="display: none;">브랜드별 인기 그래프</h3>
						<h3 id="ChartTitle3" style="display: none;">종류별 인기 그래프</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>

</html>