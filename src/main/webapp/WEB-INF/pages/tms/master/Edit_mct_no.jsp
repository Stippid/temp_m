<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form:form name="Update_mct_no" id="Update_mct_no" action="updateMctNoAction" method="post" class="form-horizontal" commandName="Update_mct_noCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header" ><h5>UPDATE MCT NO<br></h5><h6><span style="font-size: 10px;font-size:12px;color:red">(To be update by MISO)</span></h6></div>
	          		<div class="card-header" style="text-align: center;"> <strong>Basic Details</strong> </div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <input type="hidden" id="id" name="id" value="${Update_mct_noCMD.id}"/>
	              					 	<div class="row form-group">
							               <div class="col-md-4">
							                    <label class=" form-control-label"><strong style="color: red;">* </strong>(MCT) Nomenclature </label>
							                </div>
							                <div class="col-md-8">
							                     <input type="text" id="mct_nomen" name="mct_nomen" value="${mct}" class="form-control autocomplete" autocomplete="off" maxlength="100">
							                </div>
							            </div>
	              				</div>
	              				<div class="col-md-6">
	              					 <div class="row form-group">
		                				 <div class="col-md-4">
		                  					  <label class=" form-control-label"><strong style="color: red;">* </strong>MCT  </label>
		                				 </div>
		                				 <div class="col-md-8">
		                  					  <input type="text" id="mct_main_id" name="mct_main_id" value="${mct_id}" class="form-control autocomplete" autocomplete="off" readonly="readonly" maxlength="4">
		                				 </div>
              					    </div>
	              				</div>
	              			</div>
	            			
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>(Make) Nomenclature </label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="desc_make" name="desc_make" value="${make}" class="form-control autocomplete" autocomplete="off" maxlength="100">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Make </label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input type="text" id="make_no" name="make_no" value="${make_id}" class="form-control autocomplete" autocomplete="off" readonly="readonly" maxlength="3">
	                					</div>
	              					</div>
	              				</div>
	              			</div>
	            			
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						                <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>(Model) Nomenclature </label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="desc_model" name="desc_model" value="${model}" class="form-control autocomplete" autocomplete="off" maxlength="100">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Model No</label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input type="text" id="model_id" name="model_id" value="${model_id}" class="form-control autocomplete" autocomplete="off" readonly="readonly" maxlength="3">
	                					</div>
	              					</div>
	              				</div>
	              			</div>
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label">(PRF) Nomenclature </label>
	                					</div>
	                					<div class="col-md-8">
	                  						<select name="prf_group" class="form-control" id="prf_group" >
	                  							<option value="0">--Select--</option>	
												<c:forEach var="item" items="${getPrfGroupName}" varStatus="num" >
													<option value="${item.prf_code}" name="${item.vehicle_class_code}">${item.group_name}</option>
												</c:forEach>
	                  						</select>
	                					</div>
	              					</div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
							            <div class="col-md-4">
							                  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Vehicle </label>
							             </div>
							             <div class="col-md-8">
							                  <select name="type_of_vehicle" id="type_of_vehicle" class=" form-control" >
						                          <option value="">--Select the Value--</option>
											      <option value="A">A Vehicle</option>
									              <option value="B">B Vehicle</option>
									              <option value="C">C Vehicle</option>
											  </select>
							             </div>
						            </div>
	              				</div>
	              			</div>
	            			
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Vehicle Cl Code </label>
						                </div>
						                <div class="col-md-8">
						                     <input type="text" id="veh_class_code" name="veh_class_code" value="${Update_mct_noCMD.veh_class_code}"  class="form-control" readonly="readonly" maxlength="2">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					 <div class="row form-group">
							             <div class="col-md-4">
							                  <label class=" form-control-label"><strong style="color: red;">* </strong>Purpose of Vehicle </label>
							             </div>
							             <div class="col-md-8">
							                  <select name="purpose_of_vehicle" id="purpose_of_vehicle" class=" form-control" >
							                          <option selected disabled>--Select the Value--</option>
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
							                  <label class=" form-control-label">No of Wheels </label>
							             </div>
							             <div class="col-md-8">
							             	<select id="no_of_wheels" name="no_of_wheels" class="form-control">
						                  	    <option value="0">--Select--</option> 
			                                    <option value="2">2</option>
			                                    <option value="4">4</option>
			                                    <option value="6">6</option>
			                                	<option value="8">8</option>
			                                	<option value="10">10</option>
			                                	<option value="12">12</option>	
			                                </select>
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">	
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label">Auth Letter No </label>
						                </div>
						               <div class="col-md-8">
						                     <input type="text" id="auth_letter_no" name="auth_letter_no" value="${Update_mct_noCMD.auth_letter_no}" maxlength="45" class="form-control autocomplete" autocomplete="off">
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
	                					      <input type="text" id="axle_wts" name="axle_wts" value="${Update_mct_noCMD.axle_wts}" maxlength="5" class="form-control" placeholder="Max Five Character">
	                					</div>
              						</div>
	              				</div>
	              			</div>
	            			
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
							             <div class="col-md-4">
							                 <label class=" form-control-label">Tonnage</label>
							             </div>
							             <div class="col-md-8">
							             	  <input type="text" id="tonnage" name="tonnage" value="${Update_mct_noCMD.tonnage}" maxlength="5" onkeypress="return isNumber0_9Only(event);" class="form-control" placeholder="Max Five Digits Integer Value">
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
							                 <label class=" form-control-label">Towing Capacity </label>
							             </div>
							             <div class="col-md-8">
							             	  <input type="text" id="towing_capacity" name="towing_capacity" maxlength="5" value="${Update_mct_noCMD.towing_capacity}" class="form-control" onkeypress="return isNumber0_9Only(event);" placeholder="Max Five Digits Integer Value">
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label">Type of Fuel </label>
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
							                 <label class=" form-control-label">Lift Capacity </label>
							             </div>
							            <div class="col-md-8">
											<input type="text" id="lift_capacity" name="lift_capacity" maxlength="5" value="${Update_mct_noCMD.lift_capacity}" class="form-control" onkeypress="return isNumber0_9Only(event);" placeholder="Max Five Digits Integer Value">
							            </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label">Nodal DTE </label>
	                					</div>
	                					<div class="col-md-8">
	                					    <input type="text" id="sponsoring_dte" name="sponsoring_dte" value="${Update_mct_noCMD.sponsoring_dte}" class="form-control autocomplete" autocomplete="off" maxlength="100">
	                					</div>
              						</div>
	              				</div>
	              			</div>
	              			
	              		<div id="Discard_Condition" class="tabcontent" style="display: block;">
						<div class="card" >
						
					<div align="center">Discard Condition</div>
							<div class="card_body">
								<table id="discard_table"
									class="table-bordered watermarked"
									style="width: -webkit-fill-available;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th>Sr No</th>
											<th>Force Type</th>
											<th>Vintage</th>
											<th>KM</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id="discard_condi_tbody"
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<tr id="tr_discard_condi1">
											<td class="dis_con_srno">1</td>
											<td><select id="force_type1" name="force_type1"
												class="form-control autocomplete" ">
													<option value="-1">--Select--</option>
													<c:forEach var="item" items="${getForceTypeList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
											</select></td>
											<td><input type="text" id="vintage1" name="vintage1" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only1(event)"></td>
											<td><input type="text" id="km1" name="km1" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only(event)"></td>
											<td style="display:none;"><input type="hidden" id="dis_con_ch_id1" name="dis_con_ch_id1" 
											 value="0" class="form-control autocomplete" autocomplete="off" ></td>

											<td><a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="discard_condition_save1" onclick="discard_condition_save_fn(1);"><i
													class="fa fa-save"></i></a> <a style="display: none;"
												class="btn btn-success btn-sm" value="ADD" title="ADD" id="discard_condition_add1" onclick="discard_condition_add_fn(1);"><i
													class="fa fa-plus"></i></a> <a style="display: none;"
												class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" 	id="discard_condition_remove1" onclick="discard_condition_remove_fn(1);"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>


						<div class="card-footer" id="bk_id" align="center" class="form-control" style="display: none;">
							<input type="button" class="btn btn-info btn-sm" onclick="Cancel();" value="Back">
						</div>
					</div>
					
		<div id="Discard_Condition_oh_mr" class="tabcontent" style="display: block;">
						<div class="card" style="margin-top: 20px;">
						<div align="center">OH/MR Details</div>
							<div class="card_body">
								<table id="discard_table_oh_mr"
									class="table-bordered watermarked"
									style="width: -webkit-fill-available;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th>Sr No</th>
											<th>OH/MR</th>
											<th>Vintage</th>
											<th>KM</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id="discard_condi_tbody_oh_mr"
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<tr id="tr_discard_condi_oh_mr1">
											<td class="dis_con_oh_mr_srno">1</td>
											<td><select id="oh_mr1" name="oh_mr1"
												class="form-control autocomplete" ">
													<option value="0">--Select--</option>
													<option value="1">MR1</option>
													<option value="2">OH1</option>
													<option value="3">MR2</option>
													<option value="4">OH2</option>
													<option value="5">MR3</option>
													<option value="6">OH3</option>
												</select>
											</td>
											<td><input type="text" id="vintage_oh_mr1" name="vintage_oh_mr1" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only1(event)"></td>
											<td><input type="text" id="km_oh_mr1" name="km_oh_mr1" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only(event)"></td>
											<td style="display:none;"><input type="hidden" id="dis_con_oh_mr_ch_id1" name="dis_con_oh_mr_ch_id1" 
											 value="0" class="form-control autocomplete" autocomplete="off" ></td>

											<td><a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="discard_condition_oh_mr_save1" onclick="discard_condition_save_oh_mr_fn(1);"><i
													class="fa fa-save"></i></a> <a style="display: none;"
												class="btn btn-success btn-sm" value="ADD" title="ADD" id="discard_condition_oh_mr_add1" onclick="discard_condition_oh_mr_add_fn(1);"><i
													class="fa fa-plus"></i></a> <a style="display: none;"
												class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="discard_condition_oh_mr_remove1" onclick="discard_condition_oh_mr_remove_fn(1);"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>


						<div class="card-footer" id="bk_id" align="center" class="form-control" style="display: none;">
							<input type="button" class="btn btn-info btn-sm" onclick="Cancel();" value="Back">
						</div>
					</div>		
	              			
	              			
	              			
	              			
	              			
		       			</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();"> 
		            </div> 		
	        	</div>
			</div>
	</div>
</form:form>

<script>
function validate(){    
	if($("#mct_nomen").val() == ""){
		alert("Please Enter the Nomeclature MCT.");
		return false;
	}  
	
	if($("#mct_main_id").val() == ""){
		alert("Please Enter the MCT No.");
		return false;
	}  
	
	if($("#desc_make").val() == ""){
		alert("Please Enter the Nomeclature MAKE.");
		return false;
	}  
	
	if($("#make_no").val() == ""){
		alert("Please Enter the MAKE No.");
		return false;
	}  
	
	if($("#desc_model").val() == ""){
		alert("Please Enter the Nomeclature MODEL.");
		return false;
	}  
	
	if($("#model_id").val() == ""){
		alert("Please Enter the MODEL No.");
		return false;
	}  
	
	if($("#prf_group").val() == "0"){
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
		alert("Please Enter the Auth Letter No.");
		return false;
	}
	return true;
}
</script>

<script>
$(function(){
	
	 $('#type_of_vehicle option[value="${Update_mct_noCMD.type_of_vehicle}"]').attr("selected", "selected");
	 $('#purpose_of_vehicle option[value="${Update_mct_noCMD.purpose_of_vehicle}"]').attr("selected", "selected");
	 $('#no_of_wheels option[value="${Update_mct_noCMD.no_of_wheels}"]').attr("selected", "selected");
	 $('#drive option[value="${Update_mct_noCMD.drive}"]').attr("selected", "selected");
	 $('#type_fuel option[value="${Update_mct_noCMD.type_fuel}"]').attr("selected", "selected");
	 $('#prf_group option[value="${Update_mct_noCMD.prf_group}"]').attr("selected", "selected");
	 $('#no_of_axles option[value="${Update_mct_noCMD.no_of_axles}"]').attr("selected", "selected");
	 
	 $("select#prf_group").change(function() {
		var code = $(this).find('option:selected').attr("name");    
		$("#veh_class_code").val(code);
	 });
	 get_discard_condition_details();
	 get_discard_condition_details_oh_mr();
});
function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
}

function isNumber0_9Only1(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
		 return false;
	}

	 if (event.target.value.length >= 2) {
	        return false; // Prevent further input
	    }
    return true;
}

 
</script>

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
		alert("Please Enter the Make No.");
		return false;
	}  
	
	if($("#desc_model").val() == ""){
		alert("Please Enter MODEL Nomenclature. ");
		return false;
	}  
	
	if($("#model_id").val() == ""){
		alert("Please Enter the Model No.");
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
<<script type="text/javascript">
dis_con=1;
dis_con_srno=1;
function discard_condition_add_fn(q){
	 dis_con=q+1;
	
	 if( $('#discard_condition_add'+q).length )         
	 {
			$("#discard_condition_add"+q).hide();
	 }
	
	 
	 if(q!=0)
		 dis_con_srno+=1;
	
	$("table#discard_table").append('<tr id="tr_discard_condi'+dis_con+'">'
			+'<td class="dis_con_srno">'+dis_con+'</td>'
			+'<td>'
			+'<select  id="force_type'+dis_con+'" name="force_type'+dis_con+'"  class="form-control autocomplete"  >'
			+' <option value="-1">--Select--</option>'
		   +'<c:forEach var="item" items="${getForceTypeList}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			+'</c:forEach>'
			+'</select>'
			+'</td>'	
			+'<td>'
			+'<input type="text" id="vintage'+dis_con+'" name="vintage'+dis_con+'" class="form-control autocomplete " autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only1(event)"></td>'
			+'<td>'
			+'<input type="text" id="km'+dis_con+'" name="km'+dis_con+'" class="form-control autocomplete " autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only(event)"></td>'
			+'<td style="display:none;"><input type="hidden" id="dis_con_ch_id'+dis_con+'" name="dis_con_ch_id'+dis_con+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			
			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "discard_condition_save'+dis_con+'" onclick="discard_condition_save_fn('+dis_con+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "discard_condition_add'+dis_con+'" onclick="discard_condition_add_fn('+dis_con+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "discard_condition_remove'+dis_con+'" onclick="discard_condition_remove_fn('+dis_con+');"><i class="fa fa-trash"></i></a>' 
			+'</td></tr>');
	

}


function discard_condition_save_fn(qs){


			var force_type = $('#force_type' + qs).val();
			var vintage = $('#vintage' + qs).val();
			var km = $('#km' + qs).val();
			var dis_con_ch_id = $('#dis_con_ch_id' + qs).val();
			var mct_main_id = $("#mct_main_id").val();
			
	 var r =('${roleAccess}');
	 if (force_type == "-1") {
			alert("Please Select Force Type");
			$("select#force_type" + ps).focus();
			return false;
		}
		if (vintage == "") {
			alert("Please Enter Vintage");
			$("select#vintage" + ps).focus();
			return false;
		}
		if (km == "") {
			alert("Please Enter Km");
			$("select#km" + ps).focus();
			return false;
		}

		if ($("#mct_slot").val() == "0") {
			alert("Please Select PRF Nomenclature ");
			$("#mct_slot").focus();
			return false;
		}


		
		
		
		$.post('discard_conditionAction?' + key + "=" + value, {
			force_type : force_type,
			vintage : vintage,
			km : km,
			dis_con_ch_id : dis_con_ch_id,
			mct_main_id :mct_main_id

		}, function(data) {

			var ids = data.split(",");
			if (data == "update") {
				alert("Data Updated SuccessFully");
//				getFieldService();
			}

			else if (parseInt(ids[0]) > 0) {

				$("#dis_con_ch_id" + qs).val(ids[0]);
				// 			        	 $('#p_id').val(ids[1]);
				$("#discard_condition_add" + qs).show();
				$("#discard_condition_remove" + qs).show();
				alert("Data Saved SuccessFully");
				// 			        	  getFieldService();
			} else
				alert(data)
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}


function discard_condition_remove_fn(R){
		var rc = confirm("Are You Sure! You Want To Delete");
		 if(rc){
		 var dis_con_ch_id=$('#dis_con_ch_id'+R).val();
		  $.post('discard_condition_delete_action?' + key + "=" + value, {dis_con_ch_id:dis_con_ch_id }, function(data){ 
				   if(data=='1'){
						 $("tr#tr_discard_condi"+R).remove(); 
								 if(R==dis_con){
									 R = R-1;
									 var temp=true;
									 for(i=R;i>=1;i--){
									 if( $('#discard_condition_add'+i).length )         
									 {
										 $("#discard_condition_add"+i).show();
										 temp=false;
										 lang=i;
										 break;
									 }}
							 
								 if(temp){
									 discard_condition_add_fn(0);
									}
				  			 }
								 $('.dis_con_srno').each(function(i, obj) {
									 
										obj.innerHTML=i+1;
										dis_con_srno=i+1;
										 });
								 alert("Data Deleted Successfully");
				   }
					 else{
						 alert("Data not Deleted ");
					 }
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
		 }
	}


function get_discard_condition_details(){
	 var mct_main_id=$('#mct_main_id').val(); 
//	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('discard_condition_getData?' + key + "=" + value, {mct_main_id:mct_main_id}, function(j){
			var v=j.length;
			if(v!=0){
		xpro=0;
		for(i;i<v;i++){
			
			xpro=xpro+1;
				if(xpro==1){
					$('#discard_condi_tbody').empty(); 
				}
//				var dauth=ParseDateColumncommission(j[i].date_of_authority);
//alert(j[i].force_type);
				$("table#discard_table").append('<tr id="tr_discard_condi'+xpro+'">'
						+'<td class="dis_con_srno">'+xpro+'</td>'
			 			+'<td>'
			 			+'<select  id="force_type'+xpro+'" name="force_type'+xpro+'" value="'+j[i].force_type+'" class="form-control autocomplete"  >'
			 			+' <option value="-1">--Select--</option>'
			 		   +'<c:forEach var="item" items="${getForceTypeList}" varStatus="num">'
			 			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			 			+'</c:forEach>'
			 			+'</select>'
			 			+'</td>'	
			 			+'<td>'
			 			+'<input type="text" id="vintage'+xpro+'" name="vintage'+xpro+'" value="'+j[i].vintage+'" class="form-control autocomplete " autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only1(event)"></td>'
			 			+'<td>'
			 			+'<input type="text" id="km'+xpro+'" name="km'+xpro+'" value="'+j[i].km+'" class="form-control autocomplete " autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only(event)"></td>'
			 			+'<td style="display:none;"><input type="hidden" id="dis_con_ch_id'+xpro+'" name="dis_con_ch_id'+xpro+'" value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
			 			
			 			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "discard_condition_save'+xpro+'" onclick="discard_condition_save_fn('+xpro+');" ><i class="fa fa-save"></i></a>'
			 			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "discard_condition_add'+xpro+'" onclick="discard_condition_add_fn('+xpro+');" ><i class="fa fa-plus"></i></a>'
			 			+'<a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "discard_condition_remove'+xpro+'" onclick="discard_condition_remove_fn('+xpro+');"><i class="fa fa-trash"></i></a>' 
			 			+'</td></tr>');
				 $('#force_type'+xpro).val(j[i].force_type );
				
//				 onPro_exam(xpro);
			
		}
		if(xpro!=0){
			proex=xpro;
			proex_srno=xpro;
			$("#discard_condition_add"+xpro).show();
			
		}
	}
	  });
}
//-----------------------------oh_mr start------------------


dis_con_oh_mr=1;
dis_con_oh_mr_srno=1;
function discard_condition_oh_mr_add_fn(q){
dis_con_oh_mr=q+1;
	
	 if( $('#discard_condition_oh_mr_add'+q).length )         
	 {
			$("#discard_condition_oh_mr_add"+q).hide();
	 }
	
	 
	 if(q!=0)
		dis_con_oh_mr_srno+=1;
	
	$("table#discard_table_oh_mr").append('<tr id="tr_discard_condi_oh_mr'+dis_con_oh_mr+'">'
			+'<td class="dis_con_oh_mr_srno">'+dis_con_oh_mr+'</td>'
			+'<td>'
			+'<select id="oh_mr'+dis_con_oh_mr+'" name="oh_mr'+dis_con_oh_mr+'"class="form-control autocomplete"> <option value="0">--Select--</option>'
			+		'<option value="1">MR1</option>'
			+		'<option value="2">OH1</option>'
			+		'<option value="3">MR2</option>'
			+		'<option value="4">OH2</option>'
			+		'<option value="5">MR3</option>'
			+		'<option value="6">OH3</option>'
			+	'</select>'
			+'</td>'
			+'<td>'
			+'<input type="text" id="vintage_oh_mr'+dis_con_oh_mr+'" name="vintage_oh_mr'+dis_con_oh_mr+'" class="form-control autocomplete " autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only1(event)"></td>'
			+'<td>'
			+'<input type="text" id="km_oh_mr'+dis_con_oh_mr+'" name="km_oh_mr'+dis_con_oh_mr+'" class="form-control autocomplete " autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only(event)"></td>'
			+'<td style="display:none;"><input type="hidden" id="dis_con_oh_mr_ch_id'+dis_con_oh_mr+'" name="dis_con_oh_mr_ch_id'+dis_con_oh_mr+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			
			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "discard_condition_oh_mr_save'+dis_con_oh_mr+'" onclick="discard_condition_save_oh_mr_fn('+dis_con_oh_mr+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "discard_condition_oh_mr_add'+dis_con_oh_mr+'" onclick="discard_condition_oh_mr_add_fn('+dis_con_oh_mr+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "discard_condition_oh_mr_remove'+dis_con_oh_mr+'" onclick="discard_condition_oh_mr_remove_fn('+dis_con_oh_mr+');"><i class="fa fa-trash"></i></a>' 
			+'</td></tr>');
	

}


function discard_condition_save_oh_mr_fn(qs){

var oh_mr = $('#oh_mr' + qs).val();
var vintage_oh_mr = $('#vintage_oh_mr' + qs).val();
var km_oh_mr = $('#km_oh_mr' + qs).val();
var dis_con_oh_mr_ch_id = $('#dis_con_oh_mr_ch_id' + qs).val();
var mct_main_id = $("#mct_main_id").val();
		
	 var r =('${roleAccess}');
	
		if (oh_mr == "0") {
			alert("Please Select OH/MR");
			$("select#oh_mr" + ps).focus();
			return false;
		}
		if (vintage_oh_mr == "") {
			alert("Please Enter Vintage");
			$("select#vintage_oh_mr" + ps).focus();
			return false;
		}
		if (km_oh_mr == "") {
			alert("Please Enter Km");
			$("select#km_oh_mr" + ps).focus();
			return false;
		}

		if ($("#mct_slot").val() == "0") {
			alert("Please Select PRF Nomenclature ");
			$("#mct_slot").focus();
			return false;
		}
	
		$.post('discard_condition_oh_mrAction?' + key + "=" + value, {
			
			oh_mr : oh_mr,
			vintage_oh_mr : vintage_oh_mr,
			km_oh_mr : km_oh_mr,
			dis_con_oh_mr_ch_id : dis_con_oh_mr_ch_id,
			mct_main_id : mct_main_id

		}, function(data) {

			var ids = data.split(",");
			if (data == "update") {
				alert("Data Updated SuccessFully");
				// 				getFieldService();
			}

			else if (parseInt(ids[0]) > 0) {

				$("#dis_con_oh_mr_ch_id" + qs).val(ids[0]);
				// 			        	 $('#p_id').val(ids[1]);
				$("#discard_condition_oh_mr_add" + qs).show();
				$("#discard_condition_oh_mr_remove" + qs).show();
				alert("Data Saved SuccessFully");
				// 			        	  getFieldService();
			} else
				alert(data)
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	function discard_condition_oh_mr_remove_fn(R) {
		
		var rc = confirm("Are You Sure! You Want To Delete");
		if (rc) {
			var dis_con_oh_mr_ch_id = $('#dis_con_oh_mr_ch_id' + R).val();
			$
					.post(
							'discard_condition_delete_oh_mr_action?' + key
									+ "=" + value,
							{
								dis_con_oh_mr_ch_id : dis_con_oh_mr_ch_id
							},
							function(data) {
								if (data == '1') {
									$("tr#tr_discard_condi_oh_mr" + R).remove();
									if (R == dis_con_oh_mr) {
										R = R - 1;
										var temp = true;
										for (i = R; i >= 1; i--) {
											if ($('#discard_condition_oh_mr_add'
													+ i).length) {
												$(
														"#discard_condition_oh_mr_add"
																+ i).show();
												temp = false;
												lang = i;
												break;
											}
										}

										if (temp) {
											discard_condition_oh_mr_add_fn(0);
										}
									}
									$('.dis_con_oh_mr_srno').each(
											function(i, obj) {

												obj.innerHTML = i + 1;
												dis_con_srno = i + 1;
											});
									alert("Data Deleted Successfully");
								} else {
									alert("Data not Deleted ");
								}
							}).fail(function(xhr, textStatus, errorThrown) {
						alert("fail to fetch")
					});
		}
	}
	
	
	
	function get_discard_condition_details_oh_mr(){
	 var mct_main_id=$('#mct_main_id').val(); 
//	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('discard_condition_oh_mr_getData?' + key + "=" + value, {mct_main_id:mct_main_id}, function(j){
			var v=j.length;
			if(v!=0){
		xoh_mr=0;
		for(i;i<v;i++){
			
			xoh_mr =xoh_mr+1;
				if(xoh_mr==1){
					$('#discard_condi_tbody_oh_mr').empty(); 
				}

			$("table#discard_table_oh_mr").append('<tr id="tr_discard_condi_oh_mr'+xoh_mr+'">'
						+'<td class="dis_con_oh_mr_srno">'+xoh_mr+'</td>'
			 			+'<td>'
			+'<select id="oh_mr'+xoh_mr+'" name="oh_mr'+xoh_mr+'" value="'+j[i].oh_mr+'" class="form-control autocomplete"> '
			 +		'<option value="0">--Select--</option>'
			+		'<option value="1">MR1</option>'
			+		'<option value="2">OH1</option>'
			+		'<option value="3">MR2</option>'
			+		'<option value="4">OH2</option>'
			+		'<option value="5">MR3</option>'
			+		'<option value="6">OH3</option>'
			+	'</select>'
			+'</td>'
			+'<td>'
			+'<input type="text" id="vintage_oh_mr'+xoh_mr+'" name="vintage_oh_mr'+xoh_mr+'"  value="'+j[i].vintage+'" class="form-control autocomplete " autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only1(event)"></td>'
			+'<td>'
			+'<input type="text" id="km_oh_mr'+xoh_mr+'" name="km_oh_mr'+xoh_mr+'"  value="'+j[i].km+'" class="form-control autocomplete " autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only(event)"></td>'
			+'<td style="display:none;"><input type="hidden" id="dis_con_oh_mr_ch_id'+xoh_mr+'"   value="'+j[i].id+'"  name="dis_con_oh_mr_ch_id'+xoh_mr+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			
			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "discard_condition_oh_mr_save'+xoh_mr+'" onclick="discard_condition_save_oh_mr_fn('+xoh_mr+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "discard_condition_oh_mr_add'+xoh_mr+'" onclick="discard_condition_oh_mr_add_fn('+xoh_mr+');" ><i class="fa fa-plus"></i></a>'
			+'<a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "discard_condition_oh_mr_remove'+xoh_mr+'" onclick="discard_condition_oh_mr_remove_fn('+xoh_mr+');"><i class="fa fa-trash"></i></a>' 
			+'</td></tr>');

			 $('#oh_mr'+xoh_mr).val(j[i].oh_mr );
				
//				 onPro_exam(xoh_mr);
			
		}
		if(xoh_mr!=0){
			xoh_mr=xoh_mr;
			xoh_mr_srno=xoh_mr;
			$("#discard_condition_oh_mr_add"+xoh_mr).show();
		}
	}
	  });
}
</script>
