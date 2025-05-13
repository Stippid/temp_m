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

<form:form name="cii_unt_dtl" id="cii_unt_dtl" action="cii_unt_dtlAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="cii_unt_dtlCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
					
					<div class="card-header"><h5><b>CII Unit Details</b></h5> <strong>Entry Form</strong></div>
					
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
					
					 
					<div class="col-md-12">
	            					<%-- <div class="col-md-6">
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
	            					</div> --%>
	            					
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label" id="fmn"><strong style="color: red;">*</strong>Fmn Name </label>
									</div>
									<div class="col-12 col-md-8">
	
										<input type="text" id="fmn_name" name="fmn_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Fmn Name" class="form-control autocomplete" autocomplete="off" onblur="updateFormCode()">
									</div>
								</div>
							</div>
								
	            					
	            					<div class="col-md-6">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong>Formation Code </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="form_code" name="form_code" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Enter Form code" class="form-control autocomplete" autocomplete="off" readonly>
											</div>
										</div>
	            					</div>
	            				</div>
					
					
					
					
					
					
					
					
					
					
					
					
					<!-- <div class="col-md-12" style="display: none;">
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
					</div> --> 
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
					
					<!-- <div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Type of Loc </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="type_of_location" name="type_of_location" placeholder="" class="form-control-sm form-control">
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
									<input type="text" id="modification" name="modification" class="form-control-sm form-control">
									<input type="text" id="modification" name="modification" maxlength="100" class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
					</div> -->
						
					 
					
					
					
					<%-- <div class="col-md-12">
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
						
					</div> --%>
					
					
					<!-- <div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Level1 </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level1" name="level1" class="form-control-sm form-control" onchange="updateLevelDropdown(1, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
	                                    
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group" >
								<div class="col col-md-4">
									<label class=" form-control-label">Level2</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level2" name="level2" class="form-control-sm form-control" onchange="updateLevelDropdown(2, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Level3 </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level3" name="level3" class="form-control-sm form-control" onchange="updateLevelDropdown(3, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
	                                    
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group" >
								<div class="col col-md-4">
									<label class=" form-control-label">Level4</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level4" name="level4" class="form-control-sm form-control" onchange="updateLevelDropdown(4, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Level5 </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level5" name="level5" class="form-control-sm form-control" onchange="updateLevelDropdown(5, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
	                                    
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group" >
								<div class="col col-md-4">
									<label class=" form-control-label">Level 6</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level6" name="level6" class="form-control-sm form-control" onchange="updateLevelDropdown(6, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Level7 </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level7" name="level7" class="form-control-sm form-control" onchange="updateLevelDropdown(7, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
	                                    
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group" >
								<div class="col col-md-4">
									<label class=" form-control-label">Level 8</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level8" name="level8" class="form-control-sm form-control" onchange="updateLevelDropdown(8, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Level9 </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level9" name="level9" class="form-control-sm form-control" onchange="updateLevelDropdown(9, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
	                                    
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group" >
								<div class="col col-md-4">
									<label class=" form-control-label">Level 10</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="level10" name="level10" class="form-control-sm form-control" onchange="updateLevelDropdown(10, document.getElementById('arm_name').value)">
										<option value="">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div> -->
				    
				    
				    <div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>From Date 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_from" name="date_from" maxlength="250" placeholder="" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-6">
			          			<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> To Date 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_to" name="date_to" class="form-control" max="${date}">
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
															<label for="text-input" class=" form-control-label">Authority</label>
                                                  </div>
                                                   <div class="col-12 col-md-8">
	                  						                <textarea  id="remarks" name="remarks"  maxlength="255" class="form-control char-counter1"> </textarea>
										           </div>
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
      $(document).ready(function() {      
    	  
    	  $('input[id^="unit_name"]').keypress(function(){
  			var unit_name = this.value;
  			
  				 var susNoAuto=$('input[id^="unit_name"]');
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
    		$('input[id^="sus_no"]').keyup(function(){
    			var sus_no = this.value;
    			var unitNameAuto=$('input[id^="sus_no"]');
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
    	   
    	   
    	   
    		$("input#fmn_name").keyup(function() {
    			var fmn_name = this.value;
    			var fmnNameAuto = $("#fmn_name");
    			fmnNameAuto.autocomplete({
    				source : function(request, response) {
    					$.ajax({
    						type : 'POST',
    						url : "getFmnName?" + key + "=" + value,
    						data : {
    							fmn_name : fmn_name
    						},
    						success : function(data) {
    							debugger
    							 var susval = [];
    					        	  var length = data.length-1;
    					        	  var enc = data[length].substring(0,16);
    						        	for(var i = 0;i<data.length;i++){
    						        		susval.push(dec(enc,data[i]));
    						        	}
    							
    							response(susval);
    						}
    					});
    				},
    				minLength : 1,
    				autoFill : true,
    				
    				change: function(event, ui) {
 				    	 if (ui.item) {   	        	  
 				        	  return true;    	            
 				          } else {
 				        	  alert("Please Select Approved FMN Name");
 				        	  document.getElementById("sus_no").value="";
 				        	  susNoAuto.val("");	        	  
 				        	  susNoAuto.focus();
 				        	  return false;	             
 				          }    	         
 				      },
    				
    				 select: function( event, ui ) {
   				      	$(this).val(ui.item.value);
   				     updateFormCode($(this).val());
   				      
    				}
    			});
    		});
    	   
    	   
    	   
    		
    	});
</script> 


<script>
 	   
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
     
	
</script>
<script>
function validate(){
	
	if($("#unit_name").val() == ""){
		alert("Please Select Unit Name");
		$("#unit_name").focus();
		return false;
    }
	
	if($("#code").val() == ""){
		alert("Please Select Loc Code");
		$("#code").focus();
		return false;
    }
	
	/* if($("#form_code").val() == ""){
		alert("Please Enter form Code");
		$("#form_code").focus();
		return false;
    } */
	
	if($("#fmn_name").val() == ""){
		alert("Please Enter FMN Name");
		$("#fmn_name").focus();
		return false;
    }
    
	if($("#form_code").val() == ""){
		alert("Please Select Approved FMN Name");
		$("#form_code").focus();
		return false;
    }
	
	if($("#date_to").val() == ""){
		alert("Please Select To Date");
		$("#date_to").focus();
		return false;
    }
	
	
	if($("#date_from").val() == ""){
		alert("Please Select From Date");
		$("#date_from").focus();
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




function updateFormCode() {
    var fmnName = document.getElementById("fmn_name").value;

    
    var url = "get_fmncode?fmn_name=" + encodeURIComponent(fmnName);

    
    $.ajax({
        url: url,
        type: "GET",
        success: function(formCode) {
            document.getElementById("form_code").value = formCode;
        },
        error: function(xhr, status, error) {
            console.error('Error updating form code:', error);
        }
    });
}















</script>
