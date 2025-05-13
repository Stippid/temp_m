<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/miso/mnhDashboard/mnhDashboardJS.js"></script>
<link rel="stylesheet" href="js/miso/mnhDashboard/mnhDashboardCSS.css">
<style>
#chartsimple {
  width: 50%;
  height: 50%;
}
#chartpie {
  width: 50%;
  height: 50%;
}
#chartdivline {
  width: 100%;
  height: 50%;
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
</style>
	<div class="animated fadeIn" >
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					<div id="DASHBOARD_tabs">
	      				<h5 class="trans_heading">Medical</h5>
    					<div class="row">
    						<div class="card-body card-block">
								<div class="col-md-7">
									<div id="chartdivline" class="col-md-12"></div>
									<div id="chartpie" class="col-md-6"></div>
									<div id="chartsimple" class="col-md-6"></div>
								</div>
								<div class="col-md-5 col-md-offset-1 state_tables">
									<table id="datauploadstatusTbl" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="4"><h4>Data Upload Status For Last 6 Months</h4></td>
											</tr>
											<tr >
												<th>Month</th>
												<th>Hospital Uploaded</th>
												<th>Hospital Not Uploaded</th>
												<th>Total</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${datauploadstatusTblList}" varStatus="num" >
												<tr>
													
													<td>${item[0]}</td>
													<td>${item[1]}</td>
													<td><a href='#' onclick='opendatauploadinfo("${item[0]}");' style='color:white'><u>${item[2]}</u></a></td>
													<td>${item[3]}</td>
												 	
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<table id="commadwiseTbl" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="4"><h4>Top 10 Hospital Wise Adm</h4></td>
											</tr>
											<tr>
												<th width="75%">Hospital</th>
                                                <th width="25%">Admission</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${commadwiseTblList}" varStatus="num" >
												<tr>
													
													<td>${item[0]}</td>
													<td>${item[1]}</td>
												 	
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
								</div>
							</div>
							<div class="card-body card-block">	
								 <div class="col-md-12">
									<div class="col-md-2">
										<div class="info-box bg-deep-purple">
											<div class="content">
											  <h5>IHD</h5>
												<div class="number count-to" data-from="0" data-to="125" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#illegalityMiningCasesView" onclick="openihdDetails();">
														<label id="ihdTotal" ><u> ${getihdList[0]} </u></label> 
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-deep-pink">
											<div class="content">
												<h5>HYPERTENSION</h5>
												<div class="number count-to" data-from="0" data-to="257" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengethypertenstionDetails();"> 
														<label id="prfTotal"><u>${gethyperList[0]}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-green">
											<div class="content">
												<h5>DIABETES</h5>
												<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengetgetdiabetesDetails();"> 
														<label id="mctTotal"><u>${getdmList[0]}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-red">
											<div class="content">
												<h5>OBESITY</h5>
												<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengetobesityDetails();"> 
														<label id="mctTotal"><u>${getobesList[0]}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-purple">
											<div class="content">
												<h5>INJURIES NEA</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="opengetgetinjuriesDetails();"> 
														<label id="mctTotal"><u>${getinjurisList[0]}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-indigo">
											<div class="content">
												<h5>TOTAL ADM</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView"> 
														<label id="mctTotal">${getadmList[0]}</label>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>	
    					</div>
	    			</div>
	    		</div>
			</div>
		</div>
	</div>
<script>
	
		///////////////////////////////////////////////////
		var chartsimple = am4core.create("chartsimple", am4charts.XYChart);
		chartsimple.data = ${getchartsimpleList};
		var categoryAxiss = chartsimple.xAxes.push(new am4charts.CategoryAxis());
		categoryAxiss.dataFields.category = "Command";
		categoryAxiss.renderer.grid.template.location = 0;
		categoryAxiss.renderer.minGridDistance = 30;
		//categoryAxiss.renderer.labels.template.rotation = 310;
		categoryAxiss.renderer.labels.template.adapter.add("dy", function(dy, target) {
		  if (target.dataItem && target.dataItem.index & 2 == 2) {
		    return dy + 25;
		  }
		  return dy;
		});
		var valueAxiss = chartsimple.yAxes.push(new am4charts.ValueAxis());
		var seriess = chartsimple.series.push(new am4charts.ColumnSeries());
		seriess.dataFields.valueY = "count";
		seriess.dataFields.categoryX = "Command";
		seriess.name = "count";
		seriess.columns.template.tooltipText = "{categoryX}: [bold]{valueY}[/]";
		seriess.columns.template.fillOpacity = .8;
		var columnTemplates = seriess.columns.template;
		columnTemplates.strokeWidth = 2;
		columnTemplates.strokeOpacity = 1;
		columnTemplates.fill = am4core.color("#ff99cc");
		/////////////////////pie///////////////////////////
		var chartpie = am4core.create("chartpie", am4charts.PieChart);
		var pieSeries = chartpie.series.push(new am4charts.PieSeries());
		pieSeries.dataFields.value = "count";
		pieSeries.dataFields.category = "cat";
		chartpie.innerRadius = am4core.percent(30);
		pieSeries.slices.template.stroke = am4core.color("#ccc");
		pieSeries.slices.template.strokeWidth = 2;
		pieSeries.slices.template.strokeOpacity = 1;
		pieSeries.slices.template
		  .cursorOverStyle = [
		    {
		      "property": "cursor",
		      "value": "pointer"
		    }
		  ];
		var colorSet = new am4core.ColorSet();
		colorSet.list = ["#8E24AA", "#008000", "#ff3377", "#4d94ff", "#388E3C", "#1BA68D", "#0288d1", "#F44336", "#86592d", "#bf4080", "#1aff1a", "#e6e600"].map(function(color) {
		  return new am4core.color(color);
		});
		pieSeries.colors = colorSet;
		pieSeries.ticks.template.disabled = true;
		var shadow = pieSeries.slices.template.filters.push(new am4core.DropShadowFilter);
		shadow.opacity = 0;
		var hoverState = pieSeries.slices.template.states.getKey("hover");
		var hoverShadow = hoverState.filters.push(new am4core.DropShadowFilter);
		hoverShadow.opacity = 0.7;
		hoverShadow.blur = 5;
		chartpie.data = ${getcategoryList};
		///////////////line chart/////////////////////////
		am4core.useTheme(am4themes_animated);
		var chartdivline = am4core.create("chartdivline", am4charts.XYChart);
		chartdivline.data = ${getmonthwiseList};
		chartdivline.dateFormatter.inputDateFormat = "YYYY-MM";
		var dateAxis = chartdivline.xAxes.push(new am4charts.DateAxis());
		var valueAxis = chartdivline.yAxes.push(new am4charts.ValueAxis());
		var seriesLine = chartdivline.series.push(new am4charts.LineSeries());
		seriesLine.dataFields.valueY = "count";
		seriesLine.dataFields.dateX = "month";
		seriesLine.tooltipText = "{count}"
		seriesLine.strokeWidth = 2;
		seriesLine.minBulletDistance = 15;
		seriesLine.tooltip.background.cornerRadius = 20;
		seriesLine.tooltip.background.strokeOpacity = 0;
		seriesLine.tooltip.pointerOrientation = "vertical";
		seriesLine.tooltip.label.minWidth = 40;
		seriesLine.tooltip.label.minHeight = 40;
		seriesLine.tooltip.label.textAlign = "middle";
		seriesLine.tooltip.label.textValign = "middle";
		var bullet = seriesLine.bullets.push(new am4charts.CircleBullet());
		bullet.circle.strokeWidth = 2;
		bullet.circle.radius = 6;
		bullet.circle.fill = am4core.color("#F44336");
		var bullethover = bullet.states.create("hover");
		bullethover.properties.scale = 1.3;
		chartdivline.cursor = new am4charts.XYCursor();
		chartdivline.cursor.behavior = "panXY";
		chartdivline.cursor.xAxis = dateAxis;
		chartdivline.cursor.snapToSeries = seriesLine;
		chartdivline.scrollbarY = new am4core.Scrollbar();
		chartdivline.scrollbarY.parent = chartdivline.leftAxesContainer;
		chartdivline.scrollbarY.toBack();
		chartdivline.scrollbarX.series.push(series);
		chartdivline.events.on("ready", function () {
		dateAxis.zoom({start:0.79, end:12});
		});
		///////////////////////////////////////

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
function opendatauploadinfo(val) {
	var url = "getdatauploadinformation?month="+val;
		popupWindow = window.open(url, "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
}
</script>