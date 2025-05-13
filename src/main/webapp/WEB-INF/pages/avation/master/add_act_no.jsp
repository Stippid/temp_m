<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="add_act_no" id="add_act_no" action="add_act_noAction" method="post" class="form-horizontal" commandName="add_act_noCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ACT NO CREATION</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>ACT Nomenclature</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="act_nomen" name="act_nomen"  placeholder=""  class="form-control autocomplete" autocomplete="off" maxlength="100"> 
						                </div>
						            </div>
	              				</div>
	              				
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
		                				<div class="col-md-4">
		                  					  <label class=" form-control-label">ACT </label>
		                				 </div>
		                				 <div class="col-md-8">
		                  					  <input type="text" id="act_main_id" name="act_main_id" maxlength="4" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly">
		                				 </div>
	              					</div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						                <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Make Nomenclature</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="desc_make" name="desc_make" placeholder="" maxlength="100" class="form-control autocomplete" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label"> Make</label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input type="text" id="make_no" name="make_no" placeholder="" maxlength="3" class="form-control autocomplete" autocomplete="off" readonly="readonly">
	                					</div>
	              					</div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">Model Nomenclature</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="desc_model" name="desc_model" placeholder="" maxlength="100" class="form-control autocomplete" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label">Model </label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input type="text" id="model_id" name="model_id" placeholder="" maxlength="3" class="form-control autocomplete" autocomplete="off" readonly="readonly">
	                					</div>
	              					</div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label">PRF Gp</label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input name="prf_group" class="form-control" id="prf_group" maxlength="5" readonly="readonly">
	                					</div>
	              					</div>
	              				</div>
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label"> Crew</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="veh_class_code" name="veh_class_code" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="2" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label"> Cl Code</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="veh_class_code" name="veh_class_code" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="2" readonly="readonly">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label"> Capacity</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="veh_class_code" name="veh_class_code" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="2" readonly="readonly">
						                </div>
						            </div>
	              				</div>
	              				
	              			</div>
	              			 		 
	              			<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                   <label class=" form-control-label">Auth</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                  <label class=" form-control-label">Powerplant</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">Length</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			
	              			         					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                   <label class=" form-control-label">Height</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              		
	              			<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">Empty weight</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			
	              			         					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">Gross weight</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">Main rotor diameter</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			
	              			         					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                   <label class=" form-control-label">Main rotor area</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                 <label class=" form-control-label">Maximum speed</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			
	              			         					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">Range</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                  <label class=" form-control-label">Service ceiling</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			
	              			         					
	              				
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">Rate of climb</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="" class="form-control autocomplete" maxlength="45" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
							              <div class="col-md-4">
							                 <label class=" form-control-label">Under Slung Carriage Capacity<br></label>
							             </div>
							             <div class="col-md-8">
							             	 <div class="form-check-inline form-check">
											<label for="inline-radio1" class="form-check-label "><input type="radio" id="ba_no_type1" name="ba_no_type" value="0" class="form-check-input" onchange="mcthideshow()" >Yes</label>&nbsp;&nbsp;&nbsp; 
										    <label for="inline-radio2" class="form-check-label "> <input type="radio" id="ba_no_type2" name="ba_no_type" value="1" class="form-check-input"  onclick="ClearField();">No</label>&nbsp;&nbsp;&nbsp;
										</div>
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
		                					 <div class="col-md-4">
		                  						<label class=" form-control-label">Type of Fuel</label>
		                					</div>
		                					<div class="col-md-8">
		                					    <select id="type_fuel" name="type_fuel" class="form-control">
				                                    <option value="0">--Select--</option>
				                                    <option value="1">Petrol</option>
				                                    <option value="2">Diesel</option>
				                                    <option value="3">Electric/Hybrid</option>
				                                    <option value="4">CNG/PNG</option>	
				                               </select>
		                					</div>
	              						</div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
							             <div class="col-md-4">
							                 <label class=" form-control-label">Lift Capacity<br><span style="font-size: 10px;font-size:12px;color:red">(In Tons)</span></label>
							             </div>
							             <div class="col-md-8">
											<input type="text" id="lift_capacity" name="lift_capacity" maxlength="5" class="form-control" onkeypress="return isNumber0_9Only(event);" value="0" placeholder="Max Five Digits Integer Value">
							            </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
		                					 <div class="col-md-4">
		                  						<label class=" form-control-label"> Fuel Tank Capacity</label>
		                					</div>
		                					<div class="col-md-8">
		                					    <input type="text" id="type_fuel" name="type_fuel" class="form-control">
				                                    
				                              
		                					</div>
	              						</div>
	              				</div>
	              				
	              			</div>
	              			<div class="col-md-12">	      
	              			<div class="col-md-6">
	              					<div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label">Nodal Dte</label>
	                					</div>
	                					<div class="col-md-8">
	                					    <input type="text" id="sponsoring_dte" name="sponsoring_dte" class="form-control autocomplete" autocomplete="off" maxlength="100">
	                					</div>
              						</div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label">Type Of Communication</label>
	                					</div>
	                					<div class="col-md-8">
	                					    <select id="type_fuel" name="type_fuel" class="form-control">
				                                    <option value="0">--Select--</option>
				                                    <option value="1">VHF Trans-receiver</option>
				                                    <option value="2">HF Trans-receiver</option>
				                                    
				                               </select>
	                					</div>
              						</div>
	              				</div>
	              				</div>
	              				<div class="col-md-12">	      
	              			
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label">Navigation</label>
	                					</div>
	                					<div class="col-md-8">
	                					    <select id="type_fuel" name="type_fuel" class="form-control">
				                                    <option value="0">--Select--</option>
				                                    <option value="1">GPS</option>
				                                    <option value="2">Inertial</option>
				                                     <option value="3">Radio</option>
				                                    
				                               </select>
				                                   
				                                    
				                             
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
</form:form>

<script>
function validate(){    
	if($("#act_nomen").val() == ""){
		alert("Please Enter act Nomenclature.");
		return false;
	}  
	
	if($("#act_main_id").val() == ""){
		alert("Please Enter the act No.");
		return false;
	}  
	
	if($("#desc_make").val() == ""){
		alert("Please Enter MAKE Nomenclature. ");
		return false;
	}  
	
	if($("#make_no").val() == ""){
		alert("Please Enter the MAKE No.");
		return false;
	}  
	
	/* if($("#desc_model").val() == ""){
		alert("Please Enter MODEL Nomenclature. ");
		return false;
	}  
	
	if($("#model_id").val() == ""){
		alert("Please Enter the MODEL No.");
		return false;
	}   */
	
	if($("#prf_group").val() == ""){
		alert("Please Select the PRF.");
		$("input#prf_group").focus();
		return false;
	}  
	 
	if(type_of_vehicle.selectedIndex == 0){
		alert("Please Select the Type of Vehicle.");
		return false;
	} 
	 
	if($("#veh_class_code").val() == ""){
		alert("Please Enter the Vehicel Class Code.");
		return false;
	} 
	
	if(purpose_of_vehicle.selectedIndex == 0){
		alert("Please Select the Purpose of Vehicle.");
		return false;
	}

	if($("#auth_letter_no").val() == ""){
		alert("Please Enter Authority. ");
		return false;
	} 
	return true;
}
</script>

<script>

$(document).ready(function () {
	$("div#typOfVehB").hide();
	$("#prf_group").val("0");
});

$(function(){
	

	$("#act_nomen").keyup(function(){
		var actMain = this.value;
	 	var mctMainAuto=$("#act_nomen");
	  	mctMainAuto.autocomplete({
	  	source: function( request, response ) {
	        $.ajax({
	        	type: 'POST',
			    url: "getActNomenList?"+key+"="+value,
	        	data: {actMain:actMain},
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
	        	alert("Please Enter Approved ACT Nomenclature.");
	        	$("#actSlotId").text("");
 	        	$("#act_slot_id").val("");
 	        	$("#make_no").val("");
	        	mctMainAuto.val("");	        	  
	        	mctMainAuto.focus();
	        	return false;	             
	    	}   	         
	    },	    
		    select: function( event, ui ) {
		      	var act_nomen = ui.item.value;
		      	document.getElementById("act_main_id").value = "";
						
						$.post("getaddactnoactNomenToNoList?"+key+"="+value, {
							act_nomen:act_nomen
						}).done(function(j) {
							var length = j.length-1;
					          var enc = j[length][0].substring(0,16);
					          $("#act_main_id").val(dec(enc,j[0][0]));
					          $("#abc_group").val(dec(enc,j[0][1]));
					         
				        		var act_slot_id =dec(enc,j[0][0]);
				        		getMake(dec(enc,j[0][0]));
						}).fail(function(xhr, textStatus, errorThrown) {
						});
			} 
			     
		});
	});
	
	 $("#sponsoring_dte").keypress(function(){
			var sponsoring_dte = this.value;
		 	var sponsoring_dteAuto=$("#sponsoring_dte");
		  	sponsoring_dteAuto.autocomplete({
			  	source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
  				        url: "getNodalDteList?"+key+"="+value,
			        	data: {sponsoring_dte:sponsoring_dte},
			        	success: function( data ) {
			        		var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0)
				        		{
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
			        	alert("Please Enter valid Nodal Dte.");
			        	document.getElementById("sponsoring_dte").value="";
			        	sponsoring_dteAuto.val("");	        	  
			        	sponsoring_dteAuto.focus();
			        	return false;	             
			    	}   	         
			    }, 
			    select: function( event, ui ) {
			      	var ReName = ui.item.value;
					
				} 	     
			});
		}); 
	
	 $("select#prf_group").change(function() {
			var code = $(this).find('option:selected').attr("name");    
			$("#veh_class_code").val(code);
	 });
	
});

function getMake(act_slot_id){
	$("#desc_make").keyup(function(){
		var desc_make = this.value;
	 	var mctMakeAuto=$("#desc_make");
	  	mctMakeAuto.autocomplete({
		  	source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
				    url: "getactNomenMakeList?"+key+"="+value,
		        	data: {act_slot_id:act_slot_id,desc_make:desc_make},
		        	success: function( data ) {
		    		var unitval = [];
	        	  var length = data.length-1;
	        		if(data.length != 0){
		        		var enc = data[length].substring(0,16);
		        	}
		        	for(var i = 0;i<data.length;i++){
		        		unitval.push(dec(enc,data[i]));
		        	}
		        	response( unitval ); 
		        	}
		    	});
		    },
		    minLength: 1,
		    autoFill: true,
		  	change: function(event, ui) {
		    	if (ui.item) {   	        	  
		          return true;    	            
		        } else {
		        	alert("Please Enter Approve ACT Make");
		        	$("#make_no").val("");
	        		mctMakeAuto.val("");	        	  
		        	mctMakeAuto.focus();
		        	return false;	             
		    	}   	         
		    }, 
		    select: function( event, ui ) {
		      	var description = ui.item.value;
		      	document.getElementById("make_no").value = "";
				var act_main_id = document.getElementById("act_main_id").value;			    
			 
				$.post("getActNomenMakeToNoList?"+key+"="+value, {
					act_main_id:act_main_id,description:description
				}).done(function(j) {
					var length = j.length-1;
			    	var enc = j[length].substring(0,16);
			    	$("#make_no").val(dec(enc,j[0]));
			   	 	getModel(act_main_id,dec(enc,j[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});
		    } 	     
		});
	});
}

function getModel(act_main_id,make_id) {

	$("#desc_model").keyup(function(){
		var desc_model = this.value;
	 	var actModelAuto=$("#desc_model");
	  	actModelAuto.autocomplete({
		  	source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
				    url: "getactNomenModelList?"+key+"="+value,
		        	data: {act_main_id : act_main_id,make_id : make_id,desc_model:desc_model},
		        	success: function( data ) {
		        		var unitval = [];
			        	  var length = data.length-1;
		        	  	if(data != null){
		        	  		var enc = data[length].substring(0,16);
		        	  	}
			        	for(var i = 0;i<data.length;i++){
			        		unitval.push(dec(enc,data[i]));
			        	}
				        response( unitval ); 
		        	}
		    	});
		    },
		    minLength: 1,
		    autoFill: true,
		  	change: function(event, ui) {
		    	if (ui.item) {   	        	  
		          return true;    	            
		        } else {
		        	alert("Please Enter Approve act Model");
		        	$("#model_id").val("");
	        		actModelAuto.val("");	        	  
		        	actModelAuto.focus();
		        	return false;	             
		    	}   	         
		    }, 
		    select: function( event, ui ) {
				var description = ui.item.value;
				document.getElementById("model_id").value = "";
				var act_main_id = document.getElementById("act_main_id").value;
				var make_no = document.getElementById("make_no").value;			
			
				$.post("getactNomenModelToNoList?"+key+"="+value, {
					act_main_id:act_main_id,make_no:make_no,description:description
				}).done(function(j) {
					var length = j.length-1;
				   	var enc = j[length].substring(0,16);
				   	$("#model_id").val(dec(enc,j[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});
		    } 	     
		});
	});
}

function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
}

function functTypeOfVehChange()
{

	if($("#type_of_vehicle").val() == "B")
	{
	 	$("div#typOfVehB").show();
	}
	else
	{
		$("div#typOfVehB").hide();
	}
	
}
 function tuypeofwheel(obj)
{
	 
	 if(obj.value == 1)
	{
		 if($("#type_of_vehicle").val() == "A" || $("#type_of_vehicle").val() == "C")
			 
			{			 
			  	var option = '<option value="2">2</option>';
             	$("select#no_of_wheels").html(option);
			}
		 else
			 {
			  var option = '<option value="0">--Select--</option>'+ 
              	'<option value="2">2</option>'+
              	'<option value="4">4</option>'+
              	'<option value="6">6</option>'+
          		'<option value="8">8</option>'+
          		'<option value="10">10</option>'+
          		'<option value="12">12</option>';
          		$("select#no_of_wheels").html(option);
          	}
	}
	 else 
		 {
			 var option = '<option value="0">--Select--</option>'+ 
	       	'<option value="2">2</option>'+
	       	'<option value="4">4</option>'+
	       	'<option value="6">6</option>'+
	   		'<option value="8">8</option>'+
	   		'<option value="10">10</option>'+
	   		'<option value="12">12</option>';
	   		$("select#no_of_wheels").html(option);
			
		 }
} 
</script>
