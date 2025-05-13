<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="actActionForm" id="actActionForm" htmlEscape="true"
	action="actabcAction_rpas" method="POST"
	commandName="tms_slot_masterCmd_avation">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>ALLOTMENT OF ABC GP FOR RPAS </h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-5">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ACT From</label>
								</div>
								<div class="col-12 col-md-7">
									<input type="text" id="code_no_from1" name="code_no_from1"
										maxlength="4" onkeypress="return isNumber0_9Only(event);"
										class="form-control" autocomplete="off" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-5">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Range in Qty</label>
								</div>
								<div class="col-12 col-md-7">
									<input type="text" id="range" name="range"
										placeholder=" Max 4 digit" maxlength="4"
										onkeypress="return isNumber0_9Only(event);"
										class="form-control" autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-5">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ABC Gp Nomenclature </label>
								</div>
								<div class="col-12 col-md-7">
									<form:input type="text" id="group_name" path="group_name"
										class="form-control" maxlength="35"
										oninput="this.value = this.value.toUpperCase()"
										autocomplete="off" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-5">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ABC Gp Avation</label>
								</div>
								<div class="col-12 col-md-7">
									<form:input type="text" id="abc_code" path="abc_code"
										class="form-control" maxlength="5"
										onkeypress="return isNumber0_9Only(event);" autocomplete="off" />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<!--             				<div class="col-md-6"> -->
						<!--             					<div class="row form-group"> -->
						<!--             					    <div class="col col-md-5"> -->
						<!--                   						  <label class=" form-control-label"><strong style="color: red;">* </strong>Avation Class Code</label> -->
						<!--                 					</div>  -->
						<!--                 					<div class="col-12 col-md-7"> -->
						<%--                   						<form:input type="text" id="vehicle_class_code" path="vehicle_class_code" class="form-control" maxlength="1" style="text-transform:uppercase"  onkeypress="return onlyAlphabets(event);" autocomplete="off"/> --%>
						<!--                 					</div> -->
						<!--               					</div> -->
						<!--             				</div> -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-5">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Type of AVN Assets </label>
								</div>
								<div class="col-12 col-md-7">
									<select name="type_of_aircraft" id="type_of_aircraft"
										class=" form-control">
										<!-- <option selected value="">--Select--</option> -->
										<!-- <option selected value="ROTARY WING">ROTARY WING</option> -->
									 	<option selected value="RPAS">RPAS</option> 
									</select>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<input type="submit" class="btn btn-primary btn-sm" value="Save"
						onclick="return validate()"> <input type="reset"
						class="btn btn-success btn-sm" value="Clear">
				</div>
			</div>
		</div>

	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {

		$.post("getMaxToCode_avation?" + key + "=" + value, {}, function(j) {
		}).done(function(j) {
			var length = j.length - 1;
			var enc = j[length].substring(0, 16);
			var max = parseInt(dec(enc, j[0])) + 1;
			$("input#code_no_from1").val(max);
		}).fail(function(xhr, textStatus, errorThrown) {
		});

		$.post("getmaxPRFCodeID_avation?" + key + "=" + value, {}, function(j) {
		}).done(function(j) {
			var max1 = ('000' + j).substr(-3);
			$("#abc_code").val(max1);
		}).fail(function(xhr, textStatus, errorThrown) {
		});

	});

	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}

	function onlyAlphabets(e, t) {
		return (e.charCode > 64 && e.charCode < 91)
				|| (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;
	}

	function validate() {

		if ($("input#code_no_from1").val().charAt(0) == "0") {
			alert("First 0 not allowed Code Number From.");
			$("input#code_no_from1").focus();
			return false;
		}
		if ($("input#code_no_from1").val() == "") {
			alert("Please Enter MCT Number From.");
			$("input#code_no_from1").focus();
			return false;
		}
		if ($("#range").val() == "") {
			alert("Please Enter Range in Qty.");
			$("#range").focus();
			return false;
		}
		if ($("#group_name").val() == "") {
			alert("Please Enter ABC Group Nomenclature.");
			$("#group_name").focus();
			return false;
		}
		if ($("#abc_code").val() == "") {
			alert("Please Enter ABC Code.");
			$("#prf_code").focus();
			return false;
		}
		if ($("#abc_code").val().length != 3) {
			alert("Please Enter 3 Digit ABC Code.");
			$("#prf_code").focus();
			return false;
		}
	 	if($("#type_of_aircraft").val() == ""){
		 		alert("Please select the type of aircraft.");
		 		$("#type_of_aircraft").focus();
		 		return false;
		 	}  
		return true;
	}
</script>