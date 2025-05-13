<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/circle-progress.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/miso/mmsDashboard/mmsDashboardJS.js"></script>
<link rel="stylesheet" href="js/miso/mmsDashboard/mmsDashboardCSS.css">

<link href="js/miso/DashboardJSCSS/bootstrap-multiselect.css" rel="stylesheet" type="text/css" />
<link href="js/miso/mmsDashboard/deshboardmmsstyle.css" rel="stylesheet" type="text/css" />
<script src="js/miso/DashboardJSCSS/bootstrap-multiselect.js" type="text/javascript"></script>

	<div class="animated fadeIn" >
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					<div id="DASHBOARD_tabs">
	      				<h5 class="trans_heading">Material Management System</h5>
    					<div class="row">
    						<div class="card-body card-block">
								<div class="col-md-7">
									<div class="col-md-4 mvcr_updationmms">
										<h5>CORRECTNESS CERT</h5>
										<a href="#" onclick="openUstatuslistReport();">
											<div class="progressbar" href="mms_sector_wise_holding_data" id="progressbar" data-animate="false">
												<div class="circle1mms" data-percent="0" id="mvcrProgress">
													<div id="mmsmvcr"></div>
												</div>
											</div>
										</a> 
									</div>
									<div class="col-md-4">
							     		<h5>UNITS WITH OBSN IN MCR</h5>
										<a href="#">
											<div class="progressbarTransmms" id="progressbarTransmms" data-animate="false">
												<div class="circle2mms" data-percent="0" id="mvcrProgressTranmms">
													<div id="mmstrans"></div>
												</div>
											</div>
										</a>
									</div>
									<div class="col-md-4">
										<h5>RIO STATUS</h5>
										<a href="#">
											<div class="progressbarRomms" id="progressbarRomms" data-animate="false">
												<div class="circle3mms" data-percent="0" id="mvcrProgressRo_Riomms">
													<div id="mmsro_rio"></div>
												</div>
											</div>
										</a>
									</div>
									<div class="col-md-12 prf_group_drop">
									<div class="col-md-4">
									<div class="dash_check_heading">
										 <h5>PRF<i class="fa fa-eraser pull-right erase-full" id="prf_groupEraser"></i></h5>
									</div>
										<div class="panel-body sel-drop prf_search" class="accordion-toggle" data-toggle="collapse" data-target="#demoSix">
				                          <a>All<i class="fa fa-angle-down sel-ico"></i></a>
				                          <div id="demoSix" class="collapse over-collape">
										    <form>
										       <div class="multiselect">
											      <div class="selectBox">
												  <select  id="prf_group" type="checkbox" multiple="multiple" class="game">
														<c:forEach var="item" items="${getmmsprfnamenoList}" varStatus="num" >
															<option value="${item[0]}" >${item[1]}</option>
														</c:forEach>
													</select>
											      </div>
										      </div>
									        </form>
									      </div>
				                         </div>	
									</div>
									<div class="col-md-12">
										<div id="chartdivPRFWise"></div>
									</div>
								</div>
								
								</div>
									
								<div class="col-md-5 col-md-offset-1 state_tables">
								<table id="defaulterunittbl" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="5"><h4>UNIT MCR CORRECTNESS</h4></td>
											</tr>
											<tr>
												<th>MONTH</th>
												<th>YEAR</th>
												<th>TOTAL UNITS</th>
												<th>TOTAL RECD</th>
												<th>TOTAL BAL</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${getdefaulterunittbl}" varStatus="num" >
												<tr>
													<td>${item[0]}</td>
													<td>${item[1]}</td>
													<td><a href='#' onclick='openTotalUnitReport();' style='color:white'><u>${item[2]}</u></a></td>
													<td>${item[3]}</td>
													<td>${item[4]}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<table id="unitwithmcrobsn" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="4"><h4>UNITS MCR : OBSN STATUS</h4></td>
											</tr>
											<tr>
												<th>TOT UNITS</th>
											    <th>UNITS WITH OBSN</th>
												<th>UNITS WITHOUT OBSN</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${getunitwithmcrobsn}" varStatus="num" >
												<tr>
													<td>${item[0]}</td>
													<td>${item[1]}</td>
													<td>${item[2]}</td>
													
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<table id="mmsRoRioStatustbl" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="6"><h4>RO/RIO STATUS</h4></td>
											</tr>
											<tr>
												<th>MONTH</th>
												<th>YEAR</th>
												<th>RO RECD</th>
												<th>RIO ISSUED</th>
												<th>RIO PENDING</th>
												<th>RIO WITH OBSN</th>
											
										  </tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${getmmsRoRioStatustbl}" varStatus="num" >
												<tr>
													<td>${item[0]}</td>
													<td>${item[1]}</td>
													<td>${item[2]}</td>
													<td>${item[3]}</td>
													<td>${item[4]}</td>
													<td>${item[5]}</td>
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<table id="censusnostatustbl" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="5"><h4>CENSUS No STATUS</h4></td>
											</tr>
											<tr>
												<th>CENSUS No </th>
												<th>CUR</th>
												<th>OBE</th>
												<th>OBT</th>
												<th>OTHER</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${getcensusnostatustbl}" varStatus="num" >
												<tr>
													<td>${item[0]}</td>
													<td>${item[1]}</td>
													<td>${item[2]}</td>
													<td>${item[3]}</td>
													<td>${item[4]}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<table id="clofwpneqpttbl" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="4"><h4>CL OF WPN/EQPT</h4></td>
											</tr>
											<tr>
											    <th>CL'A'</th>
												<th>CL'B'</th>
												<!-- <th>CL'C'</th> -->
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${getclofwpneqpttbl}" varStatus="num" >
											<tr>
												<td>${item[0]}</td>
												<td>${item[1]}</td>
												<%-- <td>${item[2]}</td> --%>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="col-md-12">
									<div class="col-md-3">
										<div class="info-box bg-red">
										<div class="content">
											<h4>TOTAL CENSUS No</h4>
											<div class="number count-to" data-from="0" data-to="125" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView">
													    <label id="censusTotal" >${getmmscensusTotalList}</label>
													</a>
											</div>
										</div>
									</div>
									</div>
									<div class="col-md-3">
										<div class="info-box bg-indigo">
										<div class="content">
											<h4>TOTAL PRF</h4>
											<div class="number count-to" data-from="0" data-to="257" data-speed="1000" data-fresh-interval="20">
												<a data-toggle="modal" data-target="#minorMineralMiningView"> <label id="prfTotalno">${getmmsprfTotalnoList}</label></a>
											</div>
										</div>
									</div>
									</div>
									<div class="col-md-3">
										<div class="info-box bg-green">
										<div class="content">
											<h4>TOTAL UNITS</h4>
											<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
												<a data-toggle="modal" data-target="#totalOtherthanMajorMiningActivityView"> <label id="unitTotalno">${getmmsunitTotalnoList}</label></a>
											</div>
										</div>
									</div>
									</div>
									<div class="col-md-3">
										<div class="info-box bg-deep-purple">
										<div class="content">
											<h4>TOTAL DEPOTS</h4>
											<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
												<a data-toggle="modal" data-target="#otherThanMiningActivityView"> <label id="depotTotalno">${getmmsdepotTotalList}</label></a>
											</div>
										</div>
									</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-3">
										<div class="info-box bg-purple">
										<div class="content">
											<h4><a href="mms_sector_wise_holding_data" style="color:#fff;">SECTOR STORES</a></h4>
											<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
												<a data-toggle="modal" data-target="#anyOtherCaseView">
													<%-- <label id="sectorTotal">${getmmssectorTotalList}</label> --%>
												</a>
											</div>
										</div>
									</div>
									</div>
									<div class="col-md-3">
										<div class="info-box bg-darkgreen">
										<div class="content">
										<h4><a href="mms_loan_wise_holding_data" style="color:#fff;">LOAN STORES</a></h4>
											<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
												<a data-toggle="modal" data-target="#anyOtherCaseView">
													<%-- <label id="loanTotal">${getmmsloanTotalList}</label> --%>
												</a>
											</div>
										</div>
									</div>
									</div>
									<div class="col-md-3">
										<div class="info-box bg-darkblue">
										<div class="content">
										<h4><a href="mms_acsfp_wise_holding_data" style="color:#fff;">ACSFP STORES</a></h4>
											<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
												<a data-toggle="modal" data-target="#anyOtherCaseView">
													<%-- <label id="acsfpTotal">${getmmsacsfpTotalList}</label> --%>
												</a>
											</div>
										</div>
									</div>
									</div>
									<div class="col-md-3">
									<div class="info-box bg-darkyellow">
										<div class="content">
										<h4><a href="#" style="color:#fff;" onclick="Wast();">RESERVES</a></h4>
											<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
												<a data-toggle="modal" data-target="#anyOtherCaseView">
													<%-- <label id="engrTotal">${getmmsengrTotalList}</label> --%>
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
							
<c:url value="wast_dash_reportlist" var="printUrl" />
	<form:form action="${printUrl}" method="post" id="p_unit10" name="p_unit10" >
</form:form> 							
							
<script>
function Wast(){
	$("#p_unit10").submit();
}
function UEUHBDEClick_C_Veh(prf){
	am4core.useTheme(am4themes_animated);
	var chartdivPRFWise = am4core.create("chartdivPRFWise", am4charts.XYChart);
	
	chartdivPRFWise.data = prf;
	
	var categoryAxis_AVEH = chartdivPRFWise.xAxes.push(new am4charts.CategoryAxis());
	
	categoryAxis_AVEH.dataFields.category = "year";
	categoryAxis_AVEH.title.text = "PRF";
	categoryAxis_AVEH.renderer.grid.template.location = 0;
	categoryAxis_AVEH.renderer.minGridDistance = 20;
	categoryAxis_AVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_AVEH.renderer.cellEndLocation = 0.5;
	categoryAxis_AVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_AVEH.renderer.labels.template.rotation = 20;
	
	var  valueAxis_AVEH = chartdivPRFWise.yAxes.push(new am4charts.ValueAxis());
	valueAxis_AVEH.min = 0;

	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	chartdivPRFWise.colors = colorSet;
	
	// Create series
	function createSeries_AVEH(field, name, stacked) {
	  	var series_AVEH = chartdivPRFWise.series.push(new am4charts.ColumnSeries());
	  	series_AVEH.dataFields.valueY = field;
	  	series_AVEH.dataFields.categoryX = "year";
	  	series_AVEH.name = name;
	  	series_AVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_AVEH.stacked = stacked;
	  	series_AVEH.columns.template.width = am4core.percent(95);
	}
	createSeries_AVEH("ue", "UE", false);
	createSeries_AVEH("uh", "UH", false);
	chartdivPRFWise.legend = new am4charts.Legend();   
	
}	
	
   	mvcrProgressCount = (parseInt('${getdefaulterunittbl[0][3]}')/parseInt('${getdefaulterunittbl[0][2]}')) * parseInt(100); 
	progressbar(mvcrProgressCount,0,0);
	
	transctionCount = (parseInt('${getunitwithmcrobsn[0][1]}')/parseInt('${getunitwithmcrobsn[0][0]}')) * parseInt(100); 
	progressbar(0,transctionCount,0);
	
	ro_rioCount = (parseInt('${getmmsRoRioStatustbl[0][3]}')/parseInt('${getmmsRoRioStatustbl[0][2]}')) * parseInt(100); 
	progressbar(0,0,ro_rioCount);
   
////////////////////////////////////
var popupWindow = null
function openUstatuslistReport() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("Ustatuslist_dash?type=Ustatus", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("Ustatuslist_dash?type=Ustatus", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=800,height=600,fullscreen=no");

}
var popupWindow = null
function openTotalUnitReport() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("TotalUnitReport?type=TotalUnit", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("TotalUnitReport?type=TotalUnit", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");	
}
</script>
<script>

$(document).ready(function() {
	
	getchartdivPRFWiseList();
	
	$(".search_button").click( function(e) {
	    e.stopPropagation();
	});
	
	$("#prfgroupAuto").keyup(function(){
		$('select#prf_group').multiselect({
			includeSelectAllOption: true
	    });
	});
	$('select#prf_group').multiselect('rebuild');
	
    $("#prf_group").change(function(){
    	var selected = $("#prf_group option:selected");
    	if(selected.length == 0){
    		getchartdivPRFWiseList();
    	}
    	if(selected.length > 3){
    		alert("Please Select Max 3 PRF");
    	}
   		prf_group = getprfgroup();
		
   		$.post("getchartdivPRFWiseList?"+key+"="+value,{prf:prf_group}, function(j) {
  			var bde = new Array();
			for(var i=0;i<j.length;i++){
				if(i==0){
  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2]});	
	  			}else{
	  				bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2]} );
	  			}
  			}
  			UEUHBDEClick_C_Veh(bde);
  		});
   	});
 	$("#prf_groupEraser").click(function(){
 		var selected = $("#prf_group option:selected");
 		var val;
 	    selected.each(function () {
 	    	val =  $(this).val();
 	    	$('select#prf_group').multiselect('deselect', val);
 	    });
 	    getchartdivPRFWiseList();
    });
});

function getchartdivPRFWiseList(){
	$.post("getchartdivPRFWiseList?"+key+"="+value,{prf:'0'}, function(j) {
		var prf = new Array();
		for(var i=0;i<j.length;i++){
			if(i==0){
				prf.push({year:j[i][0],ue:j[i][1],uh:j[i][2]});	
			}else{
				prf.push({year:j[i][0],ue:j[i][1],uh:j[i][2]} );
			}
		}
		UEUHBDEClick_C_Veh(prf);
	});
}


function getprfgroup() {
	var selected = $("#prf_group option:selected");
	var val;
    var prf_group = "";
    var count = 0;
    selected.each(function () {
    	val =  $(this).val();
    	count++;
    	if(count > 3){
    		$('select#prf_group').multiselect('deselect', val);
    	}else{
    		if(prf_group == ""){
	    		prf_group =  val;
	    	}else{
	    		prf_group =  prf_group +','+val;
	    	}
    	}
    });
    return prf_group;
}
</script>
