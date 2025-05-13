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

<form:form name="add_mct_no" id="add_mct_no" action="add_mct_noAction" method="post" class="form-horizontal" commandName="add_mct_noCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>MCT NO CREATION</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>MCT Nomenclature</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="mct_nomen" name="mct_nomen"  placeholder=""  class="form-control autocomplete" autocomplete="off" maxlength="100"> 
						                </div>
						            </div>
	              				</div>
	              				
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
		                				<div class="col-md-4">
		                  					  <label class=" form-control-label">MCT </label>
		                				 </div>
		                				 <div class="col-md-8">
		                  					  <input type="text" id="mct_main_id" name="mct_main_id" maxlength="4" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly">
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Model Nomenclature</label>
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
							                  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Veh </label>
							             </div>
							             <div class="col-md-8">
							             		<input  name="type_of_vehicle" id="type_of_vehicle" class=" form-control"  maxlength="1" readonly="readonly" >
							                  <!-- <select name="type_of_vehicle" id="type_of_vehicle" class=" form-control" onchange="functTypeOfVehChange(this);" readonly="readonly" >
							                          <option selected disabled value="">--Select--</option>
												      <option value="A">A Vehicle</option>
										              <option value="B">B Vehicle</option>
										              <option value="C">C Vehicle</option>
											  </select> -->
							             </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">Veh Cl Code</label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="veh_class_code" name="veh_class_code" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="2" readonly="readonly">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
							              <div class="col-md-4">
							                  <label class=" form-control-label"><strong style="color: red;">* </strong>Purpose of Veh</label>
							             </div>
							             <div class="col-md-8">
							                  <select name="purpose_of_vehicle" id="purpose_of_vehicle" class=" form-control">
							                          <option selected disabled>--Select--</option>
												      <option value="0">GS</option>
										              <option value="1">SPECIAL</option>
											  </select>
							             </div>
						            </div>
	              				</div>
	              			</div>
	              			 		 	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                    <label class=" form-control-label">No of Bogie wheel</label>
						                </div>
						                <div class="col-md-8">
						                    
						                   <select name="no_of_bogie_wheel" id="no_of_bogie_wheel" class=" form-control"> 
							                 
							                          <option selected disabled>--Select--</option>
												      <option value="0">6</option>
										              <option value="1">8</option>
										              <option value="2">12&above</option>
											  </select> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
							              <div class="col-md-4">
							                  <label class=" form-control-label"><strong style="color: red;">* </strong> Wheel/Track</label>
							             </div>
							             <div class="col-md-8">
							               <select name="wheel_track" id="wheel_track" class=" form-control" onchange="tuypeofwheel(this);"> 
							                 		  <option selected disabled>--Select--</option>
												      <option value="0">Wheel</option>
										              <option value="1">Track</option>
											  </select> 
											 
							             </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <div class="row form-group">
							              <div class="col-md-4">
							                  <label class=" form-control-label">No of Wheels/Tr<br><span style="font-size: 10px;font-size:12px;color:red">(Excluding Spare)</span></label>
							             </div>
							             <div class="col-md-8">
							             	<select id="no_of_wheels" name="no_of_wheels" class="form-control"  >
						                  	  <!--   <option value="0">--Select--</option> 
			                                    <option value="2">2</option>
			                                    <option value="4">4</option>
			                                    <option value="6">6</option>
			                                	<option value="8">8</option>
			                                	<option value="10">10</option>
			                                	<option value="12">12</option>	 -->
			                                </select>
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						                 <div class="col-md-4">
						                  <strong style="color: red;">* </strong>  <label class=" form-control-label">Auth</label>
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
							                 <label class=" form-control-label">No of Axles</label>
							             </div>
							            <div class="col-md-8">
								            <select id="no_of_axles" name="no_of_axles" class="form-control">
												<option value="0">--Select--</option>
												<option value="2">2</option>
												<option value="4">4</option>
												<option value="6">6 and Above</option>
												
											</select>
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					 <div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label">Axle Weight</label>
	                					</div>
	                					<div class="col-md-8">
	                					      <input type="text" id="axle_wts" name="axle_wts" maxlength="5" class="form-control" placeholder="Max Five Character" autocomplete="off">
	                					</div>
              						</div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <div class="row form-group">
							              <div class="col-md-4">
							                 <label class=" form-control-label">Tonnage<br><span style="font-size: 10px;font-size:12px;color:red">(In Tons)</span></label>
							             </div>
							             <div class="col-md-8">
							             	  <input type="text" id="tonnage" name="tonnage" maxlength="5" onkeypress="return isNumber0_9Only(event);" class="form-control" value="0"  placeholder="Max Five Digits Integer Value">
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label">Drive</label>
	                					</div>
	                					<div class="col-md-8">
	                					     <select id="drive" name="drive" class="form-control">
						                  	     <option value="">--Select--</option> 
			                                     <option value="1">4x2</option>
			                                     <option value="2">4x4</option>
			                                     <option value="3">6x6</option>
			                                     <option value="4">8x8</option>
			                                     <option value="5">10x10</option>
			                                     <option value="6">12x12</option>
					                         </select>
	                					</div>
              						</div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
							              <div class="col-md-4">
							                 <label class=" form-control-label">Towing Capacity<br><span style="font-size: 10px;font-size:12px;color:red">(In CC)</span></label>
							             </div>
							             <div class="col-md-8">
							             	  <input type="text" id="towing_capacity" name="towing_capacity" maxlength="5" class="form-control" onkeypress="return isNumber0_9Only(event);" value="0" placeholder="Max Five Digits Integer Value">
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
	                  						<label class=" form-control-label">Nodal Dte</label>
	                					</div>
	                					<div class="col-md-8">
	                					    <input type="text" id="sponsoring_dte" name="sponsoring_dte" class="form-control autocomplete" autocomplete="off" maxlength="100">
	                					</div>
              						</div>
	              				</div>
	              			</div>
	              			<div id="typOfVehB">
	              				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
							             <div class="col-md-4">
							                 <label class=" form-control-label">Yr of Service (FF)</label>
							             </div>
							             <div class="col-md-8">
											<input type="number" id="yr_service_ff" name="yr_service_ff" maxlength="5" class="form-control" onkeypress="return isNumber0_9Only(event);" value="0" >
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label">Yr of Service (NFF)</label>
	                					</div>
	                					<div class="col-md-8">
	                					    <input type="number" id="yr_service_nff" name="yr_service_nff" maxlength="5" class="form-control" onkeypress="return isNumber0_9Only(event);" value="0" >
	                					</div>
              						</div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
							             <div class="col-md-4">
							                 <label class=" form-control-label">Km for FF</label>
							             </div>
							             <div class="col-md-8">
											<input type="number" id="km_ff" name="km_ff"  class="form-control" onkeypress="return isNumber0_9Only(event);" value="0" maxlength="10">
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					 <div class="col-md-4">
	                  						<label class=" form-control-label">Km for NFF</label>
	                					</div>
	                					<div class="col-md-8">
	                					    <input type="number" id="km_nff" name="km_nff"  class="form-control" onkeypress="return isNumber0_9Only(event);" value="0" maxlength="10">
	                					</div>
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
	if($("#mct_nomen").val() == ""){
		alert("Please Enter MCT Nomenclature.");
		return false;
	}  
	
	if($("#mct_main_id").val() == ""){
		alert("Please Enter the MCT No.");
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
	
	if($("#desc_model").val() == ""){
		alert("Please Enter MODEL Nomenclature. ");
		return false;
	}  
	
	if($("#model_id").val() == ""){
		alert("Please Enter the MODEL No.");
		return false;
	}  
	
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
	
	$("#mct_nomen").keyup(function(){
		var mctMain = this.value;
	 	var mctMainAuto=$("#mct_nomen");
	  	mctMainAuto.autocomplete({
		  	source: function( request, response ) {
		        $.ajax({		        	
		        	type: 'POST',
				    url: "getMctNomenList?"+key+"="+value,
		        	data: {mctMain:mctMain},
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
		        	alert("Please Enter Approve MCT Main");
		        	$("#mct_main_id").val("");
	        		$("input#prf_group").val("");
	        		$("#veh_class_code").val("");
		        	mctMainAuto.val("");	        	  
		        	mctMainAuto.focus();
		        	return false;	             
		    	}   	         
		    }, 
		    select: function( event, ui ) {
		      	var mct_nomen = ui.item.value;
		      	document.getElementById("mct_main_id").value = "";
						
						$.post("getaddmctnoMctNomenToNoList?"+key+"="+value, {
							mct_nomen:mct_nomen
						}).done(function(j) {
							var length = j.length-1;
					          var enc = j[length][0].substring(0,16);
					          $("#mct_main_id").val(dec(enc,j[0][0]));
					          $("#prf_group").val(dec(enc,j[0][1]));
					          $("#veh_class_code").val(dec(enc,j[0][2]));
					          $("#type_of_vehicle").val(dec(enc,j[0][3])); // new add
					         
				        		var mct_slot_id =dec(enc,j[0][0]);
				        		getMake(dec(enc,j[0][0]));
				        		functTypeOfVehChange();
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

function getMake(mct_slot_id){

	$("#desc_make").keyup(function(){
		var desc_make = this.value;
	 	var mctMakeAuto=$("#desc_make");
	  	mctMakeAuto.autocomplete({
		  	source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
				    url: "getMctNomenMakeList?"+key+"="+value,
		        	data: {mct_slot_id:mct_slot_id,desc_make:desc_make},
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
		        	alert("Please Enter Approve MCT Make");
		        	$("#make_no").val("");
	        		mctMakeAuto.val("");	        	  
		        	mctMakeAuto.focus();
		        	return false;	             
		    	}   	         
		    }, 
		    select: function( event, ui ) {
		      	var description = ui.item.value;
		      	document.getElementById("make_no").value = "";
				var mct_main_id = document.getElementById("mct_main_id").value;			    
			 
				$.post("getMctNomenMakeToNoList?"+key+"="+value, {
					mct_main_id:mct_main_id,description:description
				}).done(function(j) {
					var length = j.length-1;
			    	var enc = j[length].substring(0,16);
			    	$("#make_no").val(dec(enc,j[0]));
			   	 	getModel(mct_main_id,dec(enc,j[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});
		    } 	     
		});
	});
}

function getModel(mct_main_id,make_id) {

	$("#desc_model").keyup(function(){
		var desc_model = this.value;
	 	var mctModelAuto=$("#desc_model");
	  	mctModelAuto.autocomplete({
		  	source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
				    url: "getMctNomenModelList?"+key+"="+value,
		        	data: {mct_main_id : mct_main_id,make_id : make_id,desc_model:desc_model},
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
		        	alert("Please Enter Approve MCT Model");
		        	$("#model_id").val("");
	        		mctModelAuto.val("");	        	  
		        	mctModelAuto.focus();
		        	return false;	             
		    	}   	         
		    }, 
		    select: function( event, ui ) {
				var description = ui.item.value;
				document.getElementById("model_id").value = "";
				var mct_main_id = document.getElementById("mct_main_id").value;
				var make_no = document.getElementById("make_no").value;			
			
				$.post("getMctNomenModelToNoList?"+key+"="+value, {
					mct_main_id:mct_main_id,make_no:make_no,description:description
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
