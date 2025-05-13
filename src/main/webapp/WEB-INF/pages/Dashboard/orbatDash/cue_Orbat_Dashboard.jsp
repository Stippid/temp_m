<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:csrfInput />
 --%>
 
 
<%--  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:csrfMetaTags />
<script type="text/javascript">
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
</script> --%>


<script src="js/miso/formationDashboard/formationDashboard.js" type="text/javascript"></script>



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

<style type="text/css">
	.modal,.modalm {
	    display: none; 
	    position: fixed; 
	    z-index: 999999; 
	    padding-top: 10px; 
	    left: 0;
	    top: 0;
	    width: 100%; 
	    height: 100%; 
	    overflow: auto; 
	    background-color: rgb(0,0,0);
	    background-color: rgba(0,0,0,0.4); 
	}
	.modal-content1,.modal-content1m {
	    background-color: #fefefe;
	    margin: auto;
	    padding: 20px 20px 50px 20px;
	    border: 1px solid black;
	    border-radius: 5px;
	    width: 90%;
	    
	}
	/* The Close Button */
	.close,.closem {
	    color: maroon;
	    float: right;
	    font-size: 28px;
	    font-weight: bold;
	}
	
	.close:hover,
	.close:focus {
	    color: red;
	    text-decoration: none;
	    cursor: pointer;
	}
	
	.close1 {
	display:inline-block;padding:6px 12px;margin-bottom:0;font-size:14px;font-weight:400;line-height:1.42857143;text-align:center;
	cursor:pointer;border:1px solid #d43f3a;border-radius:4px; color:#fff;background-color:#d9534f;
	}
	
	.close1:hover,.close1:focus {
	    color:#333;text-decoration:none;
	    cursor: pointer;
	}
	
	.closem:hover,
	.closem:focus {
	    color: red;
	    text-decoration: none;
	    cursor: pointer;
	}
	
	.close1m {
	display:inline-block;padding:6px 12px;margin-bottom:0;font-size:14px;font-weight:400;line-height:1.42857143;text-align:center;
	cursor:pointer;border:1px solid #d43f3a;border-radius:4px; color:#fff;background-color:#d9534f;
	}
	
	.close1m:hover,.close1m:focus {
	    color:#333;text-decoration:none;
	    cursor: pointer;
	}
	.info-box{
	  cursor: pointer;
	}
</style>

	<div class="animated fadeIn" >
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					<div id="DASHBOARD_tabs">
	      				<h5 class="trans_heading">CUE &amp; ORBAT DASHBOARD</h5>
    					<div class="row">
    						<div class="card-body card-block">
    						<div class="col-md-12">
								<div class="col-md-4">
									<h5 style="color: maroon; font-weight: bold; text-decoration: underline;">Inter Command Move Details</h5>
									<div class="col-md-5"><b>From Date : &nbsp;</b><input type="date" id="fromDateChord"  style="width: 100%;">
									</div>
									<div class="col-md-5"><b>To Date : &nbsp;</b><input type="date" id="toDateChord" style="width: 100%;">
									</div>
									<div class="col-md-2" style="padding-top: 20px;">
										<button id="btnChord" class="btn btn-primary btn-sm" onclick="chrodChartCommandMov();">Show</button></div>
									<div class="col-md-12">																	
										<div id="chordCommdMovdiv" style="width: 100%; height: 400px;"></div>
									</div>	
									<div class="info-box bg-indigo" id="inter_cmd_move_dtl" onclick="showUnitMovReport('${roleSubAccess111}' , this);" style="position: absolute;bottom: 0;right: 0;">
								<div class="content">
									<h5 style="font-weight: bold; font-size: 12px; ">MOVE DETAILS</h5>
									<div class="number count-to">
										<a><label id="getActUnitsInFormation">${getActUnitsInFormation[0].total}</label></a>
									</div>
								</div>
							</div>								
								</div>	
								<span style="border-left: 2px solid #f0eaea; height: 400px;"></span>					
									<div class="col-md-8">
									<h5 style="color: maroon; font-weight: bold; text-decoration: underline; text-align: center;">Command Wise Status</h5><br>
									<div class="col-md-4" style="text-align: right;">
										<b>From Date : &nbsp;</b><input type="date" id="fromDateClustCmdAct"  style="width: 130px;">
									</div>
									<div class="col-md-4">
										<b>To Date : &nbsp;</b><input type="date" id="toDateClustCmdAct" style="width: 130px;">
									</div>
									<div class="col-md-4" style="text-align: left;padding-top: 20px;">
										<button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="callAmClusterdChartCommandByAction();">Show</button>
									</div>
									<div class="col-md-12">
										<div id="clusterCommandByActiondiv" style="width: 100%; height: 400px;"></div>
									</div>																
								</div>
							</div>
							<div class="col-md-12"><span class="line"></span></div>							
							
							<div class="col-md-12" >
								<div class="col-md-3"></div>
								<div class="col-md-6"><h5 style="color: maroon; font-weight: bold; text-decoration: underline;">Year Wise Uploaded WE/PE/FE/GSL</h5></div>
								<div class="col-md-3"></div>
							</div>					
							<div class="col-md-12" align="center">
<!-- 								<div class="col-md-3"></div>											 -->
								<div class="col-md-2" style="text-align: right;"><b>From Year : &nbsp;</b><select id="fromYearDocType"></select></div>
								<div class="col-md-2"><b>To Year : &nbsp;</b><select id="toYearDocType"></select></div>
								<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3" style="text-align: left; font-weight: bold;">
					                  <label class=" form-control-label">Arm/Service</label>
					                </div>
					                <div class="col-12 col-md-8">				                
					                <select  class="form-control" id="arm" name="arm">
					                <option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num">
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>					                					                                                    
		                            </select>
					                </div>
					            </div>	  								
	  						</div> 
								<div class="col-md-2" style="text-align: left;"><button id="btnYearDocType" class="btn btn-primary btn-sm" onclick="callAmClusterdChart();">Show</button></div>
<!-- 								<div class="col-md-3"></div> -->
							</div>
							<div class="col-md-12" style="display:block;">	
								<div class="row">		
									<div class="col-md-12">									
										<div id="clusterddiv" style="width: 100%; height: 350px;"></div>
									</div>
								</div>
							</div> 						
							<div class="col-md-12"><span class="line"></span></div>	
							<div class="col-md-12">
								<div class="col-md-5">
									<h5 style="color: maroon; font-weight: bold; text-decoration: underline;">Rank Category Wise UE</h5>
									<div class="col-md-12">
										<div id="piediv" style="width: 100%; height: 250px;"></div>
									</div>									
								</div>
								<span style="border-left: 2px solid #f0eaea; height: 250px;"></span>	
								
									<div class="col-md-7">
									<h5 style="color: maroon; font-weight: bold; text-decoration: underline;">Year wise Uploaded WET/PET/FET</h5>
									
									
									<div class="col-md-12"  style="text-align: center;">
								<div class="col-md-3"><b>From Year : &nbsp;</b><select id="fromYearPTW"></select></div>
								<div class="col-md-2"><b>To Year : &nbsp;</b><select id="toYearPTW"></select></div>
								
									<div class="col-md-1" style="text-align: left; font-weight: bold;	">
					                  <label class=" form-control-label">Arm</label>
					                </div>
									  <div class="col col-md-3">				                
					                <select  class="form-control" id="armwet" name="armwet">
					                <option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num">
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>					                					                                                    
		                            </select>
					                </div>
									<div class="col-md-3">
										<button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="callAmClusterdChartWET();">Show</button>
									</div>
									</div>
									
									<div class="col-md-12">
										<div id="clusterdWETdiv" style="width: 100%; height: 350px;"></div>
									</div>									
								</div>
								
								
								
								<div class="col-md-7" style="display:none;">
									<h5 style="color: maroon; font-weight: bold; text-decoration: underline;">PERS/TPT/WPN wise Uploaded WE/PE/FE/GSL</h5>
									<div class="col-md-12"  style="text-align: center;">
										<div class="col-md-4" style="text-align: right;">
										<b>From Date : &nbsp;</b><input type="date" id="fromDatPTW"  style="width: 130px;">
									</div>
									<div class="col-md-4">
										<b>To Date : &nbsp;</b><input type="date" id="toDatePTW" style="width: 130px;">
									</div>
									<div class="col-md-4" style="text-align: left;padding-top: 20px;">
										<button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="callAmClusterdChartPTWByAction();">Show</button>
									</div>
									</div>
									<div class="col-md-12">
										<div id="clusterdDocTypeModulediv" style="width: 100%; height: 350px;"></div>
									</div>									
								</div>
							</div>			
							<div class="col-md-12"><span class="line"></span></div>						
							<div class="col-md-12">	
								<div class="col-md-2" onclick="openDocTypeDetails('we');">
									<div class="info-box bg-darkgreen">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">No. of units working on WE/PE/FE/GSL</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
<%-- 													<c:set value="${getDocTypeWiseCountWe[0].we_pe}" var="num"/><c:choose> --%>
<%-- 													<c:when test="${(fn:toLowerCase(num) eq 'we')}"> --%>
													<label id="totalwe">${getDocTypeWiseCountWe[0].noofwes}</label>
<%-- 													</c:when><c:otherwise><label id="totalwe">0</label></c:otherwise></c:choose> --%>
												</a>
												
												
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2" onclick="openDocTypeDetails('pe');" style="display:none">
									<div class="info-box bg-deep-purple">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">No. of units working on PE</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
													<c:set value="${getDocTypeWiseCountPe[0].we_pe}" var="num"/><c:choose>
													<c:when test="${(fn:toLowerCase(num) eq 'pe')}">
													<label id="totalpe">${getDocTypeWiseCountPe[0].noofwes}</label></c:when><c:otherwise><label id="totalpe">0</label></c:otherwise></c:choose>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2" onclick="openDocTypeDetails('fe');" style="display:none">
									<div class="info-box bg-darkyellow">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">No. of units working on FE</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
													<c:set value="${getDocTypeWiseCountFe[0].we_pe}" var="num"/><c:choose>
													<c:when test="${(fn:toLowerCase(num) eq 'fe')}">
													<label id="totalfe">${getDocTypeWiseCountFe[0].noofwes}</label></c:when><c:otherwise><label id="totalfe">0</label></c:otherwise></c:choose>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2" onclick="openDocTypeDetails('gsl');" style="display:none">
									<div class="info-box bg-green">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">No. of units working on GSL</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
													<c:set value="${getDocTypeWiseCountGsl[0].we_pe}" var="num"/><c:choose>
													<c:when test="${(fn:toLowerCase(num) eq 'gsl')}">
													<label id="totalgsl">${getDocTypeWiseCountGsl[0].noofwes}</label></c:when><c:otherwise><label id="totalgsl">0</label></c:otherwise></c:choose>
							
												</a>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-2" onclick="openDocTypeDetails('wet');">
									<div class="info-box bg-deep-purple">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">WET</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
													<c:set value="${getDocTypeWiseCountWET[0].wet_pet}" var="num"/><c:choose>
													<c:when test="${(fn:toLowerCase(num) eq 'wet')}">
													<label id="totalwet">${getDocTypeWiseCountWET[0].noofwes}</label></c:when><c:otherwise><label id="totalwet">0</label></c:otherwise></c:choose>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2" onclick="openDocTypeDetails('pet');">
									<div class="info-box bg-darkyellow">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">PET</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
													<c:set value="${getDocTypeWiseCountPET[0].wet_pet}" var="num"/><c:choose>
													<c:when test="${(fn:toLowerCase(num) eq 'pet')}">
													<label id="totalpet">${getDocTypeWiseCountPET[0].noofwes}</label></c:when><c:otherwise><label id="totalpet">0</label></c:otherwise></c:choose>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2" onclick="openDocTypeDetails('fet');">
									<div class="info-box bg-green">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">FET</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
													<c:set value="${getDocTypeWiseCountFET[0].wet_pet}" var="num"/><c:choose>
													<c:when test="${(fn:toLowerCase(num) eq 'fet')}">
													<label id="totalfet">${getDocTypeWiseCountFET[0].noofwes}</label></c:when><c:otherwise><label id="totalfet">0</label></c:otherwise></c:choose>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2" onclick="openActiveUnitDetails();">
									<div class="info-box bg-red">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">ACTIVE UNITS</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
													<label id="totalactiveUnits">${getActiveUnits[0].total}</label>
												</a>
											</div>
										</div>
									</div>
								</div>
<%-- 								<div class="col-md-2" id="moveReport" onclick="showUnitMovReport('${roleSubAccess111}', this);"> --%>
<!-- 									<div class="info-box bg-indigo"> -->
<!-- 										<div class="content"> -->
<!-- 											<h5 style="font-weight: bold; font-size: 16px; ">MOV DETAILS</h5> -->
<%-- 											<label id="totalReliefPgme" style="float: right; font-size: 18px;"><b>${getReliefPgme[0].total}</b></label> --%>
<!-- 											<div class="number count-to"> -->
<!-- 												<a > -->
<%-- 													<label id="totalReliefPgme">${getReliefPgme[0].total}</label> --%>
<!-- 												</a> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
							</div>	
							
							
								<div class="col-md-12">	
								
										<div class="col-md-2" onclick="openDocTypeDetailsWet('pers');">
									<div class="info-box bg-darkgreen">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">Pers</h5>
											
										</div>
									</div>
								</div>
								<div class="col-md-2" onclick="openDocTypeDetailsWet('tpt');">
									<div class="info-box bg-deep-purple">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">Tpt</h5>
										</div>
									</div>
								</div>
								<div class="col-md-2" onclick="openDocTypeDetailsWet('wpn');">
									<div class="info-box bg-darkyellow">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">Wpn & Eqpt</h5>
										</div>
									</div>
								</div>
								
								<div class="col-md-2" onclick="openDocTypeDetailsWet('WE');">
									<div class="info-box bg-green">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">Log of WE/PE</h5>
										</div>
									</div>
								</div>
								
								<div class="col-md-2" onclick="openDocTypeDetailsWet('WET');">
									<div class="info-box bg-red">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">Log of WET/PET/FET</h5>
										</div>
									</div>
								</div>
								
							</div>	
							
							
							<div class="col-md-12">	
							
							
							
							
							</div>		
							
															
								
							</div>	
    					</div>
	    			</div>
	    		</div>
			</div>
		</div>
	</div>
	
<div id="myModalMonth" class="modal">
	<div class="modal-content1">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title" align="center" id="h4HeadId"> </h4>	
		<div class="col-md-12"><span class="line"></span></div>					
		
		<div class="card">
			<div class="card-body card-block cue_text">
				<div class="col-md-12" style="display:block;">	
					<div id="clusterdMonthdiv" style="width: 100%; height: 550px;"></div>				
				</div> 
			</div>
		</div>
		<div class="card-body">
					<div class="datatablediv" id="reportDiv">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="applicanttbl" 
								class="table no-margin table-striped  table-hover  table-bordered" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>Ser No.</th>
											<th>SUS No</th>
											<th>Unit Name</th>
											<th>Uploaded WE PE No</th>
											<th>WE PE No</th>
											<th>Doc Type</th>
											<th>Cr_by</th>
											
											
										</tr>
									
										
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="12" align="center" style="color: red;">Data
													Not Available</td>
											</tr>
										</c:if>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
		<div style="float:right;">
			<button type="button" class="close1" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
	</div>
</div> 
<div id="myModalMonthwet" class="modal">
	<div class="modal-content1">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title" align="center" id="h4HeadIdwet"> </h4>	
		<div class="col-md-12"><span class="line"></span></div>					
		
		<div class="card">
			<div class="card-body card-block cue_text">
				<div class="col-md-12" style="display:block;">	
					<div id="clusterdMonthwetdiv" style="width: 100%; height: 550px;"></div>				
				</div> 
			</div>
		</div>
		
		<div class="card-body">
					<div class="datatablediv" id="reportDiv">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="WETPET_DATA" 
								class="table no-margin table-striped  table-hover  table-bordered" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>Ser No.</th>
											<th>SUS No</th>
											<th>Unit Name</th>
											<th>Uploaded WE PET No</th>
<!-- 											<th>WE PE No</th> -->
											<th>Doc Type</th>
											<th>Cr_by</th>
											
											
										</tr>
									
										
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="12" align="center" style="color: red;">Data
													Not Available</td>
											</tr>
										</c:if>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
		
		<div style="float:right;">
			<button type="button" class="close1" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
	</div>
</div>

	<c:url value="unitMoveReport" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm"  target="result">
	   <input type="hidden" name="frm_dt_a" id="frm_dt_a"  value="0">
	   <input type="hidden" name="to_dt_a" id="to_dt_a"  value="0">
	   <input type="hidden" name="report_id" id="report_id"  value="0">
</form:form>

	<c:url value="cmdWiseUnitDtl" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="viewcmdForm" name="viewcmdForm"  target="result">
	   <input type="hidden" name="frm_dt_cmd" id="frm_dt_cmd"  value="0">
	   <input type="hidden" name="to_dt_cmd" id="to_dt_cmd"  value="0">
	    <input type="hidden" name="cmd" id="cmd"  value="0">
	     <input type="hidden" name="Ydata" id="Ydata"  value="0">
</form:form>

	<c:url value="rankCategoryWiseReport" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="viewRankCatForm" name="viewRankCatForm"  target="result">
	   <input type="hidden" name="cat_id" id="cat_id"  value="0">
<!-- 	   <input type="hidden" name="to_dt_a" id="to_dt_a"  value="0"> -->
<!-- 	   <input type="hidden" name="report_id" id="report_id"  value="0"> -->
</form:form>
	
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
    var minDate = year + '-01-01';
   	$('#fromDateChord').attr('max', maxDate);
    $('#toDateChord').attr('max', maxDate);
    $('#fromDateChord').val(minDate);
    $('#toDateChord').val(maxDate);
    
    $("#fromDateClustCmdAct").attr('max', maxDate);
    $("#toDateClustCmdAct").attr('max', maxDate);
    $("#fromDateClustCmdAct").val(minDate);
    $("#toDateClustCmdAct").val(maxDate);
    
	$('#fromDatPTW').attr('max', maxDate);
    $('#toDatePTW').attr('max', maxDate);
    $('#fromDatPTW').val(minDate);
    $('#toDatePTW').val(maxDate);
    
    $.ajaxSetup({
	    async: false
	});
    var year = 1000;
	var till = 3000;
	var optionsf = "",optionst = "";
	for(var y=year; y<=till; y++){
		if(y == (dtToday.getFullYear()-1))
		{
			optionsf += "<option value='"+y+"' selected='selected'>"+ y +"</option>";  	
			optionst += "<option value='"+y+"'>"+ y +"</option>";
		}
		else if(y == dtToday.getFullYear())
		{
			optionst += "<option value='"+y+"' selected='selected'>"+ y +"</option>";
			optionsf += "<option value='"+y+"'>"+ y +"</option>";  		
		}
	  	else
  		{
  			optionsf += "<option value='"+y+"'>"+ y +"</option>";
  			optionst += "<option value='"+y+"'>"+ y +"</option>";
  		}
	}
	document.getElementById("fromYearDocType").innerHTML = optionsf;
	document.getElementById("toYearDocType").innerHTML = optionst;
	
	document.getElementById("fromYearPTW").innerHTML = optionsf;
	document.getElementById("toYearPTW").innerHTML = optionst;
    
	 $.ajaxSetup({
	    async: false
	});
	////========== comment start for modal user docs
	var modal = document.getElementById('myModalMonth');
	var span = document.getElementsByClassName("close")[0];
	// Close button on click, close it
	span.onclick = function() {
	    modal.style.display = "none";
	}
	var span1 = document.getElementsByClassName("close1")[0];
	span1.onclick = function() {
	    modal.style.display = "none";
	    
	}
	
	// Get the close button element
	var closeBtn = document.querySelector("#myModalMonthwet .close1");

	// Add click event listener to the close button
	closeBtn.addEventListener("click", function() {
	    // Get the modal element
	    var modal = document.getElementById("myModalMonthwet");
	    
	    // Close the modal by setting its display to "none"
	    modal.style.display = "none";
	});

	////========== comment end for modal user docs	
	
    $.ajaxSetup({
	    async: false
	});
	callAmPieChart();	
	$.ajaxSetup({
	    async: false
	});
	//callAmStackedChart();	
// 	callAmClusterdDocTypeModuleChart();
// 	$.ajaxSetup({
// 	    async: false
// 	});
	callAmClusterdChart();
	
	$.ajaxSetup({
	    async: false
	});
	
callAmClusterdChartWET();
	
	$.ajaxSetup({
	    async: false
	});
	//callAmPieChartUnitWise();
	callAmClusterdChartCommandByAction();
	$.ajaxSetup({
	    async: false
	});
	chrodChartCommandMov();
	$.ajaxSetup({
	    async: false
	});	
	
	callAmClusterdChartPTWByAction();
	$.ajaxSetup({
	    async: false
	});	
	
	mockjax1('applicanttbl');
	table = dataTable11('applicanttbl');
	
	mockjax1('WETPET_DATA');
	table2 = dataTable11('WETPET_DATA');
	
	
});

var popupWindow = null;
function openActiveUnitDetails() {
 	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("UnitDetaisReport?type=activeunits", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
	}
	else	
		popupWindow = window.open("UnitDetaisReport?type=activeunits", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
}

var popupWindow = null;
function openUnitMovDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("UnitDetaisReport?type=unitmove", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
	}
	else
		popupWindow = window.open("UnitDetaisReport?type=unitmove", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
}

var popupWindow = null;
function openDocTypeDetails(val)
{
	//var url="DocTypeReport?type="+val+"&pdf=N";
	var url="DocTypeReport?type="+val;
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open(url, "_blank", "toolbar=no,titlebar=no, directories=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
	}
	else
		popupWindow = window.open(url, "_blank", "toolbar=no,titlebar=no, directories=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
}

function openDocTypeDetailsWet(val)
{
	//var url="DocTypeReport?type="+val+"&pdf=N";
	var url="DocTypeReportWetPetFet?type="+val;
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open(url, "_blank", "toolbar=no,titlebar=no, directories=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
	}
	else
		popupWindow = window.open(url, "_blank", "toolbar=no,titlebar=no, directories=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
}

/* function openDocTypeDetailsWPF(val)
{
	//var url="DocTypeReport?type="+val+"&pdf=N";
	var url="DocTypeReportWPF?type="+val;
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open(url, "_blank", "toolbar=no,titlebar=no, directories=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
	}
	else
		popupWindow = window.open(url, "_blank", "toolbar=no,titlebar=no, directories=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=100,width=1400,height=650,fullscreen=no");
} */
</script>


<script>
//Pie Chart 3d show
function callAmPieChart() {
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("piediv", am4charts.PieChart);
	chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
	chart.data =${getUEByRankCat};	

	var pieSeries = chart.series.push(new am4charts.PieSeries());
	pieSeries.dataFields.value = "total";
	pieSeries.dataFields.category = "rank_category";
	//pieSeries.innerRadius = am4core.percent(30);
	pieSeries.ticks.template.disabled = true;
	pieSeries.labels.template.disabled = true;
	pieSeries.slices.template.stroke = am4core.color("#ccc");
	pieSeries.slices.template.strokeWidth = 0;
	pieSeries.slices.template.strokeOpacity = 1;
	
	pieSeries.dataFields.radiusValue = "total";	
	pieSeries.slices.template.cornerRadius = 6;
	pieSeries.hiddenState.properties.endAngle = -90;
	
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
	  return new am4core.color(color);
	});
	pieSeries.colors = colorSet;
	
	
	pieSeries.slices.template.events.on("hit", function(ev) {
        var sliceData = ev.target.dataItem.dataContext;
//         console.log(sliceData);
        showRankCatWiseUE(sliceData);
      
    });
	
	chart.legend = new am4charts.Legend();
	chart.legend.useDefaultMarker = true;	
	var marker1 = chart.legend.markers.template.children.getIndex(0);
	marker1.cornerRadius(12, 12, 12, 12);
	marker1.strokeWidth = 2;
	marker1.strokeOpacity = 1;
	marker1.stroke = am4core.color("#ccc"); 
	
 	//////////////////////////////// end pie chart

}

function showRankCatWiseUE(sliceData)
{

//var url="DocTypeReport?type="+val+"&pdf=N";
//var url="unitMoveReport?type="+value;
var x = screen.width/2 - 1100/2;
      var y = screen.height/2 - 900/2;
      popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
window.onfocus = function () { 
}
var cat= sliceData.rank_category;

$("#cat_id").val(cat);

document.getElementById('viewRankCatForm').submit(); 

}
</script>	
<style>
.amcharts-pie-slice {
  transform: scale(1);
  transform-origin: 40% 50%;
  transition-duration: 0.3s;
  transition: all .3s ease-out;
  -webkit-transition: all .3s ease-out;
  -moz-transition: all .3s ease-out;
  -o-transition: all .3s ease-out;
  cursor: pointer;
  box-shadow: 0 0 30px 0 #000;
}

.amcharts-pie-slice:hover {
  transform: scale(1.07);
  filter: url(#shadow);
}	

.amcharts-legend-div {
  overflow-y: auto!important;
  max-height: 400px;
  overflow-x: auto!important;
  max-width: 400px;
}	

.line {
    width: 100%;
    display: block;
    /* margin-top: 1rem; */
    margin-bottom: 1rem;
    border: 0;
    border-top-color: currentcolor;
    border-top-style: none;
    border-top-width: 0px;
    border-top: 2px solid #f0eaea; /* 1px solid #eee; */
}

</style>



<script type="text/javascript">

 function callAmClusterdChartPTWByAction()
{
	
	var fromDate=$("#fromDatPTW").val();
	var toDate=$("#toDatePTW").val();
	
	
	
	$.ajaxSetup({
	    async: false
	});		
		
	clusterddiv.legend = new am4charts.Legend();
	//clusterddiv.legend.position = "right";	
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc");
	
	var key = "${_csrf.parameterName}";
    var value = "${_csrf.token}";
    $.post("getCueDocTypeWiseModule?"+key+"="+value,{fromDate: fromDate, toDate: toDate}).done(function(j) {
    	var getDateWiseUE = new Array();				
			for(var i=0;i<j.length;i++){ 				
				getDateWiseUE.push({'we_pe': j[i].we_pe,'noofwes': j[i].noofwes, 'noofwes_pers': j[i].noofwes_pers, 'noofwes_trans': j[i].noofwes_trans, 'noofwes_weap': j[i].noofwes_weap});  				 			
			}	    
	
			if(getDateWiseUE.length > 0){	
		
				
				am4core.useTheme(am4themes_animated);
				var clusterddiv = am4core.create("clusterdDocTypeModulediv", am4charts.XYChart);
			//===== scrollbar for zoom level
			clusterddiv.scrollbarX = new am4core.Scrollbar();	
			
			//===== Export chart button
			/* categoryAxis_cluster.exporting.menu = new am4core.ExportMenu();
			categoryAxis_cluster.exporting.menu.align = "right";
			categoryAxis_cluster.exporting.menu.verticalAlign = "top"; */
			
			clusterddiv.data = j;  //${getCueTypeYearWiseUE};
			
			var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
			categoryAxis_cluster.dataFields.category = "we_pe";
			categoryAxis_cluster.title.text = "Document Type";
			categoryAxis_cluster.renderer.grid.template.location = 0;
			categoryAxis_cluster.renderer.minGridDistance = 30;
			categoryAxis_cluster.renderer.cellStartLocation = 0.1;
			categoryAxis_cluster.renderer.cellEndLocation = 0.9;
			categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
			categoryAxis_cluster.renderer.labels.template.rotation = 360; 
			
			
			//categoryAxis_cluster.renderer.inversed = true;
			
			var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text = "No. of WE/PE/FE/GSL";
	valueAxis_cluster.rangeChangeDuration = 500;
			//valueAxis_cluster.renderer.opposite = true;
			
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
			
			// Create series
			function createSeries_cluster(field, name) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "we_pe";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name} : [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;	 	
	}
				
			createSeries_cluster("noofwes", "Total Uploaded", true);
			createSeries_cluster("noofwes_pers", "Personnel", true);
			createSeries_cluster("noofwes_trans", "Transport", true);
			createSeries_cluster("noofwes_weap", "Weapon", true);
				
			clusterddiv.legend = new am4charts.Legend();
			//clusterddiv.legend.position = "right";	
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
var year_wepefe;
function callAmClusterdChart()
{	
	if($("#fromYearDocType").val() == "")
	{
		alert("Please select From Year in Year Wise Uploaded WE/PE/FE/GSL");
		$("#fromYearDocType").focus();
		return false;
	}
	else if($("#toYearDocType").val() == "")
	{
		alert("Please select To Year in Year Wise Uploaded WE/PE/FE/GSL");
		$("#toYearDocType").focus();
		return false;
	}	
	else{
		var fromYear=$("#fromYearDocType").val();
		var toYear=$("#toYearDocType").val();
		var arm=$("#arm").val();
		
		$.ajaxSetup({
		    async: false
		});		
		
		var key = "${_csrf.parameterName}";
	    var value = "${_csrf.token}";
	    $.post("getCueTypeYearWiseUE?"+key+"="+value,{fromYear: fromYear, toYear: toYear,arm : arm}).done(function(j) {
			var getYearWiseUE = new Array();				
  			for(var i=0;i<j.length;i++){ 				
  				getYearWiseUE.push({'getyear': j[i].getyear,'we': j[i].we, 'pe': j[i].pe, 'fe': j[i].fe, 'gsl': j[i].gsl});  				 			
  			}	    
		
  			if(getYearWiseUE.length > 0){	
				am4core.useTheme(am4themes_animated);
				var clusterddiv = am4core.create("clusterddiv", am4charts.XYChart);
				//===== scrollbar for zoom level
				clusterddiv.scrollbarX = new am4core.Scrollbar();	
				
				//===== Export chart button
				/* categoryAxis_cluster.exporting.menu = new am4core.ExportMenu();
				categoryAxis_cluster.exporting.menu.align = "right";
				categoryAxis_cluster.exporting.menu.verticalAlign = "top"; */
				
				clusterddiv.data = getYearWiseUE;  //${getCueTypeYearWiseUE};
				
				var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
				categoryAxis_cluster.dataFields.category = "getyear";
				categoryAxis_cluster.title.text = "Year";
				categoryAxis_cluster.renderer.grid.template.location = 0;
				categoryAxis_cluster.renderer.minGridDistance = 30;
				categoryAxis_cluster.renderer.cellStartLocation = 0.1;
				categoryAxis_cluster.renderer.cellEndLocation = 0.9;
				categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
				categoryAxis_cluster.renderer.labels.template.rotation = 360; 
				//categoryAxis_cluster.renderer.inversed = true;
				
				var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
				valueAxis_cluster.min = 0;
				valueAxis_cluster.title.text = "No. of UE";
				valueAxis_cluster.rangeChangeDuration = 500;
				//valueAxis_cluster.renderer.opposite = true;
				
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
				
				// Create series
				function createSeries_cluster(field, name) {
				  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
				  	series_cluster.dataFields.valueY = field;
				  	series_cluster.dataFields.categoryX = "getyear";
				  	series_cluster.name = name;
				  	series_cluster.columns.template.tooltipText = "{name} : [bold]{valueY}[/]";
				  	series_cluster.columns.template.height = am4core.percent(100);
				  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
				  	series_cluster.sequencedInterpolation = true;		 
				  	
				  	series_cluster.columns.template.events.on("hit",function(ev){
				  		//alert(ev.target.dataItem.dataContext.getyear);
				  	 	var key = "${_csrf.parameterName}";
					    var value = "${_csrf.token}";
					 
				        var filterData = series_cluster.dataFields;
// 	 					console.log(filterData.valueY);
	 					
	 					var arm1 =$("#arm").val();
					    $.post("getCueTypeMonthWiseUE?"+key+"="+value,{year : ev.target.dataItem.dataContext.getyear, doc_type : filterData.valueY ,arm1 : arm1}).done(function(j) {	  			
					    	year_wepefe = ev.target.dataItem.dataContext.getyear;
					    	var getMonthWiseUE = new Array();
				  	 		for(var i=0;i<j.length;i++){	  			  				
				  				var month="";
				  				if(j[i].getmonth == "0"){ month=""};
				  				if(j[i].getmonth == "01"){ month="January"};
				  				if(j[i].getmonth == "02"){ month="February"};
				  				if(j[i].getmonth == "03"){ month="March"};
				  				if(j[i].getmonth == "04"){ month="April"};
				  				if(j[i].getmonth == "05"){ month="May"};
				  				if(j[i].getmonth == "06"){ month="June"};
				  				if(j[i].getmonth == "07"){ month="July"};
				  				if(j[i].getmonth == "08"){ month="August"};
				  				if(j[i].getmonth == "09"){ month="September"};
				  				if(j[i].getmonth == "10"){ month="October"};
				  				if(j[i].getmonth == "11"){ month="November"};
				  				if(j[i].getmonth == "12"){ month="December"};
				  				
				  				if(month != "")
				  					getMonthWiseUE.push({'getmonth': month,'we': j[i].we, 'pe': j[i].pe, 'fe': j[i].fe, 'gsl': j[i].gsl});  				 			
				  			}	    
						
				  			if(getMonthWiseUE.length > 0){
				  				var modal = document.getElementById('myModalMonth');
				  				modal.style.display = "block";
				  				
				  				document.getElementById("h4HeadId").innerHTML=ev.target.dataItem.dataContext.getyear+" : Month Wise Uploaded WE/PE/FE/GSL";
				  				callAmClusterdChartMonthWise(getMonthWiseUE, filterData);
				  				table.ajax.reload();
				  			}
				  			else
				  			{
				  				var modal = document.getElementById('myModalMonth');
				  				modal.style.display = "none";	
				  				document.getElementById("h4HeadId").innerHTML="";
				  			}  		
				  		 }).fail(function(xhr, textStatus, errorThrown) {
				  		});   
				  		
				  	},this); 
				  	
				}
					
				createSeries_cluster("we", "WE", true);
				createSeries_cluster("pe", "PE", true);
				createSeries_cluster("fe", "FE", true);
				createSeries_cluster("gsl", "GSL", true);
					
				clusterddiv.legend = new am4charts.Legend();
				//clusterddiv.legend.position = "right";	
				clusterddiv.legend.useDefaultMarker = true;	
				var marker = clusterddiv.legend.markers.template.children.getIndex(0);
				marker.cornerRadius(12, 12, 12, 12);
				marker.strokeWidth = 2;
				marker.strokeOpacity = 1;
				marker.stroke = am4core.color("#ccc");
	  		}
  			if(getYearWiseUE.length =='0'){
  				$("#clusterddiv").html("<p style='text-align: center; margin-top: 20%;'>No data available to display.</p>");

  			}
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
}

var doc_data_WET ;
var get_year_WET ;

function callAmClusterdChartWET()
{	debugger;
	if($("#fromYearPTW").val() == "")
	{
		alert("Please select From Year in Year Wise Uploaded WE/PE/FE/GSL");
		$("#fromYearPTW").focus();
		return false;
	}
	else if($("#toYearPTW").val() == "")
	{
		alert("Please select To Year in Year Wise Uploaded WE/PE/FE/GSL");
		$("#toYearPTW").focus();
		return false;
	}	
	else{
		var fromYear=$("#fromYearPTW").val();
		var toYear=$("#toYearPTW").val();
		var arm=$("#armwet").val();
		
		$.ajaxSetup({
		    async: false
		});		
		
		var key = "${_csrf.parameterName}";
	    var value = "${_csrf.token}";
	    $.post("getCueTypeYearWiseUEwet?"+key+"="+value,{fromYear: fromYear, toYear: toYear,arm : arm}).done(function(j) {
			var getYearWiseUEwet = new Array();				
  			for(var i=0;i<j.length;i++){ 				
  				getYearWiseUEwet.push({'getyear': j[i].getyear,'wet': j[i].wet, 'pet': j[i].pet, 'fet': j[i].fet});  				 			
  			}	    
  			
		
  			if(getYearWiseUEwet.length > 0){	
				am4core.useTheme(am4themes_animated);
				var clusterddiv = am4core.create("clusterdWETdiv", am4charts.XYChart);
				//===== scrollbar for zoom level
				clusterddiv.scrollbarX = new am4core.Scrollbar();	
				
				//===== Export chart button
				/* categoryAxis_cluster.exporting.menu = new am4core.ExportMenu();
				categoryAxis_cluster.exporting.menu.align = "right";
				categoryAxis_cluster.exporting.menu.verticalAlign = "top"; */
				
				clusterddiv.data = getYearWiseUEwet;  //${getCueTypeYearWiseUE};
				
				var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
				categoryAxis_cluster.dataFields.category = "getyear";
				categoryAxis_cluster.title.text = "Year";
				categoryAxis_cluster.renderer.grid.template.location = 0;
				categoryAxis_cluster.renderer.minGridDistance = 30;
				categoryAxis_cluster.renderer.cellStartLocation = 0.1;
				categoryAxis_cluster.renderer.cellEndLocation = 0.9;
				categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
				categoryAxis_cluster.renderer.labels.template.rotation = 360; 
				//categoryAxis_cluster.renderer.inversed = true;
				
				var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
				valueAxis_cluster.min = 0;
				valueAxis_cluster.title.text = "No. of UE";
				valueAxis_cluster.rangeChangeDuration = 500;
				//valueAxis_cluster.renderer.opposite = true;
				
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
				
				// Create series
				function createSeries_cluster(field, name) {
					var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
			        series_cluster.dataFields.valueY = field;
			        series_cluster.dataFields.categoryX = "getyear";
			        series_cluster.name = name;
			        series_cluster.columns.template.tooltipText = "{name} : [bold]{valueY}[/]";
			        series_cluster.columns.template.height = am4core.percent(100);
			        series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
			        series_cluster.sequencedInterpolation = true;	 
				  	
				  	series_cluster.columns.template.events.on("hit",function(ev){
				  		//alert(ev.target.dataItem.dataContext.getyear);
				  	 	var key = "${_csrf.parameterName}";
					    var value = "${_csrf.token}";
					  
				        var filterData = series_cluster.dataFields;
// 	 					console.log(filterData.valueY);
	 					 doc_data_WET =filterData.valueY;
	 					get_year_WET = ev.target.dataItem.dataContext.getyear
	 					var armwet =$("#armwet").val();
					    $.post("getCueTypeMonthWiseUEwet?"+key+"="+value,{year : ev.target.dataItem.dataContext.getyear, doc_type : filterData.valueY ,armwet : armwet}).done(function(j) {	  			
			  				var getMonthWiseUEwet = new Array();
				  	 		for(var i=0;i<j.length;i++){	  			  				
				  				var month="";
				  				if(j[i].getmonth == "0"){ month=""};
				  				if(j[i].getmonth == "01"){ month="January"};
				  				if(j[i].getmonth == "02"){ month="February"};
				  				if(j[i].getmonth == "03"){ month="March"};
				  				if(j[i].getmonth == "04"){ month="April"};
				  				if(j[i].getmonth == "05"){ month="May"};
				  				if(j[i].getmonth == "06"){ month="June"};
				  				if(j[i].getmonth == "07"){ month="July"};
				  				if(j[i].getmonth == "08"){ month="August"};
				  				if(j[i].getmonth == "09"){ month="September"};
				  				if(j[i].getmonth == "10"){ month="October"};
				  				if(j[i].getmonth == "11"){ month="November"};
				  				if(j[i].getmonth == "12"){ month="December"};
				  				
				  				if(month != "")
				  					getMonthWiseUEwet.push({'getmonth': month,'wet': j[i].wet, 'pet': j[i].pet, 'fet': j[i].fet});  				 			
				  			}	    
						
				  			if(getMonthWiseUEwet.length > 0){
				  				var modal = document.getElementById('myModalMonthwet');
				  				modal.style.display = "block";
				  				table2.ajax.reload();
				  				document.getElementById("h4HeadIdwet").innerHTML=ev.target.dataItem.dataContext.getyear+" : Month Wise Uploaded WET/PET/FET";
				  				callAmClusterdChartMonthWisewet(getMonthWiseUEwet,filterData);
				  			}
				  			else
				  			{
				  				var modal = document.getElementById('myModalMonthwet');
				  				modal.style.display = "none";	
				  				document.getElementById("h4HeadIdwet").innerHTML="";
				  			}  		
				  		 }).fail(function(xhr, textStatus, errorThrown) {
				  		});   
				  		
				  	},this); 
				  	
				}
					
				createSeries_cluster("wet", "WET", true);
				createSeries_cluster("pet", "PET", true);
				createSeries_cluster("fet", "FET", true);
// 				createSeries_cluster("gsl", "GSL", true);
					
				clusterddiv.legend = new am4charts.Legend();
				//clusterddiv.legend.position = "right";	
				clusterddiv.legend.useDefaultMarker = true;	
				var marker = clusterddiv.legend.markers.template.children.getIndex(0);
				marker.cornerRadius(12, 12, 12, 12);
				marker.strokeWidth = 2;
				marker.strokeOpacity = 1;
				marker.stroke = am4core.color("#ccc");
	  		}
  			if(getYearWiseUEwet.length =='0'){
  				$("#clusterdWETdiv").html("<p style='text-align: center; margin-top: 30%;'>No data available to display.</p>");

  			}
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
}
var doc_data ;
function callAmClusterdChartMonthWise(getMonthWiseUE, filterData)
{	
	doc_data = filterData.valueY;
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("clusterdMonthdiv", am4charts.XYChart);
	//===== scrollbar for zoom level
	clusterddiv.scrollbarX = new am4core.Scrollbar();	
// 	console.log(filterData);
	//===== Export chart button
	/* categoryAxis_cluster.exporting.menu = new am4core.ExportMenu();
	categoryAxis_cluster.exporting.menu.align = "right";
	categoryAxis_cluster.exporting.menu.verticalAlign = "top"; */
	
	clusterddiv.data = getMonthWiseUE;
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "getmonth";
	categoryAxis_cluster.title.text = "Month";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
	//categoryAxis_cluster.renderer.inversed = true;
	
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text = "No. of UE";
	valueAxis_cluster.rangeChangeDuration = 500;
	//valueAxis_cluster.renderer.opposite = true;
	
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
	 
	  if(doc_data=='we'){
		  console.log(doc_data);
		  return new am4core.color("#388E3C");
	  }
	  if(doc_data=='pe'){
		  console.log(doc_data);
		  return new am4core.color("#FBC02D");
	  }
	  if(doc_data=='fe'){
		  console.log(doc_data);
		  return new am4core.color("#0288d1");
	  }
	  if(doc_data=='gsl'){
		  console.log(doc_data);
		  return new am4core.color("#F44336");
	  }
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	
	// Create series
	function createSeries_cluster(field, name) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "getmonth";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name} : [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;		  	
	}
		if (filterData.valueY=="we"){
	createSeries_cluster("we", "WE", true);

		}
		else if (filterData.valueY=="pe"){

	createSeries_cluster("pe", "PE",true);

		}
		else if (filterData.valueY=="fe"){
	createSeries_cluster("fe", "FE", true);
		}else if (filterData.valueY=="gsl"){
	createSeries_cluster("gsl", "GSL", true);
		}
	clusterddiv.legend = new am4charts.Legend();
	//clusterddiv.legend.position = "right";	
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc");
	
}



function data(tableName) {
	jsondata = [];

	var table = $('#'+tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];
	
	var arm_a=$("#arm").val();
	var year = year_wepefe;
	var doc_type = doc_data;
	
	var doc_wet= doc_data_WET;
	var year_wet =get_year_WET ;
	var arm_wet=$("#armwet").val();
	
	if (tableName == "applicanttbl") {
		$.post("get_Dtl_doc_type_month_wiseCountsum?" + key + "=" + value,{Search:Search,
			arm_a : arm_a,
			doc_type : doc_type,
			year : year
		 },  function(j) {
			FilteredRecords = j;
		});

		$.post("get_Dtl_doc_type_month_wise?" + key + "=" + value, {	
			
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			
			arm_a : arm_a,
			doc_type : doc_type,
			year : year
// 			rank : rank
		}).done(function(j) {

			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
// 					console.log(j[i].sus_no);
					
				jsondata.push([sr, j[i].sus_no,j[i].unit_name,j[i].uploaded_wepe ,j[i].we_pe_no
					, j[i].doc_type,j[i].created_by]);
				
				
			}
		});
	}
	
	if (tableName == "WETPET_DATA") {
		
		$.post("get_Dtl_doc_type_month_wise_WET_PETCountsum?" + key + "=" + value,{Search:Search,
			arm_wet : arm_wet,
			doc_wet : doc_wet,
			year_wet : year_wet
		 },  function(j) {
			FilteredRecords = j;
		});
		
		$.post("get_Dtl_doc_type_month_wise_WET_PET?" + key + "=" + value, {	
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			arm_wet : arm_wet,
			doc_wet : doc_wet,
			year_wet : year_wet
// 			rank : rank
		}).done(function(j) {

			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
// 					console.log(j[i].sus_no);
					
				jsondata.push([sr, j[i].sus_no,j[i].unit_name,j[i].uploaded_wetpet 
					, j[i].doc_type,j[i].created_by]);
				
				
			}
		});
	}
}

function callAmClusterdChartMonthWisewet(getMonthWiseUEwet, filterData)
{	
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("clusterdMonthwetdiv", am4charts.XYChart);
	//===== scrollbar for zoom level
	clusterddiv.scrollbarX = new am4core.Scrollbar();	
	
	//===== Export chart button
	/* categoryAxis_cluster.exporting.menu = new am4core.ExportMenu();
	categoryAxis_cluster.exporting.menu.align = "right";
	categoryAxis_cluster.exporting.menu.verticalAlign = "top"; */
	
	clusterddiv.data = getMonthWiseUEwet;
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "getmonth";
	categoryAxis_cluster.title.text = "Month";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
	//categoryAxis_cluster.renderer.inversed = true;
	
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text = "No. of UE";
	valueAxis_cluster.rangeChangeDuration = 500;
	//valueAxis_cluster.renderer.opposite = true;
	
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
		  if(filterData.valueY=='wet'){
			  console.log(doc_data);
			  return new am4core.color("#388E3C");
		  }
		  if(filterData.valueY=='pet'){
			  console.log(doc_data);
			  return new am4core.color("#FBC02D");
		  }
		  if(filterData.valueY=='fet'){
			  console.log(doc_data);
			  return new am4core.color("#0288d1");
		  }
		 
		
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	
	// Create series
	function createSeries_cluster(field, name) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "getmonth";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name} : [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;		  	
	}
		
	if (filterData.valueY=="wet"){
		createSeries_cluster("wet", "WET", true);

			}
			else if (filterData.valueY=="pet"){

		createSeries_cluster("pet", "PET",true);

			}
			else if (filterData.valueY=="fet"){

				createSeries_cluster("fet", "FET",true);

					}
	
// 	createSeries_cluster("wet", "WET", true);
// 	createSeries_cluster("pet", "PET", true);
// 	createSeries_cluster("fet", "FET", true);
// // 	createSeries_cluster("gsl", "GSL", true);
		
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

<!-- <script>
//Pie Chart 3d show
function callAmPieChartUnitWise() {
	function generateDataPie() {
		var chartData1 = [];		
		$.ajax({
			url : "getUnitWisePieChart",
			type : 'GET',
			async : false,
			contentType : "application/json",
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					var randomColor = "#000000".replace(/0/g,function() {
						return (~~(Math.random() * 16)).toString(16);
					});
					chartData1.push({
						"country" : data[i].type_label, //data[i][0],
						"visits" : data[i].total,  //data[i][1],
						"color" : randomColor
					});					
				}
			}
		});
		return chartData1;
	}
	////////////////////////////////////////////piechart
	AmCharts.updateQueue = [];
	AmCharts.queueDataUpdate = function(chart, data) {
		// chart is already in queue?
		for (var i = 0; i < AmCharts.updateQueue; i++) {
			if (AmCharts.updateQueue[i].chart = chart) {
				AmCharts.updateQueue[i].data = data
				return;
			}
		}
		// insert into queue
		AmCharts.updateQueue.push({
			"chart" : chart,
			"data" : data
		});
		// process next item
		AmCharts.processUpdateQueue();
	};

	AmCharts.processUpdateQueue = function() {
		if (AmCharts.updateQueue.length) {
			var item = AmCharts.updateQueue.shift();
			item.chart.dataProvider = item.data;
			item.chart.validateData();
		}
	};

	AmCharts.addInitHandler(function(chart) {
		chart.addListener("dataUpdated", function() {
			// process the next update in queue
			AmCharts.processUpdateQueue();
		})
	}, [ "pie" ]);

	var chart1 = AmCharts.makeChart("pieUnitdiv",
	{
		"labelsEnabled" : false,
		"type" : "pie",
		"theme" : "light",
		"startDuration" : 0,
		"addClassNames" : true,
		"legend" : {
			"position" : "right",
			"marginRight" : 25,
			"autoMargins" : false
		},
		"innerRadius" : "30%",
		"defs" : {
			"filter" : [ {
				"id" : "shadow",
				"width" : "200%",
				"height" : "200%",
				"feOffset" : {
					"result" : "offOut",
					"in" : "SourceAlpha",
					"dx" : 0,
					"dy" : 0
				},
				"feGaussianBlur" : {
					"result" : "blurOut",
					"in" : "offOut",
					"stdDeviation" : 5
				},
				"feBlend" : {
					"in" : "SourceGraphic",
					"in2" : "blurOut",
					"mode" : "normal"
				}
			} ]
		},
		"dataProvider" : generateDataPie(),
		"titles" : [ {
			"text" : ""
		} ],
		"valueField" : "visits",
		"titleField" : "country",
		"pullOutRadius" : 0,
		"labelRadius" : 1,
		"fontSize" : 12,
		"marginLeft" : 10,
		"marginRight" : 10,
		"marginBottom" : 10,
		"marginTop" : 10,
		"outlineAlpha" : 0.4,		
		"depth3D" : 15,
		"balloonText" : "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
		"angle" : 30,
		"autoMargins" : false,
		"showHandOnHover":true

	});
}
</script> -->

<script>
function chrodChartCommandMov()
{	
	if($("#fromDateChord").val() == "")
	{
		alert("Please select From Date in ICMA Chart");
		$("#fromDateChord").focus();
		return false;
	}
	else if($("#toDateChord").val() == "")
	{
		alert("Please select To Date in ICMA Chart");
		$("#toDateChord").focus();
		return false;
	}	
	else{
		var fromDate=$("#fromDateChord").val();
		var toDate=$("#toDateChord").val();

		$.ajaxSetup({
		    async: false
		});				
		var key = "${_csrf.parameterName}";
	    var value = "${_csrf.token}";
	    $.post("getCommandWiseUnitMov?"+key+"="+value,{fromDate: fromDate, toDate: toDate}).done(function(j) {
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
	  				
  					if(!j[i].count1 == 0)
	  					cmdArray.push({from: cmdname, to: "SC", value: j[i].count1});
  					totalArray.push(j[i].count1);
	  				if(!j[i].count2 == 0)
	  					cmdArray.push({from: cmdname, to: "EC", value: j[i].count2});
	  				totalArray.push(j[i].count2);
	  				if(!j[i].count3 == 0)
	  					cmdArray.push({from: cmdname, to: "WC", value: j[i].count3});
	  				totalArray.push(j[i].count3);
	  				if(!j[i].count4 == 0)
	  					cmdArray.push({from: cmdname, to: "CC", value: j[i].count4});
	  				totalArray.push(j[i].count4);
	  				if(!j[i].count5 == 0)
	  					cmdArray.push({from: cmdname, to: "NC", value: j[i].count5});
	  				totalArray.push(j[i].count5);
	  				if(!j[i].count6 == 0)
	  					cmdArray.push({from: cmdname, to: "ARTRAC", value: j[i].count6});
	  				totalArray.push(j[i].count6);
	  				if(!j[i].count7 == 0)
	  					cmdArray.push({from: cmdname, to: "ANC", value: j[i].count7});
	  				totalArray.push(j[i].count7);
	  				if(!j[i].count8 == 0)
	  					cmdArray.push({from: cmdname, to: "SWC", value: j[i].count8});
	  				totalArray.push(j[i].count8);
	  				if(!j[i].count9 == 0)
	  					cmdArray.push({from: cmdname, to: "UN", value: j[i].count9});
	  				totalArray.push(j[i].count9);
	  				if(!j[i].count10 == 0)
	  					cmdArray.push({from: cmdname, to: "MOD", value: j[i].count10});
	  				totalArray.push(j[i].count10);
	  				if(!j[i].count11 == 0)
	  					cmdArray.push({from: cmdname, to: "SFC", value: j[i].count11});	 
	  				totalArray.push(j[i].count11);
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
    		label.maxWidth = 100;
    		label.rotation = 360;	
    		
    		// Configure links
    		chart.nonRibbon = false;	
    		var link = chart.links.template;
    		link.colorMode = "gradient";
    		link.fillOpacity = 0.5;
    		link.middleLine.strokeWidth = 2;
    		link.middleLine.strokeOpacity = 0.4;
	    }).fail(function(xhr, textStatus, errorThrown) {
		}); 

	}
	
	
}

</script>


<script type="text/javascript">
			/* mockjax1('getSearchcmd');
			table = dataTable('getSearchcmd'); */
			var cmd_name_dt;
function callAmClusterdChartCommandByAction()
{		
	if($("#fromDateClustCmdAct").val() == "")
	{
		alert("Please select From Date in Command Wise Status Chart");
		$("#fromDateClustCmdAct").focus();
		return false;
	}
	else if($("#toDateClustCmdAct").val() == "")
	{
		alert("Please select To Date in Command Wise Status Chart");
		$("#toDateClustCmdAct").focus();
		return false;
	}	
	else{
		var fromDate=$("#fromDateClustCmdAct").val();
		var toDate=$("#toDateClustCmdAct").val();
		
		$.ajaxSetup({
		    async: false
		});	
		var key = "${_csrf.parameterName}";
	    var value = "${_csrf.token}";
	    $.post("getCommandWiseActionCluster?"+key+"="+value,{fromDate: fromDate, toDate: toDate}).done(function(j) {
	    	cmd_name_dt =j;
			var cmdArray = new Array();				
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
				categoryAxis_cluster.title.text = "Command Name";
				categoryAxis_cluster.renderer.grid.template.location = 0;
				categoryAxis_cluster.renderer.minGridDistance = 30;
				categoryAxis_cluster.renderer.cellStartLocation = 0.1;
				categoryAxis_cluster.renderer.cellEndLocation = 0.9;
				categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
				categoryAxis_cluster.renderer.labels.template.rotation = 360; 
									
				var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
				valueAxis_cluster.min = 0;
				valueAxis_cluster.title.text = "No. of Units";
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
				
				  	 series_cluster.columns.template.events.on("hit", function(ev) {
				  		var key = "${_csrf.parameterName}";
	 				    var value = "${_csrf.token}";
	 				    
	 				   var sliceData = ev.target.dataItem.dataContext;
	 			        
	 			        var filterData = series_cluster.dataFields;
			
//  					console.log(filterData);
 					var cmd_code = ev.target.dataItem.dataContext.cmd_code;
					  	showCmdWiseUnitDtl(sliceData,filterData);				  			    
					    });
				}
				createSeries_cluster("nrus", "NRU's", true);
				createSeries_cluster("converted", "Converted", true);
				createSeries_cluster("disbanded", "Disbanded", true);
				createSeries_cluster("unit_move_details", "Unit Mov Details", true);	
				
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
}



// function showUnitMovReport(roleSubAccess111)
// {
	
// 	//var url="DocTypeReport?type="+val+"&pdf=N";
// 	//var url="unitMoveReport?type="+value;
// 		var x = screen.width/2 - 1100/2;
//     var y = screen.height/2 - 900/2;
//     popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
// 	window.onfocus = function () { 	
// 	}
// 		var frm_dt=  $("#fromDateChord").val();
// 		var to_dt=  $("#toDateChord").val();		
// 	    $("#frm_dt_a").val(frm_dt);
// 		$("#to_dt_a").val(to_dt);
// 		document.getElementById('viewForm').submit();	
	 
// 	}
	
	
	//}
	
	

// { 
	
// 	if(document.getElementById("getActUnitsInFormation").innerHTML == 0)
// 	{
// 		alert("Data Not Available");		
// 	}	
// 	else{
// 		var modal = document.getElementById('myModalUnitMovReport');
// 		modal.style.display = "block";	
// 		if(roleSubAccess111  == "MISO" ){
// 			document.getElementById("h4UnitMovHeadId").innerHTML="Inter Command Move Report";
// 		}else{
// 			document.getElementById("h4UnitMovHeadId").innerHTML="Move Report";
// 		}
		
// 		var fromDate=$("#fromDateChord").val();
// 		var toDate=$("#toDateChord").val();
// 		$.ajaxSetup({
// 		    async: false
// 		});	
		
// 		$.post("getUnitMovReport?"+key+"="+value,{fromDate: fromDate, toDate: toDate}).done(function(j) {
// 	    	$("#UnitMovReportTbody").empty();
	    
// 	    	if(j.length>0)
// 	    	{
	    		
// 	    		var row="", m=0;
// 				for(var i=0;i<j.length;i++){
					
// 					m=i+1;
// 					row += "<tr>";
// 					row += "<th width='5%' style='text-align:center;'>"+m+"</th>";
// 					row += "<th width='5%' style='text-align:center;'>"+j[i].sus_no+"</th>";
// 					row += "<th width='13%'>"+j[i].unit_name+"</th>";
// 					row += "<th width='5%' style='text-align:center;'>"+j[i].nmb_date+"</th>";		
			
// 					if(j[i].frm_cmd_name != null){
// 						row += "<th width='9%'>"+j[i].frm_cmd_name+"</th>";
// 					}else{
// 						row += "<th width='9%'></th>";
// 					}
// 					if(j[i].frm_coprs_name != null){
// 						row += "<th width='9%'>"+j[i].frm_coprs_name+"</th>";
// 					}else{
// 						row += "<th width='9%'></th>";
// 					}
// 					if(j[i].frm_div_name != null){
// 						row += "<th width='9%'>"+j[i].frm_div_name+"</th>";
// 					}else{
// 						row += "<th width='9%'></th>";
// 					}
// 					if(j[i].frm_bde_name != null){
// 						row += "<th width='9%'>"+j[i].frm_bde_name+"</th>";
// 					}else{
// 						row += "<th width='9%'></th>";
// 					}
// 					if(j[i].to_cmd_name != null){
// 						row += "<th width='9%'>"+j[i].to_cmd_name+"</th>";
// 					}else{
// 						row += "<th width='9%'></th>";
// 					}
// 					if(j[i].to_coprs_name != null){
// 						row += "<th width='9%'>"+j[i].to_coprs_name+"</th>";
// 					}else{
// 						row += "<th width='9%'></th>";
// 					}
// 					if(j[i].to_div_name != null){
// 						row += "<th width='9%'>"+j[i].to_div_name+"</th>";
// 					}else{
// 						row += "<th width='9%'></th>";
// 					}
// 					if(j[i].to_bde_name != null){
// 						row += "<th width='9%'>"+j[i].to_bde_name+"</th>";
// 					}else{
// 						row += "<th width='9%'></th>";
// 					}
// 					row += "</tr>";
// 				}
// 				$("#UnitMovReportTbody").append(row);
// 				//$("#UnitMovReport").DataTable();				
// 				//$("div#divwatermark").val('').addClass('watermarked');		
// 				//watermarkreport(); 
// 	    	}else{
// 	    		row += "<tr>";
// 				row += "<th colspan='12' style='color: red;text-align: center !important;'> Data Not Available</th>";
// 				row += "</tr>";
// 				$("#UnitMovReportTbody").append(row);
// 	    	}
// 	    }).fail(function(xhr, textStatus, errorThrown) {
// 		});	    
// 	}
// }

function showUnitMovReport(roleSubAccess111, obj)
{

//var url="DocTypeReport?type="+val+"&pdf=N";
//var url="unitMoveReport?type="+value;

var x = screen.width/2 - 1100/2;
      var y = screen.height/2 - 900/2;
      popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
window.onfocus = function () { 
}
if (obj.id=="inter_cmd_move_dtl"){
var frm_dt=   $("#fromDateChord").val();
var to_dt=   $("#toDateChord").val(); 
var formatted_frm_dt = formatDate(frm_dt);
                var formatted_to_dt = formatDate(to_dt);
      $("#frm_dt_a").val(formatted_frm_dt);
$("#to_dt_a").val(formatted_to_dt);
}
var report_id = obj.id;
$("#report_id").val(report_id);
document.getElementById('viewForm').submit(); 

}

function showCmdWiseUnitDtl(sliceData,filterData)
{

//var url="DocTypeReport?type="+val+"&pdf=N";
//var url="unitMoveReport?type="+value;
var x = screen.width/2 - 1100/2;
      var y = screen.height/2 - 900/2;
      popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
window.onfocus = function () { 
}

		var frm_dt=   $("#fromDateClustCmdAct").val();
		var to_dt=   $("#toDateClustCmdAct").val(); 
		
		var formatted_frm_dt = formatDate(frm_dt);
		var formatted_to_dt = formatDate(to_dt);
	
		 $("#frm_dt_cmd").val(formatted_frm_dt);
		$("#to_dt_cmd").val(formatted_to_dt);
		
		var cat= sliceData.cmd_name;
		var filterData = filterData.valueY;
// 		console.log(filterData);
		$("#cmd").val(cat);
		$("#Ydata").val(filterData);
// 		console.log($("#Ydata").val());
		document.getElementById('viewcmdForm').submit(); 
		
		}





function formatDate(date) {
      var d = new Date(date),
              month = '' + (d.getMonth() + 1),
              day = '' + d.getDate(),
              year = d.getFullYear();

      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

      return [ day,month, year].join('/');
}

</script>