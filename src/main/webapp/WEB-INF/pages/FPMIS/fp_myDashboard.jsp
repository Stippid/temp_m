<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/JS_CSS/jquery.dataTables.min.css">


<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">    

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcss.css">

<style>
body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  background-color: #ffffff;
  overflow: hidden;
  margin: 0;
}

#chartgauge {
  width: 100%;
  max-height: 600px;
  height: 100vh;
}

#chartbar {
  width: 100%;
  max-height: 600px;
  height: 100vh;
}


#chartsimpleguage {
  width: 100%;
  max-height: 600px;
  height: 100vh;
}

#chartrealtime {
  width: 100%;
  max-height: 600px;
  height: 100vh;
}

#chartcylinder {
  width: 100%;
  max-height: 600px;
  height: 100vh;
}

#chartdrag {
  width: 100%;
  max-height: 600px;
  height: 100vh;
}

.spedoBox {
	height:30px;
	text-overflow:none; 
	width:100%;
	text-align: center;
	background-color: #FBF6D9;
	border:1px solid blue;
	font-size:.9vw;
	color:blue;
}

.spedoBoxDiv {
	height:150px;
	text-overflow:none; 
	width:100%;
	text-align: center;
	background-color: white!important;
	border:1px solid blue!important;
	color:blue;
}

.nPerTrendOuter {
	border:1px solid gray;
	background-color:#E5E4E2;
	
	width:70%;
}
.nPerTrendInner {
	height:27px;
	border:1px solid gray;
	text-align:center;
}
.uparrow {
  float:left;
  font-size: 24px!important;
  padding: 0px;
  cursor: pointer;
  background-color: azure;
  width:30px!important;
  height:30px!important;
}

.downarrow {
  float:right;
  font-size: 20px;
  padding: 2px;
  cursor: pointer;
  background-color: azure;
}
</style>
<script>
	var role = "${role}";
</script>

<body onload="setMenu();">
<div style="height:calc(100vh - 135px);overflow:auto">
 <div class="container-flex" style="background-color: #FFF5EE;padding:10px;border-radius: 10px;">
	<div class="card-header mms-card-header ncard-body-bg"
		style="min-height: 40px; padding-top: 10px;text-align: center;">
		<h5><span>Minor Head wise Performance Indicator</span></h5>
	</div>
	<div class="row" style="align-items: flex-start !important;margin-bottom: 10px;background-color: azure;color:blue;">
		<div class="col-md-8">
			<span id="DtlFilterOpttit" class="form-control" style="width:100%;text-align: center;font-size:1.25vw;color:blue;"></span>
		</div>
		<div class="col-md-4 form-control" style="text-align: center;color:blue;">
			Show&nbsp;:&nbsp;
			<select id="DtlFilterOpt" onchange="showPerMeter();" style="color:blue;" title="Select Filter"  class="form-control-sm">
				<option value="0">Fund vs Allocation</option>
				<option value="1">Allotment vs Expenditure</option>
				<option value="2">Allotment vs Fwd to CDA</option>
				<option value="3">Expenditure vs Fwd to CDA</option>
				<option value="4">Allotment vs Booked by CDA</option>
				<option value="5">Expenditure vs Booked by CDA</option>
				<option value="6">Fwd to CDA vs Booked by CDA</option>
			</select>
		</div>	
	</div>
	<div class="row" style="align-items: flex-start !important;">
		<div class="col-md-12">
			<c:set var="ln" value="0" />
			<c:forEach var="item" items="${nDbData}" varStatus="num">
				 <c:set var="ln" value="${ln+1}" />
		         <div class="col-md-3" style="border:0.5px solid lightgray;border-radius:7px;backgroundd-color: #FBF6D9;">		         	
				   	<span data-head='${item[7]}' class="form-control spedoBox" ondblclick=showPerTrend4('${item[0]}',this) title="Double Click to View Budger Holder wise State">&nbsp;${item[0]} - ${item[8]}</span>
				 	<div id="chartgauge_${ln}" class="spedoBoxDiv" style="border: 1px solid blue!importatn;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);width: 98%;left: 1%;" title="Chart - ${item[0]}"></div>
				 </div>
			</c:forEach>		
		</div>
	</div>
</div>
<div class="nPopModelContainer" id="divShowExp" style="display:none;">
<div id="divShowExp" class="nPopContainer" style="width:80%; position:fixed;displayy:none;min-height:400px;height:cal(100%-100px);left:10%;z-index: 199;top:20%;background-color: beige;padding-bottom: 10px;border: 10px solid #3F51B5!important;">
	<div class="nPopHeader" style="text-align: center;">
		<span id="showPerTrend4_tit" style="width:100%;text-align: center;font-size:1.3vw;color:yellow;">State</span>
		<span class="nPopClose" onclick="$('#divShowExp').hide();" title="Close Msg Window">&#10006;</span>
	</div>
	<div class="nPopBody">
		<div class="row" style="align-items: flex-start!important;">
			<div class="col-md-12">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-4" style="background-color: palegreen;">
							<span style="border:1px solid transparent;">
								<input type="button" style="cursor:pointer;" value="&#8657;" onclick="sortme('1:N','D');" title="Top Good Performer">
				 				Good Performer
				 				<input type="button" style="cursor:pointer;" value="&#8659;" onclick="sortme('1:N','A');" title="Bottom Good Performer">
			 				</span>
						</div>
						<div class="col-md-4"></div>				
						<div class="col-md-4" style="background-color: mistyrose;">
							<span style="border:1px solid transparent;text-align: right;">
								<input type="button" style="cursor:pointer;" value="&#8657;" onclick="sortme('1:N','A');" title="Top Bad Performer">
				 				Bad Performer
				 				<input type="button" style="cursor:pointer;" value="&#8659;" onclick="sortme('1:N','D');" title="Bottom Bad Performer">
			 				</span>
						</div>
				 	</div>
				 </div>			
				 <div class="col-md-12" style="border: 2px solid lightgray!important;overflow: auto;max-height:350px;">				
					<table id="showPerTrend4tb" style="width:calc(100%);border-spacing: 0 7PX;border-collapse: separate;">
					    <tbody id="showPerTrend4" style="font-size: .90vw;text-decoration: none;color:blue;">											
					    </tbody>
				    </table>
			   	</div>
			  </div>
			</div>
	  </div>
	</div>
	</div>
	
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#unit_name1").val('${unit_name1}');
		var q = $("#fin_year").val();
		$("#fin_year1").val(q);
		
		var sel='${n_sel}';
		if (sel !='') {
			$("#DtlFilterOpt").val(sel);			
		} 
		
		showPerMeter();
		//showPerTrend2();
		//showPerTrend5();
		//$("div#divwatermark").val('').addClass('watermarked');	
		//watermarkreport();
	});
</script>

<script type="text/javascript">
function opttit() {
	var c=$("#DtlFilterOpt").val();
	var d;
	if (c=='0') {
		d="Fund vs Allocation";
	}
	if (c=='1') {
		d="Allotment vs Expenditure";
	}
	if (c=='2') {
		d="Allotment vs Fwd to CDA";
	}
	if (c=='3') {
		d="Expenditure vs Fwd to CDA";
	}
	if (c=='4') {
		d="Allotment vs Booked by CDA";
	}
	if (c=='5') {
		d="Expenditure vs Booked by CDA";
	}
	if (c=='6') {
		d="Fwd to CDA vs Booked by CDA";
	}
	return d;	
} 
function showPerMeter(){
	var c=$("#DtlFilterOpt").val();
	$("#DtlFilterOpttit").text(opttit()); 
	var c1=parseInt(c)+1;
	var a=${nDbData};
	for (var i=0;i<a.length;i++) {
		nPlotfpExp(i+1,a[i][c1]);
	}
}
function nPlotfpExp(a,b){
	if (b>100) {
		b=100;
	}
	var chartMin = 0;
	var chartMax = 100;
	var data = {
	  score: b,
	  gradingData: [
	    {
	      title: "Poor",
	      color: "#ED6E6E",
	      lowScore: -1,
	      highScore: 20
	    },
	    {
	      title: "Good",
	      color: "#F9CF48",
	      lowScore: 20,
	      highScore: 40
	    },
	    {
	      title: "Very Good",
	      color: "#84BD4C",
	      lowScore: 40,
	      highScore: 70
	    },
	    {
	      title: "Excellent",
	      color: "#509EE3",
	      lowScore: 70,
	      highScore: 100
	    }
	  ]
	};

	function lookUpGrade(lookupScore, grades) {
	  for (var i = 0; i < grades.length; i++) {
	    if (
	      grades[i].lowScore < lookupScore &&
	      grades[i].highScore >= lookupScore
	    ) {
	      return grades[i];
	    }
	  }
	  return null;
	}

	am4core.useTheme(am4themes_animated);

	var chart = am4core.create("chartgauge_"+a, am4charts.GaugeChart);
	chart.hiddenState.properties.opacity = 0;
	chart.fontSize = "12pt";
	chart.innerRadius = am4core.percent(60);
	chart.resizable = true;

	var axis = chart.xAxes.push(new am4charts.ValueAxis());
	axis.min = chartMin;
	axis.max = chartMax;
	axis.strictMinMax = true;
	axis.renderer.radius = am4core.percent(60);
	axis.renderer.inside = false;
	axis.renderer.line.strokeOpacity = 0.1;
	axis.renderer.ticks.template.disabled = false;
	axis.renderer.ticks.template.strokeOpacity = 1;
	axis.renderer.ticks.template.length = 5;
	axis.renderer.grid.template.disabled = true;
	axis.renderer.labels.template.radius = am4core.percent(70);
	axis.renderer.labels.template.fontSize = "0.7em";

	var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
	axis2.min = chartMin;
	axis2.max = chartMax;
	axis2.strictMinMax = true;
	axis2.renderer.labels.template.disabled = true;
	axis2.renderer.ticks.template.disabled = true;
	axis2.renderer.grid.template.disabled = false;
	axis2.renderer.grid.template.opacity = 0.5;
	axis2.renderer.labels.template.bent = true;

	for (let grading of data.gradingData) {
	  var range = axis2.axisRanges.create();
	  range.axisFill.fill = am4core.color(grading.color);
	  range.axisFill.fillOpacity = 1;
	  range.axisFill.zIndex = -1;
	  range.value = grading.lowScore > chartMin ? grading.lowScore : chartMin;
	  range.endValue = grading.highScore < chartMax ? grading.highScore : chartMax;
	  range.grid.strokeOpacity = 0;
	  range.stroke = am4core.color(grading.color).lighten(-0.1);
	}

	var matchingGrade = lookUpGrade(data.score, data.gradingData);

	var label = chart.radarContainer.createChild(am4core.Label);
	label.isMeasured = false;
	label.fontSize = ".9em";
	label.x = am4core.percent(50);
	label.paddingBottom = 15;
	label.horizontalCenter = "middle";
	label.verticalCenter = "bottom";
	label.text = data.score.toFixed(1) + " % ";

	var label2 = chart.radarContainer.createChild(am4core.Label);
	label2.isMeasured = false;
	label2.fontSize = "1.1em";
	label2.horizontalCenter = "middle";
	label2.verticalCenter = "top";	
	var lb2=matchingGrade.title || "";
	label2.text = lb2.toUpperCase();
	label2.fill = am4core.color(matchingGrade.color);

	var hand = chart.hands.push(new am4charts.ClockHand());
	hand.axis = axis2;
	hand.pin.disabled = false;
	hand.value = data.score;
	hand.fill = am4core.color(matchingGrade.color);
	hand.stroke = am4core.color(matchingGrade.color);
}

function CrTrendata(){
	var a=${nDbData1};
	var adt="[";
	for (var i=0;i<a.length;i++) {
		if (i>0) {
			adt += ',';
		}
		adt += '{"bhld":"'+a[i][2]+'",';
		adt += '"Expdr":'+a[i][3]+',';
		adt += '"CDA":'+a[i][4]+',';
		adt += '"Bkd":'+a[i][5];
		if (i==(a.length-1)) {
			adt += ', "stroke":"3,3",';
			adt += '"opacity":0.5';
		}
		adt += '}';
	}
	adt += ']';
	return adt;
}

function showPerTrend(){
	
	var a=${nDbData1};
	var adt="[";
	for (var i=0;i<a.length;i++) {
		if (i>0) {
			adt += ',';
		}
		adt += '{"bhld":"'+a[i][2]+'",';
		adt += '"Allotment":'+a[i][3]+',';
		adt += '"Expdr":'+a[i][4]+',';
		adt += '"CDA":'+a[i][5]+',';
		adt += '"Bkd":'+a[i][6];
		if (i==(a.length-1)) {
			adt += ', "stroke":"3,3",';
			adt += '"opacity":0.5';
		}
		adt += '}';
	}
	adt += ']';
	
	
	var p=adt;
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("showPerTrend", am4charts.XYChart);
	var data = [];
	chart.data=JSON.parse(p);
	
	var colorSet4 = new am4core.ColorSet();
	colorSet4.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	chart.colors = colorSet4; 
	chart.fontSize=10;

	var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.ticks.template.disabled = true;
	categoryAxis.renderer.line.opacity = 0;
	categoryAxis.renderer.grid.template.disabled = true;
	categoryAxis.renderer.minGridDistance = 40;
	categoryAxis.dataFields.category = "bhld";


	var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	valueAxis.tooltip.disabled = true;
	valueAxis.renderer.line.opacity = 0;
	valueAxis.renderer.ticks.template.disabled = true;
	valueAxis.min = 0;

	var columnSeries = chart.series.push(new am4charts.ColumnSeries());
	columnSeries.dataFields.categoryX = "bhld";
	columnSeries.dataFields.valueY = "Expdr";
	columnSeries.tooltipText = "Expdr: {valueY.value}";
	columnSeries.sequencedInterpolation = true;
	columnSeries.defaultState.transitionDuration = 900;
	
	var columnSeries1 = chart.series.push(new am4charts.ColumnSeries());
	columnSeries1.dataFields.categoryX = "bhld";
	columnSeries1.dataFields.valueY = "CDA";
	columnSeries1.tooltipText = "To CDA: {valueY.value}";
	columnSeries1.sequencedInterpolation = true;
	columnSeries1.defaultState.transitionDuration = 900;
	
	var columnSeries2 = chart.series.push(new am4charts.ColumnSeries());
	columnSeries2.dataFields.categoryX = "bhld";
	columnSeries2.dataFields.valueY = "Bkd";
	columnSeries2.tooltipText = "Booked: {valueY.value}";
	columnSeries2.sequencedInterpolation = true;
	columnSeries2.defaultState.transitionDuration = 900;

	var columnTemplate = columnSeries.columns.template;
	columnTemplate.column.cornerRadiusTopLeft = 10;
	columnTemplate.column.cornerRadiusTopRight = 10;
	columnTemplate.strokeWidth = 1;
	columnTemplate.strokeOpacity = 1;
	columnTemplate.stroke = columnSeries.fill;

	var desaturateFilter = new am4core.DesaturateFilter();
	desaturateFilter.saturation = 0.5;

	columnTemplate.filters.push(desaturateFilter);

	columnTemplate.propertyFields.strokeDasharray = "stroke";
	columnTemplate.propertyFields.fillOpacity = "opacity";

	var desaturateFilterHover = new am4core.DesaturateFilter();
	desaturateFilterHover.saturation = 1;

	var hoverState = columnTemplate.states.create("hover");
	hoverState.transitionDuration = 2000;
	hoverState.filters.push(desaturateFilterHover);

	var lineSeries = chart.series.push(new am4charts.LineSeries());
	lineSeries.dataFields.categoryX = "bhld";
	lineSeries.dataFields.valueY = "Allotment";
	lineSeries.tooltipText = "Allotment: {valueY.value}";
	lineSeries.sequencedInterpolation = true;
	lineSeries.defaultState.transitionDuration = 800;
	lineSeries.stroke = chart.colors.getIndex(11);
	lineSeries.fill = lineSeries.stroke;
	lineSeries.strokeWidth = 2;

	var bullet = lineSeries.bullets.push(new am4charts.CircleBullet());
	bullet.fill = lineSeries.stroke;
	bullet.circle.radius = 4;

	chart.cursor = new am4charts.XYCursor();
	chart.cursor.behavior = "none";
	chart.cursor.lineX.opacity = 0;
	chart.cursor.lineY.opacity = 0;
}

function showPerTrend2(){
	var amin=0;
	var amax=0;
	var aminv=0;
	var amaxv=0;
	var a=${nDbData1};
	var adt="[";
	var alen=a.length;
	if (alen>7) {
		alen=7;
	}
	for (var i=0;i<alen;i++) {
		if (i>0) {
			adt += ',';
		}
		adt += '{"bhld":"'+a[i][2]+'",';
		adt += '"Allotment":'+a[i][3]+',';
		adt += '"Expdr":'+a[i][4]+',';
		adt += '"CDA":'+a[i][5]+',';
		adt += '"Bkd":'+a[i][6];
		if (i==(a.length-1)) {
			adt += ', "stroke":"3,3",';
			adt += '"opacity":0.5';
		}
		adt += '}';
		if (parseInt(a[i][3])<amin) {
			amin = parseInt(a[i][3]);
		}
		if (amin==0){
			aminv=20;
		}
		if (parseInt(a[i][3])>amax) {
			amax = parseInt(a[i][3]);
		}
		if (amax<=200){
			aminv=170;
			amaxv=180;
		} else {
			aminv=amin+170;
			if ((amax - 200)<=aminv) {
				amaxv=amax-10;
			} else {
				amaxv=amax - 200;
			}
		}
	}
	adt += ']';
	
	
	var p=adt;
	
	am4core.useTheme(am4themes_animated);

	var chart = am4core.create("showPerTrend2", am4charts.XYChart);
	var data = [];
	chart.data=JSON.parse(p);
	
	chart.padding(40, 40, 40, 40);
 
 	var colorSet4 = new am4core.ColorSet();
	colorSet4.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	chart.colors = colorSet4; 
	chart.fontSize=10;

	var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.dataFields.category = "bhld";
	categoryAxis.renderer.minGridDistance = 60;


	var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	valueAxis.min = 0;
	valueAxis.max = amax;
	valueAxis.strictMinMax = true;
	valueAxis.renderer.minGridDistance = 30;

	valueAxis.renderer.labels.template.hiddenState.transitionDuration = 700;
	valueAxis.renderer.labels.template.defaultState.transitionDuration = 700;

	var axisBreak = valueAxis.axisBreaks.create();
	axisBreak.startValue = aminv;
	axisBreak.endValue = amaxv;
	axisBreak.breakSize = 0.002;
	

	var hoverState = axisBreak.states.create("hover");
	hoverState.properties.breakSize = 1;
	hoverState.properties.opacity = 0.1;
	hoverState.transitionDuration = 700;

	axisBreak.defaultState.transitionDuration = 1000;

	var series = chart.series.push(new am4charts.ColumnSeries());
	series.dataFields.categoryX = "bhld";
	series.dataFields.valueY = "Allotment";
	series.columns.template.tooltipText = "Allotment: {valueY.value}";
	series.columns.template.tooltipY = 0;
	series.columns.template.strokeOpacity = 0;
	
	var columnSeries = chart.series.push(new am4charts.ColumnSeries());
	columnSeries.dataFields.categoryX = "bhld";
	columnSeries.dataFields.valueY = "Expdr";
	columnSeries.columns.template.tooltipText = "Expdr: {valueY.value}";
	columnSeries.columns.template.tooltipY = 0;
	columnSeries.columns.template.strokeOpacity = 0;
	
	var columnSeries1 = chart.series.push(new am4charts.ColumnSeries());
	columnSeries1.dataFields.categoryX = "bhld";
	columnSeries1.dataFields.valueY = "CDA";
	columnSeries1.columns.template.tooltipText = "To CDA: {valueY.value}";
	columnSeries1.columns.template.tooltipY = 0;
	columnSeries1.columns.template.strokeOpacity = 0;
	
	var columnSeries2 = chart.series.push(new am4charts.ColumnSeries());
	columnSeries2.dataFields.categoryX = "bhld";
	columnSeries2.dataFields.valueY = "Bkd";
	columnSeries2.columns.template.tooltipText = "Booked: {valueY.value}";
	columnSeries2.columns.template.tooltipY = 0;
	columnSeries2.columns.template.strokeOpacity = 0;
}

function CrTrendata(){
	var a=${nDbData1};
	var adt="[";
	for (var i=0;i<a.length;i++) {
		if (i>0) {
			adt += ',';
		}
		adt += '{"bhld":"'+a[i][2]+'",';
		adt += '"Expdr":'+a[i][3]+',';
		adt += '"CDA":'+a[i][4]+',';
		adt += '"Bkd":'+a[i][5];
		if (i==(a.length-1)) {
			adt += ', "stroke":"3,3",';
			adt += '"opacity":0.5';
		}
		adt += '}';
	}
	adt += ']';
	//console.log(adt);
	return adt;
}

function showPerTrend4(a,b)
{		
	//alert(a+" "+b);	
	var c=$("#DtlFilterOpt").val();
	var opt=opttit();	
	var key = "${_csrf.parameterName}";
    var value = "${_csrf.token}";
    var val=a;
    var nkk="Budget Holder wise "+opt+ " State of Head - "+val+"-"+$(b).data("head");
    //console.log(nkk);
    $("#showPerTrend4_tit").text(nkk.replaceAll('"',''));
    $.post("getsPerTrend?"+key+"="+value, {val:val}).done(function(j){
    	//console.log(j);
		var colArray = new Array();
		var abc="";
		for(var i=0; i<j.length;i++){
			var ii=i;
			var colname=j[ii].colname;		
			if(colname != "") {
				var nV="";
				var nVc="";
				var nVb="";
				var c1=parseInt(c);
				abc += '<tr>';
				if (c=='1') {
					nV=j[ii][7];
				}
				if (c=='2') {
					nV=j[ii][8];
				}
				if (c=='3') {
					nV=j[ii][9];
				}
				if (c=='4') {
					nV=j[ii][10];
				}
				if (c=='5') {
					nV=j[ii][11];
				}
				if (c=='6') {
					nV=j[ii][12];
				}		
			  	var nVn=parseFloat(nV);
			  	console.log(i,nVn);
			  	if(nVn>100) {
			  		nVn=100;
			  	}
			  	console.log(i,nVn);
			  	if (nVn>0 && nVn<=20) {
			  		nVc="#ED6E6E";
			  		nVb="#F9CF48";
			  	}
			  	if (nVn>20 && nVn<=40) {
			  		nVc="#F9CF48";
			  		nVb="#ED6E6E";
			  	}
			  	if (nVn>40 && nVn<=70) {
			  		nVc="#84BD4C";
			  		nVb="#F9CF48";
			  	}
			  	if (nVn>70 && nVn<=100) {
			  		nVc="#509EE3";
			  		nVb="#F9CF48";
			  	}
			  	if (nVn>=100 && nVn<=1000) {
			  		nVc="#509EE3";
			  		nVb="#F9CF48";
			  	}
				abc += '<td style="text-align:right;padding-right:10px;width:20%;">'+j[ii][2].replaceAll('"','')+'</td>';
				abc += '<td style="display:none;">'+nV+'</td>';
				abc += '<td style="text-align:right;padding-right:10px;">';
				abc += '<div class="nPerTrendInner" style="background-color:'+nVc+';color:'+nVb+';width:'+nVn+'%">'+nVn+' %</div></td>';
			}
		}
		//console.log(abc);
		$("#showPerTrend4").html(abc);
		$("#divShowExp").show();
    }).fail(function(xhr, textStatus, errorThrown) {
	}); 
}


function showPerTrend4_ok(a)
{		
	var c=$("#DtlFilterOpt").val();
	var opt=opttit();	
	var key = "${_csrf.parameterName}";
    var value = "${_csrf.token}";
    var val=a;
    $("#showPerTrend4_tit").text("Budget Holder wise "+opt+ " State of Head - "+val);
    $.post("getsPerTrend?"+key+"="+value, {val:val}).done(function(j){
		var colArray = new Array();
		for(var i=0; i<j.length;i++){
			var ii=(j.length-i)-1;
			var colname=j[ii].colname;		
			if(colname != "")
				var c1=parseInt(c);
				if (c=='1') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][7]});
				}
				if (c=='2') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][8]});
				}
				if (c=='3') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][9]});
				}
				if (c=='4') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][10]});
				}
				if (c=='5') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][11]});
				}
				if (c=='6') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][12]});
				}				  				 			
		}	    

		if(colArray.length > 0){ 
			am4core.useTheme(am4themes_animated);
			var clusterddiv = am4core.create("showPerTrend4", am4charts.XYChart);
			clusterddiv.scrollbarX = new am4core.Scrollbar();
			clusterddiv.scrollbarY = new am4core.Scrollbar();
			
			clusterddiv.data =colArray;
			
			var categoryAxis_cluster = clusterddiv.yAxes.push(new am4charts.CategoryAxis());
			categoryAxis_cluster.dataFields.category = "colname";
			categoryAxis_cluster.title.text =""; 
			categoryAxis_cluster.renderer.grid.template.location = 0;
			categoryAxis_cluster.renderer.minGridDistance = 30;
			categoryAxis_cluster.renderer.cellStartLocation = 0.1;
			categoryAxis_cluster.renderer.cellEndLocation = 0.9;
			categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
			categoryAxis_cluster.renderer.labels.template.rotation = 360; 
								
			var  valueAxis_cluster = clusterddiv.xAxes.push(new am4charts.ValueAxis());
			valueAxis_cluster.min = 0;
			valueAxis_cluster.title.text =""; 
			valueAxis_cluster.rangeChangeDuration = 500;
			valueAxis_cluster.calculateTotals = true;
							
			var label = categoryAxis_cluster.renderer.labels.template;
			label.wrap = true;
			label.fontSize = 11;
			label.fontWeight = "bold";
			label.maxWidth = 120;	
			
			var label1 = categoryAxis_cluster.title;
			label1.fontSize = 15;
			label1.fontWeight = "bold";
			label1.stroke=am4core.color("#0000ff");
			label1.strokeWidth = 0.6;
			label1.strokeOpacity = 0.2;
			label1.textDecoration = "underline";
			
			var label12 = valueAxis_cluster.title;
			label12.fontSize = 15;
			label12.fontWeight = "bold";
			label12.stroke=am4core.color("#0000ff");
			label12.strokeWidth = 0.6;
			label12.strokeOpacity = 0.2;
			label12.textDecoration = "underline";
		
			var colorSet = new am4core.ColorSet();
			colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
				return new am4core.color(color);
			});
			clusterddiv.colors = colorSet;	
			clusterddiv.maskBullets = true;

			function createSeries_cluster(field, name) {
			  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
			  	series_cluster.dataFields.valueX = field;
			  	series_cluster.dataFields.categoryY = "colname";
			  	series_cluster.name = name;
			  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
			  	series_cluster.columns.template.height = am4core.percent(100);
			  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
			  	series_cluster.sequencedInterpolation = true;
			  	series_cluster.stacked = false;				  	
			  	var bullet1 = series_cluster.bullets.push(new am4charts.LabelBullet());
			  	bullet1.interactionsEnabled = false;
			  	bullet1.label.text = "{valueX}";
			  	bullet1.label.fill = am4core.color("#ffffff");
			  	bullet1.locationX = 1.5;
			  	bullet1.label.fontSize = 13;
			  	bullet1.label.fontWeight = "bold";
			  	series_cluster.columns.template.fill=am4core.color("#0288d1");
			  	var nV=field;
			  	var nVn=parseInt(nV);
			  	if (nVn>0 && nVn<=20) {
			  		series_cluster.columns.template.fill=am4core.color("#ED6E6E");
			  	}
			  	if (nVn>20 && nVn<=40) {
			  		series_cluster.columns.template.fill=am4core.color("#F9CF48");
			  	}
			  	if (nVn>40 && nVn<=70) {
			  		series_cluster.columns.template.fill=am4core.color("#84BD4C");
			  	}
			  	if (nVn>70 && nVn<=100) {
			  		series_cluster.columns.template.fill=am4core.color("#509EE3");
			  	}
			}			
			if (c=='1') {
				createSeries_cluster("allot", "Expdr", true);
			}
			if (c=='2') {
				createSeries_cluster("allot", "CDA", true);
			}
			if (c=='3') {
				createSeries_cluster("allot", "CDA", true);
			}
			if (c=='4') {
				createSeries_cluster("allot", "Booked", true);
			}
			if (c=='5') {
				createSeries_cluster("allot", "Booked", true);
			}
			if (c=='6') {
				createSeries_cluster("allot", "Booked", true);
			}		
			clusterddiv.legend = new am4charts.Legend();
			clusterddiv.legend.useDefaultMarker = true;	
			var marker = clusterddiv.legend.markers.template.children.getIndex(0);
			marker.cornerRadius(12, 12, 12, 12);
			marker.strokeWidth = 2;
			marker.strokeOpacity = 1;
			marker.stroke = am4core.color("#ccc"); 
		}
    }).fail(function(xhr, textStatus, errorThrown) {
	}); 
}

function showPerTrend4_multi(a)
{		
	var c=$("#DtlFilterOpt").val();
	var opt=opttit();	
	var key = "${_csrf.parameterName}";
    var value = "${_csrf.token}";
    var val=a;
    $("#showPerTrend4_tit").text("Budget Holder wise "+opt+ " State of Head - "+val);
    $.post("getsPerTrend?"+key+"="+value, {val:val}).done(function(j){
		var colArray = new Array();
		for(var i=0; i<j.length;i++){
			var ii=(j.length-i)-1;
			var colname=j[ii].colname;		
			if(colname != "")
				var c1=parseInt(c);
				if (c=='1') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][3],'expdr': j[ii][4]});	
				}
				if (c=='2') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][3],'cda': j[ii][5]});	
				}
				if (c=='3') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'expdr': j[ii][4],'cda': j[ii][5]});	
				}
				if (c=='4') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'allot': j[ii][3],'bkd': j[ii][7]});	
				}
				if (c=='5') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'expdr': j[ii][4],'bkd': j[ii][7]});	
				}
				if (c=='6') {
					colArray.push({'colname': j[ii][2].replaceAll('"',''),'cda': j[ii][4],'bkd': j[ii][7]});	
				}				  				 			
		}	    

		if(colArray.length > 0){ 
			am4core.useTheme(am4themes_animated);
			var clusterddiv = am4core.create("showPerTrend4", am4charts.XYChart);
			clusterddiv.scrollbarX = new am4core.Scrollbar();
			clusterddiv.scrollbarY = new am4core.Scrollbar();
			
			clusterddiv.data =colArray;
			
			var categoryAxis_cluster = clusterddiv.yAxes.push(new am4charts.CategoryAxis());
			categoryAxis_cluster.dataFields.category = "colname";
			categoryAxis_cluster.title.text =""; 
			categoryAxis_cluster.renderer.grid.template.location = 0;
			categoryAxis_cluster.renderer.minGridDistance = 30;
			categoryAxis_cluster.renderer.cellStartLocation = 0.1;
			categoryAxis_cluster.renderer.cellEndLocation = 0.9;
			categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
			categoryAxis_cluster.renderer.labels.template.rotation = 360; 
								
			var  valueAxis_cluster = clusterddiv.xAxes.push(new am4charts.ValueAxis());
			valueAxis_cluster.min = 0;
			valueAxis_cluster.title.text =""; 
			valueAxis_cluster.rangeChangeDuration = 500;
			valueAxis_cluster.calculateTotals = true;
							
			var label = categoryAxis_cluster.renderer.labels.template;
			label.wrap = true;
			label.fontSize = 11;
			label.fontWeight = "bold";
			label.maxWidth = 120;	
			
			var label1 = categoryAxis_cluster.title;
			label1.fontSize = 15;
			label1.fontWeight = "bold";
			label1.stroke=am4core.color("#0000ff");
			label1.strokeWidth = 0.6;
			label1.strokeOpacity = 0.2;
			label1.textDecoration = "underline";
			
			var label12 = valueAxis_cluster.title;
			label12.fontSize = 15;
			label12.fontWeight = "bold";
			label12.stroke=am4core.color("#0000ff");
			label12.strokeWidth = 0.6;
			label12.strokeOpacity = 0.2;
			label12.textDecoration = "underline";
		
			var colorSet = new am4core.ColorSet();
			colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
				return new am4core.color(color);
			});
			clusterddiv.colors = colorSet;	
			clusterddiv.maskBullets = true;

			function createSeries_cluster(field, name) {
			  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
			  	series_cluster.dataFields.valueX = field;
			  	series_cluster.dataFields.categoryY = "colname";
			  	series_cluster.name = name;
			  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
			  	series_cluster.columns.template.height = am4core.percent(100);
			  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
			  	series_cluster.sequencedInterpolation = true;
			  	series_cluster.stacked = false;				  	
			  	var bullet1 = series_cluster.bullets.push(new am4charts.LabelBullet());
			  	bullet1.interactionsEnabled = false;
			  	bullet1.label.text = "{valueX}";
			  	bullet1.label.fill = am4core.color("#ffffff");
			  	bullet1.locationX = 1.5;
			  	bullet1.label.fontSize = 13;
			  	bullet1.label.fontWeight = "bold";
			}			
			if (c=='1') {
				createSeries_cluster("expdr", "Expdr", true);
				createSeries_cluster("allot", "Allotment", true);
			}
			if (c=='2') {
				createSeries_cluster("cda", "CDA", true);
				createSeries_cluster("allot", "Allotment", true);
			}
			if (c=='3') {
				createSeries_cluster("cda", "CDA", true);
				createSeries_cluster("expdr", "Expdr", true);
			}
			if (c=='4') {
				createSeries_cluster("bkd", "Booked", true);
				createSeries_cluster("allot", "Allotment", true);
			}
			if (c=='5') {
				createSeries_cluster("bkd", "Booked", true);
				createSeries_cluster("expdr", "Expdr", true);
			}
			if (c=='6') {
				createSeries_cluster("bkd", "Booked", true);
				createSeries_cluster("cda", "CDA", true);
			}		
			clusterddiv.legend = new am4charts.Legend();
			clusterddiv.legend.useDefaultMarker = true;	
			var marker = clusterddiv.legend.markers.template.children.getIndex(0);
			marker.cornerRadius(12, 12, 12, 12);
			marker.strokeWidth = 2;
			marker.strokeOpacity = 1;
			marker.stroke = am4core.color("#ccc"); 
		}
    }).fail(function(xhr, textStatus, errorThrown) {
	}); 
}

function showPerTrend5(){
		am4core.useTheme(am4themes_animated);

		var chart = am4core.create("showPerTrend5", am4charts.XYChart);
		chart.scrollbarX = new am4core.Scrollbar();
		chart.scrollbarY = new am4core.Scrollbar();
		
		chart.numberFormatter.numberFormat = "#.#";
		var colorSet4 = new am4core.ColorSet();
		colorSet4.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
			return new am4core.color(color);
		});
		chart.colors = colorSet4; 
		chart.fontSize=10;
		
		var a=${nDbData1};
		var adt="[";
		for (var i=0;i<a.length;i++) {
			if (i>0) {
				adt += ',';
			}
			adt += '{"bhld":"'+a[i][2]+'",';
			adt += '"allot":'+a[i][3]+',';
			adt += '"expdr":'+a[i][4]+',';
			adt += '"CDA":'+a[i][5]+',';
			adt += '"Bkd":'+a[i][6];
			adt += '}';
		}
		adt += ']';
		
		var p=adt;
		var data = [];
		chart.data=JSON.parse(p);
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "bhld";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 30;

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "Amount in Cr";
		valueAxis.title.fontWeight = 800;

		var series = chart.series.push(new am4charts.ColumnSeries());
		series.dataFields.valueY = "allot";
		series.dataFields.categoryX = "bhld";
		series.clustered = false;
		series.tooltipText = "Allotment: [bold]{valueY}[/]";

		var series2 = chart.series.push(new am4charts.ColumnSeries());
		series2.dataFields.valueY = "expdr";
		series2.dataFields.categoryX = "bhld";
		series2.clustered = false;
		series2.columns.template.width = am4core.percent(50);
		series2.tooltipText = "Expenditure: [bold]{valueY}[/]";

		chart.cursor = new am4charts.XYCursor();
		chart.cursor.lineX.disabled = true;
		chart.cursor.lineY.disabled = true;
}

function sortTable(a,c) { 
	  var table,table1, rows,rows1, switching, i, x, y, x1,y1,shouldSwitch,nVMax;  
	  if (a =='XNX') {
	    var nV=document.getElementById("ab").value;    
	  } else {
	    if(typeof a === 'string') {
	      var nV=a;
	    } else {
	      var nV=a.value;
	    }
	  }
	  var nVa=nV.split(":");
	  table = document.getElementById("showPerTrend4tb");
	  switching = true;
	  while (switching) {
	    switching = false;
	    rows = table.rows;
	    for (i = 0; i < (rows.length - 1); i++) {
	      shouldSwitch = false;
	      if (c=='D') {
	        x = rows[i].getElementsByTagName("TD")[nVa[0]];
	        y = rows[i + 1].getElementsByTagName("TD")[nVa[0]];
	      } else {
	        y = rows[i].getElementsByTagName("TD")[nVa[0]];
	        x = rows[i + 1].getElementsByTagName("TD")[nVa[0]];        
	      }
	      if (nVa[1]=="N") {
	          if (parseFloat(x.innerHTML) < parseFloat(y.innerHTML)){
	            shouldSwitch = true;
	            break;
	          }
	      } else {
	          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()){
	            shouldSwitch = true;
	            break;
	          }
	      }
	    }
	    if (shouldSwitch) {
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	    }
	  }
}

function sortme(a,b){
	sortTable(a,b);
}
</script>	