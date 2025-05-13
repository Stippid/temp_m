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

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/mnh_css.css"> 

<link rel="stylesheet" href="js/JS_CSS/jquery.dataTables.min.css"> 

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
#chartuploadline_lmc{
  width: 100%;
  height: 60%;
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
				</div>
			</div>
		</div>
	</div>
</div>
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
							 </div>
							<div class="card-body card-block">	
								<!-- Life Style Diseases Start Here -->
									<h6 class="trans_heading"><b><u>LIFE STYLE AND IMPORTANT DISEASES</u></b></h5><br>
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
						<!-- Life Style Diseases End Here -->	
	<!-- Graph Start Here -->
	<div class="animated fadeIn">
		<div class="col-md-12">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					<div id="DASHBOARD_tabs">
						<div class="row">
							
							 <div class="col-md-12 row form-group">
							 		<div class="col-md-6">
							             <b style="text-decoration: underline;">HOSPITAL ADMISSION OF ARMY (SERVING/DEPENDENT)</b>
										 <div id="Morbidity" style="width: 100%; height: 250px;"></div>
							        </div>
							        <div class="col-md-6">
		             					<b style="text-decoration: underline;">HOSPITAL ADMISSION OF ARMY (EX-SERVICEMEN/DEPENDENT)</b>
					 					 <div id="lineGraph" style="width: 100%; height: 250px;"></div>
		        					</div>
							 </div>

						</div>
					</div>
				</div>			
			</div>
		</div>
		</div>
		<div class="col-md-12">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					<div class="col-md-12">
						<div class="col-md-6" align="center"><img alt="Refresh" src="../login/referesh.ico" onclick="chartpie();"></div>
						<div class="col-md-6" align="center"></div>
					</div>
				<div class="col-md-12 row form-group">
		         <div class="col-md-6">
				   	<b style="text-decoration: underline;">COMMAND WISE TOTAL ADMISSION OF ARMY (SERVING)</b>
				 	<div id="chartpie" style="width: 100%; height: 250px;"></div>
				</div>
		        <div class="col-md-6">
		             <b style="text-decoration: underline;">TOP 10 PRINCIPAL CAUSE OF ARMY (SERVING)</b>
					 <div id="chartuploadline" style="width: 100%; height: 250px;"></div>
					  <div align="right"><button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="openViewMoreReport();">View More</button></div>
		        </div>
		        </div>
			    </div>
			</div>
		</div>
		</div>
		
		<!-- ////lmc start -->
		<div class="col-md-12">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					   <div class="col-md-12 row from-group">
							 		<div class="col-md-2" style="text-align: right;">
			                 		    <label for="text-input" class=" form-control-label">From Date</label>
			               		    </div>
			               		    <div class="col-md-2">
			               		        <input type="date" id="from_date" name="from_date" class="form-control-sm form-control" autocomplete="off">
			               		    </div>
			               		   
			               		   <div class="col-md-2" style="text-align: right;">
			                 		  <label for="text-input" class=" form-control-label">To Date</label>
			               		   </div>
			               		   <div class="col-md-2">
			               		        <input type="date" id="to_date" name="to_date" class="form-control-sm form-control" autocomplete="off">
			               		    </div>
							        <div class="col-md-2">
							             <button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="Chart1Category2();">Refresh Graph</button>
							        </div>
							 </div>
				<div class="col-md-12 row form-group">
		         
		        <div class="col-md-6">
		             <b style="text-decoration: underline;">LOW MEDICAL CATEGORY OF ARMY (SERVING)</b>
					 <div id="chartuploadline_lmc" style="width: 100%; height: 250px;"></div>
		        </div>
		        
		        <div class="col-md-6">
		             <b style="text-decoration: underline;">BED STATE OF ARMY (SERVING)</b>
					 <div id="chartuploadline_lmc1" style="width: 100%; height: 250px;"></div>
					  <!-- <div align="right"><button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="openViewMoreReport();">View More</button></div> -->
		        </div>
		        
		        </div>
		     
			    </div>
			</div>
		</div>
		</div>
		<!-- ///lmc end here -->
		
		<!-- Data Status Month wise -->
				<div  class="col-md-12 state_tables">
							<div class="row" id="BlkDesc">
              				<table class="table no-margin table-striped  table-hover  table-bordered">
              				<thead style="color: red; text-align:center; ">
											<tr>
												<td colspan="2"><h4>DATA STATUS FOR CURRENT YEAR</h4></td>
											</tr>
											</thead>
                				<thead style="background-color: #9c27b0; color: white;">
                  					<tr style="font-size: 15px;">
                  			  			    <th style="font-size: 14px;text-align: center;" width="5%">Ser No</th>	
                                            <th style="font-size: 14px;text-align: center;" width="25%">HOSPITAL</th>									
								            <th style="font-size: 14px;text-align: center;" width="5%">JAN</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">FEB</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">MAR</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">APR</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">MAY</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">JUN</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">JUL</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">AUG</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">SEP</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">OCT</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">NOV</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">DEC</th>
								            <th style="font-size: 14px;text-align: center;" width="5%">TOTAL</th>
                  					</tr>
                				</thead>
                        			<tbody>
                          				<tr class="listheading nr-medium nr-blue box" style="font-size: 15px">
                        					<c:forEach var="item" items="${hospital_datastatus}" varStatus="num" >
												<tr>
												<th style="font-size: 14px;text-align: center;" width="5%">${num.index+1}</th>
												<th style="font-size: 14px;text-align: left;" width="25%">${item[0]}</th>
												<c:if test="${item[1]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[1]}</th>
												</c:if>
												<c:if test="${item[1]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[1]}</th>
												</c:if>
												<c:if test="${item[2]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[2]}</th>
												</c:if>
												<c:if test="${item[2]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[2]}</th>
												</c:if>
												
												<c:if test="${item[3]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[3]}</th>
												</c:if>
												<c:if test="${item[3]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[3]}</th>
												</c:if>
												
												<c:if test="${item[4]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[4]}</th>
												</c:if>
												<c:if test="${item[4]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[4]}</th>
												</c:if>
												
												<c:if test="${item[5]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[5]}</th>
												</c:if>
												<c:if test="${item[5]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[5]}</th>
												</c:if>
												
												<c:if test="${item[6]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[6]}</th>
												</c:if>
												<c:if test="${item[6]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[6]}</th>
												</c:if>
												
												<c:if test="${item[7]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[7]}</th>
												</c:if>
												<c:if test="${item[7]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[7]}</th>
												</c:if>
												
												<c:if test="${item[8]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[8]}</th>
												</c:if>
												<c:if test="${item[8]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[8]}</th>
												</c:if>
												
												<c:if test="${item[9]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[9]}</th>
												</c:if>
												<c:if test="${item[9]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[9]}</th>
												</c:if>
												
												<c:if test="${item[10]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[10]}</th>
												</c:if>
												<c:if test="${item[10]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[10]}</th>
												</c:if>
												
												<c:if test="${item[11]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[11]}</th>
												</c:if>
												<c:if test="${item[11]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[11]}</th>
												</c:if>
												
												<c:if test="${item[12]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[12]}</th>
												</c:if>
												 <c:if test="${item[12]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[12]}</th>
												</c:if> 
												
												<c:if test="${item[13]!='0'}">
												<th style="font-size: 14px;text-align: center;" width="5%">${item[13]}</th>
												</c:if>
												<c:if test="${item[13]=='0'}">
												<th style="font-size: 14px;text-align: center; color: red" width="5%">${item[13]}</th>
												</c:if>
												
												</tr>
												</c:forEach>
                          				</tr>
                        			</tbody>
								</table>
					</div>
					</div>
	</div>
		
<script type="text/javascript">
$(document).ready(function () {

	ParseDateColumn();
	function ParseDateColumn() {
		var d = new Date();
		document.getElementById("yr").value=d.getFullYear();
	} 
	$("div#divPrint").hide();
		
	$("u#ihdTotal").text('${getihdList[0]}');
	$("u#hyperTotal").text('${gethyperList[0]}');
	$("u#diabetesTotal").text('${getdmList[0]}');
	$("u#obesTotal").text('${getobesList[0]}');
	$("u#adsTotal").text('${getADSList[0]}');
	$("u#injuTotal").text('${getinjurisList[0]}');
	$("#admTotal").text('${getadmList[0]}');
	
	Morbidity();
	chartpie();
	//MorbidityEX();
	var getlowmadicalchart = ${getlowmadicalchart};
	chartuploadline_lmc(getlowmadicalchart);
	var getLMCChart1List = ${getLMCChart1List};
	chartuploadline_lmc1(getLMCChart1List);
	chartuploadline();
	lineGraph();
	
}); 

//Chart 1 Serving Start Here
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
	series.labels.template.text = "{category} : {value.value}";
	series.slices.template.tooltipText = "{category} : {value.value}";
	series.slices.template.cornerRadius = 10;
	series.colors.step = 5;
}
 function chartuploadline_lmc(lmc){
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("chartuploadline_lmc", am4charts.XYChart);
	//chart.numberFormatter.numberFormat = "#.#'%'";
	chart.data = lmc;
	
	var colorSet4 = new am4core.ColorSet();
	colorSet4.list = ["#4285F4" ,"#FBC02D", "#F44336", "#34AB53","#9400D3", "#86592d", "#1aff1a"].map(function(color) {
	  return new am4core.color(color);
	});
	chart.colors = colorSet4;
	var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "command";
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.minGridDistance = 30;
	
	let label = categoryAxis.renderer.labels.template;
	label.wrap = true;
	label.maxWidth = 70;

	var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	valueAxis.title.text = "COMMAND";
	valueAxis.title.fontWeight = 800;
	var series = chart.series.push(new am4charts.ColumnSeries());
	series.name = "OFF_TOTAL";
	series.dataFields.valueY = "off_total";
	series.dataFields.categoryX = "command";
	series.clustered = false;
	series.columns.template.width = am4core.percent(80);
	series.tooltipText = "off_total([bold]{valueY}[/]): ";

	var series2 = chart.series.push(new am4charts.ColumnSeries());
	series2.name = "JCO";
	series2.dataFields.valueY = "jco";
	series2.dataFields.categoryX = "command";
	series2.clustered = false;
	series2.columns.template.width = am4core.percent(60);
	series2.tooltipText = "jco([bold]{valueY}[/]): ";

	var series3 = chart.series.push(new am4charts.ColumnSeries());
	series3.name = "ORT";
	series3.dataFields.valueY = "ort";
	series3.dataFields.categoryX = "command";
	series3.clustered = false;
	series3.columns.template.width = am4core.percent(40);
	series3.tooltipText = "ort([bold]{valueY}[/]): ";

	var series4 = chart.series.push(new am4charts.ColumnSeries());
	series4.name = "TOTAL";
	series4.dataFields.valueY = "total";
	series4.dataFields.categoryX = "command";
	series4.clustered = false;
	series4.columns.template.width = am4core.percent(20);
	series4.tooltipText = "total([bold]{valueY}[/]): ";

	chart.cursor = new am4charts.XYCursor();
	chart.cursor.lineX.disabled = true;
	chart.cursor.lineY.disabled = true;
	//chart.legend = new am4charts.Legend();
	//chart.scrollbarX = new am4core.Scrollbar();
} 
//LMC Chart 2
function chartuploadline_lmc1(lmc1){
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("chartuploadline_lmc1", am4charts.XYChart);
	chart.data = lmc1;
	
	var colorSet4 = new am4core.ColorSet();
	colorSet4.list = ["#cc3300","#e6ac00","#86b300"].map(function(color) {
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
	
	function createSeries(field, name) {
	  var series = chart.series.push(new am4charts.ColumnSeries());
	  series.dataFields.valueX = field;
	  series.dataFields.categoryY = "Command";
	  series.name = name;
	  series.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
	  series.columns.template.height = am4core.percent(100);
	  series.sequencedInterpolation = true;

	  var categoryLabel = series.bullets.push(new am4charts.LabelBullet());
	  categoryLabel.label.horizontalCenter = "right";
	  categoryLabel.label.dx = -10;
	  categoryLabel.label.fill = am4core.color("#fff");
	  categoryLabel.label.hideOversized = false;
	  categoryLabel.label.truncate = false;
	}

	createSeries("off", "OFF");
	createSeries("jco_or", "JCO_OR");	
	//chart.legend = new am4charts.Legend();
	
	//chart.scrollbarY = new am4core.Scrollbar();
}

//Chart 2 Command wise Start Here

 function chartpie(){
	//alert("Chart new");
 	am4core.useTheme(am4themes_animated);
	var chartpie = am4core.create("chartpie", am4charts.XYChart);
	
	chartpie.data = ${getChart51List};
	
	var colorSet = new am4core.ColorSet();
		colorSet.list = ["#4285F4" ,"#34AB53", "#F44336", "#9400D3", "#FBC02D", "#86592d", "#1aff1a"].map(function(color) {
		return new am4core.color(color);
	});
	chartpie.colors = colorSet;
	
	// Create axes
	var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "Command";
	//categoryAxis.title.text = "Command";
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.minGridDistance = 20;
	categoryAxis.renderer.cellStartLocation = 0.1;
	categoryAxis.renderer.cellEndLocation = 0.7;
	categoryAxis.renderer.labels.template.verticalCenter ="middle";
	categoryAxis.renderer.labels.template.rotation = 270;

	var  valueAxis_cmd = chartpie.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cmd.min = 0;
	function createSeries_cmd(field, name, stacked) {
	  	var series1 = chartpie.series.push(new am4charts.ColumnSeries());
	  	series1.dataFields.valueY = field;
	  	series1.dataFields.categoryX = "Command";
	  	series1.name = name;
	  	series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series1.stacked = stacked;
	  	series1.columns.template.width = am4core.percent(95);
	  	
	  	series1.columns.template.events.on("hit",function(ev){
	  	 	$.getJSON("getCorpsWise_Count_List",{Command : ev.target.dataItem.dataContext.Command}, function(j) {
	  	 		if(j.length == 0){
	  	 			alert("No Data Found");
	  	 		}else{
		  			var corps = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					corps.push({Command:j[i][0],count:j[i][1]});	
		  				}else{
		  					corps.push(/*  ',' + */ {Command:j[i][0],count:j[i][1]} );
		  				}
		  			}
		  			CommandClick_MNH(corps);
		  		}
	  		});  
	  		
	  	},this);
	  	series1.columns.template.adapter.add("fill", function(fill, target) {
			return chartpie.colors.getIndex(target.dataItem.index);
		});	  	 	
	}
	createSeries_cmd("count", "Counts", false);
}	
//Chart 2 End Here

//Chart 2 corps Start Here
function CommandClick_MNH(corps){
	am4core.useTheme(am4themes_animated);
	var chartpie = am4core.create("chartpie", am4charts.XYChart);
	
	chartpie.data = corps;
	
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#4285F4" ,"#34AB53", "#F44336", "#9400D3", "#FBC02D", "#86592d", "#1aff1a"].map(function(color) {
	return new am4core.color(color);
	});
	chartpie.colors = colorSet;
	
	// Create axes
	var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "Command";
	//categoryAxis.title.text = "Command";
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.minGridDistance = 20;
	categoryAxis.renderer.cellStartLocation = 0.1;
	categoryAxis.renderer.cellEndLocation = 0.7;
	categoryAxis.renderer.labels.template.verticalCenter ="middle";
	categoryAxis.renderer.labels.template.rotation = 270;

	var  valueAxis_corps = chartpie.yAxes.push(new am4charts.ValueAxis());
	valueAxis_corps.min = 0;
	function createSeries_corps(field, name, stacked) {
	  	var series1 = chartpie.series.push(new am4charts.ColumnSeries());
	  	series1.dataFields.valueY = field;
	  	series1.dataFields.categoryX = "Command";
	  	series1.name = name;
	  	series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series1.stacked = stacked;
	  	series1.columns.template.width = am4core.percent(95);

	  	series1.columns.template.events.on("hit",function(ev){
	  	 	$.getJSON("getDivsWise_Count_List",{Command : ev.target.dataItem.dataContext.Command}, function(j) {
	  	 		if(j.length == 0){
	  	 			alert("No Data Found");
	  	 		}else{
		  			var div = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					div.push({Command:j[i][0],count:j[i][1]});	
		  				}else{
		  					div.push(/*  ',' + */ {Command:j[i][0],count:j[i][1]} );
		  				}
		  			}
		  			DivClick_MNH(div);
		  		}
	  		});  
	  		
	  	},this);
	  	
	  	
	  	series1.columns.template.adapter.add("fill", function(fill, target) {
			return chartpie.colors.getIndex(target.dataItem.index);
		});	
	}
	createSeries_corps("count", "Counts", false);
	//chartpie.legend = new am4charts.Legend(); 
} 
//Chart 2 corps End Here

//Chart 2 Div Start Here
function DivClick_MNH(div){
	am4core.useTheme(am4themes_animated);
	var chartpie = am4core.create("chartpie", am4charts.XYChart);
	
	chartpie.data = div;
	
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#4285F4" ,"#34AB53", "#F44336", "#9400D3", "#FBC02D", "#86592d", "#1aff1a"].map(function(color) {
	return new am4core.color(color);
	});
	chartpie.colors = colorSet;
	
	// Create axes
	var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "Command";
	//categoryAxis.title.text = "Command";
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.minGridDistance = 20;
	categoryAxis.renderer.cellStartLocation = 0.1;
	categoryAxis.renderer.cellEndLocation = 0.7;
	categoryAxis.renderer.labels.template.verticalCenter ="middle";
	categoryAxis.renderer.labels.template.rotation = 270;

	var  valueAxis_corps = chartpie.yAxes.push(new am4charts.ValueAxis());
	valueAxis_corps.min = 0;
	function createSeries_corps(field, name, stacked) {
	  	var series1 = chartpie.series.push(new am4charts.ColumnSeries());
	  	series1.dataFields.valueY = field;
	  	series1.dataFields.categoryX = "Command";
	  	series1.name = name;
	  	series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series1.stacked = stacked;
	  	series1.columns.template.width = am4core.percent(95);
	  	
		series1.columns.template.events.on("hit",function(ev){
	  	 	$.getJSON("getBdesWise_Count_List",{Command : ev.target.dataItem.dataContext.Command}, function(j) {
	  	 		if(j.length == 0){
	  	 			alert("No Data Found");
	  	 		}else{
		  			var bde = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					bde.push({Command:j[i][0],count:j[i][1]});	
		  				}else{
		  					bde.push(/*  ',' + */ {Command:j[i][0],count:j[i][1]} );
		  				}
		  			}
		  			BdeClick_MNH(bde);
		  		}
	  		});  
	  		
	  	},this);
		
	  	series1.columns.template.adapter.add("fill", function(fill, target) {
			return chartpie.colors.getIndex(target.dataItem.index);
		});	
	}
	createSeries_corps("count", "Counts", false);
	//chartpie.legend = new am4charts.Legend(); 
} 
//Chart 2 Div End Here

//Chart 2 Bde Start Here
function BdeClick_MNH(bde){
	am4core.useTheme(am4themes_animated);
	var chartpie = am4core.create("chartpie", am4charts.XYChart);
	
	chartpie.data = bde;
	
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#4285F4" ,"#34AB53", "#F44336", "#9400D3", "#FBC02D", "#86592d", "#1aff1a"].map(function(color) {
	return new am4core.color(color);
	});
	chartpie.colors = colorSet;
	
	// Create axes
	var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "Command";
	//categoryAxis.title.text = "Command";
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.minGridDistance = 20;
	categoryAxis.renderer.cellStartLocation = 0.1;
	categoryAxis.renderer.cellEndLocation = 0.7;
	categoryAxis.renderer.labels.template.verticalCenter ="middle";
	categoryAxis.renderer.labels.template.rotation = 270;

	var  valueAxis_corps = chartpie.yAxes.push(new am4charts.ValueAxis());
	valueAxis_corps.min = 0;
	function createSeries_corps(field, name, stacked) {
	  	var series1 = chartpie.series.push(new am4charts.ColumnSeries());
	  	series1.dataFields.valueY = field;
	  	series1.dataFields.categoryX = "Command";
	  	series1.name = name;
	  	series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series1.stacked = stacked;
	  	series1.columns.template.width = am4core.percent(95);
	  	series1.columns.template.adapter.add("fill", function(fill, target) {
			return chartpie.colors.getIndex(target.dataItem.index);
		});	
	}
	createSeries_corps("count", "Counts", false);
	//chartpie.legend = new am4charts.Legend(); 
} 
//Chart 2 Bde End Here

//Chart 4 Principal Cause Start Here
function chartuploadline(){
	am4core.useTheme(am4themes_animated);
	var chartuploadline = am4core.create("chartuploadline", am4charts.XYChart3D);
	chartuploadline.data = ${getChart4PCList};
	var colorSet4 = new am4core.ColorSet();
	colorSet4.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	chartuploadline.colors = colorSet4; 
	chartuploadline.fontSize=10;
	
	var categoryAxis_PC = chartuploadline.yAxes.push(new am4charts.CategoryAxis());
	categoryAxis_PC.dataFields.category = "cat";
	//categoryAxis_PC.numberFormatter.numberFormat = "#";
	//categoryAxis_PC.renderer.inversed = true;
	
	var valueAxis_PC = chartuploadline.xAxes.push(new am4charts.ValueAxis()); 
	var series1_PC = chartuploadline.series.push(new am4charts.ColumnSeries3D());
	series1_PC.dataFields.valueX = "count";
	series1_PC.dataFields.valueY = "cat";
	series1_PC.dataFields.categoryY = "cat";
	series1_PC.columns.template.propertyFields.fill = "color";
	series1_PC.columns.template.tooltipText = "{categoryY}: [bold]{valueX}[/]"
	
	series1_PC.columns.template.adapter.add("fill", function(fill, target) {
		return chartuploadline.colors.getIndex(target.dataItem.index);
	});
}
//Chart 4 Principal Cause End Here

//Chart 5 testing(ESM)
function lineGraph() {
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("lineGraph", am4charts.PieChart3D);
	chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
	chart.data =${getChart1ExList};
	
	chart.innerRadius = am4core.percent(5);
	chart.depth = 1;
	//Morbidity.legend = new am4charts.Legend();
	
	var seriesEx = chart.series.push(new am4charts.PieSeries3D());
	seriesEx.dataFields.value = "count";
	seriesEx.dataFields.depthValue = "count";
	seriesEx.dataFields.category = "cat";
	seriesEx.slices.template.cornerRadius = 10;
	seriesEx.labels.template.text = "{cat} : {value.value}";
	//seriesEX.slices.template.tooltipText = "{cat} : {value.value}";
	seriesEx.colors.step = 5;
	
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	chart.colors = colorSet;
}
//Chart 5 Testing(ESM)

//Chart 1 & 2 Refresh Start Here
function Chart1Category()
{
	var d = new Date();
	var year = d.getFullYear();
	
	if($("#relationship").val() == "0")
	{
		alert("Please select Type");
		$("#relationship").focus();
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
		var relationship=$("#relationship").val();
		var yr=$("#yr").val(); 

		//Army Serving
		
		$.getJSON("getChartRelationship",{relationship: relationship, yr: yr}, function(j) {
			var cmdArray = new Array();		
			for(var i=0;i<j.length;i++){	  			  				
				var cmdname="";
				cmdArray.push({'cat': j[i][0],'count': j[i][1]});  				 			
			}	    
			//if(cmdArray.length > 0){
				am4core.useTheme(am4themes_animated);
				
				var Morbidity = am4core.create("Morbidity", am4charts.PieChart3D);
				Morbidity.hiddenState.properties.opacity = 0; 
				Morbidity.data =cmdArray;
			
				Morbidity.innerRadius = am4core.percent(40);
				Morbidity.depth = 5;
				//Morbidity.legend = new am4charts.Legend();
				
				var series = Morbidity.series.push(new am4charts.PieSeries3D());
				series.dataFields.value = "count";
				series.dataFields.depthValue = "count";
				series.dataFields.category = "cat";
				series.labels.template.text = "{cat} : {value.value}";
				series.slices.template.tooltipText = "{cat} : {value.value}";
				series.slices.template.cornerRadius = 10;
				series.colors.step = 5;
			//}
	    });  
	     
  //Army ESM
	 	$.getJSON("getChartESMRelationship",{relationship: relationship, yr: yr}, function(j) {
			var cmdArray1 = new Array();		
			for(var i=0;i<j.length;i++){	  			  				
				var cmdname="";
				cmdArray1.push({'cat': j[i][0],'count': j[i][1]});  				 			
			}	   
			//if(cmdArray1.length > 0){
				am4core.useTheme(am4themes_animated);
				var chart = am4core.create("lineGraph", am4charts.PieChart3D);
				chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
				chart.data =cmdArray1;
			
				chart.innerRadius = am4core.percent(5);
				chart.depth = 1;
				//Morbidity.legend = new am4charts.Legend();
				
				var seriesEx = chart.series.push(new am4charts.PieSeries3D());
				seriesEx.dataFields.value = "count";
				seriesEx.dataFields.depthValue = "count";
				seriesEx.dataFields.category = "cat";
				seriesEx.slices.template.cornerRadius = 10;
				seriesEx.labels.template.text = "{cat} : {value.value}";
				//seriesEX.slices.template.tooltipText = "{cat} : {value.value}";
				seriesEx.colors.step = 5;
				
				var colorSet = new am4core.ColorSet();
				colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
				  return new am4core.color(color);
				});
				chart.colors = colorSet;
				
			//}
		});  
  ////////////////////////////////////////command
  
  $.getJSON("getChartCOMMANDRelationship",{relationship: relationship, yr: yr}, function(j) {
	  	var cmdArray2 = new Array();		
			for(var i=0;i<j.length;i++){
				alert("i=" + i);
				var cmdname="";
				cmdArray2.push({'Command': j[i][0],'count': j[i][1]});  				 			
			}	   
			am4core.useTheme(am4themes_animated);
			var chartpie = am4core.create("chartpie", am4charts.XYChart);
			
			chartpie.data = cmdArray2;
		
			var colorSet = new am4core.ColorSet();
				colorSet.list = ["#4285F4" ,"#34AB53", "#F44336", "#9400D3", "#FBC02D", "#86592d", "#1aff1a"].map(function(color) {
				return new am4core.color(color);
			});
			chartpie.colors = colorSet;
			
			// Create axes
			var categoryAxis = chartpie.xAxes.push(new am4charts.CategoryAxis());
			categoryAxis.dataFields.category = "Command";
			//categoryAxis.title.text = "Command";
			categoryAxis.renderer.grid.template.location = 0;
			categoryAxis.renderer.minGridDistance = 20;
			categoryAxis.renderer.cellStartLocation = 0.1;
			categoryAxis.renderer.cellEndLocation = 0.7;
			categoryAxis.renderer.labels.template.verticalCenter ="middle";
			categoryAxis.renderer.labels.template.rotation = 270;

			var  valueAxis_cmd = chartpie.yAxes.push(new am4charts.ValueAxis());
			valueAxis_cmd.min = 0;
			function createSeries_cmd(field, name, stacked) {
			  	var series1 = chartpie.series.push(new am4charts.ColumnSeries());
			  	series1.dataFields.valueY = field;
			  	series1.dataFields.categoryX = "Command";
			  	series1.name = name;
			  	series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
			  	series1.stacked = stacked;
			  	series1.columns.template.width = am4core.percent(95);
			  	
			  	series1.columns.template.events.on("hit",function(ev){
			  	 	$.getJSON("getCorpsWise_Count_List",{Command : ev.target.dataItem.dataContext.Command}, function(j) {
			  	 		if(j.length == 0){
			  	 			alert("No Data Found");
			  	 		}else{
				  			var corps = new Array();
				  			for(var i=0;i<j.length;i++){
				  				if(i==0){
				  					corps.push({Command:j[i][0],count:j[i][1]});	
				  				}else{
				  					corps.push(/*  ',' + */ {Command:j[i][0],count:j[i][1]} );
				  				}
				  			}
				  			CommandClick_MNH(corps);
				  		}
			  		});  
			  		
			  	},this);
			  	series1.columns.template.adapter.add("fill", function(fill, target) {
					return chartpie.colors.getIndex(target.dataItem.index);
				});	  	 	
			}
			createSeries_cmd("count", "Counts", false);
		});  
  /////////////////////////////
  
	  //Life style year wise
	  
	 	chartpie();
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
}

function Chart1Category2()
{
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
	if(from_date != ""){
		$.getJSON("getlowmadicalchart",{from_date: from_date,to_date:to_date}, function(j) {
			var cmdArray = new Array();
			for(var i=0;i<j.length;i++){
				cmdArray.push({'command': j[i][0],'off_total': j[i][1],'jco': j[i][2],'ort': j[i][3],'total': j[i][4]});
			}
			if(j.length == 0){
				alert("data Not Available");
			}
			chartuploadline_lmc(cmdArray);
		});
		
		$.getJSON("getLMCChart1List",{from_date: from_date,to_date:to_date}, function(j) {
			var cmdArray = new Array();
			for(var i=0;i<j.length;i++){	  			  				
				cmdArray.push({'Command': j[i][0],'off': j[i][1],'jco_or': j[i][2]});  			
			}
			if(j.length == 0){
				alert("data Not Available");
			}
			chartuploadline_lmc1(cmdArray);
		});
	}else{
		var getlowmadicalchart = ${getlowmadicalchart};
		chartuploadline_lmc(getlowmadicalchart);
		var getLMCChart1List = ${getLMCChart1List};
		chartuploadline_lmc1(getLMCChart1List);
	}
	
}
//Chart 1 & 2 Refresh End Here
</script>
	
<script>

var popupWindow = null
function openihdDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getihddesh?type=ihdDetails", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=yes");
		}
		else
		popupWindow = window.open("getihddesh?type=ihdDetails", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=yes");
}
var popupWindow = null
function opengethypertenstionDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("gethypertenstion?type=hypertenstion", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
		else
		popupWindow = window.open("gethypertenstion?type=hypertenstion", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
}
var popupWindow = null
function opengetgetdiabetesDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getdiabetes?type=diabetes", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
		else
		popupWindow = window.open("getdiabetes?type=diabetes", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
}
var popupWindow = null
function opengetgetinjuriesDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getinjuries?type=injuries", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
		else
		popupWindow = window.open("getinjuries?type=injuries", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
}
var popupWindow = null
function opengetobesityDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getobesity?type=obesity", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
		else
		popupWindow = window.open("getobesity?type=obesity", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
}
var popupWindow = null
function opengetADSDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getADS?type=ADS", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
		}
		else
		popupWindow = window.open("getADS?type=ADS", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1040,height=1040,fullscreen=no");
}

//View More
var popupWindow = null
function openViewMoreReport() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("getdataViewMore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1020,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("getdataViewMore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1020,height=600,fullscreen=no");
}
</script>