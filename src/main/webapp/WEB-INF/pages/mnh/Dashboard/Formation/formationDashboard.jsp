<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<title>Formation Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>

<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="js/progresBar/circle-progress.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<link rel="stylesheet" href="js/miso/formationDashboard/fornationDashboardCSS.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<!-- <script src="js/cue/printAllPages.js" type="text/javascript"></script> -->

<style type="text/css">
	.dataTable{
	    position: relative;
	    z-index: -2;	
	}
		table.dataTable, table.dataTable th, table.dataTable td {
			-webkit-box-sizing: content-box;
			-moz-box-sizing: content-box;
			box-sizing: content-box;
			width: 100%; 
			text-align: left;
			font-size: 12px;
			padding: 0px;
			font-weight: bold;
		}
		.dataTables_scrollHead {
			/* overflow-y:scroll !important; */
			overflow-x:hidden !important;
		}


		table.dataTable tr.odd {
	  			background-color: #f0e2f3;
		}
		table.dataTable thead {
  			background-color: #9c27b0;
  			color: white;
  			font-weight: bold;
		}
		
		.dataTables_paginate{
			margin-top: 4px;
		}
		.dataTables_paginate a{
			background-color: #9c27b0;
			border: 1px solid #9c27b0;
			color: #fff;
			border-radius: 5px;
			padding: 3px 10px;
			margin-right: 5px;
		}
		.dataTables_paginate a:hover{
			background-color: #cb5adf;
			border-color: #cb5adf;
		}
		.dataTables_info{
			color:#9c27b0 !important;
			font-weight: bold;
		}
		
		.print_btn{
		  margin:0 auto;
		}
		.print_btn input{
		  background-color: #9c27b0;
          border-color: #9c27b0;
		}
		.print_btn input:hover{
			background-color: #cb5adf;
			border-color: #cb5adf;
		}
		.watermarked::before{
		  opacity: 0.4;
		}
	</style>

<script type="text/javascript">
$(document).ready(function () {
	
    $('body').bind('cut copy paste', function (e) {
        e.preventDefault();
    });
   
    $("body").on("contextmenu",function(e){
        return false;
    });
});
</script>

</head>
<body>
	<div class="animated fadeIn" >

				<div class="card">
					<div id="DASHBOARD_tabs">
	      				<h5 class="trans_heading">FORMATION DASHBOARD</h5>
    						<div class="card-body card-block">
							<div class="row" id="divCMDUSerID">
    						
							<div class="col-lg-12 col-xl-4 divide_border">
									<h5 class="h5">Inter Command Move Details</h5>
									<div class="row" style="align-items: flex-end !important;">
									<div class="col-lg-4 col-xl-4"><b>From Date : &nbsp;</b><input type="date" id="fromDateChord"  style="width: 130px;">
									</div>
									<div class="col-lg-4 col-xl-4"><b>To Date : &nbsp;</b><input type="date" id="toDateChord" style="width: 130px;">
									</div>
									<div class="col-lg-2 col-xl-2"><button id="btnChord" class="btn btn-primary btn-sm" onclick="chrodChartCommandMov();">Show Chart</button></div>
								</div>
									<div class="col-md-12">																	
										<div id="chordCommdMovdiv" style="width: 100%; height: 400px;"></div>
									</div>	
									
									<div class="info-box bg-indigo" onclick="showUnitMovReport();" style="position: absolute;bottom: 0;right: 0;">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 12px; ">Unit Move Details</h5>
											<div class="number count-to">
												<a>
													<label id="getActUnitsInFormation"><%-- ${getActUnitsInFormation[0].total} --%></label>
												</a>
											</div>
										</div>
									</div>						
								</div>	<!--end of col-md-4-->		
											
								<div class="col-lg-12 col-xl-8">
									<%-- <h5 class="h5">${roleSubAccess111} Wise Status</h5>
									<br>													
									<div class="col-md-4" style="text-align: right;"><b>From Date : &nbsp;</b><input type="date" id="fromDateClustCmdAct"  style="width: 130px;"></div>
									<div class="col-md-4"><b>To Date : &nbsp;</b><input type="date" id="toDateClustCmdAct" style="width: 130px;"></div>
									<div class="col-md-4" style="text-align: right;"><button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="callAmClusterdChartCommandByAction();">Show Chart</button></div>
									<div class="col-md-12">
										<div id="clusterCommandByActiondiv" style="width: 100%; height: 400px;"></div>
									</div> --%>
									<h5 class="h5">Unit Entitlement of Manpower</h5><br>
									<div class="col-md-12">									
																					
								<div id="ueManpowerdiv" style="width: 100%; height: 440px;"></div>
							</div>																
								</div>
												
						</div> <!--end of row-->
						
							<div class="row"> <div class="col-md-12"><span class="line"></span></div> </div>	
							<div class="row"  style="align-items: flex-start !important;">
							
								<div class="col-lg-12 col-xl-7 divide_border">									
									<h5 class="h5">Transport Summary</h5>	
									<div class="row" style="align-items: flex-end !important;">								
									<div class="col-lg-5 col-xl-5" style="text-align: right;"><b>Type of Vehicle : &nbsp;</b>
										<select id="typeOfVehicle" onchange="getPRFList(this.value)">
											<option value="">--Select--</option>
											<option value="0">A Vehicles</option>
											<option value="1" selected="selected">B Vehicles</option>
											<option value="2">C Vehicles</option>
										</select>
									</div>
									<div class="col-lg-5 col-xl-5"><b>PRF Name : &nbsp;</b>
										<select id="prfTptList"></select>
									</div>
									<div class="col-lg-2 col-xl-2"><button id="btnTptSumm" class="btn btn-primary btn-sm" onclick="tptSummaryChart();prfWiseTptClassChart();">Show Chart</button></div>
									</div>
										<div id="tptSummarydiv" style="width: 100%; height: 350px;"></div>								
								</div>	
								<div class="col-lg-12 col-xl-5">
								<h5 class="h5">Vehicle Casualty Return Updation State</h5>
									<table border="1" id="typeOfVehicleTbl" class="table no-margin table-striped table-hover">
										<thead>											
											<tr style="background-color: darkseagreen;text-align: center;">
												<th style="width: 130px;">Type of Vehicle</th>
												<th>Data Updated</th>
												<th>Yet to Update</th>
												<th>Total</th>
											</tr>
										</thead>
										<tbody style="text-align: center;">
											<c:forEach var="item" items="${getVehicleListInFRM}" varStatus="num" >
												<tr>
													<td>A</td>
													<td>${item.a_up}</td>
													<td>${item.a_yet}</td>
												 	<td><b><a href='#' onclick='openAMvcrUpdateReport();' style='color: blue'><u> ${item.a_up + item.a_yet} </u></a></b></td>
												</tr>
												<tr>
													<td>B</td>
													<td>${item.b_up}</td>
													<td>${item.b_yet}</td>
												 	<td><b><a href='#' onclick='openBMvcrUpdateReport();' style='color: blue'><u> ${item.b_up + item.b_yet} </u></a></b></td> 
												</tr>
												<tr>
													<td>C</td>
													<td>${item.c_up}</td>
													<td>${item.c_yet}</td>
												 	<td><b><a href='#' onclick='openCMvcrUpdateReport();' style='color: blue; '><u> ${item.c_up + item.c_yet} </u></a></b></td> 
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">
									<div class="col-md-4" style="height: 50%;">
										<h5 class="h5" style="color: maroon;">A Vehicles</h5>
										<a href="#" style="text-align:center;">
											<div class="progressbar" id="progressbaraveh" data-animate="false">
												<div class="circle" data-percent="0" id="avehProgress">
													<div id="aveh"></div>
												</div>
											</div>
										</a> 
									</div>
									<div class="col-md-4" style="height: 50%;">
										<h5 class="h5" style="color: maroon;">B Vehicles</h5>
										<a href="#" style="text-align:center;">
											<div class="progressbar" id="progressbar" data-animate="false">
												<div class="circle" data-percent="0" id="mvcrProgress">
													<div id="mvcr"></div>
												</div>
											</div>
										</a> 
									</div>
									<div class="col-md-4" style="height: 50%;">
										<h5 class="h5" style="color: maroon;">C Vehicles</h5>
										<a href="#" style="text-align:center;">
											<div class="progressbar" id="progressbarcveh" data-animate="false">
												<div class="circle" data-percent="0" id="cvehProgress">
													<div id="cveh"></div>
												</div>
											</div>
										</a> 
									</div>
								</div>					
								</div>
										
						    </div> <!--end of row-->
							<div class="row"><div class="col-md-12"><span class="line"></span></div></div>	
							<div class="row">			
							<div class="col-md-12" style="display:block;">									
								<h5 class="h5">PRF-wise Transport Classification</h5>													
								<div id="prfTptClassdiv" style="width: 100%; height: 350px;"></div>
							</div>
							</div> <!--end of row-->
							<div class="row"><div class="col-md-12"><span class="line"></span></div></div>
							<div class="row">
								<div class="col-lg-12 col-xl-6 divide_border">									
									<h5 class="h5">Weapon Holding State</h5>								
										<div id="wpnHoldingStateDiv" style="width: 100%; height: 350px;"></div>							
								</div>
									
								<div class="col-lg-12 col-xl-6">	
									<h5 class="h5">Equipment Holding State</h5>						
										<div id="eqptHoldingStateDiv" style="width: 100%; height: 350px;"></div>					
								</div>
							
							</div> <!--end of row-->									
							<div class="row"><div class="col-md-12"><span class="line"></span></div></div>		
							<div class="row">				
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('auth_esta_str_regarmy_half');">
									<div class="info-box bg-darkgreen">
										<h5 style="font-weight: bold; font-size: 14px;">Authorised Establishment and Held Strength of Regular Army (Commands and Formations)</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('stati_digest_manpwr_restr_half');">
									<div class="info-box bg-deep-purple">
										<h5 style="font-weight: bold; font-size: 14px;">Statistical Digest on Manpower</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('stati_digest_a_b_veh_conf_four');">
									<div class="info-box bg-darkyellow">
										<h5 style="font-weight: bold; font-size: 14px;">Statistical Digest on ‘A’ & ‘B’ Vehicles</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('stati_digest_arm_equi_conf_four');">
									<div class="info-box bg-green">
										<h5 style="font-weight: bold; font-size: 14px;">Statistical Digest on Armament and Equipment</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>	
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('stati_digest_fol_ration_losses_etc_restr_half');">
									<div class="info-box bg-indigo">
										<h5 style="font-weight: bold; font-size: 14px;">Statistical Digest on FOL, Ration, Losses, Animals, Labour etc.</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('stre_indian_army_conf_annual');">
									<div class="info-box bg-red">
										<h5 style="font-weight: bold; font-size: 14px;">Strength of the Indian Army</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>	
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('mt_accid_army_bveh_restr_annual');">
									<div class="info-box bg-sky">
										<h5 style="font-weight: bold; font-size: 14px;">MT Accidents (WIR) on Army ‘B’ Vehicles</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('studrpt_court_inquiry_restr_fiveyear');">
									<div class="info-box bg-maroon">										
										<h5 style="font-weight: bold; font-size: 14px;">Study Report on Closed Court of Inquiry</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>											
									</div>
								</div>								
							</div> <!--end of row-->
							</div>	<!--end of card-body card-block-->
	    			</div> <!--end of DASHBOARD_tabs-->
	    		</div> <!--end of card-->
			
	</div> <!--end of animated-->
	
<div id="myModalTptPrf" class="modal_new modal1">
	<div class="modal_new_content modal-content1">
		<button type="button" class="close close2" data-dismiss="modal1" aria-hidden="true">&times;</button>
		<h4 class="modal_title" align="center" id="h4HeadId"> </h4>	
		
		<div class="card">
			<div class="card-body card-block cue_text">
				<div class="col-md-12" style="display:block;">	
					<div id="tptSummaryPrfDiv" style="width: 100%; height: 550px;"></div>				
				</div> 
			</div>
		</div>
		
		<div style="float:right;">
			<button type="button" class="close1 close3" data-dismiss="modal1" aria-hidden="true">Close</button>
		</div>
	</div>
</div>
	
<div id="myModalUnitMovReport" class="modal_new modal2">
	<div class="modal_new_content modal-content1">
		<button type="button" class="close close4" data-dismiss="modal2" aria-hidden="true">&times;</button>
		<h4 class="modal-title" align="center" id="h4UnitMovHeadId"> </h4>						
		
		<div class="card">
			<div class="card-body card-block cue_text">
				<div class="col-md-12" id="divPrint" style="display:block;">					
					 <div id="divShow" style="display: block;"></div> 
					 <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block; z-index:1111">
						<span id="ip"></span>					 
							<table id="UnitMovReport" class="no-margin table-striped table-hover table-bordered report_print">
								<thead style="background-color: #9c27b0; color: white; text-align:center;">
									<tr>
									<th colspan="4"></th>
									<th colspan="4">From</th>
									<th colspan="4">To</th>
									</tr>
									<tr>
										<th>Ser No</th>
										<th>SUS No</th>
										<th>Unit Name</th>
										<th>NMB Date</th>
										<th>Command</th>
										<th>Corps</th>
										<th>Division</th>
										<th>Brigade</th>
										<th>Command</th>
										<th>Corps</th>
										<th>Division</th>
										<th>Brigade</th>										
										<!-- <th>No of Units</th> -->											
									</tr>
																
								</thead> 
							  <tbody id="UnitMovReportTbody" >
							  <%-- <c:forEach var="item" items="${getUnitMovReport}" varStatus="num" >
								<tr>
									<th>${num.index+1}</th>
									<th>${item.from_cmd_name}</th>
									<th>${item.to_form_cmd_name}</th>
									<th>${item.count}</th>
								</tr>
							</c:forEach> --%>
							</tbody>
					  </table>
					</div>	
			
				</div> 
			</div>
		</div>
		
		<div style="float:right;">
			<button type="button" class="close1 close5" data-dismiss="modal2" aria-hidden="true">Close</button>
		</div>
	</div>
</div>
	
<script>
$(document).ready(function () {
	var dtToday = new Date();    
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;
    //var minDate = year + '-01-01';
    
    var x = 6; //or whatever offset
    var CurrentDate = new Date();
    CurrentDate.setMonth(CurrentDate.getMonth() + x);
    var monthf = CurrentDate.getMonth() + 1;
    var dayf = CurrentDate.getDate();
    var yearf = CurrentDate.getFullYear();
    if(monthf < 10)
        monthf = '0' + monthf.toString();
    if(dayf < 10)
        dayf = '0' + dayf.toString();
    
    var futureDate = yearf + '-' + monthf + '-' + dayf;
    
   /* 	$('#fromDateChord').attr('max', maxDate);
    $('#toDateChord').attr('max', maxDate); */
    $('#fromDateChord').val(maxDate);
    $('#toDateChord').val(futureDate);
    
    /* $("#fromDateClustCmdAct").attr('max', maxDate);
    $("#toDateClustCmdAct").attr('max', maxDate);
    $("#fromDateClustCmdAct").val(minDate);
    $("#toDateClustCmdAct").val(maxDate); */
    
    if('${roleSubAccess111}' == "Command")
    	$("div#divCMDUSerID").show();
    else
    	$("div#divCMDUSerID").hide();
    
   	$.ajaxSetup({
   	    async: false
   	});
	////========== comment start for modal chart
	var modal = document.getElementById('myModalTptPrf');
	var span = document.getElementsByClassName("close2")[0];
	// Close button on click, close it
	span.onclick = function() {
	    modal.style.display = "none";
	}
	var span1 = document.getElementsByClassName("close3")[0];
	span1.onclick = function() {
	    modal.style.display = "none";
	}
	////========== comment end for modal chart
	
	////========== comment start for modal report
	var modalr = document.getElementById('myModalUnitMovReport');
	var spanr = document.getElementsByClassName("close4")[0];
	// Close button on click, close it
	spanr.onclick = function() {
	    modalr.style.display = "none";
	}
	var span1r = document.getElementsByClassName("close5")[0];
	span1r.onclick = function() {
	    modalr.style.display = "none";
	}
	////========== comment end for modal report
	$.ajaxSetup({
	    async: false
	});	
	var type=$("select#typeOfVehicle").val();
	getPRFList(type);
	
	//callAmClusterdChartCommandByAction();
	$.ajaxSetup({
	    async: false
	});
	chrodChartCommandMov();
	$.ajaxSetup({
	    async: false
	});	
	
	tptSummaryChart();
	$.ajaxSetup({
	    async: false
	});
	prfWiseTptClassChart();
	
	$.ajaxSetup({
	    async: false
	});
	ueManpowerChart();
	
	$.ajaxSetup({
	    async: false
	});
	wpnHoldingStateChart();
	$.ajaxSetup({
	    async: false
	});
	eqptHoldingStateChart();
	
	$.ajaxSetup({
	    async: false
	});
	var mvcrProgressCount = (parseInt('${getVehicleListInFRM[0].b_up}')/parseInt('${getVehicleListInFRM[0].b_up+getVehicleListInFRM[0].b_yet}')) * parseInt(100); 
	var avehProgressCount = (parseInt('${getVehicleListInFRM[0].a_up}')/parseInt('${getVehicleListInFRM[0].a_up+getVehicleListInFRM[0].a_yet}')) * parseInt(100); 
	var cvehProgressCount = (parseInt('${getVehicleListInFRM[0].c_up}')/parseInt('${getVehicleListInFRM[0].c_up+getVehicleListInFRM[0].c_yet}')) * parseInt(100); 
	progressBar(mvcrProgressCount,avehProgressCount,cvehProgressCount); 
});

function getPRFList(val)
{
	if(val !="")
	{
		$.getJSON("getTptSummaryInPRFList",{type : val}, function(j) {
			var options = '<option value="">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length; i++) {
				options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name+ '</option>';
			}	
			$("select#prfTptList").html(options); 
		});
	}
	else
		$("select#prfTptList").html("");
}

///////////////////////
var popupWindow = null
function openAMvcrUpdateReport(url) {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("AMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("AMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
}
var popupWindow = null;
function openBMvcrUpdateReport(url) {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("MvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("MvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
}
var popupWindow = null;
function openCMvcrUpdateReport(url) {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("CMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("CMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
}

function showUnitMovReport()
{
	if(document.getElementById("getActUnitsInFormation").innerHTML == 0)
	{
		alert("Data Not Available");		
	}	
	else{
		var modal = document.getElementById('myModalUnitMovReport');
		modal.style.display = "block";	
		document.getElementById("h4UnitMovHeadId").innerHTML="Inter Command Move Report";
		
		var fromDate=$("#fromDateChord").val();
		var toDate=$("#toDateChord").val();
	
		$.ajaxSetup({
		    async: false
		});					
	    $.getJSON("getUnitMovReport",{fromDate: fromDate, toDate: toDate}, function(j) {
	    	$("#UnitMovReportTbody").empty();
	    	if(j.length>0)
	    	{
	    		var row="", m=0;
				for(var i=0;i<j.length;i++){
					m=i+1;
					/* row += "<tr>";
					row += "<th style='text-align:center;'>"+m+"</th>";
					row += "<th>"+j[i].from_cmd_name+"</th>";
					row += "<th>"+j[i].to_form_cmd_name+"</th>";
					row += "<th style='text-align:center;'>"+j[i].count+"</th>";
					row += "</tr>"; */
					
					row += "<tr>";
					row += "<th style='text-align:center;'>"+m+"</th>";
					row += "<th>"+j[i].sus_no+"</th>";
					row += "<th>"+j[i].unit_name+"</th>";
					row += "<th>"+j[i].nmb_date+"</th>";					
					row += "<th>"+j[i].frm_cmd_name+"</th>";
					row += "<th>"+j[i].frm_coprs_name+"</th>";
					row += "<th>"+j[i].frm_div_name+"</th>";
					row += "<th>"+j[i].frm_bde_name+"</th>";
					row += "<th>"+j[i].to_cmd_name+"</th>";
					row += "<th>"+j[i].to_coprs_name+"</th>";
					row += "<th>"+j[i].to_div_name+"</th>";
					row += "<th>"+j[i].to_bde_name+"</th>";
					/* row += "<th style='text-align:center;'>"+j[i].count+"</th>"; */
					row += "</tr>";
				}
				$("#UnitMovReportTbody").append(row);
				
				$("#UnitMovReport").DataTable();				
				$("div#divwatermark").val('').addClass('watermarked');		
				watermarkreport(); 
	    	}
	    		
	    });	    
		
	}
}
</script>

<script>
function chrodChartCommandMov()
{	
	if($("#fromDateChord").val() == "")
	{
		alert("Please select From");
		$("#fromDateChord").focus();
		return false;
	}
	else if($("#toDateChord").val() == "")
	{
		alert("Please select To Date");
		$("#toDateChord").focus();
		return false;
	}	
	else{
		var fromDate=$("#fromDateChord").val();
		var toDate=$("#toDateChord").val();

		$.ajaxSetup({
		    async: false
		});					
	    $.getJSON("getCommWiseUnitMovFormation",{fromDate: fromDate, toDate: toDate}, function(j) {
				var cmdArray = new Array();	
				var totalArray = new Array();
	  			for(var i=0;i<j.length;i++){	  			  				
	  				var cmdname="";
	  				if(j[i].cmd_code == "1"){ cmdname="SC"};
	  				if(j[i].cmd_code == "2"){ cmdname="EC"};
	  				if(j[i].cmd_code == "3"){ cmdname="WC"};
	  				if(j[i].cmd_code == "4"){ cmdname="CC"};
	  				if(j[i].cmd_code == "5"){ cmdname="NC"};
	  				if(j[i].cmd_code == "6"){ cmdname="ARTRAC"};
	  				if(j[i].cmd_code == "7"){ cmdname="ANC"};
	  				if(j[i].cmd_code == "8"){ cmdname="SWC"};
	  				if(j[i].cmd_code == "9"){ cmdname="UN"};
	  				if(j[i].cmd_code == "0"){ cmdname="MOD"};
	  				if(j[i].cmd_code == "A"){ cmdname="SFC"};	  				
	  				
  					if(!j[i].count1 == 0 && cmdname !="")
  					{
  						cmdArray.push({from: cmdname, to: "SC", value: j[i].count1});
  						totalArray.push(j[i].count1);
  					}
	  				if(!j[i].count2 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "EC", value: j[i].count2});
	  					totalArray.push(j[i].count2);
	  				}
	  				if(!j[i].count3 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "WC", value: j[i].count3});
	  					totalArray.push(j[i].count3);
	  				}
	  				if(!j[i].count4 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "CC", value: j[i].count4});
	  					totalArray.push(j[i].count4);
	  				}
	  				if(!j[i].count5 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "NC", value: j[i].count5});
	  					totalArray.push(j[i].count5);
	  				}
	  				if(!j[i].count6 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "ARTRAC", value: j[i].count6});
	  					totalArray.push(j[i].count6);
	  				}
	  				if(!j[i].count7 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "ANC", value: j[i].count7});
	  					totalArray.push(j[i].count7);
	  				}
	  				if(!j[i].count8 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "SWC", value: j[i].count8});
	  					totalArray.push(j[i].count8);
	  				}
	  				if(!j[i].count9 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "UN", value: j[i].count9});
	  					totalArray.push(j[i].count9);
	  				}
	  				if(!j[i].count10 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "MOD", value: j[i].count10});
	  					totalArray.push(j[i].count10);
	  				}
	  				if(!j[i].count11 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "SFC", value: j[i].count11});
	  					totalArray.push(j[i].count11);
	  				}
	  			}	    
	  			
	  			var totalVal=totalArray.reduce((a, b) => a + b, 0);	  			
	  			document.getElementById("getActUnitsInFormation").innerHTML=totalVal;
	  			
	    	am4core.useTheme(am4themes_animated);
    		var chart = am4core.create("chordCommdMovdiv", am4charts.ChordDiagram);
    		chart.data= cmdArray;
    		
    	    chart.dataFields.fromName = "from";
    		chart.dataFields.toName = "to";
    		chart.dataFields.value = "value";
    		
    		var colorSet = new am4core.ColorSet();
    		//colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
   			colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
   				return new am4core.color(color);
			});
			chart.colors = colorSet;	
			
    		// make nodes draggable
    		var nodeTemplate = chart.nodes.template;
    		nodeTemplate.readerTitle = "Click to show/hide or drag to rearrange";
    		nodeTemplate.showSystemTooltip = true;
    		nodeTemplate.cursorOverStyle = am4core.MouseCursorStyle.pointer;
    		
    		var nodeLink = chart.links.template;
    		nodeLink.opacity = 1;
    		nodeLink.defaultState.properties.opacity = 1;
    		
    		var linkHoverState = nodeLink.states.create("hover");
    		linkHoverState.properties.opacity = 1.5;
    		//linkHoverState.properties.scale = 1.1;
    		
    		var bullet = nodeLink.bullets.push(new am4charts.CircleBullet());
    		bullet.fillOpacity = 1.5;
    		bullet.circle.radius = 5;
    		bullet.locationX = 1;	
    	
    		// create animations
    		chart.events.on("ready", function() {
    		    for (var i = 0; i < chart.links.length; i++) {
    		        var link = chart.links.getIndex(i);
    		        var bullet = link.bullets.getIndex(0);
    	
    		        animateBullet(bullet);
    		    }
    		})
    	
    		function animateBullet(bullet) {
    		    var duration = 3000 * Math.random() + 4500;
    		    var animation = bullet.animate([{ property: "locationX", from: 0, to: 1 }], duration)
    		    animation.events.on("animationended", function(event) {
    		    	 animateBullet(event.target.object);
    		    })
    		}
    		
    		// Control width of the node
    		chart.innerRadius = -20;
    	
    		// Configure ribbon appearance
    		var slice = chart.nodes.template.slice;
    		slice.stroke = am4core.color("#000");
    		slice.strokeOpacity = 0.5;
    		slice.strokeWidth = 1;
    		slice.cornerRadius = 8;
    		slice.innerCornerRadius = 0;
    			
    		// Configure labels
    		var label = chart.nodes.template.label;
    		label.fontSize = 11;
    		label.fontWeight="bold";
    		label.fill = am4core.color("#555");
    		label.horizontalCenter = "middle";
    		label.verticalCenter = "middle";
    		label.wrap = true;	
    		//label.truncate = true;
    		label.maxWidth = 100;
    		label.rotation = 360;	
    		
    		// Configure links
    		chart.nonRibbon = false;	
    		var link = chart.links.template;
    		link.colorMode = "gradient";
    		link.fillOpacity = 0.5;
    		link.middleLine.strokeWidth = 2;
    		link.middleLine.strokeOpacity = 0.4;
	    }); 

	}	
	
}

</script>

<script type="text/javascript">
function callAmClusterdChartCommandByAction()
{		
	if($("#fromDateClustCmdAct").val() == "")
	{
		alert("Please select From Date");
		$("#fromDateClustCmdAct").focus();
		return false;
	}
	else if($("#toDateClustCmdAct").val() == "")
	{
		alert("Please select To Date");
		$("#toDateClustCmdAct").focus();
		return false;
	}	
	else{
		var fromDate=$("#fromDateClustCmdAct").val();
		var toDate=$("#toDateClustCmdAct").val();
		
		$.ajaxSetup({
		    async: false
		});					
	    $.getJSON("getCommWiseActFormation",{fromDate: fromDate, toDate: toDate}, function(j) {
			var cmdArray = new Array();				
  			for(var i=0;i<j.length;i++){	  			  				
  				var cmdname=j[i].cmd_name;		
  				if(cmdname != "")
 					cmdArray.push({'cmd_name': cmdname,'nrus': j[i].nrus, 'disbanded': j[i].disbanded, 'converted': j[i].converted, 'unit_move_details': j[i].unit_move_details});  				 			
  			}	    
		
  			if(cmdArray.length > 0){
				am4core.useTheme(am4themes_animated);
				var clusterddiv = am4core.create("clusterCommandByActiondiv", am4charts.XYChart);
				clusterddiv.scrollbarX = new am4core.Scrollbar();			
				
				clusterddiv.data =cmdArray;
				
				var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
				categoryAxis_cluster.dataFields.category = "cmd_name";
				categoryAxis_cluster.title.text ="";  // "Command Name";
				categoryAxis_cluster.renderer.grid.template.location = 0;
				categoryAxis_cluster.renderer.minGridDistance = 30;
				categoryAxis_cluster.renderer.cellStartLocation = 0.1;
				categoryAxis_cluster.renderer.cellEndLocation = 0.9;
				categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
				categoryAxis_cluster.renderer.labels.template.rotation = 360; 
									
				var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
				valueAxis_cluster.min = 0;
				valueAxis_cluster.title.text =""; // "No. of Units";
				valueAxis_cluster.rangeChangeDuration = 500;
				
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
				//colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
				colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
					 return new am4core.color(color);
				});
				clusterddiv.colors = colorSet;	
				
				// Create series
				function createSeries_cluster(field, name) {
				  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
				  	series_cluster.dataFields.valueY = field;
				  	series_cluster.dataFields.categoryX = "cmd_name";
				  	series_cluster.name = name;
				  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
				  	series_cluster.columns.template.height = am4core.percent(100);
				  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
				  	series_cluster.sequencedInterpolation = true;
				}
				
				createSeries_cluster("nrus", "NRU's", true);
				createSeries_cluster("converted", "Converted", true);
				createSeries_cluster("disbanded", "Disbanded", true);
				createSeries_cluster("unit_move_details", "Unit Mov Details", true);	
				
				clusterddiv.legend = new am4charts.Legend();
				//clusterddiv.legend.position = "right";	
				clusterddiv.legend.useDefaultMarker = true;	
				var marker = clusterddiv.legend.markers.template.children.getIndex(0);
				marker.cornerRadius(12, 12, 12, 12);
				marker.strokeWidth = 2;
				marker.strokeOpacity = 1;
				marker.stroke = am4core.color("#ccc");
  			}
	    }); 
	}
}
</script>

<script type="text/javascript">
function tptSummaryChart()
{		
	if($("select#typeOfVehicle").val() == "")
	{
		alert("Please select Type of Vehicle");
		$("select#typeOfVehicle").focus();
		$("div#tptSummarydiv").empty();
		return false;
	}
	else{
		var type=$("select#typeOfVehicle").val();
		
		$.ajaxSetup({
		    async: false
		});
		var colArray = new Array();				
		if($("select#prfTptList").val() == ""){	
		    $.getJSON("getTptSummaryData",{type: type}, function(j) {
				for(var i=0;i<j.length;i++){	  			  				
	  				var colname=j[i].colname;		
	  				if(colname != "")
	  					colArray.push({'colname': colname,'colcode': j[i].colcode,'ue': j[i].ue, 'uh': j[i].uh});  				 			
	  			}	    
		    });
		}
		else{
		    $.getJSON("getTptSummaryInPrfWithTypeVehData",{type: type, prf_code : $("select#prfTptList").val()}, function(j) {
				for(var i=0;i<j.length;i++){	  			  				
	  				var colname=j[i].colname;		
	  				if(colname != "")
	  					colArray.push({'colname': colname,'colcode': j[i].colcode,'ue': j[i].ue, 'uh': j[i].uh});  				 			
	  			}	    
		    });
		}
 		if(colArray.length > 0){
			am4core.useTheme(am4themes_animated);
			var clusterddiv = am4core.create("tptSummarydiv", am4charts.XYChart);
			clusterddiv.scrollbarX = new am4core.Scrollbar();			
			
			clusterddiv.data =colArray;
			
			var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
			categoryAxis_cluster.dataFields.category = "colname";
			categoryAxis_cluster.title.text ="";  // "Command Name";
			categoryAxis_cluster.renderer.grid.template.location = 0;
			categoryAxis_cluster.renderer.minGridDistance = 30;
			categoryAxis_cluster.renderer.cellStartLocation = 0.1;
			categoryAxis_cluster.renderer.cellEndLocation = 0.9;
			categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
			categoryAxis_cluster.renderer.labels.template.rotation = 360; 
								
			var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
			valueAxis_cluster.min = 0;
			valueAxis_cluster.title.text =""; // "No. of Units";
			valueAxis_cluster.rangeChangeDuration = 500;
			
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
			//colorSet.list = ["#008080", "#cc0052", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
			colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
				return new am4core.color(color);
			});
			clusterddiv.colors = colorSet;	
			clusterddiv.maskBullets = true;
			//clusterddiv.paddingBottom = 150;

			// Create series
			function createSeries_cluster(field, name) {
			  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
			  	series_cluster.dataFields.valueY = field;
			  	series_cluster.dataFields.categoryX = "colname";
			  	series_cluster.name = name;
			  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
			  	series_cluster.columns.template.height = am4core.percent(100);
			  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
			  	series_cluster.sequencedInterpolation = true;
			  	
			  	var bullet = series_cluster.bullets.push(new am4charts.LabelBullet);
			    bullet.label.text = "{colcode}";
			    bullet.label.rotation = 90;
			    bullet.label.truncate = false;
			    bullet.label.hideOversized = false;
			    bullet.label.horizontalCenter = "left";
			    bullet.locationY = 1;
			    bullet.dy = 10;
			    
			    series_cluster.columns.template.events.on("hit",function(ev){
			  		if($("select#prfTptList").val() == ""){	
			  			alert("Please select PRF Name");
			  			$("select#prfTptList").focus();
			  			return false;
			  		}
			  		else{
			  			//alert(ev.target.dataItem.dataContext.colcode);				  		
				  		var val=ev.target.dataItem.dataContext.colcode;
				  		$.getJSON("getVehWiseAvgKMSData",{type: type,form_code : val, prf_code : $("select#prfTptList").val()}, function(j) {	  			
			  				var getVehWiseAvgKMS = new Array();
				  	 		for(var i=0;i<j.length;i++){	  			  				
				  				var avg=j[i].avg;
				  				if(avg != "")
				  					getVehWiseAvgKMS.push(avg);  				 			
				  			}	    
						
				  			if(getVehWiseAvgKMS.length > 0){
				  				var modal = document.getElementById('myModalTptPrf');
				  				modal.style.display = "block";
				  				
				  				document.getElementById("h4HeadId").innerHTML="Avg KMS In "+$("select#prfTptList option:selected").text();
				  				vehiclesWiseAvgKMS(getVehWiseAvgKMS);
				  			}
				  			else
				  			{
				  				var modal = document.getElementById('myModalTptPrf');
				  				modal.style.display = "none";	
				  				document.getElementById("h4HeadId").innerHTML="";
				  			}  		
				  		});
				  		
				  		/* $.getJSON("getTptSummaryInPrfData",{type: type,colcode : val}, function(j) {	  			
			  				var getTptWisePrf = new Array();
				  	 		for(var i=0;i<j.length;i++){	  			  				
				  				var group_name=j[i].group_name;
				  				if(group_name != "")
				  					getTptWisePrf.push({'group_name': group_name,'ue': j[i].ue, 'uh': j[i].uh});  				 			
				  			}	    
						
				  			if(getTptWisePrf.length > 0){
				  				var modal = document.getElementById('myModalTptPrf');
				  				modal.style.display = "block";
				  				
				  				document.getElementById("h4HeadId").innerHTML="${roleSubAccess111}"+" In PRF Wise TPT Summary";
				  				tptSummaryByPrf(getTptWisePrf);
				  			}
				  			else
				  			{
				  				var modal = document.getElementById('myModalTptPrf');
				  				modal.style.display = "none";	
				  				document.getElementById("h4HeadId").innerHTML="";
				  			}  		
				  		}); */ 			  		
			  		}
			  	},this); 
			}
			
			createSeries_cluster("ue", "UE", true);
			createSeries_cluster("uh", "UH", true);				
			
			clusterddiv.legend = new am4charts.Legend();
			//clusterddiv.legend.position = "right";	
			clusterddiv.legend.useDefaultMarker = true;	
			var marker = clusterddiv.legend.markers.template.children.getIndex(0);
			marker.cornerRadius(12, 12, 12, 12);
			marker.strokeWidth = 2;
			marker.strokeOpacity = 1;
			marker.stroke = am4core.color("#ccc"); 
 		}
		else
		{
			alert("Data not available");
			$("div#tptSummarydiv").empty();
		}
	   
	}
}

function vehiclesWiseAvgKMS(getVehWiseAvgKMS)
{
	var getVal=0;
	if(getVehWiseAvgKMS[0] != null && getVehWiseAvgKMS[0] != "null")
		getVal=getVehWiseAvgKMS[0];
	
	am4core.useTheme(am4themes_animated);	
	var chart3 = am4core.create("tptSummaryPrfDiv", am4charts.GaugeChart);
	chart3.innerRadius = am4core.percent(82);
	
	var colorSet = new am4core.ColorSet();
	//colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	chart3.colors = colorSet;	
	
	var axis3 = chart3.xAxes.push(new am4charts.ValueAxis());
	axis3.strictMinMax = true;
	axis3.renderer.radius = am4core.percent(80);
	axis3.renderer.inside = true;
	axis3.renderer.line.strokeOpacity = 1;
	axis3.renderer.ticks.template.strokeOpacity = 1;
	axis3.renderer.ticks.template.length = 10;
	axis3.renderer.grid.template.disabled = false;
	axis3.renderer.labels.template.radius = 40;
	axis3.renderer.labels.template.adapter.add("text", function(text) {
	  return text + "%";
	})

	//var colorSet = new am4core.ColorSet();
	var axis2 = chart3.xAxes.push(new am4charts.ValueAxis());
	axis2.min = 0;
	axis2.max = 100000;
	axis2.renderer.innerRadius = 10
	axis2.strictMinMax = true;
	axis2.renderer.labels.template.disabled = false;
	axis2.renderer.ticks.template.disabled = false;
	axis2.renderer.grid.template.disabled = false;

	var range1 = axis2.axisRanges.create();
	range1.value = 0;
	range1.endValue = 10000;
	range1.axisFill.fillOpacity = 1;
	range1.axisFill.fill = colorSet.getIndex(0);

	var range2 = axis2.axisRanges.create();
	range2.value = 10000;
	range2.endValue = 20000;
	range2.axisFill.fillOpacity = 1;
	range2.axisFill.fill = colorSet.getIndex(1);
	
	var range3 = axis2.axisRanges.create();
	range3.value = 20000;
	range3.endValue = 30000;
	range3.axisFill.fillOpacity = 1;
	range3.axisFill.fill = colorSet.getIndex(2);
	
	var range4 = axis2.axisRanges.create();
	range4.value = 30000;
	range4.endValue = 40000;
	range4.axisFill.fillOpacity = 1;
	range4.axisFill.fill = colorSet.getIndex(3);
	
	var range5 = axis2.axisRanges.create();
	range5.value = 40000;
	range5.endValue = 50000;
	range5.axisFill.fillOpacity = 1;
	range5.axisFill.fill = colorSet.getIndex(4);
	
	var range6 = axis2.axisRanges.create();
	range6.value = 50000;
	range6.endValue = 60000;
	range6.axisFill.fillOpacity = 1;
	range6.axisFill.fill = colorSet.getIndex(5);
	
	var range7 = axis2.axisRanges.create();
	range7.value = 60000;
	range7.endValue = 70000;
	range7.axisFill.fillOpacity = 1;
	range7.axisFill.fill = colorSet.getIndex(6);
	
	var range8 = axis2.axisRanges.create();
	range8.value = 70000;
	range8.endValue = 80000;
	range8.axisFill.fillOpacity = 1;
	range8.axisFill.fill = colorSet.getIndex(7);
	
	var range9 = axis2.axisRanges.create();
	range9.value = 80000;
	range9.endValue = 90000;
	range9.axisFill.fillOpacity = 1;
	range9.axisFill.fill = colorSet.getIndex(8);

	var range10 = axis2.axisRanges.create();
	range10.value = 90000;
	range10.endValue = 100000;
	range10.axisFill.fillOpacity = 1;
	range10.axisFill.fill = colorSet.getIndex(9);
	
	var label = chart3.radarContainer.createChild(am4core.Label);
	label.isMeasured = false;
	label.fontSize = 15;
	label.fontWeight = "bold";
	label.fill=am4core.color("#1a1aff");
	label.stroke=am4core.color("#1a1aff");
	label.strokeWidth = 0.6;
	label.strokeOpacity = 0.2;
	/* label.x = am4core.percent(100);
	label.y = am4core.percent(100); */
	/* label.horizontalCenter = "middle";
	label.verticalCenter = "bottom"; */
	label.text =getVal+" kms";

	var hand = chart3.hands.push(new am4charts.ClockHand());
	hand.axis = axis2;
	//hand.innerRadius = am4core.percent(20);
	hand.startWidth = 10;
	hand.pin.disabled = false;
	hand.value =getVal;

	/* hand.events.on("propertychanged", function(ev) {
	  range01.endValue = ev.target.value;
	  range11.value = ev.target.value;
	  axis2.invalidate();
	});	 */
}

function tptSummaryByPrf(getTptWisePrf)
{	
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("tptSummaryPrfDiv", am4charts.XYChart);
	clusterddiv.scrollbarX = new am4core.Scrollbar();			
	
	clusterddiv.data =getTptWisePrf;
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "group_name";
	categoryAxis_cluster.title.text =""; //"PRF Name";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
						
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text =""; // "No. of Units";
	valueAxis_cluster.rangeChangeDuration = 500;
	
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
	//colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	
	// Create series
	function createSeries_cluster(field, name) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "group_name";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;
	  	
	  	
	}
	
	createSeries_cluster("ue", "UE", true);
	createSeries_cluster("uh", "UH", true);
	
	clusterddiv.legend = new am4charts.Legend();
	//clusterddiv.legend.position = "right";	
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc");
}

</script>

<script type="text/javascript">
function prfWiseTptClassChart()
{		
	if($("select#typeOfVehicle").val() == "")
	{
		alert("Please select Type of Vehicle");
		$("select#typeOfVehicle").focus();
		return false;
	}
	else{
		var type=$("select#typeOfVehicle").val();
		
		$.ajaxSetup({
		    async: false
		});					
	    $.getJSON("getPRFWiseTptClassData",{type: type}, function(j) {
			var colArray = new Array();				
  			for(var i=0;i<j.length;i++){	  			  				
  				var group_name=j[i].group_name;		
  				if(group_name != "")
  					colArray.push({'group_name': group_name,'prf_code': j[i].prf_code,'cl1': j[i].cl1, 'cl2': j[i].cl2, 'cl3': j[i].cl3, 'cl4': j[i].cl4});  				 			
  			}	    
		
  			if(colArray.length > 0){
				am4core.useTheme(am4themes_animated);
				var clusterddiv = am4core.create("prfTptClassdiv", am4charts.XYChart);
				clusterddiv.scrollbarX = new am4core.Scrollbar();			
				
				clusterddiv.data =colArray;
				
				var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
				categoryAxis_cluster.dataFields.category = "group_name";
				categoryAxis_cluster.title.text =""; //"PRF Name";
				categoryAxis_cluster.renderer.grid.template.location = 0;
				categoryAxis_cluster.renderer.minGridDistance = 30;
				categoryAxis_cluster.renderer.cellStartLocation = 0.1;
				categoryAxis_cluster.renderer.cellEndLocation = 0.9;
				categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
				categoryAxis_cluster.renderer.labels.template.rotation = 360; 
									
				var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
				valueAxis_cluster.min = 0;
				valueAxis_cluster.title.text =""; // "No. of Units";
				valueAxis_cluster.rangeChangeDuration = 500;
				
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
				//colorSet.list = ["#1f5793", "#ff6633", "#26734d", "#993366", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
				colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
					return new am4core.color(color);
				});
				clusterddiv.colors = colorSet;	
				clusterddiv.maskBullets = true;
				
				// Create series
				function createSeries_cluster(field, name) {
				  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
				  	series_cluster.dataFields.valueY = field;
				  	series_cluster.dataFields.categoryX = "group_name";
				  	series_cluster.name = name;
				  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
				  	series_cluster.columns.template.height = am4core.percent(100);
				  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
				  	series_cluster.sequencedInterpolation = true;
				  	series_cluster.stacked = true;
				  	/* var bullet = series_cluster.bullets.push(new am4charts.LabelBullet);
				    bullet.label.text = "{prf_code}";
				    bullet.label.rotation = 90;
				    bullet.label.truncate = false;
				    bullet.label.hideOversized = false;
				    bullet.label.horizontalCenter = "left";
				    bullet.locationY = 1;
				    bullet.dy = 10;	 */			  
				}
				
				createSeries_cluster("cl1", "C-I", true);
				createSeries_cluster("cl2", "C-II", true);	
				createSeries_cluster("cl3", "C-III", true);
				createSeries_cluster("cl4", "C-IV", true);	
				
				clusterddiv.legend = new am4charts.Legend();
				//clusterddiv.legend.position = "right";	
				clusterddiv.legend.useDefaultMarker = true;	
				var marker = clusterddiv.legend.markers.template.children.getIndex(0);
				marker.cornerRadius(12, 12, 12, 12);
				marker.strokeWidth = 2;
				marker.strokeOpacity = 1;
				marker.stroke = am4core.color("#ccc"); 
  			}
	    }); 
	}
}
</script>

<script type="text/javascript">
function ueManpowerChart()
{		
	/* if($("select#typeOfVehicle").val() == "")
	{
		alert("Please select Type of Vehicle");
		$("select#typeOfVehicle").focus();
		return false;
	}
	else{
		var type=$("select#typeOfVehicle").val();
		
		$.ajaxSetup({
		    async: false
		});	*/				
	    $.getJSON("getUEManpowerData", function(j) {
			var colArray = new Array();				
  			for(var i=0;i<j.length;i++){	  			  				
  				var colname=j[i].colname;		
  				if(colname != "")
  					colArray.push({'colname': colname,'officer': j[i].officer,'jco': j[i].jco, 'jco_or': j[i].jco_or, 'or': j[i].or});  				 			
  			}	    
		
  			if(colArray.length > 0){ 
  				am4core.useTheme(am4themes_animated);
				var clusterddiv = am4core.create("ueManpowerdiv", am4charts.XYChart);
				clusterddiv.scrollbarX = new am4core.Scrollbar();			
				
				clusterddiv.data =colArray;  //${getUEManpowerData};
				
				var categoryAxis_cluster = clusterddiv.yAxes.push(new am4charts.CategoryAxis());
				categoryAxis_cluster.dataFields.category = "colname";
				categoryAxis_cluster.title.text =""; //"PRF Name";
				categoryAxis_cluster.renderer.grid.template.location = 0;
				categoryAxis_cluster.renderer.minGridDistance = 30;
				categoryAxis_cluster.renderer.cellStartLocation = 0.1;
				categoryAxis_cluster.renderer.cellEndLocation = 0.9;
				categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
				categoryAxis_cluster.renderer.labels.template.rotation = 360; 
									
				var  valueAxis_cluster = clusterddiv.xAxes.push(new am4charts.ValueAxis());
				valueAxis_cluster.min = 0;
				valueAxis_cluster.title.text =""; // "No. of Units";
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
				//colorSet.list = [ "#8E24AA", "#1BA68D", "#ff3377", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
				colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
					return new am4core.color(color);
				});
				clusterddiv.colors = colorSet;	
				clusterddiv.maskBullets = true;
				
				// Create series
				function createSeries_cluster(field, name) {
				  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
				  	series_cluster.dataFields.valueX = field;
				  	series_cluster.dataFields.categoryY = "colname";
				  	series_cluster.name = name;
				  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
				  	series_cluster.columns.template.height = am4core.percent(100);
				  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
				  	series_cluster.sequencedInterpolation = true;
				  	series_cluster.stacked = true;				  	
				  	/* var bullet = series_cluster.bullets.push(new am4charts.LabelBullet);
				    bullet.label.text = "{prf_code}";
				    bullet.label.rotation = 90;
				    bullet.label.truncate = false;
				    bullet.label.hideOversized = false;
				    bullet.label.horizontalCenter = "left";
				    bullet.locationY = 1;
				    bullet.dy = 10;	 */
				    
				  	var bullet1 = series_cluster.bullets.push(new am4charts.LabelBullet());
				  	bullet1.interactionsEnabled = false;
				  	bullet1.label.text = "{valueX}";
				  	bullet1.label.fill = am4core.color("#ffffff");
				  	bullet1.locationX = 0.5;
				  	bullet1.label.fontSize = 13;
				  	bullet1.label.fontWeight = "bold";
				}
				
				createSeries_cluster("officer", "Officer", true);
				createSeries_cluster("jco", "JCO", true);	
				createSeries_cluster("or", "OR", true);	
				//createSeries_cluster("jco_or", "JCO/OR", true);
				
				clusterddiv.legend = new am4charts.Legend();
				//clusterddiv.legend.position = "right";	
				clusterddiv.legend.useDefaultMarker = true;	
				var marker = clusterddiv.legend.markers.template.children.getIndex(0);
				marker.cornerRadius(12, 12, 12, 12);
				marker.strokeWidth = 2;
				marker.strokeOpacity = 1;
				marker.stroke = am4core.color("#ccc"); 
  			}
	    }); 
		/* } */
}
</script>

<script>
function progressBar(mvcrProgressCount,avehProgressCount,cvehProgressCount){
	if(mvcrProgressCount != 0){
		animateElements(mvcrProgressCount);
		$(window).scroll(animateElements);
	}
	if(avehProgressCount != 0){
		animateElementsAVeh(avehProgressCount);
		$(window).scroll(animateElementsAVeh);
	}
	if(cvehProgressCount != 0){
		animateElementsCVeh(cvehProgressCount);
		$(window).scroll(animateElementsCVeh);
	} 
}
function animateElements(mvcrProgressCount) {
	$('#progressbar').each(function () {
		var elementPos = $(this).offset().top;
		var topOfWindow = $(window).scrollTop();
		var percent = mvcrProgressCount;
		var percentage = parseInt(percent, 10) / parseInt(100, 10);
		
		var animate = $(this).data('animate');
		if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
			$(this).data('animate', true);
			$(this).find('.circle').circleProgress({
				startAngle: -Math.PI / 2,
				value: percent / 100,
				size: 160,
				thickness: 30,
				emptyFill: "#ffe6ee",
				fill: {
					color: '#990033'
				}
			}).on('circle-animation-progress', function (event, progress, stepValue) {
				$("div#mvcr").text((stepValue*100).toFixed(2) + "%");
			}).stop();
		}
	});
}

function animateElementsAVeh(avehProgressCount) {
	$('#progressbaraveh').each(function () {
		var elementPos = $(this).offset().top;
		var topOfWindow = $(window).scrollTop();
		var percent = avehProgressCount;
		var percentage = parseInt(percent, 10) / parseInt(100, 10);
		
		var animate = $(this).data('animate');
		if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
			$(this).data('animate', true);
			$(this).find('.circle').circleProgress({
				startAngle: -Math.PI / 2,
				value: percent / 100,
				size: 160,
				thickness: 30,
				emptyFill: "#d5e5f6",
				fill: {
					color: '#14385f'
				}
			}).on('circle-animation-progress', function (event, progress, stepValue) {
				$("div#aveh").text((stepValue*100).toFixed(2) + "%");
			}).stop();
		}
	});
}

function animateElementsCVeh(cvehProgressCount) {
	$('#progressbarcveh').each(function () {
		var elementPos = $(this).offset().top;
		var topOfWindow = $(window).scrollTop();
		var percent = cvehProgressCount;
		var percentage = parseInt(percent, 10) / parseInt(100, 10);
		
		var animate = $(this).data('animate');
		if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
			$(this).data('animate', true);
			$(this).find('.circle').circleProgress({
				startAngle: -Math.PI / 2,
				value: percent / 100,
				size: 160,
				thickness: 30,
				emptyFill: "#d9f2d9",
				fill: {
					color: '#336600'
				}
			}).on('circle-animation-progress', function (event, progress, stepValue) {
				$("div#cveh").text((stepValue*100).toFixed(2) + "%");
			}).stop();
		}
	});
}

</script>

<script type="text/javascript">
function wpnHoldingStateChart()
{		
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("wpnHoldingStateDiv", am4charts.XYChart);
	clusterddiv.scrollbarX = new am4core.Scrollbar();			
	
	clusterddiv.data =${getWPNHoldingStateData};
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "colname";
	categoryAxis_cluster.title.text ="";  // "Command Name";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
						
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text =""; // "No. of Units";
	valueAxis_cluster.rangeChangeDuration = 500;
	
	var label = categoryAxis_cluster.renderer.labels.template;
	label.wrap = true;
	label.fontSize = 11;
	label.fontWeight = "bold";
	label.maxWidth = 80;	
	
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
	//colorSet.list = ["#008080", "#cc0052", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	clusterddiv.maskBullets = true;
	
	// Create series
	function createSeries_cluster(field, name) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "colname";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;
	  	
	  	/* var bullet = series_cluster.bullets.push(new am4charts.LabelBullet);
	    bullet.label.text = "{colcode}";
	    bullet.label.rotation = 90;
	    bullet.label.truncate = false;
	    bullet.label.hideOversized = false;
	    bullet.label.horizontalCenter = "left";
	    bullet.locationY = 1;
	    bullet.dy = 10;	 */		    
	}
	
	createSeries_cluster("ue", "UE", true);
	createSeries_cluster("uh", "UH", true);				
	
	clusterddiv.legend = new am4charts.Legend();
	//clusterddiv.legend.position = "right";	
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc"); 

}
</script>

<script type="text/javascript">
function eqptHoldingStateChart()
{		
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("eqptHoldingStateDiv", am4charts.XYChart);
	clusterddiv.scrollbarX = new am4core.Scrollbar();			
	
	clusterddiv.data =${getEQPTHoldingStateData};
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "colname";
	categoryAxis_cluster.title.text ="";  // "Command Name";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
						
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text =""; // "No. of Units";
	valueAxis_cluster.rangeChangeDuration = 500;
	
	var label = categoryAxis_cluster.renderer.labels.template;
	label.wrap = true;
	label.fontSize = 11;
	label.fontWeight = "bold";
	label.maxWidth = 80;	
	
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
	//colorSet.list = ["#008080", "#cc0052", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	clusterddiv.maskBullets = true;
	
	// Create series
	function createSeries_cluster(field, name) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "colname";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;
	  	
	  	/* var bullet = series_cluster.bullets.push(new am4charts.LabelBullet);
	    bullet.label.text = "{colcode}";
	    bullet.label.rotation = 90;
	    bullet.label.truncate = false;
	    bullet.label.hideOversized = false;
	    bullet.label.horizontalCenter = "left";
	    bullet.locationY = 1;
	    bullet.dy = 10;	 */		    
	}
	
	createSeries_cluster("ue", "UE", true);
	createSeries_cluster("uh", "UH", true);				
	
	clusterddiv.legend = new am4charts.Legend();
	//clusterddiv.legend.position = "right";	
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc"); 

}
</script>

</body>
</html>