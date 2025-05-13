<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

<style>
#Morbidity {
	width: 100%;
	height: 100%;
}
#chartpie {
	width: 100%;
	height: 100%;
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

.info-box {
	width: 150px;
}

.col-dash {
	-webkit-box-flex: 0;
	-ms-flex: 0 0 13% !important;
	flex: 0 0 13% !important;
	max-width: 13% !important;
}

.table tr th:first-child {
	
}
</style>

<div class="animated fadeIn">
	<div class="col-md-12" align="center">
		<h5 class="trans_heading">
			<b><u>PRINCIPAL CAUSE LIST</u></b>
		</h5>
		<br>
	</div>
	<div class="card-body card-block">
		<div class="card">
			<div class="col-md-12 row form-group" style="margin-top: -10px;">
				<div class="col-md-2" style="text-align: left;">
					<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Principal Cause</label>
				</div>
				<div class="col-md-6" style="text-align: center; margin-top: -10px;">
					<select id="principal_cause" name="principal_cause" class="form-control-sm form-control">
						<option value="0">--Select--</option>
						<c:forEach var="item" items="${getmnhPrincipalList}" varStatus="num">
							<option value="${item}">${item}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="col-md-12 row form-group" style="margin-top: -5px;">
				<div class="col-md-2" style="text-align: left;">
					<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
				</div>
				<div class="col-md-6" style="text-align: center; margin-top: -5px;">
					<select name="command" id="command" class="form-control-sm form-control">
						<c:if test="${r_1[0][1] != 'COMMAND'}">
							<option value="ALL">-- All Command --</option>
						</c:if>
						<c:if test="${not empty ml_1[0]}">
							<c:set var="data" value="${ml_1[0]}" />
							<c:set var="datap" value="${fn:split(data,',')}" />
							<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
								<c:set var="dataf" value="${fn:split(datap[j],':')}" />
								<option value="${dataf[2]}" name="${dataf[2]}">${dataf[2]}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>
			<div class="col-md-12 row form-group" style="margin-top: -5px;">
				<div class="col-md-2" style="text-align: left;">
					<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Year</label>
				</div>

				<div class="col-md-6" style="text-align: center; margin-top: -5px;">
					<input type="text" id="yr" name="yr" class="form-control-sm form-control" autocomplete="off" style="width: 200px;"> 
					<select name="type" id="type" class="form-control-sm form-control" style="width: 200px;">
						<option value="0">----Select----</option>
						<option value="Table">Table</option>
						<option value="Graph">Graph</option>
					</select>
				</div>
			</div>
			<div class="col-md-12 row form-group" align="center">
				<div class="col-md-4"></div>
				<div class="col-md-2" style="text-align: center;">
					<button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="ShowData();">Refresh Graph</button>
				</div>
			</div>
		</div>
	</div>
	<div class="card-body card-block">
		<div class="col-md-12 state_tables" id="divPrint" style="display: block;">
			<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block; z-index: 1111">
				<span id="ip"></span>
				<table id="UnitMovReport" class="table no-margin table-striped  table-hover  table-bordered" border="1">
					<thead style="color: red; text-align: center;">
						<tr>
							<td colspan="3"><h4>Block Description Wise Status</h4></td>
						</tr>
					</thead>
					<thead
						style="background-color: #9c27b0; color: white; text-align: center;">
						<tr>
							<th style="text-align: center;">Ser No.</th>
							<th style="text-align: center;">Block Description</th>
							<th style="text-align: center;">Officer</th>
							<th style="text-align: center;">MNS</th>
							<th style="text-align: center;">JCO</th>
							<th style="text-align: center;">OR</th>
							<th style="text-align: center;">TOTAL</th>
						</tr>
					</thead>
					<tbody id="UnitMovReportTbody"></tbody>
				</table>
			</div>
		</div>
		<div class="col-md-12">
			<div id="chartpie" style="height: 450px;">
				<b style="text-decoration: underline;">BLOCK DESCRIPTION WISE TOTAL ADMISSION OF ARMY SERVING</b>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	ParseDateColumn();
	function ParseDateColumn() {
		var d = new Date();
		document.getElementById("yr").value=d.getFullYear();
	}
	$("div#divPrint").hide();
	$("div#chartpie").hide();
	
	$('#command').change(function(){
		if($("#command").val() != "All"){
			getCorps();
		}
	});	
});	
</script>
<script>
function ShowData()
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
	else if($("#type").val() == "0") {
		alert("Select Type For Graph");
		$("#type").focus();
		return false;
	}
	else{
		var principal_cause=$("#principal_cause").val();
		var yr=$("#yr").val(); 
		var command=$("#command").val();
		var type = $("#type").val();
   		$.getJSON("BlockDescCatTblList",{principal_cause: principal_cause, yr: yr, command: command}, function(j) {
		if(type == "Table"){
			$("div#divPrint").show();	
			$("div#chartpie").hide();
			$("#UnitMovReportTbody").empty();
	    	if(j.length>0)
	    	{
	    		var row="", m=0, gt=0;
	    		var offt =0, mnst = 0, jcot = 0, ort =0, totalall=0;
				for(var i=0;i<j.length;i++){
					m=i+1;
					gt = j[i].off_value + j[i].mns_value + j[i].jco_value + j[i].or_value;
					offt = offt + j[i].off_value;
					mnst = mnst + j[i].mns_value;
					jcot = jcot + j[i].jco_value;
					ort = ort + j[i].or_value;
					totalall = offt + mnst + jcot + ort;
					row += "<tr>";
					row += "<th>"+m+"</th>";
					row += "<th>"+j[i].b_desc+"</th>";
					row += "<th>"+j[i].off_value+"</th>";
					row += "<th>"+j[i].mns_value+"</th>";
					row += "<th>"+j[i].jco_value+"</th>";
					row += "<th>"+j[i].or_value+"</th>";
					row += "<th>"+gt+"</th>";
					row += "</tr>";
					
				}
				
				row += "<th></th>"; 
				row += "<th><b><u>TOTAL ALL</b></u></th>"; 
				row += "<th><b><u>"+offt+"</b></u></th>";
				row += "<th><b><u>"+mnst+"</b></u></th>";
				row += "<th><b><u>"+jcot+"</b></u></th>";
				row += "<th><b><u>"+ort+"</b></u></th>";
				row += "<th><b><u>"+totalall+"</b></u></th>"; 
				$("#UnitMovReportTbody").append(row);
	    	}
		}
		else if(type == "Graph"){
			$("div#chartpie").show();
			$("div#divPrint").hide();	
			if(j.length == 0){
  	 			alert("No Data Found");
  	 		}else{
			 	am4core.useTheme(am4themes_animated);
				var corps = new Array();
	  			for(var i=0;i<j.length;i++){
	  				if(i==0){
	  					corps.push({b_desc:j[i].b_desc,off_value:j[i].off_value,mns_value:j[i].mns_value,jco_value:j[i].jco_value,or_value:j[i].or_value});	
	  				}else{
	  					corps.push({b_desc:j[i].b_desc,off_value:j[i].off_value,mns_value:j[i].mns_value,jco_value:j[i].jco_value,or_value:j[i].or_value} );
	  				}
	  			}
		  			var Morbidity = am4core.create("chartpie", am4charts.XYChart);
		  			Morbidity.data = corps;
		  			var colorSet1 = new am4core.ColorSet();
		  			colorSet1.list = ["#1aff1a", "#86592d","#FBC02D", "#F44336", "#388E3C", "#8E24AA","#F44336" ,"#00802b", "#0288d1", "#ff66cc", "#86592d", "#1BA68D", "#ff3377", "#1aff1a"].map(function(color) {
		  			  return new am4core.color(color);
		  			});
		  			Morbidity.colors = colorSet1;
		  			
		  			// Create axes
		  			var categoryAxis_mor = Morbidity.xAxes.push(new am4charts.CategoryAxis());
		  			categoryAxis_mor.dataFields.category = "b_desc";
		  			categoryAxis_mor.renderer.grid.template.location = 0;
		  			categoryAxis_mor.renderer.minGridDistance = 20;
		  			categoryAxis_mor.renderer.cellStartLocation = 0.1;
		  			categoryAxis_mor.renderer.cellEndLocation = 0.7;
		  			categoryAxis_mor.renderer.labels.template.verticalCenter ="middle";
		  			categoryAxis_mor.renderer.labels.template.rotation = 310;
	
		  			var  valueAxis_mor = Morbidity.yAxes.push(new am4charts.ValueAxis());
		  			valueAxis_mor.min = 0;
		  			function createSeries_mor(field, name, stacked) {
		  			  	var series_mor = Morbidity.series.push(new am4charts.ColumnSeries());
		  			  	series_mor.dataFields.valueY = field;
		  			  	series_mor.dataFields.categoryX = "b_desc";
		  			  	series_mor.name = name;
		  			  	series_mor.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
		  			  	//series_mor.stacked = stacked;
		  			  	series_mor.columns.template.width = am4core.percent(100);
		  			  
		  			}
		  			createSeries_mor("off_value", "OFFICER", true);
		  			createSeries_mor("mns_value", "MNS", true);
		  			createSeries_mor("jco_value", "JCO", true);
		  			createSeries_mor("or_value", "OR", true);
				}
			}
		});
//Table end here
	}
}

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";

function getCorps() {
	var command = $("#command").val();
	//alert("Command " +command);
	$.post("getMNHCorps?"+key+"="+value, {a1 : command},function(j) {
		var options = '<option value="-1">--Select--</option>';
		var a = [];
		var enc = j[j.length - 1].substring(0, 16);
		for (var i = 0; i < j.length; i++) {
			a[i] = dec(enc, j[i]);
		}
		var data = a[0].split(",");
		var datap;

		for (var i = 0; i < data.length - 1; i++) {
			datap = data[i].split(":");
			options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[0] + '</option>';
		}
		$("#corps").html(options);
		var q = '${list.corps}';
		if(q != ""){
			$("#corps").val(q);
		}
		});
}
</script>