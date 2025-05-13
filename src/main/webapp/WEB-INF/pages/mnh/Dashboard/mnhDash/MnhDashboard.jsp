<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/miso/mnhDashboard/mnhDashboardJS.js"></script>
<link rel="stylesheet" href="js/miso/mnhDashboard/mnhDashboardCSS.css">

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/JS_CSS/jquery.dataTables.min.css">
<script type="text/javascript" src="js/common/commonmethod.js"></script>



<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<script src="js/amchart4/index.js"></script>
<script src="js/amchart4/xy.js"></script>
<script src="js/amchart4/Animated5.js"></script>

<script src="js/amchart4/Chart.js"></script>
<style>
#Morbidity {
	width: 100%;
	height: 40%;
}

#chartpie {
	width: 100%;
	height: 60%;
}

#chartdivline {
	width: 100%;
	height: 40%;
}

#chartuploadline {
	width: 100%;
	height: 60%;
}

/* #chartuploadline_lmc {
	width: 100%;
	height: 60%;
} */
.button {
	/* Green */
	border: none;
	color: white;
	padding: 2px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.table tbody {
	display: block;
	max-height: 300px;
	overflow-y: scroll;
	width: 100%;
	scrollbar-width: thin;
}

.table thead, .table tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
}

.table thead {
	width: calc(100% - 8px);
}
/* D3 */
#BlockDescTblList1 {
	border-collapse: collapse;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	box-shadow: 0px 0px 22px #888;
	-moz-box-shadow: 0px 0px 22px #888;
	-webkit-box-shadow: 0px 0px 22px #888;
}

#BlockDescTblList1 td {
	padding: -1px;
	border: 1px solid black;
}

#BlockDescTblList1 th {
	text-align: center;
	padding: 7px 2px;
	border: 1px solid #000;
	background-color: #9c27b0;
	color: #fff;
}

#BlockDescTblList1 tr:nth-child(odd) {
	background-color: white;
}

#BlockDescTblList1 tr:nth-child(even) {
	background-color: #cedeef;
}

.highlight {
	background-color: #FFFF88;
}

table.dataTable.nowrap th, table.dataTable.nowrap td {
	white-space: normal;
}

.pagination {
	border-radius: 4px;
	display: inline-block;
	margin: 0px 0 -5px;
	padding-left: 0;
	width: 370px;
}

.paging_full_numbers {
	width: 370px !important;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	z-index: 2;
	color: #000;
	cursor: default;
	background-color: #66cc99; /* #2191c0; */
	border: 2px solid #00664d; /* 1px solid #4297d7; */
	font-weight: bold;
}

table.dataTable.display tbody tr.odd>.sorting_1, table.dataTable.order-column.stripe tbody tr.odd>.sorting_1
	{
	background-color: white;
}

table.dataTable.display tbody tr.even>.sorting_1, table.dataTable.order-column.stripe tbody tr.even>.sorting_1
	{
	background-color: #cedeef;
}

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: right;
	padding-left: 70%;
}



.table tr th:first-child {
	
}
</style>
<div class="animated fadeIn">
	<div class="row">
		<div class="col-md-12" align="center">
			<div class="card">
				<div id="DASHBOARD_tabs">
					<h4 class="trans_heading">
						<b><u>MEDICAL AND HEALTH DASHBOARD</u></b>
					</h4>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12 row from-group">
						<div class="col-md-2" style="text-align: right;">
							<label for="text-input" class=" form-control-label">Year</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="yr" name="yr" class="form-control-sm form-control" autocomplete="off">
						</div>
						<div class="col-md-2" style="text-align: right;">
							<label for="text-input" class=" form-control-label">Type</label>
						</div>
						<div class="col-md-2">
							<select name="relationship" id="relationship" class="form-control-sm form-control">
								<option value="SELF">SELF</option>
								<option value="DEPENDENT">DEPENDENT</option>
							</select>
						</div>
						<div class="col-md-2">
							<button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="Chart1Category();">Refresh Graph</button>
						</div>
						
						<div class="col-md-2">
						<a href="DgMSDashboard" class="btn btn-primary btn-sm"  >Daily Disease SVL</a>
						</div>
					</div>
					<div class="col-md-12 row from-group" style="text-align: center;border-top: solid #c8cbce 1px;">
						<div class="col-md-2">
							<h6 class="trans_heading"><b><u>LIFE STYLE AND IMPORTANT DISEASES</u></b></h6>
						</div>
						<div class="col-md-5">
							<h6 class="trans_heading"><b><u>NOTIFIABLE DISEASES (CUMULATIVE)</u></b></h6>
						</div>
						<div class="col-md-5">
							<h6 class="trans_heading"><b><u>SUICIDES</u></b></h6>
						</div>
					</div>
					<div class="col-md-12 row from-group">
						<div class="col-md-2">
							<div class="col-md-6">
								<div class="info-box bg-deep-purple">
									<div class="content">
										<h5>IHD</h5>
										<div class="number count-to" data-from="0" data-to="125" data-speed="1000" data-fresh-interval="20">
											<a data-toggle="modal" data-target="#illegalityMiningCasesView" onclick="openihdDetails();"><label><u id="ihdTotal"></u></label>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="info-box bg-deep-pink">
									<div class="content">
										<h5>HTN</h5>
										<div class="number count-to" data-from="0" data-to="257" data-speed="1000" data-fresh-interval="20">
											<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengethypertenstionDetails();"> <label><u id="hyperTotal"></u></label>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="info-box bg-green">
									<div class="content">
										<h5>DIABETES</h5>
										<div class="number count-to" data-from="0" data-to="1432"
											data-speed="1500" data-fresh-interval="20">
											<a data-toggle="modal" data-target="#minorMineralMiningView"
												onclick="opengetgetdiabetesDetails();"> <label><u
													id="diabetesTotal"></u></label>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="info-box bg-red">
									<div class="content">
										<h5>OBESITY</h5>
										<div class="number count-to" data-from="0" data-to="1432"
											data-speed="1500" data-fresh-interval="20">
											<a data-toggle="modal" data-target="#minorMineralMiningView"
												onclick="opengetobesityDetails();"> <label><u
													id="obesTotal"></u></label>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6 ">
								<div class="info-box bg-indigo">
									<div class="content">
										<h5>ADS</h5>
										<div class="number count-to" data-from="0" data-to="1432"
											data-speed="1500" data-fresh-interval="20">
											<a data-toggle="modal" data-target="#minorMineralMiningView"
												onclick="opengetADSDetails();"> <label><u
													id="adsTotal"></u></label>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6 ">
								<div class="info-box bg-purple">
									<div class="content">
										<h5>INJ NEA</h5>
										<div class="number count-to" data-from="0" data-to="117"
											data-speed="1000" data-fresh-interval="20">
											<a data-toggle="modal" data-target="#minorMineralMiningView"
												onclick="opengetgetinjuriesDetails();"> <label><u
													id="injuTotal"></u></label>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div id="notifiable_diseases" style="width: 100%; height: 350px;"></div>
						</div>
						<div class="col-md-5">
							<div id="suicideCases" style="width: 100%; height: 350px;"></div>
						</div>
					</div>
					
					
					<!-- <div class="col-md-12 row from-group" style="text-align: center;border-top: solid #c8cbce 1px;">
						<div class="col-md-3">
							<h6 class="trans_heading"><b><u>HOSPITAL ADMISSION</u></b></h6>
						</div>
						<div class="col-md-4">
							<h6 class="trans_heading"><b><u>TOP 10 CAUSES OF HOSPITAL ADM (SERVING)</u></b></h6>
						</div>
						<div class="col-md-5">
							<h6 class="trans_heading"><b><u>COMMAND-WISE TOTAL ADMISSION (SERVING)</u></b></h6>
						</div>
					</div> -->
					<div class="col-md-12 row from-group" align="center">
						<div class="col-md-3">
							<h6 class="trans_heading"><b><u>HOSPITAL ADMISSION</u></b></h6>
							<b style="text-decoration: underline;">SERVING & DEPENDENTS</b>
							<div id="Morbidity" style="width: 100%; height: 250px;"></div>
							<b style="text-decoration: underline;">EX-SERVICEMEN & DEPENDENTS</b>
							<div id="lineGraph" style="width: 100%; height: 250px;"></div>
						</div>
						<div class="col-md-9">
							<div class="col-md-6">
								<h6 class="trans_heading"><b><u>TOP 10 CAUSES OF HOSPITAL ADM (SERVING)</u></b></h6>
								<div id="chartuploadline" style="width: 100%; height: 500px;"></div>
								<div align="center">
									<button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="openViewMoreReport();">View More</button>
								</div>
							</div>
							<div class="col-md-6">
								<h6 class="trans_heading"><b><u>COMMAND-WISE TOTAL ADMISSION (SERVING)</u></b></h6>
								<!-- <b><img alt="Refresh" src="../login_file/referesh.ico" onclick="chartpie();"></b> -->
								<div id="chartpie" style="width: 100%; height: 500px;"></div>
							</div>
						</div>
					</div>
	<%-- 				<div class="col-md-12 state_tables">
						<div class="row" id="BlkDesc">
							<table
								class="table no-margin table-striped  table-hover  table-bordered">
								<thead style="color: white; text-align: center;">
									<tr>
										<td colspan="2"><h4>DATA STATUS FOR CURRENT YEAR</h4></td>
									</tr>
								</thead>
								<thead style="background-color: #9c27b0; color: white;">
									<tr style="font-size: 15px;">
										<th style="font-size: 14px; text-align: center;" width="5%">Ser	No</th>
										<th style="font-size: 14px; text-align: center;" width="25%">HOSPITAL</th>
										<th style="font-size: 14px; text-align: center;" width="5%">JAN</th>
										<th style="font-size: 14px; text-align: center;" width="5%">FEB</th>
										<th style="font-size: 14px; text-align: center;" width="5%">MAR</th>
										<th style="font-size: 14px; text-align: center;" width="5%">APR</th>
										<th style="font-size: 14px; text-align: center;" width="5%">MAY</th>
										<th style="font-size: 14px; text-align: center;" width="5%">JUN</th>
										<th style="font-size: 14px; text-align: center;" width="5%">JUL</th>
										<th style="font-size: 14px; text-align: center;" width="5%">AUG</th>
										<th style="font-size: 14px; text-align: center;" width="5%">SEP</th>
										<th style="font-size: 14px; text-align: center;" width="5%">OCT</th>
										<th style="font-size: 14px; text-align: center;" width="5%">NOV</th>
										<th style="font-size: 14px; text-align: center;" width="5%">DEC</th>
										<th style="font-size: 14px; text-align: center;" width="5%">TOTAL</th>
									</tr>
								</thead>
								<tbody>
									<tr class="listheading nr-medium nr-blue box"
										style="font-size: 15px">
										<c:forEach var="item" items="${hospital_datastatus}"
											varStatus="num">
											<tr>
												<th style="font-size: 14px; text-align: center;" width="5%">${num.index+1}</th>
												<th style="font-size: 14px; text-align: left;" width="25%">${item[0]}</th>
												<c:if test="${item[1]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[1]}</th>
												</c:if>
												<c:if test="${item[1]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[1]}</th>
												</c:if>
												<c:if test="${item[2]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[2]}</th>
												</c:if>
												<c:if test="${item[2]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[2]}</th>
												</c:if>
				
												<c:if test="${item[3]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[3]}</th>
												</c:if>
												<c:if test="${item[3]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[3]}</th>
												</c:if>
				
												<c:if test="${item[4]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[4]}</th>
												</c:if>
												<c:if test="${item[4]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[4]}</th>
												</c:if>
				
												<c:if test="${item[5]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[5]}</th>
												</c:if>
												<c:if test="${item[5]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[5]}</th>
												</c:if>
				
												<c:if test="${item[6]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[6]}</th>
												</c:if>
												<c:if test="${item[6]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[6]}</th>
												</c:if>
				
												<c:if test="${item[7]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[7]}</th>
												</c:if>
												<c:if test="${item[7]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[7]}</th>
												</c:if>
				
												<c:if test="${item[8]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[8]}</th>
												</c:if>
												<c:if test="${item[8]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[8]}</th>
												</c:if>
				
												<c:if test="${item[9]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[9]}</th>
												</c:if>
												<c:if test="${item[9]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[9]}</th>
												</c:if>
				
												<c:if test="${item[10]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[10]}</th>
												</c:if>
												<c:if test="${item[10]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[10]}</th>
												</c:if>
				
												<c:if test="${item[11]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[11]}</th>
												</c:if>
												<c:if test="${item[11]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[11]}</th>
												</c:if>
				
												<c:if test="${item[12]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[12]}</th>
												</c:if>
												<c:if test="${item[12]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[12]}</th>
												</c:if>
				
												<c:if test="${item[13]!='0'}">
													<th style="font-size: 14px; text-align: center;" width="5%">${item[13]}</th>
												</c:if>
												<c:if test="${item[13]=='0'}">
													<th style="font-size: 14px; text-align: center; color: red"
														width="5%">${item[13]}</th>
												</c:if>
				
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
						</div>
					</div> --%>
				</div>
			</div>
		</div>
	</div>
</div>




<!-- <div class="card-body card-block">
	Life Style Diseases Start Here
	<h6 class="trans_heading">
		<b><u>LIFE STYLE AND IMPORTANT DISEASES</u></b></h6>
		<br>
		<div class="col-md-12">
			<div class="col-md-2 col-dash">
				<div class="info-box bg-indigo">
					<div class="content">
						<h5>TOTAL ADM</h5>
						<div class="number count-to" data-from="0" data-to="117"
							data-speed="1000" data-fresh-interval="20">
							<a data-toggle="modal" data-target="#minorMineralMiningView">
								<label id="admTotal"></label>
							</a>
						</div>
					</div>
				</div>
			</div>
	</div>
</div> -->
<!-- Life Style Diseases End Here -->
<!-- Graph Start Here -->
<div class="animated fadeIn">

	<%-- <div class="col-md-12">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
						<div class="col-md-12">
							<div class="col-md-2" style="text-align: right;">
								<label for="text-input" class=" form-control-label">From Date</label>
							</div>
							<div class="col-md-4">
								<input type="date" id="from_date" name="from_date" class="form-control-sm form-control" min="1899-01-01" max="${date}">
							</div>
							<div class="col-md-2" style="text-align: right;">
								<label for="text-input" class=" form-control-label">To Date</label>
							</div>
							<div class="col-md-3">
								<input type="date" id="to_date" name="to_date" class="form-control-sm form-control" min="1899-01-01" max="${date}">
							</div>
							<div class="col-md-1">
								<img alt="Refresh" src="../login_file/referesh.ico" onclick="Chart1Category2();">
							</div>
						</div>
				
					<div class="col-md-12 row form-group">
						<div class="col-md-12">
							<b style="text-decoration: underline;">BED STATE OF ARMY
								(SERVING)</b>
							<div id="chartuploadline_lmc1"	style="width: 100%; height: 250px;"></div>
						</div>
						
						
					</div>
				</div>
			</div>
		</div>
	</div> --%>

	<!-- ////lmc start -->
	<!-- <div class="col-md-12">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">

					<div class="col-md-12 row form-group">

						<div class="col-md-6">
							<b style="text-decoration: underline;">LOW MEDICAL CATEGORY
								OF ARMY (SERVING)</b>
							<div id="chartuploadline_lmc" style="width: 100%; height: 250px;"></div>
						</div>
						

					</div>

				</div>
			</div>
		</div>
	</div> -->
	<!-- ///lmc end here -->

	<!-- Data Status Month wise -->
	
</div>

<script type="text/javascript">
	$(document).ready(function() {

		ParseDateColumn();
		function ParseDateColumn() {
			var d = new Date();
			document.getElementById("yr").value = d.getFullYear();
		
		}

		//	$().getCurDt(from_date);
		//	$().getCurDt(to_date);

		$("div#divPrint").hide();

		$("u#ihdTotal").text('0');
		$("u#hyperTotal").text('0');
		$("u#diabetesTotal").text('0');
		$("u#obesTotal").text('0');
		$("u#adsTotal").text('0');
		$("u#injuTotal").text('0');
		//$("#admTotal").text('0');

		"<c:forEach var='item' items='${getCountAllImportantDis}' varStatus='num'>"
		if ('${item[0]}' == 'IHD') {
			$("u#ihdTotal").text('${item[1]}');
		} else if ('${item[0]}' == 'HTN') {
			$("u#hyperTotal").text('${item[1]}');
		} else if ('${item[0]}' == 'DM') {
			$("u#diabetesTotal").text('${item[1]}');
		} else if ('${item[0]}' == 'OBESITY') {
			$("u#obesTotal").text('${item[1]}');
		} else if ('${item[0]}' == 'INJURIES NEA') {
			$("u#injuTotal").text('${item[1]}');
		} else if ('${item[0]}' == 'ADS') {
			$("u#adsTotal").text('${item[1]}');
		} else if ('${item[0]}' == 'TOTAL ALL') {
			//$("#admTotal").text('${item[1]}');
		}
		"</c:forEach>";

		Morbidity();
		chartpie();
		//var getBED_STATE_ARMY_List = ${getBED_STATE_ARMY_List};
		//chartuploadline_lmc1(getBED_STATE_ARMY_List);
		chartuploadline();
		lineGraph();

		notifiable_diseases();
		//suicideCases();
	});
 	/* function suicideCases() {
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("suicideCases", am4charts.XYChart3D);
		chart.data = [ {
			"years" : "2018",
			"male" : 30,
			"female" : 40
		}, {
			"years" : "2019",
			"male" : 10,
			"female" : 30
		}, {
			"years" : "2020",
			"male" : 20,
			"female" : 20
		}, {
			"years" : "2021",
			"male" : 20,
			"female" : 20
		}, {
			"years" : "2022",
			"male" : 10,
			"female" : 20
		} ];
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "years";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 30;

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "No of Cases";
		valueAxis.renderer.labels.template.adapter.add("text", function(text) {
			return text;
		});

		var series = chart.series.push(new am4charts.ColumnSeries3D());
		series.dataFields.valueY = "female";
		series.dataFields.categoryX = "years";
		series.clustered = false;
		series.name = "Female";
		series.columns.template.tooltipText = "No of Female Cases in {categoryX} : [bold]{valueY}[/]";
		series.columns.template.fillOpacity = 0.9;

		var series2 = chart.series.push(new am4charts.ColumnSeries3D());
		series2.dataFields.valueY = "male";
		series2.dataFields.categoryX = "years";
		series2.clustered = false;
		series2.name = "Male";
		series2.columns.template.tooltipText = "No of male Cases in {categoryX} : [bold]{valueY}[/]";
		chart.legend = new am4charts.Legend();
	} 
	 */
	
	/* var root = am5.Root.new("suicideCases");


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	]);


	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
	  panX: false,
	  panY: false,
	  wheelX: "panX",
	  wheelY: "zoomX",
	  layout: root.verticalLayout
	}));
	console.log(chart);
	
		chart.set("scrollbarX", am5.Scrollbar.new(root, {
		  orientation: "horizontal"
	}));



	var data =${Getrk_medcatlist};
	//alert(data);

	var xRenderer = am5xy.AxisRendererX.new(root, {});
	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
	  categoryField: "data1",
	  renderer: xRenderer,
	  tooltip: am5.Tooltip.new(root, {})
	}));

	xAxis.data.setAll(data);

	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
	  min: 0,
	  renderer: am5xy.AxisRendererY.new(root, {})
	}));


	// Add legend
	// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
	var legend = chart.children.push(am5.Legend.new(root, {
	  centerX: am5.p50,
	  x: am5.p50
	}));


	// Add series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	function makeSeries(name, fieldName, total1) {
	  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
	    name: name,
	    stacked: true,
	    xAxis: xAxis,
	    yAxis: yAxis,
	    valueYField: fieldName,
	    categoryXField: "data1"
	  }));

	  series.columns.template.setAll({
	    tooltipText: "{name}, {categoryX}: {valueY}",
	    tooltipY: am5.percent(10)
	  });
	  series.data.setAll(data);

	  // Make stuff animate on load
	  // https://www.amcharts.com/docs/v5/concepts/animations/
	  series.appear();

	  series.bullets.push(function () {
	    return am5.Bullet.new(root, {
	      sprite: am5.Label.new(root, {
	       // text: "{valueY}",
	        fill: root.interfaceColors.get("alternativeText"),
	        centerY: am5.p50,
	        centerX: am5.p50,
	        //populateText: true
	      })
	    });
	  });

	  legend.data.push(series);
	  
	  
	  if (total1) {
		    series.bullets.push(function () {
		      var total1Label = am5.Label.new(root, {
		        text: "{valueY}",
		        fill: root.interfaceColors.get("text"),
		        centerY: am5.p100,
		        centerX: am5.p50,
		        populateText: true,
		        textAlign: "center"
		      });
		      
		      total1Label.adapters.add("text", function(text, target) {
		        var dataContext = target.dataItem.dataContext;
		        var val = Math.abs(dataContext.data2 + dataContext.data3 + dataContext.data4);
		        return ""+val;
		      });
		      
		      return am5.Bullet.new(root, {
		        locationX: 0.5,
		        locationY: 1,
		        sprite: total1Label
		      });
		    });
		  }
	}

	makeSeries("fit", "data2",false);
	makeSeries("permanent", "data3",false);
	makeSeries("temporary", "data4",true); */
			
		
/* 	function suicideCases() {
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("suicideCases", am4charts.XYChart3D);
 		chart.data = [ {
		 "years" : "2018",
			"male" : "data1",
			"female" : "data2"
		}, {
			"years" : "2019",
			"male" : "data1",
			"female" : "data2"
		}, {
			"years" : "2020",
			"male" : "data1",
			"female" :"data2"
		}, {
			"years" : "2021",
			"male" : "data1",
			"female" : "data2"
		}, { 
			"years" : "2022",
			"Male" : "data1",
			"Female" : "data2"
		} ];
		//alert(${suicides_list});
		//chart.data = ${suicides_list};
	
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "years";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 30;

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "No of Cases";
		valueAxis.renderer.labels.template.adapter.add("text", function(text) {
			return text;
		});

		var series = chart.series.push(new am4charts.ColumnSeries3D());
		series.dataFields.valueY = "Female";
		series.dataFields.categoryX = "years";
		series.clustered = false;
		series.name = "female";
		series.columns.template.tooltipText = "No of Female Cases in {categoryX} : [bold]{valueY}[/]";
		series.columns.template.fillOpacity = 0.9;

		var series2 = chart.series.push(new am4charts.ColumnSeries3D());
		series2.dataFields.valueY = "Male";
		series2.dataFields.categoryX = "years";
		series2.clustered = false;
		series2.name = "male";
		series2.columns.template.tooltipText = "No of male Cases in {categoryX} : [bold]{valueY}[/]";
		chart.legend = new am4charts.Legend();
	} */

	/* 
	function notifiable_diseases() {
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("notifiable_diseases", am4charts.XYChart);
		chart.scrollbarX = new am4core.Scrollbar();
		
		chart.data = ${notifiable_diseaselist};
		
		 chart.data = [ {
			"country" : "CHOLERA",
			"visits" : 10
		}, {
			"country" : "VIRAL HEPATITIS",
			"visits" : 15
		}, {
			"country" : "FOOD POISONING",
			"visits" : 20
		}, {
			"country" : "COVID 19",
			"visits" : 7
		}, {
			"country" : "INFLUENZA",
			"visits" : 6
		}, {
			"country" : "MALARIA",
			"visits" : 3
		}, {
			"country" : "DENGUE",
			"visits" : 7
		}, {
			"country" : "SCRUB TYPHUS",
			"visits" : 6
		}, {
			"country" : "MENINGO - COCCAL MENINGITIS",
			"visits" : 13
		}, {
			"country" : "ENCEPHALITIS",
			"visits" : 4
		} ]; 
		
		
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "data2";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 30;
		categoryAxis.renderer.labels.template.horizontalCenter = "right";
		categoryAxis.renderer.labels.template.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.rotation = 270;
		categoryAxis.tooltip.disabled = true;
		categoryAxis.renderer.minHeight = 110;

		let label = categoryAxis.renderer.labels.template;
		label.wrap = true;
		label.maxWidth = 130;
		
		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.renderer.minWidth = 50;

		

		var series = chart.series.push(new am4charts.ColumnSeries());
		series.sequencedInterpolation = true;
		series.dataFields.valueY = "data1";
		series.dataFields.categoryX = "data2";
		series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
		series.columns.template.strokeWidth = 0;

		series.tooltip.pointerOrientation = "vertical";

		series.columns.template.column.cornerRadiusTopLeft = 10;
		series.columns.template.column.cornerRadiusTopRight = 10;
		series.columns.template.column.fillOpacity = 0.8;

		var hoverState = series.columns.template.column.states.create("hover");
		hoverState.properties.cornerRadiusTopLeft = 0;
		hoverState.properties.cornerRadiusTopRight = 0;
		hoverState.properties.fillOpacity = 1;

		series.columns.template.adapter.add("fill", function(fill, target) {
			return chart.colors.getIndex(target.dataItem.index);
		});
		chart.cursor = new am4charts.XYCursor();
	} */

/* 	function notifiable_diseases() {
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("notifiable_diseases", am4charts.XYChart);
		chart.scrollbarX = new am4core.Scrollbar();
		chart.data = [ {
			"country" : "CHOLERA",
			"visits" : 10
		}, {
			"country" : "VIRAL HEPATITIS",
			"visits" : 15
		}, {
			"country" : "FOOD POISONING",
			"visits" : 20
		}, {
			"country" : "COVID 19",
			"visits" : 7
		}, {
			"country" : "INFLUENZA",
			"visits" : 6
		}, {
			"country" : "MALARIA",
			"visits" : 3
		}, {
			"country" : "DENGUE",
			"visits" : 7
		}, {
			"country" : "SCRUB TYPHUS",
			"visits" : 6
		}, {
			"country" : "MENINGO - COCCAL MENINGITIS",
			"visits" : 13
		}, {
			"country" : "ENCEPHALITIS",
			"visits" : 4
		} ];
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "country";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 30;
		categoryAxis.renderer.labels.template.horizontalCenter = "right";
		categoryAxis.renderer.labels.template.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.rotation = 270;
		categoryAxis.tooltip.disabled = true;
		categoryAxis.renderer.minHeight = 110;

		let label = categoryAxis.renderer.labels.template;
		label.wrap = true;
		label.maxWidth = 130;
		
		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.renderer.minWidth = 50;

		

		var series = chart.series.push(new am4charts.ColumnSeries());
		series.sequencedInterpolation = true;
		series.dataFields.valueY = "visits";
		series.dataFields.categoryX = "country";
		series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
		series.columns.template.strokeWidth = 0;

		series.tooltip.pointerOrientation = "vertical";

		series.columns.template.column.cornerRadiusTopLeft = 10;
		series.columns.template.column.cornerRadiusTopRight = 10;
		series.columns.template.column.fillOpacity = 0.8;

		var hoverState = series.columns.template.column.states.create("hover");
		hoverState.properties.cornerRadiusTopLeft = 0;
		hoverState.properties.cornerRadiusTopRight = 0;
		hoverState.properties.fillOpacity = 1;

		series.columns.template.adapter.add("fill", function(fill, target) {
			return chart.colors.getIndex(target.dataItem.index);
		});
		chart.cursor = new am4charts.XYCursor();
	} */
	
	
	function notifiable_diseases(){
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("notifiable_diseases", am4charts.XYChart);
		chart.scrollbarX = new am4core.Scrollbar();
		


		chart.data = ${notifiable_diseaselist};
	 	/* chart.data = [{
		  "country": "CHOLERA",
		  "visits": 10
		}, {
		  "country": "VIRAL HEPATITIS",
		  "visits": 15
		}, {
		  "country": "FOOD POISONING",
		  "visits": 20
		}, {
		  "country": "COVID 19",
		  "visits": 7
		}, {
		  "country": "INFLUENZA",
		  "visits": 6
		}, {
		  "country": "MALARIA",
		  "visits": 3
		}, {
		  "country": "DENGUE",
		  "visits": 7
		}, {
		  "country": "SCRUB TYPHUS",
		  "visits": 6
		}, {
		  "country": "MENINGO - COCCAL MENINGITIS",
		  "visits": 13
		}, {
		  "country": "ENCEPHALITIS",
		  "visits": 4
		}];  */
		
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "data2";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 30;
		categoryAxis.renderer.labels.template.horizontalCenter = "right";
		categoryAxis.renderer.labels.template.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.rotation = 270;
		categoryAxis.tooltip.disabled = true;
		categoryAxis.renderer.minHeight = 110;

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.renderer.minWidth = 50;

		let label = categoryAxis.renderer.labels.template;
		label.wrap = true;
		label.maxWidth = 130;


		var series = chart.series.push(new am4charts.ColumnSeries());
		series.sequencedInterpolation = true;
		series.dataFields.valueY = "data1";
		series.dataFields.categoryX = "data2";
		series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
		series.columns.template.strokeWidth = 0;

		series.tooltip.pointerOrientation = "vertical";

		series.columns.template.column.cornerRadiusTopLeft = 10;
		series.columns.template.column.cornerRadiusTopRight = 10;
		series.columns.template.column.fillOpacity = 0.8;

		var hoverState = series.columns.template.column.states.create("hover");
		hoverState.properties.cornerRadiusTopLeft = 0;
		hoverState.properties.cornerRadiusTopRight = 0;
		hoverState.properties.fillOpacity = 1;

		series.columns.template.adapter.add("fill", function(fill, target) {
		  return chart.colors.getIndex(target.dataItem.index);
		});
		chart.cursor = new am4charts.XYCursor();
	}
	//Chart 1 Serving Start Here
	function Morbidity() {
		am4core.useTheme(am4themes_animated);
		var Morbidity = am4core.create("Morbidity", am4charts.PieChart3D);
		Morbidity.hiddenState.properties.opacity = 0;
		Morbidity.data = ${getChart1List};
		Morbidity.innerRadius = am4core.percent(40);
		Morbidity.depth = 5;

		var series = Morbidity.series.push(new am4charts.PieSeries3D());
		series.dataFields.value = "count";
		series.dataFields.depthValue = "count";
		series.dataFields.category = "cat";
		series.labels.template.text = "{category} : {value.value}";
		series.slices.template.tooltipText = "{category} : {value.value}";
		series.slices.template.cornerRadius = 10;
		//series.colors.step = 5;

		var colorSet = new am4core.ColorSet();
		colorSet.list = [ "#388E3C", "#FBC02D", "#0288d1", "#F44336",
				"#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d",
				"#bf4080", "#e6e600", "#ff66cc" ].map(function(color) {
			return new am4core.color(color);
		});
		Morbidity.colors = colorSet;
	}

	//LMC Chart 2
	function chartuploadline_lmc1(lmc1) {
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("chartuploadline_lmc1", am4charts.XYChart);
		chart.data = lmc1;

		// Create axes
		var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "Command";
		categoryAxis.renderer.grid.template.opacity = 0;

		var valueAxis = chart.xAxes.push(new am4charts.ValueAxis());
		valueAxis.min = 0;
		valueAxis.renderer.grid.template.opacity = 0;
		valueAxis.renderer.ticks.template.strokeOpacity = 0.5;
		valueAxis.renderer.ticks.template.stroke = am4core.color("#495C43");
		valueAxis.renderer.ticks.template.length = 10;
		valueAxis.renderer.line.strokeOpacity = 0.5;
		valueAxis.renderer.baseGrid.disabled = true;
		valueAxis.renderer.minGridDistance = 40;

		// Create series
		function createSeries(field, name) {
			var series = chart.series.push(new am4charts.ColumnSeries());
			series.dataFields.valueX = field;
			series.dataFields.categoryY = "Command";
			series.stacked = true;
			series.name = name;
			series.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";

			var labelBullet = series.bullets.push(new am4charts.LabelBullet());
			labelBullet.locationX = 0.5;
			labelBullet.label.text = "{valueX}";
			labelBullet.label.fill = am4core.color("#fff");
		}
		createSeries("off", "OFF");
		createSeries("jco_or", "JCO_OR");

		/*am4core.useTheme(am4themes_animated);
		var chart = am4core.create("chartuploadline_lmc1", am4charts.XYChart);
		chart.data = lmc1;
		
		var colorSet4 = new am4core.ColorSet();
		colorSet4.list = ["#9400D3","#86592d","#1aff1a","#4285F4" ,"#FBC02D", "#F44336", "#34AB53"].map(function(color) {
		  return new am4core.color(color);
		});
		chart.colors = colorSet4;
		var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "Command";
		categoryAxis.numberFormatter.numberFormat = "#";
		categoryAxis.renderer.inversed = true;
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.cellStartLocation = 0.1;
		categoryAxis.renderer.cellEndLocation = 0.9;

		var  valueAxis = chart.xAxes.push(new am4charts.ValueAxis()); 
		valueAxis.renderer.opposite = true;
		var series = chart.series.push(new am4charts.ColumnSeries());
		function createSeries(field, name) {
		 
		  series.dataFields.valueX = field;
		  series.dataFields.categoryY = "Command";
		  series.name = name;
		  series.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
		  series.columns.template.height = am4core.percent(100);
		  series.sequencedInterpolation = true;

		  var categoryLabel = series.bullets.push(new am4charts.LabelBullet());
		  categoryLabel.label.horizontalCenter = "right";
		  categoryLabel.label.dx = -10;
		  //categoryLabel.label.fill = am4core.color("#fff");
		  categoryLabel.label.hideOversized = false;
		  categoryLabel.label.truncate = false;
			
		
		 series.columns.template.events.on("hit",function(ev){
		  	$.post("getBedCorpsWise_Count_List?"+key+"="+value,{Command : ev.target.dataItem.dataContext.Command}, function(j) {
		 		if(j.length == 0){
		 			alert("No Data Found");
		 		}else{
					var corps = new Array();
					for(var i=0;i<j.length;i++){
						if(i==0){
							corps.push({Command:j[i][0],off:j[i][1],jco_or:j[i][2]});	
						}else{
							corps.push({Command:j[i][0],off:j[i][1],jco_or:j[i][2]} );
						}
					}
					CommandBedClick_MNH(corps);
				}
			});  
			},this); 
			series.columns.template.adapter.add("fill", function(fill, target) {
				return chart.colors.getIndex(target.dataItem.index);
			});	  
		}
		createSeries("off", "OFF");
		createSeries("jco_or", "JCO_OR");	*/

	}

	//Bed State Corps
	function CommandBedClick_MNH(corps) {
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("chartuploadline_lmc1", am4charts.XYChart);

		chart.data = corps;

		var colorSet4 = new am4core.ColorSet();
		colorSet4.list = [ "#cc3300", "#e6ac00", "#86b300" ]
				.map(function(color) {
					return new am4core.color(color);
				});
		chart.colors = colorSet4;
		var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "Command";
		categoryAxis.numberFormatter.numberFormat = "#";
		categoryAxis.renderer.inversed = true;
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.cellStartLocation = 0.1;
		categoryAxis.renderer.cellEndLocation = 0.9;

		var valueAxis = chart.xAxes.push(new am4charts.ValueAxis());
		valueAxis.renderer.opposite = true;
		var series = chart.series.push(new am4charts.ColumnSeries());
		function createSeries(field, name) {

			series.dataFields.valueX = field;
			series.dataFields.categoryY = "Command";
			series.name = name;
			series.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
			series.columns.template.height = am4core.percent(100);
			series.sequencedInterpolation = true;

			var categoryLabel = series.bullets
					.push(new am4charts.LabelBullet());
			categoryLabel.label.horizontalCenter = "right";
			categoryLabel.label.dx = -10;
			categoryLabel.label.fill = am4core.color("#fff");
			categoryLabel.label.hideOversized = false;
			categoryLabel.label.truncate = false;

		}
		series.columns.template.events.on("hit", function(ev) {
			$.post("getBedDivsWise_Count_List?" + key + "=" + value, {
				Command : ev.target.dataItem.dataContext.Command
			}, function(j) {
				if (j.length == 0) {
					alert("No Data Found");
				} else {
					var corps = new Array();
					for (var i = 0; i < j.length; i++) {
						if (i == 0) {
							corps.push({
								Command : j[i][0],
								off : j[i][1],
								jco_or : j[i][2]
							});
						} else {
							corps.push({
								Command : j[i][0],
								off : j[i][1],
								jco_or : j[i][2]
							});
						}
					}
					DivBedClick_MNH(corps);

				}
			});
		}, this);
		createSeries("off", "OFF");
		createSeries("jco_or", "JCO_OR");
	}
	//Chart 2 Command wise Start Here

	function chartpie() {
		am4core.useTheme(am4themes_animated);
		var chartpie = am4core.create("chartpie", am4charts.XYChart);

		chartpie.data = ${getCommand_wise_count_List};

		var colorSet = new am4core.ColorSet();
		colorSet.list = [ "#4285F4", "#34AB53", "#F44336", "#9400D3",
				"#FBC02D", "#86592d", "#1aff1a" ].map(function(color) {
			return new am4core.color(color);
		});
		chartpie.colors = colorSet;

		var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "Command";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 20;
		categoryAxis.renderer.cellStartLocation = 0.1;
		categoryAxis.renderer.cellEndLocation = 0.7;
		categoryAxis.renderer.labels.template.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.rotation = 270;

		var valueAxis_cmd = chartpie.yAxes.push(new am4charts.ValueAxis());
		valueAxis_cmd.min = 0;
		function createSeries_cmd(field, name, stacked) {
			var series1 = chartpie.series.push(new am4charts.ColumnSeries());
			series1.dataFields.valueY = field;
			series1.dataFields.categoryX = "Command";
			series1.name = name;
			series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
			series1.stacked = stacked;
			series1.columns.template.width = am4core.percent(95);

			series1.columns.template.events.on("hit", function(ev) {
				$.post("getCorpsWise_Count_List?" + key + "=" + value, {
					Command : ev.target.dataItem.dataContext.cmd_code
				}, function(j) {
					if (j.length == 0) {
						alert("No Data Found");
					} else {
						var corps = new Array();
						for (var i = 0; i < j.length; i++) {
							if (i == 0) {
								corps.push({
									corps : j[i][0],
									count : j[i][1],
									corps_code : j[i][2]
								});
							} else {
								corps.push({
									corps : j[i][0],
									count : j[i][1],
									corps_code : j[i][2]
								});
							}
						}
						CommandClick_MNH(corps);
					}
				});

			}, this);
			series1.columns.template.adapter.add("fill",
					function(fill, target) {
						return chartpie.colors.getIndex(target.dataItem.index);
					});
		}
		createSeries_cmd("count", "Counts", false);
	}
	//Chart 2 End Here

	//Chart 2 corps Start Here
	function CommandClick_MNH(corps) {
		am4core.useTheme(am4themes_animated);
		var chartpie = am4core.create("chartpie", am4charts.XYChart);

		chartpie.data = corps;

		var colorSet = new am4core.ColorSet();
		colorSet.list = [ "#4285F4", "#34AB53", "#F44336", "#9400D3",
				"#FBC02D", "#86592d", "#1aff1a" ].map(function(color) {
			return new am4core.color(color);
		});
		chartpie.colors = colorSet;

		var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "corps";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 20;
		categoryAxis.renderer.cellStartLocation = 0.1;
		categoryAxis.renderer.cellEndLocation = 0.7;
		categoryAxis.renderer.labels.template.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.rotation = 270;

		var valueAxis_corps = chartpie.yAxes.push(new am4charts.ValueAxis());
		valueAxis_corps.min = 0;
		function createSeries_corps(field, name, stacked) {
			var series1 = chartpie.series.push(new am4charts.ColumnSeries());
			series1.dataFields.valueY = field;
			series1.dataFields.categoryX = "corps";
			series1.name = name;
			series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
			series1.stacked = stacked;
			series1.columns.template.width = am4core.percent(95);
			series1.columns.template.events.on("hit", function(ev) {
				$.post("getDivsWise_Count_List?" + key + "=" + value, {
					Command : ev.target.dataItem.dataContext.corps_code
				}, function(j) {
					if (j.length == 0) {
						alert("No Data Found");
					} else {
						var div = new Array();
						for (var i = 0; i < j.length; i++) {
							if (i == 0) {
								div.push({
									div : j[i][0],
									count : j[i][1],
									div_code : j[i][2]
								});
							} else {
								div.push({
									div : j[i][0],
									count : j[i][1],
									div_code : j[i][2]
								});
							}
						}
						DivClick_MNH(div);
					}
				});
			}, this);
			series1.columns.template.adapter.add("fill",
					function(fill, target) {
						return chartpie.colors.getIndex(target.dataItem.index);
					});
		}
		createSeries_corps("count", "Counts", false);
	}
	//Chart 2 corps End Here

	//Chart 2 Div Start Here
	function DivClick_MNH(div) {
		am4core.useTheme(am4themes_animated);
		var chartpie = am4core.create("chartpie", am4charts.XYChart);
		chartpie.data = div;
		var colorSet = new am4core.ColorSet();
		colorSet.list = [ "#4285F4", "#34AB53", "#F44336", "#9400D3",
				"#FBC02D", "#86592d", "#1aff1a" ].map(function(color) {
			return new am4core.color(color);
		});
		chartpie.colors = colorSet;

		var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "div";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 20;
		categoryAxis.renderer.cellStartLocation = 0.1;
		categoryAxis.renderer.cellEndLocation = 0.7;
		categoryAxis.renderer.labels.template.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.rotation = 270;

		var valueAxis_corps = chartpie.yAxes.push(new am4charts.ValueAxis());
		valueAxis_corps.min = 0;
		function createSeries_corps(field, name, stacked) {
			var series1 = chartpie.series.push(new am4charts.ColumnSeries());
			series1.dataFields.valueY = field;
			series1.dataFields.categoryX = "div";
			series1.name = name;
			series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
			series1.stacked = stacked;
			series1.columns.template.width = am4core.percent(95);

			series1.columns.template.events.on("hit", function(ev) {
				$.post("getBdesWise_Count_List?" + key + "=" + value, {
					Command : ev.target.dataItem.dataContext.div_code
				}, function(j) {
					if (j.length == 0) {
						alert("No Data Found");
					} else {
						var bde = new Array();
						for (var i = 0; i < j.length; i++) {
							if (i == 0) {
								bde.push({
									bde : j[i][0],
									count : j[i][1]
								});
							} else {
								bde.push({
									bde : j[i][0],
									count : j[i][1]
								});
							}
						}
						BdeClick_MNH(bde);
					}
				});
			}, this);
			series1.columns.template.adapter.add("fill",
					function(fill, target) {
						return chartpie.colors.getIndex(target.dataItem.index);
					});
		}
		createSeries_corps("count", "Counts", false);
	}
	//Chart 2 Div End Here

	//Chart 2 Bde Start Here
	function BdeClick_MNH(bde) {
		am4core.useTheme(am4themes_animated);
		var chartpie = am4core.create("chartpie", am4charts.XYChart);
		chartpie.data = bde;

		var colorSet = new am4core.ColorSet();
		colorSet.list = [ "#4285F4", "#34AB53", "#F44336", "#9400D3",
				"#FBC02D", "#86592d", "#1aff1a" ].map(function(color) {
			return new am4core.color(color);
		});
		chartpie.colors = colorSet;

		var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "bde";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 20;
		categoryAxis.renderer.cellStartLocation = 0.1;
		categoryAxis.renderer.cellEndLocation = 0.7;
		categoryAxis.renderer.labels.template.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.rotation = 270;

		var valueAxis_corps = chartpie.yAxes.push(new am4charts.ValueAxis());
		valueAxis_corps.min = 0;
		function createSeries_corps(field, name, stacked) {
			var series1 = chartpie.series.push(new am4charts.ColumnSeries());
			series1.dataFields.valueY = field;
			series1.dataFields.categoryX = "bde";
			series1.name = name;
			series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
			series1.stacked = stacked;
			series1.columns.template.width = am4core.percent(95);
			series1.columns.template.adapter.add("fill",
					function(fill, target) {
						return chartpie.colors.getIndex(target.dataItem.index);
					});
		}
		createSeries_corps("count", "Counts", false);
	}
	//Chart 2 Bde End Here

	//Chart 4 Principal Cause Start Here
		function chartuploadline() {
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("chartuploadline", am4charts.XYChart3D);
		chart.data = ${getTop10PCList};
		var colorSet4 = new am4core.ColorSet();
		colorSet4.list = [ "#ff66cc", "#388E3C", "#FBC02D", "#0288d1",
				"#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a",
				"#86592d", "#bf4080", "#e6e600" ].map(function(color) {
			return new am4core.color(color);
		});
		chart.colors = colorSet4;
		chart.fontSize = 10;

		// Create axes
		var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "cat";
		categoryAxis.numberFormatter.numberFormat = "#";
		categoryAxis.renderer.inversed = true;
		let label = categoryAxis.renderer.labels.template;
		label.wrap = true;
		label.maxWidth = 200;
		var  valueAxis = chart.xAxes.push(new am4charts.ValueAxis()); 
		// Create series
		var series = chart.series.push(new am4charts.ColumnSeries3D());
		series.dataFields.valueX = "count";
		series.dataFields.categoryY = "cat";
		series.columns.template.tooltipText = "{categoryY}: [bold]{valueX}[/]";
		series.columns.template.column3D.stroke = am4core.color("#fff");
		series.columns.template.column3D.strokeOpacity = 0.2;
		series.columns.template.adapter.add("fill", function(fill, target) {
			return chart.colors.getIndex(target.dataItem.index);
		});

	}
	//Chart 4 Principal Cause End Here

	//Chart 5 testing(ESM)
	function lineGraph() {
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create("lineGraph", am4charts.PieChart3D);
		chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
		chart.data = ${getChart1ExList};

		chart.innerRadius = am4core.percent(5);
		chart.depth = 1;

		var seriesEx = chart.series.push(new am4charts.PieSeries3D());
		seriesEx.dataFields.value = "count";
		seriesEx.dataFields.depthValue = "count";
		seriesEx.dataFields.category = "cat";
		seriesEx.slices.template.cornerRadius = 10;
		seriesEx.labels.template.text = "{cat} : {value.value}";
		seriesEx.slices.template.tooltipText = "{cat} : {value.value}";
		seriesEx.colors.step = 5;

		var colorSet = new am4core.ColorSet();
		colorSet.list = [ "#388E3C", "#FBC02D", "#0288d1", "#F44336",
				"#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d",
				"#bf4080", "#e6e600", "#ff66cc" ].map(function(color) {
			return new am4core.color(color);
		});
		chart.colors = colorSet;
	}
	//Chart 5 Testing(ESM)

	//Chart 1 & 2 Refresh Start Here
	function Chart1Category() {
		var d = new Date();
		var year = d.getFullYear();

		if ($("#relationship").val() == "0") {
			alert("Please select Type");
			$("#relationship").focus();
			return false;
		} else if ($("#yr").val() == "") {
			alert("Please Enter Year");
			$("#yr").focus();
			return false;
		} else if ($("#yr").val() > year) {
			alert("Future Year cannot be allowed");
			$("#yr").focus();
			return false;
		} else {
			var relationship = $("#relationship").val();
			var yr = $("#yr").val();
            var yr1 = yr.substring(2,4);
			//LIFE STYLE AND IMPORTANT DISEASES
			$.post("getCountAllImportantDis?" + key + "=" + value, {
				yr : yr
			}, function(j) {
				$("u#ihdTotal").text('0');
				$("u#hyperTotal").text('0');
				$("u#diabetesTotal").text('0');
				$("u#obesTotal").text('0');
				$("u#adsTotal").text('0');
				$("u#injuTotal").text('0');
				//$("#admTotal").text('0');
				for (var i = 0; i < j.length; i++) {
					if (j[i][0] == 'IHD') {
						$("u#ihdTotal").text(j[i][1]);
					} else if (j[i][0] == 'HTN') {
						$("u#hyperTotal").text(j[i][1]);
					} else if (j[i][0] == 'DM') {
						$("u#diabetesTotal").text(j[i][1]);
					} else if (j[i][0] == 'OBESITY') {
						$("u#obesTotal").text(j[i][1]);
					} else if (j[i][0] == 'INJURIES NEA') {
						$("u#injuTotal").text(j[i][1]);
					} else if (j[i][0] == 'ADS') {
						$("u#adsTotal").text(j[i][1]);
					} else if (j[i][0] == 'TOTAL ALL') {
						//$("#admTotal").text(j[i][1]);
					}
				}
			});

			//Army Serving
			$
					.post(
							"getChartRelationship?" + key + "=" + value,
							{
								relationship : relationship,
								yr : yr
							},
							function(j) {
								var cmdArray = new Array();
								for (var i = 0; i < j.length; i++) {
									var cmdname = "";
									cmdArray.push({
										'cat' : j[i][0],
										'count' : j[i][1]
									});
								}

								am4core.useTheme(am4themes_animated);
								var Morbidity = am4core.create("Morbidity",
										am4charts.PieChart3D);
								Morbidity.hiddenState.properties.opacity = 0;
								Morbidity.data = cmdArray;

								Morbidity.innerRadius = am4core.percent(40);
								Morbidity.depth = 5;

								var series = Morbidity.series
										.push(new am4charts.PieSeries3D());
								series.dataFields.value = "count";
								series.dataFields.depthValue = "count";
								series.dataFields.category = "cat";
								series.labels.template.text = "{cat} : {value.value}";
								series.slices.template.tooltipText = "{cat} : {value.value}";
								series.slices.template.cornerRadius = 10;

								var colorSet = new am4core.ColorSet();
								colorSet.list = [ "#388E3C", "#FBC02D",
										"#0288d1", "#F44336", "#8E24AA",
										"#1BA68D", "#ff3377", "#1aff1a",
										"#86592d", "#bf4080", "#e6e600",
										"#ff66cc" ].map(function(color) {
									return new am4core.color(color);
								});
								Morbidity.colors = colorSet;
							});
//disease
              $
					.post(
							"getdiseaseRelationship?" + key + "=" + value,
							{
								relationship : relationship,
								yr1 : yr1
							},
							function(j) {
							
								var cmdArray = new Array();
								for (var i = 0; i < j.length; i++) {
									var cmdname = "";
									cmdArray.push({
										'dis' : j[i][0],
										'disease_name' : j[i][1]
									});
								}

								
								
								am4core.useTheme(am4themes_animated);
								var chart = am4core.create("notifiable_diseases", am4charts.XYChart);
								chart.scrollbarX = new am4core.Scrollbar();
								
								chart.data = cmdArray;
	
								var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
								categoryAxis.dataFields.category = "disease_name";
								categoryAxis.renderer.grid.template.location = 0;
								categoryAxis.renderer.minGridDistance = 30;
								categoryAxis.renderer.labels.template.horizontalCenter = "right";
								categoryAxis.renderer.labels.template.verticalCenter = "middle";
								categoryAxis.renderer.labels.template.rotation = 270;
								categoryAxis.tooltip.disabled = true;
								categoryAxis.renderer.minHeight = 110;

								let label = categoryAxis.renderer.labels.template;
								label.wrap = true;
								label.maxWidth = 130;
								
								var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
								valueAxis.renderer.minWidth = 50;

								

								var series = chart.series.push(new am4charts.ColumnSeries());
								series.sequencedInterpolation = true;
								series.dataFields.valueY = "dis";
								series.dataFields.categoryX = "disease_name";
								series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
								series.columns.template.strokeWidth = 0;

								series.tooltip.pointerOrientation = "vertical";

								series.columns.template.column.cornerRadiusTopLeft = 10;
								series.columns.template.column.cornerRadiusTopRight = 10;
								series.columns.template.column.fillOpacity = 0.8;

								var hoverState = series.columns.template.column.states.create("hover");
								hoverState.properties.cornerRadiusTopLeft = 0;
								hoverState.properties.cornerRadiusTopRight = 0;
								hoverState.properties.fillOpacity = 1;

								series.columns.template.adapter.add("fill", function(fill, target) {
									return chart.colors.getIndex(target.dataItem.index);
								});
								chart.cursor = new am4charts.XYCursor();
							});
			
			
			
			//Army ESM
			$.post("getChartESMRelationship?" + key + "=" + value, {
				relationship : relationship,
				yr : yr
			}, function(j) {
				var cmdArray1 = new Array();
				for (var i = 0; i < j.length; i++) {
					var cmdname = "";
					cmdArray1.push({
						'cat' : j[i][0],
						'count' : j[i][1]
					});
				}
				am4core.useTheme(am4themes_animated);
				var chart = am4core.create("lineGraph", am4charts.PieChart3D);
				chart.hiddenState.properties.opacity = 0;
				chart.data = cmdArray1;

				chart.innerRadius = am4core.percent(5);
				chart.depth = 1;

				var seriesEx = chart.series.push(new am4charts.PieSeries3D());
				seriesEx.dataFields.value = "count";
				seriesEx.dataFields.depthValue = "count";
				seriesEx.dataFields.category = "cat";
				seriesEx.slices.template.cornerRadius = 10;
				seriesEx.labels.template.text = "{cat} : {value.value}";
				seriesEx.colors.step = 5;

				var colorSet = new am4core.ColorSet();
				colorSet.list = [ "#388E3C", "#FBC02D", "#0288d1", "#F44336",
						"#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d",
						"#bf4080", "#e6e600", "#ff66cc" ].map(function(color) {
					return new am4core.color(color);
				});
				chart.colors = colorSet;
			});
			////////////////////////////////////////command

			$
					.post(
							"getChartCOMMANDRelationship?" + key + "=" + value,
							{
								yr : yr
							},
							function(j) {
								var cmdArray2 = new Array();
								for (var i = 0; i < j.length; i++) {
									var cmdname = "";
									cmdArray2.push({
										'command' : j[i][0],
										'count' : j[i][1]
									});
								}
								am4core.useTheme(am4themes_animated);
								var chartpie_cmd = am4core.create("chartpie",
										am4charts.XYChart);

								chartpie_cmd.data = cmdArray2;
								var colorSet = new am4core.ColorSet();
								colorSet.list = [ "#4285F4", "#34AB53",
										"#F44336", "#9400D3", "#FBC02D",
										"#86592d", "#1aff1a" ].map(function(
										color) {
									return new am4core.color(color);
								});
								chartpie_cmd.colors = colorSet;

								var categoryAxis_cmd = chartpie_cmd.xAxes
										.push(new am4charts.CategoryAxis());
								categoryAxis_cmd.dataFields.category = "command";
								categoryAxis_cmd.renderer.grid.template.location = 0;
								categoryAxis_cmd.renderer.minGridDistance = 20;
								categoryAxis_cmd.renderer.cellStartLocation = 0.1;
								categoryAxis_cmd.renderer.cellEndLocation = 0.7;
								categoryAxis_cmd.renderer.labels.template.verticalCenter = "middle";
								categoryAxis_cmd.renderer.labels.template.rotation = 270;

								var valueAxis_cmd1 = chartpie_cmd.yAxes
										.push(new am4charts.ValueAxis());
								valueAxis_cmd1.min = 0;
								function createSeries_cmd1(field, name, stacked) {
									var series_cmd1 = chartpie_cmd.series
											.push(new am4charts.ColumnSeries());
									series_cmd1.dataFields.valueY = field;
									series_cmd1.dataFields.categoryX = "command";
									series_cmd1.name = name;
									series_cmd1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
									series_cmd1.stacked = stacked;
									series_cmd1.columns.template.width = am4core
											.percent(95);

									series_cmd1.columns.template.events
											.on(
													"hit",
													function(ev) {
														$
																.post(
																		"getCorpsWise_Count_List1?"
																				+ key
																				+ "="
																				+ value,
																		{
																			command : ev.target.dataItem.dataContext.command,
																			relationship : relationship,
																			yr : yr
																		},
																		function(
																				j) {
																			if (j.length == 0) {
																				alert("No Data Found");
																			} else {
																				var corps = new Array();
																				for (var i = 0; i < j.length; i++) {
																					if (i == 0) {
																						corps
																								.push({
																									command : j[i][0],
																									count : j[i][1]
																								});
																					} else {
																						corps
																								.push({
																									command : j[i][0],
																									count : j[i][1]
																								});
																					}
																				}
																				CommandClick_MNH1(corps);
																			}
																		});

													}, this);
									series_cmd1.columns.template.adapter
											.add(
													"fill",
													function(fill, target) {
														return chartpie_cmd.colors
																.getIndex(target.dataItem.index);
													});
								}
								createSeries_cmd1("count", "Counts", false);
							});

			//Chart 2 corps Start Here
			function CommandClick_MNH1(corps) {
				am4core.useTheme(am4themes_animated);
				var chartpie = am4core.create("chartpie", am4charts.XYChart);

				chartpie.data = corps;

				var colorSet = new am4core.ColorSet();
				colorSet.list = [ "#4285F4", "#34AB53", "#F44336", "#9400D3",
						"#FBC02D", "#86592d", "#1aff1a" ].map(function(color) {
					return new am4core.color(color);
				});
				chartpie.colors = colorSet;

				var categoryAxis = chartpie.xAxes
						.push(new am4charts.CategoryAxis());
				categoryAxis.dataFields.category = "command";
				categoryAxis.renderer.grid.template.location = 0;
				categoryAxis.renderer.minGridDistance = 20;
				categoryAxis.renderer.cellStartLocation = 0.1;
				categoryAxis.renderer.cellEndLocation = 0.7;
				categoryAxis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.rotation = 270;

				var valueAxis_corps = chartpie.yAxes
						.push(new am4charts.ValueAxis());
				valueAxis_corps.min = 0;
				function createSeries_corps(field, name, stacked) {
					var series1 = chartpie.series
							.push(new am4charts.ColumnSeries());
					series1.dataFields.valueY = field;
					series1.dataFields.categoryX = "command";
					series1.name = name;
					series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
					series1.stacked = stacked;
					series1.columns.template.width = am4core.percent(95);

					series1.columns.template.events
							.on(
									"hit",
									function(ev) {
										$
												.post(
														"getDivsWise_Count_List1?"
																+ key + "="
																+ value,
														{
															command : ev.target.dataItem.dataContext.command,
															relationship : relationship,
															yr : yr
														},
														function(j) {
															if (j.length == 0) {
																alert("No Data Found");
															} else {
																var div = new Array();
																for (var i = 0; i < j.length; i++) {
																	if (i == 0) {
																		div
																				.push({
																					command : j[i][0],
																					count : j[i][1]
																				});
																	} else {
																		div
																				.push({
																					command : j[i][0],
																					count : j[i][1]
																				});
																	}
																}
																DivClick_MNH1(div);
															}
														});

									}, this);

					series1.columns.template.adapter.add("fill", function(fill,
							target) {
						return chartpie.colors.getIndex(target.dataItem.index);
					});
				}
				createSeries_corps("count", "Counts", false);
			}
			//Chart 2 corps End Here
			//Chart 2 Div Start Here
			function DivClick_MNH1(div) {
				am4core.useTheme(am4themes_animated);
				var chartpie = am4core.create("chartpie", am4charts.XYChart);

				chartpie.data = div;

				var colorSet = new am4core.ColorSet();
				colorSet.list = [ "#4285F4", "#34AB53", "#F44336", "#9400D3",
						"#FBC02D", "#86592d", "#1aff1a" ].map(function(color) {
					return new am4core.color(color);
				});
				chartpie.colors = colorSet;

				var categoryAxis = chartpie.xAxes
						.push(new am4charts.CategoryAxis());
				categoryAxis.dataFields.category = "command";
				categoryAxis.renderer.grid.template.location = 0;
				categoryAxis.renderer.minGridDistance = 20;
				categoryAxis.renderer.cellStartLocation = 0.1;
				categoryAxis.renderer.cellEndLocation = 0.7;
				categoryAxis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.rotation = 270;

				var valueAxis_corps = chartpie.yAxes
						.push(new am4charts.ValueAxis());
				valueAxis_corps.min = 0;
				function createSeries_corps(field, name, stacked) {
					var series1 = chartpie.series
							.push(new am4charts.ColumnSeries());
					series1.dataFields.valueY = field;
					series1.dataFields.categoryX = "command";
					series1.name = name;
					series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
					series1.stacked = stacked;
					series1.columns.template.width = am4core.percent(95);

					series1.columns.template.events
							.on(
									"hit",
									function(ev) {
										$
												.post(
														"getBdesWise_Count_List1?"
																+ key + "="
																+ value,
														{
															command : ev.target.dataItem.dataContext.command,
															relationship : relationship,
															yr : yr
														},
														function(j) {
															if (j.length == 0) {
																alert("No Data Found");
															} else {
																var bde = new Array();
																for (var i = 0; i < j.length; i++) {
																	if (i == 0) {
																		bde
																				.push({
																					command : j[i][0],
																					count : j[i][1]
																				});
																	} else {
																		bde
																				.push({
																					command : j[i][0],
																					count : j[i][1]
																				});
																	}
																}
																BdeClick_MNH1(bde);
															}
														});

									}, this);

					series1.columns.template.adapter.add("fill", function(fill,
							target) {
						return chartpie.colors.getIndex(target.dataItem.index);
					});
				}
				createSeries_corps("count", "Counts", false);
			}
			//Chart 2 Div End Here

			//Chart 2 Bde Start Here
			function BdeClick_MNH1(bde) {
				am4core.useTheme(am4themes_animated);
				var chartpie = am4core.create("chartpie", am4charts.XYChart);

				chartpie.data = bde;

				var colorSet = new am4core.ColorSet();
				colorSet.list = [ "#4285F4", "#34AB53", "#F44336", "#9400D3",
						"#FBC02D", "#86592d", "#1aff1a" ].map(function(color) {
					return new am4core.color(color);
				});
				chartpie.colors = colorSet;

				var categoryAxis = chartpie.xAxes
						.push(new am4charts.CategoryAxis());
				categoryAxis.dataFields.category = "command";
				categoryAxis.renderer.grid.template.location = 0;
				categoryAxis.renderer.minGridDistance = 20;
				categoryAxis.renderer.cellStartLocation = 0.1;
				categoryAxis.renderer.cellEndLocation = 0.7;
				categoryAxis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.rotation = 270;

				var valueAxis_corps = chartpie.yAxes
						.push(new am4charts.ValueAxis());
				valueAxis_corps.min = 0;
				function createSeries_corps(field, name, stacked) {
					var series1 = chartpie.series
							.push(new am4charts.ColumnSeries());
					series1.dataFields.valueY = field;
					series1.dataFields.categoryX = "command";
					series1.name = name;
					series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
					series1.stacked = stacked;
					series1.columns.template.width = am4core.percent(95);
					series1.columns.template.adapter.add("fill", function(fill,
							target) {
						return chartpie.colors.getIndex(target.dataItem.index);
					});
				}
				createSeries_corps("count", "Counts", false);
			}

			/////////////////////////////

			//// Top 10 Principal Cause
				$.post("getChartTopPrincipal?" + key + "=" + value,{relationship : relationship,yr : yr},function(j) {
				var cmdArray3 = new Array();
				for (var i = j.length; i > 0 ; i--) {
					var cmdname = "";
					var parseCommand = (j[(i-1)][0]).replace("-"," ");

					cmdArray3.push({'Command' : parseCommand,'count' : j[(i-1)][1]});
				}
				am4core.useTheme(am4themes_animated);
				var chartuploadline = am4core.create("chartuploadline", am4charts.XYChart3D);
				chartuploadline.data = cmdArray3;
				var colorSet4 = new am4core.ColorSet();
				colorSet4.list = [ "#388E3C", "#FBC02D","#0288d1", "#F44336", "#8E24AA","#1BA68D", "#ff3377", "#1aff1a","#86592d", "#bf4080", "#e6e600","#ff66cc" ].map(function(color) {
					return new am4core.color(color);
				});
				chartuploadline.colors = colorSet4;
				chartuploadline.fontSize = 10;
				var categoryAxis_PC = chartuploadline.yAxes.push(new am4charts.CategoryAxis());
				categoryAxis_PC.dataFields.category = "Command";
				 let label = categoryAxis_PC.renderer.labels.template;
			      label.wrap = true;
			      label.maxWidth = 200;
			      
				var valueAxis_PC = chartuploadline.xAxes.push(new am4charts.ValueAxis());
				
			      
				var series1_PC = chartuploadline.series.push(new am4charts.ColumnSeries3D());
				series1_PC.dataFields.valueX = "count";
				series1_PC.dataFields.valueY = "Command";
				series1_PC.dataFields.categoryY = "Command";
				series1_PC.columns.template.propertyFields.fill = "color";
				series1_PC.columns.template.tooltipText = "{categoryY}: [bold]{valueX}[/]"

				series1_PC.columns.template.adapter.add("fill",function(fill, target) {
					return chartuploadline.colors.getIndex(target.dataItem.index);
				});
			});
			//// Top 10
			//Life style year wise
			chartpie();

		}
	}

	function Chart1Category2() {
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();
		if (from_date != "") {
			$.post("getLMCChart1List?" + key + "=" + value, {
				from_date : from_date,
				to_date : to_date
			}, function(j) {
				var cmdArray = new Array();
				for (var i = 0; i < j.length; i++) {
					cmdArray.push({
						'Command' : j[i][0],
						'off' : j[i][1],
						'jco_or' : j[i][2]
					});
				}
				if (j.length == 0) {
					alert("No Data Found");
				}
				chartuploadline_lmc1(cmdArray);
			});
		} else {
			//var getBED_STATE_ARMY_List = ${getBED_STATE_ARMY_List};
			//chartuploadline_lmc1(getBED_STATE_ARMY_List);
		}
	}
	//Chart 1 & 2 Refresh End Here
</script>

<script>
	var d = new Date();
	var year = d.getFullYear();

	var popupWindow = null
	function openihdDetails() {
		var yr = $("#yr").val();
		if (yr == "") {
			alert("Please Enter Year");
			$("#yr").focus();
			return false;
		}
		if (yr > year) {
			alert("Future Year cannot be allowed");
			$("#yr").focus();
			return false;
		}
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"getihddesh?type=ihdDetails&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=yes");
		} else {
			popupWindow = window
					.open(
							"getihddesh?type=ihdDetails&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=yes");
		}
	}
	function opengethypertenstionDetails() {
		var yr = $("#yr").val();
		if (yr == "") {
			alert("Please Enter Year");
			$("#yr").focus();
			return false;
		}
		if (yr > year) {
			alert("Future Year cannot be allowed");
			$("#yr").focus();
			return false;
		}
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"gethypertenstion?type=hypertenstion&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		} else {
			popupWindow = window
					.open(
							"gethypertenstion?type=hypertenstion&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
	}
	function opengetgetdiabetesDetails() {
		var yr = $("#yr").val();
		if (yr == "") {
			alert("Please Enter Year");
			$("#yr").focus();
			return false;
		}
		if (yr > year) {
			alert("Future Year cannot be allowed");
			$("#yr").focus();
			return false;
		}
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"getdiabetes?type=diabetes&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		} else {
			popupWindow = window
					.open(
							"getdiabetes?type=diabetes&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
	}
	function opengetgetinjuriesDetails() {
		var yr = $("#yr").val();
		if (yr == "") {
			alert("Please Enter Year");
			$("#yr").focus();
			return false;
		}
		if (yr > year) {
			alert("Future Year cannot be allowed");
			$("#yr").focus();
			return false;
		}
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"getinjuries?type=injuries&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		} else {
			popupWindow = window
					.open(
							"getinjuries?type=injuries&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
	}
	function opengetobesityDetails() {
		var yr = $("#yr").val();
		if (yr == "") {
			alert("Please Enter Year");
			$("#yr").focus();
			return false;
		}
		if (yr > year) {
			alert("Future Year cannot be allowed");
			$("#yr").focus();
			return false;
		}
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"getobesity?type=obesity&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		} else {
			popupWindow = window
					.open(
							"getobesity?type=obesity&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
	}
	function opengetADSDetails() {
		var yr = $("#yr").val();
		if (yr == "") {
			alert("Please Enter Year");
			$("#yr").focus();
			return false;
		}
		if (yr > year) {
			alert("Future Year cannot be allowed");
			$("#yr").focus();
			return false;
		}
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"getADS?type=ADS&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		} else {
			popupWindow = window
					.open(
							"getADS?type=ADS&yr=" + yr,
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
	}

	function openViewMoreReport() {
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"getdataViewMore",
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1020,height=600,fullscreen=no");
		} else
			popupWindow = window
					.open(
							"getdataViewMore",
							"_blank",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1020,height=600,fullscreen=no");
	}
</script>

<script>
am5.ready(function() {
	
	
	//rk vs med cat
	var root = am5.Root.new("suicideCases");


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	]);


	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
	  panX: false,
	  panY: false,
	  wheelX: "panX",
	  wheelY: "zoomX",
	  layout: root.verticalLayout
	}));
	console.log(chart);
	
		chart.set("scrollbarX", am5.Scrollbar.new(root, {
		  orientation: "horizontal"
	}));



	var data =${Getsuicide_caseslist};
	//alert(data);

	var xRenderer = am5xy.AxisRendererX.new(root, {});
	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
	  categoryField: "datas",
	  renderer: xRenderer,
	  tooltip: am5.Tooltip.new(root, {})
	}));

	xAxis.data.setAll(data);

	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
	  min: 0,
	  renderer: am5xy.AxisRendererY.new(root, {})
	}));


	// Add legend
	// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
	var legend = chart.children.push(am5.Legend.new(root, {
	  centerX: am5.p50,
	  x: am5.p50
	}));


	// Add series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	function makeSeries(name, fieldName, total1) {
	  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
	    name: name,
	    stacked: true,
	    xAxis: xAxis,
	    yAxis: yAxis,
	    valueYField: fieldName,
	    categoryXField: "datas"
	  }));

	  series.columns.template.setAll({
	    tooltipText: "{name}, {categoryX}: {valueY}",
	    tooltipY: am5.percent(10)
	  });
	  series.data.setAll(data);

	  // Make stuff animate on load
	  // https://www.amcharts.com/docs/v5/concepts/animations/
	  series.appear();

	  series.bullets.push(function () {
	    return am5.Bullet.new(root, {
	      sprite: am5.Label.new(root, {
	       // text: "{valueY}",
	        fill: root.interfaceColors.get("alternativeText"),
	        centerY: am5.p50,
	        centerX: am5.p50,
	        //populateText: true
	      })
	    });
	  });

	  legend.data.push(series);
	  
	  
	  if (total1) {
		    series.bullets.push(function () {
		      var total1Label = am5.Label.new(root, {
		        text: "{valueY}",
		        fill: root.interfaceColors.get("text"),
		        centerY: am5.p100,
		        centerX: am5.p50,
		        populateText: true,
		        textAlign: "center"
		      });
		      
		      total1Label.adapters.add("text", function(text, target) {
		        var dataContext = target.dataItem.dataContext;
		        var val = Math.abs(dataContext.datas1 + dataContext.datas2 );
		        return ""+val;
		      });
		      
		      return am5.Bullet.new(root, {
		        locationX: 0.5,
		        locationY: 1,
		        sprite: total1Label
		      });
		    });
		  }
	}

	makeSeries("Male", "datas1",false);
	makeSeries("female", "datas2",true);
});

</script>