<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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


<script src="js/upload_xls/xlsx.full.min.js"></script>

<script type="text/javascript">
var role='${role}';
</script>
<%-- <form:form name="Wepe_pers_mdfs" id="Wepe_pers_mdfs" action="Wepe_pers_mdfsAct" method='POST' commandName="Wepe_pers_mdfsCmd"> --%>
		<div class="container" align="center">
        	<div class="card">
        	<div class="card-header"><h5><b>VIEW REPORT</b></h5></div>
          		<div class="card-body card-block cue_text">


		
	  					<div class="col-md-12">
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  	<label class=" form-control-label">Report Name </label>
					                </div>
					                <div class="col-12 col-md-6">
					                <select id="report" name="report" class="form-control">
										<option value = "0">--Select--</option> 
										<option value = "RankWiseStateofIA">RANK WISE STATE STR OF IA </option>
										<option value = "ArmWiseSanctionedStrofIA">ARM WISE SANCTIONED STR of IA </option>
										<!-- <option value = "SdFormat1">AUTH RK WISE STR OF IA (CT I & CT II)</option> -->
										<!-- <option value = "ArmyAirDefenceAR">Army Air Defence : Annual Report</option> -->
<!-- 										<option value = "ArmWiseStrCTI">Arm Wise Str of CT-I ESTs In Annual Audit</option>
										<option value = "ArmWiseStrCTII">Arm Wise Str of CT-II  ESTs In Annual Audit</option>
										<option value = "DtlsOfAllUnits">Details of All Units/FMNs/HQs/EST</option>
										<option value = "DteWiseStr">Date Wise Str of Manpower In IA</option>
										<option value = "AuthStrofManpower">Auth STR of Manpower in Cat A/TRG ESTBS</option>
										<option value = "DesignCapacityofTrgCen">Design Capacity of TRG Centers</option>
										<option value = "AuthStrOfClrkInIA">Auth Str of Clerks in INDIAN ARMY</option>
										<option value = "AuthStrOfManpwrInIA">Auth Str of Manpower in INDIAN ARMY</option>
										<option value = "FormationWiseTtlStrInIA">Comd/Corps/Div/Bde Wise Total Str in IA</option>
										<option value = "AuthStrOfOffrByRank">Auth STR of Officer By Rank & Parent Arm</option>
										<option value = "AuthStrinIA">Auth Str In Indian Army</option>
										<option value = "MajMinUnits">Major/minor Units</option>
										<option value = "AuthStrOfTrade">Auth Comb Str Tradesman in IA:CT Part i & ii</option>
										<option value = "AuthStrinIADtl">Auth Str in IA Details</option>
										<option value = "AuthStrNonReg">Auth Str of Manpower in Other Than Regular Army</option>
										<option value = "AuthStrOfMnsOffr">Auth Str of MNS offrs in IA</option>
 -->									</select>
					                </div>
					            </div>	  								
	  						</div>
  						</div>
  						
							<div class="col-md-12" id="susDiv" style="Display:None">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-6">
											<label for="text-input" class=" form-control-label">SUS No </label> 
										</div>
										<div class="col-12 col-md-6">
											<input id="sus_no" name="sus_no" maxlength="8"
												style="font-family: 'FontAwesome', Arial;" value="${sus_no1}"
												placeholder="&#xF002; Search" class="form-control"
												autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-6">
											<label for="text-input" class=" form-control-label" >Unit
												Name </label>
										</div>
										<div class="col-12 col-md-6">
											<input id="unit_name" name="unit_name" class="form-control"
												value="${unit_name1}" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search" autocomplete="off">
										</div>
									</div>
								</div>
							</div>
								<div class="col-md-12" id="wePeNoDiv" style="Display:None">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-6">
												<label for="text-input" class=" form-control-label">MISO WE/PE No </label>
											</div>
											<div class="col-12 col-md-6">
												<input id="we_pe_no" name="we_pe_no" class="form-control"
													maxlength="100" autocomplete="off" style="font-family: 'FontAwesome', Arial;" placeholder="&#xF002; Search">
											</div>
										</div>
									</div>
					
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-6">
												<label for="text-input" class=" form-control-label">WE/PE Title</label>
											</div>
											<div class="col-12 col-md-6">
												<input id="table_title" name="table_title" class="form-control"
													style="font-family: 'FontAwesome', Arial;" placeholder="&#xF002; Search" autocomplete="off">
											</div>
										</div>
									</div>
								</div>

			
  						<div class="col-md-12">	
  						
  						<div class="col-md-6" id="userArmDiv" style="Display:None">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">User Arm  </label>
					                </div>
					                <div class="col-12 col-md-6">	
					                 <input type="hidden" name="setTypeOnclick" id="setTypeOnclick"  value="${wepe}"/>				                
					                <select  class="form-control" id="user_arm" name="user_arm">	
					                	 ${selectArmNameList}
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>
		                            </select>
					                </div>
					            </div>	  								
	  						</div>
	  				
 						<div class="col-md-6" id="catPersDiv" style="Display:None">
  						<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Category of Personnel</label> 
				                </div>
				                <div class="col-12 col-md-6">
				                	<select  id="category_of_persn" name="category_of_persn" class="form-control"> 
				                		<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getPersonCatListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}"  name="${item.label}" >${item.label}</option>
                 						</c:forEach>
				                	</select>
				                </div>
				            </div>							
  						</div>
  					</div>
  					
  						
  					<div class="col-md-12">	
	  					<div class="col-md-6" id="parentArmDiv" style="Display:None">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  	<label class=" form-control-label">Parent Arm</label> 
					                </div>
					                <div class="col-12 col-md-6">
					                <input  id="parent_arm"  placeholder="" class="form-control" >
					                <select  id="parent_arm1"  class="form-control" style="display: none;"></select>
					                <input type="hidden" name="arm_code" id="arm_code" maxlength="4">
					                </div>
					            </div>	  								
	  						</div>
	  						<div class="col-md-6" id="rankCatDiv" style="Display:None">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  	<label class=" form-control-label">Rank Category </label> 
					                </div>
					                <div class="col-12 col-md-6">
					                <select  id="rank_cat" name="rank_cat" class="form-control" onchange="select_rank_cat();select_rank_app_trade();return bbb111();"> 
					                	<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getTypeofRankcategoryListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}"  name="${item.label}" >${item.label}</option>
                 						</c:forEach>
						 			</select>
					                </div>
					            </div>	  								
	  						</div>			
  						</div>
  						<div class="col-md-12">
	  						<div class="col-md-6" id="apptDiv" style="Display:None">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  	<label class=" form-control-label">Appt/Trade </label>
					                </div>
					                <div class="col-12 col-md-6">
					                <input type="hidden" id="appt_trade_code" name="appt_trade">
					                <input  id="appt_trade" name="appt_trade_name" maxlength="20" class="form-control" autocomplete="off" onkeyup="return ccc111();" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" >
					                </div>
					            </div>	  								
	  						</div>
	  						<div class="col-md-6" id="rankDiv" style="Display:None">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Rank</label> 
				                </div>
				                <div class="col-12 col-md-6">
				                <select id="rank" name="rank" class="form-control"  ></select>
				                <input type="hidden"  id="rank_hide" placeholder="" class="form-control" >
				                </div>
				            </div>						
  						</div>
  					</div>
						
  					  	
  				</div>
  		
  					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
             			<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search" /> 
             			<c:if test="${role!='linedte_mp'}">
             			<input type="button" id="btnExport" class="btn btn-purple" style="padding: 5px 10px; font-size: 14px; font-weight: bold; color: white; background-color: purple;"
             			value="Export to Excel" onclick="getExcel();">
             			</c:if>
             			
             			<input type="button" id="armyAir" class="btn btn-purple" style="padding: 5px 10px; Display: none; font-size: 14px; font-weight: bold; color: white; background-color: purple;"
						value="Export to Excel" onclick="exportToExcel('ArmyAirDefenceReport', 'ArmyAirDefenceAnnualAudit')">
		</div> 
            	  
            	
				
            	  </div>
            	  
            	   
		     </div>
		     
		     
<c:url value="Excel_report_query_all_report" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex">
	   <input type="hidden" name="sus_no1" id="sus_no1" >
	   <input type="hidden" name="we_pe_no1" id="we_pe_no1">
	   <input type="hidden" name="user_arm1" id="user_arm1">
	   <input type="hidden" name="category_of_persn1" id="category_of_persn1">
	   <input type="hidden" name="parent_arm11" id="parent_arm11" value="0" />
	   <input type="hidden" name="rank_cat1" id="rank_cat1" value="0" />
	   <input type="hidden" name="appt_trade1" id="appt_trade1" value="0" />
	   <input type="hidden" name="rank1" id="rank1" value="0" />
	    <input type="hidden" name="report1" id="report1" value="0" />
</form:form> 

		   <div class="card-body">
					<div class="" id="reportDiv2i" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="rankWiseStateStrReport" 
								class="table no-margin table-striped  table-hover  table-bordered ">
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th rowSpan="3">Ser No.</th>
											<th rowSpan="3">Arm/Service</th>
											<th colSpan="21">Officers</th>
											<th rowSpan="3">JCO</th>
											<th rowSpan="3">OR</th>
											<th rowSpan="3">First Line Reinforcement</th>
											<th rowSpan="3">Foot Notes</th>
											<th rowSpan="3">Total OR</th>
											<th rowSpan="3">Grand Total</th>
											<th rowSpan="3">Remarks</th>
										</tr>
										 <tr>
											     <th colSpan="2">GEN</th>
											   	 <th colSpan="2">LT GEN</th>
											   	 <th colSpan="2">MAJ GEN</th>
											   	 <th colSpan="2">BRIG</th>
											   	 <th colSpan="2">COL</th>
											   	 <th colSpan="2">LT COL</th>
											   	 
											   	 <th colSpan="2">MAJ</th>
											   	 
											   	 <th colSpan="2">CAPT</th>
											   	 <th colSpan="2">LT</th>
											   	 <th rowSpan="2">Unsp Rks</th>
												 <th colSpan="2">Total Offr</th>
									         </tr>
									          <tr>
											     <th >CT I</th>
											   	 <th >CT II</th>
											   	 <th >CT I</th>
											   	 <th >CT II</th>
											   	 <th >CT I</th>
											   	 <th >CT II</th>
											   	 <th >CT I</th>
											   	 <th >CT II</th>
											   	<th >CT I</th>
											   	 <th >CT II</th>
											   	 <th >CT I</th>
											   	 <th >CT II</th>
											   	 <th >CT I</th>
											   	 <th >CT II</th>
											   	 <th >CT I</th>
											   	 <th >CT II</th>
											   	 <th >CT I</th>
											   	 <th >CT II</th>
											   	 <th >CT I</th>
											   	 <th >CT II</th>
											   	
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
				
				<div class="card-body">
					<div class="datatablediv" id="reportDiv2ii" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="ArmWiseSanctionedStReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th rowSpan="2">Ser No.</th>
													<th rowSpan="2">Arm/Service</th>
													<th colSpan="3">Officers</th>
													<th colSpan="3">JCO</th>
													<th colSpan="3">OR</th>
													<th rowSpan="2">First Line Reinforcement</th>
													<th rowSpan="2">Foot Notes</th>
													<th rowSpan="2">Total OR</th>
													<th rowSpan="2">Grand Total</th>
												</tr>
												 <tr>
													  <th >CT I</th>
											   	      <th >CT II</th>
													  <th>Total</th>
													  <th >CT I</th>
											   	      <th >CT II</th>
													  <th>Total</th>
													  <th >CT I</th>
											   	      <th >CT II</th>
													  <th>Total</th>
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv2iii" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="SummarySanctionedStrReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th rowSpan="2">EST</th>
													<th colSpan="7">Officers</th>
													<th rowSpan="2">Total</th>
													<th rowSpan="2">JCO</th>
													<th rowSpan="2">OR</th>
													<th rowSpan="2">First Line Reinforcement</th>
													<th rowSpan="2">Foot Notes</th>
													<th rowSpan="2">Grand Total</th>
												</tr>
												 <tr>
													  <th>GEN</th>
													  <th>LT GEN</th>
													  <th>MAJ GEN</th>
													  <th>BRIG</th>
													  <th>COL</th>
													  <th>COL/LT COL</th>
													  <th>Unspecified Rank</th>
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
				
<!-- 				ARMY AIR DEFENCE -->


<!-- HERE -->


<!-- 				<div class="card-body"> -->
<!-- 					<div class="datatablediv" id="reportDiv3" style="display:none"> -->
<!-- 						<div class=""> -->
<!-- 							<div class="watermarked" data-watermark="" id="divwatermark"> -->
<!-- 								<span id="ip"></span> -->
<!-- 								<table id="ArmyAirDefenceReport"  -->
<!-- 								class="table no-margin table-striped table-hover table-bordered report_print"> -->
<!-- 											<thead style="font-size: 15px; text-align: center;"> -->
<!-- 												<tr id="firstRow"> -->
<!-- 													<th rowSpan="3" style="width: 3%">Ser No</th> -->
<!-- 													<th rowSpan="3">Type Of Unit</th> -->
<!-- 													<th rowSpan="3">No Of Units</th> -->
<!-- 													<th colSpan="4">Total Str</th> -->
													
<!-- 												</tr> -->
<!-- 												 <tr id="secondRow"> -->
<!-- 													  <th colSpan="4">Basic Est</th> -->
<!-- 												 </tr> -->
<!-- 												 <tr id="thirdRow"> -->
<!-- 													  <th>Offrs</th> -->
<!-- 													  <th>JCOs</th> -->
<!-- 													  <th>OR</th> -->
<!-- 													  <th>Total</th> -->
<!-- 												 </tr> -->
												  
<!-- 											</thead> -->
<!-- 									<tbody> -->
<%-- 										<c:if test="${list.size() == 0}"> --%>
<!-- 											<tr> -->
<!-- 												<td colspan="12" align="center" style="color: red;">Data -->
<!-- 													Not Available</td> -->
<!-- 											</tr> -->
<%-- 										</c:if> --%>
<!-- 									</tbody> -->
<!-- 								</table> -->
				
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
				<!-- 				</div> -->


								<div class="col-md-12" id="reportDiv3" style="display:none">
									<div class="watermarked" data-watermark="" id="divwatermark">
										<span id="ip"></span>
										<table id="ArmyAirDefenceReport"
											class="table no-margin table-striped  table-hover  table-bordered ">
											<thead>
												<tr>
													<th rowspan="2">Type of Unit</th>
													<c:forEach items="${allArms}" var="arm">
														<th colspan="4">${arm}</th>
													</c:forEach>
												</tr>
												<tr>
													<c:forEach items="${allArms}" var="arm">
														<th>Offr</th>
														<th>JCO</th>
														<th>OR</th>
														<th>Total</th>
													</c:forEach>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list6}" var="unitData">
													<tr>
														<td>${unitData.unitType}</td>
														<c:forEach items="${allArms}" var="arm">
															<c:set var="personnelCounts" value="${unitData.armData[arm]}" />
															<c:choose>
																<c:when test="${not empty personnelCounts}">
																	<td>${personnelCounts[0]}</td>
																	<td>${personnelCounts[1]}</td>
																	<td>${personnelCounts[2]}</td>
																	<td>${personnelCounts[3]}</td>
																</c:when>
																<c:otherwise>
																	<td>0</td>
																	<td>0</td>
																	<td>0</td>
																	<td>0</td>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>


				<div class="card-body">
					<div class="datatablediv" id="reportDiv4i" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="ArmWiseStrCTIReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Ser No</th>
													<th>Arm/Service</th>
													<th>Offrs</th>
													<th>JCO</th>
													<th>OR</th>
													<th>Total incl 1st Line Rfts</th>
													<th>Foot Notes</th>
													<th>Total Str As on</th>
													<th>Allocation of 1st Line Rfts</th>
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
				
				<div class="card-body">
					<div class="datatablediv" id="reportDiv4ii" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="ArmWiseStrCTIIReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Ser No</th>
													<th>Arm/Service</th>
													<th>Offrs</th>
													<th>JCO</th>
													<th>OR</th>
													<th>Total incl 1st Line Rfts</th>
													<th>Foot Notes</th>
													<th>Total Str As on</th>
													<th>Allocation of 1st Line Rfts</th>
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv5" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="DtlsofAllUnitsReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th rowSpan="2">Sr No</th>
													<th rowSpan="2">Type of Unit/Fmn/HQ/Est</th>
													<th rowSpan="2">No of Unit/Fmn/HQ/Est</th>
													<th colSpan="3">Manpower Auth(Officers/JCOs/OR)</th>
													<th rowSpan="2">Remarks</th>
												</tr>
												<tr>
													  <th>Officer</th>
													  <th>JCo</th>
													  <th>OR</th>
													  
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv6" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="DteWiseStrReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Ser No</th>
													<th>DATES</th>
													<th>GEN</th>
													<th>LT GEN</th>
													<th>MEJ GEN</th>
													<th>BRIG</th>
													<th>COL</th>
													<th>LT COL</th>
													<th>MAJ</th>
													<th>CAPT</th>
													<th>LT</th>
													<th>FD MARSHAL</th>
													<th>CIV GAZ</th>
													<th>JCO</th>
													<th>OR</th>
													<th>NON GZ CIV</th>
													<th>TOTAL</th>
													<th>REMARK</th>
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv7" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrofManpower" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Name of ESTB</th>
													<th>Officers</th>
													<th>JCO</th>
													<th>OR</th>
													<th>Civ Gaz</th>
													<th>Civ Non Gaz</th>
													<th>Total</th>
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
				
					<div class="card-body">
					<div class="datatablediv" id="reportDiv8" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="DesignCapofTrgCenReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Name of TRG Center</th>
													<th>Design Capacity</th>
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv9i" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrOfClrkInIAReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Sr No.</th>
													<th>Name of Record Office</th>
													<th>Combat Ants</th>
													<th>Civ Clerks</th>
													<th>Total</th>
													<th>Remarks</th>
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv10" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrOfManpwrInIAReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>User Arm</th>
													<th>Parent Arm</th>
													<th>Officer</th>
													<th>JCO</th>
													<th>OR</th>
													<th>Total</th>
													<th>Civ Gaz</th>
													<th>Civ NG Non Industrial</th>
													<th>Civ NG Industrial</th>
													<th>NCSU</th>
													<th>Grand Total</th>
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
				
				<div class="card-body">
					<div class="datatablediv" id="reportDiv12i" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="FormationWiseTtlStrInIAReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Command</th>
													<th>Corps</th>
													<th>Div</th>
													<th>Bde</th>
													<th>Unit</th>
													<th>Officers</th>
													<th>JCO</th>
													<th>OR</th>
													<th>Civ</th>
													<th>Total</th>
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
				
				<div class="card-body">
					<div class="datatablediv" id="reportDiv12ii" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrOfOffrByRankReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th rowSpan="2">Parent Arm</th>
													<th colSpan="11">Rank</th>
													
												</tr>
													<tr>
													<th>Gen</th>
													<th>Lt Gen</th>
													<th>Maj Gen</th>
													<th>Brig</th>
													<th>Col</th>
													<th>Lt Col/Col</th>
													<th>Lt Col</th>
													<th>Maj</th>
													<th>Capt</th>
													<th>Lt</th>
													<th>Total</th>
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv14i" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrInIAReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Arm</th>
													<th>WE/PE/ No</th>
													<th>WE/PE Title</th>
													<th>Date Effective from</th>
													<th>Date Effective To</th>
													<th>No of Unit</th>
													<th>Regtl/ERE</th>
													<th>Parent Arm</th>
													<th>Officers</th>
													<th>JCO</th>
													<th>OR</th>
													<th>Civs</th>
													<th>Total</th>
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
				
				<div class="card-body">
					<div class="datatablediv" id="reportDiv14ii" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="MajMinUnitsReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Arm</th>
													<th>Unit Name</th>
													<th>Unit Category</th>
													<th>Highest Rank</th>
													<th>Appt</th>
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv15" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrOfTradeReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Trade</th>
													<th>Arms/Service</th>
													<th>WE/PE/GSL No</th>
													<th>Total No</th>
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
				
				<div class="card-body">
					<div class="datatablediv" id="reportDiv16i" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrinIADtlReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Unit SUS No</th>
													<th>Unit Name</th>
													<th>User Arm</th>
													<th>Comd</th>
													<th>Corps</th>
													<th>Div</th>
													<th>Bde</th>
													<th>WE/PE/GSL No</th>
													<th>CT-i/CT-ii</th>
													<th>Unit Category</th>
													<th>FF/NFF</th>
													<th>Officers</th>
													<th>JCO</th>
													<th>OR</th>
													<th>Civ Gaz</th>
													<th>Civ NG Non Industrial</th>
													<th>Civ NG Industrial</th>
													<th>NCSU</th>
													<th>Total</th>
													<th>Design Capacity</th>
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
				<div class="card-body">
					<div class="datatablediv" id="reportDiv16ii" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrNonRegReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Arm</th>
													<th>Officer</th>
													<th>JCO</th>
													<th>OR</th>
													<th>Civ GZ I</th>
													<th>Civ GZ II</th>
													<th>Total</th>
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
					<div class="card-body">
					<div class="datatablediv" id="reportDiv16iii" style="Display:None">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="AuthStrOfMnsOffrReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
											<thead style="font-size: 15px; text-align: center;">
												<tr>
													<th>Arm</th>
													<th>Officer</th>
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
<%-- </form:form> --%>


<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}"; 
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	
// 	clearAll();

	$(function() {
	       
		 if('${roleAccess}' != "Unit")
			{      
			 var wepetext=$("#sus_no");
			  wepetext.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
		        	type: 'POST',
			        url: "getCUEUnitsSUSNoActiveList?"+key+"="+value,
			        data: {sus_no : document.getElementById('sus_no').value},
			          success: function( data ) {
			            if(data.length > 1){
			        	  var susval = [];
			        	  var length = data.length-1;
		  	        		 var enc = data[length].columnName1.substring(0,16);
		                      for(var i = 0;i<data.length-1;i++){
		                       susval.push(dec(enc,data[i].columnName));
			        	  	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=wepetext.val();
						$.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse );
						}
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit SUS No");
			        	  document.getElementById("unit_name").value="";
			        	  wepetext.val("");	        	  
			        	  wepetext.focus();
			        	  return false;	             
			          }   	         
			      }, 	 
			      
			  	select: function( event, ui ) {
					var sus_no = ui.item.value;			      	
					 $.post("getTargetUnitNameList?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				} 
			    });
		     
			 var wepetext1=$("#unit_name");
			 wepetext1.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
		        	type: 'POST',
			        url: "getCUEUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:document.getElementById('unit_name').value},
			          success: function( data ) {
			            //response( data );
			            if(data.length > 1){
			        	  var susval = [];
			        	  var length = data.length-1;
			        		 var enc = data[length].columnName1.substring(0,16);
		                   for(var i = 0;i<data.length-1;i++){
		                    susval.push(dec(enc,data[i].columnName));
			        	  	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=wepetext1.val();
						$.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
						//	if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
						}
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit's Name");
			        	  document.getElementById("sus_no").value="";
			        	  wepetext1.val("");	        	  
			        	  wepetext1.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
				    
					            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#sus_no").val(dec(enc,data[0]));
						        	alert(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      }
			    });
			  
			  } else{
			  	$("#sus_no").attr("readonly","readonly");
			  	$("#unit_name").attr("readonly","readonly");
			  } 
		  
		}); 
});


		$(function() {
			  var wepetext1=$("#we_pe_no");
			  wepetext1.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getWePeCondiByNoInReport?"+key+"="+value,
			        data: {type : "1",we_pe_no:document.getElementById('we_pe_no').value},
			          success: function( data ) {
			            //response( data );
			            if(data.length > 1){
			        	  var susval = [];
			        	  var length = data.length-1;
			        		 var enc = data[length].columnName1.substring(0,16);
		                   for(var i = 0;i<data.length-1;i++){
		                    susval.push(dec(enc,data[i].columnName));
			        	  	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=wepetext1.val();
						$.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
							//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
						}
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved WE/PE No");
			        	  wepetext1.val("");	        	  
			        	  wepetext1.focus();
			        	  
			        	  document.getElementById("table_title").value="";
			    		  $("select#user_arm").val("0");
				        	  
			        	  return false;	             
			          }   	         
			      }, 
			         select: function( event, ui ) {
			        	$(this).val(ui.item.value);    	        	
			        	getDetailsBySupeerseedNo($(this).val());	        	
			        } 	     
			    });	  
			
		});
			 

	function getDetailsBySupeerseedNo(val) {
		if (val != "" && val != null) {
			$.post("getDetailsByWePeCondiNo?" + key + "=" + value, {val : val
					})
				.done(function(j) {
					if (j != "" && j != null) {
							document.getElementById("table_title").value = j[0].table_title;
								$("select#user_arm").val(j[0].arm);
							} else {
								document.getElementById("table_title").value = "";
								$("select#sponsor_dire").val("0");
							}
					}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
	}

	$(function() {
		var wepetext1 = $("#table_title");
		wepetext1.autocomplete({
					source : function(request, response) {
						$.ajax({type : 'POST',
								url : "getTableTitleCondiReport?" + key+ "=" + value,
								  data : {type : "1",tableTitle : document.getElementById('table_title').value},
									success : function(data) {
										if (data.length > 1) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length].columnName1
													.substring(0, 16);
											for (var i = 0; i < data.length - 1; i++) {
												susval.push(dec(enc,data[i].columnName));
											}
											var dataCountry1 = susval.join("|");
											var myResponse = [];
											var autoTextVal = wepetext1.val();
											$.each(dataCountry1.toString().split("|"),
												function(i, e) {
													var newE = e.substring(0,autoTextVal.length);
														if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
															myResponse.push(e);
														}
													});
											response(myResponse);
										}
									}
								});
					},
					minLength : 1,
					autoFill : true,
					change : function(event, ui) {
						if (ui.item) {
							return true;
						} else {
							alert("Please Enter Approved WE/Pe No");
							wepetext1.val("");
							wepetext1.focus();

							document.getElementById("we_pe_no").value = "";
							$("select#user_arm").val("0");

							return false;
						}
					},
					select : function(event, ui) {
						$(this).val(ui.item.value);
						getDetailsByTableTitle($(this).val());
					}
				});

	});

	function getDetailsByTableTitle(val) {
		if (val != "" && val != null) {

			$.post("getDetailsByWePeCondiTitle?" + key + "=" + value, {
				val : val
			}).done(function(j) {
				if (j != "" && j != null) {
					document.getElementById("we_pe_no").value = j[0].we_pe_no;
					$("select#user_arm").val(j[0].arm);
				} else {
					document.getElementById("we_pe_no").value = "";
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		} else {
			alert("Please enter Approved Superseded Document No");
			document.getElementById("we_pe_no").value = "";
		}
	}

	$('select#category_of_persn').change(function() {
		var code = $(this).find('option:selected').attr("name");
		if (code == "Regimental") {
			$("#parent_arm").val($("#user_arm_hi").val());
			document.getElementById("parent_arm").disabled = true;
			$("select#parent_arm1").hide();
			$("input#parent_arm").show();
			$("#arm_code").val($("#user_arm").val());
		} else if (code == "ERE") {
			$("#parent_arm").val("");
			document.getElementById("parent_arm").disabled = false;
			$("input#parent_arm").hide();
			$("select#parent_arm1").show();
			parentArm();
		} else {
			$("#parent_arm").val("");
			document.getElementById("parent_arm").disabled = true;
			$("select#parent_arm1").val("0");
			$("select#parent_arm1").hide();
			$("input#parent_arm").show();
			$("#arm_code").val($("#user_arm").val());
		}
	});

	function parentArm() {
		var u_a = document.getElementById("user_arm").value;
		$.post("getArmNameListCue?" + key + "=" + value, {
			u : u_a
		}).done(
				function(j) {
					var length = j.length - 1;
					var enc = j[length].columnName1.substring(0, 16);
					var options = '<option value="' + "0" + '">' + "--Select--"
							+ '</option>';
					for (var i = 0; i < j.length - 1; i++) {
						options += '<option value="'
								+ dec(enc, j[i].columnCode) + '" name="'
								+ dec(enc, j[i].columnName) + '" >'
								+ dec(enc, j[i].columnName) + '</option>';
					}
					$("select#parent_arm1").html(options);
				}).fail(function(xhr, textStatus, errorThrown) {
		});

	}

	function select_rank_app_trade() {
		var rnk = $("select#rank_cat").val();

		$('#appt_trade').val("");
		$('#app_trd_code').val("");

		var wepetext1 = $("#appt_trade");

		wepetext1.autocomplete({
			source : function(request, response) {
				$.ajax({
						type : 'POST',
						url : "getTypeappt_tradeList?" + key + "=" + value,
						data : { rnk : rnk,appt_trade : document.getElementById('appt_trade').value},
								success : function(data) {
									if (data.length > 1) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length].columnName1.substring(0, 16);
											for (var i = 0; i < data.length - 1; i++) {
												susval.push(dec(enc,data[i].columnName));}
											var dataCountry1 = susval.join("|");
											var myResponse = [];
											var autoTextVal = wepetext1.val();
											$.each(dataCountry1.toString().split("|"),
											function(i, e) {
												var newE = e.substring(0,autoTextVal.length);
												 if (e.toLowerCase().includes(
													autoTextVal.toLowerCase())) {
													myResponse.push(e);
												}
											});
										response(myResponse);
										}
									}
								});
					},
					minLength : 1,
					autoFill : true,
					change : function(event, ui) {
						if (ui.item) {
							return true;
						} else {
							alert("Please Enter Approved Common Appt/Trade");
							wepetext1.val("");
							wepetext1.focus();
							return false;
						}
					},
				});

	}

	function clearAll() {
	    window.location.reload();
	}
	
	
	function exportToExcel(tableId, fileName) {debugger;
	var table = document.getElementById(tableId);
	var ws = XLSX.utils.table_to_sheet(table);
	var wb = XLSX.utils.book_new();
	XLSX.utils.book_append_sheet(wb, ws, "AAD");
	XLSX.writeFile(wb, fileName + ".xlsx");
}
	
	function getExcel() {
		var report = $("#report").val();

		if (report == '0') {
			alert("please select report name");
			$("#report").focus();
			return false;
		}

		var appt_trade = $("#appt_trade").val();
		var rank = $("#rank").val();
		var rank_cat = $("#rank_cat").val();
		var sus_no = $("#sus_no").val();
		var we_pe_no = $("#we_pe_no").val();
		var user_arm = $("#user_arm").val();
		var category_of_persn = $("#category_of_persn").val();
		var parent_arm1 = $("#parent_arm1").val();

		$("#sus_no1").val(sus_no);
		$("#we_pe_no1").val(we_pe_no);
		$("#user_arm1").val(user_arm);
		$("#category_of_persn1").val(category_of_persn);
		$("#parent_arm11").val(parent_arm1);
		$("#rank_cat1").val(rank_cat);
		$("#appt_trade1").val(appt_trade);
		$("#rank1").val(rank);
		$("#report1").val(report);

		document.getElementById('ExcelForm').submit();
	}

	mockjax1('rankWiseStateStrReport');
	table = dataTable('rankWiseStateStrReport');

	mockjax1('ArmWiseSanctionedStReport');
	table2 = dataTable('ArmWiseSanctionedStReport');

	/* mockjax1('SummarySanctionedStrReport');
	table3 = dataTable('SummarySanctionedStrReport');

	mockjax1('ArmWiseStrCTIReport');
	table4 = dataTable('ArmWiseStrCTIReport');
	
	mockjax1('ArmWiseStrCTIIReport');
	table5 = dataTable('ArmWiseStrCTIIReport');
	
	mockjax1('DteWiseStrReport');
	table6 = dataTable('DteWiseStrReport');

	mockjax1('DesignCapofTrgCenReport');
	table7 = dataTable('DesignCapofTrgCenReport');
	
	mockjax1('DtlsofAllUnitsReport');
	table8 = dataTable('DtlsofAllUnitsReport');
	
	mockjax1('AuthStrofManpower');
	table9 = dataTable('AuthStrofManpower');
	
	mockjax1('AuthStrOfClrkInIAReport');
	table10 = dataTable('AuthStrOfClrkInIAReport');
	
	mockjax1('AuthStrOfManpwrInIAReport');
	table11 = dataTable('AuthStrOfManpwrInIAReport');
	
// 	mockjax1('ArmyAirDefenceReport');
// 	table12 = dataTable('ArmyAirDefenceReport');
	
	mockjax1('FormationWiseTtlStrInIAReport');
	table13 = dataTable('FormationWiseTtlStrInIAReport');
	
	mockjax1('AuthStrOfOffrByRankReport');
	table14 = dataTable('AuthStrOfOffrByRankReport');
	
	mockjax1('AuthStrInIAReport');
	table15 = dataTable('AuthStrInIAReport');
	
	mockjax1('MajMinUnitsReport');
	table16 = dataTable('MajMinUnitsReport');
	
	mockjax1('AuthStrOfTradeReport');
	table17 = dataTable('AuthStrOfTradeReport');
	
	mockjax1('AuthStrinIADtlReport');
	table18 = dataTable('AuthStrinIADtlReport');
	
	mockjax1('AuthStrNonRegReport');
	table19 = dataTable('AuthStrNonRegReport');
	
	mockjax1('AuthStrOfMnsOffrReport');
	table20 = dataTable('AuthStrOfMnsOffrReport'); */
	
	$('#btn-reload').on('click', function() {debugger;
	
		var report = $("#report").val();
		$("#reportDiv2i, #reportDiv2ii, #reportDiv2iii, #reportDiv3, #reportDiv4i,#reportDiv4ii,#reportDiv6,#reportDiv8").hide();

		if (report == "RankWiseStateofIA") {
			$("#reportDiv2i").show();
			table.ajax.reload();
// 			$("#btnExport").prop('disabled', false);
		}

		if (report == "ArmWiseSanctionedStrofIA") {
			$("#reportDiv2ii").show();
			table2.ajax.reload();
// 			$("#btnExport").prop('disabled', true);
		}

		/* if (report == "SdFormat1") {
			$("#reportDiv2iii").show();
			table3.ajax.reload();
		}
		if (report == "ArmWiseStrCTI") {
			$("#reportDiv4i").show();
			table4.ajax.reload();
		}
		if (report == "ArmWiseStrCTII") {
			$("#reportDiv4ii").show();
			table5.ajax.reload();
		}
		if (report == "DteWiseStr") {
			$("#reportDiv6").show();
			table6.ajax.reload();
		}
		if (report == "DesignCapacityofTrgCen") {
			$("#reportDiv8").show();
			table7.ajax.reload();
		}
		
		if (report == "DtlsOfAllUnits") {
			$("#reportDiv5").show();
			table8.ajax.reload();
		}
		if (report == "AuthStrofManpower") {
			$("#reportDiv7").show();
			table9.ajax.reload();
		}
		if (report == "AuthStrOfClrkInIA") {
			$("#reportDiv9i").show();
			table10.ajax.reload();
		}
		if (report == "AuthStrOfManpwrInIA") {
			$("#reportDiv10").show();
			table11.ajax.reload();
		}
		if (report == "ArmyAirDefenceAR") {
			$("#reportDiv3").show();
			$("#armyAir").show();
// 			table12.ajax.reload();
		}
		if (report == "FormationWiseTtlStrInIA") {debugger
			$("#reportDiv12i").show();
			table13.ajax.reload();
		}
		if (report == "AuthStrOfOffrByRank") {
			$("#reportDiv12ii").show();
			table14.ajax.reload();
		}
		if (report == "AuthStrinIA") {
			$("#reportDiv14i").show();
			table15.ajax.reload();
		}
		if (report == "MajMinUnits") {
			$("#reportDiv14ii").show();
			table16.ajax.reload();
		}
		if (report == "AuthStrOfTrade") {
			$("#reportDiv15").show();
			table17.ajax.reload();
		}
		
		if (report == "AuthStrinIADtl") {
			$("#reportDiv16i").show();
			table18.ajax.reload();
		}
		if (report == "AuthStrNonReg") {
			$("#reportDiv16ii").show();
			table19.ajax.reload();
		}
		if (report == "AuthStrOfMnsOffr") {
			$("#reportDiv16iii").show();
			table20.ajax.reload();
		} */
	});

	$('#report').on('change', function() {debugger;
	    var divsToHide = [
	    	'#armyAir', '#susDiv', '#wePeNoDiv', '#userArmDiv', '#catPersDiv','#parentArmDiv', '#rankCatDiv', '#apptDiv', '#rankDiv', 
	        '#reportDiv2i', '#reportDiv2ii', '#reportDiv2iii', '#reportDiv3', '#reportDiv4i','#reportDiv4ii','#reportDiv5','#reportDiv6','#reportDiv7','#reportDiv8',
	        '#reportDiv9i','#reportDiv10','#reportDiv12i','#reportDiv12ii','#reportDiv14i','#reportDiv14ii','#reportDiv15','#reportDiv16i','#reportDiv16ii','#reportDiv16iii' ];

	    $(divsToHide.join(', ')).hide();
	    $("#btnExport").show();
	    var reportsWithParentArmAndCatPers = [
	        'RankWiseStateofIA', 
	        'ArmWiseSanctionedStrofIA', 
	        'ArmWiseStrCTI',
	        'ArmWiseStrCTII'
	    ];

	    if (reportsWithParentArmAndCatPers.includes($(this).val())) {
	        $('#parentArmDiv, #catPersDiv').show();
	    }
	    var report = $("#report").val();
	    if (report == "ArmyAirDefenceAR") {debugger;
			$("#armyAir").show();
			$("#btnExport").hide();
// 			table12.ajax.reload();
		}
	});

	function data(tableName) {
		jsondata = [];

		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		var appt_trade = $("#appt_trade").val();
		var rank = $("#rank").val();
		var rank_cat = $("#rank_cat").val();
		var sus_no = $("#sus_no").val();
		var we_pe_no = $("#we_pe_no").val();
		var user_arm = $("#user_arm").val();
		var category_of_persn = $("#category_of_persn").val();
		var parent_arm1 = $("#parent_arm1").val();
		var s_offrs = 0;
		var s_jco = 0;
		var s_or = 0;
		var flr = 0;
		var fn = 0;
		var s_total = 0; 	var s_gtotal = 0;
		var sum_a = 0; 
		var sum_b = 0; 
		var sum_c = 0; 
		var sum_d = 0; 
		var sum_e = 0; 
		var sum_f = 0; 
		var sum_g = 0;
		var sum_h = 0;
		var sum_i = 0; 
		var sum_j = 0; 
		var sum_k = 0; 
		var sum_l = 0;  
		var sum_m = 0; 
		var sum_n = 0; 
		var sum_o = 0; 
		var sum_p = 0; 
		var sum_q = 0; 
		var sum_r = 0; 
		var sum_s = 0; 
		var sum_t = 0; 
		var sum_u = 0; 
		var sum_v = 0; 
		var sum_w = 0; 
		var sum_x = 0; 
		var flr_1 = 0;
		var flr_2 = 0;

		if (tableName == "rankWiseStateStrReport") {
			$.post("rankWiseStateStrReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("rankWiseStateStrReportCountTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				jsondata = []; //clear jsondata before adding new data

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					var current_s_or = j[i].or + j[i].flr + j[i].footnotes;
					jsondata.push([ sr, j[i].arm, j[i].officer1, j[i].officer2,j[i].officer3, j[i].officer4,j[i].majgen1,j[i].majgen1,j[i].brig1,j[i].brig2,j[i].col1,j[i].col2,
						j[i].ltcol1,j[i].ltcol2,j[i].maj1,j[i].maj2,j[i].capt1,j[i].capt2,j[i].lt1,j[i].lt2,
						j[i].unsp,j[i].total_offr1,j[i].total_offr2,j[i].jco, j[i].or,j[i].flr,j[i].footnotes,current_s_or, j[i].total, "" ]);

					sum_a += j[i].officer1;
					sum_b += j[i].officer2;
					sum_c += j[i].officer3;
					sum_d += j[i].officer4;
					sum_e += j[i].brig1;
					sum_f += j[i].brig2;
					sum_g += j[i].col1;
					sum_h += j[i].col2;
					sum_i += j[i].ltcol1;
					sum_j += j[i].ltcol2;
					sum_k += j[i].majgen1;
					sum_l += j[i].majgen2;
					sum_m += j[i].maj1;
					sum_n += j[i].maj2;
					/* sum_o += j[i].majcapt1;
					sum_p += j[i].majcapt2; */
					sum_q += j[i].capt1;
					sum_r += j[i].capt2;
					sum_s += j[i].lt1;
					sum_t += j[i].lt2;
					sum_u += j[i].unsp;
					sum_v += j[i].total_offr1;
					s_offrs += j[i].total_offr2;
					s_jco += j[i].jco;
					sum_w += j[i].or;
					flr += j[i].flr;
					s_or += current_s_or;
					fn += j[i].footnotes;
					s_total += j[i].total;
				}

				jsondata.push([ "TOTAL", "",sum_a ,sum_b,sum_c,sum_d,sum_k,sum_l,sum_e,sum_f,sum_g,sum_h,sum_i,sum_j,sum_m,sum_n,sum_q,sum_r
					,sum_s,sum_t,sum_u,sum_v,s_offrs,s_jco,sum_w,flr,fn,s_or,s_total,"" ]);
// 				table.clear().rows.add(jsondata).draw(false);
			});
		}

		if (tableName == "ArmWiseSanctionedStReport") {

			$.post("armWiseStateStrReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("armWiseStateStrReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					s_or=j[i].ort+ j[i].flr+j[i].footnotes;
					jsondata.push([ sr, j[i].arm, j[i].officer1, j[i].officer2,j[i].officert, j[i].jco1, j[i].jco2, j[i].jcot,j[i].or1, 
						j[i].or2, j[i].ort, j[i].flr,j[i].footnotes,s_or, j[i].total ]);
				
					sum_a += j[i].officer1;
					sum_b += j[i].officer2; 
					sum_c += j[i].officert; 
					sum_d += j[i].jco1; 
					sum_e += j[i].jco2; 
					sum_f += j[i].jcot; 
					sum_g += j[i].or1; 
					sum_h += j[i].or2;
					sum_i += j[i].ort; 
					sum_j += j[i].flr; 
					sum_k += j[i].footnotes; 
					sum_l += s_or; 
					sum_m += j[i].total;
				}
				jsondata.push([ "TOTAL", "",sum_a ,sum_b,sum_c,sum_d,sum_e,sum_f,sum_g,sum_h,sum_i,sum_j,sum_k,sum_l,sum_m ]);
			});
		}
		
		//ARMY AIR DEFENCE HERE
		
// 		if (tableName == "ArmyAirDefenceReport") {
					
		
// 		// 			$.post("getParentArmNameForSearch?" + key + "=" + value, {
// 		// 				Search : Search,
// 		// 				appt_trade : appt_trade,
// 		// 				rank : rank,
// 		// 				rank_cat : rank_cat,
// 		// 				sus_no : sus_no,
// 		// 				we_pe_no : we_pe_no,
// 		// 				user_arm : user_arm,
// 		// 				category_of_persn : category_of_persn,
// 		// 				parent_arm1 : parent_arm1
		
// 		// 			}, function(j) {
// 		// 				const row = "";
// 		// 				let headerRow2 = $('#ArmyAirDefenceReport thead tr#secondRow');
// 		// 				console.log(headerRow2);
// 		// 			});
			
	

// 			$.post("armyAirDefenceReportCount?" + key + "=" + value, {
// 				Search : Search,
// 				appt_trade : appt_trade,
// 				rank : rank,
// 				rank_cat : rank_cat,
// 				sus_no : sus_no,
// 				we_pe_no : we_pe_no,
// 				user_arm : user_arm,
// 				category_of_persn : category_of_persn,
// 				parent_arm1 : parent_arm1

// 			}, function(j) {
// 				FilteredRecords = j;
// 			});
// 			$.post("armyAirDefenceReportTable?" + key + "=" + value, {

// 				startPage : startPage,
// 				pageLength : pageLength,
// 				Search : Search,
// 				orderColunm : orderColunm,
// 				appt_trade : appt_trade,
// 				rank : rank,
// 				rank_cat : rank_cat,
// 				sus_no : sus_no,
// 				we_pe_no : we_pe_no,
// 				user_arm : user_arm,
// 				category_of_persn : category_of_persn,
// 				parent_arm1 : parent_arm1
// 			}, function(j) {
				
// 				if ($.fn.DataTable.isDataTable('#ArmyAirDefenceReport')) {
// 		            $('#ArmyAirDefenceReport').DataTable().destroy();
// 		        }
				
// 				// 1. Process JSON data to identify unique arms
// 	            let uniqueArms = [...new Set(j.map(item => item.arm))];

// 	            // 2. Add new columns to headers
// 	            let firstRow = $('#ArmyAirDefenceReport thead tr#firstRow');
// 	            let secondRow = $('#ArmyAirDefenceReport thead tr#secondRow');
// 	            let thirdRow = $('#ArmyAirDefenceReport thead tr#thirdRow');
				
// 	            // Clear existing columns (except the first three)
// 	            firstRow.empty();
// 	            secondRow.empty();
// 	            thirdRow.empty();

// 	            firstRow.append('<th rowspan="3" style="width: 3%">Ser No</th><th rowspan="3">Type Of Unit</th><th rowspan="3">No Of Units</th>');
// 	            firstRow.append('<th colspan="4">Total Str</th>'); //Initial Total Str
	            
// 	            let i = 0;
// 	            while (i < uniqueArms.length) {
// 	            	var armname = uniqueArms[i];
// 	            	firstRow.append('<th colspan="4"></th>');
// 	            	secondRow.append('<th colspan="4">'+armname+'</th>');
// 	                thirdRow.append('<th>Offrs</th><th>JCOs</th><th>OR</th><th>Total</th>');
// 	                i++;
// 	            }
	            
// 	            secondRow.prepend('<th colspan="4">Basic Est</th>'); //Prepend to ensure correct placement.
// 	            thirdRow.prepend('<th>Offrs</th><th>JCOs</th><th>OR</th><th>Total</th>'); //Prepend initial Basic Est
	            
// 	            // 3. Update jsondata and handle data for new columns
// 	            let armData = {}; //Store data for each arm temporarily.
// 	            j.forEach(item => {
// 	                if (!armData[item.arm]) armData[item.arm] = [0,0,0,0]; //Init to 0 for Offrs,JCOs,OR,Total
// 	                armData[item.arm][0] += parseInt(item.offr || 0); //Add offr
// 	                armData[item.arm][1] += parseInt(item.jco || 0); //Add jco
// 	                armData[item.arm][2] += parseInt(item.or || 0);  //Add or
// 	                armData[item.arm][3] += parseInt(item.total || 0); //Add total

// 	            });

// 	            jsondata = [];
// 	            j.forEach((item, index) => {
// 	            	debugger
// 	                var sr = index + 1;
// 	                let rowData = [sr, item.tt, item.tou, item.offr, item.jco, item.or, item.total];

// 	                uniqueArms.forEach(arm => {
// 	                    rowData = rowData.concat(armData[arm] || [0,0,0,0]);
// 	                })

// 	                jsondata.push(rowData);
// 	            });
	            
	            
// 	         // 4.  Populate the table body AFTER building the header.
// 	            let tbody = $('#ArmyAirDefenceReport tbody');
// 	            tbody.empty(); // Clear existing tbody

// 	            if (jsondata.length === 0) {
// 	                tbody.append('<tr><td colspan="' + (7 + uniqueArms.length * 4) + '" align="center" style="color: red;">Data Not Available</td></tr>');
// 	            } else {
// 	                jsondata.forEach(row => {
// 	                    let rowHtml = '<tr>';
// 	                    row.forEach(cell => {
// 	                        rowHtml += '<td>' + cell + '</td>';
// 	                    });
// 	                    rowHtml += '</tr>';
// 	                    tbody.append(rowHtml);
// 	                });
// 	            }
	            

// 	        });
// 		}

		/* if (tableName == "SummarySanctionedStrReport") {

			$.post("estStrReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("estStrReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([ j[i].part,j[i].gen_officers,j[i].lt_gen_officers,j[i].maj_gen_officers,j[i].brig_officers,j[i].col_officers,j[i].col_ltcol,
						j[i].other_officers, j[i].offr,j[i].jco_count,j[i].or_count, j[i].flr,j[i].footnotes,j[i].total_count ]);

				}
			});
		}
		if (tableName == "ArmWiseStrCTIReport") {

			$.post("armWiseStrCTiReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("armWiseStrCTiReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					
					sum_a = j[i].offr+ j[i].jco+ j[i].or;
					sum_b=sum_a+j[i].fn;
					
					jsondata.push([ sr, j[i].parentarm, j[i].offr, j[i].jco, j[i].or, sum_a, j[i].fn, sum_b, j[i].flr ]);
					s_offrs += j[i].offr; s_jco += j[i].jco; 	s_or += j[i].or;
					flr += sum_a; fn += j[i].fn; s_total += sum_b,flr_1 += j[i].flr;
				}
				jsondata.push([ "", "TOTAL", s_offrs, s_jco, s_or, flr, fn, s_total, flr_1 ]);
			});
		}
		if (tableName == "ArmWiseStrCTIIReport") {

			$.post("armWiseStrCTiiReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("armWiseStrCTiiReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					sum_a = j[i].offr+ j[i].jco+ j[i].or;
					sum_b=sum_a+j[i].fn;
					
					jsondata.push([sr, j[i].parentarm, j[i].offr, j[i].jco, j[i].or, sum_a, j[i].fn, j[i].totalstr, j[i].flr]);
					s_offrs += j[i].offr; s_jco += j[i].jco; s_or += j[i].or;
					flr += sum_a; fn += j[i].fn; s_total += sum_b,flr_2 += j[i].flr ;
				}
				jsondata.push([ "", "TOTAL", s_offrs, s_jco, s_or, flr, fn, s_total, flr_2 ]);
			});
		}
		
		
		if (tableName == "DteWiseStrReport") {

			$.post("dteWiseStrReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("dteWiseStrReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([ sr, "", j[i].gen, j[i].lt_gen, j[i].maj_gen, j[i].brig, j[i].col, j[i].lt_col,j[i].maj,j[i].capt,
						j[i].lt,j[i].fd_marshal,j[i].civ_gaz,j[i].jco,j[i].or,j[i].non_civ_gaz,j[i].total,"" ]);
					sum_a += j[i].gen; 
					sum_b += j[i].lt_gen; 
					sum_c += j[i].maj_gen; 
					sum_d += j[i].brig; 
					sum_e += j[i].col; 
					sum_f += j[i].lt_col; 
					sum_g += j[i].maj; 
					sum_h += j[i].capt;
					sum_i += j[i].lt; 
					sum_j += j[i].fd_marshal; 
					s_jco += j[i].jco; 
					s_or += j[i].or;
					sum_k += j[i].civ_gaz; 
					sum_l += j[i].non_civ_gaz; 
					s_total += j[i].total;
				}
				jsondata.push([ "TOTAL", "",sum_a ,sum_b,sum_c,sum_d,sum_e,sum_f,sum_g,sum_h,sum_i,sum_j,s_jco,
				s_or,sum_k,sum_l,s_total,"" ]);
			});
		}
		if (tableName == "DtlsofAllUnitsReport") {

			$.post("dtlsOfAllUnitsReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("dtlsOfAllUnitsReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([sr, j[i].arm_desc,j[i].unit_name, j[i].offr,j[i].jco,j[i].or,"" ]);
				}
			});
		}
		if (tableName == "DesignCapofTrgCenReport") {

			$.post("designCapReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("designCapReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([ j[i].unit_name, j[i].training_capacity ]);
					s_total += j[i].training_capacity;
				}
				jsondata.push([ "TOTAL", s_total ]);
			});
		}
		if (tableName == "AuthStrofManpower") {

			$.post("authStrOfManpowerReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrOfManpowerReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([ j[i].unit_name, j[i].officers,j[i].jco,j[i].or,j[i].civ_gaz,j[i].non_civ_gaz,j[i].total ]);
					s_total += j[i].total;
					sum_a += j[i].officers; 
					s_jco += j[i].jco; 
					s_or += j[i].or;
					sum_k += j[i].civ_gaz; 
					sum_l += j[i].non_civ_gaz; 
				}
				jsondata.push([ "TOTAL",sum_a,s_jco,s_or,sum_k,sum_l, s_total ]);
			});
		}
		
		if (tableName == "AuthStrOfClrkInIAReport") {

			$.post("authStrOfClrkInIAReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrOfClrkInIAReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([sr,j[i].unit_name, j[i].combat_ants, j[i].civ,j[i].total,"" ]);
				}
			});
		}
		
		if (tableName == "AuthStrOfManpwrInIAReport") {

			$.post("authStrOfManpwerInIAReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrOfManpwerInIAReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([j[i].arm_desc, j[i].parentarm,j[i].officer, j[i].jco, j[i].or,j[i].total, j[i].civ_gaz,j[i].civ_ng_ni,j[i].civ_ng_indu,j[i].ncsu,j[i].gtotal ]);
					s_total += j[i].total;
					s_offrs += j[i].officer; 
					s_jco += j[i].jco; 
					s_or += j[i].or;
					sum_k += j[i].civ_gaz; 
					sum_l += j[i].civ_ng_indu; 
					sum_b += j[i].civ_ng_ni;
					s_gtotal += j[i].gtotal;
					sum_a += j[i].ncsu;
				
				}
				jsondata.push(["", "TOTAL",s_offrs,s_jco,s_or,s_total,sum_k,sum_b,sum_l,sum_a, s_gtotal ]);
			});
		}
		if (tableName == "FormationWiseTtlStrInIAReport") {

			$.post("formationWiseStrInIAReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("formationWiseStrInIAReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([j[i].comd_name, j[i].corps_name, j[i].div_name,j[i].bde_name,j[i].unit_name,j[i].officer, j[i].jco, j[i].or,j[i].civ,j[i].total ]);
				
				}
			});
		}
		
		if (tableName == "AuthStrOfOffrByRankReport") {

			$.post("authStrOfOffrByRkReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrOfOffrByRkReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([j[i].parentarm, j[i].gen, j[i].lt_gen,j[i].maj_gen,j[i].brig,j[i].col, j[i].lt_col_col, j[i].lt_col,j[i].maj, j[i].capt,j[i].lt,j[i].total ]);
					sum_a += j[i].gen; 
					sum_b += j[i].lt_gen; 
					sum_c += j[i].maj_gen; 
					sum_d += j[i].brig; 
					sum_e += j[i].col; 
					sum_f += j[i].lt_col; 
					sum_g += j[i].maj; 
					sum_h += j[i].capt;
					sum_i += j[i].lt; 
					sum_j += j[i].lt_col_col; 
					s_total += j[i].total;
				}
				jsondata.push([ "TOTAL",sum_a ,sum_b,sum_c,sum_d,sum_e,sum_j,sum_f,sum_g,sum_h,sum_i,s_total]);
			});
		}
		if (tableName == "AuthStrInIAReport") {

			$.post("authStrInIAReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrInIAReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([j[i].arm_desc, j[i].we_pe_no, j[i].table_title,j[i].eff_frm_date,j[i].eff_to_date,j[i].no_of_unit, j[i].cat_pers, j[i].parentarm,j[i].offr, j[i].jco,j[i].or,j[i].civ,j[i].total ]);
					s_total += j[i].total;
					sum_a += j[i].offr; 
					s_jco += j[i].jco; 
					s_or += j[i].or;
					sum_k += j[i].civ; 
				}
				jsondata.push([ "TOTAL","","","","","","","",sum_a ,s_jco,s_or,sum_k,s_total]);
			});
		}
		
		if (tableName == "MajMinUnitsReport") {

			$.post("majMinUnitsReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("majMinUnitsReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([j[i].parentarm, j[i].unit_name, j[i].unit_category,j[i].rank,j[i].appt]);
				}
			});
		}
		if (tableName == "AuthStrOfTradeReport") {

			$.post("authStrOfTradeReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrOfTradeReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([j[i].appt,j[i].parentarm, j[i].we_pe_no,j[i].total,]);
				}
			});
		}
		
		if (tableName == "AuthStrinIADtlReport") {

			$.post("authStrinIADtlReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrinIADtlReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([j[i].unit_sus,j[i].unit_name, j[i].arm_desc,j[i].comd_name,j[i].corps_name,j[i].div_name, j[i].bde_name,
						j[i].we_pe_no,j[i].unit_category,j[i].type_of_force, j[i].ct_part_i_ii,j[i].officer,j[i].jco,j[i].or,j[i].civ_gaz, 
						j[i].civ_ng_ni,j[i].civ_ng_indu,"",j[i].total,j[i].training_capacity]);
				}
			});
		}
		if (tableName == "AuthStrNonRegReport") {

			$.post("authStrNonRegReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrNonRegReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([j[i].arm,j[i].officer, j[i].jco,j[i].or,j[i].civgzi,j[i].civgzii,j[i].total]);
				}
			});
		}
		if (tableName == "AuthStrOfMnsOffrReport") {

			$.post("authStrOfMnsOffrReportcount?" + key + "=" + value, {
				Search : Search,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("authStrOfMnsOffrReportTable?" + key + "=" + value, {

				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				appt_trade : appt_trade,
				rank : rank,
				rank_cat : rank_cat,
				sus_no : sus_no,
				we_pe_no : we_pe_no,
				user_arm : user_arm,
				category_of_persn : category_of_persn,
				parent_arm1 : parent_arm1
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([j[i].arm,j[i].officer]);
				}
			});
		} */
	}
</script>

