<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/mnh/mnhDashboard/mnhDashboardJS.js"></script>
<link rel="stylesheet" href="js/mnh/mnhDashboard/mnhDashboardCSS.css"> 

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
  height: 40%;
}
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
    display:block;
    max-height:300px;
    overflow-y:scroll;
    width:100%; 
    scrollbar-width: thin;
}
.table thead, .table tbody tr {
    display:table;
    width:100%;
    table-layout:fixed;
}
.table thead{
  width: calc(100% - 8px);
}
/* D3 */
 #BlockDescTblList1
  {        	
      border-collapse: collapse;
      -moz-border-radius: 10px;
      -webkit-border-radius: 10px;
      box-shadow: 0px 0px 22px #888;
      -moz-box-shadow: 0px 0px 22px #888;
      -webkit-box-shadow: 0px 0px 22px #888;
  }
  #BlockDescTblList1 td
  {
      padding: -1px;
      border: 1px solid black;
  }
  #BlockDescTblList1 th
  {
  	text-align:center;
      padding: 7px 2px;
      border: 1px solid #000;
      background-color:#9c27b0;   
      color:#fff;
  }
  #BlockDescTblList1 tr:nth-child(odd)   
  {
      background-color: white;
  } 
  #BlockDescTblList1 tr:nth-child(even)   
  {
      background-color: #cedeef;
  }      
     
  .highlight
  {
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

.pagination > .active > a, .pagination > .active > a:focus, .pagination > .active > a:hover, .pagination > .active > span, .pagination > .active > span:focus, .pagination > .active > span:hover
 {
        z-index: 2;
        color: #000;
        cursor: default;
        background-color:#66cc99; /* #2191c0; */ 
        border: 2px solid #00664d; /* 1px solid #4297d7; */ 
        font-weight:bold;
}

table.dataTable.display tbody tr.odd > .sorting_1, table.dataTable.order-column.stripe tbody tr.odd > .sorting_1 {
    background-color: white;
}
table.dataTable.display tbody tr.even > .sorting_1, table.dataTable.order-column.stripe tbody tr.even > .sorting_1 {
    background-color: #cedeef;
}

.dataTables_wrapper .dataTables_filter {
    float: right;
    text-align: right;
    padding-left: 70%;
}

.info-box{
	width:150px;
}


.col-dash {
    -webkit-box-flex: 0;
    -ms-flex: 0 0 13% !important;
    flex: 0 0 13% !important;
    max-width: 13% !important;
}

.table tr th:first-child{
}
</style>

	<div class="animated fadeIn" >
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					<div id="DASHBOARD_tabs">
					<h5 class="trans_heading"><b><u>MEDICAL AND HEALTH DASHBOARD</u></b></h5><br>
						<div class="row">
						    <div class="col-md-12 row form-group">  
						        <div class="col-md-2" style="text-align: right;">
                 			         <label for="text-input" class=" form-control-label">Principal Cause</label>
               			        </div>
               			        <div class="col-md-6" style="text-align: right;">
               			             <select id="principal_cause" name="principal_cause" class="form-control-sm form-control">
											 <option value="0" >--Select--</option>
										     <c:forEach var="item" items="${getmnhPrincipalList}" varStatus="num" >
											      <option value="${item}" >${item}</option>
										     </c:forEach>
						             </select>
               			        </div>
						    </div>
						    
						    <div class="col-md-12 row form-group" style="margin-top: -10px;">
							     <div class="col-md-2" style="text-align: right;">
			                 		  <label for="text-input" class=" form-control-label">Command</label>
			               		 </div>
			               		 <div class="col-md-6">
			               			   <select name="command" id="command" class="form-control-sm form-control">
											<c:if test="${r_1[0][1] != 'COMMAND'}">
												<option value="ALL">-- All Command --</option>
											</c:if>
											<c:if test="${not empty ml_1[0]}">
												<c:set var="data" value="${ml_1[0]}" />
												<c:set var="datap" value="${fn:split(data,',')}" />
												<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
													<c:set var="dataf" value="${fn:split(datap[j],':')}" />
													<option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
												</c:forEach>
											</c:if>
									    </select>
								  </div>
						     </div>
						     
						     <div class="col-md-12 row form-group" style="margin-top: -10px;">
						           <div class="col-md-2" style="text-align: right;">
			                 		    <label for="text-input" class=" form-control-label">Year</label>
			               		   </div>
			               		   
			               		   <div class="col-md-1">
			               		        <input type="text" id="yr" name="yr" class="form-control-sm form-control" autocomplete="off">
			               		   </div>
						     </div>
						     
						     <div class="col-md-12 row form-group" align="center">
							        <div class="col-md-4">
							        </div>
							        <div class="col-md-2">
							             <button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="Chart1Category();">Refresh Graph</button>
							        </div>
							 </div>
							 
							 <div class="col-md-12 row form-group">
							        <div class="col-md-6">
							             <b style="text-decoration: underline;">MORBIDITY CATEGORY WISE OF ARMY SERVING</b>
										 <div id="Morbidity" style="width: 80%; height: 250px;"></div>
							        </div>
							        
							        <div class="col-md-6">
							             <b style="text-decoration: underline;">COMMAND WISE TOTAL ADMISSION OF ARMY SERVING</b>
										 <div id="chartpie" style="width: 80%; height: 250px;"></div>
							        </div>
							 </div>
							 
							 <div class="col-md-12 row form-group">
							        <div class="col-md-6">
							             <b style="text-decoration: underline;">MONTH WISE TOTAL ADMISSION OF ARMY SERVING</b>
										 <div id="chartdivline" style="width: 80%; height: 250px;"></div>
							        </div>
							        
							        <div class="col-md-6">
							             <b style="text-decoration: underline;">OPD WORK-LOAD OF ARMY</b>
										 <div id="chartuploadline" style="width: 80%; height: 250px;"></div>
							        </div>
							 </div>
							
								<div class="card-body card-block">

							<!-- T1 -->
							<div  class="col-md-6 state_tables">
							<div class="row" id="BlkDesc">
              				<table class="table no-margin table-striped  table-hover  table-bordered">
              				<thead style="color: red; text-align:center; ">
											<tr>
												<td colspan="2"><h4>Block Description Wise Status</h4></td>
											</tr>
											</thead>
                				<thead style="background-color: #9c27b0; color: white;">
                  					<tr style="font-size: 15px;">
                  			  			   <!-- <th>Sr. No.</th> -->
												<th style="text-align: center;"> Block Description</th>
												<th style="text-align: center;"> Total Admissions</th>
                  					</tr>
                				</thead>
                        			<tbody>
                          				<tr class="listheading nr-medium nr-blue box" style="font-size: 15px">
                        					<c:forEach var="item" items="${BlockDescTblList}" varStatus="num" >
												<tr>
													
													<td>${item[0]}</td>
													<td>${item[1]}</td>
												</tr>
											</c:forEach>  
                          				</tr>
                        			</tbody>
								</table>
					</div>
					</div>

						<div id="BlkDesc1" class="col-md-6 state_tables">
              				<table class="table no-margin table-striped  table-hover  table-bordered">
              				<thead style="color: red; text-align:center; ">
											<tr>
												<td colspan="2"><h4>Hospital Data Status</h4></td>
											</tr>
											</thead>
                				<thead style="background-color: #9c27b0; color: white;">
                  					<tr style="font-size: 15px;">
                  			  			   <!-- <th>Sr. No.</th> -->
												<th style="text-align: center;"> Hospital Name</th>
												<th style="text-align: center;"> Total Admissions</th>
                  					</tr>
                				</thead>
                        			<tbody>
                          				<tr class="listheading nr-medium nr-blue box" style="font-size: 15px">
                        					<c:forEach var="item" items="${UnitInfoTblList}" varStatus="num" >
												<tr>
													
													 <%-- <td>${item[0]}</td> --%>
													 <td><b><a href='#' onclick='openUnitDetailReport();' style='color: blue'><u> ${item[0]} </u></a></b></td>
													<td>${item[1]}</td>
													
												</tr>
											</c:forEach>  
                          				</tr>
                        			</tbody>
								</table>
					</div>
					</div>
					 <!-- T1 -->
            				 
			<div class="card-body card-block">
	
				<div class="col-md-6 state_tables" id="divPrint" style="display:block;">					
					 <div id="divShow" style="display: block;"></div> 
					 <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block; z-index:1111">
						<span id="ip"></span>					 
							<!-- <table id="UnitMovReport" class="no-margin table-striped table-hover table-bordered report_print"> -->
              				<table id="UnitMovReport" class="table no-margin table-striped  table-hover  table-bordered" border="1">
							<thead style="color: red; text-align:center; ">
											<tr>
												<td colspan="2"><h4>Block Description Wise Status</h4></td>
											</tr>
											</thead>
								<thead style="background-color: #9c27b0; color: white; text-align:center;">
									<tr>
									<th style="text-align: center;">Block Description</th>
									<th style="text-align: center;">Total Admissions</th>										
									</tr>
										</thead> 
							  <tbody id="UnitMovReportTbody" >
							  
							</tbody>
					  </table>
					</div>	
			
				</div> 
           				
					 <!-- T1 -->
					 
					 <!-- T2 -->
	
				<div class="col-md-6 state_tables" id="divPrint" style="display:block;">					
					 <div id="divShow1" style="display: block;"></div> 
					 <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block; z-index:1111">
						<span id="ip"></span>					 
							<!-- <table id="UnitMovReport1" class="no-margin table-striped table-hover table-bordered report_print"> -->
							<table id="UnitMovReport1" class="table no-margin table-striped  table-hover  table-bordered" border="1">
							<thead style="color: red; text-align:center; ">
												<tr>
												<td colspan="2"><h4>Hospital Data Status</h4></td>
											</tr>
											</thead>
								<thead style="background-color: #9c27b0; color: white; text-align:center;">
									<tr>
									<th style="text-align: center; width: 48%;">Hospital Name</th>
									<th style="text-align: center;">Total Admissions</th>										
									</tr>
																
								</thead>
								
							  <tbody id="UnitMovReportTbody1" >
									
							</tbody>
					  </table>
					</div>	
			
				</div> 
		
		</div>
   				
					 <!-- T2 -->
							</div>
							
								
							</div>			
					</div>
				</div>
			</div>
			
				<div class="card-body card-block">	
								 <div class="col-md-12">
									<div class="col-md-2 col-dash">
										<div class="info-box bg-deep-purple">
											<div class="content">
											  <h5>IHD</h5>
												<div class="number count-to" data-from="0" data-to="125" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#illegalityMiningCasesView" onclick="openihdDetails();">
														<label><u id="ihdTotal"></u></label> 
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2 col-dash">
										<div class="info-box bg-deep-pink">
											<div class="content">
												<h5>HYPERTENSION</h5>
												<div class="number count-to" data-from="0" data-to="257" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengethypertenstionDetails();"> 
														<label><u id="hyperTotal"></u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2 col-dash">
										<div class="info-box bg-green">
											<div class="content">
												<h5>DIABETES</h5>
												<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengetgetdiabetesDetails();"> 
														<label><u id="diabetesTotal"></u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2 col-dash">
										<div class="info-box bg-red">
											<div class="content">
												<h5>OBESITY</h5>
												<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengetobesityDetails();"> 
														<label><u id="obesTotal"></u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2 col-dash">
										<div class="info-box bg-deep-pink">
											<div class="content">
												<h5>ADS</h5>
												<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengetADSDetails();"> 
														<label><u id="adsTotal"></u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2 col-dash">
										<div class="info-box bg-purple">
											<div class="content">
												<h5>INJURIES NEA</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengetgetinjuriesDetails();"> 
														<label><u id="injuTotal"></u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2 col-dash">
										<div class="info-box bg-indigo">
											<div class="content">
												<h5>TOTAL ADM</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView"> 
														<label id="admTotal"></label>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>	
								<!-- sandeep tables end here -->
		</div>
<script>
$(document).ready(function () {
	
	$("u#ihdTotal").text('${getihdList[0]}');
	$("u#hyperTotal").text('${gethyperList[0]}');
	$("u#diabetesTotal").text('${getdmList[0]}');
	$("u#obesTotal").text('${getobesList[0]}');
	$("u#adsTotal").text('${getADSList[0]}');
	$("u#injuTotal").text('${getinjurisList[0]}');
	$("#admTotal").text('${getadmList[0]}');
	
	Morbidity();
	chartpie();
	lineGraph();
	chartuploadline();
});


//Chart 1 Start Here
function Morbidity(){
	am4core.useTheme(am4themes_animated);
	var Morbidity = am4core.create("Morbidity", am4charts.PieChart3D);
	Morbidity.hiddenState.properties.opacity = 0; // this creates initial fade-in
	Morbidity.data =${getChart1List};
	Morbidity.innerRadius = am4core.percent(40);
	Morbidity.depth = 5;
	//Morbidity.legend = new am4charts.Legend();
	
	var series = Morbidity.series.push(new am4charts.PieSeries3D());
	series.dataFields.value = "count";
	series.dataFields.depthValue = "count";
	series.dataFields.category = "cat";
	series.slices.template.cornerRadius = 5;
	series.colors.step = 3;
}

//Chart 1 End Here */

//Chart 2 Start Here
function chartpie(){
	am4core.useTheme(am4themes_animated);
	var chartpie = am4core.create("chartpie", am4charts.XYChart3D);
	chartpie.data = ${getChart2List};
	var colorSet2 = new am4core.ColorSet();
	colorSet2.list = ["#4285F4" ,"#34AB53", "FBBC05", "EA4335", "#E37400", "#DB4437", "#9400D3"].map(function(color) {
	return new am4core.color(color);
	});
	chartpie.colors = colorSet2; 
	var categoryAxis = chartpie.yAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "Command";
	categoryAxis.numberFormatter.numberFormat = "#";
	categoryAxis.renderer.inversed = true;
	
	var  valueAxis = chartpie.xAxes.push(new am4charts.ValueAxis()); 
	var series1 = chartpie.series.push(new am4charts.ColumnSeries3D());
	series1.dataFields.valueX = "count";
	series1.dataFields.valueY = "Command";
	series1.dataFields.categoryY = "Command";
	series1.columns.template.propertyFields.fill = "color";
	series1.columns.template.tooltipText = "{categoryY}: [bold]{valueX}[/]"
	series1.columns.template.adapter.add("fill", function(fill, target) {
		return chartpie.colors.getIndex(target.dataItem.index);
	});
}
//Chart 2 End Here
function lineGraph(){
var chart = am4core.create("chartdivline", am4charts.XYChart);

// Increase contrast by taking evey second color
chart.colors.step = 2;

// Add data
chart.data = ${getChart3List};
chart.dateFormatter.inputDateFormat =  "MM-YYYY";

var colorSet2 = new am4core.ColorSet();
colorSet2.list = ["#9400D3" ,"#E37400", "#34AB53", "EA4335", "#E37400", "#DB4437", "#9400D3"].map(function(color) {
return new am4core.color(color);
});
chart.colors = colorSet2; 

// Create axes
var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
dateAxis.renderer.minGridDistance = 50;

// Create series
function createAxisAndSeries(field, name, opposite, bullet) {
  var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
  
  var series = chart.series.push(new am4charts.LineSeries());
  series.dataFields.valueY = field;
  series.dataFields.dateX = "month";
  series.strokeWidth = 2;
  series.yAxis = valueAxis;
  series.name = name;
  series.tooltipText = "{name}: [bold]{valueY}[/]";
  series.tensionX = 0.8;
  
  var interfaceColors = new am4core.InterfaceColorSet();
  
  switch(bullet) {
    case "triangle":
      var bullet = series.bullets.push(new am4charts.Bullet());
      bullet.width = 12;
      bullet.height = 12;
      bullet.horizontalCenter = "middle";
      bullet.verticalCenter = "middle";
      
      var triangle = bullet.createChild(am4core.Triangle);
      triangle.stroke = interfaceColors.getFor("background");
      triangle.strokeWidth = 2;
      triangle.direction = "top";
      triangle.width = 12;
      triangle.height = 12;
      break;
    case "rectangle":
      var bullet = series.bullets.push(new am4charts.Bullet());
      bullet.width = 10;
      bullet.height = 10;
      bullet.horizontalCenter = "middle";
      bullet.verticalCenter = "middle";
      
      var rectangle = bullet.createChild(am4core.Rectangle);
      rectangle.stroke = interfaceColors.getFor("background");
      rectangle.strokeWidth = 2;
      rectangle.width = 10;
      rectangle.height = 10;
      break;
    default:
      var bullet = series.bullets.push(new am4charts.CircleBullet());
      bullet.circle.stroke = interfaceColors.getFor("background");
      bullet.circle.strokeWidth = 2;
      break;
  }
  
  valueAxis.renderer.line.strokeOpacity = 1;
  valueAxis.renderer.line.strokeWidth = 2;
  valueAxis.renderer.line.stroke = series.stroke;
  valueAxis.renderer.labels.template.fill = series.stroke;
  valueAxis.renderer.opposite = opposite;
  valueAxis.renderer.grid.template.disabled = true;
}
createAxisAndSeries("value","2017");
createAxisAndSeries("value1","2018");
createAxisAndSeries("value2","2019");

// Add legend
chart.legend = new am4charts.Legend();

// Add cursor
chart.cursor = new am4charts.XYCursor();


}
//Chart 3 line Start Here

/* function lineGraph(){
	var chart = am4core.create("chartdivline", am4charts.XYChart);
	chart.paddingRight = 20;
	var data =  ${getChart3List};
	chart.data = data;
	chart.dateFormatter.inputDateFormat =  "MM-YYYY";
	var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
	dateAxis.renderer.minGridDistance = 50;
	var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	valueAxis.tooltip.disabled = true;
	// Create series
	function createSeries(field, name) {
		var series = chart.series.push(new am4charts.StepLineSeries());
		series.dataFields.dateX = "year";
		series.dataFields.valueY = field;
	  series.name = name;
		series.tooltipText = "{name}: [bold]{valueY}[/]";
		series.strokeWidth = 3;
	}
	createSeries("value", "2019");
	createSeries("value1", "2018");
	createSeries("value2", "2017");
	// Add legend
	chart.legend = new am4charts.Legend();
	chart.cursor = new am4charts.XYCursor();
	chart.cursor.xAxis = dateAxis;
	chart.cursor.fullWidthLineX = true;
	chart.cursor.lineX.strokeWidth = 0;
	chart.cursor.lineX.fill = chart.colors.getIndex(3);
	chart.cursor.lineX.fillOpacity = 0.1;
}
 */

//Chart 3 line End Here

//Chart 4 Upload Start here
function chartuploadline(){
	am4core.useTheme(am4themes_animated);
	var chartuploadline = am4core.create("chartuploadline", am4charts.XYChart);
	chartuploadline.hiddenState.properties.opacity = 0; 
	chartuploadline.data = ${getChart4List};
	chartuploadline.colors.step = 2;
	chartuploadline.padding(30, 30, 10, 30);
	chartuploadline.legend = new am4charts.Legend();
	
	var colorSet4 = new am4core.ColorSet();
	colorSet4.list = ["#4285F4", "#DB4437", "#FBBC05"].map(function(color) {
	  return new am4core.color(color);
	});
	chartuploadline.colors = colorSet4;

	var categoryAxischartdiv4 = chartuploadline.xAxes.push(new am4charts.CategoryAxis());
	categoryAxischartdiv4.dataFields.category = "Month";
	categoryAxischartdiv4.renderer.grid.template.location = 0;
	categoryAxischartdiv4.renderer.labels.template.verticalCenter ="middle";
	categoryAxischartdiv4.renderer.labels.template.rotation = 310;

	var valueAxischartdiv4 = chartuploadline.yAxes.push(new am4charts.ValueAxis());
	valueAxischartdiv4.min = 0;
	valueAxischartdiv4.max = 100;
	valueAxischartdiv4.strictMinMax = true;
	valueAxischartdiv4.calculateTotals = true;
	valueAxischartdiv4.renderer.minWidth = 50;

	var series1chartdiv4 = chartuploadline.series.push(new am4charts.ColumnSeries());
	series1chartdiv4.columns.template.width = am4core.percent(80);
	series1chartdiv4.columns.template.tooltipText =
	  "{name}: {valueY}";
	series1chartdiv4.name = "OFFR";
	series1chartdiv4.dataFields.categoryX = "Month";
	series1chartdiv4.dataFields.valueY = "count1";
	series1chartdiv4.dataFields.valueYShow = "totalPercent";
	series1chartdiv4.dataItems.template.locations.categoryX = 0.5;
	series1chartdiv4.stacked = true;
	series1chartdiv4.tooltip.pointerOrientation = "vertical";

	var bullet1chartdiv4 = series1chartdiv4.bullets.push(new am4charts.LabelBullet());
	bullet1chartdiv4.interactionsEnabled = false;
	bullet1chartdiv4.label.text = "{valueY}";
	bullet1chartdiv4.label.fill = am4core.color("#ffffff");
	bullet1chartdiv4.locationY = 0.5;

	var series2chartdiv4 = chartuploadline.series.push(new am4charts.ColumnSeries());
	series2chartdiv4.columns.template.width = am4core.percent(80);
	series2chartdiv4.columns.template.tooltipText = "{name}: {valueY}";
	series2chartdiv4.name = "JCOs";
	series2chartdiv4.dataFields.categoryX = "Month";
	series2chartdiv4.dataFields.valueY = "count2";
	series2chartdiv4.dataFields.valueYShow = "totalPercent";
	series2chartdiv4.dataItems.template.locations.categoryX = 0.5;
	series2chartdiv4.stacked = true;
	series2chartdiv4.tooltip.pointerOrientation = "vertical";

	var bullet2chartdiv4 = series2chartdiv4.bullets.push(new am4charts.LabelBullet());
	bullet2chartdiv4.interactionsEnabled = false;
	bullet2chartdiv4.label.text = "{valueY}";
	bullet2chartdiv4.locationY = 0.5;
	bullet2chartdiv4.label.fill = am4core.color("#ffffff");

	var series3chartdiv4 = chartuploadline.series.push(new am4charts.ColumnSeries());
	series3chartdiv4.columns.template.width = am4core.percent(80);
	series3chartdiv4.columns.template.tooltipText = "{name}: {valueY}";
	series3chartdiv4.name = "EX-SERV";
	series3chartdiv4.dataFields.categoryX = "Month";
	series3chartdiv4.dataFields.valueY = "count3";
	series3chartdiv4.dataFields.valueYShow = "totalPercent";
	series3chartdiv4.dataItems.template.locations.categoryX = 0.5;
	series3chartdiv4.stacked = true;
	series3chartdiv4.tooltip.pointerOrientation = "vertical";

	var bullet3chartdiv4 = series3chartdiv4.bullets.push(new am4charts.LabelBullet());
	bullet3chartdiv4.interactionsEnabled = false;
	bullet3chartdiv4.label.text = "{valueY}";
	bullet3chartdiv4.locationY = 0.5;
	bullet3chartdiv4.label.fill = am4core.color("#ffffff");
	//chartuploadline.scrollbarX = new am4core.Scrollbar();	
}
//Chart 4 Upload End here
</script>
	
<script type="text/javascript">
$(document).ready(function() {
	ParseDateColumn();
	function ParseDateColumn() {
		var d = new Date();
		document.getElementById("yr").value=d.getFullYear();
	}
	$("div#divPrint").hide();
});
</script>
<script>
function Chart1Category()
{
	var d = new Date();
	var year = d.getFullYear();
	
	if($("#principal_cause").val() == "")
	{
		alert("Please select Principal Cause for Chart");
		$("#principal_cause").focus();
		return false;
	}
	else if($("#yr").val() == "")
	{
		alert("Please Enter Year");
		$("#yr").focus();
		return false;
	}
	else if($("#yr").val() > year) {
		alert("Future Year cannot be allowed");
		$("#yr").focus();
		return false;
	}
	else{
		var principal_cause=$("#principal_cause").val();
		var yr=$("#yr").val(); 
		var command=$("#command").val();
		
	 $.ajaxSetup({
	    async: false
	});	 
	
    $.getJSON("getChart1WiseCategory",{principal_cause: principal_cause, yr: yr, command: command}, function(j) {
		var cmdArray = new Array();		
		for(var i=0;i<j.length;i++){	  			  				
			var cmdname="";
			if(j[i].cat == "OFFICER"){ cmdname="OFF"};
			if(j[i].cat == "MNS"){ cmdname="MNS"};
			if(j[i].cat == "JCO"){ cmdname="JCO"};
			if(j[i].cat == "OR"){ cmdname="OR"};
			cmdArray.push({'cat': cmdname,'count': j[i].count});  				 			
		}	    
		if(cmdArray.length > 0){
			am4core.useTheme(am4themes_animated);
			var Morbidity = am4core.create("Morbidity", am4charts.PieChart3D);
			Morbidity.hiddenState.properties.opacity = 0; // this creates initial fade-in
			Morbidity.data =cmdArray;
			Morbidity.innerRadius = am4core.percent(40);
			Morbidity.depth = 5;

			var series = Morbidity.series.push(new am4charts.PieSeries3D());
			series.dataFields.value = "count";
			series.dataFields.depthValue = "count";
			series.dataFields.category = "cat";
			series.slices.template.cornerRadius = 5;
			series.colors.step = 3;
		}
    });  
    
    //Chart 2
   
   	 	$.getJSON("getChart2WiseCategory",{principal_cause: principal_cause, yr: yr, command: command}, function(j) {
    	 	var cmdArray1 = new Array();		
 			for(var i=0;i<j.length;i++){	  			  				
				var cmdname1="";
				if(j[i].command == "HQ SOUTHERN COMMAND"){ cmdname1="SC"};
  				if(j[i].command == "HQ EASTERN COMMAND"){ cmdname1="EC"};
  				if(j[i].command == "HQ WESTERN COMMAND"){ cmdname1="WC"};
  				if(j[i].command == "HQ CENTRAL COMMAND"){ cmdname1="CC"};
  				if(j[i].command == "HQ NORTHERN COMMAND"){ cmdname1="NC"};
  				if(j[i].command == "HQ ARMY TRG COMMAND (ARTRAC)"){ cmdname1="ARTRAC"};
  				if(j[i].command == "HQ ANDAMAN & NICOBAR COMMAND (ANC)"){ cmdname1="ANC"};
  				if(j[i].command == "HQ SOUTH WESTERN COMMAND"){ cmdname1="SWC"};
  				if(j[i].command == "HQ UNITED NATION"){ cmdname1="UN"};
  				if(j[i].command == "MIN OF DEFENCE"){ cmdname1="MOD"};
  				if(j[i].command == "HQ STRATEGIC FORCES COMMAND (SFC)"){ cmdname1="SFC"}; 
				cmdArray1.push({'command': cmdname1,'count': j[i].count});  
			}
	
			if(cmdArray1.length > 0){
			am4core.useTheme(am4themes_animated);
			var chartpie = am4core.create("chartpie", am4charts.XYChart3D);
			chartpie.data = cmdArray1;

			var colorSet2 = new am4core.ColorSet();
			colorSet2.list = ["#4285F4" ,"#34AB53", "FBBC05", "EA4335", "#E37400", "#DB4437", "#9400D3"].map(function(color) {				
			return new am4core.color(color);
			});
			chartpie.colors = colorSet2;

			var categoryAxis = chartpie.yAxes.push(new am4charts.CategoryAxis());
			categoryAxis.dataFields.category = "command";
			categoryAxis.numberFormatter.numberFormat = "#";
			categoryAxis.renderer.inversed = true;

			var  valueAxis = chartpie.xAxes.push(new am4charts.ValueAxis()); 
			var series1 = chartpie.series.push(new am4charts.ColumnSeries3D());
			series1.dataFields.valueX = "count";
			series1.dataFields.categoryY = "command";
			series1.name = "Count";
			series1.columns.template.propertyFields.fill = "color";
			series1.columns.template.tooltipText = "{categoryY}: [bold]{valueX}[/]"
			series1.columns.template.column3D.strokeOpacity = 0.2;
			series1.columns.template.adapter.add("fill", function(fill, target) {
				  return chartpie.colors.getIndex(target.dataItem.index);
				});
			}
    	}); 
    
    //Chart 3
    
    lineGraph();
    
    $.getJSON("getihdCount",{yr: yr}, function(j) {
    	$("u#ihdTotal").text(j[0]);
    });
    
    $.getJSON("gethypersum",{yr: yr}, function(j) {
    	$("u#hyperTotal").text(j[0]);
    });
    
    $.getJSON("getdiabetescount",{yr: yr}, function(j) {
    	alert
    	$("u#diabetesTotal").text(j[0]);
    });
    
    $.getJSON("getobescount",{yr: yr}, function(j) {
    	alert
    	$("u#obesTotal").text(j[0]);
    });
    
    $.getJSON("getadscount",{yr: yr}, function(j) {
    	alert
    	$("u#adsTotal").text(j[0]);
    });
    
    $.getJSON("getinjuriescount",{yr: yr}, function(j) {
    	alert
    	$("u#injuTotal").text(j[0]);
    });
    
    $.getJSON("getadmcount",{yr: yr}, function(j) {
    	alert
    	$("#admTotal").text(j[0]);
    });
    
    
}
//Tables start here
//alert("Table 1");
$("div#divPrint").show();
$("div#BlkDesc").hide();
$("div#BlkDesc1").hide();

//T1 table
  $.getJSON("BlockDescTblList1",{principal_cause: principal_cause, yr: yr, command: command}, function(j) {
	    	$("#UnitMovReportTbody").empty();
	    	if(j.length>0)
	    	{
	    		var row="", m=0;
				for(var i=0;i<j.length;i++){
					m=i+1;

					row += "<tr>";
					//row += "<th style='text-align:center;'>"+m+"</th>";
					row += "<th>"+j[i].b_desc+"</th>";
					row += "<th>"+j[i].count+"</th>";
					
					row += "</tr>";
				}
				$("#UnitMovReportTbody").append(row);
				
				//$("#UnitMovReport").DataTable();				
				//$("div#divwatermark").val('').addClass('watermarked');		
//				watermarkreport(); 
	    	}
	    		
	    });	 
  
//T2 table
   $.getJSON("BlockDescTblList2",{principal_cause: principal_cause, yr: yr, command: command}, function(j) {
	    	$("#UnitMovReportTbody1").empty();
	    	if(j.length>0)
	    	{
	    		var row="";
				for(var i=0;i<j.length;i++){
					row += "<tr>";
					//row += "<th style='text-align:center;'>"+m+"</th>";
					//row += "<th>"+j[i].uname+"</th>";
					row += "<th><a href='#' onclick='openUnitDetailReport();' ><u><b>" + j[i].uname + "</b></u></th>"
					//row += "<th><b><a href='#' onclick='openAMvcrUpdateReport();' style='color: blue'><u>" j[i].uname "</u></a></b></th>"
					row += "<th>"+j[i].count+"</th>";
					
					row += "</tr>";
				}
				$("#UnitMovReportTbody1").append(row);
				
				//$("#UnitMovReport1").DataTable();				
	    	}
	    		
	    });	  
	    
   /* "<input type='hidden' name='principal_cause' id='principal_cause'>" */		
//Table end here
}

var popupWindow = null
function openihdDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getihddesh?type=ihdDetails", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("getihddesh?type=ihdDetails", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}
var popupWindow = null
function opengethypertenstionDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("gethypertenstion?type=hypertenstion", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("gethypertenstion?type=hypertenstion", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}
var popupWindow = null
function opengetgetdiabetesDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getdiabetes?type=diabetes", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("getdiabetes?type=diabetes", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}
var popupWindow = null
function opengetgetinjuriesDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getinjuries?type=injuries", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("getinjuries?type=injuries", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}
var popupWindow = null
function opengetobesityDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getobesity?type=obesity", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("getobesity?type=obesity", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}
var popupWindow = null
function opengetADSDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getADS?type=ADS", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("getADS?type=ADS", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}
var popupWindow = null
function opendatauploadinfo(val) {
	var url = "getdatauploadinformation?month="+val;
	popupWindow = window.open(url, "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}
var popupWindow = null
function openUnitDetailReport() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getdataunitinformation?type=dataunitinfo", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("getdataunitinformation?type=dataunitinfo", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}

</script>