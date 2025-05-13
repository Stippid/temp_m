<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="Edit_Offic_Admi_AmcAdcMns"
	id="Edit_Offic_Admi_AmcAdcMns" action="Edit_Offic_Admi_AmcAdcMnsAction"
	method='POST' commandName="Edit_Offic_Admi_AmcAdcMnsCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>EDIT AMC/ADC/MNS OFFICERS ADMISSIONS</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be entered by
						Medical Unit)</span>
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Hospital Name</label>
								</div>
								<div class="col-md-9">
									<input type="text" id="unit_name" name="unit_name"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off"
										title="Type Unit Name or Part of Unit Name to Search"
										readonly="readonly" /> <input type="hidden"
										id="persnl_no_Original" name="persnl_no_Original"
										class="form-control-sm form-control"> <input
										type="hidden" id="dt_report1Original"
										name="dt_report1Original" class="form-control-sm form-control">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>SUS No</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="sus_no" path="sus_no"
										class="form-control-sm form-control" placeholder="Search..."
										maxlength="8" autocomplete="off"
										title="Type SUS No or Part of SUS No to Search"
										readonly="true" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Service</label>
								</div>

								<div class="col-md-8">
									<select id="service" name="service"
										class="form-control-sm form-control" readonly="true">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCodeSERVICE}"
											varStatus="num">
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
										<c:forEach var="item" items="${getMedSystemCodePERSNL_PRE}"
											varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-4">
									<form:input type="text" id="persnl_no2" path="persnl_no"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="Enter No..."
										onkeypress="return isNumber0_9Only(event)" maxlength="10" />
								</div>
								<div class="col-md-4">
									<select id="persnl_no3" name="persnl_no3"
										class="form-control-sm form-control"
										onchange="personal_number();">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCodePERSNL_SUF}"
											varStatus="num">
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
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Personnel Name</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="persnl_name" path="persnl_name"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="Enter Personnel Name..."
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()"
										maxlength="100" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Appointment</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="appointment" path="appointment"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="Enter Appointment..."
										onkeypress="return onlyAlphaNumeric(event);"
										oninput="this.value = this.value.toUpperCase()" maxlength="50" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Category</label>
								</div>
								<div class="col-md-8">
									<select name="category" id="category"
										class="form-control-sm form-control"
										onchange="category_change(this);">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCodeCATEGORY}"
											varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Rank</label>
								</div>
								<div class="col-md-8">
									<select name="a_rank.id" id="rank"
										data-placeholder="Select the Rank..."
										class="form-control-sm form-control"
										onchange="rank_change(this);">
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
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;"> </strong>PSG Rank</label>
								</div>
								<div class="col-md-8">

									<input type="text" id="daily_rank" name="daily_rank"
										class="form-control-sm form-control" readonly
										autocomplete="off">

								</div>
							</div>
						</div>

					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Gender</label>
								</div>
								<div class="col-md-8">
									<select name="sex" id="sex"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Aadhar
										Card No.</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="adhar_card_no" path="adhar_card_no"
										class="form-control-sm form-control" maxlength="12"
										onkeypress="return isNumber0_9Only(event)"
										placeholder=" Please Enter 12 Digit Aadhar card No..."
										autocomplete="off" />
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Service Status</label>
								</div>
								<div class="col-md-8">
									<select name="type" id="type"
										class="form-control-sm form-control">
										<option value="-1">--Select the Value--</option>
										<option value="serv">Serving</option>
										<option value="ex_serv">Ex-Servicemen</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Date of Reporting</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="dt_report1" name="dt_report1"
										class="form-control-sm form-control" min="1899-01-01"
										max="${date}">
								</div>
							</div>
						</div>
					</div>



					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"
										style="margin-left: 11px;">DOB</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="date_of_birth1" name="date_of_birth1"
										autocomplete="off" class="form-control-sm form-control"
										onchange="calculate_age_new(this);" maxlength="3"
										min="1899-01-01" max="${date}">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Unit</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="persnl_unit" autocomplete="off"
										path="persnl_unit" class="form-control-sm form-control"
										placeholder="Enter Unit..."
										onkeypress="return onlyAlphaNumeric(event);"
										oninput="this.value = this.value.toUpperCase()"
										maxlength="250" />
								</div>
							</div>
						</div>



					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"
										style="margin-left: 11px;">Age (Years)</label>
								</div>
								<div class="col-md-8">
									<div class="row form-group">
										<div class="col-md-6">
											<input type="text" id="age_year" name="age_year" value="0"
												autocomplete="off" class="form-control-sm form-control"
												maxlength="3" onkeypress="return isNumber0_9Only(event)"
												placeholder="Enter Age..." readonly>
										</div>
										<div class="col-md-6">
											<input type="text" id="age_month" name="age_month"
												class="form-control-sm form-control" autocomplete="off"
												placeholder="Enter Month..." readonly>
										</div>
									</div>
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
										value="${Edit_Offic_Admi_AmcAdcMnsCMD.contact_no}"
										autocomplete="off" class="form-control-sm form-control"
										onkeypress="return isNumber0_9Only(event)" maxlength="10"
										placeholder=" Please Enter 10 Digit Mobile No...">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Date/Time of Admission</label>
								</div>
								<div class="col-md-8">
									<input type="datetime-local" id="dt_admission"
										name="date_time_admission1"
										class="form-control-sm form-control" min="1899-01-01"
										max="${date}">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Relation</label>
								</div>
								<div class="col-md-8">
									<select id="relation" name="relation"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_5}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<%--  <div class="col-md-12 from-group">
				<div class="col-md-2">
						<label for="text-input" class=" form-control-label"><strong	style="color: red;">* </strong> Diagnosis</label>
			    </div>
			  	<div class="col-md-10" >
						 <input type="text" id="diagnosis" name="icd_code1" class="form-control-sm form-control" 
						 placeholder="Search..." autocomplete="off" value="${icd_code_desc}" />
						  <input type="hidden" id="diagnosis_id" name="adm_icd_code.id" class="form-control-sm form-control" 
							placeholder="Search..." autocomplete="off" value="${icd_code_id}" >
	            </div>
	    </div> --%>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Diagnosis ICD Code</label>
								</div>

								<div class="col-md-8">
									<input type="text" name="diagnosis_code1d"
										onkeypress="return onlyAlphaNumeric(event);"
										id="diagnosis_code1d" class="form-control-sm form-control"
										autocomplete="off" placeholder="Search...">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Diagnosis
										ICD Cause</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="icd_cause_code1d"
										onkeypress="return onlyAlphaNumeric(event);"
										id="icd_cause_code1d" class="form-control-sm form-control"
										autocomplete="off" placeholder="Search..." readonly>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 from-group">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">
								Remarks</label>
						</div>
						<div class="col-md-10">
							<textarea rows="2" cols="250"
								onkeypress="return onlyAlphaNumeric(event);"
								class="form-control char-counter1"
								placeholder="Enter Your Remarks..." id="remarks" name="remarks"
								maxlength="255"></textarea>
						</div>
					</div>
				</div>
			</div>

			<input type="hidden" id="id" name="id"
				value="${Edit_Offic_Admi_AmcAdcMnsCMD.id}" class="form-control"
				autocomplete="off">

			<div class="card-footer" align="center">
				<a href="mnh_search_adm_officersDA" class="btn btn-danger btn-sm">Cancel</a>
				<input type="submit" id="btn_save" class="btn btn-success btn-sm"
					value="Update" onclick="return isvalidData();" />

			</div>
		</div>
	</div>
</form:form>


<script>
	$("input#persnl_unit").keypress(
			function() {
				var unit_name = this.value;
				var susNoAuto = $("#persnl_unit");
				susNoAuto.autocomplete({
					source : function(request, response) {
						$.ajax({
							type : 'POST',
							url : "getTargetUnitsNameActiveListnew?" + key
									+ "=" + value,
							data : {
								unit_name : unit_name
							},
							success : function(data) {
								var susval = [];
								var length = data.length - 1;
								var enc = data[length].substring(0, 16);
								for (var i = 0; i < data.length; i++) {
									susval.push(dec(enc, data[i]));
								}

								response(susval);
							}
						});
					},
					minLength : 1,
					autoFill : true,
					change : function(event, ui) {
						if (ui.item) {
							return true;
						}

					}

				});
			});

	function isvalidData() {
		var rel = $("#relation").val();

		if ($("#sus_no").val() == "") {
			alert("Please Enter the SUS No");
			$("#sus_no").focus();
			return false;
		}

		if ($("#service").val() == "-1") {

			var p1 = $("#persnl_no1").val();
			var p2 = $("#persnl_no2").val();
			var p3 = $("#persnl_no3").val();
			var unit = $("#unit_name").val();
			var persnl_name = $("#persnl_name").val();
			var dt_adm = $("#date_time_admission").val();
			var p = p1.concat(p2).concat(p3);
			var diag = $("#diagnosis").val();

		} else {

			if ($("#service").val() == "-1") {
				alert("Please Select the Service");
				$("#service").focus();
				return false;
			}
			if ($("#type").val() == "-1") {
				alert("Please Select the Service Status");
				$("#type").focus();
				return false;
			}
			var adhar_no = ($("#adhar_card_no").val());
			if (!adhar_no == "" && adhar_no != "0" && adhar_no != null
					&& (adhar_no.length != 12 && adhar_no.length != 14)) {
				alert("Adhaar No Should Contain Maximum 12 Digit");
				return false;
			}
			if ($("#dt_report").val() == "-1") {
				alert("Please Select the Date of Report");
				$("#dt_report").focus();
				return false;
			}
			if ($("#service").val() == "NAVY"
					|| $("#service").val() == "AIRFORCE") {
				if ($("#persnl_no2").val() == ""
						&& $("#persnl_no3").val() == "") {
					alert("Please Enter the Personnel No");
					$("#persnl_no2").focus();
					$("#persnl_no3").focus();
					return false;
				}
			}
			if ($("#service").val() == "ARMY") {
				if ($("#persnl_no1").val() == "-1"
						&& $("#persnl_no2").val() == ""
						&& $("#persnl_no3").val() == "-1") {
					alert("Please Enter the Personnel No");
					$("#persnl_no2").focus();
					$("#persnl_no3").focus();
					$("#persnl_no1").focus();
					return false;
				}
			}
			if ($("#persnl_no2").val() == "" && $("#service").val() != "OTHERS") {
				alert("Please Enter the Personnel No");
				$("#persnl_no2").focus();
				return false;
			}
			if ($("#persnl_no3").val() == "-1"
					&& $("#service").val() != "OTHERS") {
				alert("Please Select the Personnel No");
				$("#persnl_no3").focus();
				return false;
			}
			if ($("#category").val() == "-1" && $("#service").val() != "OTHERS") {
				alert("Please Select the Category");
				$("#category").focus();
				return false;
			}
			if ($("#rank").val() == "-1" && $("#service").val() != "OTHERS") {
				alert("Please Select the Rank");
				$("#rank").focus();
				return false;
			}
			if ($("#sex").val() == "-1") {
				alert("Please Select the Gender");
				$("#sex").focus();
				return false;
			}
			if (rel == "-1") {
				alert("Please Select the Relation");
				$("#relation").focus();
				return false;
			}

			if ($("#category").val() == "MNS" && $("#sex").val() != "Female") {
				alert("category is MNS then gender should be Female(F) only.");
				$("#sex").focus();
				return false;
			}

			if ((rel == "WIFEOF" || rel == "MOTHEROF" || rel == "DAUGHTEROF" || rel == "SISTEROF")
					&& ($("#sex").val() != "Female")) {
				alert("Gender should be Female Here");
				$("#sex").focus();
				return false;
			}
			if ((rel == "BROTHEROF" || rel == "HUSBANDOF" || rel == "FATHEROF" || rel == "SONOF")
					&& ($("#sex").val() != "Male")) {
				alert("Gender should be Male Here");
				$("#sex").focus();
				return false;
			}
			if ($("#persnl_name").val().trim() == "") {
				alert("Please Enter the Personnal Name");
				$("#persnl_name").focus();
				return false;
			}

			if ($("#date_time_admission").val() == "") {
				alert("Please Select the Date of Admission");
				$("#date_time_admission").focus();
				return false;
			}
			var b_1 = $("#diagnosis_code1d").val().split("-");
			var b_2 = b_1[0].substring(0, 1).toUpperCase();
			var d_code = $("#diagnosis_code1d").val().split("-");
			var d_code_p = $("#diagnosis_code1d").val().substring(0, 3)
					.toUpperCase();
			if ((b_2 == "S" || b_2 == "T" || b_2 == "X" || b_2 == "V"
					|| b_2 == "W" || b_2 == "Y")
					&& ($("#icd_cause_code1d").val() == "")) {
				alert("Please Enter ICD Cause");
				$("#icd_cause_code1d").focus();
				return false;
			} else {
				var p1 = $("#persnl_no1").val();
				var p2 = $("#persnl_no2").val();
				var p3 = $("#persnl_no3").val();
				var unit = $("#unit_name").val();
				var persnl_name = $("#persnl_name").val();
				var dt_adm = $("#date_time_admission").val();
				var p = p1.concat(p2).concat(p3);
				var diag = $("#diagnosis").val();

			}
		}
	}
	function chgCategory() {
		if ($("#service").val() == "NAVY" || $("#service").val() == "AIRFORCE") {
			$("#persnl_no1").attr('readonly', true);
			$("#persnl_no2").attr('readonly', false);
			$("#persnl_no3").attr('readonly', false);
			$("#category").attr('readonly', false);

			$("#category option[value='OFFICER']").show();
			$("#category option[value='MNS']").show();
			$("#category option[value='JCO']").show();

			$("#persnl_no1").val('-1');
			$("#category").val('-1')
		}

		if ($("#service").val() == "OTHERS") {
			$("#persnl_no1").attr('readonly', true);
			$("#persnl_no2").attr('readonly', true);
			$("#persnl_no3").attr('readonly', true);
			$("#category").attr('readonly', true);

			$("#category option[value='OFFICER']").hide();
			$("#category option[value='MNS']").hide();
			$("#category option[value='JCO']").hide();

			$("#persnl_no1").val('-1');
			$("#persnl_no2").val('');
			$("#persnl_no3").val('-1');
			$("#category").val('-1')
		}

		if ($("#service").val() == "ARMY") {
			$("#persnl_no1").attr('readonly', false);
			$("#persnl_no2").attr('readonly', false);
			$("#persnl_no3").attr('readonly', false);
			$("#category").attr('readonly', true);

			$("#category option[value='OFFICER']").show();
			$("#category option[value='MNS']").show();
			$("#category option[value='JCO']").show();

			$("#persnl_no2").val('');
			$("#persnl_no3").val('-1');

			if ($("#persnl_no1").val() == "-1") {
				$("#category").val('OR');
			} else if ($("#persnl_no1").val() == "TJ"
					|| $("#persnl_no1").val() == "JC") {
				$("#category").val('JCO');
			} else if ($("#persnl_no1").val() == "NR"
					|| $("#persnl_no1").val() == "NS"
					|| $("#persnl_no1").val() == "NL"
					|| $("#persnl_no1").val() == "PN") {
				$("#category").val('MNS');
			} else if ($("#persnl_no1").val() != "NR"
					|| $("#persnl_no1").val() != "NS"
					|| $("#persnl_no1").val() != "NL"
					|| $("#persnl_no1").val() != "PN"
					|| $("#persnl_no1").val() != "TJ"
					|| $("#persnl_no1").val() != "JC") {
				$("#category").val('OFFICER');
			}
		}
	}

	function getRank() {
		var category = $("#category").val();
		var service = $("#service").val();
		$("#rank").attr('readonly', false);
		$
				.post(
						"getMNHRank?" + key + "=" + value,
						{
							a1 : category,
							a2 : service
						},
						function(j) {
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
								options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'
										+ datap[0] + '</option>';
							}
							$("#rank").html(options);
							var q = '${Edit_Offic_Admi_AmcAdcMnsCMD.a_rank.id}';
							if (q != "") {
								$("#rank").val(q);
							}
						});
	}

	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)) {
			return false;
		} else {
			if (charCode == 46) {
				return false;
			}
		}
		return true;
	}

	jQuery(function() {
		// Source SUS No

		jQuery("#sus_no")
				.keypress(
						function() {
							var sus_no = this.value;
							var susNoAuto = jQuery("#sus_no");
							susNoAuto
									.autocomplete({
										source : function(request, response) {
											jQuery
													.ajax({
														type : 'POST',
														url : "getTargetSUSNoList?"
																+ key
																+ "="
																+ value,
														data : {
															sus_no : sus_no
														},
														success : function(data) {
															var susval = [];
															var length = data.length - 1;
															if (data.length != 0) {
																var enc = data[length]
																		.substring(
																				0,
																				16);
															}
															for (var i = 0; i < data.length; i++) {
																susval
																		.push(dec(
																				enc,
																				data[i]));
															}
															var dataCountry1 = susval
																	.join("|");
															var myResponse = [];
															var autoTextVal = susNoAuto
																	.val();
															jQuery
																	.each(
																			dataCountry1
																					.toString()
																					.split(
																							"|"),
																			function(
																					i,
																					e) {
																				var newE = e
																						.substring(
																								0,
																								autoTextVal.length);
																				if (e
																						.toLowerCase()
																						.includes(
																								autoTextVal
																										.toLowerCase())) {
																					myResponse
																							.push(e);
																				}
																			});
															response(myResponse);
														}
													});
										},
										minLength : 1,
										autoFill : true,
										change : function(event, ui) {
											if (ui.item) {
												return true;
											} else {
												alert("Please Enter Approved SUS No.");
												document
														.getElementById("sus_no").value = "";
												susNoAuto.val("");
												susNoAuto.focus();
												return false;
											}
										},
										select : function(event, ui) {
											var unit_sus_no = ui.item.value;

											$
													.post(
															"getActiveUnitNameFromSusNo?"
																	+ key + "="
																	+ value,
															{
																sus_no : unit_sus_no
															})
													.done(
															function(j) {
																if (j == "") {
																	alert("Please Enter Approved Unit SUS No.");
																	document
																			.getElementById("unit_name").value = "";
																	susNoAuto
																			.val("");
																	susNoAuto
																			.focus();
																} else {
																	var length = j.length - 1;
																	var enc = j[length]
																			.substring(
																					0,
																					16);
																	$(
																			"#unit_name")
																			.val(
																					dec(
																							enc,
																							j[0]));

																}
															})
													.fail(
															function(xhr,
																	textStatus,
																	errorThrown) {
															});
										}
									});
						});
		// End

		// Source Unit Name

		jQuery("#unit_name")
				.keypress(
						function() {
							var unit_name = this.value;
							var susNoAuto = jQuery("#unit_name");
							susNoAuto
									.autocomplete({
										source : function(request, response) {
											jQuery
													.ajax({
														type : 'POST',
														url : "getTargetUnitsNameActiveList?"
																+ key
																+ "="
																+ value,
														data : {
															unit_name : unit_name
														},
														success : function(data) {
															var susval = [];
															var length = data.length - 1;
															if (data.length != 0) {
																var enc = data[length]
																		.substring(
																				0,
																				16);
															}
															for (var i = 0; i < data.length; i++) {
																susval
																		.push(dec(
																				enc,
																				data[i]));
															}
															var dataCountry1 = susval
																	.join("|");
															var myResponse = [];
															var autoTextVal = susNoAuto
																	.val();
															jQuery
																	.each(
																			dataCountry1
																					.toString()
																					.split(
																							"|"),
																			function(
																					i,
																					e) {
																				var newE = e
																						.substring(
																								0,
																								autoTextVal.length);
																				if (e
																						.toLowerCase()
																						.includes(
																								autoTextVal
																										.toLowerCase())) {
																					myResponse
																							.push(e);
																				}
																			});
															response(myResponse);
														}
													});
										},
										minLength : 1,
										autoFill : true,
										change : function(event, ui) {
											if (ui.item) {
												return true;
											} else {
												alert("Please Enter Approved Unit Name.");
												document
														.getElementById("unit_name").value = "";
												susNoAuto.val("");
												susNoAuto.focus();
												return false;
											}
										},
										select : function(event, ui) {
											var target_unit_name = ui.item.value;

											$
													.post(
															"getTargetSUSFromUNITNAME?"
																	+ key + "="
																	+ value,
															{
																target_unit_name : target_unit_name
															})
													.done(
															function(j) {
																var length = j.length - 1;
																var enc = j[length]
																		.substring(
																				0,
																				16);
																jQuery(
																		"#sus_no")
																		.val(
																				dec(
																						enc,
																						j[0]));
															})
													.fail(
															function(xhr,
																	textStatus,
																	errorThrown) {
															});
										}
									});
						});

	});

	$("#diagnosis").keyup(function() {
		var d1 = this.value;
		$().Autocomplete2('GET', 'getMNHDistinctICDList', diagnosis, {
			a : d1,
			b : "ALL"
		}, 'getMNHDistinctICDList_ID', diagnosis_id, '', '', '');
	});
</script>

<script>
	$(document)
			.ready(
					function() {
						personal_number();
						var category = $("#category").val();
						var service = $("#service").val();
						$("#rank").attr('readonly', false);

						$("#appointment").val(
								'${Edit_Offic_Admi_AmcAdcMnsCMD.appointment}');
						$("#contact_no").val(
								'${Edit_Offic_Admi_AmcAdcMnsCMD.contact_no}');

						$().getCurDt(dt_report1);

						$("#diagnosis_code1d")
								.val(
										'${Edit_Offic_Admi_AmcAdcMnsCMD.diagnosis_code1d}');
						$("#icd_cause_code1d")
								.val(
										'${Edit_Offic_Admi_AmcAdcMnsCMD.icd_cause_code1d}');

						if ('${Edit_Offic_Admi_AmcAdcMnsCMD.diagnosis_code1d}' != "") {

							$
									.post(
											"getMNHICDCodeToCauseVi?" + key
													+ "=" + value,
											{
												a : '${Edit_Offic_Admi_AmcAdcMnsCMD.diagnosis_code1d}',
												b : "2"
											},
											function(j) {
												var enc = j[j.length - 1]
														.substring(0, 16);
												var a = dec(enc, j[0]);
												var data = '${Edit_Offic_Admi_AmcAdcMnsCMD.diagnosis_code1d}'
														+ "-" + a;
												$("#diagnosis_code1d")
														.val(data);

												var b = '${Edit_Offic_Admi_AmcAdcMnsCMD.diagnosis_code1d}'
														.substring(0, 1);
												if (b == "S" || b == "T"
														|| b == "V" || b == "W"
														|| b == "X" || b == "Y") {
													$("#icd_cause_code1d")
															.attr('readonly',
																	false);
												}
											});
						}

						if ('${Edit_Offic_Admi_AmcAdcMnsCMD.icd_cause_code1d}' != "") {
							$
									.post(
											"getMNHICDCodeToCauseVi?" + key
													+ "=" + value,
											{
												a : '${Edit_Offic_Admi_AmcAdcMnsCMD.icd_cause_code1d}',
												b : "2"
											},
											function(j) {
												var enc = j[j.length - 1]
														.substring(0, 16);
												var a = dec(enc, j[0]);
												var data = '${Edit_Offic_Admi_AmcAdcMnsCMD.icd_cause_code1d}'
														+ "-" + a;
												$("#icd_cause_code1d")
														.val(data);

											});
						}
						var l = '${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}'.length;
						$("#service").val(
								'${Edit_Offic_Admi_AmcAdcMnsCMD.service}');
						$("#type").val('${Edit_Offic_Admi_AmcAdcMnsCMD.type}');
						$.post("getMNHUnitNameBySUSNo?" + key + "=" + value, {
							y : '${Edit_Offic_Admi_AmcAdcMnsCMD.sus_no}'
						}, function(j) {

							var enc = j[j.length - 1].substring(0, 16);
							var a = dec(enc, j[0]);
							$("#unit_name").val(a);
						});

						if ('${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}' == '-2') {
							$("#persnl_no_Original").val('-2');
						}

						if ('${Edit_Offic_Admi_AmcAdcMnsCMD.category}' == "OR"
								&& '${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "ARMY") {
							$("#persnl_no2").val(
									'${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}'
											.substring(0, l - 1));
							$("#persnl_no3").val(
									'${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}'
											.substring(l - 1, l));
						} else if ('${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "AIRFORCE"
								|| '${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "NAVY") {
							$("#persnl_no2").val(
									'${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}'
											.substring(0, l - 1));
							$("#persnl_no3").val(
									'${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}'
											.substring(l - 1, l));
						} else if ('${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "ARMY"
								&& '${Edit_Offic_Admi_AmcAdcMnsCMD.category}' != "OR") {
							$("#persnl_no1").val(
									'${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}'
											.substring(0, 2));
							$("#persnl_no2").val(
									'${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}'
											.substring(2, l - 1));
							$("#persnl_no3").val(
									'${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}'
											.substring(l - 1, l));
						} else if ('${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "OTHERS") {
							$("#persnl_no1").val('-1');
							$("#persnl_no2").val('');
							$("#persnl_no3").val('-1');
						} else if ('${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "-1") {
							$("#persnl_no1").val('-1');
							$("#persnl_no2").val('');
							$("#persnl_no3").val('-1');

							$("#service").attr('readonly', false);
							$("#category").attr('readonly', false);
						}

						if ('${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "NAVY"
								|| '${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "AIRFORCE") {
							$("#persnl_no1").attr('readonly', true);
							$("#category").attr('readonly', false);
						}
						if ('${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "OTHERS") {
							$("#persnl_no1").attr('readonly', true);
							$("#persnl_no2").attr('readonly', true);
							$("#persnl_no3").attr('readonly', true);
							$("#category").attr('readonly', true);
							$("#rank").attr('readonly', true);

							$("#category option[value='OFFICER']").hide();
							$("#category option[value='MNS']").hide();
							$("#category option[value='JCO']").hide();
						}
						if ('${Edit_Offic_Admi_AmcAdcMnsCMD.service}' == "ARMY") {
							$("#category").attr('readonly', true);
						}

						if ('${Edit_Offic_Admi_AmcAdcMnsCMD.service}' != "OTHERS") {
							$("#category").val(
									'${Edit_Offic_Admi_AmcAdcMnsCMD.category}');
							getRank();
						}

						var dt_report = '${Edit_Offic_Admi_AmcAdcMnsCMD.dt_report}'
								.substring(0, 10);
						$("#dt_report1").val(dt_report);
						$("#dt_report1Original").val(dt_report);

						var date_of_birth = '${Edit_Offic_Admi_AmcAdcMnsCMD.date_of_birth}'
								.substring(0, 10);

						$("#date_of_birth1").val(date_of_birth);
						$("#sex").val('${Edit_Offic_Admi_AmcAdcMnsCMD.sex}');
						$("#relation").val(
								'${Edit_Offic_Admi_AmcAdcMnsCMD.relation}');
						var d2 = new Date(
								'${Edit_Offic_Admi_AmcAdcMnsCMD.date_time_admission}');
						var c_d2 = d2.getFullYear() + "-"
								+ ("0" + (d2.getMonth() + 1)).slice(-2) + "-"
								+ ("0" + d2.getDate()).slice(-2) + "T"
								+ ("0" + d2.getHours()).slice(-2) + ":"
								+ ("0" + d2.getMinutes()).slice(-2);
						$("#dt_admission").val(c_d2);

						$("#remarks").text(
								'${Edit_Offic_Admi_AmcAdcMnsCMD.remarks}');

						$('#service').change(
								function() {
									chgCategory();

									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});

						$('#persnl_no1').change(
								function() {
									chgCategory();

									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});

						$('#category').change(
								function() {
									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});

					});
</script>
<Script>
	(function($) {
		"use strict";
		$.fn.charCounter = function(options) {
			if (typeof String.prototype.format == "undefined") {
				String.prototype.format = function() {
					var content = this;
					for (var i = 0; i < arguments.length; i++) {
						var replacement = '{' + i + '}';
						content = content.replace(replacement, arguments[i]);
					}
					return content;
				};
			}
			var options = $.extend({
				backgroundColor : "#FFFFFF",
				position : {
					right : 10,
					top : 10
				},
				font : {
					size : 10,
					color : "#a59c8c"
				},
				limit : 250
			}, options);
			return this.each(function() {
				var el = $(this), wrapper = $("<div/>").addClass(
						'focus-textarea').css({
					"position" : "relative",
					"display" : "inline-block"
				}), label = $("<span/>").css({
					"zIndex" : 999,
					"backgroundColor" : options.backgroundColor,
					"position" : "absolute",
					"font-size" : options.font.size,
					"color" : options.font.color
				}).css(options.position);
				if (options.limit > 0) {
					label
							.text("{0}/{1}".format(el.val().length,
									options.limit));
					el.prop("maxlength", options.limit);
				} else {
					label.text(el.val().length);
				}
				el.wrap(wrapper);
				el.before(label);
				el.on("keyup", updateLabel).on("keypress", updateLabel).on(
						'keydown', updateLabel);
				function updateLabel(e) {
					if (options.limit > 0) {
						label.text("{0}/{1}".format($(this).val().length,
								options.limit));
					} else {
						label.text($(this).val().length);
					}
				}
			});
		}
	})(jQuery);
	$(".char-counter1").charCounter();
</Script>
<script>
	$("#diagnosis_code1d").keyup(function() {
		var code = this.value;
		$().Autocomplete2('GET', 'getMNHDistinctICDList', diagnosis_code1d, {
			a : code,
			b : "ALL"
		}, '', '', '', '', '');
	});

	$("#diagnosis_code1d").change(
			function() {
				var a = this.value.split("-");
				var b = a[0].substring(0, 1).toUpperCase();

				if (b == "S" || b == "T" || b == "V" || b == "W" || b == "X"
						|| b == "Y") {
					$("#icd_cause_code1d").attr('readonly', false);

					$("#icd_cause_code1d").keyup(
							function() {
								var code = this.value;

								$().Autocomplete2('GET',
										'getMNHDistinctICDList',
										icd_cause_code1d, {
											a : code,
											b : "ALL2"
										}, '', '', '', '', '');
							});
				} else {
					$("#icd_cause_code1d").attr('readonly', true);
					$("#icd_cause_code1d").val('');
				}
			});

	$("#icd_cause_code1d").keyup(function() {
		var code = this.value;
		$().Autocomplete2('GET', 'getMNHDistinctICDList', icd_cause_code1d, {
			a : code,
			b : "ALL2"
		}, '', '', '', '', '');
	});

	var b_1 = '${list[0].diagnosis_code1d}'.split("-");
	var b_2 = b_1[0].substring(0, 1).toUpperCase();

	if (b_2 == "S" || b_2 == "T" || b_2 == "V" || b_2 == "W" || b_2 == "X"
			|| b_2 == "Y") {
		$("#icd_cause_code1d").attr('readonly', false);

		if ('${list[0].icd_cause_code1d}' != "") {
			$.post("getMNHICDCodeToCauseVi?" + key + "=" + value, {
				a : '${list[0].icd_cause_code1d}',
				b : "2"
			}, function(j) {
				if (j != "") {
					var enc = j[j.length - 1].substring(0, 16);
					var a = dec(enc, j[0]);
					var data = '${list[0].icd_cause_code1d}' + "-" + a;
					$("#icd_cause_code1d").val(data);
				} else {

					$("#icd_cause_code1d").val('');
				}
			});
		}
	}
</script>
<script>
	function personal_number() {
		var a1 = $("select#persnl_no1").val();
		var a2 = $("input#persnl_no2").val();
		var a3 = $("select#persnl_no3").val();
		var personnel_no = '${Edit_Offic_Admi_AmcAdcMnsCMD.persnl_no}';

		$.ajaxSetup({
			async : false
		});

		$.post("GetPersonnelNoDatamnh?" + key + "=" + value, {
			personnel_no : personnel_no
		}).done(function(j) {

			v = j.length;
			if (v == 1) {
				$("#category").val("OFFICER");
				$("#service").val("ARMY");
				$("#adhar_card_no1").val(j[0].adhar_number);
				$("#date_of_birth1").val(j[0].date_of_birth);
				$("#persnl_name").val(j[0].name);
				$("#sex").val(j[0].gender_name);
				$("#daily_rank").val(j[0].description);

				$("#appointment").val(j[0].appointment);
				$("#persnl_unit").val(j[0].unit_name);
				$("#contact_no").val(j[0].mobile_no);

				var from_d = j[0].date_of_birth;

				var from_d1 = from_d.substring(0, 4);
				var from_d2 = from_d.substring(7, 5);
				var from_d3 = from_d.substring(10, 8);
				var frm_d = from_d3 + "-" + from_d2 + "-" + from_d1;
				var today = new Date();
				var to_d3 = today.getDate();
				var to_d2 = today.getMonth() + 1;
				var to_d1 = today.getFullYear();
				var to_d0 = to_d3 + "-" + to_d2 + "-" + to_d1;
				if (to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3) {
					var year = to_d1 - from_d1
					var month = to_d2 - from_d2
				}
				if (to_d2 > from_d2 && to_d3 < from_d3) {
					var year = to_d1 - from_d1
					var month = to_d2 - from_d2 - 1
				}
				if (from_d2 > to_d2) {
					var year = to_d1 - from_d1 - 1
					var month1 = from_d2 - to_d2
					var month = 12 - month1
				}
				if (from_d2 == to_d2 && from_d3 > to_d3) {
					var year = to_d1 - from_d1 - 1
					var days = from_d3 - to_d3
				}
				if (from_d2 == to_d2 && to_d3 > from_d3) {
					var year = to_d1 - from_d1
					var days = to_d3 - from_d3

				}
				if (from_d2 == to_d2 && to_d3 == from_d3) {
					var year = to_d1 - from_d1
					var days = 0
				}
				//days
				if (from_d2 > to_d2 && from_d3 > to_d3) {
					var m = from_d2 - to_d2
					var n = m * 30
					var m1 = from_d3 - to_d3
					var m2 = n + m1
					var days = m2
				}
				if (from_d2 > to_d2 && to_d3 > from_d3) {
					var m = from_d2 - to_d2
					var n = m * 30
					var m1 = to_d3 - from_d3
					var m2 = n + m1
					var days = m2
				}
				if (to_d2 > from_d2 && to_d3 > from_d3) {
					var m = to_d2 - from_d2
					var n = m * 30
					var m1 = to_d3 - from_d3
					var m2 = n + m1
					var days = m2

				}
				if (to_d2 > from_d2 && from_d3 > to_d3) {
					var m = to_d2 - from_d2
					var n = m * 30
					var m1 = from_d3 - to_d3
					var m2 = n + m1
					var days = m2

				}
				$("#age_year").val(year);

				if (month == undefined) {
					month = 0;
				}
				document.getElementById('age_month').value = month;

			}

			getRank();
		});

	}
</script>