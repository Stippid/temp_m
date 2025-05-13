<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/miso/commonJS/commonmethod.js"></script> 
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">


<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/arrival_departure_report.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>

<form:form name="Arrival_Departure_ReportAction" id="Arrival_Departure_ReportAction" action="Arrival_Departure_ReportAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="Arrival_Departure_ReportCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
					
					<div class="card-header"><h5><b>ARRIVAL/DEPARTURE REPORT</b></h5> <strong>Move Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Auth Letter No 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="letter_no" name="letter_no" maxlength="250" placeholder="" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-6">
			          			<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Letter Date 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="sanction_date" name="sanction_date" class="form-control" max="${date}">
									</div>
								</div>
		          			</div>
						</div>
						<div class="col-md-12">
		                	<div class="col-md-6">
	                        	<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Upload Auth Letter
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="file" id="upload_auth_letter" maxlength="255" name="upload_auth_letter" class="form-control">
										<div style="display: none;">
											<input type="file" id="upload_goi_letter" maxlength="255" name="upload_goi_letter" class="form-control">
										</div>
									</div>
								</div>
                            </div>
                        	 <div class="col-md-6">
	                        	<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Scenario</label>
										
									</div>
									<div class="col-12 col-md-8">
										<select id="type_of_letter" name="type_of_letter" class="form-control-sm form-control" onchange="scnario_onchange()">
		                 					<option value="0">--Select--</option>
		                 					<option value="6">Amendment</option>
		                 					<option value="8">Main Body Movement</option>
		                 					<option value="12"> Re-Orbatting </option>
		                 				</select>
									</div>
								</div>
                            </div>
		                </div>
                    </div>
						<!-- ********************************************************************************* -->
					<!-- </div> -->
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"> <strong>Details Of The Unit </strong> </div>

					<div class="card-body card-block">
					
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label" id="unit"><strong style="color: red;">*</strong>Unit Name </label>
									</div>
									<div class="col-12 col-md-8">
	
										<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Unit Name" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-4">
										<label class=" form-control-label" id="su_no"><strong style="color: red;">*</strong>SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search SUS No" class="form-control autocomplete" autocomplete="off" >
									</div>
								</div>
							</div>
						</div>
					
					<div class="col-md-12" style="display: none;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label">Unit Under Army HQ</label>
								</div>
								<div class="col col-md-6">
									<div class="row form-group">
										<div class="col-md-5" align="center">
											<input type="radio" id="inline-radio1" name="unit_army_hq" value="Y" >&emsp;Yes
										</div>
										<div class="col-md-5" align="center">
											<input type="radio" id="inline-radio2" name="unit_army_hq" value="N" >&emsp; No
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>  
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Op Comd </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="op_comd" name="op_comd" class="form-control-sm form-control" >
										<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
                 							<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                 						</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group" >
								<div class="col col-md-4">
									<label class=" form-control-label">Op Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="op_corps" name="op_corps" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Op Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="op_div"	name="op_div" class="form-control-sm form-control" >
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Op Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="op_bde" name="op_bde" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Cont Comd </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_comd"	name="cont_comd" class="form-control-sm form-control">
										<option value="">--Select--</option>
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
									<label class=" form-control-label">Cont Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_corps" name="cont_corps" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_div"	name="cont_div" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_bde" name="cont_bde" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> <strong style="color: red;">*</strong> Adm Comd </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="adm_comd"	name="adm_comd" class="form-control-sm form-control">
										<option value="">--Select--</option>
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
									<label class=" form-control-label">Adm Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="adm_corps" name="adm_corps" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Adm Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="adm_div" name="adm_div" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Adm Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="adm_bde"	name="adm_bde" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12" style="display: none;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Parent Arm </label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Type of Arm </label>
								</div>
							</div>
						</div>
					</div> 
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> <strong style="color: red;">*</strong> Loc </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="code" name="code" maxlength="5" class="form-control" style="width:100%;" >
				                 	<input type="text" id="loc" name="loc" maxlength="400" class="form-control" style="width:86%;display: inline-block;" readonly="readonly">
		              				<i class="fa fa-search" onclick="openLocLOV();"></i>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">NRS</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="nrs_code" name="nrs_code" maxlength="5" class="form-control">
				                  	<input type="text" id="nrs_name" name="nrs_name" maxlength="400" class="form-control"  readonly="readonly">
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Type of Loc </label>
								</div>
								<div class="col-12 col-md-8">
									<!-- <input type="text" id="type_of_location" name="type_of_location" placeholder="" class="form-control-sm form-control"> -->
									<input type="text" id="type_of_location" name="type_of_location" maxlength="50" class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Trn Type</label>
								</div>
								<div class="col-12 col-md-8">
									<!-- <input type="text" id="modification" name="modification" class="form-control-sm form-control"> -->
									<input type="text" id="modification" name="modification" maxlength="100" class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
	         			<div class="col-md-6">
	         				<div class="row form-group">
			            	   	<div class="col col-md-4">
			                  		<label class=" form-control-label">State </label>
			                	</div>
			                	<div class="col-12 col-md-8">
			                 		<select name="state" id="state" class="form-control-sm form-control" onchange="DistrictList();"></select>
		            			</div>
		          			</div>
	         			</div>
	         			<div class="col-md-6">
	         				<div class="row form-group">
		 						<div class="col col-md-4">
		   							<label class=" form-control-label">District</label>
		 						</div>
		 						<div class="col-12 col-md-8">
		  							<select name="district" id="district" class="form-control-sm form-control">
		  								<option value=""> --Select--</option>
		  							</select>
		 						</div>
							</div>
	         			</div>
	         		</div>	
					<div class="col-md-12" >					
						<div class="col-md-6" style="display: none;">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">CT Part I/II</label>
								</div>
								<div class="col col-md-8">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
											<input type="radio" id="radio1" name="ct_part_i_ii" value="CTPartI" class="form-control form-check-input">CT Part I</label>&nbsp;&nbsp;&nbsp; 
												<label for="inline-radio2" class="form-check-label "> 
													<input type="radio" id="radio2" name="ct_part_i_ii" maxlength="8" value="CTPartII" class="form-control form-check-input">CT Part II</label> &nbsp;&nbsp;&nbsp; 
												<label for="inline-radio3" class="form-check-label "> 
													<input type="radio" id="radio3" name="ct_part_i_ii" maxlength="8" value="Others" class="form-control form-check-input">Others
												</label>
											</div>
										</div>
									</div>
								</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="selectLg" class=" form-control-label">Type of Unit</label>
								</div>
								<div class="col-12 col-md-8">
									<select name="type_force" id="type_force" class="form-control-sm form-control">
										<option value="">--Select--</option>
                           				<c:forEach var="item" items="${getTypeOfUnitList}">
            								<option value="${item[0]}" >${item[0]} - ${item[1]}</option>
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
									<label class=" form-control-label"><strong style="color: red;">*</strong> Departure Date 
									</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="comm_depart_date" name="comm_depart_date"  class="form-control" max="${date}">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Arrival Date  
									</label>
								</div>
								<div class="col-12 col-md-8">
								
									<input type="date" id="compltn_arrv_date" name="compltn_arrv_date" onchange="return checkdate(this,comm_depart_date)" class="form-control" max="${date}">
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6" style="display: none;">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> <strong style="color: red;">*</strong> Arm Name</label>
								</div>
                				<div class="col-12 col-md-8">
                					<input type="hidden" id="arm_code" maxlength="4" name="arm_code" >
                 					<select id="arm_name" name="arm_name"  class="form-control-sm form-control">
						                <option value="0">--Select--</option>
               							<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
               								<option value="${item.arm_code}" >${item.arm_desc}</option>
               							</c:forEach>
						            </select>
                				</div>
           					</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Address</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea class="form-control" id="address" name="address" maxlength="200" ></textarea>
								</div>
							</div>
						</div>
					</div>
				
					<div class="col-md-5 col-md-offset-1">							
							<div class="row form-group" style="display: none;">
                				<div class="col col-md-12">
                					<input type="text" id="cont_aname" name="cont_aname"  maxlength="100" class="form-control">
                					<input type="text" id="cont_bname" name="cont_bname"  maxlength="100" class="form-control">
                					<input type="text" id="cont_cname" name="cont_cname"  maxlength="100" class="form-control">
                					<input type="text" id="cont_dname" name="cont_dname"  maxlength="100" class="form-control">
                				</div>
                			</div>
						</div>
					</div>

					<div class="card-footer" align="center"  class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">
						<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<script>
$(function() {
	$("#unit_name").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#unit_name");
			 var scnario =$("#type_of_letter").val(); 
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getUnitsNameActiveListOrbat?"+key+"="+value,
			        data: {unit_name:unit_name, scnario : scnario},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = "";
				        	if(data.length != 0){
				        		enc = data[length].substring(0,16);
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
			        	  alert("Please Enter Approved Unit Name");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			      	$(this).val(ui.item.value);
			        getOrbatDetailsFromUnitName($(this).val());
			        
			  	} 	     
			});
		}); 
	
	 // Source Sus No auto
	 
	 
	
	$("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#sus_no");
		 var scnario =$("#type_of_letter").val(); 
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getSusNoActiveListorbat?"+key+"="+value,
		        data: {sus_no:sus_no,scnario:scnario},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = "";
			        	if(data.length != 0){
			        		enc = data[length].substring(0,16);
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
		        	  alert("Please Enter Approved SUS NO");
		        	  $("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		      	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		       		var length = j.length-1;
					var enc = j[length].substring(0,16);
			   		$("#unit_name").val(dec(enc,j[0]));
			   		getOrbatDetailsFromUnitName(dec(enc,j[0]))
			   		
// 			   		if(scnario=="12"){
// 			   			check_sus();
// 			   		}
			   		
			   	});
		     }
		});
	}); 
});
</script>

<script>
$(document).ready(function() {
	$("#upload_auth_letter").change(function(){	
		checkFileExt(this);
	});
	$("#upload_goi_letter").change(function(){	
		checkFileExt(this);
	});
	
	StateList();
	var fcode = 0;

});   	
    	    
    	
  	var Opcom,Opcorps,Opdiv,Opbde,Contcom,Contcorps,Contdiv,Contbde,Admincom,Admincorps,Admindiv,Adminbde,ParentArm,TypeOfArm;
  
  	function getOrbatDetailsFromUnitName(unitName){
  		 var scnario =$("#type_of_letter").val(); 
		$.post("getUnitDetailsList?"+key+"="+value,{unitName:unitName}, function(j) {
	       	if(j.length > 0) {
	        	var length = j.length-1;
	 			var enc = j[length].substring(0,16);
	 			
	        	$("#sus_no").val(dec(enc,j[1]));
	        	if(scnario=="12"){
			   			check_sus();
			   		}
	        	
	      	 	var forcodeOperation = dec(enc,j[4]);
	      	 	var forcodeControl = dec(enc,j[5]);
	      	 	var forcodeAdmin = dec(enc,j[6]);
	      	 	var Arm = dec(enc,j[1]);
	      	 		
	     	    Opcom = forcodeOperation[0];
	     	   
	  	        Opcorps = forcodeOperation[0] +forcodeOperation[1] + forcodeOperation[2];
	  	        Opdiv = forcodeOperation[0] +forcodeOperation[1] + forcodeOperation[2] + forcodeOperation[3] + forcodeOperation[4] + forcodeOperation[5];
	  	        Opbde = forcodeOperation ;
	  	        	
	  	        Contcom =forcodeControl[0];
	  	        Contcorps=forcodeControl[0] + forcodeControl[1] + forcodeControl[2];
	  	        Contdiv= forcodeControl[0] + forcodeControl[1] + forcodeControl[2] + forcodeControl[3] + forcodeControl[4] + forcodeControl[5];;
	  	        Contbde=forcodeControl;
	  	        	
	  	        Admincom = forcodeAdmin[0];
	  	        Admincorps = forcodeAdmin[0] + forcodeAdmin[1] + forcodeAdmin[2];
	  	        Admindiv = forcodeAdmin[0] + forcodeAdmin[1] + forcodeAdmin[2] + forcodeAdmin[3] + forcodeAdmin[4] + forcodeAdmin[5];
	  	        Adminbde = forcodeAdmin;
	  	        	
	  	        var unit_under_armhq = dec(enc,j[3]);
	      	 	if(unit_under_armhq == 'Y')
	      	 	{
	      	 		$("#inline-radio1").prop("checked", true);
		       		$("#inline-radio1").attr('checked', 'checked');
	      	 	}
	      	 	else
	      	 	{
	 	        	$("#inline-radio2").prop("checked", true);
	       	 		$("#inline-radio2").attr('checked', 'checked');
	      	 	}
	      	 		
	 	        var ct_part = dec(enc,j[15]);
	       	 		
	 	        if(ct_part == 'CTPartI')
	     	 	{	       	 				
	       	 		$("#radio1").prop("checked", true);
	       	 		$("#radio1").attr('checked', 'checked');
	     		}
	 	    	else if(ct_part == 'CTPartII')
	     		{
	     	 		$("#radio2").prop("checked", true);
	       	 		$("#radio2").attr('checked', 'checked');
	       		}
	 	   	 	else 
	    		{
	     			$("#radio3").prop("checked", true);
	     	 		$("#radio3").attr('checked', 'checked');
	    		}
	 	        	
	 	        	 	
	 	         $('#op_comd option[value="'+Opcom+'"]').prop("selected", true);
	 	         getOPCorps(Opcorps);
	 	         getOPDiv(Opdiv);
	 	         getOPBde(Opbde);
	 	        	 
	 	    	  
	 	   		$('#cont_comd option[value="'+ Contcom +'"]').prop("selected", true);
	 	 		var cont_cname = $("#cont_comd").find('option:selected').attr("name");    	 
	 			$("#cont_cname").val(cont_cname);
	  	       	getCONTCorps(Contcorps);
	  	    	getCONTDiv(Contdiv);
	  	   		getCONTBde(Contbde);
	  		    
	  	    	$('#adm_comd option[value="'+ Admincom +'"]').prop("selected", true);
	  	      	getADMCorps(Admincorps);
	  	      	getADMDiv(Admindiv);
	  	      	getADMBde(Adminbde);
	  	        
	       		ParentArm = Arm[0] + Arm[1]; 
	 	   		TypeOfArm = Arm[0] + Arm[1] + Arm[2] + Arm[3]; 
	 	        	 
		   		$("#code").val(dec(enc,j[8]));
		   		$("#loc").val(dec(enc,j[9]));
		   		$("#nrs_code").val(dec(enc,j[10]));
		   		$("#nrs_name").val(dec(enc,j[11]));
		   		$('#type_of_location').val(dec(enc,j[12]));
		    	$('#modification').val(dec(enc,j[13]));
			    
	 			$('#arm_code').val(dec(enc,j[7]));
	   	    	$("select#arm_name").val(dec(enc,j[7]));
	   	    	$("select#type_force").val(dec(enc,j[14])); 
	   	    }else{
	   			alert('Data Not Available');
	   		}
		});
	}
     
	function StateList(){		
 		var options="";
 		$.post("getStateList?"+key+"="+value, function(j) {		
 			options = '<option value="">'+ "--Select--" + '</option>';
 			for ( var i = 0; i < j.length; i++) {
 				options += '<option value="' + j[i].stcode11+ '" name="'+j[i].stname+'">'+ j[i].stname + '</option>';
 			}				
 			$("select#state").html(options);	
 		});
 	}

 	function DistrictList(){		
 		var st="";
 		st=document.getElementById("state").value;
 		$("select#district").empty();
 		if(st!='--Select--' || st !="" || st != null){
 			$.post("getStateWiseDistrictNames?"+key+"="+value, {stateId : st}, function(j) {			
 				var options = '<option value="">'+ "--Select--" + '</option>';
 				for ( var i = 0; i < j.length; i++) {
 					options += '<option value="' + j[i].distId + '" name="'+j[i].distName+'">'
 					+ j[i].distName + '</option>';
 				}
 				$("select#district").html(options);
 			});
 		} 
 		else
 		{
 			var opt = '<option value="">'+ "--Select--" + '</option>';
 			$("select#district").append("");
 		}
 	}
</script>
<script>
function validate(){
	if($("#letter_no").val() == ""){
		alert("Please Enter the Auth Letter No.");
		$("#letter_no").focus();
		return false;
	}
	if($("#sanction_date").val() == ""){
		alert("Please Select Date of Letter");
		$("#sanction_date").focus();
		return false;
    }
	if($("#upload_auth_letter").get(0).files.length === 0){
		alert("Please Upload Document of Auth Letter");
		$("#upload_auth_letter").focus();
		return false;
    } 
	if($("#type_of_letter").val() == "0"){
		alert("Please Select Scenario");
		$("#type_of_letter").focus();
		return false;
    }
	if($("#unit_name").val() == ""){
		alert("Please Select Unit Name");
		$("#unit_name").focus();
		return false;
    }
	if($("#op_comd").val() == ""){
		alert("Please Select Operation Command");
		$("#op_comd").focus();
		return false;
    }
	if($("#cont_comd").val() == ""){
		alert("Please Select Control Command");
		$("#cont_comd").focus();
		return false;
    }
	if($("#adm_comd").val() == ""){
		alert("Please Select Admin Command");
		$("#adm_comd").focus();
		return false;
    }
	if($("#code").val() == ""){
		alert("Please Select Loc Code");
		$("#code").focus();
		return false;
    }
	if($("#comm_depart_date").val() == ""){
		alert("Please Select Commencement Date");
		$("#comm_depart_date").focus();
		return false;
    }
	var from = document.getElementById("comm_depart_date").value;
	var to = document.getElementById("compltn_arrv_date").value;
	if ((Date.parse(from) > Date.parse(to))) {
		alert('You cannot select To date less than From Date.');
		document.getElementById("compltn_arrv_date").focus();
		return false;
	}
	return true;
}

var popupWindow = null
function openLocLOV(url) {
	popupWindow = window.open("locationLOV", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
}
function HandlePopupResult(loc,nrs_name,loc_code,trn_type,typeofloc,nrscode) {
    $("#code").val(loc_code);
	$("#nrs_name").val(nrs_name);
	$("#modification").val(trn_type);
	$("#type_of_location").val(typeofloc);
	$("#loc").val(loc);
	$("#nrs_code").val(nrscode);
}
function parent_disable() {
	if(popupWindow && !popupWindow.closed)
		popupWindow.focus();
}

var formation_type;
	function check_sus() {


		  $.ajaxSetup({
		        async : false
			});
		    var result=false;
	
		    var unit_sus_no =$("#sus_no").val(); 
// 			var we_pe = document.getElementById("We_pe_no").value;
		

			$.post("check_sus_hierarchy?" + key + "=" + value, {unit_sus_no: unit_sus_no }, function(j) {
			    var length= j.length;
	
						formation_type = j[0];
						
			    if (length != 0) {	    
			    	  for (var i = 0; i < j.length; i++) {		            
				            if (j[0]=="Command") {
				            	
				            	document.getElementById("op_corps").setAttribute("readonly", "readonly");
				            	document.getElementById("op_corps").setAttribute("readonly", "readonly");
				            	document.getElementById("op_div").setAttribute("readonly", "readonly");
								document.getElementById("op_bde").setAttribute("readonly", "readonly");
				            	
								document.getElementById("cont_comd").setAttribute("readonly", "readonly");
								document.getElementById("cont_corps").setAttribute("readonly", "readonly");
								document.getElementById("cont_div").setAttribute("readonly", "readonly");
				            	document.getElementById("cont_bde").setAttribute("readonly", "readonly");
				            	
				            	document.getElementById("adm_comd").setAttribute("readonly", "readonly");
				            	document.getElementById("adm_corps").setAttribute("readonly", "readonly");
				            	document.getElementById("adm_div").setAttribute("readonly", "readonly");
				            	document.getElementById("adm_bde").setAttribute("readonly", "readonly");
				            	
				            }
				            else  if (j[0]=="Corps") {
				            	
				            	
				            	document.getElementById("op_corps").setAttribute("readonly", "readonly");
				            	document.getElementById("op_div").setAttribute("readonly", "readonly");
								document.getElementById("op_bde").setAttribute("readonly", "readonly");
				            	
								document.getElementById("cont_corps").setAttribute("readonly", "readonly");
								document.getElementById("cont_div").setAttribute("readonly", "readonly");
				            	document.getElementById("cont_bde").setAttribute("readonly", "readonly");
				            	
				            	document.getElementById("adm_corps").setAttribute("readonly", "readonly");
				            	document.getElementById("adm_div").setAttribute("readonly", "readonly");
				            	document.getElementById("adm_bde").setAttribute("readonly", "readonly");
				            
				            	
				            	document.getElementById("op_comd").removeAttribute("readonly");

				                document.getElementById("cont_comd").removeAttribute("readonly");

				                document.getElementById("adm_comd").removeAttribute("readonly");
				            }
 							else  if (j[0]=="Division") {
 								document.getElementById("op_div").setAttribute("readonly", "readonly");
								document.getElementById("op_bde").setAttribute("readonly", "readonly");
				            	
								document.getElementById("cont_div").setAttribute("readonly", "readonly");
				            	document.getElementById("cont_bde").setAttribute("readonly", "readonly");
				            	
				            	document.getElementById("adm_div").setAttribute("readonly", "readonly");
				            	document.getElementById("adm_bde").setAttribute("readonly", "readonly");
				            	
				            	
				            	document.getElementById("op_comd").removeAttribute("readonly");
				            	document.getElementById("op_corps").removeAttribute("readonly");

				                document.getElementById("cont_comd").removeAttribute("readonly");
				                document.getElementById("cont_corps").removeAttribute("readonly");

				                document.getElementById("adm_comd").removeAttribute("readonly");
				                document.getElementById("adm_corps").removeAttribute("readonly");
				            }
				            else  if (j[0]=="Brigade") {
				            	
				            	document.getElementById("op_bde").setAttribute("readonly", "readonly");
				            	
				            	document.getElementById("cont_bde").setAttribute("readonly", "readonly");
				            	
				            	document.getElementById("adm_bde").setAttribute("readonly", "readonly");

				            	document.getElementById("op_comd").removeAttribute("readonly");
				            	document.getElementById("op_corps").removeAttribute("readonly");
				                document.getElementById("op_div").removeAttribute("readonly");

				                document.getElementById("cont_comd").removeAttribute("readonly");
				                document.getElementById("cont_corps").removeAttribute("readonly");
				                document.getElementById("cont_div").removeAttribute("readonly");

				                document.getElementById("adm_comd").removeAttribute("readonly");
				                document.getElementById("adm_corps").removeAttribute("readonly");
				                document.getElementById("adm_div").removeAttribute("readonly");
				                
		          			}
			    	  }
			    }else {	     	      	        
			    	result= true;     
			    }
			}).fail(function(xhr, textStatus, errorThrown) {
		 
			});
			 return result;     
		
	}

</script>
<script>
$(document).ready(function() {    
	var ddd = '${date}';
	$("#sanction_date").change(function(){
		if($("#sanction_date").val() > '${date}'){
			$("#sanction_date").val("");
			alert("You Can't select Future Date!");
			$("#sanction_date").focus();
			return false;
		}
	});	
	$("#comm_depart_date").change(function(){
		if($("#comm_depart_date").val() > '${date}'){
			$("#comm_depart_date").val("");
			alert("You Can't select Future Date!");
			$("#comm_depart_date").focus();
			return false;
		}
	});	
	
	$("#compltn_arrv_date").change(function(){
		if($("#compltn_arrv_date").val() > '${date}'){
			$("#compltn_arrv_date").val("");
			alert("You Can't select Future Date!");
			$("#compltn_arrv_date").focus();
			return false;
		}
	});
});	
</script>