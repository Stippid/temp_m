<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
__
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

	<link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>	
	
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/miso/mnhDashboard/mnhDashboardJS.js"></script>
<link rel="stylesheet" href="js/miso/mnhDashboard/mnhDashboardCSS.css">


<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>



 <style>
 .table tbody {

	max-height: 100px;
	
	
	
} 
/*   .table tbody {
	display: block;
	max-height: 100px;
	overflow-y: scroll;
	width: 100%;
	scrollbar-width: thin;
} 

.table thead, .table tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
} 
 */


</style> 
<div class="animated fadeIn">
	<div class="row">
		<div class="col-md-12" align="center">
			<div class="card">
				<div id="DASHBOARD_tabs">
					<h5 class="trans_heading">
						<b><u>DAILY DISEASE SVL</u></b>
					</h5>
					<div class="col-md-12 row from-group">
						<div class="col-md-2" style="text-align: right;">
							<label for="text-input" class=" form-control-label"><b>DATE</b></label>
						</div>
						<div class="col-md-2">
							<input type="date" id="to_dt" name="to_dt" class="form-control-sm form-control" autocomplete="off" readonly>
						</div>
						
					
					</div>
				</div>
				
				<hr>
				<div class="card-body card-block">
				
					<div class="col-md-12" align="center">
						<div class="col-md-6">
							<b style="text-decoration: underline;">BED STATE</b>
							<div id="bed_state" style="width: 100%; height: 400px;"></div>
						</div>
						<div class="col-md-6">
							<b style="text-decoration: underline;">NOTIFIABLE DISEASES (DAILY STATE)</b>
							<div id="notifiable_diseases" style="width: 100%; height: 400px;"></div>
						</div>
					</div>
					<hr>
					<div class="col-md-12" align="center">
						<div class="col-md-4">
							<table style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print" >
								<thead style="color: white; text-align: center;">
									<tr>
										<th colspan="7" align="center"><h4>BED STATE: DEFAULTER LIST</h4></th>
									</tr>
									<tr>
										<th style="width: 20%">Ser</th>
										<th style="width: 80%">Name of MH</th>
										<th style="width: 80%">Daily Disease</th>
										<th style="width: 80%">VIP</th>
										<th style="width: 80%">adm/amc</th>
										<th style="width: 80%">bed state</th>
										<th style="width: 80%">unusual</th>
										<!-- <th style="width: 80%">Action</th> -->
									</tr>
								</thead>
								<tbody>
								     <tr >
										<c:forEach var="item" items="${daily_bed_defaulterlist}"  varStatus="num">
											<tr>
												<td >${num.index+1}</td>
												<td >${item.unit_name}  
											 <input type="hidden" id="unit_name_id${num.index+1}" name="unit_name_id${num.index+1}" class="form-control autocomplete" autocomplete="off" value="${item.unit_name}" ></td>
												<%--  <td >${item.sus_no}</td>  --%>
												 <td align="center">
												<a   class="action_icons action_update" title="Edit Data" id="btn_clc" onclick="daily_disease_fn(${num.index+1});"></a>
											    </td>
												
												
												<td align="center">
												<a class="action_icons action_update" title="Edit Data" id="btn_clc" onclick="vip_fn(${num.index+1});"></a>
												</td>
												
												<td align="center">
												<!-- <i class="fa fa-plus" title="Edit Data"></i> -->
										    	<a class="action_icons action_update" title="Edit Data" id="btn_clc" onclick="adm_amc_fn(${num.index+1});"></a>
												</td>
												
												
												<td  align="center">
								            	<a  class="action_icons action_update" title="Edit Data" id="btn_clc" onclick="bed_fn(${num.index+1});"></a>
												</td>
												
												
												<td  align="center">
										    	<a  class="action_icons action_update" title="Edit Data" id="btn_clc" onclick="unusual_fn(${num.index+1});"></a>
												</td>
												
											<!-- <td >  <a href="mnh_daily_disease" class="btn btn-primary btn-sm" id="btn_clc" >Daily Disease</a></td>	      
											
											<td >  <a href="mnh_daily_disease" class="btn btn-primary btn-sm" id="btn_clc" >VIP</a></td>	      
											
											<td >  <a href="mnh_daily_disease" class="btn btn-primary btn-sm" id="btn_clc" >adm/amc</a></td>	      
											
											<td >  <a href="mnh_daily_disease" class="btn btn-primary btn-sm" id="btn_clc" >bed state</a></td>
											
											<td >  <a href="mnh_daily_disease" class="btn btn-primary btn-sm" id="btn_clc" >unusual</a></td>  -->
											</tr>
									</c:forEach>
									</tr>
									
								</tbody>
							</table>
						</div>
						<div class="col-md-8">
							<table style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="color: white; text-align: center;">
									<tr>
										<th colspan="7" align="center"><h4>UNUSUAL OCCURRENCE</h4></th>
									</tr>
									<tr>
										<th >Ser</th>
										<th >MH</th>
										<th >No/Rank/Name</th>
										<th >Unit</th>
										<th >Fmn</th>
										<th >Diagnosis</th>
										<th >Details Of Occurrence</th>
									</tr>
								</thead>
								<tbody>
				                        <tr >
										<c:forEach var="item" items="${daily_unusual_occurence}"  varStatus="num">
											<tr>
												<td >${num.index+1}</td>
												<td >${item[0]}</td>
												<td >${item[1]}</td>
												<td >${item[2]}</td>
												<td >${item[3]}</td>
												<td>${item[4]}</td>
                                                <td >${item[5]}</td>
											</tr>
									</c:forEach>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-12" align="center">
						<div class="col-md-6">
							<table style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="color: white; text-align: center;">
									<tr>
										<th colspan="6" align="center"><h4>VIP ADMISSION</h4></th>
									</tr>
									<tr>
										<th >Ser</th>
										<th >MH</th>
										<th >No/Rank/Name</th>
										<th >Unit</th>
										<th >Fmn</th>
										<th >Diagnosis</th>
								
									</tr>
								</thead>
								<tbody>
								   <tr >
										<c:forEach var="item" items="${daily_vip_admission}"  varStatus="num">
											<tr>
												<td >${num.index+1}</td>
												<td >${item[0]}</td>
												<td >${item[1]}</td>
												<td >${item[2]}</td>
												<td >${item[3]}</td>
												<td >${item[4]}</td>
												

											</tr>
											</c:forEach>
											</tr>
								</tbody>
							</table>
						</div>
						<div class="col-md-6">
							<table style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="color: white; text-align: center;">
									<tr>
										<th colspan="6" align="center"><h4>AMC/AD CORPS/MNS OFFICER ADMISSION</h4></th>
									</tr>
									<tr>
										<th >Ser</th>
										<th >MH</th>
										<th >No/Rank/Name</th>
										<th >Unit</th>
										<th >Fmn</th>
										<th >Diagnosis</th>
				
									</tr>
								</thead>
								<tbody>
								<tr>
									<c:forEach var="item" items="${daily_amc_adm_admission}"  varStatus="num">
											<tr>
												<td >${num.index+1}</td>
												<td >${item[0]}</td>
												<td >${item[1]}</td>
												<td >${item[2]}</td>
												<td >${item[3]}</td>
												<td >${item[4]}</td>
                                              
											</tr>
									</c:forEach>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					
					
						<div class="col-md-12 ">
						<div class="row" id="BlkDesc">
								<table style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">
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
									<tr >
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
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<c:url value="updatedaily_disease_h" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="unit_name_h" >
	<input type="hidden" name="unit_name_h" id="unit_name_h"  />
</form:form>

<c:url value="update_vip_h" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="vip_hForm" name="vip_hForm" modelAttribute="vip_h" >
	<input type="hidden" name="vip_h" id="vip_h"  />
</form:form>

<c:url value="update_adm_amc_h" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="adm_amc_hForm" name="adm_amc_hForm" modelAttribute="adm_amc_h" >
	<input type="hidden" name="adm_amc_h" id="adm_amc_h"  />
</form:form>

<c:url value="update_bed_state_h" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="bed_state_hForm" name="bed_state_hForm" modelAttribute="bed_state_h" >
	<input type="hidden" name="bed_state_h" id="bed_state_h"  />
</form:form>

<c:url value="update_unusual_h" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="unusual_hForm" name="unusual_hForm" modelAttribute="unusual_h" >
	<input type="hidden" name="unusual_h" id="unusual_h"  />
</form:form>
<script type="text/javascript">
$(document).ready(function () {
	$().getCurDt(to_dt);
	bed_state();
	notifiable_diseases();

});

//LMC Chart 2
function bed_state(){
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("bed_state", am4charts.PieChart);
	var selected;
	var types = [
		{
	  type: "Occupied",
	  percent:"data2" ,
	  color: chart.colors.getIndex(0),
	}, {
	  type: "Balance",
	  percent:"data1" ,
	  color: chart.colors.getIndex(1)
	}];

	// Add data
	

// 		chart.data = ${notifiable_diseaselist};
	
	chart.data = generateChartData();

	// Add and configure Series
	var pieSeries = chart.series.push(new am4charts.PieSeries());
	pieSeries.dataFields.value = "percent";
	pieSeries.dataFields.category = "type";
	pieSeries.slices.template.propertyFields.fill = "color";
	pieSeries.slices.template.propertyFields.isActive = "pulled";
	pieSeries.slices.template.strokeWidth = 0;

	function generateChartData() {
		
	  var chartData = [];
	  for (var i = 0; i < types.length; i++) {
	    if (i == selected) {
	      for (var x = 0; x < types[i].subs.length; x++) {
	        chartData.push({
	          type: types[i].subs[x].type,
	          percent: types[i].subs[x].percent,
	          color: types[i].color,
	          pulled: true
	        });
	      }
	    } else {
	      chartData.push({
	        type: types[i].type,
	        percent: types[i].percent,
	        color: types[i].color,
	        id: i
	      });
	    }
	  }
	  return chartData;
	}

	pieSeries.slices.template.events.on("hit", function(event) {
	  if (event.target.dataItem.dataContext.id != undefined) {
	    selected = event.target.dataItem.dataContext.id;
	  } else {
	    selected = undefined;
	  }
	  chart.data = generateChartData();
	});
}



function notifiable_diseases(){
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("notifiable_diseases", am4charts.XYChart);
	chart.scrollbarX = new am4core.Scrollbar();
	


	chart.data = ${daily_notifiable_disease};
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
</script>

<script>

function daily_disease_fn(ps)
{
	

	document.getElementById('unit_name_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('updateForm').submit();
} 

function vip_fn(ps){

	document.getElementById('vip_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('vip_hForm').submit();
	
}

function adm_amc_fn(ps){
	
	
	document.getElementById('adm_amc_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('adm_amc_hForm').submit();
	
}
function bed_fn(ps){
	
	document.getElementById('bed_state_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('bed_state_hForm').submit();
	
}
function unusual_fn(ps){
	
	
	document.getElementById('unusual_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('unusual_hForm').submit();
	
}


</script>