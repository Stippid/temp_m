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

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/mnh_css.css"> 
<link rel="stylesheet" type="text/css" href="js/common/jquery.dataTables.min.css">

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
				</div>
			</div>
		</div>
	</div>
</div>

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
							             <b style="text-decoration: underline;">LMC Category</b>
										 <div id="Morbidity" style="width: 80%; height: 250px;"></div>
							        </div>
							        
							 </div>
							 
							 <div class="col-md-12 row form-group">
					        <div class="col-md-6">
					             <b style="text-decoration: underline;">Low Medical Category</b>
						         <div id="chartuploadline" style="width: 80%; height: 250px;"></div>
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
$(document).ready(function () {
	
	Morbidity();
	chartuploadline();
	}); 

//Chart 1 LMC Category
function Morbidity(){
	am4core.useTheme(am4themes_animated);
	var Morbidity = am4core.create("Morbidity", am4charts.PieChart3D);
	Morbidity.hiddenState.properties.opacity = 0; // this creates initial fade-in
	Morbidity.data =${getLMCChart1List};
	Morbidity.innerRadius = am4core.percent(40);
	Morbidity.depth = 5;
	//Morbidity.legend = new am4charts.Legend();
	
	var series = Morbidity.series.push(new am4charts.PieSeries3D());
	series.dataFields.value = "perm";
	series.dataFields.depthValue = "perm";
	series.dataFields.category = "Command";
	series.labels.template.text = "{category} : {value.value}";
	series.slices.template.tooltipText = "{category} : {value.value}";
	series.slices.template.cornerRadius = 10;
	series.colors.step = 5;
}

//LMC Bar Category
function chartuploadline(){
 	am4core.useTheme(am4themes_animated);
	var chartuploadline = am4core.create("chartuploadline", am4charts.XYChart);
	
	chartuploadline.data = ${getLMCList};
	
	var colorSet = new am4core.ColorSet();
		colorSet.list = ["#4285F4" ,"#34AB53", "#F44336", "#9400D3", "#FBC02D", "#86592d", "#1aff1a"].map(function(color) {
		return new am4core.color(color);
	});
		chartuploadline.colors = colorSet;
	
	// Create axes
	var categoryAxis = chartuploadline.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "Command";
	//categoryAxis.title.text = "Command";
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.minGridDistance = 20;
	categoryAxis.renderer.cellStartLocation = 0.1;
	categoryAxis.renderer.cellEndLocation = 0.7;
	categoryAxis.renderer.labels.template.verticalCenter ="middle";
	categoryAxis.renderer.labels.template.rotation = 270;

	var  valueAxis_cmd = chartuploadline.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cmd.min = 0;
	function createSeries_cmd(field, name, stacked) {
	  	var series1 = chartuploadline.series.push(new am4charts.ColumnSeries());
	  	series1.dataFields.valueY = field;
	  	series1.dataFields.categoryX = "Command";
	  	series1.name = name;
	  	series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series1.stacked = stacked;
	  	series1.columns.template.width = am4core.percent(100);

	  	series1.columns.template.adapter.add("fill", function(fill, target) {
			return chartuploadline.colors.getIndex(target.dataItem.index);
		});	   	
	}
	createSeries_cmd("count", "LMC", false); 
	createSeries_cmd("off_str", "OFFICER", false); 
	createSeries_cmd("jco_or_str", "JCO/OR", false);  
}	

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
		if(cmdArray.length > 0){
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
			series.labels.template.text = "{category} : {value.value}";
			series.slices.template.tooltipText = "{category} : {value.value}";
			series.slices.template.cornerRadius = 10;
			series.colors.step = 5;
		}
    });  
     
  //Army ESM
  	$.getJSON("getChartESMRelationship",{relationship: relationship, yr: yr}, function(j) {
		var cmdArray1 = new Array();		
		for(var i=0;i<j.length;i++){	  			  				
			var cmdname="";
			cmdArray1.push({'cat': j[i][0],'count': j[i][1]});  				 			
		}	   
		if(cmdArray1.length > 0){
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
			seriesEx.labels.template.text = "{category} : {value.value}";
			seriesEX.slices.template.tooltipText = "{category} : {value.value}";
			seriesEx.colors.step = 5;
			
			var colorSet = new am4core.ColorSet();
			colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
			  return new am4core.color(color);
			});
			chart.colors = colorSet;
			
		}
    });  
 
}
}
//Chart 1 & 2 Refresh End Here
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