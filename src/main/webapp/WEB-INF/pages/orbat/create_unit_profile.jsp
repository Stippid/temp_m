<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="CreateUnitProfile" id="CreateUnitProfile" action="createUnitProfileAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="createUnitProfileCMD" enctype="multipart/form-data">

	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
	    			<h5><b>CREATE UNIT PROFILE</b></h5>
	          		<div class="card-header"> <strong>Schedule Details</strong> </div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">
	            				
	            				<div class="col-md-6">
	            					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Auth Letter No</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="letter_no" name="letter_no" maxlength="250" placeholder="Auth Letter No" class="form-control">
	                					</div>
	              					</div>
	            				</div>
	            				<div class="col-md-6">
	            					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Letter Date</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="date" id="sanction_date" name="sanction_date" class="form-control">
										</div>
	  								</div>
	            				</div>
	            			</div>
	            		</div>
						
						<div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"> <strong>Op Details </strong> </div>
							<div class="card-body card-block">
								<div class="col-md-12">
	            					<div class="col-md-6">
	            						<div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
											</div>
											<div class="col-12 col-md-8">			
												<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Unit Name" class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
	            					</div>
	            					<div class="col-md-6">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="sus_no" name="sus_no" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search SUS No" class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
	            					</div>
	            				</div>
	            				<div class="col-md-12">
	            					<div class="col-md-6">
	            						<div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Arm Name</label>
											</div>
			                				<div class="col-12 col-md-8">
			                 					<select id="arm_name" name="arm_name"  class="form-control">
									                <option value="0">--Select--</option>
		                  							<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
		                  								<option value="${item.arm_code}" >${item.arm_code} - ${item.arm_desc}</option>
		                  							</c:forEach>
									            </select>
			                				</div>
			           					</div>
	            					</div>
	            					<div class="col-md-6">
	            						<div class="row form-group">
											<div class="col col-md-4">
												<label for="selectLg" class=" form-control-label"> Type Of Unit</label>
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
						                  		<label class=" form-control-label"> State</label>
						                	</div>
						                	<div class="col-12 col-md-8">
						                 		<select name="state" id="state" class="form-control-sm form-control" onchange="DistrictList();"></select>
					            			</div>
					          			</div>
	            					</div>
	            					<div class="col-md-6">
	            						<div class="row form-group">
					 						<div class="col col-md-4">
					   							<label class=" form-control-label"> District</label>
					 						</div>
					 						<div class="col-12 col-md-8">
					  							<select name="district" id="district" class="form-control-sm form-control">
					  								<option value=""> --Select--</option>
					  							</select>
					 						</div>
										</div>
	            					</div>
	            				</div>	
	            					
	            				<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label for="text-input" class=" form-control-label"> CT Part I/II</label>
												</div>
												<div class="col col-md-8">
													<div class="form-check-inline form-check">
														<label for="inline-radio1" class="form-check-label ">
															<input type="radio" id="radio1" name="ct_part_i_ii" maxlength="8" value="CTPartI" class="form-control form-check-input">CT Part I</label>&nbsp;&nbsp;&nbsp; 
														<label for="inline-radio2" class="form-check-label "> 
															<input type="radio" id="radio2" name="ct_part_i_ii" maxlength="8" value="CTPartII" class="form-control form-check-input">CT Part II</label> &nbsp;&nbsp;&nbsp; 
														<label for="inline-radio3" class="form-check-label "> 
															<input type="radio" id="radio3" name="ct_part_i_ii" maxlength="8" value="Others" class="form-control form-check-input">Others</label>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label class=" form-control-label"> Address</label>
												</div>
												<div class="col-12 col-md-8">
													<textarea class="form-control" id="address" name="address" maxlength="200"></textarea>
												</div>
											</div>
										</div>
									</div>
	              	</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
	              	</div> 
	        	</div>
			</div>
		</div>
	</div>
</form:form>
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
	if($("#unit_name").val() == ""){
		alert("Please Select Unit Name");
		$("#unit_name").focus();
		return false;
    }
	if($("#comm_depart_date").val() == ""){
		alert("Please Select From Date");
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
</script>
<script>
      $(document).ready(function() {      
    	  
    	  $("#unit_name").keypress(function(){
  			var unit_name = this.value;
  			
  				 var susNoAuto=$("#unit_name");
  				  susNoAuto.autocomplete({
  				      source: function( request, response ) {
  				        $.ajax({
  				        type: 'POST',
  				        url: "getUnitsNameActiveList?"+key+"="+value,
  				        data: {unit_name:unit_name},
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
    			unitNameAuto.autocomplete({
    			      source: function( request, response ) {
    			        $.ajax({
    			        type: 'POST',
    			        url: "getSusNoActiveList?"+key+"="+value,
    			        data: {sus_no:sus_no},
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
    			       	});
    			     }
    			});
    		});
    	});
</script>


<script>
       $(document).ready(function() {
    	   
    	   StateList();
    	 
    	    $('select#arm_name').change(function() {
   	        	var code = $(this).find('option:selected').attr("name");    	   	
	   	        $("#arm_code").val(code);
    	   	});
    	    
    	   
    	    
  
    	    
    	
    	 
    	    //***********************END*************************************************************************//
    	    
    	    $('#unit_name').change(function() {
   	        	var unitName = this.value;
   	        	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unitName}, function(j) {
   	        		if(j.length != 0){
   	        			var length = j.length-1;
						var enc = j[length][0].substring(0,16);
			   			$("#sus_no").val(dec(enc,j[0][0]));
   	        		}
   	        	});
   	    	});      
       });
       
       
       /* //////////////////////////////////DETAIL OF UNIT/////////////////////////////////////////////////////////////// */
	    
	    var Opcom,Opcorps,Opdiv,Opbde,Contcom,Contcorps,Contdiv,Contbde,Admincom,Admincorps,Admindiv,Adminbde,ParentArm,TypeOfArm;
	    function getOrbatDetailsFromUnitName(unitName){
	        $.post("getUnitDetailsList?"+key+"="+value,{unitName:unitName}, function(j) {
	        	if(j.length > 0) {	
		        	var length = j.length-1;
			 		var enc = j[length].substring(0,16);
			 			
			        $("#sus_no").val(dec(enc,j[1]));
	        	 	var Arm = dec(enc,j[1]);
	        	 	
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
		   	     
		   	     	var arm_code = dec(enc,j[7]);
		   	    	$("select#arm_name").val(arm_code);
		   	     	
		   	     	var type_force = dec(enc,j[14]);
		   	     	$("select#type_force").val(type_force);
		   	     	
		   	     	$("#address").text(dec(enc,j[16]));
		        }else{
		        	alert('Data Not Available');
		        }
			});
		};
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

