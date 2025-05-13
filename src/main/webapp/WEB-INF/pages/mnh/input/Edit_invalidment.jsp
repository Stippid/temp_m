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

<form:form action="Edit_Invalid_IMB_InputAction" method="post" class="form-horizontal" id="Invalid_IMB_Input" name="Invalid_IMB_Input" commandName="Edit_Invalid_IMB_InputCMD">
   
      <div class="container">
          <div class="card">
              <div class="card-header"  align="center">
		             <h5>EDIT INVALIDMENT DETAILS</h5>
		             <h6>
						<span style="font-size: 12px; color: red">(To be entered by DGMS 5(A) & 5(B))</span>
					 </h6>
		      </div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label">Hospital Name</label>
								</div>
								<div class="col-md-9">
									<input type="text" id="unit_name" name="unit_name" class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" title="Type Unit Name or Part of Unit Name to Search" maxlength="100">
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">SUS No</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="sus_no" path="sus_no" class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" maxlength="8" title="Type SUS No or Part of SUS No to Search"></form:input>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Service</label>
								</div>
								<div class="col-md-8">
									<select id="service" name="service" class="form-control-sm form-control" >
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCodeSERVICE}" varStatus="num">
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
									<select id="persnl_no1" name="persnl_no1"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCodePERSNL_PRE}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-4">
									<input type="text" id="persnl_no2" name="persnl_no2" onkeypress="return isNumber0_9Only(event)"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="Enter No..." maxlength="10">

								</div>
								<div class="col-md-4">
									<select id="persnl_no3" name="persnl_no3"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCodePERSNL_SUF}" varStatus="num">
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
									<select name="category" id="category" class="form-control-sm form-control" readonly>
								<option value="-1">--Select--</option>
								<c:forEach var="item" items="${getMedSystemCodeCATEGORY}" varStatus="num">
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
									<select name="invalid_rank.id" id="rank"
								class="form-control-sm form-control" readonly="readonly">
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
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Relation</label>
								</div>
								<div class="col-md-8">
									<select id="relation" name="relation" class="form-control-sm form-control" readonly="readonly">
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
									<label for="text-input" class="form-control-label">Personnel Name</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="name" path="name" class="form-control-sm form-control" autocomplete="off" oninput="this.value = this.value.toUpperCase()"
										maxlength="100" placeholder="Enter Personnel Name..." ></form:input>
								</div>
							</div>
						</div> 
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Unit</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="persnl_unit" path="persnl_unit" placeholder="Search..." maxlength="100" autocomplete="off"
								class="form-control-sm form-control" oninput="this.value = this.value.toUpperCase()"></form:input>
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
									<label for="text-input" class=" form-control-label">Age(yy/mm)</label>
								</div>
									<div class="col-md-4">
							<input id="age_year" name="age_year" class="form-control-sm form-control" maxlength="4" readonly  onkeypress="return isNumberPointKey(event);" autocomplete="off" placeholder="Enter Year..." >
								
						</div>
						<div class="col-md-4">
							<input id="age_month" name="age_month" class="form-control-sm form-control" readonly maxlength="2" onkeypress="return isNumberPointKey(event);" autocomplete="off"
								placeholder="Enter Month...">
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
									<label for="text-input" class=" form-control-label">Contact Number(Mobile)</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="contact_no" name="contact_no1"
										value="${Edit_Invalid_IMB_InputCMD.contact_no}"
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
									<label class="form-control-label">Total Service</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="service_years" name="service_years1" placeholder="Enter Total Service..." maxlength="10"
								autocomplete="off" class="form-control-sm form-control" onkeypress="return isNumberPointKey(event);">
								</div>
							</div>
						</div>	
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Formation</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="fmn" path="fmn" placeholder="Enter Formation..." class="form-control-sm form-control" maxlength="50" 
									autocomplete="off"></form:input>
								</div>
							</div>
						</div>					
					</div>
				
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label">Trade</label>
								</div>
								<div class="col-md-8">
									<select id="trade" name="trade" class="form-control-sm form-control">
								<option value="0">--Select--</option>
							<c:forEach var="item" items="${ml_5}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
							</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Primary Diagnosis</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="diagnosis_remarks" path="diagnosis_remarks" placeholder="Enter Primary Diagnosis..." autocomplete="off"
								class="form-control-sm form-control" maxlength="250"></form:input>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong style="color: red;">* </strong>ICD Code</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="icd_code" id="icd_code" class="form-control-sm form-control" autocomplete="off"
								placeholder="Search..." maxlength="255">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">ICD Cause</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="icd_cause" id="icd_cause" class="form-control-sm form-control" autocomplete="off"
								placeholder="Search..." maxlength="255" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Date Of Origin</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="date_place_origine1" name="date_place_origine1" class="form-control-sm form-control">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Date of IMB</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="date_imb1" name="date_imb1" class="form-control-sm form-control">
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Percentage of IMB</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="percent" name="percent1" placeholder="Enter IMB %" class="form-control-sm form-control"
								autocomplete="off" onkeypress="return isNumberPointKeyDecimal(this,event);" maxlength="10">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Attributable/ Aggravated by Mil Service</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="attributable_aggravated" path="attributable_aggravated" placeholder="Enter Data..."
								autocomplete="off" class="form-control-sm form-control" maxlength="50"></form:input>
								</div>
							</div>
						</div>
					</div>
			    </div>
			</div>
<input type="hidden" id="id" name="id" value="${Edit_Invalid_IMB_InputCMD.id}" class="form-control" autocomplete="off">
			<div class="card-footer"  align="center">
			<a href="mnh_input_search_invalidment" class="btn btn-danger btn-sm" id="btn_cancl" >Cancel</a>
		         <input type="submit" id="btn_update" class="btn btn-success btn-sm" value="Update" onclick="return isvalidData();"/> 
		         
			
			</div>
		</div>
	</div>
	
</form:form>
<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	var category = $("#category").val();
	var service = $("#service").val();
	
	$("#icd_cause").attr('readonly',true);
	
	if('${sus1}' != "" || '${unit1}' != "" ){	
		$("#divPrint").hide();
	}
	
	//$("#service").val("ARMY");
	$("#relation").val("SELF");
	
	chgCategory();
	//getRank();
	
	
	
	$('#service').change(function(){
		chgCategory();
		
		if($("#service").val() != "-1" && $("#category").val() != "-1"){
			getRank();
		}
	});
		$('#persnl_no1').change(function(){
			chgCategory();
			
			if($("#service").val() != "-1" && $("#category").val() != "-1"){
				getRank();
			}
			
		});
	
	$('#persnl_no3').change(function(){
		if($("#service").val() != "-1" && $("#category").val() != "-1")
		{
			getRank();
		}
		});
	$('#category').change(function(){
		if($("#service").val() != "-1" && $("#category").val() != "-1")
		{
			getRank();
		}
		});
	$("#icd_code").change(function(){
		var a = this.value.split("-");
		var b = a[0].substring(0,1).toUpperCase();
		if(b == "S" || b == "T" || b == "V" || b == "W" || b == "X" || b == "Y"){
			$("#icd_cause").attr('readonly',false);
			$("#icd_cause").keyup(function(){
				var code = this.value;
				$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause,{a:code,b:"ALL2"},'','','','','');
				});
			}
		else{
			$("#icd_cause").attr('readonly',true);
			$("#icd_cause").val('');
		}
		});
	$("#service").val('${Edit_Invalid_IMB_InputCMD.service}');
	var l = '${Edit_Invalid_IMB_InputCMD.persnl_no}'.length;
	$("#id_hid").val('${Edit_Invalid_IMB_InputCMD.id}');
	$("#unit_name").attr('readonly',true);
	$("#sus_no").attr('readonly',true);
	
	$("#sus_no").val('${Edit_Invalid_IMB_InputCMD.sus_no}');
	$.post("getMNHUnitNameBySUSNo?"+key+"="+value, {y:'${Edit_Invalid_IMB_InputCMD.sus_no}'},function(j) {
		var enc = j[j.length - 1].substring(0, 16);
		var a = dec(enc, j[0]);
		$("#unit_name").val(a);
	});
	/* if('${Edit_Invalid_IMB_InputCMD.category}' == "OR"){
		$("#persnl_no2").val('${Edit_Invalid_IMB_InputCMD.persnl_no}'.substring(0,l-1));
		$("#persnl_no3").val('${Edit_Invalid_IMB_InputCMD.persnl_no}'.substring(l-1,l));
	}
	if('${Edit_Invalid_IMB_InputCMD.category}' != "OR"){
		
		$("#persnl_no1").val('${Edit_Invalid_IMB_InputCMD.persnl_no}'.substring(0, 2));
		$("#persnl_no2").val('${Edit_Invalid_IMB_InputCMD.persnl_no}'.substring(2, l - 1));
		$("#persnl_no3").val('${Edit_Invalid_IMB_InputCMD.persnl_no}'.substring(l - 1, l));
	
	} */
	
	
	if ('${Edit_Invalid_IMB_InputCMD.category}' == "OR"
		&& '${Edit_Invalid_IMB_InputCMD.service}' == "ARMY") {
	$("#persnl_no2").val(
			'${Edit_Invalid_IMB_InputCMD.persnl_no}'
					.substring(0, l - 1));
	$("#persnl_no3").val(
			'${Edit_Invalid_IMB_InputCMD.persnl_no}'
					.substring(l - 1, l));
} else if ('${Edit_Invalid_IMB_InputCMD.service}' == "AIRFORCE"
		|| '${Edit_Invalid_IMB_InputCMD.service}' == "NAVY") {
	$("#persnl_no2").val(
			'${Edit_Invalid_IMB_InputCMD.persnl_no}'
					.substring(0, l - 1));
	$("#persnl_no3").val(
			'${Edit_Invalid_IMB_InputCMD.persnl_no}'
					.substring(l - 1, l));
} else if ('${Edit_Invalid_IMB_InputCMD.service}' == "ARMY"
		&& '${Edit_Invalid_IMB_InputCMD.category}' != "OR") {
	$("#persnl_no1").val(
			'${Edit_Invalid_IMB_InputCMD.persnl_no}'
					.substring(0, 2));
	$("#persnl_no2").val(
			'${Edit_Invalid_IMB_InputCMD.persnl_no}'
					.substring(2, l - 1));
	$("#persnl_no3").val(
			'${Edit_Invalid_IMB_InputCMD.persnl_no}'
					.substring(l - 1, l));
} else if ('${Edit_Invalid_IMB_InputCMD.service}' == "OTHERS") {
	$("#persnl_no1").val('-1');
	$("#persnl_no2").val('');
	$("#persnl_no3").val('-1');
}

if ('${Edit_Invalid_IMB_InputCMD.service}' == "NAVY"
		|| '${Edit_Invalid_IMB_InputCMD.service}' == "AIRFORCE") {
	$("#persnl_no1").attr('readonly', true);
	$("#category").attr('readonly', false);
}
if ('${Edit_Invalid_IMB_InputCMD.service}' == "OTHERS") {
	$("#persnl_no1").attr('readonly', true);
	$("#persnl_no2").attr('readonly', true);
	$("#persnl_no3").attr('readonly', true);
	$("#category").attr('readonly', true);
	$("#rank").attr('readonly', true);

	$("#category option[value='OFFICER']").hide();
	$("#category option[value='MNS']").hide();
	$("#category option[value='JCO']").hide();
}
if ('${Edit_Invalid_IMB_InputCMD.service}' == "ARMY") {
	$("#category").attr('readonly', true);
}

if ('${Edit_Invalid_IMB_InputCMD.service}' != "OTHERS") {
	$("#category").val(
			'${Edit_Invalid_IMB_InputCMD.category}');
	getRank();
}

	$("#name").val('${Edit_Invalid_IMB_InputCMD.name}');
	$("#category").val('${Edit_Invalid_IMB_InputCMD.category}');
	getRank();
	
	$("#age").val('${Edit_Invalid_IMB_InputCMD.age}');
	$("#service_years").val('${Edit_Invalid_IMB_InputCMD.service_years}');
	
	$("#persnl_unit").val('${Edit_Invalid_IMB_InputCMD.persnl_unit}');
	$("#persnl_unit").val('${Edit_Invalid_IMB_InputCMD.persnl_unit}');
	$("#fmn").val('${Edit_Invalid_IMB_InputCMD.fmn}');
	//$("#date_of_birth").val('${Edit_Invalid_IMB_InputCMD.date_of_birth}');
	$("#trade").val('${Edit_Invalid_IMB_InputCMD.trade}');
	$("#diagnosis_remarks").val('${Edit_Invalid_IMB_InputCMD.diagnosis_remarks}');
	
	$("#sex").val('${Edit_Invalid_IMB_InputCMD.sex}');
	$("#relation").val('${Edit_Invalid_IMB_InputCMD.relation}');
	//$("#date_place_origine").val('${Edit_Invalid_IMB_InputCMD.date_place_origine}');
	//$("#date_imb").val('${Edit_Invalid_IMB_InputCMD.date_imb}');
	$("#percent").val('${Edit_Invalid_IMB_InputCMD.percent}');
	$("#icd_code").val('${Edit_Invalid_IMB_InputCMD.icd_code}');
	$("#icd_cause").val('${Edit_Invalid_IMB_InputCMD.icd_cause}');
		$("#attributable_aggravated").val('${Edit_Invalid_IMB_InputCMD.attributable_aggravated}')
	
 if('${Edit_Invalid_IMB_InputCMD.icd_code}' != ""){
		$.post("getMNHICDCodeToCauseVi?"+key+"="+value, {a:'${Edit_Invalid_IMB_InputCMD.icd_code}',b:"2"},function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			var data = '${Edit_Invalid_IMB_InputCMD.icd_code}' + "-" + a;
			$("#icd_code").val(data);
			
			var b = '${Edit_Invalid_IMB_InputCMD.icd_code}'.substring(0,1);
			if(b == "S" || b == "T" || b == "V" || b == "W" || b == "X" || b == "Y"){
				$("#icd_cause").attr('readonly',false);
			}
		});
	}

		
		 if('${Edit_Invalid_IMB_InputCMD.icd_cause}' != ""){
				$.post("getMNHICDCodeToCauseVi?"+key+"="+value, {a:'${Edit_Invalid_IMB_InputCMD.icd_cause}',b:"2"},function(j) {
					var enc = j[j.length-1].substring(0,16);
					var a = dec(enc,j[0]);
					var data = '${Edit_Invalid_IMB_InputCMD.icd_cause}' + "-" + a;
					$("#icd_cause").val(data);
				
			});
		}
	   
var date_of_birth = '${Edit_Invalid_IMB_InputCMD.date_of_birth}'
	.substring(0, 10);

$("#date_of_birth1").val(date_of_birth);
calculate_age_edit(date_of_birth);
var date_place_origine = '${Edit_Invalid_IMB_InputCMD.date_place_origine}'
	.substring(0, 10);
$("#date_place_origine1").val(date_place_origine);

var date_imb = '${Edit_Invalid_IMB_InputCMD.date_imb}'
	.substring(0, 10);
$("#date_imb1").val(date_imb);
}); 


</script>

<script>


function  isvalidData(){
	var age = $("#age").val();
	var serv = $("#service_years").val();
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	
	 if($("#unit_name").val() == ""){
			alert("Please Enter the Hospital Name");
			$("#unit_name").focus();
	 		return false;
		} 
		 if($("#sus_no").val() == ""){
			alert("Please Enter the SUS No");
			$("#sus_no").focus();
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
		 if ($("#sex").val() == "-1") {
				alert("Please Select the Gender");
				$("#sex").focus();
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
			
		  if ($("#date_of_birth1").val() == "") {
			alert("Please Select the date_of_birth1");
			$("#date_of_birth1").focus();
			return false;
		}
		  
		
		  if (($("#relation").val() == "SELF" || $("#relation").val() == "MOTHEROF" || $("#relation").val() == "FATHEROF") && $("#age_year").val() < "17"){
				alert("Age Year Not Allowed Below 17");
				$("#date_of_birth1").focus();
				return false;
			 }
		
			if (parseInt($("#age_year").val()) <= parseInt($("#service_years").val())) {
				alert("Personnel Age Should be greater than Service");
				$("#date_of_birth1").focus();
				return false;
			}
			
			 if ($("#icd_code").val() == "") {
					alert("Please Enter the ICD Code");
					$("#icd_code").focus();
					return false;
				} 
		    if ($("#date_place_origine1").val() == "") {
			alert("Please Select the date_place_origine");
			$("#date_place_origine1").focus();
			return false;
		}
		   if ($("#date_imb1").val() == "") {
				alert("Please Select the date_imbt");
				$("#date_imb1").focus();
				return false;
			}
		 if($("#percent").val() == ""){
			alert("Please Enter the percent");
			$("#percent").focus();
			return false;
		}  
			 var adhar_no = ($("#adhar_card_no").val());
				
				if (!adhar_no == "" && adhar_no != "0" && adhar_no != null  && adhar_no.length != 12 && adhar_no.length != 14)
				{
				alert("Adhaar No Should Contain Maximum 12 Digit");
				return false;
				}
	
}


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
function btn_clc(){
	location.reload(true);
}

function chgtrade(){
	$("#trade").attr('readonly',true);
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
			$("#hid_category").val('OR');
		}
		else if($("#persnl_no1").val() == "TJ" || $("#persnl_no1").val() == "JC"){
			$("#category").val('JCO');
			$("#hid_category").val('JCO');
		}
        else if($("#persnl_no1").val() == "NR" || $("#persnl_no1").val() == "NS" || 
				$("#persnl_no1").val() == "NL" || $("#persnl_no1").val() == "PN"){
        	$("#category").val('MNS');
        	$("#hid_category").val('MNS');
		}
        else if($("#persnl_no1").val() != "NR" || $("#persnl_no1").val() != "NS" || 
				$("#persnl_no1").val() != "NL" || $("#persnl_no1").val() != "PN" || $("#persnl_no1").val() != "TJ" || 
				$("#persnl_no1").val() != "JC"){
        	$("#category").val('OFFICER');
        	$("#hid_category").val('OFFICER');
        	
		}	
	}
}


function getRank() {
	var category = $("#category").val();
	var service = $("#service").val();
	$("#rank").attr('readonly',false);
	$.post("getMNHRank?"+key+"="+value, {a1 : category,a2 : service},function(j) {
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
		var q = '${Edit_Invalid_IMB_InputCMD.invalid_rank.id}';
		
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

function getSelectionStart(o){
	if(o.createTextRange){
		var r = document.selection.createRange().duplicate()
		r.moveEnd('character', o.value.length)
		if (r.text == '') return o.value.length
		return o.value.lastIndexOf(r.text)
	}else return o.selectionStart
}

function isNumberPointKeyDecimal(el,evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	var number = el.value.split('.');
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}
	if(number.length>1 && charCode == 46){
		return false;
    }
	
	var caratPos = getSelectionStart(el);
    var dotPos = el.value.indexOf(".");
    if( caratPos > dotPos && dotPos>-1 && (number[1].length > 1)){
        return false;
    }
    return true;
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
			        	  document.getElementById("sus_no").value="";
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
  			        	  	document.getElementById("unit_name").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name").keypress(function(){
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
				        	  document.getElementById("unit_name").value="";
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



$("#icd_code").keyup(function(){
	var code = this.value;
	$().Autocomplete2('GET','getMNHDistinctICDList',icd_code,{a:code,b:"ALL"},'','','','','');
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

