\<%@page language="java" contentType="text/html; charset=UTF-8"
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

<!-- <script src="js/cue/update.js" type="text/javascript"></script> -->
<!-- <link rel="stylesheet" href="js/cue/cueWatermark.css"> -->
<!-- <script src="js/cue/cueWatermark.js" type="text/javascript"></script> -->
<!-- <script src="js/cue/printAllPages.js" type="text/javascript"></script> -->
<!-- <script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script> -->


<%-- <form:form name="Wepe_pers_mdfs" id="Wepe_pers_mdfs" action="Wepe_pers_mdfsAct" method='POST' commandName="Wepe_pers_mdfsCmd"> --%>
		<div class="container" align="center">
        	<div class="card">
        	<div class="card-header"><h5><b>UE PERS REPORT</b></h5></div>
          		<div class="card-body card-block cue_text">
          		
          	<div class="col-md-12">	  
            		          					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
									<label class=" form-control-label"> Command</label>
								</div>
								<div class="col-12 col-md-6">
									<select id="cont_comd" name="cont_comd" class="form-control">
										${selectcomd}
										<c:forEach var="item" items="${getCommandList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class="form-control-label"> Corps</label>
								</div>
								<div class="col-12 col-md-6">
									<select id="cont_corps" name="cont_corps" class="form-control">
										${selectcorps}
										<c:forEach var="item" items="${getCorpsList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						</div>
					
				<div class="col-md-12">	  
            		          					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
									<label class=" form-control-label"> Division</label>
								</div>
								<div class="col-12 col-md-6">
									<select id="cont_div" name="cont_div" class="form-control">
										${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
				<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
									<label class=" form-control-label"> Brigade</label>
								</div>
								<div class="col-12 col-md-6">
									<select id="cont_bde" name="cont_bde" class="form-control">
										${selectBde}
										<c:forEach var="item" items="${getBdeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						</div>
          		<div class="col-md-12">	 
  						<%-- <div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">SUS No </label> 
               					</div>
               					<div class="col-12 col-md-6"> 
                 					<input  id="sus_no" name="sus_no" maxlength="8" style="font-family: 'FontAwesome',Arial;" value="${sus_no1}" placeholder="&#xF002; Search" class="form-control" autocomplete="off">
								</div>
 							</div>
 						</div> --%>
 						
 						<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
						                </div>
							                <div class="col-12 col-md-6">
					                              <div class="form-check-inline form-check">
					                                <label for="inline-radio1" class="form-check-label ">
					                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" onchange="clearDiscription();" class="form-check-input">WE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio2" class="form-check-label ">
					                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" onchange="clearDiscription();" class="form-check-input">PE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio3" class="form-check-label ">
					                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" onchange="clearDiscription();" class="form-check-input">FE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio4" class="form-check-label ">
					                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" onchange="clearDiscription();" class="form-check-input">GSL
					                                </label>&nbsp;&nbsp;&nbsp;
					                              </div>
							                 </div>					              
	                				</div>
	                			</div>
 						
 						
 						<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Unit Name </label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="unit_name" name="unit_name" class="form-control" value="${unit_name1}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  autocomplete="off">
								</div>
 							</div> 
  						</div>
  					</div>
  					
  					  <div class="col-md-12">
	    					
	                		</div>	
	                		
            		<div class="col-md-12">	  
            		          					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">MISO WE/PE No </label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="we_pe_no" name="we_pe_no"  class="form-control" maxlength="100" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
 							</div> 
  						</div>
  						
  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">WE/PE Title</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="table_title" name="table_title" class="form-control" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  autocomplete="off">
								</div>
							</div>	 							
	  						</div>
  						</div>
  						
  						<!-- <div class="col-md-12">		 
	  						<div class="col-md-6">
              					<div class="row form-group"> 
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective From </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="date" id="eff_frm_date" name="eff_frm_date"   class="form-control" onchange="checkDate()" >
									</div>
  								</div>
	  						</div>
	  						<div class="col-md-6">
  								<div class="row form-group">  								
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective To </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="date" id="eff_to_date" name="eff_to_date"  class="form-control" onchange="checkDate()">
									</div>
  								</div>
  							</div>
	  								
	  					</div> -->
  						<div class="col-md-12">	
  						
  						<div class="col-md-6">
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
	  				
 						<div class="col-md-6">
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
	  					<div class="col-md-6">
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
	  						<div class="col-md-6">
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
	  						<div class="col-md-6">
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
	  						<div class="col-md-6">
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
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-6">
												<label class=" form-control-label">Sponsor Directorate </label>
											</div>
											<div class="col-12 col-md-6">
					
												<select class="form-control" id="sponsor_dire"
													name="sponsor_dire"> ${selectLine_dte}
													<c:forEach var="item" items="${getLine_DteList}" varStatus="num">
														<option value="${item.line_dte_sus}"
															name="${item.line_dte_name}">${item.line_dte_name}</option>
													</c:forEach>
					
												</select>
											</div>
										</div>
									</div>
					
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-6">
												<label for="selectLg" class=" form-control-label">Type Of Unit</label>
											</div>
											<div class="col-12 col-md-6">
												<select name="type_force" id="type_force"
													class="form-control-sm form-control">
													<option value="">--Select--</option>
													<c:forEach var="item" items="${getTypeOfUnitList}">
														<option value="${item[1]}">${item[0]}- ${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
						<div class="col-md-12">
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
													<label for="text-input" class=" form-control-label">CT Part I/II</label>
												</div>
												<div class="col-12 col-md-6">
												
													<div class="form-check-inline form-check">
														<label for="radio-ct-part-1" class="form-check-label ">
															<input type="radio" id="radio-ct-part-1" name="ct_part_i_ii" maxlength="8" value="CTPartI" class="form-control form-check-input">CT Part I</label>&nbsp;&nbsp;&nbsp; 
														<label for="radio-ct-part-2" class="form-check-label "> 
															<input type="radio"  id="radio-ct-part-2" name="ct_part_i_ii" maxlength="8" value="CTPartII" class="form-control form-check-input">CT Part II</label> &nbsp;&nbsp;&nbsp; 
														<label for="radio-other" class="form-check-label "> 
															<input type="radio"  id="radio-other" name="ct_part_i_ii" maxlength="8" value="Others" class="form-control form-check-input">Others</label>
													</div>
												</div>
											</div>
										</div>
										</div>
	  					<!-- <div id="divPersTraUnitId" class="col-md-12">	
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label" id="lbltc" >Training Capacity </label>
					                </div>
					                <div class="col-12 col-md-6">
					               		<input class="form-control" id ="training_capacity" name ="training_capacity" maxlength="5" onkeypress="return isNumber0_9Only(event)" autocomplete="off">
					                </div>
					            </div>	  								
	  						</div>			
	  						<div class="col-md-6" >
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label" id="lbluc" >Unit Category  </label>
					                </div>
					                <div class="col-12 col-md-6">
					               <select  class="form-control" id ="unit_category" name ="unit_category" >
					                  <option value="">--Select--</option>
		                                 <option value="Major">Major</option>
		                                 <option value="Minor">Minor</option>
		                                  	                                 
		                            </select>
					                </div>
					            </div>	  								
	  						</div>
	  					</div> -->
	  					
	  					<!-- <div class="col-md-12">		 
	  						<div class="col-md-6">
              					<div class="row form-group"> 
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Data Status From </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input id="eff_frm_date" name="eff_frm_date" maxlength="10"  class="form-control" onchange="checkDate()" placeholder="dd-mm-yyyy">
									</div>
  								</div>
	  						</div>
	  						<div class="col-md-6">
  								<div class="row form-group">  								
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Data Status To </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input id="eff_to_date" name="eff_to_date" maxlength="10" class="form-control" onchange="checkDate()" placeholder="dd-mm-yyyy">
									</div>
  								</div>
  							</div>
	  								
	  					</div> -->
		  					
		  					<input type="hidden" id="sus_no" name="sus_no">
		  					<input type="hidden" id="eff_frm_date" name="eff_frm_date">
		  					<input type="hidden" id="eff_to_date" name="eff_to_date">
		  					<input type="hidden" id="unit_category" name="unit_category">
		  					<input type="hidden" id="training_capacity" name="training_capacity">  	
  				</div>
  		
  					<div class="card-footer" align="center">
						<!-- <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();"> -->   
						<a href="view_reportUrl" class="btn btn-success btn-sm">Clear</a>  
             			<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search" /> 
            	 <c:if test="${roleAccess == 'MISO'}">
            	 <i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
            	 </c:if>
            	  </div> 
            	  
            	
				
            	  </div>
            	  
            	   
		     </div>
		     
		   <div class="card-body">
					<div class="datatablediv" id="reportDiv">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getSearchPersReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead style="font-size: 10px; text-align: center;">
										<tr>
											<th>Ser No.</th>
											<th>Unit</th>
											<th>SUS No</th>
											<th>WE /PE No</th>
											<th>Table Title</th>
											
											
											<th>User Arm</th>
											<th>Trg Cap</th>
											<th>Unit Cat</th>
											<th>Comd</th>
											<th>Corps</th>
											<th>Div</th>
											<th>Bde</th>
											<th>CT</th>
											<th>P/Arm</th>
											<th>Dte</th>
											<th>FF/NFF</th>
											<th>Cat of Pers</th>
											<th>Cat</th>
											<th>Appt/Trade</th>
											<th>Rk</th>
											<th>Base</th>
											<th>Mod</th>
											<th>Footnote</th>
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
<%-- </form:form> --%>
	
<c:url value="Excel_report_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex">
	   <input type="hidden" name="cont_comd1" id="cont_comd1"  value="0">
	   <input type="hidden" name="cont_corps1" id="cont_corps1" value="0">
	   <input type="hidden" name="cont_div1" id="cont_div1" value="0">
	   <input type="hidden" name="cont_bde1" id="cont_bde1" value="0">
	   <input type="hidden" name="sus_no1" id="sus_no1" >
	   <input type="hidden" name="we_pe_no1" id="we_pe_no1">
	   <input type="hidden" name="eff_frm_date1" id="eff_frm_date1">
	   <input type="hidden" name="eff_to_date1" id="eff_to_date1">
	   <input type="hidden" name="user_arm1" id="user_arm1">
	   <input type="hidden" name="category_of_persn1" id="category_of_persn1">
	   <input type="hidden" name="parent_arm11" id="parent_arm11" value="0" />
	   <input type="hidden" name="rank_cat1" id="rank_cat1" value="0" />
	   <input type="hidden" name="appt_trade1" id="appt_trade1" value="0" />
	   <input type="hidden" name="rank1" id="rank1" value="0" />
	   <input type="hidden" name="sponsor_dire1" id="sponsor_dire1" value="0" />
	   <input type="hidden" name="type_force1" id="type_force1" value="0" />
	   <input type="hidden" name="ct_part_i_ii1" id="ct_part_i_ii1" value="0" />
	   <input type="hidden" name="training_capacity1" id="training_capacity1" value="0" />
	   <input type="hidden" name="unit_category1" id="unit_category1" value="0" />
</form:form> 

<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}"; 

function clearDiscription()
{
	document.getElementById("we_pe_no").value="";
	document.getElementById("table_title").value="";	
}
function clearAll() {
    window.location.reload();
}
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	
	  var roleAccess = '${roleAccess}';
	
// 	clearAll();
	var select = '<option value="' + "0" + '">'
			+ "--Select--" + '</option>';
	$('select#cont_comd').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_cmd_sus").val(fcode);

			getCONTCorps(fcode);

			fcode += "00";
			getCONTDiv(fcode);

			fcode += "000";
			getCONTBde(fcode);
		}
	});
	
	$('select#to_cont_comd').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#to_cont_corps").html(select);
			$("select#to_cont_div").html(select);
			$("select#to_cont_bde").html(select);
		} else {
			$("select#to_cont_corps").html(select);
			$("select#to_cont_div").html(select);
			$("select#to_cont_bde").html(select);

			$("#hd_cmd_sus").val(fcode);

			getCONTCorpsFrom(fcode);

			fcode += "00";
			getCONTDiv(fcode);

			fcode += "000";
			getCONTBde(fcode);
		}
	});
	$('select#cont_corps').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_corp_sus").val(fcode);
			getCONTDiv(fcode);
			fcode += "000";
			getCONTBde(fcode);
		}
	});
	
	$('select#cont_div').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {

			$("select#cont_bde").html(select);
			$("#hd_div_sus").val(fcode);
			getCONTBde(fcode);
		}
	});
	


	$('select#cont_bde').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {
			$("#hd_bde_sus").val(fcode);
		}
	});
	
	
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
			            var autoTextVal=wepetext.val();
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
			        	  alert("Please Enter Approved Unit SUS No");
			        	  document.getElementById("unit_name").value="";
			        	  wepetext.val("");	        	  
			        	  wepetext.focus();
			        	  return false;	             
			          }   	         
			      }, 
// 			        select: function( event, ui ) {
// 			        	$(this).val(ui.item.value);    	        	
// // 			        	getNameByCode($(this).val());
// // 			        	getOrbatDetails($(this).val());
// 			        } 	 
			      
			  	select: function( event, ui ) {
					var sus_no = ui.item.value;			      	
					 $.post("getTargetUnitNameList?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));  
		             getOrbatDetailsFromUnitNamecue(sus_no);
		                 
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
						        	getOrbatDetailsFromUnitNamecue(dec(enc,data[0]));
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


		function getOrbatDetailsFromUnitNamecue(susno) {
			debugger;
		
		    $.post("getOrbatDetailsFromUnitNamecue?" + key + "=" + value, { susno: susno }, function(j) {
		        if (j.length > 0) {
		            //var formattedFromDate = formatDate(j[0]);
		            //var formattedToDate = formatDate(j[1]);
		            
		            document.getElementById("eff_frm_date").value = j[0];
		            document.getElementById("eff_to_date").value = j[1];
		            document.getElementById("training_capacity").value = j[4];
		            document.getElementById("unit_category").value = j[5];
		            document.getElementById("we_pe_no").value = j[6];
		            document.getElementById("table_title").value = j[9];
		            
		            $("select#type_force").val(j[3]);
		            $("select#sponsor_dire").val(j[7]);
		            $("select#user_arm").val(j[8]);
		            
		            $("input[name='ct_part_i_ii'][value='" + j[2] + "']").prop('checked', true);
		        } else {
		            alert('Data Not Available');
		        }
		    });
		}


$(function(){
	var wepetext1=$("#we_pe_no");
	wepetext1.autocomplete({
	 	source: function( request, response ) {
		$.ajax({
		type: 'POST',
		url: "getWePeCondiByNoInReport?"+key+"="+value,
		data: {type : "1",we_pe_no:document.getElementById('we_pe_no').value},
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
			            var autoTextVal=wepetext1.val();
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
			        	  alert("Please Enter Approved WE/PE No");
			        	  wepetext1.val("");	        	  
			        	  wepetext1.focus();
			        	  
				   	     	document.getElementById("training_capacity").value = "";
				   	     	document.getElementById("unit_category").value = "";
				   			
			        	  
			        	  document.getElementById("table_title").value="";
			    		  document.getElementById("eff_frm_date").value="";
			    		  document.getElementById("eff_to_date").value="";
			    		  $("select#sponsor_dire").val("0");
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
			 
//Optimize date conversion function
function formatDate(dateString) {
    if (!dateString) return '';
    
    var [day, month, year] = dateString.split('-');
    return new Date(year, month - 1, day)
        .toISOString()
        .split('T')[0];
}

function getDetailsBySupeerseedNo(val)
{
	  if(val != "" && val != null)
	  {
		  
		       	 $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
		       		if(j!="" && j!= null){	 
		       	       var formattedFromDate = formatDate(j[0].eff_frm_date);
		       	       var formattedToDate = formatDate(j[0].eff_to_date);
		   			document.getElementById("table_title").value=j[0].table_title;
		   			document.getElementById("eff_frm_date").value = formattedFromDate;
		   	        document.getElementById("eff_to_date").value = formattedToDate;
		   	     	document.getElementById("training_capacity").value = j[0].training_capacity;
		   	     	document.getElementById("unit_category").value = j[0].unit_category;
		   			$("select#sponsor_dire").val(j[0].sponsor_dire);
		   			$("select#user_arm").val(j[0].arm); 
		   		}
		   		else
		   		{
		   			 document.getElementById("table_title").value="";
		   			  document.getElementById("eff_frm_date").value="";
		   			  document.getElementById("eff_to_date").value="";
		   			document.getElementById("training_capacity").value = "";
		   			  $("select#sponsor_dire").val("0");
		   			 $("select#unit_category").val("");
		   		}
		       	}).fail(function(xhr, textStatus, errorThrown) { });
	  }
	  else
	  {
		  alert("Please enter Approved Superseded Document No");
		  document.getElementById("table_title").value="";
		  document.getElementById("eff_frm_date").value="";
		  document.getElementById("eff_to_date").value="";
		  document.getElementById("training_capacity").value = "";
		  $("select#sponsor_dire").val("0");
		  $("select#unit_category").val("");
	  }
}


			 
$(function() 
		{
			  var wepetext1=$("#table_title");
			  wepetext1.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getTableTitleCondiReport?"+key+"="+value,
			        data: {type : "1",tableTitle:document.getElementById('table_title').value},
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
			        	  alert("Please Enter Approved WE/Pe No");
			        	  wepetext1.val("");	        	  
			        	  wepetext1.focus();
			        	  
			        	  document.getElementById("we_pe_no").value="";
			    		  document.getElementById("eff_frm_date").value="";
			    		  document.getElementById("eff_to_date").value="";
			    		  $("select#sponsor_dire").val("0");
			    		  $("select#user_arm").val("0");
				        	  
			        	  return false;	             
			          }   	         
			      }, 
			         select: function( event, ui ) {
			        	$(this).val(ui.item.value);    	
			        	getDetailsByTableTitle($(this).val(),"1");	        	
			        } 	     
			    });	  
			
		});
		
		
function getDetailsByTableTitle(val,ap)
{
	  if(val != "" && val != null)
	  {
		       	 $.post("getDetailsByWePeCondiTitle?"+key+"="+value, {val : val,ap:ap}).done(function(j) {
		       		if(j!="" && j!= null){	 
		       		 var formattedFromDate = formatDate(j[0].eff_frm_date);
		       	       var formattedToDate = formatDate(j[0].eff_to_date);
		   			
		       		
		   			 document.getElementById("we_pe_no").value=j[0].we_pe_no;
		   			document.getElementById("eff_frm_date").value = formattedFromDate;
		   			document.getElementById("eff_to_date").value=formattedToDate;
		   			document.getElementById("training_capacity").value = j[0].training_capacity;
		   	     	document.getElementById("unit_category").value = j[0].unit_category;
		   			$("select#sponsor_dire").val(j[0].sponsor_dire);
		   			$("select#user_arm").val(j[0].arm); 
		   		}
		   		else
		   		{
		   			 document.getElementById("we_pe_no").value="";
		   			  document.getElementById("eff_frm_date").value="";
		   			  document.getElementById("eff_to_date").value="";
		   			  $("select#sponsor_dire").val("0");
		   		}
		       	}).fail(function(xhr, textStatus, errorThrown) { });
	  }
	  else
	  {
		  alert("Please enter Approved Superseded Document No");
		  document.getElementById("we_pe_no").value="";
		  document.getElementById("eff_frm_date").value="";
		  document.getElementById("eff_to_date").value="";
		  $("select#sponsor_dire").val("0");
	  }
}
			
$('select#category_of_persn').change(function() {
	var code = $(this).find('option:selected').attr("name");  
	if(code == "Regimental")	        		
	{
		$("#parent_arm").val( $("#user_arm_hi").val());
		document.getElementById("parent_arm").disabled = true; 		
		$("select#parent_arm1").hide();		
		$("input#parent_arm").show();
		$("#arm_code").val($("#user_arm").val());
	}
	else if(code == "ERE")
	{
		$("#parent_arm").val("");
		document.getElementById("parent_arm").disabled = false; 
		$("input#parent_arm").hide();
		$("select#parent_arm1").show();
		parentArm();        		
	}
	else
	{
		$("#parent_arm").val("");
		document.getElementById("parent_arm").disabled = true; 
		$("select#parent_arm1").val("0");
		$("select#parent_arm1").hide();		
		$("input#parent_arm").show();
		$("#arm_code").val($("#user_arm").val());
	}
});

function parentArm()
{
	var u_a = document.getElementById("user_arm").value;
	 $.post("getArmNameListCue?"+key+"="+value,{ u : u_a}).done(function(j) {
			var length = j.length-1;
			var enc = j[length].columnName1.substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length-1; i++) {
				options += '<option value="'+dec(enc,j[i].columnCode)+'" name="' + dec(enc,j[i].columnName)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';
			}	
			$("select#parent_arm1").html(options);
		 }).fail(function(xhr, textStatus, errorThrown) { }); 
	
}

mockjax1('getSearchPersReport');
table1 = dataTable('getSearchPersReport');

$('#btn-reload').on('click', function(){
    table1.ajax.reload();
});


function getCONTCorps(fcode) {
	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';

			for (var i = 0; i < length; i++) {
				if ('${corp_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_corps").html(options);
		}
	});
}

function getCONTDiv(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	$.post("getDivDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			for (var i = 0; i < length; i++) {
				if ('${div_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_div").html(options);
		}
	});
}


function getCONTBde(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4]
			+ fcode[5];
	$.post("getBdeDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			jQuery("select#cont_bde").html(options);
			for (var i = 0; i < length; i++) {
				if ('${bde_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected>' + dec(enc, j[i][1])
							+ '</option>';
					$("#cont_bname").val(dec(enc, j[i][1]));
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1]) + '">'
							+ dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_bde").html(options);
		}
	});
}


function select_rank_cat(){
	var rnk = $("select#rank_cat").val();
	 $('select#rank').val("");
	
	 
	 $.post("getTypeofRankList?"+key+"="+value,{rnk : rnk}).done(function(j) {
		 var length = j.length-1;
			var enc = j[length].columnName1.substring(0,16);
			var options = '<option value="">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length-1; i++) {
				options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName) + '</option>';					
			}	
			$("select#rank").html(options); 		
		 }).fail(function(xhr, textStatus, errorThrown) { });   

		
}

function select_rank_app_trade(){
	var rnk = $("select#rank_cat").val();
	
	$('#appt_trade').val("");
	$('#app_trd_code').val("");
	
	 var wepetext1=$("#appt_trade");

	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	  	    url: "getTypeappt_tradeList?"+key+"="+value,
	        data: {rnk : rnk,appt_trade : document.getElementById('appt_trade').value },
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
					//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
	        	  alert("Please Enter Approved Common Appt/Trade");
	        	  wepetext1.val("");	
// 	        	  document.getElementById("app_trd_code").value="";
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 	     
	    });
	
	}
	  
function bbb111(){
	
	var s = document.getElementById("rank_cat").value;
	
	if(s == "" ){
		alert("Please Enter Category of Personnel");
		$("select#rank_cat").focus();
		document.getElementById("appt_trade").disabled = true;
		document.getElementById("rank").disabled = true;
		
		return false;
	}
	else{
		
		document.getElementById("appt_trade").disabled = false;
		
	}
	 return true;
}

function ccc111(){
	
	var s = document.getElementById("appt_trade").value;
	
	if(s == "" ){
		alert("Please Enter Rank");
		$("input#appt_trade").focus();
		document.getElementById("rank").disabled = true;
		
		return false;
	}
	else{
		$("#rank").val("");
		document.getElementById("rank").disabled = false;
		
	}
	 return true;
	
}
	function getExcel() {	
		var appt_trade = $("#appt_trade").val();
		var rank = $("#rank").val();  
		//var rank_cat = $("#rank_cat").val();
		var rank_cat = $("select#rank_cat option:selected").text();
		var cont_comd = $("#cont_comd").val();
		var cont_corps = $("#cont_corps").val();
		var cont_div = $("#cont_div").val();
		var cont_bde = $("#cont_bde").val();
		
		var sus_no = $("#sus_no").val();
		var we_pe_no = $("#we_pe_no").val();
		var eff_frm_date = $("#eff_frm_date").val();
		var eff_to_date = $("#eff_to_date").val();
		var user_arm = $("#user_arm").val();
		var category_of_persn = $("#category_of_persn").val();
		var sponsor_dire = $("#sponsor_dire").val();
		var training_capacity = $("#training_capacity").val();
		var unit_category = $("#unit_category").val();
		var type_force = $("#type_force").val();
		var parent_arm1 = $("#parent_arm1").val();
		var radio_ct_part = $('input[name="ct_part_i_ii"]:checked').val();  
		 
	 	$("#cont_comd1").val(cont_comd);
		$("#cont_corps1").val(cont_corps);
		$("#cont_div1").val(cont_div);
		$("#cont_bde1").val(cont_bde);
		
		$("#sus_no1").val(sus_no);
		$("#we_pe_no1").val(we_pe_no);
		$("#eff_frm_date1").val(eff_frm_date);
		$("#eff_to_date1").val(eff_to_date);
		$("#user_arm1").val(user_arm);
		$("#category_of_persn1").val(category_of_persn);
		$("#parent_arm11").val(parent_arm1);
		$("#rank_cat1").val(rank_cat);
		$("#appt_trade1").val(appt_trade);
		$("#rank1").val(rank);
		$("#sponsor_dire1").val(sponsor_dire);
		$("#type_force1").val(type_force);
		$("#ct_part_i_ii1").val(radio_ct_part);
		$("#training_capacity1").val(training_capacity);
		$("#unit_category1").val(unit_category);
		
		document.getElementById('ExcelForm').submit();
	} 

function data(tableName) {debugger;
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
	
	var appt_trade = $("#appt_trade").val();
	var rank = $("#rank").val();  
	//var rank_cat = $("#rank_cat").val(); 
	var rank_cat = $("select#rank_cat option:selected").text();
	var cont_comd = $("#cont_comd").val();
	var cont_corps = $("#cont_corps").val();
	var cont_div = $("#cont_div").val();
	var cont_bde = $("#cont_bde").val();
	
	var sus_no = $("#sus_no").val();
	var we_pe_no = $("#we_pe_no").val();
	var eff_frm_date = $("#eff_frm_date").val();
	var eff_to_date = $("#eff_to_date").val();
	var user_arm = $("#user_arm").val();
	var category_of_persn = $("#category_of_persn").val();
	var sponsor_dire = $("#sponsor_dire").val();
	var training_capacity = $("#training_capacity").val();
	var unit_category = $("#unit_category").val();
// 	var radio1 = $('input[id="radio1"]:checked').val();
	var radio1;
		if ($('input[name="ct_part_i_ii"]:checked').length > 0) {
		    radio1 = $('input[name="ct_part_i_ii"]:checked').val();
		} else {
		    radio1 = "";
		}
	var type_force = $("#type_force").val();
	var parent_arm1 = $("#parent_arm1").val();
	var weperadio;
	if ($('input[name="we_pe"]:checked').length > 0) {
		weperadio = $('input[name="we_pe"]:checked').val();
	} else {
		weperadio = "";
	}
	var s_base_auth = 0;
	var s_mod = 0;
	var s_footnotes = 0;
	var s_total = 0;
	if (tableName == "getSearchPersReport") {

	    $.post("getPersReportcount?" + key + "=" + value,{
	    	Search:Search,
	    	appt_trade : appt_trade,
	    	rank : rank,
	    	rank_cat:rank_cat,
	    	cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			
			sus_no :sus_no,
			we_pe_no : we_pe_no, 
			eff_frm_date : eff_frm_date,
			eff_to_date : eff_to_date,
			user_arm :user_arm,
			category_of_persn : category_of_persn,
			sponsor_dire : sponsor_dire,
			training_capacity : training_capacity,
			unit_category : unit_category,
			radio1 : radio1,
			type_force : type_force,
			parent_arm1 :parent_arm1,
			weperadio:weperadio
			
		 },  function(j) {
			FilteredRecords = j;
		});
		$.post("getPersReportcountTable?" + key + "=" + value, {
		
			startPage : startPage,
			pageLength : pageLength,
			Search:Search,
			orderColunm : orderColunm,
			appt_trade :  appt_trade,
			rank : rank,
			rank_cat:rank_cat,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			
			sus_no :sus_no,
			we_pe_no : we_pe_no, 
			eff_frm_date : eff_frm_date,
			eff_to_date : eff_to_date,
			user_arm :user_arm,
			category_of_persn : category_of_persn,
			sponsor_dire : sponsor_dire,
			training_capacity : training_capacity,
			unit_category : unit_category,
			radio1 : radio1,
			type_force : type_force,
			parent_arm1 :parent_arm1,
			weperadio:weperadio
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
					
				jsondata.push([sr, j[i].unit_name, j[i].unit_sus,j[i].we_pe_no, j[i].table_title,j[i].arm_desc, j[i].training_capacity,j[i].unit_category,j[i].comd_name,j[i].corps_name,j[i].div_name,j[i].bde_name,j[i].ct_part_i_ii,j[i].parentarm, j[i].linedte_name,
					j[i].type_of_force,j[i].cat_pers, j[i].rank_cat,j[i].appt, j[i].rank, 
					j[i].base_auth,j[i].mod_auth, j[i].foot_auth, j[i].total]);
				 s_base_auth += j[i].base_auth;
				 s_mod += j[i].mod_auth;
				 s_footnotes += j[i].foot_auth;
				 s_total += j[i].total;
			}
			jsondata.push([ "", "", "","","","", "", "","","","","","", "", "","","","","","Total", s_base_auth,s_mod,s_footnotes,s_total]);
		});
	}
}

</script>

