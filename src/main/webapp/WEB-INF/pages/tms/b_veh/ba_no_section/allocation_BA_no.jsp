<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/dropDown/select2.min.js"></script>
<link rel="stylesheet" href="js/dropDown/select2.min.css">
<script>
var $select2 = $('.select2').select2({
    containerCssClass: "wrap"
});
</script>

<form:form name="tmsbanumdir" id="tmsbanumdir" action="tmsbanumdirAct?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="tmsbanumdirCmd" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h5>REQUEST TO ALLOCATE BA / EM NUMBER<br></h5><h6><span style="font-size: 12px;color:red">(To be entered by Depot)</span></h6></div>
					<div class="card-header"><strong>Basic Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-6">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Army/Non-Army </label>
									</div>
									<div class=" col-md-6">
										<div class="form-check-inline form-check">
											<label for="inline-radio1" class="form-check-label "><input type="radio" id="ba_no_type1" name="ba_no_type" value="0" class="form-check-input" onchange="mcthideshow()" onclick="ClearField();">Army</label>&nbsp;&nbsp;&nbsp; 
										    <label for="inline-radio2" class="form-check-label "> <input type="radio" id="ba_no_type2" name="ba_no_type" value="1" class="form-check-input" onchange="mcthideshow()" onclick="ClearField();">Non-Army</label>&nbsp;&nbsp;&nbsp;
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Unit / Est </label>
									</div>																		
									<div class="col-md-6">
										<textarea id="requesting_agency" name="requesting_agency" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" maxlength="100"></textarea>
									     </div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No </label>
									</div>
									<div class="col-md-6">
										<input type="text" id="sus_no" name="sus_no" class="form-control"  autocomplete="off" maxlength="8">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Date </label>
									</div>
									<div class="col-md-6">
										<input type="date" id="dte_of_reqst" name="dte_of_reqst" class="form-control" value="${date}" readonly="readonly"> 
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-header"><strong>Vehicle Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Type of Vehicle </label>
									</div>
									<div class="col-12 col-md-7">
										<select name="veh_cat" id="veh_cat"  class="form-control-sm form-control" > 
										<!-- <select name="veh_cat" id="veh_cat" style="width: 100%;" class="select2 narrow wrap"> -->
								            <option value="0">--Select--</option>
								            <option value="A">A Vehicle</option>
								            <option value="B">B Vehicle</option>
								            <option value="C">C Vehicle</option>
								        </select>
									</div>
								</div>
							</div>								
						</div> 
						<div class="col-md-12">
						 	<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> <span style="color: red;" id="nonArmy">*</span> MCT</label>
									</div>
									<div class="col-md-7">
										<input type="text" id="mct_number1" name="mct_number1" onkeypress="return isNumber0_9Only(event);" placeholder="Max Ten Digits Integer Value" class="form-control autocomplete" autocomplete="off" maxlength="10">
										<input type="hidden" value =0 id="mct_number" name="mct_number">
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Nomenclature</label>
									</div>
									<div class=" col-md-7">
										<textarea id="new_nomencatre" name="new_nomencatre" class="form-control autocomplete"  autocomplete="off" style="font-size: 11px;" maxlength="300"></textarea>
									</div>
								</div>
							</div>
						</div>
 						<div class="col-md-12">
							 <div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Cl Code of Vehicle</label>
									</div>
									<div class="col-md-7">
										<input type="text" id="vehicle_clas_code" maxlength="1" name="vehicle_clas_code" onkeyup="this.value = this.value.toUpperCase();"  class="form-control" autocomplete="off" ><!-- onkeypress="return blockSpecialChar(event) -->
									</div>
								</div>
							</div> 
						 	<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Class of Vehicle</label> 
									</div>
									<div class="col-md-7">
										<input type="hidden" id="veh_class" name="veh_class"  class="form-control" value="1" readonly="readonly" maxlength="1">
										<input id="veh_class123" name="veh_class123"  class="form-control" value="I" readonly="readonly" maxlength="1"> 										
									</div>
								</div>
							</div> 
						</div>
						 <div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Type of Fuel</label>
									</div>
									<div class="col-md-7">
										<select id="type_of_fuel" name="type_of_fuel" class="form-control">
											<option value="1">Petrol</option>
				                            <option value="2">Diesel</option>
				                            <option value="3">Electric/Hybrid</option>
				                            <option value="4">CNG/PNG</option>	
										</select>
									</div>
								</div>
							</div>
							
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Wheel/Track</label>
									</div>
									<div class="col-md-7">
									<select id="wheel_track" name="wheel_track" class="form-control" onchange="typeofveh(this);">
									 		<option selected disabled>--Select--</option>
											<option value="0">Wheel</option>
				                            <option value="1">Track</option>
										</select>
									</div>
								</div>
							</div>
						</div> 
					 <div class="col-md-12">
						<div class="col-md-5">
								<div class="row form-group">
									<div class=" col-md-5">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Yr of Entry into Service</label>
									</div>
									<div class="col-md-7">
										<input id="year_of_entry" name="year_of_entry" class="form-control" value="${year}" readonly="readonly" />
									</div>
								</div>
							</div> 
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Country of Origin</label>
									</div>
									<div class="col-md-7">
										<input type="text" id="country_of_origin" name="country_of_origin" class="form-control" value="India" autocomplete="off" maxlength="10">
									</div>
								</div>
							</div>
						</div> 

						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> No of Wheels/Tr<br><span style="font-size: 8px;font-size:12px;color:red"">(Excluding Spare)</span></label>
									</div>
									<div class="col-md-7">

										<select id="no_of_wheels" name="no_of_wheels"
											class="form-control" maxlength="2">
											<option value="0">--Select--</option>
											<option value="2">2</option>
											<option value="4">4</option>
											<option value="6">6</option>
											<option value="8">8</option>
											<option value="10">10 & Above</option>
											
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> No of Axles</label>
									</div>
									<div class="col-md-7">
										<select id="no_of_axles" name="no_of_axles" class="form-control">
											<option value="0">--Select--</option>
											<option value="4">4</option>
											<option value="2">8</option>
											<option value="6">12 & Above</option>
										</select>																			
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Axle Weight<br><span style="font-size: 10px;font-size:12px;color:red">(In Kgs)</span></label>
									</div>
									<div class="col-md-7">
										<input type="text" id="axle_wts" name="axle_wts" maxlength="5" class="form-control" placeholder="Max Five Character" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Drive</label>
									</div>
									<div class="col-md-7">
										<select id="drive" name="drive" class="form-control">
											<option value="">--Select--</option>
											 <option value="1">4x2</option>
			                                     <option value="2">4x4</option>
			                                     <option value="4">8x8</option>
			                                     <option value="Tracked">Tracked</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Tonnage<br><span style="font-size: 10px;font-size:12px;color:red">(In Tons)</span></label>
									</div>
									<div class=" col-md-7">
										<input type="text" value="0" id="tonnage" name="tonnage" maxlength="5" onkeypress="return isNumber0_9Only(event);" class="form-control" placeholder="Max Five Digits Integer Value" autocomplete="off">
									</div>
								</div>
							</div>
							
							
									<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label">No of Bogie wheel </label>
									</div>
									<div class="col-md-7">
										  <select name="no_of_bogie_wheel" id="no_of_bogie_wheel" class=" form-control"> 
							                 
							                          <option selected disabled>--Select--</option>
												      <option value="0">6</option>
										              <option value="1">8</option>
										              <option value="2">12&above</option>
											  </select> 
									</div>
								</div>
							</div>
							
						</div>
						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Towing Capacity<br><span style="font-size: 10px;font-size:12px;color:red">(In CC)</span></label>
									</div>
									<div class=" col-md-7">
										<input type="text" id="towing_capcty" value="0" name="towing_capcty" maxlength="5" class="form-control" onkeypress="return isNumber0_9Only(event);" placeholder="Max Five Digits Integer Value" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Lift Capacity<br><span style="font-size: 10px;font-size:12px;color:red">(for Vehicle Mounted Crane/Cranes)</span></label>
									</div>
									<div class="col-md-7">
										<input type="text" id="lift_capcty" value="0" name="lift_capcty" maxlength="5" class="form-control" onkeypress="return isNumber0_9Only(event);" placeholder="Max Five Digits Integer Value" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Nodal Dte</label>
									</div>
									<div class="col-md-7">
										<input type="text" id="sponsoring_dte" name="sponsoring_dte" class="form-control autocomplete" autocomplete="off" maxlength="100">
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong> Supply Order No</label>
									</div>
									<div class="col-md-7">
										<input type="text" id="auth_letter_no" name="auth_letter_no" class="form-control" autocomplete="off" maxlength="100">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Initiating Auth</label>
									</div>
									<div class=" col-md-7">
										<input type="text" id="initiating_auth" name="initiating_auth" class="form-control" autocomplete="off" maxlength="20">
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Auth Date</label>
									</div>
									<div class="col-12 col-md-7">
										<input type="date" id="date_of_auth_letter" name="date_of_auth_letter" class="form-control" min='1899-01-01' max='${date}' >
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class=" col-md-5">
										<label class=" form-control-label"> Spl Eqpt Fitted<br><span style="font-size: 10px;font-size:12px;color:red">(If Any)</span></label>
									</div>
									<div class="col-md-7">
										<textarea raws="2" id="spl_eqpmnt_fitd" name="spl_eqpmnt_fitd" class="form-control" maxlength="50" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Purchase Cost in <i class="fa fa-inr"></i> <br> <span style="font-size: 10px;font-size:12px;color:red">(Rounded to Nearest Rs)</span></label>
									</div>
									<div class="col-md-7">
										<input type="text" id="purchas_cost" name="purchas_cost" class="form-control" onkeypress="return isNumber0_9Only(event);" autocomplete="off" maxlength="10">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
						
						</div>
						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Fornt View Image <br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed.)</span></label>
									</div>
									<div class="col-md-7">
										<input type="file" id="front_view_image1" name="front_view_image1" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Rear View Image<br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed.)</span></label>
									</div>
									<div class="col-md-7">
										<input type="file" id="back_view_image1" name="back_view_image1" class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Left Side View Image<br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed.)</span></label>
									</div>
									<div class="col-md-7">
										<input type="file" id="side_view_image1" name="side_view_image1" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Right Side View Image<br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed.)</span></label>
									</div>
									<div class="col-md-7">
										<input type="file" id="top_view_image1" name="top_view_image1" class="form-control" >
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Any Other Image<br><span style="font-size: 10px;font-size:12px;color:red">(Cabin/ Top/ etc. )</span></label>
									</div>
									<div class="col-md-7">
										<input type="file" id="" name="" class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> Type of Spl Eqpt/Role for which Veh will be used</label>
									</div>
									<div class="col-md-7">
										<textarea raws="2" id="typ_of_spl_eqpt_role" name="typ_of_spl_eqpt_role" class="form-control" maxlength="100" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Qty <span style="font-size: 10px;font-size:12px;color:red">(In Nos)</span></label>
									</div>
									<div class="col-md-7">
										<input type="text" id="quantity" maxlength="4" name="quantity" class="form-control" placeholder="Max Qty 50" onkeyup = "formopen(this.value)" autocomplete="off">
									</div>
								</div>
							</div>
						</div>
						<br/>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Remarks</label>
									</div>
									<div class="col-md-7">
										<textarea raws="2" id="remarks" name="remarks" class="form-control" maxlength="100" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-5">
										<!-- <label class=" form-control-label"><strong style="color: red;">*</strong> Qty <span style="font-size: 10px;font-size:12px;color:red">(In Nos)</span></label> -->
									</div>
									<div class="col-md-7">
										<!-- <input type="text" id="quantity" maxlength="4" name="quantity" class="form-control" placeholder="Max Qty 50" onkeyup = "formopen(this.value)" autocomplete="off"> -->
									</div>
								</div>
							</div>
						</div>
						
						
					</div>
					<div class="card-body card-block" style="overflow: auto;">
						<div class="col s12" style="">
			              	<table class="table no-margin table-striped  table-hover  table-bordered" id="addQuantity" >
				                <thead>
				                  	<tr align="center">
				                  		<th width="6%">Ser No</th>
				                   		<th width="27%"><strong style="color: red;">*</strong> Engine No</th>
				                    	<th width="27%"><strong style="color: red;">*</strong> Chassis No</th>
				                     	<th width="20%"><strong style="color: red;">*</strong> Pencil Rubbing of Engine <br> <span style="font-size: 10px;">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed)</span></th> 
				                     	<th width="20%"><strong style="color: red;">*</strong> Pencil Rubbing of Chassis <br> <span style="font-size: 10px;">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed)</span></th> 
				                  	</tr>
				                   <tr style="background-color: white;">	
				                  		<td style="color: black;" align="center" width="6%">1</td>
				                  		<td width="27%"><input id="engine_no1" name="engine_no1" maxlength="20" class="form-control" autocomplete="off" style="text-transform: uppercase;"></td>
		        						<td width="27%"><input id="chasis_no1" name="chasis_no1" maxlength="20" class="form-control" autocomplete="off" style="text-transform: uppercase;"></td>
		        						<td width="20%"><input type="file" id="engine_image1" name="engine_image1" class="form-control"></td>
		        						<td width="20%"><input type="file" id="chasis_image1" name="chasis_image1" class="form-control"></td>
									</tr>
								</thead>
								
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
					<input type="hidden" id="count" name="count" >
					<div class="form-control card-footer" align="center">
						<a href="banum_dirctry" type="reset" class="btn btn-success btn-sm"> Clear </a> 						
						<input type="submit" class="btn btn-primary btn-sm" value="Submit" onclick="return validate();"> 
					
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>

function mcthideshow()
{
	var r = $('input:radio[name=ba_no_type]:checked').val();
	if (r == '1')
	{
		$("#mct_number1").attr('readonly',true).css("background-color", "#e9ecef"); 
		$("#new_nomencatre").attr('readonly',true).css("background-color", "#e9ecef"); 
		$("span#nonArmy").hide();
		$("#vehicle_clas_code").attr('readonly',false).css("background-color", "white"); 	
	}else{
		$("span#nonArmy").show();
		$("#vehicle_clas_code").attr('readonly',true).css("background-color", "#e9ecef"); 	
		$("#mct_number1").attr('readonly',false).css("background-color", "white"); 
		$("#new_nomencatre").attr('readonly',false).css("background-color", "white"); 
	}
}
</script>
<script>
function blockSpecialChar(e){
	var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 64 && k < 91));
}
</script>
<script type="text/javascript">
	$(function() {    
		$("#front_view_image1").change(function(){	
			checkFileExtImage(this);
		});
		$("#back_view_image1").change(function(){	
			checkFileExtImage(this);
		});
		$("#side_view_image1").change(function(){	
			checkFileExtImage(this);
		});
		$("#top_view_image1").change(function(){	
			checkFileExtImage(this);
		});
	 	$("#requesting_agency").keypress(function(){
			var unit_name = this.value;
		 	var susNoAuto=$("#requesting_agency");
		  	susNoAuto.autocomplete({
		  	source: function( request, response ) {
		        $.ajax({
		        	
		        	type: 'POST',
		    	    url: "getReqAgencyList?"+key+"="+value,
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
		        	alert("Please Enter Unit/Est.");
		        	document.getElementById("requesting_agency").value="";
		        	susNoAuto.val("");	        	  
		        	susNoAuto.focus();
		        	return false;	             
		    	}   	         
		    }, 
		    select: function( event, ui ) {
		      	var ReName = ui.item.value;
		      
		                $.post("getSusNoFromReqAgency?"+key+"="+value, {req_agency : ReName}, function(data) {
		           
		            }).done(function(data) {
						    var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#sus_no").val(dec(enc,data[0]));
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
			}	     
		});
	});	
        
 
		$("input#sus_no").keyup(function(){
			var sus_no = this.value;
			var unitNameAuto=$("#sus_no");
			unitNameAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
			    	    url: "getReqAgencySusNoList?"+key+"="+value,
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
			        	  $("#req_agency").val("");
			        	  unitNameAuto.val("");	        	  
			        	  unitNameAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			      	var susnumber =  ui.item.value;
			    	
			            $.post("getReqAgencyFromSusNo?"+key+"="+value,  {sus_no : susnumber}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
	        				var enc = data[length].substring(0,16);
	        				$("#requesting_agency").val(dec(enc,data[0]));	
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			     }
			});
		});	
		$("input#mct_number1").keyup(function(){
			var mct = this.value;
			var mct_numberAuto=$("#mct_number1");
			var type_of_vehicle = $("#veh_cat").val();
			if(type_of_vehicle == "0"){
				alert("Please Select Type of Vehicle.");
				$("#veh_cat").focus();
				$("#new_nomencatre").val("");
	        	mct_numberAuto.val("");
				return false;
			}
			mct_numberAuto.autocomplete({
				source: function( request, response ) {
				$.ajax({
					type: 'POST',
					url: "getMctNoDetailList?"+key+"="+value,
					data: {mct:mct ,type_of_vehicle:type_of_vehicle},
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
						alert("Please Enter MCT No.");
				        $("#new_nomencatre").val("");
				        mct_numberAuto.val("");	        	  
				        mct_numberAuto.focus();
				        return false;
					}
				},
			  	select: function( event, ui ) {
			      	var mct = ui.item.value;
			        $.post("getMCTREQBANOAll?"+key+"="+value,{mct : mct}, function(data) {
		            }).done(function(data) {
		            	var length = data.length-1;
				        var enc = data[length].substring(0,16);
		      		  	$("#new_nomencatre").val(dec(enc,data[0]));
		      		  	$("#vehicle_clas_code").val(dec(enc,data[1]));
		      		 	$("#axle_wts").val(dec(enc,data[2]));
		      		    $("#drive").val(dec(enc,data[3]));
	      		        if(dec(enc,data[4]) != "null" && dec(enc,data[4]) != ""){
	      		        	$("#type_of_fuel").val(dec(enc,data[4]));
	      		        }
	      		      	if(dec(enc,data[10]) != "null" && dec(enc,data[10]) != "")
	      		    	{
	      		    		$("#no_of_wheels").val(dec(enc,data[5]));
	      		    	}
		      		    if(dec(enc,data[10]) != "null" && dec(enc,data[10]) != "")
	      		    	{
	      		    		$("select#no_of_axles").val(dec(enc,data[6]));
	      		    	}
	      		       	$("#tonnage").val(dec(enc,data[7]));
		      		    $("#towing_capcty").val(dec(enc,data[8]));
		      		    $("#lift_capcty").val(dec(enc,data[9]));
		      		    if(dec(enc,data[10]) != "null" && dec(enc,data[10]) != "")
		      		    {
		      		    	$("#wheel_track").val(dec(enc,data[10]));
		      		    }
		      		   $("#sponsoring_dte").val(dec(enc,data[11]));
				}).fail(function(xhr, textStatus, errorThrown) { });
			}
		});
	});
		
		$("#new_nomencatre").keyup(function(){
			var mctNomen = this.value;
			var type_of_vehicle = $("#veh_cat").val();
			var mct_nomenAuto=$("#new_nomencatre");
			
			if(type_of_vehicle == "0"){
				alert("Please Select  Type of Vehicle");
				$("#veh_cat").focus();
				$("#mct_number1").val("");
	        	mct_nomenAuto.val("");	  
				return false;
			}
			mct_nomenAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
			    	    url: "getStdNomenclatureListFromVeh_cat?"+key+"="+value,
			        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle},
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
			        	  alert("Please Enter Nomenclature.");
			        	  $("#mct_number1").val("");
			        	  mct_nomenAuto.val("");	        	  
			        	  mct_nomenAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
			    select: function( event, ui ) {
			      	var nomencatre = ui.item.value;
			        $.post("getStdNomenclaturetoMctNoList?"+key+"="+value,{std_nomclature : nomencatre}, function(data) {
		            }).done(function(data) {
		            	    var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#mct_number1").val(dec(enc,data[0]));
				        	
				        
					        $.post("getMCTREQBANOAll?"+key+"="+value,{mct:dec(enc,data[0])}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
						        var enc = data[length].substring(0,16);
						        $("#new_nomencatre").val(dec(enc,data[0]));
			      		  		$("#vehicle_clas_code").val(dec(enc,data[1]));
			      		 	    $("#axle_wts").val(dec(enc,data[2]));
			      		        $("#drive").val(dec(enc,data[3]));
			      		        $("#type_of_fuel").val(dec(enc,data[4]));
			      		        $("#no_of_wheels").val(dec(enc,data[5]));
			      		       $("select#no_of_axles").val(dec(enc,data[6]));
			      		       $("#tonnage").val(dec(enc,data[7]));
			      		       $("#towing_capcty").val(dec(enc,data[8]));
			      		       $("#lift_capcty").val(dec(enc,data[9]));
			      		       $("#wheel_track").val(dec(enc,data[10]));
			      		   	   $("#sponsoring_dte").val(dec(enc,data[11]));
			      		   	   
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
					        
					        
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
				}
			});
		});		
	    $('#veh_cat').change(function() {
	    	$("#mct_number1").val("");
	    	$("#new_nomencatre").val("");
	    	var type_of_vehicle = this.value;     
	    });
	
	    $("input#sponsoring_dte").keyup(function(){
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
			        	  alert("Please Enter Nodal Directorate.");
			        	  sponsoring_dteAuto.val("");	        	  
			        	  sponsoring_dteAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
			  	select: function( event, ui ) {
			      	var sponsoring_dte = ui.item.value;
				}
			});
		}); 
	});
		
		function formopen(e){
			var max_fields  = e; //maximum input boxes allowed			
			if(max_fields < 51){
				document.getElementById("count").value = max_fields;
				$("table#addQuantity tbody").empty();
				for(var x= 2 ;x <= max_fields ;x++){ //max input box allowed
			    	$("table#addQuantity").append('<tr align="center" id="'+x+'" width="6%"><td>'+x+'</td>'
		        			+'<td width="27%"><input type="text" name="engine_no'+x+'" id="engine_no'+x+'" class="form-control" autocomplete="off" maxlength="20" style="text-transform: uppercase;"/></td>'
		        			+'<td width="27%"><input type="text" name="chasis_no'+x+'" id="chasis_no'+x+'" class="form-control" autocomplete="off" maxlength="20" style="text-transform: uppercase;"/></td>'
		        			+'<td width="20%"><input type="file" name="engine_image'+x+'" id="engine_image'+x+'" class="form-control"></td>'
		        			+'<td width="20%"><input type="file" name="chasis_image'+x+'" id="chasis_image'+x+'" class="form-control"></td>'
		        			+'</tr>');			
	   			}
			}else{
				alert("Please Enter max 50 Quantity.");
				$("#quantity").val("1");
				formopen(1);
				
			}
		}
</script>
<script>
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
	
	function validate() {
		var r = $('input:radio[name=ba_no_type]:checked').val();
		if (r == undefined) {
			alert("Please Select Army/Non-Army.");
			$('input:radio[name=ba_no_type]:checked').focus();
			return false;
		}
		if ($("#requesting_agency").val() == "") {
			alert("Please Enter Unit/Est.");
			$("#requesting_agency").focus();
			return false;
		}
		if ($("#sus_no").val() == "") {
			alert("Please Enter SUS No.");
			$("#sus_no").focus();
			return false;
		}
		if ($("#dte_of_reqst").val() == "") {
			alert("Please Enter Date of Request.");
			$("#dte_of_reqst").focus();
			return false;
		}
		if ($("select#veh_cat").val() == "0") {
			alert("Please Select Type of Vehicle.");
			$("#veh_cat").focus();
			return false;
		}
		if(r =='0'){
			if ($("#mct_number1").val() == "") {
				alert("Please Enter MCT No.");
				$("#mct_number1").focus();
				return false;
			}
		}
		if ($("#vehicle_clas_code").val() == "") {
			alert("Please Enter Vehicle Class Code.");
			$("#vehicle_clas_code").focus();
			$("#vehicle_clas_code").attr('readonly',false).css("background-color", "white");
			return false;
		}
		
		if ($("#year_of_entry").val() == "") {
			alert("Please Enter Yr of Entry into Service.");
			$("#year_of_entry").focus();
			return false;
		}
		if ($("#auth_letter_no").val() == "") {
				alert("Please Enter Supply Order No.");
				$("#auth_letter_no").focus();
				return false;
		}
		if($("#initiating_auth").val() == ""){
			alert("Please Enter Initiating Auth.");
			$("#initiating_auth").focus();
			return false;
		}
		if($("#date_of_auth_letter").val() == ""){
			alert("Please Enter Auth Date.");
			$("#date_of_auth_letter").focus();
			return false;
		}
		if ($("#purchas_cost").val() == "") {
			alert("Please Enter Purchase Cost.");
			$("#purchas_cost").focus();
			return false;
		}
		if($("#front_view_image1").get(0).files.length == 0){
			alert("Please Select Fornt View Image.");
			$("#front_view_image1").focus();
			return false;
		}
		if($("#back_view_image1").get(0).files.length == 0){
			alert("Please Select Rear View Image.");
			$("#back_view_image1").focus();
			return false;
		}
		if($("#side_view_image1").get(0).files.length == 0){
			alert("Please Select Left Side View Image.");
			$("#side_view_image1").focus();
			return false;
		}
		if($("#top_view_image1").get(0).files.length == 0){
			alert("Please Select Right Side View Image.");
			$("#top_view_image1").focus();
			return false;
		} 
		if($("#quantity").val() == ""){
			alert("Please Enter Quantity.");
			$("#quantity").focus();
			return false;
		}
		if ($("#engine_no1").val() == "") {
			alert("Please Enter Engine No.");
			$("#engine_no1").focus();
			return false;
		}
		if ($("#chasis_no1").val() == "") {
			alert("Please Enter Chassis No.");
			$("#chasis_no1").focus();
			return false;
		}
		if ($("#remarks").val() == "") {
			alert("Please Enter Remarks.");
			$("#remarks").focus();
			return false;
		}
		
		var quantity = $("#quantity").val();
		// Check Engine No
		var EngineNO="";
		var ChasisNO="";
		for(var i = 1; i <= quantity ; i++){
			if($("#engine_no"+i).val() != ""){
				if(i == 1){
					EngineNO = $("#engine_no"+i).val();
				}else{
					EngineNO = EngineNO +","+ $("#engine_no"+i).val();
				}
			}else{
				alert("Please Enter Engine No.");
				$("#engine_no"+i).focus();
				return false;
			}		
			// start check Eng Exist
			var strArrayEng = EngineNO.split(",");
			var alreadySeenEng = [];
			var Engtest ="";
			strArrayEng.forEach(function(str) {
				if (alreadySeenEng[str]){
					Engtest = str;
			  	}else{
			  		alreadySeenEng[str] = true;
			  	}
			});
			if(Engtest != ""){
				alert("Already Exist Engine No." + Engtest);
				return false;
			}
			// end check Eng Exist
			if($("#chasis_no"+i).val() != ""){
				if(i == 1){
					ChasisNO = $("#chasis_no"+i).val();
				}else{
					ChasisNO = ChasisNO +","+ $("#chasis_no"+i).val();
				}
			}else{
				alert("Please Enter Chassis No.");
				$("#chasis_no"+i).focus();
				return false;
			}	
			
			var strArrayCha = ChasisNO.split(",");
			var alreadySeenCha = [];
			var Chatest ="";
			strArrayCha.forEach(function(str) {
				if (alreadySeenCha[str]){
					Chatest = str;
			  	}else{
			  		alreadySeenCha[str] = true;
			  	}
			});
			if(Chatest != ""){
				alert("Already Exist Chassis No." + Chatest);
				return false;
			}
			if($("#engine_image"+i).get(0).files.length == 0){
				alert("Please Select Pencil Rubbing of Engine.");
				$("#engine_image"+i).focus();
				return false;
			}			
			if($("#chasis_image"+i).get(0).files.length == 0){
				alert("Please Select  Pencil Rubbing of Chassis.");
				$("#chasis_image"+i).focus();
				return false;
			}
		}
		
		if(EngineNO != ""){
			var engCheck = checkExistEng(EngineNO);
			if(engCheck != "0"){
				alert("Already Exist Engine No. " + engCheck);
				return false;
		    }
		}		
		if(ChasisNO != ""){
			var chaCheck =  checkExistCha(ChasisNO);
			if(chaCheck != "0"){
				alert("Already Exist Chassis No. " + chaCheck);
				return false;
		    }
		}
		var k = confirm("Please Check the Details filled for Correctness.\nData Once Submitted is not Amendable.");		
		if (k == true) {
			return true;
		} else {
			return false;
		}
	}
		
	function checkExistEng(EngineNO){
		var test = "";
     	$.ajax({
			url : "checkIfExistEngineNO?"+key+"="+value,
			type : 'POST',
			data : {EngineNO : EngineNO},
			success : function(data) {
				if(data.length != 0){
					test = data;
				}else{
					test ="0";
				}
			},
			async : false,
		});
     	return test;
    }
	
	function checkExistCha(ChasisNO){
		var test = "";
		$.ajax({
			url : "checkIfExistChasisNO?"+key+"="+value,
			type : 'POST',
			data : {ChasisNO : ChasisNO},
			success : function(data) {
				if(data.length != 0){
					test = data;
				}else{
					test ="0";
				}
			},
			async : false,
		});
		return test;
	}
	
	function checkFileExtImage(file) {
	  	var ext = file.value.match(/\.([^\.]+)$/)[1];
		switch (ext) {
		  	case 'jpg':
		  	case 'jpeg':
		  	case 'png':
		  	case 'JPG':
		  	case 'JPEG':
		  	case 'PNG':
		 	//alert('Allowed');
		    break;
		  	default:
		    	alert('Only *.jpg, *.jpeg and *.png file extensions allowed');
		   	file.value = "";
		}
	}
	function typeofveh(obj) {
		if (obj.value == 1) {
			if ($("#veh_cat").val() == "A" || $("#veh_cat").val() == "C")
			{
				var option = '<option value="2">2</option>';
				$("select#no_of_wheels").html(option);
				$('select#no_of_wheels').attr('readonly', true);
			} else {
				var option = '<option value="0">--Select--</option>'
						+ '<option value="2">2</option>'
						+ '<option value="4">4</option>'
						+ '<option value="6">6</option>'
						+ '<option value="8">8</option>'
						+ '<option value="10">10</option>'
						+ '<option value="12">12</option>';
				$("select#no_of_wheels").html(option);
				$('select#no_of_wheels').attr('readonly', false);
			}
		} else {
			var option = '<option value="0">--Select--</option>'
					+ '<option value="2">2</option>'
					+ '<option value="4">4</option>'
					+ '<option value="6">6</option>'
					+ '<option value="8">8</option>'
					+ '<option value="10">10</option>'
					+ '<option value="12">12</option>';
			$("select#no_of_wheels").html(option);
			$('select#no_of_wheels').attr('readonly', false);

		}
	}

	function ClearField() {
		document.getElementById('addQuantity').style.display = 'block';
		document.getElementById("requesting_agency").value = "";
		document.getElementById("sus_no").value = "";
		document.getElementById("veh_cat").value = "0";
		document.getElementById("mct_number1").value = "";
		document.getElementById("new_nomencatre").value = "";
		document.getElementById("vehicle_clas_code").value = "";
		document.getElementById("type_of_fuel").value = "1";
		document.getElementById("no_of_wheels").value = "0";
		document.getElementById("no_of_axles").value = "0";
		document.getElementById("axle_wts").value = "";
		document.getElementById("drive").value = "";
		document.getElementById("tonnage").value = "0";
		document.getElementById("towing_capcty").value = "0";
		document.getElementById("lift_capcty").value = "0";
		document.getElementById("sponsoring_dte").value = "";
		document.getElementById("auth_letter_no").value = "";
		document.getElementById("initiating_auth").value = "";
		document.getElementById("date_of_auth_letter").value = "";
		document.getElementById("spl_eqpmnt_fitd").value = "";
		document.getElementById("purchas_cost").value = "";
		document.getElementById("front_view_image1").value = "";
		document.getElementById("back_view_image1").value = "";
		document.getElementById("side_view_image1").value = "";
		document.getElementById("top_view_image1").value = "";
		document.getElementById("typ_of_spl_eqpt_role").value = "";
		document.getElementById("quantity").value = "";
	}
</script>