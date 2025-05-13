<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<script src="js/amchart4/circle-progress.min.js"></script>
<link rel="stylesheet" href="js/miso/formationDashboard/fornationDashboardCSS.css">
<script src="js/miso/formationDashboard/formationDashboard.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<style>
.info-box_psg{
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
    height: 150px;
    display: flex;
    cursor: default;
    background-color: #fff;
    position: relative;
    overflow: hidden;
    margin-bottom: 10px;
    border-style: ridge;

}
.mt-2 {
    margin-top: 18px;
    margin-left: -65px;
}
.mt-1
{
 margin-top: 18px;
}
.bg-comp{
background-color: #3a43b7;
color:#fff;
}



</style>
<script type="text/javascript">
// $(document).ready(function () {
	$('body').bind('cut copy paste', function (e) {
        e.preventDefault();
    });
    $("body").on("contextmenu",function(e){
        return false;
    });
// });
</script>

<div class="animated fadeIn">
	<div class="card watermarked" data-watermark="" id="divwatermark" style="display: block; z-index:1111">
		<span id="ip"></span>
		<div id="DASHBOARD_tabs">
			<!-- <h5 class="trans_heading">DASHBOARD</h5> -->
    		<div class="card-body card-block">
    			<c:choose>
  					<c:when test="${roleSubAccess111 == 'Command'}">
    				<div class="row" id="divCMDUSerID">
						<div class="col-md-4 col-xl-4 divide_border">
							<h5 class="h5">Inter Command Move Details</h5>
							<div class="row" style="align-items: flex-end !important;">
								<div class="col-md-4 col-xl-4">
									<b>From Date :</b><input type="date" id="fromDateChord"  style="width: 100%;font-size: 11px;">
								</div>
								<div class="col-md-4 col-xl-4">
									<b>To Date :</b><input type="date" id="toDateChord" style="width: 100%;font-size: 11px;">
								</div>
								<div class="col-md-2 col-xl-2">
									<button id="btnChord" class="btn btn-primary btn-sm" onclick="chrodChartCommandMov();" style="font-size: 12px;">Show</button>
								</div>
							</div>
							<div class="col-md-12">					
								<div id="chordCommdMovdiv" style="width: 100%; height: 500px;"></div>
							</div>	
							<div class="info-box bg-indigo" onclick="showUnitMovReport('${roleSubAccess111}');" style="position: absolute;bottom: 0;right: 0;">
								<div class="content">
									<h5 style="font-weight: bold; font-size: 12px; ">MOVE DETAILS</h5>
									<div class="number count-to">
										<a><label id="getActUnitsInFormation">${getActUnitsInFormation[0].total}</label></a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-8 col-xl-8">
							<h5 class="h5">Manpower</h5>
							<div class="col-md-12">							
								<div id="ueManpowerdiv" style="width: 100%; height: 540px !important; "></div>
							</div>
						</div>
					</div>
				</c:when>
  				<c:when test="${roleAccess == 'HeadQuarter'}">
  					 <div class="row" id="divCMDUSerID">
						<div class="col-md-4 col-xl-4 divide_border">
							<h5 class="h5">Inter Command Move Details</h5>
							<div class="row" style="align-items: flex-end !important;">
								<div class="col-md-4 col-xl-4">
									<b>From Date :</b><input type="date" id="fromDateChord"  style="width: 100%;font-size: 11px;">
								</div>
								<div class="col-md-4 col-xl-4">
									<b>To Date :</b><input type="date" id="toDateChord" style="width: 100%;font-size: 11px;">
								</div>
								<div class="col-md-2 col-xl-2">
									<button id="btnChord" class="btn btn-primary btn-sm" onclick="chrodChartCommandMov();" style="font-size: 12px;">Show</button>
								</div>
							</div>
							<div class="col-md-12">
								<div id="chordCommdMovdiv" style="width: 100%; height: 500px;"></div>
							</div>
							<div class="info-box bg-indigo" onclick="showUnitMovReport('${roleAccess}');" style="position: absolute;bottom: 0;right: 0;">
								<div class="content">
									<h5 style="font-weight: bold; font-size: 12px; ">MOVE DETAILS</h5>
									<div class="number count-to">
										<a><label id="getActUnitsInFormation">${getActUnitsInFormation[0].total}</label></a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-8 col-xl-8">
							<h5 class="h5">Manpower</h5>
							<div class="col-md-12">							
								<div id="ueManpowerdiv" style="width: 100%; height: 540px;"></div>
							</div>
						</div>
					</div>
  				</c:when>
  				<c:otherwise>
					<div class="row">
						<div class="col-md-5 col-xl-5 divide_border" style="width: 100%; height: 100px;">	
	    					<h5 class="h5"> Move Details </h5>
							<div class="row" style="align-items: flex-end !important;">
								<div class="col-md-4 ">
									<b>From Date : &nbsp;</b><input type="date" id="fromDateChord"  style="width: 100%;">
								</div>
								<div class="col-md-4 ">
									<b>To Date : &nbsp;</b><input type="date" id="toDateChord" style="width: 100%;">
								</div>
								<div class="col-md-2 ">
									<button id="btnChord" class="btn btn-primary btn-sm" onclick="showUnitMovReport('${roleSubAccess111}');">Show</button>
									<label id="getActUnitsInFormation" style="display: none;">1</label>
								</div>
							</div>
						</div>
						<div class="col-md-7 col-xl-7">
							<h5 class="h5">Manpower</h5>
							<table border="1" id="unitEntitlementTable" class="table no-margin table-striped table-hover">
								<thead>											
									<tr style="background-color: darkseagreen;text-align: center;">
										<th width="55%">FMN</th>
										<th width="15%">OFFR</th>
										<th  width="15%">JCO</th>
										<th width="15%">OR</th>
									</tr>
								</thead>
								<tbody id="unitEntitlementTbody">
									
								</tbody>
							</table>
						</div>
					</div>
				</c:otherwise>
				</c:choose>
				<div class="row"><div class="col-md-12"><span class="line"></span></div></div>
					<!--<div class="row">
						<div class="col-md-12" style="display:block;">									
							<h5 class="h5">Hospital Admissions - Lifestyle Diseases</h5>													
						</div>
						 <div class="col-md-12" align="center">
							<div class="col-md-3">
								<h5 class="h5">IHD</h5>
							</div>
							<div class="col-md-3">
								<h5 class="h5">OBESITY</h5>
							</div>
							<div class="col-md-3">
								<h5 class="h5">DM</h5>
							</div>
							<div class="col-md-3">
								<h5 class="h5">HTN</h5>
							</div>
						</div>
						<div class="col-md-12" align="center">
							<div class="col-md-3">
								<div id="ihdChart" style="width: 100%;"></div>
							</div>
							<div class="col-md-3">
								<div id="obsChart" style="width: 100%;"></div>
							</div>
							<div class="col-md-3">
								<div id="dmChart" style="width: 100%;"></div>
							</div>
							<div class="col-md-3">
								<div id="htChart" style="width: 100%;"></div>
							</div>
						</div> 
					</div>-->
					<%-- <div class="row">
						<div class="col-md-12" align="center">
							<div class="col-md-4"  style="margin-right : 5px;border-radius: 14px;background-color: #00c689;margin-bottom: 5px;font-weight: bold;color: white;padding-top: 5px;padding-bottom: 5px;">
								<div class="col-md-12" style="display:block;">									
									<h5 class="h5" style="color: white;">Hospital Admissions (Col & Above)</h5>													
								</div>
								<div class="col-md-12">
									<div class="col-md-4" style="border-radius: 4px;background-color: #00c689;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${Hospital_Admissions_Col_Above[0].daily}</div>
									<div class="col-md-4" style="border-radius: 4px;background-color: #00c689;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${Hospital_Admissions_Col_Above[0].monthly}</div>
									<div class="col-md-4" style="border-radius: 4px;background-color: #00c689;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${Hospital_Admissions_Col_Above[0].annual}</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4" style=""><b>Daily</b></div>
									<div class="col-md-4" style=""><b>Monthly</b></div>
									<div class="col-md-4"  style=""><b>Annual</b></div>
								</div>
							</div>
							<div class="col-md-4" style="border-radius: 14px;background-color: #3da5f4;margin-bottom: 5px;font-weight: bold;color: white;padding-top: 5px;padding-bottom: 5px;">
								<div class="col-md-12" style="display:block;">									
									<h5 class="h5" style="color: white;">AMC/ADC/MNS Admissions</h5>													
								</div>
								<div class="col-md-12">
									<div class="col-md-4" style="border-radius: 4px;background-color: #3da5f4;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${AMC_ADC_MNS_Admissions[0].daily}</div>
									<div class="col-md-4" style="border-radius: 4px;background-color: #3da5f4;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${AMC_ADC_MNS_Admissions[0].monthly}</div>
									<div class="col-md-4" style="border-radius: 4px;background-color: #3da5f4;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${AMC_ADC_MNS_Admissions[0].annual}</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4" style=""><b>Daily</b></div>
									<div class="col-md-4" style=""><b>Monthly</b></div>
									<div class="col-md-4"  style=""><b>Annual</b></div>
								</div>
							</div>
							<div class="col-md-4" style="margin-left : 5px;border-radius: 14px;background-color: #f1536e;margin-bottom: 5px;font-weight: bold;color: white;padding-top: 5px;padding-bottom: 5px;">
								<div class="col-md-12" style="display:block;">									
									<h5 class="h5" style="color: white;">Unusual Occurrence</h5>													
								</div>
								<div class="col-md-12">
									<div class="col-md-4" style="border-radius: 4px;background-color: #f1536e;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${Unusual_Occurrence[0].daily}</div>
									<div class="col-md-4" style="border-radius: 4px;background-color: #f1536e;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${Unusual_Occurrence[0].monthly}</div>
									<div class="col-md-4" style="border-radius: 4px;background-color: #f1536e;margin-bottom: 5px;font-weight: bold;color: white;font-size: 30px;">${Unusual_Occurrence[0].annual}</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4" style=""><b>Daily</b></div>
									<div class="col-md-4" style=""><b>Monthly</b></div>
									<div class="col-md-4"  style=""><b>Annual</b></div>
								</div>
							</div> 
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-xl-6 divide_border">
							<h5 class="h5">Rank wise Hospital Admissions</h5>
							<div id="rankWiseHospitalAdmission" style="width: 100%;height: 400px;"></div>
						</div>
						<div class="col-md-6 col-xl-6">
							<h5 class="h5">Bed State</h5>
							<div id="bedStateSummarydiv" style="width: 100%; height: 400px;"></div>
						</div>
					</div> --%>
					<!-- Start MNH Chart -->
					<div class="row"><div class="col-md-12"><span class="line"></span></div></div>
					<div class="row" style="align-items: flex-start !important;">
						<div class="col-md-5 col-xl-5 divide_border">
							<h5 class="h5">Transport Summary</h5>
							<div class="row" style="align-items: flex-end !important;">								
								<div class="col-lg-4 col-xl-4" style="text-align: right;"><b>VEH CAT : &nbsp;</b>
									<select id="typeOfVehicle" onchange="getPRFList(this.value)" style="width: 100%">
										<option value="">--Select--</option>
										<option value="0">A Vehicles</option>
										<option value="1" selected="selected">B Vehicles</option>
										<option value="2">C Vehicles</option>
									</select>
								</div>
								<div class="col-lg-5 col-xl-5">
									<b>VEH SUB CAT : &nbsp;</b>
									<select id="prfTptList" style="width: 100%"></select>
								</div>
								<div class="col-lg-2 col-xl-2">
									<button id="btnTptSumm" class="btn btn-primary btn-sm" onclick="tptSummaryChart();prfWiseTptClassChart();">Show</button>
								</div>
							</div>
							<div id="tptSummarydiv" style="width: 100%; height: 350px;"></div>
						</div>
						<div class="col-md-7 col-xl-7">
							<h5 class="h5">Vehicle Casualty Return Updation State</h5>
								
							<table border="1" id="typeOfVehicleTbl" class="table no-margin table-striped table-hover">
								<thead>											
									<tr style="background-color: darkseagreen;text-align: center;">
										<th width="25%">Type of Vehicle</th>
										<th width="25%">Data Updated</th>
										<th  width="25%">Yet to Update</th>
										<th width="25%">Total</th>
									</tr>
								</thead>
								<tbody style="text-align: center;">
									<c:forEach var="item" items="${getVehicleListInFRM}" varStatus="num" >
										<tr>
											<td width="25%">A</td>
											<td width="25%">${item.a_up}</td>
											<td width="25%">${item.a_yet}</td>
										 	<td width="25%"><b><a href='#' onclick='openAMvcrUpdateReport();' style='color: blue'><u> ${item.a_up + item.a_yet} </u></a></b></td>
										</tr>
										<tr>
											<td width="25%">B</td>
											<td width="25%">${item.b_up}</td>
											<td width="25%">${item.b_yet}</td>
										 	<td width="25%"><b><a href='#' onclick='openBMvcrUpdateReport();' style='color: blue'><u> ${item.b_up + item.b_yet} </u></a></b></td> 
										</tr>
										<tr>
											<td width="25%">C</td>
											<td width="25%">${item.c_up}</td>
											<td width="25%">${item.c_yet}</td>
										 	<td width="25%"><b><a href='#' onclick='openCMvcrUpdateReport();' style='color: blue; '><u> ${item.c_up + item.c_yet} </u></a></b></td> 
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
					</div>
					<div class="row"><div class="col-md-12"><span class="line"></span></div></div>	
					<div class="row">			
						<div class="col-md-9" style="display:block;">									
							<h5 class="h5"> Transport Classification</h5>													
							<div id="prfTptClassdiv" style="width: 100%; height: 350px;"></div>
						</div>
						<div class="col-md-3" style="display:block;">									
							<h5 class="h5"> IT Asset Summary </h5>									
								<div class="row"><div class="col-md-12"><span class="line"></span></div></div>	
								<div id="Classdiv" style="width: 100%; height: 350px;">
								<div class="col-xl-12 info-box_psg  bg-comp">
					<h5 class="counter-title">COMPUTING ASSETS</h5>
					<br>
<div class="mt-2">
						<a data-toggle="modal" data-target="#illegalityMiningCasesView" onclick="openUnitwiseDetails('popupForm');">
						<span style="font-size: 18mm" class="count" id="count1">${compassetCount}</span>
						</a>
<!-- 						<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcelUnit();"></i> -->
</div>
	</div>
	<div class="col-xl-12 info-box_psg  bg-red ">
					<h5 class="counter-title">PERIPHERALS </h5>
					<br>
<div class="mt-1">
						<a data-toggle="modal" data-target="#illegalityMiningCasesView" onclick="openUnitwiseDetails('popupFormperi');">
						<span style="font-size: 18mm" class="count" id="count2">${peripheralCount}</span>
						</a>
<!-- 						<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcelUnit();"></i> -->
</div>
	</div>
								
								
								
								</div>			
						
						</div>
					</div>
					<div class="row"><div class="col-md-12"><span class="line"></span></div></div>
					<div class="row">
						<div class="col-lg-12 col-xl-6 divide_border">									
							<h5 class="h5">Weapon</h5>								
							<div id="wpnHoldingStateDiv" style="width: 100%; height: 350px;"></div>							
						</div>
						<div class="col-lg-12 col-xl-6">	
							<h5 class="h5">Equipment</h5>						
							<div id="eqptHoldingStateDiv" style="width: 100%; height: 350px;"></div>					
						</div>
					</div>		
					<div class="row"><div class="col-md-12"><span class="line"></span></div></div>		
					
					
							<div class="row">
						<div class="col-lg-12 col-xl-6 divide_border state_tables">									
<!-- 							<h5 class="h5">UNIT</h5>								 -->
							<div id="wpnHoldingStateDiv" style="width: 100%; ">
							<table id="unitwithmcrobsn" class="table no-margin table-striped  table-hover">
										<thead>
										<tr >
												<td colspan="4"><h4>UNITS MCR : OBSN STATUS</h4></td>
											</tr>
												<tr >
												<th>TOTAL UNITS</th>
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
							</div>							
						</div>
						<div class="col-lg-12 col-xl-6">	
<!-- 							<h5 class="h5">IT ASSET</h5>						 -->
							<div id="eqptHoldingStateDiv" class="state_tables"style="width: 100%; ">
							<table id="unitwithmcrobsn" class="table no-margin table-striped  table-hover">
										<thead>
											<tr >
												<td colspan="4"><h4>IT ASSET : OBSN STATUS</h4></td>
											</tr>
											<tr >
												<th>TOTAL UNITS</th>
											    <th>UNITS WITH IT ASSETS</th>
												<th>UNITS WITHOUT IT ASSETS</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${getunititobsr}" varStatus="num" >
												<tr>
													<td>${item[0]}</td>
													<td>${item[1]}</td>
													<td>${item[2]}</td>
													
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
							</div>					
						</div>
					</div>		
					<div class="row"><div class="col-md-12"><span class="line"></span></div></div>	
					<div class="row">				
						<div class="col-lg-4 col-xl-3" onclick="downloadReport('1');">
							<div class="info-box bg-darkgreen">
								<h5 style="font-weight: bold; font-size: 14px;">Authorised Establishment and Held Strength of Regular Army (Commands and Formations)</h5>
								<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
							</div>
						</div>
						<div class="col-lg-4 col-xl-3" onclick="downloadReport('2');">
									<div class="info-box bg-deep-purple">
										<h5 style="font-weight: bold; font-size: 14px;">Statistical Digest on Manpower</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('3');">
									<div class="info-box bg-darkyellow">
										<h5 style="font-weight: bold; font-size: 14px;">Statistical Digest on ‘A’ & ‘B’ Vehicles</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('4');">
									<div class="info-box bg-green">
										<h5 style="font-weight: bold; font-size: 14px;">Statistical Digest on Armament and Equipment</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>	
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('5');">
									<div class="info-box bg-indigo">
										<h5 style="font-weight: bold; font-size: 14px;">Statistical Digest on FOL, Ration, Losses, Animals, Labour etc.</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('6');">
									<div class="info-box bg-red">
										<h5 style="font-weight: bold; font-size: 14px;">Strength of the Indian Army</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>	
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('7');">
									<div class="info-box bg-sky">
										<h5 style="font-weight: bold; font-size: 14px;">MT Accidents (WIR) on Army ‘B’ Vehicles</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>
									</div>
								</div>
								<div class="col-lg-4 col-xl-3" onclick="downloadReport('8');">
									<div class="info-box bg-maroon">										
										<h5 style="font-weight: bold; font-size: 14px;">Study Report on Closed Court of Inquiry</h5>
										<a href="#" title="Download Uploaded Documents"><i class='action_icons action_download'></i></a>											
									</div>
								</div>								
							</div>
						</div>
	    			</div>
	    		</div>
			</div>
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
							<table id="UnitMovReport" class="no-margin table-striped table-hover table-bordered" width="100%">
								<thead style="background-color: #9c27b0; color: white; text-align:center;">
									<tr>
										<th rowspan="2" width="5%">Ser No</th>
										<th rowspan="2" width="5%">SUS No</th>
										<th rowspan="2" width="13%">Unit Name</th>
										<th rowspan="2" width="5%">NMB Date</th>
										<th colspan="4" width="36%">From</th>
										<th colspan="4" width="36%">To</th>
									</tr>
									<tr>
										<th  width="9%">Command</th>
										<th  width="9%">Corps</th>
										<th  width="9%">Division</th>
										<th  width="9%">Brigade</th>
										<th width="9%">Command</th>
										<th width="9%">Corps</th>
										<th width="9%">Division</th>
										<th width="9%">Brigade</th>										
									</tr>
								</thead> 
							  <tbody id="UnitMovReportTbody" >
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
<c:url value="getDownloadDocFMNDashboard" var="docDownloadUrl" /> 
<form:form action="${docDownloadUrl}" method="post" id="getDownloadDocFMNDashboardForm" name="getDownloadDocFMNDashboardForm" modelAttribute="filename">
	<input type="hidden" name="filename" id="filename" value="0"/>
</form:form>	

<c:url value="unitMoveReport" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm"  target="result">
	   <input type="hidden" name="frm_dt_a" id="frm_dt_a"  value="0">
	   <input type="hidden" name="to_dt_a" id="to_dt_a"  value="0">
</form:form>


<c:url value="computerReport" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="popupForm" name="popupForm"  target="result">
	   <input type="hidden" name="popup_sus" id="popup_sus"  value="0">
</form:form>
<c:url value="peripheralReport" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="popupFormperi" name="popupFormperi"  target="result">
	   <input type="hidden" name="popup_sus_peri" id="popup_sus_peri"  value="0">
</form:form>


<script>
$(document).ready(function () {
	
	$("#WaitLoader").show();
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	setTimeout(function(){
		progressBar('${bvehProgressCount}','${avehProgressCount}','${cvehProgressCount}');
		
		$('#fromDateChord').val('${maxDate}'); 
	    $('#toDateChord').val('${futureDate}');
	    
	    if('${roleSubAccess111}' == "Command" || '${roleAccess}' == "HeadQuarter"){
	    	$("div#divCMDUSerID").show();
	    	$.ajaxSetup({
			    async: false
			});
			chrodChartCommandMov();
			$.ajaxSetup({
			    async: false
			});	
			$.ajaxSetup({
			    async: false
			});
			ueManpowerChart();
	    }else{
	    	$("div#divCMDUSerID").hide();
	    	ueManpowerTable();
	    }
	    
	    $.ajaxSetup({
		    async: false
		});
	    
	   	//MNH Dashboard
		/* AllLifeStyleCount("","IHD","ihdChart","#C70039",57,88,110);
		AllLifeStyleCount("","OBESITY","obsChart","#58d68d",110,188,210);
		AllLifeStyleCount("","DM","dmChart","#6495ED",97,128,186);
		AllLifeStyleCount("","HTN","htChart","#f1c40f",60,136,173); */
	    
		/*rankWiseHospitalAdmission("");
		$.ajaxSetup({
		    async: false
		});
		bedStateChart();
		$.ajaxSetup({
	   	    async: false
	   	});*/
		
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
		
		$.ajaxSetup({
		    async: false
		});	
		var type=$("select#typeOfVehicle").val();
		getPRFList(type);
		
		tptSummaryChart();
		$.ajaxSetup({
		    async: false
		});
		prfWiseTptClassChart();
		$("#WaitLoader").show();
		$.ajaxSetup({
		    async: false
		});
		wpnHoldingStateChart(${getWPNHoldingStateData});
		$.ajaxSetup({
		    async: false
		});
		eqptHoldingStateChart(${getEQPTHoldingStateData});
	}, 500);
	counterLoad();
});

function showUnitMovReport(roleSubAccess111)
{
	
	//var url="DocTypeReport?type="+val+"&pdf=N";
	//var url="unitMoveReport?type="+value;
		var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 	
	}
		var frm_dt=  $("#fromDateChord").val();
		var to_dt=  $("#toDateChord").val();		
	    $("#frm_dt_a").val(frm_dt);
		$("#to_dt_a").val(to_dt);
		document.getElementById('viewForm').submit();	
	 
	}
	


function counterLoad(){
	$('.count').each(function () {
	    $(this).prop('Counter',0).animate({
	        Counter: $(this).text()
	    }, {
	        duration: 2000,
	        easing: 'swing',
	        step: function (now) {
	            $(this).text(Math.ceil(now));
	        }
	    });
	});
}
	
function openUnitwiseDetails(a) {		
$("#popup_sus").val('${roleSusNo}');
$("#popup_sus_peri").val('${roleSusNo}');

	if(popupWindow != null && !popupWindow.closed){
		
		popupWindow.close();
		popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		window.onfocus = function () { 
		}
		document.getElementById(a).submit();
}
		else
			{
			popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			window.onfocus = function () { 
			}
			document.getElementById(a).submit();
			}
}
</script>