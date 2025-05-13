<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>




<!-- <link rel="stylesheet" href="js/common/select2/select2.min.css">
<script src="js/common/select2/select2.min.js" type="text/javascript"></script> -->

<div class="animated fadeIn" id="printableArea">
	<div class="">
    	<div class="col-md-12" align="center">
    		<div class="card">
    			<div class="card-header" align="center"><h5>C Vehicle Details:Line Dte\Fmn</h5></div>
    				<div class="card-body">
    					<div class="col-md-12">
    						<div class="col-md-12">
								<div class="row form-group">
							    	<div class="col col-md-3">
							        	<label class=" form-control-label"><strong style="color: red;">*</strong> VEH CAT</label>
							       	</div>
							        <div class="col-12 col-md-5">
										<select id="type_veh"  class="form-control-sm form-control" onchange="getPRFList(this.value)" style="width: 100%">
											<option value="">--Select--</option>
											<!-- <option value="0">A Vehicles</option>
											<option value="1">B Vehicles</option> -->
											<option value="2">C Vehicles</option> 
										</select>
									</div>
								</div>		
							</div>
							<div class="col-md-12">
								<div class="row form-group">
							    	<div class="col col-md-3">
							        	<label class=" form-control-label"><strong style="color: red;">*</strong> VEH TYPE<br>Multiple Selection</label>
							       	</div>
							        <div class="col-12 col-md-9">
										<select id="prf_code"  class="form-control-sm form-control selectpicker" onchange="getMCTMainNameList(this.value)" style="width: 100%">
										</select>
									</div>
								</div>		
							</div>
						</div>
						<!-- <div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"> SUB CAT</label>
							       	</div>
							        <div class="col-12 col-md-8">
										<select id="mct_main"  class="form-control-sm form-control" style="width: 100%">
										</select>
									</div>
								</div>		
							</div>
						</div> -->
						<div class="col-md-12 " style='background-color:mistyrose;padding:7px;'>
							<div class="col-md-5" align="left">
								<b><input type=checkbox id='nSelAll' name='tregn' onclick="callsetall();">&nbsp;
									Select all (<span id="sregn" style='font-size:14px;'>0</span>)</b>&nbsp;&nbsp;
								<input id="InputSearch" type="text" placeholder="Search SUB CAT .." size="20">
							</div>   
							<div class="col-md-5" align="right">
								<b>Selected SUB CAT - <span id="tregn" style='font-size:14px;'>0</span></b>
							</div>
						</div> 
						<div  class="col-md-12">
							<div class="col-md-6" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="srctable"></div>
							<div class="col-md-6" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="tartable"></div>
							<input type="hidden" id="c_val" name="c_val" value="0">
							 <input type="hidden" id="sus_no_list" name="sus_no_list" placeholder="" class="form-control-sm nrform-control">
		                      <input type="hidden" id="unit_name_list" name="unit_name_list" placeholder="" class="form-control-sm nrform-control">
						</div>
						<div class="col-md-12">
						<br>
							<div class="col-md-6">
								<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label">Line Dte</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                 					<select id="line_dte_sus" name="line_dte_sus" class="form-control-sm form-control" >
	                 						${selectLine_dte}
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
	                 					</select>
	                				</div>
				            	</div>
							</div>
					
										<div class="col-md-6" id="type_of_force_div">
											<div class="row form-group">
              									<div class="col col-md-4" >
              										<label for="selectLg" class=" form-control-label">Type Of Force</label>
              									</div>
           										<div class="col-12 col-md-8">
                									<select name="type_of_force" id="type_of_force"  class="form-control-sm form-control">
								                    
								                    	<option value="">--Select--</option>
													 <!-- <option value="FF">FF</option>
													<option value="NFF">NFF</option>
													<option value="FFC">FFC</option> 
													<option value="TRG EST">TRG EST</option> --> 
													
					                               		 <c:forEach var="item" items="${getTypeOfUnitList}">
			                  								<option value="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>  
								                  	</select>
        										</div>
        									</div>
										</div>
									
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"> Km</label>
							       	</div>
							        <div class="col-md-8">
									<input type="text" id="kms" name="kms" onkeypress="return isNumber0_9Only(event);" 
									class="form-control autocomplete" autocomplete="off" maxlength="6" value="0">
								</div>
							  	</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
		                			<div class="col col-md-4">
		                  				<label class="form-control-label"> Vintage</label>
		               				</div>
		                			<div class="col-md-8">
									<input type="text" id="vintag" name="vintag" onkeypress="return isNumber0_9Only(event);"
										class="form-control autocomplete" autocomplete="off" maxlength="2" value="0">
								</div>
				              	</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"> Command</label>
							       	</div>
							        <div class="col-12 col-md-8">
							           	<select id="cont_comd" name="cont_comd" class="form-control-sm form-control" >
											${selectcomd}
								            <c:forEach var="item" items="${getCommandList}" varStatus="num" >
								            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
                  						</select>
							    	</div>
							  	</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
		                			<div class="col col-md-4">
		                  				<label class="form-control-label"> Corps</label>
		               				</div>
		                			<div class="col-12 col-md-8">
		                 				<select id="cont_corps" name="cont_corps" class="form-control-sm form-control" >
		                 					${selectcorps}
		                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
		                 				</select>
		                			</div>
				              	</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
					                <div class="col col-md-4">
					                  <label class=" form-control-label"> Division</label>
					                </div>
					                <div class="col-12 col-md-8">
					                 	<select id="cont_div" name="cont_div" class="form-control-sm form-control" >
					                 		${selectDiv}
					                 		<c:forEach var="item" items="${getDivList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
					                 	</select>
					                </div>
					            </div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label"> Brigade</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                 					<select id="cont_bde" name="cont_bde" class="form-control-sm form-control" >
	                 						${selectBde}
	                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
	                 					</select>
	                				</div>
				            	</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
	          					<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-12 col-md-8">			
										<textarea id="unit_name" name="unit_name" class="form-control autocomplete" style="font-size: 12px;" autocomplete="off" maxlength="100" placeholder="select Unit Name">${unit_name}</textarea>
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6" style="display: none;">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">SUS No</label>
						            </div>
						            <div class="col-12 col-md-8">
										<input type="text" id="sus_no" value='${sus_no}' name="sus_no"  class="form-control autocomplete" maxlength="8" autocomplete="off" placeholder="Select SUS No">
									</div>
              					</div>
	          				</div>
	          			</div>
					</div>
	    			<div class="form-control card-footer" align="center" id="buttonDiv">
	    				<a href="abc_vehicle_status_line_dte" class="btn btn-success btn-sm" style="border-radius: 5px;">Clear</a>  
						<button class="btn btn-primary btn-sm" onclick="return Search();" style="border-radius: 5px;">Search</button>
						<i class="fa fa-download"></i><input type="button" id="exportId" class="btn btn-sm btn_report" style="background-color: #e37c22;color: white;" value="Export" onclick="fnExcelReport();">
	             	</div>
	             	<div class="card-body">
	             		<div class="" id="reportDiv">
	             			<c:if test="${list_B.size()>0}">
							<div class="col-md-12">
								<div align="left"><h6 style="color:red">DATA AS UPDATED BY USER UNIT</h6></div>
								<div align="right"><h6>Total Count : ${list_B.size()}</h6></div>
					   			<div  class="watermarked" data-watermark="" id="divwatermark">
									<span id="ip"></span>
									<table id="getVehicleStatusTbl" class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
										<thead>
							        		<tr>
					                            <th rowspan="2"   style="width:5%;">S No</th>
										        <th rowspan="2"   style="width:5%;">Comd</th>
										        <th rowspan="2"   style="width:5%;">Corps</th>
										        <th rowspan="2"   style="width:5%;">Division</th>
										        <th rowspan="2"   style="width:5%;">Brigade</th>
										        <th rowspan="2"   style="width:5%;">Unit Name</th>										     								      
										        <th rowspan="2"   style="width:5%;">Nomen</th>
										    	<th rowspan="2"   style="width:5%;">BA No</th>										     
										       <th rowspan="2"   style="width:5%;">Kms</th>
										        <th rowspan="2"   style="width:5%;">Vintage</th>
										        <th rowspan="2"   style="width:5%;">Classification</th>
										         <th colspan="2" style="text-align: center; width:10%;">Discard Criteria</th>
										         <th colspan="6" style="text-align: center; width:30%;">Discard Condition YearWise</th>							       
											</tr>
											<tr>
														  <td style="width:5%;">Kms</td>
                                                         <td style="width:5%;">Vintage</td>
											  			 <td style="width:5%;">QFD</td>
                                                         <td style="width:5%;">AD-One</td>
                                                         <td style="width:5%;">AD-Two</td>
                                                         <td style="width:5%;">AD-Three</td>
                                                         <td style="width:5%;">AD-Four</td>
                                                         <td style="width:5%;">AD-Five</td>
                                                        
											
											</tr>
											
										</thead>
										<tbody>
											<c:if test="${list_B.size() == 0}" >
												<tr>
													<td colspan="15" align="center" style="color: red;"> Data Not Available </td>
												</tr>
											</c:if>
											<c:forEach var="item" items="${list_B}" varStatus="num" >
												<tr>
												     <td style="width:5%;" align="center"><b>${num.index+1}</b></td>
												     <td><b>${item[0]}</b></td>
												     <td><b>${item[1]}</b></td>
												     <td><b>${item[2]}</b></td>
												     <td><b>${item[3]}</b></td>
												     <td><b>${item[4]}</b></td>												     
												     <td><b>${item[11]}</b></td>
												     <td><b>${item[7]}</b></td>
												     <td><b>${item[8]}</b></td>
												     <td><b>${item[9]}</b></td>
												      <td><b>${item[10]}</b></td>
												      <td><b>${item[18]}</b></td>
												      <td><b>${item[19]}</b></td>
												      <td><b>${item[12]}</b></td>
												      <td><b>${item[13]}</b></td>
												      <td><b>${item[14]}</b></td>
												      <td><b>${item[15]}</b></td>
												      <td><b>${item[16]}</b></td>
												      <td><b>${item[17]}</b></td>
												      
												     
												     <!--  -->
												     </tr>
											</c:forEach>
									</tbody>
									</table>
									
								</div>
							</div>
							</c:if>
							<c:if test="${list_A.size()>0}">
							<div class="col-md-12">
								<div align="right"><h6>Total Count : ${list_A.size()}</h6></div>
					   			<div  class="watermarked" data-watermark="" id="divwatermark">
									<span id="ip"></span>
									<table id="getVehicleStatusTbl" class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
										<thead>
							        		<tr>
					                            <th  style="text-align: center;width:3%;">S No</th>
										        <th>Comd</th>
										        <th>Corps</th>
										        <th>Division</th>
										        <th>Brigade</th>
										        <th>Unit Name</th>										 							      
										        <th>Nomenclature</th>
										    	<th>BA No</th>										     
										       <th>Kms run</th>
										        <th>Vintage</th>
										        <th>Classification</th>
										        
										        </tr>
											 
										</thead>
										<tbody>
											<c:if test="${list_A.size() == 0}" >
												<tr>
													<td colspan="15" align="center" style="color: red;"> Data Not Available </td>
												</tr>
											</c:if>
											<c:forEach var="item" items="${list_A}" varStatus="num" >
												<tr>
												     <td style="width:3%;" align="center"><b>${num.index+1}</b></td>
												     <td><b>${item[0]}</b></td>
												     <td><b>${item[1]}</b></td>
												     <td><b>${item[2]}</b></td>
												     <td><b>${item[3]}</b></td>
												     <td><b>${item[4]}</b></td>
												     <td><b>${item[6]}</b></td>
												     <td><b>${item[7]}</b></td>
												     <td><b>${item[8]}</b></td>
												     <td><b>${item[9]}</b></td>
												      <td><b>${item[10]}</b></td>										     												     												     
												      <!--  -->
												    </tr>
											</c:forEach>
									</tbody>
									</table>
								
								</div>
							</div>
							</c:if>
							<c:if test="${list_C.size()>0}">
							<div class="col-md-12">
								<div align="right"><h6>Total Count : ${list_C.size()}</h6></div>
					   			<div  class="watermarked" data-watermark="" id="divwatermark">
									<span id="ip"></span>
									<table id="getVehicleStatusTbl" class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
										<thead>
							        		<tr>
					                             <th style="text-align: center;width:3%;">S No</th>
										        <th>Comd</th>
										        <th>Corps</th>
										        <th>Division</th>
										        <th>Brigade</th>
										        <th>Unit Name</th>
										        									      
										        <th>Nomenclature</th>
										    	<th>BA No</th>										     
										       <th>HRS</th>
										        <th>Vintage</th>
										        <th>Classification</th>
										       
										       </tr>
											
										</thead>
										<tbody>
											<c:if test="${list_C.size() == 0}" >
												<tr>
													<td colspan="15" align="center" style="color: red;"> Data Not Available </td>
												</tr>
											</c:if>
											<c:forEach var="item" items="${list_C}" varStatus="num" >
												<tr>
												     <td style="width:3%;" align="center"><b>${num.index+1}</b></td>
												     <td><b>${item[0]}</b></td>
												     <td><b>${item[1]}</b></td>
												     <td><b>${item[2]}</b></td>
												     <td><b>${item[3]}</b></td>
<%-- 												     <td><b>${item[4]}</b></td> --%>
												     <td><b>${item[4]}</b></td>
												     <td><b>${item[6]}</b></td>
												     <td><b>${item[7]}</b></td>
												     <td><b>${item[8]}</b></td>
												     <td><b>${item[9]}</b></td>
												      <td><b>${item[10]}</b></td>													     												     												     
												      <!--  -->
												     </tr>
											</c:forEach>
									</tbody>
									</table>
									
								</div>
							</div>
							</c:if>
						</div>
	             	</div> 
				</div>
			</div>
		</div>
	
</div>
<div class="animated fadeIn" id="printDiv" style="display: none;">
	<div class="" >
		<div class="container" align="center">
		<div class="col-md-12"  align="center">
			<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')" >
		</div>
		</div>
	</div>
</div>

<c:url value="c_veh_status_details_line_dte" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="type_veh1" id="type_veh1" value="0"/>
	<!-- <input type="hidden" name="mct_main1" id="mct_main1" value="0"/> -->
	<input type="hidden" name="prf_code1" id="prf_code1" value="0"/>
	<input type="hidden" name="sus_no1" id="sus_no1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="kms1" id="kms1" />
	<input type="hidden" name="vintag1" id="vintag1" />
	
	<input type="hidden" name="cont_comd1" id="cont_comd1"/>
	<input type="hidden" name="cont_corps1" id="cont_corps1" value="0"/>
	<input type="hidden" name="cont_div1" id="cont_div1" value="0"/>
	<input type="hidden" name="cont_bde1" id="cont_bde1" value="0"/>
	<input type="hidden" name="line_dte_sus1" id="line_dte_sus1" value="0"/>
	<input type="hidden" name="mct_main_list1" id="mct_main_list1" value="0"/>
	<input type="hidden" name="type_of_force1" id="type_of_force1" />
</form:form>
<script>
$(document).ready(function() {
	
	var cmdfcode='';
    var corpscode='';
    
  /*   if ('${type_veh}' == '1' ) {
        $('#type_of_force_div').show();
    }
    else{
    	 $('#type_of_force_div').hide();
    } */
   /*  $('#type_veh').change(function() {
        var selectedValue = $(this).val();
        if (selectedValue === '' || selectedValue === '0' || selectedValue === '2') {
            $('#type_of_force_div').hide();            
        } else {
            $('#type_of_force_div').show(); 
        }
    }); */
	
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	//$("#prf_code").select2({multiple:true});
	
	if('${type_veh}' == '' && '${prf_code1}' == ''){
		$("#reportDiv").hide();	
	}else{
		$("#reportDiv").show();
	}
	
	$("#type_veh").val('${type_veh}');
	getPRFList('${type_veh}');	
	$("#sus_no").val('${sus_no1}');
	$("#unit_name").val('${unit_name1}');
	
	if('${cont_comd1}' != ""){
    	$("#cont_comd").val('${cont_comd1}');
    	getCONTCorps('${cont_comd1}');
	}
	if('${cont_corps1}' != ""){
		getCONTDiv('${cont_corps1}');
	}
    if('${cont_div1}' != ""){
    	getCONTBde('${cont_div1}');
    }
	
    if('${line_dte_sus1}' != ""){
		$("#line_dte_sus").val('${line_dte_sus1}');
	}
    
    if ('${kms1}' == '') {
		$("#kms").val('0');
	} else {
		$("#kms").val('${kms1}');
	}
	if ('${vintag1}' == '') {
		$("#vintag").val('0');
	} else {
		$("#vintag").val('${vintag1}');
	}
    
	var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
   	$('select#cont_comd').change(function() {
	   	var fcode = this.value;
	   	if(fcode == "0"){
	   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
   		}else{
   			$("select#cont_corps").html(select);
   			$("select#cont_div").html(select);
   			$("select#cont_bde").html(select);

   			cmdfcode=fcode;
   			corpscode=fcode;
   			getCONTCorps(fcode);
    	
       		fcode += "00";	
   			getCONTDiv(fcode);
       	
       		fcode += "000";	
   			getCONTBde(fcode);
   		}
	});

    $('select#cont_corps').change(function() {                
                          var fcode = this.value;
                corpscode=fcode
                  if(fcode == "0"){
                          $("select#cont_div").html(select);
                          $("select#cont_bde").html(select);
                          
                cmdfcode += "00";        
                    getCONTDiv(cmdfcode);           
                    cmdfcode += "000";        
                    getCONTBde(cmdfcode);
               }else{
                       $("select#cont_div").html(select);
                          $("select#cont_bde").html(select);
                                  getCONTDiv(fcode);
                           fcode += "000";        
                               getCONTBde(fcode);
               }
    });
    $('select#cont_div').change(function() {
          
                       var fcode = this.value;                       
                       if(fcode == "0"){
                     $("select#cont_bde").html(select)
                     corpscode += "000";        
                       getCONTBde(fcode);
               }else{
                       $("select#cont_bde").html(select)
                          fcode += "000";
                               getCONTBde(fcode);
               }
    });                
	$("#InputSearch").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#srctable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

	
	
	
});
function getPRFList(val)
{
	//getMCTMainList('');
	var options = '<option value="XNR">'+ "--- Select All Veh Type ---" + '</option>';
	if(val !="")
	{
	    $.post("getTptSummaryInPRFList?"+key+"="+value,{type : val}).done(function(j) {
	    	var prfcd='${prf_code}';
    		prfcd = prfcd +",";
    		var prfcd1=[];
	    	//prfcd=prfcd.split(",");
			for ( var i = 0; i < j.length; i++) {				
				if(prfcd.indexOf(j[i].prf_code+",")>=0){
					options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" selected=selected>'+ j[i].group_name+ '</option>';
					//getMCTMainList('${prf_code}')
					prfcd1.push(j[i].prf_code);							
				}else{
					options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name+ '</option>';
				}
			}	
			
			console.log(prfcd1);
			$("select#prf_code").html(options);
			getMCTMainNameList(prfcd1);
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	else {
		$("select#prf_code").html(options);
	}
	
}
/* function getMCTMainList(val)
{
	var options = '<option value="0">'+ "--Select--" + '</option>';
	if(val !="")
	{
	    $.post("getMCtMain_from_prf_code?"+key+"="+value,{prf_code : val}).done(function(j) {
			for ( var i = 0; i < j.length; i++) {
				if(j[i].mct_main_id == '${mct_main}'){
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" selected=selected>'+ j[i].mct_nomen+ '</option>';
				}else{
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" >'+ j[i].mct_nomen+ '</option>';
				}
			}	
			$("select#mct_main").html(options); 
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	else {
		$("select#mct_main").html(options);
	}
} */

//////////////////////////////////////////////////////
function getMCTMainNameList(val){
	val=$("#prf_code").val();	
	console.log("PRF-",$("#prf_code").val());
	//alert("val-"+val);
	if (val !=',' && typeof val !== "string") {
		val=val.join(":");
	}
	console.log("cr list="+val);
	//alert(val);
	$.post("getMCtMain_from_prf_code?"+key+"="+value,{prf_code:val}).done(function(data) {	
	    if(data == ""){
			$("#srctable").empty();
			$("#tartable").empty();
			$("#sregn").text(data.length);
			$("#tregn").text(data.length);
			//alert("No Regd Data Found");
		}else{
			$("#d_reg").show();
			$("#d_reg2").show();
			$("#d_reg3").show();
			$("#srctable").val(data);
			drawregn(data);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
	});
}
function drawregn(j) {
	var ii=0;
	$("#srctable").empty();
	$("#tartable").empty();
	for(var i = 0; i < j.length; i++){
		var row="<tr id='SRC"+j[i].mct_main_id+"' padding='5px;'>";
		row=row+"<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"+j[i].mct_main_id+"' name='"+j[i].mct_nomen+"' onclick='findselected();'>&nbsp;";
		row=row+ j[i].mct_main_id+" - "+j[i].mct_nomen +"</td>";
		$("#srctable").append(row);
		ii=ii+1;
	}
	
	if('${mct_main_list1}' != ""){
    	var mctmain_list = '${mct_main_list1}';
    	const mctmainArray = mctmain_list.split(":");
    	for(var i=0;i<mctmainArray.length;i++){
			$("#"+mctmainArray[i]).prop("checked",true);
    	}
    	findselected();
    }
	
	$("#sregn").text(ii);
}
function callsetall(){
	var chkclk=$('#nSelAll').prop('checked');
	if (chkclk) {
		$('.nrCheckBox').prop('checked',true);		
	} else {
		$('.nrCheckBox').prop('checked',false);
	}
	findselected();
}
function findselected() {
	$("#srctable tr").css('background-color','white');
	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb=$(this).attr('id');
		$("#SRC"+bb).css('background-color','yellow');
		return bb;
	}).get();
	var b=nrSel.join(':');
	
	$("#c_val").val(nrSel.length);

	$("#sus_no_list").val(b);
	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb1=$(this).attr('name');
		return bb1;
	}).get();
	var c=nrSel.join(':');
	$("#unit_name_list").val(c);
	
	drawtregn(c);
}
function drawtregn(data) {
	var ii=0;
	$("#tartable").empty();
	var datap=data.split(":");
	for (var i = 0; i <datap.length; i++){
		var nkrow="<tr id='tarTableTr' padding='5px;'>";
		nkrow=nkrow+"<td>&nbsp;&nbsp;";
		nkrow=nkrow+ datap[i]+"</td>";
		$("#tartable").append(nkrow);
		ii=ii+1;
	}
	$("#tregn").text(ii);
}
////////////////////////////////////////////////////



function Search(){
	if($("#type_veh").val() == "")
	{
		alert("Please select VEH CAT.");
	}
	else if($("#prf_code").val() == "XNR" || $("#prf_code").val() == "" || $("#prf_code").val() == "0")
	{
		alert("Please select VEH TYPE.");
	}
	else
	{
		jQuery("#WaitLoader").show();
		$("#type_veh1").val($("#type_veh").val());
		$("#prf_code1").val($("#prf_code").val());
		/* $("#mct_main1").val($("#mct_main").val()); */
		$("#cont_comd1").val($("#cont_comd").val());
		$("#cont_corps1").val($("#cont_corps").val());
		$("#cont_div1").val($("#cont_div").val());
		$("#cont_bde1").val($("#cont_bde").val());
		$("#unit_name1").val($("#unit_name").val());
		$("#sus_no1").val($("#sus_no").val());
		$("#line_dte_sus1").val($("#line_dte_sus").val());
		
		$("#mct_main_list1").val($("#sus_no_list").val());
		$("#kms1").val($('#kms').val());
		$("#vintag1").val($('#vintag').val());
		$("#type_of_force1").val($('#type_of_force').val());
		document.getElementById('searchForm').submit();
	}
}

function getCONTCorps(fcode){
 	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			
			for ( var i = 0; i < length; i++) {
				if('${cont_corps1}' ==  dec(enc,j[i][0])){
					options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
				}else{
					options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
				}
			}	
			$("select#cont_corps").html(options);
		}
	});
 } 
 function getCONTDiv(fcode){
 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
   		if(j != ""){
		   	var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		for ( var i = 0; i < length; i++) {
			if('${cont_div1}' ==  dec(enc,j[i][0])){
				options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
			}else{
				options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
			}
		}
	   		$("select#cont_div").html(options);
   		}
	});
 } 
function getCONTBde(fcode){
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
	$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		jQuery("select#cont_bde").html(options);
		for ( var i = 0; i < length; i++) {
			if('${cont_bde1}' ==  dec(enc,j[i][0])){
				options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
				$("#cont_bname").val(dec(enc,j[i][1]));
			}else{
				options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
			}
		}	
		$("select#cont_bde").html(options);
		}
	});
}
	
jQuery(function() {
	// Source SUS No
	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	var sus_no = ui.item.value;
			    	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
			    		var length = j.length-1;
					   	var enc = j[length].substring(0,16);
					   	jQuery("#unit_name").val(dec(enc,j[0]));	
			    	}).fail(function(xhr, textStatus, errorThrown) {
			    });
			} 	     
		});	
	});
	// End
	
	// Source Unit Name
    jQuery("#unit_name").keyup(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        		
					        	}
					        	response( susval ); 
				          }
				        });
				      },
				      minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name").value="";
				        	  document.getElementById("sus_no").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						var target_unit_name = ui.item.value;
					 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
					 		 var length = j.length-1;
	 				         var enc = j[length].substring(0,16);
	 				         jQuery("#sus_no").val(dec(enc,j[0]));	
						}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			}); 			
 		});
	});
	
function fnExcelReport()
{
	if($("#type_veh").val() == "")
	{
		alert("Please select VEH CAT.");
	}
	else if($("#prf_code").val() == "0")
	{
		alert("Please select VEH TYPE.");
	}
	else
	{
		var tab_text="<table><tr>";
	    var textRange; var j=0;
	    tab = document.getElementById('getVehicleStatusTbl'); 
		
	    for(j = 0 ; j < tab.rows.length ; j++) 
	    {
			tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
	    }
	    tab_text=tab_text+"</table>";
	    var ua = window.navigator.userAgent;
	    var msie = ua.indexOf("MSIE "); 
	    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) 
	    {
	        txtArea1.document.open("txt/html","replace");
	        txtArea1.document.write(tab_text);
	        txtArea1.document.close();
	        txtArea1.focus(); 
	        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
	    }  
	    else               
	        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  
	    return (sa);
	}
}
</script>