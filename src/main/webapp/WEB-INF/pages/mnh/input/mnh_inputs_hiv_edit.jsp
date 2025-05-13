<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<form:form action="Hiv_aids_edit_DetailsAction" id="Capture_Hiv_Edit_Details" method="post" class="form-horizontal" commandName="Hiv_aids_edit_DetailsCMD">

	<div class="container" align="center">
		 <div class="card">
		      <div class="card-header">
		           <h5>HIV/AIDS DETAILS</h5> 
		           <h6>
					   <span style="font-size: 12px; color: red">(To be entered by Confirming Centers)</span>
				   </h6> 
		      </div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Confirming Center</label>
								</div>
								<div class="col-md-9">
									<input type="text" id="unit_name1" name="1" class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" maxlength="100" title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>
					<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
								</div>
								<div class="col-md-8">
									 
									<input type="text" id="sus_no1" name="sus_no1"
										class="form-control-sm form-control" placeholder="Search..." autocomplete="off" maxlength="8" title="Type SUS No or Part of SUS No to Search">
								</div>
							</div>
						</div>
					</div>
				<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Accession No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="accession_no" name="accession_no"
										class="form-control-sm form-control"
										placeholder="Enter Accession No..." maxlength="30"
										autocomplete="off">
								</div>
							</div>
						</div>
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Type</label>
								</div>
								<div class="col-md-8">
									<select id="type" name="type"  class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_TYPE}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Service</label>
								</div>

								<div class="col-md-8">
									<select id="service" name="service" class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_SERVICE}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Gender</label>
								</div>

								<div class="col-md-8">
									<select name="sex" id="sex" class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_SEX}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12 form-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Personnel No</label>
								</div>
								
								<div class="col-md-10">
								    <div class="row form-group">
								    <div class="col-md-4">
									<select id="persnl_no1" name="persnl_no1" class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_PERSNL_PRE}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
									</div>
									<div class="col-md-4">
									<input type="text" id="persnl_no2" onkeypress="return isNumber0_9Only(event)" name="persnl_no2" class="form-control-sm form-control" autocomplete="off" placeholder="Enter No..." maxlength="10">
								</div>
								<div class="col-md-4">
									<select id="persnl_no3" name="persnl_no3" class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_PERSNL_SUF}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
									</div>
								</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Category</label>
								</div>
								<div class="col-md-8">
									<select name="category" id="category" class="form-control-sm form-control" readonly="readonly">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_CATEGORY}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
						
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						     <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Rank</label>
					    </div>
					    
					   <div class="col-md-8">
					   
					         <select name="hivaid_rank.id" id="rank" data-placeholder="Select the Rank..." class="form-control-sm form-control" onchange="rank_change(this);">
								   <option value="-1">--Select--</option>
							</select>
					    </div>
				    </div>
				   </div>
				   
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Personnel Name</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="persnl_name" name="persnl_name" class="form-control-sm form-control" autocomplete="off" onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()" maxlength="100" placeholder="Enter Personnel Name..." />
								</div>
							</div>
						</div>
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Relation</label>
								</div>
								<div class="col-md-8">
									<select id="relation" name="relation" class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_6}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>DOB</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="date_of_birth1" name="date_of_birth1" autocomplete="off" class="form-control-sm form-control"
										onchange="calculate_age_new(this);" maxlength="3" min="1899-01-01" max="${date}">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label" style="margin-left: 13px;">Age (Years)</label>
								</div>
								
								<div class="col-md-4">
									<input id="age_year" name="age_year" class="form-control-sm form-control" maxlength="3" readonly
										onkeypress="return isNumberPointKey(event);" autocomplete="off" placeholder="Enter Year...">
								</div>
								<div class="col-md-4">
									<input id="age_month" name="age_month" class="form-control-sm form-control" maxlength="2" readonly
										onkeypress="return isNumberPointKey(event);" autocomplete="off" placeholder="Enter Month...">
								</div>
							</div>
						</div>
						
					</div>
					
					
					<div class="col-md-12">
					<div class="col-md-6">
						 <div class="row form-group">
							<div class="col-md-4">
               					<label for="text-input" class=" form-control-label">Aadhar Card No.</label>
							</div>
							<div class="col-md-8">
							<form:input type="text" id="adhar_card_no" path="adhar_card_no" class="form-control-sm form-control" 
								 maxlength="12" onkeypress="return isNumber0_9Only(event)" placeholder=" Please Enter 12 Digit Aadhar card No..." autocomplete="off"/>
						</div>
		  			</div>
				</div>
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Contact
										Number(Mobile)</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="contact_no" name="contact_no1"
										value="${Hiv_aids_edit_DetailsCMD.contact_no}"
										autocomplete="off" class="form-control-sm form-control"
										onkeypress="return isNumber0_9Only(event)" maxlength="10"
										placeholder="Enter Contact Number...">
								</div>
							</div>
						</div>	
					</div>
					
					
					
					
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Total Service (Years)</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="total_service" name="total_service" class="form-control-sm form-control" value="0"
										placeholder="Enter Total Service..." autocomplete="off" onkeypress="return isNumberPointKey(event)" maxlength="10">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label" style="margin-left: 13px;">Marital Status</label>
								</div>
								<div class="col-md-8">
									<select id="marital_status" name="marital_status" class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_MRTLSTAT}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Service in Command</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="command1" name="command1" class="form-control-sm form-control"
										placeholder="Enter Service in Command..." onkeypress="return onlyAlphaNumeric(event);" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label" style="margin-left: 13px;">Arms/Services</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit" name="unit" onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control" placeholder="Enter Arms/Services...." maxlength="100" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label" style="margin-left: 13px;">Unit</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="persnl_unit" name="persnl_unit" onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control"
										 placeholder="Search..." maxlength="150" oninput="this.value = this.value.toUpperCase()" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
	<div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Reasons for ELISA Screening</label>
								</div>
								<div class="col-md-8">
									<div id = "divContainer" class="form-control-label" >
								</div>
							</div>
							</div>
						</div>
				<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Possible Source of infection</label>
								</div>
								<div class="col-md-8">
									<div id = "divContainer21" class="form-control-label">
									</div>
							</div>
							</div>
						</div>
					</div>
                 
 					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group"  id="d11" style="display: none;">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label" >Details</label>
								</div>
								<div class="col-md-8">
									<textarea class="form-control-sm form-control" name="other_details_elisa_screening" maxlength="250"
										id="other_details_elisa_screening" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" value="${Hiv_aids_edit_DetailsCMD.other_details_elisa_screening}"></textarea>
								</div>
							</div>
						</div>
                     
						<div class="col-md-6" >
							<div class="row form-group" id="d21" style="display: none;">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"  >Details</label>
								</div>
								<div class="col-md-8">
									<textarea class="form-control-sm form-control" name="other_details_source_infection" maxlength="250"
										id="other_details_source_infection" onkeypress="return onlyAlphaNumeric(event);"  autocomplete="off" value="${Hiv_aids_edit_DetailsCMD.other_details_source_infection}"></textarea>
								</div>
							</div>
						</div>
					</div>

			<div class="col-md-12" >
						<div class="col-md-6" >
							<div class="row form-group" id="pDAte" style="display: none;">
								<div class="col-md-4">
									<label class="form-control-label">Possible Date</label>
								</div>
								<div class="col-md-8">
									<input type="Date" id="p_date" name="p_date" class="form-control-sm form-control">
								</div>
							</div>
						</div>

						<div class="col-md-6" >
							<div class="row form-group" id="plce" style="display: none;">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Possible Place</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="possible_place_siwsw" name="possible_place_siwsw" maxlength="50"
										placeholder="Enter Possible Place..." onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>
					</div>

				<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Confirming Test</label>
								</div>
								<div class="col-md-8">
									<select id="name_confirmatory_test" name="name_confirmatory_test" class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_CONFRMTEST}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Reporting Date</label>
								</div>
								<div class="col-md-8">
									<input type="Date" id="r_date1" name="r_date1"  maxlength="3" min="1899-01-01" max="${date}" class="form-control-sm form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Received Date</label>
								</div>
								<div class="col-md-8">
									<input type="Date" id="rr_date1" name="rr_date1"  maxlength="3" min="1899-01-01" max="${date}"class="form-control-sm form-control">
								</div>
							</div>
						</div>
                   </div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Lab Reference Number</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="lab_reference_no" name="lab_reference_no" placeholder="Enter... "
										class="form-control-sm form-control" onkeypress="return onlyAlphaNumeric(event);" maxlength="40" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
				<div class="col-md-12">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Remarks</label>
						</div>
						<div class="col-md-10" >
							<textarea rows="2" cols="250" class="form-control char-counter1" onkeypress="return onlyAlphaNumeric(event);" maxlength="250" placeholder="Enter Your Remarks..." id="remarks" name="remarks"></textarea>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="${Hiv_aids_edit_DetailsCMD.id}" class="form-control" autocomplete="off">
			<div class="card-footer" align="center">
			    	<a href="mnh_input_hiv_search" class="btn btn-danger btn-sm" id="btn_cancl" onclick="btn_clc();">Cancel</a>
		         <input type="submit" id="btn_update" class="btn btn-success btn-sm" value="Update" onclick="return isvalidData();"/> 
			    	
			    	   				            
             </div>	
       </div>
      </div>
</form:form>



<Script>
function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}

</Script>
<script>
function btn_clc(){
	location.reload(true);
}

function chgCategory(){
	if($("#service").val() == "NAVY" || $("#service").val() == "AIRFORCE"){
		$("#persnl_no1").attr('readonly',true);
		$("#persnl_no2").attr('readonly',false);
		$("#persnl_no3").attr('readonly',false);
		$("#category").attr('readonly',false);
		
		$("#category option[value='OFFICER']").show();
		$("#category option[value='MNS']").show();
		$("#category option[value='JCO']").show();
		
		$("#persnl_no1").val('-1');
		$("#category").val('-1')
	}	
	
	if($("#service").val() == "OTHERS"){
		$("#persnl_no1").attr('readonly',true);
		$("#persnl_no2").attr('readonly',true);
		$("#persnl_no3").attr('readonly',true);
		$("#category").attr('readonly',true);
		
		$("#category option[value='OFFICER']").hide();
		$("#category option[value='MNS']").hide();
		$("#category option[value='JCO']").hide();
		
		$("#persnl_no1").val('-1');
		$("#persnl_no2").val('');
		$("#persnl_no3").val('-1');
		$("#category").val('-1')
	}	
	
	if($("#service").val() == "ARMY"){
		$("#persnl_no1").attr('readonly',false);
		$("#persnl_no2").attr('readonly',false);
		$("#persnl_no3").attr('readonly',false);
		$("#category").attr('readonly',true);
		
		$("#category option[value='OFFICER']").show();
		$("#category option[value='MNS']").show();
		$("#category option[value='JCO']").show();
		
		$("#persnl_no2").val('');
		$("#persnl_no3").val('-1');
		
		if($("#persnl_no1").val() == "-1"){
			$("#category").val('OR');
		}
		else if($("#persnl_no1").val() == "TJ" || $("#persnl_no1").val() == "JC"){
			$("#category").val('JCO');
		}
        else if($("#persnl_no1").val() == "NR" || $("#persnl_no1").val() == "NS" || 
				$("#persnl_no1").val() == "NL" || $("#persnl_no1").val() == "PN"){
        	$("#category").val('MNS');
		}
        else if($("#persnl_no1").val() != "NR" || $("#persnl_no1").val() != "NS" || 
				$("#persnl_no1").val() != "NL" || $("#persnl_no1").val() != "PN" || $("#persnl_no1").val() != "TJ" || 
				$("#persnl_no1").val() != "JC"){
        	$("#category").val('OFFICER');
		}	
	}
}

function funcheckbox(j){
	
	var reason = '${Hiv_aids_edit_DetailsCMD.reasons_elisa_screening}'
	for ( var i = 0; i < j.length ;i++) {
		var f = reason.includes(j[i][0]);
		if(f == true)
		{
			$("#divContainer").append("<input type='checkbox' name='reasons_elisa_screening' onchange='funcheckbox(this)' id='reasons_elisa_screening"+j[i][0] +"' value='" + j[i][0] + "'  checked/>&nbsp;<label>" + j[i][1] + " </label><br/> " );
		}	 			
		else
		{
			$("#divContainer").append("<input type='checkbox' name='reasons_elisa_screening' onchange='funcheckbox(this)'  id='reasons_elisa_screening"+j[i][0] +"' value='" + j[i][0] + "'  />&nbsp;<label>" + j[i][1] + " </label><br/> " );
		}
	} 
	if($('input:checkbox[id=reasons_elisa_screening9]').is(':checked') == true){
    	$("#d11").show();
	}
	else{
    	$("#d11").hide();
    	$("#other_details_elisa_screening").val("");
    }
	
}
function funcheckbox1(j){
	var source = '${Hiv_aids_edit_DetailsCMD.source_infection}'
		for (var i = 0; i < j.length; i++) {
			var f = source
					.includes(j[i][0]);

			if (f == true) {
				$("#divContainer21")
						.append("<input type='checkbox' name='source_infection' onchange='funcheckbox1(this)' id='source_infection"+ j[i][0]+ "' value='"+ j[i][0]
										+ "' checked />&nbsp;<label>"+ j[i][1]+ " </label><br/> ");

			}
			if (f == false) {
				$("#divContainer21").append("<input type='checkbox' name='source_infection' onchange='funcheckbox1(this)' id='source_infection"+j[i][0]+"' value='" + j[i][0] + "'  />&nbsp;<label>"
										+ j[i][1]+ " </label><br/> ");

			}
		}
		if($('input:checkbox[id=source_infection1]').is(':checked') == true){
			$("#plce").show();
	    	$("#pDAte").show();
		}
		else{
			$("#plce").hide();
        	$("#pDAte").hide();
        	$("#p_date").val("");
        	$("#possible_place_siwsw").val("");
	    }
	
		if($('input:checkbox[id=source_infection5]').is(':checked') == true){
			$("#d21").show();
		}
		else{
			$("#d21").hide();
        	$("#other_details_source_infection").val("");
	    }
	
}

function funcheckbox5(ele){
	var checkboxes = ele.value;
	var b = $("#possible_source1").val();

	if (b == checkboxes && b != "" ) {
    	if($('input:checkbox[id=possible_source1]').is(':checked') == true){
	    	$("#plce").show();
	    	$("#pDAte").show();
    	}
    	else{
        	$("#plce").hide();
        	$("#pDAte").hide();
        }
    }

	
	if (c == checkboxes && c != "" ) {
    	if($('input:checkbox[id=possible_source5]').is(':checked') == true){
	    	$("#d21").show();
    	}
    	else{
        	$("#d21").hide();
        }
    }
		
} 



function getRank() {
	
	var category = $("#category").val();
	var service = $("#service").val();
	$("#rank").attr('readonly',false);
	$.post("getMNHRank?"+key+"="+value,{a1 : category,a2 : service},function(j) {
		var options = '<option value="-1">--Select--</option>';

		var a = [];
		var enc = j[j.length - 1].substring(0, 16);
		for (var i = 0; i < j.length; i++) {
			a[i] = dec(enc, j[i]);
		}
		var data = a[0].split(",");
		var datap;

		for (var i = 0; i < data.length - 1; i++) {
			datap = data[i].split(":");
			options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[0] + '</option>';
		}
		$("#rank").html(options);
		var q = '${Hiv_aids_edit_DetailsCMD.hivaid_rank.id}';
		if(q != ""){
			$("#rank").val(q);
		}
	});
}

function isNumberPointKey(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}

function isvalidData(){

	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	var chk_val;
	
	$("input[type = checkbox][name = 'source_infection']:checked").each(function() { 
		if($(this).val() == "Sexual intercourse with commercial sex workers"){
			chk_val = "true";
        }
    });
	
	if ($("#unit_name1").val() == "") {
		alert("Please Enter the Confirming Center");
		$("#unit_name1").focus();
		return false;
	}
	 if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}
	 if ($("#accession_no").val().trim() == "") {
		alert("Please Enter the Accession No");
		$("#accession_no").focus();
		return false;
	}
	 if ($("#type").val() == "-1") {
		alert("Please Service Status");
		$("#type").focus();
 		return false;
	}
	 if ($("#service").val() == "-1") {
		alert("Please Select the Service");
		$("#service").focus();
 		return false;
	}
	 if ($("#sex").val() == "-1") {
			alert("Please Select the Gender");
			$("#sex").focus();
			return false;
		}
	 if ($("#persnl_no2").val() == "" && $("#service").val() != "OTHERS") {
		alert("Please Enter the Personnel No");
		$("#persnl_no2").focus();
 		return false;
	}
	 if ($("#persnl_no3").val() == "-1" && $("#service").val() != "OTHERS") {
		alert("Please Select the Personnel No");
		$("#persnl_no3").focus();
 		return false;
	}
 	 if ($("#category").val() == "-1" && $("#service").val() != "OTHERS") {
		alert("Please Select the Category");
		$("#category").focus();
		return false;
	} 
 	  if (($("#rank").val() == "-1" || $("#rank").val() == " " || $("#rank").val() == null) && $("#service").val() != "OTHERS") {
		alert("Please Select the Rank");
		$("#rank").focus();
		return false;
	}  
	 if ($("#persnl_name").val().trim() == "") {
		alert("Please Enter the Personnal Name");
		$("#persnl_name").focus();
		return false;
	}
	 if ($("#relation").val() == "-1") {
			alert("Please Select the Relation");
			$("#relation").focus();
			return false;
		}
	 var rel = $("#relation").val();
		
	   if (rel == "-1") {
			alert("Please Select the Relation");
			$("#relation").focus();
	 		return false;
		}
	 
	 if ($("#category").val() == "MNS" && $("#sex").val() != "FEMALE") {
			alert("category is MNS then gender should be Female(F) only.");
			$("#sex").focus();
			return false;
		}

	 if ((rel == "WIFEOF" || rel == "MOTHEROF" || rel == "DAUGHTEROF" || rel == "SISTEROF") && ($("#sex").val() != "FEMALE")) {
			alert("Gender should be Female Here");
			$("#sex").focus();
			return false;
		}
		if ((rel == "BROTHEROF" || rel == "HUSBANDOF" || rel == "FATHEROF" || rel == "SONOF") && ($("#sex").val() != "MALE")) {
			alert("Gender should be Male Here");
			$("#sex").focus();
			return false;
		}
	 
	 
	
	 if ($("#date_of_birth1").val() == "")  {
		alert("Please Select The Date Of Birth");
		$("#date_of_birth1").focus();
		return false;
	}
	 if (($("#relation").val() == "SELF" || $("#relation").val() == "MOTHEROF" || $("#relation").val() == "FATHEROF") && $("#age_year").val() < "17"){
			alert("Age Year Not Allowed Below 17");
			$("#date_of_birth1").focus();
			return false;
		 }
		
	 var adhar_no = ($("#adhar_card_no").val());
		
		if (!adhar_no == "" && adhar_no != "0" && adhar_no != null  && adhar_no.length != 12 && adhar_no.length != 14)
		{
		alert("Adhaar No Should Contain Maximum 12 Digit");
		return false;
		}
		 if(parseInt($("#age_year").val()) <= parseInt($("#total_service").val())){
				alert("Personnel Age Should be greater than Service");
				$("#age_year").focus();
				return false;	
			} 

	 if ($("input[type = checkbox][name = 'reasons_elisa_screening']:checked").val == " ") {
		alert("Please Select At least One Reasons for ELISA Screening");
		return false;
	} 
	 if ($("input[type = checkbox][name = 'reasons_elisa_screening']:checked").length == 0) {
			alert("Please Select At least One Reason For ELISA Screening");
			return false;
		}
	 if ($("input[type = checkbox][name = 'source_infection']:checked").length == 0) {
		alert("Please Select At least One Possible Source of infection");
		return false;
	}
	 if ($('input:checkbox[id=source_infection1]').is(':checked') == true && $("#possible_place_siwsw").val() == ""){
			alert("Please Enter Possible Place");
			$("#possible_place_siwsw").focus();
			return false;
	 	}
	

		
	 if(chk_val == "true" && $("#p_date").val() > c_d){
		$("#p_date").focus();
		alert("Can't select the Future Possible Date");
		return false;
	}
	
	 if ($("#name_confirmatory_test").val() == "-1") {
			alert("Please Select the Confirming Test");
			$("#name_confirmatory_test").focus();
			return false;
		}
		
		 if ($("#r_date1").val() == "") {
			alert("Please Select the Report Date");
			$("#r_date1").focus();
			return false;
		}
		 if($("#r_date1").val() > c_d){
			$("#r_date1").focus();
			alert("Can't select the Future Report Date");
			return false;
		}
		 if ($("#rr_date1").val() == "") {
			alert("Please Select the Received Date");
			$("#rr_date1").focus();
			return false;
		}
		 if($("#rr_date1").val() > c_d){
			$("#rr_date1").focus();
			alert("Can't select the Future Received Date");
			return false;
		}
	
	
		$("#category").attr('readonly',false);
	    $("#Capture_Hiv_Details").submit();

}

</script>
<script>

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no1").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no1");
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
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
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
			        	  document.getElementById("sus_no1").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name1").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name1").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name1").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name1");
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
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
								var newE = e.substring(0, autoTextVal.length);
								if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
								  myResponse.push(e);
								}
							});      	          
							response( myResponse ); 
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
				        	  document.getElementById("unit_name1").value="";
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
				 				         jQuery("#sus_no1").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});
	
		
	

});
$("input#persnl_unit").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#persnl_unit");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getTargetUnitsNameActiveListnew?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
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
		        	  document.getElementById("persnl_unit").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }
		      	     
		}); 			
});
</script>
<script>
$(document).ready(function() {

	var category = $("#category").val();
	var service = $("#service").val();
	
	
	$("#d21").show();
	$("#plce").show();
	$("#pDAte").show();
	$("div#plce").show();
	
	var para = '${r_1[0][1]}';
	if (para == "UNIT") {
		$("#sus_no1").val('${a_2}');
		}
	if ('${sus1}' != "" || '${unit1}' != "" || '${a_2}' != "" || '${a_3}' != "") {
		$("#divPrint").hide();
						}
	
			var d1 = new Date('${Hiv_aids_edit_DetailsCMD.report_date}');
			var d2 = new Date('${Hiv_aids_edit_DetailsCMD.report_received_on}');
			var d4 = new Date('${Hiv_aids_edit_DetailsCMD.date_of_birth}');
			var d5 = new Date('${Hiv_aids_edit_DetailsCMD.possible_date_siwsw}');
			var c_d1 = d1.getFullYear() + "-" + ("0" + (d1.getMonth() + 1)).slice(-2) + "-" + ("0" + d1.getDate()).slice(-2);
			var c_d2 = d2.getFullYear() + "-" + ("0" + (d2.getMonth() + 1)).slice(-2) + "-" + ("0" + d2.getDate()).slice(-2);
			var c_d5 = d5.getFullYear() + "-" + ("0" + (d5.getMonth() + 1)).slice(-2) + "-" + ("0" + d5.getDate()).slice(-2);
	
			var l = '${Hiv_aids_edit_DetailsCMD.persnl_no}'.length;
			
			
			$("#id").val('${Hiv_aids_edit_DetailsCMD.id}');
	
			$("#unit_name1").attr('readonly', true);
			$("#sus_no1").attr('readonly', true);
			$("#sus_no1").val('${Hiv_aids_edit_DetailsCMD.sus_no}');
			$.post("getMNHUnitNameBySUSNo?" + key + "=" + value, {
				y : '${Hiv_aids_edit_DetailsCMD.sus_no}'
			}, function(j) {
				var enc = j[j.length - 1].substring(0, 16);
				var a = dec(enc, j[0]);
				$("#unit_name1").val(a);
			});
			
			$("#service").val('${Hiv_aids_edit_DetailsCMD.service}');
			$("#type").val('${Hiv_aids_edit_DetailsCMD.type}');
			$("#command1").val('${Hiv_aids_edit_DetailsCMD.command1}');
			$("#persnl_name").val('${Hiv_aids_edit_DetailsCMD.persnl_name}');
			
			
			if ('${Hiv_aids_edit_DetailsCMD.category}' == "OR" && '${Hiv_aids_edit_DetailsCMD.service}' == "ARMY") {
				$("#persnl_no2").val('${Hiv_aids_edit_DetailsCMD.persnl_no}'.substring(0, l - 1));
				$("#persnl_no3").val('${Hiv_aids_edit_DetailsCMD.persnl_no}'.substring(l - 1, l));
			} 
			else if ('${Hiv_aids_edit_DetailsCMD.service}' == "AIRFORCE" || '${Hiv_aids_edit_DetailsCMD.service}' == "NAVY") {
				$("#persnl_no2").val('${Hiv_aids_edit_DetailsCMD.persnl_no}'.substring(0, l - 1));
				$("#persnl_no3").val('${Hiv_aids_edit_DetailsCMD.persnl_no}'.substring(l - 1, l));
			} 
			else if ('${Hiv_aids_edit_DetailsCMD.service}' == "ARMY" && '${Hiv_aids_edit_DetailsCMD.category}' != "OR") {
				$("#persnl_no1").val('${Hiv_aids_edit_DetailsCMD.persnl_no}'.substring(0, 2));
				$("#persnl_no2").val('${Hiv_aids_edit_DetailsCMD.persnl_no}'.substring(2, l - 1));
				$("#persnl_no3").val('${Hiv_aids_edit_DetailsCMD.persnl_no}'.substring(l - 1, l));
			} 
			else if ('${Hiv_aids_edit_DetailsCMD.service}' == "OTHERS") {
				$("#persnl_no1").val('-1');
				$("#persnl_no2").val('');
				$("#persnl_no3").val('-1');
			}

			if ('${Hiv_aids_edit_DetailsCMD.service}' == "NAVY" || '${Hiv_aids_edit_DetailsCMD.service}' == "AIRFORCE") {
				$("#persnl_no1").attr('readonly', true);
				$("#category").attr('readonly', false);
			}
			if ('${Hiv_aids_edit_DetailsCMD.service}' == "OTHERS") {
				$("#persnl_no1").attr('readonly', true);
				$("#persnl_no2").attr('readonly', true);
				$("#persnl_no3").attr('readonly', true);
				$("#category").attr('readonly', true);
				$("#rank").attr('readonly', true);

				$("#category option[value='OFFICER']").hide();
				$("#category option[value='MNS']").hide();
				$("#category option[value='JCO']").hide();
			}
			if ('${Hiv_aids_edit_DetailsCMD.service}' == "ARMY") {
				$("#category").attr('readonly', true);
			}

			if ('${Hiv_aids_edit_DetailsCMD.service}' != "OTHERS") {
				$("#category").val('${Hiv_aids_edit_DetailsCMD.category}');
				getRank();
			}
			$("#accession_no").val('${Hiv_aids_edit_DetailsCMD.accession_no}');
			$("#sex").val('${Hiv_aids_edit_DetailsCMD.sex}');
			$("#relation").val('${Hiv_aids_edit_DetailsCMD.relation}');
			$("#persnl_age").val('${Hiv_aids_edit_DetailsCMD.persnl_age}');
			$("#total_service").val('${Hiv_aids_edit_DetailsCMD.total_service}');
			$("#marital_status").val('${Hiv_aids_edit_DetailsCMD.marital_status}');
			$("#unit").val('${Hiv_aids_edit_DetailsCMD.unit}');
			$("#persnl_unit").val('${Hiv_aids_edit_DetailsCMD.persnl_unit}');
	
			var R1 = '${Hiv_aids_edit_DetailsCMD.reasons_elisa_screening}'.split(",");
	
			var R2 = '${Hiv_aids_edit_DetailsCMD.source_infection}'.split(",");
			
			var chk_val1;
			$("input[type = checkbox][name = 'reasons_elisa_screening']:checked").each(function() {
								if ($(this).val() == "Any Other") {
									chk_val1 = "true";
									$("#other_details_elisa_screening").show();
								}
							});
	
			var chk_val2;
			var chk_val3;
			$("input[type = checkbox][name = 'source_infection']:checked").each(function() {
								if ($(this).val() == "Sexual intercourse with commercial sex workers") {
									chk_val2 = "true";
									$("#possible").show();
								}
	
								if ($(this).val() == "Any Other") {
									chk_val3 = "true";
									$("#other_details_source_infection").show();
								}
							});
	
			if (chk_val1 == "true") {
				$("#other_details_elisa_screening").val('${Hiv_aids_edit_DetailsCMD.other_details_elisa_screening}');
			}
	
			if (chk_val3 == "true") {
				$("#other_details_source_infection").val('${Hiv_aids_edit_DetailsCMD.other_details_source_infection}');
			}
			$("#other_details_elisa_screening").val('${Hiv_aids_edit_DetailsCMD.other_details_elisa_screening}');
			$("#possible_place_siwsw").val('${Hiv_aids_edit_DetailsCMD.possible_place_siwsw}');
			$("#other_details_source_infection").val('${Hiv_aids_edit_DetailsCMD.other_details_source_infection}');
			$("#name_confirmatory_test").val('${Hiv_aids_edit_DetailsCMD.name_confirmatory_test}');
			$("#sex").val('${Hiv_aids_edit_DetailsCMD.sex}');
			$("#r_date1").val(c_d1);
			$("#rr_date1").val(c_d2);
	
			$("#p_date").val(c_d5);
			$("#lab_reference_no").val('${Hiv_aids_edit_DetailsCMD.lab_reference_no}');
			$("#remarks").val('${Hiv_aids_edit_DetailsCMD.remarks}');
	
			var dob = '${Hiv_aids_edit_DetailsCMD.date_of_birth}';
			dob = dob.substring(0, 10);
	
			$("#date_of_birth1").val(dob);
			calculate_age_edit('${Hiv_aids_edit_DetailsCMD.date_of_birth}');
	
			$.ajaxSetup({
				async : false
			});
			var reason = '${Hiv_aids_edit_DetailsCMD.reasons_elisa_screening}'
			$.post("getReason_Elisa?" + key + "=" + value,
					function(j) {
						$("#divContainer").empty();
						funcheckbox(j);
						
					});
			$.ajaxSetup({
				async : false
			});
			var source = '${Hiv_aids_edit_DetailsCMD.source_infection}'
			$.post("getSource_Infection?" + key + "="+ value,
							function(j) {
								$("#divContainer21").empty();
								funcheckbox1(j);
								});
			$.ajaxSetup({
				async : false
			});
	
			
			$('#service').change(function() {
				chgCategory();
				if ($("#service").val() != "-1" && $("#category").val() != "-1") {
				}
			});
	
			$('#persnl_no1').change(function() {
						chgCategory();
						if ($("#service").val() != "-1" && $("#category").val() != "-1") {
							getRank();
						}
					});
	
				$('#category').change(function() {
								getRank();
						});
			
						
					});
</script>