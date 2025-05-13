<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css">

<body class="mmsbg">
	<form:form action="rio_generationNewAction" method="post"
		class="form-horizontal" commandName="rio_generationNewCMD">
		<div class="">
			<div class="container" align="center">
				<div class="card">

					<div class="card-header mms-card-header">
						<b>RIO Generation</b>
					</div>

					<div class="card-body card-block">
						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>RO No</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="ro_no" name="ro_no" class="form-control-sm form-control" maxlength="25" readonly="readonly" /> <input type="hidden" id="ro_id" name="ro_id" />
							</div>
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Stock</label>
							</div>

							<div class="col-md-4">
								<input type="text" id="type_of_stk" name="type_of_stk"
									class="form-control-sm form-control" readonly="readonly" /> <input
									type="hidden" id="stk_code" name="stk_code" maxlength="2" />
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -10px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>RO Agency</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="ro_agency" name="ro_agency"
									maxlength="25" class="form-control-sm form-control"
									readonly="readonly" />
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>RO Type</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="ro_type" name="ro_type"
									class="form-control-sm form-control" maxlength="2"
									readonly="readonly" /> <input type="hidden" id="ro_type_c"
									name="ro_type_c" maxlength="2" />
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -15px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>RO Against</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="ro_for" name="ro_for"
									class="form-control-sm form-control" maxlength="2"
									readonly="readonly" /> <input type="hidden" id="ro_for_c"
									name="ro_for_c" maxlength="2" />
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>RO Type of Issue</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="ro_type_of_issue" name="ro_type_of_issue"
									class="form-control-sm form-control" maxlength="2"
									readonly="readonly" /> <input type="hidden"
									id="ro_type_of_issue_c" name="ro_type_of_issue_c" maxlength="2" />
							</div>
						</div>

						<div class="col-md-12 row form-group"
							style="display: none; margin-top: -10px;" id="b_hldg">
							<div class="col-md-6"></div>
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Bulk Type of Holding</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="ro_type_of_hldg" name="ro_type_of_hldg"
									class="form-control-sm form-control" readonly="readonly" /> <input
									type="hidden" id="ro_type_of_hldg_c" name="ro_type_of_hldg_c" />
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -10px;">
							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">* </strong>RIO No</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="rio_no" name="rio_no"
									class="form-control-sm form-control" maxlength="25"
									readonly="readonly" />
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">* </strong>RIO Date</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="rio_date" name="rio_date"
									class="form-control-sm form-control" readonly="readonly">
								<input type="hidden" id="ro_date" name="ro_date"
									class="form-control" />
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -10px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Unit / Comd</label>
							</div>
							<div class="col-md-10">
								<input type="text" id="ro_unit" name="ro_unit"
									class="form-control-sm form-control" readonly="readonly" /> <input
									type="hidden" id="to_sus_no" name="to_sus_no"
									class="form-control" maxlength="8" />
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -10px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label" style="margin-left: 13px;">PRF
								</label>
							</div>
							<div class="col-md-10">
								<input type="text" id="ro_prf" name="ro_prf"
									class="form-control-sm form-control" readonly="readonly" /> <input
									type="hidden" id="prf_code" maxlength="8" name="prf_code"
									class="form-control" />
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -10px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label" style="margin-left: 13px;">Census
									No</label>
							</div>
							<div class="col-md-10">

								<select name="census_no" id="census_no"
									class="form-control-sm form-control">
									<c:forEach var="item" items="${ml_4}">
										<option value="${item[0]}" name="${item[1]}">${item[0]}
											- ${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -10px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>UE</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="ro_ue" maxlength="5" name="ro_ue"
									class="form-control-sm form-control" readonly="readonly" />
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>UH</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="ro_uh" name="ro_uh"
									class="form-control-sm form-control" maxlength="5"
									readonly="readonly" />
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -15px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Qty to be Issued</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="ro_qty" name="ro_qty" maxlength="5"
									class="form-control-sm form-control"
									onkeypress="return validateNumber(event, this)"
									autocomplete="off" placeholder="Enter Qty..." />
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -15px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Depot Name</label>
							</div>
							<div class="col-md-10">
								<input type="text" id="rel_depot_sus" name="rel_depot_sus"
									class="form-control-sm form-control" readonly="readonly" /> <input
									type="hidden" id="rel_depot_sus_code" name="rel_depot_sus_code"
									class="form-control" maxlength="8" /> <input type="hidden"
									id="comd_sus" name="comd_sus" maxlength="8">
							</div>
						</div>

						<div class="col-md-12 row form-group" style="margin-top: -5px;">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label" style="margin-left: 13px;">Remarks</label>
							</div>
							<div class="col-md-10">
								<textarea id="remarks" name="remarks"
									class="form-control-sm form-control" maxlength="255"
									placeholder="Enter Remarks..."></textarea>
							</div>
						</div>
					</div>

					<div class="card-footer" align="center" style="margin-top: -15px;">
						<input type="hidden" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" /> 
						<input type="submit" class="btn btn-success btn-sm" value="Save" onclick="return validate();"> 
						<a href="mmsDashboard" type="reset" class="btn btn-danger btn-sm"> Cancel </a>
					</div>
				</div>
			</div>
		</div>
	</form:form>

	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
	<script type="text/javascript" src="js/common/commonmethod.js"></script>
	<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

	<script>
$(document).ready(function() {
	$().getCurDt2(rio_date);
	
	var c_rio = '${l_1[0].rio_no}';
	if(c_rio != ""){
		var ew = confirm('RIO No already issued against same RO No and Unit.\nWant to include in Same RIO No.\nPress OK to include.');
		if(ew == true){
			$("#rio_no").val(c_rio);
		}else{
			$("#rio_no").val('${ml_1[0]}');
		}
	}else{
		$("#rio_no").val('${ml_1[0]}');
	}

	var r = '${rio_data[7]}';
	if(r == ""){
		$("#b_hldg").show();
		$("#ro_type_of_hldg").val('${rio_data[22]}'); 
		$("#ro_type_of_hldg_c").val('${rio_data[21]}');
	}

	$("#ro_no").val('${rio_data[0]}');
	$("#ro_agency").val('${rio_data[1]}');
	$("#ro_type").val('${rio_data[3]}');//[2]
	$("#ro_type_c").val('${rio_data[2]}');
	$("#ro_for").val('${rio_data[5]}');//[4]
	$("#ro_for_c").val('${rio_data[4]}');
	$("#ro_type_of_issue").val('${rio_data[7]}');//[6] 
	$("#ro_type_of_issue_c").val('${rio_data[6]}');
	$("#ro_unit").val('${rio_data[9]}');//[8] 
	$("#to_sus_no").val('${rio_data[8]}');
	$("#ro_prf").val('${rio_data[11]}');//[10] 
	$("#prf_code").val('${rio_data[10]}');
	$("#rel_depot_sus").val('${rio_data[13]}');//[12] 
	$("#rel_depot_sus_code").val('${rio_data[12]}');
	$("#remarks").val('${rio_data[18]}');
	$("#ro_qty").val('${rio_data[19]}');
	$("#ro_date").val('${rio_data[20]}');
	$("#comd_sus").val('${rio_data[23]}');
	$("#ro_id").val('${rio_data[24]}');
	$("#type_of_stk").val('${rio_data[25]}');
	$("#stk_code").val('${rio_data[26]}');
	
	if(r != ""){
		var k = '${rio_data[14]}';
		$("#census_no").val(k); 
	}
	
	var d1 = '${ml_2[0]}';
	if(d1 == "" || d1 == null){
		$("#ro_ue").val("0");
	}else{
		$("#ro_ue").val(d1);
	}
	
	$("#ro_uh").val('${ml_3[0]}');
		
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 	
	}catch(e){
		// TODO: handle exception
	}
	     
});

</script>

<script>	
function btn_clc(){
	location.reload(true);
}

function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
    if(charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){ 
    	return false;
    }else{
    	if(charCode == 46){
    		return false;
    	}
    }
    return true;
}

function validate() {
	var ro_ue = $('#ro_ue').val();
	var ro_uh = $('#ro_uh').val();
	var ro_qty = $('#ro_qty').val();
	var totalUh = parseInt(ro_uh) + parseInt(ro_qty);
	var ro_for_c = $('#ro_for_c').val();
	if(ro_for_c == '1'){
		if(parseInt(ro_ue) < parseInt(totalUh)){
			alert("Total Holding Qty ("+totalUh+") can`t be more then UE ("+ro_ue+")");
			$('#ro_qty').focus();
			return false;
		}
	}
	return true;
}
</script>